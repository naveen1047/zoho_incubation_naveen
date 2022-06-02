function addProduct() {
    let xhr = new XMLHttpRequest();
    var id = localStorage.getItem('id');
    var uid = localStorage.getItem('uid');

    if (uid == null || id == null) {
        alert('login again');
        return;
    }
    var url = 'http://localhost:8080/jersey-db/webapi/products/';
    var name = document.getElementById('name').value;
    var price = document.getElementById('price').value;


    var payload = JSON.stringify({
        "name": name,
        "price": parseFloat(price)
    });
    console.log(payload);


    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) {
            if (xhr.status == 201) {
                location.replace('products.html');
            } else {
                alert("you don't have access");
                console.log(xhr.status);
                throw new Error(xhr.response);
            }
        }

    }

    xhr.open('POST', url);
    xhr.setRequestHeader("Authorization", uid);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send(payload);
}