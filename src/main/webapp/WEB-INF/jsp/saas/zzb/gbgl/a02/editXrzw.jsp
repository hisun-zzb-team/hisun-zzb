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

				<span class="hidden-480">添加现任职务</span>

			</div>
			<div class="tools">
				<a href="javascript:location.reload();" class="reload"></a>

			</div>
		</div>
		<form action="${path}/zzb/gbgl/a02/save" class="form-horizontal" id="addZwform" method="post">
			<table  border="0" style="width:100%;" cellPadding="5px">
                <input type="hidden" id="a0200" name="a0200" value="${vo.a0200}"/>
				<div class="row-fluid">
					<div class="span6 ">
						<div id="a0201AGroup" class="control-group">
							<label class="control-label" ><span class="required">*</span>单位名称</label>
							<div class="controls" >
								<Tree:tree id="xx" valueName="a0201A"
										   selectClass="span9 m-wrap" onClick="selectDw"
										   treeUrl="${path}/api/b01/dtjz/tree"
										   token="${sessionScope.OWASP_CSRFTOKEN}" dtjz="true"
										   submitType="post" dataType="json" isSearch="false"
										   checkedByTitle="true" isSelectTree="true" defaultkeys=""
										   defaultvalues="${vo.a0201A}"/>
							</div>
						</div>
					</div>
					<div class="span6 ">
						<div id="a0243Group" class="control-group">
							<label class="control-label" >任职日期</label>
							<div class="controls">
								<input type="text" class="span9 m-wrap" name="a0243" maxlength="128" id="a0243" value="${vo.a0243}"
                                       placeholder="日期格式 例如：2018或201801或20180101"  isDate="true" dateformat="yyyy,yyyymm,yyyymmdd"/>
							</div>
						</div>
					</div>
				</div>

				<div class="row-fluid">
					<div class="span6 ">
						<div id="aRzdwsmGroup" class="control-group">
							<label class="control-label" >单位说明</label>
							<div class="controls">
								<input type="text" class="span9 m-wrap" name="aRzdwsm" maxlength="128" id="aRzdwsm" value="${vo.aRzdwsm}" />
							</div>
						</div>
					</div>
					<div class="span6 ">
						<div id="a0265Group" class="control-group">
							<label class="control-label" >免职日期</label>
                            <div class="controls">
                                <input type="text" class="span9 m-wrap" name="a0265"  required="true" maxlength="128" id="a0265" value="${vo.a0265}"
                                       placeholder="日期格式 例如：2018或201801或20180101"  isDate="true" dateformat="yyyy,yyyymm,yyyymmdd"/>
                            </div>
						</div>
					</div>
				</div>

				<div class="row-fluid">
					<div class="span6 ">
						<div id="a0215AGroup" class="control-group">
							<label class="control-label" >职务</label>
							<div class="controls" >
								<SelectTag:SelectTag id="a0215B" needNullValue="true"
													 valueName="a0215A"
													 defaultkeys="${vo.a0215B}" token="${sessionScope.OWASP_CSRFTOKEN}"
													 defaultvalues="${vo.a0215A}"
													 textClass="span9 m-wrap" radioOrCheckbox="radio"
													 selectUrl="${path}/zzb/jggl/b09/select?typeCode=pxczzw&b01Id=4028839263cea23d0163ceb9840a0001&b09Id=1"/>
							</div>
						</div>
					</div>
					<div class="span6 ">
						<div id="aA0255AGroup" class="control-group">
							<label class="control-label" >任职状态</label>
							<div class="controls">
                                <SelectTag:SelectTag id="a0255" needNullValue="true"
                                                     valueName="aA0255A"
                                                     defaultkeys="${vo.a0255}" token="${sessionScope.OWASP_CSRFTOKEN}"
                                                     defaultvalues="${vo.aA0255A}"
                                                     textClass="span9 m-wrap" radioOrCheckbox="radio"
                                                     selectUrl="${path}/api/dictionary/select?typeCode=ZB14-1994/RZZT"/>
							</div>
						</div>
					</div>
				</div>
				<div class="row-fluid">
					<div class="span6 ">
						<div id="a0217Group" class="control-group">
							<label class="control-label" >职务说明</label>
                            <div class="controls">
                                <input type="text" class="span9 m-wrap" name="a0217"  required="true" maxlength="128" id="a0217" value="${vo.a0217}" />
                            </div>
						</div>
					</div>
					<%--<div class="span6 ">--%>
						<%--<div id="bGllbAGroup" class="control-group">--%>
							<%--<label class="control-label" >退休职务</label>--%>
                            <%--<div class="controls" style="margin-left: 200px">--%>
                                <%--<label class="radio" style="float:left;margin-top: 5px">--%>
                                    <%--<input type="radio" name="bSfjr" value="1" />--%>
                                    <%--是&nbsp;&nbsp;&nbsp;--%>
                                <%--</label>--%>
                                <%--<label class="radio" style="float:left;margin-left:20px;margin-top: 5px">--%>
                                    <%--<input type="radio" name="bSfjr" value="0" checked/>--%>
                                    <%--否--%>
                                <%--</label>--%>
                                <%--<div id="sfjrGdDiv" style="float:left;display:none;margin-top: 7px;">--%>
                                    <%--&nbsp;&nbsp;<a href="javascript:gd()" class="">更多</a>--%>
                                <%--</div>--%>
                            <%--</div>--%>
						<%--</div>--%>
					<%--</div>--%>
				</div>

                <!-- -->

                <div class="row-fluid">
                    <div class="span6 ">
                        <div id="aSfsyGroup" class="control-group">
                            <label class="control-label" >试用期</label>
                            <div class="controls" style="margin-left: 200px">
                                <div style="float:left;margin-top: 5px">
                                    <label class="radio">
                                        <input type="radio" name="aSfsy" onclick="syscdw()" value="1" <c:if test="${vo.aSfsy =='1'}">checked</c:if>/>
                                        是&nbsp;&nbsp;&nbsp;
                                    </label>
                                </div>
                                <div style="float:left;margin-left:20px;margin-top: 5px">
                                    <label class="radio">
                                        <input type="radio" name="aSfsy" value="0" onclick="syscdwClose()" <c:if test="${vo.aSfsy =='0'}">checked</c:if>/>
                                        否
                                    </label>
                                </div>
                                <div id="syscdwDiv" style="float:left;display:<c:if test="${vo.aSfsy =='0'}">none</c:if>">
                                    &nbsp;&nbsp;<input type="text" class="span4 m-wrap" name="aSyq" id="aSyq" value="${vo.aSyq}" />
                                    <SelectTag:SelectTag id="aSyqdw" needNullValue=""
                                                         valueName=""
                                                         defaultkeys="${vo.aSyqdw}" token="${sessionScope.OWASP_CSRFTOKEN}"
                                                         defaultvalues=""
                                                         textClass="span4 m-wrap" radioOrCheckbox="radio"
                                                         selectUrl="${path}/api/dictionary/select?typeCode=2018/SYSCDW"/>
                                </div>
                            </div>

                        </div>
                    </div>
                    <div class="span6 ">
                        <div id="a0209Group" class="control-group">
                            <label class="control-label" >任职类别</label>
                            <div class="controls">
                                <SelectTag:SelectTag id="" needNullValue="true"
                                                     valueName="a0209"
                                                     defaultkeys="" token="${sessionScope.OWASP_CSRFTOKEN}"
                                                     defaultvalues="${vo.a0209}"
                                                     textClass="span9 m-wrap" radioOrCheckbox="radio"
                                                     selectUrl="${path}/zzb/jggl/b09/select?typeCode=pxczzw&b01Id=${b01Id}&b09Id=1"/>
                            </div>
                        </div>
                    </div>

                </div>

				<div class="row-fluid">
					<div class="span6 ">
						<div id="a0275Group" class="control-group">
							<label class="control-label" >计入职数</label>
                            <div style="float:left;margin-top: 5px;margin-left: 40px;">
                                <label class="radio">
                                    <input type="radio" name="a0275" value="1" <c:if test="${vo.a0275 =='1'}">checked</c:if>/>
                                    是&nbsp;&nbsp;&nbsp;
                                </label>
                            </div>
                            <div style="float:left;margin-left:20px;margin-top: 5px">
                                <label class="radio">
                                    <input type="radio" name="a0275" value="0" <c:if test="${vo.a0275 =='0'}">checked</c:if>/>
                                    否
                                </label>
                            </div>
						</div>
					</div>
					<div class="span6 ">
						<div id="a0245Group" class="control-group">
							<label class="control-label" >任职文号</label>
							<div class="controls">
								<input type="text" class="span9 m-wrap" name="a0245" number="true" maxlength="128" id="a0245" value="${vo.a0245}" />
							</div>
						</div>
					</div>
				</div>

				<div class="row-fluid">
					<div class="span6 ">
						<div id="aZbDwzwmcGroup" class="control-group">
							<label class="control-label" >占用职位</label>
							<div class="controls">
                                <input type="text" class="span8 m-wrap" name="aZbDwzwmc" number="true" maxlength="128" id="aZbDwzwmc" value="${vo.aZbDwzwmc}" />
							</div>
						</div>
					</div>
					<div class="span6 ">
						<div id="aZbzwbzlbb" class="control-group">
							<label class="control-label" >任职性质</label>
							<div class="controls">
                                <SelectTag:SelectTag id="aZbzwbzlba" needNullValue="true"
                                                     valueName="aZbzwbzlbb"
                                                     defaultkeys="${vo.aZbzwbzlba}" token="${sessionScope.OWASP_CSRFTOKEN}"
                                                     defaultvalues="${vo.aZbzwbzlbb}"
                                                     textClass="span9 m-wrap" radioOrCheckbox="radio"
                                                     selectUrl="${path}/zzb/jggl/b09/select?typeCode=pxczzw&b01Id=${b01Id}&b09Id=1"/>
							</div>
						</div>
					</div>
				</div>

				<div class="row-fluid">
					<div class="span6 ">
						<div id="bSfzbGroup" class="control-group">
							<label class="control-label" >占编制</label>
                            <div class="controls" style="margin-left: 200px">
                                <div style="float:left;margin-top: 5px">
                                    <label class="radio">
                                        <input type="radio" name="bSfzb" value="1" <c:if test="${vo.bSfzb =='1'}">checked</c:if>/>
                                        是&nbsp;&nbsp;&nbsp;
                                    </label>
                                </div>
                                <div style="float:left;margin-left:20px;margin-top: 5px">
                                    <label class="radio">
                                        <input type="radio" name="bSfzb" value="0" <c:if test="${vo.bSfzb =='0'}">checked</c:if>/>
                                        否
                                    </label>
                                </div>
                            </div>
						</div>
					</div>
					<div class="span6 ">
						<div id="a0229Group" class="control-group">
							<label class="control-label" >分管工作</label>
							<div class="controls">
								<input type="text" class="span9 m-wrap" name="a0229" maxlength="128" id="a0229" value="${vo.a0229}" />
							</div>
						</div>
					</div>
				</div>

				<div class="row-fluid">
					<div class="span6 ">
						<div id="bSfbzcyGroup" class="control-group">
							<label class="control-label" >名册显示</label>
							<div class="controls" style="margin-left: 200px">
								<label class="radio">
									<input type="radio" name="bSfbzcy" value="1" <c:if test="${vo.bSfbzcy =='1'}">checked</c:if>/>
									全部职务&nbsp;&nbsp;&nbsp;
								</label>
								<label class="radio">
									<input type="radio" name="bSfbzcy" value="0" <c:if test="${vo.bSfbzcy =='0'}">checked</c:if>/>
									当前单位职务
								</label>
							</div>
						</div>
					</div>
					<div class="span6 ">
						<div id="a0267Group" class="control-group">
							<label class="control-label" >免职文号</label>
							<div class="controls">
                                <input type="text" class="span9 m-wrap" name="a0267" number="true" maxlength="128" id="a0267" value="${vo.a0267}" />
							</div>
						</div>
					</div>
				</div>

				<div class="row-fluid">
					<div class="span6 ">
						<div id="a0225Group" class="control-group">
							<label class="control-label" >职务内排序</label>
							<div class="controls" style="margin-left: 180px">
                                <input type="text" class="span9 m-wrap" name="a0225" number="true" maxlength="128" id="a0225" value="${vo.a0225}" />
							</div>
						</div>
					</div>
					<div class="span6 ">
						<div id="aA0271AGroup" class="control-group">
                            <label class="control-label" >免职原因</label>
							<div class="controls">
								<SelectTag:SelectTag id="a0271" needNullValue="true"
													 valueName="aA0271A"
													 defaultkeys="${vo.a0271}" token="${sessionScope.OWASP_CSRFTOKEN}"
													 defaultvalues="${vo.aA0271A}"
													 textClass="span9 m-wrap" radioOrCheckbox="radio"
													 selectUrl="${path}/api/dictionary/select?typeCode=ZB16-2006/MZYY"/>
							</div>
						</div>
					</div>
				</div>


				<div class="row-fluid">
					<center>
						<div style="margin:auto;">
                            <button id="submitbutAddXrzw" class="btn green" type="button" style="padding:7px 20px;" >确定</button>
                            <button id="cencalXrzw" class="btn green" type="button" style="padding:7px 20px;" >取消</button>
						</div>
					</center>
				</div>

			</table>

		</form>
	</div>
