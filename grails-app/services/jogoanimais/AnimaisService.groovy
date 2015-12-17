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
            def criteria = AnimaisTreeMap.createCriteria()
            def result = criteria.list {
                isNotNull('yesAnswerNode')
                isNotNull('noAnswerNode')
                projections {
                    min('id')
                }
            }            
            log.info 'primeira'
            log.info result
            return AnimaisTreeMap.get(result[0])
    	}
        else
        {
            nodeId = previousNodeId            
        }


	   log.info 'getNextNode1'
	   log.info nodeId
       log.info previousNodeId       

		def curObj = AnimaisTreeMap.get(nodeId)    	
    	log.info 'curObj'
		log.info curObj    		
        return curObj."$answerField"

    }
    def fillPreviousQuestions(curQuestion, optionAnswered)
    {
    	if(previousQuestions == null)
    	{
    		previousQuestions = []
    	}
    	previousQuestions.push('question':curQuestion, 'answer':'sim')
    	return previousQuestions
    	
    }
    def insertNodesToAnswer(rootId, tipToFinalAnswer, finalAnswer, userChoice)
    {
        def rootObj = AnimaisTreeMap.get(rootId)
//        def answerField = getCorrespondingAnswerField(userChoice)

     
        def previousNodeQuery = AnimaisTreeMap.where{noAnswerNode.id == rootId || yesAnswerNode.id == rootId}
        def previousNode = previousNodeQuery.get()
        log.info previousNode.yesAnswerNode.id        
        log.info previousNode.noAnswerNode.id                
        log.info rootId
        log.info rootId.getClass()
        log.info previousNode.yesAnswerNode.id.getClass()        

        def answerField
        def idToCompare = previousNode.yesAnswerNode.id
        if(idToCompare == rootId)
        {
            answerField = 'yesAnswerNode'
        }
        else
        {
            answerField = 'noAnswerNode'
        }

        log.info 'previousId'
        log.info previousNode        
        log.info 'rootObj'
        log.info rootObj
        log.info 'answerField'
        log.info answerField                

        def finalAnswerRow = new AnimaisTreeMap(nodeDescription: finalAnswer, yesAnswerNode:null, noAnswerNode:null)
        def finalAnswerPersisted = finalAnswerRow.save(failOnError: true)
        log.info finalAnswerPersisted

        def tipAnswerRow = new AnimaisTreeMap(nodeDescription: tipToFinalAnswer, 
                                              yesAnswerNode:finalAnswerPersisted, 
                                              noAnswerNode:null)
        def tipAnswerPersisted = tipAnswerRow.save(failOnError: true)
        log.info tipAnswerPersisted        

        previousNode."$answerField" = tipAnswerPersisted
        previousNode.save(failOnError: true)
    }
}
