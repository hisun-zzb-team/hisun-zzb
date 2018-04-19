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
<title>添加干部名册</title>
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

							<span class="hidden-480">添加干部名册</span>

						</div>
						<div class="tools">
							<a href="javascript:location.reload();" class="reload"></a>

						</div>
					</div>

					<div class="portlet-body form">
						<!-- BEGIN FORM-->
						<form action="" class="form-horizontal" id="form1" method="post" enctype="multipart/form-data">
							<div id="mcGroup" class="control-group">
								<label class="control-label">名册名称<span class="required">*</span></label>
								<div class="controls">
									<input type="text" class="span6 m-wrap" id="mc" name="mc" required maxlength="200"  />
								</div>

							</div>
							<div class="control-group" id="mbGroup" style="display: block">
								<label class="control-label">选择模板<span class="required">*</span></label>
								<div class="controls">
									<select class="span6 m-wrap" id="mb" name="mb"   data-placeholder="Choose a Category" tabindex="1" required>
										<option value="湘西州模板" selected>湘西州模板</option>
										<option value="广州模板" >广州模板</option>
									</select>
								</div>
							</div>
							<div class="control-group" id="isMlGroup">
								<label class="control-label">有无目录<span class="required">*</span></label>
								<div class="controls">
									<select class="span6 m-wrap" id="isMl" name="isMl"   data-placeholder="Choose a Category" tabindex="1" required>
										<option value="0" selected>有目录</option>
										<option value="1" >无目录</option>
									</select>
								</div>
							</div>

							<div id="pxGroup" class="control-group">
								<label class="control-label">排序<span class="required">*</span></label>
								<div class="controls">
									<input type="text" class="span6 m-wrap" id="px" name="px" number="true"  required maxlength="3"  value="<c:out value="${gbMc.px}"></c:out>" />
								</div>

							</div>
							<div  id="gbmcFileGroup" class="control-group">
								<label class="control-label">名册文件</label>
								<div class="controls">
									<input type="file" class="default"  name="gbmcFile" id="gbmcFile" fileSizeLimit="20" onchange="setName(this)"  fileType="doc,docx,DOC,DOCX"/>
									<p class="textprompt">附件支持的格式有：'doc','docx'</p>
								</div>
							</div>
							<div  id="a01FileGroup" class="control-group">
								<label class="control-label">干部信息</label>
								<div class="controls">
									<input type="file" class="default"  name="a01File" id="a01File" fileSizeLimit="20" fileType="zip,ZIP"/>
									<p class="textprompt">附件支持的格式有：'zip','ZIP'</p>
								</div>
							</div>
							<div  id="zpFileGroup" class="control-group">
								<label class="control-label">干部照片</label>
								<div class="controls">
									<input type="file" class="default"  name="zpFile" id="zpFile" fileSizeLimit="20" fileType="zip,ZIP"/>
									<p class="textprompt">附件支持的格式有：'zip','ZIP'</p>
								</div>
							</div>
							<div class="form-actions">
								<button type="button" class="btn green" onclick="formSubmit()"><i class="icon-ok"></i> 确定</button>
								<a class="btn" href="${path }/zzb/app/console/gbmc/"><i class="icon-remove-sign"></i> 取消</a>

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
	function setName(obj) {
		if (obj.value != "") {
			var fileName  = obj.value.substring(obj.value.lastIndexOf('\\')+1);
			fileName = fileName.substring(0,fileName.lastIndexOf('.'));
			if($("#mc").val()==""){
				$("#mc").val(fileName);
			}
		}
	}
	var myLoading = new MyLoading("${path}",20000);
