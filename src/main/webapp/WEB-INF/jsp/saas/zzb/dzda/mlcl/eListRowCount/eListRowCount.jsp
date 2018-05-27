<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
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
    <link rel="stylesheet" type="text/css" href="${path}/css/bootstrap-fileupload.css" />
    <script src="${path}/js/bootstrap-fileupload.js"  type="text/javascript"></script>
</head>
<body>
<div class="container-fluid">

    <div class="row-fluid">
        <div class="span12">
            <%-- BEGIN SAMPLE FORM PORTLET 表单主体--%>

            <div class="portlet box grey">
                <div class="portlet-body form">
                    <!-- BEGIN FORM-->

                    <form action="" class="form-horizontal" id="form1" method="post" enctype="multipart/form-data">
                        <input type="hidden" name="id" id="id" value="${vo.id }">
                        <div class="control-group" id="bigTypeCountGroup">
                            <label class="control-label">大类间空行数：</label>
                            <div class="controls">
                                <input type="text" class="span10 m-wrap" name="bigTypeCount" number="true" maxlength="200" id="bigTypeCount" value="${vo.bigTypeCount}" />
                            </div>
                        </div>
                        <div id="smallTypeCountGroup" class="control-group">
                            <label class="control-label">小类间空行数：</label>
                            <div class="controls">
                                <input size="16" type="text"  class="span10 m-wrap" number="true" value="${vo.smallTypeCount }"
                                       id="smallTypeCount" name="smallTypeCount" >
                            </div>
                        </div>

                        <div class="control-group">
                                <div class="controls mt10" style="margin-left: 300px">
                                    <button class="btn green" type="button" style="padding:7px 20px;" onclick="formSubmit()">确定</button>
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
    function formSubmit(){
        $("#form1").ajaxSubmit({
            type:"POST",
            url:"${path}/zzb/dzda/e01z1/rowCount/save",
            dataType : "json",
            enctype : "multipart/form-data",
            headers:{
                "OWASP_CSRFTOKEN":'${sessionScope.OWASP_CSRFTOKEN}'
            },
            success:function(json){
                showTip("提示","上传成功!",2000);
                $('#setRowCountModal').modal('hide');
                $('#setRowCountDiv').html("");
            },
            error : function(){
                showTip("提示","上传失败!",2000);
            }
        });
    }
</script>
</body>
</html>
