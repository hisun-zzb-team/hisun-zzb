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
                <div class="clearfix fr">
                    <button id="submitSave" onclick="daochu()" class="btn green" type="button" style="padding:7px 20px;">导出</button>
                </div>

            </div>
            <div class="portlet-body">
                <table class="table table-striped table-bordered table-hover dataTable table-set">
                    <thead>

                    <TR height=28>
                        <th width=120>查询名称</th>
                        <th style="text-align: center">查询说明</th>
                        <th width=110  style="text-align: center">修改查询条件内容</th>
                        <th width=70  style="text-align: center">操作</th>
                    </thead>
                    <tbody>
                    <c:forEach items="${pager.datas}" var="vo">
                        <tr style="text-overflow:ellipsis;">
                            <TD ><a href="javascript:toQuery('${vo.id}')"><c:out value="${vo.queryName}"></c:out></a> </TD>
                            <TD  style="text-align: center"><c:out value="${vo.description}"></c:out></TD>
                            <TD  style="text-align: center"><a href="javascript:editcxtj('${vo.id}')" class="">修改查询条件内容</a></TD>
                            <TD  style="text-align: center">
                            <a href="javascript:editQuery('${vo.id}','${vo.queryName}','${vo.description}')" class="">修改</a>|
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
<div id="queryModelModal" class="modal container hide fade" tabindex="-1" data-width="400" >
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="btn btn-default" style="float: right;font-weight: bold;" data-dismiss="modal"><i class='icon-remove-sign'></i> 关闭</button>
                <a class="btn green"  style="float: right;font-weight: bold;margin-right: 10px;" href="javascript:upadateCxtj()">
                    保存
                </a>
                <h3 class="modal-title" id="title">
                    修改查询条件
                </h3>
                <input type="hidden" name="appQueryId" value="" id="appQueryId">
            </div>
            <div class="modal-body" id="queryModelDiv" >
                <div class="row-fluid">
                    <div class="span12">
                        <%-- BEGIN SAMPLE FORM PORTLET 表单主体--%>
                        <div class="portlet box grey">
                            <div class="portlet-body form">
                                <div class="control-group" id="queryNameGroup">
                                    <div class="controls">
                                        <label class="control-label" style="display: inline;width: 70px;line-height: 30px"><span class="required">*</span>查阅名称</label>
                                        <input size="16" type="text"  class="span9 m-wrap" value="${queryName}"
                                               id="queryName" name="queryName"    required  maxlength="200">
                                    </div>
                                </div>
                                <div class="control-group" id="descriptionGroup">
                                    <div class="controls">
                                        <label class="control-label" style="display: inline;width: 70px;line-height: 30px">查询描述&nbsp;&nbsp;</label>
                                        <input size="16" type="text"  class="span9 m-wrap" value="${description}"
                                               id="description" name="description" >
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

    function toQuery(id){
        $.ajax({
            url : "${path }/zzb/dzda/dacx/ajax/bdwdalistById",
            type : "post",
            data : {"appQueryId":id},
            dataType : "html",
            headers:{
                OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
            },
            success : function(html){
                /*  $('#gjcxModal').modal('hide');
                 $('#gjcxDiv').html("");*/
                var view = $("#tab_show");
                view.html("");
                view.html(html);
            },
            error : function(arg1, arg2, arg3){
                showTip("提示","查询失败");
            }
        });
    }
    //常用查询项
    function editcxtj(id){
        $.ajax({
            url:"${path}/zzb/dzda/dacx/ajax/gjcx",
            type : "post",
            data: {"appQueryId":id,
            "editFlag":"edit"},
            headers:{
                OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
            },
            dataType : "html",
            success : function(html){
                var view = $("#tab_show");
                view.html(html);
            },
            error : function(){
                showTip("提示","出错了请联系管理员", 1500);
            }
        });
    }
    function editQuery(id,name,description){
        $("#appQueryId").val(id);
        $("#queryName").val(name);
        $("#description").val(description);
        $('#queryModelModal').modal({
            keyboard: true
        });
    }
    function upadateCxtj(){
        var queryName = $("#queryName").val();
        if(queryName==""){
            showTip("提示","请填写条件名称!");
            return;
        }
        $.ajax({
            url : "${path }/zzb/dzda/dacx/saveById",
            type : "post",
            data :
            {"appQueryId":$("#appQueryId").val(),
                "queryName":$("#queryName").val(),
                "description":$("#description").val()},
            dataType : "json",
            headers:{
                OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
            },
            success : function(json){
                $.ajax({
                    url : "${path}/zzb/dzda/dacx/ajax/list",
                    type : "post",
                    data : {},
                    dataType : "html",
                    headers:{
                        "OWASP_CSRFTOKEN":'${sessionScope.OWASP_CSRFTOKEN}'
                    },
                    success : function(html){
                        $('#queryModelModal').modal('hide');
                        $("[id='#tab_1_1']").tab('show');
                        var view = $("#tab_show");
                        view.html(html);
                        myLoading.hide();
                    },
                    error : function(arg1, arg2, arg3){
                        showTip("提示","条件查询加载失败");
                    }
                });
            },
            error : function(arg1, arg2, arg3){
                showTip("提示","查询失败");
            }
        });
    }


    var deleteQuery = function(id){
        actionByConfirm1('',"${path}/zzb/dzda/dacx/delete/"+id,null,function(json){
            if(json.code == 1){
                showTip("提示","操作成功",1000);
                setTimeout(function(){
                    $.ajax({
                        url : "${path}/zzb/dzda/dacx/ajax/list",
                        type : "post",
                        data : {},
                        dataType : "html",
                        headers:{
                            "OWASP_CSRFTOKEN":'${sessionScope.OWASP_CSRFTOKEN}'
                        },
                        success : function(html){
                            $('#queryModelModal').modal('hide');
                            $("[id='#tab_1_1']").tab('show');
                            var view = $("#tab_show");
                            view.html(html);
                            myLoading.hide();
                        },
                        error : function(arg1, arg2, arg3){
                            showTip("提示","条件查询加载失败");
                        }
                    });

                },1500);

            }else{
                showTip("提示", json.message, 2000);
            }
        },"删除")
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
