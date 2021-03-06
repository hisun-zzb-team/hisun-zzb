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
					<%--<a class="btn green" href="${path}/zzb/dzda/dajs/add?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}">
						<i class="icon-ok"></i>接收
					</a>--%>
					<a href="${path}/zzb/dzda/dajs/add?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}" class="btn blue">
						<i class="icon-plus"></i> 接收</a>
				</div>
			</div>
					<form action="${path }/zzb/dzda/dajs/list" method="POST" id="searchForm" name="searchForm" style="margin: 0 0 0px;">
						<input type="hidden" name="OWASP_CSRFTOKEN" value="${sessionScope.OWASP_CSRFTOKEN}" id="OWASP_CSRFTOKEN" >
						<input type="hidden" name="pageNum" value="${pager.pageNum }" id="pageNum">
						<input type="hidden" name="pageSize" value="${pager.pageSize }" id="pageSize">
						档案名称:
						<input type="text" class="m-wrap" name="name" id="name" value="${name}" style="width: 80px;" />
						来件单位:
						<input type="text" class="m-wrap" name="e01Z507A" id="e01Z507A" value="${e01Z507A}" style="width: 80px;" />
						接收日期:
						<input type="text" class="span10 m-wrap" name="starTime" maxlength="200" style="width: 80px;height: 30px"
							   id="starTime" readonly value="${starTime}"/><span>&nbsp;到&nbsp;
								<input type="text" class="span10 m-wrap" name="endTime" maxlength="200" style="width: 80px;height: 30px"
									   id="endTime" readonly value="${endTime}"/></span>
						经办人:
						<input type="text" class="m-wrap" name="e01Z517 m-wrap" id="e01Z517" value="${e01Z517}" style="width:80px;" />
						<%--案卷质量:--%>
						<%--<SelectTag:SelectTag id="e01Z527" textClass="span10" needNullValue="true" defaultkeys="${e01Z527}"--%>
											 <%--width="80px" token="${sessionScope.OWASP_CSRFTOKEN}"     radioOrCheckbox="radio" selectUrl="${path}/api/dictionary/select?typeCode=DAZL-2018"/>--%>

						<button type="button" class="btn Short_but" onclick="searchSubmit()">查询</button>
						<button type="button" class="btn Short_but" onclick="clearData()">清空</button>
					</form>
			<div class="portlet-body">
				<table class="table table-striped table-bordered table-hover dataTable table-set">
					<thead>
					<th width=60>档案名称</th>
					<th style="text-align: center">来件单位</th>
					<th width=110>接收日期</th>
					<th width=40>经办人</th>
					<th width=70>案卷质量</th>
					<th width=110  style="text-align: center">回执日期</th>
					<th width=120  style="text-align: center">档案位置</th>
					<th width=130  style="text-align: center">操作</th>
					</thead>
					<tbody>
					<c:forEach items="${pager.datas}" var="vo">
						<tr style="text-overflow:ellipsis;">
							<TD><c:out value="${vo.name}"></c:out></TD>
							<TD style="text-align: center"><c:out value="${vo.e01Z507A}"></c:out></TD>
							<TD ><fmt:formatDate value="${vo.e01Z501}" pattern="yyyy-MM-dd"></fmt:formatDate></TD>
							<TD><c:out value="${vo.e01Z517}"></c:out></TD>
							<TD  style="text-align: center">
								<c:choose>
									<c:when test="${vo.e01Z527 == 0}">
										不合格
									</c:when>
									<c:when test="${vo.e01Z527 == 1}">
										合格
									</c:when>
									<c:when test="${vo.e01Z527 == 2}">
										优秀
									</c:when>
								</c:choose>
							</TD>
							<TD  style="text-align: center"><fmt:formatDate value="${vo.e01Z531}" pattern="yyyy-MM-dd"></fmt:formatDate></TD>
							<TD style="text-align: center"><c:out value="${vo.e01Z541}"></c:out></TD>
							<TD style="text-align: center"><a href="javascript:editHz('${vo.id}')">填写回执</a>|
								<a href="${path}/zzb/dzda/dajs/view/${vo.id}?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}">查看</a>|
								<c:choose>
									<c:when test="${vo.fileName == '' || vo.fileName == null}">
										下载
									</c:when>
									<c:otherwise>
										<a href="javascript:downloadFile('${vo.id}')">下载</a>
									</c:otherwise>
								</c:choose>
							</TD>

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
	<div id="editModal" class="modal container hide fade" tabindex="-1" data-width="400" style="z-index: 10">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button data-dismiss="modal" class="close"  type="button"></button>
					<h3 class="modal-title" id="editTitle" >
						填写回执
					</h3>
				</div>
				<div class="modal-body" id="editDiv">
				</div>
			</div>
		</div>
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
	function editHz(id){
		$.ajax({
			url:"${path}/zzb/dzda/dajs/ajax/edit/"+id,
			type : "post",
			data: {},
			headers:{
				OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
			},
			dataType : "html",
			success : function(html){
				$('#editDiv').html(html);

				$('#editModal').modal({
					keyboard: true
				});
			},
			error : function(){
				showTip("提示","出错了请联系管理员", 1500);
			}
		});
	}
	function downloadFile(id){
		window.open("${path }/zzb/dzda/dajs/down?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}&id="+id);
	}
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
		$("#starTime").val('');
		$("#endTime").val('');
//		$("#e01Z527").val('');
		$("#pageNum").val('');
		$("#pageSize").val('');
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
		$('#starTime').datepicker({
			format: 'yyyy-mm-dd',
			weekStart: 1,
			autoclose: true,
			todayBtn: 'linked',
			language: 'zh-CN'
		});
		$('#endTime').datepicker({
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
