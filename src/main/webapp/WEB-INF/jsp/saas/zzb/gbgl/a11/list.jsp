<%@ taglib prefix="Select" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jsp/inc/servlet.jsp" %>
<%@include file="/WEB-INF/jsp/inc/taglib.jsp" %>
<%--
  ~ Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
  ~ http://www.hn-hisun.com
  ~ 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
  --%>
<title>添加机构</title>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<div class="container-fluid">

    <div class="row-fluid">
        <div class="span12">
            <%-- BEGIN SAMPLE FORM PORTLET 表单主体--%>

            <div class="portlet box grey">
                <div class="portlet-title">
                    <div class="clearfix fr">
                        <a id="addPxqk" class="btn green">
                            <i class="icon-plus"></i> 添加
                        </a>
                    </div>
                </div>
                <div class="portlet-body">
                    <div is="a17ListDiv">
                        <table class="table table-striped table-bordered table-hover dataTable table-set">
                            <thead>
                            <tr>
                                <th width="90px">培训日期</th>
                                <th>培训说明</th>
                                <th width="80px">培训类别</th>
                                <th width="80px">培训学分</th>
                                <th width="80px">完成情况</th>
                                <th width="80px">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${pager.datas}" var="vo">
                                <tr style="text-overflow:ellipsis;">
                                    <td><c:out value="${vo.a1107}"></c:out></td>
                                    <td><c:out value="${vo.aPxsm}"></c:out></td>
                                    <td><c:out value="${vo.a1134A}"></c:out></td>
                                    <td><c:out value="${vo.aPxxf}"></c:out></td>
                                    <td><c:out value="${vo.a1144A}"></c:out></td>
                                    <td>
                                        <a href="javascript:edit('${vo.a1100}')" class="">修改</a>|
                                        <a href="javascript:deleteA11('${vo.a1100}')" class="">删除</a>
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
</div>
<script type="text/javascript" src="${path }/js/common/DataValidate.js"></script>
<script type="text/javascript">
    var a01Id = "${a01Id}";

    $("#addPxqk").click(function () {
        $.ajax({
            async: false,
            type: "POST",
            url: "${path}/zzb/gbgl/a11/ajax/addOrEdit",
            dataType: "html",
            headers: {
                "OWASP_CSRFTOKEN": '${sessionScope.OWASP_CSRFTOKEN}'
            },
            data: {
                "a01Id": a01Id
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
    });

    function edit(id) {
        $.ajax({
            async: false,
            type: "POST",
            url: "${path}/zzb/gbgl/a11/ajax/addOrEdit",
            dataType: "html",
            headers: {
                "OWASP_CSRFTOKEN": '${sessionScope.OWASP_CSRFTOKEN}'
            },
            data: {
                "a1100": id,
                "a01Id": a01Id
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
    function pagehref(pageNum, pageSize) {
        $.ajax({
            async: false,
            type: "POST",
            url: "${path}/zzb/gbgl/a11/ajax/list?a01Id="+a01Id+"&pageNum=" + pageNum + "&pageSize=" + pageSize,
            dataType: "html",
            headers: {
                "OWASP_CSRFTOKEN": '${sessionScope.OWASP_CSRFTOKEN}'
            },
            data:{},
            success: function (html) {
                $("#tab_show").html(html);
            },
            error: function () {
                myLoading.hide();
                showTip("提示", "出错了,请检查网络!", 2000);
            }
        });

    }
    function deleteA11(id) {
        actionByConfirm1('',"${path}/zzb/gbgl/a11/delete/"+id,null,function(json){
            if(json.success){
                showTip("提示","操作成功");
                setTimeout(function(){
                    cencal();
                },1500);

            }else{
                showTip("提示", json.message, 2000);
            }
        },"删除")
    }
</script>