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
	<title>增加职务信息</title>
</head>
<body>
<div >
	<!-- BEGIN FORM-->
	<div class="portlet box grey">

		<div class="portlet-title">

			<div class="caption">

				<i class="icon-reorder"></i>

				<span class="hidden-480">增加职务信息</span>

			</div>
			<div class="tools">
				<a href="javascript:location.reload();" class="reload"></a>

			</div>
		</div>
		<form action="${path}/zzb/jggl/b09/saveZw" class="form-horizontal" id="addZwform" method="post">
			<input type="hidden" id="b01Id" name="b01Id" value="${b01Id}">
			<table  border="0" style="width:100%;" cellPadding="5px">
				<div class="row-fluid">
					<div class="span6 ">
						<div id="b0901AGroup" class="control-group">
							<label class="control-label" ><span class="required">*</span>查询职务名称</label>
							<div class="controls" >
								<Tree:tree id="b0901B" valueName="b0901A" required="true"
										   selectClass="span9 m-wrap"
										   treeUrl="${path}/api/dictionary/tree?typeCode=ZB08-2006/ZWMC"
										   token="${sessionScope.OWASP_CSRFTOKEN}"
										   submitType="get" dataType="json" isSearch="false"
										   checkedByTitle="true" isSelectTree="true" defaultkeys=""
										   defaultvalues=""/>
							</div>
						</div>
					</div>
					<div class="span6 ">
						<div id="bDwzwmcGroup" class="control-group">
							<label class="control-label" ><span class="required">*</span>单位职务名称</label>
							<div class="controls">
								<input type="text" class="span9 m-wrap" name="bDwzwmc"  required="true" maxlength="128" id="bDwzwmc" value="" />
							</div>
						</div>
					</div>
				</div>

				<div class="row-fluid">
					<div class="span6 ">
						<div id="bZsglmcGroup" class="control-group">
							<label class="control-label" >职数管理名称</label>
							<div class="controls">
								<input type="text" class="span9 m-wrap" name="bZsglmc" maxlength="128" id="bZsglmc" value="" />
							</div>
						</div>
					</div>
					<div class="span6 ">
						<div id="b0907AGroup" class="control-group">
							<label class="control-label" >管理级别</label>
							<div class="controls" >
								<Tree:tree id="b0907" valueName="b0907A"
										   selectClass="span9 m-wrap"
										   treeUrl="${path}/api/dictionary/tree?typeCode=ZB09-2006/ZWJB"
										   token="${sessionScope.OWASP_CSRFTOKEN}"
										   submitType="get" dataType="json" isSearch="true"
										   checkedByTitle="true" isSelectTree="true" defaultkeys=""
										   defaultvalues=""/>
							</div>
						</div>
					</div>
				</div>

				<div class="row-fluid">
					<div class="span6 ">
						<div id="b0904AGroup" class="control-group">
							<label class="control-label" >职务类别</label>
							<div class="controls" >
								<SelectTag:SelectTag id="b0904" needNullValue="true"
													 valueName="b0904A"
													 defaultkeys="" token="${sessionScope.OWASP_CSRFTOKEN}"
													 defaultvalues=""
													 textClass="span9 m-wrap" radioOrCheckbox="radio"
													 selectUrl="${path}/api/dictionary/select?typeCode=ZB42-2006/ZWLB"/>
							</div>
						</div>
					</div>
					<div class="span6 ">
						<div id="b0911Group" class="control-group">
							<label class="control-label" >职数</label>
							<div class="controls">
								<input type="text" class="span9 m-wrap" name="b0911" maxlength="128" id="b0911" value="" />
							</div>
						</div>
					</div>
				</div>
				<div class="row-fluid">
					<div class="span6 ">
						<div id="bSfjrGroup" class="control-group">
							<label class="control-label" >由其他职务兼任</label>
							<div class="controls" style="margin-left: 200px">
								<label class="radio">
									<input type="radio" name="bSfjr" value="1" onclick="addHtml()"/>
									是&nbsp;&nbsp;&nbsp;
								</label>
								<label class="radio">
									<input type="radio" name="bSfjr" value="0" onclick="delHtml()"checked/>
									否
								</label>
							</div>
							<%--<label class="controls" >--%>
								<%--<a href="javascript:addHtmla()" >更多</a>--%>
							<%--</label>--%>
						</div>
					</div>
					<div class="span6 ">
						<div id="bGllbAGroup" class="control-group">
							<label class="control-label" >管理类别</label>
							<div class="controls" >
								<SelectTag:SelectTag id="bGllbB" needNullValue="true"
													 valueName="bGllbA"
													 defaultkeys="" token="${sessionScope.OWASP_CSRFTOKEN}"
													 defaultvalues=""
													 textClass="span9 m-wrap" radioOrCheckbox="radio"
													 selectUrl="${path}/api/dictionary/select?typeCode=2018/GLLB"/>
							</div>
						</div>
					</div>
				</div>
				<div id="addhtml" style="display: none">
					<div class="row-fluid">
						<div class="span6 ">
							<div id="jrB0901a1Group" class="control-group">
								<label class="control-label" >兼任职务</label>
								<div class="controls">
									<SelectTag:SelectTag id="jrGlB09001" needNullValue="true"
														 valueName="jrB0901a1"
														 defaultkeys="" token="${sessionScope.OWASP_CSRFTOKEN}"
														 defaultvalues=""
														 textClass="span9 m-wrap" radioOrCheckbox="radio"
														 selectUrl="${path}/zzb/jggl/b09/select?typeCode=pxczzw&b01Id=${b01Id}&b09Id=1"/>
								</div>
							</div>
						</div>
						<div class="span6 ">
							<div id="zs1Group" class="control-group">
								<label class="control-label" >兼任职数</label>
								<div class="controls">
									<input type="text" class="span9 m-wrap" name="zs1" number="true" maxlength="128" id="zs1" value="" />
								</div>
							</div>
						</div>
					</div>

				</div>

				<div class="row-fluid">
					<div class="span6 ">
						<div id="bSfbzcyGroup" class="control-group">
							<label class="control-label" >班子成员</label>
							<div class="controls" style="margin-left: 200px">
								<label class="radio">
									<input type="radio" name="bSfbzcy" value="1" checked/>
									是&nbsp;&nbsp;&nbsp;
								</label>
								<label class="radio">
									<input type="radio" name="bSfbzcy" value="0" />
									否
								</label>
							</div>
						</div>
					</div>
					<div class="span6 ">
						<div id="bSfldzwGroup" class="control-group">
							<label class="control-label" >领导职务</label>
							<div class="controls">
								<SelectTag:SelectTag id="xx" needNullValue="true"
													 valueName="bSfldzw"
													 defaultkeys="" token="${sessionScope.OWASP_CSRFTOKEN}"
													 defaultvalues="${vo.bSfldzw}"
													 textClass="span9 m-wrap" radioOrCheckbox="radio"
													 selectUrl="${path}/api/dictionary/select?typeCode=SFBS-2018"/>
							</div>
						</div>
					</div>
				</div>

				<div class="row-fluid">
					<div class="span6 ">
						<div id="bSftjzsGroup" class="control-group">
							<label class="control-label" >统计职数</label>
							<div class="controls" style="margin-left: 200px">
								<label class="radio">
									<input type="radio" name="bSftjzs" value="1"checked/>
									是&nbsp;&nbsp;&nbsp;
								</label>
								<label class="radio">
									<input type="radio" name="bSftjzs" value="0" />
									否
								</label>
							</div>
						</div>
					</div>
					<div class="span6 ">
						<div id="bZwztAGroup" class="control-group">
							<label class="control-label" >职务状态</label>
							<div class="controls">
								<SelectTag:SelectTag id="bZwztB" needNullValue="true"
													 valueName="bZwztA"
													 defaultkeys="" token="${sessionScope.OWASP_CSRFTOKEN}"
													 defaultvalues="${vo.bSfldzw}"
													 textClass="span9 m-wrap" radioOrCheckbox="radio"
													 selectUrl="${path}/api/dictionary/select?typeCode=2018/ZWZT"/>
							</div>
						</div>
					</div>
				</div>

				<div class="row-fluid">
					<div class="span6 ">
						<div id="bSfsyGroup" class="control-group">
							<label class="control-label" >需要试用</label>
							<div class="controls" style="margin-left: 200px">
								<div style="float:left;margin-top: 5px">
									<label class="radio">
										<input type="radio" name="bSfsy" onclick="syscdw()" value="1"/>
										是&nbsp;&nbsp;&nbsp;
									</label>
								</div>
								<div style="float:left;margin-left:20px;margin-top: 5px">
									<label class="radio">
										<input type="radio" name="bSfsy" value="0" onclick="syscdwClose()" checked/>
										否
									</label>
								</div>
								<div id="syscdwDiv" style="float:left;display:none">
									&nbsp;&nbsp;<input type="text" class="span4 m-wrap" name="bSysc" id="bSysc" value="" />
									<SelectTag:SelectTag id="bSyscdw" needNullValue=""
														 valueName=""
														 defaultkeys="" token="${sessionScope.OWASP_CSRFTOKEN}"
														 defaultvalues=""
														 textClass="span4 m-wrap" radioOrCheckbox="radio"
														 selectUrl="${path}/api/dictionary/select?typeCode=2018/SYSCDW"/>
								</div>
							</div>

						</div>
					</div>
					<div class="span6 ">
						<div id="bCzpxzwAGroup" class="control-group">
							<label class="control-label" >排序参照职务</label>
							<div class="controls">
								<SelectTag:SelectTag id="bCzpxzwB" needNullValue="true"
													 valueName="bCzpxzwA"
													 defaultkeys="" token="${sessionScope.OWASP_CSRFTOKEN}"
													 defaultvalues=""
													 textClass="span9 m-wrap" radioOrCheckbox="radio"
													 selectUrl="${path}/zzb/jggl/b09/select?typeCode=pxczzw&b01Id=${b01Id}&b09Id=1"/>
							</div>
						</div>
					</div>

				</div>

				<div class="row-fluid">
					<div class="span6 ">
						<div id="bPxGroup" class="control-group">
							<label class="control-label" ><span class="required">*</span>排序</label>
							<div class="controls">
								<input type="text" class="span9 m-wrap" name="bPx" number="true" required="true" maxlength="128" id="bPx" value="${bPx }" />
							</div>
						</div>
					</div>
				</div>

				<div class="row-fluid">
					<center>
						<div style="margin:auto;">
							<button class="btn green" type="button" style="padding:7px 20px;" onclick="formSubmit()">确定</button>
							<a class="btn" href="javascript:cencal()"><i class="icon-remove-sign"></i> 取消</a>
						</div>
					</center>
				</div>

			</table>

		</form>
	</div>
