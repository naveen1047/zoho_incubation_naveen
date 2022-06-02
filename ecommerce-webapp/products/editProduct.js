function edit() {
    let xhr = new XMLHttpRequest();
    var id = localStorage.getItem('id');
    var uid = localStorage.getItem('uid');

    if (uid == null || id == null) {
        alert('login again');
        return;
    }

    var url = 'http://localhost:8080/jersey-db/webapi/products/';

    var pid = localStorage.getItem('pid');
    var name = document.getElementById('name').value;
    var price = parseFloat(document.getElementById('price').value);

    var payload = JSON.stringify({
        'id': pid,
        'name': name,
        'price': price
    });

    console.log(payload);

    xhr.onreadystatechange = function () {
        console.log(this.readyState);
        if (xhr.readyState === 4) {
            if (xhr.status == 200) {
                // location.reload();
            } else {
                alert("you don't have access");
                console.log(xhr.status);
                throw new Error(xhr.response);
            }
        }
    }


    xhr.open('PUT', url);
    xhr.setRequestHeader("Authorization", uid);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send(payload);
}