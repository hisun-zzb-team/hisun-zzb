<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<div >
	<!-- BEGIN FORM-->

	<form action="" class="form-horizontal" id="form1" method="post">
		<input type="hidden" name="dataType" value="${vo.dataType}" id="dataType"/>
		<input type="hidden" name="parentId" value="${vo.parentId}" id="parentId"/>
		<input type="hidden" name="id" value="${vo.id }"/>

		<div id="b0101Group" class="control-group" style="margin-bottom:20px !important">
			<label class="control-label" style="width: 70px">名称:<span class="required">*</span></label>
			<div class="controls" style="margin-left: 90px">
				<input type="text" class=" m-wrap" name="b0101" required maxlength="200" id="b0101" value="${vo.b0101}" />
			</div>
		</div>
		<div id="pxGroup" class="control-group">
			<label class="control-label" style="width: 70px">排序:<span class="required">*</span></label>
			<div class="controls" style="margin-left: 90px">
				<input type="text" class=" m-wrap" id="px" name="px" number="true"  required maxlength="3"  value="${vo.px}" />
			</div>

		</div>
		<div class="control-group mybutton-group" style="text-align: right;">

			<button type="button" class="btn green" onclick="formSubmit()"><i class="icon-ok"></i> 确定</button>

			<button type="button" class="btn btn-default"  data-dismiss="modal"><i class="icon-remove-sign"></i> 取消</button>

		</div>
	</form>
</div>

<script type="text/javascript">
	var form1 = new EstValidate("form1");
	function formSubmit(){
		var bool = form1.form();
		if(bool){
			$.ajax({
				url : "${path }/zzb/app/console/appGbcxB01/save",
				type : "post",
				data : $("#form1").serialize(),
				headers:{
					OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
				},
				dataType : "json",
				success : function(data){
					if(data.success){
						$('#jgModal').modal('hide');
						setTimeout(function(){window.location.href = "${path}/zzb/app/console/gbcx/"},2000);
						showTip("提示","保存成功", 1500);
						//setTimeout(process.list,2000);
					}else{
						var message = data.msg?data.msg:data.message;
						showTip("提示", message, 2000);
					}
				},
				error : function(){
					showTip("提示","出错了,请检查网络!",2000);
				}
			});
		}
	}

</script>