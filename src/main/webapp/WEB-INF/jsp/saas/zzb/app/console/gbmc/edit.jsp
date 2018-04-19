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
<title>修改干部名册</title>
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

									<span class="hidden-480">修改干部名册</span>

								</div>
								<div class="tools">
									<a href="javascript:location.reload();" class="reload"></a>

								</div>
							</div>

							<div class="portlet-body form">
								<!-- BEGIN FORM-->

								<form action="" class="form-horizontal" id="form1" method="post"enctype="multipart/form-data">
									<input type="hidden" name="id" value="${gbMc.id }" id="id">
									<input type="hidden" name="isMl" value="${gbMc.isMl }" id="isMl">

									<%--<input type="hidden" name="tjjsondata" value="${gbtj.tjjsondata}" id="tjjsondata">--%>
									<div id="mcGroup" class="control-group">
										<label class="control-label">名册名称<span class="required">*</span></label>
										<div class="controls">
											<input type="text" class="span6 m-wrap" name="mc" required maxlength="200" id="mc" value="${gbMc.mc }"/>
										</div>

									</div>
									<div class="control-group" id="mbGroup">
										<label class="control-label">选择模板<span class="required">*</span></label>
										<div class="controls">
											<select class="span6 m-wrap" id="mb" name="mb"   data-placeholder="Choose a Category" tabindex="1" required>
												<option value="湘西州模板" <c:if test="${gbMc.mb eq '广州模板'}">selected</c:if>>广州模板</option>
												<option value="广州模板" <c:if test="${gbMc.mb eq '湖南模板'}">selected</c:if>>湖南模板</option>
											</select>
										</div>
									</div>
									<div class="control-group" >
										<label class="control-label">有无目录<span class="required">*</span></label>
										<div class="controls">
											<select class="span6 m-wrap"   data-placeholder="Choose a Category" disabled tabindex="1" required>
												<option value="0" <c:if test="${gbMc.isMl ==0}">selected</c:if>>有目录</option>
												<option value="1" <c:if test="${gbMc.isMl ==1}">selected</c:if>>无目录</option>
											</select>
										</div>
									</div>

									<div id="pxGroup" class="control-group">
										<label class="control-label">排序<span class="required">*</span></label>
										<div class="controls">
											<input type="text" class="span6 m-wrap" id="px" name="px" number="true" required maxlength="3"  value="${gbMc.px}" />
										</div>

									</div>

									<div class="form-actions">

										<button type="button" class="btn green" onclick="formUpdate()"><i class="icon-ok"></i> 确定</button>

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
				var myLoading = new MyLoading("${path}",20000);
				//	(function(){
				//		App.init();
				//
				//	})();
				var myVld = new EstValidate("form1");
				function formUpdate() {
					var bool = myVld.form();
					if (!bool) {
						return;
					}

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
	<%--function formUpdate(){--%>
		<%--var bool = myVld.form();--%>
		<%--if(bool){--%>
			<%--$.cloudAjax({--%>
				<%--path : '${path}',--%>
				<%--url : "${path }/zzb/app/console/gbmc/save",--%>
				<%--type : "post",--%>
				<%--data : $("#form1").serialize(),--%>
				<%--dataType : "json",--%>
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
	<%--}--%>
	
</script>
</body>
</html>
