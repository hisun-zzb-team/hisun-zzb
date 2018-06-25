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
<title></title>
<div>
    <!-- BEGIN FORM-->
    <div class="portlet-title">

        <%-- <div class="caption">

             <span class="hidden-480"></span>

         </div>--%>

        <div class="portlet-title" style="text-align: right">
            <%--<span id="sqbs">
                <button type="button" class="btn green" onclick="formSubmit(1)">授权查阅全部档案</button>
                <button type="button" class="btn green" onclick="bfsq('false')">授权查阅指定目录</button>
            </span>
            <span id="zcsqbs">
                <button type="button" class="btn green" onclick="zcformSubmit()">再次授权查阅全部档案</button>
                <button type="button" class="btn green" onclick="bfsq('true')">再次授权查阅指定目录</button>
            </span>--%>
            <button type="button" class="btn green" onclick="formSubmit()">授权</button>
            <a class="btn" href="javascript:cancel()"><i class="icon-undo"></i>返回</a>

        </div>
    </div>
    <dl class="dlattrbute">
        <dt><a href="###">申请信息</a></dt>
        <form action="" class="form-horizontal" method="post">
        <div id="applyUserNameGroup" class="control-group">
            <label class="control-label">申请人</label>
            <div class="controls">
                <input type="text" class="span8 m-wrap" name="applyUserName" maxlength="128" id="applyUserName"
                       readonly
                       value="${entity.applyUserName }"/>
            </div>
        </div>

        <div id="e01Z807NameGroup" class="control-group">
            <label class="control-label">查阅人</label>
            <div class="controls">
                <input type="text" class="span8 m-wrap" name="e01Z807Name" readonly maxlength="128" id="e01Z807Name"
                       value="${entity.e01Z807Name }"/>
            </div>
        </div>
            <div class="control-group" id="e01Z824AGroup">

                <label class="control-label">查阅人单位及职位</label>
                <div class="controls">
                    <input size="16" type="text"  class="span8 m-wrap" value="${entity.e01Z824A}" readonly
                           id="e01Z824A" name="e01Z824A" >
                </div>
            </div>


            <div id="phoneGroup" class="control-group">
                <label class="control-label">查阅人联系电话</label>
                <div class="controls">
                    <input type="text" class="span8 m-wrap" name="phone" maxlength="128" id="phone" readonly
                           value="${entity.phone }"/>
                </div>
            </div>


        <div id="createDateGroup" class="control-group">
            <label class="control-label">申请时间</label>
            <div class="controls">
                <input type="text" class="span8 m-wrap" name="createDate" readonly maxlength="128" id="createDate"
                       value="<fmt:formatDate value="${entity.createDate }" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
            </div>
        </div>

        <div id="a0101Group" class="control-group">
            <label class="control-label"><span class="required">*</span>查阅档案对象姓名</label>
            <div class="controls">
                <input type="text" class="span8 m-wrap" required name="a0101" maxlength="128" id="a0101"
                       value="${entity.a0101 }" readonly/>
            </div>
        </div>

        <div id="sqcydazwGroup" class="control-group">
            <label class="control-label">查阅档案对象单位及职务</label>
            <div class="controls">
                <input type="text" class="span8 m-wrap" name="sqcydazw" readonly maxlength="128" id="sqcydazw"
                       value="${entity.sqcydazw }"/>
            </div>
        </div>

        <div id="readContentGroup" class="control-group">
            <label class="control-label">查阅档案内容</label>
            <div class="controls">
                <input type="text" class="span8 m-wrap" name="readContent" maxlength="128" id="readContent" readonly
                       value="${entity.readContent }"/>
            </div>
        </div>

        <div id="readTimeGroup" class="control-group">
            <label class="control-label">申请查阅时长</label>
            <div class="controls">
                <input type="text" class="span8 m-wrap" name="readTime" maxlength="128" id="readTime" readonly
                       value="${entity.readTime }"/>
            </div>
        </div>


        <div id="applyRemarkGroup" class="control-group">
            <label class="control-label">查阅档案原因</label>
            <div class="controls">
                        <textarea class="span8 m-wrap" name="applyRemark" maxlength="128" id="applyRemark" readonly
                                  style="resize: none;">${entity.applyRemark }</textarea>
            </div>
        </div>

        <div id="applyFileNameGroup" class="control-group">
            <label class="control-label">材料附件</label>
            <div class="controls">
                <a class="btn blue" href="javascript:downloadFile('${entity.id}')"><i
                        class="icon-circle-arrow-down"></i>${entity.applyFileName }</a>
            </div>
        </div>
    </form>
    </dl>
    <dl class="dlattrbute">
        <dt><a href="###">授权信息</a></dt>
        <form action="" class="form-horizontal" id="form1" method="post">
            <input type="hidden" name="e01z8Id" value="${entity.id }"/>
            <input type="hidden" name="a38Id" value="${a38Id}" id="a38Id">
            <div id="sqrGroup" class="control-group">
                <label class="control-label">授权人</label>
                <div class="controls">
                    <input type="text" class="span8 m-wrap" name="sqr" maxlength="128" id="sqr" readonly
                           value="${sqr }"/>
                </div>
            </div>
            <div id="sqztGroup" class="control-group">
                <label class="control-label">同意申请</label>
                <div class="controls">
                    <label class="radio">
                        <input type="radio" name="sqzt" value="1" checked/>
                        是
                    </label>
                    <label class="radio">
                        <input type="radio" name="sqzt" value="2"/>
                        否
                    </label>
                </div>
            </div>
            <div id="tysqId">
                <div id="sqcydaxmGroup" class="control-group">
                    <label class="control-label"><span class="required">*</span>申请查阅档案姓名</label>
                    <div class="controls">
                        <input type="text" class="span8 m-wrap" required name="sqcydaxm" maxlength="128" id="sqcydaxm"
                               value="${entity.a0101 }" readonly/>
                        <a href="javascript:queryA0101('${entity.a0101 }')">查询</a>
                    </div>
                </div>
                <div id="sqclfwGroup" class="control-group">
                    <label class="control-label">授权查阅档案内容</label>
                    <div class="controls">
                        <label class="radio">
                        <input type="radio" name="sqclfw" value="0" checked/>
                            全部
                        </label>
                        <label class="radio">
                            <input type="radio" name="sqclfw" value="1"/>
                            部分
                        </label>
                    </div>
                </div>

                <div id="sqcymlIdsGroup" class="control-group">
                    <label class="control-label">指定查阅目录</label>
                    <div class="controls"> <%--${path}/zzb/dzda/mlcl/tpcl/ajax/tree/${a38Id}--%>
                        <Tree:tree id="sqcymlIds" valueName="sqcyml"
                                   selectClass="span8 m-wrap"
                                   treeUrl="${path}/zzb/dzda/e01z1/ajax/tree?a38Id=${a38Id}"
                                   token="${sessionScope.OWASP_CSRFTOKEN}"
                                   submitType="get" dataType="json" isSearch="false" radioOrCheckbox="checkbox"
                                   chkboxType=' "Y" : "s", "N" : "" '
                                   checkedByTitle="true" isSelectTree="true"
                        />
                    </div>
                </div>
                <div id="sfyxxzGroup" class="control-group">
                    <label class="control-label">允许下载</label>
                    <div class="controls">
                        <label class="radio">
                            <input type="radio" name="sfyxxz" value="1"/>
                            是
                        </label>
                        <label class="radio">
                            <input type="radio" name="sfyxxz" value="0" checked/>
                            否
                        </label>
                    </div>
                </div>

                <div id="sfyxdyGroup" class="control-group">
                    <label class="control-label">允许打印</label>
                    <div class="controls">
                        <label class="radio">
                            <input type="radio" name="sfyxdy" value="1"/>
                            是
                        </label>
                        <label class="radio">
                            <input type="radio" name="sfyxdy" value="0" checked/>
                            否
                        </label>
                    </div>
                </div>
            </div>
            <div id="sqbzGroup" class="control-group">
                <label class="control-label">主管科室意见</label>
                <div class="controls">
                        <textarea class="span8 m-wrap" name="sqbz" maxlength="128" id="sqbz"
                                  style="resize: none;"></textarea>
                </div>
            </div>
        </form>
    </dl>

