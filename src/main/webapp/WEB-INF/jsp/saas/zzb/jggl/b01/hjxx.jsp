<%@ taglib prefix="Select" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jsp/inc/servlet.jsp" %>
<%@include file="/WEB-INF/jsp/inc/taglib.jsp" %>
<%--
  ~ Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
  ~ http://www.hn-hisun.com
  ~ 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
  --%>
<title>增加换届信息</title>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<div class="container-fluid">

    <div class="row-fluid">
        <div class="span12">
            <%-- BEGIN SAMPLE FORM PORTLET 表单主体--%>

            <div class="portlet box grey">
                <div class="portlet-title">
                    <div class="clearfix fr">
                        <a id="sample_editable_1_new" class="btn green" href="javascript:add()">
                            <i class="icon-plus"></i> 添加
                        </a>

                    </div>
                </div>
                <div class="portlet-body">
                    <table class="table table-striped table-bordered table-hover dataTable table-set">
                        <thead>
                        <tr>
                            <th width="90px">届次</th>
                            <th width="60px">换届日期</th>
                            <th width="80px">换届年限</th>
                            <th width="80px">换届原因</th>
                            <th width="80px">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${pager.datas}" var="vo">
                            <tr style="text-overflow:ellipsis;">
                                <td><a href="javascript:edit('${vo.b1000}')"><c:out value="${vo.b1001}"></c:out></a>
                                </td>
                                <td><c:out value="${vo.b1004}"></c:out></td>
                                <td><c:out value="${vo.bHjnx}"></c:out></td>
                                <td><c:out value="${vo.b1007}"></c:out></td>
                                <td>
                                    <a href="javascript:edit('${vo.b1000}')" class="">修改</a>|
                                    <a href="javascript:deleteB10('${vo.b1000}')" class="">删除</a>
                                </td>
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

        </div>
    </div>
</div>
<script type="text/javascript" src="${path }/js/common/DataValidate.js"></script>
<script type="text/javascript">
    function add() {
        $.ajax({
            async: false,
            type: "POST",
            url: "${path}/zzb/jggl/b10/ajax/add",
            dataType: "html",
            headers: {
                "OWASP_CSRFTOKEN": '${sessionScope.OWASP_CSRFTOKEN}'
            },
            data: {
                "b01Id": "${currentId}"
            },
            success: function (html) {
                var view = $("#tab_show");
                view.html(html);
            },
            error: function () {
                myLoading.hide();
                showTip("提示", "出错了,请检查网络!", 2000);
            }
        });
    }
    function edit(id) {
        $.ajax({
            async: false,
            type: "POST",
            url: "${path}/zzb/jggl/b10/ajax/edit",
            dataType: "html",
            headers: {
                "OWASP_CSRFTOKEN": '${sessionScope.OWASP_CSRFTOKEN}'
            },
            data: {
                "b1000": id,
                "b01Id": "${currentId}"
            },
            success: function (html) {
                var view = $("#tab_show");
                view.html(html);
            },
            error: function () {
                myLoading.hide();
                showTip("提示", "出错了,请检查网络!", 2000);
            }
        });
    }
    function deleteB10(id) {
        debugger
        actionByConfirm1('',"${path}/zzb/jggl/b10/delete/"+id,null,function(json){
            if(json.success){
                showTip("提示","操作成功");
                setTimeout(function(){
                    $.ajax({
                        url : "${path }/zzb/jggl/b10/ajax/hjxx",
                        type : "post",
                        data : {"currentId":"${currentId}"},
                        dataType : "html",
                        headers:{
                            OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
                        },
                        success : function(html){
                            var view = $("#tab_show");
                            view.html(html);
                        },
                        error : function(arg1, arg2, arg3){
                            showTip("提示","换届信息加载失败");
                        }
                    });
                },1500);

            }else{
                showTip("提示", json.message, 2000);
            }
        },"删除")
    }
</script>