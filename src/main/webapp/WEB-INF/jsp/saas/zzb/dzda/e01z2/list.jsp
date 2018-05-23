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
    <title>材料接收</title>
    <style type="text/css">
        form {
            margin: 0 0 0px;
        }
    </style>
</head>
<body>
<div class="container-fluid" id="e01z2Table">
    <%-- 表格开始 --%>
    <div class="portlet-title">
        <div class="clearfix fr">

            <a id="sample_editable_1_new" class="btn green" href="javascript:add()">
                <i class="icon-plus"></i>增加材料接收
            </a>
            <a  class="btn green" href="#">
                材料接收导入
            </a>
            <a  class="btn green" href="javascript:download()">
                材料接收导出
            </a>
        </div>

    </div>
    <div class="clearfix">

    </div>
    <div class="portlet-body" >
        <table class="table table-striped table-bordered table-hover dataTable table-set">
            <thead>

            <TR height=28>
                <th width="30">序号</th>
                <th width="100">来件单位名称</th>
                <th>材料名称</th>
                <th width="60">收件日期</th>
                <th width="60">接收人</th>
                <th width="120">材料类号</th>

                <th width="30">页数</th>
                <th width="60">制成日期</th>
                <th width="30">份数</th>
                <th width="60">处理状态</th>
                <th width="60">受理状态</th>
                <th width="70">操作</th>
            </thead>
            <tbody>
            <c:forEach items="${pager.datas}" var="vo">
                <tr style="text-overflow:ellipsis;">
                    <TD><c:out value="${vo.e01Z214}"></c:out></TD>
                    <TD><c:out value="${vo.e01Z204A}"></c:out></TD>
                    <TD ><c:out value="${vo.e01Z221A}"></c:out></TD>
                    <TD><c:out value="${vo.e01Z201}"></c:out></TD>
                    <TD ><c:out value="${vo.e01Z207}"></c:out></TD>
                    <TD><c:out value="${vo.e01Z211A}"></c:out></TD>

                    <TD><c:out value="${vo.e01Z224}"></c:out></TD>
                    <TD ><c:out value="${vo.e01Z227}"></c:out></TD>
                    <TD ><c:out value="${vo.e01Z234}"></c:out></TD>
                    <TD><c:out value="${vo.e01Z237Content}"></c:out></TD>
                    <TD ><c:out value="${vo.e01Z244Content}"></c:out></TD>
                    <td>
                        <a href="javascript:edite01z2('${vo.id}')" class="">修改</a>|
                        <a href="javascript:deletee01z2('${vo.id}')" class="">删除</a>
                    </td>
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
    <%-- END PAGE CONTENT--%>
    <script type="text/javascript">
        function pagehref (pageNum ,pageSize){
            <%--window.location.href ="${path}/zzb/app/console/gbmc/a01/list?b01Id=${b01Id}&mcid=${mcid}&pageNum="+pageNum+"&pageSize="+pageSize;--%>
            $.ajax({
                async:false,
                type:"POST",
                url:"${path }/zzb/dzda/e01z2/ajax/list",
                dataType : "html",
                headers:{
                    "OWASP_CSRFTOKEN":'${sessionScope.OWASP_CSRFTOKEN}'
                },
                data:{
                    'a38Id':"${a38Id}",
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

        function edite01z2(id){
            $.ajax({
                async:false,
                type:"POST",
                url:"${path}/zzb/dzda/e01z2/ajax/editCljs",
                dataType : "html",
                headers:{
                    "OWASP_CSRFTOKEN":'${sessionScope.OWASP_CSRFTOKEN}'
                },
                data:{
                    'a38Id':"${a38Id}",
                    'id':id
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
        function deletee01z2(id){
            actionByConfirm1('',"${path}/zzb/dzda/e01z2/delete/"+id+"?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}",null,function(json){
                if(json.code == "1"){
                    showTip("提示","删除成功",1500);
                    setTimeout(function(){
                        $.ajax({
                            url : "${path }/zzb/dzda/e01z2/ajax/list",
                            type : "get",
                            data : {"a38Id":"${a38Id}"},
                            dataType : "html",
                            headers:{
                                OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
                            },
                            success : function(html){
                                var view = $("#tab_show");
                                view.html(html);
                            },
                            error : function(arg1, arg2, arg3){
                                showTip("提示","材料接收列表加载失败");
                            }
                        });
                    },1500);
                }else{
                    showTip("提示", json.message, 2000);
                }
            },"删除")
        }
        function add() {
            $.ajax({
                async:false,
                type:"POST",
                url:"${path}/zzb/dzda/e01z2/ajax/addCljs",
                dataType : "html",
                headers:{
                    "OWASP_CSRFTOKEN":'${sessionScope.OWASP_CSRFTOKEN}'
                },
                data:{
                    'a38Id':"${a38Id}"
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

        function download() {
            window.open("${path}/zzb/dzda/e01z2/download/${a38Id}?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}");
        }

    </script>
</body>
</html>
