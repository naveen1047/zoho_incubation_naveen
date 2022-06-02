// chrome.exe --user-data-dir="C:/Chrome dev session" --disable-web-security

function signup() {
    var url = "http://localhost:8080/jersey-db/webapi/users/";

    var xhr = new XMLHttpRequest();
    xhr.open("POST", url);
    xhr.setRequestHeader("Content-Type", "application/json");

    var id = document.getElementById("id").value;
    var password = document.getElementById("password").value;
    var form = document.getElementById('signupForm');

    var encryptedUserPassword = "Basic " + btoa(id + ":" + password);
    var payload = JSON.stringify({
        'name': id,
        'password': password
    });

    // on success
    localStorage.setItem("uid", encryptedUserPassword);

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 201) {
            localStorage.clear();
            localStorage.setItem('uid', encryptedUserPassword);
            localStorage.setItem('id', id);

            var bear = JSON.parse(xhr.response);
            alert("your id is:" + bear.id);
            location.replace("../home.html");
        } else if (xhr.status !== 201) {
            alert("Something went wrong");
        }
    };

    xhr.send(payload);
}