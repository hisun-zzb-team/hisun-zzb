<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
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

    <title>机构编制管理</title>
</head>
<body>

<div class="portlet-title">
    <div class="caption">

        <i class="icon-reorder"></i>

        <span class="hidden-480">添加人员</span>

    </div>
    <div class="clearfix fr">
        <button type="button" class="btn green" onclick="formSave()"><i class="icon-ok"></i> 保存</button>
        <a  class="btn" onclick="cancel()"><i class="icon-remove-sign"></i> 取消</a>
    </div>
</div>

<!--BEGIN TABS-->
<div class="tabbable tabbable-custom" style="margin-bottom: 0px">
    <ul class="nav nav-tabs" style="font-size: 14px;font-weight: bold;" id="tabs">
        <li class="active"><a id="#tab_1_1" href="#tab_1_1" data-toggle="tab">基本信息</a></li>
        <li ><a id="#tab_1_2" href="#tab_1_1" data-toggle="tab">现任职务</a></li>
        <li ><a id="#tab_1_3" href="#tab_1_1" data-toggle="tab">工作经历</a></li>
        <li ><a id="#tab_1_4" href="#tab_1_1" data-toggle="tab">学习经历</a></li>
        <li ><a id="#tab_1_5" href="#tab_1_1" data-toggle="tab">奖惩考核</a></li>
        <li ><a id="#tab_1_6" href="#tab_1_1" data-toggle="tab">培训情况</a></li>
        <li ><a id="#tab_1_7" href="#tab_1_1" data-toggle="tab">社会关系</a></li>
        <li ><a id="#tab_1_8" href="#tab_1_1" data-toggle="tab">其他信息</a></li>
        <li ><a id="#tab_1_9" href="#tab_1_1" data-toggle="tab">联系方式</a></li>
    </ul>
    <div class="tab-content" style="border:none; border-top:solid 1px #e4e4e4; padding:5px 0;">
        <div class="tab-pane active" id="tab_show">
        </div>
    </div>
</div>
<script type="text/javascript" src="${path }/js/common/loading.js"></script>
<script type="text/javascript">
    var myLoading = new MyLoading("${path}", {zindex: 11111});
    var tabIndex = "#tab_1_1";
    $(function () {
        App.init();
        baseLoad();
        $('a[data-toggle="tab"]').on('show.bs.tab', function (e) {
            if ($(e.target).attr('id') != '#tab_1_1' && tabIndex == "#tab_1_1") {
                baseLoad();
            }
            else {
                if ($(e.target).attr('id') == "#tab_1_1") {
                    baseLoad();
                } else if ($(e.target).attr('id') == "#tab_1_2") {
                    xrzwLoad();
                } else if ($(e.target).attr('id') == "#tab_1_3") {
                    gzjlLoad();
                } else if ($(e.target).attr('id') == "#tab_1_4") {
                    xxjlLoad();
                } else if ($(e.target).attr('id') == "#tab_1_5") {
                    jckhLoad();
                } else if ($(e.target).attr('id') == "#tab_1_6") {
                    pxqkLoad();
                } else if ($(e.target).attr('id') == "#tab_1_7") {
                    shgxLoad();
                } else if ($(e.target).attr('id') == "#tab_1_8") {
                    qtxxLoad();
                } else if ($(e.target).attr('id') == "#tab_1_9") {
                    lxfsLoad();
                }
                tabIndex = $(e.target).attr('id');
            }
        });
    });


    //基本信息
    function baseLoad() {
        var currentId = $("#currentId").val();
        myLoading.show();
        $.ajax({
            url: "${path}/zzb/jggl/b01/ajax/jbxx",
            type: "post",
            data: {"b01Id": "${b01Id}", "bSjlx": "${bSjlx}", "currentId": currentId, "isAdd": "${isAdd}","isAddOne":"${isAddOne}"},
            dataType: "html",
            headers: {
                OWASP_CSRFTOKEN: "${sessionScope.OWASP_CSRFTOKEN}"
            },
            success: function (html) {
                var view = $("#tab_show");
                view.html(html);
                myLoading.hide();
            },
            error: function (arg1, arg2, arg3) {
                showTip("提示", "基本信息加载失败");
            }
        });
    }
    function xrzwLoad() {
        $.ajax({
            url: "${path }/zzb/gbgl/a02/ajax/xrzw",
            type: "post",
            data: {"a01Id": "${a01Id}"},
            dataType: "html",
            headers: {
                OWASP_CSRFTOKEN: "${sessionScope.OWASP_CSRFTOKEN}"
            },
            success: function (html) {
                var view = $("#tab_show");
                view.html(html);
            },
            error: function (arg1, arg2, arg3) {
                showTip("提示", "现任职务加载失败");
            }
        });
    }




    function cancel() {

        $.ajax({
            url: "${path}/zzb/jggl/b01/ajax/list",
            type: "get",
            dataType: "html",
            headers: {
                "OWASP_CSRFTOKEN": "${sessionScope.OWASP_CSRFTOKEN}"
            },
            data: {
                "b01Id": "${b01Id}"
            },
            success: function (html) {
                refreshTreeTagByDt("leftB01Tree", "${b01Id}");
                $("#rightList").html(html);
            },
            error: function () {

            }
        });
        // window.location.href = "${path}/zzb/jggl/b01/index?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}"
    }
</script>
</body>
</html>
