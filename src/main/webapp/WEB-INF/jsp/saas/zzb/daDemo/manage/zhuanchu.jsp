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
<title>转出档案</title>
</head>
<body>
			<div class="container-fluid">

				<div class="row-fluid">
					<div class="span12">
						<%-- BEGIN SAMPLE FORM PORTLET 表单主体--%>   

						<div class="portlet box grey">



							<div class="portlet-body form">
								<!-- BEGIN FORM-->

								<form action="${path }/zzb/app/console/bwh/save" class="form-horizontal" id="form1" method="post" enctype="multipart/form-data">
									<input type="hidden" name="id" value="${shpc.id }" id="id">
									<input type="hidden" name="filePath" value="${shpc.filePath }" id="filePath">
									<div id="pcmcGroup" class="control-group">
										<label class="control-label">姓名<span class="required">*</span></label>
										<div class="controls">
											<input type="text" class="span10 m-wrap" name="pcmc"  maxlength="200" id="pcmc" value="红叶专"/>
										</div>

									</div>

									<div class="control-group" id="shlxGroup">

										<label class="control-label">干部状态</label>
										<div class="controls">
											<select class="span10 m-wrap" type="text" id="influenceInfoId" name="influenceInfoId" required>
												<option value=""></option>
												<option value="" selected>在职</option>
												<option value="" >离职</option>
												<option value="" >离退</option>
												<option value="" >辞职</option>
											</select>
										</div>

									</div>
									<div class="control-group" id="sjlxGroup">
										<label class="control-label">单位职务</label>
										<div class="controls">
											<textarea class="span10 m-wrap" rows="3">州委书记</textarea>
										</div>
									</div>

									<div id="pcsjValueGroup" class="control-group">
										<label class="control-label">档案状态<span class="required">*</span></label>
										<div class="controls">
											<select class="span10 m-wrap" type="text" id="influenceInfoId" name="influenceInfoId" required>
												<option value=""></option>
												<option value="" >有档</option>
												<option value="" >无档需追补</option>
												<option value="" >无档不需追补</option>
												<option value="" selected>转出</option>
											</select>
										</div>

									</div>
									<div id="pxGroup" class="control-group">
										<label class="control-label">接收单位<span class="required">*</span></label>
										<div class="controls">
											<input size="16" type="text"  class="span10 m-wrap" value=""
												   id="sjwcsj" name="sjwcsj" >
										</div>

									</div>
									<div class="control-group" id="mbGroup">

										<label class="control-label">转出时间</label>
										<div class="controls">
											<input size="16" type="text"  class="span10 m-wrap" value="20180402"
											id="sjwcsj" name="sjwcsj" >
										</div>
									</div>
									<div class="control-group" id="mbGroup">

										<label class="control-label">转出人</label>
										<div class="controls">
											<input size="16" type="text"  class="span10 m-wrap" value="石辉军"
												   id="sjwcsj" name="sjwcsj" >
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
		var sjwcsj = $("#pcsjValue").datepicker({
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
				<%--url : "${path }/zzb/app/console/bwh/save",--%>
				<%--type : "post",--%>
				<%--data : $("#form1").serialize(),--%>
				<%--dataType : "json",--%>
				<%--success : function(data){--%>
					<%--if(data.success){--%>
						<%--showTip("提示","操作成功",2000);--%>
						<%--setTimeout(function(){window.location.href = "${path}/zzb/app/console/bwh/"},2000);--%>
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
		var fileInput = document.getElementById("clFile");
		if (fileInput.files.length > 0) {
			var name = fileInput.files[0].name
			var arr = name.split(".");
			if (arr.length < 2 || !(arr[arr.length - 1] == "doc" || arr[arr.length - 1] == "docx" || arr[arr.length - 1] == "DOC" || arr[arr.length - 1] == "DOCX")) {
				showTip("提示", "请上传word文件", 2000);
				return;
			}
		}
		myLoading.show();
		$("#form1").ajaxSubmit({
			url : "${path }/zzb/app/console/bwh/save",
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
					setTimeout(function(){window.location.href = "${path}/zzb/app/console/bwh/?pageNum=${shpcPageNum}"},2000);
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


	function changeFile(obj){
		if(obj.value =="1"){
			window.document.getElementById("clFileGroup").style.visibility = "hidden";
			window.document.getElementById("mbGroup").style.display = "block";
		}else{
			window.document.getElementById("mbGroup").style.display = "none";
			window.document.getElementById("clFileGroup").style.visibility = "visible";
		}
	}
</script>
</body>
</html>
