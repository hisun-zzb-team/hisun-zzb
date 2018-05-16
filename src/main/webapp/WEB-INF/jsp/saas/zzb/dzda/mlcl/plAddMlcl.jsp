<%@ taglib prefix="Select" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/inc/servlet.jsp" %>
<%@include file="/WEB-INF/jsp/inc/taglib.jsp" %>
<%--
  ~ Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
  ~ http://www.hn-hisun.com
  ~ 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
  --%>
<title>批量添加材料</title>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<div class="container-fluid">

	<div class="row-fluid">
		<div class="span12">
			<%-- BEGIN SAMPLE FORM PORTLET 表单主体--%>

			<div class="portlet box grey">
				<div class="portlet-title">

					<div class="caption">

						<i class="icon-reorder"></i>

						<span class="hidden-480">批量添加材料</span>

					</div>
					<div class="relationbetTop_but">
						<button class="btn green" type="button" style="padding:7px 20px;" onclick="formSubmit()">确定</button>
						<a class="btn " href="${path }/zzb/dzda/a38/list"><i class="icon-remove-sign"></i> 取消</a>
					</div>
				</div>
				<form action="${path}/zzb/dzda/e01z1/plSaveMlcl" class="form-horizontal" id="form1" method="post">
					<input type="hidden" id="eCatalogTypeId" name="eCatalogTypeId" value=""/>
					<input type="hidden" id="a38Ids" name="a38Ids" value=""/>
					<dl class="dlattrbute">
						<dt><a href="###">基本信息</a></dt>
						<dd>
							<div class="row-fluid">
								<div class="span6 ">
									<div id="e01Z111Group" class="control-group">
										<label class="control-label"><span class="required">*</span>材料名称</label>
										<div class="controls">
											<input type="text" class="span10 m-wrap" name="e01Z111" required maxlength="128" id="e01Z111" value="" />
										</div>
									</div>
								</div>
								<div class="span6 ">
									<div id="e01Z117Group" class="control-group">
										<label class="control-label">材料制成时间</label>
										<div class="controls">
											<input type="text" class="span10 m-wrap" name="e01Z117" placeholder="日期格式 例如：2018或201801或20180101"  isDate="true" dateformat="yyyy,yyyymm,yyyymmdd" maxlength="128" id="e01Z117" value="" />
										</div>
									</div>
								</div>
							</div>
							<div class="row-fluid">
								<div class="span6 ">
									<div id="e01Z111RemarkGroup" class="control-group">
										<label class="control-label">材料名称备注</label>
										<div class="controls">
											<input type="text" class="span10 m-wrap" name="e01Z111Remark" maxlength="128" id="e01Z111Remark" value="" />
										</div>
									</div>
								</div>
								<div class="span6 ">
									<div id="e01Z114Group" class="control-group">
										<label class="control-label"><span class="required">*</span>材料页数</label>
										<div class="controls">

											<input type="text" class="span10 m-wrap" name="e01Z114" number="true" maxlength="128" id="e01Z114" value="" required/>

										</div>
									</div>
								</div>
							</div>
							<div class="row-fluid">
								<div class="span6 ">
									<div id="e01Z124Group" class="control-group">
										<label class="control-label">材料份数</label>
										<div class="controls">
											<input type="text" class="span10 m-wrap" name="e01Z124" number="true" maxlength="128" id="e01Z124" value="1" />
										</div>
									</div>
								</div>
								<div class="span6 ">
									<div id="e01Z101AGroup" class="control-group" >
										<label class="control-label">材料类型 <span class="required">*</span></label>
										<div class="controls">
											<Tree:tree id="e01Z101B" valueName="e01Z101A" treeUrl="${path}/zzb/dzda/e01z4/tree" token="${sessionScope.OWASP_CSRFTOKEN}"
													   submitType="post" dataType="json" isSearch="false" isSelectTree="true" required="true" onClick="onClickByTree1"
													   defaultkeys="${vo.parentId}" defaultvalues="${vo.parentName}" radioOrCheckbox="radio" selectClass="span10 m-wrap"/>
										</div>
									</div>
								</div>
							</div>
						</dd>
					</dl>
					<dl class="dlattrbute">
						<dt><a href="###">选择干部</a></dt>
						<dd>
							<div class="portlet-body" id="a38Table">
								<table class="table table-striped table-bordered table-hover dataTable table-set">
									<thead>
									<tr>
										<th style="width: 20px;"><input type="checkbox" id="allCheck" onchange="allCheckChange()"/></th>
										<th width=70>姓名</th>
										<th width=40>性别</th>
										<th width=70>出生年月</th>
										<th width=70>单位职务</th>
										<th width=70>干部状态</th>
										<th width=70>现职级时间</th>
									</thead>
									<tbody>
									<c:forEach items="${pager.datas}" var="vo">
										<tr style="text-overflow:ellipsis;">
											<td><input type="checkbox" name="a38Check" onchange="checkChange(this)" value="${vo.id }"></td>
											<td>${vo.a0101}</td>
											<td>${vo.a0104Content}</td>
											<td>${vo.a0107} </td>
											<td>${vo.a0157}</td>
											<td>${vo.gbztContent}</td>
											<td st>${vo.xzjsj}</td>
										</tr>
									</c:forEach>
									</tbody>
								</table>
								<jsp:include page="/WEB-INF/jsp/common/page.jsp">
									<jsp:param value="${pager.total }" name="total"/>
									<jsp:param value="${pager.pageCount }" name="endPage"/>
									<jsp:param value="${pager.pageSize }" name="pageSize"/>
									<jsp:param value="${pager.pageNum }" name="page"/>
								</jsp:include>
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

	function onClickByTree1 (event, treeId, treeNode){
		$("#eCatalogTypeId").val(treeNode.id);//赋值
	}

	function pagehref (pageNum ,pageSize){
		$.ajax({
			url: "${path}/zzb/dzda/e01z1/ajax/plGetA38List",// 请求的action路径
			cache:false,
			type: 'POST',
			dataType : "html",
			headers: {
				"OWASP_CSRFTOKEN":"${sessionScope.OWASP_CSRFTOKEN}"
			},
			data:{
				"pageNum":pageNum,
				"pageSize":pageSize
			},
			success:function(html){
				$('#a38Table').html(html);

				var checks = document.getElementsByName("a38Check");
				if(checks){
					for(var i=0;i<checks.length;i++){
						var id = checks[i].value;
						var index = indexOf(ids,id);
						if(index>-1){
							checks[i].checked=true;
							checks[i].parentNode.className = "checked";
						}
					}
				}
			},
			error: function () {// 请求失败处理函数
				alert('请求失败');
			}
		});
	}

	var ids = [];

	function indexOf(arr, str){
		if(arr && arr.indexOf){
			return arr.indexOf(str);
		}
		var len = arr.length;
		for(var i = 0; i < len; i++){
			// 定位该元素位置
			if(arr[i] == str){
				return i;
			}
		}
		// 数组中不存在该元素
		return -1;
	}

	function checkChange(obj){
		var check=obj.checked;
		var id = obj.value;
		var index = indexOf(ids,id);
		var idsLength = ids.length;
		if(check&&index==-1){
			ids[idsLength] = id;
		}else if(!check&&index>-1){
			ids.splice(index,1);
		}

	}

	function allCheckChange(){
		var allCheck = document.getElementById("allCheck");
		var checks = document.getElementsByName("a38Check");
		if(checks){
			for(var i=0;i<checks.length;i++) {
				var idsLength = ids.length;
				var check = checks[i].checked;
				var id = checks[i].value;
				var index = indexOf(ids,id);
				checks[i].checked = allCheck.checked;
				if (allCheck.checked == true) {
					if(!check&&index==-1){
						ids[idsLength] = id;
						checks[i].parentNode.className = "checked";
					}
				}else{
					if(index>-1){
						ids.splice(index,1);
						checks[i].parentNode.className = "";
					}
				}
			}
		}
	}

	var form1 = new EstValidate("form1");
	function formSubmit(){
		var idStr = "";
		for(var i= 0;i<ids.length;i++){
			idStr=idStr+ids[i];
			if(i<ids.length-1){
				idStr=idStr+",";
			}
		}
		$("#a38Ids").val(idStr);//赋值
		var bool = form1.form();
		if(bool){
			$.ajax({
				url : "${path }/zzb/dzda/e01z1/plSaveMlcl",
				type : "post",
				data : $("#form1").serialize(),
				headers:{
					OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
				},
				dataType : "json",
				success : function(data){
					if(data.success){
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

</script>