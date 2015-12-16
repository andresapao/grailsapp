package jogoanimais

import grails.transaction.Transactional

@Transactional
class AnimaisService {
	def static previousQuestions

    def serviceMethod() {

    }
    def getCorrespondingAnswerField(answerValue)
    {
        return (answerValue == 1 ? 'yesAnswerNode' : 'noAnswerNode')
    }
    def getNextNode(previousNodeId, answer)
    {
    	def nodeId
        String answerField = getCorrespondingAnswerField(answer)

    	if(previousNodeId == null)
    	{
            return AnimaisTreeMap.get(1)
    	}
        nodeId = previousNodeId

	   log.info 'getNextNode1'
	   log.info nodeId
       log.info previousNodeId       
       log.info answerField              

		def nextObj = AnimaisTreeMap.get(nodeId)    	
    	log.info 'nextObj'
		log.info nextObj    		
        nodeId = nextObj."$answerField"


    	log.info 'getNextNode2'
    	log.info nodeId
    	if(nodeId != null)
    	{
	    	def obj = AnimaisTreeMap.get(nodeId)
	    	return obj
    	}
    	else
		{
			return null
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
