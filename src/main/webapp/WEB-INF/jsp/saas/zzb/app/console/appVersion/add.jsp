<%@ page import="java.util.Date" %>
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
<title>添加APP程序</title>
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
							<span class="hidden-480">添加APP程序</span>
						</div>
						<div class="tools">
							<a href="javascript:location.reload();" class="reload"></a>
						</div>
					</div>
					<div class="portlet-body form">
						<!-- BEGIN FORM-->
						<form action="" class="form-horizontal" id="form1" method="post" enctype="multipart/form-data">
							<div id="appNameGroup" class="control-group">
								<label class="control-label">应用名称<span class="required">*</span></label>
								<div class="controls">
									<input type="text" class="span6 m-wrap" id="appName" name="appName"   required maxlength="200"  />
								</div>

							</div>
							<div id="appCodeGroup" class="control-group">
								<label class="control-label">应用代码<span class="required">*</span></label>
								<div class="controls">
									<input type="text" class="span6 m-wrap" id="appCode" name="appCode"   required maxlength="200"  />
								</div>

							</div>
							<div class="control-group" id="appTypeGroup">
								<label class="control-label">应用类型<span class="required">*</span></label>
								<div class="controls">
									<select class="span6 m-wrap" id="appType" name="appType"  data-placeholder="Choose a Category" tabindex="1" required>
										<option value="1" selected>安卓</option>
										<option value="2" >IOS</option>
									</select>
								</div>
							</div>
							<div id="appVersionNameGroup" class="control-group">
								<label class="control-label">版本名称<span class="required">*</span></label>
								<div class="controls">
									<input type="text" class="span6 m-wrap" name="appVersionName" required maxlength="200" id="appVersionName" />
								</div>

							</div>
							<div id="appVersionCodeGroup" class="control-group">
								<label class="control-label">版本号<span class="required">*</span></label>
								<div class="controls">
									<input type="text" class="span6 m-wrap" name="appVersionCode" required maxlength="200" id="appVersionCode" />
								</div>

							</div>

							<div  id="appFileGroup" class="control-group" >
								<label  class="control-label">应用程序上传</label>
								<div class="controls">
									<input type="file" class="default"  name="appFile" id="appFile" fileSizeLimit="20" fileType="apk,ipa"/>
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
<script type="text/javascript">
	var myLoading = new MyLoading("${path}",20000);
//	(function(){
//		App.init();
//
//	})();

	jQuery(document).ready(function() {
		App.init();

	});
	var myVld = new EstValidate("form1");

function formSubmit(){
	var bool = myVld.form();
	if(!bool){
		return;
	}
	var fileInput = document.getElementById("appFile");
	var appType = document.getElementById("appType").value;
	if (fileInput.files.length == 0) {
		showTip("提示", "请上传APP", 2000);
		return;
	}
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

</script>
</body>
</html>
