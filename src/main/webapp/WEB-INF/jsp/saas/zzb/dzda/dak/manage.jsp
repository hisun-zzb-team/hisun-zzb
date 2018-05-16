<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
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

	<title>电子档案系统</title>
</head>
<body>


<!--BEGIN TABS-->
<div class="tabbable tabbable-custom">
	<ul class="nav nav-tabs" style="font-size: 14px;font-weight: bold;" id="tabs">
		<li ><a id="#tab_1_1" href="#tab_1_1" data-toggle="tab">申请查阅档案</a></li>
		<li class="active"><a id="#tab_1_2" href="#tab_1_1" data-toggle="tab">本单位档案</a></li>
	</ul>
	<div class="tab-content" style="border:none; border-top:solid 1px #e4e4e4; padding:10px 0;">
		<div class="tab-pane active" id="tab_show">
		</div>
	</div>
</div>
<script type="text/javascript" src="${path }/js/common/loading.js"></script>
<script type="text/javascript">
	var myLoading = new MyLoading("${path}",{zindex : 11111});
	var tabIndex ="#tab_1_1";
	$(function(){
		App.init();
		bdwdaLoad();
		$('a[data-toggle="tab"]').on('show.bs.tab', function (e) {

			if($(e.target).attr('id')=="#tab_1_1") {
//					$("[id='#tab_1_1']").tab('show');
				sqcyLoad();
			}else if($(e.target).attr('id')=="#tab_1_2"){
//					$("[id='#tab_1_2']").tab('show');
				bdwdaLoad();
			}
			tabIndex = $(e.target).attr('id');
		});
	});

	//申请查阅
	function sqcyLoad(){
		myLoading.show();
		$.ajax({
			url : "${path}/zzb/dzda/dak/ajax/sqcydalist",
			type : "post",
			data : {},
			dataType : "html",
			success : function(html){
				var view = $("#tab_show");
				view.html(html);
				myLoading.hide();
			},
			error : function(arg1, arg2, arg3){
				showTip("提示","基本信息加载失败");
			}
		});
	}
	//本单位档案
	function bdwdaLoad(){
		myLoading.show();
		$.ajax({
			url : "${path }/zzb/dzda/dak/ajax/bdwdalist",
			type : "post",
			data : {},
			dataType : "html",
			success : function(html){
				var view = $("#tab_show");
				view.html(html);
				myLoading.hide();
			},
			error : function(arg1, arg2, arg3){
				showTip("提示","目录材料加载失败");
			}
		});
	}

	var viewA01 = function(){
		$.ajax({
			url:"${path}/zzb/app/console/daDemo/ajax/viewA01",
			type : "post",
			data: {},
			headers:{
				OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
			},
			dataType : "html",
			success : function(html){
				$('#viewA01Div').html(html);

				$('#viewA01Modal').modal({
					keyboard: true
				});
			},
			error : function(){
				showTip("提示","出错了请联系管理员", 1500);
			}
		});
	}

	function fileDown(type) {
		window.open("${path }/zzb/app/console/daDemo/ajax/down?type="+type);
	}

</script>
</body>
</html>
