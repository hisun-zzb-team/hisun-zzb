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
	<title>档案转递</title>
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
				<div class="portlet-title">
					<div class="caption">档案转递</div>
					<div class="clearfix fr">
						<a class="btn green" href="${path}/zzb/dzda/dazd/add?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}">
							<i class="icon-ok"></i>转递
						</a>
					</div>
				</div>
							<form action="" method="POST" id="searchForm" name="searchForm">
								<input type="hidden" name="pageNum" value="${pager.pageNum }" id="pageNum">
								<input type="hidden" name="OWASP_CSRFTOKEN" value="${sessionScope.OWASP_CSRFTOKEN}"/>
								<input type="hidden" name="pageSize" value="${pager.pageSize }" id="pageSize">
								档案名称:
									<input type="text" class="m-wrap" name="name" id="name" value="${name}" style="width: 80px;" />
								&nbsp;转往单位:
									<input type="text" class="m-wrap" name="e01Z704A" id="e01Z704A" value="${e01Z704A}" style="width: 80px;" />
								&nbsp;转递日期:
									<input type="text" class="span10 m-wrap" name="starTime" maxlength="200" style="width: 80px;"
										   id="starTime" readonly value="${starTime}"/><span>&nbsp;到&nbsp;
								<input type="text" class="span10 m-wrap" name="endTime" maxlength="200" style="width: 80px;"
									   id="endTime" readonly value="${endTime}"/></span>
								&nbsp;经办人:
									<input type="text" class="m-wrap" name="e01Z717" id="e01Z717" value="${e01Z717}" style="width:80px;" />
								&nbsp;回执人:
									<input type="text" class="m-wrap" name="e01Z724" id="e01Z724" value="${e01Z724}" style="width:80px;" />
									&nbsp;&nbsp;<button type="button" class="btn Short_but" onclick="searchSubmit()">查询</button>
									<button type="button" class="btn Short_but" onclick="clearData()">清空</button>
							</form>
				<div class="portlet-body">
					<table class="table table-striped table-bordered table-hover dataTable table-set">
						<thead>
							<th width=60>档案名称</th>
							<th style="text-align: center">转往单位</th>
							<th width=70>转递日期</th>
							<th width=40>经办人</th>
							<th width=70>转递原因</th>
							<th width=60>回执人</th>
							<th width=70 style="text-align: center">回执日期</th>
							<th width=60  style="text-align: center">修改回执</th>
							<th width=60  style="text-align: center">查看</th>
							<th width=90  style="text-align: center">文件下载</th>
						</thead>
						<tbody>
							<c:forEach items="${pager.datas}" var="vo">
								<tr style="text-overflow:ellipsis;">
									<TD  style="text-align: center"><c:out value="${vo.name}"></c:out></TD>
									<TD  style="text-align: center"><c:out value="${vo.e01Z704A}"></c:out></TD>
									<TD ><fmt:formatDate value="${vo.e01Z701}" pattern="yyyy-MM-dd"></fmt:formatDate></TD>
									<TD  style="text-align: center"><c:out value="${vo.e01Z717}"></c:out></TD>
									<TD ><c:out value="${vo.e01Z721}"></c:out></TD>
									<TD><c:out value="${vo.e01Z724}"></c:out></TD>
									<TD  style="text-align: center"><fmt:formatDate value="${vo.e01Z727}" pattern="yyyy-MM-dd"></fmt:formatDate></TD>
									<TD style="text-align: center"><a href="javascript:editHz('${vo.id}')">修改</a></TD>
									<TD style="text-align: center"><a href="${path}/zzb/dzda/dazd/view/${vo.id}?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}">查看</a></TD>
									<TD style="text-align: center">
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
			url:"${path}/zzb/dzda/dazd/ajax/edit/"+id,
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

	function pagehref (pageNum ,pageSize){
		$("#pageNum").val(pageNum);
		$("#pageSize").val(pageSize);
		$("#searchForm").submit();
	}

	function searchSubmit(){
		document.searchForm.submit();
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


	function clearData(){
		$("#name").val('');
		$("#e01Z704A").val('');
		$("#starTime").val('');
		$("#endTime").val('');
		$("#e01Z717").val('');
		$("#e01Z724").val('');
		$("#pageNum").val('');
		$("#pageSize").val('');
		document.searchForm.submit();
	}
	function downloadFile(id){
		window.open("${path }/zzb/dzda/dazd/ajax/down?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}&id="+id);
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
	function ruku(){
		var checks = document.getElementsByName("dataIds");
		var checkedIds = "";
		var checkedCount = 0;
		for(var i=0;i<checks.length;i++){
			if(checks[i].checked==true){
				checkedCount ++;
				if(checkedIds==""){
					checkedIds = checks[i].value;
				}
			}
		}
		if(checkedCount == 0){
			showTip("提示","请选择要入库的干部",2000);
			return;
		}
		$.ajax({
			url : "${path }/zzb/dzda/a38/update/Sjzt",
			type : "post",
			data : {
				"a38Ids":checkedIds,
				"sjzt":'1'
			},
			headers:{
				OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
			},
			dataType : "json",
			success : function(json){
				if(json.code==1){
					myLoading.hide();
					showTip("提示", "成功入库", 1300);
					setTimeout(function(){window.location.href = "${path}/zzb/dzda/a38/shList?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}"},1300);

				}else{
					myLoading.hide();
					showTip("提示", json.message, 2000);
					return false;
				}
			},
			error : function(){
				showTip("提示","出错了,请检查网络!",2000);
				myLoading.hide();
				return false;
			}
		});
	}
</script>
</body>
</html>