</div>
<div id="addModal" class="modal container hide fade" tabindex="-1" data-width="600">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close" type="button"></button>
                <h3 class="modal-title" id="addTitle">
                    档案信息
                </h3>
            </div>
            <div class="modal-body" id="addDiv">
                <table class="table table-striped table-bordered table-hover dataTable table-set">
                    <thead>
                    <TR height=28>
                        <th width=100>选择</th>
                        <th width=70>姓名</th>
                        <th width=120>性别</th>
                        <th width=120>出生年月</th>
                        <th>单位职务</th>
                    </thead>
                    <tbody>
                    <c:forEach items="${a38s}" var="vo">
                        <tr style="text-overflow:ellipsis;" id="table1">

                            <TD width=100><label class="radio">
                                <input type="radio" value="${vo.id}" name='a38IdValue' id="${vo.id}"
                                       <c:if test="${a38Id==vo.id}">checked</c:if>>
                            </label>
                            </TD>
                            <TD><c:out value="${vo.a0101}"></c:out></TD>
                            <TD><c:out value="${vo.a0104Content}"></c:out></TD>
                            <TD><c:out value="${vo.a0107}"></c:out></TD>
                            <TD><c:out value="${vo.a0157}"></c:out></TD>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <center>
        <div class="control-group">
            <div class="controls mt10">
                <button class="btn green" type="button" style="padding:7px 20px;" onclick="queding()">确定</button>

                <button type="button" class="btn btn-default" data-dismiss="modal"><i class='icon-remove-sign'></i> 关闭
                </button>
            </div>
        </div>
    </center>
</div>

<div id="bfsqModal" class="modal container hide fade" tabindex="-1" data-width="600" style="max-height:550px;">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close" type="button"></button>
                <h3 class="modal-title" id="bufsqTitle">
                    选择部分授权材料
                </h3>
            </div>
            <div class="modal-body" id="bfsqDiv">

            </div>
        </div>
    </div>
</div>


