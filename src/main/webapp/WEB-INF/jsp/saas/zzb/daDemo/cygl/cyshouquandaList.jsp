<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/inc/taglib.jsp" %>
<%--
  ~ Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
  ~ http://www.hn-hisun.com
  ~ 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
  --%>

<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	<link href="${path}/css/common/common.css" rel="stylesheet" type="text/css"/>
	<!-- BEGIN PAGE LEVEL STYLES -->
	<link rel="stylesheet" href="${path }/css/DT_bootstrap.css" />
	<link rel="stylesheet" type="text/css" href="${path }/css/bootstrap-fileupload.css">

	<link href="${path }/css/style.css" rel="stylesheet" type="text/css">
	<!-- END PAGE LEVEL STYLES -->
	<title>可查阅档案</title>
	<style type="text/css">
		form {
			margin: 0 0 0px;
		}
	</style>
</head>
<body>
<div id="jgModal" class="modal container hide fade" tabindex="-1" data-width="1010" data-height="600">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button data-dismiss="modal" class="close"  type="button"></button>
				<h3 class="modal-title" id="title" >
					“红叶专”档案图片
				</h3>
			</div>
			<div class="modal-body" id="jgAddDiv" style="background-color: #f1f3f6;">
			</div>
		</div>
	</div>
</div>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12 responsive">
			<%-- 表格开始 --%>
			<form class=""id="importForm" enctype="multipart/form-data">
				<input type="hidden" name="b01Id" value="${b01Id}"/>
				<div class="portlet-title">
					<div class="caption">可查阅档案：共<font color="red"> 10 </font>人</div>
					<div class="clearfix fr">


					</div>

				</div>
			</form>
				<div class="clearfix">
					<div class="control-group">
						<div id="query" style="float: left;">
							<form action="${path }/zzb/app/console/asetA01/ajax/list" method="POST" id="searchForm" name="searchForm">
								<input type="hidden" id="b01Id" name="b01Id" value="${b01Id}"/>
								<input type="hidden" name="OWASP_CSRFTOKEN" value="${sessionScope.OWASP_CSRFTOKEN}"/>
								<input type="hidden" name="pageNum" value="${pager.pageNum }" id="pageNum">
								<input type="hidden" name="pageSize" value="${pager.pageSize }" id="pageSize">
								姓名：<input type="text" class="m-wrap" name="xmQuery" id="xmQuery" value="${xmQuery}" style="width:80px;" />
								干部状态：
								<select class="select_form" tabindex="-1" name="noFileQuert" id="noFileQuert" style="width: 100px; margin-bottom: 0px;" >
									<option ></option>
									<option >现职</option>
									<option >离退</option>
									<option >辞职</option>
								</select>

								<button type="button" class="btn Short_but" onclick="searchSubmit()">查询</button>
								<button type="button" class="btn Short_but" onclick="clearData()">清空</button>
							</form>
						</div>
					</div>

				</div>
				<div class="portlet-body">
					<table class="table table-striped table-bordered table-hover dataTable table-set">
						<thead>

						<TR height=28>
							<th width=70>姓名</th>
							<th width=40>性别</th>
							<th width=70>出生年月</th>
							<th>单位职务</th>
							<th width=70>接收日期</th>
							<th width=70>干部状态</th>
							<th width=70>现职级时间</th>

						</thead>
						<tbody>
						<TR onmouseover="this.className='table_tr_mouse'" onmouseout="this.className=''">
							<TD style="CURSOR: hand; TEXT-ALIGN: center" ><a href="javascript:view()">红叶专</a> </TD>
							<TD style="TEXT-ALIGN: center" width=40>男 </TD>
							<TD >1962.07.02 </TD>
							<TD title=州委书记>州委书记 </TD>
							<TD >2015.03.05 </TD>
							<TD style="TEXT-ALIGN: center">现职 </TD>
							<TD style="TEXT-ALIGN: center">副局<BR>(2008.03) </TD>
							</TR>
						<TR onmouseover="this.className='table_tr_mouse'" onmouseout="this.className='table_tr_dark'" class=table_tr_dark>

							<TD style="CURSOR: hand; TEXT-ALIGN: center"><a href="#">红叶专001</a> </TD>
							<TD style="TEXT-ALIGN: center" width=40>男 </TD>
							<TD>1962.07.02 </TD>
							<TD title=州委书记>州委书记 </TD>
							<TD>2015.03.06 </TD>
							<TD style="TEXT-ALIGN: center">现职 </TD>
							<TD style="TEXT-ALIGN: center"></TD>
						</TR>
						<TR onmouseover="this.className='table_tr_mouse'" onmouseout="this.className=''">

							<TD style="CURSOR: hand; TEXT-ALIGN: center"><a href="${path}/zzb/app/console/daDemo/manage">宣庆华</a> </TD>
							<TD style="TEXT-ALIGN: center" width=40>男 </TD>
							<TD>1954.01.25 </TD>
							<TD title=中共广州市纪律检查委员会委员，广州市城乡建设委员会纪工委书记、工委委员>中共广州市纪律检查委员会委员，广州市城乡建设委员会纪工委书记、工委委员 </TD>
							<TD>2015.03.18 </TD>
							<TD style="TEXT-ALIGN: center">现职 </TD>
							<TD style="TEXT-ALIGN: center"></TD>
							</TR>
						<TR onmouseover="this.className='table_tr_mouse'" onmouseout="this.className='table_tr_dark'" class=table_tr_dark>

							<TD style="CURSOR: hand; TEXT-ALIGN: center"><a href="${path}/zzb/app/console/daDemo/manage">药占彤</a> </TD>
							<TD style="TEXT-ALIGN: center" width=40>男 </TD>
							<TD>1964.12 </TD>
							<TD title=中共广州市委员会常委，中共广州市委组织部部长，中共广州市委党校(广州行政学院校（院）长、校委会主任>中共广州市委员会常委，中共广州市委组织部部长，中共广州市委党校(广州行政学院校（院）长、校委会主任 </TD>
							<TD>2017.07.14 </TD>
							<TD style="TEXT-ALIGN: center">现职 </TD>
							<TD style="TEXT-ALIGN: center"></TD>
							</TR>
						<TR onmouseover="this.className='table_tr_mouse'" onmouseout="this.className=''">

							<TD style="CURSOR: hand; TEXT-ALIGN: center"><a href="${path}/zzb/app/console/daDemo/manage">张测试001</a> </TD>
							<TD style="TEXT-ALIGN: center" width=40></TD>
							<TD></TD>
							<TD title=""></TD>
							<TD>2015.03.18 </TD>
							<TD style="TEXT-ALIGN: center">现职 </TD>
							<TD style="TEXT-ALIGN: center"></TD>
							</TR>
						<TR onmouseover="this.className='table_tr_mouse'" onmouseout="this.className='table_tr_dark'" class=table_tr_dark>

							<TD style="CURSOR: hand; TEXT-ALIGN: center"><a href="${path}/zzb/app/console/daDemo/manage">刘戒</a> </TD>
							<TD style="TEXT-ALIGN: center" width=40>女 </TD>
							<TD>1982.02 </TD>
							<TD title=被培训机构1秘书一处处长>被培训机构1秘书一处处长 </TD>
							<TD>2015.03.12 </TD>
							<TD style="TEXT-ALIGN: center">现职 </TD>
							<TD style="TEXT-ALIGN: center"></TD>
							</TR>
						<TR onmouseover="this.className='table_tr_mouse'" onmouseout="this.className=''">

							<TD style="CURSOR: hand; TEXT-ALIGN: center"><a href="${path}/zzb/app/console/daDemo/manage">李成</a> </TD>
							<TD style="TEXT-ALIGN: center" width=40></TD>
							<TD></TD>
							<TD title=""></TD>
							<TD>2015.03.12 </TD>
							<TD style="TEXT-ALIGN: center"></TD>
							<TD style="TEXT-ALIGN: center"></TD>
						</TR>
						<TR onmouseover="this.className='table_tr_mouse'" onmouseout="this.className='table_tr_dark'" class=table_tr_dark>

							<TD style="CURSOR: hand; TEXT-ALIGN: center"><a href="${path}/zzb/app/console/daDemo/manage">胡星</a> </TD>
							<TD style="TEXT-ALIGN: center" width=40>女 </TD>
							<TD>1982.02 </TD>
							<TD title=被培训机构1秘书一处处长>被培训机构1秘书一处处长 </TD>
							<TD>2015.03.12 </TD>
							<TD style="TEXT-ALIGN: center">现职 </TD>
							<TD style="TEXT-ALIGN: center"></TD>
							</TR>
						<TR onmouseover="this.className='table_tr_mouse'" onmouseout="this.className=''">

							<TD style="CURSOR: hand; TEXT-ALIGN: center"><a href="${path}/zzb/app/console/daDemo/manage">张烈</a> </TD>
							<TD style="TEXT-ALIGN: center" width=40></TD>
							<TD></TD>
							<TD title=被培训机构1秘书一处副处长>被培训机构1秘书一处副处长 </TD>
							<TD>2015.03.12 </TD>
							<TD style="TEXT-ALIGN: center">现职 </TD>
							<TD style="TEXT-ALIGN: center"></TD>
							</TR>
						<TR onmouseover="this.className='table_tr_mouse'" onmouseout="this.className='table_tr_dark'" class=table_tr_dark>

							<TD style="CURSOR: hand; TEXT-ALIGN: center"><a href="${path}/zzb/app/console/daDemo/manage">张家华</a> </TD>
							<TD style="TEXT-ALIGN: center" width=40></TD>
							<TD></TD>
							<TD title=""></TD>
							<TD>2015.03.12 </TD>
							<TD style="TEXT-ALIGN: center"></TD>
							<TD style="TEXT-ALIGN: center"></TD>
							</TR>
						</tbody>
					</table>
					<jsp:include page="/WEB-INF/jsp/common/page.jsp">
						<jsp:param value="${pager.total }" name="total"/>
						<jsp:param value="${pager.pageCount }" name="endPage"/>
						<jsp:param value="${pager.pageSize }" name="pageSize"/>
						<jsp:param value="${pager.pageNum }" name="page"/>
					</jsp:include>
				</div>
		</div>
		<%-- 表格结束 --%>
	</div>
</div>

<%-- END PAGE CONTENT--%>
</div>

<script type="text/javascript">
	(function(){
		App.init();

		$("#btn-browseTemplate").bind("change",function(evt){
			if($(this).val()){
				ajaxSubmit();
			}
			$(this).val('');
		});

	})();
	var view = function(){
		var divHeight = $(window).height()-100;
		$('#jgModal').attr("data-height",divHeight);
		$.ajax({
			url:"${path}/zzb/app/console/daDemo/ajax/viewImgManage",
			type : "post",
			data: {},
			headers:{
				OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
			},
			dataType : "html",
			success : function(html){
				$('#jgAddDiv').html(html);

				$('#jgModal').modal({
					keyboard: true
				});
			},
			error : function(){
				showTip("提示","出错了请联系管理员", 1500);
			}
		});
	}
	function pagehref (pageNum ,pageSize){
		<%--window.location.href ="${path}/zzb/app/console/gbmc/a01/list?b01Id=${b01Id}&mcid=${mcid}&pageNum="+pageNum+"&pageSize="+pageSize;--%>
		$.ajax({
			async:false,
			type:"POST",
			url:"${path}/zzb/app/console/asetA01/ajax/list",
			dataType : "html",
			headers:{
				"OWASP_CSRFTOKEN":'${sessionScope.OWASP_CSRFTOKEN}'
			},
			data:{
				'b01Id':"${b01Id}",
				'pageNum':pageNum,
				'pageSize':pageSize
			},
			success:function(html){
				$("#catalogList").html(html);
//				$("#treeId").val(nodeId);
			},
			error : function(){
				myLoading.hide();
				showTip("提示","出错了,请检查网络!",2000);
			}
		});

	}

	function searchSubmit(){
		$.ajax({
			async:false,
			type:"POST",
			url:"${path}/zzb/app/console/asetA01/ajax/list",
			dataType : "html",
			headers:{
				"OWASP_CSRFTOKEN":'${sessionScope.OWASP_CSRFTOKEN}'
			},
			data : $("#searchForm").serialize(),
			success:function(html){
				$("#catalogList").html(html);
//				$("#treeId").val(nodeId);
			},
			error : function(){
				myLoading.hide();
				showTip("提示","出错了,请检查网络!",2000);
			}
		});
	}



	var del = function(id,itemName){
		actionByConfirm1(itemName, "${path}/zzb/app/console/asetA01/delete/" + id,{} ,function(data,status){
			if (data.success == true) {
				showTip("提示","删除成功", 2000);
				setTimeout(function(){window.location.href = "${path}/zzb/app/console/asetA01/list?b01Id=${b01Id}&mcid=${mcid}"},2000);
			}else{
				showTip("提示", data.message, 2000);
			}
		});
	};
	function uploadFile(fileName){
		document.getElementById("btn-"+fileName).click();
	}
	function clearData(){
		$("#xmQuery").val('');
		$.ajax({
			async:false,
			type:"POST",
			url:"${path}/zzb/app/console/asetA01/ajax/list",
			dataType : "html",
			headers:{
				"OWASP_CSRFTOKEN":'${sessionScope.OWASP_CSRFTOKEN}'
			},
			data : $("#searchForm").serialize(),
			success:function(html){
				$("#catalogList").html(html);
//				$("#treeId").val(nodeId);
			},
			error : function(){
				myLoading.hide();
				showTip("提示","出错了,请检查网络!",2000);
			}
		});
	}
	function exportGbrmsp(){
		$.cloudAjax({
			path : '${path}',
			url : "${path }/zzb/app/console/asetA01/ajax/exportGbrmsp",
			type : "post",
			data : $("#form1").serialize(),
			dataType : "json",
			success : function(data){
				if(data.success == true){
					showTip("提示","生成干部任免审批表成功!",2000);
					//setTimeout(function(){window.location.href = "${path}/zzb/app/console/bwh/"},2000);
				}else{
					showTip("提示", "生成干部任免审批表失败!", 2000);
				}
			},
			error : function(){
				showTip("提示","出错了请联系管理员",2000);
			}
		});
	}
	function openGzzzb(){
		var url ="http://localhost:8080/GZZZB/la/index.jsp?showFlag=init&moduleCode=LA_APPOINT_STUFF";
		window.open(url);
	}
</script>
</body>
</html>
