<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
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

        <span class="hidden-480">添加机构</span>

    </div>
    <div class="clearfix fr">
        <button type="button" class="btn green" onclick="formSave('1')"><i class="icon-ok"></i> 保存 </button>
        <a class="btn"  onclick="cancel()"><i class="icon-remove-sign"></i> 取消</a>
    </div>
</div>

<!--BEGIN TABS-->
<div class="tabbable tabbable-custom" style="margin-bottom: 0px">
    <ul class="nav nav-tabs" style="font-size: 14px;font-weight: bold;" id="tabs">
        <li class="active"><a id="#tab_1_1" href="#tab_1_1" data-toggle="tab">基本信息</a></li>
        <li ><a id="#tab_1_2" href="#tab_1_1" data-toggle="tab">编制情况</a></li>
        <li><a id="#tab_1_3" href="#tab_1_1" data-toggle="tab">职务管理</a></li>
        <li><a id="#tab_1_4" href="#tab_1_1" data-toggle="tab">换届信息</a></li>
        <li><a id="#tab_1_5" href="#tab_1_1" data-toggle="tab">通信信息</a></li>
    </ul>
    <div class="tab-content" style="border:none; border-top:solid 1px #e4e4e4; padding:5px 0;">
        <input type="hidden" id="id" name="id" value="${id}">
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
        baseLoad();
        $('a[data-toggle="tab"]').on('show.bs.tab', function (e) {
            if($(e.target).attr('id')!='#tab_1_1'&& tabIndex=="#tab_1_1"){
                var myVld = new EstValidate("b01Form");
                myLoading.show();
                var bool = myVld.form();
                if(!bool){
                    myLoading.hide();
                    return false;
                }
                tabSaveData(e);
            }else if($(e.target).attr('id')!='#tab_1_2'&& tabIndex=="#tab_1_2"){
                var myVld = new EstValidate("b02Form");
                myLoading.show();
                var bool = myVld.form();
                if(!bool){
                    myLoading.hide();
                    return false;
                }
                b02tabSave(e);
            }
            else{
                if($(e.target).attr('id')=="#tab_1_1") {
                    baseLoad();
                }else if($(e.target).attr('id')=="#tab_1_2"){
                    bzqkLoad();
                }else if($(e.target).attr('id')=="#tab_1_3"){
                    zwglLoad();
                }else if($(e.target).attr('id')=="#tab_1_4"){
                    hjxxLoad();
                }else if($(e.target).attr('id')=="#tab_1_5"){
                    txxxLoad();
                }
                tabIndex = $(e.target).attr('id');
            }
        });
    });
    function b02tabSave(e) {
        var b01Id = $("#id").val();
        $.ajax({
            url : "${path }/zzb/jggl/b02/updateOrSave?b01Id="+b01Id,
            type : "post",
            data : $("#b02Form").serialize(),
            headers:{
                OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
            },
            dataType : "json",
            success : function(json){
                if(json.code==1){
                    myLoading.hide();
                    tabIndex = $(e.target).attr('id');
                    if($(e.target).attr('id')=="#tab_1_1"){
                        $("[id='#tab_1_1']").tab('show');
                        baseLoad();
                    }else if($(e.target).attr('id')=="#tab_1_3"){
                        $("[id='#tab_1_3']").tab('show');
                        zwglLoad();
                    }else if($(e.target).attr('id')=="#tab_1_4"){
                        $("[id='#tab_1_4']").tab('show');
                        hjxxLoad();
                    }else if($(e.target).attr('id')=="#tab_1_5"){
                        $("[id='#tab_1_5']").tab('show');
                        txxxLoad();
                    }
                }else{
                    $("[id='#tab_1_2']").tab('show');
                    myLoading.hide();
                    showTip("提示", json.message, 2000);
                    return false;
                }
            },
            error : function(){
                $("[id='#tab_1_1']").tab('show');
                console.log("我这里出错了")
                showTip("提示","出错了,请检查网络!",2000);
                myLoading.hide();
                return false;
            }
        });
    }

    function tabSaveData(e){
        $.ajax({
            url : "${path }/zzb/jggl/b01/updateOrSave",
            type : "post",
            data : $("#b01Form").serialize(),
            headers:{
                OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
            },
            dataType : "json",
            success : function(json){
                if(json.code==1){
                    myLoading.hide();
                    tabIndex = $(e.target).attr('id');
                    if($(e.target).attr('id')=="#tab_1_2"){
                        $("[id='#tab_1_2']").tab('show');
                        bzqkLoad();
                    }else if($(e.target).attr('id')=="#tab_1_3"){
                        $("[id='#tab_1_3']").tab('show');
                        zwglLoad();
                    }else if($(e.target).attr('id')=="#tab_1_4"){
                        $("[id='#tab_1_4']").tab('show');
                        hjxxLoad();
                    }else if($(e.target).attr('id')=="#tab_1_5"){
                        $("[id='#tab_1_5']").tab('show');
                        txxxLoad();
                    }
                }else{
                    $("[id='#tab_1_1']").tab('show');
                    myLoading.hide();
                    showTip("提示", json.message, 2000);
                    return false;
                }
            },
            error : function(){
                $("[id='#tab_1_1']").tab('show');
                console.log("我这里出错了")
                showTip("提示","出错了,请检查网络!",2000);
                myLoading.hide();
                return false;
            }
        });
    }

    //基本信息
    function baseLoad(){
        myLoading.show();
        $.ajax({
            url : "${path}/zzb/jggl/b01/ajax/jbxx",
            type : "post",
            data : {"b01Id":"${b01Id}","bSjlx":"${bSjlx}","id":"${id}","isAdd":"${isAdd}"},
            dataType : "html",
            headers:{
                OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
            },
            success : function(html){
                var view = $("#tab_show");
                view.html(html);
                myLoading.hide();
            },
            error : function(arg1, arg2, arg3){
                showTip("提示","基本信息加载失败");
            }
        });
    }
    function bzqkLoad(){
        $.ajax({
            url : "${path }/zzb/jggl/b02/ajax/bzqk",
            type : "post",
            data : {"b01Id":"${b01Id}","id":"${id}"},
            dataType : "html",
            headers:{
                OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
            },
            success : function(html){
                var view = $("#tab_show");
                view.html(html);
            },
            error : function(arg1, arg2, arg3){
                showTip("提示","编制情况加载失败");
            }
        });
    }

    function zwglLoad(){
        $.ajax({
            url : "${path }/zzb/jggl/b09/ajax/zwgl",
            type : "post",
            data : {"b01Id":"${b01Id}"},
            dataType : "html",
            headers:{
                OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
            },
            success : function(html){
                var view = $("#tab_show");
                view.html(html);
            },
            error : function(arg1, arg2, arg3){
                showTip("提示","职务管理加载失败");
            }
        });
    }
    function hjxxLoad(){
        $.ajax({
            url : "${path }/zzb/dzda/a52/ajax/list",
            type : "post",
            data : {"b01Id":"${b01Id}"},
            dataType : "html",
            headers:{
                OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
            },
            success : function(html){
                var view = $("#tab_show");
                view.html(html);
            },
            error : function(arg1, arg2, arg3){
                showTip("提示","换届信息加载失败");
            }
        });
    }

    function txxxLoad(){
        $.ajax({
            url : "${path }/zzb/dzda/a32/ajax/list",
            type : "post",
            data : {"b01Id":"${b01Id}"},
            dataType : "html",
            headers:{
                OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
            },
            success : function(html){
                var view = $("#tab_show");
                view.html(html);
            },
            error : function(arg1, arg2, arg3){
                showTip("提示","通信信息加载失败");
            }
        });
    }


    function formSave(sjzt){
        var bool = true;
        var isSave = false;
        if($("#tabs li[class='active']").find("a").attr("id")=="#tab_1_1"){
            isSave = true;
            bool = a38Form.form();
        }
        if(isSave == true){
            if(bool){
                $("#sjzt").val(sjzt);
                var value=$("#smxh").val();
                if(value!=""){
                    localPost("${path}/zzb/dzda/a38/smxh/check",{
                        "smxh":$("#smxh").val(),
                        "id":$("#id").val()
                    },function(data) {
                        if (!data.success) {
                            showTip("提示", "扫描序号“"+value+"”已经存在，请重新输入！");
                            setTimeout(function(){
                                $("#smxh").focus();
                            },510);
                        }else{
                            saveA38(sjzt);
                        }
                    },"json", {"OWASP_CSRFTOKEN":"${sessionScope.OWASP_CSRFTOKEN}"});
                }else {
                    saveA38(sjzt);
                }
            }
        }else{
            myLoading.show();
            $.ajax({
                url : "${path }/zzb/dzda/a38/update/Sjzt",
                type : "post",
                data : {
                    "a38Ids":"${id}",
                    "sjzt":sjzt
                },
                headers:{
                    OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
                },
                dataType : "json",
                success : function(json){
                    if(json.code==1){
                        myLoading.hide();
                        if(sjzt=="0"){
                            window.location.href = "${path}/zzb/dzda/a38/shList?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}"
                        }else{
                            window.location.href = "${path}/zzb/dzda/a38/list?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}"
                        }
                    }else{
                        myLoading.hide();
                        showTip("提示", json.message, 2000);
                        return false;
                    }
                },
                error : function(){
                    showTip("提示","出错了,请检查网络!",2000);
                    myLoading.hide();
                    return false;
                }
            });
        }
    }

    function saveA38(sjzt){
        myLoading.show();
        $.ajax({
            url : "${path }/zzb/dzda/a38/update",
            type : "post",
            data : $("#a38Form").serialize(),
            headers:{
                OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
            },
            dataType : "json",
            success : function(json){
                if(json.code==1){
                    myLoading.hide();
                    if(sjzt=="0"){
                        window.location.href = "${path}/zzb/dzda/a38/shList?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}"
                    }else{
                        window.location.href = "${path}/zzb/dzda/a38/list?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}"
                    }
                }else{
                    myLoading.hide();
                    showTip("提示", json.message, 2000);
                    return false;
                }
            },
            error : function(){
                showTip("提示","出错了,请检查网络!",2000);
                myLoading.hide();
                return false;
            }
        });
    }

    var delA38ByManage = function(){
        var id = "${id}";
        var itemName = "${a0101}";
        actionByConfirm1(itemName, "${path}/zzb/dzda/a38/delete/" + id,{} ,function(data,status){
            if (data.code == "1") {
                showTip("提示","删除成功", 2000);
                if("${listType}"=="shList"){
                    setTimeout(function(){window.location.href = "${path}/zzb/dzda/a38/shList?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}"},2000);
                }else{
                    setTimeout(function(){window.location.href = "${path}/zzb/dzda/a38/list?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}"},2000);
                }
            }else{
                showTip("提示", data.message, 2000);
            }
        });
    };

    function cancel(){
        window.location.href = "${path}/zzb/jggl/b01/index?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}"
    }
</script>
</body>
</html>
