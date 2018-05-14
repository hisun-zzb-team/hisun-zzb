<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/inc/servlet.jsp" %>
<%@include file="/WEB-INF/jsp/inc/taglib.jsp" %>
<%--
  ~ Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
  ~ http://www.hn-hisun.com
  ~ 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
  --%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>单位管理</title>
<style type="text/css">
	ul.ztree{margin-bottom: 10px; background: #f1f3f6 !important;}
	.portlet.box.grey.mainleft{background-color: #f1f3f6;overflow: hidden; padding: 0px !important; margin-bottom: 0px;}
	.main_left{float:left; width:220px;  margin-right:10px; background-color: #f1f3f6; }
	.main_right{display: table-cell; width:2000px;}
</style>
</head>
<body>
		<div class="main_left">
			<div class="portlet box grey mainleft">
				<div class="portlet-body leftbody">
					<input type="hidden" id="eCatalogTypeTreeId"  name="eCatalogTypeTreeId" value="" />
					<input type="hidden" id="eCatalogTypeTreeName"  name="eCatalogTypeTreeName" value="" />
					<input type="hidden" id="eCatalogTypeTreeParentId"  name="eCatalogTypeTreeParentId" value="" />
					<input type="hidden" id="a38Id"  name="a38Id" value="${a38Id}" />
					<Tree:tree id="leftTree" treeUrl="${path}/zzb/dzda/e01z1/tree" token="${sessionScope.OWASP_CSRFTOKEN}"
							   onClick="onClickByTree" submitType="post" dataType="json" isSearch="false"/>
				</div>
			</div>
		</div>
		<div class="main_right" id="rightList" ></div>
<script type="text/javascript">
	$(function(){
		changeTreeDivHeight();
		//当浏览器大小改变的时候,要重新计算
		$(window).resize(function(){
			changeTreeDivHeight();
		})
	});
	function changeTreeDivHeight(){
		var divHeight = $(window).height()-170;
		$("#leftTree_div").css('height',divHeight);
	}

	function onClickByTree (event, treeId, treeNode){
		$("#eCatalogTypeTreeId").val(treeNode.id);//赋值
		$("#eCatalogTypeTreeCode").val(treeNode.key);//赋值
		$("#eCatalogTypeTreeName").val(treeNode.name);//赋值
		$("#eCatalogTypeTreeParentId").val(treeNode.pId);//赋值
		var a38Id = $("#a38Id").val();
		$.ajax({
			url : "${path}/zzb/dzda/e01z1/ajax/mlxxList",
			type : "get",
			data : null,
			dataType : "html",
			headers: {
				"OWASP_CSRFTOKEN":"${sessionScope.OWASP_CSRFTOKEN}"
			},
			data:{
				"eCatalogTypeTreeId":treeNode.id,
				"eCatalogTypeTreeCode":treeNode.key,
				"eCatalogTypeTreeParentId":treeNode.pId,
				"a38Id":a38Id
			},
			success : function(html){
				$("#rightList").html(html);
			},
			error : function(){

			}
		});
	}

	$(document).ready(function(){
		App.init();//必须，不然导航栏及其菜单无法折叠
		var zTree = $.fn.zTree.getZTreeObj("leftTree");//取得树对象
		var node = zTree.getNodes()[0];// 获取第一个点
		$("#eCatalogTypeTreeId").val(node.id);//赋值
		$("#eCatalogTypeTreeName").val(node.name);//赋值
		$("#eCatalogTypeTreeParentId").val(node.pId);//赋值
		$("#eCatalogTypeTreeCode").val(node.key);//赋值
		var a38Id = $("#a38Id").val();
		$.ajax({
			url: "${path}/zzb/dzda/e01z1/ajax/mlxxList",// 请求的action路径
			type: 'POST',
			dataType : "html",
			data:{
				"eCatalogTypeTreeId":"",
				"eCatalogTypeTreeCode":"",
				"eCatalogTypeTreeParentId":"",
				"eCatalogTypeTreeName":"所有材料",
				"a38Id":a38Id
			},
			headers: {
				"OWASP_CSRFTOKEN":"${sessionScope.OWASP_CSRFTOKEN}"
			},
			success : function(html){
				$("#rightList").html(html);
			},
			error : function(){
				alert('请求失败');
			}
		});
		zTree.selectNode(node);//默认选中
		zTree.expandNode(node, true, false , true);//展开
	});

	function refreshTreeManege() {
		$("#leftTree").empty();
		refreshTreeTag("leftTree",setting_leftTree,"");
		selectNodeTree();
	}
	function selectNodeTree(){
		var zTree1 = $.fn.zTree.getZTreeObj("leftTree");
		var id = $("#eCatalogTypeTreeId").val();
		var node = zTree1.getNodeByParam('id',id);// 获取id为-1的点
		zTree1.selectNode(node);
		zTree1.expandNode(node, true, false , true);
	}


</script>
</body>
</html>