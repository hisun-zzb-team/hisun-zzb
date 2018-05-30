<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jsp/inc/servlet.jsp" %>
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
    <title>档案阅档申请</title>
    <link rel="stylesheet" type="text/css" href="${path}/css/bootstrap-fileupload.css"/>
    <script src="${path}/js/bootstrap-fileupload.js" type="text/javascript"></script>
</head>
<body>
<div class="container-fluid">

    <div class="row-fluid">
        <div class="span12">
            <%-- BEGIN SAMPLE FORM PORTLET 表单主体--%>
            <div class="portlet box grey">
                <div class="portlet-body form">
                    <!-- BEGIN FORM-->
                    <form action="" class="form-horizontal" id="form2" method="post">
                        <div class="control-group" id="queryNameGroup" style="margin-bottom: 0px;">
                            <div class="controls" style="margin-left: 0px">
                                <label class="control-label"
                                       style="display: inline;width: 70px;line-height: 30px;margin-left: 30px !important;"><span
                                        class="required">*</span>查询名称：</label>
                                <input size="16" type="text" class="span8 m-wrap" value="${vo.queryName}" style="margin-top: 10px"
                                       id="queryName" name="queryName" required maxlength="200">
                            </div>
                        </div>
                        <div class="control-group" id="descriptionGroup" style="margin-bottom: 0px;">
                            <div class="controls" style="margin-left: 0px">
                                <label class="control-label"
                                       style="display: inline;width: 70px;line-height: 30px;margin-left: 30px !important;">
                                    &nbsp;&nbsp;查询描述：</label>
                                        <textarea size="16" type="text" class="span8 m-wrap" rows="3"
                                                  style="resize: none;"
                                                  id="description" name="description" maxlength="200"
                                                  >${vo.description}</textarea>
                            </div>
                        </div>
                        <div class="control-group" id="pxGroup" style="margin-bottom: 0px;">
                            <div class="controls" style="margin-left: 0px">
                                <label class="control-label"
                                       style="display: inline;width: 70px;line-height: 30px;margin-left: 30px !important;">
                                    &nbsp;&nbsp;&nbsp;<span class="required">*</span>顺序号：</label>
                                <input size="16" type="text" class="span8 m-wrap" number="true"
                                       required  value="<c:if test="${add =='add'}">${sort}</c:if><c:if test="${add !='add'}">${vo.px}</c:if>"
                                       id="px" name="px">
                            </div>
                        </div>
                        <div id="queryTypeGroup" class="control-group" style="margin-bottom: 0px;">
                            <div class="controls" style="display: inline; margin-left: 0px">
                            <label class="control-label"
                                   style="display: inline;width: 70px;line-height: 30px;margin-left: 30px !important;">
                                &nbsp;&nbsp;是否常用：</label>
                                <div class="controls" style="display: inline; margin-left: 20px">
                                <label class="radio" >
                                    <input type="radio" name="queryType" value="1" id="query1"
                                           <c:if test="${vo.queryType =='1'}">checked</c:if>/>
                                    是
                                </label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <label class="radio">
                                    <input type="radio" name="queryType" value="0" id="query0"
                                           <c:if test="${vo.queryType =='0' || add =='add'}">checked</c:if>/>
                                    否
                                </label>
                                    </div>
                            </div>
                        </div>
                        <div id="buttenGroup" class="control-group"
                             style="margin-bottom: 0px;margin-right:200px;margin-top: 10px;">
                            <button type="button" class="btn btn-default"
                                    style="float: right;font-weight: bold;" data-dismiss="modal"><i
                                    class='icon-remove-sign'></i> 关闭
                            </button>
                            <a class="btn green"
                               style="float: right;font-weight: bold;margin-right: 10px;"
                               href="javascript:upadateOrSaveCxtj()">
                                <i class="icon-ok"></i>保存
                            </a>
                        </div>
                    </form>
                </div>

            </div>

            <%-- END SAMPLE FORM PORTLET--%>
        </div>
    </div>

    <%-- END PAGE CONTENT--%>
</div>
<script type="text/javascript">
   var myVld = new EstValidate("form2");
    var editQuery= "${editQuery}";
   var editModel = "${editModel}";
    function upadateOrSaveCxtj() {
        var bool = myVld.form();
        if(!bool){
            return;
        }
            var appQueryId = "${appQueryId}";
            var queryName = $("#queryName").val();
            var description = $("#description").val();
            var px = $("#px").val();
            var queryType = $("input[name='queryType']:checked").val();
            $.ajax({
                url: "${path }/zzb/dzda/dacx/save?queryName=" + queryName +
                "&description=" + description +"&queryType=" + queryType+"&px=" + px+"&appQueryId="+appQueryId+"&editQuery="+editQuery+"&editModel="+editModel,
                type: "post",
                data: $("#form1").serialize(),
                dataType: "json",
                headers: {
                    OWASP_CSRFTOKEN: "${sessionScope.OWASP_CSRFTOKEN}"
                },
                success: function (json) {
                    if(editQuery=="editQuery"){
                        window.location.href = "${path}/zzb/dzda/dacx/list?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}";
                    }else {
                        window.location.href = "${path}/zzb/dzda/dacx/bdwdalist?appQueryId="+json.appQueryId+"&OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}";
                    }
                },
                error: function (arg1, arg2, arg3) {
                    showTip("提示", "查询失败");
                }
            });
    }
</script>
</body>
</html>
