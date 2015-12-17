package jogoanimais

import grails.rest.*
@Resource(uri='/animals', formats='json')
class AnimaisTreeMap {
   String nodeDescription
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
