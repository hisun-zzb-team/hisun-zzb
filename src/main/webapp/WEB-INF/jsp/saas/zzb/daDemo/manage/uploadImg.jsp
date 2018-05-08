<%@ page import="com.hisun.util.DateUtil" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/inc/servlet.jsp" %>
<%@include file="/WEB-INF/jsp/inc/taglib.jsp" %>
<%--
  ~ Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
  ~ http://www.hn-hisun.com
  ~ 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
  --%>

<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>加载图片</title>
</head>
<body>
<div class="container-fluid">

	<div class="row-fluid">
		<div class="span12">
			<%-- BEGIN SAMPLE FORM PORTLET 表单主体--%>

			<div class="portlet box grey">



				<div class="portlet-body form">
					<!-- BEGIN FORM-->

					<form action="" class="form-horizontal" id="form1" method="post" enctype="multipart/form-data">
						<img src="${path}/images/a38Img/upload.png" height="331">
						<%--<table width="100%" height="100" border="0" cellspacing="0" cellpadding="0" class="table" style="padding-top:2px; ">--%>
							<%--<tr>--%>
								<%--<td>--%>

									<%--<OBJECT id="FL" classid="CLSID:872D54CB-5F57-46AE-88FF-B0DFB9ED40E4" CODEBASE="${path }/js/CAB/UpFileForAR.CAB#version=1,0,0,5" width="100%" height="331" >--%>
										<%--<param name="ServerUrl" value="${path}/zzb/app/console/daDemo/ajax/editZjcl">--%>
										<%--<param name="PrvDir" value="0002">--%>
										<%--<param name="Filter" value="01001">--%>
										<%--<param name="UserID" value="297DEC6BAC15325432AEE5B48D65ADC4">--%>
										<%--<param name="FLAG" value="be5a553efe7ec0f287ccab5104242d93">--%>
										<%--<param name="mShowPicBT" value="true">--%>
										<%--<param name="mShowDirBT" value="false">--%>
										<%--<param name="allowMaxUploadFileSize" value="150000">--%>
									<%--</OBJECT>--%>

								<%--</td>--%>
							<%--</tr>--%>
							<%--<tr>--%>
								<%--<td style="text-align:left;height:10px;">--%>
									<%--<font color="red">注：标题为红色的图片超出150K，不允许上传，请处理后再上传！</font>--%>
								<%--</td>--%>
							<%--</tr>--%>
						<%--</table>--%>

						<div class="control-group">
							<div style="text-align: center">
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

	var i = 0;
	function f(){
		try{

//			var FileList = document.FL.FileList;
//			if ((FileList != "") && (FileList.toLowerCase() != "undefined")){
//				document.all.FileList.value = FileList;
//				document.getElementById("closeButton").disabled = true;
//				showSearchStatues();
//				var form = document.getElementById('e01z1Form');
//				form.submit();
//				return ;
//			}
		}catch(e){
//			document.all.FileList.value = e.description;
		}
//		i++;
//		setTimeout("f()", 1000);
	}
	function back(){
//		window.location.href="/GZZZB/E01z1Action.do?dispatch=checkScanCode&id=298008C1AC15325432AEE5B4DDAF3D97&pka38=297DEC6BAC15325432AEE5B48D65ADC4&archiveId=010&sort=01&archiveName=简历材料";

	}
	var timeInterval;

	//电子文档输出间隔
	var timemilscondes = 2000;
	function initP(){
//		document.all.statusShower.style.visibility="hidden";
	}
	//显示查询等待图片
	function showSearchStatues() {
//		var leftedge=(document.body.clientWidth-statusShower.width)/2;
//		var topmedge=(document.body.clientHeight-statusShower.height)/2-50;
//		statusShower.style.left=leftedge;
//		statusShower.style.top=topmedge;
//		statusShower.style.visibility="visible";
	}
</script>
</body>
</html>
