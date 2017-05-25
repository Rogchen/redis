var _list = null ;
var _tab_host = null ;
var _fragments = null ;
function init_objects() {
	_list = $("#list") ;
	_tab_host = $(".tab_host") ;
	_fragments = $("#fragments .fragment", _tab_host)
}
/*
function init_view() {
	$("tbody tr:odd", _list).css("background", "#f1f1f1") ;
	

	var _on_index = $("a.on", _tab_host).index() ;
	_fragments.eq(_on_index).show() ;

	$("a", _tab_host).click(function() {
		$(this).addClass("on").siblings().removeClass("on") ;
		_fragments.eq($(this).index()).show().siblings().hide() ;
	}) ;
}
*/
$(function() {
	init_objects() ;
	//init_view() ;
}) ;

$(function(){
	$(".subNav").click(function(){
		$(this).toggleClass("currentDt");

		$(this).next().slideToggle(500) ;
	})	
})	

$(function(){
	$("#user_header").click(function() {
		if(this.is_click) {
			this.is_click = false ;
			$("#user_info #menu").slideUp(300) ;
		} else {
			this.is_click = true ;
			$("#user_info #menu").slideDown(300) ;

		}
	}) ;
}) ;

window.onload = function() {
	var _first_scroll_height = document.body.scrollHeight ;
	var _last_scroll_height = document.documentElement.scrollHeight ;
	var _height = _first_scroll_height > _last_scroll_height ? _first_scroll_height : _last_scroll_height ;

	$('.menus').height(_height) ;
}


/****权限选中*****/
$(function(){
	var a ;
	var b ;
	var to_right ;
	var to_left ;
	function init_objects() {
		a = $("#a") ;
		b = $("#b") ;
		to_right = $("#to_right") ;
		to_left = $("#to_left") ;	
	}
	
	function select(parent, option) {
		parent.append(option) ;
	}
	function init_view() {
		$("#a").dblclick(function() {
			var _option = $(this.options[this.selectedIndex]) ;
			select(b, _option) ;
		}) ;
	
		$("#b").dblclick(function() {
			var _option = $(this.options[this.selectedIndex]) ;
			select(a, _option) ;
		}) ;
	
		to_right.click(function() {
			// var options = new Array() ;
			for(var i = 0 ; i< a[0].options.length ;i++) {
				var option = a[0].options[i] ;
				if(option.selected) {
					// options.push(option) ;
					select(b, option) ;
					i--;
				}
			}
			//select(b, options) ;
		}) ;
		
		to_left.click(function() {
			
			for(var i = 0 ; i< b[0].options.length ;i++) {
				var option = b[0].options[i] ;
				if(option.selected) {
					
					select(a, option) ;
					i--;
				}
			}
			
		}) ;	
		
	}
	$(function() {
		init_objects() ;
		init_view() ;
	
	}) ;	
})
