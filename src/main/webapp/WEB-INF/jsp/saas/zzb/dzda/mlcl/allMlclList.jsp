<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
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

    <!-- END PAGE LEVEL STYLES -->
    <title></title>
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
                <button type="button" class="btn btn-default" style="float: right;font-weight: bold;" data-dismiss="modal"><i class='icon-remove-sign'></i> 关闭</button>

                <button type="button" id="daYinDa" class="btn green" style="float: right;margin-right: 5px" onclick="daYinDa();" disabled>打印</button>
                <button type="button" id="xiaZaiDa" class="btn green" style="float: right;margin-right: 5px" onclick="xiaZaiDa();" disabled>下载</button>

                <button type="button" id="button4" class="btn green" style="float: right;margin-right: 5px" onclick="changeImage('end');" disabled>最后一页</button>
                <button type="button" id="button3" class="btn green" style="float: right;margin-right: 5px" onclick="changeImage('down');" disabled>下一页</button>
                <button type="button" id="button2" class="btn green" style="float: right;margin-right: 5px" onclick="changeImage('up');" disabled>上一页</button>
                <button type="button" id="button1" class="btn green" style="float: right;margin-right: 5px" onclick="changeImage('one');" disabled>第一页</button>
                <button type="button" id="daduibiBtn" class="btn green" style="float: right;margin-right: 5px" onclick="daDuibi();" disabled>对比</button>
                <div class="btn-group" style="padding-bottom: 0px;float: right;right: 5px;margin-left: 5px">
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

                <%--<button data-dismiss="modal" class="close" type="button" onclick="hiddenViewImgModal()"></button>--%>
                <h3 class="modal-title" id="title">
                    “${a0101}”档案图片
                </h3>

            </div>
            <div class="modal-body" id="viewImgDiv" style="background-color: #f1f3f6;margin-top: 0px;padding-top: 0px;padding-bottom: 0px">
            </div>
        </div>
    </div>
</div>
<div id="uploadImgModal" class="modal container hide fade" tabindex="-1" data-width="700">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close" type="button"></button>
                <h3 class="modal-title">
                    上传图片
                </h3>
            </div>
            <div class="modal-body" id="uploadImgDiv">
            </div>
        </div>
    </div>
</div>
<div id="setRowCountModal" class="modal container hide fade" tabindex="-1" data-width="500">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close" type="button"></button>
                <h3 class="modal-title">
                    设置目录空余行数
                </h3>
            </div>
            <div class="modal-body" id="setRowCountDiv">
            </div>
        </div>
    </div>
</div>

