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
                        <!-- 用于清除 -->
                        <form action="${path }/zzb/dzda/a38/list?queryType=gaojichaxun&OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}" class="form-horizontal" id="form2" method="post" >
                        </form>
                        <form action="${path }/zzb/dzda/a38/list?queryType=gaojichaxun&OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}" class="form-horizontal" id="form1" method="post" >
                            <div class="portlet-title">
                                <div class="caption">基本信息</div>
                                <div class="clearfix fr">
                                    <button id="submitbut" class="btn green" type="button" style="padding:7px 20px;" >查询</button>
                                    <button id="clearData" class="btn green" type="button" style="padding:7px 20px;" >清空</button>
                                    <a class="btn" href="javascript:cencal()"><i class="icon-remove-sign"></i> 取消</a>
                                 </div>
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
                                            <Tree:tree id="dutyLevelCode" valueName="dutyLevelValue"  selectClass="span10 m-wrap" treeUrl="${path}/api/dictionary/tree?typeCode=ZB09-2006/ZWJB" token="${sessionScope.OWASP_CSRFTOKEN}"
                                                       submitType="get" dataType="json" isSearch="true" radioOrCheckbox="checkbox" isSelectTree="true" defaultkeys="${vo.dutyLevelCode}" defaultvalues="${vo.dutyLevelValue}"/>
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
                                            <input type="text" class="span5 m-wrap" placeholder="日期格式 例如：2018或201801或20180101"  isDate="true" dateformat="yyyy,yyyymm,yyyymmdd" value="${vo.a3801End}" name="a3801" id="a3801End"/>
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
                                            <SelectTag:SelectTag id="yjztps" valueName="yjztpsName" defaultkeys="${vo.yjztps}" defaultvalues="${vo.yjztpsName}" token="${sessionScope.OWASP_CSRFTOKEN}"
                                                 textClass="m-wrap span10" radioOrCheckbox="radio" needNullValue="true" selectUrl="${path}/api/dictionary/select?typeCode=SFBS-2018"/>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="portlet-title">
                                <div class="caption">欠缺材料</div>
                            </div>

                            <div class="row-fluid">
                                <div class="span6 ">
                                    <div id="qqclGroup" class="control-group">
                                        <label class="control-label">是否欠缺材料</label>

                                        <div class="controls">
                                            <select name="qqcl" class="span10">
                                                <option value=""></option>
                                                <option value="1" <c:if test="${vo.qqcl=='1'}">selected</c:if>>是</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="portlet-title">
                                <div class="caption">材料接收</div>
                            </div>

                            <div class="row-fluid">
                                <div class="span6 ">
                                    <div id="cljsGroup" class="control-group">
                                        <label class="control-label">材料接收记录</label>

                                        <div class="controls">
                                            <select name="cljs" class="span10">
                                                <option value=""></option>
                                                <option value="1" <c:if test="${vo.cljs=='1'}">selected</c:if>>有</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="row-fluid">
                                <div class="span6 ">
                                    <div id="ljdwmcGroup" class="control-group">
                                        <label class="control-label">来件单位名称</label>

                                        <div class="controls">
                                            <input type="text" class="span10 m-wrap" name="ljdwmc" maxlength="128"
                                                   id="ljdwmc" value="${vo.ljdwmc}"/>
                                        </div>
                                    </div>
                                </div>

                                <div class="span6 ">
                                    <div id="cljsrqGroup" class="control-group">
                                        <label class="control-label">材料接收日期</label>

                                        <div class="controls">
                                            <input type="text" class="span5 m-wrap"
                                                   placeholder="日期格式 例如：2018或201801或20180101" isDate="true"
                                                   dateformat="yyyy,yyyymm,yyyymmdd" value="${vo.cljsrqStart}"
                                                   name="cljsrqStart" id="cljsrqStart"/>
                                            <input type="text" class="span5 m-wrap"
                                                   placeholder="日期格式 例如：2018或201801或20180101" isDate="true"
                                                   dateformat="yyyy,yyyymm,yyyymmdd" value="${vo.cljsrqEnd}" name="cljsrqEnd"
                                                   id="cljsrqEnd"/>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="row-fluid">
                                <div class="span6 ">
                                    <div id="e01Z237Group" class="control-group">
                                        <label class="control-label">材料处理状态：</label>
                                        <div class="controls">
                                            <SelectTag:SelectTag id="e01Z237" valueName="e01Z237Content"
                                                                 defaultkeys="${vo.e01Z237}" defaultvalues="${vo.e01Z237Content}"
                                                                 textClass="m-wrap span10" token="${sessionScope.OWASP_CSRFTOKEN}" needNullValue="true" radioOrCheckbox="radio" selectUrl="${path}/api/dictionary/select?typeCode=CLCLBS-2018"/>
                                        </div>
                                    </div>
                                </div>
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

        var form1 = new EstValidate("form1");

        $(function(){

            $("#submitbut").on("click",function(){
                var queryType = "${queryType}";

                var bool = form1.form();
                if(bool){
                    if(queryType=="a38List"){
                        $("#form1").submit();
//                        document.form1.submit();
                    }else{
                        $.ajax({
                            url : "${path }/zzb/dzda/dak/ajax/bdwdalist?queryType=gaojichaxun",
                            type : "post",
                            data : $("#form1").serialize(),
                            dataType : "html",
                            headers:{
                                OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
                            },
                            success : function(html){
                                $('#gjcxModal').modal('hide');
                                $('#gjcxDiv').html("");
                                var view = $("#tab_show");
                                view.html("");
                                view.html(html);
                            },
                            error : function(arg1, arg2, arg3){
                                showTip("提示","查询失败");
                            }
                        });
                    }
                }
            });
            $("#clearData").on("click",function(){
                var queryType = "${queryType}";
                if(queryType=="a38List"){
                    $("#form2").submit();
//                        document.form1.submit();
                }else{
                    $.ajax({
                        url : "${path }/zzb/dzda/dak/ajax/bdwdalist?queryType=gaojichaxun",
                        type : "post",
                        data : $("#form2").serialize(),
                        dataType : "html",
                        headers:{
                            OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
                        },
                        success : function(html){
                            $('#gjcxModal').modal('hide');
                            $('#gjcxDiv').html("");
                            var view = $("#tab_show");
                            view.html("");
                            view.html(html);
                        },
                        error : function(arg1, arg2, arg3){
                            showTip("提示","查询失败");
                        }
                    });
                }
            });
            //		document.searchForm.submit();
        });

        function cencal(){
            $('#gjcxModal').modal('hide');
            $('#gjcxDiv').html("");
        }
    </script>
</body>
</html>
