
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
			<g:if test="${previousQuestions}">
				<br>
				<br>
				<div class="panel panel-default">
				  <div class="panel-heading">Respostas Anteriores</div>
				  <div class="panel-body">
					<div id="previousSteps">
						<g:each in="${previousQuestions}" var="row" status="i">
							<ul class="list-group">
							  <li class="list-group-item">
							    <span class="badge">${row.answer}</span>
							    ${row.question}
							  </li>
							</ul>
						</g:each>		
					</div>

				  </div>
				</div>				
			</g:if>			

		</g:form>		

	</body>
</html>

