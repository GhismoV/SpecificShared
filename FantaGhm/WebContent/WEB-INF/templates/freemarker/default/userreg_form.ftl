<html>
	<head>
		<title>${bean.functionDescr!}</title>
	</head>
	<body>
	<!--
		context: ${context.app_context_root_path!}
		config: ${context.app_config_path!}
		appname: ${context.app_name!}
		appversion: ${context.app_version!}
		

		app_context_url: ${context.app_context_url!}
		app_servlet_url: ${context.app_servlet_url!}
		app_complete_context_url: ${context.app_complete_context_url!}
		app_complete_servlet_url: ${context.app_complete_servlet_url!}
-->
	
		<h1>${lang.EHTML_LABEL_ERR_CD_009}</h1>
	
		<form id="form1" action="${context.app_complete_servlet_url!}outlogin/userreg/wsend" method="post">
<#list bean.dataFields.fields as field>
	<#if !field.isHidden()!false> 
			<label>${field.label!}</label>
	</#if>
	<input id="${field.id!}" name="${field.id!}" type=<#if field.isHidden()!false>"hidden"<#else>"text"</#if> /> <br/>
</#list>

		<button type="submit" form="form1" value="Submit">Submit</button>
		</form>
	</body>
</html> 	