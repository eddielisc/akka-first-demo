package event;

public class Scan {
	private String folder;
	
	public Scan(){
		
	}
	
	public Scan(String folder){
		this.setFolder(folder);
	}
	
	public String getFolder() {
		return folder;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}
}
