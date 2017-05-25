<#assign ctx = request.contextPath>
<!DOCTYPE html>
<html>
<head>
<title>详情页</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${ctx }/static/bui/assets/css/dpl-min.css" rel="stylesheet"
	type="text/css" />
<link href="${ctx }/static/bui/assets/css/bui-min.css" rel="stylesheet"
	type="text/css" />
<link href="${ctx }/static/bui/assets/css/page-min.css" rel="stylesheet"
	type="text/css" />
<link href="${ctx }/static/bui/assets/css/myschool.css" rel="stylesheet" type="text/css" />
<!-- 下面的样式，仅是为了显示代码，而不应该在项目中使用-->
<link href="../assets/css/prettify.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx }/static/bui/assets/js/jquery-1.8.1.min.js"></script>
<script type="text/javascript" src="${ctx }/static/bui/assets/js/bui.js"></script>
<script type="text/javascript" src="${ctx }/static/bui/assets/js/config.js"></script>
<script type="text/javascript" src="${ctx }/static/bui/assets/js/myschool.js"></script>
<style type="text/css">
code {
	padding: 0px 4px;
	color: #d14;
	background-color: #f7f7f9;
	border: 1px solid #e1e1e8;
}
</style>
</head>
<body>

	<div class="container">
		<div class="welcome">欢迎页面</div>
		<p class="indextitle">欢迎使用Myschool系统. </p>
		<table cellspacing="0" class="table table-bordered indextable">
		<!-- 
	    <caption>一周动态[本次统计时间：2016-07-20 18:00:00,下次统计时间：2016-07-20 18:30:00]</caption>
		 -->
	    <caption>今日动态</caption>
	    <tbody>
		    <tr>
		        <td height="100">新增会员数：<span>${memberSize }个</span></td>
		        <c:if test="${!empty school }">
		        <td height="100">您好，${school.schoolName }！<span></span></td>
		        </c:if>
		        <c:if test="${empty school }">
		         <td height="100">新增学校：<span>${schoolSize }个</span></td>
		        </c:if>
		    </tr>
	    </tbody>
		</table>
		<table cellspacing="0" class="table table-bordered indextable">
	    <caption>待审核事项</caption>
	    <tbody>
		    <tr>
		        <td height="100">会员审核（<span>${checkSize }个</span>）</td>
		        <td height="100">转学审核（<span>0个</span>）</td>
		        <td height="100">调班审核（<span>0个</span>）</td>
		    </tr>
	    </tbody>
		</table>
	</div>
	<script type="text/javascript">
		BUI.use('common/page');
	</script>
	<!-- 仅仅为了显示代码使用，不要在项目中引入使用-->
	<script type="text/javascript" src="${ctx }/static/bui/assets/js/prettify.js"></script>
	<script type="text/javascript">
		$(function() {
			prettyPrint();
		});
	</script>
<body>
</html>

