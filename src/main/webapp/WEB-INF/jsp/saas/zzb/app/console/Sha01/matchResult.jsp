<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/inc/servlet.jsp" %>
<%@include file="/WEB-INF/jsp/inc/taglib.jsp" %>
<style>
	.pipeititle{ background-color:#f5f6fa; height:32px; line-height:32px; text-align:center; border:solid 1px #e1e6eb; font-weight:bold;}
	.tabpipeiSuccess{}
	.tabpipeiSuccess tr td{ padding:5px 5px; border:solid 1px #e1e6eb;}
	.tabpipeiSuccess tr th{ padding:5px 5px; border:solid 1px #e1e6eb; border-top:none; }
	.tabpipeiSuccess tr:first-child td{ border-top:none;}
	.tabpipeifailure tr td{padding:5px 5px;  border:solid 1px #e1e6eb; border-top:none; border-right:none;}
	.tabpipeifailure tr th{ padding:5px 5px; border:solid 1px #e1e6eb; border-top:none;border-right:none; }
</style>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<div id="form1Group" class="control-group">
	<div class="portlet-body">
		<form class="form-horizontal" id="form1">
			<div class="actions fr" style="margin-bottom: 5px;">
				<input type="hidden" name="shpcId"  value="${shpcId }"/>
				<input type="hidden" name="uploadMatchingMode"  value="${uploadMatchingMode }"/>
				<input type="hidden" name="split"  value="${split }"/>
				<input type="hidden" name="tmpFilePath"  value="${tmpFilePath }"/>

				<button type="button" class="btn green"  onclick="subimtCiObjectAttrExt()" id="listBtnOk"><i class="icon-ok"></i> 确定</button>
				<button type="button" class="btn btn-default" data-dismiss="modal"><i class='icon-remove-sign'></i> 关闭</button>
			</div>

			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tbody>
				<tr>
					<td>上传文件：${fileCount}个</td>
					<td>匹配成功：${matchCount}个</td>
					<td>未匹配：${nomatchCount}个</td>
				</tr>
				</tbody>
			</table>
			<div class="row-fluid mt20">
				<div class="" style="width:62%; float:left; border-bottom:solid 1px #e1e6eb;">
					<div class="pipeititle" style="">匹配成功文件</div>
					<div style="max-height:215px; overflow: auto;">
						<table width="100%" border="0" cellspacing="0" cellpadding="0" class="tabpipeiSuccess">
							<thead>
								<tr>
									<th >姓名</th>
									<th width="50%">文件名</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="map" items="${matchResult}">
									<tr>
										<td><c:out value="${map.key}"/></td>
										<td><c:out value="${map.value}"/></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
				<div class="" style="width:32%; float:right; border-bottom:solid 1px #e1e6eb;border-right:solid 1px #e1e6eb;">
					<div class="pipeititle" style="border-right: none;">未匹配文件</div>
					<div style="max-height:215px; overflow: auto;">
						<table width="100%" border="0" cellspacing="0" cellpadding="0" class="tabpipeifailure">
							<%--<thead>--%>
							<%--<tr>--%>
								<%--<th>文件名</th>--%>
							<%--</tr>--%>
							<%--</thead>--%>
							<tbody>
								<c:forEach var="filename" items="${noMatchFilenames}">
									<tr>
										<td>${filename}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</form>
	</div>

</div>
<script type="text/javascript" src="${path }/js/common/30CloudAjax.js"></script>
<script>
	function subimtCiObjectAttrExt(){
		$.cloudAjax({
			async : true,
			path : '${path}',
			url : '${path}/zzb/app/Sha01/${urlValue}/ajax/batch/match/save',
			type : 'post',
			data : $("#form1").serialize(),
			headers:{
				OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
			},
			dataType : 'json',
			success : function(data){
				if(data.success == false){
					showTip("提示","出错了,请检查网络!",2000);
				}else{
					$('#matchResultModal').modal('hide');
					showTip("提示","上传成功",2000);
					searchSubmit();
//						('#jsonDataModal').modal('hide');
					<%--//setTimeout(function(){$('#myModal2').modal('hide')},2000);--%>
					<%--ObjectAttr.init();--%>
					<%--//$("#attributeList").load("${path}/sacm/objectAttribute/ajax/objectAttributeManage?ciObjectId="+ciObjectId);--%>
				}
			},
			error : function(){
				showTip("提示","出错了,请检查网络!",2000);
			}
		});
	}
</script>