
window.onload = function(){

    var APP_ID = 'hJXrFJLHqFwx2BcV9FE4JCVh-gzGzoHsz';
    var APP_KEY = 'hlgBDhF75y5MzfvsxcrQ2jpn';

    AV.init({
        appId: APP_ID,
        appKey: APP_KEY
    });

};

var mProducts;

function getNews(){
    var query = new AV.Query('News');
    query.descending('createAt');
    query.find().then(function(products){
        mProducts = products;
        var newsList = document.getElementById("newsList");
        newsList.innerHTML = "";
        for(var i = 0;i < products.length;i++){
            var button = document.createElement("button");
            button.setAttribute("type","button");
            button.setAttribute("class","list-group-item");
            button.innerHTML = products[i].get("title");
            newsList.appendChild(button);
        }
    }).catch(function(error){

    });
}

function selectNews(button){
    alert(button);
}
