<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
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
<div id="gjcxModal" class="modal container hide fade" tabindex="-1" data-width="85%">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button data-dismiss="modal" class="close"  type="button"></button>
				<h3 class="modal-title" id="gjcxTitle" >
					高级查询条件设置
				</h3>
			</div>
			<div class="modal-body" id="gjcxDiv">
			</div>
		</div>
	</div>
</div>
<div id="viewA01Modal" class="modal container hide fade" tabindex="-1" data-width="600">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button data-dismiss="modal" class="close"  type="button"></button>
				<h3 class="modal-title" id="addTitle" >
					档案对应的干部信息
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

<div class="portlet-title">
	<div class="caption">姓名：${a0101}</div>
	<div class="clearfix fr">
		<button type="button" class="btn green" onclick="formSave('0')"><i class="icon-question-sign"></i> 待审 </button>
		<button type="button" class="btn green" onclick="formSave('1')"><i class="icon-ok"></i> 入库 </button>
		<%--<div class="btn-group" style="padding-bottom: 0px">--%>
			<%--<a class="btn green dropdown-toggle" data-toggle="dropdown" href="#">--%>
			<%--干部库 <i class="icon-angle-down"></i>--%>
			<%--</a>--%>
			<%--<ul class="dropdown-menu">--%>
				<%--<li >--%>
				<%--<a onclick="viewA01()">查看干部信息</a>--%>
				<%--</li>--%>
				<%--<li>--%>
				<%--<a onclick="selectTyle('kccl')">提取干部信息</a>--%>
				<%--</li>--%>
				<%--<li>--%>
				<%--<a onclick="selectTyle('dascqk')">取消关联</a>--%>
				<%--</li>--%>
			<%--</ul>--%>
		<%--</div>--%>
		<a  class="btn green" href="javascript:delA38ByManage()"><i class="icon-remove"></i>删除</a>
		<%--<a  class="btn green" href="javascript:zhuanchu()"><i class="icon-share-alt"></i>转递</a>--%>
		<div class="btn-group" style="padding-bottom: 0px">
			<a class="btn green dropdown-toggle" data-toggle="dropdown" href="#">
			下载<i class="icon-angle-down"></i>
			</a>
			<ul class="dropdown-menu">
				<li >
				<a onclick="fileDown('allDa')">整本档案下载(含图片)</a>
				</li>
				<li >
				<a onclick="fileDown('danganxiazai')">电子表格目录</a>
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

