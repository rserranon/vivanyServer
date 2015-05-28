package vivanyserver

class Relationship {
	
	String relationship

	static belongsTo = [relatedTo1: Person,
						relatedTo2: Person]
    static constraints = {
		relationship unique:true
    }
	
    @Override public String toString() {
      return relationship
    }
}
