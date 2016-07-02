 package lessons15;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;

import javax.swing.*;


    @SuppressWarnings("serial")
	public class Main extends JFrame implements Runnable{
	
    static private Socket connection;
	static private ObjectOutputStream output;
	static private ObjectInputStream input;
	
	public static void main (String[]args){
	new Thread (new Main("Test")).start();
	new Thread (new Server()).start();
	}
	public Main(String name){
    super ("Test" );
    setLayout(new FlowLayout());
    setSize (400 ,400);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
	setLocationRelativeTo(null);
	
    final JTextField t1 = new  JTextField(10);
    final JButton b1 = new   JButton("Send");
    b1.addActionListener(new ActionListener(){
	@Override
	  public void actionPerformed(ActionEvent arg0){
      if(arg0.getSource()==b1){
        sendData(t1.getText());
      }
      }
    });
    add(t1);
    add(b1);
    }
	 @Override
  public void run(){
   try{
		 connection = new Socket(InetAddress.getByName("127.0.0.1"),5678);
	   while (true){
		output = new ObjectOutputStream (connection.getOutputStream()); 
		input = new ObjectInputStream (connection.getInputStream());
	   JOptionPane.showMessageDialog(null, (String)input.readObject());
	      }
	   } catch (UnknownHostException e){
       } catch (IOException e){
       } catch (HeadlessException e){
       } catch (ClassNotFoundException e){}
	 }
	
 
	
	private static void sendData( Object obj){
	try{
		output.flush();
	    output.writeObject(obj);
	} catch (IOException e){}
      } 
	}
	
	
	

    