<!--BEGIN TABS-->
<div class="tabbable tabbable-custom" style="margin-bottom: 0px">
	<ul class="nav nav-tabs" style="font-size: 14px;font-weight: bold;" id="tabs">
		<li class="active"><a id="#tab_1_1" href="#tab_1_1" data-toggle="tab">基本信息</a></li>
		<li ><a id="#tab_1_2" href="#tab_1_1" data-toggle="tab">目录信息</a></li>
		<li><a id="#tab_1_3" href="#tab_1_1" data-toggle="tab">欠缺材料信息</a></li>
		<li><a id="#tab_1_4" href="#tab_1_1" data-toggle="tab">职务变动</a></li>
		<li><a id="#tab_1_5" href="#tab_1_1" data-toggle="tab">工资变动</a></li>
		<li><a id="#tab_1_6" href="#tab_1_1" data-toggle="tab">材料接收</a></li>
	</ul>
	<div class="tab-content" style="border:none; border-top:solid 1px #e4e4e4; padding:5px 0;">
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
		baseLoad();
		$('a[data-toggle="tab"]').on('show.bs.tab', function (e) {

			if($(e.target).attr('id')!='#tab_1_1'&& tabIndex=="#tab_1_1"){
				var bool = a38Form.form();
				if(bool){
					myLoading.show();
					$.ajax({
						url : "${path }/zzb/dzda/a38/update",
						type : "post",
						data : $("#a38Form").serialize(),
						headers:{
							OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
						},
						dataType : "json",
						success : function(json){
							if(json.code==1){
								myLoading.hide();
								tabIndex = $(e.target).attr('id');
								if($(e.target).attr('id')=="#tab_1_2"){
									$("[id='#tab_1_2']").tab('show');
									mlLoad();
								}else if($(e.target).attr('id')=="#tab_1_3"){
									$("[id='#tab_1_3']").tab('show');
									qqclLoad();
								}else if($(e.target).attr('id')=="#tab_1_4"){
									$("[id='#tab_1_4']").tab('show');
									zwbdLoad();
								}else if($(e.target).attr('id')=="#tab_1_5"){
									$("[id='#tab_1_5']").tab('show');
									gzbdLoad();
								}else if($(e.target).attr('id')=="#tab_1_6"){
									$("[id='#tab_1_6']").tab('show');
									cljsLoad();
								}
							}else{
								$("[id='#tab_1_1']").tab('show');
								myLoading.hide();
								showTip("提示", json.message, 2000);
								return false;
							}
						},
						error : function(){
							$("[id='#tab_1_1']").tab('show');
							showTip("提示","出错了,请检查网络!",2000);
							myLoading.hide();
							return false;
						}
					});
				}else{
					$("[id='#tab_1_1']").tab('show');
					return false;
				}
			}else{
				if($(e.target).attr('id')=="#tab_1_1") {
//					$("[id='#tab_1_1']").tab('show');
					baseLoad();
				}else if($(e.target).attr('id')=="#tab_1_2"){
//					$("[id='#tab_1_2']").tab('show');
					mlLoad();
				}else if($(e.target).attr('id')=="#tab_1_3"){
//					$("[id='#tab_1_3']").tab('show');
					qqclLoad();
				}else if($(e.target).attr('id')=="#tab_1_4"){
//					$("[id='#tab_1_4']").tab('show');
					zwbdLoad();
				}else if($(e.target).attr('id')=="#tab_1_5"){
			//		$("[id='#tab_1_5']").tab('show');
					gzbdLoad();
				}else if($(e.target).attr('id')=="#tab_1_6"){
			//		$("[id='#tab_1_6']").tab('show');
					cljsLoad();
				}
				tabIndex = $(e.target).attr('id');
			}

		});
