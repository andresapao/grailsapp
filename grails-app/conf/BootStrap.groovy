import jogoanimais.*

class BootStrap {
	def init = { servletContext ->
		if(AnimaisTreeMap.count()==0)
		{
			new AnimaisTreeMap(nodeId:1, 
							   nodeDescription:"O animal que você pensou vive na água?", 
							   noAnswerNode:3, yesAnswerNode:2)
							   .save(failOnError: true)
			new AnimaisTreeMap(nodeId:2, 
							   nodeDescription:"O animal que você pensou é tubarão?", 
							   noAnswerNode:null, 
							   yesAnswerNode:null)
							   .save(failOnError: true)
			new AnimaisTreeMap(nodeId:3, 
							   nodeDescription:"O animal que você pensou é macaco?", 
							   noAnswerNode:null, 
							   yesAnswerNode:null)
							   .save(failOnError: true)


		}
    }	

    def destroy = {
    }
}
