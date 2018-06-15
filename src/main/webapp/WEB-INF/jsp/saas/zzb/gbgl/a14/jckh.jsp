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
			<div class="portlet-body">
				<table class="table table-striped table-bordered table-hover dataTable table-set">
					<tbody>
						<tr>
							<td width="10%">奖励情况</td>
							<td width="5%"><a href="javascript:edit('j')">修改</a></td>
							<td width="85%"><textarea rows="10" cols="" style="margin: 0px 0px 10px; width: 863px; height: 145px;" readonly>${jlqkData}</textarea></td>
						</tr>
						<tr>
							<td width="10%">惩戒情况</td>
							<td width="5%"><a href="javascript:edit('c')">修改</a></td>
							<td width="85%"><textarea rows="10" cols="" style="margin: 0px 0px 10px; width: 863px; height: 145px;" readonly>${cjqkData}</textarea></td>
						</tr>
						<tr>
							<td width="10%">年度考核</td>
							<td width="5%"><a href="javascript:edit('n')">修改</a></td>
							<td width="85%"><textarea rows="10" cols="" style="margin: 0px 0px 10px; width: 863px; height: 145px;" readonly>${ndkhData}</textarea></td>
						</tr>
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

	function edit(type) {
		$.ajax({
			async: false,
			type: "POST",
			url: "${path}/zzb/gbgl/a14/ajax/edit",
			dataType: "html",
			headers: {
				"OWASP_CSRFTOKEN": '${sessionScope.OWASP_CSRFTOKEN}'
			},
			data: {
				"type":type,
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
