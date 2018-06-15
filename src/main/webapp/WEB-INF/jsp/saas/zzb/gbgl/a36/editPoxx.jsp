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
	<title>修改配偶信息</title>
</head>
<body>
<div >
	<!-- BEGIN FORM-->
	<div class="portlet box grey">

		<div class="portlet-title">

			<div class="caption">

				<i class="icon-reorder"></i>

				<span class="hidden-480">修改配偶信息</span>

			</div>
			<div class="tools">
				<a href="javascript:location.reload();" class="reload"></a>

			</div>
		</div>
		<form action="${path}/zzb/gbgl/a02/save" class="form-horizontal" id="addZwform" method="post">
			<input type="hidden" id="a01Id" name="a01Id" value="${a01Id}">
			<input type="hidden" id="a3600" name="a3600" value="${vo.a3600}">
			<input type="hidden" id="flag" name="flag" value="1">
			<table  border="0" style="width:100%;" cellPadding="5px">
                <div class="caption">修改配偶信息</div>
				<div class="row-fluid">
					<div class="span6 ">
						<div id="a3601Group" class="control-group">
							<label class="control-label" ><span class="required">*</span>配偶姓名</label>
							<div class="controls" >
								<input type="text" class="span9 m-wrap" name="a3601" required maxlength="128" id="a3601" value="${vo.a3601}"/>
							</div>
						</div>
					</div>
					<div class="span6 ">
						<div id="a3627AGroup" class="control-group">
							<label class="control-label" >政治面貌</label>
							<div class="controls">
								<SelectTag:SelectTag id="a3627" needNullValue="true"
													 valueName="a3627A"
													 defaultkeys="${vo.a3627}" token="${sessionScope.OWASP_CSRFTOKEN}"
													 defaultvalues="${vo.a3627A}"
													 textClass="span9 m-wrap" radioOrCheckbox="radio"
													 selectUrl="${path}/api/dictionary/select?typeCode=GB762-1984"/>
							</div>
						</div>
					</div>
				</div>

				<div class="row-fluid">
					<div class="span6 ">
						<div id="a3607Group" class="control-group">
							<label class="control-label" >出生日期</label>
							<div class="controls">
								<input type="text" class="span9 m-wrap" name="a3607" maxlength="128" id="a3607" value="${vo.a3607}"
									   placeholder="日期格式 例如：2018或201801或20180101"  isDate="true" dateformat="yyyy,yyyymm,yyyymmdd"/>
							</div>
						</div>
					</div>
					<%--<div class="span6 ">--%>
						<%--<div id="a0265Group" class="control-group">--%>
							<%--<label class="control-label" >学位</label>--%>
                            <%--<div class="controls">--%>
                                <%--<input type="text" class="span9 m-wrap" name="a0265"  required="true" maxlength="128" id="a0265" value=""--%>
                                       <%--placeholder="日期格式 例如：2018或201801或20180101"  isDate="true" dateformat="yyyy,yyyymm,yyyymmdd"/>--%>
                            <%--</div>--%>
						<%--</div>--%>
					<%--</div>--%>
				</div>

				<div class="row-fluid">
					<div class="span6 ">
						<div id="a3621AGroup" class="control-group">
							<label class="control-label" >民族</label>
							<div class="controls" >
								<SelectTag:SelectTag id="a3621" needNullValue="true"
													 valueName="a3621A"
													 defaultkeys="${vo.a3621}" token="${sessionScope.OWASP_CSRFTOKEN}"
													 defaultvalues="${vo.a3621A}"
													 textClass="span9 m-wrap" radioOrCheckbox="radio"
													 selectUrl="${path}/api/dictionary/select?typeCode=GB3304-1991"/>
							</div>
						</div>
					</div>
					<div class="span6 ">
						<div id="a3624AGroup" class="control-group">
							<label class="control-label" >学历</label>
							<div class="controls">
                                <Tree:tree id="a3624" valueName="a3624A"
                                           selectClass="span9 m-wrap"
                                           treeUrl="${path}/api/dictionary/tree?typeCode=ZB64-2006/XLDM"
                                           token="${sessionScope.OWASP_CSRFTOKEN}"
                                           submitType="post" dataType="json" isSearch="false"
                                           checkedByTitle="true" isSelectTree="true" defaultkeys="${vo.a3624 }"
                                           defaultvalues="${vo.a3624A}"/>
							</div>
						</div>
					</div>
				</div>
				<div class="row-fluid">
					<div class="span6 ">
						<div id="a3641Group" class="control-group">
							<label class="control-label" >现状</label>
							<div class="controls">
								<SelectTag:SelectTag id="" needNullValue="true"
													 valueName="a3641"
													 defaultkeys="" token="${sessionScope.OWASP_CSRFTOKEN}"
													 defaultvalues="${vo.a3641}"
													 textClass="span9 m-wrap" radioOrCheckbox="radio"
													 selectUrl="${path}/api/dictionary/select?typeCode=ZB56-2006/CYXZ"/>
							</div>
						</div>
					</div>
						<div class="span6 ">
							<div id="a3611Group" class="control-group">
								<label class="control-label" >工作单位及职务</label>
								<div class="controls">
									<input type="text" class="span9 m-wrap" name="a3611" maxlength="128" id="a3611" value="${vo.a3611}" />
								</div>
							</div>
						</div>
				</div>

				<div class="row-fluid">
					<center>
						<div style="margin:auto;">
                            <button id="submitbutAdd" class="btn green" type="button" style="padding:7px 20px;" >确定</button>
                            <button id="cencal" class="btn green" type="button" style="padding:7px 20px;" >取消</button>
						</div>
					</center>
				</div>

			</table>

		</form>
	</div>
</div>
<script type="text/javascript" src="${path }/js/common/loading.js"></script>
<script type="text/javascript">
		var addZwform = new EstValidate("addZwform");
        $(function(){
            $("#submitbutAdd").on("click",function(){
                var bool = addZwform.form();
                if(bool){
                    $.ajax({
                        url : "${path}/zzb/gbgl/a36/updatePoxx",
                        type : "post",
                        data : $('#addZwform').serialize(),
                        dataType : "json",
                        headers: {
                            "OWASP_CSRFTOKEN":"${sessionScope.OWASP_CSRFTOKEN}"
                        },
                        success : function(json){
                            showTip("提示","修改成功!",2000);
                            $('#addModal').modal('hide');
                            $('#addDiv').html("");
                            $.ajax({
                                url : "${path }/zzb/gbgl/a36/ajax/shgx",
                                type : "post",
                                data : {"a01Id":"${a01Id}"},
                                dataType : "html",
                                headers:{
                                    OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
                                },
                                success : function(html){
                                    var view = $("#tab_show");
                                    view.html(html);
                                },
                                error : function(arg1, arg2, arg3){
                                    showTip("提示","社会关系加载失败");
                                }
                            });
                        },
                        error : function(arg1, arg2, arg3){
                            showTip("提示","出错了请联系管理员",2000);
                        }
                    });

                }
            });
        });

        $(function(){
            $("#cencal").on("click",function(){
                $.ajax({
                    url : "${path }/zzb/gbgl/a36/ajax/shgx",
                    type : "post",
                    data : {"a01Id":"${a01Id}"},
                    dataType : "html",
                    headers:{
                    OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
                    },
                    success : function(html){
                    var view = $("#tab_show");
                    view.html(html);
                    },
                    error : function(arg1, arg2, arg3){
                    showTip("提示","社会关系加载失败");
                    }
                });
            });
        });



</script>
</body>
</html>
