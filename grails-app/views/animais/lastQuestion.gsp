
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="bootstrap-main"/>		
		<title>Jogo dos Animais</title>
	</head>
	<body>
		<g:form controller="animais" params="['rootNode': rootNode]">	
			<h3>${curQuestion} </h3>			

			<g:if test="${finished}">			
				<g:actionSubmit class="btn btn-primary" action="backToStart" value="Voltar" />
			</g:if>			
			<g:if test="${!finished}">
				<g:textField name="finalAnswer" value="${finalAnswer}"/>
				<h6><i>Escreve o nome do animal que você pensou</i></h6>				
				<g:actionSubmit class="btn btn-primary" action="submitFinalAnswer" value="OK" />					
			</g:if>
		</g:form>
	</body>
</html>

