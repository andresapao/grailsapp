package jogoanimais

import grails.transaction.Transactional

@Transactional
class AnimaisService {
	def static previousQuestions
    def serviceMethod() {

    }
    def getNextNode(previousNodeId, answer)
    {
    	def nodeId

    	if(previousNodeId == null || previousNodeId == '')
    	{
    		nodeId = 1
    	}
    	else
    	{
    		nodeId = previousNodeId
    	log.info 'getNextNode1'
    	log.info nodeId

    		def nextObj = AnimaisTreeMap.get(nodeId)    	
	    	log.info 'nextObj'
    		log.info nextObj    		
    		if(nextObj != null)
    		{
		    	if (answer == 1)
		    	{
		    		nodeId = nextObj.yesAnswerNode
		    	}
		    	else
		    	{
		    		nodeId = nextObj.noAnswerNode
		    	}    			
    		}
    		else
    		{
    			nodeId = null
    		}

    	}
    	log.info 'getNextNode'
    	log.info nodeId
    	if(nodeId != null)
    	{
	    	def obj = AnimaisTreeMap.get(nodeId)
	    	[obj]
    	}
    	else
		{
			[]

	    }
    }
    def fillPreviousQuestions(curQuestion, optionAnswered)
    {
    	if(previousQuestions == null)
    	{
    		previousQuestions = []
    	}
    	previousQuestions.push('question':curQuestion, 'answer':'sim')
    	[previousQuestions]
    	
    }
}
