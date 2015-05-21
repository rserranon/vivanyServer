package vivanyserver

class Entry {
	String 			summary
	Date 			entryDate
	String 			description
	Person  		doctor
	HealthProvider	heldAt
	
	static 			hasMany = [personsInvolved:Person, images:DigitalObject]
	
	
	Entry			nextEntry
 // HealthProvider 	heldAt    TOBE Implemented when we can re-create DB

    static constraints = {
		doctor 				blank:true
		heldAt				blank:true
		entryDate			widget:'datePicker'
		doctor				nullable:true
		summary				widget:'textarea', size: 5..200
		description			type: "text", widget:'textarea', size: 5..500
		personsInvolved 	nullable:true
		nextEntry 			nullable:true
		images				nullable:true
    }
	
	String toString() {
		"Visit: " + entryDate
	}
}
