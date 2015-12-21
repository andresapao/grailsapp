package jogoanimais

class AnimaisController {
	def animaisService
	def previousNode	

    def index() { 
		def animalsTreeObj =  AnimaisTreeMap.list()

		def curQuestion
		def currentNode
		def isFirstQuestion

		previousNode = params.index
		currentNode = params.int('curNode')
		curQuestion = params.curQuestion
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



        log.info 'enviando pra view'
        log.info params.previousQuestions
        log.info animalsTreeObj.getClass()
        log.info params.previousQuestions.getClass()


		render(view: "show", model: [animalList: animalsTreeObj, 
									 curIndex: currentNode, 
									 curQuestion: curQuestion, 
									 isFirstQuestion: isFirstQuestion,
									 previousQuestions: params.list('previousQuestions')])		
	}
	
	def loadNextStep()
	{
		def nextNode
		def curNode = params.int('index')
		def curQuestion
		def previousQuestions
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


			if(curNode != null)
			{
				def previousObj = session.getAttribute('traceability')
//				log.info 'recuperando da sessao'
//				log.info previousObj
				previousQuestions = animaisService.fillPreviousQuestions(curNode, userChoice, previousObj)
				log.info 'gravando na sessao ' 
				log.info previousQuestions
				session.setAttribute('traceability', previousQuestions)

			}

			curQuestion = animaisService.mountQuestionByNodeInfo(nextNode)
        log.info 'enviando pra index'
        log.info previousQuestions
        log.info previousQuestions.getClass()

			forward(action:'index', params: ['curNode': nextNode.id,
											 'previousNode': curNode, 
											 'curQuestion': curQuestion,
											'previousQuestions': previousQuestions])			
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