</div>
	<script type="text/javascript" src="${path }/js/common/loading.js"></script>
	<script type="text/javascript">
		var myLoading = new MyLoading("${path}",{zindex : 11111});

		function addHtml(){
			$("#addhtml").css("display","block");
		}

		function addHtmla(){
			var div=document.getElementById("addhtml");
			$("#addhtml").html();
		}

		function delHtml(){
			$("#addhtml").css("display","none");
		}
		var flag=0;

		bDwzwmc.addEventListener("input", function(event) {
			if(flag==1){
				$("#bZsglmc").val(this.value);
			}
		})
		bSysc.addEventListener("input", function(event) {
			var val = this.value;
			var num = removeNotNum(val);
			$("#bSysc").val(num);
		})

		function isRealNum(val){
			// isNaN()函数 把空串 空格 以及NUll 按照0来处理 所以先去除
			if(val === "" || val ==null){
				return false;
			}
			if(!isNaN(val)){
				return true;
			}else{
				return false;
			}
		}

		function removeNotNum(val){
			var str = val.split("");
			var num = "";
			for(var i=0;i<val.length;i++){
				if(isRealNum(str[i])){
					num+=str[i];
					if(num.length>=128){
						break;
					}
				}
			}
			return num;
		}

		bDwzwmc.addEventListener("keydown", function(event) {
			var bZsglmc=$("#bZsglmc").val();
			if(bZsglmc==this.value){
				flag=1;
			}else {
				flag=0;
			}
		});

		function syscdw(){
			$("#syscdwDiv").css("display","block");
		}
		function syscdwClose(){
			$("#syscdwDiv").css("display","none");
		}

		function cencal(){
			$.ajax({
				url : "${path }/zzb/jggl/b09/ajax/zwgl",
				type : "post",
				data : {"b01Id":"${b01Id}"},
				dataType : "html",
				headers:{
					OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
				},
				success : function(html){
					var view = $("#tab_show");
					view.html(html);
				},
				error : function(arg1, arg2, arg3){
					showTip("提示","职务管理加载失败");
				}
			});
		}

		var addZwform = new EstValidate("addZwform");
		function formSubmit(){
			var bool = addZwform.form();
			if(bool){
				$.ajax({
					url : "${path}/zzb/jggl/b09/save",
					type : "post",
					data : $('#addZwform').serialize(),
					dataType : "json",
					headers: {
						"OWASP_CSRFTOKEN":"${sessionScope.OWASP_CSRFTOKEN}"
					},
					success : function(json){
						showTip("提示","保存成功!",2000);
						$('#addModal').modal('hide');
						$('#addDiv').html("");
						cencal();
					},
					error : function(arg1, arg2, arg3){
						showTip("提示","出错了请联系管理员",2000);
					}
				});

			}
		}



	</script>
</body>
</html>
