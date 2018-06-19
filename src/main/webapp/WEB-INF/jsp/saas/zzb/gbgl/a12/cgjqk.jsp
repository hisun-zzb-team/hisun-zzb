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
                <div id="a12Div">
                    <form action="" class="form-horizontal" id="a12Form" method="post">
                        <input type="hidden" name="a1200" value="${vo.a1200 }"/>
                        <input type="hidden" name="a01Id" value="${a01Id }"/>
                        <dl class="dlattrbute">
                            <dd>
                                <div class="row-fluid">
                                    <div class="span6 ">
                                        <div id="a1237Group" class="control-group">
                                            <label class="control-label"><span class="Required">*</span>起始日期</label>
                                            <div class="controls">
                                                <input type="text" class="span10 m-wrap" name="a1237" id="a1237"
                                                       placeholder="日期格式 例如：2018或201801或20180101" isDate="true" required
                                                       dateformat="yyyy,yyyymm,yyyymmdd"
                                                       maxlength="128" value="${vo.a1237}"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="span6 ">
                                        <div id="a1241Group" class="control-group">
                                            <label class="control-label">截止日期</label>
                                            <div class="controls">
                                                <input type="text" class="span10 m-wrap" name="a1241" id="a1241"
                                                       placeholder="日期格式 例如：2018或201801或20180101" isDate="true"
                                                       dateformat="yyyy,yyyymm,yyyymmdd"
                                                       maxlength="128" value="${vo.a1241}"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row-fluid">
                                    <div class="span6 ">
                                        <div id="a1201Group" class="control-group">
                                            <label class="control-label"><span class="Required">*</span>出国（境）地点</label>
                                            <div class="controls">
                                                <input type="text" class="span10 m-wrap" name="a1201" id="a1201"
                                                       required
                                                       maxlength="128" value="${vo.a1201}"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="span6 ">
                                        <div id="aCgtsGroup" class="control-group">
                                            <label class="control-label">出国（境）天数</label>
                                            <div class="controls">
                                                <input type="text" class="span10 m-wrap" name="aCgts" id="aCgts"
                                                       maxlength="128" value="${vo.aCgts}"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="row-fluid">
                                    <div class="span6 ">
                                        <div id="a1214Group" class="control-group">
                                            <label class="control-label">出国（境）类型</label>
                                            <div class="controls">
                                                <Tree:tree id="a1214" valueName="a1214A"
                                                           selectClass="span10 m-wrap"
                                                           treeUrl="${path}/api/dictionary/tree?typeCode=GB10301-1988"
                                                           token="${sessionScope.OWASP_CSRFTOKEN}"
                                                           submitType="get" dataType="json" isSearch="false"
                                                           checkedByTitle="true" isSelectTree="true"
                                                           defaultkeys="${vo.a1214}"
                                                           defaultvalues="${vo.a1214A}"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="span6 ">
                                        <div id="aPzsjGroup" class="control-group">
                                            <label class="control-label">批准时间</label>
                                            <div class="controls">
                                                <input type="text" class="span10 m-wrap" name="aPzsj" id="aPzsj"
                                                       placeholder="日期格式 例如：2018或201801或20180101" isDate="true"
                                                       dateformat="yyyy,yyyymm,yyyymmdd"
                                                       maxlength="128" value="${vo.aPzsj}"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row-fluid">
                                    <div class="span10 ">
                                        <div id="aDwzwGroup" class="control-group">
                                            <label class="control-label">单位职务</label>
                                            <div class="controls">
                                                <input type="text" class="span10 m-wrap" name="aDwzw" id="aDwzw"
                                                       maxlength="128" value="${vo.aDwzw}"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row-fluid">
                                    <div class="span10 ">
                                        <div id="aSxryGroup" class="control-group">
                                            <label class="control-label">随行人员</label>
                                            <div class="controls">
                                                <input type="text" class="span10 m-wrap" name="aSxry" id="aSxry"
                                                       maxlength="128" value="${vo.aSxry}"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row-fluid">
                                    <div class="span10 ">
                                        <div id="aCgqkGroup" class="control-group">
                                            <label class="control-label">出国（境）情况</label>
                                            <div class="controls">
                                            <textarea id="aCgqk" name="aBz" class="span12 m-wrap" maxlength="512"
                                                      value="${vo.aCgqk}"
                                                      rows="2" style="resize: none;">${vo.aCgqk}</textarea>
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
                <div class="portlet-body">
                    <div is="a12ListDiv">
                        <table class="table table-striped table-bordered table-hover dataTable table-set">
                            <thead>
                            <tr>
                                <th width="90px">起始日期</th>
                                <th width="60px">截止日期</th>
                                <th width="80px">出国（境）天数</th>
                                <th width="80px">出国（境）类型</th>
                                <th width="80px">出国（境）地点</th>
                                <th width="80px">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${pager.datas}" var="vo">
                                <tr style="text-overflow:ellipsis;">
                                    <td><c:out value="${vo.a1237}"></c:out></td>
                                    <td><c:out value="${vo.a1241}"></c:out></td>
                                    <td><c:out value="${vo.aCgts}"></c:out></td>
                                    <td><c:out value="${vo.a1214A}"></c:out></td>
                                    <td><c:out value="${vo.a1201}"></c:out></td>
                                    <td>
                                        <a href="javascript:edit('${vo.a1200}')" class="">修改</a>|
                                        <a href="javascript:deleteA12('${vo.a1200}')" class="">删除</a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <jsp:include page="/WEB-INF/jsp/common/page.jsp">
                            <jsp:param value="${pager.total }" name="total"/>
                            <jsp:param value="${pager.pageCount }" name="endPage"/>
                            <jsp:param value="${pager.pageSize }" name="pageSize"/>
                            <jsp:param value="${pager.pageNum }" name="page"/>
                        </jsp:include>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="${path }/js/common/DataValidate.js"></script>
