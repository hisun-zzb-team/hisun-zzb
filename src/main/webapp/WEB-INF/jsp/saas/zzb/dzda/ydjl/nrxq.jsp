<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/inc/servlet.jsp" %>
<%@include file="/WEB-INF/jsp/inc/taglib.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

    <link href="${path}/css/common/common.css" rel="stylesheet" type="text/css"/>
    <!-- BEGIN PAGE LEVEL STYLES -->
    <link rel="stylesheet" href="${path }/css/DT_bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="${path }/css/bootstrap-fileupload.css">

    <link href="${path }/css/style.css" rel="stylesheet" type="text/css">
    <!-- END PAGE LEVEL STYLES -->
    <title>阅档记录</title>
    <style type="text/css">
    </style>
    <link rel="stylesheet" type="text/css"
          href="${path}/css/select2_metro.css" />
    <link href="${path}/css/common/common.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="${path}/css/DT_bootstrap.css" />
    <%--    <script type="text/javascript" src="${path}/js/select2.min.js"></script>
        <script type="text/javascript"
                src="${path}/js/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="${path}/js/jquery.form.js"></script>
        <script type="text/javascript" src="${path}/js/DT_bootstrap.js"></script>
        <script type="text/javascript" src="${path}/js/bootstrap-datepicker.js"></script>
        <script type="text/javascript" src="${path}/js/bootstrap-datepicker.zh-CN.js"></script>
        <script type="text/javascript" src="${path}/js/bootstrap-datetimepicker.js"></script>
        <script type="text/javascript" src="${path}/js/bootstrap-datetimepicker.zh-CN.js"></script>--%>
</head>
<body>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="span12 responsive">
            <%-- 表格开始 --%>
            <form class=""id="importForm" enctype="multipart/form-data">
                <div class="portlet-title">
                    <div class="caption">   "${cyrName}"浏览"'${a0101}'"的阅档详细信息 共"${pager.total}"条</div>
                    <div class="clearfix fr">
                    </div>
                </div>
            </form>
            <div class="portlet-body">
                <table class="table table-striped table-bordered table-hover dataTable table-set">
                    <thead>
                    <TR height=28>
                        <th width=70 >材料名称</th>
                        <th width=70 >材料类别</th>
                        <th width=140>起始时间</th>
                        <th width=140 >结束时间</th>
                        <th  width=80>时长</th>
                    </TR>
                    </thead>
                    <tbody>
                    <c:forEach items="${pager.datas}" var="vo">
                        <tr style="text-overflow:ellipsis;">
                            <TD > <c:out value="${vo.e01Z111}"></c:out></TD>
                            <TD > <c:out value="${vo.e01Z101A}"></c:out></TD>
                            <TD >
                             <c:forEach items="${vo.logDetailViewTimes}" var="vo1">
                                <p> <fmt:formatDate value="${vo1.stareTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></p>
                             </c:forEach>
                            </TD>
                            <TD >
                            <c:forEach items="${vo.logDetailViewTimes}" var="vo2">
                                <p><fmt:formatDate value="${vo2.endTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></p>
                            </c:forEach>
                            </TD>
                            <TD >  <c:out value="${vo.cysj}"></c:out><a href="javascript:xiangxi('${vo.id}')">【详细】</a></TD>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <%-- 表格结束 --%>
    </div>
</div>
<div id="xxviewTimeModal" class="modal container hide fade" tabindex="-1" data-width="450">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close"  type="button"></button>
                <h3 class="modal-title" id="title" >
                </h3>
            </div>
            <div class="modal-body" id="xxviewTimeDiv" style="background-color: #f1f3f6;">

            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    var xiangxi = function(id){
        $.ajax({
            url:"${path}/zzb/dzda/dacyjl/ajax/toNrxqViewTime",
            type : "post",
            data: {"a38LogDetailId":id},
            headers:{
                OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
            },
            dataType : "html",
            success : function(html){
                $('#xxviewTimeDiv').html(html);

                $('#xxviewTimeModal').modal({
                    keyboard: true
                });
            },
            error : function(){
                showTip("提示","出错了请联系管理员", 1500);
            }
        });
    }
</script>
</body>
</html>
