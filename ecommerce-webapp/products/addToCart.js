function addToCart() {
    var checkboxes = document.getElementsByClassName('chk');

    for (var i = 0; i < checkboxes.length; i++) {
        var checkbox = checkboxes[i];
        if (checkbox.checked) {
            var val = checkbox.getAttribute('id');
            console.log(val);
        }
    }

}