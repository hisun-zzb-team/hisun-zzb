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
    <title>高级查询条件设置</title>
    <link rel="stylesheet" type="text/css" href="${path}/css/bootstrap-fileupload.css" />
    <script src="${path}/js/bootstrap-fileupload.js"  type="text/javascript"></script>
</head>
<body>
    <div class="container-fluid">

        <div class="row-fluid">
            <div class="span12">
                <%-- BEGIN SAMPLE FORM PORTLET 表单主体--%>

                <div class="portlet box grey">
                    <div class="portlet-body form">
                        <!-- BEGIN FORM-->

                        <form action="${path }/zzb/dzda/a38/gjcxBdwdalist" class="form-horizontal" id="form1" name="form1" method="post" enctype="multipart/form-data">
                            <input type="hidden" name="id" value="" id="id">
                            <input type="hidden" name="filePath" value="" id="filePath">
                            <div class="portlet-title">
                                <div class="caption">基本信息</div>
                            </div>
                            
                            <div class="row-fluid">
                                <div class="span6 ">
                                    <div id="a0101Group" class="control-group">
                                        <label class="control-label">姓名</label>
                                        <div class="controls">
                                            <input type="text" class="span10 m-wrap" name="a0101" maxlength="128" id="a0101" value="${vo.a0101}" />
                                        </div>
                                    </div>
                                </div>
                                <div class="span6 ">
                                    <div id="a0157Group" class="control-group">
                                        <label class="control-label">单位职务</label>
                                        <div class="controls">
                                            <input type="text" class="span10 m-wrap" name="a0157" maxlength="128" id="a0157" value="${vo.a0157}" />
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="row-fluid">
                                <div class="span6 ">
                                    <div id="dabhGroup" class="control-group">
                                        <label class="control-label">档案编号</label>
                                        <div class="controls">
                                            <input type="text" class="span10 m-wrap" name="dabh" maxlength="128" id="dabh" value="${vo.dabh}" />
                                        </div>
                                    </div>
                                </div>
                                <div class="span6 ">
                                    <div id="smxhGroup" class="control-group">
                                        <label class="control-label">扫描序号</label>
                                        <div class="controls">
                                            <input type="text" class="span10 m-wrap" name="smxh" maxlength="128" id="smxh" value="${vo.smxh}" />
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="row-fluid">
                                <div class="span6 ">
                                    <div id="gbztCodesGroup" class="control-group">
                                        <label class="control-label">干部状态:</label>
                                        <div class="controls">
                                            <Tree:tree id="gbztCodes" valueName="gbztContents"  selectClass="span10 m-wrap" height="30px" treeUrl="${path}/api/dictionary/tree?typeCode=SAN_GBZT"
                                                       token="${sessionScope.OWASP_CSRFTOKEN}" submitType="get" dataType="json" isSearch="false" radioOrCheckbox="checkbox" checkedByTitle="true"
                                                       isSelectTree="true" defaultkeys="${vo.gbztCodes}" defaultvalues="${vo.gbztContents}"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="span6 ">
                                    <div id="daztContentsGroup" class="control-group">
                                        <label class="control-label">档案状态:</label>
                                        <div class="controls">
                                            <Tree:tree id="daztCodes" valueName="daztContents"  selectClass="span10 m-wrap" height="30px" treeUrl="${path}/api/dictionary/tree?typeCode=SAN_DAZT"
                                                       token="${sessionScope.OWASP_CSRFTOKEN}" submitType="get" dataType="json" isSearch="false" radioOrCheckbox="checkbox" checkedByTitle="true"
                                                       isSelectTree="true" defaultkeys="${vo.daztCodes}" defaultvalues="${vo.daztContents}"/>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="row-fluid">
                                <div class="span6 ">
                                    <div id="dutyLevelValueGroup" class="control-group">
                                        <label class="control-label">现职级</label>
                                        <div class="controls">
                                            <input type="text" class="span10 m-wrap" name="dutyLevelValue" maxlength="128" id="dutyLevelValue" value="${vo.dutyLevelValue}" />
                                        </div>
                                    </div>
                                </div>
                                <div class="span6 ">
                                    <div id="dutyLevelTimeBaseGroup" class="control-group">
                                        <label class="control-label">职级时间</label>
                                        <div class="controls" >
                                            <input type="text" class="span5 m-wrap" placeholder="日期格式 例如：2018或201801或20180101"  isDate="true" dateformat="yyyy,yyyymm,yyyymmdd"
                                                   value="${vo.dutyLevelTimeBaseStart }" name="dutyLevelTimeBase" id="dutyLevelTimeBaseStart" />
                                            <input type="text" class="span5 m-wrap" placeholder="日期格式 例如：2018或201801或20180101"  isDate="true" dateformat="yyyy,yyyymm,yyyymmdd"
                                                   value="${vo.dutyLevelTimeBaseEnd }" name="dutyLevelTimeBase" id="dutyLevelTimeBaseEnd" />
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="row-fluid">
                                <div class="span6 ">
                                    <div id="a3804AGroup" class="control-group">
                                        <label class="control-label">档案来处</label>
                                        <div class="controls">
                                            <input type="text" class="span10 m-wrap" name="a3804A" maxlength="128" id="a3804A" value="${vo.a3804A}" />
                                        </div>
                                    </div>
                                </div>
                                <div class="span6 ">
                                    <div id="a3821Group" class="control-group">
                                        <label class="control-label">转去单位</label>
                                        <div class="controls">
                                            <input type="text" class="span10 m-wrap" name="a3821" maxlength="128" id="a3821" value="${vo.a3821}" />
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="row-fluid">
                                <div class="span6 ">
                                    <div id="a3801Group" class="control-group">
                                        <label class="control-label">接收时间</label>
                                        <div class="controls">
                                            <input type="text" class="span5 m-wrap" placeholder="日期格式 例如：2018或201801或20180101"  isDate="true" dateformat="yyyy,yyyymm,yyyymmdd" value="${vo.a3801Start}" name="a3801" id="a3801Start"/>
                                            <input type="text" class="span5 m-wrap" placeholder="日期格式 例如：2018或201801或20180101"  isDate="true" dateformat="yyyy,yyyymm,yyyymmdd" value="${vo.a3801Start}" name="a3801" id="a3801End"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="span6 ">
                                    <div id="a3817Group" class="control-group">
                                        <label class="control-label">转出时间</label>
                                        <div class="controls">
                                            <input type="text" class="span5 m-wrap" placeholder="日期格式 例如：2018或201801或20180101"  isDate="true" dateformat="yyyy,yyyymm,yyyymmdd" value="${vo.a3817Start}" name="a3817" id="a3817Start"/>
                                            <span ></span>
                                            <input type="text" class="span5 m-wrap" placeholder="日期格式 例如：2018或201801或20180101"  isDate="true" dateformat="yyyy,yyyymm,yyyymmdd" value="${vo.a3817End}" name="a3817" id="a3817End"/>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="portlet-title">
                                <div class="caption">目录信息</div>
                            </div>

                            <div class="row-fluid">
                                <div class="span6 ">
                                    <div id="e01Z111Group" class="control-group">
                                        <label class="control-label">材料名称</label>
                                        <div class="controls">
                                            <input type="text" class="span10 m-wrap" name="e01Z111" maxlength="128" id="e01Z111" value="${vo.e01Z111}" />
                                        </div>
                                    </div>
                                </div>
                                <div class="span6 ">
                                    <div id="e01Z101AGroup" class="control-group">
                                        <label class="control-label">材料类型</label>
                                        <div class="controls">
                                            <Tree:tree id="e01Z101B" valueName="e01Z101A" treeUrl="${path}/zzb/dzda/e01z4/tree" token="${sessionScope.OWASP_CSRFTOKEN}"
                                                       submitType="post" dataType="json" isSearch="false" isSelectTree="true"
                                                       defaultkeys="${vo.e01Z101B}" defaultvalues="${vo.e01Z101A}" radioOrCheckbox="checkbox" selectClass="span10 m-wrap"/>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="row-fluid">
                                <div class="span6 ">
                                    <div id="e01Z207Group" class="control-group">
                                        <label class="control-label">接收人</label>
                                        <div class="controls">
                                            <input type="text" class="span10 m-wrap" name="e01Z207" maxlength="128" id="e01Z207" value="${vo.e01Z207}" />
                                        </div>
                                    </div>
                                </div>
                                <div class="span6 ">
                                    <div id="e01Z201Group" class="control-group">
                                        <label class="control-label">接收时间</label>
                                        <div class="controls">
                                            <input type="text" class="span5 m-wrap" placeholder="日期格式 例如：2018或201801或20180101"  isDate="true" dateformat="yyyy,yyyymm,yyyymmdd" value="${vo.e01Z201Start}" name="e01Z201" id="e01Z201Start"/>
                                            <input type="text" class="span5 m-wrap" placeholder="日期格式 例如：2018或201801或20180101"  isDate="true" dateformat="yyyy,yyyymm,yyyymmdd" value="${vo.e01Z201End}" name="e01Z201" id="e01Z201End"/>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="row-fluid">
                                <div class="span6 ">
                                    <div id="e01Z204Group" class="control-group">
                                        <label class="control-label">材料来处</label>
                                        <div class="controls">
                                            <input type="text" class="span10 m-wrap" name="e01Z204" maxlength="128" id="e01Z204" value="${vo.e01Z204}" />
                                        </div>
                                    </div>
                                </div>
                                <div class="span6 ">
                                    <div id="e01z1CreatedTimeGroup" class="control-group">
                                        <label class="control-label">创建时间</label>
                                        <div class="controls">
                                            <input type="text" class="span5 m-wrap" placeholder="日期格式 例如：2018或201801或20180101"  isDate="true" dateformat="yyyy,yyyymm,yyyymmdd" value="${vo.e01z1CreatedTimeStart}" name="e01z1CreatedTime" id="e01z1CreatedTimeStart"/>
                                            <input type="text" class="span5 m-wrap" placeholder="日期格式 例如：2018或201801或20180101"  isDate="true" dateformat="yyyy,yyyymm,yyyymmdd" value="${vo.e01z1CreatedTimeEnd}" name="e01z1CreatedTime" id="e01z1CreatedTimeEnd"/>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="row-fluid">
                                <div class="span6 ">
                                    <div id="e01Z117Group" class="control-group">
                                        <label class="control-label">材料制成时间</label>
                                        <div class="controls">
                                            <input type="text" class="span5 m-wrap" placeholder="日期格式 例如：2018或201801或20180101"  isDate="true" dateformat="yyyy,yyyymm,yyyymmdd" value="${vo.e01Z117Start}" name="e01Z117" id="e01Z117Start"/>
                                            <input type="text" class="span5 m-wrap" placeholder="日期格式 例如：2018或201801或20180101"  isDate="true" dateformat="yyyy,yyyymm,yyyymmdd" value="${vo.e01Z117End}" name="e01Z117" id="e01Z117End"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="span6 ">
                                    <div id="yjztpsGroup" class="control-group">
                                        <label class="control-label">加载图片</label>
                                        <div class="controls">
                                            <SelectTag:SelectTag id="yjztps" valueName="yjztpsName" defaultkeys="${vo.yjztps}" defaultvalues="${vo.yjztpsName}"
                                                 textClass="m-wrap span6" radioOrCheckbox="radio" selectUrl="${path}/zzb/dzda/dak/select"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row-fluid">
                                <center>
                                    <div style="margin:auto;">
                                        <%--<button id="submitbut" class="btn green" type="button" style="padding:7px 20px;" >确定</button>--%>
                                        <button type="button" class="btn Short_but" onclick="gjSearchSubmit()">查询</button>
                                        <a class="btn" href="javascript:cencal()"><i class="icon-remove-sign"></i> 取消</a>
                                    </div>
                                </center>
                            </div>
                        </form>
                    </div>

                </div>

                <%-- END SAMPLE FORM PORTLET--%>
            </div>
        </div>

        <%-- END PAGE CONTENT--%>
    </div>
    <script type="text/javascript">
