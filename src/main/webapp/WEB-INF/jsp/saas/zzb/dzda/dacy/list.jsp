<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/inc/servlet.jsp" %>
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
    <style type="text/css">
        ul.ztree{margin-bottom: 10px; background: #f1f3f6 !important;}
        .portlet.box.grey.mainleft{background-color: #f1f3f6;overflow: hidden; padding: 0px !important; margin-bottom: 0px;}
        .main_left{float:left; width:220px;  margin-right:10px; background-color: #f1f3f6; }
        .main_right{display: table-cell; width:2000px;}
    </style>
    <!-- END PAGE LEVEL STYLES -->
    <title>阅档申请</title>
    <style type="text/css">
        form {
            margin: 0 0 0px;
        }
    </style>
</head>
<body>

<div id="addModal" class="modal container hide fade" tabindex="-1" data-width="800">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close"  type="button"></button>
                <h3 class="modal-title" id="addTitle" >
                    填写查阅申请
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
                    <div class="caption">申请列表</div>
                    <div class="clearfix fr">
                        <a id="sample_editable_1_new" class="btn green" href="javascript:add()">
                            <i class="icon-plus"></i>查阅申请
                        </a>
                    </div>

                </div>
            </form>
            <div class="clearfix">
                <div class="control-group">
                    <div id="query" style="float: left;">
                        <form action="/zzb/dzda/cysq/list" method="POST" id="searchForm" name="searchForm">
                            <%--<input type="hidden" id="b01Id" name="b01Id" value="${b01Id}"/>--%>
                            <input type="hidden" name="OWASP_CSRFTOKEN" value="${sessionScope.OWASP_CSRFTOKEN}"/>
                            <input type="hidden" name="pageNum" value="${pager.pageNum }" id="pageNum">
                            <input type="hidden" name="pageSize" value="${pager.pageSize }" id="pageSize">
                            查阅档案对象姓名：<input type="text" class="m-wrap" name="userName" id="userName" value="${userName}" style="width: 80px;" />
                            申请时间：
                                <input type="text" class="span12" style="width: 100px;" value='${starttime}' name="starttime" id="starttime" readonly/>
                            到：
                                <input type="text" class="span12" style="width: 100px;" value='${endtime}' name="endtime" id="endtime" readonly/>
                              申请状态：<select class="select_form" tabindex="-1" name="auditingState" id="auditingState" style="width: 100px; margin-bottom: 0px;" >
                                    <option value="" >全部</option>
                                    <option value="0" >待授权</option>
                                    <option value="1" >已授权</option>
                                    <option value="2" >已拒绝</option>
                             </select>
                            查阅状态：<select class="select_form" tabindex="-1" name="readState" id="readState" style="width: 100px; margin-bottom: 0px;" >
                                    <option value="" >全部</option>
                                    <option value="0" >未查阅</option>
                                    <option value="1" >正在查阅</option>
                                    <option value="2" >已收回</option>
                                    <option value="3" >已拒绝</option>
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
                        <th width=100>查阅档案对象姓名</th>
                        <th width=150>查阅档案对象单位及职务</th>
                        <th width=100>查阅档案内容</th>
                        <%--<th width="100" >查阅情况</th>--%>
                        <th width=50>申请状态</th>
                        <th width=50>查阅状态</th>
                        <th width="70">操作</th>
                        <th width="70">附件材料</th>
                    </thead>
                    <tbody>
                        <c:forEach items="${pager.datas}" var="vo">
                        <tr style="text-overflow:ellipsis;">
                            <TD width="10%"><c:out value="${vo.applyUserName}"></c:out></TD>
                            <TD width="10%"><c:out value="${vo.e01Z807Name}"></c:out> </TD>
                            <TD ><fmt:formatDate value="${vo.createDate}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></TD>
                            <TD width="10%">
                                    <c:out value="${vo.a0101}"></c:out>
                            </TD>
                            <TD width="10%"><c:out value="${vo.sqcydazw}"></c:out></TD>
                            <TD width="10%"><c:out value="${vo.readContent}"></c:out> </TD>
                           <%-- <TD width="20%">
                                &lt;%&ndash;<a href="javascript:ydxiangqing('${vo.id}')">详情</a>&ndash;%&gt;
                                <div style="width: 380px;z-index:1;padding-bottom:2px;overflow:hidden;white-space:nowrap;text-overflow:ellipsis;float:left">
                                    <a href="javascript:ydxiangqing('${vo.id}')">
                                        <c:if test="${not empty vo.a38Logs}">
                                            <c:forEach items="${vo.a38Logs.get(0).a38LogDetails}" var="vo1">
                                               <c:out value="${vo1.e01Z111}"></c:out>;
                                            </c:forEach>
                                        </c:if>
                                    </a>
                                </div>
                            </TD>--%>
                            <TD width="10%">
                                <c:choose>
                                    <c:when test="${vo.auditingState == 0}">
                                       待授权
                                    </c:when>
                                    <c:when test="${vo.auditingState == 1}">
                                       <a href="javascript:viewSqzt('${vo.id}')">已授权</a>
                                    </c:when>
                                    <c:when test="${vo.auditingState == 2}">
                                        <a href="javascript:viewSqzt('${vo.id}')">已拒绝</a>
                                    </c:when>
                                </c:choose></TD>
                            <TD width="10%">
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
                            <TD width="10%">
                                <c:choose>
                                    <c:when test="${vo.auditingState == 0}">
                                        <a href="javascript:editCysq('${vo.id}')">修改</a>|
                                    </c:when>
                                    <c:when test="${vo.auditingState == 1 && vo.readState !=2 && vo.readState !=3}">
                                        <a href="javascript:viewImageMain('${vo.a38.id}','${vo.a0101}','${vo.id}')">浏览</a>|
                                    </c:when>
                                    <c:when test="${vo.auditingState == 1 && vo.readState ==2 || vo.readState ==3}">
                                        <a href="javascript:editCysq('${vo.id}')">查看</a>|
                                    </c:when>
                                </c:choose>
                                <a href="javascript:deleteYdsq('${vo.id}','${vo.auditingState}')">删除 </a>
                            </TD>
                            <TD width="10%">
                                <c:if test="${empty vo.applyFilePath}">无附件</c:if>
                                <c:if test="${not empty vo.applyFilePath}"><a href="javascript:downloadFile('${vo.id}')">下载</a></c:if>
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
    <div id="viewImgModal" class="modal container hide fade" tabindex="-1" data-width="1010" data-height="600">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="btn btn-default" style="float: right;font-weight: bold;" data-dismiss="modal" onclick="hiddenViewImgModalForLiulan()"><i class='icon-remove-sign'></i> 关闭</button>
                    <button type="button" id="daYinDa" class="btn green" style="float: right;margin-right: 5px" onclick="daYinDa();" disabled>打印</button>
                    <button type="button" id="xiaZaiDa" class="btn green" style="float: right;margin-right: 5px" onclick="xiaZaiDa();" disabled>下载</button>
                    <button type="button" id="button4" class="btn green" style="float: right;margin-right: 5px" onclick="changeImage('end');" disabled>最后一页</button>
                    <button type="button" id="button3" class="btn green" style="float: right;margin-right: 5px" onclick="changeImage('down');" disabled>下一页</button>
                    <button type="button" id="button2" class="btn green" style="float: right;margin-right: 5px" onclick="changeImage('up');" disabled>上一页</button>
                    <button type="button" id="button1" class="btn green" style="float: right;margin-right: 5px" onclick="changeImage('one');" disabled>第一页</button>
                    <div class="btn-group" style="padding-bottom: 0px;float: right;right: 5px;margin-left: 5px;">
                        <a class="btn green dropdown-toggle" data-toggle="dropdown" href="#">
                            显示方式<i class="icon-angle-down"></i>
                        </a>
                        <ul class="dropdown-menu">
                            <li>
                                <a onclick="changeViewShowType('qp')">全屏显示</a>
                            </li>
                            <li>
                                <a onclick="changeViewShowType('db')">等比显示</a>
                            </li>
                        </ul>
                    </div>
                    <a class="btn green"  style="float: right;font-weight: bold;margin-right: 5px;" href="javascript:jieshuyuedang()">
                        <i class=' icon-remove-sign'></i>结束阅档
                    </a>
                    <span id="daojishi"  style="display: none;float: right;font-weight: bold;margin-right: 5px;margin-top: 5px">剩余阅档时间:<span id="timespan" style="color: #C90003"></span></span>
                    <%--<button type="button" class="btn btn-default" style="float: right;font-weight: bold;" data-dismiss="modal" onclick="jieshuyuedang()"><i class='icon-remove-sign'></i> 结束阅档</button>--%>
                    <%--<button data-dismiss="modal" class="close" type="button" onclick="hiddenViewImgModal()"></button>--%>
                    <input type="hidden" name="eApplyE01Z8Id" id = "eApplyE01Z8Id"/>
                    <%--<input type="hidden" name="a38LogId" id = "a38LogId"/>--%>
                    <input type="hidden" name="syReadTime" id = "syReadTime"/>
                    <h3 class="modal-title" id="title">
                        “${a0101}”档案图片
                    </h3>
                </div>
                <div class="modal-body" id="viewImgDiv" style="background-color: #f1f3f6;margin-top: 0px;padding-top: 0px;padding-bottom: 0px">
                </div>
            </div>
        </div>
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

<div id="viewSqztModal" class="modal container hide fade" tabindex="-1" data-width="650">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close"  type="button"></button>
                <h3 class="modal-title" id="viewSqztTitle" >
                    授权信息
                </h3>
            </div>
            <div class="modal-body" id="viewSqztDiv">
            </div>
        </div>
    </div>
</div>
<%-- END PAGE CONTENT--%>
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
    $(function(){
        $("#auditingState option[value='${auditingState}']").attr("selected",
                true);

        $("#readState option[value='${readState}']").attr("selected",
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

    function viewSqzt(id) {
        $.ajax({
            url:"${path}/zzb/dzda/cysq/ajax/viewSqzt",
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

    function downloadFile(id){
        window.open("${path }/zzb/dzda/cysq/ajax/down?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}&id="+id);
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

/*    function hiddenViewImgModal() {//隐藏图片查看时 删除临时的解密图片
        $('#viewImgModal').modal('hide');
        $('#viewImgDiv').html("");
    }*/
    var viewImageMain = function (a38Id,a0101,id) {
        var divHeight = $(window).height() -60;
        var divWidth = $(window).width() - 100;
        $('#viewImgModal').attr("data-height", divHeight);
        $('#viewImgModal').attr("data-width", divWidth);
        $("#eApplyE01Z8Id").val(id);
        var myDirName = $("#myDirName").val();
        $.ajax({
            url: "${path}/zzb/dzda/mlcl/images/ajax/viewMain/"+a38Id,
            type: "post",
            data: {
                "a0101":a0101,
                "eApplyE01Z8Id":id
            },
            headers: {
                OWASP_CSRFTOKEN: "${sessionScope.OWASP_CSRFTOKEN}"
            },
            dataType: "html",
            success: function (html) {
                $('#viewImgDiv').html(html);
                $('#title').text(a0101+" 的档案图片")
                $('#viewImgModal').modal({backdrop: 'static', keyboard: false});
            },
            error: function () {
                showTip("提示", "出错了请联系管理员", 1500);
            }
        });
    }
    function editCysq(id){
        $.ajax({
            url: "${path}/zzb/dzda/cysq/ajax/edit?id="+id,
            type: "get",
            data: {},
            headers: {
                OWASP_CSRFTOKEN: "${sessionScope.OWASP_CSRFTOKEN}"
            },
            dataType: "html",
            success: function (html) {
                $('#addDiv').html(html);
                $('#addModal').modal({
                    keyboard: true
                });
            },
            error: function () {
                showTip("提示", "出错了请联系管理员", 1500);
            }
        });


    }

    function pagehref (pageNum ,pageSize){
        $("#pageNum").val(pageNum);
        $("#pageSize").val(pageSize);
        document.searchForm.submit();
    }



    function deleteYdsq(id,auditingState){
        if(auditingState==0){//彻底删除
            actionByConfirm1('',"${path}/zzb/dzda/cysq/deleteSq/"+id+"?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}",null,function(json){
                if(json.code == 1){
                    showTip("提示","删除成功");
                    window.location.href ="${path }/zzb/dzda/cysq/list?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}";
                }else{
                    showTip("提示", json.message, 2000);
                }
            },"删除")
        }else{
            actionByConfirm1('',"${path}/zzb/dzda/cysq/delete/"+id+"?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}",null,function(json){
                if(json.code == 1){
                    showTip("提示","操作成功");
                    setTimeout(function(){
                        window.location.href ="${path }/zzb/dzda/cysq/list?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}";
                    },1500);

                }else{
                    showTip("提示", json.message, 2000);
                }
            },"删除")
        }

    }

    var add = function(){
        $.ajax({
            url:"${path}/zzb/dzda/cysq/ajax/add",
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

    var view = function(){
        var divHeight = $(window).height()-100;
        $('#jgModal').attr("data-height",divHeight);
        $.ajax({
            url:"${path}/zzb/app/console/daDemo/ajax/viewImgManage",
            type : "post",
            data: {},
            headers:{
                OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
            },
            dataType : "html",
            success : function(html){
                $('#jgAddDiv').html(html);

                $('#jgModal').modal({
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

    function uploadFile(fileName){
        document.getElementById("btn-"+fileName).click();
    }
    function clearData(){
        window.location.href ="${path }/zzb/dzda/cysq/list?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}";
    }

</script>
</body>
</html>
