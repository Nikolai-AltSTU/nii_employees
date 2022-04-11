console.log("publication page");

publication_update_modal = document.getElementById("publication_update_modal");

cancelButton = document.getElementById("cancelButton");
cancelButton.onclick = function (){
    publication_update_modal.style.display = "none";
};

function openCrud(object, ...attributes)
{
    console.log('begin open');
    console.log(attributes[0]);
    let inputs = object.querySelectorAll("input[type='text'], textarea, input[type='file'] , input[type='date']");
    if(attributes.length > 0)
    {
        console.log(inputs);
        document.getElementById("edit_publication").action="/publication_update/" + attributes[0]['id'];
        inputs[0].value = attributes[0]["title"];
        inputs[1].value = attributes[0]["theAbstract"];

        let $editPublication = $("#edit_publication");
        //Отправка формы обновления города
        $editPublication.on('submit', function (e) {
            console.log("$editPublication entered");
            e.preventDefault();
            let formData = new FormData(this);
            //console.log($("#employees").value);
            //formData.set("employees", $("#employees").value)
            console.log(formData);

            $.ajax({
                type: 'POST',
                url: $editPublication.attr('action'),
                enctype: "multipart/form-data",
                data: formData,
                processData: false,
                contentType: false,
                success: function (data, textStatus, xhr) {
                    location.reload();
                },
            });
        });
    }
    else
    {
        inputs.forEach(function (item) {
            item.value = "";
        })
    }
    object.style.display = "block";
    console.log('end open');
}

$(document).ready(function () {

    //Форма добавления публикации
    let $editPublicationForm = $("#edit_publication");
    let $publicationUpdateModal = $("#publication_update_modal");

    //Отобразить публикацию
    function showPublication(publication) {
        let $newPublication = $(
            '<div class="accordion-item">' +
            '<h2 class="accordion-header" id="headingTwo">' +
            '<button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">' +
            '</button>' +
            '</h2>' +
            '<div id="collapseTwo" class="accordion-collapse collapse" aria-labelledby="headingTwo" data-bs-parent="#accordionExample">' +
            '<div class="accordion-body">' +
            '</div>' +
            '<div class="row p-3">' +
            '<button type="button" class="btn btn-warning col-md-3 update_publication_button">Редактировать</button>' +
            '<button type="button" class="btn btn-danger col-md-3 delete_publication">Удалить</button>' +
            '</div>' +
            '</div>'
        )
        console.log(publication)
        $($newPublication).attr('id', "publication" + publication.id);
        $(".accordion-header", $newPublication).attr('id', "heading_publication" + publication.id);
        $(".accordion-button", $newPublication).attr('data-bs-target', "#collapse_publication" + publication.id);
        $(".accordion-button", $newPublication).attr('aria-controls', "collapse_publication" + publication.id);

        $(".accordion-collapse", $newPublication).attr('id', "collapse_publication" + publication.id);
        $(".accordion-collapse", $newPublication).attr('aria-labelledby', "heading_publication" + publication.id);

        $(".accordion-button", $newPublication).text(publication.title);
        $(".accordion-body", $newPublication).text(publication.theAbstract);
        $(".btn", $newPublication).attr('id', publication.id);
        $('#add-card').before($newPublication);
    }

    // подгрузим данные
    $.ajax({
        type: 'GET',
        url: 'publications/findAll',
        success: function (data, textStatus, xhr) {
            console.log(data);
            data.forEach(function(item, i, arr) {
                showPublication(item);
            });
        },
    });

    // установим события при нажатии на кнопки
    $(document).on('click', '.delete_publication', function () {
        let id = $(this).attr("id");
        $.ajax({
            type: 'GET',
            url: "/publication_delete/" + id,
            success: function () {
                location.reload();
            }
        });
    });

    $(document).on('click', '.update_publication_button', function () {
        let id = $(this).attr("id");
        $.ajax({
            type: 'GET',
            url: "/publications/find/" + id,
            success: function (data, textStatus, xhr) {
                console.log("data");
                console.log(data);
                openCrud(publication_update_modal, data)
            }
        });
    });

});
