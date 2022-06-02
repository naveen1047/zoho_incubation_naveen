let xhr = new XMLHttpRequest();
let id = localStorage.getItem('id');
let uid = localStorage.getItem('uid');

var isEmpty = true;

xhr.onreadystatechange = function () {
    if (xhr.readyState === 4) {
        if (xhr.status == 200) {
            var bears = JSON.parse(xhr.response);
            console.log(bears);

            var table = document.getElementById('cart_table');
            document.body.appendChild(table);

            var row = document.createElement('tr');
            row.setAttribute('id', 'row');

            // title
            var td1 = document.createElement('td');
            var td2 = document.createElement('td');
            var td3 = document.createElement('td');
            var id = document.createTextNode('Id');
            var name = document.createTextNode('Name');
            var price = document.createTextNode('Price');
            td1.appendChild(id);
            td2.appendChild(name);
            td3.appendChild(price);

            row.appendChild(td1);
            row.appendChild(td2);
            row.appendChild(td3);

            document.getElementById('cart_table').appendChild(row);


            // data
            bears.forEach(p => {
                isEmpty = false;

                var row = document.createElement('tr');

                var td1 = document.createElement('td');
                var td2 = document.createElement('td');
                var td3 = document.createElement('td');
                var id = document.createTextNode(p.id);
                var name = document.createTextNode(p.name);
                var price = document.createTextNode(p.price);


                td1.appendChild(id);
                td2.appendChild(name);
                td3.appendChild(price);

                row.appendChild(td1);
                row.appendChild(td2);
                row.appendChild(td3);
                document.getElementById('cart_table').appendChild(row);
            });

            var buynow = document.getElementById('buynow');

            var button = document.createElement('button');
            var buynowLabel = document.createTextNode('buy now');

            button.setAttribute('id', 'buynowBtn');
            button.setAttribute('onclick', 'buynow()');

            button.appendChild(buynowLabel);

            buynow.appendChild(button);
        } else {
            throw new Error(xhr.response);
        }
    }

}

console.log(uid);

xhr.open('GET', 'http://localhost:8080/jersey-db/webapi/cart/' + id);
xhr.setRequestHeader("Authorization", uid);


xhr.send();