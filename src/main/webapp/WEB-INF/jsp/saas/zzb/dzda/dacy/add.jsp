<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
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
    <title>档案阅档申请</title>
</head>
<body>
<div class="container-fluid">

    <div class="row-fluid">
        <div class="span12">
            <%-- BEGIN SAMPLE FORM PORTLET 表单主体--%>

            <div class="portlet box grey">



                <div class="portlet-body form">
                    <!-- BEGIN FORM-->

                    <form action="" class="form-horizontal" id="form1" method="post" enctype="multipart/form-data">
                        <input type="hidden" name="id" value="" id="id">
                        <input type="hidden" name="filePath" value="" id="filePath">
                        <div class="control-group" id="a0101Group">
                            <label class="control-label">查阅何人档案</label>
                            <div class="controls">
                                <input type="text" class="span10 m-wrap" name="a0101"  maxlength="200" id="a0101" value="" />
                                <a href="javascript:queryUser()">添加</a>
                            </div>
                        </div>
                        <div  id="a0101ContentGroup" style="display: none">
                            <label class="control-label"><span class="required">*</span></label>
                            <div class="controls">
                                <input type="text" class="span10 m-wrap"  name="a0101Content"  maxlength="200" id="a0101Content" value="" required/>
                            </div>
                        </div>
                        <div class="control-group" id="e01Z807NameGroup">
                            <label class="control-label">查阅人<span class="required">*</span></label>
                            <div class="controls">
                                <input type="text" class="span10 m-wrap" name="e01Z807Name"  maxlength="200" id="e01Z807Name" value=""/>
                            </div>
                        </div>

                        <div id="pcsjValueGroup" class="control-group">
                            <label class="control-label">单位职务</label>
                            <div class="controls">
                                <input size="16" type="text"  class="span10 m-wrap" value=""
                                       id="pcsjValue" name="pcsjValue" >

                                <!--<input type="text" class="span10 m-wrap"  name="pcsj" formatter="yyyymmdd"   maxlength="8" id="pcsj" type="date"/>-->
                            </div>

                        </div>
                        <div id="readContentGroup" class="control-group">
                            <label class="control-label">查阅内容</label>
                            <div class="controls">
                                <input size="16" type="text"  class="span10 m-wrap" value=""
                                       id="readContent" name="readContent" >
                            </div>

                        </div>
                        <div class="control-group" id="e01Z824AGroup">

                            <label class="control-label">查阅单位</label>
                            <div class="controls">
                                <input size="16" type="text"  class="span10 m-wrap" value=""
                                       id="e01Z824A" name="e01Z824A" >
                            </div>
                        </div>
                        <div class="control-group" id="readTimeGroup">

                            <label class="control-label">查阅时间</label>
                            <div class="controls">
                                <input size="16" type="text"  class="span10 m-wrap" value=""
                                       id="readTime" name="readTime"  number="true"  required  maxlength="5">分钟
                            </div>
                        </div>
                        <div class="control-group" id="phoneGroup">

                            <label class="control-label">联系电话</label>
                            <div class="controls">
                                <input size="16" type="text"  class="span10 m-wrap" value=""
                                       id="phone" name="phone" >
                            </div>
                        </div>
                        <div id="applyRemarkGroup" class="control-group">
                            <label class="control-label">备注</label>
                            <div class="controls">
                                <textarea class="span10" style="" rows="2" name="applyRemark" maxlength="400" id="applyRemark" ></textarea>
                            </div>
                        </div>
                        <div  id="applyFileNameGroup" class="control-group" >
                            <label class="control-label">申请材料</label>
                            <div class="controls">
                                <input type="file" class="default" name="applyFileName" id="applyFileName" fileSizeLimit="20" fileType="doc,docx,DOC,DOCX"/>
                                <div class="btn-group" id="gbrmspbDownDiv" <c:if test="">
                                    style="visibility:hidden"</c:if>>
                                    <a class="btn blue" herf="javascript:void(0)" onclick="fileDown()"><i
                                            class="icon-circle-arrow-down"></i>下载文件</a>
                                </div>
                                <p class="textprompt">附件支持的格式有：'doc','docx'</p>
                                <p class="Errorred" id="attachFileError"></p>
                            </div>

                        </div>
                        <div class="control-group">
                            <div class="controls mt10">
                                <button class="btn green" type="button" style="padding:7px 20px;" onclick="formSubmit()">确定</button>

                                <button type="button" class="btn btn-default" data-dismiss="modal"><i class='icon-remove-sign'></i> 关闭</button>
                            </div>
                        </div>
                    </form>
                </div>

            </div>

            <%-- END SAMPLE FORM PORTLET--%>
        </div>
    </div>

    <%-- END PAGE CONTENT--%>
