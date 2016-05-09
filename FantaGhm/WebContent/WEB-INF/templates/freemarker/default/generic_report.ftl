<html>
	<head>
		<title>${bean.functionDescr!}</title>
	</head>
	<body>
<#list bean.dataOut.entities as msg>
	<h1>${msg.value!}</h1>
</#list>
	</body>
</html> 	