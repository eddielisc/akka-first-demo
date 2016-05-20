package actor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import event.Event;

public class Aggregator extends UntypedActor {
	 LoggingAdapter log = Logging.getLogger(getContext().system(), this);

	 public void onReceive(Object message) throws Exception {
		 //start of the file
		 if(message instanceof Event){
			 //line
			Event event = (Event) message;
			if("start-of-file".equalsIgnoreCase(event.getStatus())){
				 log.debug("init Event start of file");
				 event.setStatus("line");
				 event.setLineNum(0);
				 event.setBufferedReader(new BufferedReader(new FileReader(event.getFile())));
			}else if("line".equalsIgnoreCase(event.getStatus())){
				log.debug("line");
				BufferedReader br = event.getBufferedReader();
				if(br != null){
					while (br.readLine() != null) {
						event.addLineNum();
					}
					br.close();
				}
				event.setStatus("end-of-file");
		 	}else if("end-of-file".equalsIgnoreCase(event.getStatus())){
		 		log.debug("end");
		 		System.out.println(event.getFile().getName() +  "\t" + event.getLineNum());
		 		event.setStatus("end-of-file-done");
		 	}
		 	getSender().tell(event,getSelf());
		 }
	 }
}
