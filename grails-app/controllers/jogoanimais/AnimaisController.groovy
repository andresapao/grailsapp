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

		log.info "index - params"
		log.info params
		log.info currentNode

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
	
	def addNode()
	{
		

		def nextNode
		def curNode = params.int('index')
		def curQuestion

		log.info "addNode - params"
		log.info params
		log.info curNode		

		def userChoice = params.int('optionsForQuestion')
		nextNode = animaisService.getNextNode(curNode, userChoice)
		log.info "addNode - retorno"
		log.info nextNode

		if(nextNode != null)
		{
			forward(action:'index', params: ['curNode': nextNode.id, 'previousNode': curNode, curQuestion: nextNode.nodeDescription])			
		}
		else
		{
			curQuestion = "Em que animal voce pensou"
			render(view: "lastQuestion", model: [curQuestion:curQuestion, rootNode: curNode, userChoice: userChoice])
		}
	}
	def submitFinalAnswer()
	{
		log.info 'finalAnswer'
		log.info params
		def tipText = "um " + params.finalAnswer + " ______ "
		render(view: "lastQuestion", model: [showDivTip: true, rootNode: params.int('rootNode'), tipText: tipText, finalAnswer: params.finalAnswer])	
	}
	def submitTipForAnswer()
	{
		log.info 'tip'
		log.info params
		animaisService.insertNodesToAnswer(params.long('rootNode'), params.tipToFinalAnswer, params.finalAnswer, params.int('userChoice'))
		render(action:'index')
	}	

}
