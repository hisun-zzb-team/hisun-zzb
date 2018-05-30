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
	<title>电子档案系统</title>
	<style type="text/css">
		form {
			margin: 0 0 0px;
		}
	</style>
</head>
<body>
<div id="gjcxModal" class="modal container hide fade" tabindex="-1" data-width="85%">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button data-dismiss="modal" class="close"  type="button"></button>
				<h3 class="modal-title" id="gjcxTitle" >
					高级查询条件设置
				</h3>
			</div>
			<div class="modal-body" id="gjcxDiv">
			</div>
		</div>
	</div>
</div>
<div id="a38Modal" class="modal container hide fade" tabindex="-1" data-width="600">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button data-dismiss="modal" class="close" id="closeA38" type="button"></button>
				<h3 class="modal-title" id="a38Title" >
					导入文件错误列表
				</h3>
			</div>
			<div class="modal-body" id="a38Div">
			</div>
		</div>
	</div>
</div>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12 responsive">
			<%-- 表格开始 --%>
			<div class="portlet-title">
				<div class="caption">档案管理：共<font color="red"> ${pager.total } </font>人</div>
				<div class="clearfix fr">

					<div class="btn-group" style="padding-bottom: 0px">
						<a class="btn green dropdown-toggle" data-toggle="dropdown" href="#">
							添加<i class="icon-angle-down"></i>
						</a>
						<ul class="dropdown-menu">
							<li >
								<a href="${path}/zzb/dzda/a38/add?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}">添加档案</a>
							</li>
							<li >
								<a href="${path}/zzb/dzda/e01z1/plAddMlcl?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}">批量添加材料</a>
							</li>
						</ul>
					</div>

					<a  class="btn green" href="javascript:gjcx()">
						高级查询
					</a>
					<div class="btn-group" style="padding-bottom: 0px">
						<a class="btn green dropdown-toggle" data-toggle="dropdown" href="#">
							导入<i class="icon-angle-down"></i>
						</a>
						<ul class="dropdown-menu">
							<li >
								<a onclick="uploadZbdaFile()">导入excel</a>
							</li>
							<%--<li >--%>
								<%--<a onclick="unloadFile()">导入Lrmx</a>--%>
							<%--</li>--%>
							<%--<li >--%>
								<%--<a onclick="unloadFile()">导入Lrm</a>--%>
							<%--</li>--%>
							<%--<li >--%>
								<%--<a onclick="unloadFile()">导入HZB</a>--%>
							<%--</li>--%>
							<%--<li >--%>
								<%--<a onclick="unloadFile()">导入7z</a>--%>
							<%--</li>--%>
						</ul>
					</div>
					<input type="file" style="display: none" name="unloadFile" id="btn-unloadFile"/>
					<a class="btn green" href="javascript:download()">
						导出
					</a>
					<form action="" id="uploadForm">
						<input type="file" style="display: none" name="zbdaFile" id="zbdaFile" accept = '.csv,
                 application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel'/>
						<input type="hidden" id="a38Id" name="a38Id" value="${a38Id}">
					</form>

				</div>

			</div>
			<div class="clearfix">
				<div class="control-group">
					<form action="${path }/zzb/dzda/a38/list?queryType=listQuery&OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}" method="POST" id="searchForm" name="searchForm">
						<input type="hidden" name="pageNum" value="${pager.pageNum }" id="pageNum">
						<input type="hidden" name="pageSize" value="${pager.pageSize }" id="pageSize">
						<div style=" float:left;margin-top:4px">档案编号:</div>
						<div style=" float:left;">
							<input type="text" class="m-wrap" name="dabhQuery" id="dabhQuery" value="${dabhQuery}" style="width: 80px;" />
						</div>
						<div style=" float:left;margin-top:4px">&nbsp;扫描序号:</div>
						<div style=" float:left;">
							<input type="text" class="m-wrap" name="smxhQuery" id="smxhQuery" value="${smxhQuery}" style="width: 80px;" />
						</div>
						<div style=" float:left;margin-top:4px">&nbsp;姓名:</div>
						<div style=" float:left;">
							<input type="text" class="m-wrap" name="a0101Query" id="a0101Query" value="${a0101Query}" style="width:80px;" />
						</div>
						<div style=" float:left;margin-top:4px">&nbsp;干部状态:</div>
						<div style="float:left;width: 160px;">
							<Tree:tree id="gbztCodeQuery" valueName="gbztContentQuery"  selectClass="span12 m-wrap" height="30px" treeUrl="${path}/api/dictionary/tree?typeCode=SAN_GBZT" token="${sessionScope.OWASP_CSRFTOKEN}"
									   submitType="get" dataType="json" isSearch="false" radioOrCheckbox="checkbox" checkedByTitle="true" isSelectTree="true" defaultkeys="${gbztCodeQuery}" defaultvalues="${gbztContentQuery}"/>
						</div>
						<div style=" float:left;margin-top:4px">&nbsp;档案状态:</div>
						<div style="float:left;width: 160px;">
							<Tree:tree id="daztCodeQuery" valueName="daztContentQuery"  selectClass="span12 m-wrap" height="30px" treeUrl="${path}/api/dictionary/tree?typeCode=SAN_DAZT" token="${sessionScope.OWASP_CSRFTOKEN}"
									   submitType="get" dataType="json" isSearch="false" radioOrCheckbox="checkbox" checkedByTitle="true" isSelectTree="true" defaultkeys="${daztCodeQuery}" defaultvalues="${daztContentQuery}"/>

						</div>
						<div style="float:left">
							&nbsp;&nbsp;<button type="button" class="btn Short_but" onclick="searchSubmit()">查询</button>
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
						<th width=70  style="text-align: center">接收日期</th>
						<th width=70  style="text-align: center">干部状态</th>
						<th width=70  style="text-align: center">现职级时间</th>
						<th width="50">修改者</th>
						<th width=70>修改时间</th>
					</thead>
					<tbody>
					<c:forEach items="${pager.datas}" var="vo">
						<tr style="text-overflow:ellipsis;">
							<TD  style="text-align: center"><c:out value="${vo.dabh}"></c:out></TD>
							<TD  style="text-align: center"><c:out value="${vo.smxh}"></c:out></TD>
							<TD >
								<a href="${path}/zzb/dzda/a38/editManage?id=${vo.id }&OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}"><c:out value="${vo.a0101}"></c:out></a>
							</TD>
							<TD  style="text-align: center"><c:out value="${vo.a0104Content}"></c:out></TD>
							<TD ><c:out value="${vo.a0107}"></c:out></TD>
							<TD><c:out value="${vo.a0157}"></c:out></TD>
							<TD  style="text-align: center"><c:out value="${vo.a3801}"></c:out></TD>
							<TD  style="text-align: center"><c:out value="${vo.gbztContent}"></c:out></TD>
							<TD style="text-align: center"><c:out value="${vo.dutyLevelValue}"></c:out><br><c:out value="${vo.dutyLevelTimeBase}"></c:out></TD>
							<TD  width=40><c:out value="${vo.updateUserNameByShow}"></c:out></TD>
							<TD ><fmt:formatDate value="${vo.updateDateByShow}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></TD>
						</TR>
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

	function uploadZbdaFile(){
		document.getElementById("zbdaFile").click();
	}

	$("#zbdaFile").on("change", function (evt) {
		var uploadFile = document.getElementById("zbdaFile");
		var file = uploadFile.files[0];
		if (uploadFile.files.length > 0) {
			var name = uploadFile.files[0].name
			var arr = name.split(".");
			if (arr.length < 2 || !(arr[arr.length - 1] == "csv" || arr[arr.length - 1] == "xlsx" || arr[arr.length - 1] == "xls")) {
				showTip("提示", "请上传Excel文件", 2000);
				return;
			}
		}
		$("#uploadForm").ajaxSubmit({
			type:"POST",
			url:"${path}/zzb/dzda/a38/uploadFile",
			dataType : "json",
			enctype : "multipart/form-data",
			headers:{
				"OWASP_CSRFTOKEN":'${sessionScope.OWASP_CSRFTOKEN}'
			},
			success:function(data){
				if(data.isWrong){
					$.ajax({
						url:"${path}/zzb/dzda/a38/ajax/cwjl",
						type : "post",
						data: {},
						headers:{
							OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
						},
						dataType : "html",
						success : function(html){
							$('#a38Div').html(html);

							$('#a38Modal').modal({backdrop: 'static', keyboard: false});
						},
						error : function(){
							showTip("提示","出错了请联系管理员", 1500);
						}
					});
				}else {
					showTip("提示","上传成功!",2000);
					$.ajax({
						async:false,
						type:"POST",
						url:"${path }/zzb/dzda/a52/ajax/list",
						dataType : "html",
						headers:{
							"OWASP_CSRFTOKEN":'${sessionScope.OWASP_CSRFTOKEN}'
						},
						data:{
							'a38Id':"${a38Id}"
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
			},
			error : function(){
				showTip("提示","上传失败!",2000);
			}
		});
	});


	function pagehref (pageNum ,pageSize){
		$("#pageNum").val(pageNum);
		$("#pageSize").val(pageSize);
		$("#searchForm").submit();
	}

	function searchSubmit(){
		document.searchForm.submit();
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

	function uploadFile(fileName){
		document.getElementById("btn-"+fileName).click();
	}
	function clearData(){
		$("#dabhQuery").val('');
		$("#smxhQuery").val('');
		$("#a0101Query").val('');
		$("#gbztCodeQuery").val('');
		$("#daztCodeQuery").val('');
		$("#gbztContentQuery").val('');
		$("#daztContentQuery").val('');
		document.searchForm.submit();
	}

	var gjcx = function(){
		$.ajax({
			url:"${path}/zzb/dzda/a38/ajax/gjcx",
			type : "post",
			data: {},
			headers:{
				OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
			},
			dataType : "html",
			success : function(html){
				$('#gjcxModal').modal({backdrop: 'static', keyboard: false});
				$('#gjcxDiv').html(html);
			},
			error : function(){
				showTip("提示","出错了请联系管理员", 1500);
			}
		});
	}

	function download() {
		window.open("${path}/zzb/dzda/a38/dagl/download?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}");
	}

	$("#closeA38").on("click",function(){
		pagehref("","");
	});
</script>
</body>
</html>
