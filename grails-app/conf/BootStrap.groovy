import jogoanimais.*

class BootStrap {
	def animaisService	
	def init = { servletContext ->
		log.info 'BootStrap'
		log.info AnimaisTreeMap.count()

		if(AnimaisTreeMap.count()==0)
		{
			animaisService.initializeDB()
		}
    }	

    def destroy = {
    }
}
