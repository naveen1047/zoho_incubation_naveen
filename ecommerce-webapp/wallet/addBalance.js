function addBalance() {
    let xhr = new XMLHttpRequest();
    var id = localStorage.getItem('id');
    var uid = localStorage.getItem('uid');

    if (uid == null || id == null) {
        alert('login again');
        return;
    }
    var url = 'http://localhost:8080/jersey-db/webapi/wallet/' + id;
    // var bal = document.getElementById('balance').innerText.value;
    var balTextBox = parseFloat(document.getElementById('amt').value);
    // console.log(parseInt(String(bal)));


    var payload = JSON.stringify({ "balance": parseFloat(balTextBox) });
    console.log(payload);


    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) {
            if (xhr.status == 200) {
                location.reload();
            } else {
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