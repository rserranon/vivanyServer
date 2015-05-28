package vivanyserver

class Relationship {
	
	String relationship

	static belongsTo = [relatedTo1: Person,
						relatedTo2: Person]
    static constraints = {
		relatedTo1   blank: false
		relationship unique: false
		relatedTo2   blank: false
    }
	
    @Override public String toString() {
      return relationship
    }
}
