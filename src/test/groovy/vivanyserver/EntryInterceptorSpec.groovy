package vivanyserver


import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(EntryInterceptor)
class EntryInterceptorSpec extends Specification {

    def setup() {
    }

    def cleanup() {

    }

    void "Test entry interceptor matching"() {
        when:"A request matches the interceptor"
            withRequest(controller:"entry")

        then:"The interceptor does match"
            interceptor.doesMatch()
    }
}
