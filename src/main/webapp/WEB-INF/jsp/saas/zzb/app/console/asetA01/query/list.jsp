<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/inc/taglib.jsp" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link href="${path}/css/common/common.css" rel="stylesheet" type="text/css"/>
<!-- BEGIN PAGE LEVEL STYLES -->
<link rel="stylesheet" href="${path }/css/DT_bootstrap.css" />
<!-- END PAGE LEVEL STYLES -->
<title>${queryName}<c:if test="${empty queryId}">干部列表</c:if><c:if test="${!empty queryId}">查询结果</c:if></title>
	<style type="text/css">
		/*.dropdown-menu{ height: 300px; overflow: auto;}*/
		form {
			margin: 0 0 0px;
		}
	</style>

</head>
<body>
<div id="conditionModal" class="modal container hide fade" tabindex="-1" data-width="400">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button data-dismiss="modal" class="close"  type="button"></button>
				<h3 class="modal-title" id="title" >
					输入条件查询信息
				</h3>
			</div>
			<div class="modal-body form-horizontal" id="dabzAddDiv">
				<div class="control-group">
					<label class="control-label" style=" width: 80px;"><span class="required">*</span>查询名称</label>
					<div class="controls" style="margin-left: 80px;">
						<input class="m-wrap" type="text" id="queryName" name="queryName"  maxlength="45" value="${saveQueryName}"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label"  style=" width: 80px;"><span class="required">*</span>排序</label>
					<div class="controls" style="margin-left: 80px;">
						<input class="m-wrap" type="text" id="querySort" name="querySort"  maxlength="45" value="${querySort}"/>
					</div>
				</div>
				<div class="control-group mybutton-group" style="text-align: right;">
					<button type="button" class="btn green" onclick="saveCondition()"><i class="icon-ok"></i> 确定</button>
					<button type="button" class="btn btn-default"  data-dismiss="modal"><i class="icon-remove-sign"></i> 取消</button>
				</div>
			</div>
		</div>
	</div>
</div>
<div id="mcModal" class="modal container hide fade" tabindex="-1" data-width="400">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button data-dismiss="modal" class="close"  type="button"></button>
				<h3 class="modal-title" id="mctitle" >
					输入名册信息
				</h3>
			</div>
			<div class="modal-body form-horizontal" id="mcAddDiv">
				<div class="control-group">
					<label class="control-label" style=" width: 80px;"><span class="required">*</span>名册名称</label>
					<div class="controls" style="margin-left: 80px;">
						<input class="m-wrap" type="text" id="mcName" name="mcName"  maxlength="45" value="${saveQueryName}"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label"  style=" width: 80px;"><span class="required">*</span>排序</label>
					<div class="controls" style="margin-left: 80px;">
						<input class="m-wrap" type="text" id="mcSort" name="mcSort"  maxlength="45" value="99"/>
					</div>
				</div>
				<div class="control-group mybutton-group" style="text-align: right;">
					<button type="button" class="btn green" onclick="savaAsGbmc()"><i class="icon-ok"></i> 确定</button>
					<button type="button" class="btn btn-default"  data-dismiss="modal"><i class="icon-remove-sign"></i> 取消</button>
				</div>
			</div>
		</div>
	</div>
