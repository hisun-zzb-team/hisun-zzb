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
                    <div class="portlet-title">

                        <div class="caption">

                            <i class="icon-reorder"></i>

                            <span class="hidden-480">借阅申请</span>

                        </div>
                        <div class="relationbetTop_but">
                            <button id="submitbutAdd" style="margin-left:70px;" type="button" class="btn green mybutton" ><i class='icon-ok'></i> 确定</button>

                            <a class="btn" id="cencal"><i class="icon-remove-sign"></i> 关闭</a>
                        </div>
                    </div>
                    <form action="" class="form-horizontal" id="form1" method="post" enctype="multipart/form-data">
                        <input type="hidden" id="id" name="id" value="${vo.id}"/>
                        <dl class="dlattrbute">
                            <dt><a href="###">申请信息</a></dt>
                            <dd>
                                <div class="control-group" id="e01Z9DamcGroup">
                                    <label class="control-label">借阅档案名称<span class="required">*</span></label>
                                    <div class="controls">
                                        <input type="text" class="span9 m-wrap" required name="e01Z9Damc" maxlength="128" id="e01Z9Damc" value="${vo.e01Z9Damc}"/>
                                    </div>
                                </div>
                                <div id="e01Z907Group" class="control-group">
                                    <label class="control-label">借阅人<span class="required">*</span></label>
                                    <div class="controls">
                                        <input type="text" class="span9 m-wrap" required name="e01Z907" maxlength="128" id="e01Z907" value="${vo.e01Z907}" />
                                    </div>

                                </div>
                                <div id="e01Z901Group" class="control-group" >
                                    <label class="control-label">借阅日期</label>
                                    <div class="controls">
                                        <input type="text" class="span9 m-wrap" name="e01Z901" maxlength="128" id="e01Z901" value="${vo.e01Z901}"
                                               placeholder="日期格式 例如：2018或201801或20180101"  isDate="true" dateformat="yyyy,yyyymm,yyyymmdd"/>
                                    </div>

                                </div>

                                <div id="e01Z904AGroup" class="control-group" >
                                    <label class="control-label">借阅单位名称<span class="required">*</span></label>
                                    <div class="controls">
                                        <input type="text" class="span9 m-wrap" name="e01Z904A" required maxlength="128" id="e01Z904A" value="${vo.e01Z904A}"/>
                                        <%--<SelectTag:SelectTag id="a3627" needNullValue="true"--%>
                                                             <%--valueName="a3627A"--%>
                                                             <%--defaultkeys="" token="${sessionScope.OWASP_CSRFTOKEN}"--%>
                                                             <%--defaultvalues=""--%>
                                                             <%--textClass="span9 m-wrap" radioOrCheckbox="radio"--%>
                                                             <%--selectUrl="${path}/api/dictionary/select?typeCode=GB762-1984"/>--%>
                                    </div>
                                </div>

                                <div id="e01Z911Group" class="control-group">
                                    <label class="control-label">借阅人电话号码</label>
                                    <div class="controls">
                                        <input type="text" class="span9 m-wrap" mobilePhone="true" name="e01Z911" maxlength="128" id="e01Z911" value="${vo.e01Z911}" />
                                    </div>
                                </div>
                                <%--<div id="e01Z9JyztGroup" class="control-group">--%>
                                    <%--<label class="control-label">借阅状态</label>--%>
                                    <%--<div class="controls">--%>
                                        <%--&lt;%&ndash;<input type="text" class="span9 m-wrap" name="e01Z9Jyzt" maxlength="128" id="e01Z9Jyzt" value="" />&ndash;%&gt;--%>
                                        <%--<select class="span9 m-wrap" id="e01Z9Jyzt" name="e01Z9Jyzt" data-placeholder="Choose a Category" tabindex="1" disabled>--%>
                                            <%--<option value=""></option>--%>
                                            <%--<option value="0" selected>申请借阅</option>--%>
                                            <%--<option value="1">未归还</option>--%>
                                            <%--<option value="2">已归还</option>--%>
                                            <%--&lt;%&ndash;<option value="1" <c:if test="${shpc.sjlx eq '1'}">selected</c:if>>干部名单</option>&ndash;%&gt;--%>
                                            <%--&lt;%&ndash;<option value="2" <c:if test="${shpc.sjlx eq '2'}">selected</c:if>>汇报材料</option>&ndash;%&gt;--%>
                                        <%--</select>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                                <div id="e01Z917Group" class="control-group">
                                    <label class="control-label">批准人</label>
                                    <div class="controls">
                                        <input type="text" class="span9 m-wrap" name="e01Z917" maxlength="128" id="e01Z917" value="${vo.e01Z917}" />
                                    </div>
                                </div>
                                <div id="e01Z914Group" class="control-group">
                                    <label class="control-label">借阅理由</label>
                                    <div class="controls">
                                        <input type="text" class="span9 m-wrap" name="e01Z914" maxlength="40" id="e01Z914" value="${vo.e01Z914}" />
                                    </div>
                                </div>
                            <%--<div id="e01Z927Group" class="control-group">--%>
                                    <%--<label class="control-label">归还日期</label>--%>
                                    <%--<div class="controls">--%>
                                        <%--<input type="text" class="span9 m-wrap" name="e01Z927" maxlength="128" id="e01Z927" value=""--%>
                                               <%--placeholder="日期格式 例如：2018或201801或20180101"  isDate="true" dateformat="yyyy,yyyymm,yyyymmdd"/>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                                <%--<div id="e01Z931Group" class="control-group">--%>
                                    <%--<label class="control-label">借阅经办人</label>--%>
                                    <%--<div class="controls">--%>
                                        <%--<input type="text" class="span9 m-wrap" name="e01Z931" maxlength="128" id="e01Z931" value="" />--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                                <%--<div id="e01Z934Group" class="control-group">--%>
                                    <%--<label class="control-label">归还经办人</label>--%>
                                    <%--<div class="controls">--%>
                                        <%--<input type="text" class="span9 m-wrap" name="e01Z934" maxlength="128" id="e01Z934" value="" />--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                                <%--<div id="e01Z941Group" class="control-group">--%>
                                    <%--<label class="control-label">备注</label>--%>
                                    <%--<div class="controls">--%>
                                        <%--<textarea id="e01Z941" name="e01Z941" class="span9 m-wrap"  style="resize: none;">${vo.e01Z941}</textarea>--%>
                                        <%--&lt;%&ndash;<input type="text" class="span9 m-wrap" name="e01Z941" maxlength="128" id="e01Z941" value="" />&ndash;%&gt;--%>
                                    <%--</div>--%>
                                <%--</div>--%>

                                <%--<div class="control-group">--%>
                                    <%--<div class="controls mt10">--%>
                                        <%--<button id="submitbutAdd" style="margin-left:70px;" type="button" class="btn green mybutton" ><i class='icon-ok'></i> 确定</button>--%>

                                        <%--<a class="btn" id="cencal"><i class="icon-remove-sign"></i> 关闭</a>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            </dd>
                        </dl>
                    </form>
                </div>

            </div>

            <%-- END SAMPLE FORM PORTLET--%>
        </div>
    </div>

    <%-- END PAGE CONTENT--%>
</div>
<script type="text/javascript">

    var myVld = new EstValidate("form1");
    $(function(){
        $("#submitbutAdd").on("click",function(){
            var bool = myVld.form();
            if(bool){
                $.ajax({
                    url : "${path}/zzb/dzda/jysq/saveOrUpdate",
                    type : "post",
                    data : $('#form1').serialize(),
                    dataType : "json",
                    headers: {
                        "OWASP_CSRFTOKEN":"${sessionScope.OWASP_CSRFTOKEN}"
                    },
                    success : function(json){
                        showTip("提示","保存成功!",2000);
                        window.location.href ="${path }/zzb/dzda/jysq/list?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}";
                    },
                    error : function(arg1, arg2, arg3){
                        showTip("提示","出错了请联系管理员",2000);
                    }
                });

            }
        });
    });

    $(function(){
        $("#cencal").on("click",function(){
            window.location.href ="${path }/zzb/dzda/jysq/list?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}";
        });
    });
</script>
</body>
</html>
