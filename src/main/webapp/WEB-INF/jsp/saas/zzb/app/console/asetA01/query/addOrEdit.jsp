<%@ page import="java.util.Date" %>
<%@ page import="com.hisun.util.DateUtil" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/inc/servlet.jsp" %>
<%@include file="/WEB-INF/jsp/inc/taglib.jsp" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="${path }/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css"/>
<title>查询条件设置</title>
</head>
<body>
	<div class="container-fluid">

		<div class="row-fluid">
			<div class="span12">
				<%-- BEGIN SAMPLE FORM PORTLET 表单主体--%>   

				<div class="portlet box grey">

					<div class="portlet-title">

						<div class="caption">

							<i class="icon-reorder"></i>

							<span class="hidden-480">查询条件设置</span>

						</div>
						<div class="clearfix fr">
							<button type="button" class="btn green" onclick="formSubmit('query')">查询</button>
							<button type="button" class="btn green" onclick="saveAndQuery()"><i class="icon-ok"></i>保存并查询</button>
							<c:if test="${addType eq 'a01List'}">
								<a class="btn" href="${path }/zzb/app/console/asetA01Query/"><i class="icon-remove-sign"></i> 取消</a>
							</c:if>
							<c:if test="${empty addType}">
								<a class="btn" href="${path }/zzb/app/console/asetA01Query/queryList"><i class="icon-remove-sign"></i> 取消</a>
							</c:if>

						</div>

					</div>

					<div class="portlet-body form">
						<!-- BEGIN FORM-->
						<form action="" class="form-horizontal" id="form1"  method="post">
							<input type="hidden" id="id" name="id" value="${vo.id}">
							<input type="hidden" id="saveType" name="saveType" value="">
							<input type="hidden" id="queryName" name="queryName" value="${vo.queryName}">
							<input type="hidden" id="querySort" name="querySort" value="${vo.querySort}">

							<div id="conditionModal" class="modal container hide fade" tabindex="-1" data-width="400">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button data-dismiss="modal" class="close"  type="button"></button>
											<h3 class="modal-title" id="title" >
												输入查询名称、排序
											</h3>
										</div>
										<div class="modal-body form-horizontal" id="dabzAddDiv">
											<div class="control-group">
												<label class="control-label" style=" width: 70px;"><span class="required">*</span>查询名称</label>
												<div class="controls" style="margin-left: 80px;">
													<input class="m-wrap" type="text" id="queryNameTmp" name="queryNameTmp"  maxlength="45" value="${vo.queryName}"/>
												</div>
											</div>
											<div class="control-group">
												<label class="control-label"  style=" width: 70px;"><span class="required">*</span>排序</label>
												<div class="controls" style="margin-left: 80px;">
													<input class="m-wrap" type="text" id="querySortTmp" name="querySortTmp"  maxlength="45" value="${vo.querySort}"/>
												</div>
											</div>
											<div id="ErrMsg" name="ErrMsg" style="color: red;margin-left: 80px;"></div>
											<div class="control-group mybutton-group" style="text-align: right;">
												<button type="button" class="btn green" onclick="formSubmit('save')"><i class="icon-ok"></i> 确定</button>
												<button type="button" class="btn btn-default"  data-dismiss="modal"><i class="icon-remove-sign"></i> 取消</button>
											</div>
										</div>
									</div>
								</div>
							</div>
							<dl class="dlattrbute">
								<dt><a href="###">干部信息</a></dt>
								<dd>
									<table  border="0" style="width:100%;" cellPadding="5px">
										<tr>
											<td>
												<div id="xmGroup" class="control-group">
													<label class="control-label">姓名</label>
													<div class="controls">
														<input class="span8 m-wrap" type="text" id="xm" name="xm"  maxlength="45" value="${vo.xm}"/>
													</div>
												</div>
											</td>
											<td>
												<div id="xbGroup" class="control-group">
													<label class="control-label">性别</label>
													<div class="controls">
														<select class="span8 m-wrap" tabindex="-1" name="xb" id="xb" >
															<option value="">全部</option>
															<option value="男" <c:if test="${vo.xb == '男'}">selected="selected"</c:if>>男</option>
															<option value="女" <c:if test="${vo.xb == '女'}">selected="selected"</c:if>>女</option>
														</select>
													</div>
												</div>
											</td>
										</tr>
										<tr>
											<td>
												<div >
													<label class="control-label">年龄</label>
													<div class="controls">
														<span  id="nlStartGroup" style=" display: inline-block;float: left; ">
															<input class="span8 m-wrap" style="width: 100px;" type="text" id="nlStart" name="nlStart"  maxlength="45" value="${vo.nlStart}"/>
														</span>

														<span  id="nlEndGroup" >
															--<input class="span8 m-wrap" style="width: 100px;" type="text" id="nlEnd" name="nlEnd"  maxlength="45" value="${vo.nlEnd}"/>
														</span>
													</div>
												</div>
											</td>
											<td width="50%">
												<div id="mzGroup" class="control-group">
													<label class="control-label">民族</label>
													<div class="controls">
														<input class="span8 m-wrap" type="text" id="mz" name="mz"  maxlength="45" value="${vo.mz}"/>
													</div>
												</div>
											</td>

										</tr>
										<tr>
											<td width="50%">
												<div id="jgGroup" class="control-group">
													<label class="control-label">籍贯</label>
													<div class="controls">
														<input class="span8 m-wrap" type="text" id="jg" name="jg"  maxlength="45" value="${vo.jg}"/>
													</div>
												</div>
											</td>
											<td>
												<div id="csdGroup" class="control-group">
													<label class="control-label">出生地</label>
													<div class="controls">
														<input class="span8 m-wrap" type="text" id="csd" name="csd"  maxlength="45" value="${vo.csd}"/>
													</div>
												</div>
											</td>
										</tr>
										<tr>
											<td width="50%">
												<div id="xrzwGroup" class="control-group">
													<label class="control-label">现任职务</label>
													<div class="controls">
														<input class="span8 m-wrap" type="text" id="xrzw" name="xrzw"  maxlength="45" value="${vo.xrzw}"/>
													</div>
												</div>
											</td>
											<td width="50%">
												<div class="control-group">
													<label class="control-label">现任职务时间</label>
													<div class="controls">
														<span  id="xrzwsjStartGroup" style=" display: inline-block;float: left; ">
															<input class="span8 m-wrap" style="width: 100px;" type="text" id="xrzwsjStart" name="xrzwsjStart"  maxlength="45" value="${vo.xrzwsjStart}"/>
														</span>

														<span  id="xrzwsjEndGroup" >
															--<input class="span8 m-wrap" style="width: 100px;" type="text" id="xrzwsjEnd" name="xrzwsjEnd"  maxlength="45" value="${vo.xrzwsjEnd}"/>
														</span>
													</div>
												</div>
											</td>
										</tr>
										<tr>
											<td width="50%">
												<div id="xrzjGroup" class="control-group">
													<label class="control-label">现任职级</label>
													<div class="controls">
														<select class="span8 m-wrap" tabindex="-1" name="xrzj" id="xrzj" >
															<option value="">全部</option>
															<option value="厅局级正职" <c:if test="${vo.xrzj == '厅局级正职'}">selected="selected"</c:if>>厅局级正职</option>
															<option value="厅局级副职" <c:if test="${vo.xrzj == '厅局级副职'}">selected="selected"</c:if>>厅局级副职</option>
															<option value="县处级正职" <c:if test="${vo.xrzj == '县处级正职'}">selected="selected"</c:if>>县处级正职</option>
															<option value="县处级副职" <c:if test="${vo.xrzj == '县处级副职'}">selected="selected"</c:if>>县处级副职</option>
															<option value="科员" <c:if test="${vo.xrzj == '科员'}">selected="selected"</c:if>>科员</option>
														</select>
													</div>
												</div>
											</td>
											<td width="50%">
												<div class="control-group">
													<label class="control-label">现任职级时间</label>
													<div class="controls">
														<span  id="xrzjsjStartGroup" style=" display: inline-block;float: left; ">
															<input class="span8 m-wrap" style="width: 100px;" type="text" id="xrzjsjStart" name="xrzjsjStart"  maxlength="45" value="${vo.xrzjsjStart}"/>
														</span>

														<span  id="xrzjsjEndGroup" >
															--<input class="span8 m-wrap" style="width: 100px;" type="text" id="xrzjsjEnd" name="xrzjsjEnd"  maxlength="45" value="${vo.xrzjsjEnd}"/>
														</span>
													</div>
												</div>
											</td>
										</tr>
										<tr>
											<td width="50%">
												<div id="dpGroup" class="control-group">
													<label class="control-label">党派</label>
													<div class="controls">
														<select class="span8 m-wrap" tabindex="-1" name="dp" id="dp" >
															<option value="">全部</option>
															<option value="中共党员" <c:if test="${vo.dp == '中共党员'}">selected="selected"</c:if>>中共党员</option>
															<option value="其他" <c:if test="${vo.dp == '其他'}">selected="selected"</c:if>>其他</option>
														</select>
													</div>
												</div>
											</td>
											<td width="50%">
												<div class="control-group">
													<label class="control-label">入党时间</label>
													<div class="controls">
														<span  id="rdsjStartGroup" style=" display: inline-block;float: left; ">
															<input class="span8 m-wrap" style="width: 100px;" type="text" id="rdsjStart" name="rdsjStart"  maxlength="45" value="${vo.rdsjStart}"/>
														</span>
														<span  id="rdsjEndGroup" >
															--<input class="span8 m-wrap" style="width: 100px;" type="text" id="rdsjEnd" name="rdsjEnd"  maxlength="45" value="${vo.rdsjEnd}"/>
														</span>
													</div>
												</div>
											</td>
										</tr>
										<tr>
											<td>
												<div id="qrzxlGroup" class="control-group">
													<label class="control-label">学历</label>
													<div class="controls">
														<input class="span8 m-wrap" type="text" id="qrzxl" name="qrzxl"  maxlength="45" value="${vo.qrzxl}"/>
													</div>
												</div>
											</td>
											<td width="50%">
												<div id="qrzxwGroup" class="control-group">
													<label class="control-label">学位</label>
													<div class="controls">
														<input class="span8 m-wrap" type="text" id="qrzxw" name="qrzxw"  maxlength="45" value="${vo.qrzxw}"/>
													</div>
												</div>
											</td>
										</tr>
										<tr>
											<td width="50%">
												<div id="qrzByyxGroup" class="control-group">
													<label class="control-label">毕业院校</label>
													<div class="controls">
														<input class="span8 m-wrap" type="text" id="qrzByyx" name="qrzByyx"  maxlength="45" value="${vo.qrzByyx}"/>
													</div>
												</div>
											</td>
											<td>
												<div id="qrzZyGroup" class="control-group">
													<label class="control-label">专业</label>
													<div class="controls">
														<input class="span8 m-wrap" type="text" id="qrzZy" name="qrzZy"  maxlength="45" value="${vo.qrzZy}"/>
													</div>
												</div>
											</td>

										</tr>

									</table>
								</dd>
							</dl>
							<dl class="dlattrbute">
								<dt><a href="###">单位信息</a></dt>
								<dd>
									<table  border="0" style="width:100%;" cellPadding="5px">
										<tr>

											<td width="50%">
												<div id="b01IdGroup" class="control-group">
													<label class="control-label">单位</label>
													<div class="controls">
														<div class="btn-group span12" style="font-size: 12px">
															<input type="hidden" name="b01Id" id="b01Id" value="${vo.b01Id}"/>
															<input type="text" id="b0101" name="b0101" readonly="readonly"
																   class="span8 m-wrap" style="cursor: pointer;" onclick="$('#objectTreeSelDiv').toggle();" value="${vo.b0101}">
															<div class="span8 m-wrap" style="border:solid 1px #ccc;overflow-y: scroll;overflow-x: auto;position: absolute;
											top: 100%;left: 0;z-index: 1000;display: none;float: left;list-style: none;text-shadow: none;padding: 0px;margin: 0px;height: 200px;background-color: white;" id="objectTreeSelDiv">
																<ul id="treeDemo" class="ztree" style="margin: 0px;padding: 0px;height: 200px;"></ul>
															</div>
														</div>

													</div>
												</div>
											</td>
											<td>
												<div class="control-group">
													<label class="control-label">&nbsp;</label>
													<div class="controls">
														&nbsp;
													</div>
												</div>
											</td>
										</tr>
									</table>
								</dd>
							</dl>
							<dl class="dlattrbute">
								<dt><a href="###">工作经历</a></dt>
								<dd>
									<table  border="0" style="width:100%;" cellPadding="5px">
										<tr>

											<td width="50%">
												<div id="gzjlStrGroup" class="control-group">
													<label class="control-label">工作经历</label>
													<div class="controls">
														<input class="span8 m-wrap" type="text" id="gzjlStr" name="gzjlStr"  maxlength="45" value="${vo.gzjlStr}"/>
													</div>
												</div>
											</td>
											<td>
												<div class="control-group">
													<label class="control-label">&nbsp;</label>
													<div class="controls">
														&nbsp;
													</div>
												</div>
											</td>
										</tr>
									</table>
								</dd>
							</dl>
						</form>
					</div>

				</div>

				<%-- END SAMPLE FORM PORTLET--%>
			</div>
		</div>
		
		<%-- END PAGE CONTENT--%>  
	</div>
	<script type="text/javascript" src="${path }/js/jquery.validate.min.js"></script>
	<script type="text/javascript" src="${path }/js/common/est-validate-init.js"></script>
	<script type="text/javascript" src="${path }/js/common/validate-message.js"></script>
	<script type="text/javascript" src="${path }/js/common/30CloudAjax.js"></script>
	<script type="text/javascript" src="${path }/js/common/DataValidate.js"></script>
	<script type="text/javascript" src="<%=path%>/js/bootstrap-datepicker.js"></script>
	<script type="text/javascript" src="<%=path%>/js/bootstrap-datepicker.zh-CN.js"></script>
	<script type="text/javascript" src="${path }/js/zTree/jquery.ztree.all-3.5.js"></script>
