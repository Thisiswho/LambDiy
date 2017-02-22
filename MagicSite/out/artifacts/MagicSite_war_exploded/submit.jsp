<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/11/6
  Time: 22:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>提交结果</title>
    <script src="script/jquery-3.0.0.min.js"></script>
    <script src="https://cdn1.lncld.net/static/js/av-min-1.2.1.js"></script>
    <script>




        window.onload = function(){

            $("#success").hide();
            $("#fail").hide();

            var APP_ID = 'tVEnu5PLpqDmIP1rYBkI7q9d-gzGzoHsz';
            var APP_KEY = 'JDHHXv8OogrO1dAwBx5gypr2';

            AV.init({
                appId: APP_ID,
                appKey: APP_KEY
            });



            requestData();
        }

        function request(paras)
        {
            var url = location.href;
            var paraString = url.substring(url.indexOf("?")+1,url.length).split("&");
            var paraObj = {}
            for (i=0; j=paraString[i]; i++){
                paraObj[j.substring(0,j.indexOf("=")).toLowerCase()] = j.substring(j.indexOf("=")+1,j.length);
            }
            var returnValue = paraObj[paras.toLowerCase()];
            if(typeof(returnValue)=="undefined"){
                return "";
            }else{
                return decodeURI(returnValue);
            }
        }


        function requestData(){
            var name = decodeURI(request("name"));
            var areaIndex = request("area");
            var area;
            switch (areaIndex){
                case "0":
                    area = "未选中";
                    break;
                case "1":
                    area = "紫藤公寓";
                    break;
                case "2":
                    area = "学苑公寓";
                    break;
                case "3":
                    area = "群星公寓";
                    break;
                case "4":
                    area = "校外";
                    break;
            }
            var telephone = request("telephone");
//            var wayIndex = request("way");
//            var way;
//            switch(wayIndex){
//                case "1":
//                    way = "面交";
//                    break;
//                case "2":
//                    way = "快递";
//                    break;
//            }
            var num = request("num");
            var price = parseInt(num) * 12;
            var qq = request("qq");
            var addon = request("addon");
            var buyRecord = AV.Object.extend('BuyRecord');
            var recordObject = new buyRecord();

            recordObject.save({
                name:name,
                area:area,
                telephone:telephone,
                num:num,
                price:price,
                qq:qq,
                addon,addon
            }).then(function(){
                $("#success").show();
            }).catch(function(err){
                $("#fail").innerText = err;
                alert(err);
                alert(err.toString());
            });
        }

    </script>
    <style>
    </style>
</head>
<body>
<div class="container">
    <div id="success">购买成功，请您耐心等待，我会稍后与您网络或短信联系。</div>
    <div id="fail">你看到这一行就说明购买失败了。</div>
</div>
</body>
</html>