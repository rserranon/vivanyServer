package vivanyserver

class Person {
	String 	name
	String 	lastName
	String 	type
	Address livesAt
//	static hasOne = [livesAt:Address]
	
	static hasMany = [contacts:Person]

    static constraints = {
		name 		blank:false
		lastName 	blank:false
		type 		blank:false
		livesAt 	nullable: true
    }
	
	String toString() {
		name + " " + lastName
	}
}
