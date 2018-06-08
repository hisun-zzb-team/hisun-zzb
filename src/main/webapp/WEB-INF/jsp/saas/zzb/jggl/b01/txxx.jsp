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
                <form action="" class="form-horizontal" id="b04Form" method="post">
                    <input type="hidden" name="b0400" value="${vo.b0400 }"/>
                    <dl class="dlattrbute">
                        <dd>
                            <div class="row-fluid">
                                <div class="span6 ">
                                    <div id="b0404Group" class="control-group">
                                        <label class="control-label"><span class="Required">*</span>机构地址</label>
                                        <div class="controls">
                                            <input type="text" class="span10 m-wrap" name="b0404" id="b0404" required
                                                    maxlength="128" value="${vo.b0404 }"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="span6 ">
                                    <div id="b0401Group" class="control-group">
                                        <label class="control-label">机构邮政编码</label>
                                        <div class="controls">
                                            <input type="text" class="span10 m-wrap" name="b0401" id="b0401"
                                                   maxlength="128" value="${vo.b0401 }"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row-fluid">

                                <div class="span6 ">
                                    <div id="b0407Group" class="control-group">
                                        <label class="control-label">电话号码</label>
                                        <div class="controls">
                                            <input type="text" class="span10 m-wrap" name="b0407" id="b0407"
                                                   maxlength="128" value="${vo.b0407 }"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="span6 ">
                                    <div id="b0411AGroup" class="control-group">
                                        <label class="control-label">传真号码</label>
                                        <div class="controls">
                                            <input type="text" class="span10 m-wrap" name="b0411" id="b0411"
                                                   maxlength="128" value="${vo.b0411 }"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row-fluid">

                                <div class="span6 ">
                                    <div id="b0417Group" class="control-group">
                                        <label class="control-label">机构网址</label>
                                        <div class="controls">
                                            <input type="text" class="span10 m-wrap" name="b0417" id="b0417"
                                                   maxlength="128" value="${vo.b0417 }"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="span6 ">
                                    <div id="b0421Group" class="control-group">
                                        <label class="control-label">电子邮箱</label>
                                        <div class="controls">
                                            <input type="text" class="span10 m-wrap" name="b0421" id="b0421"
                                                   maxlength="128" value="${vo.b0421 }"/>
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