function getContentHeight(titleDiv,topDiv,footDiv){
    titleDiv = titleDiv || $('.title-bar');
    topDiv = topDiv || $('.content-top');
    footDiv = footDiv || $('.content-foot');
    var totalHeight = BUI.viewportHeight();
    var titHeight = titleDiv.outerHeight() || 0;
    var topHeight = topDiv.outerHeight() || 0;
    var footHeight = footDiv.outerHeight() || 0;
    return totalHeight - titHeight - topHeight - footHeight - 10;
}