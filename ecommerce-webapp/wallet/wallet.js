let xhr = new XMLHttpRequest();
var id = localStorage.getItem('id');
var uid = localStorage.getItem('uid');

if (uid == null || id == null) {
    alert('login again');

}
var url = 'http://localhost:8080/jersey-db/webapi/wallet/' + id;


xhr.onreadystatechange = function () {
    if (xhr.readyState === 4) {
        if (xhr.status == 200) {
            var bear = JSON.parse(xhr.response);
            console.log(bear);
            document.getElementById('balance').innerText = 'Balance: ' + bear.balance;
        } else {
            throw new Error(xhr.response);
        }
    }

}


xhr.open('GET', url);
xhr.setRequestHeader("Authorization", uid);
xhr.send();