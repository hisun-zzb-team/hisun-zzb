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
	<link href="<%=path%>/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css"/>
	<!-- END PAGE LEVEL STYLES -->
	<title>目录信息</title>
</head>
<style type="text/css">
	.ztree li span.button.add {margin-left:2px; margin-right: -1px; background-position:-144px 0; vertical-align:top; *vertical-align:middle}


	ul.ztree{margin-bottom: 10px; background: #f1f3f6 !important;}
	.portlet.box.grey.mainleft{background-color: #f1f3f6;overflow: hidden; padding: 0px !important; margin-bottom: 0px;}
	.main_left{float:left; width:220px;  margin-right:10px; background-color: #f1f3f6; }
	.main_leftBlock{float:left; width:220px;  margin-right:10px; background-color: #f1f3f6; }
	.main_right{display: table-cell; width:2000px; padding:20px 20px; }
	.portlet-title .caption.mainlefttop{ border: none !important; background-color:#eaedf1;width: 220px; height: 48px;line-height: 48px;padding: 0;margin: 0;text-indent: 1em; }
	.portlet.box .portlet-body.leftbody{padding: 15px 8px;}
</style>
<style type="text/css">
	#rMenu {position:absolute; visibility:hidden; top:0; background-color: #111;text-align: left;
		padding:0px ; opacity:0.8; border-radius:3px; text-align: center; border:solid 1px #000;font-size: 13px;border-bottom:none;
	}
	#rMenu ul{margin: 0;}
	#rMenu ul li{
		padding: 3px 12px ;
		cursor: pointer;
		list-style: none outside none;
		color: #fff;
		border-bottom:solid 1px #1b1b1b;

	}
	#rMenu ul li:hover{color: #f30; background-color: #000;}
	.imgGray{
		-webkit-filter:grayscale(100%);
		-moz-filter:grayscale(100%);
		-ms-filter:grayscale(100%);
		-o-filter:grayscale(100%);
		filter:grayscale(100%);
		filter:gray;
		-webkit-filter:grayscale(1);
	}

	a.Btndownload{background-color:inherit; margin:0; padding:0;}
	a.Btndownload:hover{ background-color:inherit !important;}
	a.Btndownload:hover, a.Btndownload:focus, a.Btndownload:active{ background-color:inherit !important; color:#06c;}
	.btn-group > .btn{ font-size: 13px;}
	.dropdown-menu label{ font-size:12px;}
	.portlet-body .btn-group {
		margin: 0px !important;
	}
	.dropdown-menu label{ font-size:12px;}
	.dropdown-checkboxes{ padding:0px;}
	.agentdownload { padding:5px 0;}
	.agentdownload li a{ margin:0; font-size:12px; text-align:left; color:#333333;}
</style>
<body >
<div class="container-fluid" >

			<!-- 脚本目录 -->
			<div class="span6" style="width: 20%; margin: 0px;padding: 0px;">
				<div class="portlet box grey" style="margin: 0px;padding: 0px">
					<div class="portlet-body fuelux"style="margin: 0px;padding: 0px">
						<div class="tab-pane active" id="tab_2_1"style="margin: 0px;padding: 0px">
							<span id="errorInfo" style="color: red;position: absolute;"></span>
							<div class="zTreeDemoBackground" id="zTreeViewDiv" style=" background: #f1f3f6 !important; overflow: auto;margin: 0px;padding: 0px;">
								<ul id="customViewTree" class="ztree"></ul>
							</div>
						</div>
					</div>
				</div>
			</div>

		<div id="rMenu">

		</div>

		<div>
			<div style="margin-bottom: 5px;text-align: right">
				<input type="hidden" name="id"  value="${id }"/>
				<a href="javascript:void(0)" class="btn green" onclick="">打印</a>
				<button type="button" class="btn btn-default" data-dismiss="modal"><i class='icon-remove-sign'></i> 关闭</button>
			</div>
			<div id="viewList" >
		</div>
</div>
	<%--<script src="${path}/js/bootstrap.min.js" type="text/javascript"></script>--%>
	<script type="text/javascript" src="${path }/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="${path }/js/zTree/jquery.ztree.all-3.5.js"></script>
	<script type="text/javascript" src="${path}/js/jquery.json-2.3.min.js"></script>
	<script type="text/javascript" src="${path }/js/common/loading.js"></script>
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
			$("#zTreeViewDiv").css('height',divHeight);
//			$("#viewList").css('height',listHeight);
//			$(".main_left").height(mainHeight);

		}
	var zTree, rMenu, valiZTree;
	$(document).ready(function(){

		App.init();//必须，不然导航栏及其菜单无法折叠

		var setting = {
//			edit: {
//				enable: true,
//				editNameSelectAll: true,
//				showRemoveBtn: showRemoveBtn,
//				showRenameBtn: showRenameBtn,
//				drag: {
//					autoExpandTrigger: true,
//					prev: dropPrev,
//					inner: dropInner,
//					next: dropNext
//				},
//				removeTitle:"移出/删除该分组",
//				renameTitle:"重命名"
//			},
			data: {
				keep:{
					leaf: true,
					parent: true
				},
				simpleData: {
					enable: true
				}
			},
			callback: {
//				beforeDrag: beforeDrag,
//				onDrag: onDrag,
//				onDrop: onDrop,
				onRightClick: OnRightClick,
//				beforeRemove: beforeRemove,
//				onRemove: onRemove,
//				beforeRename: beforeRename,
//				beforeDrop: beforeDrop,
				onClick: zTreeOnClick,
				onExpand: onExpand
			}
		};
		var catalogType = '${catalogType}';
		var treeId = '${treeId}';

		$.ajax({
			async:false,
			type:"POST",
			url:"${path}/zzb/app/console/daDemo/ajax/load/tree",
			dataType : "json",
			headers:{
				"OWASP_CSRFTOKEN":'${sessionScope.OWASP_CSRFTOKEN}'
			},
			data:{loadType:"view"},
			success:function(json){
				$.fn.zTree.init($("#customViewTree"), setting, json.customTree);
				zTree = $.fn.zTree.getZTreeObj("customViewTree");
//				$.fn.zTree.init($("#validTree"), setting, json.validTree);
//				valiZTree = $.fn.zTree.getZTreeObj("validTree");
				zTree.expandNode(zTree.getNodes()[0],true,false,false,true);
//				valiZTree.expandNode(valiZTree.getNodes()[0],true,false,false,true);
				rMenu = $("#rMenu");
				var firstNode = zTree.getNodes()[0];
				zTree.selectNode(firstNode);

				if (catalogType && treeId) {
					var node;
					var parentNode;
						$("#custom-catalog").addClass("active");
						$("#tab_2_1").addClass("active");
						node = zTree.getNodeByParam("id",treeId, null);
						parentNode = node.getParentNode();
						zTree.expandNode(parentNode,true,false,true,true);

					if (parentNode) {
						firstNode = parentNode;
					}
				}
				if(firstNode==null ||firstNode==undefined){
					loadRightPage("");
				}else {
					loadRightPage(firstNode.id);
				}
			},
			error : function(){
				myLoading.hide();
				showTip("提示","出错了,请检查网络!",2000);
			}
		});

		$("#catalog-nav").find("li").click(function(){
				valiZTree.cancelSelectedNode();
		});

	});

	var myLoading = new MyLoading("${path}",{zindex:20000});
	var addTreeNode = function(dataType){
		var node = zTree.getSelectedNodes()[0];
		if(!node){
			node = valiZTree.getSelectedNodes()[0];
		}

//		if(node.isParent){
//			return;
//		}
		var _id = node.id;
		$.ajax({
			url : "${path}/zzb/app/console/bset/fl/ajax/addOrEdit",
			type : "post",
			data: {"parentId":_id,"dataType":dataType},
			headers:{
				OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
			},
			dataType : "html",
			success : function(html){
				if(dataType=="1") {
					$('#title').text('添加分类');
				}else{
					$('#title').text('添加机构');
				}
				$('#jgAddDiv').html(html);
				$('#jgModal').modal({
					keyboard: true
				});
			},
			error : function(){
				showTip("提示","出错了请联系管理员", 1500);
			}
		});
	}
	var editTreeNode = function(){
		var node = zTree.getSelectedNodes()[0];
		if(!node){
			node = valiZTree.getSelectedNodes()[0];
		}

//		if(node.isParent){
//			return;
//		}
		var _id = node.id;
		$.ajax({
			url : "${path}/zzb/app/console/bset/fl/ajax/addOrEdit",
			type : "post",
			data: {"id":_id},
			headers:{
				OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
			},
			dataType : "html",
			success : function(html){
				$('#title').text('修改信息');
				$('#jgAddDiv').html(html);
				$('#jgModal').modal({
					keyboard: true
				});
			},
			error : function(){
				showTip("提示","出错了请联系管理员", 1500);
			}
		});
	}

		function OnRightClick(event, treeId, treeNode) {
			if (!treeNode && event.target.tagName.toLowerCase() != "button" && $(event.target).parents("a").length == 0) {
				zTree.cancelSelectedNode();
				showRMenu("root", event.clientX, event.clientY);
			} else if (treeNode && !treeNode.noR) {
				zTree.selectNode(treeNode);
				if(treeNode.isParent){
					if(treeNode.id != "-1"){
						if(treeNode.id === "1"){
							showRMenu("root", event.clientX, event.clientY, treeNode.haveStart);
						} else {
							showRMenu("parent", event.clientX, event.clientY, treeNode.haveStart);
						}
					}
				} else {
					showRMenu("children", event.clientX, event.clientY, treeNode.haveStart);
				}
			} else if (treeNode && treeNode.noR) {
				//右击根节点触发事件
				zTree.selectNode(treeNode);
				if(treeNode.isParent){
					if(treeNode.id != "-1"){
						if(treeNode.id === "1"){
							showRMenu("root", event.clientX, event.clientY, treeNode.haveStart);
						}
					}
				}
			}
		}
		function onExpand(event, treeId, treeNode){

		}

	function loadRightPage(nodeId) {
		$.ajax({
			async:false,
			type:"POST",
			url:"${path}/zzb/app/console/daDemo/ajax/viewImg",
			dataType : "html",
			headers:{
				"OWASP_CSRFTOKEN":'${sessionScope.OWASP_CSRFTOKEN}'
			},
			data:{
				'queryId':nodeId
			},
			success:function(html){
				$("#viewList").html(html);
				$("#treeId").val(nodeId);
			},
			error : function(){
				myLoading.hide();
				showTip("提示","出错了,请检查网络!",2000);
			}
		});
	}

	// 点击事件
	function zTreeOnClick(event, treeId, treeNode){
		var dataType = "0";
		var treeObj = zTree;

		var selectNodes = treeObj.getSelectedNodes();
		if(selectNodes.length > 1){
			return;
		}

		dataType = treeNode.dataType;
		if(treeNode==null || dataType != "1") {
			loadRightPage(treeNode.id);
		}
	}



</script>
</body>
</html>
