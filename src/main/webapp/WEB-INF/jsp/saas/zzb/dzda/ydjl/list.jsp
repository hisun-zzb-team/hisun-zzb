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
   <script type="text/javascript" src="${path}/js/select2.min.js"></script>
    <script type="text/javascript"
            src="${path}/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="${path}/js/jquery.form.js"></script>
    <script type="text/javascript" src="${path}/js/DT_bootstrap.js"></script>
    <script type="text/javascript" src="${path}/js/bootstrap-datepicker.js"></script>
    <script type="text/javascript" src="${path}/js/bootstrap-datepicker.zh-CN.js"></script>
    <script type="text/javascript" src="${path}/js/bootstrap-datetimepicker.js"></script>
    <script type="text/javascript" src="${path}/js/bootstrap-datetimepicker.zh-CN.js"></script>
</head>
<body>
<div id="viewTimeModal" class="modal container hide fade" tabindex="-1" data-width="600">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close"  type="button"></button>
                <h3 class="modal-title" id="title" >
                </h3>
            </div>
            <div class="modal-body" id="viewTimeDiv" style="background-color: #f1f3f6;">
            </div>
        </div>
    </div>
</div>
<div id="viewNeiRongModal" class="modal container hide fade" tabindex="-1" data-width="650">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close"  type="button"></button>
                <h3 class="modal-title" id="addTitle" >
                </h3>
            </div>
            <div class="modal-body" id="viewNeiRongDiv">
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
                    <div class="caption">阅档记录</div>
                    <div class="clearfix fr">

                    </div>
                </div>
            </form>
                <div class="clearfix">
                    <div class="control-group">
                        <div id="query" style="float: left;">
                        <form action="${path }/zzb/dzda/dacyjl/list?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}" method="POST" id="searchForm" name="searchForm">
                          <%--  <input type="hidden" name="OWASP_CSRFTOKEN" value="${sessionScope.OWASP_CSRFTOKEN}"/>--%>
                            <input type="hidden" name="pageNum" value="${pager.pageNum }" id="pageNum">
                            <input type="hidden" name="pageSize" value="${pager.pageSize }" id="pageSize">
                            查阅人：<input type="text" class="m-wrap" name="cyrName" id="cyrName" value="${cyrName}" style="width: 80px;" />
                            档案名称：<input type="text" class="m-wrap" name="a0101" id="a0101" value="${a0101}" style="width: 80px;" />
                            <%--查阅时间：<input type="text" class="m-wrap" name="xmQuery" id="xmQuery" value="${xmQuery}" style="width: 100px;" />
                            到<input type="text" class="m-wrap" name="xmQuery" id="xmQuery" value="${xmQuery}" style="width: 100px;" />--%>
                            <span class="inp_name">查阅时间：</span>
                            <div class="input-append" id="starttimeDiv">
                                <input type="text" class="span12" style="width: 100px;" value='${starttime}' name="starttime" id="starttime" readonly/>
                            </div>
                            <span class="inp_name">到：</span>
                            <div class="input-append" id="endtimeDiv">
                                <input type="text" class="span12" style="width: 100px;" value='${endtime}' name="endtime" id="endtime" readonly/>
                            </div>
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
                            <th width=90>档案名称</th>
                            <th width=90 >查阅人</th>
                            <th  width=150>查阅时间</th>
                            <th width=120>查阅时长</th>
                            <th>查阅内容</th>
                        </TR>
                    </thead>
                    <tbody>
                    <c:forEach items="${pager.datas}" var="vo">
                        <tr style="text-overflow:ellipsis;">
                            <TD width><c:out value="${vo.a0101}"></c:out></TD>
                            <TD width><c:out value="${vo.cyrName}"></c:out></TD>
                            <TD width> <fmt:formatDate value="${vo.cysj}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></TD>
                            <TD width><c:out value="${vo.viewTime}"></c:out>秒 &nbsp;<a href="javascript:scxq('${vo.id}')">【详情】</a> </TD>
                            <TD width>
                            <div style="width: 180px;;z-index:1;padding-bottom:2px;overflow:hidden;white-space:nowrap;text-overflow:ellipsis;">
                                <c:forEach items="${vo.a38LogDetails}" var="vo1">
                                    <c:out value="${vo1.e01Z111}"></c:out>
                                </c:forEach>
                            </div>


                         <a href="javascript:viewNeiRong('${vo.id}')">【详情】</a></TD>
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
    })


    var viewNeiRong = function(id){
        $.ajax({
            url:"${path}/zzb/dzda/dacyjl/ajax/toNrxq",
            type : "post",
            data: {"a38LogId":id},
            headers:{
                OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
            },
            dataType : "html",
            success : function(html){
                $('#viewNeiRongDiv').html(html);

                $('#viewNeiRongModal').modal({
                    keyboard: true
                });
            },
            error : function(){
                showTip("提示","出错了请联系管理员", 1500);
            }
        });
    }

    var scxq = function(id){
        $.ajax({
            url:"${path}/zzb/dzda/dacyjl/ajax/toScxq",
            type : "post",
            data: {"a38LogId":id},
            headers:{
                OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
            },
            dataType : "html",
            success : function(html){
                $('#viewTimeDiv').html(html);
                $('#viewTimeModal').modal({
                    keyboard: true
                });
            },
            error : function(){
                showTip("提示","出错了请联系管理员", 1500);
            }
        });
    }
    function pagehref (pageNum ,pageSize){
        $("#pageNum").val(pageNum);
        $("#pageSize").val(pageSize);
        document.searchForm.submit();
    }

    function searchSubmit(){
        document.searchForm.submit();
    }

    function clearData(){
        $("#pageNum").val('');
        $("#pageSize").val('');
        $("#a0101").val('');
        $("#cyrName").val('');
        $("#starttime").val('');
        $("#endtime").val('');
        document.searchForm.submit();

    }

</script>
</body>
</html>
