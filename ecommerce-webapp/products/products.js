let request = new XMLHttpRequest();

function cartButton() {
    var cartButton = document.createElement('button');
    cartButton.setAttribute('id', 'cartBtn');
    cartButton.setAttribute('onclick', 'addToCart()');

    let buttonText = document.createTextNode('add to cart');
    cartButton.appendChild(buttonText);
    document.body.appendChild(cartButton);
}

request.onreadystatechange = function () {
    if (request.readyState === 4) {
        if (request.status == 200) {
            var bears = JSON.parse(request.response);
            console.log(bears);

            var table = document.getElementById('prd_table');
            document.body.appendChild(table);

            var row = document.createElement('tr');
            row.setAttribute('id', 'row');

            // title
            var td1 = document.createElement('td');
            var td2 = document.createElement('td');
            var td3 = document.createElement('td');
            var td4 = document.createElement('td');
            var id = document.createTextNode('Id');
            var name = document.createTextNode('Name');
            var price = document.createTextNode('Price');
            var checkbox = document.createTextNode(' ');
            td1.appendChild(id);
            td2.appendChild(name);
            td3.appendChild(price);
            td4.appendChild(checkbox);

            row.appendChild(td1);
            row.appendChild(td2);
            row.appendChild(td3);
            row.appendChild(td4);

            document.getElementById('prd_table').appendChild(row);


            // data
            bears.forEach(p => {

                var row = document.createElement('tr');

                var td1 = document.createElement('td');
                var td2 = document.createElement('td');
                var td3 = document.createElement('td');
                var td4 = document.createElement('td');
                var chk = document.createElement('input');
                chk.setAttribute('type', 'checkbox');
                chk.setAttribute('class', 'chk');
                chk.setAttribute('id', p.id);

                var id = document.createTextNode(p.id);
                var name = document.createTextNode(p.name);
                var price = document.createTextNode(p.price);


                td1.appendChild(id);
                td2.appendChild(name);
                td3.appendChild(price);
                td4.appendChild(chk);

                row.appendChild(td1);
                row.appendChild(td2);
                row.appendChild(td3);
                row.appendChild(td4);
                document.getElementById('prd_table').appendChild(row);

            });

            cartButton();

        } else {
            throw new Error(request.response);
        }
    }

}

request.open('GET', 'http://localhost:8080/jersey-db/webapi/products');
request.send();