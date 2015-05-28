package vivanyserver

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class RelationshipController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Relationship.list(params), model:[relationshipCount: Relationship.count()]
    }

    def show(Relationship relationship) {
        respond relationship
    }

    def create() {
        respond new Relationship(params)
    }

    @Transactional
    def save(Relationship relationship) {
        if (relationship == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (relationship.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond relationship.errors, view:'create'
            return
        }

        relationship.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'relationship.label', default: 'Relationship'), relationship.id])
                redirect relationship
            }
            '*' { respond relationship, [status: CREATED] }
        }
    }

    def edit(Relationship relationship) {
        respond relationship
    }

    @Transactional
    def update(Relationship relationship) {
        if (relationship == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (relationship.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond relationship.errors, view:'edit'
            return
        }

        relationship.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'relationship.label', default: 'Relationship'), relationship.id])
                redirect relationship
            }
            '*'{ respond relationship, [status: OK] }
        }
    }

    @Transactional
    def delete(Relationship relationship) {

        if (relationship == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        relationship.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'relationship.label', default: 'Relationship'), relationship.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'relationship.label', default: 'Relationship'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
