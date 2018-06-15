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
	<title>学习经历</title>
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
			<form class=""id="importForm" enctype="multipart/form-data">
				<input type="hidden" name="a01Id" id="a01Id" value="${a01Id}"/>
				<div class="portlet-title">
					<div class="clearfix fr">
						<a id="sample_editable_1_new" class="btn green" href="javascript:add()">
							<i class="icon-plus"></i> 添加
						</a>

					</div>

				</div>
			</form>
			<div class="portlet-body">
				<table class="table table-striped table-bordered table-hover dataTable table-set">
					<thead>
					<tr>
						<th width="20"></th>
						<th>学习类型</th>
						<th>入学日期</th>
						<th>毕业日期</th>
						<th>学历</th>
						<th>学位</th>
						<th width="150">学校（单位）及专业名称</th>
						<th>最后修改人</th>
						<th width="40">修改</th>
						<th width="40">删除</th>
					</tr>
					</thead>
					<tbody>
					<c:forEach items="${datas}" var="vo">
						<tr style="text-overflow:ellipsis;">
							<td><a href="javascript:edit('${vo.a0800}')">+</a></td>
							<td><c:if test="${vo.a0811=='1'}">全日制</c:if><c:if test="${vo.a0811=='0'}">在职</c:if></td>
							<td><c:out value="${vo.a0804}"></c:out></td>
							<td><c:out value="${vo.a0807}"></c:out></td>
							<td><c:out value="${vo.a0801A}"></c:out></td>
							<td><c:out value="${vo.a0901A}"></c:out></td>
							<td><c:out value="${vo.xxZy}"></c:out></td>
							<td><c:out value="${vo.lastUpdateBy}"></c:out></td>
							<td >
								<a href="javascript:edit('${vo.a0800}')" class="">修改</a>
							</td>
							<td >
								<a href="javascript:del('${vo.a0800}','${vo.xxZy}')" class="">删除</a>
							</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
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
	function pagehref (pageNum ,pageSize){
		<%--window.location.href ="${path}/zzb/app/console/gbmc/a01/list?b01Id=${b01Id}&mcid=${mcid}&pageNum="+pageNum+"&pageSize="+pageSize;--%>
		$.ajax({
			async:false,
			type:"POST",
			url:"${path}/zzb/jggl/b09/ajax/zwgl",
			dataType : "html",
			headers:{
				"OWASP_CSRFTOKEN":'${sessionScope.OWASP_CSRFTOKEN}'
			},
			data:{
				'b01Id':"${b01Id}",
				'pageNum':pageNum,
				'pageSize':pageSize
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

	function del(id, voname) {
		actionByConfirm1(voname, "${path}/zzb/gbgl/a08/del/" + id+"?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}", {}, function (data, status) {
			if (data.success == true) {
				$.ajax({
					url : "${path }/zzb/gbgl/a08/ajax/xxjl",
					type : "post",
					data : {"a01Id":"${a01Id}"},
					dataType : "html",
					headers:{
						OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
					},
					success : function(html){
						var view = $("#tab_show");
						view.html(html);
					},
					error : function(arg1, arg2, arg3){
						showTip("提示","职务管理加载失败");
					}
				});
			} else {
				showTip("提示", data.msg, 2000);
			}
		});
	}

	function edit(id) {
		$.ajax({
			async: false,
			type: "POST",
			url: "${path}/zzb/gbgl/a08/ajax/edit",
			dataType: "html",
			headers: {
				"OWASP_CSRFTOKEN": '${sessionScope.OWASP_CSRFTOKEN}'
			},
			data: {
				"id":id,
				"a01Id":"${a01Id}"
			},
			success: function (html) {
				var view = $("#tab_show");
				view.html(html);
			},
			error: function () {
				myLoading.hide();
				showTip("提示", "出错了,请检查网络!", 2000);
			}
		});
	}

	function add() {
		$.ajax({
			async: false,
			type: "POST",
			url: "${path}/zzb/gbgl/a08/ajax/add",
			dataType: "html",
			headers: {
				"OWASP_CSRFTOKEN": '${sessionScope.OWASP_CSRFTOKEN}'
			},
			data: {
				"a01Id":"${a01Id}"
			},
			success: function (html) {
				var view = $("#tab_show");
				view.html(html);
			},
			error: function () {
				myLoading.hide();
				showTip("提示", "出错了,请检查网络!", 2000);
			}
		});

	}
</script>
</body>
</html>
