<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<div >
	<!-- BEGIN FORM-->

	<form action="" class="form-horizontal" id="form1" method="post">
		<input type="hidden" name="dataType" value="${vo.dataType}" id="dataType"/>
		<input type="hidden" name="parentId" value="${vo.parentId}" id="parentId"/>
		<input type="hidden" name="id" value="${vo.id }"/>
		<table  border="0" style="width:100%;" cellPadding="5px">
			<tr>
				<td width="50%">
					<div id="ciNameGroup" class="control-group">
						<label class="control-label">档案编号</label>
						<div class="controls">
							<input type="text" class="span8 m-wrap" name="ciName" required maxlength="128" id="ciName" value="" />
						</div>
					</div>
				</td>
				<td width="50%">
					<div id="influenceInfoIdGroup" class="control-group">
						<label class="control-label">扫描序号</label>
						<div class="controls">
							<input type="text" class="span8 m-wrap" name="ciName" required maxlength="128" id="ciName" value="0002" />
						</div>
					</div>
				</td>

			</tr>
			<tr>
				<td width="50%">
					<div id="ciNameGroup" class="control-group">
						<label class="control-label">姓名<span class="required">*</span></label>
						<div class="controls">
							<input type="text" class="span8 m-wrap" name="ciName" required maxlength="128" id="ciName" value="红叶专" />
						</div>
					</div>
				</td>
				<td width="50%">
					<div id="influenceInfoIdGroup" class="control-group">
						<label class="control-label">性别</label>
						<div class="controls">
							<select class="span8 m-wrap" type="text" id="influenceInfoId" name="influenceInfoId" required>
								<option value="">请选择...</option>
								<option value="">男</option>
								<option value="">女</option>
							</select>
						</div>
					</div>
				</td>

			</tr>
			<tr>
				<td width="50%">
					<div id="ciNameGroup" class="control-group">
						<label class="control-label">出生日期</label>
						<div class="controls">
							<input type="text" class="span8 m-wrap" name="ciName" required maxlength="128" id="ciName" value="19620702" />
						</div>
					</div>
				</td>
				<td width="50%">
					<div id="influenceInfoIdGroup" class="control-group">
						<label class="control-label">籍贯</label>
						<div class="controls">
							<input type="text" class="span8 m-wrap" name="ciName" required maxlength="128" id="ciName" value="" />
						</div>
					</div>
				</td>
			</tr>

			<tr>
				<td width="50%">
					<div id="ciNameGroup" class="control-group">
						<label class="control-label">现职级时间</label>
						<div class="controls">
							<select class="span4 m-wrap" type="text" id="influenceInfoId" name="influenceInfoId" required>
								<option value=""></option>
								<option value="">正局</option>
								<option value="" selected>副局</option>
							</select>
							<input type="text" class="span4 m-wrap" name="ciName" required maxlength="128" id="ciName" value="200803" />
						</div>
					</div>
				</td>
				<td width="50%">
					<div id="influenceInfoIdGroup" class="control-group">
						<label class="control-label">干部状态</label>
						<div class="controls">
							<select class="span8 m-wrap" type="text" id="influenceInfoId" name="influenceInfoId" required>
								<option value=""></option>
								<option value="" selected>在职</option>
								<option value="" >离职</option>
								<option value="" >离退</option>
								<option value="" >辞职</option>
							</select>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td width="50%">
					<div id="ciNameGroup" class="control-group">
						<label class="control-label">单位职务</label>
						<div class="controls">
							<textarea class="span8 m-wrap" rows="3">州委书记</textarea>
						</div>
					</div>
				</td>
				<td width="50%">
					<div id="ciNameGroup" class="control-group">
						<label class="control-label">档案状态</label>
						<div class="controls">
							<select class="span8 m-wrap" type="text" id="influenceInfoId" name="influenceInfoId" required>
								<option value=""></option>
								<option value="" selected>有档</option>
								<option value="" >无档需追补</option>
								<option value="" >无档不需追补</option>
								<option value="" >转出</option>
							</select>
						</div>
					</div>
				</td>
			</tr>
			<tr>

				<td width="50%">
					<div id="influenceInfoIdGroup" class="control-group">
						<label class="control-label">联系单位</label>
						<div class="controls">
							<input type="text" class="span8 m-wrap" name="ciName" required maxlength="128" id="ciName" value="" />
						</div>
					</div>
				</td>
				<td width="50%">
					<div id="ciNameGroup" class="control-group">
						<label class="control-label">档案位置</label>
						<div class="controls">
							<input type="text" class="span8 m-wrap" name="ciName" required maxlength="128" id="ciName" value="" />
						</div>
					</div>
				</td>
			</tr>

			<tr>
				<td colspan="2">
					<div id="ciNameGroup" class="control-group">
						<label class="control-label">备注</label>
						<div class="controls">
							<textarea class="span10 m-wrap"></textarea>
						</div>
					</div>
				</td>

			</tr>
		</table>

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