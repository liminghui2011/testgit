<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="common/common_tags.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${entityNameZh}信息列表</title>
</head>
<body>
<div id="accordion" class="accordion">
	<div class="accordion-group">
		<div class="accordion-heading">
			<a href="#logAccordion" class="accordion-toggle" data-toggle="collapse" data-parent="#accordion">
				<i class="icon-th-large icon-white"></i>
				<span>${entityNameZh}信息列表</span>
				<i class="icon-chevron-down icon-white pull-right"></i>
			</a>
		</div>
		<div id="logAccordion" class="accordion-body in">
			<div class="accordion-inner">
				<form:form action="list.do" method="post" modelAttribute="${entityNameFirstLower}Req" cssClass="form-inline">	
					<label>名称：
						<form:input path="name" cssClass="input-small" cssStyle="width:120px;"/>
					</label>&nbsp;&nbsp;
					<form:select path="status" cssStyle="width:120px;" >						
						<form:option value="">-状态-</form:option>
						<form:option value="online">在线</form:option>
						<form:option value="offline">离线</form:option>
					</form:select>&nbsp;&nbsp;
									
					<form:hidden path="current"/>
					<form:hidden path="pageSize"/>
					<div class="btn-group">
						<a href="javascript:$('#${entityNameFirstLower}Req').submit()" class="btn">查询</a>
						<a href="preAdd.do" class="btn">新增</a>
	                </div>
				</form:form>			
				<table class="table table-hover table-condensed">
					<thead>
						<tr>
						<#list fieldList as item>
							<th>${item.nameDesc}</th>
						</#list>
							<th width="15%">操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${r"${pb.dataList}"}" var="item" varStatus="step">
							<tr>
						<#list fieldList as var>
							<#if var.type == 'DateTime'>
								<td><joda:format value="${r"${item."}${var.name}${r"}"}" pattern="yyyy-MM-dd"/></td>
							<#else>
								<th>${r"${item."}${var.name}${r"}"}</th>
							</#if>
						</#list>
								<td>
									<div class="btn-group">
										<!-- 修改 -->
										<a href="preUpdate.do?id=${r"${item.id}"}" class="btn">修改</a>
										<a href="javascript: remove(${r"${item.id}"})" class="btn">删除</a>
					                </div>					               									
								</td>
							</tr>
						</c:forEach>
						<tr>
							<td colspan="${fieldList?size+1}" form-id="${entityNameFirstLower}Req" class="paginationPanel">${r"${pb.fullDisplay}"}</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>

<div id="modal" class="modal hide fade">
	<div class="modal-header">
	  <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	  <h3>删除作者</h3>
 	</div>
	<div class="modal-body">
	  <p>真的要删除该${entityNameZh}记录吗？</p>
	</div>
    <div class="modal-footer">
      <a href="#" class="btn btn-primary" data-dismiss="modal">否</a>
      <a class="btn" href="javascript: remove()">是</a>
    </div>
</div>

<script type="text/javascript">
function remove(id){
	
	if(id){
		$('#modal').modal();
		$('#modal').data('id', id);
	}else{
		$('#modal').modal('hide');
		location.href = 'remove.do?id='+$('#modal').data('id');
	}
}
</script>
</body>
</html>