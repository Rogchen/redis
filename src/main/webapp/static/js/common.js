/**
 * Set the Conponet of select to secified index.
 * @param {Object} Object
 * @param {Object} Value
 */
function compareListValue(Object, Value) {
	for (i = 0; i < Object.length; i++) {
		if (Value == Object.options[i].value) {
			Object.selectedIndex = i;
		}
	}
}
function compareListText(Object, Text) {
	for (i = 0; i < Object.length; i++) {
		if (Text == Object.options[i].text) {
			Object.selectedIndex = i;
		}
	}
}
function compareRadioValue(Object, Value) {
	for (i = 0; i < Object.length; i++) {
		if (Value == Object[i].value) {
			Object[i].checked = true;
		}
	}
}
function compareCheckBox(Object,Value){
    var boxs=Value.split(",");
    for (i = 0; i < Object.length; i++) {
        for(j = 0;j< boxs.length;j++){
			if (boxs[j] == Object[i].value) {
				Object[i].checked = true;
			}
		}
	}
}

function compareCheckBoxByOne(Object,Value){
	if (Value == Object.value) {
		Object.checked = true;
	}
}
/**
 * Get the value of the radio group.
 * @param {Object} _name
 */
function getRadioValue(_name) {
	var values = document.getElementsByName(_name);
	for (var i = 0; i < values.length; i++) {
		if (values[i].checked) {
			return values[i].value;
		}
	}
}
/**
 * Open a new window with the specified parameters
 * @param {Object} url
 * @param {Object} _name
 * @param {Object} iWidth
 * @param {Object} iHeight
 */
openwindow = function (url, _name, iWidth, iHeight) {
	var url;
	var name = _name;
	var iWidth;
	var iHeight;
	var iTop = ((window.screen.availHeight) - parseInt(iHeight)) / 2;
	var iLeft = ((window.screen.availWidth) - parseInt(iWidth)) / 2;
	newWindow = window.open(url, name, "height=" + iHeight + ",innerHeight=" + iHeight + ",width=" + iWidth + ",innerWidth=" + iWidth + ",top=" + iTop + ",left=" + iLeft + ",toolbar=no,menubar=no,scrollbars=auto,resizable=no,status=no");
	newWindow.focus();
	return newWindow;
};
/**
 * Clear the information in the page
 * @param {Object} tempObj
 */
function cleanComponentValues(formContainer) {
	with (formContainer) {
		for (var i = 0; i < elements.length; i++) {
			if (elements[i].type.toUpperCase() == "TEXT" || elements[i].type.toUpperCase() == "HIDDEN") {
				elements[i].value = "";
			}
			if (elements[i].tagName.toUpperCase() == "SELECT") {
				tempObj.item(i).selectedIndex = 0;
			}
			if (elements[i].tagName.toUpperCase() == "CHECKBOX") {
				elements[i].checked = false;
			}
		}
	}
}
/**
 * Set submit state
 * @param {Object} flag
 */
function submitState(flag) {
	var allComponents = document.getElementsByTagName("input");
	for (var i = 0; i < allComponents.length; i++) {
		if ((allComponents[i].type.toUpperCase() == "BUTTON" || allComponents[i].type.toUpperCase() == "SUBMIT" || allComponents[i].type.toUpperCase() == "IMAGE")) {
			if (flag == false) {
				allComponents[i].disabled = !false;
			} else {
				if (flag == !false) {
					allComponents[i].disabled = false;
				}
			}
		} else {
			continue;
		}
	}
}
/**
 * 
 * @param {Object} e
 */
function getLength(e) {
	if (e) {
		var str = e.replace(/[^\x00-\xff]/g, "**");
		return str.length;
	}
	return 0;
}
/**
 * 
 * @param {Object} str
 * @param {Object} begin
 * @param {Object} end
 */
function subString(str, begin, end) {
	var length = getLength(str);
	if (parseInt(length) > parseInt(end)) {
		return str.leftB(begin, end) + "...";
	} else {
		return str;
	}
}
/**
 * 
 * @param {Object} begin
 * @param {Object} len
 */
String.prototype.leftB = function (begin, len) {
	var s = this.replace(/\*/g, " ").replace(/[^\x00-\xff]/g, "**");
	return this.slice(0, s.slice(0, len).replace(/\*\*/g, " ").replace(/\*/g, "").length);
};
/**
 * 
 */
