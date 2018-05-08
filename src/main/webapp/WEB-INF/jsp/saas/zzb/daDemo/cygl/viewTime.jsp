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
<title>档案阅档申请</title>
</head>
<body>


<div class="portlet-body">
	<table class="table table-striped table-bordered table-hover dataTable table-set">
		<thead>

		<TR height=28>

			<th width=140>起始时间</th>
			<th width=140>结束时间</th>
			<th >时长</th>

		</thead>
		<tbody>
		<TR onmouseover="this.className='table_tr_mouse'" onmouseout="this.className=''">
			<TD style="CURSOR: hand; TEXT-ALIGN: center" >2013.08.29 10:03:06 </TD>
			<TD style="TEXT-ALIGN: center" width=40>2013.08.29 10:03:33 </TD>
			<TD >26秒</TD>
		</TR>

		</tbody>
	</table>

</div>
</body>
</html>
