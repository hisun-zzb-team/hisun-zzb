<%@ page import="com.hisun.util.DateUtil" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/inc/servlet.jsp" %>
<%@include file="/WEB-INF/jsp/inc/taglib.jsp" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>添加现任职务</title>
</head>
<body>
<div >
	<!-- BEGIN FORM-->
	<div class="portlet box grey">

		<div class="portlet-title">

			<div class="caption">

				<i class="icon-reorder"></i>

				<span class="hidden-480">添加学历及学习经历</span>

			</div>
			<div class="tools">
				<a href="javascript:location.reload();" class="reload"></a>

			</div>
		</div>
		<form action="${path}/zzb/gbgl/a02/save" class="form-horizontal" id="addXxjlform" method="post">
			<input type="hidden" id="a01Id" name="a01Id" value="${a01Id}">
			<table  border="0" style="width:100%;" cellPadding="5px">
                <div class="caption">学历及学习经历基本信息</div>
				<div class="row-fluid">
					<div class="span6 ">
						<div id="a0801AGroup" class="control-group">
							<label class="control-label" ><span class="required">*</span>学历</label>
							<div class="controls" >
								<Tree:tree id="a0801B" valueName="a0801A"
										   selectClass="span9 m-wrap"
										   treeUrl="${path}/api/dictionary/tree?typeCode=ZB64-2006/XLDM"
										   token="${sessionScope.OWASP_CSRFTOKEN}"
										   submitType="post" dataType="json" isSearch="false"
										   checkedByTitle="true" isSelectTree="true" defaultkeys=""
										   defaultvalues=""/>
							</div>
						</div>
					</div>
					<div class="span6 ">
						<div id="a0811Group" class="control-group">
							<label class="control-label" >学习类型</label>
							<div class="controls" style="margin-left: 200px">
								<label class="radio">
									<input type="radio" name="a0811" value="1" checked/>
									全日制&nbsp;&nbsp;&nbsp;
								</label>
								<label class="radio">
									<input type="radio" name="a0811" value="0" />
									在职
								</label>
							</div>
						</div>
					</div>
				</div>

				<div class="row-fluid">
					<%--<div class="span6 ">--%>
						<%--<div id="aRzdwsmGroup" class="control-group">--%>
							<%--<label class="control-label" >学历说明</label>--%>
							<%--<div class="controls">--%>
								<%--<input type="text" class="span9 m-wrap" name="aRzdwsm" maxlength="128" id="aRzdwsm" value="" />--%>
							<%--</div>--%>
						<%--</div>--%>
					<%--</div>--%>
					<div class="span6 ">
						<div id="a0831Group" class="control-group">
							<label class="control-label" >学习完成情况</label>
                            <div class="controls">
								<Tree:tree id="a0831" valueName="a0831A"
										   selectClass="span9 m-wrap"
										   treeUrl="${path}/api/dictionary/tree?typeCode=ZB28-2994/XXWC"
										   token="${sessionScope.OWASP_CSRFTOKEN}"
										   submitType="post" dataType="json" isSearch="false"
										   checkedByTitle="true" isSelectTree="true" defaultkeys=""
										   defaultvalues=""/>
                            </div>
						</div>
					</div>
				</div>

				<div class="row-fluid">
					<div class="span6 ">
						<div id="aXlzsbhGroup" class="control-group">
							<label class="control-label" >学历证书编号</label>
							<div class="controls" >
								<input type="text" class="span9 m-wrap" name="aXlzsbh" maxlength="128" id="aXlzsbh" value="" />
							</div>
						</div>
					</div>
					<div class="span6 ">
						<div id="a0821AGroup" class="control-group">
							<label class="control-label" >从学单位类别</label>
							<div class="controls">
                                <Tree:tree id="a0821" valueName="a0821A"
                                           selectClass="span9 m-wrap"
                                           treeUrl="${path}/api/dictionary/tree?typeCode=ZB27-2006/CXDW"
                                           token="${sessionScope.OWASP_CSRFTOKEN}"
                                           submitType="post" dataType="json" isSearch="false"
                                           checkedByTitle="true" isSelectTree="true" defaultkeys=""
                                           defaultvalues=""/>
							</div>
						</div>
					</div>
				</div>

                <div class="row-fluid">
                    <div class="span6 ">
                        <div id="aXlzssjGroup" class="control-group">
                            <label class="control-label" >学历发证日期</label>
							<div class="controls">
								<input type="text" class="span9 m-wrap" name="aXlzssj"  maxlength="128" id="aXlzssj" value=""
									   placeholder="日期格式 例如：2018或201801或20180101"  isDate="true" dateformat="yyyy,yyyymm,yyyymmdd"/>
							</div>
                        </div>
                    </div>
                    <div class="span6 ">
                        <div id="a0827AGroup" class="control-group">
                            <label class="control-label" >所学专业类别</label>
                            <div class="controls">
                                <SelectTag:SelectTag id="a0827" needNullValue="true"
                                                     valueName="a0827A"
                                                     defaultkeys="" token="${sessionScope.OWASP_CSRFTOKEN}"
                                                     defaultvalues=""
                                                     textClass="span9 m-wrap" radioOrCheckbox="radio"
                                                     selectUrl="${path}/api/dictionary/tree?typeCode=GB/T1635-1997"/>
                            </div>
                        </div>
                    </div>

                </div>

				<div class="row-fluid">
					<div class="span6 ">
						<div id="a0901AGroup" class="control-group">
							<label class="control-label" >学位</label>
							<div class="controls">
								<Tree:tree id="a0901B" valueName="a0901A"
										   selectClass="span9 m-wrap"
										   treeUrl="${path}/api/dictionary/tree?typeCode=GB/T6864-2003"
										   token="${sessionScope.OWASP_CSRFTOKEN}"
										   submitType="post" dataType="json" isSearch="false"
										   checkedByTitle="true" isSelectTree="true" defaultkeys=""
										   defaultvalues=""/>
							</div>

						</div>
					</div>
					<div class="span6 ">
						<div id="a0804Group" class="control-group">
							<label class="control-label" >入学日期</label>
							<div class="controls">
								<input type="text" class="span9 m-wrap" name="a0804" maxlength="128" id="a0804" value=""
									   placeholder="日期格式 例如：2018或201801或20180101"  isDate="true" dateformat="yyyy,yyyymm,yyyymmdd"/>
							</div>
						</div>
					</div>
				</div>

				<div class="row-fluid">
					<%--<div class="span6 ">--%>
						<%--<div id="aZbDwzwmcGroup" class="control-group">--%>
							<%--<label class="control-label" >学位说明</label>--%>
							<%--<div class="controls">--%>
                                <%--<input type="text" class="span9 m-wrap" name="aZbDwzwmc" number="true" maxlength="128" id="aZbDwzwmc" value="" />--%>
							<%--</div>--%>
						<%--</div>--%>
					<%--</div>--%>
					<div class="span6 ">
						<div id="a0807Group" class="control-group">
							<label class="control-label" >毕(肄)业日期</label>
							<div class="controls">
								<input type="text" class="span9 m-wrap" name="a0807" number="true" maxlength="128" id="a0807" value=""
									   placeholder="日期格式 例如：2018或201801或20180101"  isDate="true" dateformat="yyyy,yyyymm,yyyymmdd"/>
							</div>
						</div>
					</div>
				</div>

				<div class="row-fluid">
					<div class="span6 ">
						<div id="aXwzsbhGroup" class="control-group">
							<label class="control-label" >学位证书编号</label>
							<div class="controls">
								<input type="text" class="span9 m-wrap" name="aXwzsbh" maxlength="128" id="aXwzsbh" value="" />
							</div>
						</div>
					</div>
					<%--<div class="span6 ">--%>
						<%--<div id="a0229Group" class="control-group">--%>
							<%--<label class="control-label" >学科</label>--%>
							<%--<div class="controls">--%>
								<%--<input type="text" class="span9 m-wrap" name="a0229" maxlength="128" id="a0229" value="" />--%>
							<%--</div>--%>
						<%--</div>--%>
					<%--</div>--%>
				</div>


				<div class="row-fluid">
					<div class="span6 ">
						<div id="aXwzssjGroup" class="control-group">
							<label class="control-label" >学位发证日期</label>
							<div class="controls">
								<input type="text" class="span9 m-wrap" name="aXwzssj" maxlength="128" id="aXwzssj" value=""
									   placeholder="日期格式 例如：2018或201801或20180101"  isDate="true" dateformat="yyyy,yyyymm,yyyymmdd"/>
							</div>
						</div>
					</div>
					<div class="span6 ">
						<div id="a0814Group" class="control-group">
							<label class="control-label" >学校(单位)及专业</label>
							<div class="controls">
                                <input type="text" class="span9 m-wrap" name="a0814" maxlength="128" id="a0814" value="" />
							</div>
						</div>
					</div>
				</div>

				<div class="row-fluid">
					<center>
						<div style="margin:auto;">
                            <button id="submitbutAddXxjl" class="btn green" type="button" style="padding:7px 20px;" >确定</button>
                            <button id="cencalXxjl" class="btn green" type="button" style="padding:7px 20px;" >取消</button>
						</div>
					</center>
				</div>

			</table>

		</form>
	</div>
