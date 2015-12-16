
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Jogo dos Animais</title>
	</head>
	<body>
		<g:form controller="animais" action="addNode" params="['index': curIndex]">	
			<div>${curIndex} - ${curQuestion}</div>
			
<!--			
			<g:textField name="myField" value="${myValue}" />

			<g:actionSubmit value="OK, próximo"  />
-->

				<g:radioGroup name="optionsForQuestion" labels="['Sim','Nao']" values="[1,0]" >
					<p>${it.radio} ${it.label} </p>
				</g:radioGroup>			
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
				<div id="finalAnswer">
					<label for="questionToUser">Questão:</label>
					<g:textField name="questionToUser" maxlength="50"/>
				</div>
			</g:if>						
			<g:submitButton name="Próximo" />
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

