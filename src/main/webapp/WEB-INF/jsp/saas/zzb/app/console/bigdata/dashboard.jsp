<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/inc/taglib.jsp" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <link href="${path}/css/common/common.css" rel="stylesheet" type="text/css"/>
    <!-- BEGIN PAGE LEVEL STYLES -->
    <link rel="stylesheet" href="${path }/css/DT_bootstrap.css" />
    <title>大数据应用</title>

    <link href="${pageContext.request.contextPath}/css/style_operate.css" rel="stylesheet" />
    <!-- END PAGE LEVEL STYLES -->
    <style type="text/css">
        .page-content{/* background:url(media/images/templateImage/xinkong_3.jpg) no-repeat center center;*/ background-color:inherit; margin-top:35px;}/*f2f3f5*/
        body{ background-color: #063365}
        .tabbable-custom > .tab-content{ padding:10px 10px 10px 10px !important;}

        .page-header-fixed .page-container {
            padding-top: 0px;
        }
        .page-sidebar{
            margin-top: 60px;
        }



    </style>
</head>
<body>

    <p style="opacity:0;">0</p>

    <div class="BigdatamainOne">
        <div class="OperAmount">
            <p class="Atotal" id="myTargetElement">16709</p>
            <p class="AName" style="height:24px; padding-top:15px; padding-bottom:0;">干部总数</p>
        </div>
        <div class="mainright">
            <div class="ExpertTitle">图表分析 共16709人</div>
            <div id="MonitorTopN01" style="width:100%;height:180px;"></div>
        </div>
    </div>

    <div class="row-fluid" style="margin-top:15px;">
        <div class="bintulist3">
            <div class="bintumain">
                <div class="ExpertTitle">全市干部年龄结构</div>
                <div id="Agedata01" style=" height:200px; width:300px; margin:0 auto 0 auto;"></div>
            </div>
        </div>
        <div class="bintulist3">
            <div class="bintumain">
                <div class="ExpertTitle">全市干部学历结构</div>
                <div id="Agedata02" style=" height:200px; width:300px; margin:0 auto 0 auto;"></div>
            </div>
        </div>
        <div class="bintulist3">
            <div class="bintumain" style="margin-right:0;">
                <div class="ExpertTitle">全市干部民族结构</div>
                <div id="Agedata03" style=" height:200px; width:300px; margin:0 auto 0 auto;"></div>
            </div>
        </div>
    </div>

    <div class="row-fluid" style="margin-top:15px;">
        <div class="bintulist3" style="width:33.33333%">
            <div class="bintumain">
                <div class="ExpertTitle">平均年龄变化趋势</div>
                <div id="Agedata04" style="width:100%;height:180px;"></div>
            </div>
        </div>
        <div class="bintulist3" style="width:66.66667%">
            <div class="bintumain" style="margin-right:0;">
                <div class="ExpertTitle">事务提醒</div>
                <ul class="ulshiwutslist">
                    <li><span><a href="###">查看</a></span><em>1</em><a href="###">试用期到期人员</a><span>8&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></li>
                    <li><span><a href="###">查看</a></span><em>2</em><a href="###">已到退休人员</a><span>36&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></li>
                    <li><span><a href="###">查看</a></span><em>3</em><a href="###">任职满十年</a><span>13&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></li>
                    <li><span><a href="###">查看</a></span><em>4</em><a href="###">干部监督提醒</a><span>1&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></li>
                    <li><span><a href="###">查看</a></span><em>5</em><a href="###">其他提醒</a><span>2&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></li>
                </ul>
            </div>
        </div>


    </div>
    <!-- END PAGE -->

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

<!--<script src="media/js/echarts_bintu.js" type="text/javascript" ></script>-->


<!--数字滚动-->
<script type="text/javascript" src="${path}/js/countUp.min.js"></script>
<script type="text/javascript">
    jQuery(document).ready(function() {
        // initiate layout and plugins
        App.init();

        //日志数数字滚动
        var options = {
            useEasing : true,
            useGrouping : true,
            separator : ',',
            decimal : '.',
            prefix : '',
            suffix : ''
        };
        var demo = new CountUp("myTargetElement", 0, 16709, 0, 2.5, options);
        demo.start();




        <!--图表分析-->
        var myChart1 = echarts.init(document.getElementById('MonitorTopN01'));
        option1 = {
            /*title: {
             text: '世界人口总量',
             subtext: '数据来自网络'
             },*/
            color: ['#007cd5'],

            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'shadow'
                },
                backgroundColor: 'rgba(248,248,248,0.7)',     // 提示背景颜色，默认为透明度为0.7的黑色
                borderColor: '#3f88bb',       // 提示边框颜色
                borderRadius: 4,           // 提示边框圆角，单位px，默认为4
                borderWidth: 1,            // 提示边框线宽，单位px，默认为0（无边框）
                padding: 5,                // 提示内边距，单位px，默认各方向内边距为5，
                textStyle: {
                    color: '#333333'
                },
            },

            grid: {
                top: '3%',
                left: '3%',
                right: '3%',
                bottom: '1%',
                containLabel: true
            },
            xAxis : [
                {
                    type : 'category',
                    data : ['厅局级正职', '厅局级副职', '县处级正职', '县处级副职', '乡科级正职', '乡科级副职', '科员', '办事员', '试用期人员'],
                    axisLine : {
                        show : true,
                        lineStyle : {
                            color: '#409fff'
                        }
                    },
                    axisTick : {
                        show : true,
                        lineStyle : {
                            color: '#0a5bad'
                        }
                    },
                    splitLine : {
                        show : false,
                        lineStyle : {
                            color: '#ddd'
                        }
                    },
                    axisLabel: {
                        show: true,
                        textStyle: {
                            color: "#409fff"
                        },
                    },
                    boundaryGap: [0, 0.01]
                }
            ],
            yAxis : [
                {
                    type : 'value',
                    axisLine : {
                        show : true,
                        lineStyle : {
                            color: '#409fff'
                        }
                    },
                    axisTick : {
                        show : true,
                        lineStyle : {
                            color: '#0a5bad'
                        }
                    },
                    splitLine : {
                        show : true,
                        lineStyle : {
                            color: '#073971'
                        }
                    },
                    axisLabel: {
                        show: true,
                        textStyle: {
                            color: "#409fff"
                        },
                    },
                }
            ],
            series : [
                {
                    name:'总人数',
                    type:'bar',
                    barWidth: '30%',
                    data:[4, 34, 215, 778, 3770, 5848, 5423, 6, 631]
                }
            ]
        };
        myChart1.setOption(option1, true);

        var myChart2 = echarts.init(document.getElementById('Agedata01'));
        option2 = {
            color: [
                "#2ec7c9",
                "#b6a2de",
                "#5ab1ef",
                "#ffb980",
                "#d87a80",
            ],
            tooltip: {
                trigger: 'item',
                formatter: "{a} <br/>{b}: {c} ({d}%)"
            },
            legend: {
                orient: 'vertical',
                x: 'left',
                top: 18,
                data:['35岁及以下','36~40岁','41~45岁','46~50岁','51~55岁','56~60岁','60岁及以上'],
                itemWidth:18,
                itemHeight:8,

                textStyle:{
                    fontSize: 12,
                    color: '#fff',
                },


            },
            series: [
                {
                    name:'全市干部年龄结构',
                    type:'pie',
                    //radius: ['50%', '70%'],  空心圆
                    radius : '80%',
                    avoidLabelOverlap: false,
                    label: {
                        normal: {
                            show: false,
                            position: 'center'
                        },
                        emphasis: {
                            show: true,
                            textStyle: {
                                fontSize: '30',
                                fontWeight: 'bold'
                            }
                        }
                    },
                    center:[
                        180,
                        90
                    ],
                    labelLine: {
                        normal: {
                            show: false
                        }
                    },
                    data:[
                        {value:1335, name:'35岁及以下'},
                        {value:310, name:'36~40岁'},
                        {value:234, name:'41~45岁'},
                        {value:135, name:'46~50岁'},
                        {value:548, name:'51~55岁'},
                        {value:100, name:'56~60岁'},
                        {value:170, name:'60岁及以上'}
                    ]
                }
            ]
        };
        myChart2.setOption(option2, true);

        var myChart3 = echarts.init(document.getElementById('Agedata02'));
        option3 = {
            color: [
                "#2ec7c9",
                "#b6a2de",
                "#5ab1ef",
                "#ffb980",
                "#d87a80",
            ],
            tooltip: {
                trigger: 'item',
                formatter: "{a} <br/>{b}: {c} ({d}%)"
            },
            legend: {
                orient: 'vertical',
                x: 'left',
                top: 18,
                data:['本科','大专','硕士','博士'],
                itemWidth:18,
                itemHeight:8,

                textStyle:{
                    fontSize: 12,
                    color: '#fff',
                },


            },
            series: [
                {
                    name:'全市干部学历结构',
                    type:'pie',
                    //radius: ['50%', '70%'],  空心圆
                    radius : '80%',
                    avoidLabelOverlap: false,
                    label: {
                        normal: {
                            show: false,
                            position: 'center'
                        },
                        emphasis: {
                            show: true,
                            textStyle: {
                                fontSize: '30',
                                fontWeight: 'bold'
                            }
                        }
                    },
                    center:[
                        180,
                        90
                    ],
                    labelLine: {
                        normal: {
                            show: false
                        }
                    },
                    data:[
                        {value:600, name:'本科'},
                        {value:120, name:'大专'},
                        {value:362, name:'硕士'},
                        {value:135, name:'博士'},
                    ]
                }
            ]
        };
        myChart3.setOption(option3, true);

        var myChart4 = echarts.init(document.getElementById('Agedata03'));
        option4 = {
            color: [
                "#2ec7c9",
                "#b6a2de",
                "#5ab1ef",
                "#ffb980",
                "#d87a80",
            ],
            tooltip: {
                trigger: 'item',
                formatter: "{a} <br/>{b}: {c} ({d}%)"
            },
            legend: {
                orient: 'vertical',
                x: 'left',
                top: 18,
                data:['汉族','少数名族'],
                itemWidth:18,
                itemHeight:8,

                textStyle:{
                    fontSize: 12,
                    color: '#fff',
                },


            },
            series: [
                {
                    name:'全市干部民族结构',
                    type:'pie',
                    //radius: ['50%', '70%'],  空心圆
                    radius : '80%',
                    avoidLabelOverlap: false,
                    label: {
                        normal: {
                            show: false,
                            position: 'center'
                        },
                        emphasis: {
                            show: true,
                            textStyle: {
                                fontSize: '30',
                                fontWeight: 'bold'
                            }
                        }
                    },
                    center:[
                        180,
                        90
                    ],
                    labelLine: {
                        normal: {
                            show: false
                        }
                    },
                    data:[
                        {value:7001, name:'汉族'},
                        {value:10600, name:'少数名族'}
                    ]
                }
            ]
        };
        myChart4.setOption(option4, true);

        var myChart5 = echarts.init(document.getElementById('Agedata04'));
        option5 = {
            animation : true,
            animationDuration : 500,
            animationEasing : 'linear',
            animationDurationUpdate : 500,
            animationEasingUpdate : 'linear',
            //backgroundColor:'#fff',
            toolbox: {
                show : true,
                feature : {
                    mark : {show: false},
                    dataView : {show: false, readOnly: false},
                    magicType : {show: false, type: ['line', 'bar']},
                    restore : {show: false},
                    saveAsImage : {show: false}
                }
            },
            grid: {
                left: '8%',
                right: '12%',
                top: '7%',
                bottom: '5%',
                containLabel: true
            },
            //        title: {
            //            text: '访客近7天趋势TOP5',
            //            x:'center'
            //        },
            legend: {
                //data:['攻击总量'],
                x: 'center',
                y: 'bottom',
                textStyle: {
                    color: '#409fff'
                }
            },
            calculable : true,
            tooltip : {
                trigger: 'axis',
                backgroundColor: 'rgba(255,255,255,0.8)',     // 提示背景颜色，默认为透明度为0.7的黑色
                borderColor: '#1cb9ff',       // 提示边框颜色
                borderRadius: 4,           // 提示边框圆角，单位px，默认为4
                borderWidth: 1,            // 提示边框线宽，单位px，默认为0（无边框）
                padding: 5,                // 提示内边距，单位px，默认各方向内边距为5，
                textStyle: {
                    color: '#333333'
                },
                axisPointer : {
                    type : 'line',
                    lineStyle : {
                        type : 'dotted',
                        color : '#409fff'
                    }
                }
            },
            xAxis : [
                {
                    type : 'category',
                    //axisLine : {onZero: false},
                    boundaryGap : false,
                    splitLine	: {
                        show : false
                    },
                    splitLine	: {
                        show : false
                    },
                    axisLine : {
                        show : true,
                        lineStyle : {
                            color: '#409fff'
                        }
                    },
                    axisTick : {
                        show : true,
                        lineStyle : {
                            color: '#0a5bad'
                        }
                    },

                    axisTick: {
                        lineStyle: {
                            color: "#0a5bad"
                        },
                    },
                    data : ['2012-03-05', '2013-03-05', '2014-03-05', '2015-03-05', '2016-03-05', '2017-03-05', '2018-03-05']
                }
            ],
            yAxis : [
                {
                    type : 'value',
                    axisLine : {
                        show : true,
                        lineStyle : {
                            color: '#409fff'
                        }
                    },
                    axisTick : {
                        show : true,
                        lineStyle : {
                            color: '#0a5bad'
                        }
                    },
                    splitLine : {
                        show : true,
                        lineStyle : {
                            color: '#073971'
                        }
                    },
                    axisLabel: {
                        show: true,
                        textStyle: {
                            color: "#409fff"
                        },
                    },
                }
            ],
            color:["#0088CC","#E09195","#F07608","#9FE4A6", "#8160F1"],
            series : [
                {
                    name:'平均年龄变化趋势',
                    type:'line',
                    smooth:true,
                    itemStyle: {
                        normal: {
                            lineStyle: {
                                shadowColor : 'rgba(0,0,0,0.4)'
                            },
                        }
                    },
                    lineStyle : {
                        normal : {
                            width : 1
                        }
                    },
                    data:[37.9,37.4,37.2,36.9,36.5,36.3,35.6]
                }
            ],
        };
        myChart5.setOption(option5, true);

    });
</script>
</body>
</html>