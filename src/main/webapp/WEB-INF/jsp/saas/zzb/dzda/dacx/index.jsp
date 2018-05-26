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
    <style type="text/css">
        ul.ztree{margin-bottom: 10px; background: #f1f3f6 !important;}
        .portlet.box.grey.mainleft{background-color: #f1f3f6;overflow: hidden; padding: 0px !important; margin-bottom: 0px;}
        .main_left{float:left; width:220px;  margin-right:10px; background-color: #f1f3f6; }
        .main_right{display: table-cell; width:2000px;}
    </style>
    <title>电子档案系统</title>
</head>
<body>
<div id="gjcxModal" class="modal container hide fade" tabindex="-1" data-width="85%">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close"  type="button"></button>
                <h3 class="modal-title" id="gjcxTitle" >
                    高级查询条件设置
                </h3>
            </div>
            <div class="modal-body" id="gjcxDiv">
            </div>
        </div>
    </div>
</div>
<!--BEGIN TABS-->
<div class="tabbable tabbable-custom">
    <ul class="nav nav-tabs" style="font-size: 14px;font-weight: bold;" id="tabs">
        <li class="active"><a id="#tab_1_1" href="#tab_1_1" data-toggle="tab">常用查询</a></li>
        <li ><a id="#tab_1_2" href="#tab_1_1" data-toggle="tab">常用查询项</a></li>
    </ul>
    <div class="tab-content" style="border:none; border-top:solid 1px #e4e4e4; padding:10px 0;">
        <div class="tab-pane active" id="tab_show">
        </div>
    </div>
</div>
<script type="text/javascript" src="${path }/js/common/loading.js"></script>
<script type="text/javascript">
    var myLoading = new MyLoading("${path}",{zindex : 11111});
    var tabIndex ="#tab_1_1";
    $(function(){
        App.init();
        cycxList();
        $('a[data-toggle="tab"]').on('show.bs.tab', function (e) {

            if($(e.target).attr('id')=="#tab_1_1") {
//					$("[id='#tab_1_1']").tab('show');
                cycxList();
            }else if($(e.target).attr('id')=="#tab_1_2"){
//					$("[id='#tab_1_2']").tab('show');
                cycx();
            }
            tabIndex = $(e.target).attr('id');
        });
    });

    //条件查询
    function cycxList(){
        myLoading.show();
        $.ajax({
            url : "${path}/zzb/dzda/dacx/ajax/list",
            type : "post",
            data : {},
            dataType : "html",
            headers:{
                "OWASP_CSRFTOKEN":'${sessionScope.OWASP_CSRFTOKEN}'
            },
            success : function(html){
                var view = $("#tab_show");
                view.html(html);
               myLoading.hide();
            },
            error : function(arg1, arg2, arg3){
                showTip("提示","条件查询加载失败");
            }
        });
    }
    //常用查询项
    function cycx(){
        myLoading.show();
        $.ajax({
            url:"${path}/zzb/dzda/dacx/ajax/gjcx",
            type : "post",
            data: {},
            headers:{
                OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
            },
            dataType : "html",
            success : function(html){
                var view = $("#tab_show");
                view.html(html);
                myLoading.hide();
            },
            error : function(){
                showTip("提示","出错了请联系管理员", 1500);
            }
        });
    }



</script>
</body>
</html>
