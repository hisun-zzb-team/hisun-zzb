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
	<title>社会关系</title>
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
					<div class="caption">配偶信息列表</div>
					<div class="clearfix fr">
						<a id="sample_editable_1_new" class="btn green" href="javascript:add('1')">
							<i class="icon-plus"></i> 添加
						</a>

					</div>

				</div>
			</form>
			<div class="portlet-body">
				<table class="table table-striped table-bordered table-hover dataTable table-set">
					<thead>
					<tr>
						<th>姓名</th>
						<th>出生日期</th>
						<th>政治面貌</th>
						<th>工作单位及职务</th>
						<th>配偶状态</th>
						<th width="40">修改</th>
						<th width="40">删除</th>
					</tr>
					</thead>
					<tbody>
					<c:forEach items="${poxxDatas}" var="vo">
						<tr style="text-overflow:ellipsis;">
							<td><c:out value="${vo.a3601}"></c:out></td>
							<td><c:out value="${vo.a3607}"></c:out></td>
							<td><c:out value="${vo.a3627A}"></c:out></td>
							<td><c:out value="${vo.a3611}"></c:out></td>
							<td><c:out value=""></c:out></td>
							<td >
								<a href="javascript:edit('${vo.a3600}')" class="">修改</a>
							</td>
							<td >
								<a href="javascript:del('${vo.a3600}','${vo.a3601}')" class="">删除</a>
							</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<div class="span12 responsive">
			<%-- 表格开始 --%>
			<form class=""id="importForm" enctype="multipart/form-data">
				<div class="portlet-title">
					<div class="caption">其他家庭成员及主要社会关系信息列表</div>
					<div class="clearfix fr">
						<a id="sample_editable_1_newAdd" class="btn green" href="javascript:addQtgx()">
							<i class="icon-plus"></i> 添加
						</a>
					</div>
				</div>
			</form>
			<div class="portlet-body">
				<table class="table table-striped table-bordered table-hover dataTable table-set">
					<thead>
					<tr>
						<th>姓名</th>
						<th>出生日期</th>
						<th>政治面貌</th>
						<th>工作单位及职务</th>
						<th>类别</th>
						<th>排序</th>
						<th width="40">修改</th>
						<th width="40">删除</th>
					</tr>
					</thead>
					<tbody>
					<c:forEach items="${qtgxDatas}" var="vo">
						<tr style="text-overflow:ellipsis;">
							<td><c:out value="${vo.a3601}"></c:out></td>
							<td><c:out value="${vo.a3607}"></c:out></td>
							<td><c:out value="${vo.a3627A}"></c:out></td>
							<td><c:out value="${vo.a3611}"></c:out></td>
							<td><c:out value="${vo.a3604A}"></c:out></td>
							<td><c:out value="${vo.a3647}"></c:out></td>
							<td >
								<a href="javascript:editQtgx('${vo.a3600}')" class="">修改</a>
							</td>
							<td >
								<a href="javascript:del('${vo.a3600}','${vo.a3601}')" class="">删除</a>
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
			url:"${path}/zzb/gbgl/a02/ajax/xrzw",
			dataType : "html",
			headers:{
				"OWASP_CSRFTOKEN":'${sessionScope.OWASP_CSRFTOKEN}'
			},
			data:{
				'a01Id':"${a01Id}",
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
		actionByConfirm1(voname, "${path}/zzb/gbgl/a36/del/" + id+"?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}", {}, function (data, status) {
			if (data.success == true) {
                $.ajax({
                    url : "${path }/zzb/gbgl/a36/ajax/shgx",
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
                        showTip("提示","现任职务加载失败");
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
			url: "${path}/zzb/gbgl/a36/ajax/edit",
			dataType: "html",
			headers: {
				"OWASP_CSRFTOKEN": '${sessionScope.OWASP_CSRFTOKEN}'
			},
			data: {
				"id":id,
				"flag":"1",
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
			url: "${path}/zzb/gbgl/a36/ajax/addPoxx",
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
	function addQtgx() {
		$.ajax({
			async: false,
			type: "POST",
			url: "${path}/zzb/gbgl/a36/ajax/addQtgx",
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

function editQtgx(id) {
    $.ajax({
        async: false,
        type: "POST",
        url: "${path}/zzb/gbgl/a36/ajax/edit",
        dataType: "html",
        headers: {
            "OWASP_CSRFTOKEN": '${sessionScope.OWASP_CSRFTOKEN}'
        },
        data: {
            "id":id,
            "flag":"2",
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
