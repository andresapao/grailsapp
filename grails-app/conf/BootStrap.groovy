import jogoanimais.*

class BootStrap {
	def animaisService	
	def init = { servletContext ->
		if(AnimaisTreeMap.count()==0)
		{
			animaisService.initializeDB()
		}
    }	

    def destroy = {
    }
}
