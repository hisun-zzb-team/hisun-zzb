<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/inc/servlet.jsp" %>
<%@include file="/WEB-INF/jsp/inc/taglib.jsp" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->

<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->

<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->
<head>

	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	<!-- BEGIN PAGE LEVEL STYLES -->
	<link href="<%=path%>/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css"/>
	<link href="<%=path%>/css/mainleft.css" rel="stylesheet" type="text/css"/>
	<style type="text/css">
		.ztree li span.button.add {margin-left:2px; margin-right: -1px; background-position:-144px 0; vertical-align:top; *vertical-align:middle}

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
	<!-- END PAGE LEVEL STYLES -->
	<title>职数查询</title>
</head>
<body>
<div id="jgModal" class="modal container hide fade" tabindex="-1" data-width="500" >
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button data-dismiss="modal" class="close"  type="button"></button>
				<h3 class="modal-title" id="title" >
					添加机构
				</h3>
			</div>
			<div class="modal-body" id="jgAddDiv">
			</div>
		</div>
	</div>
</div>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="row-fluid">
			<!-- 脚本目录 -->
			<div id="scriptCatalog" class="span6" style="width: 20%; margin: 0px;padding: 0px">
				<div class="portlet box grey" style="margin: 0px;padding: 0px">
					<div class="portlet-body fuelux"style="margin: 0px;padding: 0px">
						<div class="tab-pane active" id="tab_1_1"style="margin: 0px;padding: 0px">
							<span id="errorInfo" style="color: red;position: absolute;"></span>
							<div class="zTreeDemoBackground" id="zTreeDiv" style=" background: #f1f3f6 !important; overflow: auto;margin: 0px;padding: 0px">
								<ul id="customTree" class="ztree"></ul>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div id="rMenu">
				<ul>
					<%--<li id="m_add" onclick="addTreeNode('1');">添加分类</li>--%>
					<%--<li id="mJG_add" onclick="addTreeNode('0');">添加机构</li>--%>
					<%--<li id="m_eddit" onclick="editTreeNode()">修改</li>--%>
					<%--<li id="m_del" onclick="deleteTreeNode();">删除</li>--%>
				</ul>
			</div>
			<input type="hidden" id="treeId" value="">
			<div class="main_right" id="catalogList" >
			</div>
		</div>
	</div>
	<script type="text/javascript" src="${path }/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="${path }/js/zTree/jquery.ztree.all-3.5.js"></script>
	<script type="text/javascript" src="${path}/js/jquery.json-2.3.min.js"></script>
	<script type="text/javascript" src="${path }/js/common/loading.js"></script>

	<script type="text/javascript" src="${path }/js/jquery.validate.min.js"></script>
	<script type="text/javascript" src="${path }/js/common/est-validate-init.js"></script>
	<script type="text/javascript" src="${path }/js/common/validate-message.js"></script>
	<script type="text/javascript" src="${path }/js/common/30CloudAjax.js"></script>
	<script type="text/javascript" src="${path }/js/common/DataValidate.js"></script>
	<script type="text/javascript" src="<%=path%>/js/bootstrap-datepicker.js"></script>
	<script type="text/javascript" src="<%=path%>/js/bootstrap-datepicker.zh-CN.js"></script>

	<%--<script src="${path}/js/bootstrap.min.js" type="text/javascript"></script>--%>
	<script type="text/javascript">
		$(function(){
			changeTreeDivHeight();
			//当浏览器大小改变的时候,要重新计算
			$(window).resize(function(){
				changeTreeDivHeight();
			})
		});

		function changeTreeDivHeight(){
			var divHeight = $(window).height()-60;
			$("#zTreeDiv").css('height',divHeight);
//			$('#zTreeDiv').height(divHeight);
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
				url:"${path}/zzb/app/console/bset/ajax/load/tree",
				dataType : "json",
				headers:{
					"OWASP_CSRFTOKEN":'${sessionScope.OWASP_CSRFTOKEN}'
				},
				data:{},
				success:function(json){
					$.fn.zTree.init($("#customTree"), setting, json.customTree);
					zTree = $.fn.zTree.getZTreeObj("customTree");
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
						$("#tab_1_1").addClass("active");
						node = zTree.getNodeByParam("id",treeId, null);
						parentNode = node.getParentNode();
						zTree.expandNode(parentNode,true,false,true,true);

						if (parentNode) {
							firstNode = parentNode;
						}
					}
					if(firstNode.getParentNode()==null ||firstNode.getParentNode()==undefined){
						loadRightPage("allZs");
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
				url : "${path}/zzb/app/console/appGbcxB01/ajax/addOrEdit",
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
				url : "${path}/zzb/app/console/appGbcxB01/ajax/addOrEdit",
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

		var deleteTreeNode = function(){
			var node = zTree.getSelectedNodes()[0];
			if(!node){
				node = valiZTree.getSelectedNodes()[0];
			}

			if (node.children && node.children.length > 0) {
				showTip("提示","要删除的节点存在子节点，请先删除子节点", 1500);
				return false;
			}
			var id = node.id;
			var itemName = node.name+"\",删除该机构将删除机构下的干部，\"请确认继续删除";

			actionByConfirm1(itemName, "${path}/zzb/app/console/appGbcxB01/delete/" + id,{} ,function(data,status){
				if (data.success == true) {
					showTip("提示","删除成功", 2000);
					setTimeout(function(){window.location.href = "${path}/zzb/app/console/gbcx/"},2000);
				}else{
					showTip("提示", data.message, 2000);
				}
			});
		}

		function loadRightPage(nodeId) {
			$.ajax({
				async:false,
				type:"POST",
				url:"${path}/zzb/app/console/appZscxZs/ajax/list",
				dataType : "html",
				headers:{
					"OWASP_CSRFTOKEN":'${sessionScope.OWASP_CSRFTOKEN}'
				},
				data:{
					'b01Id':nodeId
				},
				success:function(html){
					$("#catalogList").html(html);
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
			if(treeNode==null || dataType != "fl") {
				loadRightPage(treeNode.id);
			}
//			else if(treeNode.getParentNode()==null ||treeNode.getParentNode()==undefined){
//				loadRightPage("allZs");
//			}
		}

		function pagehref(pageNum ,pageSize){
			$.ajax({
				type:"POST",
				url:"${path}/monitor/inst/ajax/listByCatalog",
				dataType : "html",
				headers:{
					"OWASP_CSRFTOKEN":'${sessionScope.OWASP_CSRFTOKEN}'
				},
				data:{
					'catalogId':$("#catalogId").val(),
					'catalogType':$("#treeId").val(),
					'pageNum':pageNum,
					'pageSize':pageSize
				},
				success:function(html){
					$("#catalogList").html(html);
				},
				error : function(){
					myLoading.hide();
					showTip("提示","出错了,请检查网络!",2000);
				}
			})
		}

		// 拖拽到前一个位置
		function dropPrev(treeId, nodes, targetNode) {
			var pNode = targetNode.getParentNode();
			if ((pNode && pNode.dropInner === false) || targetNode.id === "1") {
				return false;
			} else {
				for (var i=0,l=curDragNodes.length; i<l; i++) {
					var curPNode = curDragNodes[i].getParentNode();
					if (!targetNode.getParentNode() && curDragNodes[i].dropRoot === false) {
						return false;
					} else if (curPNode && curPNode !== targetNode.getParentNode() && curPNode.childOuter === false) {
						return false;
					}
				}
			}
			return true;
		}
		// 拖拽到里面
		function dropInner(treeId, nodes, targetNode) {
			if(!targetNode){
				return false;
			}
			if ((targetNode && targetNode.dropInner === false) || targetNode.id === "1") {
				return false;
			} else {
				for (var i=0,l=curDragNodes.length; i<l; i++) {
					if (!targetNode && curDragNodes[i].dropRoot === false) {
						return false;
					} else if (curDragNodes[i].parentTId && curDragNodes[i].getParentNode() !== targetNode && curDragNodes[i].getParentNode().childOuter === false) {
						return false;
					}
				}
			}
			return true;
		}
		// 拖拽到后一个位置
		function dropNext(treeId, nodes, targetNode) {
			var pNode = targetNode.getParentNode();
			if ((pNode && pNode.dropInner === false) || targetNode.id === "1") {
				return false;
			} else {
				for (var i=0,l=curDragNodes.length; i<l; i++) {
					var curPNode = curDragNodes[i].getParentNode();
					if (!targetNode.getParentNode() && curDragNodes[i].dropRoot === false) {
						return false;
					} else if (curPNode && curPNode !== targetNode.getParentNode() && curPNode.childOuter === false) {
						return false;
					}
				}
			}
			return true;
		}

		var curDragNodes, autoExpandNode;

		// 拖拽前
		function beforeDrag(treeId, treeNodes) {
			for (var i=0,l=treeNodes.length; i<l; i++) {
				if (treeNodes[i].drag === false) {
					curDragNodes = null;
					return false;
				} else if (treeNodes[i].parentTId && treeNodes[i].getParentNode().childDrag === false) {
					curDragNodes = null;
					return false;
				}
			}
			curDragNodes = treeNodes;
			return true;
		}
		// 拖拽后
		function onDrag(event, treeId, treeNodes) {
		}

		// 拖拽放下前
		function beforeDrop(treeId, treeNodes, targetNode, moveType){
			<%--if(!targetNode){--%>
			<%--return false;--%>
			<%--}--%>
			<%--if(targetNode.id === "-1" && treeNodes[0].isParent){--%>
			<%--return false;--%>
			<%--}--%>
			<%--var pId;--%>
			<%--if(!targetNode){--%>
			<%--pId = null;--%>
			<%--} else if (moveType === "inner"){--%>
			<%--pId = targetNode.id;--%>
			<%--} else if(moveType ==="prev" || moveType ==="next"){--%>
			<%--pId = targetNode.pId;--%>
			<%--}--%>
			<%--alert(pId)--%>
			<%--$.ajax({--%>
			<%--type:"POST",--%>
			<%--url:"${path}/monitor/customCatalog/update",--%>
			<%--dataType : "json",--%>
			<%--headers:{--%>
			<%--"OWASP_CSRFTOKEN":'${sessionScope.OWASP_CSRFTOKEN}'--%>
			<%--},--%>
			<%--data:{--%>
			<%--'json':$.toJSON(treeNodes),--%>
			<%--'pId':pId--%>
			<%--},--%>
			<%--//contentType: "application/json; charset=utf-8",--%>
			<%--success:function(data){--%>
			<%--if(data.code!=-1){--%>
			<%--return data.success;--%>
			<%--}else{--%>
			<%--showTip("提示","系统繁忙，请稍后再试！",2000);--%>
			<%--}--%>
			<%--},--%>
			<%--error : function(){--%>
			<%--myLoading.hide();--%>
			<%--showTip("提示","出错了,请检查网络!",2000);--%>
			<%--}--%>
			<%--})--%>

		}

		// 拖拽放下后
		function onDrop(event, treeId, treeNodes, targetNode, moveType, isCopy) {
			if(!targetNode){
				return;
			}
			if(targetNode.id==="-1"){
				var childrens = targetNode.children;
				if(childrens){
					for(var i=0; i<childrens.length; i++){
						childrens[i].noRemoveBtn = true;
						zTree.updateNode(childrens[i]);
						zTree.cancelSelectedNode(childrens[i]);
					}
				}
			} else {
				var childrens = targetNode.children;
				if(childrens){
					for(var i=0; i<childrens.length; i++){
						childrens[i].noRemoveBtn = false;
						zTree.updateNode(childrens[i]);
						zTree.cancelSelectedNode(childrens[i]);
					}
				}
			}
		}

		// 右击
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
//		$(".ztree").find(".nogray_ico_docu").css("background-size","16px 16px")
//		$(".ztree").find(".ico_docu").css("background-size","16px 16px").css("-webkit-filter","grayscale(100%)")
//				.css("-moz-filter","grayscale(100%)").css("-ms-filter","grayscale(100%)").css("-o-filter","grayscale(100%)")
//				.css("filter","grayscale(100%)").css("filter","gray").css("-webkit-filter","grayscale(1)");
		}

		function showRMenu(type, x, y, haveStart) {
			$("#rMenu ul").show();
//		if (type=="root") {
//			$("#m_del").hide();
//			$("#m_eddit").hide();
//			$("#m_add").show();
//			$("#mJG_add").show();
//		} else{
//			$("#mJG_add").show();
//			$("#m_del").show();
//			$("#m_eddit").show();
//			$("#m_add").show();
//
//		}
			rMenu.css({"top":y+"px", "left":x+"px", "visibility":"visible"});

			$("body").bind("mousedown", onBodyMouseDown);
		}

		function hideRMenu() {
			if (rMenu) rMenu.css({"visibility": "hidden"});
			$("body").unbind("mousedown", onBodyMouseDown);
		}

		function onBodyMouseDown(event){
			if (!(event.target.id == "rMenu" || $(event.target).parents("#rMenu").length>0)) {
				rMenu.css({"visibility" : "hidden"});
			}
		}



		function beforeRemove(treeId, treeNode) {
			zTree.selectNode(treeNode);
			if (treeNode.children && treeNode.children.length > 0) {
				var msg = "要删除的节点是父节点，如果删除将连同子节点一起删掉。\n\n请确认！";
				if(!confirm(msg)){
					return false;
				}
			}
			var nodes = zTree.transformToArray(treeNode);
			var updateNodes = new Array();
			var ids = "";
			for(var i=0; i<nodes.length; i++){
				if(nodes[i].isParent){
					ids += nodes[i].id + ",";
				} else {
					updateNodes.push(nodes[i]);
				}
			}
			if(ids && ids.length > 0){
				ids = ids.substring(0, ids.length - 1);
			}
			if(confirm("确定要删除\"" + treeNode.name + "\"吗?")){
				$.ajax({
					type:"POST",
					url:"${path}/monitor/customCatalog/delete",
					dataType : "json",
					headers:{
						"OWASP_CSRFTOKEN":'${sessionScope.OWASP_CSRFTOKEN}'
					},
					data:{
						'json':$.toJSON(updateNodes),
						'ids':ids
					},
					success:function(data){
						if(data.code==-1){
							showTip("提示","系统繁忙，请稍后再试！",2000);
						}
						return data.success;
					},
					error : function(){
						myLoading.hide();
						showTip("提示","出错了,请检查网络!",2000);
					}
				})
			} else {
				return false;
			}
		}

		function beforeRename(treeId, treeNode, newName, isCancel) {
			if(newName.replace(/^\s*$/g,'') === treeNode.name){
				$("#errorInfo").html("");
			} else {
				if(newName.length > 10){
					$("#errorInfo").html("分组名长度不能超过10，请重新输入");
					return false;
				} else if(zTree.getNodeByParam("name", newName, null)){
					$("#errorInfo").html("分组名已经存在，请重新输入");
					return false;
				}

				$("#errorInfo").html("");
			}
			$.ajax({
				type:"POST",
				url:"${path}/monitor/customCatalog/updateName",
				dataType : "json",
				headers:{
					"OWASP_CSRFTOKEN":'${sessionScope.OWASP_CSRFTOKEN}'
				},
				data:{
					'id':treeNode.id,
					'name':newName
				},
				//contentType: "application/json; charset=utf-8",
				success:function(data){
					if(!data.success){
						$("#errorInfo").html("重命名失败，请稍后再试");
					}
					return data.success;
				},
				error : function(){
					myLoading.hide();
					showTip("提示","出错了,请检查网络!",2000);
				}
			})
		}

		function onRemove(event, treeId, treeNode) {
//		var childrens = treeNode.children;
//		var parentNode = zTree.getNodesByParam("id","-1", null);
//		if(!parentNode){
//			parentNode = zTree.getNodesByParam("name","未分组", null);
//		}

		}



		function showRenameBtn(treeId, treeNode) {
			if(treeId === "validTree"){
				return false;
			}
			if(treeNode.id === "1" || treeNode.id === "-1"){
				return false;
			}
			if(!treeNode.isParent){
				return false;
			}
			if(treeNode.pId == "-1"){
				return false;
			}
			return !treeNode.noEditBtn;
		}
		function showRemoveBtn(treeId, treeNode) {
			if(treeId === "validTree"){
				return false;
			}
			if(treeNode.id === "1" || treeNode.id === "-1"){
				return false;
			}
			if(treeNode.pId == "-1"){
				return false;
			}
			return !treeNode.noRemoveBtn;
		}

		// 右击菜单功能
		function onRightClickFun(type){
			var node = zTree.getSelectedNodes()[0];
			if(!node){
				node = valiZTree.getSelectedNodes()[0];
			}
			if(node.isParent){
				return;
			}
			var _id = node.id;

//		toEdit(_id, _service);

		}
	</script>
</body>
</html>
