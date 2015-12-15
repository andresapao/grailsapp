
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Jogo dos Animais</title>
	</head>
	<body>
		<g:form controller="animais" action="addNode" params="['index': curIndex]">	
			<div>Pense em um animal</div>
<!--			
			<g:textField name="myField" value="${myValue}" />

			<g:actionSubmit value="OK, próximo"  />
-->
			
			<div>
				<label for="questionToUser">Questão:</label>
				<g:textField name="questionToUser" maxlength="50"/>
			</div>
			 
			<input type="submit" value="Submit">	
	
			<g:each in="${animalList}" var="row" status="i">
				<h3> ${row.nodeDescription}, ${row.yesAnswerNode}</h3>
				<br/>
			</g:each>		
		</g:form>		
	</body>
</html>