String.prototype.trim = function () {
	return this.replace(/(^\s*)|(\s*$)/g, "");
};
String.prototype.Trim = function () {
	return this.replace(/(^\s*)|(\s*$)/g, "");
};
String.prototype.LTrim = function () {
	return this.replace(/(^\s*)/g, "");
};
String.prototype.RTrim = function () {
	return this.replace(/(\s*$)/g, "");
};
/**
 * 
 */
String.prototype.toSimpleDate = function () {
	if (this.indexOf("-") != -1) {
		elements = this.split("-");
		if (elements.length == 3) {
			if (elements[1] != 10 && elements[2] != 10 && elements[2] != 20 && elements[2] != 30) {
				return elements[0] + "-" + (elements[1] + "-" + elements[2]).replace(/((0)*)/g, "");
			} else {
				return elements[0] + "-" + elements[1] + "-" + elements[2];
			}
		} else {
			return this.replace(/((0)*)/g, "");
		}
	}
};
/**
 * 
 * @param {Object} _form
 * @param {Object} _actionPath
 */
function modifyObject(_form, _actionPath) {
	with (_form) {
		var userId;
		var j = 0;
		var k = 0;
		for (var i = 0; i < elements.length; i++) {
			if ((elements[i].type == "checkbox") && (elements[i].checked == true)) {
				j++;
			}
		}
		for (var i = 0; i < elements.length; i++) {
			if (elements[i].type == "checkbox") {
				k++;
			}
		}
		if (k > 0 && j != 1) {
			if (k == 2 && j == 2) {
				action = _actionPath;
				submit();
			} else {
				alert("\u8bf7\u9009\u62e9\u9700\u8981\u4fee\u6539\u7684\u9879!");
			}
		} else {
			if (j == 1) {
				action = _actionPath;
				submit();
			}
		}
	}
}
/**
 * 
 * @param {Object} _form
 * @param {Object} _actionPath
 */
function queryObjects(_form, _actionPath) {
	with (_form) {
		action = _actionPath;
		submit();
	}
}
/**
 _orderFiled: table field;
_orderFlag:table field order(value:asc,desc);
 */
