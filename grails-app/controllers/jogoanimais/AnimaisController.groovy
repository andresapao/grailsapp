package jogoanimais

class AnimaisController {
    def index() { 
		def  animalsTreeObj =  AnimaisTreeMap.list()

		log.debug "chaves"
		log.debug animalsTreeObj
		
		render(view: "show", model: [animalList: animalsTreeObj])		
//		render "Hello World!"	
	}
	
	def addNode()
	{
		
		log.info "add node"
		log.info params
		/*
		println 'addNode method'
		render 'ok'			
*/
	}

}
