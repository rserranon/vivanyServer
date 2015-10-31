package vivanyserver

import grails.rest.*

@Resource(uri='/api/entry', formats=['json', 'html'])
class Entry {
	String 					summary
	Date 					entryDate
	String 					description
	Person  				doctor
	HealthProvider	heldAt

	static			belongsTo 	= [patient:Person]
	static 			hasMany 	= [images:DigitalObject]


	Entry			nextEntry
 // HealthProvider 	heldAt    TOBE Implemented when we can re-create DB

    static constraints = {
			patient					blank:false
			doctor 					blank:true
			heldAt					blank:true
			entryDate				widget: 	'datePicker'
			doctor					nullable: true
			summary					widget: 	'textarea', size: 5..200
			description			type:  		"text", widget:'textarea', size: 5..500
			nextEntry 			nullable: true
			images					nullable: true
    }

	String toString() {
		"${this.class.simpleName}-${entryDate}"
	}
}
