<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<div >
	<!-- BEGIN FORM-->

	<form action="" class="form-horizontal" id="form1" method="post">
		<input type="hidden" name="importType" value="${importType}" id="importType"/>
		<div id="ipGroup" class="control-group" style="margin-bottom:20px !important">
			<label class="control-label" style="width: 70px">IP地址:<span class="required">*</span></label>
			<div class="controls" style="margin-left: 90px">
				<input type="text" class=" m-wrap" name="ip" required maxlength="200" id="ip" value="" />
			</div>
		</div>
		<div id="portGroup" class="control-group">
			<label class="control-label" style="width: 70px">端口号:<span class="required">*</span></label>
			<div class="controls" style="margin-left: 90px">
				<input type="text" class=" m-wrap" id="port" name="port" number="true"  required maxlength="3"  value="" />
			</div>
		</div>
		<div id="dbNameGroup" class="control-group" style="margin-bottom:20px !important">
			<label class="control-label" style="width: 70px">数据库名:<span class="required">*</span></label>
			<div class="controls" style="margin-left: 90px">
				<input type="text" class=" m-wrap" name="dbName" required maxlength="200" id="dbName" value="" />
			</div>
		</div>
		<div id="dbUserGroup" class="control-group" style="margin-bottom:20px !important">
			<label class="control-label" style="width: 70px">用户名:<span class="required">*</span></label>
			<div class="controls" style="margin-left: 90px">
				<input type="text" class=" m-wrap" name="dbUser" required maxlength="200" id="dbUser" value="" />
			</div>
		</div>
		<div id="dbPwdGroup" class="control-group" style="margin-bottom:20px !important">
			<label class="control-label" style="width: 70px">密码:<span class="required">*</span></label>
			<div class="controls" style="margin-left: 90px">
				<input type="text" class=" m-wrap" name="dbPwd" required maxlength="200" id="dbPwd" value="" />
			</div>
		</div>
		<div class="control-group mybutton-group" style="text-align: right;">

			<button type="button" class="btn green" onclick="formSubmit()"><i class="icon-ok"></i> 确定</button>

			<button type="button" class="btn btn-default"  data-dismiss="modal"><i class="icon-remove-sign"></i> 取消</button>

		</div>
	</form>
</div>

<script type="text/javascript">
	var myLoading = new MyLoading("${path}",20000);
	var form1 = new EstValidate("form1");
	function formSubmit(){

		var bool = form1.form();
		if(bool){
			$('#impPModal').modal('hide');
			myLoading.show();
			$.ajax({
				url : "${path }/zzb/app/console/bset/import",
				type : "post",
				data : $("#form1").serialize(),
				headers:{
					OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
				},
				dataType : "json",
				success : function(data){
					if(data.success){
						myLoading.hide();

						setTimeout(function(){window.location.href = "${path}/zzb/app/console/bset/"},2000);
						showTip("导入提示","导入成功", 1500);
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