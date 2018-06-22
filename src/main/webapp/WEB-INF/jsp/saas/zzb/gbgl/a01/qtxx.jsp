<%@ taglib prefix="Select" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jsp/inc/servlet.jsp" %>
<%@include file="/WEB-INF/jsp/inc/taglib.jsp" %>
<%--
  ~ Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
  ~ http://www.hn-hisun.com
  ~ 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
  --%>
<title>添加人员</title>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<div class="container-fluid">

    <div class="row-fluid">
        <div class="span12">
            <div class="portlet box grey">
                <div class="portlet-title">
                    <div class="relationbetTop_but">
                    </div>
                </div>
                <form action="" class="form-horizontal" id="qtxxForm" method="post">
                    <input type="hidden" name="a0100" value="${vo.a0100 }"/>
                    <dl class="dlattrbute">
                        <dd>
                            <div class="row-fluid">
                                <div class="span10 ">
                                    <div id="cgjqkGroup" class="control-group">
                                        <label class="control-label" style="padding-top: 30px">出国境情况<br><a href="javascript:cgjqk()" id="cgjqkId">修改</a></label>
                                        <div class="controls">
                                            <textarea id="cgjqk" name="cgjqk" class="span12 m-wrap"
                                                      value="${vo.cgjqk}"
                                                      rows="8" style="resize: none;">${vo.cgjqk}</textarea>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row-fluid">
                                <div class="span10 ">
                                    <div id="zcqkGroup" class="control-group">
                                        <label class="control-label" style="padding-top: 30px">职称信息<br><a href="javascript:zcqk()" >修改</a></label>
                                        <div class="controls">
                                            <textarea id="zcqk" name="zcqk" class="span12 m-wrap"
                                                      value="${vo.zcqk}"
                                                      rows="8" style="resize: none;">${vo.zcqk}</textarea>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row-fluid">
                                <div class="span10 ">
                                    <div id="jrshzwqkGroup" class="control-group">
                                        <label class="control-label" style="padding-top: 30px">兼任社会职务情况</label>
                                        <div class="controls">
                                            <textarea id="jrshzwqk" name="jrshzwqk" class="span12 m-wrap"
                                                      value="${vo.jrshzwqk}"
                                                      rows="8" style="resize: none;">${vo.jrshzwqk}</textarea>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="row-fluid">
                                <div class="span10 ">
                                    <div id="dxdbqkGroup" class="control-group">
                                        <label class="control-label" style="padding-top: 30px">当选会议代表情况</label>
                                        <div class="controls">
                                            <textarea id="dxdbqk" name="dxdbqk" class="span12 m-wrap"
                                                      value="${vo.dxdbqk}"
                                                      rows="8" style="resize: none;">${vo.dxdbqk}</textarea>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="row-fluid">
                                <div class="span10 ">
                                    <div id="kycgqkGroup" class="control-group">
                                        <label class="control-label" style="padding-top: 30px">科研成果著作发明</label>
                                        <div class="controls">
                                            <textarea id="kycgqk" name="kycgqk" class="span12 m-wrap"
                                                      value="${vo.kycgqk}"
                                                      rows="8" style="resize: none;">${vo.kycgqk}</textarea>
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
<div id="cgjqkModal" class="modal container hide fade" tabindex="-1" data-width="1100">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close"  type="button"></button>
                <h3 class="modal-title" id="title1">
                    出国境情况
                </h3>
            </div>
            <div class="modal-body" id="cgjqkDiv">
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="${path }/js/common/DataValidate.js"></script>
<script type="text/javascript">
    function cgjqk(){
        $.ajax({
            url: "${path }/zzb/gbgl/a12/ajax/list",
            type: "post",
            data: {"a01Id": "${vo.a0100}"},
            dataType: "html",
            headers: {
                OWASP_CSRFTOKEN: "${sessionScope.OWASP_CSRFTOKEN}"
            },
            success: function (html) {
                $("#cgjqkDiv").html(html);
                $('#cgjqkModal').modal({
                    keyboard: true
                });
            },
            error: function (arg1, arg2, arg3) {
                showTip("提示", "其他信息加载失败");
            }
        });
    }
    function zcqk(){
        $.ajax({
            url: "${path }/zzb/gbgl/aZc/ajax/edit",
            type: "post",
            data: {"a01Id": "${vo.a0100}"},
            dataType: "html",
            headers: {
                OWASP_CSRFTOKEN: "${sessionScope.OWASP_CSRFTOKEN}"
            },
            success: function (html) {
                $("#zcqkDiv").html(html);
                $('#zcqkModal').modal({
                    keyboard: true
                });
            },
            error: function (arg1, arg2, arg3) {
                showTip("提示", "其他信息加载失败");
            }
        });
    }
    /*$("#cgjqkId").click(function () {
        $.ajax({
            url: "${path }/zzb/gbgl/a12/ajax/list",
            type: "post",
            data: {"a01Id": "${vo.a0100}"},
            dataType: "html",
            headers: {
                OWASP_CSRFTOKEN: "${sessionScope.OWASP_CSRFTOKEN}"
            },
            success: function (html) {
                    debugger
                 $("#cgjqkDiv").html(html);
                 $('#cgjqkModal').modal({
                 keyboard: true
                 });
            },
            error: function (arg1, arg2, arg3) {
                showTip("提示", "其他信息加载失败");
            }
        });
    })*/
</script>