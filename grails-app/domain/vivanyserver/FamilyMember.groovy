package vivanyserver

class FamilyMember {
	String 	name
	String 	lastName
	String 	alias
    Date 	birthDate

    static hasMany = [clinicalHistory: History, contacts:Contact, entries:Entry]	

    static constraints = {
    }
	String toString() {
		name + " " + lastName
	}
}
