package vivanyserver

class Disease {
		String  diseaseId
		String 	diseaseName

	    static constraints = {
			diseaseId		blank:false
			diseaseName 	blank:false
	    }
	
		String toString() {
			diseaseName
		}
	}
