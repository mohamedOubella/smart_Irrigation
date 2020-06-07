package org.gseii.sftp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

public class MoveToHistory {

static public Boolean move(Path p) {
		Date d = new Date(); 
		String newNameOfFile = d.toString().replace(" ","_") ;
		newNameOfFile = newNameOfFile.replace(":","-") ;
		try {
			// move the file from shareable folder to history folder by changing extension to .txt
			Files.move(p, Paths.get("C:\\Users\\NZT48\\Desktop\\history\\"+newNameOfFile+".txt")) ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

}
