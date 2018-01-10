$(document).ready(function () {
    get_dropdown_list();

    $.when(get_dropdown_list()).done(function(response){
        // the code here will be executed when ajax request resolve.
        // response is list of length 3 containing the response text,
        // status, and jqXHR object for each of the ajax calls respectively.
        get_form_info();
    });

    $("#save-form").submit(function (event) {
        //stop submit the form, we will post it manually.
        event.preventDefault();
        fire_ajax_submit();
    });

    $("#logout").click(function(e) {
        e.preventDefault();
        // assumes we are not part of SSO so just logout of local session
        window.location = "${fn:replace(pageContext.request.requestURL, pageContext.request.requestURI, '')}/logout";
    });

});

function get_dropdown_list() {
    return $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/api/sectors",
        cache: false,
        timeout: 600000,
        success: function (data) {
            $.each(JSON.parse(data),function(i,obj)
            {
                var div_data="<option value="+obj.value+">"+obj.name+"</option>";
                $(div_data).appendTo('#sectors');
            });
            console.log("SUCCESS : ", data);
        },
        error: function (e) {
            console.log("ERROR : ", e);
        }
    });
}

function get_form_info() {
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/api/forminfo",
        cache: false,
        timeout: 600000,
        success: function (data) {
            $.each(JSON.parse(data),function(field, obj)
            {
                if (field == "name") {
                    $('#name').val(obj);
                }
                if (field == "agree") {
                    $('#agree-to-terms').attr('checked', true);
                }
                if (field == "sectors") {
                    $.each(obj, function (i, sector) {
                        $("#sectors option[value="+sector.value+"]").attr('selected', true);
                    });
                }
            });
            console.log("SUCCESS : ", data);
        },
        error: function (e) {
            console.log("ERROR : ", e);
        }
    });
}

function fire_ajax_submit() {

    var userform = {}
    userform["name"] = $("#name").val();
    userform["agree"] = $("#agree-to-terms").is(":checked");
    userform["sectors"] = $("#sectors").val();
    $("#btn-save").prop("disabled", true);

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/api/save",
        data: JSON.stringify(userform),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
            console.log("SUCCESS : ", data);
            toastr.success('Saved successfully')
            $("#btn-save").prop("disabled", false);
        },
        error: function (e) {
            console.log("ERROR : ", e);
            toastr.error('Error occurred')
            $("#btn-save").prop("disabled", false);
        }
    });

}