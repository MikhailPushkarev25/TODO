function showAll() {
    let status = $('#checkbox1').is(":checked");
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/list/item.do',
        contentType: 'application/json'
    }).done(function (data) {
        let result = '<tbody id="bodyTableId">';
        let row = 1;
        for (let i = 0; i < data.length; i++) {
            let item = data[i];
            if (!status && item.done) {
                continue;
            }
            result += "<tr>";
            result += "<input type=\"hidden\" value=\"" + item.description + "\" id=\"userId" + item.id + "\"/>";
            result += "<th>" + row + "</th>";
            result += "<th>" + item.description + "</th>";
            result += "<th>" + item.user + "</th>";
            result += "<th>" + item.category.name + "</th>";
            let date = new Date(item.created);
            const dtFormat = new Intl.DateTimeFormat('ru', {
                hour: "numeric",
                minute: "numeric",
                year: "numeric",
                month: "long",
                day: "numeric"
            });
            result += "<th>" + dtFormat.format(date) + "</th>";
            let done = item.done ? 'выполнено'
                : "<input type=\"checkbox\"value=\"" + item.id + "\" onclick=\"changeStatus(this)\">";
            result += "<th>" + done + "</th></tr>";
            row++;
        }
        result += "</tbody>";
        document.getElementById('bodyTableId').innerHTML = result;
    }).fail(function (err) {
        console.log(err);
    });
}