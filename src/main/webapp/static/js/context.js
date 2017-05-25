function Context() {
	this.get_window_manager = function() {
		var _doc_ele = document.body || document.documentElement ;
		return {
			get_client_width : function() { // 获得客户的宽度
				return _doc_ele.clientWidth ;
			},
			get_client_height : function() { // 获得客户的高度
				return _doc_ele.clientHeight ;
			}
		}
	}
}
var context = new Context() ; // 创建上下文对象