function sortByColumn(_orderFiled, _orderFlag, _form, _actionPath) {
	var orderFiled = "_id";
	var orderFlag = "asc";
	if (_orderFiled != "_id") {
		orderFiled = _orderFiled;
	}
	if(_orderFlag != null && _orderFlag !=""){
		if (_orderFlag == "desc") {
			orderFlag = "asc";
		}
		if (_orderFlag == "asc") {
			orderFlag = "desc";
		}
	}
	
	var newInput = document.createElement("input");   
    newInput.type="hidden";   
    newInput.name="sortby";  
    newInput.value=orderFiled +":"+ orderFlag;
    _form.appendChild(newInput);
    //_form.action = _actionPath + "?sortby_" + orderFiled + "=" + orderFlag;
	_form.submit();
}
//show editPage
function showEdit() {
	document.getElementById("spanEdit1").style.display = "";
	document.getElementById("spanEdit2").style.display = "";
	document.getElementById("spanView1").style.display = "none";
	document.getElementById("spanView2").style.display = "none";
}
//show listPage
function showView(urlwithparam) {
	if(urlwithparam!=null){
		window.location.href=urlwithparam;
	}else{
		history.go(0);
	}
	//document.getElementById("spanView1").style.display = "";
	//document.getElementById("spanView2").style.display = "";
	//document.getElementById("spanEdit1").style.display = "none";
	//document.getElementById("spanEdit2").style.display = "none";
	//clearErrorSpan();
	
}
function clearErrorSpan() {
	var errorSpans = document.getElementsByTagName("SPAN");
	if (errorSpans != null) {
		var mount=errorSpans.length;
		for (var i = 0; i <mount; i++) {
			alert(errorSpans[i].id);
			if (errorSpans[i].id == "__ErrorMessagePanel") {
				errorSpans[i].parentNode.removeChild(errorSpans[i]);
				mount--;
				i=0;
			}
		}
	}
}
function loadViewEdit(value) {
	if (value == "edit") {
		document.getElementById("spanView1").style.display = "none";
		document.getElementById("spanView2").style.display = "none";
		document.getElementById("spanEdit1").style.display = "";
		document.getElementById("spanEdit2").style.display = "";
	} else {
		document.getElementById("spanView1").style.display = "";
		document.getElementById("spanView2").style.display = "";
		document.getElementById("spanEdit1").style.display = "none";
		document.getElementById("spanEdit2").style.display = "none";
	}
}
//normal page begin
function viewOrEdit(value) {
	if (value == "edit") {
		$("#edit").css("display", "");
		$("#button-edit").css("display", "");
		$("#view").css("display", "none");
		$("#button-view").css("display", "none");
	} else {
		$("#edit").css("display", "none");
		$("#button-edit").css("display", "none");
		$("#view").css("display", "");
		$("#button-view").css("display", "");
	}
}
function gotoViewPage(urlwithparam) {
	if(urlwithparam!=null){
		window.location.href=urlwithparam;
	}else{
		history.go(0);
	}
	//$("#edit").css("display", "none");
	//$("#view").css("display", "");
}
function gotoEditPage() {
	$("#edit").css("display", "");
	$("#view").css("display", "none");
	$("#button-edit").css("display", "");
	$("#button-view").css("display", "none");	
}
//normal page end
//scrool page begin
function viewOrEditScrool(value) {
	if (value == "edit") {
		$("#edit").css("display", "");
		$("#button-edit").css("display", "");
		$("#view").css("display", "none");
		$("#button-view").css("display", "none");
	} else {
		$("#edit").css("display", "none");
		$("#button-edit").css("display", "none");
		$("#view").css("display", "");
		$("#button-view").css("display", "");
	}
	var height = document.body.clientHeight;
	var width = document.body.clientWidth;
	var offset_height = height - $("#div-button").height() - 70;
	var offset_width = width;
	if ($("#list").height() < offset_height) {
		$("#list").height($("#list").height()+35);
	} else {
		$("#list").height(offset_height);
	}
	if ($("#list").width() < offset_width) {
		$("#list").width($("#list").width());
	} else {
		$("#list").width(offset_width);
	}
}
function gotoScroolViewPage(urlwithparam) {
	if(urlwithparam!=null){
		window.location.href=urlwithparam;
	}else{
		history.go(0);
	}
	//$("#edit").css("display", "none");
	//$("#view").css("display", "");
}
function gotoScroolEditPage() {
	$("#edit").css("display", "");
	$("#button-edit").css("display", "");
	$("#view").css("display", "none");
	$("#button-view").css("display", "none");
}
//scroll page end
/*
	method name:getTabelHeight
	detai:This method is used implements scrool style for div2
*/
function getTabelHeight(div1, div2) {
	var height = document.body.clientHeight;
	var width = document.body.clientWidth;
	var offset_height = height - $("#"+div1).height()-40;
	var offset_width = width-50;
	if ($("#"+div2).height() < offset_height) {
	    $("#"+div2).height($("#"+div2).height()+40);	   
	} else {	   
		$("#"+div2).height(offset_height);		
	}
	if ($("#"+div2).width() < offset_width) {
		$("#"+div2).width($("#"+div2).width());		
	} else {
		$("#"+div2).width(offset_width);		
	}
}
function getTabelHeight1(div1, div2) {
	var height = document.body.clientHeight;
	var width = document.body.clientWidth;
	var offset_height = height - $("#"+div1).height()-40;
	var offset_width = width-50;
	if ($("#"+div2).height() < offset_height) {	    
	    $("#"+div2).height($("#"+div2).height());	   
	} else {	      
		$("#"+div2).height(offset_height);		
	}
	if ($("#"+div2).width() < offset_width) {	   
		$("#"+div2).width($("#"+div2).width());		
	} else {	    
		$("#"+div2).width(offset_width);		
	}
}
function getTabelHeightWithoutpageHolder(div1, div2) {
	var height = document.body.clientHeight;
	var width = document.body.clientWidth;
	var offset_height = height - $("#" + div1).height();
	var offset_width = width;
	if ($("#" + div2).height() < offset_height) {
		$("#" + div2).height($("#" + div2).height());
	} else {
		$("#" + div2).height(offset_height);
	}
	if ($("#" + div2).width() < offset_width) {
		$("#" + div2).width($("#" + div2).width());
	} else {
		$("#" + div2).width(offset_width);
	}
}
function deleteObjects(_form, _actionPath, _tableId) {
	var displayTable = document.getElementById(_tableId);
	var elements = displayTable.getElementsByTagName("input");
	var value = "";
	for (var i = 0; i < elements.length; i++) {
		if ((elements[i].type == "checkbox") && (elements[i].checked == true)) {
			if (!isNaN(elements[i].value)) {
				value = value + elements[i].value + ",";
			}
		}
	}
	if (value == "" || value == null) {
		alert("\u8bf7\u9009\u62e9\u60a8\u8981\u5220\u9664\u7684\u9879\u76ee\uff01");
	} else {
		if ((confirm("\u786e\u5b9a\u8981\u5220\u9664\uff1f"))) {
			with (_form) {
				action = _actionPath + "&ids=" + value;
				submit();
			}
		}
	}
}
function submitObjects(_form, _actionPath, _tableId, _errorMsg, _confirmMsg) {
	var displayTable = document.getElementById(_tableId);
	var elements = displayTable.getElementsByTagName("input");
	var value = "";
	for (var i = 0; i < elements.length; i++) {
		if ((elements[i].type == "checkbox") && (elements[i].checked == true)) {
			if (!isNaN(elements[i].value)) {
				value = value + elements[i].value + ",";
			}
		}
	}
	if (value == "" || value == null) {
		alert(_errorMsg);
	} else {
		if ((confirm(_confirmMsg))) {
			with (_form) {
				action = _actionPath + "&ids=" + value;
				submit();
			}
		}
	}
}
function exportObjects(_form, _actionPath, _tableId) {
	var displayTable = document.getElementById(_tableId);
	var elements = displayTable.getElementsByTagName("input");
	var varTr= displayTable.getElementsByTagName("tr");
	var value = "";
	if(varTr.length<=1){
	  alert('\u5f53\u524d\u5217\u8868\u4e2d\u65e0\u6570\u636e\u5bfc\u51fa');
	  return;
	}
	for (var i = 0; i < elements.length; i++) {
		if ((elements[i].type == "checkbox") && (elements[i].checked == true)) {
			if (!isNaN(elements[i].value)) {
				value = value + elements[i].value + ",";
			}
		}
	}

	if(value==""){
		if ((confirm("\u5982\u679c\u4f60\u8981\u5bfc\u51fa\u7279\u5b9a\u7684\u6570\u636e,\u8bf7\u628a\u8fd9\u4e9b\u6570\u636e\u9009\u4e2d;\u6ca1\u6709\u9009\u4e2d\u6570\u636e\u7684\u8bdd,\u5219\u9ed8\u8ba4\u5bfc\u51fa\u6240\u6709\u7684\u6570\u636e,\u4f46\u662f\u8fd9\u6837\u4f1a\u9700\u8981\u4e00\u4e9b\u65f6\u95f4,\u786e\u5b9e\u8981\u7ee7\u7eed\u5417?"))) {
			with (_form) {
				action = _actionPath + "&ids=" + value;
				submit();
			}
		}
	}else{
			with (_form) {
				action = _actionPath + "&ids=" + value;
				submit();
			}
	
	}
	
}

