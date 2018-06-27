<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/inc/servlet.jsp" %>
<%@include file="/WEB-INF/jsp/inc/taglib.jsp" %>
<%--
  ~ Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
  ~ http://www.hn-hisun.com
  ~ 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
  --%>

<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<script type="text/javascript">
	window.PATH = "${path}";
</script>
<style>
	.image-wrap {
		position: relative;
		display: inline-block;
		overflow: hidden;
		vertical-align: middle;
	}
	.dropdownMob{ position:absolute; top:5px; right:5px; display:none;}
	.dropdownMob .dropdown-menu{ left:-93px; min-width: 120px;}
	.dropdownMob .dropdown-menu li{left:-93px; min-width: 120px;}
	.btn.downMobBtn{ padding:3px 6px; background-color:#FFFFFF; border:solid 1px #e5e5e5;}
	.btn.downMobBtn:hover{ background-color:#FFFFFF !important;}
</style>
<%--<link href="${path}/css/images-view/viewer-image.css" rel="stylesheet" type="text/css"/>--%>
<%--<link href="${path}/css/images-view/main-image.css" rel="stylesheet" type="text/css"/>--%>

	<div id="viewLeftDiv" style="float: left;width:50%;overflow: auto;border-right:1px solid #d8d8d8" oncontextmenu='return false'>
		<c:if test="${empty e01z1Id}">
			<table style="width: 100%;height: 100%;">
				<tr>
					<td style="text-align:center;width: 100%">
						<font size="5"><b>请 选 择 材 料 进 行 对 比 查 看 </b></font>
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
				<div class="docs-galley">
					<ul class="docs-pictures clearfix">
						<c:forEach items="${images}" var="image">
							<li style="text-align: center;">
								<div class="image-wrap" >
									<img class="showImageClass" src="${path}/zzb/dzda/mlcl/images/showImages?a38Id=${a38Id}&imgId=${image.id}&OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}" title="${image.imgNo}" alt="${image.imgNo}/${imagesSize}">
									<span style="display: block; width: 40px; height: 20px; top: 0; left: 0; z-index: 1111; position: absolute; text-align: center; font-size: 16px; cursor: pointer; ">${image.imgNo}/${imagesSize}</span>
								</div>
							</li>
						</c:forEach>
					</ul>
				</div>
			</c:if>
		</c:if>
	</div>

	<div id="viewRightDiv" style="float: left;width:49%;overflow: auto;margin: 0px;" oncontextmenu='return false'>
		<c:if test="${empty duibiE01z1Id}">
			<table style="width: 100%;height: 100%;">
				<tr>
					<td style="text-align:center;width: 100%">
						<font size="5"><b>请 选 择 材 料 进 行 对 比 查 看 </b></font>
					</td>
				</tr>
			</table>
		</c:if>
		<c:if test="${not empty duibiE01z1Id}">
			<c:if test="${duibiImagesSize==0}">
				<table style="width: 100%;height: 100%;">
					<tr >
						<td style="text-align:center;width: 100%;">
							<font size="5"><b>此 份 材 料 未 加 载 图 片 </b></font>
						</td>
					</tr>
				</table>
			</c:if>
			<c:if test="${duibiImagesSize>=0}">
				<div class="docs-galley">
					<ul class="docs-pictures clearfix">
						<c:forEach items="${duibiImages}" var="image">
							<li style="text-align: center;">
								<div class="image-wrap" >
									<img class="showImageClass" src="${path}/zzb/dzda/mlcl/images/showImages?a38Id=${a38Id}&imgId=${image.id}&OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}" title="${image.imgNo}" alt="${image.imgNo}/${imagesSize}">
									<span style="display: block; width: 40px; height: 20px; top: 0; left: 0; z-index: 1111; position: absolute; text-align: center; font-size: 16px; cursor: pointer; ">${image.imgNo}/${duibiImagesSize}</span>
								</div>
							</li>
						</c:forEach>
					</ul>
				</div>
			</c:if>
		</c:if>
	</div>
<%--<script  type="text/javascript" src="${path }/js/images-view/images-grid.js"></script>--%>
<%--<script  type="text/javascript" src="${path }/js/images-view/viewer-image.js"></script>--%>
<%--<script  type="text/javascript" src="${path }/js/images-view/main-image.js"></script>--%>
<script type="text/javascript">
	$(function(){
		changeTreeDivHeight();
		//当浏览器大小改变的时候,要重新计算
		$(window).resize(function(){
			changeTreeDivHeight();
		})
        myLoading.hide();
	});


	function changeTreeDivHeight(){
		var listHeight = $(window).height()-65;
		$("#viewLeftDiv").css('height',listHeight);
        $("#viewRightDiv").css('height',listHeight);
	}


</script>