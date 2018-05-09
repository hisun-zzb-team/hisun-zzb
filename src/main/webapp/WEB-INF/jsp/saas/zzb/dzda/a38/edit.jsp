<%@ taglib prefix="Select" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/inc/servlet.jsp" %>
<%@include file="/WEB-INF/jsp/inc/taglib.jsp" %>
<form action="" class="form-horizontal" id="a38Form" method="post" style="margin: 0 0 0px;">
	<input type="hidden" name="sjzt" value="${vo.sjzt}" id="sjzt"/>
	<input type="hidden" name="id" id="id" value="${vo.id }"/>
	<dl class="dlattrbute">
		<dt><a href="###">基本信息</a></dt>
		<dd>
			<div class="row-fluid">
				<div class="span6 ">
					<div id="dabhGroup" class="control-group">
						<label class="control-label">档案编号</label>
						<div class="controls">
							<input type="text" class="span10 m-wrap" id="dabh" name="dabh" maxlength="128"  value="${vo.dabh}" />
						</div>
					</div>
				</div>

				<div class="span6 ">
					<div id="smxhGroup" class="control-group">
						<label class="control-label">扫描序号</label>
						<div class="controls">
							<input type="text" class="span10 m-wrap" name="smxh"  id="smxh" maxlength="128" value="${vo.smxh}" onblur="checkSmxh(this)"/>
						</div>
					</div>
				</div>

			</div>
			<div class="row-fluid">
				<div class="span6 ">
					<div id="a0101Group" class="control-group">
						<label class="control-label"><span class="Required">*</span>姓名</label>
						<div class="controls">
							<input type="text" class="span10 m-wrap" name="a0101" required maxlength="24" id="a0101" value="${vo.a0101}" />
						</div>
					</div>
				</div>
				<div class="span6 ">
					<div id="a0104Group" class="control-group">
						<label class="control-label">性别</label>
						<div class="controls">
							<SelectTag:SelectTag id="a0104" valueName="a0104Content" textClass="m-wrap span10" defaultkeys="${vo.a0104}" defaultvalues="${vo.a0104Content}"
												 radioOrCheckbox="radio" selectUrl="${path}/api/dictionary/select?typeCode=GB/T2261.1-2003"/>
						</div>
					</div>
				</div>
			</div>
			<div class="row-fluid">
				<div class="span6 ">
					<div id="a0107Group" class="control-group">
						<label class="control-label">出生日期</label>
						<div class="controls">
							<input type="text" class="span10 m-wrap" id="a0107" name="a0107" maxlength="64" type="date" formatter="yyyymmdd"  value="${vo.a0107}"/>
						</div>
					</div>
				</div>
				<div class="span6 ">
					<div id="a0111BGroup" class="control-group">
						<label class="control-label">籍贯</label>
						<div class="controls">
							<Tree:tree id="a0111B" valueName="a0111A"  selectClass="span10 m-wrap" treeUrl="${path}/api/dictionary/tree?typeCode=ZB01-2006/GQMC" token="${sessionScope.OWASP_CSRFTOKEN}"
									   submitType="get" dataType="json" isSearch="true" isSelectTree="true" defaultkeys="${vo.a0111B}" defaultvalues="${vo.a0111A}"/>
						</div>
					</div>
				</div>
			</div>
			<div class="row-fluid">
				<div class="span6 ">
					<div id="dutyLevelValueGroup" class="control-group">
						<label class="control-label">现职级</label>
						<div class="controls">
							<Tree:tree id="dutyLevelCode" valueName="dutyLevelValue"  selectClass="span10 m-wrap" treeUrl="${path}/api/dictionary/tree?typeCode=ZB09-2006/ZWJB" token="${sessionScope.OWASP_CSRFTOKEN}"
									   submitType="get" dataType="json" isSearch="true" isSelectTree="true" defaultkeys="${vo.dutyLevelCode}" defaultvalues="${vo.dutyLevelValue}"/>
						</div>
					</div>
				</div>
				<div class="span6 ">
					<div id="dutyLevelTimeBaseGroup" class="control-group">
						<label class="control-label">现职级时间</label>
						<div class="controls">
							<input type="text" class="span10 m-wrap" id="dutyLevelTimeBase" name="dutyLevelTimeBase" maxlength="64"  value="${vo.dutyLevelTimeBase}" />
						</div>
					</div>
				</div>
			</div>
			<div class="row-fluid">
				<div class="span6 ">
					<div id="gbztContentGroup" class="control-group">
						<label class="control-label">干部状态</label>
						<div class="controls">
							<Tree:tree id="gbztCode" valueName="gbztContent"  selectClass="span10 m-wrap" treeUrl="${path}/api/dictionary/tree?typeCode=ZB14-1994/RZZT" token="${sessionScope.OWASP_CSRFTOKEN}"
									   submitType="get" dataType="json" isSearch="true" isSelectTree="true" defaultkeys="${vo.gbztCode}" defaultvalues="${vo.gbztContent}"/>
						</div>
					</div>
				</div>
				<div class="span6 ">
					<div id="a0157Group" class="control-group">
						<label class="control-label">单位职务</label>
						<div class="controls">
							<input type="text" class="span10 m-wrap" id="a0157" name="a0157" maxlength="64"  value="${vo.a0157}" />
						</div>
					</div>
				</div>
			</div>
			<div class="row-fluid">
				<div class="span6 ">
					<div id="daztContentGroup" class="control-group">
						<label class="control-label">档案状态</label>
						<div class="controls">
							<Tree:tree id="daztCode" valueName="daztContent"  selectClass="span10 m-wrap" treeUrl="${path}/api/dictionary/tree?typeCode=ZB09-2006/ZWJB" token="${sessionScope.OWASP_CSRFTOKEN}"
									   submitType="get" dataType="json" isSearch="true" isSelectTree="true" defaultkeys="${vo.daztCode}" defaultvalues="${vo.daztContent}"/>
						</div>
					</div>
				</div>
				<div class="span6 ">
					<div id="a3814Group" class="control-group">
						<label class="control-label">版本类别</label>
						<div class="controls">
							<input type="text" class="span10 m-wrap" id="a3814" name="a3814" maxlength="64"  value="${vo.a3814}" />
						</div>
					</div>
				</div>
			</div>
			<div class="row-fluid">
				<div class="span6 ">
					<div id="a3834Group" class="control-group">
						<label class="control-label">档案位置</label>
						<div class="controls">
							<textarea id="a3834" name="a3834" class="span10 m-wrap" maxlength="128" rows="2" style="resize: none;">${vo.a3834}</textarea>
						</div>
					</div>
				</div>
				<div class="span6 ">
					<div id="a3824Group" class="control-group">
						<label class="control-label">备注</label>
						<div class="controls">
							<textarea id="a3824" name="a3824" class="span10 m-wrap" maxlength="512" rows="2" style="resize: none;">${vo.a3824}</textarea>
						</div>
					</div>
				</div>
			</div>
		</dd>
	</dl>
	<dl class="dlattrbute">
		<dt><a href="###">档案管理信息</a></dt>
		<dd>
			<div class="row-fluid">
				<div class="span6 ">
					<div id="a3804AGroup" class="control-group">
						<label class="control-label">档案来处</label>
						<div class="controls">
							<input type="text" class="span10 m-wrap" id="a3804A" name="a3804A" maxlength="64"  value="${vo.a3804A}" />
						</div>
					</div>
				</div>
				<div class="span6 ">
					<div id="a3807AGroup" class="control-group">
						<label class="control-label">转去单位</label>
						<div class="controls">
							<input type="text" class="span10 m-wrap" id="a3807A" name="a3807A" maxlength="64"  value="${vo.a3807A}" />
						</div>
					</div>
				</div>
			</div>
			<div class="row-fluid">
				<div class="span6 ">
					<div id="a3801Group" class="control-group">
						<label class="control-label">接收日期</label>
						<div class="controls">
							<input type="text" class="span10 m-wrap" id="a3801" name="a3801" maxlength="8"  value="${vo.a3801}" />
						</div>
					</div>
				</div>
				<div class="span6 ">
					<div id="a3817Group" class="control-group">
						<label class="control-label">转递日期</label>
						<div class="controls">
							<input type="text" class="span10 m-wrap" id="a3817" name="a3817" maxlength="8"  value="${vo.a3817}" />
						</div>
					</div>
				</div>
			</div>
			<div class="row-fluid">
				<div class="span6 ">
					<div id="jsrGroup" class="control-group">
						<label class="control-label">接收人</label>
						<div class="controls">
							<input type="text" class="span10 m-wrap" id="jsr" name="jsr" maxlength="24"  value="${vo.jsr}" />
						</div>
					</div>
				</div>
				<div class="span6 ">
					<div id="jbrXmGroup" class="control-group">
						<label class="control-label">经办人</label>
						<div class="controls">
							<input type="text" class="span10 m-wrap" id="jbrXm" name="jbrXm" maxlength="8"  value="${vo.jbrXm}" />
						</div>
					</div>
				</div>
			</div>
			<div class="row-fluid">
				<div class="span6 ">
					<div id="zryyGroup" class="control-group">
						<label class="control-label">转入说明</label>
						<div class="controls">
							<textarea id="zryy" name="zryy" class="span10 m-wrap" maxlength="128" rows="2" style="resize: none;">${vo.zryy}</textarea>
						</div>
					</div>
				</div>
				<div class="span6 ">
					<div id="zcyyGroup" class="control-group">
						<label class="control-label">转出说明</label>
						<div class="controls">
							<textarea id="zcyy" name="zcyy" class="span10 m-wrap" maxlength="512" rows="2" style="resize: none;">${vo.zcyy}</textarea>
						</div>
					</div>
				</div>
			</div>
		</dd>
	</dl>
</form>

<script type="text/javascript">
	jQuery(document).ready(function() {
	});
	var a38Form = new EstValidate("a38Form");
	function formSubmit(){
		var bool = a38Form.form();
		if(bool){
			$.ajax({
				url : "${path }/zzb/dzda/a38/save",
				type : "post",
				data : $("#form1").serialize(),
				headers:{
					OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
				},
				dataType : "json",
				success : function(data){
					if(data.code=="1"){
						setTimeout(function(){window.location.href = "${path}/zzb/dzda/a38/list"},2000);
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

	function checkSmxh(obj){
		var value=obj.value;
		if(value!=""){
			localPost("${path}/zzb/dzda/a38/smxh/check",{
				"smxh":$("#smxh").val(),
				"id":$("#id").val()
			},function(data) {
				if (!data.success) {
					showTip("提示", "扫描序号“"+value+"”已经存在，请重新输入！");
					setTimeout(function(){
						obj.value="";
						obj.focus();
					},510);

				}
			},"json", {"OWASP_CSRFTOKEN":"${sessionScope.OWASP_CSRFTOKEN}"});
		}
	}
</script>