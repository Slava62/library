    function showCardById(){
        var r_id = document.getElementById("card-book-id").value;
        var xmlhttp = new XMLHttpRequest();   // new HttpRequest instance
        xmlhttp.open("GET", "/cards/" + r_id);
        xmlhttp.setRequestHeader("Content-Type", "application/json");
        xmlhttp.send();
        xmlhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                var cardList = JSON.parse("[" + xmlhttp.responseText + "]");
                fillCardList(cardList);
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
                var cardList = JSON.parse(xmlhttp.responseText);
                fillCardList(cardList);
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
               showAllCards();
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
                var cardList = JSON.parse("[" + xmlhttp.responseText + "]");
                fillCardList(cardList);
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
                var cardList = JSON.parse("[" + xmlhttp.responseText + "]");
                fillCardList(cardList);
            }
        };
        cleanCardFields();
    }

    function cleanCardFields(){
       
       document.getElementById("card-book-id").value=1;
       document.getElementById("card-reader-id").value=1;
}

//<select  id="card-list" class="input-group" onclick="selectCardList()"  card-book-id card-reader-id>

    function fillCardList(elements){
        clearCardList();
        var cardList = document.getElementById("card-list");
        var i=0;
        for (var key in elements){
            var card= new Option(elements[key].readerid, elements[key].bookid,  false,false);
            cardList[i++]=card;
        }
        document.getElementById("card-list").size=i;
        return true;
    }
    function clearCardList() {
        var cardList = document.getElementById("card-list");
        while (cardList.length > 0) {
            cardList.options[0] = null;
        }
        cardList.options[0] = new Option('', 0, false, false)

    }
    function selectCardList(){
        var cardList = document.getElementById("card-list");

        document.getElementById("card-book-id").value=
            cardList.options[cardList.options.selectedIndex].value;
        document.getElementById("card-reader-id").value=
            cardList.options[cardList.options.selectedIndex].text;
        document.getElementById("book-id").value=
            cardList.options[cardList.options.selectedIndex].value;
    }