package vivanyserver

import grails.test.mixin.*
import spock.lang.*

@TestFor(FamilyController)
@Mock(Family)
class FamilyControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.familyList
            model.familyCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.family!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            def family = new Family()
            family.validate()
            controller.save(family)

        then:"The create view is rendered again with the correct model"
            model.family!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            family = new Family(params)

            controller.save(family)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/family/show/1'
            controller.flash.message != null
            Family.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def family = new Family(params)
            controller.show(family)

        then:"A model is populated containing the domain instance"
            model.family == family
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def family = new Family(params)
            controller.edit(family)

        then:"A model is populated containing the domain instance"
            model.family == family
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/family/index'
            flash.message != null

        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def family = new Family()
            family.validate()
            controller.update(family)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.family == family

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            family = new Family(params).save(flush: true)
            controller.update(family)

        then:"A redirect is issued to the show action"
            family != null
            response.redirectedUrl == "/family/show/$family.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/family/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def family = new Family(params).save(flush: true)

        then:"It exists"
            Family.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(family)

        then:"The instance is deleted"
            Family.count() == 0
            response.redirectedUrl == '/family/index'
            flash.message != null
    }
}
