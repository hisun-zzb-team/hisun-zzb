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
<title>修改数据源</title>
</head>
<body>
			<div class="container-fluid">

				<div class="row-fluid">
					<div class="span12">
						<%-- BEGIN SAMPLE FORM PORTLET 表单主体--%>   

						<div class="portlet box grey">

							<div class="portlet-title">

								<div class="caption">

									<i class="icon-reorder"></i>

									<span class="hidden-480">修改数据源</span>

								</div>
								<div class="tools">
									<a href="javascript:location.reload();" class="reload"></a>

								</div>
							</div>

							<div class="portlet-body form">
								<!-- BEGIN FORM-->

								<form action="" class="form-horizontal" id="form1"  method="post">
									<input type="hidden" name="id" value="${vo.id }" id="id">
									<div id="actuatorNameGroup" class="control-group"style="margin-bottom:20px !important">
										<label class="control-label">名称:<span class="required">*</span></label>
										<div class="controls">
											<input type="text" class="span6 m-wrap" name="actuatorName" required maxlength="200" id="actuatorName" value="${vo.actuatorName}" />
										</div>
									</div>
									<div class="control-group" id="groupArea">
										<label class="control-label">数据源<span style="color: red;">*</span></label>
										<div class="controls">
											<select class="m-wrap span6" name="sourceType" id="sourceType">
												<option value="1" <c:if test="${vo.sourceType eq '1'}">selected</c:if>>公务员管理系统(浙大网新)</option>
												<option value="2" <c:if test="${vo.sourceType eq '2'}">selected</c:if>>组织综合业务平台(广州三零)</option>
												<option value="3" <c:if test="${vo.sourceType eq '3'}">selected</c:if>>干部管理系统(长沙远望)</option>
											</select>
										</div>
									</div>
									<div id="ipGroup" class="control-group" style="margin-bottom:20px !important">
										<label class="control-label">IP地址:<span class="required">*</span></label>
										<div class="controls">
											<input type="text" class="span6 m-wrap" name="ip" required maxlength="200" id="ip" value="${vo.ip}" />
										</div>
									</div>
									<div id="portGroup" class="control-group">
										<label class="control-label">端口号:<span class="required">*</span></label>
										<div class="controls">
											<input type="text" class="span6 m-wrap" id="port" name="port" number="true"  required maxlength="10"  value="${vo.port}" />
										</div>
									</div>
									<div id="databaseNameGroup" class="control-group" style="margin-bottom:20px !important">
										<label class="control-label">数据库名:<span class="required">*</span></label>
										<div class="controls">
											<input type="text" class="span6 m-wrap" name="databaseName" required maxlength="200" id="databaseName" value="${vo.databaseName}" />
										</div>
									</div>
									<div id="userNameGroup" class="control-group" style="margin-bottom:20px !important">
										<label class="control-label">用户名:<span class="required">*</span></label>
										<div class="controls">
											<input type="text" class="span6 m-wrap" name="userName" required maxlength="200" id="userName" value="${vo.userName}" />
										</div>
									</div>
									<div id="passwordGroup" class="control-group" style="margin-bottom:20px !important">
										<label class="control-label">密码:<span class="required">*</span></label>
										<div class="controls">
											<input type="password" class="span6 m-wrap" name="password" required maxlength="200" id="password" value="${vo.password}" />
										</div>
									</div>

									<div id="sortGroup" class="control-group">
										<label class="control-label">排序<span class="required">*</span></label>
										<div class="controls">
											<input type="text" class="span6 m-wrap" id="sort" name="sort" number="true"  required maxlength="3"  value="${vo.sort}" />
										</div>

									</div>

									<div class="form-actions">
										<button type="button" class="btn green" onclick="formUpdate()"><i class="icon-ok"></i> 确定</button>
										<a class="btn" href="${path }/zzb/app/console/exchange/"><i class="icon-remove-sign"></i> 取消</a>

									</div>
								</form>
							</div>

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

			<!— 引入确认框模块 —>
<%@ include file="/WEB-INF/jsp/inc/confirmModal.jsp"%>
<script type="text/javascript">

	jQuery(document).ready(function() {
		App.init();
		var startDate = $("#pcsjValue").datepicker({
			language:  'zh-CN',
			format: "yyyymmdd",
			pickerPosition: "bottom-left",
			weekStart : 1,
			autoclose : true
		});
	});

	var myVld = new EstValidate("form1");
	function formUpdate(){
		var bool = myVld.form();
		if(bool){
			$.cloudAjax({
				path : '${path}',
				url : "${path }/zzb/app/console/exchange/save",
				type : "post",
				data : $("#form1").serialize(),
				dataType : "json",
				success : function(data){
					if(data.success){
						showTip("提示","操作成功",2000);
						setTimeout(function(){window.location.href = "${path}/zzb/app/console/exchange/"},2000);
					}else{
						showTip("提示", json.message, 2000);
					}
				},
				error : function(){
					showTip("提示","出错了请联系管理员",2000);
				}
			});
		}
	}
	
</script>
</body>
</html>
