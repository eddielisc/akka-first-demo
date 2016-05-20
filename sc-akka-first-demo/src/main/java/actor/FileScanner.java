package actor;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import event.Event;
import event.Scan;

public class FileScanner extends UntypedActor {
	LoggingAdapter log = Logging.getLogger(getContext().system(), this);

	static String pathString = "/Users/eddieli/Documents/temp/tests";
	private Integer fileNum = 0;
	private Integer fileDoneCount = 0;

	public void onReceive(Object message) {
		if (message instanceof Scan) {
			Scan scan = (Scan) message;

			Path path = Paths.get(scan.getFolder());

			if (Files.exists(path) && Files.isDirectory(path)) {
				File[] files = new File(pathString).listFiles();
				fileNum = files.length;
				for (File file : files) {
					getContext().actorSelection("/user/fileParser").tell(new Event("start-of-file", file), getSelf());
				}
			}
		} else if (message instanceof String && "success".equalsIgnoreCase(String.valueOf(message))) {
			fileDoneCount++;
			log.debug("succes : {}, {}", fileNum, fileDoneCount);
			if (fileDoneCount == fileNum) {
				log.debug("succes with same line num");
				context().stop(getSender());
				context().stop(getSelf());
				context().system().shutdown();
			}

		}
	}

}