<script type="text/javascript">

    function xuanzhong(id) {
        $("input:radio[name='38IdValue']").parent().removeClass('checked');
        $("input [name='38IdValue']").attr("checked", false);

        $("#" + id).parent().addClass("checked");
        $("#" + id).attr("checked", true);
    }

    $(function () {
        //部分授权
        $("#sqcymlIdsGroup").hide();
        $("input[name='sqclfw']").bind("click", function () {
            var sqclfw = $('input[name="sqclfw"]:checked').val();
            if (sqclfw == "1") {
                $("#sqcymlIdsGroup").show();
            } else {
                $("#sqcymlIdsGroup").hide();
            }
        });

        //是否同意授权
        $("input[name='sqzt']").bind("click", function () {
            var sqclfw = $('input[name="sqzt"]:checked').val();
            if (sqclfw == "1") {
                $("#tysqId").show();
            } else {
                $("#tysqId").hide();
            }
        });

    })
    function bfsq(sfzasq) {

        var a38 = $("#a38Id").val();
        if (a38 == "" || a38 == null) {
            showTip("提示", "请选择要查阅何人档案", 2000);
            return;
        }
        $.ajax({
            url: "${path }/zzb/dzda/cyshouquan/ajax/tobfShouquan",
            type: "post",
            data: {"a38Id": a38, "sfzasq": sfzasq},
            headers: {
                OWASP_CSRFTOKEN: "${sessionScope.OWASP_CSRFTOKEN}"
            },
            dataType: "html",
            success: function (html) {
                $('#bfsqDiv').html(html);
                $('#bfsqModal').modal({
                    keyboard: true
                });
            },
            error: function () {
                showTip("提示", "出错了,请检查网络!", 2000);
            }
        });
    }


    function queding() {
        var a38Id = $('input[name="a38IdValue"]:checked').val();
        $("#a38Id").val(a38Id);
        $('#addModal').modal('hide');
        $("#sqcymlIds_tree").empty();
        refreshTreeTagByNewUrl("sqcymlIds", setting_sqcymlIds, "", "${path}/zzb/dzda/mlcl/tpcl/ajax/tree/" + a38Id);
    }
    var form1 = new EstValidate("form1");
    function formSubmit(status) {
        var sqzt = $('input[name="sqzt"]:checked').val();
        var a38 = $("#a38Id").val();
        if (sqzt=="1" && a38 == "" || a38 == null) {
            showTip("提示", "请选择申请查阅档案姓名", 2000);
            return;
        }
        var treeObj = $.fn.zTree.getZTreeObj("sqcymlIds_tree");
        var nodes = treeObj.getCheckedNodes(true);
        var idString = "";
        for (var i = 0; i < nodes.length; i++) {
            if (nodes[i].nodeType != "dir") {
                if (idString == "") {
                    idString = nodes[i].id;
                } else {
                    idString = idString + "," + nodes[i].id;
                }
            }
        }
        var sqclfw = $('input[name="sqclfw"]:checked').val();
        if (idString == "" && sqclfw == '1') {
            showTip("提示", "请选择要授权的材料", 2000);
            return
        }
        $("#sqcymlIds").val(idString);
        var bool = form1.form();
        if (bool) {
            $.ajax({
                url: "${path }/zzb/dzda/cyshouquan/shouquan",
                type: "post",
                data: $("#form1").serialize(),
                headers: {
                    OWASP_CSRFTOKEN: "${sessionScope.OWASP_CSRFTOKEN}"
                },
                dataType: "html",
                success: function (html) {
                    window.location.href = "${path}/zzb/dzda/cyshouquan/list?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}";
                },
                error: function () {
                    showTip("提示", "出错了,请检查网络!", 2000);
                }
            });
        }
    }
    function zcformSubmit() {
        var a38 = $("#a38Id").val();
        if (a38 == "" || a38 == null) {
            showTip("提示", "请选择要查阅何人档案", 2000);
            return;
        }
        var bool = form1.form();
        if (bool) {
            actionByConfirm1('', "${path }/zzb/dzda/cyshouquan/zcshouquan?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}", $("#form1").serialize(), function (json) {
                if (json.success) {
                    window.location.href = "${path}/zzb/dzda/cyshouquan/list?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}";
                } else {
                    showTip("提示", "请求失败", 2000);
                }
            }, "再次授权")
        }
    }
    function queryA0101(a0101) {
        $('#addDiv').show();

        $('#addModal').modal({
            keyboard: true
        });
    }
    $(function () {
        var applyFileName = "${entity.applyFileName }";
        if (applyFileName == "" || applyFileName == null) {
            $("#applyFileNameGroup").hide();
        }
        $("#table1 input").eq(0).checked = true;
        var zcsqbs = "${zcsqbs}"
        if (zcsqbs == "true") {
            $("#sqbs").hide();
        } else {
            $("#zcsqbs").hide();
        }
    })
    function downloadFile(id) {
        window.open("${path }/zzb/dzda/cysq/ajax/down?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}&id=" + id);
    }
    function cancel() {
        window.location.href = "${path}/zzb/dzda/cyshouquan/list?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}";
    }
</script>