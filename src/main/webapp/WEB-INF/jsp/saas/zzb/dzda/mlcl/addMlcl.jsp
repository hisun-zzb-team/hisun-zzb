<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%--
  ~ Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
  ~ http://www.hn-hisun.com
  ~ 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
  --%>

<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<div >
	<!-- BEGIN FORM-->
	<div class="portlet box grey">

		<div class="portlet-title">

			<div class="caption">

				<i class="icon-reorder"></i>

				<span class="hidden-480">增加${eCatalogTypeTreeName}</span>

			</div>
			<div class="tools">
				<a href="javascript:location.reload();" class="reload"></a>

			</div>
		</div>
	<form action="${path}/zzb/dzda/e01z1/save" class="form-horizontal" id="addForm" method="post">
		<input type="hidden" name="a38Id" id="a38Id" value="${a38Id}"/>
		<input type="hidden" name="a0101" id="a0101" value="${a0101}"/>
		<input type="hidden" id="eCatalogTypeTreeId" name="eCatalogTypeTreeId" value="${eCatalogTypeTreeId}"/>
		<input type="hidden" id="eCatalogTypeTreeCode" name="eCatalogTypeTreeCode" value="${eCatalogTypeTreeCode}"/>
		<input type="hidden" id="eCatalogTypeTreeName" name="eCatalogTypeTreeName" value="${eCatalogTypeTreeName}"/>
		<input type="hidden" id="eCatalogTypeTreeParentId" name="eCatalogTypeTreeParentId" value="${eCatalogTypeTreeParentId}"/>
		<table  border="0" style="width:100%;" cellPadding="5px">
			<div class="row-fluid">
				<div class="span6 ">
					<div id="e01Z111Group" class="control-group">
						<label class="control-label" style="width:120px"><span class="required">*</span>材料名称</label>
						<div class="controls" style="margin-left: 140px;">
							<input type="text" class="span9 m-wrap" name="e01Z111" required maxlength="128" id="e01Z111" value="${vo.e01Z111}" />
						</div>
					</div>
				</div>
				<div class="span6 ">
					<div id="e01Z117Group" class="control-group">
						<label class="control-label" style="width:120px">材料制成时间</label>
						<div class="controls" style="margin-left: 140px;">
							<input type="text" class="span9 m-wrap" name="e01Z117" placeholder="日期格式 例如：2018或201801或20180101"  isDate="true" dateformat="yyyy,yyyymm,yyyymmdd" maxlength="128" id="e01Z117" value="${vo.e01Z117}" />
						</div>
					</div>
				</div>
			</div>
			<div class="row-fluid">
				<div class="span6 ">
					<div id="e01Z111RemarkGroup" class="control-group">
						<label class="control-label" style="width:120px">材料名称备注</label>
						<div class="controls" style="margin-left: 140px;">
							<input type="text" class="span9 m-wrap" name="e01Z111Remark" maxlength="128" id="e01Z111Remark" value="${vo.e01Z111Remark}" />
						</div>
					</div>
				</div>
				<div class="span6 ">
					<div id="e01Z114Group" class="control-group">
						<label class="control-label" style="width:120px"><span class="required">*</span>材料页数</label>
						<div class="controls" style="margin-left: 140px;">

								<input type="text" class="span9 m-wrap" name="e01Z114" number="true" maxlength="128" id="e01Z114" value="${vo.e01Z114}" required/>

						</div>
					</div>
				</div>
			</div>
			<div class="row-fluid">
				<div class="span6 ">
					<div id="e01Z124Group" class="control-group">
						<label class="control-label" style="width:120px">材料份数</label>
						<div class="controls" style="margin-left: 140px;">
							<input type="text" class="span9 m-wrap" name="e01Z124" number="true" maxlength="128" id="e01Z124" value="${vo.e01Z124}" />
						</div>
					</div>
				</div>
				<div class="span6 ">
					<div id="e01Z207Group" class="control-group">
						<label class="control-label" style="width:120px">接收人</label>
						<div class="controls" style="margin-left: 140px;">
							<input type="text" class="span9 m-wrap" name="e01Z207" maxlength="128" id="e01Z207" value="${vo.e01Z207}" />
						</div>
					</div>
				</div>
			</div>
			<div class="row-fluid">
				<div class="span6 ">
					<div id="e01Z204Group" class="control-group">
						<label class="control-label" style="width:120px">材料来处</label>
						<div class="controls" style="margin-left: 140px;">

							<input type="text" class="span9 m-wrap" name="e01Z204" maxlength="128" id="e01Z204" value="${vo.e01Z204}" />
						</div>
					</div>
				</div>
				<div class="span6 ">
					<div id="e01Z201Group" class="control-group">
						<label class="control-label" style="width:120px">接收日期</label>
						<div class="controls" style="margin-left: 140px;">
							<input type="text" class="span9 m-wrap" name="e01Z201" placeholder="日期格式 例如：2018或201801或20180101"  isDate="true" dateformat="yyyy,yyyymm,yyyymmdd" maxlength="128" id="e01Z201" value="${vo.e01Z201}" />
						</div>
					</div>
				</div>
			</div>
			<div class="row-fluid">
				<div class="span6 ">
					<div id="e01Z104Group" class="control-group">
						<label class="control-label" style="width:120px"><span class="required">*</span>材料序号</label>
						<div class="controls" style="margin-left: 140px;">

							<input type="text" class="span9 m-wrap" name="e01Z104" required number="true" maxlength="128" id="e01Z104" value="${vo.e01Z104}" />
						</div>
					</div>
				</div>
				<div class="span6 ">
					<div id="e01Z231Group" class="control-group">
						<label class="control-label" style="width:120px">备注</label>
						<div class="controls" style="margin-left: 140px;">
							<textarea id="e01Z231" name="e01Z231" class="span9 m-wrap"  style="resize: none;">${vo.e01Z231}</textarea>
						</div>
					</div>
				</div>
				<%--<div class="span6 ">--%>
					<%--<div id="e01Z107Group" class="control-group">--%>
						<%--<label class="control-label" style="width:120px">扫描排序</label>--%>
						<%--<div class="controls" style="margin-left: 140px;">--%>
							<%--<input type="text" class="span9 m-wrap" readonly name="e01Z107" required maxlength="128" id="e01Z107" value="${vo.e01Z107}" />--%>
						<%--</div>--%>
					<%--</div>--%>
				<%--</div>--%>
			</div>
			<%--<div class="row-fluid">--%>
				<%--<div class="span11 ">--%>
					<%--<div id="e01Z231Group" class="control-group">--%>
						<%--<label class="control-label" style="width:120px" style="width:120px">备注</label>--%>
						<%--<div class="controls" style="margin-left: 140px;">--%>
							<%--<textarea id="e01Z231" name="e01Z231" class="span12 m-wrap">${vo.e01Z231}</textarea>--%>
						<%--</div>--%>
					<%--</div>--%>
				<%--</div>--%>
			<%--</div>--%>

			<div class="row-fluid">
				<center>
					<div style="margin:auto;">
						<button id="submitbut" class="btn green" type="button" style="padding:7px 20px;" >确定</button>
						<a class="btn" href="javascript:cencal()"><i class="icon-remove-sign"></i> 取消</a>
					</div>
				</center>
			</div>
			<%--<tr>
				<td colspan="2" style="text-align: center">
					<button id="submitbut" class="btn green" type="button" style="padding:7px 20px;" >确定</button>
					<a class="btn" href="javascript:cencal()"><i class="icon-remove-sign"></i> 取消</a>

				</td>

			</tr>--%>

		</table>

	</form>