//        jQuery(document).ready(function () {
//
//            App.init();//必须，不然导航栏及其菜单无法折叠
//
//        });
        function gjSearchSubmit(){
            document.form1.submit();
        }
        var form1 = new EstValidate("form1");

        <%--$(function(){--%>
            <%--$("#submitbut").on("click",function(){--%>
                <%--var bool = form1.form();--%>
                <%--if(bool){--%>
                    <%--$.ajax({--%>
                        <%--url : "${path }/zzb/dzda/a38/ajax/gjcxBdwdalist",--%>
                        <%--type : "post",--%>
                        <%--data : $("#form1").serialize(),--%>
                        <%--dataType : "html",--%>
                        <%--success : function(html){--%>
                            <%--$('#gjcxModal').modal('hide');--%>
                            <%--$('#gjcxDiv').html("");--%>
                            <%--var view = $("#tab_show");--%>
                            <%--view.html("");--%>
                            <%--view.html(html);--%>
                        <%--},--%>
                        <%--error : function(arg1, arg2, arg3){--%>
                            <%--showTip("提示","查询失败");--%>
                        <%--}--%>
                    <%--});--%>
                <%--}--%>
            <%--});--%>
            <%--//		document.searchForm.submit();--%>
        <%--});--%>

        function cencal(){
            $('#gjcxModal').modal('hide');
            $('#gjcxDiv').html("");
        }
    </script>
</body>
</html>
