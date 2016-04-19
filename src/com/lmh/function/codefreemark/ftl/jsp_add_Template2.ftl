<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="common/common_tags.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>新增${entityNameZh}信息</title>
</head>
<body>
<div id="accordion" class="accordion">
	<div class="accordion-group">
		<div class="accordion-heading">
			<a href="#addAccordion" class="accordion-toggle" data-toggle="collapse" data-parent="#accordion">
				<i class="icon-th-large icon-white"></i>
				<span>新增${entityNameZh}信息</span>
				<i class="icon-chevron-down icon-white pull-right"></i>
			</a>
		</div>
		<div id="addAccordion" class="accordion-body in">
			<div class="accordion-inner">
				<form:form action="add.do" method="post" modelAttribute="${entityNameFirstLower}" cssClass="form-horizontal">
			<#list fieldList as item>
				<#if item.type == 'DateTime'>
					<div class="control-group">
						<label class="control-label" for="${item.name}">${item.nameDesc}</label>
						<div class="controls">
							<form:input path="${item.name}" onfocus="WdatePicker({isShowWeek:true})" readonly="true"/>
						</div>
					</div>
				<#else>
					<div class="control-group">
						<label class="control-label" for="${item.name}">${item.nameDesc}</label>
						<div class="controls">
							<form:input path="${item.name}" maxlength="16" onkeydown="hideTips('#column_span2')"/>
							<span id="column_span" class="help-inline"></span>
							<span id="column_span2" class="help-inline">${r"${empty code?'':'该值已存在'}"}</span>
						</div>
					</div>
				</#if>
			</#list>
					<div class="form-actions">
						<button type="submit" class="btn btn-primary">提交</button>
						<a href="${r"${returnURI}"}" class="btn">返回</a>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="<w:path/>resources/js/pc.js"></script>
<script type="text/javascript">
	
	jQuery.validator.addMethod('word', function(value, element){
		return /^[a-zA-Z0-9_]+$/.test(value);
	});
	
	$('#${entityNameFirstLower}').validate({
		submitHandler: function(form){
			form.submit();
		},
		rules:{
			property1:"word", //设置规则，只能输入数字和英文字母
			property1:"required" //设置规则，不能为空
		},
		messages:{
			property1:'只能输入数字和英文字母',
			property1:'XX不能为空'
		},
		onfocusout: function(element) {
	        ${r"$(element)"}.valid();
	    },
	    errorElement: 'span',
	    errorClass: 'help-inline',
	   	highlight: function(element, errorClass) {
	   		$("#column_span").hide('hide');
	    	${r"$(element)"}.parents('.control-group').addClass('error');
	  	},
	  	unhighlight: function(element, errorClass, validClass) {
	    	${r"$(element)"}.parents('.control-group').removeClass('error');
	  	}
	});

	function hideTips(tagId){
		${r"$(tagId)"}.hide('hide');
	}
</script>
</body>
</html>