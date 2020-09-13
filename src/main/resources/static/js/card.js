    function showCardById(){
        var r_id = document.getElementById("card-book-id").value;
        var xmlhttp = new XMLHttpRequest();   // new HttpRequest instance
        xmlhttp.open("GET", "/cards/" + r_id);
        xmlhttp.setRequestHeader("Content-Type", "application/json");
        xmlhttp.send();
        xmlhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                // var responce = JSON.parse(xmlhttp.responseText);
                var html = '';
                html = html + xmlhttp.responseText;
                document.getElementById("responce-card-text").innerHTML = html;
            }
        };
    cleanCardFields();
    }
    function showAllCards(){
        var xmlhttp = new XMLHttpRequest();   // new HttpRequest instance
        xmlhttp.open("GET", "/cards");
        xmlhttp.setRequestHeader("Content-Type", "application/json");
        xmlhttp.send();
        xmlhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                // var responce = JSON.parse(xmlhttp.responseText);
                var html = '';
                html = html + xmlhttp.responseText;
                document.getElementById("responce-card-text").innerHTML = html;
            }
        };
        cleanCardFields();
    }
    function deleteCard(){
        var r_id = document.getElementById("card-book-id").value;
        var xmlhttp = new XMLHttpRequest();   // new HttpRequest instance
        xmlhttp.open("DELETE", "/cards/" + r_id);
        xmlhttp.setRequestHeader("Content-Type", "application/json");
        xmlhttp.send();
        xmlhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                // var responce = JSON.parse(xmlhttp.responseText);
                var html = '';
                html = html + xmlhttp.responseText;
                document.getElementById("responce-card-text").innerHTML = html;
            }
        };
        cleanCardFields();
    }
    function updateCard() {
        var r_id = document.getElementById("card-book-id").value;
        var r_name = document.getElementById("card-reader-id").value;

        var xmlhttp = new XMLHttpRequest();   // new HttpRequest instance
        xmlhttp.open("PUT", "/cards/" + r_id);
        xmlhttp.setRequestHeader("Content-Type", "application/json");
        xmlhttp.send(JSON.stringify({ bookid: r_id, readerid: r_name }));

        xmlhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
               // var responce = JSON.parse(xmlhttp.responseText);
                var html = '';
                html = html + xmlhttp.responseText;
                document.getElementById("responce-card-text").innerHTML = html;
            }
        };
        cleanCardFields();
    }
    function addCard() {
        var r_id = document.getElementById("card-book-id").value;
        var r_name = document.getElementById("card-reader-id").value;

        var xmlhttp = new XMLHttpRequest();   // new HttpRequest instance
        xmlhttp.open("POST", "/cards");
        xmlhttp.setRequestHeader("Content-Type", "application/json");
        xmlhttp.send(JSON.stringify({ bookid: r_id, readerid: r_name })); //id: r_id,

        xmlhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
               // var responce = JSON.parse(xmlhttp.responseText);
                var html = '';
                html = html + xmlhttp.responseText;
                document.getElementById("responce-card-text").innerHTML = html;
            }
        };
        cleanCardFields();
    }
    function cleanCardFields(){
       
       document.getElementById("card-book-id").value=1;
       document.getElementById("card-reader-id").value=1;
}