<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/inc/servlet.jsp" %>
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
    <title>工资变动</title>
    <style type="text/css">
        form {
            margin: 0 0 0px;
        }
    </style>
</head>
<body>
<div class="container-fluid" id="a32Table">
            <%-- 表格开始 --%>
            <div class="portlet-title">
                <div class="clearfix fr">

                    <a id="sample_editable_1_new" class="btn green" href="javascript:add()">
                        <i class="icon-plus"></i>增加工资变动
                    </a>
                    <a  class="btn green" href="#">
                        工资变动导入
                    </a>
                    <a  class="btn green" href="javascript:download()">
                        打印工资变动
                    </a>
                </div>

            </div>
            <div class="clearfix">

            </div>
            <div class="portlet-body" >
                <table class="table table-striped table-bordered table-hover dataTable table-set">
                    <thead>

                    <TR height=28>
                        <th width="40">序号</th>
                        <th>工作部门</th>
                        <th width=15%>职务名称</th>
                        <th width=10%>职务工资标准</th>
                        <th width=11%>职务工资额</th>
                        <th width="80px">批准日期</th>
                        <th width=11%>批准机关</th>
                        <th width=15%>批准文号</th>
                        <th width="90">操作</th>
                    </thead>
                    <tbody>
                    <c:forEach items="${pager.datas}" var="vo">
                        <tr style="text-overflow:ellipsis;">
                            <TD><c:out value="${vo.px}"></c:out></TD>
                            <TD><c:out value="${vo.gzbm}"></c:out></TD>
                            <TD ><c:out value="${vo.zwmc}"></c:out></TD>
                            <TD><c:out value="${vo.a3224}"></c:out></TD>
                            <TD ><c:out value="${vo.a3234}"></c:out></TD>
                            <TD><c:out value="${vo.a3207}"></c:out></TD>
                            <TD ><c:out value="${vo.a3204}"></c:out></TD>
                            <TD ><c:out value="${vo.a3211}"></c:out></TD>
                            <td>
                                <a href="javascript:editA32('${vo.id}')" class="">修改</a>|
                                <a href="javascript:deleteA32('${vo.id}')" class="">删除</a>
                            </td>
                        </TR>
                    </c:forEach>
                    </tbody>
                </table>
                <jsp:include page="/WEB-INF/jsp/common/page.jsp">
                    <jsp:param value="${pager.total }" name="total"/>
                    <jsp:param value="${pager.pageCount }" name="endPage"/>
                    <jsp:param value="${pager.pageSize }" name="pageSize"/>
                    <jsp:param value="${pager.pageNum }" name="page"/>
                </jsp:include>
            </div>
        </div>
    <div class="main_right" id="catalogList" >
<%-- 表格结束 --%>
<%-- END PAGE CONTENT--%>
  <script type="text/javascript">
      function pagehref (pageNum ,pageSize){
          <%--window.location.href ="${path}/zzb/app/console/gbmc/a01/list?b01Id=${b01Id}&mcid=${mcid}&pageNum="+pageNum+"&pageSize="+pageSize;--%>
          $.ajax({
              async:false,
              type:"POST",
              url:"${path }/zzb/dzda/a32/ajax/list",
              dataType : "html",
              headers:{
                  "OWASP_CSRFTOKEN":'${sessionScope.OWASP_CSRFTOKEN}'
              },
              data:{
                  'a38Id':"${a38Id}",
                  'pageNum':pageNum,
                  'pageSize':pageSize
              },
              success:function(html){
                  var view = $("#tab_show");
                  view.html(html);
              },
              error : function(){
                  myLoading.hide();
                  showTip("提示","出错了,请检查网络!",2000);
              }
          });

      }

      function editA32(id){
          $.ajax({
              async:false,
              type:"POST",
              url:"${path}/zzb/dzda/a32/ajax/editGzbd",
              dataType : "html",
              headers:{
                  "OWASP_CSRFTOKEN":'${sessionScope.OWASP_CSRFTOKEN}'
              },
              data:{
                  'a38Id':"${a38Id}",
                  'id':id
              },
              success:function(html){
                  $("#a32Table").hide();
                  $("#catalogList").html(html);
              },
              error : function(){
                  myLoading.hide();
                  showTip("提示","出错了,请检查网络!",2000);
              }
          });
      }
      function deleteA32(id){
          console.log(id);
          actionByConfirm1('',"${path}/zzb/dzda/a32/delete/"+id,null,function(json){
              if(json.code == 1){
                  showTip("提示","操作成功");
                  setTimeout(function(){
                      $.ajax({
                          url : "${path }/zzb/dzda/a32/ajax/list",
                          type : "get",
                          data : {"a38Id":"${a38Id}"},
                          dataType : "html",
                          success : function(html){
                              console.log(111);
                              var view = $("#tab_show");
                              view.html(html);
                          },
                          error : function(arg1, arg2, arg3){
                              showTip("提示","工资变动加载失败");
                          }
                      });
                  },1500);

              }else{
                  showTip("提示", json.message, 2000);
              }
          },"删除")
      }
      function add() {
          $.ajax({
              async:false,
              type:"POST",
              url:"${path}/zzb/dzda/a32/ajax/addGzbd",
              dataType : "html",
              headers:{
                  "OWASP_CSRFTOKEN":'${sessionScope.OWASP_CSRFTOKEN}'
              },
              data:{
                   'a38Id':"${a38Id}"
              },
              success:function(html){
                  $("#a32Table").hide();
                  $("#catalogList").html(html);
              },
              error : function(){
                  myLoading.hide();
                  showTip("提示","出错了,请检查网络!",2000);
              }
          });
      }

      function download() {
          window.open("${path}/zzb/dzda/a32/download/${a38Id}");
      }

  </script>
</body>
</html>
