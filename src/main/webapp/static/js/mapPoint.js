/**
 * 根据经纬度定位，显示标注
 */
function mapPoint(longitude,latitude){
	if(longitude !=0 ){
    	var new_point = new BMap.Point(longitude,latitude);
    	// 百度地图API功能
    	var map = new BMap.Map("allmap");
        map.centerAndZoom(new_point, 14);
        map.enableScrollWheelZoom();    //启用滚轮放大缩小，默认禁用
        map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用
        map.addControl(new BMap.NavigationControl());  //添加默认缩放平移控件
        map.addControl(new BMap.OverviewMapControl()); //添加默认缩略地图控件
   	
	    map.clearOverlays();//清空原来的标注
		var marker = new BMap.Marker(new_point);  // 创建标注
		map.addOverlay(marker);              // 将标注添加到地图中
		map.panTo(new_point);  
		
		var gc = new BMap.Geocoder();
		gc.getLocation(new_point, function(rs){
		   var addComp = rs.addressComponents;
		   var address =  addComp.province + addComp.city + addComp.district + addComp.street + addComp.streetNumber;
		   var content = address + "<br><br/>${info }";
	       var infoWindow = new BMap.InfoWindow("<p style='font-size:14px;'>" + content + "</p>");
	       marker.addEventListener("click", function () { this.openInfoWindow(infoWindow); });
		});
    }else{
    	//alert("无法定位用户坐标！");
    }
}