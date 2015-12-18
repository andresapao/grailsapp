
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="bootstrap-main"/>		
		<title>Jogo dos Animais</title>
	</head>
	<body>
		<g:form controller="animais" action="loadNextStep" params="['index': curIndex]">	
			<h3>${curQuestion} </h3>			
			<br>
			<g:if test="${!isFirstQuestion}">			
				<g:radioGroup name="optionsForQuestion" labels="['Sim','Não']" values="[1,0]" value="0" >
					<p>${it.radio} ${it.label} </p>
				</g:radioGroup>			
			</g:if>
			<g:submitButton class="btn btn-primary" name="loadNextStep" value="Próximo" />						
			<g:actionSubmit class="btn btn-link" action="reset" value="Restaurar Padrões" />		
${previousQuestions}

			<g:if test="${previousQuestions}">

				<div id="previousSteps">
					<g:each in="${previousQuestions}" var="row" status="i">
						<div>
							<label>${row.question}</label>
							<label>${row.answer}</label>							
						</div>
						<br/>
					</g:each>		
				</div>
			</g:if>			


		</g:form>		

	</body>
</html>

