<form action="" class="form-horizontal" id="a01Form" method="post">
    <input type="hidden" name="a1700" value="${vo.a1700 }"/>
    <dl class="dlattrbute">
        <dd>
            <div class="row-fluid">
                <div class="span6 ">
                    <div id="aQssjGroup" class="control-group">
                        <label class="control-label"><span class="Required">*</span>起始日期</label>
                        <div class="controls">
                            <input type="text" class="span10 m-wrap" name="aQssj" id="aQssj"
                                   placeholder="日期格式 例如：2018或201801或20180101" isDate="true" required
                                   dateformat="yyyy,yyyymm,yyyymmdd"
                                   maxlength="128" value="${vo.aQssj}"/>
                        </div>
                    </div>
                </div>
                <div class="span6 ">
                    <div id="aJzsjGroup" class="control-group">
                        <label class="control-label">截止日期</label>
                        <div class="controls">
                            <input type="text" class="span10 m-wrap" name="aJzsj" id="aJzsj"
                                   placeholder="日期格式 例如：2018或201801或20180101" isDate="true"
                                   dateformat="yyyy,yyyymm,yyyymmdd"
                                   maxlength="128" value="${vo.aJzsj}"/>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row-fluid">
                <div class="span6 ">
                    <div id="aDwzwGroup" class="control-group">
                        <label class="control-label"><span class="Required">*</span>所在单位及职务</label>
                        <div class="controls">
                            <input type="text" class="span10 m-wrap" name="aDwzw" id="aDwzw"
                                   required
                                   maxlength="128" value="${vo.aDwzw}"/>
                        </div>
                    </div>
                </div>
                <div class="span6 ">
                    <div id="aZwjbbGroup" class="control-group">
                        <label class="control-label">行政级别</label>
                        <div class="controls">
                            <Tree:tree id="aZwjbb" valueName="aZwjba"
                                       selectClass="span10 m-wrap"
                                       treeUrl="${path}/api/dictionary/tree?typeCode=ZB09-2006/ZWJB"
                                       token="${sessionScope.OWASP_CSRFTOKEN}"
                                       submitType="get" dataType="json" isSearch="false"
                                       checkedByTitle="true" isSelectTree="true"
                                       defaultkeys="${vo.aZwjbb}"
                                       defaultvalues="${vo.aZwjba}"/>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row-fluid">
                <div class="span6 ">
                    <div id="aJlmsGroup" class="control-group">
                        <label class="control-label">工作内容</label>
                        <div class="controls">
                            <input type="text" class="span10 m-wrap" name="aJlms" id="aJlms"
                                   maxlength="128" value="${vo.aJlms}"/>
                        </div>
                    </div>
                </div>
                <div class="span6 ">
                    <div id="aSfdqdwGroup" class="control-group">
                        <label class="control-label">是否当前单位</label>
                        <div class="controls">
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <label class="radio">
                                <input type="radio" name="aSfdqdw" value="1"
                                       <c:if test="${vo.aSfdqdw =='1'}">checked</c:if>/>
                                是&nbsp;&nbsp;&nbsp;
                            </label>
                            <label class="radio">
                                <input type="radio" name="aSfdqdw" value="0"
                                       <c:if test="${vo.aSfdqdw =='0' || empty vo.aSfdqdw}">checked</c:if>/>
                                否
                            </label>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row-fluid">
                <div class="span10 ">
                    <div id="aBzGroup" class="control-group">
                        <label class="control-label">备注</label>
                        <div class="controls">
                                            <textarea id="remark" name="remark" class="span12 m-wrap" maxlength="512"
                                                      value="${vo.aBz}"
                                                      rows="2" style="resize: none;">${vo.aBz}</textarea>
                        </div>
                    </div>
                </div>
            </div>
            <div>
                <center>
                    <div style="margin:auto;">
                        <button class="btn green" type="button" onclick="saveOrUpdate()">确定</button>
                        <a class="btn" href="javascript:cencal()"><i class="icon-remove-sign"></i>
                            取消</a>
                    </div>
                </center>
            </div>
        </dd>
    </dl>
</form>