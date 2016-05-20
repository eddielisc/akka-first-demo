package actor;

import akka.actor.ActorSelection;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import constant.Constant;
import event.Event;

public class FileParser extends UntypedActor {
	 LoggingAdapter log = Logging.getLogger(getContext().system(), this);
	  
	 public void onReceive(Object message) throws Exception {
		 ActorSelection aggregator = getContext().actorSelection("/user/aggregator");
		 ActorSelection fileScanner = getContext().actorSelection("/user/fileScanner");
		 if(message instanceof Event){
			 Event event = (Event) message;
			 Boolean isCall = true;
			 
/*			 if status is done, no need, tell file scanner, 
 * 			 otherwise, 
 * 			just keep call the updated status to aggregator
*/		 
			 if(Constant.statusDone.equalsIgnoreCase(event.getStatus())){
				 isCall = false;
				 log.debug("end-of-file is done");
				 fileScanner.tell(Constant.statusDone, getSelf());
			 }
			 if(isCall)
				 aggregator.tell(event, getSelf());
		 }
	 }
}