function IdCardValidate(_this) {
	var _exp = _this.match(/^(\d{2})\d{4}(((\d{2})(\d{2})(\d{2})(\d{3}))|((\d{4})(\d{2})(\d{2})(\d{3}[x\d])))$/i);
	if (_exp == null) {
		return false;
		_this.focus();
	}
	return true;
}

function getSexByIdCard(_this) {
	var _exp = _this.match(/^(\d{2})\d{4}(((\d{2})(\d{2})(\d{2})(\d{3}))|((\d{4})(\d{2})(\d{2})(\d{3}[x\d])))$/i);
	if (_exp[2].length == 12) {
		if ((_this.charAt(16) % 2) == 0) {
			return "female";
		} else {
			return "male";
		}
	} else {
		if ((_this.charAt(14) % 2) == 0) {
			return "female";
		} else {
			return "male";
		}
	}
}

function getBirthdayByIdCard(_this) {
	var _exp = _this.match(/^(\d{2})\d{4}(((\d{2})(\d{2})(\d{2})(\d{3}))|((\d{4})(\d{2})(\d{2})(\d{3}[x\d])))$/i);
	if (_exp[2].length == 12) {
		var birthday = [_exp[9], _exp[10], _exp[11]].join("-");
		return birthday;
	} else {
		var birthday = ["19" + _exp[4], _exp[5], _exp[6]].join("-");
		return birthday;
	}
}
function validateStr(_this) {
	var len = _this.length;
	var charArr = new Array();
	charArr = ["[", "]", "'", "\""];
	for (i = 0; i < len; i++) {
		for (j = 0; j < charArr.length; j++) {
			if (_this.charAt(i) == charArr[j]) {
				return false;
			}
		}
	}
	return true;
}
function clearState(elem) {
	with (elem) {
		if (style.color == "red") {
			style.color = "";
		}
		var lastNode = parentNode.childNodes[parentNode.childNodes.length - 1];
		if (lastNode.id == "__ErrorMessagePanel") {
			parentNode.removeChild(lastNode);
		}
	}
}
/**
* specical char validate
**/
function isSpecicalChar(theForm) {
	//alert('1');
	var obj = theForm;
	var count = obj.elements.length;
	var flag = true;
	for (var i = 0; i < count; i++) {
		with (obj.elements[i]) {
			clearState(obj.elements[i]);
			var especialChar=getAttribute("especialChar")!=null?getAttribute("especialChar"):"true";
			var _message = (getAttribute("specicalMsg") != null) ? getAttribute("specicalMsg") : "";
			if (obj.elements[i].type == "text" || obj.elements[i].type == "textarea") {
			    //此控件无需验证特殊字符
				
			    if(especialChar=="true"){
					if (validateStr(value) == false) {
						var span = document.createElement("SPAN");
						span.id = "__ErrorMessagePanel";
						span.style.color = "red";
						obj.elements[i].parentElement.appendChild(span);
						span.innerHTML = _message;
						flag = false;
						return;
					}
				}
				if(obj.elements[i].type == "textarea"){
				    obj.elements[i].value =obj.elements[i].value.replace(/(\s*$)/g, "");
				}
				
				if(obj.elements[i].type =="text"){
					if(getAttribute("spritBlock")) {
					}else {
					    obj.elements[i].value=obj.elements[i].value.replaceAll(" ", "");							   
					}}
				
			}
		}
	}
	return flag;
}
/**
* validate form specical char from div
**/
function isSpecicalCharDiv(theForm) {
	var obj = theForm;
	var count = obj.length;
	var flag = true;
	for (var i = 0; i < count; i++) {
		with (obj[i]) {
			clearState(obj[i]);
			var _message = (getAttribute("specicalMsg") != null) ? getAttribute("specicalMsg") : "";
			if (obj[i].type == "text" || obj[i].type == "textarea") {
				if (validateStr(value) == false) {
					var span = document.createElement("SPAN");
					span.id = "__ErrorMessagePanel";
					span.style.color = "red";
					obj[i].parentElement.appendChild(span);
					span.innerHTML = _message;
					flag = false;
					return;
				}
				
				if(obj[i].type == "textarea"){
				    obj[i].value =obj[i].value.replace(/(\s*$)/g, "");
				}
				
				if(obj[i].type =="text"){
					if(getAttribute("spritBlock")) {
					}else {
						obj[i].value=obj[i].value.replaceAll(" ", "");
												   
					}
												   
				}
				
			}
		}
	}
	return flag;
}
String.prototype.replaceAll = function (s1, s2) {
	return this.replace(new RegExp(s1, "gm"), s2);
};
function replaceSpecicalChar(theForm){
	var obj = theForm;
	var count = obj.elements.length;
	for (var i = 0; i < count; i++) {
		if(obj.elements[i].type == "text" || obj.elements[i].type == "textarea"){
			with (obj.elements[i]) {
			if(validateStr(value) == false){
				value = value.replaceAll("[\]'\"\[]","");
			}
			obj.elements[i].value = $.trim(value);
		}
		
		}
		
	}
	return true;
}
function selectRadio(_RadioName, _selectRadioValue, _prefixLabel) {
	var stateArr = document.getElementsByName(_RadioName);
	var labelStateArr;
	var prefixLabel;
	if (_prefixLabel == "" || _prefixLabel == "null" || typeof (_prefixLabel) == "undefined") {
		prefixLabel = _RadioName;
	} else {
		prefixLabel = _prefixLabel;
	}
	for (var i = 0; i < stateArr.length; i++) {
		labelStateArr = document.getElementById(prefixLabel + stateArr[i].value);
		if (stateArr[i].value == _selectRadioValue) {
			stateArr[i].checked = "checked";
			labelStateArr.style.border = "1px dotted black";
		} else {
			labelStateArr.style.border = "";
		}
	}
}

