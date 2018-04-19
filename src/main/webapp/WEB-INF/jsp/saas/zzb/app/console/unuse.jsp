<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/inc/servlet.jsp" %>
<%@include file="/WEB-INF/jsp/inc/taglib.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>建设中...</title>
    <link href="${pageContext.request.contextPath}/css/common/common.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/datetimepicker.css" rel="stylesheet" type="text/css"/>
    <c:set var="path" value="${pageContext.request.contextPath}"></c:set>
    <!-- BEGIN PAGE LEVEL STYLES -->
    <link rel="stylesheet" href="${path }/css/DT_bootstrap.css" />
    <%-- <link href="${pageContext.request.contextPath}/css/error.css" rel="stylesheet" type="text/css"/> --%>
    <link href="${pageContext.request.contextPath}/css/error.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css">
    <script src="${pageContext.request.contextPath}/js/jquery-1.10.1.min.js" type="text/javascript"></script>

    <!-- END PAGE LEVEL STYLES -->
</head>
<body class="page-500-full-page">

<div class="row-fluid">

    <div class="span12 page-500">

        <div class=" number">

            <div style="text-align:center; margin-top:70px;"><img src="${pageContext.request.contextPath}/images/templateImage/jsym.jpg"></div>


        </div>

    </div>

</div>
</body>

<!-- BEGIN PAGE LEVEL PLUGINS -->

<script type="text/javascript" src="<%=path%>/js/select2.min.js"></script>

<script type="text/javascript"
        src="<%=path%>/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.form.js"></script>
<script type="text/javascript" src="<%=path%>/js/DT_bootstrap.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap-datepicker.zh-CN.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript">
    jQuery(document).ready(function() {

        App.init();//必须，不然导航栏及其菜单无法折叠

        $("#submitBtn").on("click",function(){
            //$('#getLogForm')[0].reset();
            $("#starttime").val("");
            $("#endtime").val("");
            $("#userName").val("");
        });

        $('#starttime').datepicker({
            format : 'yyyy-mm-dd',
            weekStart : 1,
            autoclose : true,
            todayBtn : 'linked',
            language : 'zh-CN'
        });
        $('#endtime').datepicker({
            format : 'yyyy-mm-dd',
            weekStart : 1,
            autoclose : true,
            todayBtn : 'linked',
            language : 'zh-CN'
        });
    });

    function pagehref(pageNum, pageSize) {
        var url = location.href;
        var length = url.indexOf("?");
        var starttime = $("#starttime").val();
        var endtime = $("#endtime").val();
        var userName = $("#userName").val();
        userName=userName?userName:"";
        var type = $("#type").val();
        length = length > 0 ? length : url.length;
        window.location.href = url.substring(0, length) + "?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}&pageNum="+pageNum+"&pageSize="+pageSize+"&starttime="+starttime+"&endtime="+endtime+"&userName="+userName+"&type="+type;
    }
</script>
</body>
</html>