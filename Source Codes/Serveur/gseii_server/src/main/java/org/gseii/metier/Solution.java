package org.gseii.metier;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
/* Secure Shell (SSH) is a criptografic network protocol,
 *  used in network service operations on a secure way over an unsecure network.
 *   It's kind of a tunnel that you and the remote server 
 *   there'is a lot of  implementations for this SSH solution 
 *   in our case we will use JSch which's a pure Java implementation of SSH2.
 *   it's allows you to connect to an sshd server and use port(22 sftp) forwarding
 *   in our case we want to execute from the server 
 *   a command on raspberry pi 
 */
public class Solution {
	
    public Solution() {
		super();
		// TODO Auto-generated constructor stub
	}

	static public void execMotor(){
        try{
            String command = "python /home/pi/servo.py";//we want to execute a python code on shell
            String host = "192.168.43.173";//Raspberry's ip 
            String user = "pi";//username 
            String password = "raspberry";// password
             
            JSch jsch = new JSch();//instanciation of JSch
            Session session = jsch.getSession(user, host, 22);/*to start a new Session
             The username and password and port number are passed on arguments*/
            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");//Disabling the StrictHostKeyChecking option;
            session.setConfig(config);//sets several configuration options at once.
            session.setPassword(password);
            session.connect();//start session
             
            Channel channel = session.openChannel("exec");//open connection to a raspberry executing program (terminal)
            ((ChannelExec)channel).setCommand(command);//sets the command to be executed.
            channel.setInputStream(null);//we won't forward other datat with command to the rasoberry
            ((ChannelExec)channel).setErrStream(System.err);//Sets the error stream
            /*Recuper the output of the shell and try to print it here */ 
            InputStream input = channel.getInputStream();
            channel.connect();
             
            System.out.println("Channel Connected to machine " + host + " server with command: " + command ); 
            
             
            try{
                InputStreamReader inputReader = new InputStreamReader(input);
                BufferedReader bufferedReader = new BufferedReader(inputReader);
                String line = null;
                 
                while((line = bufferedReader.readLine()) != null){
                    System.out.println(line);
                }
                bufferedReader.close();
                inputReader.close();
            }catch(IOException ex){
                ex.printStackTrace();
            }
             
            channel.disconnect();
            session.disconnect();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}



