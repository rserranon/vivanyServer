package vivanyserver

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class TreatmentController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Treatment.list(params), model:[treatmentCount: Treatment.count()]
    }

    def show(Treatment treatment) {
        respond treatment
    }

    def create() {
        respond new Treatment(params)
    }

    @Transactional
    def save(Treatment treatment) {
        if (treatment == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (treatment.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond treatment.errors, view:'create'
            return
        }

        treatment.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'treatment.label', default: 'Treatment'), treatment.id])
                redirect treatment
            }
            '*' { respond treatment, [status: CREATED] }
        }
    }

    def edit(Treatment treatment) {
        respond treatment
    }

    @Transactional
    def update(Treatment treatment) {
        if (treatment == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (treatment.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond treatment.errors, view:'edit'
            return
        }

        treatment.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'treatment.label', default: 'Treatment'), treatment.id])
                redirect treatment
            }
            '*'{ respond treatment, [status: OK] }
        }
    }

    @Transactional
    def delete(Treatment treatment) {

        if (treatment == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        treatment.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'treatment.label', default: 'Treatment'), treatment.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'treatment.label', default: 'Treatment'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
