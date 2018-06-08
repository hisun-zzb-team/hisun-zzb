<%@ taglib prefix="Select" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jsp/inc/servlet.jsp" %>
<%@include file="/WEB-INF/jsp/inc/taglib.jsp" %>
<%--
  ~ Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
  ~ http://www.hn-hisun.com
  ~ 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
  --%>
<title>增加换届信息</title>
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
                <form action="" class="form-horizontal" id="b10Form" method="post">
                    <input type="hidden" name="b1000" value="${vo.b1000 }"/>
                    <dl class="dlattrbute">
                        <dd>
                            <div class="row-fluid">
                                <div class="span6 ">
                                    <div id="b1001Group" class="control-group">
                                        <label class="control-label"><span class="Required">*</span>届次</label>
                                        <div class="controls">
                                            <input type="text" class="span10 m-wrap" name="b1001" id="b1001" required
                                                 number="true"  maxlength="128" value="${vo.b1001 }"/>
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
                                            <input type="text" class="span10 m-wrap" name="bHjnx" id="bHjnx" number="true"
                                                   maxlength="128" value="${vo.bHjnx }"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="span6 ">
                                    <div id="b1007Group" class="control-group">
                                        <label class="control-label">换届原因</label>
                                        <div class="controls">
                                            <textarea id="b1007" name="b1007" class="span10 m-wrap" maxlength="512" value="${vo.b1007}"
                                                      rows="2" style="resize: none;"></textarea>
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