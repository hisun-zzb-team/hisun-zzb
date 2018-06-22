<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/inc/servlet.jsp" %>
<%@include file="/WEB-INF/jsp/inc/taglib.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  ~ Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
  ~ http://www.hn-hisun.com
  ~ 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
  --%>

<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <link href="${path}/css/common/common.css" rel="stylesheet" type="text/css"/>
    <!-- BEGIN PAGE LEVEL STYLES -->
    <link rel="stylesheet" href="${path }/css/DT_bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="${path }/css/bootstrap-fileupload.css">

    <link href="${path }/css/style.css" rel="stylesheet" type="text/css">
    <!-- END PAGE LEVEL STYLES -->
    <title>阅档记录</title>
    <style type="text/css">
        .radio input[type="radio"], .checkbox input[type="checkbox"] {
            float: left;
            margin-left: 0px;
        }
    </style>
    <link rel="stylesheet" type="text/css"
          href="${path}/css/select2_metro.css" />
    <link href="${path}/css/common/common.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="${path}/css/DT_bootstrap.css" />
</head>
<body>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="span12 responsive">
            <%-- 表格开始 --%>
            <div class="portlet-body" style="max-height: 550px;overflow: auto;margin: 0px;">
                <form action="" class="form-horizontal" >
                <div id="sqrGroup" class="control-group">
                    <label class="control-label">授权人</label>
                    <div class="controls">
                        <input type="text" class="span8 m-wrap" name="sqr" maxlength="128" id="sqr" readonly
                               value="${vo.sqr }"/>
                    </div>
                </div>
                    <div id="sqsjGroup" class="control-group">
                        <label class="control-label">授权时间</label>
                        <div class="controls">
                            <input type="text" class="span8 m-wrap" name="sqsj" maxlength="128" id="" readonly
                                   value=" ${vo.eApplyE01Z8Vo.accreditDate}"/>
                            <%--<fmt:formatDate value="${vo.createDate}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>--%>
                        </div>
                    </div>
                <div id="sqztGroup" class="control-group">
                    <label class="control-label">同意授权</label>
                    <div class="controls">
                        <label class="radio">
                            <input type="radio" name="sqzt" value="1" <c:if test="${vo.sqzt==1}">checked</c:if>/>
                            是
                        </label>
                        <label class="radio">
                            <input type="radio" name="sqzt" value="2" <c:if test="${vo.sqzt==2}">checked</c:if>/>
                            否
                        </label>
                    </div>
                </div>
                <div id="tysqId" <c:if test="${vo.sqzt==2}">style="display: none" </c:if>>
                    <div id="sqclfwGroup" class="control-group">
                        <label class="control-label">部分授权</label>
                        <div class="controls">
                            <label class="radio">
                                <input type="radio" name="sqclfw" value="1" <c:if test="${vo.sqclfw==1}">checked</c:if>/>
                                是
                            </label>
                            <label class="radio">
                                <input type="radio" name="sqclfw" value="0" <c:if test="${vo.sqclfw==0}">checked</c:if>/>
                                否
                            </label>
                        </div>
                    </div>

                    <div id="sqcymlIdsGroup" class="control-group"  <c:if test="${vo.sqclfw==0}">style="display: none" </c:if>>
                        <label class="control-label">授权查看目录</label>
                        <div class="controls"> <%--${path}/zzb/dzda/mlcl/tpcl/ajax/tree/${a38Id}--%>
                            <input type="text" class="span8 m-wrap" value="${vo.sqcyml}" readonly
                                            />
                        </div>
                    </div>
                    <div id="sfyxxzGroup" class="control-group">
                        <label class="control-label">允许下载</label>
                        <div class="controls">
                            <label class="radio">
                                <input type="radio" name="sfyxxz" value="1" <c:if test="${vo.sfyxxz==1}">checked</c:if>/>
                                是
                            </label>
                            <label class="radio">
                                <input type="radio" name="sfyxxz" value="0" <c:if test="${vo.sfyxxz==0}">checked</c:if>/>
                                否
                            </label>
                        </div>
                    </div>

                    <div id="sfyxdyGroup" class="control-group">
                        <label class="control-label">允许打印</label>
                        <div class="controls">
                            <label class="radio">
                                <input type="radio" name="sfyxdy" value="1" <c:if test="${vo.sfyxdy==1}">checked</c:if>/>
                                是
                            </label>
                            <label class="radio">
                                <input type="radio" name="sfyxdy" value="0" <c:if test="${vo.sfyxdy==0}">checked</c:if>/>
                                否
                            </label>
                        </div>
                    </div>
                </div>
                <div id="sqbzGroup" class="control-group">
                    <label class="control-label">授权备注</label>
                    <div class="controls">
                        <textarea class="span8 m-wrap" name="sqbz" maxlength="128" id="sqbz" readonly
                                  style="resize: none;">${vo.sqbz}</textarea>
                    </div>
                </div>
                <div class="control-group">
                    <div class="controls mt10" style="margin-left: 270px">
                        <button type="button" class="btn btn-default" data-dismiss="modal"><i class='icon-remove-sign'></i> 关闭</button>
                    </div>
                </div>
                </form>
            </div>
        </div>
        <%-- 表格结束 --%>
    </div>
</div>

<script type="text/javascript">

</script>
</body>
</html>
