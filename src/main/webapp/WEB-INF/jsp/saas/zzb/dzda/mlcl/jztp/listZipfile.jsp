<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jsp/inc/taglib.jsp" %>
<%--
  ~ Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
  ~ http://www.hn-hisun.com
  ~ 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
  --%>

<div class="container-fluid">
    <div class="row-fluid">
        <div class="portlet-title" style="vertical-align: middle;">
            <div class="caption">本地材料</div>
            <div class="clearfix fr">
                <a id="checkFile" class="btn green" href="#"><i class="icon-plus"></i>检查</a>
                <a id="uploadAndSaveFile" class="btn green" href="#" disabled><i class="icon-plus"></i>保存</a>
            </div>
        </div>
        <div class="clearfix">
            <div class="control-group">
                <div id="query" style="float: left;">
                    <form action="${path}/zzb/dzda/mlcl/jztp/save" id="uploadAndSaveFileForm" style="margin: 0 0 10px;"
                          method="post" enctype="multipart/form-data">
                        <input type="hidden" id="currentNodeId" name="currentNodeId" value=""/>
                        <input type="hidden" id="currentNodeName" name="currentNodeName" value=""/>
                        <input type="hidden" id="currentNodeParentId" name="currentNodeParentId" value=""/>
                        <input type="hidden" id="isPass" name="isPass" value="false"/>
                        <input type="hidden" name="OWASP_CSRFTOKEN" value="${sessionScope.OWASP_CSRFTOKEN}">
                        <input type="file" name="zipfile" id="zipfile">
                    </form>
                </div>
            </div>
        </div>
        <div class="portlet-body">
            <table class="table table-striped table-bordered table-hover dataTable table-set">
                <thead>
                <tr>
                    <th>序号</th>
                    <th>文件名</th>
                    <th>修改日期</th>
                    <th>文件大小</th>
                </tr>
                </thead>
                <tbody id="resultFilelist">
                </tbody>
            </table>
        </div>
    </div>
    <div id="checkResultModal" class="modal container hide fade" tabindex="-1" data-width="800">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button data-dismiss="modal" class="close" type="button"></button>
                    <h3 class="modal-title" id="title">
                        检查结果
                    </h3>
                </div>
                <div class="portlet-body">
                    <table class="table table-striped table-bordered table-hover dataTable table-set">
                        <thead>
                        <tr>
                            <th>序号</th>
                            <th>错误信息</th>
                        </tr>
                        </thead>
                        <tbody id="checkResultList">
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="${path}/js/jszip.min.js"></script>
<script type="text/javascript" src="${path }/js/common/loading.js"></script>
<script type="text/javascript">
    var myLoading = new MyLoading('${path}', {zindex: 11111});
    $("#selectFile").click(function () {
        var currentNodeId = $("#currentNodeId").val();
        var currentNodeName = $("#currentNodeName").val();
        var currentNodeParentId = $("#currentNodeParentId").val();


    });
    Date.prototype.Format = function (fmt) {
        var o = {
            "M+": this.getMonth() + 1, //月份
            "d+": this.getDate(), //日
            "H+": this.getHours(), //小时
            "m+": this.getMinutes(), //分
            "s+": this.getSeconds(), //秒
            "q+": Math.floor((this.getMonth() + 3) / 3), //季度
            "S": this.getMilliseconds() //毫秒
        };
        if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        for (var k in o)
            if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        return fmt;
    }

    var $fileJson = [];
    var $resultFilelist = $("#resultFilelist");
    var $aggregateFilelist = []
    $("#zipfile").on("change", function (evt) {
        myLoading.show();
        $resultFilelist.html("");
        var currentNodeId = $("#currentNodeId").val();

        function handleFile(f) {
            //var dateBefore = new Date();
            JSZip.loadAsync(f)
                    .then(function (zip) {
                        //var dateAfter = new Date();
                        var index = 1;
                        zip.forEach(function (relativePath, zipEntry) {
                            //mac隐藏文件不显示
                            if (zipEntry.dir == false && zipEntry.name.indexOf(".DS_Store") == -1) {
                                var $resultTR = "";
                                $resultTR += "<tr>";
                                $resultTR += "<td>" + index + "</td>";
                                $resultTR += "<td>" + zipEntry.name + "</td>";
                                $resultTR += "<td>" + zipEntry.date.Format('yyyy-MM-dd HH:mm:ss') + "</td>";
                                $resultTR += "<td>" + (zipEntry._data.uncompressedSize / 1048576).toFixed(2) + "M</td>";
                                $resultTR += "</tr>";
                                $resultFilelist.append($resultTR);
                                $fileJson.push({
                                    "fileIndex": index,
                                    "fileName": zipEntry.name,
                                    "fileModifyDate": zipEntry.date.Format('yyyy-MM-dd HH:mm:ss'),
                                    "fileSize": zipEntry._data.uncompressedSize
                                });
                                index++;
                            }
                        });
                        myLoading.hide();
                        //初始化$aggregateFilelist
                        for (var i = 0; i < $fileJson.length; i++) {
                            var dirCode = getDirCode($fileJson[i].fileName);
                            var nameCode = getFileNameCode($fileJson[i].fileName).substring(0, 2);
                            if ($aggregateFilelist.length == 0) {//如果没有,进行初始化
                                $aggregateFilelist.push({"dirCode": dirCode, "nameCode": nameCode, "count": 1});
                            } else {
                                var isAddDirCode = true;
                                for (var j = 0; j < $aggregateFilelist.length; j++) {
                                    if ($aggregateFilelist[j].dirCode == dirCode) {
                                        isAddDirCode = false
                                    }
                                }
                                if (isAddDirCode) {
                                    $aggregateFilelist.push({"dirCode": dirCode, "nameCode": nameCode, "count": 1});
                                }
                            }
                        }

                        for (var i = 0; i < $fileJson.length; i++) {
                            var dirCode = getDirCode($fileJson[i].fileName);
                            var nameCode = getFileNameCode($fileJson[i].fileName).substring(0, 2);
                            var isAddNameCode = true;
                            for (var j = 0; j < $aggregateFilelist.length; j++) {
                                if ($aggregateFilelist[j].nameCode == nameCode) {
                                    isAddNameCode = false
                                }
                            }
                            if (isAddNameCode) {
                                $aggregateFilelist.push({"dirCode": dirCode, "nameCode": nameCode, "count": 1});
                            }
                        }

                        for (var i = 0; i < $aggregateFilelist.length; i++) {
                            var count = 0;
                            for (var j = 0; j < $fileJson.length; j++) {
                                var dirCode = getDirCode($fileJson[j].fileName);
                                var nameCode = getFileNameCode($fileJson[j].fileName).substring(0, 2);
                                if ($aggregateFilelist[i].dirCode == dirCode
                                        && $aggregateFilelist[i].nameCode == nameCode) {
                                    count++;
                                }
                            }
                            $aggregateFilelist[i].count = count;
                        }
                    }, function (e) {
                        showTip("提示", "解压失败!", 1500);
                    });
        }

        var files = evt.target.files;
        for (var i = 0; i < files.length; i++) {
            handleFile(files[i]);
        }

    });

    var getDirCode = function (name) {
        return name.substring(name.indexOf("/") + 1, name.indexOf("."));
    }
    var getFileNameCode = function (name) {
        return name.substring(name.lastIndexOf("/") + 1, name.lastIndexOf("."));
    }

    var $maxFileSize =${maxFileSize};
    var $checkResultList = $("#checkResultList");
    $("#checkFile").click(function () {
        var isPass = true;
        var $checkResultJson = [];
        //如果未上传文件,则提示上传
        if ($fileJson.length == 0) {
            isPass = false;
            showTip("提示", "请先上传材料图片！", 2000);
        } else {
            //检查文件大小
            $fileJson.forEach(function (file) {
                if (file.fileSize > $maxFileSize) {
                    isPass = false
                    $checkResultJson.push({"message": "文件:" + file.fileName + " 大于" + ($maxFileSize / 1048576).toFixed(2) + "M。"});
                }

            });
            //检查每类材料份数
            $.ajax({
                url: "${path}/zzb/dzda/mlcl/jztp/mlcl/${a38Id}",
                type: "get",
                data: {},
                dataType: "json",
                headers: {
                    "OWASP_CSRFTOKEN": "${sessionScope.OWASP_CSRFTOKEN}"
                },
                success: function (json) {
                    if (json.success) {
                        alert(json.mlclAggregateJson);
                    }
                },
                error: function () {

                }
            });

            //展示错误提示信息
            if ($checkResultJson.length > 0) {
                $('#checkResultModal').modal({
                    keyboard: true
                });
                $checkResultList.html("");
                var index = 1;
                $checkResultJson.forEach(function (result) {
                    var $resultTR = "";
                    $resultTR += "<tr>";
                    $resultTR += "<td>" + index + "</td>";
                    $resultTR += "<td>" + result.message + "</td>";
                    $resultTR += "</tr>";
                    $checkResultList.append($resultTR);
                    index++;
                });
            }else{
                showTip("提示", "检查通过！", 2000);
            }
            if (true) {
                $("#isPass").val("true");
                $("#uploadAndSaveFile").removeAttr("disabled");
            }
        }

    });

    //根据材料刷新列表
    var refreshFileList = function (dirCodeSelected, nameCodeSelected) {
        $resultFilelist.html("");
        var index = 1;
        $fileJson.forEach(function (file) {
            var dirCode = getDirCode(file.fileName);
            var nameCode = getFileNameCode(file.fileName).substring(0, 2);
            if(dirCodeSelected ==null && nameCodeSelected==null){
                var $resultTR = "";
                $resultTR += "<tr>";
                $resultTR += "<td>" + index + "</td>";
                $resultTR += "<td>" + file.fileName + "</td>";
                $resultTR += "<td>" + file.fileModifyDate + "</td>";
                $resultTR += "<td>" + (file.fileSize / 1048576).toFixed(2) + "M</td>";
                $resultTR += "</tr>";
                $resultFilelist.append($resultTR);
                index++;
            }else {
                if (nameCodeSelected == null || nameCodeSelected == "") {
                    if (dirCodeSelected == dirCode) {
                        var $resultTR = "";
                        $resultTR += "<tr>";
                        $resultTR += "<td>" + index + "</td>";
                        $resultTR += "<td>" + file.fileName + "</td>";
                        $resultTR += "<td>" + file.fileModifyDate + "</td>";
                        $resultTR += "<td>" + (file.fileSize / 1048576).toFixed(2) + "M</td>";
                        $resultTR += "</tr>";
                        $resultFilelist.append($resultTR);
                        index++;
                    }
                } else {
                    if (dirCodeSelected == dirCode && nameCodeSelected == nameCode) {
                        var $resultTR = "";
                        $resultTR += "<tr>";
                        $resultTR += "<td>" + index + "</td>";
                        $resultTR += "<td>" + file.fileName + "</td>";
                        $resultTR += "<td>" + file.fileModifyDate + "</td>";
                        $resultTR += "<td>" + (file.fileSize / 1048576).toFixed(2) + "M</td>";
                        $resultTR += "</tr>";
                        $resultFilelist.append($resultTR);
                        index++;
                    }
                }
            }
        });
    }

    $("#uploadAndSaveFile").click(function () {
        if ($("#isPass").val() == "true") {
            $("#uploadAndSaveFileForm").ajaxSubmit({
                url: "${path}/zzb/dzda/mlcl/jztp/save",
                type: "post",
                headers: {
                    OWASP_CSRFTOKEN: "${sessionScope.OWASP_CSRFTOKEN}"
                },
                beforeSend: function (XHR) {
                    myLoading.show();
                },
                success: function (json) {
                    if (json.success == true) {
                        showTip("提示", json.message, 2000);
                    } else {
                        showTip("提示", "上传失败!", 2000);
                    }
                },
                error: function (arg1, arg2, arg3) {
                    showTip("提示", "上传失败!", 2000);
                },
                complete: function (XHR, TS) {
                    myLoading.hide();
                }
            });
        } else {
            showTip("提示", "材料验证未通过,请检查通过后再提交！", 2000);
        }

    });

</script>