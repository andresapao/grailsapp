
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Jogo dos Animais</title>
	</head>
	<body>
		<g:form controller="animais" action="addNode" params="['index': curIndex]">	
			<div>${curIndex} - ${curQuestion}</div>
			<g:if test="${!lastQuestion}">			
				<g:radioGroup name="optionsForQuestion" labels="['Sim','Nao']" values="[1,0]" value="0" >
					<p>${it.radio} ${it.label} </p>
				</g:radioGroup>			
			</g:if>
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
			<g:if test="${lastQuestion}">			
				<div id="finalStep">
					<g:textField name="finalAnswer" value="${finalAnswer}"/>
					<g:if test="${finalAnswer}">			
						<g:textField name="tipToFinalAnswer"/>					
					</g:if>
					<g:submitButton name="submitFinalAnswer" value="Finalizar" />
				</div>
			</g:if>						
			<g:if test="${!lastQuestion}">						
				<g:submitButton name="addNode" value="Próximo" />			
			</g:if>							

<!--						 
			<input type="submit" value="Submit">	
	
			<g:each in="${animalList}" var="row" status="i">
				<h3> ${row.nodeDescription}, ${row.yesAnswerNode}</h3>
				<br/>
			</g:each>		
		-->
		</g:form>		
	</body>
</html>

