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
    <title>导入文件错误列表</title>
    <link rel="stylesheet" type="text/css" href="${path}/css/bootstrap-fileupload.css" />
    <script src="${path}/js/bootstrap-fileupload.js"  type="text/javascript"></script>
</head>
<body>
<div class="container-fluid">

    <div class="row-fluid">
        <a class="btn" href="javascript:closeWrongPage()" style="margin-left: 500px"><i class="icon-remove-sign"></i> 关闭</a>
        <div class="portlet-body" >
            <table class="table table-striped table-bordered table-hover dataTable table-set">
                <thead>

                <TR height=28>
                    <th width="150">错误列表</th>
                    <th>错误位置</th>
                    <th width=150>错误原因</th>
                </thead>
                <tbody>
                <c:forEach items="${datas}" var="vo">
                    <tr style="text-overflow:ellipsis;">
                        <TD><c:out value="${vo.wrongExcel}"></c:out></TD>
                        <TD><c:out value="${vo.lines}"></c:out></TD>
                        <TD ><c:out value="${vo.reason}"></c:out></TD>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <%-- END PAGE CONTENT--%>
</div>
<script type="text/javascript">
    function closeWrongPage(){
        $('#wrongModal').modal('hide');
        $('#wrongDiv').html("");
        pagehref("","");
    }
</script>
</body>
</html>
