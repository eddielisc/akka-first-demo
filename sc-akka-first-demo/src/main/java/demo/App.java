package demo;

import actor.Aggregator;
import actor.FileParser;
import actor.FileScanner;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;

/**
 * Hello world!
 *
 */
public class App
{
    

	
	public static void main( String[] args )
    {
        ActorSystem system = ActorSystem.create("FileSystem");
        system.actorOf(Props.create(FileScanner.class),"fileScanner");
        system.actorOf(Props.create(FileParser.class),"fileParser");
        system.actorOf(Props.create(Aggregator.class),"aggregator");

        system.actorSelection("/user/fileScanner").tell("scan" , null);

    }
	
}
