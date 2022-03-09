console.log("admin page");

$(document).ready(function () {
    console.log("$(document).ready(function ())");

    //Форма добавления города
    let $addPublicationForm = $("#add_publication_form");

    //Отправка формы добавления города
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

});
