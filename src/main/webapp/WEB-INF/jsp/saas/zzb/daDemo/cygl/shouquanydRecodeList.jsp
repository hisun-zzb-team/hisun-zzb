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
	<title>授权阅档记录</title>
	<style type="text/css">
		form {
			margin: 0 0 0px;
		}
	</style>
</head>
<body>
<div id="viewTimeModal" class="modal container hide fade" tabindex="-1" data-width="600">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button data-dismiss="modal" class="close"  type="button"></button>
				<h3 class="modal-title" id="title" >
					何建英 浏览 “钱某某” 的阅档详细时间
				</h3>
			</div>
			<div class="modal-body" id="viewTimeDiv" style="background-color: #f1f3f6;">
			</div>
		</div>
	</div>
</div>
<div id="viewNeiRongModal" class="modal container hide fade" tabindex="-1" data-width="650">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button data-dismiss="modal" class="close"  type="button"></button>
				<h3 class="modal-title" id="addTitle" >
					何建英 浏览 “钱某某” 的阅档详细信息
				</h3>
			</div>
			<div class="modal-body" id="viewNeiRongDiv">
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
					<div class="caption">授权阅档记录</div>
					<div class="clearfix fr">




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
								查阅人：<input type="text" class="m-wrap" name="xmQuery" id="xmQuery" value="${xmQuery}" style="width: 80px;" />
								档案名称：<input type="text" class="m-wrap" name="xmQuery" id="xmQuery" value="${xmQuery}" style="width: 80px;" />
								查阅时间：<input type="text" class="m-wrap" name="xmQuery" id="xmQuery" value="${xmQuery}" style="width: 100px;" />
								到<input type="text" class="m-wrap" name="xmQuery" id="xmQuery" value="${xmQuery}" style="width: 100px;" />


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
							<th width=70>档案名称</th>
							<th width=150 style="text-align: center">查阅单位</th>
							<th width=70 style="text-align: center">查阅人</th>
							<th  width=120 style="text-align: center">查阅时间</th>
							<th width=120>查阅时长</th>
							<th>查阅内容</th>
						</thead>
						<tbody>
						<tr onmouseout="this.className=''" onmouseover="this.className='table_tr_mouse'">
							<td style="text-align:center;">
								 钱某某
							</td>
							<td   style="text-align:center;">
								区委组织部
							</td>
							<td  style="text-align:center;">
								何建英
							</td>
							<td  style="text-align:center;">2018.03.31
							</td>
							<td   style="text-align:left;">
								26秒<a href="javascript:viewTime()">【详细】</a>
							</td>
							<td  style="text-align:left;" title=""><!-- 查阅内容 -->
								干部履历表,19981029,干部履历书,19941102<a href="javascript:viewNeiRong()">【详细】</a>
							</td>


						</tr>
						<tr class="table_tr_dark" onmouseout="this.className='table_tr_dark'" onmouseover="this.className='table_tr_mouse'">
							<td  style="text-align:center;" >
								王某某
							</td>
							<td  style="text-align:center;">
								区组织部
							</td>
							<td  style="text-align:center;">
								组织部
							</td>
							<td  style="text-align:center;">
								2016.12.13
							</td>
							<td style="text-align:left;">
								16秒<font color="blue" style="cursor:hand" onclick="showViewTimes('F75E4B37A9FE71FC6A826BD152493604','组织部','王某某')">【详细】</font>
							</td>
							<td style="text-align:left;text-valign:middle;" title="">
								干部履历书,19940402<font color="blue" style="cursor:hand" onclick="showContents('F75E4B37A9FE71FC6A826BD152493604','组织部','王某某')">【详细】</font>
							</td>
							<!--<td  style="text-align:center;">
                                <font color="blue" style="cursor:hand" onclick="viewPhoto('F75E4B37A9FE71FC6A826BD152493604','组织部','王某某')">照片</font>
                            </td> -->
						</tr>



						<tr onmouseout="this.className=''" onmouseover="this.className='table_tr_mouse'">
							<td  style="text-align:center;" >
								何某某
							</td>
							<td  style="text-align:center;">
								区组织部
							</td>
							<td  style="text-align:center;">
								组织部
							</td>
							<td  style="text-align:center;">
								2016.11.17
							</td>
							<td style="text-align:left;">
								2分15秒<font color="blue" style="cursor:hand" onclick="showViewTimes('70693329A9FE71FC25BDCB366C5058C9','组织部','何某某')">【详细】</font>
							</td>
							<td style="text-align:left;text-valign:middle;" title="干部履历书,19940402">
								干部履历书,19940402<font color="blue" style="cursor:hand" onclick="showContents('70693329A9FE71FC25BDCB366C5058C9','组织部','何某某')">【详细】</font>
							</td>
							<!--<td  style="text-align:center;">
                                <font color="blue" style="cursor:hand" onclick="viewPhoto('70693329A9FE71FC25BDCB366C5058C9','组织部','何某某')">照片</font>
                            </td> -->
						</tr>



						<tr class="table_tr_dark" onmouseout="this.className='table_tr_dark'" onmouseover="this.className='table_tr_mouse'">
							<td  style="text-align:center;" >
								钟海腾
							</td>
							<td  style="text-align:center;">
								区组织部
							</td>
							<td  style="text-align:center;">
								关振雄
							</td>
							<td  style="text-align:center;">
								2013.09.18
							</td>
							<td style="text-align:left;">
								40秒<font color="blue" style="cursor:hand" onclick="showViewTimes('2E8A3D55AC15325432AEE5B45B691FAF','关振雄','钟海腾')">【详细】</font>
							</td>
							<td style="text-align:left;text-valign:middle;" title="毕业生登记表,200507,成人高等教育学生成绩表,200507,成人高教毕业证书,20050710">
								毕业生登记表,200507,成人高等教...<font color="blue" style="cursor:hand" onclick="showContents('2E8A3D55AC15325432AEE5B45B691FAF','关振雄','钟海腾')">【详细】</font>
							</td>
							<!--<td  style="text-align:center;">
                                <font color="blue" style="cursor:hand" onclick="viewPhoto('2E8A3D55AC15325432AEE5B45B691FAF','关振雄','钟海腾')">照片</font>
                            </td> -->
						</tr>



						<tr onmouseout="this.className=''" onmouseover="this.className='table_tr_mouse'">
							<td  style="text-align:center;" >
								王楠
							</td>
							<td  style="text-align:center;">
								区组织部
							</td>
							<td  style="text-align:center;">
								小雯
							</td>
							<td  style="text-align:center;">
								2013.09.05
							</td>
							<td style="text-align:left;">
								1分20秒<font color="blue" style="cursor:hand" onclick="showViewTimes('ED39AF89AC15325432AEE5B452EACC76','小雯','王楠')">【详细】</font>
							</td>
							<td style="text-align:left;text-valign:middle;" title="免党委办主任呈报表,20000911,任党委宣传委员呈报表,19910607,任正科级呈报表,19881230,任党委办副主任呈报表,19870704,定科员呈报表,19850930,转干审批表,19830923,新工人审批表,19721007,任党委办主任呈报表,19951119,公务员过渡审批表,19980414,任公司党委办主任呈报表,20001115,任党工委副书记纪工委书记审批表,20011016,任主任科员呈报表,20010927,公务员登记表,20061231,干部任免审批表,20090601,干部履历表,1988,干部履历表,19991020,关于文化程度的证明,19830404,职工子弟学校学生学籍表,高级政工师资格评审表,20011230">
								免党委办主任呈报表,20000911,任...<font color="blue" style="cursor:hand" onclick="showContents('ED39AF89AC15325432AEE5B452EACC76','小雯','王楠')">【详细】</font>
							</td>
							<!--<td  style="text-align:center;">
                                <font color="blue" style="cursor:hand" onclick="viewPhoto('ED39AF89AC15325432AEE5B452EACC76','小雯','王楠')">照片</font>
                            </td> -->
						</tr>



						<tr class="table_tr_dark" onmouseout="this.className='table_tr_dark'" onmouseover="this.className='table_tr_mouse'">
							<td  style="text-align:center;" >
								王文荣
							</td>
							<td  style="text-align:center;">
								区组织部
							</td>
							<td  style="text-align:center;">
								小雯
							</td>
							<td  style="text-align:center;">
								2013.09.05
							</td>
							<td style="text-align:left;">
								3分12秒<font color="blue" style="cursor:hand" onclick="showViewTimes('ED1A255DAC15325432AEE5B47B636F05','小雯','王文荣')">【详细】</font>
							</td>
							<td style="text-align:left;text-valign:middle;" title="海珠区第十次党代会代表登记表,20060906,海珠区第八次党代会代表登记表,19980320,共青团海珠区第八次代表大会登记表,19910630,体格检查表,19860610,分配工作报到通知书,19890625,党性锻炼小结,任科员呈报表,19921201,任人事科副科长呈报表,19950215,任党委秘书呈报表,19990831,任区教育局党委副书记审批表,20011016,任区党校常委副校长审批表,20050104,关于王文荣档案中无公务员过渡审批表的处理意见,20050203,干部任免审批表,20081226,毕业生见习期考核转正表,19901020,任小学教导主任呈报表,19900901,入党志愿书,19890623,转正申请书,19890415,干部履历表,20041102,任海珠区教育局局长审批表,20060112,任海珠区教育局局长审批表,20061222">
								海珠区第十次党代会代表登记表,2...<font color="blue" style="cursor:hand" onclick="showContents('ED1A255DAC15325432AEE5B47B636F05','小雯','王文荣')">【详细】</font>
							</td>
							<!--<td  style="text-align:center;">
                                <font color="blue" style="cursor:hand" onclick="viewPhoto('ED1A255DAC15325432AEE5B47B636F05','小雯','王文荣')">照片</font>
                            </td> -->
						</tr>



						<tr onmouseout="this.className=''" onmouseover="this.className='table_tr_mouse'">
							<td  style="text-align:center;" >
								李福坚
							</td>
							<td  style="text-align:center;">
								区组织部
							</td>
							<td  style="text-align:center;">
								关振雄
							</td>
							<td  style="text-align:center;">
								2013.09.05
							</td>
							<td style="text-align:left;">
								5分10秒<font color="blue" style="cursor:hand" onclick="showViewTimes('ECE704F1AC15325432AEE5B48F1C1E91','关振雄','李福坚')">【详细】</font>
							</td>
							<td style="text-align:left;text-valign:middle;" title="毕业证书,20001231,学历证明,20001231,中共中央党校函授学院学员成绩表,20001230,在职人员报考研究生登记表,19990701,本科招生审批表,19980710,成绩单,19920703,毕业证书,19920701">
								毕业证书,20001231,学历证明,200...<font color="blue" style="cursor:hand" onclick="showContents('ECE704F1AC15325432AEE5B48F1C1E91','关振雄','李福坚')">【详细】</font>
							</td>
							<!--<td  style="text-align:center;">
                                <font color="blue" style="cursor:hand" onclick="viewPhoto('ECE704F1AC15325432AEE5B48F1C1E91','关振雄','李福坚')">照片</font>
                            </td> -->
						</tr>



						<tr class="table_tr_dark" onmouseout="this.className='table_tr_dark'" onmouseover="this.className='table_tr_mouse'">
							<td  style="text-align:center;" >
								余荣彬
							</td>
							<td  style="text-align:center;">
								区组织部
							</td>
							<td  style="text-align:center;">
								小雯
							</td>
							<td  style="text-align:center;">
								2013.08.27
							</td>
							<td style="text-align:left;">
								8秒<font color="blue" style="cursor:hand" onclick="showViewTimes('BDE1E58FAC15325432AEE5B4FF9CDB17','小雯','余荣彬')">【详细】</font>
							</td>
							<td style="text-align:left;text-valign:middle;" title="2008年度考核登记表 ,20090511,2010年度考核登记表 ,20110609,2006年度考核登记表,20070405,2005年度考核登记表,20060412,考察报告,20011211,干部履历书,19880914">
								2008年度考核登记表 ,20090511,2...<font color="blue" style="cursor:hand" onclick="showContents('BDE1E58FAC15325432AEE5B4FF9CDB17','小雯','余荣彬')">【详细】</font>
							</td>
							<!--<td  style="text-align:center;">
                                <font color="blue" style="cursor:hand" onclick="viewPhoto('BDE1E58FAC15325432AEE5B4FF9CDB17','小雯','余荣彬')">照片</font>
                            </td> -->
						</tr>



						<tr onmouseout="this.className=''" onmouseover="this.className='table_tr_mouse'">
							<td  style="text-align:center;" >
								冯国超
							</td>
							<td  style="text-align:center;">
								区组织部
							</td>
							<td  style="text-align:center;">
								小雯
							</td>
							<td  style="text-align:center;">
								2013.08.27
							</td>
							<td style="text-align:left;">
								20秒<font color="blue" style="cursor:hand" onclick="showViewTimes('BDE17697AC15325432AEE5B43F0566B7','小雯','冯国超')">【详细】</font>
							</td>
							<td style="text-align:left;text-valign:middle;" title="考察报告,19901224,1995年度考核登记表,19960304,2002年度考核登记表,20030310,2006年度考核登记表,20070405,1998年度考核登记表,19990119">
								考察报告,19901224,1995年度考核...<font color="blue" style="cursor:hand" onclick="showContents('BDE17697AC15325432AEE5B43F0566B7','小雯','冯国超')">【详细】</font>
							</td>
							<!--<td  style="text-align:center;">
                                <font color="blue" style="cursor:hand" onclick="viewPhoto('BDE17697AC15325432AEE5B43F0566B7','小雯','冯国超')">照片</font>
                            </td> -->
						</tr>



						<tr class="table_tr_dark" onmouseout="this.className='table_tr_dark'" onmouseover="this.className='table_tr_mouse'">
							<td  style="text-align:center;" >
								郭留记
							</td>
							<td  style="text-align:center;">
								区组织部
							</td>
							<td  style="text-align:center;">
								王小楠
							</td>
							<td  style="text-align:center;">
								2013.08.14
							</td>
							<td style="text-align:left;">
								50秒<font color="blue" style="cursor:hand" onclick="showViewTimes('7A8DA455AC153254726228008AE97B83','王小楠','郭留记')">【详细】</font>
							</td>
							<td style="text-align:left;text-valign:middle;" title="干部送学登记表,19920808,毕业证书,19860726">
								干部送学登记表,19920808,毕业证...<font color="blue" style="cursor:hand" onclick="showContents('7A8DA455AC153254726228008AE97B83','王小楠','郭留记')">【详细】</font>
							</td>
							<!--<td  style="text-align:center;">
                                <font color="blue" style="cursor:hand" onclick="viewPhoto('7A8DA455AC153254726228008AE97B83','王小楠','郭留记')">照片</font>
                            </td> -->
						</tr>



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

	var viewNeiRong = function(){
		$.ajax({
			url:"${path}/zzb/app/console/daDemo/ajax/viewNeiRong",
			type : "post",
			data: {},
			headers:{
				OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
			},
			dataType : "html",
			success : function(html){
				$('#viewNeiRongDiv').html(html);

				$('#viewNeiRongModal').modal({
					keyboard: true
				});
			},
			error : function(){
				showTip("提示","出错了请联系管理员", 1500);
			}
		});
	}

	var viewTime = function(){
		$.ajax({
			url:"${path}/zzb/app/console/daDemo/ajax/viewTime",
			type : "post",
			data: {},
			headers:{
				OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
			},
			dataType : "html",
			success : function(html){
				$('#viewTimeDiv').html(html);

				$('#viewTimeModal').modal({
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