</div>
<script type="text/javascript">
    function queryUser(){
        var value = $("#a0101").val();
        if(value == "" || value == null){
            showTip("提示","请输入查阅人信息");
            return;
        }else {
            $.ajax({
                url : "${path }/zzb/dzda/cysq/ajax/getDaxx",
                type : "get",
                data : {"param":value},
                dataType : "json",
                success : function(json){
                    if(json.success){
                        var view = $("#a0101ContentGroup");
                        var a0101 = $("#a0101Content").val();
                        var value1 = $("#a0101").val();
                        if(a0101 != ""){
                            a0101 = a0101 + "," + value1;
                            $("#a0101Content").val(a0101);
                        }else {
                            $("#a0101Content").val(value1);
                        }

                        var value = $("#a0101").val("");
                        view.show();
                    }else {
                        showTip("提示","不存在此档案");
                    }

                },
                error : function(arg1, arg2, arg3){
                    showTip("提示","加载失败");
                }
            });
        }
    }

 /*   var myLoading = new MyLoading("${path}",20000);
    jQuery(document).ready(function() {
        App.init();
        var startDate = $("#pcsjValue").datepicker({
            language:  'zh-CN',
            format: "yyyymmdd",
            pickerPosition: "bottom-left",
            weekStart : 1,
            autoclose : true
        });
        var sjwcsj = $("#pcsjValue").datepicker({
            language:  'zh-CN',
            format: "yyyymmdd",
            pickerPosition: "bottom-left",
            weekStart : 1,
            autoclose : true
        });

    });*/

    var myVld = new EstValidate("form1");
    function formSubmit(){
        var bool = myVld.form();
        if(!bool){
            return;
        }
        var fileInput = document.getElementById("applyFileName");
        if (fileInput.files.length > 0) {
            var name = fileInput.files[0].name
            var arr = name.split(".");
            if (arr.length < 2 || !(arr[arr.length - 1] == "doc" || arr[arr.length - 1] == "docx" || arr[arr.length - 1] == "DOC" || arr[arr.length - 1] == "DOCX")) {
                showTip("提示", "请上传word文件", 2000);
                return;
            }
        }
        //myLoading.show();
        $("#form1").ajaxSubmit({
            url : "${path }/zzb/dzda/cysq/save",
            type : "post",
            dataType : "json",
            enctype : "multipart/form-data",
            headers: {
                "OWASP_CSRFTOKEN":"${sessionScope.OWASP_CSRFTOKEN}"
            },
            success : function(data){
               // myLoading.hide();
                if(data.success){
                    window.location.href ="${path }/zzb/dzda/cysq/list";
                }else{
                    showTip("提示", json.message, 2000);
                }
            },
            error : function(arg1, arg2, arg3){
                //myLoading.hide();
                showTip("提示","出错了请联系管理员");
            }
        });
    }


    function changeFile(obj){
        if(obj.value =="1"){
            window.document.getElementById("clFileGroup").style.visibility = "hidden";
            window.document.getElementById("mbGroup").style.display = "block";
        }else{
            window.document.getElementById("mbGroup").style.display = "none";
            window.document.getElementById("clFileGroup").style.visibility = "visible";
        }
    }
</script>
</body>
</html>