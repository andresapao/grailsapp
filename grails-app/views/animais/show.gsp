
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
					<g:form controller="animais" action="addNode" params="['index': curIndex]">	
						<div>${curQuestion}</div>
						<g:if test="${!isFirstQuestion}">			
							<g:radioGroup name="optionsForQuestion" labels="['Sim','Não']" values="[1,0]" value="0" >
								<p>${it.radio} ${it.label} </p>
							</g:radioGroup>			
						</g:if>
						<g:submitButton class="btn btn-primary" name="addNode" value="Próximo" />						
						<g:actionSubmit class="btn btn-link" action="reset" value="Restaurar Padrões" />		

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

			<!--						 
						<input type="submit" value="Submit">	
				
						<g:each in="${animalList}" var="row" status="i">
							<h3> ${row.nodeDescription}, ${row.yesAnswerNode}</h3>
							<br/>
						</g:each>		
					-->
					</g:form>		
				</div>				
			</div>
		</div>		
		
	</body>
</html>

