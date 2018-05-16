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
	<title>增加追缴材料</title>
</head>
<body>
<div class="container-fluid">

	<div class="row-fluid">
		<div class="span12">
			<%-- BEGIN SAMPLE FORM PORTLET 表单主体--%>

			<div class="portlet box grey">

				<div class="portlet-title">
					<div class="caption">
						<span class="hidden-480">添加欠缺材料</span>
					</div>
				</div>

				<div class="portlet-body form">
					<!-- BEGIN FORM-->

					<form action="${path }/zzb/dzda/e01z1/save" class="form-horizontal" id="form1" method="post" enctype="multipart/form-data">
						<input type="hidden" id="a38Id" name="a38Id" value="${a38Id }"/>
						<input type="hidden" id="eCatalogTypeId" name="eCatalogTypeId" value="${eCatalogTypeId }"/>
						<div id="e01Z401Group" class="control-group">
							<label class="control-label">材料名称<span class="required">*</span></label>
							<div class="controls">
								<input type="text" class="span6 m-wrap" name="e01Z401" required  maxlength="200" id="e01Z401" value=""/>
							</div>

						</div>

						<div class="control-group" id="fileTimeGroup">

							<label class="control-label">材料时间</label>
							<div class="controls">
								<input type="text" class="span6 m-wrap" name="fileTime" placeholder="日期格式 例如：2018或201801或20180101"  isDate="true" dateformat="yyyy,yyyymm,yyyymmdd" maxlength="200" id="fileTime" value=""/>

							</div>

						</div>
						<div id="e01Z401AGroup" class="control-group" >
							<label class="control-label">所属大类 <span class="required">*</span></label>
							<div class="controls">
								<Tree:tree id="e01Z401B" valueName="e01Z401A" treeUrl="${path}/zzb/dzda/e01z4/tree" token="${sessionScope.OWASP_CSRFTOKEN}"
										   submitType="post" dataType="json" isSearch="false" isSelectTree="true" required="true" onClick="onClickByTree1"
										   defaultkeys="${vo.parentId}" defaultvalues="${vo.parentName}" />
							</div>
						</div>

						<div id="fileTypeNameGroup" class="control-group" >
							<label class="control-label">材料类型 <span class="required">*</span></label>
							<div class="controls">
								<SelectTag:SelectTag id="fileTypeCode" valueName="fileTypeName" textClass="m-wrap span6" radioOrCheckbox="radio" selectUrl="${path}/zzb/dzda/e01z4/select"/>
							</div>
						</div>

						<div id="pxGroup" class="control-group">
							<label class="control-label">材料顺序号<span class="required">*</span></label>
							<div class="controls">
								<input size="16" type="text" required class="span6 m-wrap" value="${px }" id="px" name="px" >
							</div>

						</div>

						<div id="remarkGroup" class="control-group">
							<label class="control-label">备注</label>
							<div class="controls">
								<textarea class="span6" style="" rows="2" name="remark" maxlength="400" id="remark" ></textarea>
							</div>
						</div>

						<div class="control-group">
							<div class="controls mt10">

								<button id="submitbut" style="margin-left:205px;" type="button" class="btn green mybutton" ><i class='icon-ok'></i> 确定</button>

								<a class="btn" href="javascript:cencal()"><i class="icon-remove-sign"></i> 关闭</a>
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

	var addForm = new EstValidate("form1");

	function onClickByTree1 (event, treeId, treeNode){
		$("#eCatalogTypeId").val(treeNode.id);//赋值
	}

	$(function(){
		$("#submitbut").on("click",function(){
			var a38Id = $("#a38Id").val();
			var bool = addForm.form();
			if(bool){
				$.ajax({
					url : "${path}/zzb/dzda/e01z4/save",
					type : "post",
					data : $("#form1").serialize(),
					dataType : "json",
					headers: {
						"OWASP_CSRFTOKEN":"${sessionScope.OWASP_CSRFTOKEN}"
					},
					success : function(json){
						if(json.success){
							$.ajax({
								url: "${path }/zzb/dzda/e01z4/ajax/list",// 请求的action路径qq
								type: 'POST',
								dataType : "html",
								data:{
									"a38Id":a38Id
								},
								headers: {
									"OWASP_CSRFTOKEN":"${sessionScope.OWASP_CSRFTOKEN}"
								},
								success : function(html){
									$("#rightList").hide();
									var view = $("#tab_show");
									view.html(html);
								},
								error : function(){
									alert('请求失败');
								}
							});
						}else{
							document.getElementById("addForm").reset();
							$(".control-group").removeClass("error").removeClass("success");
							$(".help-inline").remove();
							showTip("警告","新增欠缺材料失败!",2000);
						}
					},
					error : function(){
						document.getElementById("addForm").reset();
						$(".control-group").removeClass("error").removeClass("success");
						$(".help-inline").remove();
					}
				});
			}
		});
	});

	function cencal(){
		var a38Id = $("#a38Id").val();
		$.ajax({
			url : "${path }/zzb/dzda/e01z4/ajax/list",
			type : "post",
			data : {"a38Id":a38Id},
			dataType : "html",
			success : function(html){
				var view = $("#tab_show");
				view.html(html);
			},
			error : function(arg1, arg2, arg3){
				showTip("提示","欠缺材料加载失败");
			}
		});
		<%--$.ajax({--%>
			<%--url: "${path}/zzb/dzda/e01z4/ajax/list",// 请求的action路径--%>
			<%--type: 'POST',--%>
			<%--dataType : "html",--%>
			<%--data:{--%>
				<%--"a38Id":a38Id--%>
			<%--},--%>
			<%--headers: {--%>
				<%--"OWASP_CSRFTOKEN":"${sessionScope.OWASP_CSRFTOKEN}"--%>
			<%--},--%>
			<%--success : function(html){--%>
				<%--$("#rightList").hide();--%>
				<%--$("#e01z1Table").show();--%>
				<%--$("#e01z1Table").html(html);--%>
			<%--},--%>
			<%--error : function(){--%>
				<%--alert('请求失败');--%>
			<%--}--%>
		<%--});--%>
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
