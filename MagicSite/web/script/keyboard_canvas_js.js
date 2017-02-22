/**
 * Created by Administrator on 2017/1/5.
 */

var c;
var cxt;
window.onload = function(){
        c = document.getElementById("myCanvas");
        cxt=c.getContext("2d");
        cxt.fillStyle = "#223354"
        cxt.fillRect(0,0,20,20);
        var img=new Image();
        img.src="logo.png";
        img.onload = function(){
            cxt.drawImage(img,0,0);
        }
}
