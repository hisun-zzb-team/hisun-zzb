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

        <span class="hidden-480">修改机构</span>

    </div>
    <div class="clearfix fr">
        <button type="button" class="btn green" onclick="formSave()"><i class="icon-ok"></i> 保存</button>
        <a <c:if test="${isAddOne=='addOne'}">style="display: none" </c:if> class="btn" onclick="cancel()"><i class="icon-remove-sign"></i> 取消</a>
    </div>
</div>

<!--BEGIN TABS-->
<div class="tabbable tabbable-custom" style="margin-bottom: 0px">
    <ul class="nav nav-tabs" style="font-size: 14px;font-weight: bold;" id="tabs">
        <li class="active"><a id="#tab_1_1" href="#tab_1_1" data-toggle="tab">基本信息</a></li>
        <li ><a id="#tab_1_2" href="#tab_1_1" data-toggle="tab">编制情况</a>
        </li>
        <li ><a id="#tab_1_3" href="#tab_1_1" data-toggle="tab">职务管理</a>
        </li>
        <li ><a id="#tab_1_4" href="#tab_1_1"
                                                                                   data-toggle="tab">换届信息</a></li>
        <li ><a id="#tab_1_5" href="#tab_1_1"
                                                                                   data-toggle="tab">通信信息</a></li>
    </ul>
    <div class="tab-content" style="border:none; border-top:solid 1px #e4e4e4; padding:5px 0;">
        <input type="hidden" id="currentId" name="currentId" value="${currentId}">
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
                var myVld = new EstValidate("b01Form");
                myLoading.show();
                var bool = myVld.form();
                if (!bool) {
                    myLoading.hide();
                    return false;
                }
                tabSaveData(e);
            } else if ($(e.target).attr('id') != '#tab_1_2' && tabIndex == "#tab_1_2") {
                var myVld = new EstValidate("b02Form");
                myLoading.show();
                var bool = myVld.form();
                if (!bool) {
                    myLoading.hide();
                    return false;
                }
                b02tabSave(e);
            }
            else if ($(e.target).attr('id') != '#tab_1_5' && tabIndex == "#tab_1_5") {
                var myVld = new EstValidate("b04Form");
                myLoading.show();
                var bool = myVld.form();
                if (!bool) {
                    myLoading.hide();
                    return false;
                }
                b04tabSave(e);
            }
            else {
                if ($(e.target).attr('id') == "#tab_1_1") {
                    baseLoad();
                } else if ($(e.target).attr('id') == "#tab_1_2") {
                    bzqkLoad();
                } else if ($(e.target).attr('id') == "#tab_1_3") {
                    zwglLoad();
                } else if ($(e.target).attr('id') == "#tab_1_4") {
                    hjxxLoad();
                } else if ($(e.target).attr('id') == "#tab_1_5") {
                    txxxLoad();
                }
                tabIndex = $(e.target).attr('id');
            }
        });
    });
    function b02tabSave(e) {
        var currentId = $("#currentId").val();
        $.ajax({
            url: "${path }/zzb/jggl/b02/updateOrSave?currentId=" + currentId,
            type: "post",
            data: $("#b02Form").serialize(),
            headers: {
                OWASP_CSRFTOKEN: "${sessionScope.OWASP_CSRFTOKEN}"
            },
            dataType: "json",
            success: function (json) {
                if (json.code == 1) {
                    myLoading.hide();
                    tabIndex = $(e.target).attr('id');
                    if ($(e.target).attr('id') == "#tab_1_1") {
                        $("[id='#tab_1_1']").tab('show');
                        baseLoad();
                    } else if ($(e.target).attr('id') == "#tab_1_3") {
                        $("[id='#tab_1_3']").tab('show');
                        zwglLoad();
                    } else if ($(e.target).attr('id') == "#tab_1_4") {
                        $("[id='#tab_1_4']").tab('show');
                        hjxxLoad();
                    } else if ($(e.target).attr('id') == "#tab_1_5") {
                        $("[id='#tab_1_5']").tab('show');
                        txxxLoad();
                    }
                } else {
                    $("[id='#tab_1_2']").tab('show');
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
    function b04tabSave(e) {
        var currentId = $("#currentId").val();
        $.ajax({
            url: "${path }/zzb/jggl/b04/updateOrSave?currentId=" + currentId,
            type: "post",
            data: $("#b04Form").serialize(),
            headers: {
                OWASP_CSRFTOKEN: "${sessionScope.OWASP_CSRFTOKEN}"
            },
            dataType: "json",
            success: function (json) {
                if (json.code == 1) {
                    myLoading.hide();
                    tabIndex = $(e.target).attr('id');
                    if ($(e.target).attr('id') == "#tab_1_1") {
                        $("[id='#tab_1_1']").tab('show');
                        baseLoad();
                    } else if ($(e.target).attr('id') == "#tab_1_2") {
                        $("[id='#tab_1_2']").tab('show');
                        bzqkLoad();
                    } else if ($(e.target).attr('id') == "#tab_1_3") {
                        $("[id='#tab_1_3']").tab('show');
                        zwglLoad();
                    } else if ($(e.target).attr('id') == "#tab_1_4") {
                        $("[id='#tab_1_4']").tab('show');
                        hjxxLoad();
                    }
                } else {
                    $("[id='#tab_1_5']").tab('show');
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
    function b10tabSave(e) {
        var currentId = $("#currentId").val();
    }
    function tabSaveData(e) {
        $.ajax({
            url: "${path }/zzb/jggl/b01/updateOrSave",
            type: "post",
            data: $("#b01Form").serialize(),
            headers: {
                OWASP_CSRFTOKEN: "${sessionScope.OWASP_CSRFTOKEN}"
            },
            dataType: "json",
            success: function (json) {
                if (json.code == 1) {
                    var currentId = json.currentId;
                    if (currentId != "" && currentId != undefined) {
                        $("#currentId").val(currentId);
                    }
                    myLoading.hide();
                    tabIndex = $(e.target).attr('id');
                    if ($(e.target).attr('id') == "#tab_1_2") {
                        $("[id='#tab_1_2']").tab('show');
                        bzqkLoad();
                    } else if ($(e.target).attr('id') == "#tab_1_3") {
                        $("[id='#tab_1_3']").tab('show');
                        zwglLoad();
                    } else if ($(e.target).attr('id') == "#tab_1_4") {
                        $("[id='#tab_1_4']").tab('show');
                        hjxxLoad();
                    } else if ($(e.target).attr('id') == "#tab_1_5") {
                        $("[id='#tab_1_5']").tab('show');
                        txxxLoad();
                    }
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
    function bzqkLoad() {
        var currentId = $("#currentId").val();
        $.ajax({
            url: "${path }/zzb/jggl/b02/ajax/bzqk",
            type: "post",
            data: {"b01Id": "${b01Id}", "currentId": currentId},
            dataType: "html",
            headers: {
                OWASP_CSRFTOKEN: "${sessionScope.OWASP_CSRFTOKEN}"
            },
            success: function (html) {
                var view = $("#tab_show");
                view.html(html);
            },
            error: function (arg1, arg2, arg3) {
                showTip("提示", "编制情况加载失败");
            }
        });
    }

    function zwglLoad() {
        var currentId = $("#currentId").val();
        $.ajax({
            url: "${path }/zzb/jggl/b09/ajax/zwgl",
            type: "post",
            data: {"b01Id": currentId},
            dataType: "html",
            headers: {
                OWASP_CSRFTOKEN: "${sessionScope.OWASP_CSRFTOKEN}"
            },
            success: function (html) {
                var view = $("#tab_show");
                view.html(html);
            },
            error: function (arg1, arg2, arg3) {
                showTip("提示", "职务管理加载失败");
            }
        });
    }
    function hjxxLoad() {
        var currentId = $("#currentId").val();
        $.ajax({
            url: "${path }/zzb/jggl/b10/ajax/hjxx",
            type: "post",
            data: {"b01Id": "${b01Id}", "currentId": currentId},
            dataType: "html",
            headers: {
                OWASP_CSRFTOKEN: "${sessionScope.OWASP_CSRFTOKEN}"
            },
            success: function (html) {
                var view = $("#tab_show");
                view.html(html);
            },
            error: function (arg1, arg2, arg3) {
                showTip("提示", "换届信息加载失败");
            }
        });
    }

    function txxxLoad() {
        var currentId = $("#currentId").val();
        $.ajax({
            url: "${path }/zzb/jggl/b04/ajax/txxx",
            type: "post",
            data: {"b01Id": "${b01Id}", "currentId": currentId},
            dataType: "html",
            headers: {
                OWASP_CSRFTOKEN: "${sessionScope.OWASP_CSRFTOKEN}"
            },
            success: function (html) {
                var view = $("#tab_show");
                view.html(html);
            },
            error: function (arg1, arg2, arg3) {
                showTip("提示", "通信信息加载失败");
            }
        });
    }


    function formSave(sjzt) {
        if (tabIndex == "#tab_1_1") {
            var myVld = new EstValidate("b01Form");
            myLoading.show();
            var bool = myVld.form();
            if (!bool) {
                myLoading.hide();
                return false;
            }
            $.ajax({
                url: "${path }/zzb/jggl/b01/updateOrSave",
                type: "post",
                data: $("#b01Form").serialize(),
                headers: {
                    OWASP_CSRFTOKEN: "${sessionScope.OWASP_CSRFTOKEN}"
                },
                dataType: "json",
                success: function (json) {
                    var oneNodeId= json.oneNodeId;
                    var oneNodeName = json.oneNodeName;
                    showTip("提示", "保存成功", 1500);
                    myLoading.hide();
                    if(oneNodeId !="" && oneNodeId !=undefined){
                        refreshTreeTagByDt("leftB01Tree", oneNodeId);
//                        var zTree = $.fn.zTree.getZTreeObj("leftB01Tree");//取得树对象
//                        var node = zTree.getNodes()[0];// 获取第一个点

                        var b01Id ;
                        var parentB01Id ;
                        var b0101 ;
                            $("#b01Id").val(oneNodeId);//赋值
                            $("#b0101").val(oneNodeName);//赋值
                            b01Id =oneNodeId;
                            b0101 =oneNodeName;
                        $.ajax({
                            async:false,
                            cache:false,
                            type: 'POST',
                            dataType : "html",
                            data:{
                                "b01Id":b01Id,
                                "b0101":b0101
                            },
                            headers: {
                                "OWASP_CSRFTOKEN":"${sessionScope.OWASP_CSRFTOKEN}"
                            },
                            url: "${path}/zzb/jggl/b01/ajax/list",// 请求的action路径
                            error: function () {// 请求失败处理函数
                                alert('请求失败');
                            },
                            success:function(html){
                                $("#rightList").html(html);
                            }
                        });
                       // zTree.selectNode(node);//默认选中
                       // zTree.expandNode(node, true, false , true);//展开
                    }else {
                        setTimeout(function () {
                            cancel()
                        }, 1500);
                    }
                },
                error: function () {
                }
            });
        } else if (tabIndex == "#tab_1_2") {
            var myVld = new EstValidate("b02Form");
            myLoading.show();
            var bool = myVld.form();
            if (!bool) {
                myLoading.hide();
                return false;
            }
            var currentId = $("#currentId").val();
            $.ajax({
                url: "${path }/zzb/jggl/b02/updateOrSave?currentId=" + currentId,
                type: "post",
                data: $("#b02Form").serialize(),
                headers: {
                    OWASP_CSRFTOKEN: "${sessionScope.OWASP_CSRFTOKEN}"
                },
                dataType: "json",
                success: function (json) {
                    myLoading.hide();
                    if (json.code == 1) {
                        showTip("提示", "保存成功", 1500)
                        setTimeout(function () {
                            cancel()
                        }, 1500);
                    }
                },
                error: function () {
                }
            });
        } else if (tabIndex == "#tab_1_4") {
            showTip("提示", "保存成功", 1500)
            setTimeout(function () {
                cancel()
            }, 1500);
        } else if (tabIndex == "#tab_1_5") {
            var myVld = new EstValidate("b04Form");
            myLoading.show();
            var bool = myVld.form();
            if (!bool) {
                myLoading.hide();
                return false;
            }
            var currentId = $("#currentId").val();
            $.ajax({
                url: "${path }/zzb/jggl/b04/updateOrSave?currentId=" + currentId,
                type: "post",
                data: $("#b04Form").serialize(),
                headers: {
                    OWASP_CSRFTOKEN: "${sessionScope.OWASP_CSRFTOKEN}"
                },
                dataType: "json",
                success: function (json) {
                    myLoading.hide();
                    if (json.code == 1) {
                        showTip("提示", "保存成功", 1500)
                        setTimeout(function () {
                            cancel()
                        }, 1500);
                    }
                },
                error: function () {
                }
            });
        }
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
