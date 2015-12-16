package jogoanimais

import grails.rest.*
@Resource(uri='/animals', formats='json')
class AnimaisTreeMap {
	Long nodeId 
	String nodeDescription
	Long yesAnswerNode
	Long noAnswerNode
	static mapping = {
	    nodeId composite: ['title', 'author']
	}	
    static constraints = {
    	yesAnswerNode nullable:true
    	noAnswerNode nullable:true    	
    }
	
}