//		$("#tabs li a").each(function(){
//			$(this).click(function(){
//				if($(this).attr("id")=="#tab_1_1"){
//					$("[id='#tab_1_1']").tab('show');
//					baseLoad();
//				}else if($(this).attr("id")=="#tab_1_2"){
//					$("[id='#tab_1_2']").tab('show');
//					mlLoad();
//				}else if($(this).attr("id")=="#tab_1_3"){
//					$("[id='#tab_1_3']").tab('show');
//					qqclLoad();
//				}else if($(this).attr("id")=="#tab_1_4"){
//					$("[id='#tab_1_3']").tab('show');
//					zwbdLoad();
//				}
//			});
//		});

	});


	//基本信息
	function baseLoad(){
		myLoading.show();
		$.ajax({
			url : "${path}/zzb/dzda/a38/ajax/edit",
			type : "post",
			data : {"id":"${id}"},
			dataType : "html",
			headers:{
				OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
			},
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
	function mlLoad(){
		$.ajax({
			url : "${path }/zzb/dzda/e01z1/ajax/mlxxManage",
			type : "post",
			data : {"a38Id":"${id}"},
			dataType : "html",
			headers:{
				OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
			},
			success : function(html){
				var view = $("#tab_show");
				view.html(html);
			},
			error : function(arg1, arg2, arg3){
				showTip("提示","目录材料加载失败");
			}
		});
	}

	function qqclLoad(){
		$.ajax({
			url : "${path }/zzb/dzda/e01z4/ajax/list",
			type : "post",
			data : {"a38Id":"${id}"},
			dataType : "html",
			headers:{
				OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
			},
			success : function(html){
				var view = $("#tab_show");
				view.html(html);
			},
			error : function(arg1, arg2, arg3){
				showTip("提示","欠缺材料加载失败");
			}
		});
	}
	function zwbdLoad(){
		$.ajax({
			url : "${path }/zzb/dzda/a52/ajax/list",
			type : "post",
			data : {"a38Id":"${id}"},
			dataType : "html",
			headers:{
				OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
			},
			success : function(html){
				var view = $("#tab_show");
				view.html(html);
			},
			error : function(arg1, arg2, arg3){
				showTip("提示","职务变动加载失败");
			}
		});
	}

	function gzbdLoad(){
		$.ajax({
			url : "${path }/zzb/dzda/a32/ajax/list",
			type : "post",
			data : {"a38Id":"${id}"},
			dataType : "html",
			headers:{
				OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
			},
			success : function(html){
				var view = $("#tab_show");
				view.html(html);
			},
			error : function(arg1, arg2, arg3){
				showTip("提示","工资变动加载失败");
			}
		});
	}
	function cljsLoad(){
		$.ajax({
			url : "${path }/zzb/dzda/e01z2/ajax/list",
			type : "post",
			data : {"a38Id":"${id}"},
			dataType : "html",
			headers:{
				OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
			},
			success : function(html){
				var view = $("#tab_show");
				view.html(html);
			},
			error : function(arg1, arg2, arg3){
				showTip("提示","接收材料加载失败");
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
		if(type=="danganxiazai"){
			window.open("${path}/zzb/dzda/a38/download/${id}?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}");
		}
		if(type=="dangantupianxiazai"){
			window.open("${path}/zzb/dzda/mlcl/tpcl/download/${id}?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}");
		}
		<%--window.open("${path }/zzb/app/console/daDemo/ajax/down?type="+type);--%>
	}

	function formSave(sjzt){
		var bool = true;
		var isSave = false;
		if($("#tabs li[class='active']").find("a").attr("id")=="#tab_1_1"){
			isSave = true;
			bool = a38Form.form();
		}
		if(isSave == true){
			if(bool){
				$("#sjzt").val(sjzt);
				saveA38(sjzt);
			}
		}else{
			myLoading.show();
			$.ajax({
				url : "${path }/zzb/dzda/a38/update/Sjzt",
				type : "post",
				data : {
					"a38Ids":"${id}",
					"sjzt":sjzt
				},
				headers:{
					OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
				},
				dataType : "json",
				success : function(json){
					if(json.code==1){
						myLoading.hide();
						if(sjzt=="0"){
							window.location.href = "${path}/zzb/dzda/a38/shList?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}"
						}else{
							window.location.href = "${path}/zzb/dzda/a38/list?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}"
						}
					}else{
						myLoading.hide();
						showTip("提示", json.message, 2000);
						return false;
					}
				},
				error : function(){
					showTip("提示","出错了,请检查网络!",2000);
					myLoading.hide();
					return false;
				}
			});
		}
	}

	function saveA38(sjzt){
		myLoading.show();
		$.ajax({
			url : "${path }/zzb/dzda/a38/update",
			type : "post",
			data : $("#a38Form").serialize(),
			headers:{
				OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
			},
			dataType : "json",
			success : function(json){
				if(json.code==1){
					myLoading.hide();
					if(sjzt=="0"){
						window.location.href = "${path}/zzb/dzda/a38/shList?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}"
					}else{
						window.location.href = "${path}/zzb/dzda/a38/list?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}"
					}
				}else{
					myLoading.hide();
					showTip("提示", json.message, 2000);
					return false;
				}
			},
			error : function(){
				showTip("提示","出错了,请检查网络!",2000);
				myLoading.hide();
				return false;
			}
		});
	}

	var delA38ByManage = function(){
		var id = "${id}";
		var itemName = "${a0101}";
		actionByConfirm1(itemName, "${path}/zzb/dzda/a38/delete/" + id,{} ,function(data,status){
			if (data.code == "1") {
				showTip("提示","删除成功", 2000);
				setTimeout(function(){window.location.href = "${path}/zzb/dzda/a38/list?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}"},2000);
			}else{
				showTip("提示", data.message, 2000);
			}
		});
	};

	function cancel(){
		if("${listType}"=="shList"){
			window.location.href = "${path}/zzb/dzda/a38/shList?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}";
		}else{
			window.location.href = "${path}/zzb/dzda/a38/list?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}";
		}
	}
</script>
</body>
</html>
