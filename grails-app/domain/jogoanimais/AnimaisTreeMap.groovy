package jogoanimais

import grails.rest.*

@Resource(uri='/animals', formats='json')

class AnimaisTreeMap {

    static final int ANIMAL  = 1
    static final int ACTION = 2

   String nodeDescription
   int nodeInfo
   AnimaisTreeMap yesAnswerNode
   AnimaisTreeMap noAnswerNode
   
   	static constraints = {
	    yesAnswerNode nullable:true
    	noAnswerNode nullable:true        
	   	
  	}
	static mappedBy = [ yesAnswerNode: "none", noAnswerNode: "none" ]	
    static mapping = {
        yesAnswerNode cascade: 'delete'
        noAnswerNode cascade: 'delete'
    }
}