//	(function(){
//		App.init();
//
//	})();
var myVld = new EstValidate("form1");
function formSubmit() {
	var bool = myVld.form();
	if (!bool) {
		return;
	}
	var isMl = $('#isMl').val();//0;有目录  1:无目录
	var isHavaB01File = false;//
	var gbmcFileInput = document.getElementById("gbmcFile");
	if (gbmcFileInput.files.length > 0) {
		var name = gbmcFileInput.files[0].name
		var arr = name.split(".");
		if (arr.length < 2 || !(arr[arr.length - 1] == "doc" || arr[arr.length - 1] == "docx" || arr[arr.length - 1] == "DOC" || arr[arr.length - 1] == "DOCX")) {
			showTip("提示", "请上传word文件", 2000);
			return;
		} else {
			isHavaB01File = true;
		}
	}
	var isHavaA01File = false;//
	var a01fileInput = document.getElementById("a01File");
	if (a01fileInput.files.length > 0) {
		var name = a01fileInput.files[0].name
		var arr = name.split(".");
		if (arr.length < 2 || !(arr[arr.length - 1] == "zip" || arr[arr.length - 1] == "ZIP")) {
			showTip("提示", "请上传zip文件", 2000);
			return;
		} else {
			isHavaA01File = true;
		}
	}
	var isHavaZpFile = false;//
	var zpfileInput = document.getElementById("zpFile");
	if (zpfileInput.files.length > 0) {
		var name = zpfileInput.files[0].name
		var arr = name.split(".");
		if (arr.length < 2 || !(arr[arr.length - 1] == "zip" || arr[arr.length - 1] == "ZIP")) {
			showTip("提示", "请上传zip文件", 2000);
			return;
		} else {
			isHavaZpFile = true;
		}
	}
	if(isHavaB01File == false){
		showTip("提示", "请上传名册文件", 2000);
		return;
	}
//	if (isMl == "0") {//0;有目录  1:无目录 如果为无目录则  如果为无目录：如上传了干部照片则必须传干部信息  有目录：上传了干部照片则必须上传名册文件、干部信息；如上传了干部信息则必须上传名册信息
//		if(isHavaZpFile == true){
//			if(isHavaB01File == false || isHavaA01File==false){
//				showTip("提示", "有目录已上传了照片文件，请上传名册文件和干部信息", 2000);
//				return;
//			}
//		}else{
//			if(isHavaA01File==true&&isHavaB01File == false ){
//				showTip("提示", "有目录已上传了干部信息，请上传名册文件", 2000);
//				return;
//			}
//		}
//	}else{
//		if(isHavaZpFile == true){
//			if(isHavaB01File == false || isHavaA01File==false){
//				showTip("提示", "无目录已上传了照片文件，请上传干部信息", 2000);
//				return;
//			}
//		}
//	}
	myLoading.show();
	$("#form1").ajaxSubmit({
		url : "${path }/zzb/app/console/gbmc/save",
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
				setTimeout(function(){window.location.href = "${path}/zzb/app/console/gbmc/"},2000);
			}else{
				showTip("提示", data.message, 2000);
			}
		},
		error : function(arg1, arg2, arg3){
			myLoading.hide();
			showTip("提示","出错了请联系管理员");
		}
	});
}

	<%--var myVld = new EstValidate("form1");--%>
	<%--function formSubmit(){--%>
		<%--var bool = myVld.form();--%>
		<%--if(!bool){--%>
			<%--return;--%>
		<%--}--%>
		<%--var fileInput = document.getElementById("clFile");--%>
		<%--if (fileInput.files.length > 0) {--%>
			<%--var name = fileInput.files[0].name--%>
			<%--var arr = name.split(".");--%>
			<%--if (arr.length < 2 || !(arr[arr.length - 1] == "doc" || arr[arr.length - 1] == "docx" || arr[arr.length - 1] == "DOC" || arr[arr.length - 1] == "DOCX")) {--%>
				<%--showTip("提示", "请上传word文件", 2000);--%>
				<%--return;--%>
			<%--}--%>
		<%--}--%>
		<%--$.cloudAjax({--%>
			<%--path : '${path}',--%>
			<%--url : "${path }/zzb/app/console/gbmc/save",--%>
			<%--type : "post",--%>
			<%--data : $("#form1").serialize(),--%>
			<%--dataType : "json",--%>
			<%--enctype : "multipart/form-data",--%>
			<%--headers: {--%>
				<%--"OWASP_CSRFTOKEN":"${sessionScope.OWASP_CSRFTOKEN}"--%>
			<%--},--%>
			<%--success : function(data){--%>
				<%--if(data.success){--%>
					<%--showTip("提示","操作成功",2000);--%>
					<%--setTimeout(function(){window.location.href = "${path}/zzb/app/console/gbmc/"},2000);--%>
				<%--}else{--%>
					<%--showTip("提示", json.message, 2000);--%>
				<%--}--%>
			<%--},--%>
			<%--error : function(){--%>
				<%--showTip("提示","出错了请联系管理员",2000);--%>
			<%--}--%>
		<%--});--%>

	<%--}--%>
	
</script>
</body>
</html>
