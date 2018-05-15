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
<title></title>
<div >
    <!-- BEGIN FORM-->
    <div class="portlet-title">

        <div class="caption">


            <span class="hidden-480">阅档申请信息</span>

        </div>
        <div class="portlet-title" style="text-align: right">

            <button type="button" class="btn green" onclick="formSubmit(1)">授权查阅全部档案</button>
            <button type="button" class="btn green" onclick="formSubmit()">授权查阅指定目录</button>
            <button type="button" class="btn green" onclick="formSubmit(2)">拒绝</button>
            <a class="btn" href="javascript:cancel()"><i class="icon-undo"></i>返回</a>

        </div>
    </div>
    <form action="" class="form-horizontal" id="form1" method="post">
        <input type="hidden" name="id" value="${entity.id }"/>
        <input type="hidden" name="auditingState" id="auditingState" value=""/>
        <input type="hidden" name="a38Id" value="${a38Id}" id="a38Id">
        <div class="row-fluid">
            <div class="span6 ">
                <div id="a0101Group" class="control-group">
                    <label class="control-label"><span class="required">*</span>查阅何人档案</label>
                    <div class="controls">
                        <input type="text" class="span8 m-wrap"  required name="a0101"  maxlength="128" id="a0101" value="${entity.a0101 }"/>
                        <a href="javascript:queryA0101('${entity.a0101 }')">查询</a>
                    </div>
                </div>
            </div>
            <div class="span6 ">
                <div id="applyUserNameGroup" class="control-group">
                    <label class="control-label">申请人</label>
                    <div class="controls">
                        <input type="text" class="span8 m-wrap" name="applyUserName"  maxlength="128" id="applyUserName" value="${entity.applyUserName }"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="row-fluid">
            <div class="span6 ">
                <div id="sqcydazwGroup" class="control-group">
                    <label class="control-label">单位职务</label>
                    <div class="controls">
                        <input type="text" class="span8 m-wrap" name="sqcydazw" readonly  maxlength="128" id="sqcydazw" value="${entity.sqcydazw }"/>
                    </div>
                </div>
            </div>
            <div class="span6 ">
                <div id="e01Z807NameGroup" class="control-group">
                    <label class="control-label">查阅人</label>
                    <div class="controls">
                        <input type="text" class="span8 m-wrap" name="e01Z807Name" readonly maxlength="128" id="e01Z807Name" value="${entity.e01Z807Name }"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="row-fluid">
            <div class="span6 ">
                <div id="sqdwpzldGroup" class="control-group">
                    <label class="control-label">批准人</label>
                    <div class="controls">
                        <input type="text" class="span8 m-wrap" name="sqdwpzld"  maxlength="128" id="sqdwpzld" value="${entity.sqdwpzld }"/>
                    </div>
                </div>
            </div>
            <div class="span6 ">
                <div id="createDateGroup" class="control-group">
                    <label class="control-label">申请时间</label>
                    <div class="controls">
                        <input type="text" class="span8 m-wrap" name="createDate" readonly maxlength="128" id="createDate" value="${entity.createDate }"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="row-fluid">
            <div class="span6 ">
                <div id="readTimeGroup" class="control-group">
                    <label class="control-label">查阅时长</label>
                    <div class="controls">
                        <input type="text" class="span8 m-wrap" name="readTime"  maxlength="128" id="readTime" value="${entity.readTime }"/>
                    </div>
                </div>
            </div>
            <div class="span6 ">
                <div id="phoneGroup" class="control-group">
                    <label class="control-label">联系电话</label>
                    <div class="controls">
                        <input type="text" class="span8 m-wrap" name="phone"  maxlength="128" id="phone" value="${entity.phone }"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="row-fluid">
            <div class="span6 ">
                <div id="readContentGroup" class="control-group">
                    <label class="control-label">查阅内容</label>
                    <div class="controls">
                        <input type="text" class="span8 m-wrap" name="readContent"  maxlength="128" id="readContent" value="${entity.readContent }"/>
                    </div>
                </div>
            </div>
            <div class="span6 ">
                <div id="isDownloadGroup" class="control-group">
                    <label class="control-label">允许下载</label>
                    <div class="controls">
										<span class=""><input type="radio" value="0"
                                                              name="isDownload" >否</span>

                                        <span class="checked"><input type="radio" checked
                                                                         value="1" name="isDownload" >是</span>

                    </div>
                </div>
            </div>
        </div>
        <div class="row-fluid" id="applyFileNameId">
            <div class="span6 ">
                <div id="applyFileNameGroup" class="control-group">
                    <label class="control-label">上传材料</label>
                    <div class="controls">
                        <input type="text" class="span8 m-wrap" name="applyFileName"  maxlength="128" readonly id="applyFileName" value="${entity.applyFileName }"/>
                        <a href="javascript:downloadFile('${entity.id}')">下载</a>
                    </div>
                </div>
            </div>
        </div>
        <div class="row-fluid">
            <div class="span6 ">
                <div id="applyRemarkGroup" class="control-group">
                    <label class="control-label">查阅理由</label>
                    <div class="controls">
                        <textarea  class="span8 m-wrap" name="applyRemark"  maxlength="128" id="applyRemark" >${entity.applyRemark }</textarea>
                    </div>
                </div>
            </div>
            <div class="span6 ">
                <div id="auditingRemarkGroup" class="control-group">
                    <label class="control-label">备注</label>
                    <div class="controls">
                        <textarea class="span8 m-wrap" name="auditingRemark"  maxlength="128" id="auditingRemark">${entity.auditingRemark }</textarea>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
