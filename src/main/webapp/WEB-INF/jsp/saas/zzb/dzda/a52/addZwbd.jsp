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

                <span class="hidden-480">增加职位变动</span>

            </div>
            <div class="tools">
                <a href="javascript:location.reload();" class="reload"></a>

            </div>
        </div>
        <form action="${path}/zzb/dzda/a52/ajax/save" class="form-horizontal" id="form1" method="post">

                <div class="row-fluid">
                    <div class="span6 ">
                        <div id="a5204Group" class="control-group">
                            <label class="control-label"><span class="required">*</span>工作部门</label>
                            <div class="controls">
                                <input type="text" class="span8 m-wrap" name="a5204" required maxlength="128" id="a5204" value="" />
                            </div>
                        </div>
                    </div>
                    <div class="span6 ">
                        <div id="a5211Group" class="control-group">
                            <label class="control-label">职务名称</label>
                            <div class="controls">
                                <input type="text" class="span8 m-wrap" name="a5211"  maxlength="128" id="a5211" value="" />
                            </div>
                        </div>
                    </div>
                </div>

            <div class="row-fluid">
                <div class="span6 ">
                        <div id="a5227InGroup" class="control-group">
                            <label class="control-label">任职时间</label>
                            <div class="controls">
                                <input type="text" class="span8 m-wrap" name="a5227In"  maxlength="128" id="a5227In" value="" placeholder="日期格式 例如：2018或201801或20180101" isDate="true" dateformat="yyyy,yyyymm,yyyymmdd"/>
                            </div>
                        </div>
                </div>
                <div class="span6 ">
                        <div id="a5227OutGroup" class="control-group">
                            <label class="control-label">免职时间</label>
                            <div class="controls">
                                <input type="text" class="span8 m-wrap" name="a5227Out"  maxlength="128" id="a5227Out" value="" placeholder="日期格式 例如：2018或201801或20180101" isDate="true" dateformat="yyyy,yyyymm,yyyymmdd"/>
                            </div>
                        </div>
                </div>
            </div>
            <div class="row-fluid">
                <div class="span6 ">
                        <div id="a0245Group" class="control-group">
                            <label class="control-label">任职批准文号</label>
                            <div class="controls">
                                <input type="text" class="span8 m-wrap" name="a0245"  maxlength="128" id="a0245" value="" />
                            </div>
                        </div>
                </div>
                <div class="span6 ">
                        <div id="a0267Group" class="control-group">
                            <label class="control-label">免职批准文号</label>
                            <div class="controls">
                                <input type="text" class="span8 m-wrap" name="a0267"  maxlength="128" id="a0267" value="" />
                            </div>
                        </div>
                </div>
            </div>
            <div class="row-fluid">
                <div class="span6 ">
                    <div id="pxGroup" class="control-group">
                        <label class="control-label"><span class="required">*</span>职务变动顺序号</label>
                        <div class="controls">
                            <input type="text" number="true"  required maxlength="3" class="span8 m-wrap" name="px"  maxlength="128" id="px" value="${sort}" />
                        </div>
                    </div>
                    </div>
            </div>
            <div  >
                <center>
                <div style="margin:auto;">
                    <input type="hidden" name="a38Id" value="${a38Id}">
                    <button class="btn green" type="button" style="padding:7px 20px;" onclick="submitA52()">确定</button>
                    <a class="btn" href="javascript:cencal()"><i class="icon-remove-sign"></i> 取消</a>
                </div>
                </center>
            </div>
        </form>
    </div>

    <script type="text/javascript">
        function cencal(){
            $("#catalogList").html("");
            $("#a52Table").show();
        }
        var addForm = new EstValidate("form1");
        function submitA52(){
            var bool = addForm.form();
            if(bool){
                $.ajax({
                    url : "${path }/zzb/dzda/a52/save",
                    type : "post",
                    data : $('#form1').serialize(),
                    dataType : "json",
                    headers: {
                        "OWASP_CSRFTOKEN":"${sessionScope.OWASP_CSRFTOKEN}"
                    },
                    success : function(json){
                        if(json.success){
                            $.ajax({
                                url : "${path }/zzb/dzda/a52/ajax/list",
                                type : "get",
                                data : {"a38Id":"${a38Id}"},
                                dataType : "html",
                                success : function(html){
                                    console.log(111);
                                    var view = $("#tab_show");
                                    view.html(html);
                                },
                                error : function(arg1, arg2, arg3){
                                    showTip("提示","职务变动加载失败");
                                }
                            });
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