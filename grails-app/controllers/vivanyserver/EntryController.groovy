package vivanyserver

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class EntryController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Entry.list(params), model:[entryCount: Entry.count()]
    }

    def show(Entry entry) {
        respond entry
    }

    def create() {
        respond new Entry(params)
    }

    @Transactional
    def save(Entry entry) {
        if (entry == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (entry.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond entry.errors, view:'create'
            return
        }

        entry.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'entry.label', default: 'Entry'), entry.id])
                redirect entry
            }
            '*' { respond entry, [status: CREATED] }
        }
    }

    def edit(Entry entry) {
        respond entry
    }

    @Transactional
    def update(Entry entry) {
        if (entry == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (entry.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond entry.errors, view:'edit'
            return
        }

        entry.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'entry.label', default: 'Entry'), entry.id])
                redirect entry
            }
            '*'{ respond entry, [status: OK] }
        }
    }

    @Transactional
    def delete(Entry entry) {

        if (entry == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        entry.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'entry.label', default: 'Entry'), entry.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'entry.label', default: 'Entry'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
