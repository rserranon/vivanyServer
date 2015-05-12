package vivanyserver

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ProcedureController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Procedure.list(params), model:[procedureCount: Procedure.count()]
    }

    def show(Procedure procedure) {
        respond procedure
    }

    def create() {
        respond new Procedure(params)
    }

    @Transactional
    def save(Procedure procedure) {
        if (procedure == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (procedure.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond procedure.errors, view:'create'
            return
        }

        procedure.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'procedure.label', default: 'Procedure'), procedure.id])
                redirect procedure
            }
            '*' { respond procedure, [status: CREATED] }
        }
    }

    def edit(Procedure procedure) {
        respond procedure
    }

    @Transactional
    def update(Procedure procedure) {
        if (procedure == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (procedure.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond procedure.errors, view:'edit'
            return
        }

        procedure.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'procedure.label', default: 'Procedure'), procedure.id])
                redirect procedure
            }
            '*'{ respond procedure, [status: OK] }
        }
    }

    @Transactional
    def delete(Procedure procedure) {

        if (procedure == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        procedure.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'procedure.label', default: 'Procedure'), procedure.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'procedure.label', default: 'Procedure'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
