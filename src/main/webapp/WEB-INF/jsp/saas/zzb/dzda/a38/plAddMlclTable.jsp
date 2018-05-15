<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
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
    <title></title>
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
            <div class="portlet-body">
                <table class="table table-striped table-bordered table-hover dataTable table-set">
                    <thead>
                    <tr>
                        <th style="width: 20px;"><input type="checkbox" id="allCheck" onchange="allCheckChange()"/></th>
                        <th width=70>姓名</th>
                        <th width=40>性别</th>
                        <th width=70>出生年月</th>
                        <th width=70>单位职务</th>
                        <th width=70>干部状态</th>
                        <th width=70>现职级时间</th>
                    </thead>
                    <tbody>
                    <c:forEach items="${pager.datas}" var="vo">
                        <tr style="text-overflow:ellipsis;">
                            <td><input type="checkbox" name="a38Check" onchange="checkChange(this)" value="${vo.id }"></td>
                            <td>${vo.a0101}</td>
                            <td>${vo.a0104Content}</td>
                            <td>${vo.a0107} </td>
                            <td>${vo.a0157}</td>
                            <td>${vo.gbztContent}</td>
                            <td st>${vo.xzjsj}</td>
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

<script type="text/javascript">
    function unloadFile() {
        document.getElementById("btn-unloadFile").click();
    }
    function add() {
        var a38Id = $("#a38Id").val();
        var eCatalogTypeTreeId = $("#eCatalogTypeTreeId").val();
        var eCatalogTypeTreeCode = $("#eCatalogTypeTreeCode").val();
        var eCatalogTypeTreeName = $("#eCatalogTypeTreeName").val();
        var eCatalogTypeTreeParentId = $("#eCatalogTypeTreeParentId").val();
        $.ajax({
            async: false,
            type: "POST",
            url: "${path}/zzb/dzda/e01z1/ajax/addMlcl",
            dataType: "html",
            headers: {
                "OWASP_CSRFTOKEN": '${sessionScope.OWASP_CSRFTOKEN}'
            },
            data: {
                "a38Id": a38Id,
                "eCatalogTypeTreeId": eCatalogTypeTreeId,
                "eCatalogTypeTreeCode": eCatalogTypeTreeCode,
                "eCatalogTypeTreeName": eCatalogTypeTreeName,
                "eCatalogTypeTreeParentId": eCatalogTypeTreeParentId
            },
            success: function (html) {
                $("#rightList").html(html);
                $("#treeId").val();
            },
            error: function () {
                myLoading.hide();
                showTip("提示", "出错了,请检查网络!", 2000);
            }
        });
    }
    var uploadImg = function () {
        $.ajax({
            url: "${path}/zzb/app/console/daDemo/ajax/uploadImg",
            type: "post",
            data: {},
            headers: {
                OWASP_CSRFTOKEN: "${sessionScope.OWASP_CSRFTOKEN}"
            },
            dataType: "html",
            success: function (html) {
                $('#uploadImgDiv').html(html);

                $('#uploadImgModal').modal({
                    keyboard: true
                });
            },
            error: function () {
                showTip("提示", "出错了请联系管理员", 1500);
            }
        });
    }
    function edit(id) {
        var a38Id = $("#a38Id").val();
        var eCatalogTypeTreeId = $("#eCatalogTypeTreeId").val();
        var eCatalogTypeTreeCode = $("#eCatalogTypeTreeCode").val();
        var eCatalogTypeTreeName = $("#eCatalogTypeTreeName").val();
        var eCatalogTypeTreeParentId = $("#eCatalogTypeTreeParentId").val();
        $.ajax({
            async: false,
            type: "POST",
            url: "${path}/zzb/dzda/e01z1/ajax/editMlcl",
            dataType: "html",
            headers: {
                "OWASP_CSRFTOKEN": '${sessionScope.OWASP_CSRFTOKEN}'
            },
            data: {
                "id": id,
                "a38Id": a38Id,
                "eCatalogTypeTreeId": eCatalogTypeTreeId,
                "eCatalogTypeTreeCode": eCatalogTypeTreeCode,
                "eCatalogTypeTreeName": eCatalogTypeTreeName,
                "eCatalogTypeTreeParentId": eCatalogTypeTreeParentId
            },
            success: function (html) {
                $("#rightList").html(html);
                $("#treeId").val(eCatalogTypeTreeId);
            },
            error: function () {
                myLoading.hide();
                showTip("提示", "出错了,请检查网络!", 2000);
            }
        });
    }
    var viewImageMain = function (e01z1Id,e01Z101B) {
        var divHeight = $(window).height() -60;
        var divWidth = $(window).width() - 100;
        $('#viewImgModal').attr("data-height", divHeight);
        $('#viewImgModal').attr("data-width", divWidth);

        var myDirName = $("#myDirName").val();
        $.ajax({
            url: "${path}/zzb/dzda/mlcl/images/ajax/viewMain/"+$("#a38Id").val(),
            type: "post",
            data: {
                "a0101":"${a0101}",
                "archiveId":e01Z101B,
                "e01z1Id":e01z1Id,
                "myDirName":myDirName
            },
            headers: {
                OWASP_CSRFTOKEN: "${sessionScope.OWASP_CSRFTOKEN}"
            },
            dataType: "html",
            success: function (html) {
                $('#viewImgDiv').html(html);
                $('#viewImgModal').modal({backdrop: 'static', keyboard: false});
            },
            error: function () {
                showTip("提示", "出错了请联系管理员", 1500);
            }
        });
    }
    function hiddenViewImgModal(){//隐藏图片查看时 删除临时的解密图片
        $('#viewImgModal').modal('hide');
        $('#viewImgDiv').html("");
        <%--var a38Id = $("#a38Id").val();--%>
        <%--var myDirName = $("#myDirName").val();--%>
        <%--$.ajax({--%>
        <%--url: "${path}/zzb/dzda/mlcl/images/delete/jmImages",--%>
        <%--type: "post",--%>
        <%--data: {--%>
        <%--"a38Id":a38Id,--%>
        <%--"myDirName":myDirName--%>
        <%--},--%>
        <%--headers: {--%>
        <%--OWASP_CSRFTOKEN: "${sessionScope.OWASP_CSRFTOKEN}"--%>
        <%--},--%>
        <%--dataType: "json",--%>
        <%--success: function (data) {--%>
        <%--if (data.success == "true" || data.success == true) {--%>

        <%--}else{--%>
        <%--showTip("提示", "删除解密图片失败，请联系管理员!", 1300);--%>
        <%--}--%>
        <%--},--%>
        <%--error: function () {--%>
        <%--showTip("提示", "出错了请联系管理员!", 1300);--%>

        <%--}--%>
        <%--});--%>
    }

    function fileDown(type) {
        window.open("${path }/zzb/app/console/daDemo/ajax/down?type=" + type);
    }

    function del(id, voname) {
        var a38Id = $("#a38Id").val();
        var eCatalogTypeTreeId = $("#eCatalogTypeTreeId").val();
        var eCatalogTypeTreeCode = $("#eCatalogTypeTreeCode").val();
        var eCatalogTypeTreeName = $("#eCatalogTypeTreeName").val();
        var eCatalogTypeTreeParentId = $("#eCatalogTypeTreeParentId").val();
        actionByConfirm1(voname, "${path}/zzb/dzda/e01z1/delete/" + id, {}, function (data, status) {
            if (data.success == true) {
                showTip("提示", "成功删除！", 2000);
                refreshTree();
                $.ajax({
                    url: "${path}/zzb/dzda/e01z1/ajax/mlxxList",
                    type: 'POST',
                    dataType: "html",
                    data: {
                        "eCatalogTypeTreeId": eCatalogTypeTreeId,
                        "eCatalogTypeTreeCode": eCatalogTypeTreeCode,
                        "eCatalogTypeTreeParentId": eCatalogTypeTreeParentId,
                        "eCatalogTypeTreeName": eCatalogTypeTreeName,
                        "a38Id": a38Id
                    },
                    headers: {
                        "OWASP_CSRFTOKEN": "${sessionScope.OWASP_CSRFTOKEN}"
                    },
                    success: function (html) {
                        $("#rightList").html(html);
                    },
                    error: function () {
                        showTip("提示", "删除失败!", 2000);
                    }
                });
            } else {
                showTip("提示", data.msg, 2000);
            }
        });
    }
    $("#jztpButton").click(function(){
        var divHeight = $(window).height()-100;
        var divWidth = $(window).width()-20;
        $('#jztpModal').attr("data-width",divWidth);
        $('#jztpModal').attr("data-height",divHeight);
        $.ajax({
            url: "${path}/zzb/dzda/mlcl/jztp/ajax/index/${a38Id}",
            type: "post",
            data: {},
            headers: {
                OWASP_CSRFTOKEN: "${sessionScope.OWASP_CSRFTOKEN}"
            },
            dataType: "html",
            success: function (html) {
                $('#jztpPage').html(html);
                $('#jztpModal').modal({
                    keyboard: true
                });
            },
            error: function () {
                showTip("提示", "出错了请联系管理员", 1500);
            }
        });
    });
</script>
</body>
</html>
