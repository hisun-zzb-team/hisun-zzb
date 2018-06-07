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
	<title>分类列表</title>
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
				<input type="hidden" name="queryId" value="${queryId}"/>
				<div class="portlet-title">
					<div class="caption">${flmc}</div>
					<div class="clearfix fr">
						<a id="sample_editable_1_new" class="btn green" href="javascript:add()">
							<i class="icon-plus"></i> 添加
						</a>

					</div>

				</div>
			</form>
			<div class="clearfix">
				<div class="control-group">
					<div id="query" style="float: left;">
						<form action="${path }/zzb/app/console/bset/ajax/list" method="POST" id="searchForm" name="searchForm">
							<input type="hidden" name="OWASP_CSRFTOKEN" value="${sessionScope.OWASP_CSRFTOKEN}"/>
							<input type="hidden" name="pageNum" value="${pager.pageNum }" id="pageNum">
							<input type="hidden" name="pageSize" value="${pager.pageSize }" id="pageSize">
							分类名称：<input type="text" class="m-wrap" name="flQuery" id="flQuery" value="${flQuery}" style="width: 100px;" />
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
							<th>分类名称</th>
							<th>排序</th>
							<%--<th width="100">专业技<br>术职务--%>
							<%--</th>--%>
							<%--<th width="65">任现职<br>务时间--%>
							<%--</th>--%>
							<%--<th width="100">任现职<br>级时间--%>
							<%--</th>--%>
							<th width="40">操作</th>
						</tr>
						</thead>
						<tbody>
						<c:forEach items="${pager.datas}" var="vo">
							<tr style="text-overflow:ellipsis;">
								<%--<td title="${vo.xm}"><a href="${path}/zzb/app/console/appGbcxA01/view?id=${vo.id }"><c:out value="${vo.xm}"></c:out></a></td>--%>
								<%--<td><a href="${path }/zzb/app/console/bset/addOrEditManage?dataType=0&parentId=${queryId}"><c:out value="${vo.b0101}"></c:out></a></td>--%>
								<%--&lt;%&ndash;<td><c:out value="${vo.b0101}"></c:out></td>&ndash;%&gt;--%>
								<%--<td>&nbsp;</td><td>&nbsp;</td>--%>
								<td><a href="javascript:edit('${vo.id}')" class="">${vo.fl}</a></td>
								<td><c:out value="${vo.px}"></c:out></td>
								<%--<td><c:out value="${vo.xrzwsj}"></c:out></td>--%>
								<%--<td title="${vo.xrzjsj}"><c:out value="${vo.xrzjsj}"></c:out></td>--%>
								<td class="Left_alignment">
									<a href="javascript:del('${vo.id}','${vo.fl}')" class="">删除</a>
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
<script type="text/javascript" src="${path }/js/common/loading.js"></script>
<script type="text/javascript">
var myLoading = new MyLoading("${path}",{zindex : 11111});
	function pagehref (pageNum ,pageSize){
		<%--window.location.href ="${path}/zzb/app/console/gbmc/a01/list?b01Id=${b01Id}&mcid=${mcid}&pageNum="+pageNum+"&pageSize="+pageSize;--%>
		$.ajax({
			async:false,
			type:"POST",
			url:"${path}/zzb/app/console/bset/ajax/list",
			dataType : "html",
			headers:{
				"OWASP_CSRFTOKEN":'${sessionScope.OWASP_CSRFTOKEN}'
			},
			data:{
				'queryId':"${queryId}",
				'pageNum':pageNum,
				'pageSize':pageSize
			},
			success:function(html){
				$("#rightList").html(html);
//				$("#treeId").val(nodeId);
			},
			error : function(){
				myLoading.hide();
				showTip("提示","出错了,请检查网络!",2000);
			}
		});

	}

	function del(id, voname) {
		actionByConfirm1(voname, "${path}/zzb/jggl/fl/delFl/" + id+"?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}", {}, function (data, status) {
			if (data.success == true) {
				window.location.href = "${path }/zzb/jggl/fl/flManage?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}";
			} else {
				showTip("提示", data.msg, 2000);
			}
		});
	}
	function clearData() {
		$("#flQuery").val("");
	}

	function edit(id) {
		$.ajax({
			async: false,
			type: "POST",
			url: "${path}/zzb/jggl/fl/ajax/editFl",
			dataType: "html",
			headers: {
				"OWASP_CSRFTOKEN": '${sessionScope.OWASP_CSRFTOKEN}'
			},
			data: {
				"id":id
			},
			success: function (html) {
				$("#rightList").html(html);
			},
			error: function () {
				myLoading.hide();
				showTip("提示", "出错了,请检查网络!", 2000);
			}
		});
	}

	function searchSubmit(){
		var fl = $("#flQuery").val();
		var bflId = $("#bflId").val();
		var parentBFlId = $("#parentBFlId").val();
		$.ajax({
			async:false,
			type:"POST",
			url:"${path}/zzb/jggl/fl/ajax/list",
			dataType : "html",
			headers:{
				"OWASP_CSRFTOKEN":'${sessionScope.OWASP_CSRFTOKEN}'
			},
			data : {
				"flQuery":fl,
				"bflId":bflId,
				"parentBFlId":parentBFlId,
				"key":"1"
			},
			success:function(html){
				$("#rightList").html(html);
			},
			error : function(){
				myLoading.hide();
				showTip("提示","出错了,请检查网络!",2000);
			}
		});
	}

	function add() {
		var bflId = $("#bflId").val();
		var parentBFlId = $("#parentBFlId").val();
		var fl = $("#fl").val();
		$.ajax({
			async: false,
			type: "POST",
			url: "${path}/zzb/jggl/fl/ajax/addFl",
			dataType: "html",
			headers: {
				"OWASP_CSRFTOKEN": '${sessionScope.OWASP_CSRFTOKEN}'
			},
			data: {
				"bflId":bflId,
				"parentBFlId":parentBFlId,
				"fl":fl
			},
			success: function (html) {
				$("#rightList").html(html);
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
