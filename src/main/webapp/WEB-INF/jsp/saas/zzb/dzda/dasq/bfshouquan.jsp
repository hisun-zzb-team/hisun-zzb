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
<title></title>
                <div style="max-height:400px;overflow:auto;">
                    <input type="hidden" id="currentNodeId"  name="currentNodeId" value="" />
                    <input type="hidden" id="currentNodeName"  name="currentNodeName" value="" />
                    <input type="hidden" id="currentNodeParentId"  name="currentNodeParentId" value="" />
                    <Tree:tree id="a38MlclTree" treeUrl="${path}/zzb/dzda/mlcl/tpcl/ajax/tree/${a38Id}" token="${sessionScope.OWASP_CSRFTOKEN}"
                            height="200px"   onClick="onClickByTreeByTpsc" submitType="post" dataType="json" isSearch="false" radioOrCheckbox="checkbox" chkboxType=' "Y" : "s", "N" : "" '/>

                </div>
                <center>
                    <div class="controls mt10">
                        <button class="btn green" type="button" style="padding:7px 20px;" onclick="bfsqSubmit()">确定</button>

                        <button type="button" class="btn btn-default" data-dismiss="modal"><i class='icon-remove-sign'></i> 关闭</button>
                    </div>
                </center>
<script type="text/javascript">
    $(function(){
        changebfzsqTreeDivHeight();
        //当浏览器大小改变的时候,要重新计算
        $(window).resize(function(){
            changebfzsqTreeDivHeight();
        })
    });
    function changebfzsqTreeDivHeight(){
        var divHeight = 355;
        $("#a38MlclTree_div").css('height',divHeight);
    }
    function bfsqSubmit(){
        var treeObj = $.fn.zTree.getZTreeObj("a38MlclTree");
        var nodes = treeObj.getCheckedNodes(true);
        if(nodes.length<1){
            showTip("提示","请选择要授权的材料",2000);
            return
        }
        var idString="";
        for(var i=0;i<nodes.length;i++){
            if(nodes[i].nodeType=="dir"){
                showTip("提示","不能选择材料目录",2000);
                return
            }
            idString =  idString + ","+ nodes[i].id;
        }
        $("#e01z1IdContent").val(idString);
        var bool = form1.form();
        if(bool){
            $.ajax({
                url : "${path }/zzb/dzda/cyshouquan/bfshouquan",
                type : "post",
                data : $("#form1").serialize(),
                headers:{
                    OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
                },
                dataType : "json",
                success : function(data){
                    if(data.success){
                        window.location.href = "${path}/zzb/dzda/cyshouquan/list";
                    }
                },
                error : function(){
                    showTip("提示","出错了,请检查网络!",2000);
                }
            });
        }
    }


    function onClickByTreeByTpsc (event, treeId, treeNode){
        $("#currentNodeId").val(treeNode.id);//赋值
        $("#currentNodeName").val(treeNode.name);//赋值
        $("#currentNodeParentId").val(treeNode.pId);//赋值
        if(treeNode.pId==null || treeNode.pId==''){
            refreshFileList();
        }else{
            if(treeNode.nodeType=="dir"){
                refreshFileList(treeNode.key);
            }else{
                var zTree1 = $.fn.zTree.getZTreeObj("a38MlclTree");
                var parentNode = zTree1.getNodeByParam('id',treeNode.pId);// 获取id为-1的点
                refreshFileList(parentNode.key,treeNode.key);
            }
        }
    }


    $(document).ready(function(){
        App.init();//必须，不然导航栏及其菜单无法折叠
        var zTree = $.fn.zTree.getZTreeObj("a38MlclTree");//取得树对象
        var node = zTree.getNodes()[0];// 获取第一个点
        $("#currentNodeId").val(node.id);//赋值
        $("#currentNodeName").val(node.name);//赋值
        $("#currentNodeParentId").val(node.pId);//赋值
        /*$.ajax({
            cache:false,
            type: 'POST',
            dataType : "html",
            data:{
                "currentNodeId":node.id,
                "currentNodeParentId":node.pId,
                "currentNodeName":node.name
            },
            headers: {
                "OWASP_CSRFTOKEN":"${sessionScope.OWASP_CSRFTOKEN}"
            },
            url: "${path}/zzb/dzda/mlcl/jztp/ajax/list/${a38Id}",// 请求的action路径
            error: function () {// 请求失败处理函数
                alert('请求失败');
            },
            success:function(html){
                $("#jztpList").html(html);
            }
        });*/
        zTree.selectNode(node);//默认选中
        zTree.expandNode(node, true, false , true);//展开
    });

    function refreshTree() {
        $("#a38MlclTree").empty();
        refreshTreeTag("a38MlclTree",setting_a38MlclTree,"");
        selectNodeTree();
    }
    function selectNodeTree(){
        var zTree1 = $.fn.zTree.getZTreeObj("a38MlclTree");
        var id = $("#currentNodeId").val();
        var node = zTree1.getNodeByParam('id',id);// 获取id为-1的点
        zTree1.selectNode(node);
        zTree1.expandNode(node, true, false , true);
    }



</script>