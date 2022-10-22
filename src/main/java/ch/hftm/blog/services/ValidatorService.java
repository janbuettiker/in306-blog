package ch.hftm.blog.services;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.reactive.messaging.Channel;

import ch.hftm.blog.models.Message;
import io.smallrye.reactive.messaging.MutinyEmitter;

@ApplicationScoped
public class ValidatorService {

    @Inject
    @Channel("message-source")
    MutinyEmitter<Message> emitter;

    public void validateMessage(String messageString) {
        System.out.println("Trying to validate message: " + messageString);
        var m = new Message();
        m.setMessage(messageString);
        emitter.sendAndAwait(m);
    }
}
