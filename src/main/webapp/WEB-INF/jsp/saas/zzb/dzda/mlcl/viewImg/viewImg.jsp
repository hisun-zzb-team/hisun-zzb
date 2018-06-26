<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/inc/servlet.jsp" %>
<%@include file="/WEB-INF/jsp/inc/taglib.jsp" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<script type="text/javascript">
	window.PATH = "${path}";
</script>
<style>
	.image-wrap {
		position: relative;
		display: inline-block;
		overflow: hidden;
		vertical-align: middle;
	}
	.dropdownMob{ position:absolute; top:5px; right:5px; display:none;}
	.dropdownMob .dropdown-menu{ left:-93px; min-width: 120px;}
	.dropdownMob .dropdown-menu li{left:-93px; min-width: 120px;}
	.btn.downMobBtn{ padding:3px 6px; background-color:#FFFFFF; border:solid 1px #e5e5e5;}
	.btn.downMobBtn:hover{ background-color:#FFFFFF !important;}
</style>
<%--<link href="${path}/css/images-view/viewer-image.css" rel="stylesheet" type="text/css"/>--%>
<%--<link href="${path}/css/images-view/main-image.css" rel="stylesheet" type="text/css"/>--%>

<form action="" id="importTpForm" method="post" enctype="multipart/form-data">
	<input style="display: none" type="file" name="tpFile" id="tpFile" accept = 'image/*'>
	<input type="hidden" id="curImgNo" name="curImgNo" value="">
	<input type="hidden" id="uploadType" name="uploadType" value="">
</form>
<div id="updateImgNoModal" class="modal container hide fade" tabindex="-1" data-width="400">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button data-dismiss="modal" class="close"  type="button"></button>
				<h3 class="modal-title" id="title" >
					图片顺序调整
				</h3>
			</div>
			<div class="modal-body form-horizontal" data-height="150" id="updateImgNoDiv">
					<input type="hidden" name="imgId" id="imgId"/>
					<div class="control-group">
						<label class="control-label" style=" width: 70px;">当前排序：</label>
						<div class="controls" style="margin-left: 80px;">
							<input class="m-wrap" type="text" id="cuaImgNo" name="cuaImgNo"  maxlength="2" readonly value=""/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label"  style=" width: 70px;"><span class="required">*</span>调整排序</label>
						<div class="controls" style="margin-left: 80px;">
							<input class="m-wrap" type="text" id="newImgNo" name="newImgNo"  maxlength="2" number="true"  value=""/>
						</div>
					</div>
					<div id="ErrMsg" name="ErrMsg" style="color: red;margin-left: 80px;"></div>
					<div class="control-group mybutton-group" style="text-align: center;">
						<button type="button" class="btn green" onclick="updateImgNoSubmit()"><i class="icon-ok"></i> 确定</button>
						<button type="button" class="btn btn-default"  data-dismiss="modal"><i class="icon-remove-sign"></i> 取消</button>
					</div>
			</div>
		</div>
	</div>
</div>
	<div id="viewDiv" style="overflow: auto;margin: 0px;" oncontextmenu='return false'>
		<c:if test="${empty e01z1Id}">
			<table style="width: 100%;height: 100%;">
				<tr>
					<td style="text-align:center;width: 100%">
						<font size="5"><b>请 点 击 左 侧 目 录 下 的 材 料 查 看 档 案 图 片 </b></font>
					</td>
				</tr>
			</table>
		</c:if>
		<c:if test="${not empty e01z1Id}">
			<c:if test="${imagesSize==0}">
				<table style="width: 100%;height: 100%;">
					<tr >
						<td style="text-align:center;width: 100%;">
							<font size="5"><b>此 份 材 料 未 加 载 图 片 </b></font>
						</td>
					</tr>
				</table>
			</c:if>
			<c:if test="${imagesSize>=0}">
				<div class="docs-galley">
					<ul class="docs-pictures clearfix">
						<c:forEach items="${images}" var="image">
							<li style="text-align: center;">
								<div class="image-wrap" >
									<input type="hidden" id="showImgId" name="showImgId" value="${image.id}">
									<input type="hidden" id="showImgNo" name="showImgNo" value="${image.imgNo}">
									<input type="hidden" id="showClmc" name="showClmc" value="${image.e01z1.e01Z111}">
									<input type="hidden" id="showDamc" name="showDamc" value="${image.e01z1.a38.a0101}">
									<img class="showImageClass" src="${path}/zzb/dzda/mlcl/images/showImages?a38Id=${a38Id}&imgId=${image.id}&OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}" title="${image.imgNo}" alt="${image.imgNo}/${imagesSize}">
									<span style="display: block; width: 40px; height: 20px; top: 0; left: 0; z-index: 1111; position: absolute; text-align: center; font-size: 16px; cursor: pointer; ">${image.imgNo}/${imagesSize}</span>
									<%--按比例挡住图片下面的部分 但是在全屏查看有问题<span style="display: block; width:100%; height: 40%; bottom: 0; left: 0; z-index: 1111; position: absolute; text-align: center; font-size: 16px; cursor: pointer; color:dimgrey;background-color: #979797"></span>--%>
									<div class="dropdownMob">
										<div class="btn-group">
											<a class="btn downMobBtn" href="#" data-toggle="dropdown"><i class="icon-angle-down"></i></a>
											<ul class="dropdown-menu">
												<li><a href="javascript:uploadImageByOne('1','frist')"><i class="icon-plus"></i> 插入首页</a></li>
												<li><a href="javascript:uploadImageByOne('${image.imgNo}','up')"><i class="icon-plus"></i> 插入上一页</a></li>
												<li><a href="javascript:uploadImageByOne('${image.imgNo}','down')"><i class="icon-plus"></i> 插入下一页</a></li>
												<li><a href="javascript:uploadImageByOne('','end')"><i class="icon-plus"></i> 插入尾页</a></li>
												<li><a href="javascript:updateImgNo('${image.id}','${image.imgNo}')"><i class="icon-sort-by-alphabet-alt"></i> 排序</a></li>
												<li><a href="javascript:deleteImg('${image.id}','${image.imgNo}')"><i class="icon-remove-sign"></i> 删除</a></li>
											</ul>
										</div>
									</div>
								</div>
							</li>
						</c:forEach>
					</ul>
				</div>
				<%--<div id="gallery1"></div>--%>
				<%--<table  id="jsonDataFormTable" width="100%">--%>

					<%--<c:forEach items="${eImages}" var="image">--%>
					<%--<tr >--%>
						<%--<td style="width:100%;text-align: center">--%>
							<%--<img src="${path}/zzb/dzda/mlcl/images/showImages?a38Id=${a38Id}&imgId=${image}&OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}">--%>
						<%--</td>--%>
					<%--</tr>--%>
					<%--</c:forEach>--%>
				<%--</table>--%>
			</c:if>
		</c:if>
	</div>
<%--<script  type="text/javascript" src="${path }/js/images-view/images-grid.js"></script>--%>
<%--<script  type="text/javascript" src="${path }/js/images-view/viewer-image.js"></script>--%>
<%--<script  type="text/javascript" src="${path }/js/images-view/main-image.js"></script>--%>
<script type="text/javascript">


	var imgs = [];
	var isManage = "false";

	$(function(){
		changeTreeDivHeight();
		//当浏览器大小改变的时候,要重新计算
		$(window).resize(function(){
			changeTreeDivHeight();
		})
		//如果图片数大于0 则下一页和最后一页可用
        document.all("imagesCount").value = "${imagesSize}";
        var imageIndex = document.all("imageIndex").value;
		if("${imagesSize}"=="0"){
            document.all("button1").disabled = true;
            document.all("button2").disabled = true;
            document.all("button3").disabled = true;
            document.all("button4").disabled = true;
			try {
                document.all("daYinDa").disabled = true;
                document.all("xiaZaiDa").disabled = true;
            }catch(e){}
        }else{
		    if(imageIndex!=document.all("imagesCount").value) {
                document.all("button3").disabled = false;
                document.all("button4").disabled = false;
            }

            try {
                document.all("daduibiBtn").disabled = false;
				document.all("daYinDa").disabled = false;
				document.all("xiaZaiDa").disabled = false;
            }catch(e){}
        }

        //改成图片显示的比例
        var viewShowType =  document.all("viewShowType").value;
		if(viewShowType=="qp"){
            $(".showImageClass").css("height",'');
        }else{
		    debugger
		    var showHeight = $(window).height()-65;
            $(".showImageClass").css("height",showHeight+"px");
        }
        myLoading.hide();
		<%--var images = "${images}";--%>
		<%--if(images!=null && images!="") {--%>
			<%--var imgList = images.replace('[','').replace(']','').split(',');--%>
			<%--for (var i = 0; i < imgList.length; i++) {  //循环LIST--%>
				<%--var imgId = imgList[i].split(";")[0];--%>
				<%--imgId = imgId.replace(/(^\s*)|(\s*$)/g, "")--%>
				<%--var imgNo  = imgList[i].split(";")[1];--%>
				<%--imgs[i] ={ src: "/zzb/dzda/mlcl/images/showImages?a38Id=${a38Id}&imgId="+imgId+"&OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}",imgId:imgId,alt:imgNo+"/${imagesSize}", title:imgNo};--%>
			<%--}--%>
		<%--}--%>
		<%--$('#gallery1').imagesGrid({--%>
			<%--images:imgs--%>
		<%--});--%>

		isManage = "${isManage}";
		$(".image-wrap").hover(function () {
			if (isManage == "true") {
				$(this).find('.dropdownMob').show();
			}
		}, function () {
			if (isManage == "true") {
				$(this).find('.dropdownMob').hide();
			}
		});

//		if($("#showTpWidth").val()!=""){
//			$(".imgs-grid-image").css("width",$("#showTpWidth").val()+"%");
//
//			if($("#showTpWidth").val()=="100"){
//				$(".imgs-grid").css("text-align","center");
//			}else{
//				$(".imgs-grid").css("text-align","left");
//			}
//		}
		$("#tpFile").bind("change",function(evt){
			if($(this).val()){
				tpscSubmit();
			}
			$(this).val('');
		});

		function tpscSubmit(){
			var fileInput = document.getElementById("tpFile");
			if(fileInput.files.length>0){

			}else{
				showTip("提示","请选择上传图片",2000);
				return;
			}
			myLoading.show();
			$("#importTpForm").ajaxSubmit({
				url : "${path }/zzb/dzda/mlcl/images/uploadImg?e01z1Id=${e01z1Id}",
				type : "post",
				headers:{
					OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
				},
				beforeSend:function(XHR){
					myLoading.show();
				},
				success : function(json){
					if(json.success){
						myLoading.hide();
						showTip("提示","图片上传成功",2000);
						setTimeout(function(){
							refreshTree();
							loadRight("${e01z1Id}")
						},2000)
					}else{
						myLoading.hide();
						showTip("提示","出错了,请检查网络!",500);
					}
				},
				error : function(arg1, arg2, arg3){
					showTip("提示","出错了,请检查网络!",500);
				},
				complete : function(XHR, TS){
					myLoading.hide();
				}
			});
		}


//		$("img").bind("mousewheel", function() {
//			alert("aaaa");
//			zoomImg(this);
//			return false;
//		});
	});


	function changeTreeDivHeight(){
		var listHeight = $(window).height()-65;
		$("#viewDiv").css('height',listHeight);
//			$("#viewList").css('height',listHeight);
//			$(".main_left").height(mainHeight);

	}

	function changeViewType(type){

//		if(isManage==true) {
//			$("#showTpWidth").val(width);
//			$(".imgs-grid-image").css("width", width + "%");
//
//			if (width == "100") {
//				$(".imgs-grid").css("text-align", "center");
//			} else {
//				$(".imgs-grid").css("text-align", "left");
//			}
//		}else{
//			$(".viewer-toggle").css("height", width + "%");
//			$(".docs-pictures li").css("width", width + "%");
//			$(".docs-pictures li").css("width", width + "%");
//
//		}
	}
	//uploadType 上传方式 frist表示插入首页，up表示插入上一页 down表示下一页 end表示尾页
	function uploadImageByOne(imgNo,uploadType){
		$("#curImgNo").val(imgNo);
		$("#uploadType").val(uploadType);
		document.getElementById("tpFile").click();
//		alert(imgNo);
//		alert(uploadType);
	}

	//调整图片顺序
	function updateImgNo(imgId,imgNo){
		$('#cuaImgNo').val(imgNo);
		$('#imgId').val(imgId);
		$('#updateImgNoDiv').css("height","150");

		$('#updateImgNoModal').modal({
			keyboard: false,
			backdrop: 'static'
		});
	}
	//调整图片顺序
	function updateImgNoSubmit(){
		var newImgNo = $('#newImgNo').val();
		var imgId = $('#imgId').val();
		if(newImgNo==""){
			showTip("提示","调整排序不能为空",2000);
			return false;
		}else{
			if(isNumberTmp(newImgNo)==false){
				showTip("提示","调整排序必须为正整数",2000);
				return false;
			}else{
				if(newImgNo<0||newImgNo==0){
					showTip("提示","调整排序必须为正整数",2000);
					return false;
				}
			}
		}

		$.ajax({
			url : "${path }/zzb/dzda/mlcl/images/updateImgNo/"+imgId,
			type : "post",
			data : {
				"newImgNo":newImgNo
			},
			dataType : "json",
			headers: {
				"OWASP_CSRFTOKEN":"${sessionScope.OWASP_CSRFTOKEN}"
			},
			beforeSend:function(XHR){
				myLoading.show();
			},
			success : function(json){
				if(json.success){
					$('#updateImgNoModal').modal('hide');
					myLoading.hide();
					showTip("提示","图片排序调整成功",2000);
					setTimeout(function(){
						refreshTree();
						loadRight("${e01z1Id}");
					},2000)
				}else{
					showTip("提示", "调整图片顺序失败", 2000);
				}
			},
			error : function(arg1, arg2, arg3){
				showTip("提示","出错了请联系管理员",2000);
			},
			complete : function(XHR, TS){
				myLoading.hide();
			}
		});
	}
	//删除图片
	function deleteImg(imgId,imgNo){
		actionByConfirm1("第“"+imgNo+"”张图片", "${path}/zzb/dzda/mlcl/images/delete/" + imgId+"?OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}", {}, function (data, status) {
			myLoading.show();
			if (data.success == true) {
				showTip("提示","图片成功删除",2000);
				setTimeout(function(){
					myLoading.hide();
					refreshTree();
					loadRight("${e01z1Id}")
				},2000)
			} else {
				myLoading.hide();
				showTip("提示", data.msg, 2000);
			}
		});
	}
	function isNumberTmp(str) {
		var Letters = "0123456789";
		var Letters2 = "-0123456789";
		if(str.length==0)
			return false;

		//对首位进行附加判断
		if(Letters2.indexOf(str.charAt(0)) == -1){
			return false;
		}else{
			for (i = 1; i < str.length; i++) {
				var checkChar = str.charAt(i);
				if (Letters.indexOf(checkChar) == -1)
					return false;
			}
			return true;
		}
	}

    function daYinDa()
    {
        var imgId = $("#showImgId").val();
        var imgNo = $("#showImgNo").val();
        var clmc = $("#showClmc").val();
        var damc = $("#showDamc").val();

        if(imgId==""){
            showTip("提示","没有可打印图片",2000);
        }else{
            $.ajax({
                url : "${path}/zzb/dzda/mlcl/images/ajax/printImg",
                type : "post",
                data : {
                    "imgId":imgId,
                    "imgNo":imgNo,
                    "clmc":clmc,
                    "damc":damc,

                },
                dataType : "json",
                headers: {
                    "OWASP_CSRFTOKEN":"${sessionScope.OWASP_CSRFTOKEN}"
                },
                beforeSend:function(XHR){
                    myLoading.show();
                },
                success : function(json){
                    if(json.success){
                        showTip("提示","图片已成功打印",2000);
                    }else{
                        showTip("提示", "未找到打印机或其他异常打印失败", 2000);
                    }
                },
                error : function(arg1, arg2, arg3){
                    showTip("提示","出错了请联系管理员",2000);
                },
                complete : function(XHR, TS){
                    myLoading.hide();
                }
            });
        }
    }

    function xiaZaiDa(){
        var imgNo = $("#showImgNo").val();
        var clmc = $("#showClmc").val();
        var damc = $("#showDamc").val();
	    var imgId = $("#showImgId").val();
	    if(imgId==""){
            showTip("提示","没有可下载图片",2000);
		}else{
            window.open("${path}/zzb/dzda/mlcl/images/ajax/downImg?imgId="+imgId+"&imgNo="+imgNo+"&clmc="+clmc+"&damc="+damc+"&OWASP_CSRFTOKEN=${sessionScope.OWASP_CSRFTOKEN}");
		}
	}

</script>