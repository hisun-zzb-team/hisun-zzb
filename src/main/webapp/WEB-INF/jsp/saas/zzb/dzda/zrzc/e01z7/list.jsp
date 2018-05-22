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
						<a class="btn green" href="javascript:ruku()">
							<i class="icon-ok"></i>转递
						</a>
					</div>
				</div>
				<div class="clearfix">
					<div class="control-group">
							<form action="${path }/zzb/dzda/a38/shList" method="POST" id="searchForm" name="searchForm">
								<input type="hidden" name="pageNum" value="${pager.pageNum }" id="pageNum">
								<input type="hidden" name="pageSize" value="${pager.pageSize }" id="pageSize">
								<div style=" float:left;margin-top:4px">档案名称:</div>
								<div style=" float:left;">
									<input type="text" class="m-wrap" name="dabhQuery" id="dabhQuery" value="${dabhQuery}" style="width: 80px;" />
								</div>
								<div style=" float:left;margin-top:4px">&nbsp;转往单位:</div>
								<div style=" float:left;">
									<input type="text" class="m-wrap" name="smxhQuery" id="smxhQuery" value="${smxhQuery}" style="width: 80px;" />
								</div>
								<div style=" float:left;margin-top:4px">&nbsp;转递日期:</div>
								<div style=" float:left;">
									<input type="text" class="m-wrap" name="a0101Query" id="a0101Query" value="${a0101Query}" style="width:80px;" />
								</div>
								<div style=" float:left;margin-top:4px">&nbsp;经办人:</div>
								<div style="float:left;width: 160px;">
									<input type="text" class="m-wrap" name="a0101Query" id="a0101Query" value="${a0101Query}" style="width:80px;" />
								</div>
								<div style=" float:left;margin-top:4px">&nbsp;回执人:</div>
								<div style="float:left;width: 160px;">
									<input type="text" class="m-wrap" name="a0101Query" id="a0101Query" value="${a0101Query}" style="width:80px;" />
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
							<th>转往单位</th>
							<th width=70>转递日期</th>
							<th width=40>经办人</th>
							<th width=70>转递原因</th>
							<th width=60>回执人</th>
							<th width=70 style="text-align: center">回执日期</th>
						</thead>
						<tbody>
							<c:forEach items="${pager.datas}" var="vo">
								<tr style="text-overflow:ellipsis;">
									<TD  style="text-align: center"><c:out value="${vo.name}"></c:out></TD>
									<TD  style="text-align: center"><c:out value="${vo.e01Z704A}"></c:out></TD>
									<TD ><fmt:formatDate value="${vo.e01Z701}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></TD>
									<TD  style="text-align: center"><c:out value="${vo.e01Z717}"></c:out></TD>
									<TD ><c:out value="${vo.e01Z721}"></c:out></TD>
									<TD><c:out value="${vo.e01Z724}"></c:out></TD>
									<TD  style="text-align: center"><c:out value="${vo.e01Z727}"></c:out></TD>
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
		$("#dabhQuery").val('');
		$("#smxhQuery").val('');
		$("#a0101Query").val('');
		$("#gbztCodeQuery").val('');
		$("#daztCodeQuery").val('');
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
