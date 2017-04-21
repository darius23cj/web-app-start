function getRow(persoana, total) {
    return '<tr>' +
        '<td>' + persoana + '</td>' +
        '<td>' + Math.ceil(total) + ' lei</td>' +
        '</tr>';
}

function showList(summary) {
    var lista = '';
    for (var i = 0; i < summary.length; i++) {
        var person = summary[i];
        lista += getRow(person.nume, person.total);
    }

    document.getElementsByTagName('tbody')[0].innerHTML = lista;
}

$.ajax('/shoppingList?action=summary').success(function (sumar) {
    console.info(sumar);
    showList(sumar);
});