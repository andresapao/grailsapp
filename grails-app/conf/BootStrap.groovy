import jogoanimais.*

class BootStrap {
	def animaisService	
	def init = { servletContext ->
		if(AnimaisTreeMap.count()==0)
		{
			/*
			def noAnswer =  new AnimaisTreeMap(nodeDescription:"macaco", 
											   nodeInfo: AnimaisTreeMap.ANIMAL,
								   			   noAnswerNode:null, 
											   yesAnswerNode:null)
			noAnswer.save(failOnError: true)
			def yesAnswer =  new AnimaisTreeMap(nodeDescription:"tubarão", 
											    nodeInfo: AnimaisTreeMap.ANIMAL,
								   			    noAnswerNode:null, 
											    yesAnswerNode:null)
			yesAnswer.save(failOnError: true)			

			new AnimaisTreeMap(nodeDescription:"vive na água", 
							   nodeInfo: AnimaisTreeMap.ACTION,
							   noAnswerNode: noAnswer, 
							   yesAnswerNode:  yesAnswer).
							   save(failOnError: true)			
							   */
			animaisService.initializeDB()
		}
    }	

    def destroy = {
    }
}
