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
	<title>分类管理</title>
	<style type="text/css">
		.ztree li span.button.add {margin-left:2px; margin-right: -1px; background-position:-144px 0; vertical-align:top; *vertical-align:middle}
		.page-content{   padding: 0 !important; }
		ul.ztree{margin-bottom: 10px; background: #f1f3f6 !important;}
		.portlet.box.grey.mainleft{background-color: #f1f3f6;overflow: hidden; padding: 0px !important; margin-bottom: 0px;}
		.main_left{float:left; width:220px;  margin-right:10px; background-color: #f1f3f6; }
		.main_right{display: table-cell; width:2000px; padding:20px 20px; }
		.portlet-title .caption.mainlefttop{ border: none !important; background-color:#eaedf1;width: 220px; height: 48px;line-height: 48px;padding: 0;margin: 0;text-indent: 1em; }
		.portlet.box .portlet-body.leftbody{padding: 15px 8px;}
	</style>
</head>
<body>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="main_left">
			<div class="portlet box grey mainleft">
				<div class="portlet-body leftbody">
					<input type="hidden" id="bflId"  name="bflId" value="" />
					<input type="hidden" id="fl"  name="fl" value="" />
					<input type="hidden" id="parentBFlId"  name="parentB01Id" value="" />
					<input type="hidden" id="key"  name="key" value="" />
					<Tree:tree id="leftBFlTree" treeUrl="${path}/zzb/jggl/fl/tree" token="${sessionScope.OWASP_CSRFTOKEN}"
							   onClick="onClickByTree" submitType="post" dataType="json" isSearch="false"/>
				</div>
			</div>
		</div>
		<div class="main_right" id="rightList" ></div>
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
		var divHeight = $(window).height()-80;
		$("#leftBFlTree_div").css('height',divHeight);
	}

	function onClickByTree (event, treeId, treeNode){
		$("#bflId").val(treeNode.id);//赋值
		$("#fl").val(treeNode.name);//赋值
		$("#parentBFlId").val(treeNode.pId);//赋值
		$("#key").val(treeNode.key);//赋值
		$.ajax({
			url: "${path}/zzb/jggl/fl/ajax/list",
			type : "get",
			data : null,
			dataType : "html",
			headers: {
				"OWASP_CSRFTOKEN":"${sessionScope.OWASP_CSRFTOKEN}"
			},
			data:{
				"bflId":treeNode.id,
				"parentBFlId":treeNode.pId,
				"fl":treeNode.name,
				"key":treeNode.key
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
		var zTree = $.fn.zTree.getZTreeObj("leftBFlTree");//取得树对象
		var node = zTree.getNodes()[0];// 获取第一个点
		var bflId ;
		var parentBFlId ;
		var fl ;
		var key ;
		if(node!=null){
			$("#bflId").val(node.id);//赋值
			$("#fl").val(node.name);//赋值
			$("#parentBFlId").val(node.pId);//赋值
			bflId =node.id;
			fl =node.name;
			parentBFlId =node.pId;
			key =node.key;
		}

		$.ajax({
			cache:false,
			type: 'POST',
			dataType : "html",
			data:{
				"bflId":bflId,
				"parentBFlId":parentBFlId,
				"fl":fl,
				"key":key
			},
			headers: {
				"OWASP_CSRFTOKEN":"${sessionScope.OWASP_CSRFTOKEN}"
			},
				url: "${path}/zzb/jggl/fl/ajax/list",// 请求的action路径
			error: function () {// 请求失败处理函数
				alert('请求失败');
			},
			success:function(html){
				$("#rightList").html(html);
			}
		});
		zTree.selectNode(node);//默认选中
		zTree.expandNode(node, true, false , true);//展开
	});

	function refreshTree() {
		$("#leftB01Tree").empty();
		refreshTreeTag("leftB01Tree",setting_leftB01Tree,"");
		selectNodeTree();
	}
	function selectNodeTree(){
		var zTree1 = $.fn.zTree.getZTreeObj("leftB01Tree");
		var id = $("#cuaB01Id").val();
		var node = zTree1.getNodeByParam('id',id);// 获取id为-1的点
		zTree1.selectNode(node);
		zTree1.expandNode(node, true, false , true);
	}


</script>
</body>
</html>