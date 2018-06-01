<%@ taglib prefix="Select" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/inc/servlet.jsp" %>
<%@include file="/WEB-INF/jsp/inc/taglib.jsp" %>
<%--
  ~ Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
  ~ http://www.hn-hisun.com
  ~ 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
  --%>
<title></title>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<div class="container-fluid">
    <style type="text/css">
        form {
            margin: 0 0 0px;
        }
    </style>
    <div class="row-fluid">
        <div>
            <%-- BEGIN SAMPLE FORM PORTLET 表单主体--%>

            <div class="portlet box grey"  id="viewDiv" style="overflow: auto;">
                <%--<div class="portlet-title">
                    &lt;%&ndash;<div class="caption">
                        <i class="icon-reorder"></i>
                        <span class="hidden-480">选择档案</span>
                    </div>&ndash;%&gt;

                </div>--%>
                <form action="$${path}/zzb/dzda/dazd/ajax/xzgb?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}" class="form-horizontal" id="form1" name="form1" method="post" style="margin-bottom: 0px">
                <div style="margin-left: 10px;margin-bottom: 10px">
                    <input type="hidden" id="eCatalogTypeId" name="eCatalogTypeId" value=""/>
                    <input type="hidden" id="a38Ids" name="a38Ids" value=""/>
                    <div style=" float:left;margin-top:4px">&nbsp;姓名:</div>
                <div style=" float:left;">
                    <input type="text" class="m-wrap" name="a0101Query" id="a0101Query" value="${a0101Query}"
                           style="width:80px;"/>
                </div>
                <div style=" float:left;margin-top:4px">&nbsp;干部状态:</div>
                <div style="float:left;width: 160px;">
                    <Tree:tree id="gbztCodeQuery" valueName="gbztContentQuery" selectClass="span12 m-wrap"
                               height="30px" treeUrl="${path}/api/dictionary/tree?typeCode=SAN_GBZT"
                               token="${sessionScope.OWASP_CSRFTOKEN}"
                               submitType="get" dataType="json" isSearch="false"
                               checkedByTitle="true" isSelectTree="true" defaultkeys="${gbztCodeQuery}"
                               defaultvalues="${gbztContentQuery}"/>
                </div>
                <div style=" float:left;margin-top:4px">&nbsp;档案状态:</div>
                <div style="float:left;width: 160px;">
                    <Tree:tree id="daztCodeQuery" valueName="daztContentQuery" selectClass="span12 m-wrap"
                               height="30px" treeUrl="${path}/api/dictionary/tree?typeCode=SAN_DAZT"
                               token="${sessionScope.OWASP_CSRFTOKEN}"
                               submitType="get" dataType="json" isSearch="false"
                               checkedByTitle="true" isSelectTree="true" defaultkeys="${daztCodeQuery}"
                               defaultvalues="${daztContentQuery}"/>

                </div>
                <div style="float:left">
                    &nbsp;&nbsp;
                    <button type="button" class="btn Short_but" onclick="searchList()">查询</button>
                    <button type="button" class="btn Short_but" onclick="clearData()">清空</button>
                </div>
             </div>
                            <div class="portlet-body" id="a38Table"style=" padding-top: 5px;">
                                <input type="hidden" name="pageNum" value="${pager.pageNum }" id="pageNum">
                                <input type="hidden" name="pageSize" value="${pager.pageSize }" id="pageSize">
                                <table class="table table-striped table-bordered table-hover dataTable table-set">
                                    <thead>
                                    <tr>
                                        <th style="width: 25px;">
                                           <input type="checkbox" id="allCheck" onchange="allCheckChange()">
                                        </th>
                                        <th width=70>姓名</th>
                                        <th width=40>性别</th>
                                        <th width=70>出生年月</th>
                                        <th>单位职务</th>
                                        <th width=70>干部状态</th>
                                        <th width=70>现职级时间</th>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${pager.datas}" var="vo">
                                        <tr style="text-overflow:ellipsis;">
                                            <td><input type="checkbox" name="a38Check" onchange="checkChange(this)" value="${vo.id },${vo.a0101}"></td>
                                            <td>${vo.a0101}</td>
                                            <td>${vo.a0104Content}</td>
                                            <td>${vo.a0107} </td>
                                            <td>${vo.a0157}</td>
                                            <td>${vo.gbztContent}</td>
                                            <td st>${vo.xzjsj}</td>
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
                </form>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="${path }/js/common/DataValidate.js"></script>
