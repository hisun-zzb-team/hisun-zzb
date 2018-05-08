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

	<!-- END PAGE LEVEL STYLES -->
	<title></title>
	<style type="text/css">
		form {
			margin: 0 0 0px;
		}
	</style>
</head>
<body>
<div id="jgModal" class="modal container hide fade" tabindex="-1" data-width="500">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button data-dismiss="modal" class="close"  type="button"></button>
				<h3 class="modal-title" id="title" >
					增加目录类型
				</h3>
			</div>
			<div class="modal-body" id="jgAddDiv">
			</div>
		</div>
	</div>
</div>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12 responsive">
			<%-- 表格开始 --%>
			<form class=""id="importForm" enctype="multipart/form-data">
				<input type="hidden" name="queryId" value="${queryId}"/>
				<div class="portlet-title">
					<div class="caption">目录类型 共<font color="red"> 10 </font>条记录 </div>
					<div class="clearfix fr">
						<a id="sample_editable_1_new" class="btn green" href="javascript:add()">
							<i class="icon-plus"></i> 增加目录类型
						</a>

					</div>

				</div>
			</form>
				<div class="portlet-body">
					<table class="table table-striped table-bordered table-hover dataTable table-set">
						<thead>
						<tr>
							<th width="30">排序</th>
							<th width="90">目录编码</th>
							<th >目录名称</th>
							<th width="90">操作</th>
						</thead>
						<tbody>
							<tr style="text-overflow:ellipsis;">
								<td>1</td>
								<td>010</td>
								<td><a href="javascript:edit()" class="">简历材料</a></td>
								<td>
									<a href="#" class="">修改</a>|
									<a href="#" class="">删除</a>
								</td>
							</tr>
							<tr style="text-overflow:ellipsis;">
								<td>2</td>
								<td>020</td>
								<td><a href="javascript:edit()" class="">自传材料</a></td>
								<td>
									<a href="#" class="">修改</a>|
									<a href="#" class="">删除</a>
								</td>
							</tr>
							<tr style="text-overflow:ellipsis;">
								<td>3</td>
								<td>030</td>
								<td><a href="javascript:edit()" class="">鉴定、考核、考察材料</a></td>
								<td>
									<a href="#" class="">修改</a>|
									<a href="#" class="">删除</a>
								</td>
							</tr>
							<tr style="text-overflow:ellipsis;">
								<td>4</td>
								<td>040</td>
								<td><a href="javascript:edit()" class="">学历、学位、学绩、培训和专业技术情况材料</a></td>
								<td>
									<a href="#" class="">修改</a>|
									<a href="#" class="">删除</a>
								</td>
							</tr>
							<tr style="text-overflow:ellipsis;">
								<td>5</td>
								<td>050</td>
								<td><a href="javascript:edit()" class="">政审材料</a></td>
								<td>
									<a href="#" class="">修改</a>|
									<a href="#" class="">删除</a>
								</td>
							</tr>
							<tr style="text-overflow:ellipsis;">
								<td>6</td>
								<td>060</td>
								<td><a href="javascript:edit()" class="">加入党团材料</a></td>
								<td>
									<a href="#" class="">修改</a>|
									<a href="#" class="">删除</a>
								</td>
							</tr>
							<tr style="text-overflow:ellipsis;">
								<td>7</td>
								<td>070</td>
								<td><a href="javascript:edit()" class="">奖励材料</a></td>
								<td>
									<a href="#" class="">修改</a>|
									<a href="#" class="">删除</a>
								</td>
							</tr>
							<tr style="text-overflow:ellipsis;">
								<td>8</td>
								<td>080</td>
								<td><a href="javascript:edit()" class="">处分材料</a></td>
								<td>
									<a href="#" class="">修改</a>|
									<a href="#" class="">删除</a>
								</td>
							</tr>
							<tr style="text-overflow:ellipsis;">
								<td>9</td>
								<td>090</td>
								<td><a href="javascript:edit()" class="">工资、任免、出国、代表大会等材料</a></td>
								<td>
									<a href="#" class="">修改</a>|
									<a href="#" class="">删除</a>
								</td>
							</tr>
							<tr style="text-overflow:ellipsis;">
								<td>10</td>
								<td>100</td>
								<td><a href="javascript:edit()" class="">其他供参考材料</a></td>
								<td>
									<a href="#" class="">修改</a>|
									<a href="#" class="">删除</a>
								</td>
							</tr>

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
	function edit() {

	}
	var add = function(){

		$.ajax({
			url:"${path}/zzb/app/console/daDemo/ajax/catalogTypeAdd",
			type : "post",
			data: {},
			headers:{
				OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
			},
			dataType : "html",
			success : function(html){
				$('#jgAddDiv').html(html);

				$('#jgModal').modal({
					keyboard: true
				});
			},
			error : function(){
				showTip("提示","出错了请联系管理员", 1500);
			}
		});
	}

</script>
</body>
</html>