//init scroll for list page
function initScroll(navigation, button, page1, list, page2, tableid,isChangColor) {
//init height
	try{
		var total_height = document.body.clientHeight;//get the client height
		var navigation_height = $("#" + navigation).height();//get the navigation height of div 
		var button_height = $("#" + button).height();//get the button height of div 
		var page1_height = $("#" + page1).height();//get the page1 height of div 
		var list_height = $("#" + list).height();//get the list height of div 
		var page2_height = $("#" + page2).height();//get the page2 height of div 
		var offset_height = total_height - button_height - page1_height - page2_height - 55;//get the offset
		var total_width = document.body.clientWidth - 45;//get the width of list
		$("#" + list).width(total_width);//set the width for list
		if (list_height < offset_height) {
			if ($("#" + tableid).width() < total_width) {//dot not exist horizontal scrool
				$("#" + list).height($("#" + list).height());
			} else {//exist horizontal scrool
				$("#" + list).height($("#" + list).height() + 20);
			}
		} else {//if list_height>offset_height
			$("#" + list).height(offset_height);
		}
		if(isChangColor!=false){
			changeRowColor(tableid);//change table color
		}
	}catch(e){
	}
//init width	
}

//add by yxq  
function initScroll1(navigation, button, page1, list, page2, tableid,isChangColor) {
	try{
		var total_height = document.body.clientHeight;//get the client height
		var navigation_height = $("#" + navigation).height();//get the navigation height of div 
		var button_height = $("#" + button).height();//get the button height of div 
		var page1_height = $("#" + page1).height();//get the page1 height of div 
		var list_height = $("#" + list).height();//get the list height of div 
		var page2_height = $("#" + page2).height();//get the page2 height of div 
		var offset_height = total_height - button_height - page1_height - page2_height - 55;//get the offset
		var total_width = document.body.clientWidth - 45;//get the width of list
		$("#" + list).width(total_width);//set the width for list
		if (list_height < offset_height) {
			if ($("#" + tableid).width() < total_width) {//dot not exist horizontal scrool
				$("#" + list).height($("#" + list).height());
			} else {//exist horizontal scrool
				$("#" + list).height($("#" + list).height() + 20);
			}
		} else {//if list_height>offset_height
			$("#" + list).height(offset_height);
		}		
	}catch(e){
	}
//init width	
}

