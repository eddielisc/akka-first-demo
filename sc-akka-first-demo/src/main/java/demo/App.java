package demo;

import actor.Aggregator;
import actor.FileParser;
import actor.FileScanner;
import akka.actor.ActorSystem;
import akka.actor.Props;
import event.Scan;

public class App {

	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("no predefined directory");
		}else{
			ActorSystem system = ActorSystem.create("FileSystem");
			system.actorOf(Props.create(FileScanner.class), "fileScanner");
			system.actorOf(Props.create(FileParser.class), "fileParser");
			system.actorOf(Props.create(Aggregator.class), "aggregator");

			system.actorSelection("/user/fileScanner").tell(new Scan(args[0]), null);
		}

	}

}
