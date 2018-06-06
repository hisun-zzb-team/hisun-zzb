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
    <title>${b0101} 机构列表</title>
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
            <form class="" id="importForm" enctype="multipart/form-data">
                <input type="hidden" name="queryId" value="${queryId}"/>
                <div class="portlet-title">
                    <div class="caption">${b0101}</div>
                    <div class="clearfix fr">
                        <div class="btn-group" style="padding-bottom: 0px">
                            <a class="btn green dropdown-toggle" data-toggle="dropdown" href="#">
                                添加<i class="icon-angle-down"></i>
                            </a>
                            <ul class="dropdown-menu">
                                <%--<li >
                                <a onclick="fileDownManage('allDa')">整本档案下载(含图片)</a>
                                </li>--%>
                                <li>
                                    <a onclick="addfrjg()">法人机构</a>
                                </li>
                                <li>
                                    <a href="javascript:nsjg()">内设机构</a>
                                </li>
                                <li>
                                    <a onclick="fileDownManage('dangantupianxiazai')">机构分组</a>
                                </li>
                            </ul>
                        </div>

                    </div>

                </div>
            </form>
            <div class="clearfix">
                <div class="control-group">
                    <div id="query" style="float: left;">
                        <form action="${path }/zzb/app/console/bset/ajax/list" method="POST" id="searchForm"
                              name="searchForm">
                            <input type="hidden" id="queryId" name="queryId" value="${queryId}"/>
                            <input type="hidden" name="OWASP_CSRFTOKEN" value="${sessionScope.OWASP_CSRFTOKEN}"/>
                            <input type="hidden" name="pageNum" value="${pager.pageNum }" id="pageNum">
                            <input type="hidden" name="pageSize" value="${pager.pageSize }" id="pageSize">
                            <div style=" float:left;margin-top:4px">
                                机构名称：<input type="text" class="span2 m-wrap" name="b0101Query" id="b0101Query"
                                            value="${b0101Query}" style="width: 100px;"/>
                                &nbsp; &nbsp; &nbsp; &nbsp;所属机构：
                                <Tree:tree id="bSjjg" valueName="bSjjgContentQuery" selectClass="span2 m-wrap"
                                           treeUrl="${path}/api/b01/dtjz/tree"
                                           token="${sessionScope.OWASP_CSRFTOKEN}" dtjz="true"
                                           submitType="get" dataType="json" isSearch="true"
                                           checkedByTitle="true" isSelectTree="true" defaultkeys="${bSjjg}"
                                           defaultvalues="${bSjjg}"/>
                                机构级别：<SelectTag:SelectTag id="b0127" needNullValue="true" valueName="b0127Content"
                                                          defaultkeys="" token="${sessionScope.OWASP_CSRFTOKEN}"
                                                          defaultvalues=""
                                                          textClass="span2 m-wrap" radioOrCheckbox="radio"
                                                          selectUrl="${path}/api/dictionary/select?typeCode=ZB03-1994/DWJB"/>
                                <%--
                                  <input type="text" class="m-wrap" name="b0127" id="b0127"
                                              value="${b0101Query}" style="width: 100px;"/>--%>
                                <button type="button" class="btn Short_but" onclick="searchSubmit()">查询</button>
                            </div>
                            <div>
                                机构性质：<Tree:tree id="b0131Query" valueName="b0131ContentQuery" selectClass="span2 m-wrap"
                                                treeUrl="${path}/api/dictionary/tree?typeCode=ZB04-2006/DWXZ"
                                                token="${sessionScope.OWASP_CSRFTOKEN}"
                                                submitType="get" dataType="json" isSearch="false"
                                                checkedByTitle="true" isSelectTree="true" defaultkeys="${b0131Query}"
                                                defaultvalues="${b0131Query}"/>
                                机构管理类别：<SelectTag:SelectTag id="bGllbB" needNullValue="true" valueName="bGllbBContent"
                                                            defaultkeys="" token="${sessionScope.OWASP_CSRFTOKEN}"
                                                            defaultvalues=""
                                                            textClass="span2 m-wrap" radioOrCheckbox="radio"
                                                            selectUrl="${path}/api/dictionary/select?typeCode=2018-JGGLLB"/>
                                机构状态：<Tree:tree id="bDwztBQuery" valueName="bDwztBContentQuery" selectClass="span2 m-wrap"
                                                treeUrl="${path}/api/dictionary/tree?typeCode=2018-JGZT"
                                                token="${sessionScope.OWASP_CSRFTOKEN}"
                                                submitType="get" dataType="json" isSearch="false"
                                                checkedByTitle="true" isSelectTree="true" defaultkeys="${bDwztBQuery}"
                                                defaultvalues="${bDwztBQuery}"/>

                                <button type="button" class="btn Short_but" onclick="clearData()">清空</button>
                            </div>
                        </form>
                    </div>
                </div>

            </div>
            <div class="portlet-body">
                <table class="table table-striped table-bordered table-hover dataTable table-set">
                    <thead>
                    <tr>
                        <th width="90px">机构名称</th>
                        <th width="60px">机构简称</th>
                        <th width="80px">机构性质</th>
                        <th width="80px">机构级别</th>
                        <th width="80px">机构状态</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${pager.datas}" var="vo">
                        <tr style="text-overflow:ellipsis;">
                            <td><a href="javascript:edit('${vo.b0100}')"><c:out value="${vo.b0101}"></c:out></a></td>
                            <td><a href=""><c:out value="${vo.b0104}"></c:out></a></td>
                                <%--<td><c:out value="${vo.b0101}"></c:out></td>--%>
                            <td><c:out value="${vo.b0131A}"></c:out></td>
                            <td><c:out value="${vo.b0127A}"></c:out></td>
                            <td><c:out value="${vo.bDwztA}"></c:out></td>
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
<script type="text/javascript" src="${path }/js/common/loading.js"></script>
<script type="text/javascript">
    var myLoading = new MyLoading("${path}", {zindex: 11111});
    function nsjg() {
        alert("1111")
    }

    function edit(id) {
        window.location.href = "${path}/zzb/jggl/b01/manage?bSjlx=0&b01Id=${b01Id}&b0101=${b0101}&currentId="+id+"&OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}";
    }
    $(function () {
    })

    function addfrjg() {
        var bSjlx = '0';
        window.location.href = "${path}/zzb/jggl/b01/manage?isAdd=add&bSjlx=0&b01Id=${b01Id}&b0101=${b0101}&OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}";
    }


    function pagehref(pageNum, pageSize) {
        <%--window.location.href ="${path}/zzb/app/console/gbmc/a01/list?b01Id=${b01Id}&mcid=${mcid}&pageNum="+pageNum+"&pageSize="+pageSize;--%>
        $.ajax({
            async: false,
            type: "POST",
            url: "${path}/zzb/app/console/bset/ajax/list",
            dataType: "html",
            headers: {
                "OWASP_CSRFTOKEN": '${sessionScope.OWASP_CSRFTOKEN}'
            },
            data: {
                'queryId': "${queryId}",
                'pageNum': pageNum,
                'pageSize': pageSize
            },
            success: function (html) {
                $("#catalogList").html(html);
//				$("#treeId").val(nodeId);
            },
            error: function () {
                myLoading.hide();
                showTip("提示", "出错了,请检查网络!", 2000);
            }
        });

    }

    function searchSubmit() {
        $.ajax({
            async: false,
            type: "POST",
            url: "${path}/zzb/app/console/bset/ajax/list",
            dataType: "html",
            headers: {
                "OWASP_CSRFTOKEN": '${sessionScope.OWASP_CSRFTOKEN}'
            },
            data: $("#searchForm").serialize(),
            success: function (html) {
                $("#catalogList").html(html);
//				$("#treeId").val(nodeId);
            },
            error: function () {
                myLoading.hide();
                showTip("提示", "出错了,请检查网络!", 2000);
            }
        });
    }


    var view = function (id) {
        $.ajax({
            async: false,
            type: "POST",
            url: "${path}/zzb/app/console/appGbcxA01/ajax/view",
            dataType: "html",
            headers: {
                "OWASP_CSRFTOKEN": '${sessionScope.OWASP_CSRFTOKEN}'
            },
            data: {
                'id': id
            },
            success: function (html) {
                $("#catalogList").html(html);
//				$("#treeId").val(nodeId);
            },
            error: function () {
                myLoading.hide();
                showTip("提示", "出错了,请检查网络!", 2000);
            }
        });
    }
    var del = function (id, itemName) {
        actionByConfirm1(itemName, "${path}/zzb/app/console/appGbcxA01/delete/" + id, {}, function (data, status) {
            if (data.success == true) {
                showTip("提示", "删除成功", 2000);
                setTimeout(function () {
                    window.location.href = "${path}/zzb/app/console/appGbcxA01/list?b01Id=${b01Id}&mcid=${mcid}"
                }, 2000);
            } else {
                showTip("提示", data.message, 2000);
            }
        });
    };
    function uploadFile(fileName) {
        document.getElementById("btn-" + fileName).click();
    }
    function clearData() {
        window.location.href = "${path}/zzb/jggl/b01/index?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}"
    }
</script>
</body>
</html>
