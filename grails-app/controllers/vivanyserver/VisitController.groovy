package vivanyserver

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class VisitController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Visit.list(params), model:[visitCount: Visit.count()]
    }

    def show(Visit visit) {
        respond visit
    }

    def create() {
        respond new Visit(params)
    }

    @Transactional
    def save(Visit visit) {
        if (visit == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (visit.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond visit.errors, view:'create'
            return
        }

        visit.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'visit.label', default: 'Visit'), visit.id])
                redirect visit
            }
            '*' { respond visit, [status: CREATED] }
        }
    }

    def edit(Visit visit) {
        respond visit
    }

    @Transactional
    def update(Visit visit) {
        if (visit == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (visit.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond visit.errors, view:'edit'
            return
        }

        visit.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'visit.label', default: 'Visit'), visit.id])
                redirect visit
            }
            '*'{ respond visit, [status: OK] }
        }
    }

    @Transactional
    def delete(Visit visit) {

        if (visit == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        visit.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'visit.label', default: 'Visit'), visit.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'visit.label', default: 'Visit'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
