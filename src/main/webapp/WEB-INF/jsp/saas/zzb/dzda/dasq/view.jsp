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
    <div class="portlet-title">
        <div class="portlet-title" style="text-align: right">
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
                           value="${vo.eApplyE01Z8Vo.applyUserName }"/>
                </div>
            </div>

            <div id="e01Z807NameGroup" class="control-group">
                <label class="control-label">查阅人</label>
                <div class="controls">
                    <input type="text" class="span8 m-wrap" name="e01Z807Name" readonly maxlength="128" id="e01Z807Name"
                           value="${vo.eApplyE01Z8Vo.e01Z807Name }"/>
                </div>
            </div>

            <div class="control-group" id="e01Z824AGroup">

                <label class="control-label">查阅人单位及职位</label>
                <div class="controls">
                    <input size="16" type="text"  class="span8 m-wrap" value="${vo.eApplyE01Z8Vo.e01Z824A}" readonly
                           id="e01Z824A" name="e01Z824A" >
                </div>
            </div>

            <div id="phoneGroup" class="control-group">
                <label class="control-label">查阅人联系电话</label>
                <div class="controls">
                    <input type="text" class="span8 m-wrap" name="phone" maxlength="128" id="phone" readonly
                           value="${vo.eApplyE01Z8Vo.phone }"/>
                </div>
            </div>

            <div id="createDateGroup" class="control-group">
                <label class="control-label">申请时间</label>
                <div class="controls">
                    <input type="text" class="span8 m-wrap" name="createDate" readonly maxlength="128" id="createDate"
                           value="${vo.eApplyE01Z8Vo.sqsj }"/>
                </div>
            </div>

            <div id="a0101Group" class="control-group">
                <label class="control-label"><span class="required">*</span>查阅档案对象姓名</label>
                <div class="controls">
                    <input type="text" class="span8 m-wrap" required name="a0101" maxlength="128" id="a0101"
                           value="${vo.eApplyE01Z8Vo.a0101 }" readonly/>
                </div>
            </div>

            <div id="sqcydazwGroup" class="control-group">
                <label class="control-label">查阅档案对象单位及职务</label>
                <div class="controls">
                    <input type="text" class="span8 m-wrap" name="sqcydazw" readonly maxlength="128" id="sqcydazw"
                           value="${vo.eApplyE01Z8Vo.sqcydazw }"/>
                </div>
            </div>

            <div id="readContentGroup" class="control-group">
                <label class="control-label">查阅档案内容</label>
                <div class="controls">
                    <input type="text" class="span8 m-wrap" name="readContent" maxlength="128" id="readContent" readonly
                           value="${vo.eApplyE01Z8Vo.readContent }"/>
                </div>
            </div>

            <div id="readTimeGroup" class="control-group">
                <label class="control-label">申请查阅时长</label>
                <div class="controls">
                    <input type="text" class="span8 m-wrap" name="readTime" maxlength="128" id="readTime" readonly
                           value="${vo.eApplyE01Z8Vo.readTime }"/>
                </div>
            </div>


            <div id="applyRemarkGroup" class="control-group">
                <label class="control-label">查阅档案原因</label>
                <div class="controls">
                        <textarea class="span8 m-wrap" name="applyRemark" maxlength="128" id="applyRemark" readonly
                                  style="resize: none;">${vo.eApplyE01Z8Vo.applyRemark }</textarea>
                </div>
            </div>

            <div id="applyFileNameGroup" class="control-group">
                <label class="control-label">材料附件</label>
                <div class="controls">
                    <a class="btn blue" href="javascript:downloadFile('${vo.eApplyE01Z8Vo.id}')"><i
                            class="icon-circle-arrow-down"></i>${vo.eApplyE01Z8Vo.applyFileName }</a>
                </div>
            </div>
        </form>
    </dl>
    <dl class="dlattrbute">
        <dt><a href="###">授权信息</a></dt>
        <form action="" class="form-horizontal" id="form1" method="post">
            <div id="sqrGroup" class="control-group">
                <label class="control-label">授权人</label>
                <div class="controls">
                    <input type="text" class="span8 m-wrap" name="sqr" maxlength="128" id="sqr" readonly
                           value="${vo.sqr }"/>
                </div>
            </div>
            <div id="sqztGroup" class="control-group">
                <label class="control-label">同意申请</label>
                <div class="controls">
                    <label class="radio">
                        <input type="radio" name="sqzt" value="1" <c:if test="${vo.sqzt==1}"> checked</c:if>/>
                        是
                    </label>
                    <label class="radio">
                        <input type="radio" name="sqzt" value="2" <c:if test="${vo.sqzt==2}"> checked</c:if>/>
                        否
                    </label>
                </div>
            </div>
            <div id="tysqId" <c:if test="${vo.sqzt==2}">style="display: none" </c:if>>
                <div id="sqclfwGroup" class="control-group">
                    <label class="control-label">授权查阅档案内容</label>
                    <div class="controls">
                        <label class="radio">
                            <input type="radio" name="sqclfw" value="0" <c:if test="${vo.sqclfw==0}">checked</c:if>/>
                            全部
                        </label>
                        <label class="radio">
                            <input type="radio" name="sqclfw" value="1" <c:if test="${vo.sqclfw==1}">checked</c:if>/>
                            部分
                        </label>
                    </div>
                </div>

                <div id="sqcymlIdsGroup" class="control-group"  <c:if test="${vo.sqclfw==0}">style="display: none" </c:if>>
                    <label class="control-label">指定查阅目录</label>
                    <div class="controls"> <%--${path}/zzb/dzda/mlcl/tpcl/ajax/tree/${a38Id}--%>
                        <input type="text" class="span8 m-wrap" value="${vo.sqcyml}" readonly
                        />
                    </div>
                </div>
                <div id="sfyxxzGroup" class="control-group">
                    <label class="control-label">允许下载</label>
                    <div class="controls">
                        <label class="radio">
                            <input type="radio" name="sfyxxz" value="1" <c:if test="${vo.sfyxxz==1}">checked</c:if>/>
                            是
                        </label>
                        <label class="radio">
                            <input type="radio" name="sfyxxz" value="0" <c:if test="${vo.sfyxxz==0}">checked</c:if>/>
                            否
                        </label>
                    </div>
                </div>

                <div id="sfyxdyGroup" class="control-group">
                    <label class="control-label">允许打印</label>
                    <div class="controls">
                        <label class="radio">
                            <input type="radio" name="sfyxdy" value="1" <c:if test="${vo.sfyxdy==1}">checked</c:if>/>
                            是
                        </label>
                        <label class="radio">
                            <input type="radio" name="sfyxdy" value="0" <c:if test="${vo.sfyxdy==0}">checked</c:if>/>
                            否
                        </label>
                    </div>
                </div>
            </div>
            <div id="sqbzGroup" class="control-group">
                <label class="control-label">授权备注</label>
                <div class="controls">
                        <textarea class="span8 m-wrap" name="sqbz" maxlength="128" id="sqbz" readonly
                                  style="resize: none;">${vo.sqbz}</textarea>
                </div>
            </div>
            <div class="control-group">
                <div class="controls mt10" style="margin-left: 270px">
                    <button type="button" class="btn btn-default" data-dismiss="modal" onclick="cancel()"><i class='icon-remove-sign'></i> 关闭</button>
                </div>
            </div>
        </form>
    </dl>

</div>

<script type="text/javascript">
    $(function () {
        var applyFileName = "${entity.applyFileName }";
        if (applyFileName == "" || applyFileName == null) {
            $("#applyFileNameGroup").hide();
        }
    })
    function cancel() {
        window.location.href = "${path}/zzb/dzda/cyshouquan/list?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}";
    }
</script>