/**
function tablemouseover(_tableid) {			
	var dTable = document.getElementById(_tableid);	
	var eles = dTable.getElementsByTagName('span');
	for (var i = 0; i < eles.length; i++) {
		eles[i].onmouseover = function () {
			this.style.cursor = 'hand';
		}
	}
}

function initListBody(navigation,button,page1,list,page2,tableid){
  initScroll(navigation,button,page1,list,page2,tableid);
  tablemouseover(tableid);
}
**/
/**
*	Submit Form.
*/

function changeRowColor(tableId){
	//var as = document.getElementById(tableId).getElementsByTagName('a');
	var trs = document.getElementById(tableId).getElementsByTagName('tr');
	for(var i=0;i<trs.length;i++){
		(function(e){
				trs[i].onmouseover=function(){
					trs[e].style.backgroundColor='#FFE6E6';
				}
				trs[i].onmouseout=function(){
					if(e%2==0){
						trs[e].style.backgroundColor='EEE';
					}
					if(e%2==1){
						trs[e].style.backgroundColor='#ffffff';
					}
				}
		})(i);
	}
}

function submit(_form, _path, _param) {
	with (_form) {
		if (_param != undefined) {
			action = _path + _param;
		} else {
			action = _path;
		}
		submit();
	}
}
$o = function (_obj) {
	return document.getElementById(_obj);
};


function setQuery(){  
   document.getElementById('state_middlelayer').style.display = '';
   document.getElementById('state_showlayer').style.display = ''; 
}
function setQueryCondition(id1,id2){
	$("#"+id1).css("display","");
	$("#"+id2).css("display","");
	//document.getElementById('state_middlelayer').style.display = '';
    //document.getElementById('state_showlayer').style.display = ''; 
}
function hideState(){
   document.getElementById('state_middlelayer').style.display = 'none';
   document.getElementById('state_showlayer').style.display = 'none'; 
}
function hideStateDefined(id1,id2){
	$("#"+id1).css("display","none");
	$("#"+id2).css("display","none");
}
function setEndYearOptions(begin,end){
	var items = document.getElementById(begin).options;
	var checkValue = $("select[@id="+begin+"] option[@selected]").val();
	var _options = '';
	for(var i=0;i<items.length;i++){
		var val = items[i].value;
		if(val>=checkValue){
			_options=_options+"<option value="+val+">"+val+"</option>";
		}
	}
	$("#"+end).html(_options);
}