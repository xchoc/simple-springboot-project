package com.example.simple.springboot.project.controller;

import com.auth0.SessionUtils;
import com.auth0.json.auth.UserInfo;
import com.example.simple.springboot.project.dao.SectorDao;
import com.example.simple.springboot.project.dao.UserDao;
import com.example.simple.springboot.project.dto.UserForm;
import com.example.simple.springboot.project.entity.Sector;
import com.example.simple.springboot.project.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
@RestController
public class MainController {

    private final Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private SectorDao sectorDao;

    @Autowired
    private UserDao userDao;

    @Transactional
    @PostMapping("/api/save")
    public ResponseEntity<?> saveForm(final HttpServletRequest req, @Valid @RequestBody UserForm userForm, Errors errors) {
        final UserInfo userInfo = (UserInfo) SessionUtils.get(req, "userInfo");
        Map<String, Object> userInfoValues = userInfo.getValues();
        String message = null;
        //If error, just return a 400 bad request, along with the error message
        if (errors.hasErrors()) {
            message = errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(","));
            return ResponseEntity.badRequest().body(message);
        }

        List<Sector> sectors = new ArrayList<>();
        for (String sectorValue : userForm.getSectors()) {
            Sector sector = sectorDao.getByValue(Integer.valueOf(sectorValue));
            if (sector == null) {
                logger.info("Cannot find sector: " + sectorValue);
                continue;
            }
            sectors.add(sector);
        }

        User user = new User((String) userInfoValues.get("sub"), userForm.getName(), userForm.isAgree(), sectors);
        userDao.save(user);

        return ResponseEntity.ok(userForm);
    }

    @GetMapping("/api/forminfo")
    public ResponseEntity<?> getFormInfo(final HttpServletRequest req) throws JsonProcessingException {
        final UserInfo userInfo = (UserInfo) SessionUtils.get(req, "userInfo");
        User user = userDao.getById((String) userInfo.getValues().get("sub"));
        JSONObject formInfo = new JSONObject();
        if (user != null) {
            formInfo.put("name", user.getName());
            JSONArray sectors = new JSONArray();
            for (Sector sector : user.getSectors()) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("value", sector.getValue());
                jsonObject.put("name", sector.getName());
                sectors.add(jsonObject);
            }
            formInfo.put("sectors", sectors);
            formInfo.put("agree", user.isAgreeToTerms());
        }
        return ResponseEntity.ok(formInfo.toJSONString());
    }

    @GetMapping("/api/sectors")
    public ResponseEntity<?> getSectors() {
        JSONArray jsonArray = new JSONArray();
        List<Sector> sectors = sectorDao.getSortedSectors();
        if (sectors != null && !sectors.isEmpty()) {
            for (Sector sector : sectors) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("value", sector.getValue());
                jsonObject.put("name", sector.getName());
                jsonArray.add(jsonObject);
            }
        }
        return ResponseEntity.ok(jsonArray.toJSONString());
    }
}
