<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jsp/inc/servlet.jsp" %>
<%@include file="/WEB-INF/jsp/inc/taglib.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

    <link href="${path}/css/common/common.css" rel="stylesheet" type="text/css"/>
    <!-- BEGIN PAGE LEVEL STYLES -->
    <link rel="stylesheet" href="${path }/css/DT_bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="${path }/css/bootstrap-fileupload.css">

    <link href="${path }/css/style.css" rel="stylesheet" type="text/css">
    <!-- END PAGE LEVEL STYLES -->
    <title>档案接收</title>
    <style type="text/css">
    </style>
    <link rel="stylesheet" type="text/css" href="${path}/css/bootstrap-fileupload.css" />
    <script src="${path}/js/bootstrap-fileupload.js"  type="text/javascript"></script>
    <link rel="stylesheet" type="text/css"
          href="${path}/css/select2_metro.css" />
    <link href="${path}/css/common/common.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="${path}/css/DT_bootstrap.css" />
    <script type="text/javascript" src="${path}/js/select2.min.js"></script>
    <script type="text/javascript"
            src="${path}/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="${path}/js/jquery.form.js"></script>
    <script type="text/javascript" src="${path}/js/DT_bootstrap.js"></script>
    <script type="text/javascript" src="${path}/js/bootstrap-datepicker.js"></script>
    <script type="text/javascript" src="${path}/js/bootstrap-datepicker.zh-CN.js"></script>
    <script type="text/javascript" src="${path}/js/bootstrap-datetimepicker.js"></script>
    <script type="text/javascript" src="${path}/js/bootstrap-datetimepicker.zh-CN.js"></script>
