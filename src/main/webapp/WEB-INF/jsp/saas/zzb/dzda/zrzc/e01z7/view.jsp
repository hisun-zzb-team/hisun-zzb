<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jsp/inc/servlet.jsp" %>
<%@include file="/WEB-INF/jsp/inc/taglib.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

    <!-- END PAGE LEVEL STYLES -->
    <title>档案接收</title>
    <style type="text/css">
    </style>
</head>
<body>
<div class="container-fluid">

    <div class="row-fluid">
        <div class="span12">
            <%-- BEGIN SAMPLE FORM PORTLET 表单主体--%>

            <div class="portlet box grey">
                <div class="portlet-title">
                    <div class="caption">查看档案转递</div>
                    <div class="clearfix fr">

                        <div class="btn-group" style="padding-bottom: 0px">
                            <%--<button type="button" class="btn btn-default" onclick="cancel()"><i
                                    class='icon-remove-sign'></i> 关闭
                            </button>--%>
                            <a href="#" onclick="cancel()" class="btn icn-only"><i class="icon-undo"></i>返回</a>
                        </div>
                    </div>
                </div>


                <div class="portlet-body form">
                    <!-- BEGIN FORM-->

                    <form action="" class="form-horizontal" id="form1" method="post" enctype="multipart/form-data">
                        <input type="hidden" name="id" value="" id="id">
                        <input type="hidden" name="filePath" value="" id="filePath">

                        <div class="row-fluid">
                            <div class="span6 ">
                                <div class="control-group" id="nameGroup">
                                    <label class="control-label">档案名称<span class="required">*</span></label>

                                    <div class="controls">
                                        <input type="text" class="span10 m-wrap" name="name" maxlength="200"
                                               id="name"
                                               required
                                               value="${vo.name}"/>
                                    </div>
                                </div>
                            </div>
                            <div class="span6 ">
                                <div id="e01Z704AGroup" class="control-group">
                                    <label class="control-label">转往单位</label>

                                    <div class="controls">
                                        <input type="text" class="span10 m-wrap" value="${vo.e01Z704A}"
                                               id="e01Z704A" name="e01Z704A">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row-fluid">
                            <div class="span6 ">
                                <div id="e01Z701Group" class="control-group">
                                    <label class="control-label">转递日期</label>

                                    <div class="controls">
                                        <input type="text" class="span10 m-wrap" value="${vo.e01Z701}" readonly
                                               id="e01Z701" name="e01Z701">
                                    </div>
                                </div>
                            </div>
                            <div class="span6 ">
                                <div class="control-group" id="e01Z721Group">
                                    <label class="control-label">转递原因</label>

                                    <div class="controls">
                                        <input type="text" class="span10 m-wrap" name="e01Z724" maxlength="200"
                                               readonly
                                               value="${vo.e01Z721}" id="e01Z721"
                                        />
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row-fluid">
                            <div class="span6 ">
                                <div class="control-group" id="e01Z724Group">
                                    <label class="control-label">回执人</label>

                                    <div class="controls">
                                        <input type="text" class="span10 m-wrap" name="e01Z724" maxlength="200"
                                               id="e01Z724" readonly
                                               value="${vo.e01Z724}"/>
                                    </div>
                                </div>
                            </div>
                            <div class="span6 ">
                                <div id="e01Z727Group" class="control-group">
                                    <label class="control-label">回执日期</label>

                                    <div class="controls">
                                        <input type="text" class="span10 m-wrap" value="${vo.e01Z727}" readonly
                                               id="e01Z727" name="e01Z727">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row-fluid">
                            <div class="span6 ">
                                <div class="control-group" id="e01Z711Group">
                                    <label class="control-label">转出正本数</label>

                                    <div class="controls">
                                        <input type="text" class="span10 m-wrap" number="true" name="e01Z711"
                                               maxlength="200" id="e01Z711"
                                               value="${vo.e01Z711}"/>
                                    </div>
                                </div>
                            </div>
                            <div class="span6 ">
                                <div class="control-group" id="e01Z714Group">
                                    <label class="control-label">转出副本数</label>

                                    <div class="controls">
                                        <input type="text" class="span10 m-wrap" number="true" name="e01Z714"
                                               maxlength="200" id="e01Z714"
                                               value="${vo.e01Z714}"/>
                                    </div>
                                </div>
                            </div>
                        </div>


                        <div class="row-fluid">
                            <div class="span6 ">
                                <div class="control-group" id="sjlyGroup">
                                    <label class="control-label"><span class="required">*</span>数据来源</label>

                                    <div class="controls">
                                        <select name="sjly"  class="span10 m-wrap" id="sjly" required>
                                            <option value=""></option>
                                            <option value="1" <c:if test="${vo.sjly eq '1'}">selected</c:if>>导入excel</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="span6 ">
                                <div id="fileNameGroup" class="control-group">
                                    <label class="control-label">文件下载</label>

                                    <div class="controls">
                                        <div class="btn-group" id="gbrmspbDownDiv" <c:if test="${vo.filePath}">
                                            style="visibility:hidden"</c:if>>
                                            <a class="btn blue" herf="javascript:void(0)" onclick="downloadFile()"><i
                                                    class="icon-circle-arrow-down"></i>下载文件</a>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>
                        <div class="row-fluid">
                            <div>
                                <div id="e01Z544Group" class="control-group">
                                    <label class="control-label">备注</label>

                                    <div class="controls">
                                            <textarea class="span11" rows="2" name="e01Z731" maxlength="400"
                                                      id="e01Z731" style="resize: none;">${vo.e01Z731}</textarea>
                                    </div>
                                </div>
                            </div>

                        </div>
                        <div class="row-fluid">

                        </div>
                    </form>
                </div>
            </div>

            <%-- END SAMPLE FORM PORTLET--%>
        </div>
    </div>

    <%-- END PAGE CONTENT--%>
</div>
<script type="text/javascript">
    (function () {
        App.init();

        $("#btn-browseTemplate").bind("change", function (evt) {
            if ($(this).val()) {
                ajaxSubmit();
            }
            $(this).val('');
        });

    })();
    function downloadFile(){
        window.open("${path }/zzb/dzda/dazd/down?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}&id=${vo.id}");
    }

    $(function () {
        $('#e01Z524').datepicker({
            format: 'yyyy-mm-dd',
            weekStart: 1,
            autoclose: true,
            todayBtn: 'linked',
            language: 'zh-CN'
        });
        $('#e01Z501').datepicker({
            format: 'yyyy-mm-dd',
            weekStart: 1,
            autoclose: true,
            todayBtn: 'linked',
            language: 'zh-CN'
        });
        $('#e01Z531').datepicker({
            format: 'yyyy-mm-dd',
            weekStart: 1,
            autoclose: true,
            todayBtn: 'linked',
            language: 'zh-CN'
        });
        $('#e01Z534').datepicker({
            format: 'yyyy-mm-dd',
            weekStart: 1,
            autoclose: true,
            todayBtn: 'linked',
            language: 'zh-CN'
        });
    })
    var cancel = function () {
        window.location.href = "${path }/zzb/dzda/dazd/list?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}";
    }


</script>
</body>
</html>
