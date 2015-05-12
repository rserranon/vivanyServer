package vivanyserver

class Address {
	String 	   street
	String 	   extNumber
	String     intNumber
	String     zipCode 
	String     state
	String     country

    static constraints = {
    }
	
	String toString() {
		street + " " + extNumer + " " + zipCode
	}
}
