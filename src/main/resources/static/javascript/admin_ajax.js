console.log("admin page");

$(document).ready(function () {
    console.log("$(document).ready(function ())");

    //Форма добавления публикации
    let $addPublicationForm = $("#add_publication_form");

    //Отправка формы добавления публикации
    $addPublicationForm.on('submit', function (e) {
        console.log("$addPublicationForm entered");
        e.preventDefault();
        let formData = new FormData(this);
        $.ajax({
            type: 'POST',
            url: $addPublicationForm.attr('action'),
            enctype: "multipart/form-data",
            data: formData,
            processData: false,
            contentType: false,
        });
    });



    //Форма добавления работника
    let $addEmployeeForm = $("#add_employee_form");

    //Отправка формы добавления работника
    $addEmployeeForm.on('submit', function (e) {
        console.log("$addEmployeeForm entered");
        e.preventDefault();
        let formData = new FormData(this);
        let photo = $("#photoEmployee");
        console.log(formData);
        if(photo.prop('files')[0] !== undefined)
            formData.set("photo", photo.prop('files')[0]);
        else{
            //formData.set("photo", 0);
            formData.set("photoPath", "images/employee0.jpg");
        }
        console.log(formData);

        $.ajax({
            type: 'POST',
            url: $addEmployeeForm.attr('action'),
            enctype: "multipart/form-data",
            data: formData,
            processData: false,
            contentType: false,
        });
    });
});
