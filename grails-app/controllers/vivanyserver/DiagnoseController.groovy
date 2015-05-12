package vivanyserver

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class DiagnoseController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Diagnose.list(params), model:[diagnoseCount: Diagnose.count()]
    }

    def show(Diagnose diagnose) {
        respond diagnose
    }

    def create() {
        respond new Diagnose(params)
    }

    @Transactional
    def save(Diagnose diagnose) {
        if (diagnose == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (diagnose.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond diagnose.errors, view:'create'
            return
        }

        diagnose.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'diagnose.label', default: 'Diagnose'), diagnose.id])
                redirect diagnose
            }
            '*' { respond diagnose, [status: CREATED] }
        }
    }

    def edit(Diagnose diagnose) {
        respond diagnose
    }

    @Transactional
    def update(Diagnose diagnose) {
        if (diagnose == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (diagnose.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond diagnose.errors, view:'edit'
            return
        }

        diagnose.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'diagnose.label', default: 'Diagnose'), diagnose.id])
                redirect diagnose
            }
            '*'{ respond diagnose, [status: OK] }
        }
    }

    @Transactional
    def delete(Diagnose diagnose) {

        if (diagnose == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        diagnose.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'diagnose.label', default: 'Diagnose'), diagnose.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'diagnose.label', default: 'Diagnose'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
