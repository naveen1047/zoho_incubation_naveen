function buynow() {
    var id = localStorage.getItem('id');
    var uid = localStorage.getItem('uid');

    if (uid == null || id == null) {
        alert('login again');
        return;
    }

    let url = 'http://localhost:8080/jersey-db/webapi/orders/' + id;
    console.log(url);

    var xhr = new XMLHttpRequest();
    xhr.open('POST', url);
    xhr.setRequestHeader("Authorization", uid);


    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            location.reload();
        } else if (xhr.status === 500) {
            var bear = JSON.parse(xhr.response);
            alert(bear.message);
            return;
        }
    };

    xhr.send();
}