package jogoanimais

class AnimaisController {
	def animaisService
	def previousNode	

    def index() { 
		def animalsTreeObj =  AnimaisTreeMap.list()
		def curQuestion
		def currentNode
		def previousQuestions


//		currentNode = animaisService.getCurrentNode(params)

		log.info "index - params"
		log.info params


		previousNode = params.index
		currentNode = params.curNode
		curQuestion = params.curQuestion
		if(curQuestion == null)
		{
			curQuestion = "Pense em um animal"
		}
		if(params.lastQuestion)
		{
			lastQuestion = "Em que animal voce pensou"
		}
/*
		if(previousNode != null && previousNode != '')
		{
			previousQuestions = animaisService.fillPreviousQuestions(curQuestion, params.optionsForQuestion)
		}
*/
		render(view: "show", model: [animalList: animalsTreeObj, curIndex: currentNode, curQuestion: curQuestion, previousQuestions:previousQuestions])		
	}
	
	def addNode()
	{
		
		log.info "addNode - params"
		log.info params

		def nextNode
		def curNode = params.int('index')

		nextNode = animaisService.getNextNode(curNode, params.questionToUser)
		log.info "addNode - retorno"
		log.info nextNode

		if(nextNode != null)
		{
			forward(action:'index', params: ['curNode': nextNode.nodeId, 'previousNode': curNode, curQuestion: nextNode.nodeDescription])			
		}
		else
		{
			forward(action:'index', params: ['lastQuestion': true])			
		}


	}

}
