<%@ page import="com.hisun.util.DateUtil" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jsp/inc/servlet.jsp" %>
<%@include file="/WEB-INF/jsp/inc/taglib.jsp" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>“${gbMcA01Vo.xm}”个人信息</title>

    <link rel="stylesheet" type="text/css" href="${path }/css/style-metro.css">
    <link rel="stylesheet" type="text/css" href="${path }/css/bootstrap-fileupload.css">
    <link rel="stylesheet"type="text/css" href="${path }/css/DT_bootstrap.css" />
    <link href="${path }/css/style.css" rel="stylesheet" type="text/css">
    <style type="text/css">
        .showdabzcss{width:450px;;overflow:hidden;text-overflow:ellipsis; display: inline-block; white-space: nowrap; color: #333; font-size: 13px;
            vertical-align: middle; cursor: pointer; background-color: #f8f8f8; height: 34px; line-height: 34px; padding-left: 10px;  }
        .showdabzcss:hover{  color:#009ae1;}
    </style>
</head>
<body>
<div class="xwbmain">

    <div class="mainone">

        <div class="mainoneleft">
            <img class="imgtp" width="180" height="200" src="${path}/zzb/app/console/gbmc/a01/${gbMcA01Vo.id}/photo?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}" />
        </div>
        <div class="clearfix fr">
            <a class="btn red" href="javascript:del('${gbMcA01Vo.xm }')"><i class=" icon-remove-sign"></i>删除</a>
            <a class="btn" href="${path }/zzb/app/console/gbmc/a01/list?mcid=${mcid}&mcb01id=${mcb01id}"><i class="icon-undo"></i>返回</a>
        </div>
        <div class="mainoneright">
            <div class="Fullname">${gbMcA01Vo.xm}</div>
            <div class="gerenintrodu">${gbMcA01Vo.csny}生，${gbMcA01Vo.jg}人，${gbMcA01Vo.cjgzsj}参加工作，${gbMcA01Vo.rdsj}加入中国共产党。</div>
            <ul class="ulonleftjx">
                <li><span>全日制学历学位及专业：</span>${gbMcA01Vo.qrzxlxwjzy}</li>
                <li><span>&nbsp;&nbsp;&nbsp;在职学历学位及专业：</span>${gbMcA01Vo.zzxlxwjzy}</li>
            </ul>
        </div>
    </div>
    <div class="maintwo">
        <h1 class="tith1">工作经历</h1>
        <c:forEach items="${gbMcA01Vo.gzjlVos}" var="vo">
            <p><c:out value="${vo.jlsm}"></c:out></p>
        </c:forEach>
    </div>
    <div class="mainthree">
        <h1 class="tith1" style="margin-bottom:30px;">其他材料</h1>

        <form class="form-horizontal" id="importForm" enctype="multipart/form-data">
            <div class="control-group">
                <label class="control-label">干部任免审批表</label>
                <div class="controls">
                    <div class="fileupload fileupload-new" data-provides="fileupload">
                       <span class="btn btn-file">
                        <span class="fileupload-new">点击上传</span>
                        <input type="file" class="default" name="gbrmspbFile" id="btn-gbrmspb"/>
                        </span>
                        <div class="btn-group" id="gbrmspbDownDiv"
                             <c:if test="${!isHavagbrmspbFile }">style="visibility:hidden"</c:if>>
                            <a class="btn blue" herf="javascript:void(0)" onclick="gbrmspbDown()"><i
                                    class="icon-circle-arrow-down"></i>下载文件</a>
                        </div>
                        <%--<a herf="javascript:void(0)" onclick="gbrmspbDelete()" class="close fileupload-exists" data-dismiss="fileupload" style="float: none"></a>--%>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>

<script type="text/javascript" src="${path }/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="${path }/js/common/est-validate-init.js"></script>
<script type="text/javascript" src="${path }/js/common/validate-message.js"></script>
<script type="text/javascript" src="${path }/js/common/30CloudAjax.js"></script>
<script type="text/javascript" src="${path }/js/common/DataValidate.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap-datepicker.zh-CN.js"></script>
<script type="text/javascript" src="${path }/js/common/loading.js"></script>
<script src="${path }/js/bootstrap-fileupload.js"></script>
<script type="text/javascript">




    jQuery(document).ready(function () {
        App.init();
        //干部详细信息附件
        $("#btn-gbrmspb").bind("change", function (evt) {
            if ($(this).val()) {
                gbrmspbSubmit();
            }
            $(this).val('');
        });


    });

    var myLoading = new MyLoading("${path}", {zindex: 20000});
    function gbrmspbSubmit() {
        var fileInput = document.getElementById("btn-gbrmspb");
        if (fileInput.files.length > 0) {
            var name = fileInput.files[0].name
            var arr = name.split(".");
            if (arr.length < 2 || !(arr[arr.length - 1] == "doc" || arr[arr.length - 1] == "docx" || arr[arr.length - 1] == "DOC" || arr[arr.length - 1] == "DOCX")) {
                showTip("提示", "请上传word文件", 2000);
                return;
            }
        } else {
            showTip("提示", "请选择文件上传", 2000);
            return;
        }
        $("#importForm").ajaxSubmit({
            url: "${path }/zzb/app/console/GbMca01/gbrmspb/ajax/uploadFile?gbMcA01Id=${gbMcA01Vo.id}",
            type: "post",
            headers: {
                OWASP_CSRFTOKEN: "${sessionScope.OWASP_CSRFTOKEN}"
            },
            beforeSend: function (XHR) {
                myLoading.show();
            },
            success: function (json) {
                if (json.code == 1) {
                    //showTip("提示","操作成功",2000);

                    window.document.getElementById("gbrmspbDownDiv").style.visibility = "visible";
                    window.location.href="${path }/zzb/app/console/gbmc/a01/view?id=${gbMcA01Vo.id}";
                } else if (json.code == -1) {
                    showTip("提示", json.message, 2000);
                } else {
                    showTip("提示", "出错了,请检查网络!", 2000);
                }
            },
            error: function (arg1, arg2, arg3) {
                showTip("提示", "出错了,请检查网络!", 2000);
            },
            complete: function (XHR, TS) {
                myLoading.hide();
            }
        });
    }

    function gbrmspbDown() {
        window.open("${path }/zzb/app/console/GbMca01/gbrmspb/ajax/down?gbMcA01Id=${gbMcA01Vo.id}");
    }


     function del(itemName){
        actionByConfirm1(itemName, "${path}/zzb/app/console/gbmc/a01/delete/${gbMcA01Vo.id}",{} ,function(data,status){
            if (data.success == true) {
                showTip("提示","删除成功", 1000);
                setTimeout(function(){window.location.href = "${path}/zzb/app/console/gbmc/a01/list?mcid=${mcid}&mcb01id=${mcb01id}"},1000);
            }else{
                showTip("提示", data.message, 2000);
            }
        });
    }
</script>
</body>
</html>
