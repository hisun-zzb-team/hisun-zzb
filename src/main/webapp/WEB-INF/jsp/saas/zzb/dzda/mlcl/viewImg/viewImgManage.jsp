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
	<script src="${path}/js/common/loading.js" type="text/javascript"></script>
</head>


<body onunload="testGuanbi()">

<script type="text/javascript">
		function testGuanbi(){
			alert("哈哈哈");
		}
</script>
<div class="container-fluid" >
	<!-- 脚本目录 -->
	<input id="showTpWidth" type="hidden"/>
	<div class="span6" style="width: 20%; margin: 0px;padding: 0px;background: #f1f3f6;border-right:1px solid #d8d8d8">
		<input type="hidden" name="ea38LogDetailId" id="ea38LogDetailId">
		<input type="hidden" name="eLogDetailViewTimeId" id="eLogDetailViewTimeId">
		<input type="hidden" name="a38LogViewTimeId" id="a38LogViewTimeId">
		<input type="hidden" name="a38LogId" id="a38LogId">
		<div class="portlet box grey" style="margin: 0px;padding: 0px;background: #f1f3f6">
				<div style="margin: 0px;padding: 0px">
					<Tree:tree id="viewImagesTree" treeUrl="${path}/zzb/dzda/mlcl/images/ajax/typeAndE01z1Tree/${a38Id}?eApplyE01Z8Id=${eApplyE01Z8Id}" token="${sessionScope.OWASP_CSRFTOKEN}"
							   onClick="viewImages" submitType="post" dataType="json" isSearch="false"/>
				</div>
		</div>
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
	var timer1;
	var timer2;
	$(function(){
		$("#myDirName").val("${myDirName}");
		changeTreeDivHeight();
		//当浏览器大小改变的时候,要重新计算
		$(window).resize(function(){
			changeTreeDivHeight();
		})

		if("${isAddLog}" != "false"){
			$.ajax({
				url: "${path}/zzb/dzda/cysq/ajax/liulanLog",
				type: "post",
				data: {
					"eApplyE01Z8Id":$("#eApplyE01Z8Id").val(),
					"a38Id":"${a38Id}",
					"isManage":"${isManage}"
				},
				dataType: "json",
				headers:{
					OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
				},
				success: function (json) {
					var  eApplyE01Z8Id = json.eApplyE01Z8Id;
					$("#eApplyE01Z8Id").val(eApplyE01Z8Id);
					$("#a38LogId").val(json.a38LogId);
					$("#a38LogViewTimeId").val(json.a38LogViewTimeId);
					//	$("#syReadTime").val(json.syReadTime);
					var time = json.syReadTime;
					if(time!=null && time!="" && time!=undefined){
						$("#daojishi").css('display','block');
						timer1 = setInterval(function () {
							if(time <=0){
								//阅档时间到期
								jieshuyuedang(1);
							}
							time = time-1;
							var minute = parseInt((time  /60));
							var seconds = parseInt(time % 60);
							$('#timespan').html( minute + "分钟" + seconds + "秒");
						}, 1000);
					}
				},
				error: function () {
					showTip("提示", "出错了请联系管理员", 1500);
				}
			});
			var time = 5000;
			//更新阅档日志时间
			timer2 = setInterval(function () {
				var a38LogId = $("#a38LogId").val();
				$.ajax({
					type: "POST",
					url:"${path}/zzb/dzda/cysq/ajax/updateViewTime",
					dataType: "json",
					data: {
						"a38LogId": a38LogId,
						"time":time/1000,
						"eApplyE01Z8Id" :$("#eApplyE01Z8Id").val()
					},
					headers:{
						OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
					},
					success: function (json) {
						if(json.code == 2){
							showTip("提示", "您的阅档权限已被收回或阅档时间已结束", 2000);
							$('#viewImgModal').modal('hide');
							$('#viewImgDiv').html("");
							$("#timespan").html("");
							location.reload();
						}
					},
					error: function () {
						//myLoading.hide();
						console.log("我这里出错了")
						showTip("提示", "出错了,请检查网络!", 2000);
					}
				});
			}, time);
		}



	});
	function  jieshuyuedang(){
		window.clearInterval(timer1);
		window.clearInterval(timer2);
		var a38LogId = $("#a38LogId").val();
		$.ajax({
			type: "POST",
			url:"${path}/zzb/dzda/cysq/ajax/guanbiOrjieshu",
			dataType: "json",
			data: {
				"a38LogId": a38LogId,
				"lseLogDetailViewTimeId":$("#eLogDetailViewTimeId").val(),
				"ea38LogDetailId":$("#ea38LogDetailId").val(),
				"a38LogViewTimeId":$("#a38LogViewTimeId").val(),
				"eApplyE01Z8Id" :$("#eApplyE01Z8Id").val(),
				"ydsjzt":"1"
			},
			headers:{
				OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
			},
			success: function (json) {
				if(json.code == 5){
					showTip("提示", "阅档时间已结束", 2000);
				}
			},
			error: function () {
				//myLoading.hide();
				showTip("提示", "出错了,请检查网络!", 2000);
			}
		});
		$('#viewImgModal').modal('hide');
		$('#viewImgDiv').html("");
		$("#timespan").html("");
		location.reload();
	}
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
		//var  eApplyE01Z8Id = $("#eApplyE01Z8Id").val();
		var a38LogId = $("#a38LogId").val();
		if(a38LogId != undefined && a38LogId != "" && a38LogId != null){
			//记录阅档日志
			if(treeNode.nodeType != "dir"){
				$.ajax({
					type: "POST",
					url:"${path}/zzb/dzda/cysq/ajax/detailViewTime",
					dataType: "json",
					data: {
						"a38LogId": a38LogId,
						"e01z1Id": treeNode.id,
						"e01z111":treeNode.name,
						"lseLogDetailViewTimeId":$("#eLogDetailViewTimeId").val(),
						"lsEa38LogDetailId":$("#ea38LogDetailId").val()
					},
					headers:{
						OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
					},
					success: function (json) {

						$("#eLogDetailViewTimeId").val(json.eLogDetailViewTimeId);
						$("#ea38LogDetailId").val(json.ea38LogDetailId);
					},
					error: function () {
						//myLoading.hide();
						showTip("提示", "出错了,请检查网络!", 2000);
					}
				});
			}
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
	function hiddenViewImgModalForLiulan(){//隐藏图片查看时 删除临时的解密图片
		clearInterval(timer1);
		clearInterval(timer2);
		$.ajax({
			type: "POST",
			url:"${path}/zzb/dzda/cysq/ajax/guanbiOrjieshu",
			dataType: "json",
			data: {
				"a38LogId": $("#a38LogId").val(),
				"lseLogDetailViewTimeId":$("#eLogDetailViewTimeId").val(),
				"ea38LogDetailId":$("#ea38LogDetailId").val(),
				"a38LogViewTimeId":$("#a38LogViewTimeId").val()
			},
			headers:{
				OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
			},
			success: function (json) {
			},
			error: function () {
				//myLoading.hide();
				showTip("提示", "出错了,请检查网络!", 2000);
			}
		});
		$('#viewImgModal').modal('hide');
		$('#viewImgDiv').html("");
		$("#timespan").html("");
		//window.location.href ="${path }/zzb/dzda/cysq/list";
	    location.reload();
	}


</script>
</body>
</html>
