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

				<span class="hidden-480">修改目录材料</span>

			</div>
			<div class="tools">
				<a href="javascript:location.reload();" class="reload"></a>

			</div>
		</div>
	<form action="" class="form-horizontal" id="form1" method="post">

		<table  border="0" style="width:100%;" cellPadding="5px">
			<tr>
				<td width="50%">
					<div id="ciNameGroup" class="control-group">
						<label class="control-label"><span class="required">*</span>材料名称</label>
						<div class="controls">
							<input type="text" class="span8 m-wrap" name="ciName" required maxlength="128" id="ciName" value="干部履历表" />
						</div>
					</div>
				</td>
				<td width="50%">
					<div id="influenceInfoIdGroup" class="control-group">
						<label class="control-label">材料制成时间</label>
						<div class="controls">
							<input type="text" class="span8 m-wrap" name="ciName" required maxlength="128" id="ciName" value="2000.11.02" />
						</div>
					</div>
				</td>

			</tr>
			<tr>
				<td width="50%">
					<div id="ciNameGroup" class="control-group">
						<label class="control-label">材料名称备注</label>
						<div class="controls">
							<input type="text" class="span8 m-wrap" name="ciName" required maxlength="128" id="ciName" value="" />
						</div>
					</div>
				</td>
				<td width="50%">
					<div id="influenceInfoIdGroup" class="control-group">
						<label class="control-label">材料页数</label>
						<div class="controls">

								<input type="text" class="span8 m-wrap" name="ciName" required maxlength="128" id="ciName" value="13" />

						</div>
					</div>
				</td>

			</tr>
			<tr>
				<td width="50%">
					<div id="ciNameGroup" class="control-group">
						<label class="control-label">材料份数</label>
						<div class="controls">
							<input type="text" class="span8 m-wrap" name="ciName" required maxlength="128" id="ciName" value="1" />
						</div>
					</div>
				</td>
				<td width="50%">
					<div id="influenceInfoIdGroup" class="control-group">
						<label class="control-label">接收人</label>
						<div class="controls">
							<input type="text" class="span8 m-wrap" name="ciName" required maxlength="128" id="ciName" value="" />
						</div>
					</div>
				</td>
			</tr>

			<tr>
				<td width="50%">
					<div id="ciNameGroup" class="control-group">
						<label class="control-label">材料来处</label>
						<div class="controls">

							<input type="text" class="span8 m-wrap" name="ciName" required maxlength="128" id="ciName" value="" />
						</div>
					</div>
				</td>
				<td width="50%">
					<div id="influenceInfoIdGroup" class="control-group">
						<label class="control-label">接收日期</label>
						<div class="controls">
							<input type="text" class="span8 m-wrap" name="ciName" required maxlength="128" id="ciName" value="200803" />
						</div>
					</div>
				</td>
			</tr>
			<td width="50%">
				<div id="ciNameGroup" class="control-group">
					<label class="control-label"><span class="required">*</span>材料序号</label>
					<div class="controls">

						<input type="text" class="span8 m-wrap" name="ciName" required maxlength="128" id="ciName" value="1" />
					</div>
				</div>
			</td>
			<td width="50%">
				<div id="influenceInfoIdGroup" class="control-group">
					<label class="control-label">扫描排序</label>
					<div class="controls">
						<input type="text" class="span8 m-wrap" name="ciName" required maxlength="128" id="ciName" value="1" />
					</div>
				</div>
			</td>
			</tr>
			<tr>
				<td width="50%">
					<div id="ciNameGroup" class="control-group">
						<label class="control-label">扫描序号</label>
						<div class="controls">
							<input type="text" class="span8 m-wrap" name="ciName" required maxlength="128" id="ciName" value="0002" />
						</div>
					</div>
				</td>
				<td width="50%">

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
			<tr>
				<td colspan="2" style="text-align: center">
					<button class="btn green" type="button" style="padding:7px 20px;" onclick="cencal()">确定</button>
					<a class="btn" href="javascript:cencal()"><i class="icon-remove-sign"></i> 取消</a>

				</td>

			</tr>

		</table>

	</form>
</div>

<script type="text/javascript">
	function cencal(){
		$.ajax({
			async:false,
			type:"POST",
			url:"${path}/zzb/app/console/daDemo/ajax/mlxxList",
			dataType : "html",
			headers:{
				"OWASP_CSRFTOKEN":'${sessionScope.OWASP_CSRFTOKEN}'
			},
			data:{

			},
			success:function(html){
				$("#catalogList").html(html);
			},
			error : function(){
				myLoading.hide();
				showTip("提示","出错了,请检查网络!",2000);
			}
		});
	}

</script>