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


<div id="viewImgModal" class="modal container hide fade" tabindex="-1" data-width="1010" data-height="600">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="btn btn-default" style="float: right;font-weight: bold;" data-dismiss="modal" onclick="hiddenViewImgModalForLiulan()"><i class='icon-remove-sign'></i> 关闭</button>
				<div class="btn-group" style="padding-bottom: 0px;float: right;right: 10px">
					<a class="btn green dropdown-toggle" data-toggle="dropdown" href="#">
						显示方式<i class="icon-angle-down"></i>
					</a>
					<ul class="dropdown-menu">
						<li >
							<a onclick="changeViewType('19')">小图(一行5张图)</a>
						</li>
						<li >
							<a onclick="changeViewType('32')">大图(一行3张图)</a>
						</li>
						<%--<li>--%>
						<%--<a onclick="changeViewType('50')">大图(一行2张图)</a>--%>
						<%--</li>--%>
						<li>
							<a onclick="changeViewType('99')">原始图(一行1张图)</a>
						</li>
					</ul>
				</div>
				<%--<button data-dismiss="modal" class="close" type="button" onclick="hiddenViewImgModal()"></button>--%>
				<h3 class="modal-title" id="title">
					“${a0101}”档案图片
				</h3>

			</div>
			<div class="modal-body" id="viewImgDiv" style="background-color: #f1f3f6;margin-top: 0px;padding-top: 0px;padding-bottom: 0px">
			</div>
		</div>
	</div>
</div>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12 responsive">
			<%-- 表格开始 --%>
				<div class="portlet-title">
					<div class="caption">本单位档案：共<font color="red"> ${pager.total } </font>人</div>
					<div class="clearfix fr">

						<a  class="btn green" href="javascript:gjcx()">
							高级查询
						</a>
						<a class="btn green" href="javascript:fileDown('list')">
							输出
						</a>

					</div>

				</div>
				<div class="clearfix">
					<div class="control-group">
							<form action="${path }/zzb/dzda/a38/list?queryType=listQuery&OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}" method="POST" id="searchForm" name="searchForm">
								<input type="hidden" name="pageNum" value="${pager.pageNum }" id="pageNum">
								<input type="hidden" name="pageSize" value="${pager.pageSize }" id="pageSize">
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
									<TD ><a href="javascript:viewImageMain('${vo.id}','${vo.a0101}')"><c:out value="${vo.a0101}"></c:out></a> </TD>
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

	})();
	
	function pagehref (pageNum ,pageSize){
		$("#pageNum").val(pageNum);
		$("#pageSize").val(pageSize);
		var a0101Query = $("#a0101Query").val();
		var gbztCodeQuery = $("#gbztCodeQuery").val();
		var gbztContentQuery = $("#gbztContentQuery").val();
		var daztCodeQuery = $("#daztCodeQuery").val();
		var daztContentQuery = $("#daztContentQuery").val();
		$.ajax({
			url : "${path }/zzb/dzda/dak/ajax/bdwdalist?queryType=listQuery",
			type : "post",
			data : {
				"a0101Query":a0101Query,
				"gbztCodeQuery":gbztCodeQuery,
				"gbztContentQuery":gbztContentQuery,
				"daztContentQuery":daztContentQuery,
				"daztCodeQuery":daztCodeQuery,
				"pageNum":pageNum,
				"pageSize":pageSize
			},
			dataType : "html",
			headers:{
				OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
			},
			success : function(html){
				var view = $("#tab_show");
				view.html(html);
			},
			error : function(arg1, arg2, arg3){
				showTip("提示","档案库列表加载失败");
			}
		});
//		$("#searchForm").submit();
	}

	function searchSubmit(){
		var a0101Query = $("#a0101Query").val();
		var gbztCodeQuery = $("#gbztCodeQuery").val();
		var gbztContentQuery = $("#gbztContentQuery").val();
		var daztCodeQuery = $("#daztCodeQuery").val();
		var daztContentQuery = $("#daztContentQuery").val();
		var data = $("#form1").serialize();
		$.ajax({
			url : "${path }/zzb/dzda/dak/ajax/bdwdalist?queryType=listQuery",
			type : "post",
			data :{
				"data":data,
				"a0101Query":a0101Query,
				"gbztCodeQuery":gbztCodeQuery,
				"gbztContentQuery":gbztContentQuery,
				"daztCodeQuery":daztCodeQuery,
				"daztContentQuery":daztContentQuery
			},
			dataType : "html",
			headers:{
				OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
			},
			success : function(html){
				var view = $("#tab_show");
				view.html(html);
			},
			error : function(arg1, arg2, arg3){
				showTip("提示","档案库列表加载失败");
			}
		});
		//		document.searchForm.submit();
	}

	var viewImageMain = function (a38Id,a0101) {
		var divHeight = $(window).height() -60;
		var divWidth = $(window).width() - 100;
		$("#title").text('"'+a0101+'"'+'档案图片');
		$('#viewImgModal').attr("data-height", divHeight);
		$('#viewImgModal').attr("data-width", divWidth);
		$.ajax({
			url: "${path}/zzb/dzda/mlcl/images/ajax/viewMain/"+a38Id,
			type: "post",
			data: {
				"a0101":a0101,
			},
			headers: {
				OWASP_CSRFTOKEN: "${sessionScope.OWASP_CSRFTOKEN}"
			},
			dataType: "html",
			headers:{
				OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
			},
			success: function (html) {
				$('#viewImgDiv').html(html);
				$('#viewImgModal').modal({backdrop: 'static', keyboard: false});
			},
			error: function () {
				showTip("提示", "出错了请联系管理员", 1500);
			}
		});
	}

	function hiddenViewImgModal(){//隐藏图片查看时 删除临时的解密图片
		$('#viewImgModal').modal('hide');
		$('#viewImgDiv').html("");
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
		$.ajax({
			url : "${path }/zzb/dzda/dak/ajax/bdwdalist?queryType=listQuery",
			type : "post",
			data : {
			},
			dataType : "html",
			headers:{
				OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
			},
			success : function(html){
				var view = $("#tab_show");
				view.html(html);
			},
			error : function(arg1, arg2, arg3){
				showTip("提示","档案库列表加载失败");
			}
		});
//		document.searchForm.submit();
	}



	var gjcx = function(){
		$.ajax({
			url:"${path}/zzb/dzda/dak/ajax/gjcx",
			type : "post",
			data: {},
			headers:{
				OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
			},
			dataType : "html",
			success : function(html){
				$('#gjcxModal').modal({
					keyboard: true
				});
				$('#gjcxDiv').html(html);
			},
			error : function(){
				showTip("提示","出错了请联系管理员", 1500);
			}
		});
	}
</script>
</body>
</html>
