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

	<link href="${path}/css/common/common.css" rel="stylesheet" type="text/css"/>
	<!-- BEGIN PAGE LEVEL STYLES -->
	<link rel="stylesheet" href="${path }/css/DT_bootstrap.css" />
	<link rel="stylesheet" type="text/css" href="${path }/css/bootstrap-fileupload.css">

	<link href="${path }/css/style.css" rel="stylesheet" type="text/css">
	<!-- END PAGE LEVEL STYLES -->
	<title>档案接收</title>
	<style type="text/css">
	</style>
	<link rel="stylesheet" type="text/css"
		  href="${path}/css/select2_metro.css" />
	<link href="${path}/css/common/common.css" rel="stylesheet" type="text/css"/>
	<link rel="stylesheet" href="${path}/css/DT_bootstrap.css" />
	<script type="text/javascript" src="${path}/js/select2.min.js"></script>
	<script type="text/javascript"
			src="${path}/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="${path}/js/jquery.form.js"></script>
	<script type="text/javascript" src="${path}/js/DT_bootstrap.js"></script>
	<script type="text/javascript" src="${path}/js/bootstrap-datepicker.js"></script>
	<script type="text/javascript" src="${path}/js/bootstrap-datepicker.zh-CN.js"></script>
	<script type="text/javascript" src="${path}/js/bootstrap-datetimepicker.js"></script>
	<script type="text/javascript" src="${path}/js/bootstrap-datetimepicker.zh-CN.js"></script>
</head>
<body>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12 responsive">
			<%-- 表格开始 --%>
				<div class="portlet-title">
					<div class="caption">档案接收</div>
					<div class="clearfix fr">
						<a class="btn green" href="${path}/zzb/dzda/dajs/add?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}">
							<i class="icon-ok"></i>接收
						</a>
					</div>
				</div>
				<div class="clearfix">
					<div class="control-group">
							<form action="${path }/zzb/dzda/dajs/list" method="POST" id="searchForm" name="searchForm">
								<input type="hidden" name="OWASP_CSRFTOKEN" value="${sessionScope.OWASP_CSRFTOKEN}" id="OWASP_CSRFTOKEN">
								<input type="hidden" name="pageNum" value="${pager.pageNum }" id="pageNum">
								<input type="hidden" name="pageSize" value="${pager.pageSize }" id="pageSize">
								<div style=" float:left;margin-top:4px">档案名称:</div>
								<div style=" float:left;">
									<input type="text" class="m-wrap" name="name" id="name" value="${name}" style="width: 80px;" />
								</div>
								<div style=" float:left;margin-top:4px">&nbsp;来件单位:</div>
								<div style=" float:left;">
									<input type="text" class="m-wrap" name="e01Z507A" id="e01Z507A" value="${e01Z507A}" style="width: 80px;" />
								</div>
								<div style=" float:left;margin-top:4px">&nbsp;接收日期:</div>
								<div style=" float:left;">
									<input type="text" class="span10 m-wrap" name="e01Z501" maxlength="200"
										   id="e01Z501" readonly
										   value=""/>
								</div>
								<div style=" float:left;margin-top:4px">&nbsp;经办人:</div>
								<div style="float:left;width: 160px;">
									<input type="text" class="m-wrap" name="e01Z517" id="e01Z517" value="${e01Z517}" style="width:80px;" />
								</div>
								<div style=" float:left;margin-top:4px">&nbsp;案卷质量:</div>
								<div style="float:left;width: 160px;">
									<SelectTag:SelectTag id="e01Z244" valueName="e01Z244Content" textClass="m-wrap span10" needNullValue="true" defaultkeys="${e01Z527}"
														 token="${sessionScope.OWASP_CSRFTOKEN}"     radioOrCheckbox="radio" selectUrl="${path}/api/dictionary/select?typeCode=SFBS-2018"/>

								</div>
								<div style="float:left">
									&nbsp;&nbsp;<button type="button" class="btn Short_but" onclick="searchSubmit()">查询</button>
									<button type="button" class="btn Short_but" onclick="clearData()">清空</button>
								</div>
							</form>
					</div>

				</div>
				<div class="portlet-body">
					<table class="table table-striped table-bordered table-hover dataTable table-set">
						<thead>
							<th width=60>档案名称</th>
							<th>来件单位</th>
							<th width=70>接收日期</th>
							<th width=40>经办人</th>
							<th width=70>案卷质量</th>
							<th width=70  style="text-align: center">回执日期</th>
							<th width=120  style="text-align: center">档案位置</th>
						</thead>
						<tbody>
							<c:forEach items="${pager.datas}" var="vo">
								<tr style="text-overflow:ellipsis;">
									<TD><a href="${path}/zzb/dzda/a38/editManage?id=${vo.id }&listType=shList"><c:out value="${vo.name}"></c:out></a> </TD>
									<TD style="text-align: center"><c:out value="${vo.e01Z507A}"></c:out></TD>
									<TD ><fmt:formatDate value="${vo.e01Z501}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></TD>
									<TD><c:out value="${vo.e01Z517}"></c:out></TD>
									<TD  style="text-align: center"><c:out value="${vo.e01Z527}"></c:out></TD>
									<TD  style="text-align: center"><fmt:formatDate value="${vo.e01Z531}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></TD>
									<TD style="text-align: center"><c:out value="${vo.e01Z541}"></c:out></TD>
								</TR>
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
<script type="text/javascript">

	var myLoading = new MyLoading("${path}",{zindex : 11111});
	(function(){
		App.init();



	})();

	function pagehref (pageNum ,pageSize){
		$("#pageNum").val(pageNum);
		$("#pageSize").val(pageSize);
		$("#searchForm").submit();
	}

	function searchSubmit(){
		document.searchForm.submit();
	}
	function clearData(){
		$("#name").val('');
		$("#e01Z507A").val('');
		$("#e01Z517").val('');
		$("#e01Z501").val('');
		$("#e01Z527").val('');
		$("#gbztContentQuery").val('');
		$("#daztContentQuery").val('');
		document.searchForm.submit();
	}
	function dataAllcheckChange(){
		var allCheck = document.getElementById("allCheck");
		var checks = document.getElementsByName("dataIds");
		if(checks){
			for(var i=0;i<checks.length;i++) {
				checks[i].checked = allCheck.checked;
				if (allCheck.checked == true) {
					checks[i].parentNode.className = "checked";
				}else{
					checks[i].parentNode.className = "";
				}
			}
		}
	}
	$(function(){
		$('#e01Z501').datepicker({
			format: 'yyyy-mm-dd',
			weekStart: 1,
			autoclose: true,
			todayBtn: 'linked',
			language: 'zh-CN'
		});
	})
</script>
</body>
</html>
