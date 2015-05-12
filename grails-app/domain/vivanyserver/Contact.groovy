package vivanyserver

class Contact {
	String name
	String lastName

    static constraints = {
    }
	
	String toString() {
		name + " " + lastName
	}
}
