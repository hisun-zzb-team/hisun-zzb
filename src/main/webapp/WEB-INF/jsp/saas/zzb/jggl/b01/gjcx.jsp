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
    <!-- END PAGE LEVEL STYLES -->
    <title>高级查询</title>
    <style type="text/css">
        form {
            margin: 0 0 0px;
        }
    </style>
</head>
<body>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="span12 responsive">
            <form action="${path }/zzb/jggl/b01/ajax/list" method="POST" id="searchForm"
                  name="searchForm">
                <div class="portlet-title">
                    <div class="caption">查询条件</div>
                    <div class="clearfix fr">
                        <div class="btn-group">
                            <a id="chaxun" class="btn green" href="javascript:chaxun()">
                                查询
                            </a>
                        </div>
                        <div class="btn-group">
                            <a class="btn green" href="javascript:cleanData()">
                                清空
                            </a>
                        </div>
                        <div class="btn-group">
                            <a class="btn green" href="javascript:cancel()">
                                取消
                            </a>
                        </div>
                    </div>
                </div>

            </form>
        </div>
    </div>
</div>

<%-- END PAGE CONTENT--%>
<script type="text/javascript" src="${path }/js/common/loading.js"></script>
<script type="text/javascript">
    function chaxun() {

    }

    function cleanData() {

    }

    function cancel() {
        $('#queryModelModal').modal('hide');
        window.location.href = "${path}/zzb/jggl/b01/index?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}";
    }
</script>
</body>
</html>
