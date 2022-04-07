employee_update_modal = document.getElementById("employee_update");

let $update_form_action = $("#update_form_action");
console.log("$update_form_action");
console.log($update_form_action);
let $save_employee_changes = $("#save_employee_changes");


$(document).ready(function () {

    console.log("$ employee page .ready(function ())");
    /// переопределить сохранени сущности работника

    // Форма добавления работника

    //Отправка формы добавления работника
    // $save_employee_changes.on('click', function (e) {
    //
    // }
    $update_form_action.on('submit', function (e) {
        console.log("update EmployeeForm entered");
        e.preventDefault();
        let formData = new FormData(this);
        let photo = $("#photoEmployee");
        console.log(formData);
        console.log("Photo file ", photo.prop('files')[0]);

        if(photo.prop('files')[0] === undefined){
            var file = new File([], "", {type : "image/png"});
            formData.set("photo", new Blob());
            console.log("Photo file will null ");
        }
        else{
            formData.set("photo", photo.prop('files')[0]);
        }

        console.log(formData);

        $.ajax({
            type: 'POST',
            url: $update_form_action.attr('action'),
            enctype: "multipart/form-data",
            data: formData,
            processData: false,
            contentType: false,
            success: function () {
                closeCrud(employee_update_modal);
                location.reload();
            }
        });
    });

});


function closeCrud(object)
{
    object.style.display = "none";
}

function openCrud(object, ...attributes)
{
    console.log('begin open employee crud');
    let inputs = object.querySelectorAll("input[type='text'], textarea, input[type='file'] , input[type='date']");
    if(attributes.length > 0)
    {
        inputs.forEach(function (item) {
            item.value = "";
        })

        console.log("inputs: ", inputs);
        console.log("attributes: ", attributes);
        document.getElementById("deleteButton").href= "/employee_delete/" + attributes[0];
        //document.getElementById("update_form_action").action="/employee_update/" + attributes[0];
        // for(let i = 0; i < inputs.length - 1; i++)
        // {
        //     inputs[i].value = attributes[i+1];
        // }
        for(let i = 0; i < inputs.length-1; i++)
        {
            if(attributes[i] !== null)
                inputs[i].value = attributes[i];
        }
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

