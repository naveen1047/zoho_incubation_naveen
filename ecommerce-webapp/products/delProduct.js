function del(pid) {
    let xhr = new XMLHttpRequest();
    var id = localStorage.getItem('id');
    var uid = localStorage.getItem('uid');

    if (uid == null || id == null) {
        alert('login again');
        return;
    }

    var url = 'http://localhost:8080/jersey-db/webapi/products/' + pid;


    xhr.onreadystatechange = function () {
        console.log(this.readyState);
        if (xhr.readyState === 4) {
            if (xhr.status == 204) {
                location.reload();
            } else {
                alert("you don't have access");
                console.log(xhr.status);
                throw new Error(xhr.response);
            }
        }

    }


    xhr.open('DELETE', url);
    xhr.setRequestHeader("Authorization", uid);
    xhr.send();
}