package event;

import java.io.BufferedReader;
import java.io.File;

public class Event {
	private Integer lineNum;
	private BufferedReader bufferedReader;
	private String status;
	private File file;
	
	public Event(String status,File file){
		this.setStatus(status);
		this.setFile(file);
	}
	
	public Event(String status, Integer lineNum, BufferedReader bufferedReader){
		this.setStatus(status);
		this.setLineNum(lineNum);
		this.setBufferedReader(bufferedReader);
	}
	
	public Event(){
		
	}
	
	public void addLineNum(){
		this.lineNum++;
	}
	
	public Integer getLineNum() {
		return lineNum;
	}

	public void setLineNum(Integer lineNum) {
		this.lineNum = lineNum;
	}

	public BufferedReader getBufferedReader() {
		return bufferedReader;
	}

	public void setBufferedReader(BufferedReader bufferedReader) {
		this.bufferedReader = bufferedReader;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
}
