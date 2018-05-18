<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/inc/servlet.jsp" %>
<%@include file="/WEB-INF/jsp/inc/taglib.jsp" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<script type="text/javascript">
	window.PATH = "${path}";
</script>
<style>
	.dropdownMob{ position:absolute; top:5px; right:5px; display:none;}
	.dropdownMob .dropdown-menu{ left:-93px; min-width: 120px;}
	.btn.downMobBtn{ padding:3px 6px; background-color:#FFFFFF; border:solid 1px #e5e5e5;}
	.btn.downMobBtn:hover{ background-color:#FFFFFF !important;}
</style>
<link href="${path}/css/images-view/images-grid.css" rel="stylesheet" type="text/css"/>

<form action="" class="form-horizontal" id="importTpForm" method="post" enctype="multipart/form-data">
	<input class="file_progress" type="file" name="tpFile" id="tpFile" accept = 'image/*'>
	<input type="hidden" id="curImgNo" name="curImgNo" value="">
	<input type="hidden" id="uploadType" name="uploadType" value="">
</form>
	<div id="viewDiv" style="overflow: auto;margin: 0px;">
		<c:if test="${empty e01z1Id}">
			<table style="width: 100%;height: 100%;">
				<tr>
					<td style="text-align:center;width: 100%">
						<font size="5"><b>请 点 击 左 侧 目 录 下 的 材 料 查 看 档 案 图 片 </b></font>
					</td>
				</tr>
			</table>
		</c:if>
		<c:if test="${not empty e01z1Id}">
			<c:if test="${imagesSize==0}">
				<table style="width: 100%;height: 100%;">
					<tr >
						<td style="text-align:center;width: 100%;">
							<font size="5"><b>此 份 材 料 未 加 载 图 片 </b></font>
						</td>
					</tr>
				</table>
			</c:if>
			<c:if test="${imagesSize>=0}">
				<div id="gallery1"></div>
				<%--<table  id="jsonDataFormTable" width="100%">--%>

					<%--<c:forEach items="${eImages}" var="image">--%>
					<%--<tr >--%>
						<%--<td style="width:100%;text-align: center">--%>
							<%--<img src="${path}/zzb/dzda/mlcl/images/showImages?a38Id=${a38Id}&imgId=${image}&OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}">--%>
						<%--</td>--%>
					<%--</tr>--%>
					<%--</c:forEach>--%>
				<%--</table>--%>
			</c:if>
		</c:if>
	</div>

<script src="${contextPath}/js/images-view/images-grid.js" charset=“utf-8”></script>
<script type="text/javascript">
	var imgs = [];
	var isManage = "false";
	var myLoading = new MyLoading("${path}",{zindex:20000});
	$(function(){
		changeTreeDivHeight();
		//当浏览器大小改变的时候,要重新计算
		$(window).resize(function(){
			changeTreeDivHeight();
		})
		var images = "${images}";
		if(images!=null && images!="") {
			var imgList = images.replace('[','').replace(']','').split(',');
			for (var i = 0; i < imgList.length; i++) {  //循环LIST
				var imgId = imgList[i].split(";")[0];
				var imgNo  = imgList[i].split(";")[1];
				imgs[i] ={ src: "/zzb/dzda/mlcl/images/showImages?a38Id=${a38Id}&imgId="+imgId+"&OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}",imgId:imgId,alt:imgNo+"/${imagesSize}", title:imgNo};
			}
		}
		$('#gallery1').imagesGrid({
			images:imgs
		});
		isManage = "${isManage}";
		$(".image-wrap").hover(function(){
			if(isManage=="true"){
				$(this).find('.dropdownMob').show();
			}
		},function(){
			if(isManage=="true") {
				$(this).find('.dropdownMob').hide();
			}
		});

		if($("#showTpWidth").val()!=""){
			$(".imgs-grid-image").css("width",$("#showTpWidth").val()+"%");

			if($("#showTpWidth").val()=="100"){
				$(".imgs-grid").css("text-align","center");
			}else{
				$(".imgs-grid").css("text-align","left");
			}
		}
		$("#tpFile").bind("change",function(evt){
			if($(this).val()){
				tpscSubmit();
			}
			$(this).val('');
		});

		function tpscSubmit(){
			var fileInput = document.getElementById("tpFile");
			if(fileInput.files.length>0){

			}else{
				showTip("提示","请选择上传图片",2000);
				return;
			}
			$("#importTpForm").ajaxSubmit({
				url : "${path }/zzb/dzda/mlcl/images/uploadImg?e01z1Id=${e01z1Id}",
				type : "post",
				headers:{
					OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
				},
				beforeSend:function(XHR){
					myLoading.show();
				},
				success : function(json){
					if(json.success){
						myLoading.hide();
						showTip("提示","图片上传成功",2000);
						setTimeout(function(){
							loadRight("${e01z1Id}")
						},2000)
					}else{
						myLoading.hide();
						showTip("提示","出错了,请检查网络!",500);
					}
				},
				error : function(arg1, arg2, arg3){
					showTip("提示","出错了,请检查网络!",500);
				},
				complete : function(XHR, TS){
					myLoading.hide();
				}
			});
		}
	});

	function changeTreeDivHeight(){
		var listHeight = $(window).height()-65;
		$("#viewDiv").css('height',listHeight);
//			$("#viewList").css('height',listHeight);
//			$(".main_left").height(mainHeight);

	}

	function changeViewType(width){
		$("#showTpWidth").val(width);
		$(".imgs-grid-image").css("width",width+"%");

		if(width=="100"){
			$(".imgs-grid").css("text-align","center");
		}else{
			$(".imgs-grid").css("text-align","left");
		}
	}
	//uploadType 上传方式 frist表示插入首页，up表示插入上一页 down表示下一页 end表示尾页
	function uploadImageByOne(imgNo,uploadType){
		$("#curImgNo").val(imgNo);
		$("#uploadType").val(uploadType);
		document.getElementById("tpFile").click();
//		alert(imgNo);
//		alert(uploadType);
	}

	//调整图片顺序
	function updateImgNo(imgId,imgNo){

	}
	//删除图片
	function deleteImg(imgId,imgNo){

	}
</script>