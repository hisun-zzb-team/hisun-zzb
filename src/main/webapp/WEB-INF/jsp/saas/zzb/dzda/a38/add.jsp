<%@ taglib prefix="Select" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/inc/servlet.jsp" %>
<%@include file="/WEB-INF/jsp/inc/taglib.jsp" %>
<%--
  ~ Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
  ~ http://www.hn-hisun.com
  ~ 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
  --%>
<title>添加档案</title>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<div class="container-fluid">

	<div class="row-fluid">
		<div class="span12">
			<%-- BEGIN SAMPLE FORM PORTLET 表单主体--%>

			<div class="portlet box grey">
				<div class="portlet-title">

					<div class="caption">

						<i class="icon-reorder"></i>

						<span class="hidden-480">添加档案</span>

					</div>
					<div class="relationbetTop_but">

						<button class="btn green" type="button" style="padding:7px 20px;" onclick="formSubmit('0')">待审</button>
						<button class="btn green" type="button" style="padding:7px 20px;" onclick="formSubmit('1')">入库</button>
						<a class="btn " href="javascript:cancel()"><i class="icon-remove-sign"></i> 取消</a>
					</div>
				</div>
				<form action="" class="form-horizontal" id="form1" method="post">
					<input type="hidden" name="dataType" value="${vo.dataType}" id="dataType"/>
					<input type="hidden" name="parentId" value="${vo.parentId}" id="parentId"/>
					<input type="hidden" name="sjzt" value="${vo.sjzt}" id="sjzt"/>

					<input type="hidden" name="id" value="${vo.id }"/>
					<dl class="dlattrbute">
						<dt><a href="###">基本信息</a></dt>
						<dd>
							<div class="row-fluid">
								<div class="span6 ">
									<div id="dabhGroup" class="control-group">
										<label class="control-label">档案编号</label>
										<div class="controls">
											<input type="text" class="span10 m-wrap" id="dabh" name="dabh" maxlength="128"  value="" />
										</div>
									</div>
								</div>

								<div class="span6 ">
									<div id="smxhGroup" class="control-group">
										<label class="control-label">扫描序号</label>
										<div class="controls">
											<input type="text" class="span10 m-wrap" name="smxh"  id="smxh" maxlength="128" value="" onblur="checkSmxh(this)"/>
										</div>
									</div>
								</div>

							</div>
							<div class="row-fluid">
								<div class="span6 ">
									<div id="a0101Group" class="control-group">
										<label class="control-label"><span class="Required">*</span>姓名</label>
										<div class="controls">
											<input type="text" class="span10 m-wrap" name="a0101" required maxlength="24" id="a0101" value="" />
										</div>
									</div>
								</div>
								<div class="span6 ">
									<div id="a0104Group" class="control-group">
										<label class="control-label">性别</label>
										<div class="controls">
											<SelectTag:SelectTag id="a0104" valueName="a0104Content" needNullValue="true" textClass="m-wrap span10" radioOrCheckbox="radio" selectUrl="${path}/api/dictionary/select?typeCode=GB/T2261.1-2003"/>
										</div>
									</div>
								</div>
							</div>
							<div class="row-fluid">
								<div class="span6 ">
									<div id="a0107Group" class="control-group">
										<label class="control-label">出生日期</label>
										<div class="controls">
											<input type="text" class="span10 m-wrap" id="a0107" name="a0107" maxlength="64" type="date" placeholder="日期格式 例如：2018或201801或20180101" isDate="true" dateformat="yyyy,yyyymm,yyyymmdd"  />
										</div>
									</div>
								</div>
								<div class="span6 ">
									<div id="a0111BGroup" class="control-group">
										<label class="control-label">籍贯</label>
										<div class="controls">
											<Tree:tree id="a0111B" valueName="a0111A"  selectClass="span10 m-wrap" treeUrl="${path}/api/dictionary/tree?typeCode=ZB01-2006/GQMC" token="${sessionScope.OWASP_CSRFTOKEN}"
													   submitType="get" dataType="json" isSearch="true" isSelectTree="true"/>
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
													   submitType="get" dataType="json" isSearch="true" isSelectTree="true"/>
										</div>
									</div>
								</div>
								<div class="span6 ">
									<div id="dutyLevelTimeBaseGroup" class="control-group">
										<label class="control-label">现职级时间</label>
										<div class="controls">
											<input type="text" class="span10 m-wrap" id="dutyLevelTimeBase" name="dutyLevelTimeBase" maxlength="64"  value=""  placeholder="日期格式 例如：2018或201801或20180101" isDate="true" dateformat="yyyy,yyyymm,yyyymmdd"/>
										</div>
									</div>
								</div>
							</div>
							<div class="row-fluid">
								<div class="span6 ">
									<div id="gbztContentGroup" class="control-group">
										<label class="control-label">干部状态</label>
										<div class="controls">
											<Tree:tree id="gbztCode" valueName="gbztContent"  selectClass="span10 m-wrap" treeUrl="${path}/api/dictionary/tree?typeCode=SAN_GBZT" token="${sessionScope.OWASP_CSRFTOKEN}"
													   submitType="get" dataType="json" isSearch="false" isSelectTree="true"/>
										</div>
									</div>
								</div>
								<div class="span6 ">
									<div id="a0157Group" class="control-group">
										<label class="control-label">单位职务</label>
										<div class="controls">
											<input type="text" class="span10 m-wrap" id="a0157" name="a0157" maxlength="64"  value="" />
										</div>
									</div>
								</div>
							</div>
							<div class="row-fluid">
								<div class="span6 ">
									<div id="daztContentGroup" class="control-group">
										<label class="control-label">档案状态</label>
										<div class="controls">
											<Tree:tree id="daztCode" valueName="daztContent"  selectClass="span10 m-wrap" treeUrl="${path}/api/dictionary/tree?typeCode=SAN_DAZT" token="${sessionScope.OWASP_CSRFTOKEN}"
													   submitType="get" dataType="json" isSearch="false" isSelectTree="true"/>
										</div>
									</div>
								</div>
								<div class="span6 ">
									<div id="a3814Group" class="control-group">
										<label class="control-label">版本类别</label>
										<div class="controls">
											<input type="text" class="span10 m-wrap" id="a3814" name="a3814" maxlength="64"  value="" />
										</div>
									</div>
								</div>
							</div>
							<div class="row-fluid">
								<div class="span6 ">
									<div id="a3834Group" class="control-group">
										<label class="control-label">档案位置</label>
										<div class="controls">
											<textarea id="a3834" name="a3834" class="span10 m-wrap" maxlength="128" rows="2" style="resize: none;"></textarea>
										</div>
									</div>
								</div>
								<div class="span6 ">
									<div id="a3824Group" class="control-group">
										<label class="control-label">备注</label>
										<div class="controls">
											<textarea id="a3824" name="a3824" class="span10 m-wrap" maxlength="512" rows="2" style="resize: none;"></textarea>
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
											<input type="text" class="span10 m-wrap" id="a3804A" name="a3804A" maxlength="64"  value="" />
										</div>
									</div>
								</div>
								<div class="span6 ">
									<div id="a3807AGroup" class="control-group">
										<label class="control-label">转去单位</label>
										<div class="controls">
											<input type="text" class="span10 m-wrap" id="a3807A" name="a3807A" maxlength="64"  value="" />
										</div>
									</div>
								</div>
							</div>
							<div class="row-fluid">
								<div class="span6 ">
									<div id="a3801Group" class="control-group">
										<label class="control-label">接收日期</label>
										<div class="controls">
											<input type="text" class="span10 m-wrap" id="a3801" name="a3801" maxlength="8"  value=""  placeholder="日期格式 例如：2018或201801或20180101" isDate="true" dateformat="yyyy,yyyymm,yyyymmdd"/>
										</div>
									</div>
								</div>
								<div class="span6 ">
									<div id="a3817Group" class="control-group">
										<label class="control-label">转递日期</label>
										<div class="controls">
											<input type="text" class="span10 m-wrap" id="a3817" name="a3817" maxlength="8"  value=""  placeholder="日期格式 例如：2018或201801或20180101" isDate="true" dateformat="yyyy,yyyymm,yyyymmdd"/>
										</div>
									</div>
								</div>
							</div>
							<div class="row-fluid">
								<div class="span6 ">
									<div id="jsrGroup" class="control-group">
										<label class="control-label">接收人</label>
										<div class="controls">
											<input type="text" class="span10 m-wrap" id="jsr" name="jsr" maxlength="24"  value="" />
										</div>
									</div>
								</div>
								<div class="span6 ">
									<div id="jbrXmGroup" class="control-group">
										<label class="control-label">经办人</label>
										<div class="controls">
											<input type="text" class="span10 m-wrap" id="jbrXm" name="jbrXm" maxlength="8"  value="" />
										</div>
									</div>
								</div>
							</div>
							<div class="row-fluid">
								<div class="span6 ">
									<div id="zryyGroup" class="control-group">
										<label class="control-label">转入说明</label>
										<div class="controls">
											<textarea id="zryy" name="zryy" class="span10 m-wrap" maxlength="128" rows="2" style="resize: none;"></textarea>
										</div>
									</div>
								</div>
								<div class="span6 ">
									<div id="zcyyGroup" class="control-group">
										<label class="control-label">转出说明</label>
										<div class="controls">
											<textarea id="zcyy" name="zcyy" class="span10 m-wrap" maxlength="512" rows="2" style="resize: none;"></textarea>
										</div>
									</div>
								</div>
							</div>
						</dd>
					</dl>
				</form>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="${path }/js/common/DataValidate.js"></script>
<script type="text/javascript">
	jQuery(document).ready(function() {
		App.init();
	});
	var form1 = new EstValidate("form1");
	function formSubmit(sjzt){
		$("#sjzt").val(sjzt);
		var bool = form1.form();
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
						if(sjzt=="0"){
							setTimeout(function(){window.location.href = "${path}/zzb/dzda/a38/shList"},2000);
						}else{
							setTimeout(function(){window.location.href = "${path}/zzb/dzda/a38/list"},2000);
						}
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

	function cancel(){
		if("${listType}"=="shList"){
			window.location.href = "${path}/zzb/dzda/a38/shList";
		}else{
			window.location.href = "${path}/zzb/dzda/a38/list";
		}
	}
</script>