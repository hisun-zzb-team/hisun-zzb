<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/inc/taglib.jsp" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	<link href="${path}/css/common/common.css" rel="stylesheet" type="text/css"/>
	<!-- BEGIN PAGE LEVEL STYLES -->
	<link rel="stylesheet" href="${path }/css/DT_bootstrap.css" />
	<link rel="stylesheet" type="text/css" href="${path }/css/bootstrap-fileupload.css">

	<link href="${path }/css/style.css" rel="stylesheet" type="text/css">
	<!-- END PAGE LEVEL STYLES -->
	<title>职务管理</title>
	<style type="text/css">
		form {
			margin: 0 0 0px;
		}
	</style>
</head>
<body>
<div id="impPModal" class="modal container hide fade" tabindex="-1" data-width="500" >
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button data-dismiss="modal" class="close"  type="button"></button>
				<h3 class="modal-title" id="title" >
					导入参数配置
				</h3>
			</div>
			<div class="modal-body" id="impPAddDiv">
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
				<div class="portlet-title">
					<div class="clearfix fr">
						<a id="sample_editable_1_new" class="btn green" href="#">
							<i class="icon-plus"></i> 添加
						</a>

					</div>

				</div>
			</form>

				<div class="portlet-body">
					<table class="table table-striped table-bordered table-hover dataTable table-set">
						<thead>
						<tr>
							<th>单位职务名称</th>
							<th>职务级别</th>
							<th>职数</th>
							<th>职务管理层次</th>
							<th>排序</th>
							<th width="12%">操作</th>

						</tr>
						</thead>
						<tbody>
							<tr style="text-overflow:ellipsis;">
								<td><a href="#">书记</a></td>
								<td>正厅</td>
								<td>1</td>
								<td>市管职务</td>
								<td>1</td>
								<td>
									<a href="#" class="">编辑</a>|
									<a href="#" class="">删除</a>
								</td>
							</tr>
							<tr style="text-overflow:ellipsis;">
								<td><a href="#">副书记</a></td>
								<td>副厅</td>
								<td>2</td>
								<td>市管职务</td>
								<td>2</td>
								<td>
									<a href="#" class="">编辑</a>|
									<a href="#" class="">删除</a>
								</td>
							</tr>
							<tr style="text-overflow:ellipsis;">
								<td><a href="#">常务</a></td>
								<td>副厅</td>
								<td>10</td>
								<td>市管职务</td>
								<td>3</td>
								<td>
									<a href="#" class="">编辑</a>|
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
	function importParmenter(importType){
		$.ajax({
			url : "${path}/zzb/app/console/bset/ajax/importParamSet",
			type : "post",
			data: {"importType":importType},
			headers:{
				OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
			},
			dataType : "html",
			success : function(html){
				$('#impPAddDiv').html(html);
				$('#impPModal').modal({
					keyboard: true
				});
			},
			error : function(){
				showTip("提示","出错了请联系管理员", 1500);
			}
		});
	}
	(function(){
		App.init();

		$("#btn-browseTemplate").bind("change",function(evt){
			if($(this).val()){
				ajaxSubmit();
			}
			$(this).val('');
		});
		var myLoading = new MyLoading("${path}",{zindex:20000});
		function ajaxSubmit(){
			var fileInput = document.getElementById("btn-browseTemplate");
			if(fileInput.files.length>0){
				var name = fileInput.files[0].name
				var arr = name.split(".");
				if(arr.length<2 || !(arr[arr.length-1]=="doc" || arr[arr.length-1]=="docx"|| arr[arr.length-1]=="DOC"|| arr[arr.length-1]=="DOCX")){
					showTip("提示","请上传word文件",2000);
					return;
				}
			}else{
				showTip("提示","请选择文件上传",2000);
				return;
			}
			//hideErrorMsg();
			$("#importForm").ajaxSubmit({
				url : "${path }/zzb/app/console/appGbcxA01/ajax/execute?b01Id=${b01Id}",
				type : "post",
				headers:{
					OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
				},
				beforeSend:function(XHR){
					myLoading.show();
				},
				success : function(json){
					if(json.code == 1){
						searchSubmit()
						showTip("提示","操作成功",2000);
					}else if(json.code == -1){
						showTip("提示", json.message,2000);
					}else if(json.code == -2){
						showTip("提示", "导入数据存在错误，请及时下载已标记错误处的日志模板文件",500);
						//$('#downloanErrorTd').show();
						//$('#downloanError').attr('href','${path}/sacm/asset/export/downloanError?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}&path='+encodeURIComponent(encodeURIComponent(json.path)));
						//$('#errorMsg').text('导入数据存在错误，请及时下载已标记错误处的日志模板文件');
					}else{
						showTip("提示","出错了,请检查网络!",2000);
					}
				},
				error : function(arg1, arg2, arg3){
					showTip("提示","出错了,请检查网络!",2000);
				},
				complete : function(XHR, TS){
					myLoading.hide();
				}
			});
		}

		//批量上传干部人员审批表
		$("#btn-moreAttTemplate").bind("change", function (evt) {
			if ($(this).val()) {
				gbrmspbSubmit();
			}
			$(this).val('');
		});

		function gbrmspbSubmit() {
			var fileInput = document.getElementById("btn-moreAttTemplate");
			if (fileInput.files.length > 0) {
				var name = fileInput.files[0].name
				var arr = name.split(".");
				if (arr.length < 2 || !(arr[arr.length - 1] == "zip" || arr[arr.length - 1] == "ZIP")) {
					showTip("提示", "请上传zip文件", 2000);
					return;
				}
			} else {
				showTip("提示", "请选择文件上传", 2000);
				return;
			}
			$("#importForm").ajaxSubmit({
				url: "${path }/zzb/app/console/GbMca01/gbrmspb/ajax/batch/upload?b01Id=${b01Id}",
				type: "post",
				headers: {
					OWASP_CSRFTOKEN: "${sessionScope.OWASP_CSRFTOKEN}"
				},
				beforeSend: function (XHR) {
					myLoading.show();
				},
				success: function (json) {
					if (json.code == 1) {
						showTip("提示","上传成功",2000);
						<%--window.location.href="${path}/zzb/app/console/gbmc/a01/list?b01Id=${b01Id}&mcid=${mcid}";--%>
					} else if (json.code == -1) {
						showTip("提示", json.message, 2000);
					} else {
						showTip("提示", "出错了,请检查网络!", 2000);
					}
				},
				error: function (arg1, arg2, arg3) {
					showTip("提示", "出错了,请检查网络!", 2000);
				},
				complete: function (XHR, TS) {
					myLoading.hide();
				}
			});
		}
	})();

	function pagehref (pageNum ,pageSize){
		<%--window.location.href ="${path}/zzb/app/console/gbmc/a01/list?b01Id=${b01Id}&mcid=${mcid}&pageNum="+pageNum+"&pageSize="+pageSize;--%>
		$.ajax({
			async:false,
			type:"POST",
			url:"${path}/zzb/app/console/bset/ajax/list",
			dataType : "html",
			headers:{
				"OWASP_CSRFTOKEN":'${sessionScope.OWASP_CSRFTOKEN}'
			},
			data:{
				'queryId':"${queryId}",
				'pageNum':pageNum,
				'pageSize':pageSize
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

	function searchSubmit(){
		$.ajax({
			async:false,
			type:"POST",
			url:"${path}/zzb/app/console/bset/ajax/list",
			dataType : "html",
			headers:{
				"OWASP_CSRFTOKEN":'${sessionScope.OWASP_CSRFTOKEN}'
			},
			data : $("#searchForm").serialize(),
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


	var view = function(id){
		$.ajax({
			async:false,
			type:"POST",
			url:"${path}/zzb/app/console/appGbcxA01/ajax/view",
			dataType : "html",
			headers:{
				"OWASP_CSRFTOKEN":'${sessionScope.OWASP_CSRFTOKEN}'
			},
			data:{
				'id':id
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
	var del = function(id,itemName){
		actionByConfirm1(itemName, "${path}/zzb/app/console/appGbcxA01/delete/" + id,{} ,function(data,status){
			if (data.success == true) {
				showTip("提示","删除成功", 2000);
				setTimeout(function(){window.location.href = "${path}/zzb/app/console/appGbcxA01/list?b01Id=${b01Id}&mcid=${mcid}"},2000);
			}else{
				showTip("提示", data.message, 2000);
			}
		});
	};
	function uploadFile(fileName){
		document.getElementById("btn-"+fileName).click();
	}
	function clearData(){
		$("#b0101Query").val('');
		$.ajax({
			async:false,
			type:"POST",
			url:"${path}/zzb/app/console/bset/ajax/list",
			dataType : "html",
			headers:{
				"OWASP_CSRFTOKEN":'${sessionScope.OWASP_CSRFTOKEN}'
			},
			data : $("#searchForm").serialize(),
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
</script>
</body>
</html>
