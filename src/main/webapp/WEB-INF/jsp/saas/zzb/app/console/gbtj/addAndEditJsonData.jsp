<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/inc/servlet.jsp" %>
<%@include file="/WEB-INF/jsp/inc/taglib.jsp" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<div id="jsonDataFormGroup" class="control-group">
	<div class="portlet-body">
		<form class="form-horizontal" id="jsonDataForm">
			<div class="actions fr" style="margin-bottom: 5px;">
				<input type="hidden" name="id"  value="${id }"/>
				<a href="javascript:void(0)" class="btn green" onclick="toAddApplyObject()"><i class="icon-plus"></i>增加</a>
			</div>
			<div class="dataTables_wrapper form-inline">
				<table class="table table-striped table-bordered table-hover dataTable table-set" id="jsonDataFormTable">
					<tr>
						<th>名称<span style="color: red">*</span></th>
						<th>值<span style="color: red">*</span></th>
						<th width="80px">操作</th>
					</tr>

					<c:forEach items="${data}" var="vo" varStatus="status">
						<tr style="text-overflow:ellipsis;" id="jsonDataForm_${status.index}">

							<td>
								<div id="jsonDataVos[${status.index}].nameGroup" class="control-group" style="margin-bottom: 0px !important;">
									<input type="text" name="jsonDataVos[${status.index}].name" placeholder="必填项"	 required style="width: 90%;" value="${vo.name }"/>
								</div>
							</td>
							<td>

								<div id="jsonDataVos[${status.index}].valueGroup" class="control-group" style="margin-bottom: 0px !important;">
									<input type="text" name="jsonDataVos[${status.index}].value" style="width:90%;" number="true" placeholder="请填写整数"	 required value="${vo.value }"/>
								</div>

							</td>
							<td>
								<a href="javascript:void(0)" class="" onclick="toDeleteApplyObject('${status.index}')">删除</a>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<div class="control-group mybutton-group" style="text-align: right;margin-top: 20px">
				<button type="button" class="btn green"  onclick="subimtCiObjectAttrExt()" id="listBtnOk"><i class="icon-ok"></i> 确定</button>
				<button type="button" class="btn btn-default" data-dismiss="modal"><i class='icon-remove-sign'></i> 关闭</button>
			</div>
		</form>
	</div>

</div>
<script type="text/javascript" src="${path }/js/common/30CloudAjax.js"></script>
<script>

	var EstValidate = function(formId){
		var  vld = $("#"+formId).validate({
			errorClass : "help-inline",
			validClass : "help-inline ok valid",
			showErrors : function(errorMap, errorList){
				var f = document.getElementById(formId);
				for(var i=0;i<errorList.length;i++){
					var ele = errorList[i].element;
					if(ele.id == "attListName"){
						var errEle = document.getElementById(ele.id+"Err");
						var line = document.getElementById(ele.id+"Group");
					}else{
						var errEle = document.getElementById(ele.name+"Err");
						var line = document.getElementById(ele.name+"Group");
					}

					line.className = "control-group error";
					if(errEle){
						errEle.className = "help-inline";
						errEle.innerHTML = errorMap[ele.name];
					}else{
						if(ele.id == "attListName"){
							$(ele.parentNode).append("<span id=\"" + ele.id + "Err\" for=\"" + ele.id + "\" class=\"help-inline\">" + errorMap[ele.name] + "</span>");
						}
					}
				}
				var sucList = this.successList;
				for(var i=0;i<sucList.length;i++){
					var ele = sucList[i];
					if(ele.id == "attListName"){
						var line = document.getElementById(ele.id+"Group");
						line.className = "control-group success";
						var errEle = document.getElementById(ele.id+"Err");
					}else{
						var line = document.getElementById(ele.name+"Group");
						line.className = "control-group success";
						var errEle = document.getElementById(ele.name+"Err");
					}

					if(errEle){
						errEle.className = "help-inline ok";
						errEle.innerHTML = "";
					}else{
						if(ele.id == "attListName"){
							$(ele.parentNode).append("<span id=\"" + ele.id + "Err\" for=\"" + ele.id + "\" class=\"help-inline ok\"></span>");
						}
					}
				}
			}
		});
		return vld;
	}

	var count = 0;
	function toAddApplyObject(){
		var all="${data.size()}";
		var index = $("#jsonDataFormTable").find("tr").length - 1;
		var tr = "<tr style=\"text-overflow:ellipsis;\" id=\"jsonDataForm_"+parseInt(Number(all)+Number(count))+"\">"+
				"<td>"+
				"<div id=\"jsonDataVos["+parseInt(Number(all)+Number(count))+"].nameGroup\" class=\"control-group\"   style=\"margin-bottom: 0px !important;\">"+
				"<input type=\"text\" name=\"jsonDataVos["+parseInt(Number(all)+Number(count))+"].name\" placeholder=\"必填项\"  required style=\"width: 90%;\"/>"+
				"</div>"+
				"</td>"+
				"<td>"+
				"<div id=\"jsonDataVos["+parseInt(Number(all)+Number(count))+"].valueGroup\" class=\"control-group\"   style=\"margin-bottom: 0px !important;\">"+
				"<input type=\"text\" name=\"jsonDataVos["+parseInt(Number(all)+Number(count))+"].value\" placeholder=\"必填项\"  required  number=\"true\" style=\"width: 90%;\"/>"+
				"</div>"+
				"</td>"+
				"<td>"+
				"<a href=\"javascript:void(0)\" class=\"\" onclick=\"toDeleteApplyObject('"+parseInt(Number(all)+Number(count))+"')\">删除</a>"+
				"</td>"+
				"</tr>";

		$("#jsonDataFormTable").append(tr);
		jQuery.uniform.update("#jsonDataFormTable");
		count++;
		$("#listBtnOk").attr("disabled",false);
	}

	function toDeleteApplyObject(id){
		$("#jsonDataForm_"+id).remove();
		jQuery.uniform.update("#jsonDataFormTable");
		//只要有一行才给点击确定按钮
		if($("#jsonDataFormTable tr").length > 1){
			$("#listBtnOk").attr("disabled",false);
		}else{
			$("#listBtnOk").attr("disabled",true);
		}
	}
	var ciObjectAttrExtForm = new EstValidate("jsonDataForm");
	function subimtCiObjectAttrExt(){
		var bool = ciObjectAttrExtForm.form();
		if(bool){
			$.cloudAjax({
				async : true,
				url : '${path}/zzb/app/console/gbtj/saveJsonData',
				type : 'post',
				data : $("#jsonDataForm").serialize(),
				headers:{
					OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
				},
				dataType : 'json',
				success : function(data){
					if(data.success == false){
						showTip("提示","出错了,请检查网络!",2000);
					}else{
						$('#jsonDataModal').modal('hide');
						showTip("提示","保存成功", 1000);
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
	}
</script>