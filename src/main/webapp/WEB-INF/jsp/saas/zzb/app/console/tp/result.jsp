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
<title>"${shpcmc}" 票决结果</title>
</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12 responsive">
				<%-- 表格开始 --%>
				<form class="portlet box grey"id="importForm" enctype="multipart/form-data">
					<input type="hidden" id="shpcId" name="shpcId" value="${shpcId}"/>
					<div class="portlet-title">
						<div class="caption">"${shpcmc}" 票决结果</div>
						<div class="clearfix fr">
							<a class="btn green" id="btn-export" onclick="exportCi()"><i class="icon-circle-arrow-down" ></i>导出</a>
							<a class="btn" href="${path }/zzb/app/console/tp/"><i class="icon-undo"></i>返回</a>
						</div>

                        </div>

                            <table class="table table-striped table-bordered table-hover dataTable">
                                <thead>
                                    <tr>
                                        <th width="20%" style="text-align: center;border-right-color: rgb(225, 230, 235);border-right-style: solid;border-right-width: 1px" rowspan=2>姓名</th>
										<th colspan="3" style="text-align: center;border-right-color: rgb(225, 230, 235);border-right-style: solid;border-right-width: 1px">票决结果</th>
										<th width="20%" style="text-align: center;" rowspan=2>得票率</th>
                                    </tr>
									<tr>
										<th width="20%"style="text-align: center;border-right-color: rgb(225, 230, 235);border-right-style: solid;border-right-width: 1px">同意(票数）</th>
										<th width="20%"style="text-align: center;border-right-color: rgb(225, 230, 235);border-right-style: solid;border-right-width: 1px">不同意(票数）</th>
										<th width="20%"style="text-align: center;border-right-color: rgb(225, 230, 235);border-right-style: solid;border-right-width: 1px">弃权(票数）</th>
									</tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${pager.datas}" var="vo">
                                        <tr style="text-overflow:ellipsis;">
											<td width="20%"><c:out value="${vo.xm}"></c:out></td>
											<td width="20%"><c:out value="${vo.tyCount}"></c:out></td>
											<td width="20%"><c:out value="${vo.btyCount}"></c:out></td>
											<td width="20%"><c:out value="${vo.qqCount}"></c:out></td>
											<td width="20%"><c:out value="${vo.dplCount}%"></c:out></td>
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
		(function(){
			App.init();

			$("#btn-browseTemplate").bind("change",function(evt){
				if($(this).val()){
					ajaxSubmit();
				}
				$(this).val('');
			});
		})();

		function pagehref (pageNum ,pageSize){
			window.location.href ="${path}/zzb/app/console/tp/result?shpcId=${shpcId}&pageNum="+pageNum+"&pageSize="+pageSize;
		}

		function searchSubmit(){
			document.searchForm.submit();
		}
		//导出方法
		function exportCi(){
			window.open('${path}/zzb/app/console/tp/ajax/exportToExcel?shpcId=${shpcId}&OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}');
			<%--window.open('${path }/zzb/app/console/tp/exportToExcel?shpcId=${shpcId}');--%>
		}

	</script>
</body>
</html>
