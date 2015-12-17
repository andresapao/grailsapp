import jogoanimais.*

class BootStrap {
	def init = { servletContext ->
		if(AnimaisTreeMap.count()==0)
		{
			new AnimaisTreeMap(nodeId:1, 
							   nodeDescription:"vive na água?", 
							   noAnswerNode:3, yesAnswerNode:2)
							   .save(failOnError: true)
			new AnimaisTreeMap(nodeId:2, 
							   nodeDescription:"é tubarão?", 
							   noAnswerNode:null, 
							   yesAnswerNode:null)
							   .save(failOnError: true)
			new AnimaisTreeMap(nodeId:3, 
							   nodeDescription:"é macaco?", 
							   noAnswerNode:null, 
							   yesAnswerNode:null)
							   .save(failOnError: true)


		}
    }	

    def destroy = {
    }
}