</div>
<script type="text/javascript" src="${path }/js/common/loading.js"></script>
<script type="text/javascript">
		var addXxjlform = new EstValidate("addXxjlform");
        $(function(){
            $("#submitbutAddXxjl").on("click",function(){
                var bool = addXxjlform.form();
                if(bool){
                    $.ajax({
                        url : "${path}/zzb/gbgl/a08/save",
                        type : "post",
                        data : $('#addXxjlform').serialize(),
                        dataType : "json",
                        headers: {
                            "OWASP_CSRFTOKEN":"${sessionScope.OWASP_CSRFTOKEN}"
                        },
                        success : function(json){
                            showTip("提示","保存成功!",2000);
                            $('#addModal').modal('hide');
                            $('#addDiv').html("");
                            $.ajax({
                                url : "${path }/zzb/gbgl/a08/ajax/xxjl",
                                type : "post",
                                data : {"a01Id":"${a01Id}"},
                                dataType : "html",
                                headers:{
                                    OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
                                },
                                success : function(html){
                                    var view = $("#tab_show");
                                    view.html(html);
                                },
                                error : function(arg1, arg2, arg3){
                                    showTip("提示","现任职务加载失败");
                                }
                            });
                        },
                        error : function(arg1, arg2, arg3){
                            showTip("提示","出错了请联系管理员",2000);
                        }
                    });

                }
            });
        });

        $(function(){
            $("#cencalXxjl").on("click",function(){
                $.ajax({
                    url : "${path }/zzb/gbgl/a08/ajax/xxjl",
                    type : "post",
                    data : {"a01Id":"${a01Id}"},
                    dataType : "html",
                    headers:{
                    OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
                    },
                    success : function(html){
                    var view = $("#tab_show");
                    view.html(html);
                    },
                    error : function(arg1, arg2, arg3){
                    showTip("提示","现任职务加载失败");
                    }
                });
            });
        });



</script>
</body>
</html>
