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
                <form action="" class="form-horizontal" id="b01Form" method="post">
                    <%--法人机构--%>
                    <input type="hidden" name="b01Id" value="${vo.b01Id}" id="b01Id"/> <%--当前点击节点的b01Id--%>
                    <input type="hidden" name="bSjlx" value="${vo.bSjlx}" id="bSjlx"/>
                    <input type="hidden" name="b0100" value="${vo.b0100 }"/>
                    <dl class="dlattrbute">
                        <dd>
                            <c:if test='${vo.bSjlx=="0"}'>
                                <div class="row-fluid">
                                    <div class="span6 ">
                                        <div id="b0101Group" class="control-group">
                                            <label class="control-label"><span class="Required">*</span>机构名称</label>
                                            <div class="controls">
                                                <input type="text" class="span10 m-wrap" id="b0101" name="b0101"
                                                       maxlength="128" value="${vo.b0101}" required/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row-fluid">
                                    <div class="span6 ">
                                        <div id="b0104Group" class="control-group">
                                            <label class="control-label">机构简称</label>
                                            <div class="controls">
                                                <input type="text" class="span10 m-wrap" name="b0104" id="b0104"
                                                       maxlength="128" value="${vo.b0104}"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="span6 ">
                                        <div id="b0131Group" class="control-group">
                                            <label class="control-label">机构性质</label>
                                            <div class="controls">
                                                <Tree:tree id="b0131" valueName="b0131A"
                                                           selectClass="span10 m-wrap"
                                                           treeUrl="${path}/api/dictionary/tree?typeCode=ZB04-2006/DWXZ"
                                                           token="${sessionScope.OWASP_CSRFTOKEN}"
                                                           submitType="get" dataType="json" isSearch="false"
                                                           checkedByTitle="true" isSelectTree="true"
                                                           defaultkeys="${vo.b0131}"
                                                           defaultvalues="${vo.b0131A}"/>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                                <div class="row-fluid">
                                    <div class="span6 ">
                                        <div id="b0127Group" class="control-group">
                                            <label class="control-label">机构级别</label>
                                            <div class="controls">
                                                <SelectTag:SelectTag id="b0127" needNullValue="true"
                                                                     valueName="b0127A"
                                                                     defaultkeys="${vo.b0127}"
                                                                     token="${sessionScope.OWASP_CSRFTOKEN}"
                                                                     defaultvalues="${vo.b0127A}"
                                                                     textClass="span10 m-wrap" radioOrCheckbox="radio"
                                                                     selectUrl="${path}/api/dictionary/select?typeCode=ZB03-1994/DWJB"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="span6 ">
                                        <div id="bGllbBGroup" class="control-group">
                                            <label class="control-label"><span class="Required">*</span>机构管理类别</label>
                                            <div class="controls">
                                                <SelectTag:SelectTag id="bGllbB" needNullValue="true"
                                                                     valueName="bGllbA"
                                                                     defaultkeys="${vo.bGllbB}"
                                                                     token="${sessionScope.OWASP_CSRFTOKEN}"
                                                                     defaultvalues="${vo.bGllbA}" required="true"
                                                                     textClass="span10 m-wrap" radioOrCheckbox="radio"
                                                                     selectUrl="${path}/api/dictionary/select?typeCode=2018-JGGLLB"/>
                                            </div>
                                        </div>
                                    </div>

                                </div>

                                <div class="row-fluid">
                                    <div class="span6 ">
                                        <div id="b0151BGroup" class="control-group">
                                            <label class="control-label">主管部门</label>
                                            <div class="controls">
                                                <Tree:tree id="b0151B" valueName="b0151A"
                                                           selectClass="span10 m-wrap"
                                                           treeUrl="${path}/api/dictionary/tree?typeCode=ZB02-2006/JGMC"
                                                           token="${sessionScope.OWASP_CSRFTOKEN}"
                                                           submitType="get" dataType="json" isSearch="false"
                                                           checkedByTitle="true" isSelectTree="true"
                                                           defaultkeys="${vo.b0151B}"
                                                           defaultvalues="${vo.b0151A}"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="span6 ">
                                        <div id="b0154BGroup" class="control-group">
                                            <label class="control-label">协管部门</label>
                                            <div class="controls">
                                                <Tree:tree id="b0154B" valueName="b0154A"
                                                           selectClass="span10 m-wrap"
                                                           treeUrl="${path}/api/dictionary/tree?typeCode=ZB02-2006/JGMC"
                                                           token="${sessionScope.OWASP_CSRFTOKEN}"
                                                           submitType="get" dataType="json" isSearch="false"
                                                           checkedByTitle="true" isSelectTree="true"
                                                           defaultkeys="${vo.b0154B}"
                                                           defaultvalues="${vo.b0154A}"/>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                                <div class="row-fluid">
                                    <div class="span6 ">
                                        <div id="parentNameGroup" class="control-group">
                                            <label class="control-label"><span class="Required">*</span>所属机构</label>
                                            <div class="controls">
                                                <Tree:tree id="parentId" valueName="parentName"
                                                           selectClass="span10 m-wrap"
                                                           treeUrl="${path}/api/b01/dtjz/tree"
                                                           token="${sessionScope.OWASP_CSRFTOKEN}" onChange="updatePx()"
                                                           submitType="get" dataType="json" isSearch="false"
                                                           required="true"
                                                           checkedByTitle="true" isSelectTree="true"
                                                           defaultkeys="${vo.parentId }"
                                                           defaultvalues="${vo.parentName}" dtjz="true"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="span6 ">
                                        <div id="bPxGroup" class="control-group">
                                            <label class="control-label"><span class="Required">*</span>排序</label>
                                            <div class="controls">
                                                <input type="text" class="span10 m-wrap" id="bPx" name="bPx"
                                                       maxlength="64"
                                                       value="<c:if test="${empty vo.b0100}">${sort}</c:if><c:if test="${!empty vo.b0100}">${vo.bPx}</c:if>"
                                                       number="true" required/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row-fluid">
                                    <div class="span6 ">
                                        <div id="bDwztBGroup" class="control-group">
                                            <label class="control-label">机构状态</label>
                                            <div class="controls">
                                                <Tree:tree id="bDwztB" valueName="bDwztAa"
                                                           selectClass="span10 m-wrap"
                                                           treeUrl="${path}/api/dictionary/tree?typeCode=2018-JGZT"
                                                           token="${sessionScope.OWASP_CSRFTOKEN}"
                                                           submitType="get" dataType="json" isSearch="false"
                                                           checkedByTitle="true" isSelectTree="true"
                                                           defaultkeys="${vo.bDwztB}"
                                                           defaultvalues="${vo.bDwztA}"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="span6 ">
                                        <div id="bSflsGroup" class="control-group">
                                            <label class="control-label">临时机构</label>
                                            <div class="controls">
                                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                <label class="radio">
                                                    <input type="radio" name="bSfls" value="1"
                                                           <c:if test="${vo.bSfls =='1'}">checked</c:if>/>
                                                    是&nbsp;&nbsp;&nbsp;
                                                </label>
                                                <label class="radio">
                                                    <input type="radio" name="bSfls" value="0"
                                                           <c:if test="${vo.bSfls =='0' || isAdd =='add'}">checked</c:if>/>
                                                    否
                                                </label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row-fluid">
                                    <div class="span6 ">
                                        <div id="b0164Group" class="control-group">
                                            <label class="control-label">成立时间</label>
                                            <div class="controls">
                                                <input type="text" class="span10 m-wrap"
                                                       placeholder="日期格式 例如：2018或201801或20180101" isDate="true"
                                                       dateformat="yyyy,yyyymm,yyyymmdd"
                                                       value="${vo.b0164 }" name="b0164"
                                                       id="b0164"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="span6 ">
                                        <div id="b0167Group" class="control-group">
                                            <label class="control-label">成立批准文号</label>
                                            <div class="controls">
                                                <input type="text" class="span10 m-wrap" name="b0167" id="b0167"
                                                       maxlength="128" value="${vo.b0167 }"/>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                                <div class="row-fluid">
                                    <div class="span6 ">
                                        <div id="b0181Group" class="control-group">
                                            <label class="control-label">撤销批准日期</label>
                                            <div class="controls">
                                                <input type="text" class="span10 m-wrap"
                                                       placeholder="日期格式 例如：2018或201801或20180101" isDate="true"
                                                       dateformat="yyyy,yyyymm,yyyymmdd"
                                                       value="${vo.b0181 }" name="b0181"
                                                       id="b0181"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="span6 ">
                                        <div id="b0187aGroup" class="control-group">
                                            <label class="control-label">撤销批准机关</label>
                                            <div class="controls">
                                                <Tree:tree id="b0187B" valueName="b0187A"
                                                           selectClass="span10 m-wrap"
                                                           treeUrl="${path}/api/dictionary/tree?typeCode=ZB02-2006/JGMC"
                                                           token="${sessionScope.OWASP_CSRFTOKEN}"
                                                           submitType="get" dataType="json" isSearch="false"
                                                           checkedByTitle="true" isSelectTree="true"
                                                           defaultkeys="${vo.b0187B}"
                                                           defaultvalues="${vo.b0187A}"/>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                                <div class="row-fluid">
                                    <div class="span6 ">
                                        <div id="b0184Group" class="control-group">
                                            <label class="control-label">撤销批准文号</label>
                                            <div class="controls">
                                                <input type="text" class="span10 m-wrap" name="b0184" id="b0184"
                                                       maxlength="128" value="${vo.b0184}"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="span6 ">
                                        <div id="bSfgwytjGroup" class="control-group">
                                            <label class="control-label">公务员管理</label>
                                            <div class="controls">
                                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                <label class="radio">
                                                    <input type="radio" name="bSfgwytj" value="-1"/>
                                                    是&nbsp;&nbsp;&nbsp;
                                                </label>
                                                <label class="radio">
                                                    <input type="radio" name="bSfgwytj" value="0" checked/>
                                                    否
                                                </label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row-fluid">
                                    <div class="span6 ">
                                        <div id="remarkGroup" class="control-group">
                                            <label class="control-label">备注</label>
                                            <div class="controls">
                                            <textarea id="remark" name="remark" class="span10 m-wrap" maxlength="512"
                                                      value="${vo.remark}"
                                                      rows="2" style="resize: none;">${vo.remark}</textarea>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test='${vo.bSjlx=="1"}'>
                                <div class="row-fluid">
                                    <div class="span6 ">
                                        <div id="b0101Group" class="control-group">
                                            <label class="control-label"><span class="Required">*</span>机构名称</label>
                                            <div class="controls">
                                                <input type="text" class="span10 m-wrap" id="b0101" name="b0101"
                                                       maxlength="128" value="${vo.b0101}" required/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="span6 ">
                                        <div id="b0104Group" class="control-group">
                                            <label class="control-label">机构简称</label>
                                            <div class="controls">
                                                <input type="text" class="span10 m-wrap" name="b0104" id="b0104"
                                                       maxlength="128" value="${vo.b0104}"/>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                                <div class="row-fluid">
                                    <div class="span6 ">
                                        <div id="b0111Group" class="control-group">
                                            <label class="control-label">机构代码</label>
                                            <div class="controls">
                                                <Tree:tree id="b0111" valueName="b0111A"
                                                           selectClass="span10 m-wrap"
                                                           treeUrl="${path}/api/dictionary/tree?typeCode=ZB02-2006/JGMC"
                                                           token="${sessionScope.OWASP_CSRFTOKEN}"
                                                           submitType="get" dataType="json" isSearch="false"
                                                           checkedByTitle="true" isSelectTree="true"
                                                           defaultkeys="${vo.b0111}"
                                                           defaultvalues="${vo.b0111A}"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="span6 ">
                                        <div id="bPxGroup" class="control-group">
                                            <label class="control-label"><span class="Required">*</span>排序</label>
                                            <div class="controls">
                                                <input type="text" class="span10 m-wrap" id="bPx" name="bPx"
                                                       maxlength="64"
                                                       value="<c:if test="${empty vo.b0100}">${sort}</c:if><c:if test="${!empty vo.b0100}">${vo.bPx}</c:if>"
                                                       number="true" required/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row-fluid">
                                    <div class="span6 ">
                                        <div id="parentNameGroup" class="control-group">
                                            <label class="control-label"><span class="Required">*</span>所属机构</label>
                                            <div class="controls">
                                                <input type="text" class="span10 m-wrap" id="parentName"
                                                       name="parentName" readonly
                                                       maxlength="128" value="${vo.parentName}" required/>
                                                <input type="hidden" name="parentId" value="${vo.parentId}">
                                                    <%-- <Tree:tree id="parentId" valueName="parentName" selectClass="span10 m-wrap"
                                                                treeUrl="${path}/api/b01/dtjz/tree"
                                                                token="${sessionScope.OWASP_CSRFTOKEN}"
                                                                submitType="get" dataType="json" isSearch="false" required="true"
                                                                checkedByTitle="true" isSelectTree="true" defaultkeys="${vo.parentId}"
                                                                defaultvalues="${vo.parentName}" dtjz="true"/>--%>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="span6 ">
                                        <div id="b0127Group" class="control-group">
                                            <label class="control-label">机构级别</label>
                                            <div class="controls">
                                                <SelectTag:SelectTag id="b0127" needNullValue="true"
                                                                     valueName="b0127A"
                                                                     defaultkeys="${vo.b0127}"
                                                                     token="${sessionScope.OWASP_CSRFTOKEN}"
                                                                     defaultvalues="${vo.b0127A}"
                                                                     textClass="span10 m-wrap" radioOrCheckbox="radio"
                                                                     selectUrl="${path}/api/dictionary/select?typeCode=ZB03-1994/DWJB"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row-fluid">
                                    <div class="span6 ">
                                        <div id="b0131Group" class="control-group">
                                            <label class="control-label">机构性质</label>
                                            <div class="controls">
                                                <Tree:tree id="b0131" valueName="b0131A"
                                                           selectClass="span10 m-wrap"
                                                           treeUrl="${path}/api/dictionary/tree?typeCode=ZB04-2006/DWXZ"
                                                           token="${sessionScope.OWASP_CSRFTOKEN}"
                                                           submitType="get" dataType="json" isSearch="false"
                                                           checkedByTitle="true" isSelectTree="true"
                                                           defaultkeys="${vo.b0131}"
                                                           defaultvalues="${vo.b0131A}"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="span6 ">
                                        <div id="remarkGroup" class="control-group">
                                            <label class="control-label">备注</label>
                                            <div class="controls">
                                            <textarea id="remark" name="remark" class="span10 m-wrap" maxlength="512"
                                                      value="${vo.remark}"
                                                      rows="2" style="resize: none;">${vo.remark}</textarea>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test='${vo.bSjlx=="2"}'>
                                <div class="row-fluid">
                                    <div class="span6 ">
                                        <div id="b0101Group" class="control-group">
                                            <label class="control-label"><span class="Required">*</span>机构名称</label>
                                            <div class="controls">
                                                <input type="text" class="span10 m-wrap" id="b0101" name="b0101"
                                                       maxlength="128" value="${vo.b0101}" required/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="span6 ">
                                        <div id="b0104Group" class="control-group">
                                            <label class="control-label">机构简称</label>
                                            <div class="controls">
                                                <input type="text" class="span10 m-wrap" name="b0104" id="b0104"
                                                       maxlength="128" value="${vo.b0104}"/>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                                <div class="row-fluid">
                                    <div class="span6 ">
                                        <div id="b0111Group" class="control-group">
                                            <label class="control-label">机构代码</label>
                                            <div class="controls">
                                                <Tree:tree id="b0111" valueName="b0111A"
                                                           selectClass="span10 m-wrap"
                                                           treeUrl="${path}/api/dictionary/tree?typeCode=ZB02-2006/JGMC"
                                                           token="${sessionScope.OWASP_CSRFTOKEN}"
                                                           submitType="get" dataType="json" isSearch="false"
                                                           checkedByTitle="true" isSelectTree="true"
                                                           defaultkeys="${vo.b0111}"
                                                           defaultvalues="${vo.b0111A}"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="span6 ">
                                        <div id="bPxGroup" class="control-group">
                                            <label class="control-label"><span class="Required">*</span>排序</label>
                                            <div class="controls">
                                                <input type="text" class="span10 m-wrap" id="bPx" name="bPx"
                                                       maxlength="64"
                                                       <c:if test="${isAddOne == 'addOne' || empty vo.parentId}">readonly</c:if>
                                                       value="<c:if test="${empty vo.b0100}">${sort}</c:if><c:if test="${!empty vo.b0100}">${vo.bPx}</c:if>"
                                                       number="true" required/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row-fluid">
                                    <c:if test="${!empty vo.parentId}">
                                        <c:if test="${isAddOne != 'addOne'}">
                                            <div class="span6 ">
                                                <div id="parentNameGroup" class="control-group">
                                                    <label class="control-label"><span
                                                            class="Required">*</span>所属机构</label>
                                                    <div class="controls">
                                                        <Tree:tree id="parentId" valueName="parentName"
                                                                   selectClass="span10 m-wrap"
                                                                   treeUrl="${path}/api/b01/dtjz/tree"
                                                                   onChange="updatePx()"
                                                                   token="${sessionScope.OWASP_CSRFTOKEN}"
                                                                   submitType="get" dataType="json" isSearch="false"
                                                                   required="true"
                                                                   checkedByTitle="true" isSelectTree="true"
                                                                   defaultkeys="${vo.parentId}"
                                                                   defaultvalues="${vo.parentName}" dtjz="true"/>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:if>
                                    </c:if>
                                    <div class="span6 ">
                                        <div id="remarkGroup" class="control-group">
                                            <label class="control-label">备注</label>
                                            <div class="controls">
                                            <textarea id="remark" name="remark" class="span10 m-wrap" maxlength="512"
                                                      value="${vo.remark}"
                                                      rows="2" style="resize: none;">${vo.remark}</textarea>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:if>
                        </dd>
                    </dl>
                </form>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="${path }/js/common/DataValidate.js"></script>
