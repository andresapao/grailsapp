package jogoanimais

import grails.rest.*
@Resource(uri='/animals', formats='json')
class AnimaisTreeMap {
	Long nodeId
	String nodeDescription
	Long yesAnswerNode
	Long noAnswerNode
	
    static constraints = {
    }
	
}
