import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
public class Emailer {
	public static String ipAdd2; 
	public static String ipTxtLoc = "C:\\Users\\Carberto\\workspace\\Emailer\\src\\ip.txt";

	public static void main(String args[]) {
    	Emailer emailer = new Emailer();
    	
    	ipAdd2 = FileRead();
    	
    	FileWriter fstream2;
      	BufferedWriter ipTxt2 = null;
			try {
				fstream2 = new FileWriter(ipTxtLoc);
				ipTxt2 = new BufferedWriter(fstream2);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
    
    	while(true){
    		 String ipAdd; 
    		 
    		 try {
				Thread.sleep(60000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			} 
			
    			
	    		 try {
		            java.net.URL url = new java.net.URL(
		                    "http://whatismyip.com/automation/n09230945.asp");
		
		            java.net.HttpURLConnection con = (HttpURLConnection) url
		                    .openConnection();
		
		            java.io.InputStream stream = con.getInputStream();
		
		            java.io.InputStreamReader reader = new java.io.InputStreamReader(
		                    stream);
		
		            java.io.BufferedReader bReader = new java.io.BufferedReader(reader);
		        
		            ipAdd = bReader.readLine();
		         // Create file 
		            
		          
		            //ipAdd2 = FileRead();
	    			if(ipAdd2 == null){
						ipTxt2.write("192.168.0.1");
						ipTxt2.close();
						ipAdd2 = FileRead();
		        	}
	        	    //Close the output stream
	        	    if (ipAdd.compareTo(ipAdd2)!= 0){
	        	    	FileWriter fstream = new FileWriter(ipTxtLoc);
	        	        BufferedWriter ipTxt = new BufferedWriter(fstream);
	        	        ipTxt.write(ipAdd);
	        	        ipTxt.close();
	        	    	ipAdd2 = FileRead();
	        	    	emailer.sendEmail(ipAdd);
	        	    	
		            	//System.out.print("failure");
		            }
		         
	        	    
		        } catch (Exception e) {
		            //e.printStackTrace();
		        /*	FileWriter fstream2;
		        	BufferedWriter ipTxt2 = null;
					try {
						fstream2 = new FileWriter(ipTxtLoc);
						ipTxt2 = new BufferedWriter(fstream2);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
        	        
        	        try {
        	        	String ipAdd3;
        	        	ipAdd3 = FileRead();
        	        	if(ipAdd3 == null){
							ipTxt2.write("192.168.0.1");
							ipTxt2.close();
        	        	}
					} catch (IOException e1) {
						e1.printStackTrace();
					}*/
		            System.out.print("Error");
		        }
    	     //}
		
	    	
        }
}
	public void sendEmail(String IP){
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
 
		Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("pixterEmailer","thepixter2011");
				}
			});
 
		try {
 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("ipWatch@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse("carlospadillanv@gmail.com"));
			message.setSubject("Testing Subject");
			message.setText("Ip Changed to: " + IP);
 
			Transport.send(message);
 
			System.out.println("Sent");
 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	
	   public static String FileRead()
	    {
		   String Save = null;
	          try{
	        // Open the file that is the first 
	        // command line parameter
	        FileInputStream fstream = new FileInputStream(ipTxtLoc);
	        // Get the object of DataInputStream
	        DataInputStream in = new DataInputStream(fstream);
	            BufferedReader br = new BufferedReader(new InputStreamReader(in));
	       
	           Save = br.readLine();
	        //Close the input stream
	        in.close();
	        //return Save;
	        }catch (Exception e){//Catch exception if any
	          System.err.println("Error: " + e.getMessage());
	          Save = "1";
	          return Save;
	        }
			return Save;
	      }

}

/* Calendar calendar = new GregorianCalendar();
System.out.println(calendar.get(Calendar.MINUTE) + ":" + calendar.get(Calendar.SECOND)+ ":" + calendar.get(Calendar.MILLISECOND));
while (counterTop <= 50){
	 counterTop++;
	 
	 //System.out.print(counter);
	while(counter != 0){
		counter = counter + 1;
		
	}
	
	counter = 1;
}
counterTop = 0;

System.out.println(calendar.get(Calendar.MINUTE) + ":" + calendar.get(Calendar.SECOND)+ ":" + calendar.get(Calendar.MILLISECOND));
connection = isInternetReachable();*/