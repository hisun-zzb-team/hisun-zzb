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
                <div class="clearfix fr">
                    <a href="${path}/zzb/dzda/dacx/gjcx?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}" class="btn blue">
                        <i class="icon-plus"></i>新增条件</a>
                    <a href="${path}/zzb/dzda/dacx/bdwdalist?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}"
                       id="fanhui" class="btn icn-only" style="height:22px;"><i class="icon-undo"></i>返回</a>

                </div>

            </div>
            <div class="portlet-body">
                <table class="table table-striped table-bordered table-hover dataTable table-set">
                    <thead>

                    <TR height=28>
                        <th width=40>序号</th>
                        <th width=120>查询名称</th>
                        <th style="text-align: center">查询说明</th>
                        <th width=70>是否常用</th>
                        <th width=110 style="text-align: center">修改查询条件内容</th>
                        <th width=70 style="text-align: center">操作</th>
                    </thead>
                    <tbody>
                    <c:forEach items="${pager.datas}" var="vo">
                        <tr style="text-overflow:ellipsis;">
                            <TD style="text-align: center"><c:out value="${vo.px}"></c:out></TD>
                            <TD>
                                <a href="${path }/zzb/dzda/dacx/bdwdalist?appQueryId=${vo.id}&OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}"><c:out
                                        value="${vo.queryName}"></c:out></a></TD>
                            <TD style="text-align: center"><c:out value="${vo.description}"></c:out></TD>
                            <TD style="text-align: center">
                                <c:choose>
                                    <c:when test="${vo.queryType=='0'}">
                                        否
                                    </c:when>
                                    <c:when test="${vo.queryType=='1'}">
                                        是
                                    </c:when>
                                </c:choose>
                            </TD>
                            <TD style="text-align: center"><a href="javascript:editcxtj('${vo.id}')"
                                                              class="">修改查询条件内容</a></TD>
                            <TD style="text-align: center">
                                <a href="javascript:editQuery('${vo.id}','${vo.queryName}','${vo.description}','${vo.queryType}','${vo.px}')"
                                   class="">修改</a>|
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
<div id="queryModelModal" class="modal container hide fade" tabindex="-1" data-width="500">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">

                <h3 class="modal-title" id="title">
                    修改查询条件
                </h3>
                <input type="hidden" name="appQueryId" value="" id="appQueryId">
            </div>
            <div class="modal-body" id="queryModelDiv">
                <div class="row-fluid">
                    <div class="span12">
                        <%-- BEGIN SAMPLE FORM PORTLET 表单主体--%>
                        <div class="portlet box grey">
                            <div class="portlet-body form">
                                <div class="control-group" id="queryNameGroup" style="margin-bottom: 0px;">
                                    <div class="controls">
                                        <label class="control-label"
                                               style="display: inline;width: 70px;line-height: 30px;margin-left: 30px !important;"><span
                                                class="required">*</span>查阅名称：</label>
                                        <input size="16" type="text" class="span8 m-wrap" value="${queryName}"
                                               id="queryName" name="queryName" required maxlength="200">
                                    </div>
                                </div>
                                <div class="control-group" id="descriptionGroup" style="margin-bottom: 0px;">
                                    <div class="controls">
                                        <label class="control-label"
                                               style="display: inline;width: 70px;line-height: 30px;margin-left: 30px !important;">
                                            &nbsp;&nbsp;查询描述：</label>
                                        <textarea size="16" type="text" class="span8 m-wrap" rows="3" style="resize: none;"
                                               id="description" name="description" maxlength="200" number="true" required>${description}</textarea>
                                    </div>
                                </div>
                                <div class="control-group" id="pxGroup" style="margin-bottom: 0px;">
                                    <div class="controls">
                                        <label class="control-label"
                                               style="display: inline;width: 70px;line-height: 30px;margin-left: 30px !important;">
                                            &nbsp;&nbsp;&nbsp;<span class="required">*</span>顺序号：</label>
                                        <input size="16" type="text" class="span8 m-wrap" value="${sort}"
                                               id="px" name="px">
                                    </div>
                                </div>
                                <div id="queryTypeGroup" class="control-group" style="margin-bottom: 0px;">
                                    <label class="control-label" style="display: inline;width: 70px;line-height: 30px;margin-left: 30px !important;">&nbsp;&nbsp;是否常用：</label>

                                    <div class="controls" style="display: inline;">
                                        <label class="radio">
                                            <input type="radio" name="queryType" value="1" id="query1"/>
                                            是
                                        </label>
                                        <label class="radio">
                                            <input type="radio" name="queryType" value="0" id="query0"/>
                                            否
                                        </label>
                                    </div>
                                </div>
                                <div id="buttenGroup" class="control-group" style="margin-bottom: 0px;margin-right:130px;margin-top: 10px;">
                                    <button type="button" class="btn btn-default"
                                            style="float: right;font-weight: bold;" data-dismiss="modal"><i
                                            class='icon-remove-sign'></i> 关闭
                                    </button>
                                    <a class="btn green" style="float: right;font-weight: bold;margin-right: 10px;"
                                       href="javascript:upadateCxtj()">
                                        <i class="icon-ok"></i>保存
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<%-- END PAGE CONTENT--%>
</div>

