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
			curQuestion = "O animal que você pensou " + nextNode.nodeDescription + " ?"
			forward(action:'index', params: ['curNode': nextNode.id, 'previousNode': curNode, curQuestion: curQuestion])			
		}
		else
		{
			if(params.int('optionsForQuestion') == 1)
			{
				curQuestion = "Acertei de novo"				
			}
			else
			{
				curQuestion = "Em que animal voce pensou?"				
			}

			render(view: "lastQuestion", model: [curQuestion:curQuestion, rootNode: curNode])
		}
	}
	def submitFinalAnswer()
	{
		log.info 'finalAnswer'
		log.info params

		def curQuestion = "um " + params.finalAnswer + " ______  mas um  não "
		render(view: "lastQuestion", model: [showDivTip: true, rootNode: params.int('rootNode'), 
											 curQuestion: curQuestion, finalAnswer: params.finalAnswer])	
	}
	def reset()
	{
		animaisService.reset()
		redirect(action:'index')		
	}
	def submitTipForAnswer()
	{
		log.info 'tip'
		log.info params

		animaisService.insertNodesToAnswer(params.long('rootNode'), params.tipToFinalAnswer, params.finalAnswer)
		redirect(action:'index')
	}	

}
