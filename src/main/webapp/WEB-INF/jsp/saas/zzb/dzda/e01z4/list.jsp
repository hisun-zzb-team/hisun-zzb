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
	<title>欠缺材料</title>
	<style type="text/css">
		form {
			margin: 0 0 0px;
		}
	</style>
</head>
<body>
<input type="hidden" name="a38Id" id="a38Id" value="${a38Id}"/>
<div class="container-fluid" >
	<div class="row-fluid">
		<div class="span12 responsive" id="e01z1Table">
			<%-- 表格开始 --%>
				<div class="portlet-title">
					<div class="clearfix fr">

						<a id="sample_editable_1_new" class="btn green" href="javascript:addQQcl()">
							添加
						</a>

					</div>

				</div>

				<div class="portlet-body">
					<table class="table table-striped table-bordered table-hover dataTable table-set">
						<thead>
						<tr>
							<th  width="150">材料名称</th>
							<th width="90">材料时间</th>
							<th width="250">材料大类</th>
							<th>材料类型</th>
							<th width="150">备注</th>
							<th width="90">操作</th>
						</tr>
						</thead>
						<tbody>
						<c:forEach items="${pager.datas}" var="vo">
							<tr style="text-overflow:ellipsis;">
								<td><a href="javascript:edit('${vo.id}')" class="">${vo.e01Z401}</a></td>
								<td>${vo.fileTime} </td>
								<td>${vo.e01Z401A}</td>
								<td>${vo.fileTypeName}</td>
								<td st>${vo.remark}</td>
								<td>
									<a href="javascript:edit('${vo.id}')" class="">修改</a>|
									<a href="javascript:del('${vo.id}','${vo.e01Z401}')" class="">删除</a>
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
<script type="text/javascript">
	(function(){
		App.init();


	})();

	function pagehref (pageNum ,pageSize){
		<%--window.location.href ="${path}/zzb/app/console/gbmc/a01/list?b01Id=${b01Id}&mcid=${mcid}&pageNum="+pageNum+"&pageSize="+pageSize;--%>
		$.ajax({
			async:false,
			type:"POST",
			url:"${path }/zzb/dzda/e01z4/ajax/list",
			dataType : "html",
			headers:{
				"OWASP_CSRFTOKEN":'${sessionScope.OWASP_CSRFTOKEN}'
			},
			data:{
				"a38Id":"${a38Id}",
				"pageNum":pageNum,
				"pageSize":pageSize
			},
			success:function(html){
				var view = $("#tab_show");
				view.html(html);
			},
			error : function(){
				myLoading.hide();
				showTip("提示","出错了,请检查网络!",2000);
			}
		});

	}

	var addQQcl = function(){
		var a38Id = $('#a38Id').val();
		$.ajax({
			url:"${path}/zzb/dzda/e01z4/ajax/add",
			type : "post",
			data: {
				"a38Id":a38Id
			},
			headers:{
				OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
			},
			dataType : "html",
			success:function(html){
				var view = $("#tab_show");
				view.html(html);
			},
			error : function(){
				showTip("提示","出错了请联系管理员", 1500);
			}
		});
	}
	function edit(id){
		var a38Id = $('#a38Id').val();
		$.ajax({
			url:"${path}/zzb/dzda/e01z4/ajax/edit",
			type : "post",
			data: {
				"id":id,
				"a38Id":a38Id
			},
			headers:{
				OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
			},
			dataType : "html",
			success : function(html){
				var view = $("#tab_show");
				view.html(html);
			},
			error : function(){
				showTip("提示","出错了请联系管理员", 1500);
			}
		});
	}

	function del(id, voname) {
		var a38Id = $("#a38Id").val();
		actionByConfirm1(voname, "${path}/zzb/dzda/e01z4/delete/" + id, {}, function (data, status) {
			if (data.success == true) {
				showTip("提示", "成功删除！", 2000);
				$.ajax({
					url: "${path }/zzb/dzda/e01z4/ajax/list",
					type: 'POST',
					dataType: "html",
					data: {
						"a38Id": a38Id
					},
					headers: {
						"OWASP_CSRFTOKEN": "${sessionScope.OWASP_CSRFTOKEN}"
					},
					success: function (html) {
						var view = $("#tab_show");
						view.html(html);
					},
					error: function () {
						showTip("提示", "删除失败!", 2000);
					}
				});
			} else {
				showTip("提示", data.msg, 2000);
			}
		});
	}
</script>
</body>
</html>
