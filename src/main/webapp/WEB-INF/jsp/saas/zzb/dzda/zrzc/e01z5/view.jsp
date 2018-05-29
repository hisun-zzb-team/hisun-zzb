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

</head>
<body>
<div class="container-fluid">

    <div class="row-fluid">
        <div class="span12">
            <%-- BEGIN SAMPLE FORM PORTLET 表单主体--%>

            <div class="portlet box grey">
                <div class="portlet-title">
                    <div class="caption">查看档案接收</div>
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
                                            <input type="text" class="span10 m-wrap" name="name" maxlength="200" id="name"
                                                   required
                                                   value="${vo.name}"/>
                                        </div>

                                    </div>
                                </div>
                                <div class="span6 ">
                                    <div id="e01Z507AGroup" class="control-group">
                                        <label class="control-label">来件单位名称</label>

                                        <div class="controls">
                                            <input type="text" class="span10 m-wrap" value="${vo.e01Z507A}"
                                                   id="e01Z507A" name="e01Z507A">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row-fluid">
                                <div class="span6 ">
                                    <div class="control-group" id="e01Z501Group">
                                        <label class="control-label">接收日期</label>

                                        <div class="controls">
                                            <input type="text" class="span10 m-wrap" name="e01Z501" maxlength="200"
                                                   id="e01Z501" readonly
                                                   value="${vo.e01Z501}"/>
                                        </div>
                                    </div>
                                </div>

                                <div class="span6 ">
                                    <div id="e01Z541Group" class="control-group">
                                        <label class="control-label">档案位置</label>

                                        <div class="controls">
                                            <input type="text" class="span10 m-wrap" value="${vo.e01Z541}"
                                                   id="e01Z541" name="e01Z541">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row-fluid">
                                <div class="span6 ">
                                    <div class="control-group" id="e01Z521Group">
                                        <label class="control-label">审核人</label>

                                        <div class="controls">
                                            <input type="text" class="span10 m-wrap" name="e01Z521" maxlength="200"
                                                   id="e01Z521"
                                                   value="${vo.e01Z521}"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="span6 ">
                                    <div id="e01Z524Group" class="control-group">
                                        <label class="control-label">审核日期</label>
                                        <div class="controls">
                                            <input type="text" class="span10 m-wrap" value="${vo.e01Z524}" readonly
                                                   id="e01Z524" name="e01Z524">
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="row-fluid">
                                <div class="span6 ">
                                    <div class="control-group" id="e01Z527Group">
                                        <label class="control-label">案卷质量</label>

                                        <div class="controls">
                                            <SelectTag:SelectTag id="e01Z527"  textClass="m-wrap span10" needNullValue="true"  defaultkeys="${vo.e01Z527}"
                                                                 token="${sessionScope.OWASP_CSRFTOKEN}"     radioOrCheckbox="radio" selectUrl="${path}/api/dictionary/select?typeCode=DAZL-2018"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="span6 ">
                                    <div id="e01Z531Group" class="control-group">
                                        <label class="control-label">回执日期</label>

                                        <div class="controls">
                                            <input type="text" class="span10 m-wrap" value="${vo.e01Z531}" readonly
                                                   id="e01Z531" name="e01Z531">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row-fluid">
                                <div class="span6 ">
                                    <div class="control-group" id="e01Z534Group">
                                        <label class="control-label">入库日期</label>

                                        <div class="controls">
                                            <input type="text" class="span10 m-wrap" name="e01Z534" maxlength="200"
                                                   id="e01Z534" readonly
                                                   value="${vo.e01Z534}"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="span6 ">
                                    <div id="e01Z537Group" class="control-group">
                                        <label class="control-label">入库审批人</label>

                                        <div class="controls">
                                            <input type="text" class="span10 m-wrap" value="${vo.e01Z537}"
                                                   id="e01Z537" name="e01Z537">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row-fluid">
                                <div class="span6 ">
                                    <div class="control-group" id="e01Z511Group">
                                        <label class="control-label">正本卷数</label>

                                        <div class="controls">
                                            <input type="text" class="span10 m-wrap" number="true" name="e01Z511"
                                                   maxlength="200" id="e01Z511"
                                                   value="${vo.name}"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="span6 ">
                                    <div class="control-group" id="e01Z514Group">
                                        <label class="control-label">副本卷数</label>

                                        <div class="controls">
                                            <input type="text" class="span10 m-wrap" number="true" name="e01Z514"
                                                   maxlength="200" id="e01Z514"
                                                   value="${vo.e01Z514}"/>
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
                                            <div class="btn-group" id="gbrmspbDownDiv" <c:if test="${empty vo.filePath}">
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
                                            <textarea class="span11" rows="2" name="e01Z544" maxlength="400"
                                                      id="e01Z544" style="resize: none;">${vo.e01Z544}</textarea>
                                        </div>
                                    </div>
                                </div>

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
            window.location.href = "${path }/zzb/dzda/dajs/list?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}";
        }
        function downloadFile(id){
            window.open("${path }/zzb/dzda/dajs/down?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}&id=${vo.id}");
        }

    </script>
</body>
</html>
