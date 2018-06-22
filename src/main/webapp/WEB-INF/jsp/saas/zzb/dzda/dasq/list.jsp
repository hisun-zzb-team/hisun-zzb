<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/inc/taglib.jsp" %>
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

    <link href="${path}/css/common/common.css" rel="stylesheet" type="text/css"/>
    <!-- BEGIN PAGE LEVEL STYLES -->
    <link rel="stylesheet" href="${path }/css/DT_bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="${path }/css/bootstrap-fileupload.css">

    <link href="${path }/css/style.css" rel="stylesheet" type="text/css">
    <!-- END PAGE LEVEL STYLES -->
    <title>查阅管理</title>
    <style type="text/css">
        form {
            margin: 0 0 0px;
        }
    </style>
</head>
<body>

<div id="addModal" class="modal container hide fade" tabindex="-1" data-width="600">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close"  type="button"></button>
                <h3 class="modal-title" id="addTitle" >
                    档案阅档申请
                </h3>
            </div>
            <div class="modal-body" id="addDiv">
            </div>
        </div>
    </div>
</div>

<div class="container-fluid">
    <div class="row-fluid">
        <div class="span12 responsive">
            <%-- 表格开始 --%>
            <form class=""id="importForm" enctype="multipart/form-data">
                <div class="portlet-title">
                    <div class="caption">授权列表</div>
                    <div class="clearfix fr">

                        <a id="sample_editable_1_new" class="btn green" href="javascript:download()">
                            导出
                        </a>


                        <%--<span class="controllerClass btn green file_but" >--%>
                        <%--<i class="icon-circle-arrow-up"></i>清空数据--%>
                        <%--<input class="file_progress" type="file" name="attachFile" id="btn-browseTemplate">--%>
                        <%--</span>--%>
                    </div>

                </div>
            </form>
            <div class="clearfix">
                <div class="control-group">
                    <div id="query" style="float: left;">
                        <form action="${path }/zzb/dzda/cyshouquan/list" method="POST" id="searchForm" name="searchForm">
                            <input type="hidden" name="OWASP_CSRFTOKEN" value="${sessionScope.OWASP_CSRFTOKEN}"/>
                            <input type="hidden" name="pageNum" value="${pager.pageNum }" id="pageNum">
                            <input type="hidden" name="pageSize" value="${pager.pageSize }" id="pageSize">
                            申请查阅档案姓名：<input type="text" class="m-wrap" name="userName" id="userName" value="${userName}" style="width: 80px;" />
                            申请时间：
                            <input type="text" class="span12" style="width: 100px;" value='${starttime}' name="starttime" id="starttime" readonly/>
                            到：
                            <input type="text" class="span12" style="width: 100px;" value='${endtime}' name="endtime" id="endtime" readonly/>
                            授权状态：<select class="select_form" tabindex="-1" name="auditingState" id="auditingState" style="width: 100px; margin-bottom: 0px;" >
                            <option value="-1" >全部</option>
                            <option value="0" >待授权</option>
                            <option value="1" >已授权</option>
                            <option value="2" >已拒绝</option>
                        </select>
                            <button type="button" class="btn Short_but" onclick="searchSubmit()">查询</button>
                            <button type="button" class="btn Short_but" onclick="clearData()">清空</button>
                        </form>
                    </div>
                </div>

            </div>
            <div class="portlet-body">
                <table class="table table-striped table-bordered table-hover dataTable table-set">
                    <thead>
                    <TR height=28>
                        <th width=70>申请人</th>
                        <th width=70>查阅人</th>
                        <th width="120">申请时间</th>
                        <th width=100>申请查阅档案姓名</th>
                        <th width=150>申请查阅档案职务</th>
                        <th width=100>查阅申请内容</th>
                        <th width=100>查阅状态</th>
                        <th width=150>授权信息</th>
                        <th width=50>授权状态</th>
                        <th width="90">操作</th>
                    </thead>
                    <tbody>
                    <c:forEach items="${pager.datas}" var="vo">
                        <tr style="text-overflow:ellipsis;">
                            <TD ><c:out value="${vo.applyUserName}"></c:out></TD>
                            <TD><c:out value="${vo.e01Z807Name}"></c:out></TD>
                            <TD ><fmt:formatDate value="${vo.createDate}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></TD>
                            <TD ><c:out value="${vo.a0101}"></c:out></TD>
                            <TD ><c:out value="${vo.sqcydazw}"></c:out></TD>
                            <TD><c:out value="${vo.readContent}"></c:out ></TD>
                            <TD>
                                <c:choose>
                                    <c:when test="${vo.readState == 0}">
                                        未查阅
                                    </c:when>
                                    <c:when test="${vo.readState == 1}">
                                        正在查阅
                                    </c:when>
                                    <c:when test="${vo.readState == 2}">
                                        已收回
                                    </c:when>
                                    <c:when test="${vo.readState == 3}">
                                        已结束
                                    </c:when>
                                </c:choose></TD>
                            <TD >
                                <c:out value="${vo.accreditDate}"></c:out >
                                <br><c:if test="${not empty vo.sqdwpzld}">${vo.sqdwpzld} &nbsp;授权</c:if>

                               <%-- <a href="javascript:ydxiangqing('${vo.id}')">查阅情况</a>
                                <div style="width: 380px;z-index:1;padding-bottom:2px;overflow:hidden;white-space:nowrap;text-overflow:ellipsis;float:left">
                                    <a href="javascript:ydxiangqing('${vo.id}')">
                                        <c:if test="${not empty vo.a38Logs}">
                                            <c:forEach items="${vo.a38Logs.get(0).a38LogDetails}" var="vo1">
                                                <c:out value="${vo1.e01Z111}"></c:out>;
                                            </c:forEach>
                                        </c:if>
                                    </a>
                                </div>--%>
                            </TD>
                            <TD >
                                <c:choose>
                                    <c:when test="${vo.auditingState == 0}">
                                        待授权
                                    </c:when>
                                    <c:when test="${vo.auditingState == 1}">
                                        <a href="${path}/zzb/dzda/cyshouquan/view?id=${vo.id}&OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}">已授权</a>
                                    </c:when>
                                    <c:when test="${vo.auditingState == 2}">
                                        <a href="${path}/zzb/dzda/cyshouquan/view?id=${vo.id}&OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}">已拒绝</a>
                                    </c:when>
                                </c:choose>
                            </TD>
                            <TD >
                                <c:choose>
                                    <c:when test="${vo.auditingState == 0}">
                                        <a href="${path}/zzb/dzda/cyshouquan/toShouquan?id=${vo.id}&OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}">授权</a>|
                                    </c:when>
                                    <c:when test="${vo.auditingState == 1 && vo.readState == 1 || vo.readState == 0}">
                                        <a href="javascript:shouhuiQx('${vo.id}')">收回权限</a>|
                                    </c:when>
                                </c:choose>
                                <c:if test="${vo.auditingState == 1}">删除 </c:if>
                                <c:if test="${vo.auditingState != 1}"><a href="javascript:deleteSq('${vo.id}')">删除 </a></c:if>
                            </TD>
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
        <%-- 表格结束 --%>
    </div>
