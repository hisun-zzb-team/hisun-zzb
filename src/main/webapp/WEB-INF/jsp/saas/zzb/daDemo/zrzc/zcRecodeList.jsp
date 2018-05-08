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
	<title>转出记录</title>
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
					<div class="caption">转出记录：共<font color="red"> 1 </font>人</div>
					<div class="clearfix fr">

					</div>

				</div>
			</form>
			<div class="clearfix">
				<div class="control-group">
					<div id="query" style="float: left;">
						<form action="${path }/zzb/app/console/asetA01/ajax/list" method="POST" id="searchForm" name="searchForm">

							姓名：<input type="text" class="m-wrap" name="xmQuery" id="xmQuery" value="${xmQuery}" style="width:80px;" />
							接收单位：<input type="text" class="m-wrap" name="xmQuery" id="xmQuery" value="${xmQuery}" style="width:80px;" />
							转出状态：
							<select class="select_form" tabindex="-1" name="noFileQuert" id="noFileQuert" style="width: 100px; margin-bottom: 0px;" >
								<option ></option>
								<option >待接收</option>
								<option >已接收</option>
								<option >拒绝接收</option>
								<option >主动收回</option>
							</select>
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

						<th width=70>姓名</th>
						<th width=40>性别</th>
						<th width=70>出生年月</th>
						<th>单位职务</th>
						<th width=70>干部状态</th>
						<th width=70>现职级时间</th>
						<th width=70>转出单位</th>
						<th width=70>转出人</th>
						<th width=70>转出时间</th>
						<th width=70>接收人</th>
						<th width=70>接收时间</th>
						<th width="90">转出状态</th>
					</thead>
					<tbody>
					<TR onmouseover="this.className='table_tr_mouse'" onmouseout="this.className=''">
						<TD style="CURSOR: hand; TEXT-ALIGN: center" ><a href="${path}/zzb/app/console/daDemo/zrBase">红叶专</a> </TD>
						<TD style="TEXT-ALIGN: center" width=40>男 </TD>
						<TD >1962.07.02 </TD>
						<TD title=州委书记>州委书记 </TD>
						<TD style="TEXT-ALIGN: center">现职 </TD>
						<TD style="TEXT-ALIGN: center">副局<BR>(2008.03) </TD>
						<TD style="TEXT-ALIGN: center">市纪委 </TD>
						<TD style="TEXT-ALIGN: center">张某</TD>
						<TD style="TEXT-ALIGN: center">2016.3.12 </TD>

						<TD style="TEXT-ALIGN: center">李某 </TD>
						<TD style="TEXT-ALIGN: center">2016.3.27 </TD>
						<TD style="TEXT-ALIGN: center">
							已接收
						</TD><!--<td  style="text-align:center;" width="5%">
									<a href="javascript:openFile('58F2F87EC0A800CC2DE33CCA0E6F9647','红叶专');"><font color="blue" style="cursor:hand">导入</font></a>
								</td>--></TR>

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
</script>
</body>
</html>
