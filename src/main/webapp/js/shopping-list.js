console.info("shopping list init");

function getRow(produs, cantitate, persoana, id) {
    return '<tr>' +
        '<td><a href="'+produs+'" target="_blank">' + produs + '</a></td>' +
        '<td>' + cantitate + '</td>' +
        '<td>' + persoana + '</td>' +
        '<td><a href="/shoppingList?action=remove&id=' + id + '">x</a></td>' +
        '</tr>';
}

function showList(shoppingList) {
    var lista = '';
    for (var i = 0; i < shoppingList.length; i++) {
        var produs = shoppingList[i];
        lista += getRow(produs.nume, produs.cantitate, produs.persoana, produs.id);
    }

    document.getElementsByTagName('tbody')[0].innerHTML = lista;
}

$.ajax('/shoppingList?action=list').success(function (shoppingList) {
    console.info(shoppingList);
    showList(shoppingList);
});
console.info('done');