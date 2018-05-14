<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/inc/servlet.jsp" %>
<%@include file="/WEB-INF/jsp/inc/taglib.jsp" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>

			<div id="viewDiv" style="overflow: auto;margin: 0px;padding: 20px 20px;height: 200px;line-height:200px">
				<c:if test="${empty e01z1Id}">
					<table style="width: 100%">
						<tr>
							<td style="text-align:center;width: 100%">
								<font size="5"><b>请 点 击 左 侧 目 录 下 的 材 料 查 看 档 案 图 片 </b></font>
							</td>
						</tr>
					</table>
				</c:if>
				<c:if test="${not empty e01z1Id}">
					<table  id="jsonDataFormTable">
						<tr >
							<td>
								<img src="${path }/images/a38Img/010011.jpg" style="width: 99%">
							</td>
						</tr>
						<tr style="text-overflow:ellipsis;" id="jsonDataForm_${status.index}">
							<td style="padding-top: 20px;">
								<img src="${path }/images/a38Img/041011.jpg" style="width: 99%">
							</td>
						</tr>
						<tr style="text-overflow:ellipsis;" id="jsonDataForm_${status.index}">
							<td style="padding-top: 20px;">
								<img src="${path }/images/a38Img/050011.png" style="width: 99%">
							</td>
						</tr>
						<tr style="text-overflow:ellipsis;" id="jsonDataForm_${status.index}">
							<td style="padding-top: 20px;">
								<img src="${path }/images/a38Img/060011.jpg" style="width: 99%">
							</td>
						</tr>
					</table>
				</c:if>
			</div>


<script type="text/javascript">
	$(function(){
		changeTreeDivHeight();
		//当浏览器大小改变的时候,要重新计算
		$(window).resize(function(){
			changeTreeDivHeight();
		})
	});

	function changeTreeDivHeight(){
		var listHeight = $(window).height()-150;
		$("#viewDiv").css('height',listHeight);
//			$("#viewList").css('height',listHeight);
//			$(".main_left").height(mainHeight);

	}
</script>