</head>
<body>
<div class="container-fluid">

    <div class="row-fluid">
        <div class="span12">
            <%-- BEGIN SAMPLE FORM PORTLET 表单主体--%>

            <div class="portlet box grey">
                <div class="portlet-title">

                    <div class="caption">

                        <i class="icon-reorder"></i>

                        <span class="hidden-480">增加档案接收</span>

                    </div>
                    <div class="clearfix fr">

                        <div class="btn-group" style="padding-bottom: 0px">
                            <button class="btn green" type="button" style="padding:7px 20px; margin: 7px 10px" onclick="formSubmit()">确定</button>
                            <button type="button" class="btn btn-default" onclick="cancel()"><i
                                    class='icon-remove-sign'></i> 关闭
                            </button>
                        </div>
                    </div>
                </div>


                <div class="portlet-body form">
                    <!-- BEGIN FORM-->

                    <form action="" class="form-horizontal" id="form1" method="post" enctype="multipart/form-data">
                        <input type="hidden" name="id" value="" id="id">
                        <input type="hidden" name="filePath" value="" id="filePath">
                        <div class="row-fluid">
                            <div class="span6 ">
                                <div class="control-group" id="nameGroup">
                                    <label class="control-label">档案名称<span class="required">*</span></label>

                                    <div class="controls">
                                        <input type="text" class="span10 m-wrap" name="name" maxlength="200" id="name"
                                               required
                                               value=""/>
                                    </div>

                                </div>
                            </div>
                            <div class="span6 ">
                                <div id="e01Z507AGroup" class="control-group">
                                    <label class="control-label">来件单位名称</label>

                                    <div class="controls">
                                        <input type="text" class="span10 m-wrap" value=""
                                               id="e01Z507A" name="e01Z507A">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row-fluid">
                            <div class="span6 ">
                                <div class="control-group" id="e01Z511Group">
                                    <label class="control-label">正本卷数</label>

                                    <div class="controls">
                                        <input type="text" class="span10 m-wrap" number="true" name="e01Z511"
                                               maxlength="200" id="e01Z511"
                                               value=""/>
                                    </div>
                                </div>
                            </div>
                            <div class="span6 ">
                                <div class="control-group" id="e01Z514Group">
                                    <label class="control-label">副本卷数</label>

                                    <div class="controls">
                                        <input type="text" class="span10 m-wrap" number="true" name="e01Z514"
                                               maxlength="200" id="e01Z514"
                                               value=""/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row-fluid">
                            <div class="span6 ">
                                <div class="control-group" id="e01Z521Group">
                                    <label class="control-label">审核人</label>

                                    <div class="controls">
                                        <input type="text" class="span10 m-wrap" name="e01Z521" maxlength="200"
                                               id="e01Z521"
                                               value=""/>
                                    </div>
                                </div>
                            </div>
                            <div class="span6 ">
                                <div id="e01Z524Group" class="control-group">
                                    <label class="control-label">审核日期</label>
                                    <div class="controls">
                                        <input type="text" class="span10 m-wrap" value="" readonly
                                               id="e01Z524" name="e01Z524">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row-fluid">
                            <div class="span6 ">
                                <div class="control-group" id="e01Z527Group">
                                    <label class="control-label">案卷质量</label>

                                    <div class="controls">
                                        <SelectTag:SelectTag id="e01Z527"  textClass="m-wrap span10" needNullValue="true"  defaultkeys="1"
                                                             token="${sessionScope.OWASP_CSRFTOKEN}"     radioOrCheckbox="radio" selectUrl="${path}/api/dictionary/select?typeCode=DAZL-2018"/>
                                    </div>
                                </div>
                            </div>
                            <div class="span6 ">
                                <div id="e01Z541Group" class="control-group">
                                    <label class="control-label">档案位置</label>

                                    <div class="controls">
                                        <input type="text" class="span10 m-wrap" value=""
                                               id="e01Z541" name="e01Z541">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row-fluid">
                            <div class="span6 ">
                                <div class="control-group" id="e01Z501Group">
                                    <label class="control-label">接收日期</label>

                                    <div class="controls">
                                        <input type="text" class="span10 m-wrap" name="e01Z501" maxlength="200"
                                               id="e01Z501" readonly
                                               value=""/>
                                    </div>
                                </div>
                            </div>
                            <div class="span6 ">
                                <div id="e01Z531Group" class="control-group">
                                    <label class="control-label">回执日期</label>

                                    <div class="controls">
                                        <input type="text" class="span10 m-wrap" value="" readonly
                                               id="e01Z531" name="e01Z531">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row-fluid">
                            <div class="span6 ">
                                <div class="control-group" id="e01Z534Group">
                                    <label class="control-label">入库日期</label>

                                    <div class="controls">
                                        <input type="text" class="span10 m-wrap" name="e01Z534" maxlength="200"
                                               id="e01Z534" readonly
                                               value=""/>
                                    </div>
                                </div>
                            </div>
                            <div class="span6 ">
                                <div id="e01Z537Group" class="control-group">
                                    <label class="control-label">入库审批人</label>

                                    <div class="controls">
                                        <input type="text" class="span10 m-wrap" value=""
                                               id="e01Z537" name="e01Z537">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row-fluid">
                            <div class="span6 ">
                                <div class="control-group" id="sjlyGroup">
                                    <label class="control-label">数据来源</label>

                                    <div class="controls">
                                        <select name="sjly"  class="span10 m-wrap" id="sjly">
                                            <option value=""></option>
                                            <option value="1">导入本单位excel</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="span6 ">
                                <div id="e01Z544Group" class="control-group">
                                    <label class="control-label">备注</label>

                                    <div class="controls">
                                            <textarea class="span10" style="" rows="2" name="e01Z544" maxlength="400"
                                                      id="e01Z544" style="resize: none;"></textarea>
                                    </div>
                                </div>
                            </div>

                        </div>
                        <div class="row-fluid">
                            <div class="span6 ">
                                <div  id="clFileGroup" class="control-group">
                                    <label id="clFilelb" class="control-label">上传文件</label>
                                    <div class="controls">
                                        <div class="fileupload fileupload-new" data-provides="fileupload">
                                            <div class="input-append">
                                                <div class="uneditable-input border_radius_none heig20">
                                                    <i class="icon-file fileupload-exists"></i>
                                                    <span class="fileupload-preview"></span>
                                                </div>
													<span class="btn btn-file border_radius_none">
													<span class="fileupload-new ">选择文件</span>
													<span class="fileupload-exists">修改文件</span>
													<input type="file" class="default " name="clFile" id="clFile" onchange="setName(this)"
                                                          accept="" />
													</span>
                                                <p class="Errorred" id="attachFileError"></p>
                                                <a href="#" class="btn fileupload-exists border_radius_none" data-dismiss="fileupload">移除</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
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
    (function(){
        App.init();

        $("#btn-browseTemplate").bind("change",function(evt){
            if($(this).val()){
                ajaxSubmit();
            }
            $(this).val('');
        });

    })();


    $(function(){
        $('#sjly').change(function(){
            var value =$(this).children('option:selected').val();//这就是selected的值
            if(value ==1){
                $("#clFile").attr("accept","application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            }
        })
    })

    $(function () {
        $('#e01Z524').datepicker({
            format: 'yyyy-mm-dd',
            weekStart: 1,
            autoclose: true,
            todayBtn: 'linked',
            language: 'zh-CN'
        });
        $('#e01Z501').datepicker({
            format: 'yyyy-mm-dd',
            weekStart: 1,
            autoclose: true,
            todayBtn: 'linked',
            language: 'zh-CN'
        });
        $('#e01Z531').datepicker({
            format: 'yyyy-mm-dd',
            weekStart: 1,
            autoclose: true,
            todayBtn: 'linked',
            language: 'zh-CN'
        });
        $('#e01Z534').datepicker({
            format: 'yyyy-mm-dd',
            weekStart: 1,
            autoclose: true,
            todayBtn: 'linked',
            language: 'zh-CN'
        });
    })
    var cancel= function(){
        window.location.href = "${path }/zzb/dzda/dajs/list?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}";
    }

    var myVld = new EstValidate("form1");
    function formSubmit(){
        debugger
        var riq = $("#e01Z534").val();
        var bool = myVld.form();
        if(!bool){
            return;
        }
        var fileInput = document.getElementById("clFile");
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
            url : "${path }/zzb/dzda/dajs/save",
            type : "post",
            dataType : "json",
            enctype : "multipart/form-data",
            headers: {
                "OWASP_CSRFTOKEN":"${sessionScope.OWASP_CSRFTOKEN}"
            },
            success : function(data){
                // myLoading.hide();
                if(data.code==1){
                    window.location.href ="${path }/zzb/dzda/dajs/list?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}";
                }else{
                    showTip("提示", "新增失败", 2000);
                }
            },
            error : function(arg1, arg2, arg3){
                //myLoading.hide();
                showTip("提示","出错了请联系管理员");
            }
        });
    }

</script>
</body>
</html>
