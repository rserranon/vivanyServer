package vivanyserver

class Entry {
	String 			summary
	Date 			entryDate
	String 			description
	Person  		doctor
	
	static 			hasMany = [personsInvolved:Person, images:DigitalObject]
	
	
	Entry			nextEntry
 // HealthProvider 	heldAt    TOBE Implemented when we can re-create DB

    static constraints = {
		doctor blank:true
		entryDate			widget:'datePicker'
		summary				widget:'textarea'
		description			widget:'textarea'
		personsInvolved 	nullable:true
		nextEntry 			nullable:true
		doctor				nullable:true
		images				nullable:true
    }
	
	String toString() {
		"Visit: " + entryDate
	}
}
