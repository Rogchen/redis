<#assign ctx = request.contextPath>
<!DOCTYPE HTML>
<html>
<head>
    <title>新闻列表</title>
<#include "${ctx}/includes/sa/header.ftl"/>
</head>
<body>

<div class="container">
    <div class="content-top">
        <form id="searchForm" class="form-horizontal">
            <div class="row">
                <div class="control-group">
                    <div class="controls control-row-auto">
                        关键词：
                        <input type="text" class="control-text mr" placeholder="请输入标题" name="search_LIKE_title">
                        <button type="button" id="btnSearch" class="button button-primary">搜索</button>
                    </div>
                </div>
            </div>
        </form>
    </div>
    ${sb!}
    <div class="content-body">
        <div id="grid"></div>
    </div>

</div>

<script type="text/javascript">
    BUI.use('common/page');
</script>

<script type="text/javascript">
//    $.post("/admin/sa/gridJson",function (data){
//        console.log(data);
//        console.log(eval(data));
//    });



    BUI.use('common/search',function (Search) {

         var enumObj = {"1":"启用","2":"禁用"},
        editing = new BUI.Grid.Plugins.DialogEditing({
            contentId : 'content', //设置隐藏的Dialog内容
            autoSave : true, //添加数据或者修改数据时，自动保存
            //triggerCls : 'btn-edit'
        }),

        columns = [
            {title:'博客编号',dataIndex:'id',width:80},
            {title:'博客图片',dataIndex:'id',width:100, renderer : function(value, rowObj){
                var img_url = "";
                if(value!=null){
                    img_url = '<img src="${ctx}'+value+'" style="width:30px;height: 30px;"/>';
                }else{
                    img_url = '<img src="" style="width:30px;height: 30px;"/>';
                }
                return img_url;
            }},
            {title:'博客标题',dataIndex:'blogTitle',width:300},
            {title:'创建时间',dataIndex:'createTime',width:150},
            /*  {title:'状态',dataIndex:'status',width:50,renderer:BUI.Grid.Format.enumRenderer(enumObj)}, */
            {title:'操作',dataIndex:'',width:200,renderer : function(value,obj){
                var str = '';
                str +=  '<span class="grid-command btn-edit" title="编辑">编辑</span>';
                str += '<span class="grid-command btn-del" title="删除">删除</span>';//页面操作不需要使用Search.createLink
                return str;
            }}
        ],

        store = Search.createStore('${ctx }/admin/sa/gridJson',{
            proxy : {
                save : { //也可以是一个字符串，那么增删改，都会往那么路径提交数据，同时附加参数saveType
                    addUrl : '${ctx }/admin/news/edit',
                    removeUrl : '${ctx }/admin/news/delete',
                    /*    closeUrl : '${ctx}/admin/news/close'
*/	          }/*,
      method : 'POST'*/
            },
            pageSize : 5,
            autoSync : true //保存数据后，自动更新
        }),

        gridCfg = Search.createGridCfg(columns,{
            width:'100%',
            height: getContentHeight(),
            tbar : {
                items : [
                    {text : '新建',btnCls : 'button',handler:addFunction},
                    {text : '删除',btnCls : 'button',handler : delFunction}

                 ],
                pagingBar:false
            },
         plugins : [editing,BUI.Grid.Plugins.CheckSelection,BUI.Grid.Plugins.AutoFit] // 插件形式引入多选表格
    });

        var  search = new Search({
                    store : store,
                    gridCfg : gridCfg
                }),
                grid = search.get('grid');

        function addFunction(){
            top.topManager.openPage({
                href : '${ctx}/admin/news/add',
                title : '新建新闻'
            });
        }

        //删除操作
        function delFunction(){
            var selections = grid.getSelection();
            if(selections.length==0){
                BUI.Message.Alert("没有选择记录！");
            }
            delItems(selections);
        }
        //监听事件，删除一条记录
        grid.on('cellclick',function(ev){
            var sender = $(ev.domTarget); //点击的Dom
            if(sender.hasClass('btn-del')){
                var record = ev.record;
                delItems([record]);
            }
            if(sender.hasClass('btn-edit')){//编辑
                var record = ev.record;
                editItems(record.id);
            }
        });
        function editItems(id){
            window.location.href='${ctx }/admin/news/edit?id='+id;
        }

        function delItems(items){
            var ids = [];
            BUI.each(items,function(item){
                ids.push(item.id);
            });

            if(ids.length){
                BUI.Message.Confirm('确认要选中的记录么？',function(){store.save('remove',{ids : ids}); },'question');
            }
        }
        //禁用与启用
        function closeItems(items){
            var ids = [];
            BUI.each(items,function(item){
                ids.push(item.id);
            });

            if(ids.length){
                store.save('close',{ids : ids});
            }
        }



        //监听事件，禁用一条记录
        grid.on('cellclick',function(ev){
            var sender = $(ev.domTarget); //点击的Dom
            if(sender.hasClass('btn-clo')){
                var record = ev.record;
                closeItems([record]);
            }
        });
    });
</script>
</body>
</html>
