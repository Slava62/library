    function showAuthorById(){
        var r_id = document.getElementById("author-id").value;
        var xmlhttp = new XMLHttpRequest();   // new HttpRequest instance
        xmlhttp.open("GET", "/authors/" + r_id);
        xmlhttp.setRequestHeader("Content-Type", "application/json");
        xmlhttp.send();
        xmlhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                // var responce = JSON.parse(xmlhttp.responseText);
                var html = '';
                html = html + xmlhttp.responseText;
                document.getElementById("responce-author-text").innerHTML = html;
            }
        };
    cleanAuthorFields();
    }
    function showAllAuthors(){
        var xmlhttp = new XMLHttpRequest();   // new HttpRequest instance
        xmlhttp.open("GET", "/authors");
        xmlhttp.setRequestHeader("Content-Type", "application/json");
        xmlhttp.send();
        xmlhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                // var responce = JSON.parse(xmlhttp.responseText);
                var html = '';
                html = html + xmlhttp.responseText;
                document.getElementById("responce-author-text").innerHTML = html;
            }
        };
        cleanAuthorFields();
    }
    function deleteAuthor(){
        var r_id = document.getElementById("author-id").value;
        var xmlhttp = new XMLHttpRequest();   // new HttpRequest instance
        xmlhttp.open("DELETE", "/authors/" + r_id);
        xmlhttp.setRequestHeader("Content-Type", "application/json");
        xmlhttp.send();
        xmlhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                // var responce = JSON.parse(xmlhttp.responseText);
                var html = '';
                html = html + xmlhttp.responseText;
                document.getElementById("responce-author-text").innerHTML = html;
            }
        };
        cleanAuthorFields();
    }
    function updateAuthor() {
        var r_id = document.getElementById("author-id").value;
        var r_name = document.getElementById("author-name").value;

        var xmlhttp = new XMLHttpRequest();   // new HttpRequest instance
        xmlhttp.open("PUT", "/authors/" + r_id);
        xmlhttp.setRequestHeader("Content-Type", "application/json");
        xmlhttp.send(JSON.stringify({id: r_id, name: r_name}));

        xmlhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
               // var responce = JSON.parse(xmlhttp.responseText);
                var html = '';
                html = html + xmlhttp.responseText;
                document.getElementById("responce-author-text").innerHTML = html;
            }
        };
        cleanAuthorFields();
    }
    function addAuthor() {
       // var r_id = document.getElementById("author-id").value;
        var r_name = document.getElementById("author-name").value;

        var xmlhttp = new XMLHttpRequest();   // new HttpRequest instance
        xmlhttp.open("POST", "/authors");
        xmlhttp.setRequestHeader("Content-Type", "application/json");
        xmlhttp.send(JSON.stringify({ name: r_name})); //id: r_id,

        xmlhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
               // var responce = JSON.parse(xmlhttp.responseText);
                var html = '';
                html = html + xmlhttp.responseText;
                document.getElementById("responce-author-text").innerHTML = html;
            }
        };
        cleanAuthorFields();
    }
    function cleanAuthorFields(){
        document.getElementById("author-id").value=1;
        document.getElementById("author-name").value="";
}