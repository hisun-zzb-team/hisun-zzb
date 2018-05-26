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
    <title>填写回执日期</title>
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
                    <form action="" class="form-horizontal" id="form1" method="post" style="margin: 0 0 0px;">
                        <input type="hidden" name="id" value="${id}" id="id">
                        <div id="hzUserNameGroup" class="control-group">
                            <label class="control-label" style="margin: 0 0 0px;width: 100px"><span class="required">*</span>回执人</label>

                            <div class="controls" style="margin-left: 120px">
                                <input type="text" class="span8 m-wrap" name="hzUserName" maxlength="128" id="hzUserName" required
                                       value="${hzUserName}"/>
                            </div>
                        </div>
                        <div id="hzrqGroup" class="control-group">
                            <label class="control-label" style="margin: 0 0 0px;width: 100px"><span class="required">*</span>回执日期</label>

                            <div class="controls" style="margin-left: 120px">
                                <input type="text" class="span8 m-wrap" name="hzrq" maxlength="128" id="hzrq" required
                                       value="${hzrq}" placeholder="日期格式 例如:20180101" isDate="true" dateformat="yyyymmdd"/>
                            </div>
                        </div>

                        <div class="control-group">
                            <div class="controls mt10" style="margin-left: 120px">
                                <button class="btn green" type="button" style="padding:7px 20px;"
                                        onclick="formSubmit()">确定
                                </button>
                                <button type="button" class="btn btn-default" data-dismiss="modal"><i
                                        class='icon-remove-sign'></i> 关闭
                                </button>
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
<script type="text/javascript">
    var myVld = new EstValidate("form1");
    function formSubmit(){
        var bool = myVld.form();
        if(!bool){
            return;
        }
        //myLoading.show();
        $("#form1").ajaxSubmit({
            url : "${path }/zzb/dzda/dazd/updateHzrq",
            type : "post",
            dataType : "json",
            headers: {
                "OWASP_CSRFTOKEN":"${sessionScope.OWASP_CSRFTOKEN}"
            },
            success : function(data){
                // myLoading.hide();
                if(data.code==1){
                    window.location.href ="${path }/zzb/dzda/dazd/list?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}";
                }else{
                    showTip("提示", "加载档案转递失败", 2000);
                }
            },
            error : function(arg1, arg2, arg3){
                //myLoading.hide();
                showTip("提示","出错了请联系管理员");
            }
        });
    }

    $(function () {
    })
</script>
</body>
</html>
