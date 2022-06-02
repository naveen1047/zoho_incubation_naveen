let request = new XMLHttpRequest();

request.onreadystatechange = function () {
    if (request.readyState === 4) {
        if (request.status == 200) {
            var bears = JSON.parse(request.response);
            console.log(bears);

            var table = document.getElementById('users_table');
            table.setAttribute('border', '');
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
            var password = document.createTextNode('password');
            var roles = document.createTextNode('roles');

            td1.appendChild(id);
            td2.appendChild(name);
            td3.appendChild(password);
            td4.appendChild(roles);

            row.appendChild(td1);
            row.appendChild(td2);
            row.appendChild(td3);
            row.appendChild(td4);

            document.getElementById('users_table').appendChild(row);


            // data
            bears.users.forEach(u => {

                var row = document.createElement('tr');

                var td1 = document.createElement('td');
                var td2 = document.createElement('td');
                var td3 = document.createElement('td');
                var td4 = document.createElement('td');

                var id = document.createTextNode(u.id);
                var name = document.createTextNode(u.name);
                var password = document.createTextNode(u.password);


                td1.appendChild(id);
                td2.appendChild(name);
                td3.appendChild(password);

                var roles1 = "";
                u.roles.forEach(role => {
                    roles1 += role.name + ", ";
                });

                var roles = document.createTextNode(roles1);

                td4.appendChild(roles);
                roles1 = "";

                row.appendChild(td1);
                row.appendChild(td2);
                row.appendChild(td3);
                row.appendChild(td4);
                document.getElementById('users_table').appendChild(row);

            });


        } else {
            throw new Error(request.response);
        }
    }

}

request.open('GET', 'http://localhost:8080/jersey-db/webapi/users');
request.send();