<script type="text/javascript">
    /*  function dealValue() {
     debugger
     var b0101 = $("#b0101").val();
     $("#b0104").val(b0101);
     }
     */
    function updatePx() {
        $.ajax({
            url: "${path }/zzb/jggl/b01/updatePx",
            type: "post",
            data: {"parentId": $("#parentId").val(), "b0100": "${vo.b0100 }"},
            dataType: "json",
            headers: {
                OWASP_CSRFTOKEN: "${sessionScope.OWASP_CSRFTOKEN}"
            },
            success: function (json) {
                if (json.success) {
                    if (!json.checkValue) {
                        //refreshTreeTagByDt("parentId_tree",json.oldParentId)
                        var zTree1 = $.fn.zTree.getZTreeObj("parentId_tree");
                        var node = zTree1.getNodeByParam('id', json.oldParentId);// 获取id为-1的点
                        zTree1.selectNode(node);
                        zTree1.expandNode(node, true, false, true);
                        $("#parentId").val(json.oldParentId);
                        $("#parentName").val(json.oldParentName);
                        showTip("提示", json.message, 1500);
                        return;
                    }
                    $("#bPx").val(json.px);
                }

            },
            error: function () {
            }
        });
    }
    $(function () {
        $('#b0104').keyup(function () {
            $("#b0101").unbind();
        });

        $('#b0101').keyup(function () {
            var b0101 = $("#b0101").val();
            $("#b0104").val(b0101);
        });
    })
</script>