package vivanyserver

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class NonPathologicalController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond NonPathological.list(params), model:[nonPathologicalCount: NonPathological.count()]
    }

    def show(NonPathological nonPathological) {
        respond nonPathological
    }

    def create() {
        respond new NonPathological(params)
    }

    @Transactional
    def save(NonPathological nonPathological) {
        if (nonPathological == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (nonPathological.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond nonPathological.errors, view:'create'
            return
        }

        nonPathological.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'nonPathological.label', default: 'NonPathological'), nonPathological.id])
                redirect nonPathological
            }
            '*' { respond nonPathological, [status: CREATED] }
        }
    }

    def edit(NonPathological nonPathological) {
        respond nonPathological
    }

    @Transactional
    def update(NonPathological nonPathological) {
        if (nonPathological == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (nonPathological.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond nonPathological.errors, view:'edit'
            return
        }

        nonPathological.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'nonPathological.label', default: 'NonPathological'), nonPathological.id])
                redirect nonPathological
            }
            '*'{ respond nonPathological, [status: OK] }
        }
    }

    @Transactional
    def delete(NonPathological nonPathological) {

        if (nonPathological == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        nonPathological.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'nonPathological.label', default: 'NonPathological'), nonPathological.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'nonPathological.label', default: 'NonPathological'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
