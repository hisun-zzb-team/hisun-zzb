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
<title>终端设备管理</title>
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
							<div class="caption">终端设备管理</div>
							<div class="clearfix fr">

							</div>
						</div>


					<div class="clearfix">
						<div class="control-group">
							<div id="query" style="float: left;">
								<form action="${path }/zzb/app/console/appClient/" method="POST" id="searchForm" name="searchForm">
								<input type="hidden" name="OWASP_CSRFTOKEN" value="${sessionScope.OWASP_CSRFTOKEN}"/>
								<input type="hidden" name="pageNum" value="${pager.pageNum }" id="pageNum">
								<input type="hidden" name="pageSize" value="${pager.pageSize }" id="pageSize">
								终端标识：<input type="text" class="m-wrap" name="identificationQuery" id="identificationQuery" value="${identificationQuery}" style="width: 100px;" />
									状态：<select name="statusQuery" id="statusQuery" style="margin-bottom: 0px;width:80px;">
									<option value="-1" ${statusQuery == -1?'selected="selected"':''}>全部</option>
									<option value="1" ${statusQuery == 1?'selected="selected"':''}>正常</option>
									<option value="2" ${statusQuery ==2?'selected="selected"':''}>停用</option>
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
									<th >终端标识</th>
									<th width="10%">终端设备状态</th>
									<th width="10%">操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${pager.datas}" var="vo">
									<tr style="text-overflow:ellipsis;">
										<td><c:out value="${vo.identification}"></c:out></td>
										<td id="${vo.id }_td" style="text-align: center"><c:out value="${vo.statusValue}"></c:out></td>
										<td class="Left_alignment">
											<a href="javascript:changeStatus('${vo.id}')" id="${vo.id }_Status">
												<c:if test="${vo.status eq '1'}">
													停用该设备
												</c:if>
												<c:if test="${vo.status eq '2'}">
													启用该设备
												</c:if>

											</a>
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
	<script type="text/javascript" src="${path }/js/common/30CloudAjax.js"></script>
	<script type="text/javascript" src="${path }/js/common/loading.js"></script>
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
			actionByConfirm1(itemName, "${path}/zzb/app/console/appClient/delete/" + id,{} ,function(data,status){
				if (data.success == true) {
					showTip("提示","删除成功", 2000);
					setTimeout(function(){window.location.href = "${path}/zzb/app/console/gbmc/"},2000);
				}else{
					showTip("提示", data.message, 2000);	
				}
			});
		};

		function clearData(){
			$("#identificationQuery").val('');
			$("#statusQuery").val('-1');
			document.searchForm.submit();
		}

		function changeStatus(id){
			$.cloudAjax({
				path : '${path}',
				url : "${path }/zzb/app/console/appClient/changeStatus/"+id,
				type : "post",
				data : $("#form1").serialize(),
				dataType : "json",
				success : function(data){
					if(data.success){
						document.getElementById(id+"_td").innerHTML  = data.statustdValue;
						document.getElementById(id+"_Status").innerHTML  = data.statusBtnValue;
						showTip("提示","该设备状态成功修改为“"+data.statustdValue+"”",2000);
						//setTimeout(function(){window.location.href = "${path}/zzb/app/console/bwh/"},2000);
					}else{
						showTip("提示", json.message, 2000);
					}
				},
				error : function(){
					showTip("提示","出错了请联系管理员",2000);
				}
			});
		}
	</script>
</body>
</html>
