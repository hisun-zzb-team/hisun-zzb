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

                <span class="hidden-480">修改工资变动</span>

            </div>
            <div class="tools">
                <a href="javascript:location.reload();" class="reload"></a>

            </div>
        </div>
        <form action="${path}/zzb/dzda/a32/ajax/update" class="form-horizontal" id="form1" method="post">
            <input type="hidden" id="a0101" name="a0101" value="${a0101 }"/>
            <div class="row-fluid">
                <div class="span6 ">
                    <div id="gzbmGroup" class="control-group">
                        <label class="control-label"><span class="required">*</span>工作部门</label>
                        <div class="controls">
                            <input type="text" class="span10 m-wrap" name="gzbm" required maxlength="128" id="gzbm" value="" />
                        </div>
                    </div>
                </div>
                <div class="span6 ">
                    <div id="zwmcGroup" class="control-group">
                        <label class="control-label">职务名称</label>
                        <div class="controls">
                            <input type="text" class="span10 m-wrap" name="zwmc"  maxlength="128" id="zwmc" value="" />
                        </div>
                    </div>
                </div>
            </div>

            <div class="row-fluid">
                <div class="span6 ">
                    <div id="a3224Group" class="control-group">
                        <label class="control-label">执行职务工资标准：</label>
                        <div class="controls">
                            <input type="text" class="span10 m-wrap" name="a3224"  maxlength="128" id="a3224" value=""/>
                        </div>
                    </div>
                </div>
                <div class="span6 ">
                    <div id="a3234Group" class="control-group">
                        <label class="control-label">职务工资额：</label>
                        <div class="controls">
                            <input type="text" class="span10 m-wrap" name="a3234"  maxlength="128" id="a3234" value=""/>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row-fluid">
                <div class="span6 ">
                    <div id="a3207Group" class="control-group">
                        <label class="control-label">批准日期：</label>
                        <div class="controls">
                            <input type="text" class="span10 m-wrap" name="a3207"  maxlength="128" id="a3207" value="" placeholder="日期格式 例如：2018或201801或20180101" isDate="true" dateformat="yyyy,yyyymm,yyyymmdd"/>
                        </div>
                    </div>
                </div>
                <div class="span6 ">
                    <div id="a3204Group" class="control-group">
                        <label class="control-label">批准机关：</label>
                        <div class="controls">
                            <input type="text" class="span10 m-wrap" name="a3204"  maxlength="128" id="a3204" value="" />
                        </div>
                    </div>
                </div>
            </div>
            <div class="row-fluid">
                <div class="span6 ">
                    <div id="a3211Group" class="control-group">
                        <label class="control-label">批准文号：</label>
                        <div class="controls">
                            <input type="text"  class="span10 m-wrap" name="a3211"  maxlength="128" id="a3211" value="" />
                        </div>
                    </div>
                </div>
                <div class="span6 ">
                    <div id="pxGroup" class="control-group">
                        <label class="control-label"><span class="required">*</span>工资变动顺序号</label>
                        <div class="controls">
                            <input type="text" number="true"  required maxlength="3" class="span10 m-wrap" name="px"  maxlength="128" id="px" value="${sort}" />
                        </div>
                    </div>
                </div>
            </div>
            <div  >
                <center>
                    <div style="margin:auto;">
                        <input type="hidden" name="a38Id" value="${a38Id}">
                        <input type="hidden" name="id" value="${a32.id}">
                        <button class="btn green" id="submitbut" type="button" style="padding:7px 20px;" onclick="submita32()">确定</button>
                        <a class="btn" href="javascript:cencal()"><i class="icon-remove-sign"></i> 取消</a>
                    </div>
                </center>
            </div>
        </form>
    </div>

    <script type="text/javascript">
        function cencal(){
            var isDacx = $("#isDacx").val();
            $.ajax({
                url : "${path }/zzb/dzda/a32/ajax/list",
                type : "get",
                data : {"a38Id":"${a38Id}","isDacx":isDacx},
                dataType : "html",
                headers:{
                    OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
                },
                success : function(html){
                    console.log(111);
                    var view = $("#tab_show");
                    view.html(html);
                },
                error : function(arg1, arg2, arg3){
                    showTip("提示","工资变动加载失败");
                }
            });
        }
        $(function(){
            var isDacx = $("#isDacx").val();
            if(isDacx=='1'){
                $("#submitbut").hide();
            }
            $("#gzbm").val("${a32.gzbm}");
            $("#zwmc").val("${a32.zwmc}");
            $("#a3224").val("${a32.a3224}");
            $("#a3234").val("${a32.a3234}");
            $("#a3207").val("${a32.a3207}");
            $("#a3204").val("${a32.a3204}");
            $("#a3211").val("${a32.a3211}");
            $("#px").val("${a32.px}");
        })
        var addForm = new EstValidate("form1");
        function submita32(){
            var bool = addForm.form();
            if(bool){
                $.ajax({
                    url : "${path }/zzb/dzda/a32/update",
                    type : "post",
                    data : $('#form1').serialize(),
                    dataType : "json",
                    headers: {
                        "OWASP_CSRFTOKEN":"${sessionScope.OWASP_CSRFTOKEN}"
                    },
                    success : function(json){
                        if(json.success){
                            $.ajax({
                                url : "${path }/zzb/dzda/a32/ajax/list",
                                type : "get",
                                data : {"a38Id":"${a38Id}"},
                                dataType : "html",
                                headers:{
                                    OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
                                },
                                success : function(html){
                                    console.log(111);
                                    var view = $("#tab_show");
                                    view.html(html);
                                },
                                error : function(arg1, arg2, arg3){
                                    showTip("提示","工资变动加载失败");
                                }
                            });
                        }else{
                            showTip("提示", "修改失败", 2000);
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