package vivanyserver

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class PathologicalController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Pathological.list(params), model:[pathologicalCount: Pathological.count()]
    }

    def show(Pathological pathological) {
        respond pathological
    }

    def create() {
        respond new Pathological(params)
    }

    @Transactional
    def save(Pathological pathological) {
        if (pathological == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (pathological.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond pathological.errors, view:'create'
            return
        }

        pathological.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'pathological.label', default: 'Pathological'), pathological.id])
                redirect pathological
            }
            '*' { respond pathological, [status: CREATED] }
        }
    }

    def edit(Pathological pathological) {
        respond pathological
    }

    @Transactional
    def update(Pathological pathological) {
        if (pathological == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (pathological.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond pathological.errors, view:'edit'
            return
        }

        pathological.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'pathological.label', default: 'Pathological'), pathological.id])
                redirect pathological
            }
            '*'{ respond pathological, [status: OK] }
        }
    }

    @Transactional
    def delete(Pathological pathological) {

        if (pathological == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        pathological.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'pathological.label', default: 'Pathological'), pathological.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'pathological.label', default: 'Pathological'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
