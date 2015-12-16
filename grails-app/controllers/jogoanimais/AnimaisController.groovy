package jogoanimais

class AnimaisController {
	def animaisService
    def index() { 
		def  animalsTreeObj =  AnimaisTreeMap.list()
		def curQuestion
		def currentNode

//		currentNode = animaisService.getCurrentNode(params)
		currentNode = params.curNode
		curQuestion = params.curQuestion
		if(curQuestion == null)
		{
			curQuestion = "Pense em um animal"
		}
		else
		{
			curQuestion = params.curQuestion
		}
		log.info 'index'
		log.info curQuestion
		log.info currentNode
		render(view: "show", model: [animalList: animalsTreeObj, curIndex: currentNode, curQuestion: curQuestion])		
//		render "Hello World!"	
	}
	
	def addNode()
	{
		
		log.info "addNode - params"
		log.info params

		def nextNode

		nextNode = animaisService.getNextNode(params.index, params.questionToUser)
		log.info "addNode - retorno"
		log.info nextNode


//		render 'OK'
		forward(action:'index', params: ['curNode': nextNode.nodeId, 'curQuestion': nextNode.nodeDescription])

	}

}
