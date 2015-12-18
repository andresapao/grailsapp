package jogoanimais

class AnimaisController {
	def animaisService
	def previousNode	

    def index() { 
		def animalsTreeObj =  AnimaisTreeMap.list()
		def curQuestion
		def currentNode
		def previousQuestions
		def isFirstQuestion


		previousNode = params.index
		currentNode = params.int('curNode')
		curQuestion = params.curQuestion
		isFirstQuestion = false
/*
		log.info "index - params"
		log.info params
		log.info currentNode
*/
		if(curQuestion == null)
		{
			curQuestion = "Pense em um animal"
			isFirstQuestion = true;
		}
		else
		{
			isFirstQuestion = false;
		}

			render(view: "show", model: [animalList: animalsTreeObj, 
										 curIndex: currentNode, 
										 curQuestion: curQuestion, 
										 isFirstQuestion: isFirstQuestion,
										 previousQuestions:previousQuestions])		
/*
		if(previousNode != null && previousNode != '')
		{
			previousQuestions = animaisService.fillPreviousQuestions(curQuestion, params.optionsForQuestion)
		}
*/
	}
	
	def loadNextStep()
	{
		def nextNode
		def curNode = params.int('index')
		def curQuestion
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
			
//			animaisService.setTraceability(curQuestion, userChoice)

			curQuestion = animaisService.mountQuestionByNodeInfo(nextNode)
			forward(action:'index', params: ['curNode': nextNode.id, 'previousNode': curNode, curQuestion: curQuestion])			
		}
		else
		{
			def finished = false
			if(userChoice == 1)
			{
				curQuestion = "Acertei de novo"				
				finished = true
			}
			else
			{
				curQuestion = "Em que animal voce pensou?"
			}

			render(view: "lastQuestion", model: [curQuestion:curQuestion, rootNode: curNode, finished: finished])
		}
	}
	def submitFinalAnswer()
	{
/*
		log.info 'finalAnswer'
		log.info params
*/
		def previousAnimal = animaisService.getAnimalDesc(params.int('rootNode'))
		def curQuestion = "um(a) " + params.finalAnswer + " ______  mas um(a) " + previousAnimal.nodeDescription + " não "
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

		log.info 'tipAnswer'
		log.info params

		animaisService.insertNodesToAnswer(params.long('rootNode'), params.tipToFinalAnswer, params.finalAnswer)
		redirect(action:'index')
	}	
	def backToStart()
	{
		redirect(action:'index')		
	}
}
