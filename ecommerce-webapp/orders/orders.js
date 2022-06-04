let xhr = new XMLHttpRequest();
var id = localStorage.getItem('id');
var uid = localStorage.getItem('uid');

if (uid == null || id == null) {
    alert('login again');
}
var url = 'http://localhost:8080/jersey-db/webapi/orders/' + id;

function products(bears, tid, t, time) {

    var table = document.createElement('table');
    table.setAttribute('id', tid);
    table.setAttribute('border', '');
    document.body.appendChild(table);

    var row = document.createElement('tr');
    row.setAttribute('id', 'row');

    // title
    var td1 = document.createElement('td');
    var td2 = document.createElement('td');
    var td3 = document.createElement('td');
    var td4 = document.createElement('td');
    var id = document.createTextNode('tid');
    var name = document.createTextNode('Name');
    var price = document.createTextNode('Price');
    var tidLabel = document.createTextNode('prd_id');
    td1.appendChild(id);
    td2.appendChild(name);
    td3.appendChild(price);
    td4.appendChild(tidLabel);

    row.appendChild(td1);
    row.appendChild(td2);
    row.appendChild(td3);
    row.appendChild(td4);

    document.getElementById(tid).appendChild(row);


    // data
    bears.forEach(p => {
        var row = document.createElement('tr');

        var td1 = document.createElement('td');
        var td2 = document.createElement('td');
        var td3 = document.createElement('td');
        var td4 = document.createElement('td');

        var id = document.createTextNode(p.id);
        var name = document.createTextNode(p.name);
        var price = document.createTextNode(p.price);
        var tidLabel = document.createTextNode(tid);


        td1.appendChild(tidLabel);
        td2.appendChild(name);
        td3.appendChild(price);
        td4.appendChild(id);

        row.appendChild(td1);
        row.appendChild(td2);
        row.appendChild(td3);
        row.appendChild(td4);
        document.getElementById(tid).appendChild(row);

    });

    var br = document.createElement('br');
    var total = document.createElement('b');
    var timeElem = document.createElement('p');
    var totalVal = document.createTextNode('Total: ' + t);
    var timeVal = document.createTextNode(' ' + time);
    total.appendChild(totalVal);
    timeElem.appendChild(timeVal);

    document.getElementById(tid).appendChild(total);
    document.getElementById(tid).appendChild(timeElem);
    // document.getElementById(tid).appendChild(br);
    // document.body.appendChild(table);
}

xhr.onreadystatechange = function () {
    if (xhr.readyState === 4) {
        if (xhr.status == 200) {
            var bears = JSON.parse(xhr.response);
            console.log(bears[1]);
            console.log(bears);

            bears.forEach(order => {
                products(order.products, 't_' + order.tid, order.total, order.dateTime);
            });
        } else {
            throw new Error(xhr.response);
        }
    }

}


xhr.open('GET', url);
xhr.setRequestHeader("Authorization", uid);

xhr.send();