<div class="container-fluid">
    <div class="row-fluid">
        <div class="span12 responsive">
            <%-- 表格开始 --%>
            <form class="" id="importForm" enctype="multipart/form-data">
                <input type="hidden" name="queryId" value="${queryId}"/>
                <input type="hidden" name="a38Id" id="a38Id" value="${a38Id}"/>
                <input type="hidden" id="eCatalogTypeTreeCode" name="eCatalogTypeTreeCode" value="${eCatalogTypeTreeCode}"/>
                <input type="hidden" id="eCatalogTypeTreeName" name="eCatalogTypeTreeName" value="${eCatalogTypeTreeName}"/>
                <input type="hidden" id="eCatalogTypeTreeParentId" name="eCatalogTypeTreeParentId"
                       value="${eCatalogTypeTreeParentId}"/>
                <div class="portlet-title">
                    <div class="caption">${eCatalogTypeTreeName} </div>
                    <div class="clearfix fr" id="dacx_mlcl_id">
                        <div class="btn-group" >
                            <a class="btn green dropdown-toggle" data-toggle="dropdown" href="#">
                                图片处理 <i class="icon-angle-down"></i>
                            </a>
                            <ul class="dropdown-menu">
                                <li>
                                    <a href="javascript:jztpButtonInit()">加载图片(初始化)</a>
                                </li>
                                <li>
                                    <a href="javascript:jztpButton()">加载图片</a>
                                </li>

                                <li>
                                    <a href="javascript:viewImageMain('','','true')" >图片调整</a>
                                </li>
                                <li>
                                    <a href="javascript:xztpButton()">卸载图片</a>
                                </li>
                                <li>
                                    <a href="javascript:downloadImgButton()">下载图片</a>
                                </li>
                            </ul>
                        </div>
                        <div class="btn-group">
                            <a class="btn green dropdown-toggle" data-toggle="dropdown" href="#">
                                导入目录 <i class="icon-angle-down"></i>
                            </a>
                            <ul class="dropdown-menu">
                                <li>
                                    <a onclick="downloadMB()">下载目录导入模板</a>
                                </li>
                                <li>
                                    <a onclick="uploadFile()">导入目录</a>
                                </li>
                                <li>
                                    <a onclick="setRowCount()">设置目录空余行数</a>
                                </li>

                            </ul>
                        </div>

                        <a class="controllerClass btn green file_but" href="javascript:download()">
                            <i class="icon-circle-arrow-down"></i>导出目录
                        </a>
                        <input type="file" style="display: none" name="mlxxFile" id="mlxxFile" accept = '.csv,
             application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel'/>
                    </div>

                </div>
            </form>
            <div class="portlet-body">
                <table class="table table-striped table-bordered table-hover dataTable table-set">
                    <thead>
                    <tr>
                        <th width="180">材料类别</th>
                        <th>材料名称</th>
                        <th width="60">制成时间</th>
                        <th width="30">材料<br>页数</th>
                        <th width="40">图片数</th>
                        <th width="30">加载<br>图片</th>
                        <th width="40">浏览</th>
                        <%--<th width="30">扫描<br>排序</th>--%>
                        <th width="130">操作</th>
                    </thead>
                    <tbody>
                    <c:forEach items="${pager.datas}" var="vo">
                        <tr style="text-overflow:ellipsis;">
                            <td>${vo.e01Z101A} </td>
                            <td><a href="javascript:edit('${vo.id}')" class="">${vo.e01Z111}</a></td>
                            <td>${vo.e01Z117} </td>
                            <td style="text-align: center">${vo.e01Z114}</td>
                            <td style="text-align: center">${vo.yjztps}</td>
                            <td style="text-align: center"> <c:if test="${isDacx=='1'}">加载</c:if>
                                <c:if test="${isDacx !='1'}"><a href="javascript:jztp('${vo.id}','${vo.e01Z111}')" class="">加载</a></c:if></td>
                            <td style="text-align: center"><a href="javascript:viewImageMain('${vo.id}','${vo.e01Z101B}')" class="">浏览</a></td>
                            <%--<td style="text-align: center">${vo.e01Z107}</td>--%>
                            <td>
                                <a href="javascript:edit('${vo.id}')" class=""><c:if test="${isDacx=='1'}">查看</c:if><c:if test="${isDacx!='1'}">修改</c:if></a>
                                <c:if test="${isDacx!='1'}">
                                |<a href="javascript:viewImageMain('${vo.id}','${vo.e01Z101B}','true')" class="">图片调整</a>|
                                <a href="javascript:del('${vo.id}','${vo.e01Z111}')" voname="${vo.e01Z111}"
                                   class="">删除</a>
                                </c:if>
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
        <%-- 表格结束 --%>
    </div>
</div>

<div id="jztpModal" class="modal container hide fade" tabindex="-1" data-width="520">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close"  type="button"></button>
                <h3 class="modal-title" id="jztpPageTitle" >
                    加载图片
                </h3>
            </div>
            <div class="modal-body" id="jztpPage">
            </div>
        </div>
    </div>
</div>