</div>
<script type="text/javascript" src="${path }/js/common/loading.js"></script>
<script type="text/javascript">
		function syscdw(){
			$("#syscdwDiv").css("display","block");
		}
		function syscdwClose(){
			$("#syscdwDiv").css("display","none");
		}

        aSyq.addEventListener("input", function(event) {
            var val = this.value;
            var num = removeNotNum(val);
            $("#aSyq").val(num);
        })

        function isRealNum(val){
            // isNaN()函数 把空串 空格 以及NUll 按照0来处理 所以先去除
            if(val === "" || val ==null){
                return false;
            }
            if(!isNaN(val)){
                return true;
            }else{
                return false;
            }
        }

        function removeNotNum(val){
            var str = val.split("");
            var num = "";
            for(var i=0;i<val.length;i++){
                if(isRealNum(str[i])){
                    num+=str[i];
                    if(num.length>=128){
                        break;
                    }
                }
            }
            return num;
        }

        function selectDw (event, treeId, treeNode){
            $("#aRzdwsm").val(treeNode.name);//赋值
        }

		var addZwform = new EstValidate("addZwform");
        $(function(){
            $("#submitbutAddXrzw").on("click",function(){
                var bool = addZwform.form();
                if(bool){
                    $.ajax({
                        url : "${path}/zzb/gbgl/a02/update",
                        type : "post",
                        data : $('#addZwform').serialize(),
                        dataType : "json",
                        headers: {
                            "OWASP_CSRFTOKEN":"${sessionScope.OWASP_CSRFTOKEN}"
                        },
                        success : function(json){
                            showTip("提示","保存成功!",2000);
                            $('#addModal').modal('hide');
                            $('#addDiv').html("");
                            $.ajax({
                                url : "${path }/zzb/gbgl/a02/ajax/xrzw",
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
                                    showTip("提示","职务管理加载失败");
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
            $("#cencalXrzw").on("click",function(){
                $.ajax({
                    url : "${path }/zzb/gbgl/a02/ajax/xrzw",
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
                    showTip("提示","职务管理加载失败");
                    }
                });
            });
        });



</script>
</body>
</html>
