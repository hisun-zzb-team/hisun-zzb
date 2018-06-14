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
        <button type="button" class="btn green" id ="saveButton" onclick="formSave()"><i class="icon-ok"></i> 保存</button>
        <a  class="btn" id="cancelId"><i class="icon-remove-sign"></i> 取消</a>
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
    <input type="hidden" name="a01Id" id="a01Id" value="${a01Id}"/>
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
            debugger
            if ($(e.target).attr('id') != '#tab_1_1' && tabIndex == "#tab_1_1") {
                var myVld = new EstValidate("a01Form");
                myLoading.show();
                var bool = myVld.form();
                if (!bool) {
                    myLoading.hide();
                    return false;
                }
                a01Save(e)
               // baseLoad();
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
    function a01Save(e){
        $.ajax({
            url: "${path }/zzb/gbgl/a01/updateOrSave",
            type: "post",
            data: $("#a01Form").serialize(),
            headers: {
                OWASP_CSRFTOKEN: "${sessionScope.OWASP_CSRFTOKEN}"
            },
            dataType: "json",
            success: function (json) {
                if (json.code == 1) {
                    var a01Id = json.a01Id;
                    if (a01Id != "" && a01Id != undefined) {
                        $("#a01Id").val(a01Id);
                    }
                    myLoading.hide();
                    tabIndex = $(e.target).attr('id');
                    changeTable(e);
                } else {
                    $("[id='#tab_1_1']").tab('show');
                    myLoading.hide();
                    showTip("提示", json.message, 2000);
                    return false;
                }
            },
            error: function () {
                $("[id='#tab_1_1']").tab('show');
                console.log("我这里出错了")
                showTip("提示", "出错了,请检查网络!", 2000);
                myLoading.hide();
                return false;
            }
        });
    }
    //切换table
    function changeTable(e) {
        if ($(e.target).attr('id') == "#tab_1_1") {
            $("[id='#tab_1_1']").tab('show');
            baseLoad();
        } else if ($(e.target).attr('id') == "#tab_1_2") {
            $("[id='#tab_1_2']").tab('show');
            xrzwLoad();
        } else if ($(e.target).attr('id') == "#tab_1_3") {
            $("[id='#tab_1_3']").tab('show');
            gzjlLoad();
        } else if ($(e.target).attr('id') == "#tab_1_4") {
            $("[id='#tab_1_4']").tab('show');
            xxjlLoad();
        } else if ($(e.target).attr('id') == "#tab_1_5") {
            $("[id='#tab_1_5']").tab('show');
            jckhLoad();
        }else if ($(e.target).attr('id') == "#tab_1_6") {
            $("[id='#tab_1_6']").tab('show');
            pxqkLoad();
        } else if ($(e.target).attr('id') == "#tab_1_7") {
            $("[id='#tab_1_7']").tab('show');
            shgxLoad();
        } else if ($(e.target).attr('id') == "#tab_1_8") {
            $("[id='#tab_1_8']").tab('show');
            qtxxLoad();
        } else if ($(e.target).attr('id') == "#tab_1_9") {
            $("[id='#tab_1_9']").tab('show');
            lxfsLoad();
        }
    }

    //基本信息
    function baseLoad() {
        myLoading.show();
        $.ajax({
            url: "${path}/zzb/gbgl/a01/ajax/jbxx",
            type: "post",
            data: {"b01Id": "${b01Id}", "a01Id": "${a01Id}"},
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
    function xxjlLoad() {
        $.ajax({
            url: "${path }/zzb/gbgl/a08/ajax/xxjl",
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
                showTip("提示", "学习经历加载失败");
            }
        });
    }

    function gzjlLoad() {
        $.ajax({
            url: "${path }/zzb/gbgl/a17/ajax/list",
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
                showTip("提示", "工作经历加载失败");
            }
        });
    }
    function pxqkLoad() {
        $.ajax({
            url: "${path }/zzb/gbgl/a11/ajax/list",
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
                showTip("提示", "工作经历加载失败");
            }
        });
    }
    function jckhLoad() {
        $.ajax({
            url: "${path }/zzb/gbgl/a14/ajax/jckh",
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
                showTip("提示", "奖惩考核加载失败");
            }
        });
    }
    $("#saveButton").click(function () {
        if (tabIndex == "#tab_1_1") {
            var myVld = new EstValidate("a01Form");
            myLoading.show();
            var bool = myVld.form();
            if (!bool) {
                myLoading.hide();
                return false;
            }
            $.ajax({
                url: "${path }/zzb/gbgl/a01/updateOrSave",
                type: "post",
                data: $("#a01Form").serialize(),
                headers: {
                    OWASP_CSRFTOKEN: "${sessionScope.OWASP_CSRFTOKEN}"
                },
                dataType: "json",
                success: function (json) {
                    myLoading.hide();
                    showTip("提示", "保存成功", 1500)
                    setTimeout(function () {
                        toA01List()
                    }, 1500);
                },
                error: function () {
                }
            });
        } else if (tabIndex == "#tab_1_2") {

        } else if (tabIndex == "#tab_1_4") {
            showTip("提示", "保存成功", 1500)
            setTimeout(function () {
                toA01List()
            }, 1500);
        } else if (tabIndex == "#tab_1_5") {
        }
    });

    $("#cancelId").click(function(){
        $.ajax({
            url: "${path}/zzb/gbgl/a01/ajax/list",
            type: "get",
            dataType: "html",
            headers: {
                "OWASP_CSRFTOKEN": "${sessionScope.OWASP_CSRFTOKEN}"
            },
            data: {
                "b01Id": "${b01Id}"
            },
            success: function (html) {
                //refreshTreeTagByDt("leftB01Tree", "${b01Id}");
                $("#rightList").html(html);
            },
            error: function () {

            }
        });
    });
    function toA01List() {
        $.ajax({
            url: "${path}/zzb/gbgl/a01/ajax/list",
            type: "get",
            dataType: "html",
            headers: {
                "OWASP_CSRFTOKEN": "${sessionScope.OWASP_CSRFTOKEN}"
            },
            data: {
                "b01Id": "${b01Id}"
            },
            success: function (html) {
                $("#rightList").html(html);
            },
            error: function () {

            }
        });
    }
</script>
</body>
</html>
