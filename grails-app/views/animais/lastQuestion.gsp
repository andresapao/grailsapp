
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Jogo dos Animais</title>
	</head>
	<body>
		<g:form controller="animais" params="['rootNode': rootNode, 'finalAnswer':finalAnswer, 'userChoice': userChoice]">	
			<div>${curQuestion}</div>		
			<div>${rootNode}</div>
				<g:if test="${!showDivTip}">			
					<g:textField name="finalAnswer" value="${finalAnswer}"/>
					<g:actionSubmit action="submitFinalAnswer" value="OK" />					
				</g:if>								
				<g:if test="${showDivTip}">			
					<g:textField name="tipToFinalAnswer" value="${tipText}"/>					
					<g:actionSubmit action="submitTipForAnswer" value="Finalizar" />						
				</g:if>
		</g:form>
	</body>
</html>

