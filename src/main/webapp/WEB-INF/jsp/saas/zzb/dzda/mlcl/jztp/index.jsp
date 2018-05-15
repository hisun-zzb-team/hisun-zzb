<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/inc/taglib.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%--
  ~ Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
  ~ http://www.hn-hisun.com
  ~ 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
  --%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>加载图片</title>
<link href="${path}/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css"/>

</head>
<body>

<div class="container-fluid">
	<div class="row-fluid">
		<div class="main_left" style="width:25%">
			<div class="portlet box grey mainleft">
				<div class="portlet-body leftbody">
							<input type="hidden" id="currentNodeId"  name="currentNodeId" value="" />
							<input type="hidden" id="currentNodeName"  name="currentNodeName" value="" />
							<input type="hidden" id="currentNodeParentId"  name="currentNodeParentId" value="" />
							<Tree:tree id="a38MlclTree" treeUrl="${path}/zzb/dzda/mlcl/jztp/ajax/tree/${a38Id}" token="${sessionScope.OWASP_CSRFTOKEN}"
									   onClick="onClickByTreeByTpsc" submitType="post" dataType="json" isSearch="false"/>

				</div>
			</div>
		</div>
		<div class="main_right" id="jztpList" ></div>
	</div>
</div>
<script type="text/javascript" src="${path}/js/common/est-validate-init.js"></script>
<script type="text/javascript" src="${path}/js/common/validate-message.js"></script>
<script type="text/javascript">
	$(function(){
		changeTreeDivHeight();
		//当浏览器大小改变的时候,要重新计算
		$(window).resize(function(){
			changeTreeDivHeight();
		})
	});
	function changeTreeDivHeight(){
		var divHeight = $(window).height()-100;
		$("#a38MlclTree_div").css('height',divHeight);
	}

	function onClickByTreeByTpsc (event, treeId, treeNode){
		$("#currentNodeId").val(treeNode.id);//赋值
		$("#currentNodeName").val(treeNode.name);//赋值
		$("#currentNodeParentId").val(treeNode.pId);//赋值
		if(treeNode.pId==null || treeNode.pId==''){
			refreshFileList();
		}else{
			if(treeNode.nodeType=="dir"){
				refreshFileList(treeNode.key);
			}else{
				var zTree1 = $.fn.zTree.getZTreeObj("a38MlclTree");
				var parentNode = zTree1.getNodeByParam('id',treeNode.pId);// 获取id为-1的点
				refreshFileList(parentNode.key,treeNode.key);
			}
		}
	}


	$(document).ready(function(){
		App.init();//必须，不然导航栏及其菜单无法折叠
		var zTree = $.fn.zTree.getZTreeObj("a38MlclTree");//取得树对象
		var node = zTree.getNodes()[0];// 获取第一个点
		$("#currentNodeId").val(node.id);//赋值
		$("#currentNodeName").val(node.name);//赋值
		$("#currentNodeParentId").val(node.pId);//赋值
		$.ajax({
			cache:false,
			type: 'POST',
			dataType : "html",
			data:{
				"currentNodeId":node.id,
				"currentNodeParentId":node.pId,
				"currentNodeName":node.name
			},
			headers: {
				"OWASP_CSRFTOKEN":"${sessionScope.OWASP_CSRFTOKEN}"
			},
			url: "${path}/zzb/dzda/mlcl/jztp/ajax/list/${a38Id}",// 请求的action路径
			error: function () {// 请求失败处理函数
				alert('请求失败');
			},
			success:function(html){
				$("#jztpList").html(html);
			}
		});
		zTree.selectNode(node);//默认选中
		zTree.expandNode(node, true, false , true);//展开
	});

	function refreshTree() {
		$("#a38MlclTree").empty();
		refreshTreeTag("a38MlclTree",setting_a38MlclTree,"");
		selectNodeTree();
	}
	function selectNodeTree(){
		var zTree1 = $.fn.zTree.getZTreeObj("a38MlclTree");
		var id = $("#currentNodeId").val();
		var node = zTree1.getNodeByParam('id',id);// 获取id为-1的点
		zTree1.selectNode(node);
		zTree1.expandNode(node, true, false , true);
	}



</script>
</body>
</html>