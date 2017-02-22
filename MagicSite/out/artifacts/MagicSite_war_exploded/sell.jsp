<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/11/6
  Time: 21:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width,initial-scale=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
  <title>【聊大】酷毙灯~</title>
  <script src="script/jquery-3.0.0.min.js"></script>
  <script src="script/bootstrap.min.js"></script>
  <script src="script/app.js"></script>
  <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
  <link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="container" id="head">
  <h1 class="text-center my-title" id="title">酷毙灯<small>——学习开黑好帮手</small></h1>
  <ul class="nav nav-tabs" role="tablist">
    <li role="presentation" class="active"><a href="#lamp" role="tab" data-toggle="tab">灯棍详情</a></li>
    <li role="presentation"><a href="#buy" role="tab" data-toggle="tab">购买方式</a></li>
    <li role="presentation"><a href="#com" role="tab" data-toggle="tab">联系我们</a></li>
  </ul>

  <div class="tab-content">



    <div role="tabpanel" class="tab-pane fade in active" id="lamp">

      <div class="separate center-block"></div>

      <div class="img_1">
        <h4 class="text-center">白光灯棍——附赠磁铁螺丝和粘胶。</h4>
        <img src="images/1.jpg" class="img-responsive" alt="样品">
      </div>
      <div class="separate center-block"></div>
      <div class="img_2">
        <h4 class="text-center">便捷放置——可以附着在任何表面。</h4>
        <img src="images/2.jpg" class="img-responsive" alt="样品">
      </div>
      <div class="separate center-block"></div>
      <div class="img_3">
        <h4 class="text-center">这是笔记本电脑在晚上没开灯的效果。</h4>
        <img src="images/3.jpg" class="img-responsive" alt="样品">
      </div>
      <div class="separate center-block"></div>
      <div class="img_4">
        <h4 class="text-center">这是笔记本电脑在晚上开了灯的效果。</h4>
        <img src="images/4.jpg" class="img-responsive" alt="样品">
      </div>
      <div class="separate center-block"></div>
      <div class="img_5">
        <h4 class="text-center">看书时光强也正合适，不伤眼睛。</h4>
        <img src="images/5.jpg" class="img-responsive" alt="样品">
      </div>

      <div class="text-center"><a href="#head">回到顶端</a></div>
    </div>

    <div role="tabpanel" class="tab-pane fade" id="buy">

      <div class="text-center">请填写您的个人信息，并且支付完成购买。</br></div>
      </br>
      <form class="form-inline" role="form" method="get" action="submit.jsp" >
        <div class="form-group">
          <div class="input-group">
            <div class="input-group-addon">姓氏</div>
            <input class="form-control" id="name" type="text" name="name" placeholder="请填写您的姓氏，以便称呼">
          </div>
        </div>
        <div class="form-group">
          <div class="input-group">
            <div class="input-group-addon">宿舍</div>
            <select class="form-control"name="area">
              <option value="0" checked>请选择您的宿舍</option>
              <option value="1">紫藤公寓</option>
              <option value="2">学苑公寓</option>
              <option value="3">群星公寓</option>
              <option value="4">校外</option>
            </select>
          </div>
        </div>
        <div class="form-group">
          <div class="input-group">
            <div class="input-group-addon">电话</div>
            <input class="form-control" type="text" name="telephone" placeholder="请填写您的电话号码" id="telephone">
          </div>
        </div>
        <div class="form-group">
          <div class="input-group">
            <div class="input-group-addon">QQ或微信</div>
            <input class="form-control" type="text" name="qq" placeholder="请填写您的QQ或者微信" id="qq">
          </div>
        </div>
        <div class="form-group">
          <div class="input-group">
            <div class="input-group-addon">备注</div>
            <textarea class="form-control" name="addon" placeholder="您还有啥要求从这写~" id="addon"></textarea>
          </div>
        </div>
        <!--<div class="form-group text-center" onchange="selectWay()">-->
        <!--<p>请选择交易方式。</p>-->
        <!--<div class="radio-inline">-->
        <!--<label>-->
        <!--<input type="radio" name="way" value="1" checked>当面交易-->
        <!--</label>-->
        <!--</div>-->
        <!--<div class="radio-inline">-->
        <!--<label>-->
        <!--<input type="radio" name="way" value="2">邮递交易-->
        <!--</label>-->
        <!--</div>-->

        <!--<p id="fee">邮递交易需要支付10元邮费。</p>-->
        <!--</div>-->
        <div class="form-group text-center" onchange="selectPrice()">
          <p>请选择您要购买的数量。</p>
          <div class="radio-inline">
            <label>
              <input type="radio" name="num" value="1" checked>1个
            </label>
          </div>
          <div class="radio-inline">
            <label>
              <input type="radio" name="num" value="2">2个
            </label>
          </div>
          <div class="radio-inline">
            <label>
              <input type="radio" name="num" value="3">3个
            </label>
          </div>
          <div class="radio-inline">
            <label>
              <input type="radio" name="num" value="4">4个
            </label>
          </div>
        </div>
        <div class="text-center">
          <span>价格：</span>
          <span id="price_1">12元</span>
          <span id="price_2" class="red"></span>
        </div>
        <button type="submit" class="btn btn-block">提交</button>
      </form>
    </div>

    <div role="tabpanel" class="tab-pane fade" id="com">
      <div class="text-center">
        如果有什么问题，可以联系我。<br>
        或者直接联系我买灯。<br>
        QQ：<a href="tencent://message/?uin=4542879&Site=&Menu=yes">4542879</a><br>
        电话： <a href="tel:17862516964">17862516964</a>
      </div>
    </div>
  </div>

  <footer class="text-center" style="margin-top: 20px">
    a
  </footer>
</div>
</body>

</html>