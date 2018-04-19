<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/inc/servlet.jsp" %>
<%@include file="/WEB-INF/jsp/inc/taglib.jsp" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>修改机构</title>
</head>
<body>
<div class="container-fluid">

	<div class="row-fluid">
		<div class="portlet box grey ">
			<div class="portlet-body">
				<div class="relationbetTop">
					<div class="relationbetTop_left">修改机构</div>
					<div class="relationbetTop_but">

						<button type="button" class="btn green" onclick=""><i class="icon-ok"></i> 确定</button>
						<a class="btn"  onclick="cancel()"><i class="icon-remove-sign"></i> 取消</a>
					</div>
				</div>
				<div class="tabbable tabbable-custom">
					<ul class="nav nav-tabs" style="font-size: 14px;font-weight: bold;" id="tabs">
						<li class="active"><a id="#tab_1_1" href="#tab_1_1" data-toggle="tab">基本信息</a></li>
						<li ><a id="#tab_1_2" href="#tab_1_1" data-toggle="tab">编制情况</a></li>
						<li><a id="#tab_1_3" href="#tab_1_1" data-toggle="tab">职务管理</a></li>

					</ul>
					<div class="tab-content" style="border:none; border-top:solid 1px #e4e4e4; padding:10px 0;">
						<div class="tab-pane active" id="tab_show">
						</div>
					</div>
				</div>
			</div>
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
	$(function(){
		App.init();
//		var bzCount=0;
//		var zwCount=0;
		baseLoad();

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
				if($(this).attr("id")=="#tab_1_2"){
					$("[id='#tab_1_2']").tab('show');
					bzLoad();
				}else if($(this).attr("id")=="#tab_1_3"){
					$("[id='#tab_1_3']").tab('show');
					zwLoad();
				}else if($(this).attr("id")=="#tab_1_1"){
					$("[id='#tab_1_1']").tab('show');
					baseLoad();
				}
			});
		});

	});

	function cancel(){
			window.location.href = "${path}/zzb/app/console/bset/";

	}

	//基本信息
	function baseLoad(){
		$.ajax({
			url : "${path }/zzb/app/console/bset/ajax/addOrEdit",
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
	//资产
	function bzLoad(){
		$.ajax({
			url : "${path}/zzb/app/console/bset/Bz/ajax/list",
			type : "post",
			data : {},
			dataType : "html",
			success : function(html){
					var view = $("#tab_show");
					view.html(html);

			},
			error : function(arg1, arg2, arg3){
				showTip("提示","加载资产数据失败");
			}
		});
	}



	//连接方式
	function zwLoad(){
		$.ajax({
			url : "${path}/zzb/app/console/bset/b09/ajax/list",
			type : "get",
			data : {},
			dataType : "html",
			success : function(html){
				var view = $("#tab_show");
				view.html(html);
			},
			error : function(arg1, arg2, arg3){
				showTip("提示","加载连接方式数据失败");
			}
		});
	}


	var myLoading = new MyLoading('${path}',{zindex:11111});
	function formAdd(){
		var bool = true;
		var booAsset = true;

		if($("#tabs li[class='active']").find("a").attr("id")=="#tab_1_1"){
			bool = form1.form();
		}

		if($("#tabs li[class='active']").find("a").attr("id")=="#tab_1_2"){
			booAsset = assetForm.form();
		}
		if(bool && booAsset){
			myLoading.show();
			$("[id='sacmAssetVo.manufacturerInfoId']").attr("disabled",false);
			$.ajax({
				url : "${path }/sacm/ci/save",
				type : "post",
				data : $("#form1").serialize()+"&"+$("#assetForm").serialize(),
				dataType : "json",
				success : function(json){
					if(json.code==1){
						myLoading.hide();
						showTip("提示","操作成功",1000);
						//setTimeout(cancel,1000);
						setTimeout(function(){window.location.href = "${path}/sacm/ci/detailCi?type=${type}&objectId="+json.ciObjectId+"&id="+json.ciId+"&ciName="+json.ciName},2000);
					}else{
						myLoading.hide();
						showTip("提示", json.message, 2000);
					}
				},
				error : function(){
					showTip("提示","出错了请联系管理员",2000);
					myLoading.hide();
				}
			});
		}
	}

</script>
</body>
</html>
