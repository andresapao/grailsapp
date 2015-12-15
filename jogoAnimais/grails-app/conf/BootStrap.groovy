import jogoanimais.*

class BootStrap {
	def init = { servletContext ->
		if(AnimaisTreeMap.count()==0)
		{
			new AnimaisTreeMap(nodeId:1, nodeDescription:"teste1", noAnswerNode:3, yesAnswerNode:4).save(failOnError: true)
			new AnimaisTreeMap(nodeId:2, nodeDescription:"teste2", noAnswerNode:3, yesAnswerNode:4).save(failOnError: true)
			log.info "aqui"
		}
    }	

    def destroy = {
    }
}
