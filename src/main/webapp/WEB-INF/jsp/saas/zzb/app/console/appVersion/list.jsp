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
<title>APP程序管理</title>
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
					<div class="portlet box grey">
						<div class="portlet-title">
							<div class="caption">APP程序管理</div>
							<div class="clearfix fr">
								<a id="sample_editable_1_new" class="btn green" href="${path }/zzb/app/console/appVersion/add">
									<i class="icon-plus"></i> 添加
								</a>
							</div>
						</div>




					<div class="portlet-body">
						<table class="table table-striped table-bordered table-hover dataTable table-set">
							<thead>
								<tr>
									<th >App应用名称</th>
									<th width="10%">应用代码</th>
									<th width="70">应用类型</th>
									<th width="10%">版本名称</th>
									<th width="50">版本号</th>
									<th width="10%">App大小</th>
									<th width="250">MD5</th>
									<th width="130">操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${pager.datas}" var="vo">
									<tr style="text-overflow:ellipsis;">
										<td><c:out value="${vo.appName}"></c:out></td>
										<td><c:out value="${vo.appCode}"></c:out></td>
										<td><c:out value="${vo.appTypeValue}"></c:out></td>
										<td><c:out value="${vo.appVersionName}"></c:out></td>
										<td><c:out value="${vo.appVersionCode}"></c:out></td>


										<td><c:out value="${vo.appSize}"></c:out>字节</td>
										<td><c:out value="${vo.appMd5}"></c:out></td>
										<td class="Left_alignment">
											<c:if test="${empty appVersionVo.appStorePath}">
												<a href="javascript:fileDown('${vo.id }');" class="">下载</a>|
											</c:if>
											<a href="${path}/zzb/app/console/appVersion/edit?id=${vo.id }" class="">编辑</a>|
											<a href="javascript:del('${vo.id }','${vo.appVersionName}')" class="">删除</a>
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
				</div>
				<%-- 表格结束 --%>
			</div>
		</div>
		
		<%-- END PAGE CONTENT--%>  
	</div>
	<script type="text/javascript">
		(function(){
			App.init();
		})();
	

		function pagehref (pageNum ,pageSize){
			<%--window.location.href ="${path}/zzb/app/console/tp/?pageNum="+pageNum+"&pageSize="+pageSize;--%>
			$("#pageNum").val(pageNum);
			$("#pageSize").val(pageSize);
			$("#searchForm").submit();
		}

		function searchSubmit(){
			document.searchForm.submit();
		}
		var del = function(id,itemName){
			actionByConfirm1(itemName, "${path}/zzb/app/console/appVersion/delete/" + id,{} ,function(data,status){
				if (data.success == true) {
					showTip("提示","删除成功", 2000);
					setTimeout(function(){window.location.href = "${path}/zzb/app/console/appVersion/"},2000);
				}else{
					showTip("提示", data.message, 2000);	
				}
			});
		};

		function clearData(){
			$("#mcQuery").val('');
			document.searchForm.submit();
		}
		function fileDown(id) {
			window.open("${path }/zzb/app/console/appVersion/ajax/down?id="+id);
		}
	</script>
</body>
</html>