<div id="addModal" class="modal container hide fade" tabindex="-1" data-width="600">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close"  type="button"></button>
                <h3 class="modal-title" id="addTitle" >
                    档案信息
                </h3>
            </div>
            <div class="modal-body" id="addDiv">
                <table class="table table-striped table-bordered table-hover dataTable table-set">
                    <thead>
                    <TR height=28>
                        <th width=100>选择</th>
                        <th width=70>姓名</th>
                        <th  width=120>性别</th>
                        <th width=120>出生年月</th>
                        <th>单位职务</th>
                    </thead>
                    <tbody>
                    <c:forEach items="${a38s}" var="vo">
                        <tr style="text-overflow:ellipsis;" id="table1">

                            <TD width=100><span style="margin-top: 5px; margin-right: 10px; display: inline-block;">
                                <input type="radio" value="${vo.id}" name = 'a38IdValue' <c:if test="${a38Id==vo.id}">checked</c:if>> </span>
                            </TD>
                            <TD><c:out value="${vo.a0101}"></c:out></TD>
                            <TD ><c:out value="${vo.a0104Content}"></c:out> </TD>
                            <TD ><c:out value="${vo.a0107}"></c:out ></TD>
                            <TD ><c:out value="${vo.a0157}"></c:out ></TD>
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

            <button type="button" class="btn btn-default" data-dismiss="modal"><i class='icon-remove-sign'></i> 关闭</button>
        </div>
    </div>
    </center>
</div>

<script type="text/javascript">
    function queding(){
        var  a38Id =  $('input[name="a38IdValue"]:checked').val();
        $("#a38Id").val(a38Id);
        $('#addModal').modal('hide');
    }
    var form1 = new EstValidate("form1");
    function formSubmit(status){
        $("#auditingState").val(status);
        var a38 = $("#a38Id").val();
        if(a38 == "" || a38 == null){
            showTip("提示","请选择要查阅和人档案");
            return;
        }
        var bool = form1.form();
        if(bool){
            $.ajax({
                url : "${path }/zzb/dzda/cyshouquan/shouquan",
                type : "post",
                data : $("#form1").serialize(),
                headers:{
                    OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
                },
                dataType : "json",
                success : function(data){
                    if(data.success){
                        window.location.href = "${path}/zzb/dzda/cyshouquan/list";
                    }
                },
                error : function(){
                    showTip("提示","出错了,请检查网络!",2000);
                }
            });
        }
    }
    function queryA0101(a0101){
        $('#addDiv').show();

        $('#addModal').modal({
            keyboard: true
        });
    }
    $(function(){
        var applyFileName = "${entity.applyFileName }";
        if(applyFileName == "" || applyFileName == null){
            $("#applyFileNameId").hide();
        }
        $("#table1 input").eq(0).checked = true;
    })
    function downloadFile(id){
        window.open("${path }/zzb/dzda/cysq/ajax/down?id="+id);
    }
    function cancel(){
        window.location.href = "${path}/zzb/dzda/cyshouquan/list";
    }
</script>