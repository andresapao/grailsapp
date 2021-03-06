package jogoanimais

class AnimaisController {
	def animaisService
	def previousNode	

    def index() { 
		def animalsTreeObj =  AnimaisTreeMap.list()

		def curQuestion
		def currentNode
		def isFirstQuestion

		previousNode = request.index
		currentNode = request.curNode
		curQuestion = request.curQuestion
		isFirstQuestion = false

		if(curQuestion == null)
		{
			curQuestion = "Pense em um animal"
			isFirstQuestion = true;
			session.setAttribute('traceability', null)						
		}
		else
		{
			isFirstQuestion = false;
		}

		render(view: "savedQuestions", model: [animalList: animalsTreeObj, 
									 		   curIndex: currentNode, 
									 		   curQuestion: curQuestion, 
									 		   isFirstQuestion: isFirstQuestion,
									 		   previousQuestions: session.getAttribute('traceability')])		
	}
	
	def loadNextStep()
	{
		def nextNode
		def curNode = params.int('index')
		def curQuestion
		def answersTrace
/*
		log.info "addNode - params"
		log.info params
		log.info curNode		
*/
		def userChoice = params.int('optionsForQuestion')
		nextNode = animaisService.getNextNode(curNode, userChoice)
/*		
		log.info "addNode - retorno"
		log.info nextNode
*/

		if(nextNode != null)
		{
			curQuestion = animaisService.mountQuestionByNodeInfo(nextNode)
			answersTrace = saveTraceability(curNode, userChoice)

			forward(action:'index', model: [curNode: nextNode.id,
											previousNode: curNode, 
											curQuestion: curQuestion])					
		}
		else
		{
			def finished = false
			if(userChoice == 1)
			{
				curQuestion = "Acertei"				
				finished = true
			}
			else
			{
				curQuestion = "Em que animal voce pensou?"
			}
			answersTrace = saveTraceability(curNode, userChoice)
			render(view: "lastQuestion", model: [curQuestion:curQuestion, rootNode: curNode, finished: finished])
		}
	}
	def saveTraceability(curNode, userChoice)
	{
		if(curNode != null)
		{
			def previousObj = session.getAttribute('traceability')
			def objReturned = animaisService.fillPreviousQuestions(curNode, userChoice, previousObj)
			session.setAttribute('traceability', objReturned)
			return objReturned
		}
		return null
	}

	def submitFinalAnswer()
	{
		def previousAnimal = animaisService.getAnimalDesc(params.int('rootNode'))
		def curQuestion = "Um(a) " + params.finalAnswer + " ______,  mas um(a) " + previousAnimal.nodeDescription + " não. "
		render(view: "tipAnswer", model: [rootNode: params.int('rootNode'), curQuestion: curQuestion, 
										  finalAnswer: params.finalAnswer])	
	}
	def reset()
	{
		animaisService.reset()
		animaisService.initializeDB()
		redirect(action:'index')		
	}
	def submitTipForAnswer()
	{
		animaisService.insertNodesToAnswer(params.long('rootNode'), params.tipToFinalAnswer, params.finalAnswer)
		redirect(action:'index')
	}	
	def backToStart()
	{
		redirect(action:'index')		
	}
}
