$(document).ready(function(){
		 $('.son_ul').hide(); //初始ul隐藏
		 $('.select_box span').hover(function(){ //鼠标移动函数
		 	$(this).parent().find('ul.son_ul').stop(true,false).slideDown();  //找到ul.son_ul显示
		 	$(this).parent().find('li').hover(function(){$(this).addClass('hover')},function(){$(this).removeClass('hover')}); //li的hover效果
		 	$(this).parent().hover(function(){},function(){$(this).parent().find("ul.son_ul").slideUp(); });
		 },function(){});
		 $('ul.son_ul li').click(function(){
			$(this).parents('li').find('span').html($(this).html());
			$(this).parents('li').find('ul').slideUp();
		 });
		  $("#txtHot").blur(function(){
		  	var txtHot=$(this).val();
		  	if(txtHot==""){
			  $(this).val("热门搜索： 洗车、 自助餐、 牛排、 烤鱼"); //this.defaultValue
		  	};
		}).focus(function(){
		 	 var txtHot=$(this).val();
		  	 if(txtHot=="热门搜索： 洗车、 自助餐、 牛排、 烤鱼"){
			  	$(this).val("");
		 	 };
		});
		
		  $(".payTxt").blur(function(){
		  	var txtHot=$(this).val();
		  	if(txtHot==""){
			  $(this).val(this.defaultValue); 
			 $(this).removeClass("inputOver");
		  	};
		}).focus(function(){
		 	 var txtHot=$(this).val();
		  	 if(txtHot==this.defaultValue){
			  	$(this).val("");
				 $(this).addClass("inputOver");
		 	 };
		});

		$(".colSub dd a").click(function(){
			//$("#rightFrame")[0].src=$(this).attr("url");
			$(".colSub dd a").removeClass("current");
			$(this).addClass("current");
		});
		

		$(".colSub dt").click(function(){
			$(this).nextUntil('dt').toggle();
		});

		$(".colSub dd a[href*='"+window.location.pathname+"']").closest("dl").children("dd").show();

});


function onload(event) {
SetWinHeight(this);
}

//iframe自动伸缩
function SetWinHeight(obj)
{
var win=obj;
var height,h1,h2;
if (document.getElementById)
{
if (win && !window.opera)
{
win.style.height="0px";
var f = document.getElementById('rightFrame');
if(f.contentWindow.document.body.scrollHeight!=null && f.contentWindow.document.body.scrollHeight!=0)
height = f.contentWindow.document.body.scrollHeight;
else if(f.contentWindow.document.documentElement.scrollHeight != 0)
height = f.contentWindow.document.documentElement.scrollHeight;
win.style.height=height+20+"px";
}
}
}			


function clickZJCJJL(){
	$(".colSub dd a[href='乐享拍成交记录.html']").click();
	$("#rightFrame").attr("src","中奖人统计.html")
	return false;
}
