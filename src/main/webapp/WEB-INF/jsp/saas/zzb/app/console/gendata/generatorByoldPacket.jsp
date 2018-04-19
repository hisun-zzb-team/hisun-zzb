<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/inc/servlet.jsp" %>
<%@include file="/WEB-INF/jsp/inc/taglib.jsp" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>生成APP数据包</title>
	<style type="text/css">


		.header .brand { padding-top:8px;}

		.caption_title{font-size:14px; float:left; border-left: solid 3px #219ae0; padding-left:10px; margin-top:8px;}
		input[type="radio"], input[type="checkbox"]{ margin:0;}
		.ulChoicelist{ overflow:hidden; margin-left:40px;}
		.ulChoicelist li{ padding:4px 0; height:35px; overflow:hidden;}
		.ulChoicelist li.heightauto{ height:auto;}
		.ulChoicelist li h4{ font-weight:bold; font-size:15px; border-bottom: dashed 1px #DDDDDD; padding:0px 0 10px 0; margin:0; cursor:pointer;}
		.ulChoicelist li h4 a{ display:inline-block; color:#333333;}
		.ulChoicelist li h4 a:hover{ color:#0083f1}
		.ulChoicelist li h4 label{ font-weight:bold; font-size:15px;}
		.ulChoicelist li h4:hover{color:#1686E1;}
		.ulChoicelist li .checkbox{  margin-right:5px;}
		.Choicetwo{ padding-left:20px; padding-top:10px;}
		.Choicetwo p{ padding:3px 0;}
	</style>
</head>
<body class="page-header-fixed white">
	<div class="row-fluid mb10">
		<div class="caption_title">生成APP数据包</div>
		<div class="fr">
			<a type="button" class="btn green" href="javascript:formSubmit()"  style="color: #ffffff;height: auto"><i class="icon-download"></i>生成</a>
			<a type="button" class="btn" style="height: 20px;" href="${path }/zzb/app/console/gendata/loadGenerator" ><i class="icon-refresh"></i>刷新</a>
			<a class="btn" href="${path}/zzb/app/console/gendata/"><i class="icon-undo"></i>返回</a>
		</div>
	</div>
<form action="${path }/zzb/app/console/gendata/generator" class="form-horizontal" id="form1" method="post">
	<input type="hidden" name="oldcheckBoxTypeValues" value="" id="oldcheckBoxTypeValues">
	<input type="hidden" name="oldcheckHyyjValues" value="" id="oldcheckHyyjValues">
	<input type="hidden" name="oldcheckGbmcValues" value="" id="oldcheckGbmcValues">
	<input type="hidden" name="oldcheckGbtjValues" value="" id="oldcheckGbtjValues">
	<input type="hidden" name="oldPacketId" value="${oldPacketId}" id="oldPacketId">

	<input type="hidden" name="checkBoxTypeValues" value="" id="checkBoxTypeValues">
	<input type="hidden" name="checkHyyjValues" value="" id="checkHyyjValues">
	<input type="hidden" name="checkGbmcValues" value="" id="checkGbmcValues">
	<input type="hidden" name="checkGbtjValues" value="" id="checkGbtjValues">

	<div class="row-fluid mb10">
		<div id="packetNameGroup" class="control-group">
			<label class="control-label" style="width:100px">数据包名称<span class="required">*</span></label>
			<div class="controls" style="margin-left:100px">
				<input type="text" class="span6 m-wrap" name="packetName" required maxlength="200" id="packetName" value="${packetName}"/>
			</div>
		</div>

		<div style="width: 50%;float: left">
			<b style="margin-left:40px;font-size:15px;">&nbsp;</b>
			<ul class="ulChoicelist">
				<li class="heightauto">
					<h4 class=""><input class="checkbox"  id="CheckAllHyyj" type="checkbox" onclick="setCheckedValues(this,'hyyj')"  value="hyyj" /><a href="###">会议研究</a></h4>
					<div class="Choicetwo">
						<c:forEach items="${shpcVos}" var="vo">
							<p><label><input clt="checkBoxHyyjValueGroup" id="checkBoxHyyjValue" name="checkBoxHyyjValue"  type="checkbox" onclick="setCheckedValues(this,'hyyj')"  value="${vo.id}" />${vo.pcmc}</label></p>
						</c:forEach>
					</div>
				</li>
				<li>
					<h4><input class="checkbox" type="checkbox" onclick="setCheckedValues(this,'GBCX')" id="checkBoxGBCX"  name="checkBoxValue" value="GBCX" /> 干部和职数查询</h4>
				</li>
				<%--<li>--%>
					<%--<h4><input class="checkbox" type="checkbox" onclick="setCheckedValues(this,'ZSCX')"  id="checkBoxZSCX"  name="checkBoxValue" value="ZSCX" /> 职数查询</h4>--%>
				<%--</li>--%>
				<li class="heightauto">
					<h4  class=""><input class="checkbox" type="checkbox" onclick="setCheckedValues(this,'gbmc')"  id="CheckAllGbmc" value="gbmc" /> <a href="###">干部名册</a></h4>
					<div class="Choicetwo">
						<c:forEach items="${gbmcVos}" var="vo">
							<p><label><input clt="checkBoxGbmcValueGroup" id="checkBoxGbmcValue" name="checkBoxGbmcValue"  type="checkbox" onclick="setCheckedValues(this,'gbmc')"  value="${vo.id}" />${vo.mc}</label></p>
						</c:forEach>
					</div>
				</li>
				<li class="heightauto">
					<h4  class=""><input class="checkbox" type="checkbox" onclick="setCheckedValues(this,'gbdwtj')"  id="CheckAllGbtj" value="gbdwtj" />  <a href="###">干部队伍统计</a></h4>
					<div class="Choicetwo">
						<c:forEach items="${gbtjVos}" var="vo">
							<p><label><input clt="checkBoxGbtjValueGroup" id="checkBoxGbtjValue" name="checkBoxGbtjValue"  type="checkbox" onclick="setCheckedValues(this,'gbdwtj')"  value="${vo.id}" />${vo.tjmc}</label></p>
						</c:forEach>
					</div>
				</li>
			</ul>
		</div>
		<div style="width: 50%;float: left">
			<b style="margin-left:40px;font-size:15px;">原有数据包（${oldPacketName}）</b>
			<ul class="ulChoicelist">
				<c:if test="${oldShpcVos!= null && fn:length(oldShpcVos) > 0}">
					<li class="heightauto">
						<h4 class=""><input class="checkbox"  id="oldCheckAllHyyj" type="checkbox" onclick="setCheckedValues(this,'oldhyyj')"  value="hyyj" /><a href="###">会议研究</a></h4>
						<div class="Choicetwo">
							<c:forEach items="${oldShpcVos}" var="vo">
								<p><label><input clt="oldCheckBoxHyyjValueGroup" id="oldCheckBoxHyyjValue" disabled  name="oldCheckBoxHyyjValue"  type="checkbox" onclick="setCheckedValues(this,'oldhyyj')"  value="${vo.id}" />${vo.pcmc}</label></p>
							</c:forEach>
						</div>
					</li>
				</c:if>
				<c:if test="${isLoadGbcxData eq 'true'}">
					<li>
						<h4><input class="checkbox" type="checkbox" onclick="setCheckedValues(this,'oldGBCX')"    id="oldcheckBoxGBCX" name="oldCheckBoxValue" value="GBCX" /> 干部和职数查询</h4>
					</li>
				</c:if>
				<%--<c:if test="${isLoadZsxcData eq 'true'}">--%>
					<%--<li>--%>
						<%--<h4><input class="checkbox" type="checkbox" onclick="setCheckedValues(this,'oldZSCX')"   id="oldcheckBoxZSCX" name="oldCheckBoxValue" value="ZSCX" /> 职数查询</h4>--%>
					<%--</li>--%>
				<%--</c:if>--%>
				<c:if test="${oldGbmcVos!= null && fn:length(oldGbmcVos)> 0}">
					<li class="heightauto">
						<h4  class=""><input class="checkbox" type="checkbox" onclick="setCheckedValues(this,'oldgbmc')"  id="oldCheckAllGbmc" value="gbmc" /> <a href="###">干部名册</a></h4>
						<div class="Choicetwo">
							<c:forEach items="${oldGbmcVos}" var="vo">
								<p><label><input clt="oldCheckBoxGbmcValueGroup" id="oldCheckBoxGbmcValue" disabled name="oldCheckBoxGbmcValue"  type="checkbox" onclick="setCheckedValues(this,'oldgbmc')"  value="${vo.id}" />${vo.mc}</label></p>
							</c:forEach>
						</div>
					</li>
				</c:if>
				<c:if test="${oldGbtjVos!= null && fn:length(oldGbtjVos) > 0}">
					<li class="heightauto">
						<h4  class=""><input class="checkbox" type="checkbox" onclick="setCheckedValues(this,'oldgbdwtj')" id="oldCheckAllGbtj" value="gbdwtj" />  <a href="###">干部队伍统计</a></h4>
						<div class="Choicetwo">
							<c:forEach items="${oldGbtjVos}" var="vo">
								<p><label><input clt="oldCheckBoxGbtjValueGroup" id="oldCheckBoxGbtjValue" disabled  name="oldCheckBoxGbtjValue"  type="checkbox" onclick="setCheckedValues(this,'oldgbdwtj')"  value="${vo.id}" />${vo.tjmc}</label></p>
							</c:forEach>
						</div>
					</li>
				</c:if>
			</ul>
		</div>
	</div>
</form>


<script src="${path }/js/common/loading.js" type="text/javascript" ></script>
<script type="text/javascript" src="${path }/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${path }/js/zTree/jquery.ztree.all-3.5.js"></script>
<script type="text/javascript" src="${path}/js/jquery.json-2.3.min.js"></script>
<script type="text/javascript" src="${path}/js/monitor/common.js"></script>
<script type="text/javascript" src="${path }/js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="${path }/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="${path }/js/bootstrap-datetimepicker.zh-CN.js"></script>

	<script type="text/javascript" src="${path }/js/jquery.validate.min.js"></script>
	<script type="text/javascript" src="${path }/js/common/est-validate-init.js"></script>
	<script type="text/javascript" src="${path }/js/common/validate-message.js"></script>
	<script type="text/javascript" src="${path }/js/common/30CloudAjax.js"></script>
	<script type="text/javascript" src="${path }/js/common/DataValidate.js"></script>
<script type="text/javascript">
	(function(){
		App.init();
//===========================================同一分类数据，两边不能同时选择
		$("#checkBoxGBCX").click(function() {
			if ($(this).attr("checked")) {
				$("#oldcheckBoxGBCX").parent().removeClass("checked");
				$("#oldcheckBoxGBCX").attr("checked",false);
			}
		});
//		$("#checkBoxZSCX").click(function() {
//			if ($(this).attr("checked")) {
//				$("#oldcheckBoxZSCX").parent().removeClass("checked");
//				$("#oldcheckBoxZSCX").attr("checked",false);
//			}
//		});
		$("#oldcheckBoxGBCX").click(function() {
			if ($(this).attr("checked")) {
				$("#checkBoxGBCX").parent().removeClass("checked");
				$("#checkBoxGBCX").attr("checked",false);
			}
		});
//		$("#oldcheckBoxZSCX").click(function() {
//			if ($(this).attr("checked")) {
//				$("#checkBoxZSCX").parent().removeClass("checked");
//				$("#checkBoxZSCX").attr("checked",false);
//			}
//		});

		$("#checkBoxHyyjValue").click(function() {
			if ($(this).attr("checked")) {
				$("input[name=oldCheckBoxHyyjValue]").each(function() {
					$(this).parent().removeClass("checked")
					$(this).attr("checked",false);
				});
			}
			$("#oldCheckAllHyyj").parent().removeClass("checked");
			$("#oldCheckAllHyyj").attr("checked",false);
		});

		$("#checkBoxGbmcValue").click(function() {
			if ($(this).attr("checked")) {
				$("input[name=oldCheckBoxGbmcValue]").each(function() {
					$(this).parent().removeClass("checked")
					$(this).attr("checked",false);
				});
			}
			$("#oldCheckAllGbmc").parent().removeClass("checked");
			$("#oldCheckAllGbmc").attr("checked",false);
		});

		$("#checkBoxGbtjValue").click(function() {
			if ($(this).attr("checked")) {
				$("input[name=oldCheckBoxGbtjValue]").each(function() {
					$(this).parent().removeClass("checked")
					$(this).attr("checked",false);
				});
				$("#oldCheckAllGbtj").parent().removeClass("checked");
				$("#oldCheckAllGbtj").attr("checked",false);
			}
		});

		$("#oldCheckBoxHyyjValue").click(function() {
			if ($(this).attr("checked")) {
				$("input[name=checkBoxHyyjValue]").each(function() {
					$(this).parent().removeClass("checked")
					$(this).attr("checked",false);
				});
				$("#CheckAllHyyj").parent().removeClass("checked");
				$("#CheckAllHyyj").attr("checked",false);
			}
		});

		$("#oldCheckBoxGbmcValue").click(function() {
			if ($(this).attr("checked")) {
				$("input[name=checkBoxGbmcValue]").each(function() {
					$(this).parent().removeClass("checked")
					$(this).attr("checked",false);
				});
				$("#CheckAllGbmc").parent().removeClass("checked");
				$("#CheckAllGbmc").attr("checked",false);
			}
		});

		$("#oldCheckBoxGbtjValue").click(function() {
			if ($(this).attr("checked")) {
				$("input[name=checkBoxGbtjValue]").each(function() {
					$(this).parent().removeClass("checked")
					$(this).attr("checked",false);
				});
				$("#CheckAllGbtj").parent().removeClass("checked");
				$("#CheckAllGbtj").attr("checked",false);
			}
		});


		$("#CheckAllHyyj").click(function() {
			if ($(this).attr("checked")) {
				$("input[name=checkBoxHyyjValue]").each(function() {
					$(this).parent().addClass("checked");
					$(this).attr("checked",true);
				});
				$("#oldCheckAllHyyj").parent().removeClass("checked");
				$("#oldCheckAllHyyj").attr("checked",false);
				$("input[name=oldCheckBoxHyyjValue]").each(function() {
					$(this).parent().removeClass("checked")
					$(this).attr("checked",false);
				});
			} else {
				$("input[name=checkBoxHyyjValue]").each(function() {
					$(this).parent().removeClass("checked")
					$(this).attr("checked",false);
				});
			}
		});

		$("#CheckAllGbmc").click(function() {
			if ($(this).attr("checked")) {
				$("input[name=checkBoxGbmcValue]").each(function() {
					$(this).parent().addClass("checked");
					$(this).attr("checked", true);
				});
				$("#oldCheckAllGbmc").parent().removeClass("checked");
				$("#oldCheckAllGbmc").attr("checked",false);
				$("input[name=oldCheckBoxGbmcValue]").each(function() {
					$(this).parent().removeClass("checked")
					$(this).attr("checked",false);
				});
			} else {
				$("input[name=checkBoxGbmcValue]").each(function() {
					$(this).parent().removeClass("checked")
					$(this).attr("checked", false);
				});
			}
		});

		$("#CheckAllGbtj").click(function() {
			if ($(this).attr("checked")) {
				$("input[name=checkBoxGbtjValue]").each(function() {
					$(this).parent().addClass("checked");
					$(this).attr("checked", true);
				});
				$("#oldCheckBoxGbtjValue").parent().removeClass("checked");
				$("#oldCheckBoxGbtjValue").attr("checked",false);
				$("input[name=oldCheckBoxGbtjValue]").each(function() {
					$(this).parent().removeClass("checked")
					$(this).attr("checked",false);
				});
			} else {
				$("input[name=checkBoxGbtjValue]").each(function() {
					$(this).parent().removeClass("checked")
					$(this).attr("checked", false);
				});
			}
		});



		<!-- old-->
		$("#oldCheckAllHyyj").click(function() {
			if ($(this).attr("checked")) {
				$("input[name=oldCheckBoxHyyjValue]").each(function() {
					$(this).parent().addClass("checked");
					$(this).attr("checked",true);
				});
				$("#CheckAllHyyj").parent().removeClass("checked");
				$("#CheckAllHyyj").attr("checked",false);
				$("input[name=checkBoxHyyjValue]").each(function() {
					$(this).parent().removeClass("checked")
					$(this).attr("checked",false);
				});
			} else {
				$("input[name=oldCheckBoxHyyjValue]").each(function() {
					$(this).parent().removeClass("checked")
					$(this).attr("checked",false);
				});
			}
		});

		$("#oldCheckAllGbmc").click(function() {
			if ($(this).attr("checked")) {
				$("input[name=oldCheckBoxGbmcValue]").each(function() {
					$(this).parent().addClass("checked");
					$(this).attr("checked", true);
				});
				$("#CheckAllGbmc").parent().removeClass("checked");
				$("#CheckAllGbmc").attr("checked",false);
				$("input[name=checkBoxGbmcValue]").each(function() {
					$(this).parent().removeClass("checked")
					$(this).attr("checked",false);
				});
			} else {
				$("input[name=oldCheckBoxGbmcValue]").each(function() {
					$(this).parent().removeClass("checked")
					$(this).attr("checked", false);
				});
			}
		});

		$("#oldCheckAllGbtj").click(function() {
			if ($(this).attr("checked")) {
				$("input[name=oldCheckBoxGbtjValue]").each(function() {
					$(this).parent().addClass("checked");
					$(this).attr("checked", true);
				});
				$("#CheckAllGbtj").parent().removeClass("checked");
				$("#CheckAllGbtj").attr("checked",false);
				$("input[name=checkBoxGbtjValue]").each(function() {
					$(this).parent().removeClass("checked")
					$(this).attr("checked",false);
				});
			} else {
				$("input[name=oldCheckBoxGbtjValue]").each(function() {
					$(this).parent().removeClass("checked")
					$(this).attr("checked", false);
				});
			}
		});
	})();


	//====================================================================================


	$(function(){
		$('.ulChoicelist li h4 a').click(function(e) {
			$(this).parents('li').toggleClass('heightauto');
		});
	});

	function setCheckedValues(obj,DateType){


	}

	function formSubmit(){
		var oldcheckHyyjValues="";//会议研究列表记录值
		var oldcheckGbmcValues="";//干部名册列表记录值
		var oldcheckGbtjValues="";//干部统计列表记录值

		var checkHyyjValues="";//会议研究列表记录值
		var checkGbmcValues="";//干部名册列表记录值
		var checkGbtjValues="";//干部统计列表记录值
		var packetName = $("#packetName").val();
		if(packetName.trim()==""){
			flag = false;
			showTip('提示','数据包名称不能为空', 1500);
			return;
		}
		var flag= true;

		$("input[name=oldCheckBoxHyyjValue]").each(function() {
			if ($(this).attr("checked")) {
				if(oldcheckHyyjValues == ""){
					oldcheckHyyjValues = $(this).val();
				}else{
					oldcheckHyyjValues = oldcheckHyyjValues+","+$(this).val();
				}
			}
		});

		$("input[name=oldCheckBoxGbmcValue]").each(function() {
			if ($(this).attr("checked")) {
				if(oldcheckGbmcValues == ""){
					oldcheckGbmcValues = $(this).val();
				}else{
					oldcheckGbmcValues = oldcheckGbmcValues+","+$(this).val();
				}
			}
		});

		$("input[name=oldCheckBoxGbtjValue]").each(function() {
			if ($(this).attr("checked")) {
				if(oldcheckGbtjValues == ""){
					oldcheckGbtjValues = $(this).val();
				}else{
					oldcheckGbtjValues = oldcheckGbtjValues+","+$(this).val();
				}
			}
		});

		var oldcheckBoxTypeValues = "";//选择类型的值
		$("input[name=oldCheckBoxValue]").each(function() {
			if ($(this).attr("checked")) {
					if (oldcheckBoxTypeValues == "") {
						oldcheckBoxTypeValues = $(this).val();
					} else {
						oldcheckBoxTypeValues = oldcheckBoxTypeValues + "," + $(this).val();
					}
			}
		});

//======================================================
		$("input[name=checkBoxHyyjValue]").each(function() {
			if ($(this).attr("checked")) {
				if(checkHyyjValues == ""){
					checkHyyjValues = $(this).val();
				}else{
					checkHyyjValues = checkHyyjValues+","+$(this).val();
				}
			}
		});

		$("input[name=checkBoxGbmcValue]").each(function() {
			if ($(this).attr("checked")) {
				if(checkGbmcValues == ""){
					checkGbmcValues = $(this).val();
				}else{
					checkGbmcValues = checkGbmcValues+","+$(this).val();
				}
			}
		});

		$("input[name=checkBoxGbtjValue]").each(function() {
			if ($(this).attr("checked")) {
				if(checkGbtjValues == ""){
					checkGbtjValues = $(this).val();
				}else{
					checkGbtjValues = checkGbtjValues+","+$(this).val();
				}
			}
		});
		var checkBoxTypeValues = "";//选择类型的值
		$("input[name=checkBoxValue]").each(function() {
			if ($(this).attr("checked")) {
				if (checkBoxTypeValues == "") {
					checkBoxTypeValues = $(this).val();
				} else {
					checkBoxTypeValues = checkBoxTypeValues + "," + $(this).val();
				}
			}
		});
		document.getElementById("oldcheckHyyjValues").value = oldcheckHyyjValues;
		document.getElementById("oldcheckGbmcValues").value = oldcheckGbmcValues;
		document.getElementById("oldcheckGbtjValues").value = oldcheckGbtjValues;
		document.getElementById("oldcheckBoxTypeValues").value = oldcheckBoxTypeValues;

		document.getElementById("checkHyyjValues").value = checkHyyjValues;
		document.getElementById("checkGbmcValues").value = checkGbmcValues;
		document.getElementById("checkGbtjValues").value = checkGbtjValues;
		document.getElementById("checkBoxTypeValues").value = checkBoxTypeValues;
		if(''==oldcheckHyyjValues&&''==oldcheckGbmcValues&&''==oldcheckGbtjValues&&''==oldcheckBoxTypeValues&&''==checkHyyjValues&&''==checkGbmcValues&&''==checkGbtjValues&&''==checkBoxTypeValues)
		{
			flag = false;
			showTip('提示','请先选择需要生成数据的项', 1500);
		}
		var myVld = new EstValidate("form1");
		if(myVld && flag){
//			var winopenRef = window.open("","_blank");
			$.cloudAjax({
				path : '${path}',
				url : "${path }/zzb/app/console/gendata/generatorByoldPacket",
				type : "post",
				data : $("#form1").serialize(),
				dataType : "json",
				success : function(data){
					if(data.success){
						//winopenRef.location="${path }/zzb/app/console/gendata/zip/down?id="+data.gendataId;
						showTip("提示","数据包生成成功",2000);
						setTimeout(function(){window.location.href = "${path}/zzb/app/console/gendata/"},1000);
					}else{
						showTip("提示", "数据包生成失败", 2000);
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
