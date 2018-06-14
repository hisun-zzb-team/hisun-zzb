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
                    <form action="" class="form-horizontal" id="a17Form" method="post">
                        <input type="hidden" name="a1700" value="${vo.a1700 }"/>
                        <input type="hidden" name="a01Id" value="${a01Id }"/>
                        <dl class="dlattrbute">
                            <dd>
                                <div class="row-fluid">
                                    <div class="span6 ">
                                        <div id="aQssjGroup" class="control-group">
                                            <label class="control-label"><span class="Required">*</span>起始日期</label>
                                            <div class="controls">
                                                <input type="text" class="span10 m-wrap" name="aQssj" id="aQssj"
                                                       placeholder="日期格式 例如：2018或201801或20180101" isDate="true" required
                                                       dateformat="yyyy,yyyymm,yyyymmdd"
                                                       maxlength="128" value="${vo.aQssj}"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="span6 ">
                                        <div id="aJzsjGroup" class="control-group">
                                            <label class="control-label">截止日期</label>
                                            <div class="controls">
                                                <input type="text" class="span10 m-wrap" name="aJzsj" id="aJzsj"
                                                       placeholder="日期格式 例如：2018或201801或20180101" isDate="true"
                                                       dateformat="yyyy,yyyymm,yyyymmdd"
                                                       maxlength="128" value="${vo.aJzsj}"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row-fluid">
                                    <div class="span6 ">
                                        <div id="aDwzwGroup" class="control-group">
                                            <label class="control-label"><span class="Required">*</span>所在单位及职务</label>
                                            <div class="controls">
                                                <input type="text" class="span10 m-wrap" name="aDwzw" id="aDwzw"
                                                       required
                                                       maxlength="128" value="${vo.aDwzw}"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="span6 ">
                                        <div id="aZwjbbGroup" class="control-group">
                                            <label class="control-label">行政级别</label>
                                            <div class="controls">
                                                <Tree:tree id="aZwjbb" valueName="aZwjba"
                                                           selectClass="span10 m-wrap"
                                                           treeUrl="${path}/api/dictionary/tree?typeCode=ZB09-2006/ZWJB"
                                                           token="${sessionScope.OWASP_CSRFTOKEN}"
                                                           submitType="get" dataType="json" isSearch="false"
                                                           checkedByTitle="true" isSelectTree="true"
                                                           defaultkeys="${vo.aZwjbb}"
                                                           defaultvalues="${vo.aZwjba}"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="row-fluid">
                                    <div class="span6 ">
                                        <div id="aJlmsGroup" class="control-group">
                                            <label class="control-label">工作内容</label>
                                            <div class="controls">
                                                <input type="text" class="span10 m-wrap" name="aJlms" id="aJlms"
                                                       maxlength="128" value="${vo.aJlms}"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="span6 ">
                                        <div id="aSfdqdwGroup" class="control-group">
                                            <label class="control-label">是否当前单位</label>
                                            <div class="controls">
                                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                <label class="radio">
                                                    <input type="radio" name="aSfdqdw" value="1"
                                                           <c:if test="${vo.aSfdqdw =='1'}">checked</c:if>/>
                                                    是&nbsp;&nbsp;&nbsp;
                                                </label>
                                                <label class="radio">
                                                    <input type="radio" name="aSfdqdw" value="0"
                                                           <c:if test="${vo.aSfdqdw =='0' || empty vo.aSfdqdw}">checked</c:if>/>
                                                    否
                                                </label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row-fluid">
                                    <div class="span10 ">
                                        <div id="aBzGroup" class="control-group">
                                            <label class="control-label">备注</label>
                                            <div class="controls">
                                            <textarea id="aBz" name="aBz" class="span12 m-wrap" maxlength="512"
                                                      value="${vo.aBz}"
                                                      rows="2" style="resize: none;">${vo.aBz}</textarea>
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
                    <div is="a17ListDiv">
                        <table class="table table-striped table-bordered table-hover dataTable table-set">
                            <thead>
                            <tr>
                                <th width="90px">起始日期</th>
                                <th width="60px">截止日期</th>
                                <th width="80px">所在单位及职务</th>
                                <th width="80px">行政级别</th>
                                <th width="80px">工作内容</th>
                                <th width="80px">备注</th>
                                <th width="80px">当前单位</th>
                                <th width="80px">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${pager.datas}" var="vo">
                                <tr style="text-overflow:ellipsis;">
                                    <td><c:out value="${vo.aQssj}"></c:out></td>
                                    <td><c:out value="${vo.aJzsj}"></c:out></td>
                                    <td><c:out value="${vo.aDwzw}"></c:out></td>
                                    <td><c:out value="${vo.aZwjba}"></c:out></td>
                                    <td><c:out value="${vo.aJlms}"></c:out></td>
                                    <td><c:out value="${vo.aBz}"></c:out></td>
                                    <td><c:if test="${vo.aSfdqdw ==1}">是</c:if><c:if
                                            test="${vo.aSfdqdw ==0}">否</c:if></td>
                                    <td>
                                        <a href="javascript:edit('${vo.a1700}')" class="">修改</a>|
                                        <a href="javascript:deleteA17('${vo.a1700}')" class="">删除</a>
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
         var myVld = new EstValidate("a17Form");
         var bool = myVld.form();
         if (!bool) {
             return false;
         }
         $.ajax({
             url : "${path }/zzb/gbgl/a17/saveOrUpdate",
             type : "post",
             data : $("#a17Form").serialize(),
             headers:{
                 OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
             },
             dataType : "json",
             success : function(json){
                 if(json.code==1){
                     $.ajax({
                         url : "${path }/zzb/gbgl/a17/ajax/list",
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

             },
             error : function(){
             }
         });
     }
     function pagehref(pageNum, pageSize) {
         $.ajax({
             async: false,
             type: "POST",
             url: "${path}/zzb/gbgl/a17/ajax/list?a01Id="+a01Id+"&pageNum=" + pageNum + "&pageSize=" + pageSize,
             dataType: "html",
             headers: {
                 "OWASP_CSRFTOKEN": '${sessionScope.OWASP_CSRFTOKEN}'
             },
             data: $("#searchForm").serialize(),
             success: function (html) {
                 $("#tab_show").html(html);
             },
             error: function () {
                 myLoading.hide();
                 showTip("提示", "出错了,请检查网络!", 2000);
             }
         });

     }

     function edit(id) {
         $.ajax({
             url : "${path }/zzb/gbgl/a17/ajax/list?a01Id="+a01Id+"&a17Id="+id,
             type : "post",
             data : {},
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

    function cencal() {
        $.ajax({
            url : "${path }/zzb/gbgl/a17/ajax/list",
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
</script>