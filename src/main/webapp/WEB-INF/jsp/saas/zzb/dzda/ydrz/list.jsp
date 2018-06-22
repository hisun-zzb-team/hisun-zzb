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
    <!-- END PAGE LEVEL STYLES -->
    <title>阅档日志</title>
    <style type="text/css">
    </style>

</head>
<body>
<div id="viewTimeModal" class="modal container hide fade" tabindex="-1" data-width="600">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close"  type="button"></button>
                <h3 class="modal-title" id="viewTimeTitle" >
                    查阅时长
                </h3>
            </div>
            <div class="modal-body" id="viewTimeDiv">
            </div>
        </div>
    </div>
</div>

<div id="viewNeiRongModal" class="modal container hide fade" tabindex="-1" data-width="750">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close"  type="button"></button>
                <h3 class="modal-title" id="viewNeiRongTitle" >
                    查阅详情
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
                <div class="portlet-title">
                    <div class="caption">查阅记录</div>
                    <div class="clearfix fr">
                        <a id="sample_editable_1_new" class="btn green" href="javascript:download()">
                            导出
                        </a>
                    </div>
                </div>
                <div class="clearfix">
                    <div class="control-group">
                        <div id="query" style="float: left;">
                        <form action="${path }/zzb/dzda/ydrz/list?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}" method="POST" id="searchForm" name="searchForm" style="margin: 0 0 0px;">
                          <%--  <input type="hidden" name="OWASP_CSRFTOKEN" value="${sessionScope.OWASP_CSRFTOKEN}"/>--%>
                            <input type="hidden" name="pageNum" value="${pager.pageNum }" id="pageNum">
                            <input type="hidden" name="pageSize" value="${pager.pageSize }" id="pageSize">
                            查阅人：<input type="text" class="m-wrap" name="cyrName" id="cyrName" value="${cyrName}" style="width: 80px;" />
                            <%--查阅时间：<input type="text" class="m-wrap" name="xmQuery" id="xmQuery" value="${xmQuery}" style="width: 100px;" />
                            到<input type="text" class="m-wrap" name="xmQuery" id="xmQuery" value="${xmQuery}" style="width: 100px;" />--%>
                           查阅时间：
                                <input type="text" class="span12" style="width: 100px;" value='${starttime}' name="starttime" id="starttime" readonly/>
                            到：
                                <input type="text" class="span12" style="width: 100px;" value='${endtime}' name="endtime" id="endtime" readonly/>
                             查阅档案姓名：<input type="text" class="m-wrap" name="a0101" id="a0101" value="${a0101}" style="width: 80px;" />
                              阅档类型：<select class="select_form" tabindex="-1" name="ydlx" id="ydlx" style="width: 100px; margin-bottom: 0px;" >
                              <option value="" >全部</option>
                              <option value="0" >申请阅档</option>
                              <option value="1" >其他阅档</option>
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
                            <th width=90 >查阅人</th>
                            <th width=90>查阅档案姓名</th>
                            <th  width=150>查阅开始时间</th>
                            <th width=100>查阅总时长</th>
                            <th width=200>查阅情况</th>
                            <th width=90>阅档类型</th>
                        </TR>
                    </thead>
                    <tbody>
                    <c:forEach items="${pager.datas}" var="vo">
                        <tr style="text-overflow:ellipsis;">
                            <TD ><c:out value="${vo.cyrName}"></c:out></TD>
                            <TD ><c:out value="${vo.a0101}"></c:out></TD>
                            <TD > <fmt:formatDate value="${vo.cysj}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></TD>
                            <TD ><a href="javascript:scxq('${vo.id}')"><c:out value="${vo.viewTime}"></c:out>秒</a> </TD>
                            <TD >
                            <div style="width: 480px;z-index:1;padding-bottom:2px;overflow:hidden;white-space:nowrap;text-overflow:ellipsis;float:left">
                                <a href="javascript:viewNeiRong('${vo.id}')"><c:forEach items="${vo.a38LogDetails}" var="vo1">
                                    <c:out value="${vo1.e01Z111}"></c:out>;
                                </c:forEach></a>
                            </div>
                            <TD ><c:if test="${not empty vo.applyE01Z8}">申请查阅</c:if>
                            <c:if test="${empty vo.applyE01Z8}">其他查阅</c:if></TD>
                            </TD>
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
        $("#ydlx option[value='${ydlx}']").attr("selected",
            true);

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

  function download() {
      var a0101=$("#a0101").val();
      var cyrName = $("#cyrName").val();
      var starttime = $("#starttime").val();
      var endtime = $("#endtime").val();

      window.open("${path}/zzb/dzda/dacyjl/download?a0101="+a0101+"&cyrName="+cyrName+"&starttime="+starttime+"&endtime="+endtime+
              "&OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}");
  }

</script>
</body>
</html>
