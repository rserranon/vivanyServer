package vivanyserver

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class HistoryController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond History.list(params), model:[historyCount: History.count()]
    }

    def show(History history) {
        respond history
    }

    def create() {
        respond new History(params)
    }

    @Transactional
    def save(History history) {
        if (history == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (history.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond history.errors, view:'create'
            return
        }

        history.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'history.label', default: 'History'), history.id])
                redirect history
            }
            '*' { respond history, [status: CREATED] }
        }
    }

    def edit(History history) {
        respond history
    }

    @Transactional
    def update(History history) {
        if (history == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (history.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond history.errors, view:'edit'
            return
        }

        history.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'history.label', default: 'History'), history.id])
                redirect history
            }
            '*'{ respond history, [status: OK] }
        }
    }

    @Transactional
    def delete(History history) {

        if (history == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        history.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'history.label', default: 'History'), history.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'history.label', default: 'History'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
