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
<title>会议研究</title>
</head>
<body>
<div id="attsModal" class="modal container hide fade" tabindex="-1" data-width="700">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button data-dismiss="modal" class="close"  type="button"></button>
				<h3 class="modal-title" id="title" >
					附件列表
				</h3>
			</div>
			<div class="modal-body" id="attsAddDiv">
			</div>
		</div>
	</div>
</div>
	<div class="container-fluid">

		<div class="row-fluid">
			<div class="span12 responsive">
				<%-- 表格开始 --%>
				<div class="portlet box grey">
					<div class="portlet-title">
						<div class="caption">会议研究批次</div>
						<div class="clearfix fr">
							<a id="sample_editable_1_new" class="btn green" href="${path }/zzb/app/console/bwh/add?shpcPageNum=${pager.pageNum }">
								<i class="icon-plus"></i> 添加 
							</a>
						</div>
					</div>
					<form action="" class="form-horizontal" id="form1" method="post">
					<div class="portlet-body">
						<table class="table table-striped table-bordered table-hover dataTable table-set">
							<thead>
								<tr>
									<th >批次名称</th>
									<th width="10%">上会类型</th>
									<th width="10%">数据类型</th>
									<th width="10%">上会时间</th>
									<th width="10%">上会状态</th>
									<th width="10%">上会名单</th>
									<th width="50">排序</th>
									<th width="12%">操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${pager.datas}" var="vo">
									<tr style="text-overflow:ellipsis;">
										<td>
											<c:if test="${vo.sjlx eq '1'}">
												<a href="${path}/zzb/app/console/Sha01/list?shpcId=${vo.id }&shpcPageNum=${pager.pageNum}"><c:out value="${vo.pcmc}"></c:out></a>
											</c:if>
											<c:if test="${vo.sjlx eq '2'}">
												<c:if test="${!empty vo.filePath}">
													<a href="javascript:fileDown('${vo.id }')" class=""><c:out value="${vo.pcmc}"></c:out></a>
												</c:if>
												<c:if test="${empty vo.filePath}">
													<c:out value="${vo.pcmc}"></c:out>
												</c:if>
											</c:if>
										</td>
										<td><c:out value="${vo.shlxValue}"></c:out></td>
										<td><c:out value="${vo.sjlxValue}"></c:out></td>
										<td><c:out value="${vo.pcsjValue}"></c:out></td>
										<td><a href="javascript:changeShZt('${vo.id}')" id="${vo.id }_shZt"><c:out value="${vo.shZtValue}"></c:out></a></td>
										<td>
											<c:if test="${vo.sjlx eq '1'}">
												<a href="${path}/zzb/app/console/Sha01/list?shpcId=${vo.id }&shpcPageNum=${pager.pageNum}" class="">共${vo.a01Count }人</a>
											</c:if>
											<c:if test="${vo.sjlx eq '2'}">---</c:if>
										</td>
										<td><c:out value="${vo.px}"></c:out></td>
										<td class="Left_alignment">

											<a href="${path}/zzb/app/console/bwh/edit?id=${vo.id }&shpcPageNum=${pager.pageNum}" class="">编辑</a>|
											<a href="javascript:del('${vo.id }','${vo.pcmc}')" class="">删除</a>
											<c:if test="${vo.sjlx eq '2'}">
												|<a href="javascript:addAtts('${vo.id }')" class="">附件</a>
											</c:if>
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
		var addAtts = function(shpcId){
			$.ajax({
				url : "${path}/zzb/app/console/shpcAtts/ajax/editAtts",
				type : "post",
				data: {"shpcId":shpcId,"shpcPageNum":"${pager.pageNum }"},
				headers:{
					OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
				},
				dataType : "html",
				success : function(html){
					$('#attsAddDiv').html(html);
					$('#attsModal').modal({
						keyboard: true
					});
				},
				error : function(){
					showTip("提示","出错了请联系管理员", 1500);
				}
			});
		}

		function pagehref (pageNum ,pageSize){
			window.location.href ="${path}/zzb/app/console/bwh/?pageNum="+pageNum+"&pageSize="+pageSize;
		}
		
		function searchSubmit(){
			document.searchForm.submit();
		}
		function fileDown(id) {
			window.open("${path }/zzb/app/console/bwh/ajax/down?id="+id);
		}
		var del = function(id,itemName){
			actionByConfirm1(itemName, "${path}/zzb/app/console/bwh/delete/?id=" + id+"&shpcPageNum=${pager.pageNum }",{} ,function(data,status){
				if (data.success == true) {
					showTip("提示","删除成功", 2000);
					setTimeout(function(){window.location.href = "${path}/zzb/app/console/bwh/?pageNum=${shpcPageNum}"},2000);
				}else{
					showTip("提示", data.message, 2000);	
				}
			});
		};
		function changeShZt(id){
			$.cloudAjax({
				path : '${path}',
				url : "${path }/zzb/app/console/bwh/changeShZt/"+id,
				type : "post",
				data : $("#form1").serialize(),
				dataType : "json",
				success : function(data){
					if(data.success){
						document.getElementById(id+"_shZt").innerHTML  = data.shZtValue;
						showTip("提示","上会状态成功修改为“"+data.shZtValue+"”",2000);
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
