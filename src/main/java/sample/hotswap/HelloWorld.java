package sample.hotswap;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

// Based on AKKA samples
public class HelloWorld extends UntypedActor {

    @Override
    public void preStart() {
        // create the greeter actor
        final ActorRef greeter = getContext().actorOf(Props.create(Greeter.class), "greeter");

        // tell it to perform the greeting
        greeter.tell(Greeter.Msg.GREET, getSelf());
        greeter.tell(Greeter.Msg.GREET, getSelf());

    }

    @Override
    public void onReceive(Object msg) {
        if (msg == Greeter.Msg.DONE) {
            getContext().stop(getSelf());
        } else {
            unhandled(msg);
        }
    }


}
