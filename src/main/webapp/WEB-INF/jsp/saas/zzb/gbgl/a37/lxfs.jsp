<%@ taglib prefix="Select" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jsp/inc/servlet.jsp" %>
<%@include file="/WEB-INF/jsp/inc/taglib.jsp" %>
<%--
  ~ Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
  ~ http://www.hn-hisun.com
  ~ 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
  --%>
<title>增加通信信息</title>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<div class="container-fluid">

    <div class="row-fluid">
        <div class="span12">
            <%-- BEGIN SAMPLE FORM PORTLET 表单主体--%>

            <div class="portlet box grey">
                <div class="portlet-title">

                    <div class="relationbetTop_but">
                    </div>
                </div>
                <form action="" class="form-horizontal" id="a37Form" name="a37Form" method="post">
                    <input type="hidden" name="a3700" value="${vo.a3700 }"/>
                    <dl class="dlattrbute">
                        <dd>
                            <div class="row-fluid">
                                <div class="span6 ">
                                    <div id="a3701Group" class="control-group">
                                        <label class="control-label">工作单位通信地址</label>
                                        <div class="controls">
                                            <input type="text" class="span10 m-wrap" name="a3701" id="a3701"
                                                   maxlength="128" value="${vo.a3701 }"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="span6 ">
                                    <div id="a3704Group" class="control-group">
                                        <label class="control-label">工作单位邮编</label>
                                        <div class="controls">
                                            <input type="text" class="span10 m-wrap" name="a3704" id="a3704"
                                                   maxlength="128" value="${vo.a3704 }"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row-fluid">

                                <div class="span6 ">
                                    <div id="a3707AGroup" class="control-group">
                                        <label class="control-label">办公电话</label>
                                        <div class="controls">
                                            <input type="text" class="span10 m-wrap" name="a3707A" id="a3707A"
                                                   maxlength="128" value="${vo.a3707A }"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="span6 ">
                                    <div id="a3707BGroup" class="control-group">
                                        <label class="control-label">住宅电话</label>
                                        <div class="controls">
                                            <input type="text" class="span10 m-wrap" name="a3707B" id="a3707B"
                                                   maxlength="128" value="${vo.a3707B }"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row-fluid">

                                <div class="span6 ">
                                    <div id="a3707CGroup" class="control-group">
                                        <label class="control-label">移动电话</label>
                                        <div class="controls">
                                            <input type="text" class="span10 m-wrap" name="a3707C" id="a3707C"
                                                   maxlength="128" value="${vo.a3707C }"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="span6 ">
                                    <div id="a3708Group" class="control-group">
                                        <label class="control-label">电子邮箱</label>
                                        <div class="controls">
                                            <input type="text" class="span10 m-wrap" name="a3708" id="a3708"
                                                   maxlength="128" value="${vo.a3708 }"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row-fluid">

                                <div class="span6 ">
                                    <div id="a3711Group" class="control-group">
                                        <label class="control-label">家庭地址</label>
                                        <div class="controls">
                                            <input type="text" class="span10 m-wrap" name="a3711" id="a3711"
                                                   maxlength="128" value="${vo.a3711 }"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="span6 ">
                                    <div id="a3714Group" class="control-group">
                                        <label class="control-label">家庭地址邮编</label>
                                        <div class="controls">
                                            <input type="text" class="span10 m-wrap" name="a3714" id="a3714"
                                                   maxlength="128" value="${vo.a3714 }"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </dd>
                    </dl>
                </form>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="${path }/js/common/DataValidate.js"></script>
<script type="text/javascript">
    $(function () {
    })
</script>