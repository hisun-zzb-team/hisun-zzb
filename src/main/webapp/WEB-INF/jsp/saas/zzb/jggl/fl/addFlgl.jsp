<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/inc/servlet.jsp" %>
<%@include file="/WEB-INF/jsp/inc/taglib.jsp" %>
<%--
  ~ Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
  ~ http://www.hn-hisun.com
  ~ 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
  --%>

<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<div >
    <!-- BEGIN FORM-->
    <div class="portlet box grey">

        <div class="portlet-title">

            <div class="caption">

                <i class="icon-reorder"></i>

                <span class="hidden-480">增加分类</span>

            </div>
            <div class="tools">
                <a href="javascript:location.reload();" class="reload"></a>

            </div>
        </div>
        <form action="${path}/zzb/dzda/e01z2/ajax/save" class="form-horizontal" id="form1" method="post">

            <input type="hidden" id="bflId" name="bflId" value="${bflId }">
            <div id="flGroup" class="control-group">
                <label class="control-label">分类名称<span class="required">*</span></label>
                <div class="controls">
                    <input size="16" type="text" required class="span10 m-wrap" value="${fl }"
                           id="fl" name="fl" >
                </div>

            </div>

            <div id="pxGroup" class="control-group">
                <label class="control-label">排序<span class="required">*</span></label>
                <div class="controls">
                    <input size="16" type="text" number="true" class="span10 m-wrap" value="${px }"
                           id="px" name="px" >
                </div>

            </div>

            <div  >
                <center>
                    <div style="margin:auto;">
                        <input type="hidden" name="a38Id" value="${a38Id}">
                        <button class="btn green" type="button" style="padding:7px 20px;" onclick="submite()">确定</button>
                        <a class="btn" href="javascript:cencal()"><i class="icon-remove-sign"></i> 取消</a>
                    </div>
                </center>
            </div>
        </form>
    </div>

    <script type="text/javascript">
        function cencal(){
            $.ajax({
                url : "${path }/zzb/jggl/fl/ajax/list",
                type : "get",
                data : {"bflId":"${bflId}","key":"1"},
                dataType : "html",
                headers:{
                    OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
                },
                success : function(html){
                    $("#rightList").html(html);
                },
                error : function(arg1, arg2, arg3){
                    showTip("提示","加载失败");
                }
            });
        }
        var addForm = new EstValidate("form1");
        function submite(){
            var bool = addForm.form();
            if(bool){
                $.ajax({
                    url : "${path }/zzb/jggl/fl/saveFl",
                    type : "post",
                    data : $('#form1').serialize(),
                    dataType : "json",
                    headers: {
                        "OWASP_CSRFTOKEN":"${sessionScope.OWASP_CSRFTOKEN}"
                    },
                    success : function(json){
                        if(json.success){
                            window.location.href = "${path }/zzb/jggl/fl/flManage?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}";
                        }else{
                            showTip("提示", "新增失败", 2000);
                        }
                    },
                    error : function(arg1, arg2, arg3){
                        showTip("提示","出错了请联系管理员",2000);
                    }
                });

            }
        }
    </script>
</div>