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

    <link href="${path }/css/style.css" rel="stylesheet" type="text/css">
    <!-- END PAGE LEVEL STYLES -->
    <title>可查阅授权档案</title>
    <style type="text/css">
        form {
            margin: 0 0 0px;
        }
    </style>
</head>
<body>
<div id="viewImgModal" class="modal container hide fade" tabindex="-1" data-width="1010" data-height="600">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="btn btn-default" style="float: right;font-weight: bold;" data-dismiss="modal" onclick="hiddenViewImgModalForLiulan()"><i class='icon-remove-sign'></i> 关闭</button>
                <div class="btn-group" style="padding-bottom: 0px;float: right;right: 10px">
                    <a class="btn green dropdown-toggle" data-toggle="dropdown" href="#">
                        显示方式<i class="icon-angle-down"></i>
                    </a>
                    <ul class="dropdown-menu">
                        <li >
                            <a onclick="changeViewType('19')">小图(一行5张图)</a>
                        </li>
                        <li >
                            <a onclick="changeViewType('32')">大图(一行3张图)</a>
                        </li>
                        <%--<li>--%>
                        <%--<a onclick="changeViewType('50')">大图(一行2张图)</a>--%>
                        <%--</li>--%>
                        <li>
                            <a onclick="changeViewType('99')">原始图(一行1张图)</a>
                        </li>
                    </ul>
                </div>
                <h3 class="modal-title" id="title">

                </h3>

            </div>
            <div class="modal-body" id="viewImgDiv" style="background-color: #f1f3f6;margin-top: 0px;padding-top: 0px;padding-bottom: 0px">
            </div>
        </div>
    </div>
</div>
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
                    <div class="caption">阅档申请</div>
                    <div class="clearfix fr">
                        <a id="sample_editable_1_new" class="btn green" href="javascript:add()">
                            阅档申请
                        </a>
                    </div>

                </div>
            </form>
            <div class="clearfix">
                <div class="control-group">
                    <div id="query" style="float: left;">
                        <form action="${path}/zzb/dzda/dak/ajax/sqcydalist" method="POST" id="searchForm" name="searchForm">
                            <%--<input type="hidden" id="b01Id" name="b01Id" value="${b01Id}"/>--%>
                            <input type="hidden" name="OWASP_CSRFTOKEN" value="${sessionScope.OWASP_CSRFTOKEN}"/>
                            <input type="hidden" name="pageNum" value="${pager.pageNum }" id="pageNum">
                            <input type="hidden" name="pageSize" value="${pager.pageSize }" id="pageSize">
                            姓名：<input type="text" class="m-wrap" name="userName" id="userName" value="${userName}" style="width: 80px;" />
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
                        <th width="15%">姓名</th>
                        <th>单位职务</th>
                        <th width="20%">查阅时长</th>
                        <th width="15%">授权时间</th>
                    </thead>
                    <tbody>
                        <c:forEach items="${pager.datas}" var="vo">
                        <tr style="text-overflow:ellipsis;">
                            <TD width="15%"><a href="javascript:viewImageMain('${vo.a38.id}','${vo.a0101}')"><c:out value="${vo.a0101}"></c:out></a></TD>
                            <TD ><c:out value="${vo.sqcydazw}"></c:out></TD>
                            <TD width="20%"><c:out value="${vo.readTime}"></c:out> </TD>
                            <TD width="15%"><c:out value="${vo.accreditDate}"></c:out> </TD>
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

<%-- END PAGE CONTENT--%>
</div>

<script type="text/javascript">
    (function(){
//        App.init();

        $("#btn-browseTemplate").bind("change",function(evt){
            if($(this).val()){
                ajaxSubmit();
            }
            $(this).val('');
        });

    })();

    function pagehref (pageNum ,pageSize){
        var userName = $("#userName").val();
        $("#pageNum").val(pageNum);
        $("#pageSize").val(pageSize);
        $.ajax({
            url : "${path}/zzb/dzda/dak/ajax/sqcydalist",
            type : "post",
            data : {
                "userName":userName,
                "pageNum":pageNum,
                "pageSize":pageSize
            },
            dataType : "html",
            headers:{
                OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
            },
            success : function(html){
                var view = $("#tab_show");
                view.html(html);
            },
            error : function(arg1, arg2, arg3){
                showTip("提示","基本信息加载失败");
            }
        });
//        document.searchForm.submit();
    }

    var viewImageMain = function (a38Id,a0101) {
        var divHeight = $(window).height() -60;
        var divWidth = $(window).width() - 100;
        $("#title").text('"'+a0101+'"'+'档案图片');
        $('#viewImgModal').attr("data-height", divHeight);
        $('#viewImgModal').attr("data-width", divWidth);
        $.ajax({
            url: "${path}/zzb/dzda/mlcl/images/ajax/viewMain/"+a38Id,
            type: "post",
            data: {
                "a0101":a0101,
            },
            headers: {
                OWASP_CSRFTOKEN: "${sessionScope.OWASP_CSRFTOKEN}"
            },
            dataType: "html",
            success: function (html) {
                $('#viewImgDiv').html(html);
                $('#viewImgModal').modal({backdrop: 'static', keyboard: false});
            },
            error: function () {
                showTip("提示", "出错了请联系管理员", 1500);
            }
        });
    }

    function hiddenViewImgModal(){//隐藏图片查看时 删除临时的解密图片
        $('#viewImgModal').modal('hide');
        $('#viewImgDiv').html("");
    }

    var add = function(){
        $.ajax({
            url:"${path}/zzb/dzda/dak/ajax/sqcydaAdd",
            type : "post",
            data: {},
            headers:{
                OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
            },
            dataType : "html",
            success : function(html){
                $('#addDiv').html(html);

                $('#addModal').modal({backdrop: 'static', keyboard: false});
            },
            error : function(){
                showTip("提示","出错了请联系管理员", 1500);
            }
        });
    }

    function searchSubmit(){
        var userName = $("#userName").val();
        $.ajax({
            url : "${path}/zzb/dzda/dak/ajax/sqcydalist",
            type : "post",
            data : {"userName":userName},
            dataType : "html",
            headers:{
                OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
            },
            success : function(html){
                var view = $("#tab_show");
                view.html(html);
            },
            error : function(arg1, arg2, arg3){
                showTip("提示","基本信息加载失败");
            }
        });
        //        document.searchForm.submit();
    }

    function clearData(){
        $("#userName").val('');
    }

</script>
</body>
</html>
