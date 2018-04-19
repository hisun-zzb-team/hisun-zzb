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
<title>修改APP程序</title>
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
									<span class="hidden-480">修改APP程序</span>
								</div>
								<div class="tools">
									<a href="javascript:location.reload();" class="reload"></a>
								</div>
							</div>
							<div class="portlet-body form">
								<!-- BEGIN FORM-->
								<form action="${path }/zzb/app/console/appVersion/save" class="form-horizontal" id="form1" method="post" enctype="multipart/form-data">
									<input type="hidden" name="id" value="${appVersionVo.id }" id="id">
									<input type="hidden" name="appStorePath" value="${appVersionVo.appStorePath }" id="appStorePath">
									<input type="hidden" name="appMd5" value="${appVersionVo.appMd5 }" id="appMd5">
									<input type="hidden" name="appSize" value="${appVersionVo.appSize }" id="appSize">
									<div id="appNameGroup" class="control-group">
										<label class="control-label">应用名称<span class="required">*</span></label>
										<div class="controls">
											<input type="text" class="span6 m-wrap" id="appName" name="appName"  required maxlength="200"  value="${appVersionVo.appName}" />
										</div>

									</div>
									<div id="appCodeGroup" class="control-group">
										<label class="control-label">应用代码<span class="required">*</span></label>
										<div class="controls">
											<input type="text" class="span6 m-wrap" id="appCode" name="appCode"   required maxlength="200"  value="${appVersionVo.appCode}" />
										</div>

									</div>
									<div class="control-group" id="appTypeGroup">
										<label class="control-label">应用类型<span class="required">*</span></label>
										<div class="controls">
											<select class="span6 m-wrap" id="appType" name="appType"  data-placeholder="Choose a Category" tabindex="1" required>
												<option value="1" <c:if test="${appVersionVo.appType eq '1'}">selected</c:if>>安卓</option>
												<option value="2" <c:if test="${appVersionVo.appType eq '2'}">selected</c:if>>IOS</option>
											</select>
										</div>
									</div>
									<div id="appVersionNameGroup" class="control-group">
										<label class="control-label">版本名称<span class="required">*</span></label>
										<div class="controls">
											<input type="text" class="span6 m-wrap" name="appVersionName" required maxlength="200" id="appVersionName" value="${appVersionVo.appVersionName}"/>
										</div>

									</div>
									<div id="appVersionCodeGroup" class="control-group">
										<label class="control-label">版本号<span class="required">*</span></label>
										<div class="controls">
											<input type="text" class="span6 m-wrap" name="appVersionCode" required maxlength="200" id="appVersionCode"  value="${appVersionVo.appVersionCode}"/>
										</div>

									</div>
									<div  id="appFileGroup" class="control-group" >
										<label  class="control-label">APP程序上传</label>
										<div class="controls">
											<input type="file" class="default"  name="appFile" id="appFile" fileSizeLimit="20" fileType="apk,ipa"/>
											<div class="btn-group" <c:if test="${empty appVersionVo.appStorePath}">
												style="visibility:hidden"</c:if>>
												<a class="btn blue" herf="javascript:void(0)" onclick="fileDown()"><i
														class="icon-circle-arrow-down"></i>下载APP</a>
											</div>
											<p class="textprompt">安卓为'apk',IOS为'ipa'</p>
											<p class="Errorred" id="attachFileError"></p>
										</div>
									</div>
									<div class="control-group">
										<div class="controls mt10">
											<button class="btn green" type="button" style="padding:7px 20px;" onclick="formSubmit()">确定</button>
											<a class="btn" href="${path }/zzb/app/console/appVersion/"><i class="icon-remove-sign"></i> 取消</a>
										</div>
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
			<script type="text/javascript" src="${path }/js/common/loading.js"></script>
			<!— 引入确认框模块 —>
<%@ include file="/WEB-INF/jsp/inc/confirmModal.jsp"%>
<script type="text/javascript">
	var myLoading = new MyLoading("${path}",20000);
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
	<%--function formUpdate(){--%>
		<%--var bool = myVld.form();--%>
		<%--if(bool){--%>
			<%--$.cloudAjax({--%>
				<%--path : '${path}',--%>
				<%--url : "${path }/zzb/app/console/appVersion/save",--%>
				<%--type : "post",--%>
				<%--data : $("#form1").serialize(),--%>
				<%--dataType : "json",--%>
				<%--success : function(data){--%>
					<%--if(data.success){--%>
						<%--showTip("提示","操作成功",2000);--%>
						<%--setTimeout(function(){window.location.href = "${path}/zzb/app/console/appVersion/"},2000);--%>
					<%--}else{--%>
						<%--showTip("提示", json.message, 2000);--%>
					<%--}--%>
				<%--},--%>
				<%--error : function(){--%>
					<%--showTip("提示","出错了请联系管理员",2000);--%>
				<%--}--%>
			<%--});--%>
		<%--}--%>
	<%--}--%>
	function formSubmit(){
		var bool = myVld.form();
		if(!bool){
			return;
		}
		var fileInput = document.getElementById("appFile");
		var appType = document.getElementById("appType").value;
		if (fileInput.files.length > 0) {
			var name = fileInput.files[0].name
			var arr = name.split(".");
			if(appType==1) {
				if (arr.length < 2 || !(arr[arr.length - 1] == "apk")) {
					showTip("提示", "安卓APP请上传apk文件", 2000);
					return;
				}
			}else{
				if (arr.length < 2 || !(arr[arr.length - 1] == "ipa")) {
					showTip("提示", "IOS请上传ipa文件", 2000);
					return;
				}
			}
		}
		myLoading.show();
		$("#form1").ajaxSubmit({
			url : "${path }/zzb/app/console/appVersion/save",
			type : "post",
			dataType : "json",
			enctype : "multipart/form-data",
			headers: {
				"OWASP_CSRFTOKEN":"${sessionScope.OWASP_CSRFTOKEN}"
			},
			success : function(data){
				myLoading.hide();
				if(data.success){
					showTip("提示","操作成功",2000);
					setTimeout(function(){window.location.href = "${path}/zzb/app/console/appVersion/"},2000);
				}else{
					showTip("提示", json.message, 2000);
				}
			},
			error : function(arg1, arg2, arg3){
				myLoading.hide();
				showTip("提示","出错了请联系管理员");
			}
		});
	}

	function fileDown() {
		window.open("${path }/zzb/app/console/appVersion/ajax/down?id=${appVersionVo.id}");
	}

</script>
</body>
</html>
