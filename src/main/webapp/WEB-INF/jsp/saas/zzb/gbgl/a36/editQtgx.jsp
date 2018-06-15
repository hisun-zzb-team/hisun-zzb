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
	<title>修改欠缺材料</title>
</head>
<body>
<div class="container-fluid">

	<div class="row-fluid">
		<div class="span12">
			<%-- BEGIN SAMPLE FORM PORTLET 表单主体--%>

			<div class="portlet box grey">
				<div class="portlet-title">
					<div class="caption">
						<span class="hidden-480">修改欠缺材料</span>
					</div>
				</div>


				<div class="portlet-body form">
					<!-- BEGIN FORM-->

					<form action="${path }/zzb/app/console/bwh/save" class="form-horizontal" id="qtgxform" method="post" enctype="multipart/form-data">
						<input type="hidden" id="a01Id" name="a01Id" value="${a01Id}">
						<input type="hidden" id="a3600" name="a3600" value="${vo.a3600}">
						<input type="hidden" id="flag" name="flag" value="2">
						<div id="a3604AGroup" class="control-group">
							<label class="control-label">称谓<span class="required">*</span></label>
							<div class="controls">
								<%--<input type="text" class="span6 m-wrap" name="a3604A" required  maxlength="200" id="a3604A" value=""/>--%>
								<SelectTag:SelectTag id="a3604B" needNullValue="true"
													 valueName="a3604A"
													 defaultkeys="${vo.a3604B}" token="${sessionScope.OWASP_CSRFTOKEN}"
													 defaultvalues="${vo.a3604A}"
													 textClass="span9 m-wrap" radioOrCheckbox="radio"
													 selectUrl="${path}/api/dictionary/select?typeCode=GB4761-1984"/>
							</div>
						</div>

						<div class="control-group" id="a3601Group">

							<label class="control-label">姓名</label>
							<div class="controls">
								<input type="text" class="span9 m-wrap" name="a3601" required maxlength="128" id="a3601" value="${vo.a3601}"/>
							</div>

						</div>
						<div id="a3607Group" class="control-group" >
							<label class="control-label">出生日期</label>
							<div class="controls">
								<input type="text" class="span9 m-wrap" name="a3607" maxlength="128" id="a3607" value="${vo.a3607}"
									   placeholder="日期格式 例如：2018或201801或20180101"  isDate="true" dateformat="yyyy,yyyymm,yyyymmdd"/>
							</div>
						</div>

						<div id="a3627AGroup" class="control-group" >
							<label class="control-label">政治面貌</label>
							<div class="controls">
								<SelectTag:SelectTag id="a3627" needNullValue="true"
													 valueName="a3627A"
													 defaultkeys="${vo.a3627}" token="${sessionScope.OWASP_CSRFTOKEN}"
													 defaultvalues="${vo.a3627A}"
													 textClass="span9 m-wrap" radioOrCheckbox="radio"
													 selectUrl="${path}/api/dictionary/select?typeCode=GB762-1984"/>
							</div>
						</div>

						<div id="a3611Group" class="control-group">
							<label class="control-label">工作单位及职务<span class="required">*</span></label>
							<div class="controls">
								<input type="text" class="span9 m-wrap" name="a3611" maxlength="128" id="a3611" value="${vo.a3611}" />
							</div>

						</div>

						<div id="a3641Group" class="control-group">
							<label class="control-label">现状</label>
							<div class="controls">
								<SelectTag:SelectTag id="" needNullValue="true"
													 valueName="a3641"
													 defaultkeys="" token="${sessionScope.OWASP_CSRFTOKEN}"
													 defaultvalues="${vo.a3641}"
													 textClass="span9 m-wrap" radioOrCheckbox="radio"
													 selectUrl="${path}/api/dictionary/select?typeCode=ZB56-2006/CYXZ"/>
							</div>
						</div>
						<div id="a3647Group" class="control-group">
							<label class="control-label">排序</label>
							<div class="controls">
								<input type="text" class="span9 m-wrap" number="true" name="a3647" maxlength="128" id="a3647" value="${vo.a3647}" />
							</div>
						</div>

						<div class="control-group">
							<div class="controls mt10">

								<button id="submitbutAdd" style="margin-left:205px;" type="button" class="btn green mybutton" ><i class='icon-ok'></i> 确定</button>

								<a class="btn" href="javascript:cencal()"><i class="icon-remove-sign"></i> 关闭</a>
							</div>
						</div>
					</form>
				</div>

			</div>

			<%-- END SAMPLE FORM PORTLET--%>
		</div>
	</div>

	<%-- END PAGE CONTENT--%>
</div>

<script type="text/javascript" src="${path }/js/common/loading.js"></script>
<!— 引入确认框模块 —>
<%@ include file="/WEB-INF/jsp/inc/confirmModal.jsp"%>
<script type="text/javascript">
    var qtgxform = new EstValidate("qtgxform");
    $(function(){
        $("#submitbutAdd").on("click",function(){
            var bool = qtgxform.form();
            if(bool){
                $.ajax({
                    url : "${path}/zzb/gbgl/a36/update",
                    type : "post",
                    data : $('#qtgxform').serialize(),
                    dataType : "json",
                    headers: {
                        "OWASP_CSRFTOKEN":"${sessionScope.OWASP_CSRFTOKEN}"
                    },
                    success : function(json){
                        showTip("提示","保存成功!",2000);
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
