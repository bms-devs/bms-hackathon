package pl.com.bms.email

import com.google.common.eventbus.EventBus
import org.springframework.core.io.ByteArrayResource
import org.springframework.core.io.Resource
import org.springframework.core.io.ResourceLoader
import spock.lang.Specification

class EventMailerTest extends Specification {

    def eventBus = Mock(EventBus)
    def resourceLoader = Mock(ResourceLoader)
    def emailSender = Mock(EmailSender)

    def eventConsumer = new EventMailer(eventBus, resourceLoader, emailSender)

    class TestEvent { }

    def "should register with the event bus"() {
        when:
        new EventMailer(eventBus, null, null)

        then:
        1 * eventBus.register(_)
    }

    def "should not send an e-mail when neither subject not body are available"() {
        when:
        eventConsumer.onEvent(new TestEvent())

        then:
        0 * emailSender.sendEmail(_, _)
    }

    def "should not send an e-mail when no subject is available for the event"() {
        given:
        resourceLoader.getResource("classpath:email/TestEvent-body.txt") >> createResource("Some body")

        when:
        eventConsumer.onEvent(new TestEvent())

        then:
        0 * emailSender.sendEmail(_, _)
    }

    def "should not send an e-mail when no body is available for the event"() {
        given:
        resourceLoader.getResource("classpath:email/TestEvent-subject.txt") >> createResource("Some subject")

        when:
        eventConsumer.onEvent(new TestEvent())

        then:
        0 * emailSender.sendEmail(_, _)
    }

    def "should send an e-mail if subject and body are available for the event"() {
        given:
        resourceLoader.getResource("classpath:email/TestEvent-subject.txt") >> createResource("Some subject")
        resourceLoader.getResource("classpath:email/TestEvent-body.txt") >> createResource("Some body")

        when:
        eventConsumer.onEvent(new TestEvent())

        then:
        1 * emailSender.sendEmail("Some subject", "Some body")
    }

    private Resource createResource(final String text) {
        new ByteArrayResource(new String(text).getBytes("utf-8"))
    }

}