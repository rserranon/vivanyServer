package vivanyserver

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class PersonController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    // def timeLineService

    def timeline(Person person) {
      def entryList = []

      def classList = []
      classList.add(Diagnose.name)
      classList.add(Treatment.name)
      classList.add(Procedure.name)
      classList.add(Study.name)
      classList.add(Visit.name)
      log.info "Classes: ${classList}"



      entryList = Entry.createCriteria().list() {
                eq('patient', person)
          and {
                'in'("class", classList)
                // eq('class', Diagnose.name) // compare clas column to Diagnose.name
          }
      }

      log.info  "lista ${person.entries}"

     def orderedEntryList = entryList.sort{it.entryDate}

      orderedEntryList.each {
        log.info  "orderedEntryList (each): ${it.class.simpleName} - ${it.entryDate}"
      }

     respond person, model: [entryList: orderedEntryList]
    }

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Person.list(params), model:[personCount: Person.count()]
    }

    def show(Person person) {
        respond person
    }

    def create() {
        respond new Person(params)
    }

    @Transactional
    def save(Person person) {
        if (person == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (person.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond person.errors, view:'create'
            return
        }

        person.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'person.label', default: 'Person'), person.id])
                redirect person
            }
            '*' { respond person, [status: CREATED] }
        }
    }

    def edit(Person person) {
        respond person
    }

    @Transactional
    def update(Person person) {
        if (person == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (person.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond person.errors, view:'edit'
            return
        }

        person.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'person.label', default: 'Person'), person.id])
                redirect person
            }
            '*'{ respond person, [status: OK] }
        }
    }

    @Transactional
    def delete(Person person) {

        if (person == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        person.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'person.label', default: 'Person'), person.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'person.label', default: 'Person'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