</div>
<div id="viewNeiRongModal" class="modal container hide fade" tabindex="-1" data-width="650">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close"  type="button"></button>
                <h3 class="modal-title" id="viewNeiRongTitle" >
                </h3>
            </div>
            <div class="modal-body" id="viewNeiRongDiv">
            </div>
        </div>
    </div>
</div>
<%-- END PAGE CONTENT--%>
</div>

<script type="text/javascript">
   (function(){
        App.init();

        $("#btn-browseTemplate").bind("change",function(evt){
            if($(this).val()){
                ajaxSubmit();
            }
            $(this).val('');
        });

    })();
   function view(id) {
       $.ajax({
           url:"${path}/zzb/dzda/cyshouquan/view",
           type : "post",
           data: {"id":id},
           headers:{
               OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
           },
           dataType : "html",
           success : function(html){
               $('#viewSqztDiv').html(html);

               $('#viewSqztModal').modal({
                   keyboard: true
               });
           },
           error : function(){
               showTip("提示","出错了请联系管理员", 1500);
           }
       });
   }

   var ydxiangqing = function(id){
       $.ajax({
           url:"${path}/zzb/dzda/cysq/ajax/cyqkList",
           type : "post",
           data: {"eApplyE01Z8Id":id},
           headers:{
               OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
           },
           dataType : "html",
           success : function(html){
               $('#viewNeiRongDiv').html(html);

               $('#viewNeiRongModal').modal({
                   keyboard: true
               });
           },
           error : function(){
               showTip("提示","出错了请联系管理员", 1500);
           }
       });
   }

    function pagehref (pageNum ,pageSize){
        $("#pageNum").val(pageNum);
        $("#pageSize").val(pageSize);
        document.searchForm.submit();
    }
    function shouhuiQx(id){
        actionByConfirm1('',"${path}/zzb/dzda/cyshouquan/shouhuiQx/"+id+"?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}",null,function(json){
            if(json.success){
                showTip("提示","操作成功");
                window.location.href ="${path }/zzb/dzda/cyshouquan/list?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}";
            }else{
                showTip("提示", json.message, 2000);
            }
        },"收回权限")
    }
    function deleteSq(id){
        actionByConfirm1('',"${path}/zzb/dzda/cysq/deleteSq/"+id+"?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}",null,function(json){
            if(json.code == 1){
                showTip("提示","删除成功");
                window.location.href ="${path }/zzb/dzda/cyshouquan/list?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}";
            }else{
                showTip("提示", json.message, 2000);
            }
        },"删除")
    }

    function shouquan(id){
        $.ajax({
            async:false,
            type:"POST",
            url:"${path}/zzb/dzda/cyshouquan/shouquan",
            dataType : "html",
            headers:{
                "OWASP_CSRFTOKEN":'${sessionScope.OWASP_CSRFTOKEN}'
            },
            data:{
                'id':id
            },
            success:function(html){
                $("#a32Table").hide();
                $("#catalogList").html(html);
            },
            error : function(){
                myLoading.hide();
                showTip("提示","出错了,请检查网络!",2000);
            }
        });
    }

    var add = function(){
        $.ajax({
            url:"${path}/zzb/app/console/daDemo/ajax/addApplyDa",
            type : "post",
            data: {},
            headers:{
                OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
            },
            dataType : "html",
            success : function(html){
                $('#addDiv').html(html);

                $('#addModal').modal({
                    keyboard: true
                });
            },
            error : function(){
                showTip("提示","出错了请联系管理员", 1500);
            }
        });
    }





    function searchSubmit(){
        document.searchForm.submit();
    }



    var del = function(id,itemName){
        actionByConfirm1(itemName, "${path}/zzb/app/console/asetA01/delete/" + id+"&OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}",{} ,function(data,status){
            if (data.success == true) {
                showTip("提示","删除成功", 2000);
                setTimeout(function(){window.location.href = "${path}/zzb/app/console/asetA01/list?b01Id=${b01Id}&mcid=${mcid}&OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}"},2000);
            }else{
                showTip("提示", data.message, 2000);
            }
        });
    };
    function uploadFile(fileName){
        document.getElementById("btn-"+fileName).click();
    }
    function clearData(){
        $("#userName").val("");
        $("#readContent").val("");
        $("#e01Z807Name").val("");
        $("#auditingState").val("-1");
        $("#pageNum").val("");
        $("#pageSize").val("");
        document.searchForm.submit();
    }
   $(function(){
       $("#auditingState option[value='${auditingState}']").attr("selected",
           true);

       $('#starttime').datepicker({
           format : 'yyyy-mm-dd',
           weekStart : 1,
           autoclose : true,
           todayBtn : 'linked',
           language : 'zh-CN'
       });
       $('#endtime').datepicker({
           format : 'yyyy-mm-dd',
           weekStart : 1,
           autoclose : true,
           todayBtn : 'linked',
           language : 'zh-CN'
       });
   })
   function download() {
       var userName = $("#userName").val();
       var readContent = $("#readContent").val();
       var e01Z807Name=$("#e01Z807Name").val();
       var auditingState = $("#auditingState").val();
       window.open("${path}/zzb/dzda/cyshouquan/download?"+"&userName="+userName+"&readContent="+readContent+"&e01Z807Name="+e01Z807Name+"&auditingState="+auditingState+
       "&OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}");
   }
    </script>
</body>
</html>
