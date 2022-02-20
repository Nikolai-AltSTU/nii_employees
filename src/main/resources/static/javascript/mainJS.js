
employee_update = document.getElementById("employee_update");


function closeCrud(object)
{
    object.style.display = "none";
}

function openCrud(object, ...attributes)
{
    console.log('begin open');
    let inputs = object.querySelectorAll("input[type='text'], textarea, input[type='file'] , input[type='date']");
    if(attributes.length > 0)
    {
        inputs.forEach(function (item) {
            item.value = "";
        })
        for(let i = 0; i < inputs.length; i++)
        {
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

