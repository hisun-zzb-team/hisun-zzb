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
	<title>阅档申请</title>
	<style type="text/css">
		form {
			margin: 0 0 0px;
		}
	</style>
</head>
<body>
<div id="jgModal" class="modal container hide fade" tabindex="-1" data-width="1010" data-height="600">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button data-dismiss="modal" class="close"  type="button"></button>
				<h3 class="modal-title" id="title" >
					“红叶专”档案图片
				</h3>
			</div>
			<div class="modal-body" id="jgAddDiv" style="background-color: #f1f3f6;">
			</div>
		</div>
	</div>
</div>

<div id="addModal" class="modal container hide fade" tabindex="-1" data-width="600">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button data-dismiss="modal" class="close"  type="button"></button>
				<h3 class="modal-title" id="addTitle" >
					档案阅档申请
				</h3>
			</div>
			<div class="modal-body" id="addDiv">
			</div>
		</div>
	</div>
</div>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12 responsive">
			<%-- 表格开始 --%>
			<form class=""id="importForm" enctype="multipart/form-data">
				<input type="hidden" name="b01Id" value="${b01Id}"/>
				<div class="portlet-title">
					<div class="caption">阅档申请</div>
					<div class="clearfix fr">

						<a id="sample_editable_1_new" class="btn green" href="javascript:add()">
							阅档申请
						</a>


						<%--<span class="controllerClass btn green file_but" >--%>
							<%--<i class="icon-circle-arrow-up"></i>清空数据--%>
							<%--<input class="file_progress" type="file" name="attachFile" id="btn-browseTemplate">--%>
						<%--</span>--%>
					</div>

				</div>
			</form>
				<div class="clearfix">
					<div class="control-group">
						<div id="query" style="float: left;">
							<form action="${path }/zzb/app/console/asetA01/ajax/list" method="POST" id="searchForm" name="searchForm">
								<input type="hidden" id="b01Id" name="b01Id" value="${b01Id}"/>
								<input type="hidden" name="OWASP_CSRFTOKEN" value="${sessionScope.OWASP_CSRFTOKEN}"/>
								<input type="hidden" name="pageNum" value="${pager.pageNum }" id="pageNum">
								<input type="hidden" name="pageSize" value="${pager.pageSize }" id="pageSize">
								姓名：<input type="text" class="m-wrap" name="xmQuery" id="xmQuery" value="${xmQuery}" style="width: 80px;" />
								查阅申请内容：<input type="text" class="m-wrap" name="xmQuery" id="xmQuery" value="${xmQuery}" style="width: 80px;" />
								档案管理单位：<input type="text" class="m-wrap" name="xmQuery" id="xmQuery" value="${xmQuery}" style="width:80px;" />


								<button type="button" class="btn Short_but" onclick="searchSubmit()">查询</button>
								<button type="button" class="btn Short_but" onclick="clearData()">清空</button>
							</form>
						</div>
					</div>

				</div>
				<div class="portlet-body">
					<table class="table table-striped table-bordered table-hover dataTable table-set">
						<thead>

						<TR height=28>
							<th width=70>档案名称</th>
							<th width=150>单位职务</th>
							<th width=100>查阅申请内容</th>
							<th  width=90>档案管理单位</th>
							<th>查阅情况</th>
							<th width=70>申请时间</th>
							<th width=70>阅档状态</th>
							<th width=70>申请情况</th>
							<th width="60">操作</th>
						</thead>
						<tbody>
						<TR onmouseover="this.className='table_tr_mouse'" onmouseout="this.className=''">
							<TD style="CURSOR: hand; TEXT-ALIGN: center" >红叶专</TD>
							<TD style="TEXT-ALIGN: center" width=40>广州市监察局第三纪检监察室副局级主任 </TD>
							<TD >个人履历表 </TD>
							<TD >市委组织部</TD>
							<TD ></TD>
							<TD style="TEXT-ALIGN: center">2017.9.19 </TD>
							<TD style="TEXT-ALIGN: center">同意阅档</TD>
							<TD style="TEXT-ALIGN: center"><a href="javascript:view()">浏览</a></TD>
							<TD style="TEXT-ALIGN: center">
								<a href="#">删除 </a>
							</TD>
						</TR>

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

		$("#btn-browseTemplate").bind("change",function(evt){
			if($(this).val()){
				ajaxSubmit();
			}
			$(this).val('');
		});

	})();

	var add = function(){
		$.ajax({
			url:"${path}/zzb/app/console/daDemo/ajax/addApplyDa",
			type : "post",
			data: {},
			headers:{
				OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
			},
			dataType : "html",
			success : function(html){
				$('#addDiv').html(html);

				$('#addModal').modal({
					keyboard: true
				});
			},
			error : function(){
				showTip("提示","出错了请联系管理员", 1500);
			}
		});
	}

	var view = function(){
		var divHeight = $(window).height()-100;
		$('#jgModal').attr("data-height",divHeight);
		$.ajax({
			url:"${path}/zzb/app/console/daDemo/ajax/viewImgManage",
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
	function pagehref (pageNum ,pageSize){
		<%--window.location.href ="${path}/zzb/app/console/gbmc/a01/list?b01Id=${b01Id}&mcid=${mcid}&pageNum="+pageNum+"&pageSize="+pageSize;--%>
		$.ajax({
			async:false,
			type:"POST",
			url:"${path}/zzb/app/console/asetA01/ajax/list",
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
				$("#catalogList").html(html);
//				$("#treeId").val(nodeId);
			},
			error : function(){
				myLoading.hide();
				showTip("提示","出错了,请检查网络!",2000);
			}
		});

	}

	function searchSubmit(){
		$.ajax({
			async:false,
			type:"POST",
			url:"${path}/zzb/app/console/asetA01/ajax/list",
			dataType : "html",
			headers:{
				"OWASP_CSRFTOKEN":'${sessionScope.OWASP_CSRFTOKEN}'
			},
			data : $("#searchForm").serialize(),
			success:function(html){
				$("#catalogList").html(html);
//				$("#treeId").val(nodeId);
			},
			error : function(){
				myLoading.hide();
				showTip("提示","出错了,请检查网络!",2000);
			}
		});
	}



	var del = function(id,itemName){
		actionByConfirm1(itemName, "${path}/zzb/app/console/asetA01/delete/" + id,{} ,function(data,status){
			if (data.success == true) {
				showTip("提示","删除成功", 2000);
				setTimeout(function(){window.location.href = "${path}/zzb/app/console/asetA01/list?b01Id=${b01Id}&mcid=${mcid}"},2000);
			}else{
				showTip("提示", data.message, 2000);
			}
		});
	};
	function uploadFile(fileName){
		document.getElementById("btn-"+fileName).click();
	}
	function clearData(){
		$("#xmQuery").val('');
		$.ajax({
			async:false,
			type:"POST",
			url:"${path}/zzb/app/console/asetA01/ajax/list",
			dataType : "html",
			headers:{
				"OWASP_CSRFTOKEN":'${sessionScope.OWASP_CSRFTOKEN}'
			},
			data : $("#searchForm").serialize(),
			success:function(html){
				$("#catalogList").html(html);
//				$("#treeId").val(nodeId);
			},
			error : function(){
				myLoading.hide();
				showTip("提示","出错了,请检查网络!",2000);
			}
		});
	}
	function exportGbrmsp(){
		$.cloudAjax({
			path : '${path}',
			url : "${path }/zzb/app/console/asetA01/ajax/exportGbrmsp",
			type : "post",
			data : $("#form1").serialize(),
			dataType : "json",
			success : function(data){
				if(data.success == true){
					showTip("提示","生成干部任免审批表成功!",2000);
					//setTimeout(function(){window.location.href = "${path}/zzb/app/console/bwh/"},2000);
				}else{
					showTip("提示", "生成干部任免审批表失败!", 2000);
				}
			},
			error : function(){
				showTip("提示","出错了请联系管理员",2000);
			}
		});
	}
	function openGzzzb(){
		var url ="http://localhost:8080/GZZZB/la/index.jsp?showFlag=init&moduleCode=LA_APPOINT_STUFF";
		window.open(url);
	}
</script>
</body>
</html>
