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
<title>添加名册目录</title>
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

							<span class="hidden-480">添加名册目录</span>

						</div>
						<div class="tools">
							<a href="javascript:location.reload();" class="reload"></a>

						</div>
					</div>

					<div class="portlet-body form">
						<!-- BEGIN FORM-->
						<form action="" class="form-horizontal" id="form1"  method="post">
							<input type="hidden" name="mcid" value="${gbMcB01.gbMcVo.id}" id="mcid">
							<div id="b0101Group" class="control-group">
								<label class="control-label">目录名称<span class="required">*</span></label>
								<div class="controls">
									<input type="text" class="span6 m-wrap" id="b0101" name="b0101" required maxlength="200"  />
								</div>

							</div>


							<div id="pxGroup" class="control-group">
								<label class="control-label">排序<span class="required">*</span></label>
								<div class="controls">
									<input type="text" class="span6 m-wrap" id="px" name="px" number="true"  required maxlength="3"  value="<c:out value="${gbMcB01.px}"></c:out>" />
								</div>

							</div>

							<div class="form-actions">
								<button type="button" class="btn green" onclick="formSubmit()"><i class="icon-ok"></i> 确定</button>
								<a class="btn" href="${path }/zzb/app/console/gbmc/b01/list?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}&mcid=${gbMcB01.gbMcVo.id}"><i class="icon-remove-sign"></i> 取消</a>

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
<script type="text/javascript">
	
//	(function(){
//		App.init();
//
//	})();

	var myVld = new EstValidate("form1");
	function formSubmit(){
		var bool = myVld.form();
		if(bool){
			$.cloudAjax({
				path : '${path}',
				url : "${path }/zzb/app/console/gbmc/b01/save",
				type : "post",
				data : $("#form1").serialize(),
				dataType : "json",
				headers:{
					"OWASP_CSRFTOKEN":'${sessionScope.OWASP_CSRFTOKEN}'
				},
				success : function(data){
					if(data.success){
						showTip("提示","操作成功",2000);
						setTimeout(function(){window.location.href = "${path}/zzb/app/console/gbmc/b01/list?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}&mcid=${gbMcB01.gbMcVo.id}"},2000);
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
