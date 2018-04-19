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
<!-- END PAGE LEVEL STYLES -->
<title>数据交换</title>
</head>
<body>

	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12 responsive">
				<%-- 表格开始 --%>
				<div class="portlet box grey">
					<div class="portlet-title">
						<div class="caption">数据交换</div>
						<div class="clearfix fr">
							<a id="sample_editable_1_new" class="btn green" href="${path }/zzb/app/console/exchange/add">
								<i class="icon-plus"></i> 配置数据源
							</a>
						</div>
					</div>
					<form action="" class="form-horizontal" id="form1" method="post">
					<div class="portlet-body">
						<table class="table table-striped table-bordered table-hover dataTable table-set">
							<thead>
								<tr>
									<th >名称</th>
									<th width="20%">数据源</th>
									<th width="100">IP地址</th>
									<th width="80">port</th>
									<th width="100">数据库名称</th>
									<th width="80">用户名</th>
									<th width="240">操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${pager.datas}" var="vo">
									<tr style="text-overflow:ellipsis;">
										<td>
											<a href="${path}/zzb/app/console/exchange/edit?id=${vo.id }" class=""><c:out value="${vo.actuatorName}"></c:out></a>
										</td>
										<td><c:out value="${vo.sourceTypeValue}"></c:out></td>
										<td><c:out value="${vo.ip}"></c:out></td>
										<td><c:out value="${vo.port}"></c:out></td>
										<td><c:out value="${vo.databaseName}"></c:out></td>
										<td><c:out value="${vo.userName}"></c:out></td>
										<td class="Left_alignment">
											<a href="javascript:importData('${vo.id }','${vo.actuatorName}')" class="">导入数据</a>|
											<a href="javascript:clearData('${vo.id }','${vo.actuatorName}')" class="">清除导入数据</a>|
											<a href="${path}/zzb/app/console/exchange/edit?id=${vo.id }" class="">编辑</a>|
											<a href="javascript:del('${vo.id }','${vo.sourceTypeValue}')" class="">删除</a>
										</td>
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
					</form>
				</div>
				<%-- 表格结束 --%>
			</div>
		</div>
		
		<%-- END PAGE CONTENT--%>  
	</div>
	<script type="text/javascript" src="${path }/js/common/30CloudAjax.js"></script>
