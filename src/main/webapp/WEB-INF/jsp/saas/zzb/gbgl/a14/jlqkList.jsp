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
                <div id="a17Div">
                    <form action="" class="form-horizontal" id="a14Form" method="post">
                        <input type="hidden" name="a14Z200" value="${vo.a14Z200 }"/>
                        <input type="hidden" name="a01Id" value="${a01Id }"/>
                        <dl class="dlattrbute">
                            <dd>
                                <div class="row-fluid">
                                    <div class="span6 ">
                                        <div id="a14Z204AGroup" class="control-group">
                                            <label class="control-label"><span class="Required">*</span>奖励名称</label>
                                            <div class="controls">
                                                <Tree:tree id="a14Z204B" valueName="a14Z204A"
                                                           selectClass="span10 m-wrap" onClick="jlRySm"
                                                           treeUrl="${path}/api/dictionary/tree?typeCode=ZB65-2006/JLDM"
                                                           token="${sessionScope.OWASP_CSRFTOKEN}"
                                                           submitType="get" dataType="json" isSearch="false"
                                                           checkedByTitle="true" isSelectTree="true"
                                                           defaultkeys="${vo.a14Z204B}"
                                                           defaultvalues="${vo.a14Z204A}"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="span6 ">
                                        <div id="aA14Z207AGroup" class="control-group">
                                            <label class="control-label">荣誉名称</label>
                                            <div class="controls">
                                                <%--<Tree:tree id="aA14Z207B" valueName="aA14Z207A"--%>
                                                           <%--selectClass="span10 m-wrap"--%>
                                                           <%--treeUrl="${path}/api/dictionary/tree?typeCode=ZB66-2006/CHJZ"--%>
                                                           <%--token="${sessionScope.OWASP_CSRFTOKEN}"--%>
                                                           <%--submitType="get" dataType="json" isSearch="false"--%>
                                                           <%--checkedByTitle="true" isSelectTree="true"--%>
                                                           <%--defaultkeys="${vo.aA14Z207B}"--%>
                                                           <%--defaultvalues="${vo.aA14Z207A}"/>--%>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row-fluid">
                                    <div class="span6 ">
                                        <div id="a14Z211Group" class="control-group">
                                            <label class="control-label"><span class="Required">*</span>获得奖励荣誉时间</label>
                                            <div class="controls">
                                                <input type="text" class="span10 m-wrap" name="a14Z211" id="a14Z211" maxlength="128" value="${vo.a14Z211}"
                                                       placeholder="日期格式 例如：2018或201801或20180101"  isDate="true" dateformat="yyyy,yyyymm,yyyymmdd"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="span6 ">
                                        <div id="a14Z227Group" class="control-group">
                                            <label class="control-label">撤销奖励时间</label>
                                            <div class="controls">
                                                <input type="text" class="span10 m-wrap" name="a14Z227" id="a14Z227" maxlength="128" value="${vo.a14Z227}"
                                                       placeholder="日期格式 例如：2018或201801或20180101"  isDate="true" dateformat="yyyy,yyyymm,yyyymmdd"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="row-fluid">
                                    <div class="span10 ">
                                        <div id="a14Z224Group" class="control-group">
                                            <label class="control-label">奖励和荣誉说明</label>
                                            <div class="controls">
                                            <textarea id="a14Z224" name="a14Z224" class="span12 m-wrap" maxlength="512"
                                                      value="${vo.a14Z224}"
                                                      rows="2" style="resize: none;">${vo.a14Z224}</textarea>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="row-fluid">
                                    <div class="span6 ">
                                        <div id="aJljbaGroup" class="control-group">
                                            <label class="control-label">奖励、荣誉级别</label>
                                            <div class="controls">
                                                <Tree:tree id="aJljbb" valueName="aJljba"
                                                           selectClass="span10 m-wrap"
                                                           treeUrl="${path}/api/dictionary/tree?typeCode=ZB03-1994/DWJB"
                                                           token="${sessionScope.OWASP_CSRFTOKEN}"
                                                           submitType="get" dataType="json" isSearch="false"
                                                           checkedByTitle="true" isSelectTree="true"
                                                           defaultkeys="${vo.aJljba}"
                                                           defaultvalues="${vo.aJljba}"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="span6 ">
                                        <div id="a14Z214AGroup" class="control-group">
                                            <label class="control-label">批准单位</label>
                                            <div class="controls">
                                                <input type="text" class="span10 m-wrap" name="a14Z214A" id="a14Z214A"
                                                       maxlength="128" value="${vo.a14Z214A}"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div>
                                    <center>
                                        <div style="margin:auto;">
                                            <button class="btn green" type="button" onclick="saveOrUpdate()">确定</button>
                                            <a class="btn" href="javascript:cencal()"><i class="icon-remove-sign"></i>
                                                清空</a>
                                        </div>
                                    </center>
                                </div>
                            </dd>
                        </dl>
                    </form>
                </div>
                <div class="portlet-body">
                    <div is="a17ListDiv">
                        <table class="table table-striped table-bordered table-hover dataTable table-set">
                            <thead>
                            <tr>
                                <th >奖励时间</th>
                                <th >奖励、荣誉名称</th>
                                <th >奖励、荣誉说明</th>
                                <th >奖励荣誉级别</th>
                                <th width="40px">修改</th>
                                <th width="40px">删除</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${pager.datas}" var="vo">
                                <tr style="text-overflow:ellipsis;">
                                    <td><c:out value="${vo.jlsj}"></c:out></td>
                                    <td><c:out value="${vo.jlrymc}"></c:out></td>
                                    <td><c:out value="${vo.a14Z224}"></c:out></td>
                                    <td><c:out value="${vo.aJljba}"></c:out></td>
                                    <%--<td><c:out value="${vo.aJlms}"></c:out></td>--%>
                                    <%--<td><c:out value="${vo.aBz}"></c:out></td>--%>
                                    <%--<td><c:if test="${vo.aSfdqdw ==1}">是</c:if><c:if--%>
                                            <%--test="${vo.aSfdqdw ==0}">否</c:if></td>--%>
                                    <td>
                                        <a href="javascript:edit('${vo.a14Z200}')" class="">修改</a>|
                                    </td>
                                    <td>
                                        <a href="javascript:del('${vo.a14Z200}','${vo.jlrymc}')" class="">删除</a>
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
                    <div>
                        <center>
                            <div style="margin:auto;">
                                <button class="btn green" type="button" onclick="showJlqk()">确定</button>
                                <a class="btn" href="javascript:hidJlqk()"><i class="icon-remove-sign"></i>
                                    取消</a>
                            </div>
                        </center>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="${path }/js/common/DataValidate.js"></script>
