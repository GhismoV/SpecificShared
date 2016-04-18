<html>
	<head>
		<title>${bean.functionDescr!}</title>
	</head>
	<body>
<#list bean.dataFields.fields as field>
	<h1>${field.id!} : ${field.value!}</h1>
</#list>
	</body>
</html> 	