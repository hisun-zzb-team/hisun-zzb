<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
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
    <title>电子档案系统</title>
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
            <div class="portlet-title">
                <div class="caption">条件查询：</div>

            </div>
            <div class="portlet-body">
                <table class="table table-striped table-bordered table-hover dataTable table-set">
                    <thead>

                    <TR height=28>
                        <th width=120>查询名称</th>
                        <th style="text-align: center">查询说明</th>
                        <th width=70>查询索引</th>
                        <th width=130>使用限制</th>
                        <th width=70  style="text-align: center">查询类型</th>
                        <th width=70  style="text-align: center">操作</th>
                    </thead>
                    <tbody>
                    <c:forEach items="${pager.datas}" var="vo">
                        <tr style="text-overflow:ellipsis;">
                            <TD ><a href="#"><c:out value="${vo.queryName}"></c:out></a> </TD>
                            <TD  style="text-align: center"><c:out value="${vo.description}"></c:out></TD>
                            <TD ><c:out value="${vo.cxsy}"></c:out></TD>
                            <TD><c:out value="${vo.syxz}"></c:out></TD>
                            <TD  style="text-align: center"><c:out value="${vo.queryType}"></c:out></TD>
                            <TD  style="text-align: center">
                            <a href="javascript:editQuery('${vo.id}')" class="">修改</a>|
                            <a href="javascript:deleteQuery('${vo.id}')" class="">删除</a>
                            </TD>
                        </TR>
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
    var deleteQuery = function(id){
        actionByConfirm1('',"${path}/zzb/dzda/dacx/delete/"+id,null,function(json){
            if(json.code == 1){
                showTip("提示","操作成功",1000);
                setTimeout(function(){
                    $.ajax({
                        async:false,
                        type:"POST",
                        url:"${path}/zzb/dzda/dacx/ajax/list",
                        dataType : "html",
                        headers:{
                            "OWASP_CSRFTOKEN":'${sessionScope.OWASP_CSRFTOKEN}'
                        },
                        data:{
                            'pageNum':pageNum,
                            'pageSize':pageSize
                        },
                        success:function(html){
                            var view = $("#tab_show");
                            view.html(html);
                        },
                        error : function(){
                            myLoading.hide();
                            showTip("提示","出错了,请检查网络!",2000);
                        }
                    });

                },1500);

            }else{
                showTip("提示", json.message, 2000);
            }
        },"删除")
    }

    var editQuery = function(id){

    }


    function pagehref (pageNum ,pageSize){
        <%--window.location.href ="${path}/zzb/app/console/gbmc/a01/list?b01Id=${b01Id}&mcid=${mcid}&pageNum="+pageNum+"&pageSize="+pageSize;--%>
        $.ajax({
            async:false,
            type:"POST",
            url:"${path}/zzb/dzda/dacx/ajax/list",
            dataType : "html",
            headers:{
                "OWASP_CSRFTOKEN":'${sessionScope.OWASP_CSRFTOKEN}'
            },
            data:{
                'pageNum':pageNum,
                'pageSize':pageSize
            },
            success:function(html){
                var view = $("#tab_show");
                view.html(html);
            },
            error : function(){
                myLoading.hide();
                showTip("提示","出错了,请检查网络!",2000);
            }
        });

    }
</script>
</body>
</html>
