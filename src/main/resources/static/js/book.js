l_book_attr = [];

function showBookById() {
    var r_id = document.getElementById("book-id").value;
    var xmlhttp = new XMLHttpRequest();   // new HttpRequest instance
    xmlhttp.open("GET", "/books/" + r_id);
    xmlhttp.setRequestHeader("Content-Type", "application/json");
    xmlhttp.send();
    xmlhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            var bookList = JSON.parse("[" + xmlhttp.responseText + "]");
            fillBookList(bookList);
        }
    };
    cleanbookFields();
}

function showAllBooks() {
    var xmlhttp = new XMLHttpRequest();   // new HttpRequest instance
    xmlhttp.open("GET", "/books");
    xmlhttp.setRequestHeader("Content-Type", "application/json");
    xmlhttp.send();
    xmlhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            // var responce = JSON.parse(xmlhttp.responseText);
            var bookList = JSON.parse(xmlhttp.responseText);
            fillBookList(bookList);
        }
    };
    cleanbookFields();
}
function showAllBooksByAuthorId() {
    var r_id = document.getElementById("author-id").value;
    var xmlhttp = new XMLHttpRequest();   // new HttpRequest instance
    xmlhttp.open("GET", "/library/b/" + r_id);
    xmlhttp.setRequestHeader("Content-Type", "application/json");
    xmlhttp.send();
    xmlhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            // var responce = JSON.parse(xmlhttp.responseText);
            var bookList = JSON.parse(xmlhttp.responseText);
            fillBookList(bookList);
        }
    };
    cleanbookFields();
}
function deleteBook() {
    var r_id = document.getElementById("book-id").value;
    var xmlhttp = new XMLHttpRequest();   // new HttpRequest instance
    xmlhttp.open("DELETE", "/books/" + r_id);
    xmlhttp.setRequestHeader("Content-Type", "application/json");
    xmlhttp.send();
    xmlhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            // var responce = JSON.parse(xmlhttp.responseText);
            showAllBooks();
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
    xmlhttp.send(JSON.stringify({id: 0, caption: r_caption, authorId: r_author_id, bookStatus: r_status}));

    xmlhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            var bookList = JSON.parse("[" + xmlhttp.responseText + "]");
            var idValue = (JSON.parse(xmlhttp.responseText)).id;
            for (let i = 0; i < l_book_attr.length; i++) {
                var e = JSON.parse(l_book_attr[i]);
                console.log("elelment e.idB of arr - " + e.id);
                if (e.id == idValue) {
                    l_book_attr.splice(i, 1);
                    l_book_attr.push(xmlhttp.responseText);
                    break;
                }
            }
            fillBookList(bookList);
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
    xmlhttp.send(JSON.stringify({id: 0, caption: r_caption, authorId: r_author_id, bookStatus: r_status}));

    xmlhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            var bookList = JSON.parse("[" + xmlhttp.responseText + "]");
            var idValue = (JSON.parse(xmlhttp.responseText)).id;
            for (let i = 0; i < l_book_attr.length; i++) {
                var e = JSON.parse(l_book_attr[i]);
                console.log("elelment e.idB of arr - " + e.id);
                if (e.id == idValue) {
                    l_book_attr.splice(i, 1);
                    break;
                }
            }
            l_book_attr.push(xmlhttp.responseText);
            fillBookList(bookList);
        }
    };
    cleanbookFields();
}

function cleanbookFields() {
    document.getElementById("book-id").value = 1;
    document.getElementById("book-caption").value = "";
    document.getElementById("book-authorId").value = 1;
    document.getElementById("book-status").value = 0;
    //<select  id="book-list" class="input-group" onclick="selectBookList()">
}

//**********************************
function fillBookList(elements) {
    clearBooklist();
    var bookList = document.getElementById("book-list");
    var i = 0;
    for (var key in elements) {
        var book = new Option(elements[key].caption, elements[key].id, false, false);
        bookList[i++] = book;
    }
    document.getElementById("book-list").size = i;
    return true;
}

//********************
function clearBooklist() {
    var bookList = document.getElementById("book-list");
    while (bookList.length > 0) {
        bookList.options[0] = null;
    }
    bookList.options[0] = new Option('', 0, false, false)

}

//*****************----------------
function selectBookList() {
    var authorList = document.getElementById("book-list");

    document.getElementById("book-id").value =
        authorList.options[authorList.options.selectedIndex].value;
    document.getElementById("book-caption").value =
        authorList.options[authorList.options.selectedIndex].text;
    document.getElementById("card-book-id").value =
        authorList.options[authorList.options.selectedIndex].value;
    var idValue = authorList.options[authorList.options.selectedIndex].value;
    console.log("value of selected index - " + idValue);
    console.log("arr" + l_book_attr);

    for (let i = 0; i < l_book_attr.length; i++) {
        var e = JSON.parse(l_book_attr[i]);
        console.log("elelment e.idB of arr - " + e.id);
        if (e.id == idValue) {
            document.getElementById("book-authorId").value = e.authorId;
            console.log("status of elelment from arr -" + e.bookStatus +
                " author id - " + e.authorId);
            var s = 1;
            if (e.bookStatus == "READYTOBORROW") {
                s = 0;
            }
            console.log("value of changed string - " + s);
            document.getElementById("book-status").value = s;
        }
    }

}