<script type="text/javascript">
	
//	(function(){
//		App.init();
//
//	})();
jQuery(document).ready(function() {
	App.init();
	var xrzwsjStart = $("#xrzwsjStart").datepicker({
		language:  'zh-CN',
		format: "yyyymmdd",
		pickerPosition: "bottom-left",
		weekStart : 1,
		autoclose : true
	});
	var xrzwsjEnd = $("#xrzwsjEnd").datepicker({
		language:  'zh-CN',
		format: "yyyymmdd",
		pickerPosition: "bottom-left",
		weekStart : 1,
		autoclose : true
	});
	var xrzjsjStart = $("#xrzjsjStart").datepicker({
		language:  'zh-CN',
		format: "yyyymmdd",
		pickerPosition: "bottom-left",
		weekStart : 1,
		autoclose : true
	});
	var xrzjsjEnd = $("#xrzjsjEnd").datepicker({
		language:  'zh-CN',
		format: "yyyymmdd",
		pickerPosition: "bottom-left",
		weekStart : 1,
		autoclose : true
	});
});
	var myVld = new EstValidate("form1");
	function saveAndQuery(){
		if(checkNlisNum() == false){
			return;
		}
		var bool = myVld.form();
		if(bool) {
			document.getElementById("ErrMsg").innerHTML="";
			$('#conditionModal').modal({
				keyboard: true
			});
		}
	}
	var setting = {
		view : {
			selectedMulti : false
		},
		edit : {
			enable : true,
			showRemoveBtn : false,
			showRenameBtn : false
		},
		data : {
			simpleData : {
				enable : true
			}
		},
		callback : {
			onCheck : zTreeOnClick
		},
		check: {
			enable: true,
			nocheckInherit: true

		}
	};
	var resTree;
	$(function(){
		localPost("<%=path%>/zzb/app/console/bset/ajax/load/tree",{"status":1}, function(data,status){
			if (status == "success") {
				//setting.data.key.url = "_url" ;
				resTree = $.fn.zTree.init($("#treeDemo"), setting, data.customTree);

				localPost("<%=path%>/zzb/app/console/asetA01Query/selectB01?queryId=${vo.id}",null, function(data,status){
					if (status == "success") {
						var resIdList = data.data;
						if (resTree != null && resIdList != null) {

							$.each(resIdList, function(index, resId) {
								var node = resTree.getNodeByParam("id", resId, null);
								if(node){
									resTree.checkNode(node, true, false);
								}
							});
						}
					}
				},"json",{"OWASP_CSRFTOKEN":"${sessionScope.OWASP_CSRFTOKEN}"});
			}
		},"json",{"OWASP_CSRFTOKEN":"${sessionScope.OWASP_CSRFTOKEN}"});
	});

	function zTreeOnClick() {
		var nodes = resTree.getCheckedNodes(true);
		var b01IdObj = $("[id='b01Id']");
		var b0101Obj = $("[id='b0101']");
		var b01Ids = "";
		var b0101s = "";
		$.each(nodes, function(index, node) {
			if(b01Ids==""){
				b01Ids = node.id;
				b0101s = node.name;
			}else{
				b01Ids = b01Ids+";"+ node.id;
				b0101s = b0101s+","+ node.name;
			}
		});
		b01IdObj.val(b01Ids);
		b0101Obj.val(b0101s);
	}
