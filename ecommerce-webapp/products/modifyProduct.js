let request = new XMLHttpRequest();

function edit(id) {
    console.log(id);
}

// function del(id) {
//     console.log(id);
// }

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
            var td5 = document.createElement('td');
            var id = document.createTextNode('Id');
            var name = document.createTextNode('Name');
            var price = document.createTextNode('Price');
            var edit = document.createTextNode(' ');
            var del = document.createTextNode(' ');
            td1.appendChild(id);
            td2.appendChild(name);
            td3.appendChild(price);
            td4.appendChild(edit);
            td5.appendChild(del);

            row.appendChild(td1);
            row.appendChild(td2);
            row.appendChild(td3);
            row.appendChild(td4);
            row.appendChild(td5);

            document.getElementById('prd_table').appendChild(row);


            // data
            bears.forEach(p => {

                var row = document.createElement('tr');
                row.setAttribute('id', p.id);

                var td1 = document.createElement('td');
                var td2 = document.createElement('td');
                var td3 = document.createElement('td');
                var td4 = document.createElement('button');
                var td5 = document.createElement('button');

                td4.setAttribute('onclick', 'edit(this.id)');
                td5.setAttribute('onclick', 'del(this.id)');

                var editBtn = document.createTextNode('Edit');
                var delBtn = document.createTextNode('Delete');

                td4.setAttribute('id', p.id);
                td5.setAttribute('id', p.id);
                td4.appendChild(editBtn);
                td5.appendChild(delBtn);

                var id = document.createTextNode(p.id);
                var name = document.createTextNode(p.name);
                var price = document.createTextNode(p.price);


                td1.appendChild(id);
                td2.appendChild(name);
                td3.appendChild(price);
                td4.appendChild(editBtn);
                td5.appendChild(delBtn);

                row.appendChild(td1);
                row.appendChild(td2);
                row.appendChild(td3);
                row.appendChild(td4);
                row.appendChild(td5);
                document.getElementById('prd_table').appendChild(row);

            });


        } else {
            throw new Error(request.response);
        }
    }

}

request.open('GET', 'http://localhost:8080/jersey-db/webapi/products');
request.send();