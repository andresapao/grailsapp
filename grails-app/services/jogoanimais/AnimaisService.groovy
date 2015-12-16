package jogoanimais

import grails.transaction.Transactional

@Transactional
class AnimaisService {

    def serviceMethod() {

    }
    def getCurrentNode(params)
    {
		def currentNode
		if(params.currentNode != null)
		{
			currentNode = params.currentNode
		}
		else
		{
			currentNode = 1
		}    
    	[currentNode]
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
    		def nextObj = AnimaisTreeMap.get(previousNodeId)    	
	    	if (answer == 1)
	    	{
	    		nodeId = nextObj.yesAnswerNode
	    	}
	    	else
	    	{
	    		nodeId = nextObj.noAnswerNode
	    	}
    	}
    	log.info 'getNextNode'
    	log.info nodeId
    	def obj = AnimaisTreeMap.get(nodeId)
    	[obj]
    }

}
