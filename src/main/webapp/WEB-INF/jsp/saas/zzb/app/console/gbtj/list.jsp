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
<title>干部统计</title>
</head>
<body>
<div id="jsonDataModal" class="modal container hide fade" tabindex="-1" data-width="700">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button data-dismiss="modal" class="close"  type="button"></button>
				<h3 class="modal-title" id="title" >
					编辑数据
				</h3>
			</div>
			<div class="modal-body" id="jsonDataAddDiv">
			</div>
		</div>
	</div>
</div>
<div id="jsonDataViewModal" class="modal container hide fade" tabindex="-1" data-width="700">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button data-dismiss="modal" class="close"  type="button"></button>
				<h3 class="modal-title" id="titleView" >
					干部年龄结构
				</h3>
			</div>
			<div class="modal-body" id="jsonDataViewAddDiv">
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
						<div class="caption">干部统计数据</div>
						<div class="clearfix fr">
							<a id="sample_editable_1_new" class="btn green" href="${path }/zzb/app/console/gbtj/add">
								<i class="icon-plus"></i> 添加 
							</a>
						</div>
					</div>
			
					<div class="portlet-body">
						<table class="table table-striped table-bordered table-hover dataTable table-set">
							<thead>
								<tr>
									<th >统计名称</th>
									<th width="20%">预览</th>
									<th width="5%">排序</th>
									<th width="10%">操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${pager.datas}" var="vo">
									<tr style="text-overflow:ellipsis;">
										<td><a href="${path}/zzb/app/console/gbtj/edit?id=${vo.id }"><c:out value="${vo.tjmc}"></c:out></a></td>
										<td class="Left_alignment">
											<a href="javascript:viewJosnDate('${vo.id }','${vo.tblx}','${vo.tjmc }')" class="">预览</a>|
											<a href="javascript:editJosnDate('${vo.id }','${vo.tjmc}')" class="">编辑数据</a>
										</td>
										<td><c:out value="${vo.px}"></c:out></td>
										<td class="Left_alignment">
											<a href="${path}/zzb/app/console/gbtj/edit?id=${vo.id }" class="">编辑</a>|
											<a href="javascript:del('${vo.id }','${vo.tjmc}')" class="">删除</a>
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
			window.location.href ="${path}/zzb/app/console/gbtj/?pageNum="+pageNum+"&pageSize="+pageSize;
		}
		
		function searchSubmit(){
			document.searchForm.submit();
		}
		
		var del = function(id,itemName){
			actionByConfirm1(itemName, "${path}/zzb/app/console/gbtj/delete/" + id,{} ,function(data,status){
				if (data.success == true) {
					showTip("提示","删除成功", 2000);
					setTimeout(function(){window.location.href = "${path}/zzb/app/console/gbtj/"},2000);
				}else{
					showTip("提示", data.message, 2000);	
				}
			});
		};
		var editJosnDate = function(id,itemName){
			$.ajax({
				url : "${path}/zzb/app/console/gbtj/ajax/editJsonData",
				type : "post",
				data: {"id":id},
				headers:{
					OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
				},
				dataType : "html",
				success : function(html){
					$('#jsonDataAddDiv').html(html);
					$('#jsonDataModal').modal({
						keyboard: true
					});
				},
				error : function(){
					showTip("提示","出错了请联系管理员", 1500);
				}
			});
		}
		var viewJosnDate = function(id,tblx,tjmc){
			if(tblx == "1"){
				$.ajax({
					url : "${path}/zzb/app/console/gbtj/ajax/btView",
					type : "post",
					data: {"id":id},
					headers:{
						OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
					},
					dataType : "html",
					success : function(html){
						$('#jsonDataViewAddDiv').html(html);
						$('#titleView').text(tjmc);
						$('#jsonDataViewModal').modal({
							keyboard: true
						});
					},
					error : function(){
						showTip("提示","出错了请联系管理员", 1500);
					}
				});
			}else if(tblx == "2"){
				showTip("提示","柱状图待开发", 1500);
			}else if(tblx == "3"){
				showTip("提示","折线图待开发", 1500);
			}
		}
	</script>
</body>
</html>
