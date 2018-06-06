<%@ taglib prefix="Select" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jsp/inc/servlet.jsp" %>
<%@include file="/WEB-INF/jsp/inc/taglib.jsp" %>
<%--
  ~ Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
  ~ http://www.hn-hisun.com
  ~ 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
  --%>
<title>增加编制</title>
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
                <form action="" class="form-horizontal" id="b02Form" method="post">
                    <input type="hidden" name="b02Id" value="${b02Id }"/>
                    <input type="hidden" name="b0200" value="${vo.b0200 }"/>
                    <dl class="dlattrbute">
                        <dd>
                            <div class="row-fluid">
                                <div class="span6 ">
                                    <div id="b0207BGroup" class="control-group">
                                        <label class="control-label"><span class="Required">*</span>编制批准机关</label>
                                        <div class="controls">
                                            <Tree:tree id="b0207B" valueName="b0207A"
                                                        selectClass="span10 m-wrap"
                                                        treeUrl="${path}/api/dictionary/tree?typeCode=ZB02-2006/JGMC"
                                                        token="${sessionScope.OWASP_CSRFTOKEN}"
                                                        submitType="get" dataType="json" isSearch="false"
                                                        checkedByTitle="true" isSelectTree="true"
                                                        defaultkeys="${vo.b0207B}"
                                                        defaultvalues="${vo.b0207A}"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row-fluid">
                                <div class="span6 ">
                                    <div id="b0201Group" class="control-group">
                                        <label class="control-label">编制批准日期</label>
                                        <div class="controls">
                                            <input type="text" class="span10 m-wrap"
                                                   placeholder="日期格式 例如：2018或201801或20180101" isDate="true"
                                                   dateformat="yyyy,yyyymm,yyyymmdd"
                                                   value="${vo.b0201 }" name="b0201"
                                                   id="b0201"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="span6 ">
                                    <div id="b0204Group" class="control-group">
                                        <label class="control-label">编制批准文号</label>
                                        <div class="controls">
                                            <input type="text" class="span10 m-wrap" name="b0204" id="b0204"
                                                   maxlength="128" value="${vo.b0204 }"/>
                                        </div>
                                    </div>
                                </div>

                            </div>
                            <div class="row-fluid">
                                <div class="span6 ">
                                    <div id="b0204AGroup" class="control-group">
                                        <label class="control-label">编制文号</label>
                                        <div class="controls">
                                            <input type="text" class="span10 m-wrap" name="b0204A" id="b0204A"
                                                   maxlength="128" value="${vo.b0204A }"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="span6 ">
                                    <div id="b0221Group" class="control-group">
                                        <label class="control-label">编制性质</label>
                                        <div class="controls">
                                            <SelectTag:SelectTag id="b0221" needNullValue="true"
                                                                 valueName="b0221A"
                                                                 defaultkeys="${vo.b0221}" token="${sessionScope.OWASP_CSRFTOKEN}"
                                                                 defaultvalues="${vo.b0221A}"
                                                                 textClass="span10 m-wrap" radioOrCheckbox="radio"
                                                                 selectUrl="${path}/api/dictionary/select?typeCode=ZB61-2006/BZXZ"/>
                                        </div>
                                    </div>
                                </div>

                            </div>

                            <div class="row-fluid">
                                <div class="span6 ">
                                    <div id="b0214Group" class="control-group">
                                        <label class="control-label">内设单位名称</label>
                                        <div class="controls">
                                            <input type="text" class="span10 m-wrap" name="b0214" id="b0214"
                                                   maxlength="128" value="${vo.b0214 }"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="span6 ">
                                    <div id="b0211Group" class="control-group">
                                        <label class="control-label">内设单位数</label>
                                        <div class="controls">
                                            <input type="text" class="span10 m-wrap" name="b0211" id="b0211" number="true"
                                                   maxlength="128" value="${vo.b0211 }"/>
                                        </div>
                                    </div>
                                </div>

                            </div>
                            <div class="row-fluid">
                                <div class="span6 ">
                                    <div id="b0217Group" class="control-group">
                                        <label class="control-label">内设单位级别</label>
                                        <div class="controls">
                                            <input type="text" class="span10 m-wrap" name="b0211" id="b0217"
                                                   maxlength="128" value="${vo.b0217 }"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="span6 ">
                                    <div id="b0224Group" class="control-group">
                                        <label class="control-label">批准编制总数</label>
                                        <div class="controls">
                                            <input type="text" class="span10 m-wrap" id="b0224" name="b0224" maxlength="64"
                                                   value="${vo.b0224}" number="true" />
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row-fluid">
                                <div class="span6 ">
                                    <div id="b0227Group" class="control-group">
                                        <label class="control-label">行政编制数</label>
                                        <div class="controls">
                                            <input type="text" class="span10 m-wrap" id="b0227" name="b0227" maxlength="64"
                                                   value="${vo.b0227}" number="true" />
                                        </div>
                                    </div>
                                </div>
                                <div class="span6 ">
                                    <div id="b0231Group" class="control-group">
                                        <label class="control-label">事业编制数</label>
                                        <div class="controls">
                                            <input type="text" class="span10 m-wrap" id="b0231" name="b0231" maxlength="64"
                                                   value="${vo.b0231}" number="true" />
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row-fluid">
                                <div class="span6 ">
                                    <div id="b0234Group" class="control-group">
                                        <label class="control-label">企业编制数</label>
                                        <div class="controls">
                                            <input type="text" class="span10 m-wrap" id="b0234" name="b0234" maxlength="64"
                                                   value="${vo.b0234}" number="true" />
                                        </div>
                                    </div>
                                </div>
                                <div class="span6 ">
                                    <div id="b0237Group" class="control-group">
                                        <label class="control-label">社团编制数</label>
                                        <div class="controls">
                                            <input type="text" class="span10 m-wrap" id="b0237" name="b0237" maxlength="64"
                                                   value="${vo.b0237}" number="true" />
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row-fluid">
                                <div class="span6 ">
                                    <div id="b0241Group" class="control-group">
                                        <label class="control-label">单位领导职数</label>
                                        <div class="controls">
                                            <input type="text" class="span10 m-wrap" id="b0241" name="b0241" maxlength="64"
                                                   value="${vo.b0241}" number="true" />
                                        </div>
                                    </div>
                                </div>
                                <div class="span6 ">
                                    <div id="b0244Group" class="control-group">
                                        <label class="control-label">领导同级非领导职务职数</label>
                                        <div class="controls">
                                            <input type="text" class="span10 m-wrap" id="b0244" name="b0244" maxlength="64"
                                                   value="${vo.b0244}" number="true" />
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row-fluid">
                                <div class="span6 ">
                                    <div id="b0247Group" class="control-group">
                                        <label class="control-label">内设第一级单位领导职数</label>
                                        <div class="controls">
                                            <input type="text" class="span10 m-wrap" id="b0247" name="b0247" maxlength="64"
                                                   value="${vo.b0247}" number="true" />
                                        </div>
                                    </div>
                                </div>
                                <div class="span6 ">
                                    <div id="b0251Group" class="control-group">
                                        <label class="control-label">内设第一级单位领导同级非领导职数</label>
                                        <div class="controls">
                                            <input type="text" class="span10 m-wrap" id="b0251" name="b0251" maxlength="64"
                                                   value="${vo.b0251}" number="true" />
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row-fluid">
                                <div class="span6 ">
                                    <div id="b0254Group" class="control-group">
                                        <label class="control-label">业务人员编制数</label>
                                        <div class="controls">
                                            <input type="text" class="span10 m-wrap" id="b0254" name="b0254" maxlength="64"
                                                   value="${vo.b0254}" number="true" />
                                        </div>
                                    </div>
                                </div>
                                <div class="span6 ">
                                    <div id="b0257Group" class="control-group">
                                        <label class="control-label">工勤人员编制数</label>
                                        <div class="controls">
                                            <input type="text" class="span10 m-wrap" id="b0257" name="b0251" maxlength="64"
                                                   value="${vo.b0257}" number="true" />
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="row-fluid">
                                <div class="span6 ">
                                    <div id="b0261Group" class="control-group">
                                        <label class="control-label">使用全额拨款人数</label>
                                        <div class="controls">
                                            <input type="text" class="span10 m-wrap" id="b0261" name="b0261" maxlength="64"
                                                   value="${vo.b0261}" number="true" />
                                        </div>
                                    </div>
                                </div>
                                <div class="span6 ">
                                    <div id="b0264Group" class="control-group">
                                        <label class="control-label">使用差额拨款人数</label>
                                        <div class="controls">
                                            <input type="text" class="span10 m-wrap" id="b0264" name="b0264" maxlength="64"
                                                   value="${vo.b0264}" number="true" />
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="row-fluid">
                                <div class="span6 ">
                                    <div id="b0267Group" class="control-group">
                                        <label class="control-label">使用自筹资金人数</label>
                                        <div class="controls">
                                            <input type="text" class="span10 m-wrap" id="b0267" name="b0267" maxlength="64"
                                                   value="${vo.b0267}" number="true" />
                                        </div>
                                    </div>
                                </div>
                                <div class="span6 ">
                                    <div id="bBzzysmGroup" class="control-group">
                                        <label class="control-label">编制摘要说明</label>
                                        <div class="controls">
                                            <textarea id="bBzzysm" name="bBzzysm" class="span14 m-wrap" maxlength="512" value="${vo.bBzzysm}"
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