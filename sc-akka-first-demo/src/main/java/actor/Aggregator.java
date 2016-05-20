package actor;

import java.io.BufferedReader;
import java.io.FileReader;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import constant.Constant;
import event.Event;

public class Aggregator extends UntypedActor {
	 LoggingAdapter log = Logging.getLogger(getContext().system(), this);

	 public void onReceive(Object message) throws Exception {
		
		 if(message instanceof Event){
			Event event = (Event) message;
			if(Constant.statusStart.equalsIgnoreCase(event.getStatus())){
				 //Step 1: start of the file
				 log.debug("init Event start of file");
				 event.setStatus(Constant.statusLine);
				 event.setLineNum(0);
				 event.setBufferedReader(new BufferedReader(new FileReader(event.getFile())));
			}else if(Constant.statusLine.equalsIgnoreCase(event.getStatus())){
				 //Step 2 : line
				log.debug("line");
				BufferedReader br = event.getBufferedReader();
				if(br != null){
					while (br.readLine() != null) {
						event.addLineNum();
					}
					br.close();
				}
				event.setStatus(Constant.statusEnd);
		 	}else if(Constant.statusEnd.equalsIgnoreCase(event.getStatus())){
		 		 //Step 2 : end of the file
		 		log.debug("end");
		 		System.out.println(event.getFile().getName() +  "\t" + event.getLineNum());
		 		event.setStatus(Constant.statusDone);
		 	}
		 	getSender().tell(event,getSelf());
		 }
	 }
}
