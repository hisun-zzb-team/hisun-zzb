<dl class="dlattrbute">
    <dd>
        <div class="row-fluid">
            <div class="span6 ">
                <div id="b0101Group" class="control-group">
                    <label class="control-label"><span class="Required">*</span>机构名称</label>
                    <div class="controls">
                        <input type="text" class="span10 m-wrap" id="b0101" name="b0101"
                               maxlength="128" value="${vo.b0101}" required/>
                    </div>
                </div>
            </div>
        </div>
        <div class="row-fluid">
            <div class="span6 ">
                <div id="b0104Group" class="control-group">
                    <label class="control-label">机构简称</label>
                    <div class="controls">
                        <input type="text" class="span10 m-wrap" name="b0104" id="b0104"
                               maxlength="128" value="${vo.b0104}" onblur="checkSmxh(this)"/>
                    </div>
                </div>
            </div>
            <div class="span6 ">
                <div id="b0131Group" class="control-group">
                    <label class="control-label">机构性质</label>
                    <div class="controls">
                        <Tree:tree id="b0131" valueName="b0131A"
                                   selectClass="span10 m-wrap"
                                   treeUrl="${path}/api/dictionary/tree?typeCode=ZB04-2006/DWXZ"
                                   token="${sessionScope.OWASP_CSRFTOKEN}"
                                   submitType="get" dataType="json" isSearch="false"
                                   checkedByTitle="true" isSelectTree="true"
                                   defaultkeys="${vo.b0131}"
                                   defaultvalues="${vo.b0131A}"/>
                    </div>
                </div>
            </div>

        </div>
        <div class="row-fluid">
            <div class="span6 ">
                <div id="b0127Group" class="control-group">
                    <label class="control-label">机构级别</label>
                    <div class="controls">
                        <SelectTag:SelectTag id="b0127" needNullValue="true"
                                             valueName="b0127A"
                                             defaultkeys="${vo.b0127}" token="${sessionScope.OWASP_CSRFTOKEN}"
                                             defaultvalues="${vo.b0127A}"
                                             textClass="span10 m-wrap" radioOrCheckbox="radio"
                                             selectUrl="${path}/api/dictionary/select?typeCode=ZB03-1994/DWJB"/>
                    </div>
                </div>
            </div>
            <div class="span6 ">
                <div id="bGllbBGroup" class="control-group">
                    <label class="control-label"><span class="Required">*</span>机构管理类别</label>
                    <div class="controls">
                        <SelectTag:SelectTag id="bGllbB" needNullValue="true"
                                             valueName="bGllbA"
                                             defaultkeys="${vo.bGllbB}" token="${sessionScope.OWASP_CSRFTOKEN}"
                                             defaultvalues="${vo.bGllbA}"
                                             textClass="span10 m-wrap" radioOrCheckbox="radio"
                                             selectUrl="${path}/api/dictionary/select?typeCode=2018-JGGLLB"/>
                    </div>
                </div>
            </div>

        </div>

        <div class="row-fluid">
            <div class="span6 ">
                <div id="b0151BGroup" class="control-group">
                    <label class="control-label">主管部门</label>
                    <div class="controls">
                        <Tree:tree id="b0151B" valueName="b0151A"
                                   selectClass="span10 m-wrap"
                                   treeUrl="${path}/api/dictionary/tree?typeCode=ZB02-2006/JGMC"
                                   token="${sessionScope.OWASP_CSRFTOKEN}"
                                   submitType="get" dataType="json" isSearch="false"
                                   checkedByTitle="true" isSelectTree="true" defaultkeys="${vo.b0151B}"
                                   defaultvalues="${vo.b0151A}"/>
                    </div>
                </div>
            </div>
            <div class="span6 ">
                <div id="b0154BGroup" class="control-group">
                    <label class="control-label">协管部门</label>
                    <div class="controls">
                        <Tree:tree id="b0154B" valueName="b0154A"
                                   selectClass="span10 m-wrap"
                                   treeUrl="${path}/api/dictionary/tree?typeCode=ZB02-2006/JGMC"
                                   token="${sessionScope.OWASP_CSRFTOKEN}"
                                   submitType="get" dataType="json" isSearch="false"
                                   checkedByTitle="true" isSelectTree="true" defaultkeys="${vo.b0154B}"
                                   defaultvalues="${vo.b0154A}"/>
                    </div>
                </div>
            </div>

        </div>
        <div class="row-fluid">
            <div class="span6 ">
                <div id="bSjjgGroup" class="control-group">
                    <label class="control-label"><span class="Required">*</span>所属机构</label>
                    <div class="controls">
                        <Tree:tree id="parentId" valueName="bSjjgContent" selectClass="span10 m-wrap"
                                   treeUrl="${path}/zzb/jggl/b01Api/load/tree"
                                   token="${sessionScope.OWASP_CSRFTOKEN}"
                                   submitType="get" dataType="json" isSearch="false" required=""
                                   checkedByTitle="true" isSelectTree="true" defaultkeys="${vo.b0151A}"
                                   defaultvalues="${bSjjg}"/>
                    </div>
                </div>
            </div>
            <div class="span6 ">
                <div id="bPxGroup" class="control-group">
                    <label class="control-label"><span class="Required">*</span>排序</label>
                    <div class="controls">
                        <input type="text" class="span10 m-wrap" id="bPx" name="bPx" maxlength="64"
                               value="<c:if test="${empty vo}">${sort}</c:if><c:if test="${!empty vo}">${vo.bPx}</c:if>" number="true" required/>
                    </div>
                </div>
            </div>
        </div>
        <div class="row-fluid">
            <div class="span6 ">
                <div id="bDwztBGroup" class="control-group">
                    <label class="control-label">机构状态</label>
                    <div class="controls">
                        <Tree:tree id="bDwztB" valueName="bDwztAa"
                                   selectClass="span10 m-wrap"
                                   treeUrl="${path}/api/dictionary/tree?typeCode=2018-JGZT"
                                   token="${sessionScope.OWASP_CSRFTOKEN}"
                                   submitType="get" dataType="json" isSearch="false"
                                   checkedByTitle="true" isSelectTree="true" defaultkeys="${vo.bDwztB}"
                                   defaultvalues="${vo.bDwztA}"/>
                    </div>
                </div>
            </div>
            <div class="span6 ">
                <div id="bSflsGroup" class="control-group">
                    <label class="control-label">临时机构</label>
                    <div class="controls">
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <label class="radio">
                            <input type="radio" name="bSfls" value="1"  <c:if test="${vo.bSfls =='1'}">checked</c:if>/>
                            是&nbsp;&nbsp;&nbsp;
                        </label>
                        <label class="radio">
                            <input type="radio" name="bSfls" value="0" <c:if test="${vo.bSfls =='0' || isAdd =='add'}">checked</c:if>/>
                            否
                        </label>
                    </div>
                </div>
            </div>
        </div>
        <div class="row-fluid">
            <div class="span6 ">
                <div id="b0164Group" class="control-group">
                    <label class="control-label">成立时间</label>
                    <div class="controls">
                        <input type="text" class="span10 m-wrap"
                               placeholder="日期格式 例如：2018或201801或20180101" isDate="true"
                               dateformat="yyyy,yyyymm,yyyymmdd"
                               value="${vo.b0164 }" name="b0164"
                               id="b0164"/>
                    </div>
                </div>
            </div>
            <div class="span6 ">
                <div id="b0167Group" class="control-group">
                    <label class="control-label">成立批准文号</label>
                    <div class="controls">
                        <input type="text" class="span10 m-wrap" name="b0167" id="b0167"
                               maxlength="128" value="${vo.b0167 }"/>
                    </div>
                </div>
            </div>

        </div>
        <div class="row-fluid">
            <div class="span6 ">
                <div id="b0181Group" class="control-group">
                    <label class="control-label">撤销批准日期</label>
                    <div class="controls">
                        <input type="text" class="span10 m-wrap"
                               placeholder="日期格式 例如：2018或201801或20180101" isDate="true"
                               dateformat="yyyy,yyyymm,yyyymmdd"
                               value="${vo.b0181 }" name="b0181"
                               id="b0181"/>
                    </div>
                </div>
            </div>
            <div class="span6 ">
                <div id="b0187aGroup" class="control-group">
                    <label class="control-label">撤销批准机关</label>
                    <div class="controls">
                        <Tree:tree id="b0187a" valueName="b0187aBContent"
                                   selectClass="span10 m-wrap"
                                   treeUrl="${path}/api/dictionary/tree?typeCode=ZB02-2006/JGMC"
                                   token="${sessionScope.OWASP_CSRFTOKEN}"
                                   submitType="get" dataType="json" isSearch="false"
                                   checkedByTitle="true" isSelectTree="true" defaultkeys="${vo.b0187a}"
                                   defaultvalues="${vo.b0187a}"/>
                    </div>
                </div>
            </div>

        </div>
        <div class="row-fluid">
            <div class="span6 ">
                <div id="b0184Group" class="control-group">
                    <label class="control-label">撤销批准文号</label>
                    <div class="controls">
                        <input type="text" class="span10 m-wrap" name="b0184" id="b0184"
                               maxlength="128" value="${vo.b0184}"/>
                    </div>
                </div>
            </div>
            <div class="span6 ">
                <div id="bSfgwytjGroup" class="control-group">
                    <label class="control-label">公务员管理</label>
                    <div class="controls" >
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <label class="radio">
                            <input type="radio" name="bSfgwytj" value="-1"/>
                            是&nbsp;&nbsp;&nbsp;
                        </label>
                        <label class="radio">
                            <input type="radio" name="bSfgwytj" value="0" checked/>
                            否
                        </label>
                    </div>
                </div>
            </div>
        </div>
        <div class="row-fluid">
            <div class="span6 ">
                <div id="remarkGroup" class="control-group">
                    <label class="control-label">备注</label>
                    <div class="controls">
                                            <textarea id="remark" name="remark" class="span14 m-wrap" maxlength="512" value="${vo.remark}"
                                                      rows="2" style="resize: none;"></textarea>
                    </div>
                </div>
            </div>
        </div>
    </dd>
</dl>