function isNumberTmp(str) {
	var Letters = "0123456789";
	var Letters2 = "-0123456789";
	if(str.length==0)
		return false;

	//对首位进行附加判断
	if(Letters2.indexOf(str.charAt(0)) == -1){
		return false;
	}else{
		for (i = 1; i < str.length; i++) {
			var checkChar = str.charAt(i);
			if (Letters.indexOf(checkChar) == -1)
				return false;
		}
		return true;
	}
}
	function checkNlisNum(){
		var nlStart = $("[id='nlStart']").val();
		var nlEnd = $("[id='nlEnd']").val();

		if(nlStart!=""){
			if(isNumberTmp(nlStart)==false){
				showTip("提示","年龄必须为数字",2000);
				return false;
			}
		}
		if(nlEnd!=""){
			if(isNumberTmp(nlEnd)==false){
				showTip("提示","年龄必须为数字",2000);
				return false;
			}
		}
		return true;
	}
	function formSubmit(saveType){
		if(checkNlisNum() == false){
			return;
		}
		if(saveType=="save"){
			if($("#queryNameTmp").val()==""){
//				showTip("提示","请输入条件名称",2000);
				document.getElementById("ErrMsg").innerHTML  = "请输入查询名称";
				$("#queryNameTmp").focus();
				return;
			}
			if($("#querySortTmp").val()==""){
//				showTip("提示","请输入排序",2000);
				document.getElementById("ErrMsg").innerHTML  = "请输入排序";
				$("#querySortTmp").focus();
				return;
			}else{
				if(isNumberTmp($("#querySortTmp").val())==false){
					document.getElementById("ErrMsg").innerHTML  = "排序必须为数字";
					$("#querySortTmp").focus();
//					showTip("提示","排序必须为数字",2000);
					return;
				}
			}
			document.getElementById("ErrMsg").innerHTML="";
			$("#queryName").val($("#queryNameTmp").val());
			$("#querySort").val($("#querySortTmp").val());
		}
		var bool = myVld.form();
		if(bool){
			$("#saveType").val(saveType);
			$.cloudAjax({
				path : '${path}',
				url : "${path }/zzb/app/console/asetA01Query/save",
				type : "post",
				data : $("#form1").serialize(),
				dataType : "json",
				success : function(data){
					if(data.success){
//						showTip("提示","操作成功",2000);
						window.location.href = "${path}/zzb/app/console/asetA01Query/?queryId="+data.id;
					}else{
						showTip("提示", json.message, 2000);
					}
				},
				error : function(){
					showTip("提示","出错了请联系管理员",2000);
				}
			});
		}
	}
	
</script>
</body>
</html>
