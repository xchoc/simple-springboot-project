package com.example.simple.springboot.project.dao.jpa;

import com.example.simple.springboot.project.dao.SectorDao;
import com.example.simple.springboot.project.entity.Sector;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class SectorDaoJpa implements SectorDao {

    @PersistenceContext
    private javax.persistence.EntityManager em;

    @Override
    public List<Sector> getAllSectors() {
        Query query = em.createQuery("select s from Sector s");
        return query.getResultList();
    }

    @Override
    public Sector getByValue(Integer value) {
        Sector result;
        Query query = em
                .createQuery("select s from Sector s where s.value = :value")
                .setParameter("value", value);
        List<Sector> sectors = query.getResultList();
        if (sectors == null || sectors.size() == 0) {
            return null;
        } else {
            result = sectors.get(0);
        }
        return result;
    }

    @Override
    public List<Sector> getSortedSectors() {
        Query query = em.createNamedQuery("Sector.getSortedSectors");
        return query.getResultList();
    }
}
