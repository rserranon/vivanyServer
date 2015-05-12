package vivanyserver

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class HealthProviderController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond HealthProvider.list(params), model:[healthProviderCount: HealthProvider.count()]
    }

    def show(HealthProvider healthProvider) {
        respond healthProvider
    }

    def create() {
        respond new HealthProvider(params)
    }

    @Transactional
    def save(HealthProvider healthProvider) {
        if (healthProvider == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (healthProvider.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond healthProvider.errors, view:'create'
            return
        }

        healthProvider.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'healthProvider.label', default: 'HealthProvider'), healthProvider.id])
                redirect healthProvider
            }
            '*' { respond healthProvider, [status: CREATED] }
        }
    }

    def edit(HealthProvider healthProvider) {
        respond healthProvider
    }

    @Transactional
    def update(HealthProvider healthProvider) {
        if (healthProvider == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (healthProvider.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond healthProvider.errors, view:'edit'
            return
        }

        healthProvider.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'healthProvider.label', default: 'HealthProvider'), healthProvider.id])
                redirect healthProvider
            }
            '*'{ respond healthProvider, [status: OK] }
        }
    }

    @Transactional
    def delete(HealthProvider healthProvider) {

        if (healthProvider == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        healthProvider.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'healthProvider.label', default: 'HealthProvider'), healthProvider.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'healthProvider.label', default: 'HealthProvider'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
