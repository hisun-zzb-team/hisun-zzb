<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/inc/servlet.jsp" %>
<%@include file="/WEB-INF/jsp/inc/taglib.jsp" %>
<%--
  ~ Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
  ~ http://www.hn-hisun.com
  ~ 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
  --%>

<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<div >
    <!-- BEGIN FORM-->
    <div class="portlet box grey">

        <div class="portlet-title">

            <div class="caption">

                <i class="icon-reorder"></i>

                <span class="hidden-480">修改材料接收</span>

            </div>
            <div class="tools">
                <a href="javascript:location.reload();" class="reload"></a>

            </div>
        </div>
        <form action="${path}/zzb/dzda/e01z2/ajax/update" class="form-horizontal" id="form1" method="post">

            <div class="row-fluid">
                <div class="span6 ">
                    <div id="e01Z204AGroup" class="control-group">
                        <label class="control-label"><span class="required">*</span>来件单位名称</label>
                        <div class="controls">
                            <input type="text" class="span10 m-wrap" name="e01Z204A" required maxlength="128" id="e01Z204A" value="${e01z2.e01Z204A}" />
                        </div>
                    </div>
                </div>
                <div class="span6 ">
                    <div id="e01Z204BGroup" class="control-group">
                        <label class="control-label">来件单位代码</label>
                        <div class="controls">
                            <input type="text" class="span10 m-wrap" name="e01Z204B"  maxlength="128" id="e01Z204B" value="${e01z2.e01Z204B}" />
                        </div>
                    </div>
                </div>
            </div>
            <div class="row-fluid">
                <div class="span6 ">
                    <div id="e01Z221AGroup" class="control-group">
                        <label class="control-label">材料名称：</label>
                        <div class="controls">
                            <input type="text" class="span10 m-wrap" name="e01Z221A"  maxlength="128" id="e01Z221A" value="${e01z2.e01Z221A}" />
                        </div>
                    </div>
                </div>
                <div class="span6 ">
                    <div id="e01Z221BGroup" class="control-group">
                        <label class="control-label">材料名称代码：</label>
                        <div class="controls">
                            <input type="text"  class="span10 m-wrap" name="e01Z221B"  maxlength="128" id="e01Z221B" value="${e01z2.e01Z221B}" />
                        </div>
                    </div>
                </div>
            </div>
            <div class="row-fluid">
                <div class="span6 ">
                    <div id="e01Z217Group" class="control-group">
                        <label class="control-label">材料编号：</label>
                        <div class="controls">
                            <input type="text" class="span10 m-wrap" name="e01Z217"  maxlength="128" id="e01Z217" value="${e01z2.e01Z217}" />
                        </div>
                    </div>
                </div>
                <div class="span6 ">
                    <div id="e01Z211Group" class="control-group">
                        <label class="control-label">材料类号：</label>
                        <div class="controls">
                            <Tree:tree id="parentId" valueName="e01Z211" treeUrl="${path}/sys/admin/dzda/catalogType/cljs/tree" token="${sessionScope.OWASP_CSRFTOKEN}"
                                       defaultkeys="${e01z2.e01Z211}" defaultvalues="${e01z2.e01Z211}" submitType="post" dataType="json" isSearch="false" isSelectTree="true" required="false" onClick="onClickByTree1" selectClass="span10 m-wrap"
                            />
                            <%--<input type="hidden" id="currentNodeName" name="e01Z211" value="${currentNodeName}"/>--%>
                        </div>
                    </div>
                </div>

            </div>
            <div class="row-fluid">
                <div class="span6 ">
                    <div id="e01Z207Group" class="control-group">
                        <label class="control-label">接收人：</label>
                        <div class="controls">
                            <input type="text" class="span10 m-wrap" name="e01Z207"  maxlength="128" id="e01Z207" value="${e01z2.e01Z207}"/>
                        </div>
                    </div>
                </div>
                <div class="span6 ">
                    <div id="e01Z214Group" class="control-group">
                        <label class="control-label"><span class="required">*</span>材料接收顺序号</label>
                        <div class="controls">
                            <input type="text" number="true"  required maxlength="3" class="span10 m-wrap" name="e01Z214"  maxlength="128" id="e01Z214" value="${e01z2.e01Z214}" />
                        </div>
                    </div>
                </div>
            </div>
            <div class="row-fluid">
                <div class="span6 ">
                    <div id="e01Z201Group" class="control-group">
                        <label class="control-label">收件日期：</label>
                        <div class="controls">
                            <input type="text" class="span10 m-wrap" name="e01Z201"  maxlength="128" id="e01Z201" value="${e01z2.e01Z201}"  placeholder="日期格式 例如：2018或201801或20180101" isDate="true" dateformat="yyyy,yyyymm,yyyymmdd"/>
                        </div>
                    </div>
                </div>
                <div class="span6 ">
                    <div id="e01Z227Group" class="control-group">
                        <label class="control-label">材料制成日期：</label>
                        <div class="controls">
                            <input type="text" class="span10 m-wrap" name="e01Z227"  maxlength="128" id="e01Z227" value="${e01z2.e01Z227}"  placeholder="日期格式 例如：2018或201801或20180101" isDate="true" dateformat="yyyy,yyyymm,yyyymmdd"/>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row-fluid">
                <div class="span6 ">
                    <div id="e01Z237Group" class="control-group">
                        <label class="control-label">材料处理状态：</label>
                        <div class="controls">
                            <SelectTag:SelectTag id="e01Z237" valueName="e01Z237Content"  defaultkeys="${e01z2.e01Z237}" defaultvalues="${e01z2.e01Z237Content}"
                                                 textClass="m-wrap span10" radioOrCheckbox="radio" selectUrl="${path}/api/dictionary/select?typeCode=CLCLBS-2018"/>
                        </div>
                    </div>
                </div>
                <div class="span6 ">
                    <div id="e01Z244Group" class="control-group">
                        <label class="control-label">是否已处理：</label>
                        <div class="controls">
                            <SelectTag:SelectTag id="e01Z244" valueName="e01Z244Content" textClass="m-wrap span10" defaultkeys="${e01z2.e01Z244}" defaultvalues="${e01z2.e01Z244Content}"
                                                 radioOrCheckbox="radio" selectUrl="${path}/api/dictionary/select?typeCode=SFBS-2018"/>
                        </div>
                    </div>
                </div>

            </div>


            <div class="row-fluid">
                <div class="span6 ">
                    <div id="e01Z224Group" class="control-group">
                        <label class="control-label">页数：</label>
                        <div class="controls">
                            <input type="text" number="true" class="span10 m-wrap" name="e01Z224"  maxlength="128" id="e01Z224" value="${e01z2.e01Z224}" />
                        </div>
                    </div>
                </div>
                <div class="span6 ">
                    <div id="e01Z234Group" class="control-group">
                        <label class="control-label">份数：</label>
                        <div class="controls">
                            <input type="text" number="true"   class="span10 m-wrap" name="e01Z234"  maxlength="128" id="e01Z234" value="${e01z2.e01Z234}" />
                        </div>
                    </div>
                </div>
            </div>
            <div class="row-fluid">
                <div class="span6 ">
                    <div id="e01Z241Group" class="control-group">
                        <label class="control-label">零散材料序号：</label>
                        <div class="controls">
                            <input type="text" class="span10 m-wrap" name="e01Z241"  maxlength="128" id="e01Z241" value="${e01z2.e01Z241}" />
                        </div>
                    </div>
                </div>
                <div class="span6 ">
                    <div id="e01Z231Group" class="control-group">
                        <label class="control-label">备注：</label>
                        <div class="controls">
                            <input type="text" class="span10 m-wrap" name="e01Z231"  maxlength="128" id="e01Z231" value="${e01z2.e01Z231}" />
                        </div>
                    </div>
                </div>

            </div>


            <div  >
                <center>
                    <div style="margin:auto;">
                        <input type="hidden" name="a38Id" value="${a38Id}">
                        <input type="hidden" name="id" value="${e01z2.id}">
                        <button class="btn green" type="button" style="padding:7px 20px;" onclick="submite01z2()">确定</button>
                        <a class="btn" href="javascript:cencal()"><i class="icon-remove-sign"></i> 取消</a>
                    </div>
                </center>
            </div>
        </form>
    </div>

    <script type="text/javascript">
        function cencal(){
            $("#catalogList").html("");
            $("#e01z2Table").show();
        }
        function onClickByTree1 (event, treeId, treeNode){
            // $("#currentNodeId").val(treeNode.id);//赋值
            $("#currentNodeName").val(treeNode.name);//赋值
            //    $("#currentNodeParentId").val(treeNode.pId);//赋值
            /*var currentNodeId = $("#currentNodeId").val();
             var currentNodeName = $("#currentNodeName").val();
             var currentNodeParentId = $("#currentNodeParentId").val();*/
            /*   $.ajax({
             url : "${path}/sys/admin/dzda/catalogType/ajax/changeAddEdit?currentNodeId="+currentNodeId+"&currentNodeName="+currentNodeName+"&currentNodeParentId="+currentNodeParentId,
             type : "get",
             data : null,
             dataType : "json",
             headers: {
             "OWASP_CSRFTOKEN":"${sessionScope.OWASP_CSRFTOKEN}"
             },
             success : function(data){
             $("#sort").val(data.sort);//赋值
             $("#catalogCode").val(data.catalogCode);
             },
             error : function(){

             }
             });*/
        }
        var addForm = new EstValidate("form1");
        function submite01z2(){
            var bool = addForm.form();
            if(bool){
                $.ajax({
                    url : "${path }/zzb/dzda/e01z2/update",
                    type : "post",
                    data : $('#form1').serialize(),
                    dataType : "json",
                    headers: {
                        "OWASP_CSRFTOKEN":"${sessionScope.OWASP_CSRFTOKEN}"
                    },
                    success : function(json){
                        if(json.success){
                            $.ajax({
                                url : "${path }/zzb/dzda/e01z2/ajax/list",
                                type : "get",
                                data : {"a38Id":"${a38Id}"},
                                dataType : "html",
                                success : function(html){
                                    console.log(111);
                                    var view = $("#tab_show");
                                    view.html(html);
                                },
                                error : function(arg1, arg2, arg3){
                                    showTip("提示","材料接受加载失败");
                                }
                            });
                        }else{
                            showTip("提示", "新增失败", 2000);
                        }
                    },
                    error : function(arg1, arg2, arg3){
                        showTip("提示","出错了请联系管理员",2000);
                    }
                });

            }
        }
    </script>
</div>