<script type="text/javascript">

    function toQuery(id) {
        $.ajax({
            url: "${path }/zzb/dzda/dacx/ajax/bdwdalistById",
            type: "post",
            data: {"appQueryId": id},
            dataType: "html",
            headers: {
                OWASP_CSRFTOKEN: "${sessionScope.OWASP_CSRFTOKEN}"
            },
            success: function (html) {
                /*  $('#gjcxModal').modal('hide');
                 $('#gjcxDiv').html("");*/
                var view = $("#tab_show");
                view.html("");
                view.html(html);
            },
            error: function (arg1, arg2, arg3) {
                showTip("提示", "查询失败");
            }
        });
    }
    //常用查询项
    function editcxtj(id) {
        window.location.href = "${path}/zzb/dzda/dacx/gjcx?appQueryId=" + id + "&OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}";
    }
    function editQuery(id, name, description, queryType, px) {
        $("#appQueryId").val(id);
        $("#queryName").val(name);
        $("#description").val(description);
        $("#px").val(px);
        if (queryType == '1') {
            $("#query0")[0].parentNode.className = "";
            $("#query0").removeAttr('checked');
            $("#query1")[0].parentNode.className = "checked";
            $("#query1").attr('checked', 'checked')
        } else {
            $("#query1")[0].parentNode.className = "";
            $("#query1").removeAttr('checked');
            $("#query0")[0].parentNode.className = "checked";
            $("#query0").attr('checked', 'checked')
        }
        $('#queryModelModal').modal({
            keyboard: true
        });


    }

    /*  function cs(){
     var radioNode = $("#cgfcggcgfgfg")[0];
     var parent = radioNode.parentNode
     parent.className = "checked"
     $("#cgfcggcgfgfg").attr("checked","checked");
     }*/
    function upadateCxtj() {
        var queryName = $("#queryName").val();
        if (queryName == "") {
            showTip("提示", "请填写条件名称!");
            return;
        }
        $.ajax({
            url: "${path }/zzb/dzda/dacx/saveById",
            type: "post",
            data: {
                "appQueryId": $("#appQueryId").val(),
                "queryName": $("#queryName").val(),
                "description": $("#description").val(),
                "queryType": $("input[name='queryType']:checked").val(),
                "px": $("#px").val()
            },
            dataType: "json",
            headers: {
                OWASP_CSRFTOKEN: "${sessionScope.OWASP_CSRFTOKEN}"
            },
            success: function (json) {
                window.location.href = "${path}/zzb/dzda/dacx/list?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}";
            },
            error: function (arg1, arg2, arg3) {
                showTip("提示", "查询失败");
            }
        });
    }


    var deleteQuery = function (id) {
        actionByConfirm1('', "${path}/zzb/dzda/dacx/delete/" + id, null, function (json) {
            if (json.code == 1) {
                showTip("提示", "操作成功", 1000);
                setTimeout(function () {
                    window.location.href = "${path}/zzb/dzda/dacx/list?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}";
                }, 1500);

            } else {
                showTip("提示", json.message, 2000);
            }
        }, "删除")
    }


    function pagehref(pageNum, pageSize) {
        <%--window.location.href ="${path}/zzb/app/console/gbmc/a01/list?b01Id=${b01Id}&mcid=${mcid}&pageNum="+pageNum+"&pageSize="+pageSize;--%>
        /*$.ajax({
         async:false,
         type:"POST",
         url:"
        ${path}/zzb/dzda/dacx/list",
         dataType : "html",
         headers:{
         "OWASP_CSRFTOKEN":'
        ${sessionScope.OWASP_CSRFTOKEN}'
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
         });*/
        window.location.href = "${path}/zzb/dzda/dacx/list?pageNum=" + pageNum + "&pageSize=" + pageSize + "&OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}";

    }
</script>
</body>
</html>
