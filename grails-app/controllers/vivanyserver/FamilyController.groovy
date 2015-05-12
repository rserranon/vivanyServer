package vivanyserver

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class FamilyController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Family.list(params), model:[familyCount: Family.count()]
    }

    def show(Family family) {
        respond family
    }

    def create() {
        respond new Family(params)
    }

    @Transactional
    def save(Family family) {
        if (family == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (family.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond family.errors, view:'create'
            return
        }

        family.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'family.label', default: 'Family'), family.id])
                redirect family
            }
            '*' { respond family, [status: CREATED] }
        }
    }

    def edit(Family family) {
        respond family
    }

    @Transactional
    def update(Family family) {
        if (family == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (family.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond family.errors, view:'edit'
            return
        }

        family.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'family.label', default: 'Family'), family.id])
                redirect family
            }
            '*'{ respond family, [status: OK] }
        }
    }

    @Transactional
    def delete(Family family) {

        if (family == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        family.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'family.label', default: 'Family'), family.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'family.label', default: 'Family'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
