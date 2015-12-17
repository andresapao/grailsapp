import jogoanimais.*

class BootStrap {
	def init = { servletContext ->
		if(AnimaisTreeMap.count()==0)
		{
			def noAnswer =  new AnimaisTreeMap(nodeDescription:"é macaco?", 
								   			   noAnswerNode:null, 
											   yesAnswerNode:null)
			noAnswer.save(failOnError: true)
			def yesAnswer =  new AnimaisTreeMap(nodeDescription:"é tubarão?", 
								   			   noAnswerNode:null, 
											   yesAnswerNode:null)
			yesAnswer.save(failOnError: true)			
			new AnimaisTreeMap(nodeDescription:"vive na água?", noAnswerNode: noAnswer, yesAnswerNode:  yesAnswer).
							   save(failOnError: true)			
		}
    }	

    def destroy = {
    }
}
