package vivanyserver

class Disease {
		String 	diseaseName

	    static constraints = {
			diseaseName 	blank:false
	    }
	
		String toString() {
			diseaseName
		}
	}
