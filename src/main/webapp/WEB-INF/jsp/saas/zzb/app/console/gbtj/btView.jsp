<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<div class="control-group mybutton-group" style="text-align: right;margin-top: 0px">
	<button type="button" class="btn btn-default" data-dismiss="modal"><i class='icon-remove-sign'></i> 关闭</button>
</div>
  <div    style="border:1px solid #CDCDCD;width: 100%;   margin: 0 auto 30px auto; " >
 	<div id="pie" class="main" style='width:650px;height:450px;float:center;margin:0 auto;padding-top:0;'></div>
 </div>
 <%--<div    style="border:1px solid #CDCDCD;width: 100%; margin: auto;">--%>
 	<%--<div id="histogram" class="main" style='width:90%;height:350px;float:center;margin:0 auto;padding-top:0;'></div>--%>
 <%--</div>--%>


<script type="text/javascript" src="${path }/js/echarts3/echarts.min.js"></script>
	<script type="text/javascript">

	(function(){
		var pie = '${pieData}';
		var text = '${text}';
		var name = '${name}';
		option1 = {
		    title : {
		        text: '',
		        subtext: '',
		        x:'center'
		    },
		    tooltip : {
		        trigger: 'item',
		        formatter: "{a} <br/>{b} : {c} ({d}%)"
		    },
		    legend: {
		        orient : 'vertical',
		        x : 'left',
		        data:eval(text)
		    },
		    toolbox: {
		        show : true,
		        feature : {
		            mark : {show: false},
		            dataView : {show: true, readOnly: false},
		            magicType : {
		                show: true,
		                type: ['pie', 'funnel'],
		                option: {
		                    funnel: {
		                        x: '25%',
		                        width: '100%',
		                        funnelAlign: 'center',
		                        max: 1548
		                    }
		                }
		            },
		            restore : {show: true},
		            saveAsImage : {show: true}
		        }
		    },
		    calculable : true,
		    series : [
		        {
		            name:name,
		            type:'pie',
		            radius : '60%',
		            center: ['56%', '45%'],
		            data:eval(pie)
		        }
		    ]
		};

//		option2 = {
//			    title : {
//			        text: '',
//			        subtext: ''
//			    },
//			    tooltip : {
//			        trigger: 'axis'
//			    },
//
//			    toolbox: {
//			        show : true,
//			        feature : {
//			            mark : {show: false},
//			            dataView : {show: true, readOnly: false},
//			            magicType : {show: true, type: ['line', 'bar']},
//			            restore : {show: true},
//			            saveAsImage : {show: true}
//			        }
//			    },
//			    calculable : true,
//			    xAxis : [
//			        {
//			            type : 'category',
//			            data : eval(text)
//			        }
//			    ],
//			    yAxis : [
//			        {
//			            type : 'value'
//			        }
//			    ],
//			    series : [
//			        {
//			            type:'bar',
//			            data:eval(pie)
//
//			        }
//			    ]
//			};
		pieChart = echarts.init(document.getElementById('pie'));
		pieChart.setOption(option1);

//		histogramChart = echarts.init(document.getElementById('histogram'));
//		histogramChart.setOption(option2);

	})();
	setTimeout(function (){
	    window.onresize = function () {
	    	pieChart.resize();
//	    	histogramChart.resize();
	    }
	},200)
	</script>