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
	<input type="hidden" name="checkBoxTypeValues" value="" id="checkBoxTypeValues">
	<input type="hidden" name="checkHyyjValues" value="" id="checkHyyjValues">
	<input type="hidden" name="checkGbmcValues" value="" id="checkGbmcValues">
	<input type="hidden" name="checkGbtjValues" value="" id="checkGbtjValues">

	<div class="row-fluid mb10">
		<div id="packetNameGroup" class="control-group">
			<label class="control-label" style="width:100px">数据包名称<span class="required">*</span></label>
			<div class="controls" style="margin-left:100px">
				<input type="text" class="span6 m-wrap" name="packetName" required maxlength="200" id="packetName" value="${packetName}" />
			</div>

		</div>
		<ul class="ulChoicelist">
			<li class="heightauto">
				<h4 class=""><input class="checkbox"  name="checkBoxValue" id="CheckAllHyyj" type="checkbox" value="hyyj" /><a href="###">会议研究</a></h4>
				<div class="Choicetwo">
					<c:forEach items="${shpcVos}" var="vo">
							<p><label><input clt="checkBoxHyyjValueGroup"  name="checkBoxHyyjValue"  type="checkbox" value="${vo.id}" />${vo.pcmc}</label></p>
					</c:forEach>
				</div>
			</li>
			<li>
				<h4><input class="checkbox" type="checkbox" name="checkBoxValue" value="GBCX" /> 干部和职数查询</h4>
			</li>
			<%--<li>--%>
				<%--<h4><input class="checkbox" type="checkbox" name="checkBoxValue" value="ZSCX" /> 职数查询</h4>--%>
			<%--</li>--%>
			<li class="heightauto">
				<h4  class=""><input class="checkbox" type="checkbox" name="checkBoxValue" id="CheckAllGbmc" value="gbmc" /> <a href="###">干部名册</a></h4>
				<div class="Choicetwo">
					<c:forEach items="${gbmcVos}" var="vo">
						<p><label><input clt="checkBoxGbmcValueGroup"  name="checkBoxGbmcValue"  type="checkbox" value="${vo.id}" />${vo.mc}</label></p>
					</c:forEach>
				</div>
			</li>
			<li class="heightauto">
				<h4  class=""><input class="checkbox" type="checkbox" name="checkBoxValue" id="CheckAllGbtj" value="gbdwtj" />  <a href="###">干部队伍统计</a></h4>
				<div class="Choicetwo">
					<c:forEach items="${gbtjVos}" var="vo">
						<p><label><input clt="checkBoxGbtjValueGroup"  name="checkBoxGbtjValue"  type="checkbox" value="${vo.id}" />${vo.tjmc}</label></p>
					</c:forEach>
				</div>
			</li>
		</ul>
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

		$("#CheckAllHyyj").click(function() {
			if ($(this).attr("checked")) {
				$("input[name=checkBoxHyyjValue]").each(function() {
					$(this).parent().addClass("checked");
					$(this).attr("checked",true);
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
			} else {
				$("input[name=checkBoxGbtjValue]").each(function() {
					$(this).parent().removeClass("checked")
					$(this).attr("checked", false);
				});
			}
		});
	})();

	$(function(){
		$('.ulChoicelist li h4 a').click(function(e) {
			$(this).parents('li').toggleClass('heightauto');
		});
	});


	function formSubmit(){
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
				if ($(this).val() == "GBCX" ) {//分类目前只有干部查询可加入选择的内容中
					if (checkBoxTypeValues == "") {
						checkBoxTypeValues = $(this).val();
					} else {
						checkBoxTypeValues = checkBoxTypeValues + "," + $(this).val();
					}
				}
			}
		});
		document.getElementById("checkHyyjValues").value = checkHyyjValues;
		document.getElementById("checkGbmcValues").value = checkGbmcValues;
		document.getElementById("checkGbtjValues").value = checkGbtjValues;
		document.getElementById("checkBoxTypeValues").value = checkBoxTypeValues;
		if(''==checkHyyjValues&&''==checkGbmcValues&&''==checkGbtjValues&&''==checkBoxTypeValues)
		{
			flag = false;
			showTip('提示','请先选择需要生成数据的项', 1500);
		}
		var myVld = new EstValidate("form1");
		if(myVld && flag){
//			var winopenRef = window.open("","_blank");
			$.cloudAjax({
				path : '${path}',
				url : "${path }/zzb/app/console/gendata/generator",
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
