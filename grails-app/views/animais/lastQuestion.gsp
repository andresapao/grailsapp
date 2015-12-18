
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="bootstrap-main"/>		
		<title>Jogo dos Animais</title>
	</head>
	<body>
		<nav class="navbar navbar-inverse">
			<a class="navbar-brand" href="#">Jogo dos Animais</a>			
		</nav>			
		<div class="container">
			<div class="row">			
				<div class="col-md-12 col-xs-12 col-lg-12 col-sm-12">
					<div>${finalAnswer}</div>
					<g:form controller="animais" params="['rootNode': rootNode, 'finalAnswer': finalAnswer]">	
						<div>${curQuestion}</div>		

						<g:if test="${finished}">			
							<g:actionSubmit class="btn btn-primary" action="backToStart" value="Voltar" />
						</g:if>			
						<g:if test="${!finished}">
							<g:if test="${!showDivTip}">			
								<g:textField name="finalAnswer" value="${finalAnswer}"/>
								<g:actionSubmit class="btn btn-primary" action="submitFinalAnswer" value="OK" />					
							</g:if>								
							<g:if test="${showDivTip}">			
								<g:textField name="tipToFinalAnswer" value="${tipText}"/>					
								<g:actionSubmit class="btn btn-primary" action="submitTipForAnswer" value="Finalizar" />						
							</g:if>
						</g:if>
					</g:form>
				</div>				
			</div>
		</div>		

	</body>
</html>

