<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <!-- END PAGE LEVEL STYLES -->
    <title>阅档记录</title>
<body>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="span12">
            <%-- 表格开始 --%>

            <div class="portlet-body" style="max-height: 550px;overflow: auto;margin: 0px;">
                <table class="table table-striped table-bordered table-hover dataTable table-set">
                    <thead>
                    <TR height=28>
                        <th width=140>起始时间</th>
                        <th width=140 >结束时间</th>
                        <th  width=80>时长</th>
                    </TR>
                    </thead>
                    <tbody>
                    <c:forEach items="${resultList}" var="vo">
                        <tr style="text-overflow:ellipsis;">
                            <TD width="10%"><fmt:formatDate value="${vo.stareTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></TD>
                            <TD width="10%"><fmt:formatDate value="${vo.endTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></TD>
                            <TD width="10%"> <c:out value="${vo.viewTime}"></c:out>秒</TD>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <%-- 表格结束 --%>
    </div>
</div>
<script type="text/javascript">
    (function(){
        $("#viewTimeTitle").text("${cyrName}浏览'${a0101}'的阅档详细时间 共${total}条");
    })();

</script>
</body>
</html>
