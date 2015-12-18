package jogoanimais

import grails.transaction.Transactional

@Transactional
class AnimaisService {


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

            return AnimaisTreeMap.get(result[0])
    	}
        else
        {
            nodeId = previousNodeId            
        }

/*
	   log.info 'getNextNode1'
	   log.info nodeId
       log.info previousNodeId       
*/
		def curObj = AnimaisTreeMap.get(nodeId)    	
        return curObj."$answerField"
    }
    def fillPreviousQuestions(curNode, optionAnswered, previousQuestions)
    {

    	if(previousQuestions == null)
    	{
    		previousQuestions = []
    	}
        def obj = AnimaisTreeMap.get(curNode)
/*
        log.info 'fillPrevious'        
        log.info curNode
        log.info obj        
*/

    	previousQuestions.push('question':obj.nodeDescription, 'answer': getDescOptionAnswer(optionAnswered))
/*
        log.info "retornando previousObj"
        log.info previousQuestions
*/
        return previousQuestions
    }
    def getDescOptionAnswer(answer)
    {
        if(answer == 1)
        {
            return "Sim"
        }
        else
        {
            return "Não"
        }
    }
    def insertNodesToAnswer(rootId, tipToFinalAnswer, finalAnswer)
    {
        def rootObj = AnimaisTreeMap.get(rootId)
        def previousNodeQuery = AnimaisTreeMap.where{noAnswerNode.id == rootId || yesAnswerNode.id == rootId}
        def previousNode = previousNodeQuery.get()
/*
        log.info previousNode.yesAnswerNode.id        
        log.info previousNode.noAnswerNode.id                
        log.info rootId
*/
//        log.info rootId.getClass()
//        log.info previousNode.yesAnswerNode.id.getClass()        

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

        def finalAnswerRow = new AnimaisTreeMap(nodeDescription: finalAnswer, 
                                                nodeInfo: AnimalInfoTypeEnum.ANIMAL,
                                                yesAnswerNode:null, 
                                                noAnswerNode:null)
        def finalAnswerPersisted = finalAnswerRow.save(failOnError: true)

        def tipAnswerRow = new AnimaisTreeMap(nodeDescription: tipToFinalAnswer, 
                                              nodeInfo: AnimalInfoTypeEnum.ACTION,
                                              yesAnswerNode:finalAnswerPersisted, 
                                              noAnswerNode:rootObj)
        def tipAnswerPersisted = tipAnswerRow.save(failOnError: true)

        previousNode."$answerField" = tipAnswerPersisted
        previousNode.save(failOnError: true)
    }
    def reset()
    {

        //AnimaisTreeMap.executeUpdate("delete from AnimaisTreeMap")
        def list = AnimaisTreeMap.list()
        list.each { row ->
            row.delete()
        }        
    }
    def mountQuestionByNodeInfo(nextNode)
    {
        def curQuestion
        if(nextNode.nodeInfo == AnimalInfoTypeEnum.ANIMAL)
        {
            curQuestion = "O animal que você pensou é " + nextNode.nodeDescription + " ?"               
        }
        else
        {
            curQuestion = "O animal que você pensou " + nextNode.nodeDescription + " ?"                             
        }
        return curQuestion
    }
    def getAnimalDesc(animalId)
    {
        return AnimaisTreeMap.get(animalId)             
    }
    def initializeDB()
    {
        def noAnswer =  new AnimaisTreeMap(nodeDescription:"macaco", 
                                           nodeInfo: AnimalInfoTypeEnum.ANIMAL,
                                           noAnswerNode:null, 
                                           yesAnswerNode:null)
        noAnswer.save(failOnError: true)
        def yesAnswer =  new AnimaisTreeMap(nodeDescription:"tubarão", 
                                            nodeInfo: AnimalInfoTypeEnum.ANIMAL,
                                            noAnswerNode:null, 
                                            yesAnswerNode:null)
        yesAnswer.save(failOnError: true)           

        new AnimaisTreeMap(nodeDescription:"vive na água", 
                           nodeInfo: AnimalInfoTypeEnum.ACTION,
                           noAnswerNode: noAnswer, 
                           yesAnswerNode:  yesAnswer).
                           save(failOnError: true)                  
    }
    
}