<script type="text/javascript">
     var a01Id = "${a01Id}";

     function jlRySm(event, treeId, treeNode) {
         $("#a14Z224").val(treeNode.name);
     }

     function saveOrUpdate() {
         var myVld = new EstValidate("a14Form");
         var bool = myVld.form();
         if (!bool) {
             return false;
         }
         $.ajax({
             url : "${path }/zzb/gbgl/a14/saveOrUpdateJlqk",
             type : "post",
             data : $("#a14Form").serialize(),
             headers:{
                 OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
             },
             dataType : "json",
             success : function(json){
                 if(json.code==1){
                     $.ajax({
                         async: false,
                         type: "POST",
                         url: "${path}/zzb/gbgl/a14/ajax/edit",
                         dataType: "html",
                         headers: {
                             "OWASP_CSRFTOKEN": '${sessionScope.OWASP_CSRFTOKEN}'
                         },
                         data: {
                             "type":"j",
                             "a01Id":"${a01Id}"
                         },
                         success: function (html) {
                             var view = $("#tab_show");
                             view.html(html);
                         },
                         error: function () {
                             myLoading.hide();
                             showTip("提示", "出错了,请检查网络!", 2000);
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
             url: "${path}/zzb/gbgl/a14/ajax/list?a01Id="+a01Id+"&pageNum=" + pageNum + "&pageSize=" + pageSize,
             dataType: "html",
             headers: {
                 "OWASP_CSRFTOKEN": '${sessionScope.OWASP_CSRFTOKEN}'
             },
             data: $("#searchForm").serialize(),
             success: function (html) {
                 $("#rightList").html(html);
             },
             error: function () {
                 myLoading.hide();
                 showTip("提示", "出错了,请检查网络!", 2000);
             }
         });

     }

     function edit(id) {
         $.ajax({
             url : "${path }/zzb/gbgl/a14/ajax/edit?a01Id="+a01Id+"&a14Z2Id="+id,
             type : "post",
             data : {"type":"j"},
             dataType : "html",
             headers:{
                 OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
             },
             success : function(html){
                 var view = $("#tab_show");
                 view.html(html);
             },
             error : function(arg1, arg2, arg3){
                 showTip("提示","加载失败");
             }
         });
     }

    function cencal() {
        $.ajax({
            url : "${path }/zzb/gbgl/a14/ajax/edit",
            type : "post",
            data : {"a01Id":a01Id,"type":"j"},
            dataType : "html",
            headers:{
                OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
            },
            success : function(html){
                var view = $("#tab_show");
                view.html(html);
            },
            error : function(arg1, arg2, arg3){
                showTip("提示","加载失败");
            }
        });
    }

     function del(id, voname) {
         actionByConfirm1(voname, "${path}/zzb/gbgl/a14/del/" + id+"?type=j&OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}", {}, function (data, status) {
             if (data.success == true) {
                 $.ajax({
                     url : "${path }/zzb/gbgl/a14/ajax/edit",
                     type : "post",
                     data : {"a01Id":"${a01Id}","type":"j"},
                     dataType : "html",
                     headers:{
                         OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
                     },
                     success : function(html){
                         var view = $("#tab_show");
                         view.html(html);
                     },
                     error : function(arg1, arg2, arg3){
                         showTip("提示","职务管理加载失败");
                     }
                 });
             } else {
                 showTip("提示", data.msg, 2000);
             }
         });
     }

     function showJlqk(){
         $.ajax({
             url : "${path }/zzb/gbgl/a14/ajax/jckh",
             type : "post",
             data : {"a01Id":"${a01Id}","type":"j","isShow":true},
             dataType : "html",
             headers:{
                 OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
             },
             success : function(html){
                 var view = $("#tab_show");
                 view.html(html);
             },
             error : function(arg1, arg2, arg3){
                 showTip("提示","职务管理加载失败");
             }
         });
     }

     function hidJlqk(){
         $.ajax({
             url : "${path }/zzb/gbgl/a14/ajax/jckh",
             type : "post",
             data : {"a01Id":"${a01Id}","type":"j","isShow":false},
             dataType : "html",
             headers:{
                 OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
             },
             success : function(html){
                 var view = $("#tab_show");
                 view.html(html);
             },
             error : function(arg1, arg2, arg3){
                 showTip("提示","职务管理加载失败");
             }
         });
     }
</script>