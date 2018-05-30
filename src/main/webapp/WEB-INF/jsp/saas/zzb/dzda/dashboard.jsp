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

    <!-- BEGIN PAGE LEVEL STYLES -->
    <title>干部档案管理系统</title>

    <!-- END PAGE LEVEL STYLES -->
    <style type="text/css">
        .CadreSuper{ padding:0 30px;}
        .CadreSuper h2{ text-align:center; font-weight:bold; font-size:24px;}
        .CadreSjx{ font-size:14px; color:#555; line-height:220%;}
        .CadreSjx a.term{ color:#0376d8; font-size:16px; font-weight:bold; text-decoration: underline !important; margin:0 5px;}

        .ulCadreSuperlist{ margin-top:50px; overflow:hidden;}
        .ulCadreSuperlist li{ width:20%; float:left; text-align:center; font-size:16px;}
        .ulCadreSuperlist li img{ width:102px; height:102px; margin-top:51px;}
        .ulCadreSuperlist li p{ height:46px; line-height:46px;}
        .ulCadreSuperlist li:hover a{ color:#0376d8;}


    </style>
</head>
<body>

        <!--干部监督信息系统-->
        <div class="CadreSuper">
            <h2>干部档案管理系统</h2>
            <div class="CadreSjx">
                <br><br>
                <p>干部档案管理系统要实现了档案管理工作信息化，档案资料图片化，档案查阅无纸化和查阅远程化。</p>
                <p> 通过该系统建成干部电子档案库，同时系统还将档案资料与干部信息相结合，实现查询干部基本信息能和电子档案无缝关联。提供档案借阅、查阅过程的网上办理功能。</p>
                <p>在完成日常工作的过程中，自动形成各种档案信息名册（目录表、职务变动表等）和档案工作名册。</p>
                <p>其功能包括档案登记、档案审核、欠缺材料管理、目录维护与打印、查阅和授权管理、档案转递与接收、在线阅档等功能</p>
            </div>
            <ul class="ulCadreSuperlist">
                <li>
                    <a href="${path}/zzb/dzda/dacx/bdwdalist?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}">
                        <img src="${path}/images/templateImage/dagl05.png">
                        <p>档案查询</p>
                    </a>
                </li>
                <li>
                    <a href="${path}/zzb/dzda/a38/list?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}">
                        <img src="${path}/images/templateImage/dagl01.png">
                        <p>档案管理</p>
                    </a>
                </li>
                <li>
                    <a href="${path}/zzb/dzda/a38/shList?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}">
                        <img src="${path}/images/templateImage/dagl02.png">
                        <p>档案审核</p>
                    </a>
                </li>
                <li>
                    <a href="${path}/zzb/dzda/dajs/list?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}">
                        <img src="${path}/images/templateImage/dagl03.png">
                        <p>转入转出</p>
                    </a>
                </li>
                <li>
                    <a href="${path}/zzb/dzda/cysq/list?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}">
                        <img src="${path}/images/templateImage/dagl04.png">
                        <p>档案查阅</p>
                    </a>
                </li>

            </ul>

        </div>
        <!--干部监督信息系统 END-->



<!-- BEGIN PAGE LEVEL PLUGINS -->
<script src="${path}/js/jquery-1.10.1.min.js" type="text/javascript"></script>
<script src="${path}/js/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
<!-- IMPORTANT! Load jquery-ui-1.10.1.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
<script src="${path}/js/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script>
<script src="${path}/js/bootstrap.min.js" type="text/javascript"></script>

<script src="${path}/js/jquery.slimscroll.min.js" type="text/javascript"></script>
<script src="${path}/js/jquery.blockui.min.js" type="text/javascript"></script>
<script src="${path}/js/jquery.cookie.min.js" type="text/javascript"></script>
<script src="${path}/js/jquery.uniform.min.js" type="text/javascript" ></script>
<!-- END CORE PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${path}/js/app.js"></script>
<!-- END PAGE LEVEL SCRIPTS -->
<!--echarts图表-->
<script src="${path}/js/echarts3/echarts.min.js" type="text/javascript" ></script>
<!--数字滚动-->
<script type="text/javascript" src="${path}/js/countUp.min.js"></script>
<script type="text/javascript">
    jQuery(document).ready(function() {
        // initiate layout and plugins
        App.init();
    });
</script>
</body>
</html>