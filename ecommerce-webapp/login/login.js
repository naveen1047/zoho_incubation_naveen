// function login() {
//     var url = "http://localhost:8080/jersey-db/webapi/auth/";

//     var xhr = new XMLHttpRequest();
//     xhr.open("GET", url);

//     xhr.setRequestHeader("Authorization", "Basic MzM6cGFzc3dvcmQ=");

//     xhr.onreadystatechange = function () {
//         if (xhr.readyState === 4) {
//             console.log(xhr.status);
//             console.log(xhr.responseText);
//         }
//     };

//     xhr.send();
// }

function login() {
    fetch('http://localhost:8080/jersey-db/webapi/auth/', {
        method: 'GET',
        headers: {
            Authorization: `Basic MzM6cGFzc3dvcmQ=`,
            'Access-Control-Allow-Origin': '*',
        },
    }).then(response => response.text())
        .then(console.log);
    // .then(response => console.log(response));
}

// function login() {
//     var x = fetch('http://localhost:8080/jersey-db/webapi/auth/', {
//         method: 'GET',
//         mode: 'cors',
//         headers: {
//             'Access-Control-Allow-Origin': '*',
//             'Access-Control-Allow-Method': 'POST, GET, OPTIONS, DELETE, PUT',
//             'Access-Control-Allow-Headers': "append,delete,entries,foreach,get,has,keys,set,values,Authorization",
//             'Authorization': `Basic MzM6cGFzc3dvcmQ=`,
//         },
//     });
//     alert(x);
//     // .then(response => console.log(response));
// }

// function login() {
//     let request = new XMLHttpRequest();

//     // var id = document.getElementById("id").value;
//     // var password = document.getElementById("password").value;

//     // localStorage.setItem("id", id);
//     // localStorage.setItem("password", password);

//     // request.onreadystatechange = function () {
//     //     alert();
//     // }

//     console.log(request);

//     // var encryptedUserPassword = 'Basic ' + btoa(id + ":" + password);

//     request.open('GET', 'http://localhost:8080/jersey-db/webapi/auth/');
//     request.setRequestHeader('Access-Control-Allow-Origin', 'http://localhost:8080');
//     request.setRequestHeader('Authorization', 'Basic MzM6cGFzc3dvcmQ=');


//     // request.setRequestHeader('Authorization', encryptedUserPassword);
//     alert();

//     request.send();
// }