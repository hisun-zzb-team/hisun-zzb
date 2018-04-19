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
<title>修改干部统计</title>
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

									<span class="hidden-480">修改干部统计</span>

								</div>
								<div class="tools">
									<a href="javascript:location.reload();" class="reload"></a>

								</div>
							</div>

							<div class="portlet-body form">
								<!-- BEGIN FORM-->

								<form action="${path }/zzb/app/console/gbtj/save" class="form-horizontal" id="form1" method="post">
									<input type="hidden" name="id" value="${gbtj.id }" id="id">
									<%--<input type="hidden" name="tjjsondata" value="${gbtj.tjjsondata}" id="tjjsondata">--%>
									<div id="tjmcGroup" class="control-group">
										<label class="control-label">统计名称<span class="required">*</span></label>
										<div class="controls">
											<input type="text" class="span6 m-wrap" name="tjmc" required maxlength="200" id="tjmc" value="${gbtj.tjmc }"/>
										</div>

									</div>
									<div class="control-group" id="groupArea">
										<label class="control-label">图表类型<span style="color: red;">*</span></label>
										<div class="controls">
											<select class="m-wrap span6" name="tblx" id="tblx">
												<option value="1" <c:if test="${gbtj.tblx eq '1'}">selected</c:if>>饼图</option>
												<option value="2" <c:if test="${gbtj.tblx eq '2'}">selected</c:if>>柱状图</option>
												<option value="3" <c:if test="${gbtj.tblx eq '3'}">selected</c:if>>折线图</option>
											</select>
										</div>
									</div>


									<div id="pxGroup" class="control-group">
										<label class="control-label">排序<span class="required">*</span></label>
										<div class="controls">
											<input type="text" class="span6 m-wrap" id="px" name="px" number="true" required maxlength="3"  value="${gbtj.px}" />
										</div>

									</div>

									<div class="form-actions">

										<button type="button" class="btn green" onclick="formUpdate()"><i class="icon-ok"></i> 确定</button>

										<a class="btn" href="${path }/zzb/app/console/gbtj/"><i class="icon-remove-sign"></i> 取消</a>

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
				url : "${path }/zzb/app/console/gbtj/save",
				type : "post",
				data : $("#form1").serialize(),
				dataType : "json",
				success : function(data){
					if(data.success){
						showTip("提示","操作成功",2000);
						setTimeout(function(){window.location.href = "${path}/zzb/app/console/gbtj/"},2000);
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
