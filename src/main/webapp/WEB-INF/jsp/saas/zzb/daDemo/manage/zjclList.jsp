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
	<title>追缴材料</title>
	<style type="text/css">
		form {
			margin: 0 0 0px;
		}
	</style>
</head>
<body>
<div id="addZjclModal" class="modal container hide fade" tabindex="-1" data-width="600">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button data-dismiss="modal" class="close"  type="button"></button>
				<h3 class="modal-title" id="addTitle" >
					增加追缴材料
				</h3>
			</div>
			<div class="modal-body" id="addZjclDiv">
			</div>
		</div>
	</div>
</div>
<div id="editZjclModal" class="modal container hide fade" tabindex="-1" data-width="600">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button data-dismiss="modal" class="close"  type="button"></button>
				<h3 class="modal-title" >
					修改追缴材料
				</h3>
			</div>
			<div class="modal-body" id="editZjclDiv">
			</div>
		</div>
	</div>
</div>

<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12 responsive">
			<%-- 表格开始 --%>
				<div class="portlet-title">
					<div class="caption">追缴材料  共<font color="red"> 1 </font>条记录 </div>
					<div class="clearfix fr">

						<a id="sample_editable_1_new" class="btn green" href="javascript:addZjcl()">
							添加
						</a>

						<%--<span class="controllerClass btn green file_but" >--%>
						<%--<i class="icon-circle-arrow-up"></i>清空数据--%>
						<%--<input class="file_progress" type="file" name="attachFile" id="btn-browseTemplate">--%>
						<%--</span>--%>
					</div>

				</div>

				<div class="portlet-body">
					<table class="table table-striped table-bordered table-hover dataTable table-set">
						<thead>
						<tr>
							<th  width="150">材料名称</th>
							<th width="90">材料时间</th>
							<th width="250">材料大类</th>
							<th>材料类型</th>
							<th width="150">备注</th>
							<th width="90">操作</th>
						</tr>
						</thead>
						<tbody>
							<tr style="text-overflow:ellipsis;">
								<td ><a href="javascript:editZjcl()" class="">学历材料</a></td>
								<td  >2001.03.01</td>
								<td  title="学历、学位、学绩、培训和专业技术情况材料">学历、学位、学绩、培训和专业技术情况材料</td>
								<td title="A.大学本（专）科学历需有: ①高校学生登记表 ②高校学生学习成绩登记表 ③高校毕业生登记表" >
									A.大学本（专）科学历需有: ①高校学生登记表 ②高校学生学习成绩登记表 ③高校毕业生登记表
								</td>
								<td  ></td>
								<td>
									<a href="javascript:editZjcl()" class="">修改</a>|
									<a href="#" class="">删除</a>
								</td>
							</tr>

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

	var addZjcl = function(){
		$.ajax({
			url:"${path}/zzb/app/console/daDemo/ajax/addZjcl",
			type : "post",
			data: {},
			headers:{
				OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
			},
			dataType : "html",
			success : function(html){
				$('#addZjclDiv').html(html);

				$('#addZjclModal').modal({
					keyboard: true
				});
			},
			error : function(){
				showTip("提示","出错了请联系管理员", 1500);
			}
		});
	}
	var editZjcl = function(){
		$.ajax({
			url:"${path}/zzb/app/console/daDemo/ajax/editZjcl",
			type : "post",
			data: {},
			headers:{
				OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
			},
			dataType : "html",
			success : function(html){
				$('#editZjclDiv').html(html);

				$('#editZjclModal').modal({
					keyboard: true
				});
			},
			error : function(){
				showTip("提示","出错了请联系管理员", 1500);
			}
		});
	}
</script>
</body>
</html>
