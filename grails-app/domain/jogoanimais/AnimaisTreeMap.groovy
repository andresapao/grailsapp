package jogoanimais

import grails.rest.*
@Resource(uri='/animals', formats='json')
class AnimaisTreeMap {
	Long nodeId 
	String nodeDescription
	Long yesAnswerNode
	Long noAnswerNode
	static mapping = {
	    id column:'nodeId'
	}	
    static constraints = {
    	yesAnswerNode nullable:true
    	noAnswerNode nullable:true    	
    }
	
}
