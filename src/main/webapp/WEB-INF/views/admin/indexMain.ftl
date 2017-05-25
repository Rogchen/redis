<#assign ctx = request.contextPath>
<!DOCTYPE html>
<html>
<head>
    <title>admin_Index</title>
    <style>
        body{ text-align:center}
        .ul{float:left;}
    </style>
</head>
<body>
<h2 style="color: crimson">欢迎使用权限管理系统</h2>
<h3>欢迎${(user.userName)!}您的真实姓名是：${(user.realName)!}</h3>
<div class="content">
    <div class="left">
        <ul class="ul">
            <li><a class="button" href="http://www.baidu.com">百度</a></li>
            <li><a class="button" href="https://www.google.com">谷歌</a></li>
            <li><a class="button"href="http://www.biying.com">必应</a></li>
            <li><a class="button"href="http://www.souguo.com">搜狗</a></li>
            <li><a class="button"href="http://www.360.com">360</a></li>
            <li><a class="button"href="http://www.msn.com">msn</a></li>
        </ul>
    </div>
    <div class="right">
      <textarea id="textArea" style="margin: 0px; width: 1251px; height: 159px;" placeholder="我们开始测试权限"></textarea>
    </div>
</div>
</body>
</html>
