
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="bootstrap-main"/>		
		<title>Jogo dos Animais</title>
	</head>
	<body>
		<g:form controller="animais" params="['rootNode': rootNode, 'finalAnswer':finalAnswer]">	
			<h3>${curQuestion} </h3>			

			<g:textField name="tipToFinalAnswer" value="${tipText}"/>					
			<g:actionSubmit class="btn btn-primary" action="submitTipForAnswer" value="Finalizar" />						

		</g:form>
	</body>
</html>

