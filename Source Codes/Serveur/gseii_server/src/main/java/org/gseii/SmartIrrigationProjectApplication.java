package org.gseii;

import java.io.*;

import org.gseii.sftp.Watcher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class SmartIrrigationProjectApplication {
	public static String[] st = new String[3];
	public static void main(String[] args) throws IOException {
		
		int i=0;
		File file = new File("Configuration\\config.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));
		 while (i<3 && (st[i] = br.readLine()) != null) 
		 {
			    System.out.println(st[i]);
			    i++;
		 }
		
		ConfigurableApplicationContext context =SpringApplication.run(SmartIrrigationProjectApplication.class, args);
		 Watcher watcher=context.getBean(Watcher.class);
		 watcher.watch();
	}

}
