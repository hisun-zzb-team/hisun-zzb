<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/inc/taglib.jsp" %>
<%--
  ~ Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
  ~ http://www.hn-hisun.com
  ~ 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
  --%>

<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	<!-- END PAGE LEVEL STYLES -->
	<title></title>
	<style type="text/css">
		form {
			margin: 0 0 0px;
		}
	</style>
</head>
<body>
<div id="jgModal" class="modal container hide fade" tabindex="-1" data-width="1010" data-height="600">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button data-dismiss="modal" class="close"  type="button"></button>
				<h3 class="modal-title" id="title" >
					“红叶专”档案图片
				</h3>
			</div>
			<div class="modal-body" id="jgAddDiv" style="background-color: #f1f3f6;">
			</div>
		</div>
	</div>
</div>
<div id="uploadImgModal" class="modal container hide fade" tabindex="-1" data-width="700">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button data-dismiss="modal" class="close"  type="button"></button>
				<h3 class="modal-title"  >
					上传图片
				</h3>
			</div>
			<div class="modal-body" id="uploadImgDiv">
			</div>
		</div>
	</div>
</div>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12 responsive">
			<%-- 表格开始 --%>
			<form class=""id="importForm" enctype="multipart/form-data">
				<input type="hidden" name="queryId" value="${queryId}"/>
				<input type="hidden" name="a38Id" id="a38Id" value="${a38Id}"/>
				<input type="hidden" id="currentNodeId" name="currentNodeId" value="${currentNodeId}"/>
				<input type="hidden" id="currentNodeName" name="currentNodeName" value="${currentNodeName}"/>
				<div class="portlet-title">
					<div class="caption">简历材料  共<font color="red"> 1 </font>条记录 </div>
					<div class="clearfix fr">
						<a id="sample_editable_1_new" class="btn green" href="javascript:add()">
							<i class="icon-plus"></i> 增加材料
						</a>
						<div class="btn-group">
							<a class="btn green dropdown-toggle" data-toggle="dropdown" href="#">
								图片处理 <i class="icon-angle-down"></i>
							</a>
							<ul class="dropdown-menu">
								<li >
									<a onclick="uploadImg()">加载图片</a>
								</li>
								<li>
									<a onclick="">卸载图片</a>
								</li>
								<li>
									<a onclick="fileDown('dangantupianxiazai')">下载图片</a>
								</li>
							</ul>
						</div>
						<div class="btn-group">
							<a class="btn green dropdown-toggle" data-toggle="dropdown" href="#">
								导入目录 <i class="icon-angle-down"></i>
							</a>
							<ul class="dropdown-menu" >
								<li >
									<a onclick="fileDown('xiazaimludaorumoban')">下载目录导入模板</a>
								</li>
								<li>
									<a onclick="unloadFile()">导入目录</a>
									<input type="file" style="display: none" name="unloadFile" id="btn-unloadFile"/>
								</li>

							</ul>
						</div>

						<a class="controllerClass btn green file_but"  href="javascript:fileDown('daochumilu')">
							<i class="icon-circle-arrow-down" ></i>打印目录
						</a>
					</div>

				</div>
			</form>
				<div class="portlet-body">
					<table class="table table-striped table-bordered table-hover dataTable table-set">
						<thead>
						<tr>
							<th width="30">序号</th>
							<th>材料名称</th>
							<th width="60">制成时间</th>
							<th width="60">材料页数</th>
							<th width="60">图片数</th>
							<th width="60">加载图片</th>
							<th width="40">浏览</th>
							<th width="60">扫描排序</th>
							<th width="90">操作</th>
						</thead>
						<tbody>
							<tr style="text-overflow:ellipsis;">
								<td>1</td>
								<td><a href="javascript:edit()" class="">干部履历表</a></td>
								<td>2000.11.02 </td>
								<td>13</td>
								<td>13</td>
								<td><a href="javascript:uploadImg()" class="">加载</a></td>
								<td><a href="javascript:view()" class="">浏览</a></td>
								<td st>1</td>
								<td>
									<a href="javascript:edit()" class="">修改</a>|
									<a href="#" class="">删除</a>
								</td>
							</tr>
						</tbody>
					</table>
					<jsp:include page="/WEB-INF/jsp/common/page.jsp">
						<jsp:param value="${pager.total }" name="total"/>
						<jsp:param value="${pager.pageCount }" name="endPage"/>
						<jsp:param value="${pager.pageSize }" name="pageSize"/>
						<jsp:param value="${pager.pageNum }" name="page"/>
					</jsp:include>
				</div>
		</div>
		<%-- 表格结束 --%>
	</div>
</div>

<%-- END PAGE CONTENT--%>
</div>

<script type="text/javascript">
	function unloadFile(){
		document.getElementById("btn-unloadFile").click();
	}
	function add() {
		var a38Id = $("#a38Id").val();
		var currentNodeId = $("#currentNodeId").val();
		var currentNodeName = $("#currentNodeName").val();
		alert("currentNodeId:"+currentNodeId);
		alert("currentNodeName:"+currentNodeName);
		$.ajax({
			async:false,
			type:"POST",
			url:"${path}/zzb/dzda/e01z1/ajax/addMlcl",
			dataType : "html",
			headers:{
				"OWASP_CSRFTOKEN":'${sessionScope.OWASP_CSRFTOKEN}'
			},
			data:{
				"a38Id":a38Id,
				"currentNodeId":currentNodeId,
				"currentNodeName":currentNodeName
			},
			success:function(html){
				$("#catalogList").html(html);
//				$("#treeId").val(nodeId);
			},
			error : function(){
				myLoading.hide();
				showTip("提示","出错了,请检查网络!",2000);
			}
		});
	}
	var uploadImg = function(){
		$.ajax({
			url:"${path}/zzb/app/console/daDemo/ajax/uploadImg",
			type : "post",
			data: {},
			headers:{
				OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
			},
			dataType : "html",
			success : function(html){
				$('#uploadImgDiv').html(html);

				$('#uploadImgModal').modal({
					keyboard: true
				});
			},
			error : function(){
				showTip("提示","出错了请联系管理员", 1500);
			}
		});
	}
	function edit() {
		$.ajax({
			async:false,
			type:"POST",
			url:"${path}/zzb/app/console/daDemo/ajax/editMlcl",
			dataType : "html",
			headers:{
				"OWASP_CSRFTOKEN":'${sessionScope.OWASP_CSRFTOKEN}'
			},
			data:{

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
	var view = function(){
		var divHeight = $(window).height()-100;
		$('#jgModal').attr("data-height",divHeight);
		$.ajax({
			url:"${path}/zzb/app/console/daDemo/ajax/viewImgManage",
			type : "post",
			data: {},
			headers:{
				OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
			},
			dataType : "html",
			success : function(html){
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
	function fileDown(type) {
		window.open("${path }/zzb/app/console/daDemo/ajax/down?type="+type);
	}
</script>
</body>
</html>
