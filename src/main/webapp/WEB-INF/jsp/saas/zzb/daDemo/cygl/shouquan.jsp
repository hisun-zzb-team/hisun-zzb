<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%--
  ~ Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
  ~ http://www.hn-hisun.com
  ~ 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
  --%>

<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<title></title>
<div >
	<!-- BEGIN FORM-->
	<div class="portlet-title">

		<div class="caption">


			<span class="hidden-480">阅档申请信息</span>

		</div>
		<div class="portlet-title" style="text-align: right">

			<button type="button" class="btn green" onclick="formSubmit()">授权查阅全部档案</button>
			<button type="button" class="btn green" onclick="formSubmit()">授权查阅指定目录</button>
			<button type="button" class="btn green" onclick="formSubmit()">拒绝</button>
			<a class="btn" href="javascript:cancel()"><i class="icon-undo"></i>返回</a>

		</div>
	</div>
	<form action="" class="form-horizontal" id="form1" method="post">
		<input type="hidden" name="dataType" value="${vo.dataType}" id="dataType"/>
		<input type="hidden" name="parentId" value="${vo.parentId}" id="parentId"/>
		<input type="hidden" name="id" value="${vo.id }"/>
		<table  border="0" style="width:100%;" cellPadding="5px">
			<tr>
				<td width="50%">
					<div id="ciNameGroup" class="control-group">
						<label class="control-label">查阅何人档案<span class="required">*</span></label>
						<div class="controls">
							<input type="text" class="span8 m-wrap" name="ciName" required maxlength="128" id="ciName" value="钱某某" />
						</div>
					</div>
				</td>
				<td width="50%">
					<div id="influenceInfoIdGroup" class="control-group">
						<label class="control-label">申请人</label>
						<div class="controls">
							<input type="text" class="span8 m-wrap" name="ciName" required maxlength="128" id="ciName" value="何建英" />
						</div>
					</div>
				</td>

			</tr>
			<tr>
				<td width="50%">
					<div id="ciNameGroup" class="control-group">
						<label class="control-label">单位职务</label>
						<div class="controls">
							<input type="text" class="span8 m-wrap" name="ciName" required maxlength="128" id="ciName" value="" />
						</div>
					</div>
				</td>
				<td width="50%">
					<div id="influenceInfoIdGroup" class="control-group">
						<label class="control-label">查阅单位</label>
						<div class="controls">
							<input type="text" class="span8 m-wrap" name="ciName" required maxlength="128" id="ciName" value="海珠区委组织部" />
						</div>
					</div>
				</td>

			</tr>
			<tr>
				<td width="50%">
					<div id="ciNameGroup" class="control-group">
						<label class="control-label">查阅人</label>
						<div class="controls">
							<input type="text" class="span8 m-wrap" name="ciName" required maxlength="128" id="ciName" value="19620702" />
						</div>
					</div>
				</td>
				<td width="50%">
					<div id="influenceInfoIdGroup" class="control-group">
						<label class="control-label">批准人</label>
						<div class="controls">
							<input type="text" class="span8 m-wrap" name="ciName" required maxlength="128" id="ciName" value="" />
						</div>
					</div>
				</td>
			</tr>

			<tr>
				<td width="50%">
					<div id="ciNameGroup" class="control-group">
						<label class="control-label">申请时间</label>
						<div class="controls">
							<input type="text" class="span8 m-wrap" name="ciName" required maxlength="128" id="ciName" value="2018.03.31 11:00" />
						</div>
					</div>
				</td>
				<td width="50%">
					<div id="influenceInfoIdGroup" class="control-group">
						<label class="control-label">查阅时间</label>
						<div class="controls">
							<input type="text" class="span8 m-wrap" name="ciName" required maxlength="128" id="ciName" value="120" />分钟
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td width="50%">
					<div id="ciNameGroup" class="control-group">
						<label class="control-label">联系电话</label>
						<div class="controls">
							<input type="text" class="span8 m-wrap" name="ciName" required maxlength="128" id="ciName" value="" />
						</div>
					</div>
				</td>
				<td width="50%">
					<div id="ciNameGroup" class="control-group">
						<label class="control-label">允许下载</label>
						<div class="controls">
										<span class=""><input type="radio" value="0"
															  name="tombstone" id="tombstone"></span>
								 是

											<span class="checked"><input type="radio" checked
																		 value="1" name="tombstone" id="tombstone"></span>
								 否

						</div>

					</div>
				</td>
			</tr>
			<tr>

				<td width="50%">
					<div id="influenceInfoIdGroup" class="control-group">
						<label class="control-label">查阅内容</label>
						<div class="controls">
							<textarea class="span8 m-wrap">全部材料</textarea>
						</div>
					</div>
				</td>
				<td width="50%">
					<div id="ciNameGroup" class="control-group">
						<label class="control-label">查阅理由</label>
						<div class="controls">
							<textarea class="span8 m-wrap"></textarea>
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
	function cancel(){
			window.location.href = "${path}/zzb/app/console/daDemo/cyManageList";


	}
</script>