<script type="text/javascript">
    jQuery(document).ready(function() {
        App.init();
        changeXzgbDivHeight();
        $(window).resize(function(){
            changeXzgbDivHeight();
        })
    });
    function changeXzgbDivHeight(){
        var listHeight = $(window).height()-85;
        $("#viewDiv").css('height',listHeight);
//			$("#viewList").css('height',listHeight);
//			$(".main_left").height(mainHeight);

    }

    function clearData(){
        $("#a0101Query").val("");
        $("#gbztCodeQuery").val("");
        $("#gbztContentQuery").val("");
        $("#daztCodeQuery").val("");
        $("#daztContentQuery").val("");
        $.ajax({
            url: "${path}/zzb/dzda/dazd/ajax/getA38List",
            type: "post",
            data: {},
            dataType: "html",
            headers: {
                OWASP_CSRFTOKEN: "${sessionScope.OWASP_CSRFTOKEN}"
            },
            success: function (html) {
                $('#a38Table').html(html);
                App.init();
            },
            error: function (arg1, arg2, arg3) {
                showTip("提示", "查询失败");
            }
        });
    }
    function searchList() {
        var a0101Query = $("#a0101Query").val();
        var gbztCodeQuery = $("#gbztCodeQuery").val();
        var gbztContentQuery = $("#gbztContentQuery").val();
        var daztCodeQuery = $("#daztCodeQuery").val();
        var daztContentQuery = $("#daztContentQuery").val();
        $.ajax({
            url: "${path}/zzb/dzda/dazd/ajax/getA38List",
            type: "post",
            data: {
                "a0101Query":a0101Query,
                "gbztCodeQuery":gbztCodeQuery,
                "gbztContentQuery":gbztContentQuery,
                "daztCodeQuery":daztCodeQuery,
                "daztContentQuery":daztContentQuery,
            },
            dataType: "html",
            headers: {
                OWASP_CSRFTOKEN: "${sessionScope.OWASP_CSRFTOKEN}"
            },
            success: function (html) {
                $('#a38Table').html(html);
                App.init();
            },
            error: function (arg1, arg2, arg3) {
                showTip("提示", "查询失败");
            }
        });


        /*
        $.ajax({
            url: "${path}/zzb/dzda/dazd/ajax/getA38List",// 请求的action路径
            type: 'POST',
            dataType : "html",
            headers: {
                "OWASP_CSRFTOKEN":"${sessionScope.OWASP_CSRFTOKEN}"
            },
            data:{
                "pageNum":pageNum,
                "pageSize":pageSize
            },
            success:function(html){
                $('#a38Table').html(html);
            },
            error: function () {// 请求失败处理函数
                alert('请求失败');
            }
        });*/
    }
    var ids = [];

    function saveXzda(){

        if(ids.length>0){
            var idString = "";
            var nameString = "";
            for(var i=0;i<ids.length;i++){
                var idAndName =ids[i].split(",");
                var id = idAndName[0];
                var name = idAndName[1];
                if(id !=""){
                    if(idString == ""){
                        idString =id;
                        nameString = name;
                        continue;
                    }
                    idString=idString+","+id;
                    nameString = nameString+","+name;
                }
            }
            $("#name").val(nameString);
            $("#nameContent").val(idString);
        }



//        var pageNum = $("#pageNum").val();
//        var checks = document.getElementsByName("a38Check");
//        var idString = $("#nameContent").val();
//        var nameString = $("#name").val();
//        for(var i=0;i<checks.length;i++){
//            var value = checks[i].value;
//            var idAndName =value.split(",");
//            var id = idAndName[0];
//            var name = idAndName[1]
//            // var index = indexOf(ids,id);
//            if(id !="" && checks[i].checked){
//                if(idString == ""){
//                    idString =id;
//                    nameString = name;
//                    continue;
//                }
//                idString=idString+","+id;
//                nameString = nameString+","+name;
//            }
//        }
//        $("#name").val(nameString);
//        $("#nameContent").val(idString);
        if(idString=="" || idString==undefined){
            showTip("提示","请选择档案",1500);
            return;
        }
        $("#xzdaModal").modal("hide");
    }
    function pagehref (pageNum ,pageSize){
//        var checks = document.getElementsByName("a38Check");
//        var idString = $("#nameContent").val();
//        var nameString = $("#name").val();
//        for(var i=0;i<checks.length;i++){
//            var value = checks[i].value;
//            var idAndName =value.split(",");
//            var id = idAndName[0];
//            var name = idAndName[1]
//            // var index = indexOf(ids,id);
//            if(id !="" && checks[i].checked){
//                if(idString == ""){
//                    idString =id;
//                    nameString = name;
//                    continue;
//                }
//                idString=idString+","+id;
//                nameString = nameString+","+name;
//            }
//        }
//        $("#name").val(nameString);
//        $("#nameContent").val(idString);
        $.ajax({
            url: "${path}/zzb/dzda/dazd/ajax/getA38List",// 请求的action路径
            cache:false,
            type: 'POST',
            dataType : "html",
            headers: {
                "OWASP_CSRFTOKEN":"${sessionScope.OWASP_CSRFTOKEN}"
            },
            data:{
                "pageNum":pageNum,
                "pageSize":pageSize
            },
            success:function(html){
                $('#a38Table').html(html);
                App.init();
                var checks = document.getElementsByName("a38Check");
                if(checks){
                    var isAll = true;
                    for(var i=0;i<checks.length;i++){
                        var id = checks[i].value;
                        var index = indexOf(ids,id);
                        if(index>-1){
                            checks[i].checked=true;
                            checks[i].parentNode.className = "checked";
                        }else {
                            isAll = false;
                        }
                    }
                    if(isAll){
                        var allCheck = document.getElementById("allCheck");
                        allCheck.checked=true;
                        allCheck.parentNode.className = "checked";
                    }
                }
            },
            error: function () {// 请求失败处理函数
                alert('请求失败');
            }
        });
    }


    function indexOf(arr, str){
        if(arr && arr.indexOf){
            return arr.indexOf(str);
        }
        var len = arr.length;
        for(var i = 0; i < len; i++){
            // 定位该元素位置
            if(arr[i] == str){
                return i;
            }
        }
        // 数组中不存在该元素
        return -1;
    }

    function checkChange(obj){
        var check=obj.checked;
        var id = obj.value;
        var index = indexOf(ids,id);
        var idsLength = ids.length;
        if(check&&index==-1){
            ids[idsLength] = id;
        }else if(!check&&index>-1){
            ids.splice(index,1);
        }

    }

    function allCheckChange(){
        var allCheck = document.getElementById("allCheck");
        var checks = document.getElementsByName("a38Check");
        if(checks){
            for(var i=0;i<checks.length;i++) {
                var idsLength = ids.length;
                var check = checks[i].checked;
                var id = checks[i].value;
                var index = indexOf(ids,id);
                checks[i].checked = allCheck.checked;
                if (allCheck.checked == true) {
                    if(!check&&index==-1){
                        ids[idsLength] = id;
                        checks[i].parentNode.className = "checked";
                    }
                }else{
                    if(index>-1){
                        ids.splice(index,1);
                        checks[i].parentNode.className = "";
                    }
                }
            }
        }
    }

</script>