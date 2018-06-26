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

    <link href="${path}/css/common/common.css" rel="stylesheet" type="text/css"/>
    <!-- BEGIN PAGE LEVEL STYLES -->
    <link rel="stylesheet" href="${path }/css/DT_bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="${path }/css/bootstrap-fileupload.css">
    <style type="text/css">
        ul.ztree{margin-bottom: 10px; background: #f1f3f6 !important;}
        .portlet.box.grey.mainleft{background-color: #f1f3f6;overflow: hidden; padding: 0px !important; margin-bottom: 0px;}
        .main_left{float:left; width:220px;  margin-right:10px; background-color: #f1f3f6; }
        .main_right{display: table-cell; width:2000px;}
    </style>
    <!-- END PAGE LEVEL STYLES -->
    <title>借阅申请</title>
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
            <%-- 表格开始 --%>
            <form class=""id="importForm" enctype="multipart/form-data">
                <div class="portlet-title">
                    <div class="caption">借阅申请列表</div>
                    <div class="clearfix fr">
                        <a id="sample_editable_1_new" class="btn green" href="javascript:add()">
                            借阅申请
                        </a>
                    </div>

                </div>
            </form>
            <div class="clearfix">
                <div class="control-group">
                    <div id="query" style="float: left;">
                        <form action="/zzb/dzda/jysq/list" method="POST" id="searchForm" name="searchForm">
                            <input type="hidden" name="OWASP_CSRFTOKEN" value="${sessionScope.OWASP_CSRFTOKEN}"/>
                            <input type="hidden" name="pageNum" value="${pager.pageNum }" id="pageNum">
                            <input type="hidden" name="pageSize" value="${pager.pageSize }" id="pageSize">
                            借阅档案名称：<input type="text" class="m-wrap" name="e01Z9Damc" id="e01Z9Damc" value="${e01Z9Damc}" style="width: 80px;" />
                            借阅人<input type="text" class="m-wrap" name="e01Z907" id="e01Z907" value="${e01Z907}" style="width: 80px;" />
                            借阅状态：<select class="select_form" tabindex="-1" name="e01Z9Jyzt" id="e01Z9Jyzt" style="width: 100px; margin-bottom: 0px;" >
                                    <option value="" <c:if test="${e01Z9Jyzt == ''}">selected</c:if>>全部</option>
                                    <option value="0" <c:if test="${e01Z9Jyzt == '0'}">selected</c:if>>未审核</option>
                                    <option value="1" <c:if test="${e01Z9Jyzt == '1'}">selected</c:if>>已审核</option>
                                    <option value="2" <c:if test="${e01Z9Jyzt == '2'}">selected</c:if>>已归还</option>
                                    <option value="3" <c:if test="${e01Z9Jyzt == '3'}">selected</c:if>>已拒绝</option>
                            </select>
                            是否逾期：<select class="select_form" tabindex="-1" name="e01z9Yh" id="e01z9Yh" style="width: 100px; margin-bottom: 0px;" >
                            <option value="" <c:if test="${e01z9Yh == ''}">selected</c:if>></option>
                            <option value="1" <c:if test="${e01z9Yh == '1'}">selected</c:if>>是</option>
                            <option value="0" <c:if test="${e01z9Yh == '0'}">selected</c:if>>否</option>
                            </select>
                            <button type="button" class="btn Short_but" onclick="searchSubmit()">查询</button>
                            <button type="button" class="btn Short_but" onclick="clearData()">清空</button>
                        </form>
                    </div>
                </div>

            </div>
            <div class="portlet-body">
                <table class="table table-striped table-bordered table-hover dataTable table-set">
                    <thead>

                    <TR height=28>
                        <th width=100>借阅档案名称</th>
                        <th width=100>借阅人</th>
                        <th width=100>借阅日期</th>
                        <th >借阅单位名称</th>
                        <th width=50>批准人</th>
                        <th width=60>借阅经办人</th>
                        <th width="100">借阅审核时间</th>
                        <th width="120">借阅状态</th>
                        <th width="100">应还时间</th>
                        <th width=60>归还经办人</th>
                        <th width="100">归还时间</th>
                        <th width="40">操作</th>
                    </thead>
                    <tbody>
                        <c:forEach items="${pager.datas}" var="vo">
                            <TD width="10%"
                                <c:if test="${vo.e01z9Yh eq 1}">style="color: #FF0000"</c:if>>
                                <c:out value="${vo.e01Z9Damc}"></c:out>
                            </TD>
                            <TD width="10%"
                                <c:if test="${vo.e01z9Yh eq 1}">style="color: #FF0000"</c:if>>
                                <c:out value="${vo.e01Z907}"></c:out> </TD>
                            <TD width="10%"
                                <c:if test="${vo.e01z9Yh eq 1}">style="color: #FF0000"</c:if>>
                                <c:out value="${vo.e01Z901}"></c:out></TD>
                            <TD width="10%"
                                <c:if test="${vo.e01z9Yh eq 1}">style="color: #FF0000"</c:if>>
                                <c:out value="${vo.e01Z904A}"></c:out> </TD>
                            <TD width="10%"
                                <c:if test="${vo.e01z9Yh eq 1}">style="color: #FF0000"</c:if>>
                                <c:out value="${vo.e01Z917}"></c:out></TD>
                            <TD width="10%"
                                <c:if test="${vo.e01z9Yh eq 1}">style="color: #FF0000"</c:if>>
                                <c:out value="${vo.e01Z931}"></c:out></TD>
                            <TD width="10%"
                                <c:if test="${vo.e01z9Yh eq 1}">style="color: #FF0000"</c:if>>
                                <c:out value="${vo.e01Z9Shsj}"></c:out></TD>
                            <TD width="10%"
                                <c:if test="${vo.e01z9Yh eq 1}">style="color: #FF0000"</c:if>>
                                <c:choose>
                                    <c:when test="${vo.e01Z9Jyzt == 0}">
                                        未审核
                                    </c:when>
                                    <c:when test="${vo.e01Z9Jyzt == 1}">
                                        已审核
                                    </c:when>
                                    <c:when test="${vo.e01Z9Jyzt == 2}">
                                        已归还
                                    </c:when>
                                    <c:when test="${vo.e01Z9Jyzt == 3}">
                                        已拒绝
                                    </c:when>
                                </c:choose></TD>
                            <TD width="10%"
                                <c:if test="${vo.e01z9Yh eq 1}">style="color: #FF0000"</c:if>>
                                <c:out value="${vo.e01z9Yhsj}"></c:out></TD>
                            <TD width="10%"
                                <c:if test="${vo.e01z9Yh eq 1}">style="color: #FF0000"</c:if>>
                                <c:out value="${vo.e01Z934}"></c:out></TD>
                            <TD width="10%"
                                <c:if test="${vo.e01z9Yh eq 1}">style="color: #FF0000"</c:if>>
                                <c:out value="${vo.e01Z927}"></c:out></TD>
                            <TD width="10%"
                                <c:if test="${vo.e01z9Yh eq 1}">style="color: #FF0000"</c:if>>
                                <c:choose>
                                    <c:when test="${vo.e01Z9Jyzt == 0}">
                                        <a href="javascript:edit('${vo.id}')">修改</a>
                                    </c:when>
                                    <c:when test="${vo.e01Z9Jyzt == 2}">
                                        <a href="javascript:del('${vo.id}','${vo.e01Z9Damc}')">删除</a>
                                    </c:when>
                                    <c:when test="${vo.e01Z9Jyzt == 3}">
                                        <a href="javascript:del('${vo.id}','${vo.e01Z9Damc}')">删除</a>
                                    </c:when>
                                 </c:choose></TD>
                        </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <jsp:include page="/WEB-INF/jsp/common/page.jsp">
                    <jsp:param value="${pager.total }" name="total"/>
                    <jsp:param value="${pager.pageCount }" name="endPage"/>
                    <jsp:param value="${pager.pageSize }" name="pageSize"/>
                    <jsp:param value="${pager.pageNum }" name="page"/>
                </jsp:include>
            </div>
        </div>
        <%-- 表格结束 --%>
    </div>
</div>
<%-- END PAGE CONTENT--%>
</div>

<script type="text/javascript">
    (function(){
        App.init();

        $("#btn-browseTemplate").bind("change",function(evt){
            if($(this).val()){
                ajaxSubmit();
            }
            $(this).val('');
        });

    })();
    $(function(){
        $("#auditingState option[value='${auditingState}']").attr("selected",
                true);
    })

    function pagehref (pageNum ,pageSize){
        $("#pageNum").val(pageNum);
        $("#pageSize").val(pageSize);
        document.searchForm.submit();
    }

    var add = function(){
        window.location.href ="${path }/zzb/dzda/jysq/add?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}";
    }

    var edit = function(id){
        window.location.href ="${path }/zzb/dzda/jysq/edit?id="+id+"&OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}";
    }

    function searchSubmit(){
        document.searchForm.submit();
    }

    function clearData(){
        $("#e01Z9Damc").val('');
        $("#e01Z907").val('');
        $("#e01Z9Jyzt").val('');
        $("#e01Z9Yh").val('');
    }

    function del(id, voname) {
        actionByConfirm1(voname, "${path}/zzb/dzda/jysq/del/" + id+"?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}", {}, function (data, status) {
            if (data.success == true) {
                document.searchForm.submit();
            } else {
                showTip("提示", data.msg, 2000);
            }
        });
    }

</script>
</body>
</html>
