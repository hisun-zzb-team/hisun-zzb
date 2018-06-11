<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/inc/servlet.jsp" %>
<%@include file="/WEB-INF/jsp/inc/taglib.jsp" %>
<%--
  ~ Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
  ~ http://www.hn-hisun.com
  ~ 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
  --%>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>机构编制管理</title>
	<style type="text/css">
		.ztree li span.button.add {margin-left:2px; margin-right: -1px; background-position:-144px 0; vertical-align:top; *vertical-align:middle}
		.page-content{   padding: 0 !important; }
		ul.ztree{margin-bottom: 10px; background: #f1f3f6 !important;}
		.portlet.box.grey.mainleft{background-color: #f1f3f6;overflow: hidden; padding: 0px !important; margin-bottom: 0px;}
		.main_left{float:left; width:220px;  margin-right:10px; background-color: #f1f3f6; }
		.main_right{display: table-cell; width:2000px; padding:20px 20px; }
		.portlet-title .caption.mainlefttop{ border: none !important; background-color:#eaedf1;width: 220px; height: 48px;line-height: 48px;padding: 0;margin: 0;text-indent: 1em; }
		.portlet.box .portlet-body.leftbody{padding: 15px 8px;}</style>
</head>
<body>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="main_left">
			<div class="portlet box grey mainleft">
				<div class="portlet-body leftbody">
					<input type="hidden" id="b01Id"  name="b01Id" value="" />
					<input type="hidden"  name="b0101" value="" />
					<input type="hidden" id="parentB01Id"  name="parentB01Id" value="" />
					<input type="hidden" id="cuaNodeId" name="cuaNodeId" value=""/><!--当前节点id-->
					<%--<Tree:tree id="leftB01Tree" treeUrl="${path}/zzb/jggl/b01Api/load/tree" token="${sessionScope.OWASP_CSRFTOKEN}"--%>
							   <%--onClick="onClickByTree" submitType="post" dataType="json" isSearch="false"/>--%>
					<Tree:tree id="leftB01Tree"  treeUrl="${path}/api/b01/dtjz/tree" token="${sessionScope.OWASP_CSRFTOKEN}"
							   onClick="onClickByTree" shjz="true" submitType="post" dataType="json" isSearch="false" isSelectTree="false" dtjz="true"/>
				</div>
			</div>
		</div>
		<div class="main_right" id="rightList" ></div>
	</div>
</div>
<script type="text/javascript" src="${path}/js/common/est-validate-init.js"></script>
<script type="text/javascript" src="${path}/js/common/validate-message.js"></script>
<script type="text/javascript">
	$(function(){
		changeTreeDivHeight();
		//当浏览器大小改变的时候,要重新计算
		$(window).resize(function(){
			changeTreeDivHeight();
		})
	});
	function changeTreeDivHeight(){
		var divHeight = $(window).height()-60;
		$("#leftB01Tree_div").css('height',divHeight);
	}

	function onClickByTree (event, treeId, treeNode){
		$("#b01Id").val(treeNode.id);//赋值
		$("#b0101").val(treeNode.name);//赋值
		$("#parentB01Id").val(treeNode.pId);//赋值
		$.ajax({
			url: "${path}/zzb/jggl/b01/ajax/list",
			type : "get",
			dataType : "html",
			headers: {
				"OWASP_CSRFTOKEN":"${sessionScope.OWASP_CSRFTOKEN}"
			},
			data:{
				"b01Id":treeNode.id,
				"parentB01Id":treeNode.pId,
				"b0101":treeNode.name
			},
			success : function(html){
				$("#rightList").html(html);
			},
			error : function(){

			}
		});
	}

	$(document).ready(function(){
	    var isAddOne = false;
		App.init();//必须，不然导航栏及其菜单无法折叠
		//判断是否已添加顶级节点
        $.ajax({
            async:false,
            url: "${path}/zzb/jggl/b01/getB01List",
            type : "get",
            dataType : "json",
            headers: {
                "OWASP_CSRFTOKEN":"${sessionScope.OWASP_CSRFTOKEN}"
            },
            data:{},
            success : function(json){
                if(!json.exist){
                    isAddOne =true;
                    $.ajax({
                        async:false,
                        url: "${path}/zzb/jggl/b01/ajax/manage",
                        type : "get",
                        dataType : "html",
                        headers: {
                            "OWASP_CSRFTOKEN":"${sessionScope.OWASP_CSRFTOKEN}"
                        },
                        data:{
                            "isAdd":"add",
                            "bSjlx":"2",
                            "isAddOne":"addOne"
                        },
                        success : function(html){
                            $("#rightList").html(html);
                        },
                        error : function(){

                        }
                    });
                }
            },
            error : function(){

            }
        });
        if(isAddOne){
            return;
        }
		var zTree = $.fn.zTree.getZTreeObj("leftB01Tree");//取得树对象
		var node = zTree.getNodes()[0];// 获取第一个点

		var b01Id ;
		var parentB01Id ;
		var b0101 ;
		if(node!=null){
			$("#b01Id").val(node.id);//赋值
			$("#b0101").val(node.name);//赋值
			$("#parentB01Id").val(node.pId);//赋值
            b01Id =node.id;
			parentB01Id =node.pId;
			b0101 =node.name;
		}


		$.ajax({
			cache:false,
			type: 'POST',
			dataType : "html",
			data:{
				"b01Id":b01Id,
				"parentB01Id":parentB01Id,
				"b0101":b0101
			},
			headers: {
				"OWASP_CSRFTOKEN":"${sessionScope.OWASP_CSRFTOKEN}"
			},
			url: "${path}/zzb/jggl/b01/ajax/list",// 请求的action路径
			error: function () {// 请求失败处理函数
				alert('请求失败');
			},
			success:function(html){
				$("#rightList").html(html);
			}
		});
		zTree.selectNode(node);//默认选中
		zTree.expandNode(node, true, false , true);//展开
	});
	function refreshTree() {
        refreshTreeTagByDt("leftB01Tree","4028839263d8e2710163d9256b400005");
	}

</script>
</body>
</html>