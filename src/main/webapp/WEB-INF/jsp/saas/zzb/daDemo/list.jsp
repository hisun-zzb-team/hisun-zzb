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
	<title>电子档案系统</title>
	<style type="text/css">
		form {
			margin: 0 0 0px;
		}
	</style>
</head>
<body>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12 responsive">
			<%-- 表格开始 --%>
			<form class=""id="importForm" enctype="multipart/form-data">
				<input type="hidden" name="b01Id" value="${b01Id}"/>
				<div class="portlet-title">
					<div class="caption">档案管理：共<font color="red"> 10 </font>人</div>
					<div class="clearfix fr">


						<div class="btn-group" style="padding-bottom: 0px">
							<a class="btn green dropdown-toggle" data-toggle="dropdown" href="#">
								新建 <i class="icon-angle-down"></i>
							</a>
							<ul class="dropdown-menu">
								<li >
									<a href="${path}/zzb/app/console/daDemo/addDaBase">新建</a>
								</li>
								<li >
									<a onclick="unloadFile()">导入Lrmx</a>
								</li>
								<li >
									<a onclick="unloadFile()">导入Lrm</a>
								</li>
								<li >
									<a onclick="unloadFile()">导入HZB</a>
								</li>
								<li >
									<a onclick="unloadFile()">导入7z</a>
								</li>
							</ul>
						</div>
						<%--<a id="sample_editable_1_new" class="btn green" href="#">--%>
							<%--高级查询--%>
						<%--</a>--%>
						<a class="btn green" href="javascript:unloadFile()">导入档案</a>
						<input type="file" style="display: none" name="unloadFile" id="btn-unloadFile"/>

						<a id="sample_editable_1_new" class="btn green" href="javascript:fileDown('list')">
							输出
						</a>

						<%--<span class="controllerClass btn green file_but" >--%>
							<%--<i class="icon-circle-arrow-up"></i>清空数据--%>
							<%--<input class="file_progress" type="file" name="attachFile" id="btn-browseTemplate">--%>
						<%--</span>--%>
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
								档案编号：<input type="text" class="m-wrap" name="xmQuery" id="xmQuery" value="${xmQuery}" style="width: 80px;" />
								扫描序号：<input type="text" class="m-wrap" name="xmQuery" id="xmQuery" value="${xmQuery}" style="width: 80px;" />
								姓名：<input type="text" class="m-wrap" name="xmQuery" id="xmQuery" value="${xmQuery}" style="width:80px;" />
								干部状态：
								<select class="select_form" tabindex="-1" name="noFileQuert" id="noFileQuert" style="width: 100px; margin-bottom: 0px;" >
									<option ></option>
									<option >现职</option>
									<option >离退</option>
									<option >辞职</option>
								</select>
								档案状态：
								<select class="select_form" tabindex="-1" name="noFileQuert" id="noFileQuert" style="width: 100px; margin-bottom: 0px;" >
									<option ></option>
									<option >无档需追补</option>
									<option >有档</option>
									<option >转出</option>
									<option >无档不需追补</option>
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
							<th width=60>档案编号</th>
							<th width=60>扫描序号</th>
							<th width=70>姓名</th>
							<th width=40>性别</th>
							<th width=70>出生年月</th>
							<th>单位职务</th>
							<th width=70>接收日期</th>
							<th width=70>干部状态</th>
							<th width=70>现职级时间</th>
							<th width="5%">修改者</th>
							<th width=80>修改时间</th>
							<th width=80>关联情况</th>
						</thead>
						<tbody>
						<TR onmouseover="this.className='table_tr_mouse'" onmouseout="this.className=''">
							<TD style="TEXT-ALIGN: center"></TD>
							<TD style="TEXT-ALIGN: center">0002 </TD>
							<TD style="CURSOR: hand; TEXT-ALIGN: center" ><a href="${path}/zzb/app/console/daDemo/manage">红叶专</a> </TD>
							<TD style="TEXT-ALIGN: center" width=40>男 </TD>
							<TD >1962.07.02 </TD>
							<TD title=州委书记>州委书记 </TD>
							<TD >2015.03.05 </TD>
							<TD style="TEXT-ALIGN: center">现职 </TD>
							<TD style="TEXT-ALIGN: center">副局<BR>(2008.03) </TD>
							<TD style="TEXT-ALIGN: center" width=40>杜政 </TD>
							<TD style="TEXT-ALIGN: center">2018.03.29 16:02 </TD>
							<TD style="TEXT-ALIGN: center"><a href="${path}/zzb/app/console/asetA01Query/view?id=51992&queryId=&queryPosition=da">红叶专 </a></TD><!--<td  style="text-align:center;" width="5%">
									<a href="javascript:openFile('58F2F87EC0A800CC2DE33CCA0E6F9647','红叶专');"><font color="blue" style="cursor:hand">导入</font></a>
								</td>--></TR>
						<TR onmouseover="this.className='table_tr_mouse'" onmouseout="this.className='table_tr_dark'" class=table_tr_dark>
							<TD style="TEXT-ALIGN: center"></TD>
							<TD style="TEXT-ALIGN: center"></TD>
							<TD style="CURSOR: hand; TEXT-ALIGN: center"><a href="#">红叶专001</a> </TD>
							<TD style="TEXT-ALIGN: center" width=40>男 </TD>
							<TD>1962.07.02 </TD>
							<TD title=州委书记>州委书记 </TD>
							<TD>2015.03.06 </TD>
							<TD style="TEXT-ALIGN: center">现职 </TD>
							<TD style="TEXT-ALIGN: center"></TD>
							<TD style="TEXT-ALIGN: center" width=40>杜政 </TD>
							<TD style="TEXT-ALIGN: center">2017.07.14 20:33 </TD>
							<TD style="TEXT-ALIGN: center"></TD><!--<td  style="text-align:center;" width="5%">
									<a href="javascript:openFile('ED354F89646464C3424EDFEBF1D25A85','红叶专001');"><font color="blue" style="cursor:hand">导入</font></a>
								</td>--></TR>
						<TR onmouseover="this.className='table_tr_mouse'" onmouseout="this.className=''">
							<TD style="TEXT-ALIGN: center"></TD>
							<TD style="TEXT-ALIGN: center"></TD>
							<TD style="CURSOR: hand; TEXT-ALIGN: center"><a href="${path}/zzb/app/console/daDemo/manage">宣庆华</a> </TD>
							<TD style="TEXT-ALIGN: center" width=40>男 </TD>
							<TD>1954.01.25 </TD>
							<TD title=中共广州市纪律检查委员会委员，广州市城乡建设委员会纪工委书记、工委委员>中共广州市纪律检查委员会委员，广州市城乡建设委员会纪工委书记、工委委员 </TD>
							<TD>2015.03.18 </TD>
							<TD style="TEXT-ALIGN: center">现职 </TD>
							<TD style="TEXT-ALIGN: center"></TD>
							<TD style="TEXT-ALIGN: center" width=40></TD>
							<TD style="TEXT-ALIGN: center"></TD>
							<TD style="TEXT-ALIGN: center"></TD><!--<td  style="text-align:center;" width="5%">
									<a href="javascript:openFile('2AAF70A4C0A800690058C83921BA2017','宣庆华');"><font color="blue" style="cursor:hand">导入</font></a>
								</td>--></TR>
						<TR onmouseover="this.className='table_tr_mouse'" onmouseout="this.className='table_tr_dark'" class=table_tr_dark>
							<TD style="TEXT-ALIGN: center"></TD>
							<TD style="TEXT-ALIGN: center"></TD>
							<TD style="CURSOR: hand; TEXT-ALIGN: center"><a href="${path}/zzb/app/console/daDemo/manage">药占彤</a> </TD>
							<TD style="TEXT-ALIGN: center" width=40>男 </TD>
							<TD>1964.12 </TD>
							<TD title=中共广州市委员会常委，中共广州市委组织部部长，中共广州市委党校(广州行政学院校（院）长、校委会主任>中共广州市委员会常委，中共广州市委组织部部长，中共广州市委党校(广州行政学院校（院）长、校委会主任 </TD>
							<TD>2017.07.14 </TD>
							<TD style="TEXT-ALIGN: center">现职 </TD>
							<TD style="TEXT-ALIGN: center"></TD>
							<TD style="TEXT-ALIGN: center" width=40>杜政 </TD>
							<TD style="TEXT-ALIGN: center">2017.07.14 12:41 </TD>
							<TD style="TEXT-ALIGN: center"><a href="#">药占彤 </a></TD><!--<td  style="text-align:center;" width="5%">
									<a href="javascript:openFile('3F66B9DC7F0000010339505731DCE6C8','药占彤');"><font color="blue" style="cursor:hand">导入</font></a>
								</td>--></TR>
						<TR onmouseover="this.className='table_tr_mouse'" onmouseout="this.className=''">
							<TD style="TEXT-ALIGN: center"></TD>
							<TD style="TEXT-ALIGN: center"></TD>
							<TD style="CURSOR: hand; TEXT-ALIGN: center"><a href="${path}/zzb/app/console/daDemo/manage">张测试001</a> </TD>
							<TD style="TEXT-ALIGN: center" width=40></TD>
							<TD></TD>
							<TD title=""></TD>
							<TD>2015.03.18 </TD>
							<TD style="TEXT-ALIGN: center">现职 </TD>
							<TD style="TEXT-ALIGN: center"></TD>
							<TD style="TEXT-ALIGN: center" width=40>杜政 </TD>
							<TD style="TEXT-ALIGN: center">2015.03.25 08:37 </TD>
							<TD style="TEXT-ALIGN: center"><a href="#">张测试001 </a></TD><!--<td  style="text-align:center;" width="5%">
									<a href="javascript:openFile('2AC56CCEC0A800690058C839EAFE75B4','张测试001');"><font color="blue" style="cursor:hand">导入</font></a>
								</td>--></TR>
						<TR onmouseover="this.className='table_tr_mouse'" onmouseout="this.className='table_tr_dark'" class=table_tr_dark>
							<TD style="TEXT-ALIGN: center"></TD>
							<TD style="TEXT-ALIGN: center"></TD>
							<TD style="CURSOR: hand; TEXT-ALIGN: center"><a href="${path}/zzb/app/console/daDemo/manage">刘戒</a> </TD>
							<TD style="TEXT-ALIGN: center" width=40>女 </TD>
							<TD>1982.02 </TD>
							<TD title=被培训机构1秘书一处处长>被培训机构1秘书一处处长 </TD>
							<TD>2015.03.12 </TD>
							<TD style="TEXT-ALIGN: center">现职 </TD>
							<TD style="TEXT-ALIGN: center"></TD>
							<TD style="TEXT-ALIGN: center" width=40>杜政 </TD>
							<TD style="TEXT-ALIGN: center">2015.03.19 10:49 </TD>
							<TD style="TEXT-ALIGN: center"></TD><!--<td  style="text-align:center;" width="5%">
									<a href="javascript:openFile('0B70CD95C0A8006901F64DA08F07DB43','猪三戒');"><font color="blue" style="cursor:hand">导入</font></a>
								</td>--></TR>
						<TR onmouseover="this.className='table_tr_mouse'" onmouseout="this.className=''">
							<TD style="TEXT-ALIGN: center"></TD>
							<TD style="TEXT-ALIGN: center"></TD>
							<TD style="CURSOR: hand; TEXT-ALIGN: center"><a href="${path}/zzb/app/console/daDemo/manage">李成</a> </TD>
							<TD style="TEXT-ALIGN: center" width=40></TD>
							<TD></TD>
							<TD title=""></TD>
							<TD>2015.03.12 </TD>
							<TD style="TEXT-ALIGN: center"></TD>
							<TD style="TEXT-ALIGN: center"></TD>
							<TD style="TEXT-ALIGN: center" width=40>杜政 </TD>
							<TD style="TEXT-ALIGN: center">2015.03.25 09:00 </TD>
							<TD style="TEXT-ALIGN: center"></TD><!--<td  style="text-align:center;" width="5%">
									<a href="javascript:openFile('0B6D9E02C0A8006901F64DA0F36AB76E','猪三戒');"><font color="blue" style="cursor:hand">导入</font></a>
								</td>--></TR>
						<TR onmouseover="this.className='table_tr_mouse'" onmouseout="this.className='table_tr_dark'" class=table_tr_dark>
							<TD style="TEXT-ALIGN: center"></TD>
							<TD style="TEXT-ALIGN: center"></TD>
							<TD style="CURSOR: hand; TEXT-ALIGN: center"><a href="${path}/zzb/app/console/daDemo/manage">胡星</a> </TD>
							<TD style="TEXT-ALIGN: center" width=40>女 </TD>
							<TD>1982.02 </TD>
							<TD title=被培训机构1秘书一处处长>被培训机构1秘书一处处长 </TD>
							<TD>2015.03.12 </TD>
							<TD style="TEXT-ALIGN: center">现职 </TD>
							<TD style="TEXT-ALIGN: center"></TD>
							<TD style="TEXT-ALIGN: center" width=40>杜政 </TD>
							<TD style="TEXT-ALIGN: center">2015.03.25 08:54 </TD>
							<TD style="TEXT-ALIGN: center"></TD><!--<td  style="text-align:center;" width="5%">
									<a href="javascript:openFile('0B7C1225C0A8006901F64DA028B1ED90','猪三戒');"><font color="blue" style="cursor:hand">导入</font></a>
								</td>--></TR>
						<TR onmouseover="this.className='table_tr_mouse'" onmouseout="this.className=''">
							<TD style="TEXT-ALIGN: center"></TD>
							<TD style="TEXT-ALIGN: center"></TD>
							<TD style="CURSOR: hand; TEXT-ALIGN: center"><a href="${path}/zzb/app/console/daDemo/manage">张烈</a> </TD>
							<TD style="TEXT-ALIGN: center" width=40></TD>
							<TD></TD>
							<TD title=被培训机构1秘书一处副处长>被培训机构1秘书一处副处长 </TD>
							<TD>2015.03.12 </TD>
							<TD style="TEXT-ALIGN: center">现职 </TD>
							<TD style="TEXT-ALIGN: center"></TD>
							<TD style="TEXT-ALIGN: center" width=40>杜政 </TD>
							<TD style="TEXT-ALIGN: center">2015.03.25 09:03 </TD>
							<TD style="TEXT-ALIGN: center"><a href="#">胡星</a> </TD><!--<td  style="text-align:center;" width="5%">
									<a href="javascript:openFile('0CBEFF4FC0A800690137C12FA91894DA','猪四戒');"><font color="blue" style="cursor:hand">导入</font></a>
								</td>--></TR>
						<TR onmouseover="this.className='table_tr_mouse'" onmouseout="this.className='table_tr_dark'" class=table_tr_dark>
							<TD style="TEXT-ALIGN: center"></TD>
							<TD style="TEXT-ALIGN: center"></TD>
							<TD style="CURSOR: hand; TEXT-ALIGN: center"><a href="${path}/zzb/app/console/daDemo/manage">张家华</a> </TD>
							<TD style="TEXT-ALIGN: center" width=40></TD>
							<TD></TD>
							<TD title=""></TD>
							<TD>2015.03.12 </TD>
							<TD style="TEXT-ALIGN: center"></TD>
							<TD style="TEXT-ALIGN: center"></TD>
							<TD style="TEXT-ALIGN: center" width=40>杜政 </TD>
							<TD style="TEXT-ALIGN: center">2015.03.25 08:54 </TD>
							<TD style="TEXT-ALIGN: center"></TD><!--<td  style="text-align:center;" width="5%">
									<a href="javascript:openFile('0B681686C0A8006901F64DA0A3B55738','猪一戒');"><font color="blue" style="cursor:hand">导入</font></a>
								</td>--></TR>
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
	function unloadFile(){
		document.getElementById("btn-unloadFile").click();
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


	var view = function(id){
		$.ajax({
			async:false,
			type:"POST",
			url:"${path}/zzb/app/console/asetA01/ajax/view",
			dataType : "html",
			headers:{
				"OWASP_CSRFTOKEN":'${sessionScope.OWASP_CSRFTOKEN}'
			},
			data:{
				'id':id,
				'b01Id':"${b01Id}"
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
	function fileDown(type) {
		window.open("${path }/zzb/app/console/daDemo/ajax/down?type="+type);
	}
</script>
</body>
</html>