</div>
	<script type="text/javascript" src="${path }/js/common/loading.js"></script>
<script type="text/javascript">
	var myLoading = new MyLoading("${path}",{zindex : 11111});
	var addForm = new EstValidate("addForm");

	$(function(){
		$("#submitbut").on("click",function(){
			var eCatalogTypeTreeId = $("#eCatalogTypeTreeId").val();
			var eCatalogTypeTreeCode = $("#eCatalogTypeTreeCode").val();
			var eCatalogTypeTreeName = $("#eCatalogTypeTreeName").val();
			var eCatalogTypeTreeParentId = $("#eCatalogTypeTreeParentId").val();
			var a38Id = $("#a38Id").val();
			var e01Z231 = $("#e01Z231").val();
			var bool = addForm.form();
			if(bool){
				myLoading.show();
				$.ajax({
					url : "${path}/zzb/dzda/e01z1/save",
					type : "post",
					data : $("#addForm").serialize(),
					dataType : "json",
					headers: {
						"OWASP_CSRFTOKEN":"${sessionScope.OWASP_CSRFTOKEN}"
					},
					success : function(json){
						if(json.success){
							refreshTreeManege();
							$.ajax({
								url: "${path}/zzb/dzda/e01z1/ajax/mlxxList",// 请求的action路径
								type: 'POST',
								dataType : "html",
								data:{
									"eCatalogTypeTreeId":eCatalogTypeTreeId,
									"eCatalogTypeTreeCode":eCatalogTypeTreeCode,
									"eCatalogTypeTreeParentId":eCatalogTypeTreeParentId,
									"eCatalogTypeTreeName":eCatalogTypeTreeName,
									"a38Id":a38Id
								},
								headers: {
									"OWASP_CSRFTOKEN":"${sessionScope.OWASP_CSRFTOKEN}"
								},
								success : function(html){
									myLoading.hide();
									$("#rightList").html(html);
								},
								error : function(){
									myLoading.hide();
									showTip("警告","请求失败!",2000);
								}
							});
						}else{
							myLoading.hide();
							document.getElementById("addForm").reset();
							$(".control-group").removeClass("error").removeClass("success");
							$(".help-inline").remove();
							showTip("警告","新增材料项失败!",2000);
						}
					},
					error : function(){
						myLoading.hide();
						document.getElementById("addForm").reset();
						$(".control-group").removeClass("error").removeClass("success");
						$(".help-inline").remove();
					}
				});
			}
		});
	});

	function cencal(){
		var eCatalogTypeTreeId = $("#eCatalogTypeTreeId").val();
		var eCatalogTypeTreeCode = $("#eCatalogTypeTreeCode").val();
		var eCatalogTypeTreeName = $("#eCatalogTypeTreeName").val();
		var eCatalogTypeTreeParentId = $("#eCatalogTypeTreeParentId").val();
		var a38Id = $("#a38Id").val();
		$.ajax({
			url: "${path}/zzb/dzda/e01z1/ajax/mlxxList",// 请求的action路径
			type: 'POST',
			dataType : "html",
			data:{
				"eCatalogTypeTreeId":eCatalogTypeTreeId,
				"eCatalogTypeTreeCode":eCatalogTypeTreeCode,
				"eCatalogTypeTreeParentId":eCatalogTypeTreeParentId,
				"eCatalogTypeTreeName":eCatalogTypeTreeName,
				"a38Id":a38Id
			},
			headers: {
				"OWASP_CSRFTOKEN":"${sessionScope.OWASP_CSRFTOKEN}"
			},
			success : function(html){
				$("#rightList").html(html);
			},
			error : function(){
				alert('请求失败');
			}
		});
	}

</script>