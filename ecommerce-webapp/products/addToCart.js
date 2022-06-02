function addToCart() {
    var checkboxes = document.getElementsByClassName('chk');
    var id = localStorage.getItem('id');
    var uid = localStorage.getItem('uid');

    if (uid == null || id == null) {
        alert('login again');
        return;
    }

    var cart = new Object();
    var isEmpty = true;

    for (var i = 0; i < checkboxes.length; i++) {
        let url = 'http://localhost:8080/jersey-db/webapi/cart/';

        var checkbox = checkboxes[i];
        if (checkbox.checked) {
            isEmpty = false;
            var val = checkbox.getAttribute('id');

            url = url + id + '?cartId=' + val;
            makeCall(url);

            console.log(url);

        }
    }

    if (isEmpty) alert('item cannot be empty');

}

function makeCall(url) {
    var xhr = new XMLHttpRequest();
    xhr.open('PUT', url);
    xhr.setRequestHeader("Authorization", localStorage.getItem('uid'));

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 201) {
        } else if (xhr.status !== 201) {
            alert("Something went wrong");
        }
    };

    xhr.send();
}