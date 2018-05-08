<%@ page import="com.hisun.util.DateUtil" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/inc/servlet.jsp" %>
<%@include file="/WEB-INF/jsp/inc/taglib.jsp" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>档案阅档申请</title>
</head>
<body>
<div class="container-fluid">

	<div class="row-fluid">
		<div class="span12">
			<%-- BEGIN SAMPLE FORM PORTLET 表单主体--%>



				<div class="portlet-body form">
					<!-- BEGIN FORM-->

					<form action="${path }/zzb/app/console/bwh/save" class="form-horizontal" id="form1" method="post" enctype="multipart/form-data">
						<input type="hidden" name="id" value="${shpc.id }" id="id">
						<input type="hidden" name="filePath" value="${shpc.filePath }" id="filePath">
						<div id="pcmcGroup" class="control-group">
							<label class="control-label">上级目录<span class="required">*</span></label>
							<div class="controls">
								<select class="span10 m-wrap" type="text" id="influenceInfoId" name="influenceInfoId" required>
									<option value=""></option>
									<option value="">简历材料</option>
									<option value="">自传材料</option>
									<option value="">鉴定、考核、考察材料</option>
									<option value="">学历、学位、学绩、培训和专业技术情况材料</option>
									<option value="">政审材料</option>
									<option value="">加入党团材料</option>
									<option value="">奖励材料</option>
									<option value="">处分材料</option>
									<option value="">工资、任免、出国、代表大会等材料</option>
									<option value="">其他供参考材料</option>

								</select>
							</div>

						</div>

						<div class="control-group" id="shlxGroup">

							<label class="control-label">目录名称<span class="required">*</span></label>
							<div class="controls">
								<input type="text" class="span10 m-wrap" name="pcmc"  maxlength="200" id="pcmc" value=""/>

							</div>

						</div>
						<div class="control-group" id="sjlxGroup">
							<label class="control-label">排序<span class="required">*</span></label>
							<div class="controls">
								<input type="text" class="span10 m-wrap" name="pcmc"  maxlength="200" id="pcmc" value="11"/>
							</div>
						</div>

						<div id="pcsjValueGroup" class="control-group">
							<label class="control-label">目录编码</label>
							<div class="controls">
								<input size="16" type="text"  class="span10 m-wrap" value="110"
									   id="pcsjValue" name="pcsjValue" >
							</div>
						</div>
						<div class="control-group">
							<div class="controls mt10">
								<button class="btn green" type="button" style="padding:7px 20px;" onclick="">确定</button>

								<button type="button" class="btn btn-default" data-dismiss="modal"><i class='icon-remove-sign'></i> 关闭</button>
							</div>
						</div>
					</form>
				</div>

			<%-- END SAMPLE FORM PORTLET--%>
		</div>
	</div>

	<%-- END PAGE CONTENT--%>
</div>

<script type="text/javascript" src="${path }/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="${path }/js/common/est-validate-init.js"></script>
<script type="text/javascript" src="${path }/js/common/validate-message.js"></script>
<script type="text/javascript" src="${path }/js/common/30CloudAjax.js"></script>
<script type="text/javascript" src="${path }/js/common/DataValidate.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap-datepicker.zh-CN.js"></script>
<script type="text/javascript" src="${path }/js/common/loading.js"></script>
<!— 引入确认框模块 —>
<%@ include file="/WEB-INF/jsp/inc/confirmModal.jsp"%>
<script type="text/javascript">

</script>
</body>
</html>