<div id="jztpE01z1Modal" class="modal container hide fade" tabindex="-1" data-width="520">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close"  type="button"></button>
                <h3 class="modal-title" id="e01z1Title" >
                    加载图片
                </h3>
            </div>
            <div class="modal-body" id="jztpE01z1Page">
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(function () {
        var isDacx = $("#isDacx").val();
        if(isDacx=='1'){
            $("#dacx_mlcl_id").hide();
        }
    })
    function unloadFile() {
        document.getElementById("btn-unloadFile").click();
    }
    function add() {
        var a38Id = $("#a38Id").val();
        var eCatalogTypeTreeCode = $("#eCatalogTypeTreeCode").val();
        var eCatalogTypeTreeName = $("#eCatalogTypeTreeName").val();
        var eCatalogTypeTreeParentId = $("#eCatalogTypeTreeParentId").val();
        $.ajax({
            async: false,
            type: "POST",
            url: "${path}/zzb/dzda/e01z1/ajax/addMlcl",
            dataType: "html",
            headers: {
                "OWASP_CSRFTOKEN": '${sessionScope.OWASP_CSRFTOKEN}'
            },
            data: {
                "a38Id": a38Id,
                "eCatalogTypeTreeCode": eCatalogTypeTreeCode,
                "eCatalogTypeTreeName": eCatalogTypeTreeName,
                "eCatalogTypeTreeParentId": eCatalogTypeTreeParentId
            },
            success: function (html) {
                $("#rightList").html(html);
                $("#treeId").val();
            },
            error: function () {
                myLoading.hide();
                showTip("提示", "出错了,请检查网络!", 2000);
            }
        });
    }
    var uploadImg = function () {
        $.ajax({
            url: "${path}/zzb/app/console/daDemo/ajax/uploadImg",
            type: "post",
            data: {},
            headers: {
                OWASP_CSRFTOKEN: "${sessionScope.OWASP_CSRFTOKEN}"
            },
            dataType: "html",
            success: function (html) {
                $('#uploadImgDiv').html(html);

                $('#uploadImgModal').modal({
                    keyboard: true
                });
            },
            error: function () {
                showTip("提示", "出错了请联系管理员", 1500);
            }
        });
    }
    function edit(id) {
        var a38Id = $("#a38Id").val();
        var eCatalogTypeTreeCode = $("#eCatalogTypeTreeCode").val();
        var eCatalogTypeTreeName = $("#eCatalogTypeTreeName").val();
        var eCatalogTypeTreeParentId = $("#eCatalogTypeTreeParentId").val();
        $.ajax({
            async: false,
            type: "POST",
            url: "${path}/zzb/dzda/e01z1/ajax/editMlcl",
            dataType: "html",
            headers: {
                "OWASP_CSRFTOKEN": '${sessionScope.OWASP_CSRFTOKEN}'
            },
            data: {
                "id": id,
                "a38Id": a38Id,
                "eCatalogTypeTreeCode": eCatalogTypeTreeCode,
                "eCatalogTypeTreeName": eCatalogTypeTreeName,
                "eCatalogTypeTreeParentId": eCatalogTypeTreeParentId
            },
            success: function (html) {
                $("#rightList").html(html);
            },
            error: function () {
                myLoading.hide();
                showTip("提示", "出错了,请检查网络!", 2000);
            }
        });
    }
    var viewImageMain = function (e01z1Id,e01Z101B,isManage) {
        var divHeight = $(window).height() -60;
        var divWidth = $(window).width() - 100;
        $('#viewImgModal').attr("data-height", divHeight);
        $('#viewImgModal').attr("data-width", divWidth);
        $.ajax({
            url: "${path}/zzb/dzda/mlcl/images/ajax/viewMain/"+$("#a38Id").val(),
            type: "post",
            data: {
                "a0101":"${a0101}",
                "archiveId":e01Z101B,
                "e01z1Id":e01z1Id,
                "isManage":isManage,
                "isAddLog":"false"
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

    function del(id, voname) {
        var a38Id = $("#a38Id").val();
        var eCatalogTypeTreeCode = $("#eCatalogTypeTreeCode").val();
        var eCatalogTypeTreeName = $("#eCatalogTypeTreeName").val();
        var eCatalogTypeTreeParentId = $("#eCatalogTypeTreeParentId").val();
        actionByConfirm1(voname, "${path}/zzb/dzda/e01z1/delete/" + id+"?a0101=${a0101}&e01Z111="+voname+"&OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}", {}, function (data, status) {
            if (data.success == true) {
                showTip("提示", "成功删除！", 2000);
                $.ajax({
                    url: "${path}/zzb/dzda/e01z1/ajax/mlxxList",
                    type: 'POST',
                    dataType: "html",
                    data: {
                        "eCatalogTypeTreeCode": eCatalogTypeTreeCode,
                        "eCatalogTypeTreeParentId": eCatalogTypeTreeParentId,
                        "eCatalogTypeTreeName": eCatalogTypeTreeName,
                        "a38Id": a38Id
                    },
                    headers: {
                        "OWASP_CSRFTOKEN": "${sessionScope.OWASP_CSRFTOKEN}"
                    },
                    success: function (html) {
                        $("#rightList").html(html);
                    },
                    error: function () {
                        showTip("提示", "删除失败!", 2000);
                    }
                });
            } else {
                showTip("提示", data.msg, 2000);
            }
        });
    }


    function jztpButtonInit(){
        var divHeight = $(window).height()-100;
        var divWidth = 800;
        $('#jztpE01z1Modal').attr("data-width",divWidth);
        $('#jztpE01z1Modal').attr("data-height",divHeight);
        $.ajax({
            url: "${path}/zzb/dzda/mlcl/tpcl/ajax/init/list/${a38Id}",
            type: "post",
            data: {},
            headers: {
                OWASP_CSRFTOKEN: "${sessionScope.OWASP_CSRFTOKEN}"
            },
            dataType: "html",
            success: function (html) {
                $('#jztpPage').html("");
                $('#e01z1Title').html("“${jztpTitle}”加载图片");
                $('#jztpE01z1Page').html(html);
                $('#jztpE01z1Modal').modal({
                    keyboard: true
                });
            },
            error: function () {
                showTip("提示", "出错了请联系管理员", 1500);
            }
        });
    }

    function jztpButton(){
        var divHeight = $(window).height()-100;
        var divWidth = $(window).width()-20;
        $('#jztpModal').css("width",divWidth+"px");
//        $('#jztpModal').css("height", divHeight+"px");
        $('#jztpModal').attr("data-width",divWidth);
        $('#jztpModal').attr("data-height",divHeight);
        $.ajax({
            url: "${path}/zzb/dzda/mlcl/tpcl/ajax/index/${a38Id}",
            type: "post",
            data: {},
            headers: {
                OWASP_CSRFTOKEN: "${sessionScope.OWASP_CSRFTOKEN}"
            },
            dataType: "html",
            success: function (html) {
                $('#jztpE01z1Page').html("");
                $('#jztpPageTitle').html("“${jztpTitle}”加载图片");
                $('#jztpPage').html(html);
                $('#jztpModal').modal({
                    keyboard: true
                });
            },
            error: function () {
                showTip("提示", "出错了请联系管理员", 1500);
            }
        });
    }


    function xztpButton(){
        actionByConfirm1('',"${path}/zzb/dzda/mlcl/tpcl/delete/${a38Id}?a0101=${a0101}&OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}",null,function(json){
            if(json.success){
                showTip("提示", json.message, 1500);
                mlLoad();
            }else{
                showTip("提示", "出错了请联系管理员", 1500);
            }
        },"卸载已加载的图片")
        <%--$.ajax({--%>
            <%--url: "${path}/zzb/dzda/mlcl/tpcl/delete/${a38Id}",--%>
            <%--type: "post",--%>
            <%--data: {},--%>
            <%--headers: {--%>
                <%--OWASP_CSRFTOKEN: "${sessionScope.OWASP_CSRFTOKEN}"--%>
            <%--},--%>
            <%--dataType: "json",--%>
            <%--success: function (json) {--%>
                <%--if(json.success){--%>
                    <%--mlLoad();--%>
                    <%--showTip("提示", json.message, 1500);--%>
                <%--}--%>
            <%--},--%>
            <%--error: function () {--%>
                <%--showTip("提示", "出错了请联系管理员", 1500);--%>
            <%--}--%>
        <%--});--%>
    }

    function downloadImgButton(){
        window.open("${path}/zzb/dzda/mlcl/tpcl/download/${a38Id}?a0101=${a0101}&OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}");
    }

    function jztp(id,e01Z111){
        var divHeight = $(window).height()-300;
        var divWidth = 800;
        $('#jztpE01z1Modal').attr("data-width",divWidth);
        $('#jztpE01z1Modal').attr("data-height",divHeight);
        $.ajax({
            url: "${path}/zzb/dzda/mlcl/tpcl/ajax/list/e01z1/"+id,
            type: "post",
            data: {},
            headers: {
                OWASP_CSRFTOKEN: "${sessionScope.OWASP_CSRFTOKEN}"
            },
            dataType: "html",
            success: function (html) {
                $('#e01z1Title').html("加载“"+e01Z111+"”图片");
                $('#jztpPage').html("");
                $('#jztpE01z1Page').html(html);
                $('#jztpE01z1Modal').modal({
                    keyboard: true
                });
            },
            error: function () {
                showTip("提示", "出错了请联系管理员", 1500);
            }
        });
    }

    function download(){
        window.open("${path}/zzb/dzda/e01z1/download/${a38Id}?a0101=${a0101}&OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}");

    };
    function downloadMB(){
        window.open("${path}/zzb/dzda/e01z1/downloadMB/${a38Id}?a0101=${a0101}&OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}");
    }
    function uploadFile(){
        document.getElementById("mlxxFile").click();
    }

    $("#mlxxFile").on("change", function (evt) {
        var uploadFile = document.getElementById("mlxxFile");
        if (uploadFile.files.length > 0) {
            var name = uploadFile.files[0].name
            var arr = name.split(".");
            if (arr.length < 2 || !(arr[arr.length - 1] == "csv" || arr[arr.length - 1] == "xlsx" || arr[arr.length - 1] == "xls")) {
                showTip("提示", "请上传Excel文件", 2000);
                return;
            }
        }
        var a38Id = $("#a38Id").val();
        var e01Z231 = $("#e01Z231").val();
        myLoading.show();
        $("#importForm").ajaxSubmit({
            type:"POST",
            url:"${path}/zzb/dzda/e01z1/uploadFile",
            dataType : "json",
            enctype : "multipart/form-data",
            headers:{
                "OWASP_CSRFTOKEN":'${sessionScope.OWASP_CSRFTOKEN}'
            },
            success:function(data){
                if(data.isWrong){
                    $.ajax({
                        url:"${path}/zzb/dzda/e01z1/ajax/cwjl",
                        type : "post",
                        data: {},
                        headers:{
                            OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
                        },
                        dataType : "html",
                        success : function(html){
                            myLoading.hide();
                            $('#wrongDiv').html(html);
                            $('#wrongModal').modal({backdrop: 'static', keyboard: false});
                        },
                        error : function(){
                            myLoading.show();
                            showTip("提示","出错了请联系管理员", 1500);
                        }
                    });
                }else {
                    showTip("提示","上传成功!",2000);
                    $.ajax({
                        async:false,
                        type:"POST",
                        url:"${path }/zzb/dzda/e01z1/ajax/mlxxList",
                        dataType : "html",
                        headers:{
                            "OWASP_CSRFTOKEN":'${sessionScope.OWASP_CSRFTOKEN}'
                        },
                        data:{
                            "a38Id":a38Id
                        },
                        success:function(html){
                            myLoading.hide();
                            $("#rightList").html(html);
                        },
                        error : function(){
                            myLoading.hide();
                            showTip("提示","出错了,请检查网络!",2000);
                        }
                    });
                }
            },
            error : function(){
                showTip("提示","上传失败!",2000);
            }
        });
    });

    function setRowCount() {
        $.ajax({
            async: false,
            type: "POST",
            url: "${path}/zzb/dzda/e01z1/ajax/setRowCount",
            dataType: "html",
            headers: {
                "OWASP_CSRFTOKEN": '${sessionScope.OWASP_CSRFTOKEN}'
            },
            data: {
            },
            success: function (html) {
                $('#setRowCountDiv').html(html);
                $('#setRowCountModal').modal({backdrop: 'static', keyboard: false});
            },
            error: function () {
                myLoading.hide();
                showTip("提示", "出错了,请检查网络!", 2000);
            }
        });
    }

</script>
</body>
</html>
