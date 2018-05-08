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
	<link rel="stylesheet" href="${path }/css/DT_bootstrap.css" />
	<link rel="stylesheet" type="text/css" href="${path }/css/bootstrap-fileupload.css">

	<title>查阅电子档案</title>
</head>
<body>
<div id="addModal" class="modal container hide fade" tabindex="-1" data-width="600">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button data-dismiss="modal" class="close"  type="button"></button>
				<h3 class="modal-title" id="addTitle" >
					档案阅档申请
				</h3>
			</div>
			<div class="modal-body" id="addDiv">
			</div>
		</div>
	</div>
</div>
<div class="container-fluid">

	<div class="row-fluid">
		<div class="portlet box grey ">
			<div class="relationbetTop" style="height: 10px">
				<div class="relationbetTop_left">查阅可查阅的档案</div>
				<div class="relationbetTop_but">
					<a id="sample_editable_1_new" class="btn green" href="javascript:add()">
						阅档申请
					</a>
				</div>
			</div>
			<div class="tabbable tabbable-custom">
				<ul class="nav nav-tabs" style="font-size: 14px;font-weight: bold;" id="tabs">
					<li class="active"><a id="#tab_1_1" href="#tab_1_1" data-toggle="tab">查阅申请档案</a></li>
					<li ><a id="#tab_1_2" href="#tab_1_1" data-toggle="tab">查阅外单位授权档案</a></li>
				</ul>
				<div class="tab-content" style="border:none; border-top:solid 1px #e4e4e4; padding:10px 0;">
					<div class="tab-pane active" id="tab_show">
					</div>
				</div>
			</div>
		</div>
	</div>
	<%-- END PAGE CONTENT--%>
</div>

<script type="text/javascript" src="${path }/js/common/loading.js"></script>
<script src="${path }/js/bootstrap-fileupload.js"></script>
<script src="${path }/js/bootstrap-fileupload.js"></script>
<script type="text/javascript">
	$(function(){
		App.init();
//		var bzCount=0;
//		var zwCount=0;
		cysqdaLoad();

		var ciFlag=true;

//		$('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
//			if($(e.target).attr('id')!='#tab_1_1'){
////				if(!ciFlag){
//					$("[id='#tab_1_1']").tab('show');
//					return;
////				}
//			}
//
//		});



		$("#tabs li a").each(function(){
			$(this).click(function(){
				if($(this).attr("id")=="#tab_1_1"){
					$("[id='#tab_1_1']").tab('show');
					cysqdaLoad();
				}else if($(this).attr("id")=="#tab_1_2"){
					$("[id='#tab_1_2']").tab('show');
					cyshouquandaLoad();
				}

			});
		});

	});

	function cancel(){
		var loadType = "${loadType}";
		if(loadType=="zcManageList"){
			window.location.href = "${path}/zzb/app/console/daDemo/zcManageList";
		}else{
			window.location.href = "${path}/zzb/app/console/daDemo/list";
		}


	}

	//基本信息
	function cysqdaLoad(){
		$.ajax({
			url : "${path }/zzb/app/console/daDemo/ajax/cysqdaList",
			type : "post",
			data : {"parentId":"${parentId}","id":"${ciId}"},
			dataType : "html",
			success : function(html){
				var view = $("#tab_show");
				view.html(html);
			},
			error : function(arg1, arg2, arg3){
				showTip("提示","基本信息数据失败");
			}
		});
	}
	function cyshouquandaLoad(){
		$.ajax({
			url : "${path }/zzb/app/console/daDemo/ajax/cyshouquandaList",
			type : "post",
			data : {"parentId":"${parentId}","id":"${ciId}"},
			dataType : "html",
			success : function(html){
				var view = $("#tab_show");
				view.html(html);
			},
			error : function(arg1, arg2, arg3){
				showTip("提示","基本信息数据失败");
			}
		});
	}



	var add = function(){
		$.ajax({
			url:"${path}/zzb/app/console/daDemo/ajax/addApplyDa",
			type : "post",
			data: {},
			headers:{
				OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
			},
			dataType : "html",
			success : function(html){
				$('#addDiv').html(html);

				$('#addModal').modal({
					keyboard: true
				});
			},
			error : function(){
				showTip("提示","出错了请联系管理员", 1500);
			}
		});
	}

</script>
</body>
</html>
