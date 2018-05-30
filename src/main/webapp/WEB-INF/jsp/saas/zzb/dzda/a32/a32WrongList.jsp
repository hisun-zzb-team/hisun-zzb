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
    <title>导入文件错误列表</title>
    <link rel="stylesheet" type="text/css" href="${path}/css/bootstrap-fileupload.css" />
    <script src="${path}/js/bootstrap-fileupload.js"  type="text/javascript"></script>
</head>
<body>
<div class="container-fluid">

    <div class="row-fluid">
        <div class="portlet-body" >
            <table class="table table-striped table-bordered table-hover dataTable table-set">
                <thead>

                <TR height=28>
                    <th width="150">错误列表</th>
                    <th>错误位置</th>
                    <th width=150>错误原因</th>
                </thead>
                <tbody>
                <c:forEach items="${datas}" var="vo">
                    <tr style="text-overflow:ellipsis;">
                        <TD><c:out value="${vo.wrongExcel}"></c:out></TD>
                        <TD><c:out value="${vo.lines}"></c:out></TD>
                        <TD ><c:out value="${vo.reason}"></c:out></TD>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <%-- END PAGE CONTENT--%>
</div>
<script type="text/javascript">
    function queryUser(){
        var value = $("#a0101").val();
        if(value == "" || value == null){
            showTip("提示","请输入查阅人信息",1000);
            return;
        }else {
            $.ajax({
                url : "${path }/zzb/dzda/cysq/ajax/getDaxx",
                type : "get",
                data : {"param":value},
                dataType : "json",
                headers:{
                    OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
                },
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
                        showTip("提示","不存在此档案",1500);
                    }

                },
                error : function(arg1, arg2, arg3){
                    showTip("提示","加载失败");
                }
            });
        }
    }

    var myVld = new EstValidate("form1");
    function formSubmit(){
        var a0101 = $("#a0101").val();
        if(a0101 == "" || a0101 == null){
            showTip("提示","请输入查阅人信息",1000);
            return;
        }else {
            $.ajax({
                url : "${path }/zzb/dzda/cysq/ajax/getDaxx",
                type : "get",
                data : {"param":a0101},
                dataType : "json",
                headers:{
                    OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
                },
                success : function(json){
                    if(json.success){
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
                            url : "${path }/zzb/dzda/cysq/save",
                            type : "post",
                            dataType : "json",
                            enctype : "multipart/form-data",
                            headers: {
                                "OWASP_CSRFTOKEN":"${sessionScope.OWASP_CSRFTOKEN}"
                            },
                            success : function(data){
                                if(data.success){
                                    // myLoading.hide();
                                    $('#addModal').modal('hide');
                                    $('#addDiv').html("");
                                    showTip("提示", "申请成功！", 1500);
                                }else{
                                    showTip("提示", json.message, 2000);
                                }
                            },
                            error : function(arg1, arg2, arg3){
                                //myLoading.hide();
                                showTip("提示","出错了请联系管理员");
                            }
                        });
                    }else {
                        showTip("提示","不存在此档案",1500);
                    }

                },
                error : function(arg1, arg2, arg3){
                    showTip("提示","加载失败");
                }
            });
        }

    }

    function setName(obj) {
        if (obj.value != "") {
            var fileName  = obj.value.substring(obj.value.lastIndexOf('\\')+1);
            fileName = fileName.substring(0,fileName.lastIndexOf('.'));
            if($("#pcmc").val()==""){
                $("#pcmc").val(fileName);
            }
        }
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
