function showReaders(){
    var xmlhttp = new XMLHttpRequest();   // new HttpRequest instance
        xmlhttp.open("GET", "/libreaders");
        xmlhttp.setRequestHeader("Content-Type", "application/json");
        xmlhttp.send();
        xmlhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                // var responce = JSON.parse(xmlhttp.responseText);
                var readerList = JSON.parse(xmlhttp.responseText);
                fillReaderList(readerList);
               // document.getElementById("responce-author-text").innerHTML = html;
            }
        };
}
function fillReaderList(elements){
    clearReaderList();
    var readerList = document.getElementById("reader-list");
    var i=0;
    for (var key in elements){
        var reader= new Option(elements[key].name, elements[key].id,  false,false);
        readerList[i++]=reader;
    }
    document.getElementById("reader-list").size=i;
    return true;
}
//***************************
function clearReaderList() {
    var readerList = document.getElementById("reader-list");
    while (readerList.length > 0) {
        readerList.options[0] = null;
    }
    readerList.options[0] = new Option('', 0, false, false)

}
//*************************
function selectReaderList(){
    var readerList = document.getElementById("reader-list");

    document.getElementById("card-reader-id").value=
         readerList.options[readerList.options.selectedIndex].value;
}

function showAllReadersByAuthorId() {
    var r_id = document.getElementById("author-id").value;
    var xmlhttp = new XMLHttpRequest();   // new HttpRequest instance
    xmlhttp.open("GET", "/library/r/" + r_id);
    xmlhttp.setRequestHeader("Content-Type", "application/json");
    xmlhttp.send();
    xmlhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            // var responce = JSON.parse(xmlhttp.responseText);
            var readerList = JSON.parse(xmlhttp.responseText);
            fillReaderList(readerList);
        }
    };
}