</div>
	<div class="container-fluid">

		<div class="row-fluid">
			<div class="span12 responsive">
				<%-- 表格开始 --%>
				<div class="portlet box grey">
					<div class="portlet-title">
						<div class="caption">${queryName}<c:if test="${empty queryId}">干部列表</c:if><c:if test="${!empty queryId}">查询结果</c:if></div>
						<div class="clearfix fr">
						<c:if test="${!empty queryId}">
							<button type="button" class="btn green" onclick="saveQuery()"><i class="icon-ok"></i>保存条件</button>
						</c:if>
						<c:if test="${empty queryId}">
							<div class="btn-group">
								<a class="btn green dropdown-toggle" data-toggle="dropdown" href="#">
									条件查询 <i class="icon-angle-down"></i>
								</a>

								<ul class="dropdown-menu" style="left: -60px;">
									<li >
										<a href="${path }/zzb/app/console/asetA01Query/add?addType=a01List">新条件</a>
									</li>
									<li class="divider"></li>
									<c:forEach items="${appAsetA01Querys}" var="vo">
										<li >
											<a onclick="queryByCondition('${vo.id}')">${vo.queryName}</a>
										</li>
									</c:forEach>
									<li class="divider"></li>
									<li>
										<a href="${path }/zzb/app/console/asetA01Query/queryList">条件管理</a>
									</li>
								</ul>
							</div>
							<a id="sample_editable_1_new" class="btn green" href="${path }/zzb/app/console/asetA01/">
								按机构查询
							</a>
							<a id="sample_editable_1_new" class="btn green" href="javascript:openGjcx()">
								高级查询
							</a>
							<div class="btn-group">
								<a class="btn green dropdown-toggle" data-toggle="dropdown" href="#">
									输出 <i class="icon-angle-down"></i>
								</a>
								<ul class="dropdown-menu" style="margin-left: -90px">
									<li >
										<a onclick="">当前查询结果</a>
										<a onclick="">定制查询结果</a>
										<a onclick="">任免审批表(多人一表)</a>
										<a onclick="">任免审批表(一人一表)</a>
									</li>
								</ul>
							</div>
							</c:if>
							<c:if test="${!empty queryId}">
								<div class="btn-group">
									<a class="btn green dropdown-toggle" data-toggle="dropdown" href="#">
										另存为 <i class="icon-angle-down"></i>
									</a>
									<ul class="dropdown-menu">
										<li >
											<a href="javascript:loadGbmc()">干部名册</a>
										</li>

									</ul>
								</div>
								<a id="sample_editable_1_new" class="btn green" href="${path }/zzb/app/console/asetA01/">
									按机构查询
								</a>
								<a id="sample_editable_1_new" class="btn green" href="#">
									高级查询
								</a>
								<div class="btn-group">
									<a class="btn green dropdown-toggle" data-toggle="dropdown" href="#">
										输出 <i class="icon-angle-down"></i>
									</a>
									<ul class="dropdown-menu">
										<li >
											<a onclick="">干部任免审批表</a>
										</li>
									</ul>
								</div>
								<c:if test="${empty queryPosition}">
									<a class="btn" href="${path }/zzb/app/console/asetA01Query/"><i class="icon-undo"></i>返回</a>
								</c:if>
								<c:if test="${queryPosition eq 'queryList'}">
									<a class="btn" href="${path }/zzb/app/console/asetA01Query/queryList"><i class="icon-undo"></i> 返回</a>
								</c:if>
							</c:if>


						</div>
					</div>
					<div class="clearfix">
						<div class="control-group">
							<div id="query" style="float: left;">
								<form action="${path }/zzb/app/console/asetA01Query/" method="POST" id="searchForm" name="searchForm">
									<input type="hidden" name="OWASP_CSRFTOKEN" value="${sessionScope.OWASP_CSRFTOKEN}"/>
									<input type="hidden" name="queryId" value="${queryId }" id="queryId">
									<input type="hidden" name="queryPosition" value="${queryPosition }" id="queryPosition">
									<input type="hidden" name="pageNum" value="${pager.pageNum }" id="pageNum">
									<input type="hidden" name="pageSize" value="${pager.pageSize }" id="pageSize">
									姓名：<input type="text" class="m-wrap" name="xmQuery" id="xmQuery" value="${xmQuery}" style="width: 100px;" />
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
									<th width="60">姓名</th>
									<th width="40">性别</th>
									<th width="60">出生<br>年月</th>
									<th>现任职务</th>
									<th width="100" style="text-align: center">全日制<br>学历学位
									</th>
									<th width="150" style="text-align: center">在职<br>学历学位
									<th width="80"style="text-align: center">任现职级<br>时间
									</th>
								</tr>
							</thead>
							<tbody>
							<c:forEach items="${pager.datas}" var="vo">
								<tr style="text-overflow:ellipsis;">
									<td title="${vo.xm}"><a href="${path}/zzb/app/console/asetA01Query/view?id=${vo.id }&queryId=${queryId}&queryPosition=${queryPosition}"><c:out value="${vo.xm}"></c:out></a></td>
										<%--<td title="${vo.xm}">--%>
										<%--<c:out value="${vo.xm}"></c:out>--%>
										<%--</td>--%>
									<td title="${vo.xb}" ><c:out value="${vo.xb}"></c:out></td>
									<td title="${vo.csnyStr}" style="text-align: center"><c:out value="${vo.csnyStr}"></c:out><br>(<c:out value="${vo.nl}"></c:out>岁)</td>
									<td title="${vo.xrzw}"><c:out value="${vo.xrzw}"></c:out></td>
									<td ><c:out value="${vo.gbrmspbQrzxlxw}"></c:out></td>
									<td ><c:out value="${vo.gbrmspbZzxlxw}"></c:out></td>
									<td ><c:out value="${vo.xrzjsjStr}"></c:out></td>
										<%--<td><c:out value="${vo.zyjszw}"></c:out></td>--%>
										<%--<td><c:out value="${vo.xrzwsj}"></c:out></td>--%>
										<%--<td title="${vo.xrzjsj}"><c:out value="${vo.xrzjsj}"></c:out></td>--%>
										<%--<td class="Left_alignment">--%>
										<%--<a href="javascript:del('${vo.id }','${vo.xm}')" class="">删除</a>--%>
										<%--</td>--%>
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
	<script type="text/javascript" src="${path }/js/common/30CloudAjax.js"></script>
	<script type="text/javascript" src="${path }/js/common/loading.js"></script>
	<script type="text/javascript">
		(function(){
			App.init();
		})();
	
		function pagehref (pageNum ,pageSize){
			window.location.href ="${path}/zzb/app/console/asetA01Query/?queryId=${queryId}&pageNum="+pageNum+"&pageSize="+pageSize;
		}
		
		function searchSubmit(){
			document.searchForm.submit();
		}


		function clearData(){
			$("#xmQuery").val('');
			document.searchForm.submit();
		}
		function savaAsGbmc(){
			var queryId = "${queryId}";
			if(queryId=="") {
				showTip("提示", "条件不能为空", 2000);
				return;
			}
			if($("#mcName").val()==""){
				showTip("提示","请输入名册名称",2000);
				$("#mcName").focus();
				return;
			}
			if($("#mcSort").val()==""){
				showTip("提示","请输入排序",2000);
				$("#mcSort").focus();
				return;
			}else{
				if(isNumberTmp($("#mcSort").val())==false){
					$("#mcSort").focus();
					showTip("提示","排序必须为数字",2000);
					return;
				}
			}
			$.cloudAjax({
				path : '${path}',
				url : "${path }/zzb/app/console/asetA01Query/savaAsGbmc",
				type : "post",
				data: {"queryId":"${queryId}","mcName":$("#mcName").val(),"mcSort":$("#mcSort").val()},
				dataType : "json",
				success : function(data){
					if(data.success){
						showTip("提示","成功另存为干部名册",2000);

						setTimeout(searchSubmit(),2000);
					}else{
						showTip("提示", json.message, 2000);
					}
				},
				error : function(){
					showTip("提示","出错了请联系管理员",2000);
				}
			});
		}
		var del = function(id,itemName){
			actionByConfirm1(itemName, "${path}/zzb/app/console/asetA01Query/delete/" + id,{} ,function(data,status){
				if (data.success == true) {
					showTip("提示","删除成功", 2000);
					setTimeout(function(){window.location.href = "${path}/zzb/app/console/asetA01Query/?queryId=${queryId}"},2000);
				}else{
					showTip("提示", data.message, 2000);	
				}
			});
		};
		function queryByCondition(queryId){
			window.location.href = "${path}/zzb/app/console/asetA01Query/?queryId="+queryId;
		}
		function isNumberTmp(str) {
			var Letters = "0123456789";
			var Letters2 = "-0123456789";
			if(str.length==0)
				return false;

			//对首位进行附加判断
			if(Letters2.indexOf(str.charAt(0)) == -1){
				return false;
			}else{
				for (i = 1; i < str.length; i++) {
					var checkChar = str.charAt(i);
					if (Letters.indexOf(checkChar) == -1)
						return false;
				}
				return true;
			}
		}
		function saveQuery(){
			$('#conditionModal').modal({
				keyboard: true
			});
		}
		function saveCondition(){
			if($("#queryName").val()==""){
				showTip("提示","请输入条件名称",2000);
				$("#queryName").focus();
				return;
			}
			if($("#querySort").val()==""){
				showTip("提示","请输入排序",2000);
				$("#querySort").focus();
				return;
			}else{
				if(isNumberTmp($("#querySort").val())==false){
					$("#querySort").focus();
					showTip("提示","排序必须为数字",2000);
					return;
				}
			}
			$.cloudAjax({
				path : '${path}',
				url : "${path }/zzb/app/console/asetA01Query/saveCondition",
				type : "post",
				data: {"id":"${queryId}","queryName":$("#queryName").val(),"querySort":$("#querySort").val()},
				dataType : "json",
				success : function(data){
					if(data.success){
						showTip("提示","条件保存成功",2000);
						setTimeout(function(){window.location.href = "${path}/zzb/app/console/asetA01Query/queryList"},2000);
					}else{
						showTip("提示", json.message, 2000);
					}
				},
				error : function(){
					showTip("提示","出错了请联系管理员",2000);
				}
			});
		}
		function loadGbmc(){
			$('#mcModal').modal({
				keyboard: true
			});
		}
		function openGjcx(){
			window.open('http://localhost:8080/GZZZB/lq/conditionQuery/CadreQueryListPage_ConditionsInput.jsp?isFor_BZPB=&indexType=市管干部库');
		}
	</script>
</body>
</html>
