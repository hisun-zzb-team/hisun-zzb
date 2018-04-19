<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<div class="portlet-body form">
	<!-- BEGIN FORM-->

	<form action="" class="form-horizontal" id="dascqktipsForm" method="post">
		<input type="hidden" name="sha01Id" value="" id="sha01Id"/>
		<input type="hidden" name="id" value="${vo.id }"/>

		<div id="tipGroup" class="control-group" style="margin-bottom:20px !important">
			<label class="control-label" style="width: 50px;">备注:</label>
			<div class="controls" style="margin-left: 70px">
				<textarea style="width:420px" required rows="9" name="tip" maxlength="400" id="tip" value="${vo.tip}">${vo.tip}</textarea>
			</div>

		</div>
		<div class="control-group mybutton-group" style="text-align: right;">

			<button type="button" class="btn green" onclick="dascqktipsFormSubmit()"><i class="icon-ok"></i> 确定</button>

			<button type="button" class="btn btn-default"  data-dismiss="modal"><i class="icon-remove-sign"></i> 取消</button>

		</div>
	</form>
</div>
<script type="text/javascript">
	var dascqktipsForm = new EstValidate("dascqktipsForm");
	function dascqktipsFormSubmit(){
		var bool = dascqktipsForm.form();
		if(bool){
			$.ajax({
				url : "${path }/zzb/app/Sha01/dascqk/dascqktips/save",
				type : "post",
				data : $("#dascqktipsForm").serialize(),
				headers:{
					OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
				},
				dataType : "json",
				success : function(data){
					if(data.success){
						$('#dabzModal').modal('hide');
						document.getElementById('dabzAddbtnDiv').style.display="none";
						document.getElementById('dabzshowspan').style.display="inline-block";
						document.getElementById('dabzshowspan').innerHTML=data.tip;
						document.getElementById('dabzshowspan').title=data.tip;
						document.getElementById('dabzDelete').style.display="inline-block";
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