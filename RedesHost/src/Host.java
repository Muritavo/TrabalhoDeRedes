import java.awt.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Scanner;

public class Host {

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		DatagramSocket serverSocket = new DatagramSocket(9875);

		
		System.out.println("Digite o endereço de broadcast da sua rede");
		String ip = in.next();
		
		FileReader arq = new FileReader("Neighbors.txt");
	    BufferedReader lerArq = new BufferedReader(arq);
	    
	    String line = "";
	    
	    ArrayList<String> lines = new ArrayList();
	    
	    while((line = lerArq.readLine()) != null){
	    	lines.add(line);
	    }
	    
	    byte[] sendData = new byte[1024];
	    
	    String sentence = lines.toString().replaceAll("\\[", "").replaceAll("\\]", "");
	    sendData = sentence.getBytes();
	    
	    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName(ip), 9876);
	    serverSocket.send(sendPacket);
	    
	    byte[] receiveData = new byte[1024];
		DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
		
		String retorno = new String(receiveData);
	    System.err.println(retorno);
	}

}
