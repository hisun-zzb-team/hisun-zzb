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
	<title>${flmc} 机构列表</title>
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
					<div class="caption">${flmc}</div>
					<div class="clearfix fr">
						<a id="sample_editable_1_new" class="btn green" href="#">
							<i class="icon-plus"></i> 添加
						</a>
						<%--<div class="btn-group">--%>
							<%--<a class="btn green dropdown-toggle" data-toggle="dropdown" href="#">--%>
								<%--导入数据 <i class="icon-angle-down"></i>--%>
							<%--</a>--%>
							<%--<ul class="dropdown-menu" style="left: -112px;">--%>
								<%--<li >--%>
									<%--<a onclick="importParmenter('gwyglxt')">从公务员管理系统(浙大网新)</a>--%>
								<%--</li>--%>
								<%--<li>--%>
									<%--<a onclick="importParmenter('zzzhywpt')">从组织综合业务平台(广州三零)</a>--%>
								<%--</li>--%>
								<%--<li>--%>
									<%--<a onclick="importParmenter('gbglxt')">从干部管理系统(长沙远望)</a>--%>
								<%--</li>--%>
							<%--</ul>--%>
						<%--</div>--%>
						<%--<span class="controllerClass btn green file_but" >--%>
							<%--<i class="icon-circle-arrow-up"></i>清空数据--%>
							<%--<input class="file_progress" type="file" name="attachFile" id="btn-browseTemplate">--%>
						<%--</span>--%>
					</div>

				</div>
			</form>
			<div class="clearfix">
				<div class="control-group">
					<div id="query" style="float: left;">
						<form action="${path }/zzb/app/console/bset/ajax/list" method="POST" id="searchForm" name="searchForm">
							<input type="hidden" id="queryId" name="queryId" value="${queryId}"/>
							<input type="hidden" name="OWASP_CSRFTOKEN" value="${sessionScope.OWASP_CSRFTOKEN}"/>
							<input type="hidden" name="pageNum" value="${pager.pageNum }" id="pageNum">
							<input type="hidden" name="pageSize" value="${pager.pageSize }" id="pageSize">
							机构名称：<input type="text" class="m-wrap" name="b0101Query" id="b0101Query" value="${b0101Query}" style="width: 100px;" />
							<button type="button" class="btn Short_but" onclick="searchSubmit()">查询</button>
							<button type="button" class="btn Short_but" onclick="clearData()">清空</button>
						</form>
					</div>
				</div>

			</div>
				<div class="portlet-body">
					<table class="table table-striped table-bordered table-hover dataTable table-set">
						<thead>
						<tr>
							<th>机构名称</th>
							<th>机构简称</th>
							<th width="80">排序</th>

							<%--<th width="100">专业技<br>术职务--%>
							<%--</th>--%>
							<%--<th width="65">任现职<br>务时间--%>
							<%--</th>--%>
							<%--<th width="100">任现职<br>级时间--%>
							<%--</th>--%>
							<%--<th width="40">操作</th>--%>
						</tr>
						</thead>
						<tbody>
						<c:forEach items="${pager.datas}" var="vo">
							<tr style="text-overflow:ellipsis;">
								<%--<td title="${vo.xm}"><a href="${path}/zzb/app/console/appGbcxA01/view?id=${vo.id }"><c:out value="${vo.xm}"></c:out></a></td>--%>
								<td><a href="${path }/zzb/app/console/bset/addOrEditManage?dataType=0&parentId=${queryId}"><c:out value="${vo.b0101}"></c:out></a></td>
								<%--<td><c:out value="${vo.b0101}"></c:out></td>--%>
								<td>&nbsp;</td>
								<td><c:out value="${vo.px}"></c:out></td>
								<%--<td><c:out value="${vo.zyjszw}"></c:out></td>--%>
								<%--<td><c:out value="${vo.xrzwsj}"></c:out></td>--%>
								<%--<td title="${vo.xrzjsj}"><c:out value="${vo.xrzjsj}"></c:out></td>--%>
								<%--<td class="Left_alignment">--%>
									<%--<a href="javascript:del('${vo.id }','${vo.xm}')" class="">删除</a>--%>
								<%--</td>--%>
							</tr>
						</c:forEach>
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
