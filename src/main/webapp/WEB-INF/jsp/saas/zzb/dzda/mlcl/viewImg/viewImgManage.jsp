<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/inc/servlet.jsp" %>
<%@include file="/WEB-INF/jsp/inc/taglib.jsp" %>
<%--
  ~ Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
  ~ http://www.hn-hisun.com
  ~ 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
  --%>

<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>

	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<!-- END PAGE LEVEL STYLES -->
	<title>档案浏览</title>
</head>


<body >


<div class="container-fluid" >
	<!-- 脚本目录 -->
	<input id="showTpWidth" type="hidden"/>
	<div class="span6" style="width: 20%; margin: 0px;padding: 0px;background: #f1f3f6;border-right:1px solid #d8d8d8">
		<div class="portlet box grey" style="margin: 0px;padding: 0px;background: #f1f3f6">
				<div style="margin: 0px;padding: 0px">
					<Tree:tree id="viewImagesTree" treeUrl="${path}/zzb/dzda/mlcl/images/ajax/typeAndE01z1Tree/${a38Id}" token="${sessionScope.OWASP_CSRFTOKEN}"
							   onClick="viewImages" submitType="post" dataType="json" isSearch="false"/>
				</div>
		</div>
		<input type="hidden" name="lsE01z1Id" id="lsE01z1Id">
	</div>
	<div>
		<%--<div style="margin-bottom: 5px;text-align: right;margin-top: 10px">--%>
			<%--<input type="hidden" name="id"  value="${id }"/>--%>
			<%--&lt;%&ndash;<a href="javascript:void(0)" class="btn green" onclick="">打印</a>&ndash;%&gt;--%>
			<%--<button type="button" class="btn btn-default" data-dismiss="modal" onclick="hiddenViewImgModal()"><i class='icon-remove-sign'></i> 关闭</button>--%>
		<%--</div>--%>
		<div id="viewList" ></div>
	</div>
</div>
<script type="text/javascript">
	$(function(){
		$("#myDirName").val("${myDirName}");
		changeTreeDivHeight();
		//当浏览器大小改变的时候,要重新计算
		$(window).resize(function(){
			changeTreeDivHeight();
		})
	});

	function changeTreeDivHeight(){
		var divHeight = $(window).height()-65;
		$("#viewImagesTree_div").css('height',divHeight);
	}

	var zViewTree;
	$(document).ready(function() {
		zViewTree =  $.fn.zTree.getZTreeObj("viewImagesTree");//取得树对象
		var e01z1Id = "${e01z1Id}";
		if(e01z1Id!="") {
			var node = zViewTree.getNodeByParam('id', e01z1Id);// 获取id为-1的点
			zTree1.selectNode(node);
			zTree1.expandNode(node, true, false, true);
		}
		loadRight(e01z1Id);
	});
	function loadRight(nodeId) {
		$.ajax({
			async: false,
			type: "POST",
			url:"${path}/zzb/dzda/mlcl/images/ajax/viewImg",
			dataType: "html",
			headers: {
				"OWASP_CSRFTOKEN": '${sessionScope.OWASP_CSRFTOKEN}'
			},
			data: {
				"a38Id": "${a38Id}",
				"myDirName": "${myDirName}",
				"e01z1Id": nodeId,
				"isManage":"${isManage}"
			},
			success: function (html) {
				$("#viewList").html(html);
			},
			error: function () {
				showTip("提示", "出错了,请检查网络!", 2000);
			}
		});
	}

	// 点击事件
	function viewImages(event, treeId, treeNode){
		var  eApplyE01Z8Id = $("#eApplyE01Z8Id").val();
		var a38LogId = $("#a38LogId").val();
		if(eApplyE01Z8Id == undefined || eApplyE01Z8Id == "" || eApplyE01Z8Id == null){
			return;
		}
		//记录阅档日志
		if(treeNode.nodeType != "dir"){
			$.ajax({
				type: "POST",
				url:"${path}/zzb/dzda/cysq/ajax/detailViewTime",
				dataType: "html",
				data: {
					"a38LogId": a38LogId,
					"eApplyE01Z8Id": eApplyE01Z8Id,
					"e01z1Id": treeNode.id,
					"e01z111":treeNode.name
				},

				success: function (html) {
					$("#lsE01z1Id").val()
				},
				error: function () {
					//myLoading.hide();
					showTip("提示", "出错了,请检查网络!", 2000);
				}
			});
		}

		var nodeType = "0";
		var treeObj = zViewTree;
		var selectNodes = treeObj.getSelectedNodes();
		if(selectNodes.length > 1){
			return;
		}

		nodeType = treeNode.nodeType;
		if(treeNode!=null && nodeType == "cl") {
			loadRight(treeNode.id);
		}
	}
	function hiddenViewImgModal(){//隐藏图片查看时 删除临时的解密图片
		$('#viewImgModal').modal('hide');
		$('#viewImgDiv').html("");
		<%--var a38Id = $("#a38Id").val();--%>
		<%--var myDirName = $("#myDirName").val();--%>
		<%--$.ajax({--%>
		<%--url: "${path}/zzb/dzda/mlcl/images/delete/jmImages",--%>
		<%--type: "post",--%>
		<%--data: {--%>
		<%--"a38Id":a38Id,--%>
		<%--"myDirName":myDirName--%>
		<%--},--%>
		<%--headers: {--%>
		<%--OWASP_CSRFTOKEN: "${sessionScope.OWASP_CSRFTOKEN}"--%>
		<%--},--%>
		<%--dataType: "json",--%>
		<%--success: function (data) {--%>
		<%--if (data.success == "true" || data.success == true) {--%>

		<%--}else{--%>
		<%--showTip("提示", "删除解密图片失败，请联系管理员!", 1300);--%>
		<%--}--%>
		<%--},--%>
		<%--error: function () {--%>
		<%--showTip("提示", "出错了请联系管理员!", 1300);--%>

		<%--}--%>
		<%--});--%>
	}


</script>
</body>
</html>
