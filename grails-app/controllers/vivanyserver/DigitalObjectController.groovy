package vivanyserver

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class DigitalObjectController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond DigitalObject.list(params), model:[digitalObjectCount: DigitalObject.count()]
    }

    def show(DigitalObject digitalObject) {
        respond digitalObject
    }

    def create() {
        respond new DigitalObject(params)
    }

    @Transactional
    def save(DigitalObject digitalObject) {
        if (digitalObject == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (digitalObject.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond digitalObject.errors, view:'create'
            return
        }

        digitalObject.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'digitalObject.label', default: 'DigitalObject'), digitalObject.id])
                redirect digitalObject
            }
            '*' { respond digitalObject, [status: CREATED] }
        }
    }

    def edit(DigitalObject digitalObject) {
        respond digitalObject
    }

    @Transactional
    def update(DigitalObject digitalObject) {
        if (digitalObject == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (digitalObject.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond digitalObject.errors, view:'edit'
            return
        }

        digitalObject.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'digitalObject.label', default: 'DigitalObject'), digitalObject.id])
                redirect digitalObject
            }
            '*'{ respond digitalObject, [status: OK] }
        }
    }

    @Transactional
    def delete(DigitalObject digitalObject) {

        if (digitalObject == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        digitalObject.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'digitalObject.label', default: 'DigitalObject'), digitalObject.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'digitalObject.label', default: 'DigitalObject'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
