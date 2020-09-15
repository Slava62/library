    function showAuthorById(){
        var r_id = document.getElementById("author-id").value;
        var xmlhttp = new XMLHttpRequest();   // new HttpRequest instance
        xmlhttp.open("GET", "/authors/" + r_id);
        xmlhttp.setRequestHeader("Content-Type", "application/json");
        xmlhttp.send();
        xmlhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                var authorList = JSON.parse("[" + xmlhttp.responseText + "]");
                fillAuthorList(authorList);
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
                var authorList = JSON.parse(xmlhttp.responseText);
                fillAuthorList(authorList);
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
              showAllAuthors();
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
                var authorList = JSON.parse("[" + xmlhttp.responseText + "]");
                fillAuthorList(authorList);
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

                var authorList = JSON.parse("[" + xmlhttp.responseText + "]");
                fillAuthorList(authorList);
            }
        };
        cleanAuthorFields();
    }

    function cleanAuthorFields(){
        document.getElementById("author-id").value=1;
        document.getElementById("author-name").value="";
}

    function fillAuthorList(elements){
        clearAuthorList();
        var authorList = document.getElementById("author-list");
        var i=0;
        for (var key in elements){
            var author= new Option(elements[key].name, elements[key].id,  false,false);
            authorList[i++]=author;
        }
        document.getElementById("author-list").size=i;
        return true;
    }
    function clearAuthorList() {
        var authorList = document.getElementById("author-list");
        while (authorList.length > 0) {
            authorList.options[0] = null;
        }
        authorList.options[0] = new Option('', 0, false, false)

    }
    function selectAuthorList(){
        var authorList = document.getElementById("author-list");

        document.getElementById("author-id").value=
            authorList.options[authorList.options.selectedIndex].value;
        document.getElementById("author-name").value=
            authorList.options[authorList.options.selectedIndex].text;
        document.getElementById("book-authorId").value=
            authorList.options[authorList.options.selectedIndex].value;
        showAllBooksByAuthorId();

    }