package org.gseii.sftp;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import org.gseii.metier.SensorMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class Watcher {

	public Watcher() {
		
	}
	// gets the name of a file 
	public File getTheFileName(String pathOfTheFolder) {
		File folder=new File(pathOfTheFolder);
		//Returns an array of abstract pathnames denoting the files in the directory
		File[] files=folder.listFiles();
		// checks if there is a file in my sharable folder 
		if(files[0].isFile()) {
			return files[0];
		}else {
			System.out.println("no file to show");
			return  null;
		}
	}
	// gets the file's path 
	public Path getPath(File f) {
		return f.toPath();
	}
	//convert a JSON Object to a JAVA Object 
	public Json ReadDataDronJsonFile(File TheFile) throws JsonParseException, JsonMappingException, IOException {
			ObjectMapper objectMapper = new ObjectMapper();
				 Json json =objectMapper.readValue(TheFile, Json.class);
				 return json;
	}
	
	@Autowired
	public SensorMetier s ;
	
	public void watch() {
			
/*	The java.nio.file package provides a file change notification API, called the Watch Service API.
 *	This API enables you to register a directory (or directories) with the watch service. When registering,
 *	you tell the service which types of events you are interested in: file creation (our case), file deletion,
 *	or file modification. When the service detects an event of interest,
 *	it is forwarded to the registered process. The registered process has a thread (or a pool of threads) 
 *	dedicated to watching for any events it has registered for.
 *  When an event comes in, it is handled as needed.
 */
		
		// Creates a instance of WatchService.
		   try( WatchService watcher = FileSystems.getDefault().newWatchService()) {
	            // the path of my shareable folder 
	            String chemin="C:\\Users\\NZT48\\Desktop\\shareable";
	            Path logDir = Paths.get(chemin);
	            /* 	Registers the logDir below with a watch service. with the specification of the type of events
	            *	in our case "a directory entry is created".
	            */
	            logDir.register(watcher, StandardWatchEventKinds.ENTRY_CREATE);
	            
				/*an infinite loop to wait for incoming events. When an event occurs,
				 *  the key is signaled and placed into the watcher's queue
				 */
	            while (true) {
	            	
	            	//Retrieve the key from the watcher's queue. If no queued key is available, this method waits
	                WatchKey key = watcher.take();
	                System.out.println("the file is received successfully !");
	                
	                for (WatchEvent<?> event : key.pollEvents()) {
	                	//Retrieve the type of event 
	                    WatchEvent.Kind<?> kind = event.kind();
	                    if ( StandardWatchEventKinds.ENTRY_CREATE.equals(kind)) {
	                    	Json j=this.ReadDataDronJsonFile(this.getTheFileName(chemin));
	                    	System.out.println(j.toString());
	                    	//save the object json to our data base 
	                    	s.saveValue(j.getId(), j.getValue());
	                    	//move the file received to our history file 
	                    	 MoveToHistory.move(this.getPath(this.getTheFileName(chemin)));
	                    }
	                }
	                //resume waiting for the event
	                key.reset();
	            }
	        } catch (IOException | InterruptedException e) {
	            e.printStackTrace();
	        }
	}
}
