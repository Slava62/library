function showReaders(){
    var xmlhttp = new XMLHttpRequest();   // new HttpRequest instance
        xmlhttp.open("GET", "/readers");
        xmlhttp.setRequestHeader("Content-Type", "application/json");
        xmlhttp.send();
        xmlhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                // var responce = JSON.parse(xmlhttp.responseText);
                var readerList = eval ( "(" + xmlhttp.responseText + ")");
                fillReaderlist(readerList)
               // document.getElementById("responce-author-text").innerHTML = html;
            }
        };
}
function fillReaderList(elements){
    clearReaderList();
    var readerList = document.getElementById("reader-list");
    var i=1;
    for (var key in elements){
        var reader= new Option(elements[key], key, false,false);
        readerList[i++]=reader;
    }
    return true;
}
function clearReaderList(){
    var readerList = document.getElementById("reader-list");
    while(readerList.length>0){
        readerList.options[0]=null;
        readerList.options[0] = new Opyion('',0, false,false)
        
    }
}