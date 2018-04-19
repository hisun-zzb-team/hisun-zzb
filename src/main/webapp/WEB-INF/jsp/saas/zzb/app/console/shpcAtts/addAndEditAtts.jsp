<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/inc/servlet.jsp" %>
<%@include file="/WEB-INF/jsp/inc/taglib.jsp" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<div id="jsonDataFormGroup" class="control-group">
	<div class="portlet-body">
		<form class="form-horizontal" id="importForm" enctype="multipart/form-data">
			<div class="actions fr" style="margin-bottom: 5px;">
				<input type="hidden" name="shpcId"  value="${shpcId }"/>
				<div class="clearfix fr">
						<span class="controllerClass btn green file_but" >
								<i class="icon-circle-arrow-up"></i>添加附件
								<input class="file_progress" type="file" name="attachFile" id="btn-browseTemplate">
						</span>
					<button type="button" class="btn btn-default" data-dismiss="modal"><i class='icon-remove-sign'></i> 关闭</button>
				</div>

			</div>
			<div class="dataTables_wrapper form-inline">
				<table class="table table-striped table-bordered table-hover dataTable table-set" id="jsonDataFormTable">
					<tr>
						<th>附件</th>
						<th width="80px">操作</th>
					</tr>

					<c:forEach items="${shpcAttsVos}" var="vo" varStatus="status">
						<tr style="text-overflow:ellipsis;" id="jsonDataForm_${status.index}">
							<td><a href="javascript:fileDown('${vo.id}')"><c:out value="${vo.filename}"></c:out></a></td>

							<td>
								<a href="javascript:void(0)" class="" onclick="del('${vo.id}','${vo.filename}')">删除</a>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<div class="control-group mybutton-group" style="text-align: right;margin-top: 20px">
				<%--<button type="button" class="btn green"  onclick="subimtCiObjectAttrExt()" id="listBtnOk"><i class="icon-ok"></i> 确定</button>--%>

			</div>
		</form>
	</div>

</div>

<script>
	(function(){
		App.init();

		$("#btn-browseTemplate").bind("change",function(evt){
			if($(this).val()){
				ajaxSubmit();
			}
			$(this).val('');
		});

		function ajaxSubmit(){
			var fileInput = document.getElementById("btn-browseTemplate");
			if(fileInput.files.length>0){
				var name = fileInput.files[0].name
				var arr = name.split(".");
				if(arr.length<2 || !(arr[arr.length-1]=="doc" || arr[arr.length-1]=="docx"|| arr[arr.length-1]=="DOC"|| arr[arr.length-1]=="DOCX")){
					showTip("提示","请上传word文件",2000);
					return;
				}
			}else{
				showTip("提示","请选择文件上传",2000);
				return;
			}
			//hideErrorMsg();
			$("#importForm").ajaxSubmit({
				url : "${path }/zzb/app/console/shpcAtts/ajax/uploadAtt?shpcId=${shpcId}",
				type : "post",
				headers:{
					OWASP_CSRFTOKEN:"${sessionScope.OWASP_CSRFTOKEN}"
				},
//				beforeSend:function(XHR){
//					myLoading.show();
//				},
				success : function(json){
					if(json.code == 1){
						showTip("提示","操作成功",2000);
						addAtts("${shpcId}");
						//loadCiList(ciObjectId);
						<%--window.location.href="${path }/zzb/app/console/Sha01/list?shpcId=${shpcId}";--%>
					}else if(json.code == -1){
						showTip("提示", json.message, 2000);
					}else{
						showTip("提示","出错了,请检查网络!",2000);
					}
				},
				error : function(arg1, arg2, arg3){
//					showTip("提示","出错了,请检查网络!",500);
				},
				complete : function(XHR, TS){
					myLoading.hide();
				}
			});
		}
	})();
	var del = function(id,itemName){
		actionByConfirm1(itemName, "${path}/zzb/app/console/shpcAtts/delete/" + id,{} ,function(data,status){
			if (data.success == true) {
				showTip("提示","删除成功", 2000);
				setTimeout(addAtts('${shpcId}'),2000);
				setTimeout(myLoading.hide(),2000);

			}else{
				showTip("提示", data.message, 2000);
			}
		});
	};
	function fileDown(id) {
		window.open("${path }/zzb/app/console/shpcAtts/ajax/down?id="+id);
	}
</script>