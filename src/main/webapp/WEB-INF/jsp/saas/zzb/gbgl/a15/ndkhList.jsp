<%@ taglib prefix="Select" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jsp/inc/servlet.jsp" %>
<%@include file="/WEB-INF/jsp/inc/taglib.jsp" %>
<%--
  ~ Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
  ~ http://www.hn-hisun.com
  ~ 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
  --%>
<title>年度考核</title>
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
                    <form action="" class="form-horizontal" id="a15Form" method="post">
                        <input type="hidden" name="a1500" value="${vo.a1500 }"/>
                        <input type="hidden" name="a01Id" value="${a01Id }"/>
                        <dl class="dlattrbute">
                            <dd>
                                <div class="row-fluid">
                                    <div class="span6 ">
                                        <div id="a1501AGroup" class="control-group">
                                            <label class="control-label"><span class="Required">*</span>考核类别</label>
                                            <div class="controls">
                                                <SelectTag:SelectTag id="a1501" needNullValue="true"
                                                                     valueName="a1501A"
                                                                     defaultkeys="${vo.a1501}" token="${sessionScope.OWASP_CSRFTOKEN}"
                                                                     defaultvalues="${vo.a1501A}"
                                                                     textClass="span10 m-wrap" radioOrCheckbox="radio"
                                                                     selectUrl="${path}/api/dictionary/select?typeCode=ZB17-2006/KHLB"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="span6 ">
                                        <div id="aKhndGroup" class="control-group">
                                            <label class="control-label">考核年度</label>
                                            <div class="controls">
                                                <input type="text" class="span10 m-wrap" name="aKhnd" id="aKhnd" maxlength="128" value="${vo.aKhnd}">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row-fluid">
                                    <div class="span6 ">
                                        <div id="a1504Group" class="control-group">
                                            <label class="control-label"><span class="Required">*</span>起始日期</label>
                                            <div class="controls">
                                                <input type="text" class="span10 m-wrap" name="a1504" id="a1504" maxlength="128" value="${vo.a1504}"
                                                       placeholder="日期格式 例如：2018或201801或20180101"  isDate="true" dateformat="yyyy,yyyymm,yyyymmdd"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="span6 ">
                                        <div id="a1505Group" class="control-group">
                                            <label class="control-label">截止日期</label>
                                            <div class="controls">
                                                <input type="text" class="span10 m-wrap" name="a1505" id="a1505" maxlength="128" value="${vo.a1505}"
                                                       placeholder="日期格式 例如：2018或201801或20180101"  isDate="true" dateformat="yyyy,yyyymm,yyyymmdd"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row-fluid">
                                    <div class="span6 ">
                                        <div id="a1517AGroup" class="control-group">
                                            <label class="control-label"><span class="Required">*</span>考核结论</label>
                                            <div class="controls">
                                                <SelectTag:SelectTag id="a1517" needNullValue="true"
                                                                     valueName="a1517A"
                                                                     defaultkeys="${vo.a1517}" token="${sessionScope.OWASP_CSRFTOKEN}"
                                                                     defaultvalues="${vo.a1517A}"
                                                                     textClass="span10 m-wrap" radioOrCheckbox="radio"
                                                                     selectUrl="${path}/api/dictionary/select?typeCode=ZB18-2006/KHJL"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="span6 ">
                                        <div id="a1507Group" class="control-group">
                                            <label class="control-label">考核单位</label>
                                            <div class="controls">
                                                <input type="text" class="span10 m-wrap" name="a1507" id="a1507" maxlength="128" value="${vo.a1507}" />
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="row-fluid">
                                    <div class="span10 ">
                                        <div id="a1511Group" class="control-group">
                                            <label class="control-label">考核意见</label>
                                            <div class="controls">
                                            <textarea id="a1511" name="a1511" class="span12 m-wrap" maxlength="512"
                                                      value="${vo.a1511}"
                                                      rows="2" style="resize: none;">${vo.a1511}</textarea>
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
                                <th >考核时间</th>
                                <th >考核结论</th>
                                <th >考核意见</th>
                                <th >考核单位</th>
                                <th width="40px">修改</th>
                                <th width="40px">删除</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${pager.datas}" var="vo">
                                <tr style="text-overflow:ellipsis;">
                                    <td><c:out value="${vo.khsj}"></c:out></td>
                                    <td><c:out value="${vo.a1517A}"></c:out></td>
                                    <td><c:out value="${vo.a1511}"></c:out></td>
                                    <td><c:out value="${vo.a1507}"></c:out></td>
                                    <td>
                                        <a href="javascript:edit('${vo.a1500}')" class="">修改</a>|
                                    </td>
                                    <td>
                                        <a href="javascript:del('${vo.a1500}','${vo.khsj}')" class="">删除</a>
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
         var myVld = new EstValidate("a15Form");
         var bool = myVld.form();
         if (!bool) {
             return false;
         }
         $.ajax({
             url : "${path }/zzb/gbgl/a14/saveOrUpdateNdkh",
             type : "post",
             data : $("#a15Form").serialize(),
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
                             "type":"n",
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
             data : {"type":"n"},
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
            data : {"a01Id":a01Id,"type":"n"},
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
         actionByConfirm1(voname, "${path}/zzb/gbgl/a14/del/" + id+"?type=n&OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}", {}, function (data, status) {
             if (data.success == true) {
                 $.ajax({
                     url : "${path }/zzb/gbgl/a14/ajax/edit",
                     type : "post",
                     data : {"a01Id":"${a01Id}","type":"n"},
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
             data : {"a01Id":"${a01Id}","type":"n","isShow":true},
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
             data : {"a01Id":"${a01Id}","type":"n","isShow":false},
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