// chrome.exe --user-data-dir="C:/Chrome dev session" --disable-web-security

function login() {
    var url = "http://localhost:8080/jersey-db/webapi/auth/";

    var xhr = new XMLHttpRequest();
    xhr.open("GET", url);
    xhr.withCredentials = false;

    var id = document.getElementById("id").value;
    var password = document.getElementById("password").value;

    var encryptedUserPassword = "Basic " + btoa(id + ":" + password);

    localStorage.setItem("uid", encryptedUserPassword);
    localStorage.setItem("id", id);
    try {
        // xhr.setRequestHeader("host", "localhost:8080");
        xhr.setRequestHeader("Authorization", encryptedUserPassword);
        xhr.send();
    }
    catch (e) {
        console.log(e);
        alert(e);
    }

    console.log(xhr);

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            location.replace("../home.html");
            console.log(xhr.status);
            console.log(xhr.responseText);
        } else if (xhr.status === 500) {
            alert("Incorrect username or password");
        }
    };

}