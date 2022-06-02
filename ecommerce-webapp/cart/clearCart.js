function clearCart() {
    var id = localStorage.getItem('id');
    var uid = localStorage.getItem('uid');

    if (uid == null || id == null) {
        alert('login again');
        return;
    }


    let url = 'http://localhost:8080/jersey-db/webapi/cart/' + id;
    console.log(url);

    var xhr = new XMLHttpRequest();
    xhr.open('DELETE', url);
    xhr.setRequestHeader("Authorization", uid);

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 204) {
            location.reload();
        } else if (xhr.status !== 204) {
            alert("Your cart is already empty");
            location.replace('../home.html');
        }
    };

    xhr.send();
}