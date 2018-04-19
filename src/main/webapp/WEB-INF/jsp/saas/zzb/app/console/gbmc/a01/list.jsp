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
	<title>人员列表</title>
	<style type="text/css">
		form {
			margin: 0 0 0px;
		}
		.table td a{
			margin:0px;
		}

	</style>
</head>
<body>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12 responsive">
			<%-- 表格开始 --%>
			<form class=""id="importForm" enctype="multipart/form-data">
				<input type="hidden" name="mcb01id" value="${mcb01id}"/>
				<div class="portlet-title">
					<div class="caption">人员列表</div>
					<div class="clearfix fr">

							<span class="controllerClass btn green file_but" >
								<i class="icon-circle-arrow-up"></i>上传名册
								<input class="file_progress" type="file" name="attachFile" id="btn-browseTemplate">
							</span>
							<%--<span class="controllerClass btn green file_but" >--%>
								<%--<i class="icon-circle-arrow-up"></i>批量上传--%>
								<%--<input class="file_progress" type="file" name="moreAttFile" id="btn-moreAttTemplate">--%>
							<%--</span>--%>
						<input class="file_progress" type="file" name="gbrmspbFile" id="btn-gbrmspbFile">
						<c:if test="${returnList eq 'b01Lis'}">
							<a class="btn" href="${path }/zzb/app/console/gbmc/b01/list?mcid=${mcid}"><i class="icon-undo"></i>返回</a>
						</c:if>
						<c:if test="${returnList eq 'gbmcList'}">
							<a class="btn" href="${path }/zzb/app/console/gbmc/"><i class="icon-undo"></i>返回</a>
						</c:if>
					</div>
				</div>
			</form>
			<div class="clearfix">
				<div class="control-group">
					<div id="query" style="float: left;">
						<form action="${path }/zzb/app/console/gbmc/a01/list" method="POST" id="searchForm" name="searchForm">
							<input type="hidden" id="mcb01id" name="mcb01id" value="${mcb01id}"/>
							<input type="hidden" id="mcid" name="mcid" value="${mcid}"/>
							<input type="hidden" name="OWASP_CSRFTOKEN" value="${sessionScope.OWASP_CSRFTOKEN}"/>
							<input type="hidden" name="pageNum" value="${pager.pageNum }" id="pageNum">
							<input type="hidden" name="pageSize" value="${pager.pageSize }" id="pageSize">
							姓名：<input type="text" class="m-wrap" name="xmQuery" id="xmQuery" value="${xmQuery}" style="width: 100px;" />
							是否上传材料：<select class="select_form" tabindex="-1" name="gbrmspbFileQuery" id="gbrmspbFileQuery" style="width: 150px; margin-bottom: 0px;" >
							<option value="all" <c:if test="${gbrmspbFileQuery eq 'all'}">selected</c:if>>全部</option>
							<option value="no" <c:if test="${gbrmspbFileQuery eq 'no'}">selected</c:if>>未上传</option>
							<option value="yes" <c:if test="${gbrmspbFileQuery eq 'yes'}">selected</c:if>>已上传</option>
						</select>
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
							<th width="70">姓名</th>
							<th width="80">职务</th>
							<th width="60">籍贯</th>
							<th width="60">出生地</th>
							<th width="60">出生<br>年月</th>
							<th width="60">参加工<br>作时间</th>
							<th width="60">入党<br>时间</th>
							<th>全日制学历<br>学位及专业
							</th>
							<th width="120">在职学历<br>学位及专业
							</th>
							<th width="100">专业技<br>术职务
							</th>
							<th width="70">任现职<br>务时间
							</th>
							<th width="60">任现职<br>级时间
							</th>
							<th width="60">材料</th>
							<%--<th width="40">操作</th>--%>
						</tr>
						</thead>
						<tbody>
						<c:forEach items="${pager.datas}" var="vo">
							<tr style="text-overflow:ellipsis;">
								<td title="${vo.xm}"><a href="${path}/zzb/app/console/gbmc/a01/view?id=${vo.id }"><c:out value="${vo.xm}"></c:out></a></td>
								<td title="${vo.zw}"><c:out value="${vo.zw}"></c:out></td>
								<td><c:out value="${vo.jg}"></c:out></td>
								<td><c:out value="${vo.csd}"></c:out></td>
								<td><c:out value="${vo.csny}"></c:out></td>
								<td><c:out value="${vo.cjgzsj}"></c:out></td>
								<td><c:out value="${vo.rdsj}"></c:out></td>
								<td><c:out value="${vo.qrzxlxwjzy}"></c:out></td>
								<td><c:out value="${vo.zzxlxwjzy}"></c:out></td>
								<td><c:out value="${vo.zyjszw}"></c:out></td>
								<td><c:out value="${vo.xrzwsj}"></c:out></td>
								<td title="${vo.xrzjsj}"><c:out value="${vo.xrzjsj}"></c:out></td>
								<td style="margin: 0px;">
									<c:if test="${vo.havagbrmspbFile }">
									<em style="margin: 0px;display: inline-block"><a href="javascript:gbrmspbDown('${vo.id }')" class="margin: 0px;">任免审批表</a></em>
									</c:if>
									<c:if test="${!vo.havagbrmspbFile }">
									<a class="margin: 0px;" href="javascript:unloadFile('${vo.id }')"><em style="color:#333;display: inline-block">任免审批表</em></a>
									</c:if>
								</td>
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
<script type="text/javascript" src="${path }/js/common/loading.js"></script>
<script src="${path }/js/bootstrap-fileupload.js"></script>
<script src="${path }/js/bootstrap-fileupload.js"></script>
<!— 引入确认框模块 —>
<%@ include file="/WEB-INF/jsp/inc/confirmModal.jsp" %>
<script type="text/javascript">
	var a01Id ="";
	function unloadFile(uploadA01Id){
		a01Id = uploadA01Id;
		document.getElementById("btn-gbrmspbFile").click();
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
				url : "${path }/zzb/app/console/gbmc/a01/ajax/execute?mcb01id=${mcb01id}",
				type : "post",
				headers:{
					OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
				},
				beforeSend:function(XHR){
					myLoading.show();
				},
				success : function(json){
					if(json.code == 1){
						showTip("提示","操作成功",500);
						//loadCiList(ciObjectId);
						window.location.href="${path}/zzb/app/console/gbmc/a01/list?mcb01id=${mcb01id}&mcid=${mcid}";
					}else if(json.code == -1){
						showTip("提示", json.message, 500);
					}else if(json.code == -2){
						showTip("提示", "导入数据存在错误，请及时下载已标记错误处的日志模板文件",500);
						//$('#downloanErrorTd').show();
						//$('#downloanError').attr('href','${path}/sacm/asset/export/downloanError?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}&path='+encodeURIComponent(encodeURIComponent(json.path)));
						//$('#errorMsg').text('导入数据存在错误，请及时下载已标记错误处的日志模板文件');
					}else{
						showTip("提示","出错了,请检查网络!",500);
					}
				},
				error : function(arg1, arg2, arg3){
					showTip("提示","出错了,请检查网络!",500);
				},
				complete : function(XHR, TS){
					myLoading.hide();
				}
			});
		}


		$("#btn-gbrmspbFile").bind("change", function (evt) {
			if ($(this).val()) {
				gbrmspbSubmit();
			}
			$(this).val('');
		});
		var myLoading = new MyLoading("${path}", {zindex: 20000});

		function gbrmspbSubmit() {
			var fileInput = document.getElementById("btn-gbrmspbFile");
			if (fileInput.files.length > 0) {
				var name = fileInput.files[0].name
				var arr = name.split(".");
				if (arr.length < 2 || !(arr[arr.length - 1] == "doc" || arr[arr.length - 1] == "docx" || arr[arr.length - 1] == "DOC" || arr[arr.length - 1] == "DOCX")) {
					showTip("提示", "请上传word文件", 2000);
					return;
				}
			} else {
				showTip("提示", "请选择文件上传", 2000);
				return;
			}

			$("#importForm").ajaxSubmit({
				url:"${path }/zzb/app/console/GbMca01/gbrmspb/ajax/uploadFile?gbMcA01Id="+a01Id ,
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
						searchSubmit();
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

		//批量上传干部人员审批表
		$("#btn-moreAttTemplate").bind("change", function (evt) {
			if ($(this).val()) {
				moreGbrmspbSubmit();
			}
			$(this).val('');
		});

		function moreGbrmspbSubmit() {
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
				url: "${path }/zzb/app/console/GbMca01/gbrmspb/ajax/batch/upload?mcb01id=${mcb01id}",
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
						<%--window.location.href="${path}/zzb/app/console/gbmc/a01/list?mcb01id=${mcb01id}&mcid=${mcid}";--%>
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
		<%--window.location.href ="${path}/zzb/app/console/gbmc/a01/list?mcb01id=${mcb01id}&mcid=${mcid}&pageNum="+pageNum+"&pageSize="+pageSize;--%>
		$("#pageNum").val(pageNum);
		$("#pageSize").val(pageSize);
		$("#searchForm").submit();
	}

	function searchSubmit(){
		document.searchForm.submit();
	}

	var del = function(id,itemName){
		actionByConfirm1(itemName, "${path}/zzb/app/console/gbmc/a01/delete/" + id,{} ,function(data,status){
			if (data.success == true) {
				showTip("提示","删除成功", 2000);
				setTimeout(function(){window.location.href = "${path}/zzb/app/console/gbmc/a01/list?mcb01id=${mcb01id}&mcid=${mcid}"},2000);
			}else{
				showTip("提示", data.message, 2000);
			}
		});
	};
	function gbrmspbDown(id) {
		window.open("${path }/zzb/app/console/GbMca01/gbrmspb/ajax/down?gbMcA01Id="+id);
	}
	function uploadFile(fileName){
		document.getElementById("btn-"+fileName).click();
	}
	function clearData(){
		$("#xmQuery").val('');
		$("#gbrmspbFileQuery").val('');
		document.searchForm.submit();
	}
</script>
</body>
</html>
