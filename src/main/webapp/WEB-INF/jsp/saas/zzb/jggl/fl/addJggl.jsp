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
<style type="text/css">
    ul.ztree{margin-bottom: 10px; background:#ffffff !important;}
</style>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>添加机构管理</title>
    <link rel="stylesheet" type="text/css" href="${path}/css/bootstrap-fileupload.css" />
    <script src="${path}/js/bootstrap-fileupload.js"  type="text/javascript"></script>
</head>
<body>
<div class="container-fluid">

    <div class="row-fluid">
        <div class="span12">
            <%-- BEGIN SAMPLE FORM PORTLET 表单主体--%>

            <div class="portlet box grey">
                <div class="portlet-body form">
                    <!-- BEGIN FORM-->

                    <form action="" class="form-horizontal" id="form1" method="post">
                        <input type="hidden" name="bflId" id="bflId" value="${bflId }">
                        <input type="hidden" name="jgIds" id="jgIds" value="${jgIds }">
                        <input type="hidden" name="jgNames" id="jgNames" value="${jgNames }">

                        <%--<Tree:tree id="jgQuery" treeUrl="${path}/api/b01/dtjz/tree" token="${sessionScope.OWASP_CSRFTOKEN}" defaultkeys="${jgQuery}" defaultvalues="${jgNameQuery}"--%>
                                   <%--onClick="" radioOrCheckbox="checkbox" submitType="post" dataType="json" isSearch="true" checkedByTitle="true" chkboxType="'Y': 's', 'N': 's'"/>--%>

                        <Tree:tree id="jgQuery" treeUrl="${path}/api/b01/dtjz/tree" token="${sessionScope.OWASP_CSRFTOKEN}" isSearch="true" checkedByTitle="true"
                                   chkboxType="'Y': 's', 'N': 's'" submitType="post" dataType="json" isSelectTree="false" dtjz="true" valueName="jgNameQuery"
                                   defaultkeys="${jgQuery}" radioOrCheckbox="checkbox" defaultvalues="${jgNameQuery}"/>
                        <%--<Tree:tree id="jgQuery" valueName="jgQuery"  selectClass="span8 m-wrap" height="30px" treeUrl="${path}/zzb/jggl/fl/tree" token="${sessionScope.OWASP_CSRFTOKEN}"--%>
                                   <%--submitType="get" dataType="json" isSearch="false" radioOrCheckbox="checkbox" checkedByTitle="true" isSelectTree="true" defaultkeys="${jgQuery}" defaultvalues="${jgQuery}"/>--%>

                        <div class="control-group">
                                <div class="controls mt10" style="margin-left: 180px">
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
    var form1 = new EstValidate("form1");
    $(function(){
        changeTreeDivHeight();
        //当浏览器大小改变的时候,要重新计算
        $(window).resize(function(){
            changeTreeDivHeight();
        })
        var treeObj = $.fn.zTree.getZTreeObj("jgQuery");
        treeObj.expandNode(treeObj.getNodes()[0],true,false,false,true);
    });
    function changeTreeDivHeight(){
        var divHeight = $(window).height()-280;
        $("#jgQuery_div").css('height',divHeight);
    }
    function formSubmit(){
        var treeObj = $.fn.zTree.getZTreeObj("jgQuery");
        var nodes = treeObj.getCheckedNodes(true);
        var idString="";
        for(var i=0;i<nodes.length;i++){
            if(idString==""){
                idString = nodes[i].id;
            }else{
                idString =  idString + ","+ nodes[i].id;
            }
        }
        if(idString==""){
            showTip("提示","请选择要授权的材料",2000);
        }

        var bflId = $("#bflId").val();
        var fl = $("#fl").val();
        var parentBFlId = $("#parentBFlId").val();
        var bool = form1.form();
        if(bool){
            $("#form1").ajaxSubmit({
                type:"POST",
                url:"${path}/zzb/jggl/fl/saveJg",
                dataType : "json",
                enctype : "multipart/form-data",
                headers:{
                    "OWASP_CSRFTOKEN":'${sessionScope.OWASP_CSRFTOKEN}'
                },
                data : {
                    "idString":idString,
                },
                success:function(json){
                    showTip("提示","设置成功!",2000);
                    $('#addModal').modal('hide');
                    $('#addDiv').html("");

                    $.ajax({
                        async:false,
                        type:"POST",
                        url:"${path}/zzb/jggl/fl/ajax/list",
                        dataType : "html",
                        headers:{
                            "OWASP_CSRFTOKEN":'${sessionScope.OWASP_CSRFTOKEN}'
                        },
                        data : {
                            "flQuery":fl,
                            "bflId":bflId,
                            "parentBFlId":parentBFlId,
                            "key":"2"
                        },
                        success:function(html){
                            $("#rightList").html(html);
                        },
                        error : function(){
                            myLoading.hide();
                            showTip("提示","出错了,请检查网络!",2000);
                        }
                    });
                },
                error : function(){
                    showTip("提示","设置失败!",2000);
                }
            });
        }
    }
</script>
</body>
</html>
