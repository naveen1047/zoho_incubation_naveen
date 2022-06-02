// chrome.exe --user-data-dir="C:/Chrome dev session" --disable-web-security

function setAsAdmin(id, encryptedUserPassword) {
    var url = "http://localhost:8080/jersey-db/webapi/users/setrole/" + id;

    var xhr = new XMLHttpRequest();
    xhr.open("PUT", url);
    xhr.setRequestHeader("Authorization", encryptedUserPassword);
    xhr.setRequestHeader("Content-Type", "application/json");
    var payload = JSON.stringify(["ADMIN"]);

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            // localStorage.clear();
            var bear = JSON.parse(xhr.response);

            alert("your id is:" + bear.id + "\n you are admin");
            location.replace("../home.html");
        }
    };

    xhr.send(payload);
}

function signup() {
    var url = "http://localhost:8080/jersey-db/webapi/users/";

    var xhr = new XMLHttpRequest();
    xhr.open("POST", url);
    xhr.setRequestHeader("Content-Type", "application/json");

    var name = document.getElementById("id").value;
    var password = document.getElementById("password").value;
    var form = document.getElementById('signupForm');

    var payload = JSON.stringify({
        'name': name,
        'password': password
    });

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 201) {
            localStorage.clear();
            var bear = JSON.parse(xhr.response);
            var encryptedUserPassword = "Basic " + btoa(bear.id + ":" + password);

            localStorage.setItem('uid', encryptedUserPassword);
            localStorage.setItem('id', bear.id);

            var adminChkBox = document.getElementById('adminChkBox');
            if (adminChkBox.checked) {
                setAsAdmin(bear.id, encryptedUserPassword);
            }

            alert("your id is:" + bear.id);
            location.replace("../home.html");
        } else if (xhr.status !== 201) {
            alert("Something went wrong");
        }
    };

    xhr.send(payload);
}