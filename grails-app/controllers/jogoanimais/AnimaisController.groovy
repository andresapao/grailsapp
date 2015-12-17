package jogoanimais

class AnimaisController {
	def animaisService
	def previousNode	

    def index() { 
		def animalsTreeObj =  AnimaisTreeMap.list()
		def curQuestion
		def currentNode
		def previousQuestions
		def isLastQuestion


		previousNode = params.index
		currentNode = params.int('curNode')
		curQuestion = params.curQuestion

		log.info "index - params"
		log.info params
		log.info currentNode

		if(curQuestion == null)
		{
			curQuestion = "Pense em um animal"
		}

		if(params.lastQuestion)
		{
			curQuestion = "Em que animal voce pensou"
			render(view: "lastQuestion", model: [curQuestion:curQuestion])		

		}
		else
		{
			render(view: "show", model: [animalList: animalsTreeObj, 
										 curIndex: currentNode, 
										 curQuestion: curQuestion, 
										 lastQuestion: isLastQuestion,
										 previousQuestions:previousQuestions])		
		}
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

		nextNode = animaisService.getNextNode(curNode, params.int('optionsForQuestion'))
		log.info "addNode - retorno"
		log.info nextNode

		if(nextNode != null)
		{
			forward(action:'index', params: ['curNode': nextNode.nodeId, 'previousNode': curNode, curQuestion: nextNode.nodeDescription])			
		}
		else
		{
			curQuestion = "Em que animal voce pensou"
			render(view: "lastQuestion", model: [curQuestion:curQuestion, rootNode: curNode])
		}
	}
	def submitFinalAnswer()
	{
		log.info 'finalAnswer'
		log.info params
		render(view: "lastQuestion", model: [showDivTip: true, rootNode: params.int('rootNode'), finalAnswer: params.finalAnswer])	
	}
	def submitTipForAnswer()
	{
		log.info 'tip'
		log.info params
		animaisService.insertNodesToAnswer(params.int('rootNode'), params.tipToFinalAnswer, params.finalAnswer)
		render 'ok'
	}	

}
