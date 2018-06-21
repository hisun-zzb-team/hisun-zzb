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
    <title>借阅管理</title>
    <style type="text/css">
        form {
            margin: 0 0 0px;
        }
    </style>
</head>
<body>

<div id="addModal" class="modal container hide fade" tabindex="-1" data-width="600">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close"  type="button"></button>
                <h3 class="modal-title" id="addTitle" >
                    借阅申请
                </h3>
            </div>
            <div class="modal-body" id="addDiv">
            </div>
        </div>
    </div>
</div>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="span12 responsive">
            <%-- 表格开始 --%>
            <form class=""id="importForm" enctype="multipart/form-data">
                <div class="portlet-title">
                    <div class="caption">借阅管理列表</div>
                    <div class="clearfix fr">
                    </div>

                </div>
            </form>
            <div class="clearfix">
                <div class="control-group">
                    <div id="query" style="float: left;">
                        <form action="/zzb/dzda/jygl/list" method="POST" id="searchForm" name="searchForm">
                            <input type="hidden" name="OWASP_CSRFTOKEN" value="${sessionScope.OWASP_CSRFTOKEN}"/>
                            <input type="hidden" name="pageNum" value="${pager.pageNum }" id="pageNum">
                            <input type="hidden" name="pageSize" value="${pager.pageSize }" id="pageSize">
                            借阅档案名称：<input type="text" class="m-wrap" name="e01Z9Damc" id="e01Z9Damc" value="${e01Z9Damc}" style="width: 80px;" />
                            借阅人<input type="text" class="m-wrap" name="e01Z907" id="e01Z907" value="${e01Z907}" style="width: 80px;" />
                            借阅状态：<select class="select_form" tabindex="-1" name="e01Z9Jyzt" id="e01Z9Jyzt" style="width: 100px; margin-bottom: 0px;" >
                            <option value="x" <c:if test="${e01Z9Jyzt == 'x'}">selected</c:if>>全部</option>
                                    <option value="0" <c:if test="${e01Z9Jyzt == '0'}">selected</c:if>>未审核</option>
                                    <option value="1" <c:if test="${e01Z9Jyzt == '1'}">selected</c:if>>已审核</option>
                                    <option value="2" <c:if test="${e01Z9Jyzt == '2'}">selected</c:if>>已归还</option>
                                    <option value="3" <c:if test="${e01Z9Jyzt == '3'}">selected</c:if>>已拒绝</option>
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
                        <th width=60>归还经办人</th>
                        <th width="100">归还时间</th>
                        <th width="40">操作</th>
                    </thead>
                    <tbody>
                        <c:forEach items="${pager.datas}" var="vo">
                        <tr style="text-overflow:ellipsis;">
                            <TD width="10%">
                                <c:out value="${vo.e01Z9Damc}"></c:out>
                            </TD>
                            <TD width="10%"><c:out value="${vo.e01Z907}"></c:out> </TD>
                            <TD width="10%"><c:out value="${vo.e01Z901}"></c:out></TD>
                            <TD width="10%"><c:out value="${vo.e01Z904A}"></c:out> </TD>
                            <TD width="10%"><c:out value="${vo.e01Z917}"></c:out></TD>
                            <TD width="10%"><c:out value="${vo.e01Z931}"></c:out></TD>
                            <TD width="10%"><c:out value="${vo.e01Z9Shsj}"></c:out></TD>
                            <TD width="10%">
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
                            <TD width="10%"><c:out value="${vo.e01Z934}"></c:out></TD>
                            <TD width="10%"><c:out value="${vo.e01Z927}"></c:out></TD>
                            <TD width="10%">
                                <c:choose>
                                    <c:when test="${vo.e01Z9Jyzt == 0}">
                                        <a href="javascript:edit('${vo.id}','1')">审核</a>
                                    </c:when>
                                    <c:when test="${vo.e01Z9Jyzt == 1}">
                                        <a href="javascript:gh('${vo.id}','2')">归还</a>
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
    var e01Z9Jyzt = "${e01Z9Jyzt}";
    $(function(){
        $("#auditingState option[value='${auditingState}']").attr("selected",
                true);
    })

    function pagehref (pageNum ,pageSize){
        $("#pageNum").val(pageNum);
        $("#pageSize").val(pageSize);
        document.searchForm.submit();
    }

    var edit = function(id,flag){
        window.location.href ="${path }/zzb/dzda/jygl/edit?e01Z9Jyzt="+e01Z9Jyzt+"&id="+id+"&OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}";
    }
    var gh = function(id,flag){
        $.ajax({
            url : "${path}/zzb/dzda/jygl/gh",
            type : "post",
            data : {"e01Z9Jyzt":"2","id":id},
            dataType : "json",
            headers: {
                "OWASP_CSRFTOKEN":"${sessionScope.OWASP_CSRFTOKEN}"
            },
            success : function(json){
                showTip("提示","保存成功!",2000);
                window.location.href ="${path }/zzb/dzda/jygl/list?e01Z9Jyzt="+e01Z9Jyzt+"&OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}";
            },
            error : function(arg1, arg2, arg3){
                showTip("提示","出错了请联系管理员",2000);
            }
        });
    }

    function searchSubmit(){
        document.searchForm.submit();
    }

    function clearData(){
        $("#e01Z9Damc").val('');
        $("#e01Z907").val('');
        $("#e01Z9Jyzt").val('x');
    }

</script>
</body>
</html>
