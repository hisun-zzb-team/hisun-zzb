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
    <title>${b0101} 人员列表</title>
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
                                    <a onclick="add('0')">法人机构</a>
                                </li>
                                <li>
                                    <a onclick="add('1')">内设机构</a>
                                </li>
                                <li>
                                    <a onclick="add('2')">机构分组</a>
                                </li>
                            </ul>
                        </div>
                    <%--    <div class="btn-group">
                            <a class="btn green" href="javascript:gjcx()">
                                高级查询
                            </a>
                        </div>--%>
                        <div class="btn-group">
                            <a id="sample_editable_1_new" class="btn green" href="javascript:download()">
                                导出
                            </a>
                        </div>
                    </div>

                </div>
            </form>
            <div class="clearfix">
                <div class="control-group">
                    <div id="query" style="float: left;">
                        <form action="${path }/zzb/jggl/b01/ajax/list" method="POST" id="searchForm"
                              name="searchForm">
                            <input type="hidden" name="OWASP_CSRFTOKEN" value="${sessionScope.OWASP_CSRFTOKEN}"/>
                         <%--   <input type="hidden" name="pageNum" value="${pager.pageNum }" id="pageNum">
                            <input type="hidden" name="pageSize" value="${pager.pageSize }" id="pageSize">--%>
                            <input type="hidden" name="b01Id" value="${b01Id }" id="b01Id">
                            <input type="hidden" name="b0101" value="${b0101 }" id="b0101">
                            <%--<input type="hidden" name="pageNum" value="${ }" id="pageNum">--%>
                            <div style=" float:left;margin-top:4px">机构名称：</div>
                            <div style=" float:left;">
                            <input type="text" class="span2 m-wrap" name="b0101Query" id="b0101Query"
                                            value="${queryModel.b0101Query}" style="width: 100px;"/>
                            </div>
                            <div style=" float:left;margin-top:4px">所属机构：</div>
                            <div style="float:left;width: 160px;"><Tree:tree id="parentIdQuery" valueName="parentNameQuery" selectClass="span12 m-wrap"
                                           treeUrl="${path}/api/b01/dtjz/tree"
                                           token="${sessionScope.OWASP_CSRFTOKEN}" dtjz="true"
                                           submitType="get" dataType="json" isSearch="true"
                                           checkedByTitle="true" isSelectTree="true" defaultkeys="${queryModel.parentIdQuery}"
                                           defaultvalues="${queryModel.parentNameQuery}"/>
                            </div>
                            <div style=" float:left;margin-top:4px">机构级别：</div>
                            <div style="float:left;width: 120px;"><SelectTag:SelectTag id="b0127Query" needNullValue="true" valueName="b0127AQuery"
                                                          defaultkeys="${queryModel.b0127Query}" token="${sessionScope.OWASP_CSRFTOKEN}"
                                                          defaultvalues="${queryModel.b0127AQuery}"
                                                          textClass="span12 m-wrap" radioOrCheckbox="radio"
                                                          selectUrl="${path}/api/dictionary/select?typeCode=ZB03-1994/DWJB"/>
                            </div>
                            <%--<div style=" float:left;margin-top:4px">机构性质：</div>--%>
                            <%--<div style="float:left;width: 120px;"><Tree:tree id="b0131Query" valueName="b0131ContentQuery" selectClass="span12 m-wrap"--%>
                                                <%--treeUrl="${path}/api/dictionary/tree?typeCode=ZB04-2006/DWXZ"--%>
                                                <%--token="${sessionScope.OWASP_CSRFTOKEN}"--%>
                                                <%--submitType="get" dataType="json" isSearch="false"--%>
                                                <%--checkedByTitle="true" isSelectTree="true" defaultkeys="${b0131Query}"--%>
                                                <%--defaultvalues="${b0131Query}"/>--%>
                            <%--</div>--%>
                            <div style=" float:left;margin-top:4px">机构管理类别：</div>
                            <div style="float:left;width: 120px;">
                                <SelectTag:SelectTag id="bGllbBQuery" needNullValue="true" valueName="bGllbAQuery"
                                                            defaultkeys="${queryModel.bGllbBQuery}" token="${sessionScope.OWASP_CSRFTOKEN}"
                                                            defaultvalues="${queryModelbGllbAQuery}"
                                                            textClass="span12 m-wrap" radioOrCheckbox="radio"
                                                            selectUrl="${path}/api/dictionary/select?typeCode=2018-JGGLLB"/>
                            </div>
                            <div style="float:left;margin-left: 5px">
                                <button type="button" class="btn Short_but" onclick="searchSubmit()">查询</button>
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
                        <th width="80px">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${pager.datas}" var="vo">
                        <tr style="text-overflow:ellipsis;">
                            <td><a href="javascript:edit('${vo.b0100}')"><c:out value="${vo.b0101}"></c:out></a></td>
                            <td><c:out value="${vo.b0104}"></c:out></td>
                            <td><c:out value="${vo.b0131A}"></c:out></td>
                            <td><c:out value="${vo.b0127A}"></c:out></td>
                            <td><c:out value="${vo.bDwztA}"></c:out></td>
                            <td>
                                <a href="javascript:edit('${vo.b0100}')" class="">修改</a>|
                                <a href="javascript:deleteB01('${vo.b0100}','${vo.b0101}')" class="">删除</a>
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
        <%-- 表格结束 --%>
    </div>
