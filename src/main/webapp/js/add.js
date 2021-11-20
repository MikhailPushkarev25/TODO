function add() {
    let description = $('#formForDefinition1').val();
    let id = 0;
    let arr = {
        id: id,
        description: description
    }
    $.ajax({
        cache: false,
        type: 'POST',
        url: 'http://localhost:8080/list/item.do',
        data: JSON.stringify(arr)
    }).done(function () {
        showAll();
    }).fail(function (err) {
        console.log(err);
    });
}