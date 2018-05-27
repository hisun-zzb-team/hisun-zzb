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
<title>干部名册</title>
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
							<div class="caption">干部名册</div>
							<div class="clearfix fr">
								<a id="sample_editable_1_new" class="btn green" href="${path }/zzb/app/console/gbmc/add?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}">
									<i class="icon-plus"></i> 添加
								</a>
							</div>
						</div>


					<div class="clearfix">
						<div class="control-group">
							<div id="query" style="float: left;">
								<form action="${path }/zzb/app/console/gbmc/?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}" method="POST" id="searchForm" name="searchForm">
								<input type="hidden" name="OWASP_CSRFTOKEN" value="${sessionScope.OWASP_CSRFTOKEN}"/>
								<input type="hidden" name="pageNum" value="${pager.pageNum }" id="pageNum">
								<input type="hidden" name="pageSize" value="${pager.pageSize }" id="pageSize">
									名册名称：<input type="text" class="m-wrap" name="mcQuery" id="mcQuery" value="${mcQuery}" style="width: 100px;" />
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
									<th >名册名称</th>

									<th width="10%">人员</th>
									<th width="10%">排序</th>
									<th width="10%">有无目录</th>
									<th width="15%">操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${pager.datas}" var="vo">
									<tr style="text-overflow:ellipsis;">
										<c:if test="${vo.isMl ==0}">
											<td><a href="${path}/zzb/app/console/gbmc/b01/list?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}&mcid=${vo.id }"><c:out value="${vo.mc}"></c:out></a></td>
										</c:if>
										<c:if test="${vo.isMl ==1}">
											<td><a href="${path}/zzb/app/console/gbmc/a01/list?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}&mcid=${vo.id}"><c:out value="${vo.mc}"></c:out></a></td>
										</c:if>
										<td><a href="${path}/zzb/app/console/gbmc/a01/list?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}&mcid=${vo.id}">共<c:out value="${vo.a01Count}"></c:out>人</a></td>
										<td><c:out value="${vo.px}"></c:out></td>
										<td><c:out value="${vo.isMlValue}"></c:out></td>
										<td class="Left_alignment">
											<a href="${path}/zzb/app/console/gbmc/edit?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}&id=${vo.id }" class="">编辑</a>|
											<a href="javascript:del('${vo.id }','${vo.mc}')" class="">删除</a>
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
			actionByConfirm1(itemName, "${path}/zzb/app/console/gbmc/delete/" + id+"?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}",{} ,function(data,status){
				if (data.success == true) {
					showTip("提示","删除成功", 2000);
					setTimeout(function(){window.location.href = "${path}/zzb/app/console/gbmc/?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}"},2000);
				}else{
					showTip("提示", data.message, 2000);	
				}
			});
		};

		function clearData(){
			$("#mcQuery").val('');
			document.searchForm.submit();
		}
	</script>
</body>
</html>