</div>
<div id="queryModelModal" class="modal container hide fade" tabindex="-1" data-width="600">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close"  type="button"></button>
                <h3 class="modal-title" id="title1">
                    高级查询
                </h3>
                <%-- <input type="hidden" name="appQueryId" value="" id="appQueryId">--%>
            </div>
            <div class="modal-body" id="queryModelDiv">
            </div>
        </div>
    </div>
</div>
<%-- END PAGE CONTENT--%>
<script type="text/javascript" src="${path }/js/common/loading.js"></script>
<script type="text/javascript">
    var myLoading = new MyLoading("${path}", {zindex: 11111});
    function gjcx() {
        $.ajax({
            url: "${path }/zzb/jggl/b01/ajax/toGjcx",
            type: "post",
            data: {},
            dataType: "html",
            headers: {
                OWASP_CSRFTOKEN: "${sessionScope.OWASP_CSRFTOKEN}"
            },
            success: function (html) {
                $("#queryModelDiv").html(html);
                $('#queryModelModal').modal({
                    keyboard: true
                });
            },
            error: function (arg1, arg2, arg3) {
                showTip("提示", "查询失败");
            }
        });
    }

    function edit(id) {
        $.ajax({
            url: "${path}/zzb/jggl/b01/ajax/manage",
            type : "get",
            dataType : "html",
            headers: {
                "OWASP_CSRFTOKEN":"${sessionScope.OWASP_CSRFTOKEN}"
            },
            data:{
                "b01Id":"${b01Id}",
                "b0101":"${b0101}",
                "currentId":id
            },
            success : function(html){
                $("#rightList").html(html);
            },
            error : function(){

            }
        });
        //window.location.href = "${path}/zzb/jggl/b01/manage?b01Id=${b01Id}&b0101=${b0101}&currentId="+id+"&OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}";
    }

    function add(bSjlx) {
        $.ajax({
            url: "${path}/zzb/jggl/b01/ajax/manage",
            type : "get",
            dataType : "html",
            headers: {
                "OWASP_CSRFTOKEN":"${sessionScope.OWASP_CSRFTOKEN}"
            },
            data:{
                "b01Id":"${b01Id}",
                "b0101":"${b0101}",
                "isAdd":"add",
                "bSjlx":bSjlx
            },
            success : function(html){
                $("#rightList").html(html);
            },
            error : function(){

            }
        });
      //  window.location.href = "${path}/zzb/jggl/b01/manage?isAdd=add&bSjlx="+bSjlx+"&b01Id=${b01Id}&b0101=${b0101}&OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}";
    }


    function pagehref(pageNum, pageSize) {
        $.ajax({
            async: false,
            type: "POST",
            url: "${path}/zzb/jggl/b01/ajax/list?pageNum="+pageNum+"&pageSize="+pageSize,
            dataType: "html",
            headers: {
                "OWASP_CSRFTOKEN": '${sessionScope.OWASP_CSRFTOKEN}'
            },
            data: $("#searchForm").serialize(),
            success: function (html) {
                $("#rightList").html(html);
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
            url: "${path}/zzb/jggl/b01/ajax/list",
            dataType: "html",
            headers: {
                "OWASP_CSRFTOKEN": '${sessionScope.OWASP_CSRFTOKEN}'
            },
            data: $("#searchForm").serialize(),
            success: function (html) {
                $("#rightList").html(html);
            },
            error: function () {
                myLoading.hide();
                showTip("提示", "出错了,请检查网络!", 2000);
            }
        });
    }
    var deleteB01 = function(id,name){
        var title = "您确定要删除["+name+"]吗？";
        var msg = "此操作将删除该机构及其下所有的法人机构、分组和内设机构。";
        var tip = "请输入要删除的机构名称";
        myLoading.show();
        showPrompModal2(title,name,msg,tip,"${path}/zzb/jggl/b01/delete?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}&id=" + id,null, function(json){

            if(json.success == true){
                myLoading.hide();
                showTip("提示","删除成功", 1500);
                setTimeout(function(){window.location.href = "${path}/zzb/jggl/b01/index?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}"},1500);
            }else{
                myLoading.hide();
                showTip("提示", json.message, 2000);
            }

        })
    };
    function download() {
        var b01Id = "${b01Id}";
        var b0101Query = $("#b0101Query").val();
        var parentIdQuery = $("#parentIdQuery").val();
        var b0127Query = $("#b0127Query").val();
        var bGllbBQuery = $("#bGllbBQuery").val();

        window.open("${path}/zzb/jggl/b01/download?b01Id="+b01Id+"&b0101Query="+b0101Query+"&parentIdQuery="+parentIdQuery+"&b0127Query="+b0127Query+
            "&bGllbBQuery="+bGllbBQuery+"&OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}");
    }
    function clearData() {
        window.location.href = "${path}/zzb/jggl/b01/index?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}"
    }
</script>
</body>
</html>
