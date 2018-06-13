<%@ taglib prefix="Select" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jsp/inc/servlet.jsp" %>
<%@include file="/WEB-INF/jsp/inc/taglib.jsp" %>
<%--
  ~ Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
  ~ http://www.hn-hisun.com
  ~ 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
  --%>
<title>添加机构</title>
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
                <form action="" class="form-horizontal" id="a01Form" method="post">
                    <input type="hidden" name="b01Id" value="${b01Id}" id="b01Id"/> <%--当前点击节点的b01Id--%>
                    <input type="hidden" name="a0100" value="${vo.a0100 }"/>
                    <dl class="dlattrbute">
                        <dd>
                            <div class="row-fluid">
                                <div class="span6 ">
                                    <div id="a0101Group" class="control-group">
                                        <label class="control-label"><span class="Required">*</span>姓名</label>
                                        <div class="controls">
                                            <input type="text" class="span10 m-wrap" name="a0101" id="a0101" required
                                                   maxlength="128" value="${vo.a0101}"/>
                                        </div>
                                    </div>
                                </div>
                                <%-- <div class="span2"><simg
                                         src="${path}/sys/tenant/user/photo?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}"
                                         alt="" class="headPhoto" style="width: 138px;"/></div>--%>
                                <div class="span6 ">
                                    <div id="a0104AGroup" class="control-group">
                                        <label class="control-label">性别</label>
                                        <div class="controls">
                                            <SelectTag:SelectTag id="a0104" needNullValue="true"
                                                                 valueName="a0104A"
                                                                 defaultkeys="${vo.a0104}"
                                                                 token="${sessionScope.OWASP_CSRFTOKEN}"
                                                                 defaultvalues="${vo.a0104A}"
                                                                 textClass="span10 m-wrap" radioOrCheckbox="radio"
                                                                 selectUrl="${path}/api/dictionary/select?typeCode=GB/T2261.1-2003"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row-fluid">
                                <div class="span6 ">
                                    <div id="a0107Group" class="control-group">
                                        <label class="control-label">出生日期</label>
                                        <div class="controls">
                                            <input type="text" class="span10 m-wrap" name="a0107" id="a0107"
                                                   placeholder="日期格式 例如：2018或201801或20180101" isDate="true"
                                                   dateformat="yyyy,yyyymm,yyyymmdd"
                                                   maxlength="128" value="${vo.a0107}"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="span6 ">
                                    <div id="a0117AGroup" class="control-group">
                                        <label class="control-label">名族</label>
                                        <div class="controls">
                                            <SelectTag:SelectTag id="a0117" needNullValue="true"
                                                                 valueName="a0117A"
                                                                 defaultkeys="${vo.a0117}"
                                                                 token="${sessionScope.OWASP_CSRFTOKEN}"
                                                                 defaultvalues="${vo.a0117A}"
                                                                 textClass="span10 m-wrap" radioOrCheckbox="radio"
                                                                 selectUrl="${path}/api/dictionary/select?typeCode=GB3304-1991"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row-fluid">
                                <div class="span6 ">
                                    <div id="a0111BGroup" class="control-group">
                                        <label class="control-label">籍贯</label>
                                        <div class="controls">
                                            <Tree:tree id="a0111B" valueName="a0111A"
                                                       selectClass="span10 m-wrap"
                                                       treeUrl="${path}/api/dictionary/tree?typeCode=ZB01-2006/GQMC"
                                                       token="${sessionScope.OWASP_CSRFTOKEN}"
                                                       submitType="get" dataType="json" isSearch="false"
                                                       checkedByTitle="true" isSelectTree="true"
                                                       defaultkeys="${vo.a0111B}"
                                                       defaultvalues="${vo.a0111A}"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="span6 ">
                                    <div id="a0184Group" class="control-group">
                                        <label class="control-label">身份证号码</label>
                                        <div class="controls">
                                            <input type="text" class="span10 m-wrap" name="a0184" id="a0184"
                                                   maxlength="128" value="${vo.a0184}"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row-fluid">
                                <div class="span6 ">
                                    <div id="a0114BGroup" class="control-group">
                                        <label class="control-label">出生地</label>
                                        <div class="controls">
                                            <Tree:tree id="a0114B" valueName="a0114A"
                                                       selectClass="span10 m-wrap"
                                                       treeUrl="${path}/api/dictionary/tree?typeCode=ZB01-2006/GQMC"
                                                       token="${sessionScope.OWASP_CSRFTOKEN}"
                                                       submitType="get" dataType="json" isSearch="false"
                                                       checkedByTitle="true" isSelectTree="true"
                                                       defaultkeys="${vo.a0114B}"
                                                       defaultvalues="${vo.a0114A}"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="span6 ">
                                    <div id="a0115BGroup" class="control-group">
                                        <label class="control-label">成长地</label>
                                        <div class="controls">
                                            <Tree:tree id="a0115B" valueName="a0115A"
                                                       selectClass="span10 m-wrap"
                                                       treeUrl="${path}/api/dictionary/tree?typeCode=ZB01-2006/GQMC"
                                                       token="${sessionScope.OWASP_CSRFTOKEN}"
                                                       submitType="get" dataType="json" isSearch="false"
                                                       checkedByTitle="true" isSelectTree="true"
                                                       defaultkeys="${vo.a0115B}"
                                                       defaultvalues="${vo.a0115A}"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row-fluid">
                                <div class="span6 ">
                                    <div id="a0131Group" class="control-group">
                                        <label class="control-label">婚姻状况</label>
                                        <div class="controls">
                                            <SelectTag:SelectTag id="a0131" needNullValue="true"
                                                                 valueName="a0131A"
                                                                 defaultkeys="${vo.a0131}"
                                                                 token="${sessionScope.OWASP_CSRFTOKEN}"
                                                                 defaultvalues="${vo.a0131A}"
                                                                 textClass="span10 m-wrap" radioOrCheckbox="radio"
                                                                 selectUrl="${path}/api/dictionary/select?typeCode=GB/T2261.2-2003"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="span6 ">
                                    <div id="a0127Group" class="control-group">
                                        <label class="control-label">健康状况</label>
                                        <div class="controls">
                                            <Tree:tree id="a0127" valueName="a0127A"
                                                       selectClass="span10 m-wrap"
                                                       treeUrl="${path}/api/dictionary/tree?typeCode=GB/T2261.3-2003"
                                                       token="${sessionScope.OWASP_CSRFTOKEN}"
                                                       submitType="get" dataType="json" isSearch="false"
                                                       checkedByTitle="true" isSelectTree="true"
                                                       defaultkeys="${vo.a0127}"
                                                       defaultvalues="${vo.a0127A}"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row-fluid">
                                <div class="span6 ">
                                    <div id="a0141Group" class="control-group">
                                        <label class="control-label">政治面貌</label>
                                        <div class="controls">
                                            <SelectTag:SelectTag id="a0141" needNullValue="true"
                                                                 valueName="a0141A"
                                                                 defaultkeys="${vo.a0141}"
                                                                 token="${sessionScope.OWASP_CSRFTOKEN}"
                                                                 defaultvalues="${vo.a0141A}"
                                                                 textClass="span10 m-wrap" radioOrCheckbox="radio"
                                                                 selectUrl="${path}/api/dictionary/select?typeCode=GB4762-1984"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="span6 ">
                                    <div id="a0144Group" class="control-group">
                                        <label class="control-label">组织时间</label>
                                        <div class="controls">
                                            <input type="text" class="span10 m-wrap" name="a0144" id="a0144"
                                                   placeholder="日期格式 例如：2018或201801或20180101" isDate="true"
                                                   dateformat="yyyy,yyyymm,yyyymmdd"
                                                   maxlength="128" value="${vo.a0144}"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row-fluid">
                                <div class="span6 ">
                                    <div id="a0501BGroup" class="control-group">
                                        <label class="control-label">现职级</label>
                                        <div class="controls">
                                            <Tree:tree id="a0501B" valueName="a0501A"
                                                       selectClass="span10 m-wrap"
                                                       treeUrl="${path}/api/dictionary/tree?typeCode=ZB09-2006/ZWJB"
                                                       token="${sessionScope.OWASP_CSRFTOKEN}"
                                                       submitType="get" dataType="json" isSearch="false"
                                                       checkedByTitle="true" isSelectTree="true"
                                                       defaultkeys="${vo.a0501B}"
                                                       defaultvalues="${vo.a0501A}"/>
                                            <%--<SelectTag:SelectTag id="a0501B" needNullValue="true"
                                                                 valueName="a0501A"
                                                                 defaultkeys="${vo.a0501B}"
                                                                 token="${sessionScope.OWASP_CSRFTOKEN}"
                                                                 defaultvalues="${vo.a0501A}"
                                                                 textClass="span10 m-wrap" radioOrCheckbox="radio"
                                                                 selectUrl="${path}/api/dictionary/select?typeCode=ZB09-2006/ZWJB"/>--%>
                                        </div>
                                    </div>
                                </div>
                                <div class="span6 ">
                                    <div id="a0504Group" class="control-group">
                                        <label class="control-label">职级时间</label>
                                        <div class="controls">
                                            <input type="text" class="span10 m-wrap" name="a0504" id="a0504"
                                                   placeholder="日期格式 例如：2018或201801或20180101" isDate="true"
                                                   dateformat="yyyy,yyyymm,yyyymmdd"
                                                   maxlength="128" value="${vo.a0504}"/>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="row-fluid">
                                <div class="span6 ">
                                    <div id="aZjsmGroup" class="control-group">
                                        <label class="control-label">职级说明</label>
                                        <div class="controls">
                                            <input type="text" class="span10 m-wrap" name="aZjsm" id="aZjsm"
                                                   maxlength="128" value="${vo.aZjsm}"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="span6 ">
                                    <div id="a0187AGroup" class="control-group">
                                        <label class="control-label">专业特长</label>
                                        <div class="controls">
                                            <input type="text" class="span10 m-wrap" name="a0187A" id="a0187A"
                                                   maxlength="128" value="${vo.a0187A}"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row-fluid">
                                <div class="span6 ">
                                    <div id="aGblybGroup" class="control-group">
                                        <label class="control-label">干部来源</label>
                                        <div class="controls">
                                            <SelectTag:SelectTag id="aGblyb" needNullValue="true"
                                                                 valueName="aGblya"
                                                                 defaultkeys="${vo.aGblyb}"
                                                                 token="${sessionScope.OWASP_CSRFTOKEN}"
                                                                 defaultvalues="${vo.aGblya}"
                                                                 textClass="span10 m-wrap" radioOrCheckbox="radio"
                                                                 selectUrl="${path}/api/dictionary/select?typeCode=GB/T2261.2-2003"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="span6 ">
                                    <div id="aGbztbGroup" class="control-group">
                                        <label class="control-label">干部状态</label>
                                        <div class="controls">
                                            <SelectTag:SelectTag id="aGbztb" needNullValue="true"
                                                                 valueName="aGbzta"
                                                                 defaultkeys="${vo.aGbztb}"
                                                                 token="${sessionScope.OWASP_CSRFTOKEN}"
                                                                 defaultvalues="${vo.aGbzta}"
                                                                 textClass="span10 m-wrap" radioOrCheckbox="radio"
                                                                 selectUrl="${path}/api/dictionary/select?typeCode=GB/T2261.3-2003"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row-fluid">
                                <div class="span6 ">
                                    <div id="a0157BGroup" class="control-group">
                                        <label class="control-label">人员管理单位</label>
                                        <div class="controls">
                                            <Tree:tree id="a0157B" valueName="a0157A"
                                                       selectClass="span10 m-wrap"
                                                       treeUrl="${path}/api/dictionary/tree?typeCode=ZB02-2006/JGMC"
                                                       token="${sessionScope.OWASP_CSRFTOKEN}"
                                                       submitType="get" dataType="json" isSearch="false"
                                                       checkedByTitle="true" isSelectTree="true"
                                                       defaultkeys="${vo.a0157B}"
                                                       defaultvalues="${vo.a0157A}"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="span6 ">
                                    <div id="aSfydzdaGroup" class="control-group">
                                        <label class="control-label">是否创建电子档案</label>
                                        <div class="controls">
                                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                            <label class="radio">
                                                <input type="radio" name="aSfydzda" value="1"
                                                       <c:if test="${vo.aSfydzda =='1'}">checked</c:if>/>
                                                是&nbsp;&nbsp;&nbsp;
                                            </label>
                                            <label class="radio">
                                                <input type="radio" name="aSfydzda" value="0"
                                                       <c:if test="${vo.aSfydzda =='0' || empty vo.aSfydzda}">checked</c:if>/>
                                                否
                                            </label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row-fluid">
                                <div class="span6 ">
                                    <div id="aCjgzdwGroup" class="control-group">
                                        <label class="control-label">参加工作单位</label>
                                        <div class="controls">
                                            <input type="text" class="span10 m-wrap" name="aCjgzdw" id="aCjgzdw"
                                                   maxlength="128" value="${vo.aCjgzdw}"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="span6 ">
                                    <div id="a0134Group" class="control-group">
                                        <label class="control-label">参加工作时间</label>
                                        <div class="controls">
                                            <input type="text" class="span10 m-wrap" name="a0134" id="a0134"
                                                   placeholder="日期格式 例如：2018或201801或20180101" isDate="true"
                                                   dateformat="yyyy,yyyymm,yyyymmdd"
                                                   maxlength="128" value="${vo.a0134}"/>
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
</script>