<script type="text/javascript">
    var a01Id = "${a01Id}";

    function saveOrUpdate() {
        var myVld = new EstValidate("a12Form");
        var bool = myVld.form();
        if (!bool) {
            return false;
        }
        $.ajax({
            url : "${path }/zzb/gbgl/a12/saveOrUpdate",
            type : "post",
            data : $("#a12Form").serialize(),
            headers:{
                OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
            },
            dataType : "json",
            success : function(json){
                if(json.code==1){
                    $.ajax({
                        url : "${path }/zzb/gbgl/a12/ajax/list",
                        type : "post",
                        data : {"a01Id":a01Id},
                        dataType : "html",
                        headers:{
                            OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
                        },
                        success : function(html){
                            $("#cgjqkDiv").html(html);
                            $('#cgjqkModal').modal({
                                keyboard: true
                            });
                        },
                        error : function(arg1, arg2, arg3){
                            showTip("提示","出镜情况加载失败");
                        }
                    });
                }

            },
            error : function(){
            }
        });
    }
    function pagehref(pageNum, pageSize) {
        $.ajax({
            async: false,
            type: "POST",
            url: "${path}/zzb/gbgl/a12/ajax/list?a01Id="+a01Id+"&pageNum=" + pageNum + "&pageSize=" + pageSize,
            dataType: "html",
            headers: {
                "OWASP_CSRFTOKEN": '${sessionScope.OWASP_CSRFTOKEN}'
            },
            data: {},
            success: function (html) {
                $("#cgjqkDiv").html(html);
                $('#cgjqkModal').modal({
                    keyboard: true
                });
            },
            error: function () {
                myLoading.hide();
                showTip("提示", "出错了,请检查网络!", 2000);
            }
        });

    }

    function edit(id) {
        $.ajax({
            url : "${path }/zzb/gbgl/a12/ajax/list?a01Id="+a01Id+"&a12Id="+id,
            type : "post",
            data : {},
            dataType : "html",
            headers:{
                OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
            },
            success : function(html){
                $("#cgjqkDiv").html(html);
                $('#cgjqkModal').modal({
                    keyboard: true
                });
            },
            error : function(arg1, arg2, arg3){
                showTip("提示","出镜情况加载失败");
            }
        });
    }

    function cencal() {
        $.ajax({
            url : "${path }/zzb/gbgl/a12/ajax/list",
            type : "post",
            data : {"a01Id":a01Id},
            dataType : "html",
            headers:{
                OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
            },
            success : function(html){
                $("#cgjqkDiv").html(html);
                $('#cgjqkModal').modal({
                    keyboard: true
                });
            },
            error : function(arg1, arg2, arg3){
                showTip("提示","出镜情况加载失败");
            }
        });
    }
    function deleteA12(id) {
        actionByConfirm1("", "${path}/zzb/gbgl/a12/delete?id=" + id+"&OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}", {}, function (data, status) {
            if (data.success == true) {
                $.ajax({
                    url : "${path }/zzb/gbgl/a12/ajax/list",
                    type : "post",
                    data : {"a01Id":a01Id},
                    dataType : "html",
                    headers:{
                        OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
                    },
                    success : function(html){
                        $("#cgjqkDiv").html(html);
                        $('#cgjqkModal').modal({
                            keyboard: true
                        });
                    },
                    error : function(arg1, arg2, arg3){
                        showTip("提示","出镜情况加载失败");
                    }
                });
            } else {
                showTip("提示", data.msg, 2000);
            }
        });
    }

</script>