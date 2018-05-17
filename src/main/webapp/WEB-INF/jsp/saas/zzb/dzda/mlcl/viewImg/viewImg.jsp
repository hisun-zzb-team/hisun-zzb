<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/inc/servlet.jsp" %>
<%@include file="/WEB-INF/jsp/inc/taglib.jsp" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<script type="text/javascript">
	window.PATH = "${path}";
</script>
<style>
	.dropdownMob{ position:absolute; top:10px; right:10px; display:none;}
	.dropdownMob .dropdown-menu{ left:-53px; min-width: 80px;}
	.btn.downMobBtn{ padding:3px 6px; background-color:#FFFFFF; border:solid 1px #e5e5e5;}
	.btn.downMobBtn:hover{ background-color:#FFFFFF !important;}
</style>
<link href="${path}/css/images-view/images-grid.css" rel="stylesheet" type="text/css"/>


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
				imgs[i] ={ src: "/zzb/dzda/mlcl/images/showImages?a38Id=${a38Id}&imgId="+imgId+"&OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}", alt:imgNo+"/${imagesSize}", title:'排序:'+imgNo };
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

</script>