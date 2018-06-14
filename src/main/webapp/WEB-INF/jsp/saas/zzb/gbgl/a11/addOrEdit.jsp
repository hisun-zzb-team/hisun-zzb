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
                <div id="a11Div">
                    <form action="" class="form-horizontal" id="a11Form" method="post">
                        <input type="hidden" name="a1100" value="${vo.a1100 }"/>
                        <input type="hidden" name="a01Id" value="${a01Id }"/>
                        <dl class="dlattrbute">
                            <dd>
                                <div class="row-fluid">
                                    <div class="span6 ">
                                        <div id="a1107Group" class="control-group">
                                            <label class="control-label"><span class="Required">*</span>起始日期</label>
                                            <div class="controls">
                                                <input type="text" class="span10 m-wrap" name="a1107" id="a1107"
                                                       placeholder="日期格式 例如：2018或201801或20180101" isDate="true" required
                                                       dateformat="yyyy,yyyymm,yyyymmdd"
                                                       maxlength="128" value="${vo.a1107}"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="span6 ">
                                        <div id="a1111Group" class="control-group">
                                            <label class="control-label">截止日期</label>
                                            <div class="controls">
                                                <input type="text" class="span10 m-wrap" name="a1111" id="a1111"
                                                       placeholder="日期格式 例如：2018或201801或20180101" isDate="true"
                                                       dateformat="yyyy,yyyymm,yyyymmdd"
                                                       maxlength="128" value="${vo.a1111}"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row-fluid">
                                    <div class="span6 ">
                                        <div id="a1131Group" class="control-group">
                                            <label class="control-label">培训班级</label>
                                            <div class="controls">
                                                <input type="text" class="span10 m-wrap" name="a1131" id="a1131"
                                                       maxlength="128" value="${vo.a1131}"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="span6 ">
                                        <div id="a1101Group" class="control-group">
                                            <label class="control-label">培训类别</label>
                                            <div class="controls">
                                                <SelectTag:SelectTag id="a1101" needNullValue="true"
                                                                     valueName="a1101A"
                                                                     defaultkeys="${vo.a1101}"
                                                                     token="${sessionScope.OWASP_CSRFTOKEN}"
                                                                     defaultvalues="${vo.a1101A}"
                                                                     textClass="span10 m-wrap" radioOrCheckbox="radio"
                                                                     selectUrl="${path}/api/dictionary/select?typeCode=ZB31-2006/PXLB"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row-fluid">
                                    <div class="span10 ">
                                        <div id="aPxsmGroup" class="control-group">
                                            <label class="control-label">培训说明</label>
                                            <div class="controls">
                                            <textarea id="aPxsm" name="aPxsm" class="span12 m-wrap" maxlength="512"
                                                      value="${vo.aPxsm}"
                                                      rows="1" style="resize: none;">${vo.aPxsm}</textarea>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row-fluid">
                                    <div class="span6 ">
                                        <div id="aPxxfGroup" class="control-group">
                                            <label class="control-label">培训学分</label>
                                            <div class="controls" >
                                                <input type="text" class="span6 m-wrap" name="aPxxf" id="aPxxf" number="true"
                                                       maxlength="128" value="${vo.aPxxf}"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                <select name="aPxxflb" style="width:90px; height: 34px">
                                                    <option value="1">Ⅰ</option>
                                                    <option value="2">Ⅱ</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="span6 ">
                                        <div id="aPxscGroup" class="control-group">
                                            <label class="control-label">培训时长</label>
                                            <div class="controls">
                                                <input type="text" class="span6 m-wrap" name="aPxsc" id="aPxsc" number="true"
                                                       maxlength="128" value="${vo.aPxsc}"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                <select name="aPxscdw" style="width:90px; height: 34px">
                                                    <option value="1">年</option>
                                                    <option value="2">月</option>
                                                    <option value="3">周</option>
                                                    <option value="4">天</option>
                                                    <option value="5">小时</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row-fluid">
                                <div class="span6 ">
                                    <div id="aPxccbGroup" class="control-group">
                                        <label class="control-label">培训层次</label>
                                        <div class="controls">
                                            <SelectTag:SelectTag id="aPxccb" needNullValue="true"
                                                                 valueName="aPxcca"
                                                                 defaultkeys="${vo.aPxccb}"
                                                                 token="${sessionScope.OWASP_CSRFTOKEN}"
                                                                 defaultvalues="${vo.aPxcca}"
                                                                 textClass="span10 m-wrap" radioOrCheckbox="radio"
                                                                 selectUrl="${path}/api/dictionary/select?typeCode=ZB31-2006/PXLB"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="span6 ">
                                    <div id="a1144Group" class="control-group">
                                        <label class="control-label">完成情况</label>
                                        <div class="controls">
                                            <SelectTag:SelectTag id="a1144" needNullValue="true"
                                                                 valueName="a1144A"
                                                                 defaultkeys="${vo.a1144}"
                                                                 token="${sessionScope.OWASP_CSRFTOKEN}"
                                                                 defaultvalues="${vo.a1144A}"
                                                                 textClass="span10 m-wrap" radioOrCheckbox="radio"
                                                                 selectUrl="${path}/api/dictionary/select?typeCode=ZB28-1994/XXWC"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row-fluid">
                                    <div class="span6 ">
                                        <div id="a1114BGroup" class="control-group">
                                            <label class="control-label">主管机构</label>
                                            <div class="controls">
                                                <SelectTag:SelectTag id="a1114B" needNullValue="true"
                                                                     valueName="a1114A"
                                                                     defaultkeys="${vo.a1114B}"
                                                                     token="${sessionScope.OWASP_CSRFTOKEN}"
                                                                     defaultvalues="${vo.a1114A}"
                                                                     textClass="span10 m-wrap" radioOrCheckbox="radio"
                                                                     selectUrl="${path}/api/dictionary/select?typeCode=ZB02-2006/JGMC"/>
                                            </div>
                                        </div>
                                    </div>
                                <div class="span6 ">
                                    <div id="aPxxsGroup" class="control-group">
                                        <label class="control-label">网络培训</label>
                                        <div class="controls">
                                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                            <label class="radio">
                                                <input type="radio" name="aPxxs" value="1"
                                                       <c:if test="${vo.aPxxs =='1'}">checked</c:if>/>
                                                是&nbsp;&nbsp;&nbsp;
                                            </label>
                                            <label class="radio">
                                                <input type="radio" name="aPxxs" value="0"
                                                       <c:if test="${vo.aPxxs =='0' || empty vo.aPxxs}">checked</c:if>/>
                                                否
                                            </label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                                <div class="row-fluid">
                                    <div class="span6 ">
                                        <div id="aPxddGroup" class="control-group">
                                            <label class="control-label">国内(外)培训</label>
                                            <div class="controls">
                                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                <label class="radio">
                                                    <input type="radio" name="aSfdqdw" value="1"
                                                           <c:if test="${vo.aPxdd =='1' || empty vo.aPxdd}">checked</c:if>/>
                                                    内地&nbsp;&nbsp;&nbsp;
                                                </label>
                                                <label class="radio">
                                                    <input type="radio" name="aSfdqdw" value="3"
                                                           <c:if test="${vo.aPxdd =='3' }">checked</c:if>/>
                                                    港澳台&nbsp;&nbsp;&nbsp;
                                                </label>
                                                <label class="radio">
                                                    <input type="radio" name="aSfdqdw" value="3"
                                                           <c:if test="${vo.aPxdd =='2'}">checked</c:if>/>
                                                    国外
                                                </label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="span6 ">
                                        <div id="aPxjtdzGroup" class="control-group">
                                            <label class="control-label">培训地址</label>
                                            <div class="controls">
                                                <input type="text" class="span10 m-wrap" name="aPxjtdz" id="aPxjtdz"
                                                       maxlength="128" value="${vo.aPxjtdz}"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div>
                                    <center>
                                        <div style="margin:auto;">
                                            <button class="btn green" type="button" onclick="saveOrUpdate()">确定</button>
                                            <a class="btn" href="javascript:cencal()"><i class="icon-remove-sign"></i>
                                                取消</a>
                                        </div>
                                    </center>
                                </div>
                            </dd>
                        </dl>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="${path }/js/common/DataValidate.js"></script>
<script type="text/javascript">
    var a01Id = "${a01Id}";
    function cencal() {
        $.ajax({
            url : "${path }/zzb/gbgl/a11/ajax/list",
            type : "post",
            data : {"a01Id":a01Id},
            dataType : "html",
            headers:{
                OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
            },
            success : function(html){
                var view = $("#tab_show");
                view.html(html);
            },
            error : function(arg1, arg2, arg3){
                showTip("提示","工作经历加载失败");
            }
        });
    }


    function saveOrUpdate() {
        var myVld = new EstValidate("a11Form");
        var bool = myVld.form();
        if (!bool) {
            return false;
        }
        $.ajax({
            url : "${path }/zzb/gbgl/a11/saveOrUpdate",
            type : "post",
            data : $("#a11Form").serialize(),
            headers:{
                OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
            },
            dataType : "json",
            success : function(json){
                if(json.code==1){
                    cencal();
                }

            },
            error : function(){
            }
        });
    }
</script>