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

	<title>电子档案系统</title>
</head>
<body>
<div id="viewA01Modal" class="modal container hide fade" tabindex="-1" data-width="600">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button data-dismiss="modal" class="close"  type="button"></button>
				<h3 class="modal-title" id="addTitle" >
					“红叶专”档案对应的干部信息
				</h3>
			</div>
			<div class="modal-body" id="viewA01Div">
			</div>
		</div>
	</div>
</div>

<div id="zhuanchuModal" class="modal container hide fade" tabindex="-1" data-width="600">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button data-dismiss="modal" class="close"  type="button"></button>
				<h3 class="modal-title"  >
					转出档案
				</h3>
			</div>
			<div class="modal-body" id="zhuanchuDiv">
			</div>
		</div>
	</div>
</div>

<div class="row-fluid ">

	<div class="span12" style=" width: 100%;">
		<!-- BEGIN INLINE TABS PORTLET-->
		<div class="portlet box grey">
			<div class="portlet-title">
				<div class="caption">姓名：红叶专</div>
				<div class="relationbetTop_but">
					<button type="button" class="btn green" onclick=""><i class="icon-ok"></i> 保存 </button>&nbsp;
					<div class="btn-group" style="padding-bottom: 0px">
						<a class="btn green dropdown-toggle" data-toggle="dropdown" href="#">
						干部库 <i class="icon-angle-down"></i>
						</a>
						<ul class="dropdown-menu">
							<li >
							<a onclick="viewA01()">查看干部信息</a>
							</li>
							<li>
							<a onclick="selectTyle('kccl')">提取干部信息</a>
							</li>
							<li>
							<a onclick="selectTyle('dascqk')">取消关联</a>
							</li>
						</ul>
					</div>
					<a  class="btn green" href="#">删除</a>
					<a  class="btn green" href="javascript:zhuanchu()">转出</a>&nbsp;
					<div class="btn-group" style="padding-bottom: 0px">
						<a class="btn green dropdown-toggle" data-toggle="dropdown" href="#">
						下载<i class="icon-angle-down"></i>
						</a>
						<ul class="dropdown-menu">
							<li >
							<a onclick="fileDown('allDa')">整本档案下载(含图片)</a>
							</li>
							<li >
							<a onclick="fileDown('dianzibiaogemulu')">电子表格目录</a>
							</li>
							<li>
							<a onclick="fileDown('qianquecail')">欠缺材料信息</a>
							</li>
							<li>
							<a onclick="fileDown('dangantupianxiazai')">档案图片下载</a>
							</li>
						</ul>
					</div>
					<a class="btn"  onclick="cancel()"><i class="icon-remove-sign"></i> 取消</a>
				</div>
			</div>

			<div class="portlet-body">
				<div class="row-fluid">
					<!--BEGIN TABS-->
					<div class="tabbable tabbable-custom">
						<ul class="nav nav-tabs" style="font-size: 14px;font-weight: bold;" id="tabs">
							<li class="active"><a id="#tab_1_1" href="#tab_1_1" data-toggle="tab">基本信息</a></li>
							<li ><a id="#tab_1_2" href="#tab_1_1" data-toggle="tab">目录信息</a></li>
							<li><a id="#tab_1_3" href="#tab_1_1" data-toggle="tab">欠缺材料信息</a></li>
							<li><a id="#tab_1_4" href="#tab_1_1" data-toggle="tab">职务变动</a></li>
							<li><a id="#tab_1_5" href="#tab_1_1" data-toggle="tab">工资变动</a></li>
							<li><a id="#tab_1_6" href="#tab_1_1" data-toggle="tab">材料接收</a></li>
						</ul>
						<div class="tab-content" style="border:none; border-top:solid 1px #e4e4e4; padding:10px 0;">
							<div class="tab-pane active" id="tab_show">
							</div>
						</div>
					</div>
				</div>
			</div>
		<!-- END INLINE TABS PORTLET-->
	</div>
</div>
<script type="text/javascript" src="${path }/js/common/loading.js"></script>
<script src="${path }/js/bootstrap-fileupload.js"></script>
<script src="${path }/js/bootstrap-fileupload.js"></script>
<script type="text/javascript">
	$(function(){
		App.init();
		baseLoad();
		$("#tabs li a").each(function(){
			$(this).click(function(){
				if($(this).attr("id")=="#tab_1_1"){
					$("[id='#tab_1_1']").tab('show');
					baseLoad();
				}else if($(this).attr("id")=="#tab_1_2"){
					$("[id='#tab_1_2']").tab('show');
					mlLoad();
				}else if($(this).attr("id")=="#tab_1_3"){
					$("[id='#tab_1_3']").tab('show');
					zjclLoad();
				}else if($(this).attr("id")=="#tab_1_4"){
					$("[id='#tab_1_3']").tab('show');
					zwbdLoad();
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
	function baseLoad(){
		$.ajax({
			url : "${path }/zzb/app/console/daDemo/ajax/base",
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
	function mlLoad(){
		$.ajax({
			url : "${path }/zzb/app/console/daDemo/ajax/mlxxManage",
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

	function zjclLoad(){
		$.ajax({
			url : "${path }/zzb/app/console/daDemo/ajax/zjclList",
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
	function zwbdLoad(){
		$.ajax({
			url : "${path }/zzb/dzda/a52/list",
			type : "post",
			data : {"ciId":"${ciId}"},
			dataType : "html",
			success : function(html){
				var view = $("#tab_show");
				view.html(html);
			},
			error : function(arg1, arg2, arg3){
				showTip("提示","职务变动加载失败");
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
	var zhuanchu = function(){
		$.ajax({
			url:"${path}/zzb/app/console/daDemo/ajax/zhuanchu",
			type : "post",
			data: {},
			headers:{
				OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
			},
			dataType : "html",
			success : function(html){
				$('#zhuanchuDiv').html(html);

				$('#zhuanchuModal').modal({
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
