package actor;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import event.Event;

public class FileScanner extends UntypedActor {
	LoggingAdapter log = Logging.getLogger(getContext().system(), this);

	static String pathString = "/Users/eddieli/Documents/temp/tests";
	private Integer fileNum = 0; 
	private Integer fileDoneCount = 0;
	@SuppressWarnings("deprecation")
	public void onReceive(Object message) {
		if (message instanceof String) {
			if ("scan".equalsIgnoreCase(String.valueOf(message))) {
				Path path = Paths.get(pathString);
				if (Files.exists(path) && Files.isDirectory(path)) {
					try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
						for (Path p : stream) {
							fileNum++;
							getContext().actorSelection("/user/fileParser").tell(new Event("start-of-file", p.toFile()), getSelf());
						}
					} catch (IOException e) {
						throw new RuntimeException(e);
					}
				}
			}else if("success".equalsIgnoreCase(String.valueOf(message))){
				fileDoneCount++;
				log.info("succes");
				if(fileDoneCount == fileNum){
					context().stop(getSender());
					context().system().shutdown();
				}
				
			}
		}

	}
}
