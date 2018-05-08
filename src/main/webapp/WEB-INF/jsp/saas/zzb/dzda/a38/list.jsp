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
	<title>电子档案系统</title>
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
				<input type="hidden" name="b01Id" value="${b01Id}"/>
				<div class="portlet-title">
					<div class="caption">档案管理：共<font color="red"> ${pager.total } </font>人</div>
					<div class="clearfix fr">

						<a id="sample_editable_1_new" class="btn green" href="${path}/zzb/dzda/a38/add?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}">
							<i class="icon-plus"></i>添加
						</a>
						<div class="btn-group" style="padding-bottom: 0px">
							<a class="btn green dropdown-toggle" data-toggle="dropdown" href="#">
								导入<i class="icon-angle-down"></i>
							</a>
							<ul class="dropdown-menu">

								<li >
									<a onclick="unloadFile()">导入Lrmx</a>
								</li>
								<li >
									<a onclick="unloadFile()">导入Lrm</a>
								</li>
								<li >
									<a onclick="unloadFile()">导入HZB</a>
								</li>
								<li >
									<a onclick="unloadFile()">导入7z</a>
								</li>
							</ul>
						</div>
						<a  class="btn green" href="#">
							高级查询
						</a>

						<input type="file" style="display: none" name="unloadFile" id="btn-unloadFile"/>

						<a class="btn green" href="javascript:fileDown('list')">
							输出
						</a>

					</div>

				</div>
			</form>
				<div class="clearfix">
					<div class="control-group">
							<form action="${path }/zzb/app/console/asetA01/ajax/list" method="POST" id="searchForm" name="searchForm">
								<input type="hidden" name="pageNum" value="${pager.pageNum }" id="pageNum">
								<input type="hidden" name="pageSize" value="${pager.pageSize }" id="pageSize">
								<div style=" float:left;text-align: center">
									档案编号：<input type="text" class="m-wrap" name="dabhQuery" id="dabhQuery" value="${dabhQuery}" style="width: 80px;" />
									扫描序号：<input type="text" class="m-wrap" name="smxhQuery" id="smxhQuery" value="${smxhQuery}" style="width: 80px;" />
									姓名：<input type="text" class="m-wrap" name="a0101Query" id="a0101Query" value="${a0101Query}" style="width:80px;" />
									干部状态：
								</div>
								<div style="float:left">
									<SelectTag:SelectTag id="gbztCodeQuery" width="200px" moreSelectAll="false" token="${sessionScope.OWASP_CSRFTOKEN}"
													 radioOrCheckbox="checkbox" moreSelectSearch="no" selectUrl="${path }/api/dictionary/select?typeCode=ZB14-1994/RZZT" defaultkeys="${vo.gbztCodeQuery}"/>
								</div>
								<div style=" float:left">档案状态：</div>
								<div style="float:left">
									<SelectTag:SelectTag id="daztCodeQuery" width="200px" moreSelectAll="false" token="${sessionScope.OWASP_CSRFTOKEN}"
													 radioOrCheckbox="checkbox" moreSelectSearch="no" selectUrl="${path }/api/dictionary/select?typeCode=ZB14-1994/RZZT" defaultkeys="${vo.daztCodeQuery}"/>
								</div>
								<div style="float:left">
									<button type="button" class="btn Short_but" onclick="searchSubmit()">查询</button>
								<button type="button" class="btn Short_but" onclick="clearData()">清空</button>
									</div>
							</form>
					</div>

				</div>
				<div class="portlet-body">
					<table class="table table-striped table-bordered table-hover dataTable table-set">
						<thead>

						<TR height=28>
							<th width=60>档案编号</th>
							<th width=60>扫描序号</th>
							<th width=70>姓名</th>
							<th width=40>性别</th>
							<th width=70>出生年月</th>
							<th>单位职务</th>
							<th width=70>接收日期</th>
							<th width=70>干部状态</th>
							<th width=70>现职级时间</th>
							<th width="5%">修改者</th>
							<th width=80>修改时间</th>
						</thead>
						<tbody>
							<tr style="text-overflow:ellipsis;">
								<TD></TD>
								<TD>0002 </TD>
								<TD ><a href="${path}/zzb/dzda/a38/editManage?id=${vo.id }&shpcPageNum=${pager.pageNum}">红叶专</a> </TD>
								<TD>男 </TD>
								<TD >1962.07.02 </TD>
								<TD>州委书记 </TD>
								<TD >2015.03.05 </TD>
								<TD >现职 </TD>
								<TD >副局<BR>(2008.03) </TD>
								<TD  width=40>杜政 </TD>
								<TD >2018.03.29<br>16:02 </TD>
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
	function unloadFile(){
		document.getElementById("btn-unloadFile").click();
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


	var view = function(id){
		$.ajax({
			async:false,
			type:"POST",
			url:"${path}/zzb/app/console/asetA01/ajax/view",
			dataType : "html",
			headers:{
				"OWASP_CSRFTOKEN":'${sessionScope.OWASP_CSRFTOKEN}'
			},
			data:{
				'id':id,
				'b01Id':"${b01Id}"
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
	function fileDown(type) {
		window.open("${path }/zzb/app/console/daDemo/ajax/down?type="+type);
	}
</script>
</body>
</html>
