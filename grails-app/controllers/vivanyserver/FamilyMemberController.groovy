package vivanyserver

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class FamilyMemberController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond FamilyMember.list(params), model:[familyMemberCount: FamilyMember.count()]
    }

    def show(FamilyMember familyMember) {
        respond familyMember
    }

    def create() {
        respond new FamilyMember(params)
    }

    @Transactional
    def save(FamilyMember familyMember) {
        if (familyMember == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (familyMember.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond familyMember.errors, view:'create'
            return
        }

        familyMember.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'familyMember.label', default: 'FamilyMember'), familyMember.id])
                redirect familyMember
            }
            '*' { respond familyMember, [status: CREATED] }
        }
    }

    def edit(FamilyMember familyMember) {
        respond familyMember
    }

    @Transactional
    def update(FamilyMember familyMember) {
        if (familyMember == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (familyMember.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond familyMember.errors, view:'edit'
            return
        }

        familyMember.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'familyMember.label', default: 'FamilyMember'), familyMember.id])
                redirect familyMember
            }
            '*'{ respond familyMember, [status: OK] }
        }
    }

    @Transactional
    def delete(FamilyMember familyMember) {

        if (familyMember == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        familyMember.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'familyMember.label', default: 'FamilyMember'), familyMember.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'familyMember.label', default: 'FamilyMember'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
