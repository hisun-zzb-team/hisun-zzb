<%@ page import="com.hisun.util.DateUtil" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/inc/servlet.jsp" %>
<%@include file="/WEB-INF/jsp/inc/taglib.jsp" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>增加职务信息</title>
</head>
<body>
<div >
    <!-- BEGIN FORM-->
    <div class="portlet box grey">

        <div class="portlet-title">

            <div class="caption">

                <i class="icon-reorder"></i>

                <span class="hidden-480">修改换届信息</span>

            </div>
            <div class="tools">
                <a href="javascript:location.reload();" class="reload"></a>

            </div>
        </div>
        <div id="addhjxxDiv" class="portlet-body">
            <form action="${path }/zzb/jggl/b10/updateOrSave?currentId=${currentId}" class="form-horizontal" id="b10Form" method="post">
                <input type="hidden" name="b1000" value="${vo.b1000 }"/>
                <dl class="dlattrbute">
                    <dd>
                        <div class="row-fluid">
                            <div class="span6 ">
                                <div id="b1001Group" class="control-group">
                                    <label class="control-label">届次</label>
                                    <div class="controls">
                                        <input type="text" class="span10 m-wrap" name="b1001" id="b1001"
                                               number="true" maxlength="128" value="${vo.b1001 }"/>
                                    </div>
                                </div>
                            </div>
                            <div class="span6 ">
                                <div id="b1004Group" class="control-group">
                                    <label class="control-label">换届日期</label>
                                    <div class="controls">
                                        <input type="text" class="span10 m-wrap"
                                               placeholder="日期格式 例如：2018或201801或20180101" isDate="true"
                                               dateformat="yyyy,yyyymm,yyyymmdd"
                                               value="${vo.b1004 }" name="b1004"
                                               id="b1004"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row-fluid">

                            <div class="span6 ">
                                <div id="bHjnxGroup" class="control-group">
                                    <label class="control-label">换届年限</label>
                                    <div class="controls">
                                        <input type="text" class="span10 m-wrap" name="bHjnx" id="bHjnx"
                                               number="true"
                                               maxlength="128" value="${vo.bHjnx }"/>
                                    </div>
                                </div>
                            </div>
                            <div class="span6 ">
                                <div id="b1007Group" class="control-group">
                                    <label class="control-label">换届原因</label>
                                    <div class="controls">
                                            <textarea id="b1007" name="b1007" class="span10 m-wrap" maxlength="512"
                                                      value="${vo.b1007}"
                                                      rows="2" style="resize: none;">${vo.b1007}</textarea>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div  >
                            <center>
                                <div style="margin:auto;">
                                    <button class="btn green" type="button" onclick="update()" >确定</button>
                                    <a class="btn" href="javascript:cencal()"><i class="icon-remove-sign"></i> 取消</a>
                                </div>
                            </center>
                        </div>
                    </dd>
                </dl>
            </form>
        </div>

    </div>
</div>
<script type="text/javascript" src="${path }/js/common/loading.js"></script>
<script type="text/javascript">
    var b01Id = "${b01Id}";
    function update() {
        $.ajax({
            url : "${path }/zzb/jggl/b10/updateOrSave?currentId="+b01Id,
            type : "post",
            data : $("#b10Form").serialize(),
            headers:{
                OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
            },
            dataType : "json",
            success : function(json){
                if(json.code==1){
                    $.ajax({
                        url : "${path }/zzb/jggl/b10/ajax/hjxx",
                        type : "post",
                        data : {"currentId":b01Id},
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

            },
            error : function(){
            }
        });
    }
    function cencal() {
            $.ajax({
                url : "${path }/zzb/jggl/b10/ajax/hjxx",
                type : "post",
                data : {"currentId":b01Id},
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
</script>
</body>
</html>
