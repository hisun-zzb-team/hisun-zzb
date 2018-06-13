<table class="table table-striped table-bordered table-hover dataTable table-set">
    <thead>
    <tr>
        <th width="90px">起始日期</th>
        <th width="60px">截止日期</th>
        <th width="80px">所在单位及职务</th>
        <th width="80px">行政级别</th>
        <th width="80px">工作内容</th>
        <th width="80px">备注</th>
        <th width="80px">当前单位</th>
        <th width="80px">操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${pager.datas}" var="vo">
        <tr style="text-overflow:ellipsis;">
            <td><c:out value="${vo.aQssj}"></c:out></td>
            <td><c:out value="${vo.aJzsj}"></c:out></td>
            <td><c:out value="${vo.aDwzw}"></c:out></td>
            <td><c:out value="${vo.aZwjba}"></c:out></td>
            <td><c:out value="${vo.aJlms}"></c:out></td>
            <td><c:out value="${vo.aBz}"></c:out></td>
            <td><c:if test="${vo.aSfdqdw ==1}">是</c:if><c:if
                    test="${vo.aSfdqdw ==0}">否</c:if></td>
            <td>
                <a href="javascript:edit('${vo.a1700}')" class="">修改</a>|
                <a href="javascript:deleteA17('${vo.a0700}')" class="">删除</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<jsp:include page="/WEB-INF/jsp/common/page.jsp">
    <jsp:param value="${pager.total }" name="total"/>
    <jsp:param value="${pager.pageCount }" name="endPage"/>
    <jsp:param value="${pager.pageSize }" name="pageSize"/>
    <jsp:param value="${pager.pageNum }" name="page"/>
</jsp:include>