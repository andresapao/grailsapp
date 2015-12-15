package jogoanimais

class AnimaisController {
    def index() { 
		def  animalsTreeObj =  AnimaisTreeMap.list()
/*
		log.debug "chaves"
		log.debug animalsTreeObj
*/		def currentNode
		if(params.currentNode != null)
		{
			currentNode = params.currentNode
		}
		else
		{
			currentNode = 1
		}
		render(view: "show", model: [animalList: animalsTreeObj, curIndex: currentNode])		
//		render "Hello World!"	
	}
	
	def addNode()
	{
		
		log.info "add node"
		log.info params
		render 'OK'

	}

}
