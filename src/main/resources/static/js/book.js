    function showBookById(){
        var r_id = document.getElementById("book-id").value;
        var xmlhttp = new XMLHttpRequest();   // new HttpRequest instance
        xmlhttp.open("GET", "/books/" + r_id);
        xmlhttp.setRequestHeader("Content-Type", "application/json");
        xmlhttp.send();
        xmlhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                // var responce = JSON.parse(xmlhttp.responseText);
                var html = '';
                html = html + xmlhttp.responseText;
                document.getElementById("responce-book-text").innerHTML = html;
            }
        };
    cleanbookFields();
    }
    function showAllBooks(){
        var xmlhttp = new XMLHttpRequest();   // new HttpRequest instance
        xmlhttp.open("GET", "/books");
        xmlhttp.setRequestHeader("Content-Type", "application/json");
        xmlhttp.send();
        xmlhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                // var responce = JSON.parse(xmlhttp.responseText);
                var html = '';
                html = html + xmlhttp.responseText;
                document.getElementById("responce-book-text").innerHTML = html;
            }
        };
        cleanbookFields();
    }
    function deleteBook(){
        var r_id = document.getElementById("book-id").value;
        var xmlhttp = new XMLHttpRequest();   // new HttpRequest instance
        xmlhttp.open("DELETE", "/books/" + r_id);
        xmlhttp.setRequestHeader("Content-Type", "application/json");
        xmlhttp.send();
        xmlhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                // var responce = JSON.parse(xmlhttp.responseText);
                var html = '';
                html = html + xmlhttp.responseText;
                document.getElementById("responce-book-text").innerHTML = html;
            }
        };
        cleanbookFields();
    }
    function updateBook() {
        var r_id = document.getElementById("book-id").value;
        var r_caption = document.getElementById("book-caption").value;
        var r_author_id = document.getElementById("book-authorId").value;
        var r_status = document.getElementById("book-status").value;

        var xmlhttp = new XMLHttpRequest();   // new HttpRequest instance
        xmlhttp.open("PUT", "/books/" + r_id);
        xmlhttp.setRequestHeader("Content-Type", "application/json");
        xmlhttp.send(JSON.stringify({ caption: r_caption, authorId: r_author_id, bookStatus: r_status}));

        xmlhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
               // var responce = JSON.parse(xmlhttp.responseText);
                var html = '';
                html = html + xmlhttp.responseText;
                document.getElementById("responce-book-text").innerHTML = html;
            }
        };
        cleanbookFields();
    }
    function addBook() {
       
        var r_caption = document.getElementById("book-caption").value;
        var r_author_id = document.getElementById("book-authorId").value;
        var r_status = document.getElementById("book-status").value;
        

        var xmlhttp = new XMLHttpRequest();   // new HttpRequest instance
        xmlhttp.open("POST", "/books");
        xmlhttp.setRequestHeader("Content-Type", "application/json");
        xmlhttp.send(JSON.stringify({ caption: r_caption, authorId: r_author_id, bookStatus: r_status})); 

        xmlhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
               // var responce = JSON.parse(xmlhttp.responseText);
                var html = '';
                html = html + xmlhttp.responseText;
                document.getElementById("responce-book-text").innerHTML = html;
            }
        };
        cleanbookFields();
    }
    function cleanbookFields(){
        document.getElementById("book-id").value=1;
        document.getElementById("book-caption").value="";
        document.getElementById("book-authorId").value=1;
        document.getElementById("book-status").value=0;
}