<script type="text/javascript" src="${path }/js/common/loading.js"></script>
<%--<%@ include file="/WEB-INF/jsp/inc/confirmModal.jsp" %>--%>
	<script type="text/javascript">
		(function(){
			App.init();
		})();
		var myLoading = new MyLoading("${path}",{zindex:20000});


		function pagehref (pageNum ,pageSize){
			window.location.href ="${path}/zzb/app/console/exchange/?pageNum="+pageNum+"&pageSize="+pageSize;
		}
		
		function searchSubmit(){
			document.searchForm.submit();
		}
		function fileDown(id) {
			window.open("${path }/zzb/app/console/exchange/ajax/down?id="+id);
		}
		var del = function(id,itemName){
			actionByConfirm1(itemName, "${path}/zzb/app/console/exchange/delete/" + id,{} ,function(data,status){
				if (data.success == true) {
					showTip("提示","删除成功", 2000);
					setTimeout(function(){window.location.href = "${path}/zzb/app/console/exchange/"},2000);
				}else{
					showTip("提示", data.message, 2000);	
				}
			});
		};
		var importData = function(id,name){
			var title = "您确定要使用["+name+"]数据源导入数据吗？";
			var msg = "系统将在执行导入数据之前，将自动清除原有数据，此过程可能需要几十分钟。请确认后再进行操作，确认后请在下面填写数据源名称。";
			var tip = "请输入数据源的名称";
			myLoading.show();
			showPrompModal2(title,name,msg,tip,"${path}/zzb/app/console/exchange/ajax/importData?id=" + id,null, function(json){

				if(json.success == true){
					myLoading.hide();
					showTip("提示","导入成功", 1500);
				}else{
					myLoading.hide();
					showTip("提示", json.message, 2000);
				}

			})
//			showTip("提示","导入数据可能需要消耗较长时间，请耐心等待！", 2000);
			<%--$.ajax({--%>
				<%--type:"POST",--%>
				<%--url:"${path}/zzb/app/console/exchange/ajax/importData",--%>
				<%--dataType : "json",--%>
				<%--headers:{--%>
					<%--"OWASP_CSRFTOKEN":'${sessionScope.OWASP_CSRFTOKEN}'--%>
				<%--},--%>
				<%--data:{--%>
					<%--'id':id--%>
				<%--},--%>
				<%--beforeSend: function (XHR) {--%>
					<%--myLoading.show();--%>
				<%--},--%>
				<%--success:function(data){--%>

					<%--if (data.success == true) {--%>
						<%--showTip("提示","导入成功", 2000);--%>
						<%--&lt;%&ndash;setTimeout(function(){window.location.href = "${path}/zzb/app/console/exchange/"},2000);&ndash;%&gt;--%>
					<%--}else{--%>
						<%--showTip("提示", data.message, 2000);--%>
					<%--}--%>
				<%--},--%>
				<%--error : function(){--%>
					<%--showTip("提示","出错了,请检查网络!",2000);--%>
				<%--},--%>
				<%--complete: function (XHR, TS) {--%>
					<%--myLoading.hide();--%>
				<%--}--%>
			<%--});--%>
		}
		var clearData = function(id,name){
			var title = "您确定要清除数据源["+name+"]导入的数据吗？";
			var msg = "这个操作不能撤消，这将永久清除导入数据，清除后将不可恢复，请确认后再进行操作，确认后请在下面填写数据源名称。";
			var tip = "请输入数据源名称";
			myLoading.show();
			showPrompModal2(title,name,msg,tip,"${path}/zzb/app/console/exchange/ajax/clearData?id=" + id,null, function(json){

				if(json.success == true){
					myLoading.hide();
					showTip("提示","成功清除导入数据", 1500);
				}else{
					myLoading.hide();
					showTip("提示", json.message, 2000);
				}

			})
			<%--$.ajax({--%>
				<%--type:"POST",--%>
				<%--url:"${path}/zzb/app/console/exchange/ajax/clearData",--%>
				<%--dataType : "json",--%>
				<%--headers:{--%>
					<%--"OWASP_CSRFTOKEN":'${sessionScope.OWASP_CSRFTOKEN}'--%>
				<%--},--%>
				<%--data:{--%>
					<%--'id':id--%>
				<%--},--%>
				<%--beforeSend: function (XHR) {--%>
					<%--myLoading.show();--%>
				<%--},--%>
				<%--success:function(data){--%>

					<%--if (data.success == true) {--%>
						<%--showTip("提示","成功清除导入数据", 2000);--%>
						<%--&lt;%&ndash;setTimeout(function(){window.location.href = "${path}/zzb/app/console/exchange/"},2000);&ndash;%&gt;--%>
					<%--}else{--%>
						<%--showTip("提示", data.message, 2000);--%>
					<%--}--%>
				<%--},--%>
				<%--error : function(){--%>
					<%--showTip("提示","出错了,请检查网络!",2000);--%>
				<%--},--%>
				<%--complete: function (XHR, TS) {--%>
					<%--myLoading.hide();--%>
				<%--}--%>
			<%--});--%>
		}
		function showConfirmImport(name, operation) {
			var tempInConfirmModal = name;
			if (name != null && name.length > 0) {
				tempInConfirmModal = "“" + name + "”";
			}
			var defaultOperation ="导入数据";
			if (operation != null) {
				defaultOperation = operation;
			}
			setConfirmContent1("确定要" + defaultOperation + tempInConfirmModal + "吗？");
			$('#confirmModal1').modal('show');
		}
	</script>
</body>
</html>
