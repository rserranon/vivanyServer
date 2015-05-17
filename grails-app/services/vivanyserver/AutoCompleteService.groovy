package vivanyserver

import grails.transaction.Transactional

@Transactional
class AutoCompleteService {

	def diseasesList(params){
		def query = {
			like("diseaseName", "${params.term}%") // term is the parameter send by jQuery autocomplete	
			projections { // good to select only the required columns.
				property("id")
				property("diseaseName")
			}
		}
		def dList = Disease.createCriteria().list(query) // execute  to the get the list of companies
		println dList
		def diseaseSelectList = [] // to add each company details
		dList.each {
			def diseaseMap = [:] // add to map. jQuery autocomplete expects the JSON object to be with id/label/value.
			diseaseMap.put("id", it[0])
			diseaseMap.put("label", it[1])
			diseaseMap.put("value", it[1])
			// companyMap.put("nasSymbol", it[2]) // will use this to pre-populate the Emp Id
			diseaseSelectList.add(diseaseMap) // add to the arraylist
		}
		return diseaseSelectList
	}
}
