package vivanyserver
import grails.rest.*

@Resource(uri='/api/person', formats=['json', 'html'])

class Person {
	String 	name
	String 	lastName
	String 	type
	Address livesAt

//	static hasOne = [livesAt:Address]

	static mappedBy = [entries:'patient',
										 hasRelationships:'relatedTo1',
										 isRelatedTo: 'relatedTo2']
	static hasMany = [contacts:Person,
										entries:Entry,
										hasRelationships: Relationship,
										isRelatedTo: Relationship]

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
