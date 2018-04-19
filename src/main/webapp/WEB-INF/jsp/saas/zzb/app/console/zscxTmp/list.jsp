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
	<title>${b0101} 职数情况</title>
	<style type="text/css">
		form {
			margin: 0 0 0px;
		}
	</style>
</head>
<body>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12 responsive">
			<%-- 表格开始 --%>
			<form class=""id="importForm" enctype="multipart/form-data">
				<input type="hidden" name="b01Id" value="${b01Id}"/>
				<div class="portlet-title">
					<div class="caption">${b0101} 职数情况</div>
					<div class="clearfix fr">
						<a class="btn blue" herf="javascript:void(0)" onclick="fileDown()"><i
						class="icon-circle-arrow-down"></i>输出</a>
                   </div>
				</div>
			</form>
               <%--<div class="clearfix">--%>
				<%--<div class="control-group">--%>
					<%--<div id="query" style="float: left;">--%>
						<%--<form action="${path }/zzb/app/console/appZscxZs/ajax/list" method="POST" id="searchForm" name="searchForm">--%>
							<%--<input type="hidden" id="b01Id" name="b01Id" value="${b01Id}"/>--%>
							<%--<input type="hidden" name="OWASP_CSRFTOKEN" value="${sessionScope.OWASP_CSRFTOKEN}"/>--%>
							<%--<input type="hidden" name="pageNum" value="${pager.pageNum }" id="pageNum">--%>
							<%--<input type="hidden" name="pageSize" value="${pager.pageSize }" id="pageSize">--%>
							<%--姓名：<input type="text" class="m-wrap" name="xmQuery" id="xmQuery" value="${xmQuery}" style="width: 100px;" />--%>
							<%--<button type="button" class="btn Short_but" onclick="searchSubmit()">查询</button>--%>
							<%--<button type="button" class="btn Short_but" onclick="clearData()">清空</button>--%>
						<%--</form>--%>
					<%--</div>--%>
				<%--</div>--%>

			<%--</div>--%>
				<div class="portlet-body">
					<table class="table table-striped table-bordered table-hover dataTable table-set">
						<thead>
						<tr>
							<th width="200">职务名称</th>
							<th width="100">职务级别</th>
							<th width="100">编制数</th>
							<th width="100">现配干部数</th>
							<th width="100">超缺编情况</th>
							<th>备注</th>
							<%--<th width="100">操作</th>--%>
						</tr>
						</thead>
						<tbody>
						<c:if test="${ b01Id eq 'allZs'}">
							<tr style="text-overflow:ellipsis;">
								<td>调研员</td>
								<td>正处</td>
								<td>65</td>
								<td>60</td>
								<td>-5</td>
								<td>&nbsp;</td>
							</tr>
							<tr style="text-overflow:ellipsis;">
								<td>副调研员</td>
								<td>副处</td>
								<td>156</td>
								<td>150</td>
								<td>-6</td>
								<td>&nbsp;</td>
							</tr>
							<tr style="text-overflow:ellipsis;">
								<td colspan="2">合计</td>
								<td>221</td>
								<td>210</td>
								<td>-11</td>
								<td>&nbsp;</td>
							</tr>
							<tr style="text-overflow:ellipsis;">
								<td colspan="6" style="color: #0b64bc">注：调研员缺编5人，副调研员缺编6人，合计缺编11人</td>
							</tr>
						</c:if>
						<c:if test="${b01Id ne 'allZs'}">
							<tr style="text-overflow:ellipsis;">
								<td>部长</td>
								<td>正处</td>
								<td>1</td>
								<td>1</td>
								<td>0</td>
								<td>&nbsp;</td>
							</tr>
							<tr style="text-overflow:ellipsis;">
								<td>副部长</td>
								<td>副处</td>
								<td>5</td>
								<td>2</td>
								<td>-3</td>
								<td>&nbsp;</td>
							</tr>
							<tr style="text-overflow:ellipsis;">
								<td>宣传办主任</td>
								<td>副处</td>
								<td>1</td>
								<td>0</td>
								<td>-1</td>
								<td>&nbsp;</td>
							</tr>
							<tr style="text-overflow:ellipsis;">
								<td>精神文明办主任</td>
								<td>副处</td>
								<td>1</td>
								<td>1</td>
								<td>0</td>
								<td>&nbsp;</td>
							</tr>
							<tr style="text-overflow:ellipsis;">
								<td>互联网信息办主任</td>
								<td>副处</td>
								<td>1</td>
								<td>1</td>
								<td>0</td>
								<td>&nbsp;</td>
							</tr>
							<tr style="text-overflow:ellipsis;">
								<td colspan="2">合计</td>
								<td>9</td>
								<td>5</td>
								<td>-4</td>
								<td>&nbsp;</td>
							</tr>
							<tr style="text-overflow:ellipsis;">
								<td colspan="6" style="color: #0b64bc">注：副部长缺编3人，宣传办主任缺编1人，合计缺编4人</td>
							</tr>
						</c:if>
						</tbody>
					</table>

				</div>
		</div>
		<%-- 表格结束 --%>
	</div>
</div>

<%-- END PAGE CONTENT--%>
</div>

<script type="text/javascript">
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
				url : "${path }/zzb/app/console/appZscxZs/ajax/execute?b01Id=${b01Id}",
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
			url:"${path}/zzb/app/console/appZscxZs/ajax/list",
			dataType : "html",
			headers:{
				"OWASP_CSRFTOKEN":'${sessionScope.OWASP_CSRFTOKEN}'
			},
			data:{
				'b01Id':"${b01Id}",
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
			url:"${path}/zzb/app/console/appZscxZs/ajax/list",
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


	var viewA01s = function(id){
		$.ajax({
			async:false,
			type:"POST",
			url:"${path}/zzb/app/console/asetA01/ajax/listByZscx",
			dataType : "html",
			headers:{
				"OWASP_CSRFTOKEN":'${sessionScope.OWASP_CSRFTOKEN}'
			},
			data:{
				'b01Id':"${b01Id}",
				'zsId':id,
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
		actionByConfirm1(itemName, "${path}/zzb/app/console/appZscxZs/delete/" + id,{} ,function(data,status){
			if (data.success == true) {
				showTip("提示","删除成功", 2000);
				setTimeout(function(){window.location.href = "${path}/zzb/app/console/appZscxZs/list?b01Id=${b01Id}&mcid=${mcid}"},2000);
			}else{
				showTip("提示", data.message, 2000);
			}
		});
	};
	function uploadFile(fileName){
		document.getElementById("btn-"+fileName).click();
	}
	function clearData(){
		$("#xmQuery").val('');
		$.ajax({
			async:false,
			type:"POST",
			url:"${path}/zzb/app/console/appZscxZs/ajax/list",
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
