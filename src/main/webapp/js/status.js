function changeStatus(data) {
    let description = $('#userId' + data.value).val();
    let question = "Вы уверены что задача выполнена? \n" + description;
    let result = confirm(question);
    let arr = {
        id: data.value,
        description: description
    }
    if (result) {
        $.ajax({
            cache: false,
            type: 'POST',
            url: 'http://localhost:8080/list/todo',
            data: JSON.stringify(arr),
            contentType: 'application/json'
        }).done(function () {
            showAll();
        }).fail(function (err) {
            console.log(err);
        });
    }
}