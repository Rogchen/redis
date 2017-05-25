<#assign ctx = request.contextPath>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Rogchen</title>
<link rel="shortcut icon" href="${ctx }/static/favicon.ico" type="image/x-icon" />
<link href="${ctx }/static/bui/assets/css/dpl-min.css" rel="stylesheet" type="text/css" />
<link href="${ctx }/static/bui/assets/css/bui-min.css" rel="stylesheet" type="text/css" />
<link href="${ctx }/static/bui/assets/css/main-min.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx }/static/bui/assets/js/jquery-1.8.1.min.js"></script>
<script type="text/javascript" src="${ctx }/static/bui/assets/js/bui.js"></script>
<script type="text/javascript" src="${ctx }/static/bui/assets/js/config.js"></script>
</head>
<body>
	<div class="header">
		<div class="dl-title">
				<span class="lp-title-port">Rogchen_Blog</span>
				<span class="dl-title-text"></span>
		</div>
		<div class="dl-log">
			欢迎您，<span class="dl-log-user" property="adminName" />Rogchen</span>
			<a href="${ctx }/logout" title="退出系统" class="dl-log-quit" id="adminInfo">[退出]</a>
		</div>
	</div>

	<div class="content">
		<div class="dl-main-nav">
			<ul id="J_Nav" class="nav-list ks-clear">
				<input type="hidden" id="adminType" value="${adminType!}" />
				<li class="nav-item dl-selected">
					<div class="nav-item-inner nav-home">首页</div></li>
					<li class="nav-item"><div class="nav-item-inner nav-order">关于我</div></li>
					<li class="nav-item"><div class="nav-item-inner nav-order">blog</div></li>
					<li class="nav-item"><div class="nav-item-inner nav-inventory">系统设置</div></li>
			</ul>
		</div>
		<ul id="J_NavContent" class="dl-tab-conten">
		</ul>
	</div>
<script type="">
$('.nav-item').eq(0).trigger('click');
</script>
<script>
    BUI.use('common/main', function () {
		var config=[{
			id: 'home',
			homePage: 'index',
			collapsed: false, //默认左侧菜单收缩
			menu: [
				{
					text: '首页',
					items: [
						{id: 'index', text: '首页', href:'http://www.baidu.com', closeable: false}
					]
				}
			]
		}, {
            id: 'school',
            menu: [{
                text: '关于我',
                items: [
                    {id: 'AboutUs', text: '学校列表', href: '${ctx}/admin/school/list'}
                ]
            }]
        }, {
            id: 'pub_blog',
            menu: [{
                text: 'blog列表',
                items: [
                    {id: 'personalBlog', text: '个人博客列表', href: '${ctx}/admin/sa/personalBlogList'}
                ]
            }]
        },{
			id:'setting',
			menu:[{
				text:'系统设置',
				items:[
					{id:'adminAdminList',text:'后台管理员',href:'${ctx}/admin/adminAdmin/list'},
					{id:'permissionList',text:'权限管理',href:'${ctx}/admin/permission/list'},
					//{id:'adminRoleList',text:'角色管理',href:'${ctx}/admin/adminRole/list'},
					//  {id:'adminPermissionList',text:'权限管理',href:'${ctx}/admin/permission/list'},
					{id:'modify',text:'修改密码',href:'${ctx}/admin/adminAdmin/toEdit?id=${(admin.id)!}'},
					{id:'articleList',text:'文章列表',href:'${ctx}/admin/article/list'},
				]
			}]
		}];
        	new PageUtil.MainPage({
            modulesConfig: config
        });
    });




    </script>

</body>
</html>