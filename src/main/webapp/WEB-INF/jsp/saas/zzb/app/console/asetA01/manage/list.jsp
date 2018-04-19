<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/inc/taglib.jsp" %>
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
	<title>${b0101} 干部管理</title>
	<style type="text/css">
		form {
			margin: 0 0 0px;
		}
		.tabbable-custom > .nav-tabs{border-bottom: 1px solid #ddd;}
		.tabbable-custom{ width:640px; float:left;}


		.NewlyaddedRight{float:right; width:320px; background-color:#f9f9f9;min-height:550px;}
		.form-horizontal .control-label{ width:120px;}
		.form-horizontal .controls{ margin-left:130px;}
		.NewlyaddedRYtitle{ height:36px; line-height:36px; font-size:14px; text-indent:1em; background-color:#f5f5f5;border-bottom: 1px solid #ddd; margin-bottom:20px;}
		.inputtz{ width:140px; background-color:#FFFFFF !important; float:left;}
		.inputtz02{width:166px; background-color:#FFFFFF !important; float:left;}
		.inptzBtn{ height:30px; line-height:30px; background-color:#E1E1E1; display:inline-block; width:26px; text-align:center;}
		.form-horizontal .control-label{ padding-top:3px;}
		.PresentBtn{ text-align:center; padding-top:20px;}
		.PresentBtn button.btn{ margin:0 5px;}

		.VolunteerApplytable{ border:solid 1px #DDDDDD;border-bottom: none; border-right: none; color:#555;}
		.VolunteerApplytable tr td{ font-size:12px; border-bottom:solid 1px #DDDDDD; border-right:solid 1px #DDDDDD; padding:2px 2px; text-align:center; }
		.VolunteerApplytable tr td input.text01{ height:28px; width:87px; border:none; background-color:rgba(255,255,255,0.00); font-size:12px; margin-bottom:0; color:#333333;}
		.VolunteerApplytable tr td input.text02{ height:20px; width:145px; border:none; background-color:rgba(255,255,255,0.00); font-size:12px; margin-bottom:0; color:#333333;}
		.VolunteerApplytable tr td input.text03{ height:20px; width:220px; border:none; background-color:rgba(255,255,255,0.00); font-size:12px; margin-bottom:0; color:#333333;}
		.VolunteerApplytable tr td input.text04{ height:20px; width:100%; border:none; background-color:rgba(255,255,255,0.00); font-size:12px; margin-bottom:0; color:#333333;}

		.VolunteerApplytable tr td input.text05{ height:20px; width:80px; border:none; background-color:rgba(255,255,255,0.00); font-size:12px; margin-bottom:0; color:#333333; text-align:center;}
		.UploadPhotos{ background-color:#F6F6F6; width:150px;}

		.UploadpBtn{ width:80px; height:30px; line-height:30px; text-align:center; background-color:#a73116; font-size:14px; color:#FFFFFF; display: inline-block; border-radius:4px;}
		.UploadpBtn:hover{ background-color:#d49e4a;color:#FFFFFF;  }
		textarea:focus, input[type="text"]:focus, input[type="password"]:focus, input[type="datetime"]:focus, input[type="datetime-local"]:focus, input[type="date"]:focus, input[type="month"]:focus, input[type="time"]:focus, input[type="week"]:focus, input[type="number"]:focus, input[type="email"]:focus, input[type="url"]:focus, input[type="search"]:focus, input[type="tel"]:focus, input[type="color"]:focus, .uneditable-input:focus{box-shadow: inset 0 1px 1px rgba(0,0,0,0.0),0 0 8px rgba(82,168,236,0.0);
		}
		textarea, input[type="text"], input[type="password"], input[type="datetime"], input[type="datetime-local"], input[type="date"], input[type="month"], input[type="time"], input[type="week"], input[type="number"], input[type="email"], input[type="url"], input[type="search"], input[type="tel"], input[type="color"], .uneditable-input{box-shadow: inset 0 1px 1px rgba(0,0,0,0.0);}
		input[type="radio"], input[type="checkbox"]{ margin:0;}
		.textareatab{height:127px; width:100%; border:none; background-color:rgba(255,255,255,0.00); font-size:12px; font-family:"微软雅黑", Verdana, Geneva, sans-serif; color:#333333;}

		.VolunteerApplytable tr td p.pb01{ padding:5px 20px;}
		.VolunteerApplytable tr td.c01{ color:#333333; background-color:#F9F9F9;}

		.dyresume{ margin:10px 0 30px 0;}
		.dyresume i{ font-size:20px;}

		.textareatab02{height:78px; width:100%; border:none; background-color:rgba(255,255,255,0.00); font-size:12px; font-family:"微软雅黑", Verdana, Geneva, sans-serif; color:#333333;}
		.zytwoname{ margin-top:50px; margin-bottom:60px;}
		.MoveBtn{ background-color:#DDDDDD; display:inline-block; padding:3px 10px; border-radius:4px; color:#0385D8 !important; margin-bottom:5px; margin-top:10px;}
		.MoveBtn:hover{background-color:#0385D8;color:#fff !important; }
		.DownBtn{ background-color:#DDDDDD; display:inline-block; padding:3px 10px; border-radius:4px; color:#0385D8 !important;}
		.DownBtn:hover{background-color:#0385D8;color:#fff !important; }
		.dydeleteBtn{ background-color:#DDDDDD; display:inline-block; padding:3px 10px; border-radius:4px; color:#F40101 !important;}
		.dydeleteBtn:hover{background-color:#F40101;color:#fff !important; }

		.Otherinformationtable{ border:solid 1px #DDDDDD;border-bottom: none; border-right: none; color:#555;}
		.Otherinformationtable tr td{ font-size:12px; border-bottom:solid 1px #DDDDDD; border-right:solid 1px #DDDDDD; padding:10px 8px; text-align: left; }
		.Otherinformationtable tr td.c01{ color:#333333; background-color:#F9F9F9; text-align:center;}
		.Otherinformationtable tr td input.text01{ height:28px; width:87px; border:none; background-color:rgba(255,255,255,0.00); font-size:12px; margin-bottom:0; color:#333333;}

		.spd01{ float:left; margin-right:8px;}
	</style>
</head>
<body>

<div class="container-fluid">
	<div id="ModalNotice" class="modal hide fade" tabindex="-1" data-width="1000">
		<div class="modal-header" >
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
			<h3>人员新增信息</h3>
		</div>
		<div class="modal-body" style="min-height:550px;">

			<div class="tabbable tabbable-custom">
				<ul class="nav nav-tabs">
					<li class="active"><a href="#tab_1_1" data-toggle="tab">任免审批表(一)</a></li>
					<li><a href="#tab_1_2" data-toggle="tab">任免审批表(二)</a></li>
					<li><a href="#tab_1_3" data-toggle="tab">其他信息</a></li>
				</ul>

				<div class="tab-content pt0">
					<div class="tab-pane active" id="tab_1_1">
						<table width="100%" border="0" cellspacing="0" cellpadding="0" class="VolunteerApplytable mt20">
							<tbody>
							<tr>
								<td width="60" class="c01"><em class="red">*</em>姓&nbsp;&nbsp;名</td>
								<td><input type="text" class="text01"></td>
								<td width="60" class="c01"><em class="red">*</em>性&nbsp;&nbsp;别</td>
								<td><input type="text" class="text01"></td>
								<td width="60" class="c01"><em class="red">*</em>出生<br>&nbsp;&nbsp;年月</td>
								<td><input type="text" class="text01"></td>
								<!--<td rowspan="3" class="UploadPhotos">
                                    <p>照片</p>
                                    <a href="###" class="UploadpBtn">上传</a>
                                </td>-->
								<td rowspan="4" class="UploadPhotos">
									<img src="${path}/images/templateImage/portraitIco01.jpg">
								</td>
							</tr>
							<tr>
								<td class="c01"><em class="red">*</em>民&nbsp;&nbsp;族</td>
								<td><input type="text" class="text01"></td>
								<td class="c01"><em class="red">*</em>籍&nbsp;&nbsp;贯</td>
								<td><input type="text" class="text01"></td>
								<td class="c01"><em class="red">*</em>出生地</td>
								<td><input type="text" class="text01"></td>
							</tr>
							<tr>
								<td class="c01">入&nbsp;&nbsp;党<br>时&nbsp;&nbsp;间</td>
								<td><input type="text" class="text01"></td>
								<td class="c01"><em class="red">*</em>参加工<br>&nbsp;&nbsp;作时间</td>
								<td><input type="text" class="text01"></td>
								<td class="c01"><em class="red">*</em>健康<br>&nbsp;&nbsp;状况</td>
								<td><input type="text" class="text01"></td>
							</tr>
							<tr>
								<td class="c01">专业技<br>术职务</td>
								<td colspan="2"><input type="text" class="text01" style="width:150px;"></td>
								<td class="c01">熟悉专业<br>有何特长</td>
								<td colspan="2"><input type="text" class="text01" style="width:150px;"></td>
							</tr>
							<tr>
								<td rowspan="4" class="c01">学历<br>学位</td>
								<td rowspan="2" class="c01">全日制<br>教&nbsp;&nbsp;育</td>
								<td colspan="2"><input type="text" class="text02" value=""></td>
								<td rowspan="2" class="c01">毕业院校<br>系及专业</td>
								<td colspan="2"><input type="text" class="text03" value=""></td>
							</tr>
							<tr>
								<td colspan="2"><input type="text" class="text02" value=""></td>
								<td colspan="2"><input type="text" class="text03" value=""></td>
							</tr>
							<tr>
								<td rowspan="2" class="c01">在&nbsp;&nbsp;职<br>教&nbsp;&nbsp;育</td>
								<td colspan="2"><input type="text" class="text02" value=""></td>

								<td rowspan="2" class="c01">毕业院校<br>系及专业</td>
								<td colspan="2"><input type="text" class="text03" value=""></td>
							</tr>
							<tr>
								<td colspan="2"><input type="text" class="text02" value=""></td>
								<td colspan="2"><input type="text" class="text03" value=""></td>
							</tr>

							<tr>
								<td class="c01" colspan="2"><em class="red">*</em> 工作单位及职务</td>
								<td colspan="5"><input type="text" class="text04"></td>
							</tr>
							<tr>
								<td class="c01" style=" vertical-align:top;">
									<p class="dyresume"><i class="icon-file-text"></i></p>
									<em class="red">*</em> 简历
								</td>
								<td colspan="6"><textarea class="textareatab"></textarea></td>
							</tr>
							</tbody>
						</table>
					</div>

					<div class="tab-pane" id="tab_1_2">
						<table width="100%" border="0" cellspacing="0" cellpadding="0" class="VolunteerApplytable mt20">
							<tbody>
							<tr>
								<td class="c01" style="width:80px;">奖惩综述</td>
								<td colspan="6"><textarea class="textareatab02">无</textarea></td>
							</tr>
							<tr>
								<td class="c01" style="width:80px;">年度考核<br>结果综述</td>
								<td colspan="6"><textarea class="textareatab02">无</textarea></td>
							</tr>
							<tr>
								<td rowspan="9" class="c01" style="width:80px; vertical-align:top;">
									<p><a href="###" class="MoveBtn"><i class="icon-long-arrow-up"></i> 上移</a></p>
									<p><a href="###" class="DownBtn"><i class="icon-long-arrow-down"></i> 下移</a></p>
									<p class="zytwoname">家庭主要<br>成员及社<br>会重要关系</p>
									<p><a href="###" class="dydeleteBtn"><i class="icon-remove-sign"></i> 删除</a></p>
								</td>
								<td class="c01">称谓</td>
								<td class="c01">姓名</td>
								<td class="c01">出生日期</td>
								<td class="c01">政治面貌</td>
								<td class="c01">工作职位及职务</td>
							</tr>
							<tr>
								<td><input type="text" class="text05"></td>
								<td><input type="text" class="text05"></td>
								<td><input type="text" class="text05"></td>
								<td><input type="text" class="text05"></td>
								<td><input type="text" class="text05" style="width:150px;"></td>
							</tr>
							<tr>
								<td><input type="text" class="text05"></td>
								<td><input type="text" class="text05"></td>
								<td><input type="text" class="text05"></td>
								<td><input type="text" class="text05"></td>
								<td><input type="text" class="text05" style="width:150px;"></td>
							</tr>
							<tr>
								<td><input type="text" class="text05"></td>
								<td><input type="text" class="text05"></td>
								<td><input type="text" class="text05"></td>
								<td><input type="text" class="text05"></td>
								<td><input type="text" class="text05" style="width:150px;"></td>
							</tr>
							<tr>
								<td><input type="text" class="text05"></td>
								<td><input type="text" class="text05"></td>
								<td><input type="text" class="text05"></td>
								<td><input type="text" class="text05"></td>
								<td><input type="text" class="text05" style="width:150px;"></td>
							</tr>
							<tr>
								<td><input type="text" class="text05"></td>
								<td><input type="text" class="text05"></td>
								<td><input type="text" class="text05"></td>
								<td><input type="text" class="text05"></td>
								<td><input type="text" class="text05" style="width:150px;"></td>
							</tr>
							<tr>
								<td><input type="text" class="text05"></td>
								<td><input type="text" class="text05"></td>
								<td><input type="text" class="text05"></td>
								<td><input type="text" class="text05"></td>
								<td><input type="text" class="text05" style="width:150px;"></td>
							</tr>
							<tr>
								<td><input type="text" class="text05"></td>
								<td><input type="text" class="text05"></td>
								<td><input type="text" class="text05"></td>
								<td><input type="text" class="text05"></td>
								<td><input type="text" class="text05" style="width:150px;"></td>
							</tr>
							<tr>
								<td><input type="text" class="text05"></td>
								<td><input type="text" class="text05"></td>
								<td><input type="text" class="text05"></td>
								<td><input type="text" class="text05"></td>
								<td><input type="text" class="text05" style="width:150px;"></td>
							</tr>
							</tbody>
						</table>

					</div>

					<div class="tab-pane" id="tab_1_3">

						<table width="100%" border="0" cellspacing="0" cellpadding="0" class="Otherinformationtable mt20">
							<tbody>
							<tr>
								<td class="c01" rowspan="5" style="width:80px;">其他信息</td>
								<td><span class="spd01">是否考录</span> <input type="checkbox"></td>
								<td>
									<span class="spd01" style="margin-top:5px; margin-right:10px;">录用时间</span>
									<input type="text" placeholder="" class="m-wrap inputtz mb0">
								</td>
							</tr>
							<tr>
								<td><span class="spd01">是否选调生</span> <input type="checkbox"></td>
								<td>
									<span class="spd01">进入选调<br>生&nbsp;&nbsp;时&nbsp;&nbsp;间</span>
									<input type="text" placeholder="" class="m-wrap inputtz mb0 mt4">
									<!--<div data-date="2012-12-21T15:25:00Z" class="input-append date form_datetime">
                                        <input type="text" class="m-wrap" readonly value="" size="16" style="width:160px;">
                                        <span class="add-on"><i class="icon-remove"></i></span>
                                        <span class="add-on"><i class="icon-calendar"></i></span>
                                    </div>-->
								</td>
							</tr>
							<tr>
								<td>
									<span class="spd01 mt4">成长地</span>
                                                  <span>
                                                      <input type="text" placeholder="" class="m-wrap inputtz mb0">
                                                      <a href="###" class="inptzBtn"><i class="icon-search"></i></a>
                                                  </span>
								</td>
								<td>
									<span class="spd01 mt4">级别</span>
                                                  <span>
                                                      <input type="text" placeholder="" class="m-wrap inputtz mb0">
                                                      <a href="###" class="inptzBtn"><i class="icon-search"></i></a>
                                                  </span>
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<span class="spd01 mt4">专业技术类任职资格</span>
                                                 <span>
                                                      <input type="text" placeholder="" class="m-wrap inputtz mb0">
                                                      <a href="###" class="inptzBtn"><i class="icon-search"></i></a>
                                                  </span>
								</td>
							</tr>
							<tr>
								<td colspan="2" style="height:100px;"></td>
							</tr>
							<tr>
								<td class="c01" style="width:80px;">备注</td>
								<td colspan="6"><textarea class="textareatab02" style="height:116px;"></textarea></td>
							</tr>
							</tbody>
						</table>


					</div>

				</div>
			</div>

			<div class="NewlyaddedRight">
				<div class="NewlyaddedRYtitle">现职人员</div>
				<div class="row-fluid form-horizontal">
					<div class="control-group">
						<label class="control-label"><span class="Required">*</span>统计关系所在单位</label>
						<div class="controls">
							<input type="text" placeholder="" class="m-wrap inputtz">
							<a href="###" class="inptzBtn"><i class="icon-search"></i></a>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label"><span class="Required">*</span>身份证号</label>
						<div class="controls">
							<input type="text" placeholder="" class="m-wrap inputtz02">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label"><span class="Required">*</span>管理类别</label>
						<div class="controls">
							<input type="text" placeholder="" class="m-wrap inputtz02">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label"><span class="Required">*</span>人员类别</label>
						<div class="controls">
							<input type="text" placeholder="" class="m-wrap inputtz02">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label"><span class="Required">*</span>编制类型</label>
						<div class="controls">
							<input type="text" placeholder="" class="m-wrap inputtz02">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">现职位层次</label>
						<div class="controls">
							<input type="text" placeholder="" class="m-wrap inputtz">
							<a href="###" class="inptzBtn"><i class="icon-search"></i></a>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">任现职务层次时间</label>
						<div class="controls">
							<input type="text" placeholder="" class="m-wrap inputtz02">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">现职级</label>
						<div class="controls">
							<input type="text" placeholder="" class="m-wrap inputtz">
							<a href="###" class="inptzBtn"><i class="icon-search"></i></a>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">任现职级时间</label>
						<div class="controls">
							<input type="text" placeholder="" class="m-wrap inputtz02">
						</div>
					</div>


					<div class="PresentBtn">
						<button type="button" class="btn green">上一人</button>
						<button type="button" class="btn green">下一人</button>
						<button type="button" class="btn green">打印任免表</button>
					</div>
				</div>
			</div>

		</div>

		<div class="modal-footer">
			<button type="button" class="btn green">继续增加</button>
			<button type="button" data-dismiss="modal" class="btn red">保 存</button>
		</div>
	</div>
	<div class="row-fluid">
		<div class="span12 responsive">
			<%-- 表格开始 --%>
			<form class=""id="importForm" enctype="multipart/form-data">
				<input type="hidden" name="b01Id" value="${b01Id}"/>
				<div class="portlet-title">
					<div class="caption">${b0101} 干部列表</div>
					<div class="clearfix fr">

						<div class="btn-group">
							<a class="btn green dropdown-toggle" data-toggle="dropdown" href="#">
								增加 <i class="icon-angle-down"></i>
							</a>
							<ul class="dropdown-menu" style="margin-left: -90px;">
								<li >
									<a onclick="addData()">按干部任免审批表增加</a>
								</li>
								<li >
									<a href="${path}/zzb/app/console/asetA01/addManage">按信息集增加</a>
								</li>
							</ul>
						</div>
						<a id="sample_editable_1_new" class="btn green" href="#">
							<i class="icon-circle-arrow-up"></i>任免审批表导入
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
							姓名：<input type="text" class="m-wrap" name="xmQuery" id="xmQuery" value="${xmQuery}" style="width: 100px;" />
							<button type="button" class="btn Short_but" onclick="searchSubmit()">查询</button>
							<button type="button" class="btn Short_but" onclick="clearData()">清空</button>
						</form>
					</div>
				</div>

			</div>
				<div class="portlet-body">
					<table class="table table-striped table-bordered table-hover dataTable table-set">
						<thead>
						<tr>
							<th width="60">姓名</th>
							<th width="40">性别</th>
							<th width="60">出生<br>年月</th>
							<th>现任职务</th>
							<th width="100" style="text-align: center">全日制<br>学历学位
							</th>
							<th width="150" style="text-align: center">在职<br>学历学位
							<th width="80"style="text-align: center">任现职级<br>时间
							<th width="12%">操作</th>
							</th>
							<%--<th width="100">专业技<br>术职务--%>
							<%--</th>--%>
							<%--<th width="65">任现职<br>务时间--%>
							<%--</th>--%>
							<%--<th width="100">任现职<br>级时间--%>
							<%--</th>--%>
							<%--<th width="40">操作</th>--%>
						</tr>
						</thead>
						<tbody>
						<tr style="text-overflow:ellipsis;">
							<td title="龙晓华"><a href="javascript:view('04DB6601-5ECB-4518-9ED5-96420FDB5A82')" class="">龙晓华</a></td>
							<td title="女">女</td>
							<td title="1963.12" style="text-align: center">1963.12<br>(55岁)</td>
							<td title="州委副书记，州政府州长">州委副书记，州政府州长</td>
							<td>中专
							</td>
							<td>研究生
								管理学硕士学位</td>
							<td>2010.11</td>
							<td>
								<a href="#" class="">编辑</a>|
								<a href="#" class="">删除</a>
							</td>
						</tr>
						<tr style="text-overflow:ellipsis;">
							<td title="叶红专"><a href="javascript:view('9FE583E7-3C8C-4BA1-AE0A-D1456C1195B3')" class="">叶红专</a></td>
							<td title="男">男</td>
							<td title="1958.06" style="text-align: center">1958.06<br>(60岁)</td>
							<td title="州委书记">州委书记</td>
							<td>高中
							</td>
							<td>大专
							</td>
							<td>2009.03</td>
							<td>
								<a href="#" class="">编辑</a>|
								<a href="#" class="">删除</a>
							</td>
						</tr>
						<tr style="text-overflow:ellipsis;">
							<td title="曹普华"><a href="javascript:view('8B4C4132-A83F-48AD-95D0-E90F836BB4C7')" class="">曹普华</a></td>
							<td title="男">男</td>
							<td title="1966.09" style="text-align: center">1966.09<br>(52岁)</td>
							<td title="州委副书记，州政协党组副书记，州委统战部部长">州委副书记，州政协党组副书记，州委统战部部长</td>
							<td>研究生
								法学硕士学位</td>
							<td>
							</td>
							<td>2008.03</td>
							<td>
								<a href="#" class="">编辑</a>|
								<a href="#" class="">删除</a>
							</td>
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



	})();

	function addData(){
		$('#ModalNotice').modal({
			keyboard: true
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
</script>
</body>
</html>
