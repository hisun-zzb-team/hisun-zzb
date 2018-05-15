<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/inc/servlet.jsp" %>
<%@include file="/WEB-INF/jsp/inc/taglib.jsp" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>

			<div id="viewDiv" style="overflow: auto;margin: 0px;">
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
					<c:if test="${imagesSize==0}">
						<table style="width: 100%">
							<tr>
								<td style="text-align:center;width: 100%">
									<font size="5"><b>此 份 材 料 暂 时 未 加 载 图 片 </b></font>
								</td>
							</tr>
						</table>
					</c:if>
					<c:if test="${imagesSize>=0}">
						<table  id="jsonDataFormTable" width="100%">

							<c:forEach items="${images}" var="image">
							<tr >
								<td style="width:100%;text-align: center">
									<img src="${path}/zzb/dzda/mlcl/images/showImages?a38Id=${a38Id}&imgPath=${image}&OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}">
								</td>
							</tr>
							</c:forEach>
						</table>
					</c:if>
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
		var listHeight = $(window).height()-65;
		$("#viewDiv").css('height',listHeight);
//			$("#viewList").css('height',listHeight);
//			$(".main_left").height(mainHeight);

	}
</script>