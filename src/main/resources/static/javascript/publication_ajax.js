console.log("publication page");

$(document).ready(function () {

    //Форма добавления города
    let $editPublicationForm = $("#edit_publication");
    let $publicationUpdateModal = $("#publication_update_modal");

    //Отобразить город
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
            '<button type="button" class="btn btn-warning col-md-6 delete_publication">Удалить</button>' +
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

});
