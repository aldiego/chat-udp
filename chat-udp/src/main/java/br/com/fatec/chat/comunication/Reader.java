/**
 * 
 */
package br.com.fatec.chat.comunication;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

import br.com.fatec.chat.EntryPoint;
import br.com.fatec.chat.util.ChatReaderUtil;

/**
 * @author Diego
 *
 */
public class Reader implements Runnable {

	private boolean stop = false;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		try {
			System.out.println("Iniciando Reader...");
			DatagramSocket dsocket = new DatagramSocket(EntryPoint.PORTA);

			byte[] buffer = new byte[2048];

			DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

			while (!stop) {
				// Wait to receive a datagram
				dsocket.receive(packet);

				// Convert the contents to a string, and display them
				String msg = new String(buffer, 0, packet.getLength());
				System.out.println(packet.getAddress().getHostName() + ": " + msg);

				ChatReaderUtil.atualizaTela(EntryPoint.getChat(), msg, packet.getAddress().getHostName());

				// Reset the length of the packet before reusing it.
				packet.setLength(buffer.length);
			}

			dsocket.close();
		} catch (Exception e) {

			System.err.println(e);

		}
	}

	public boolean isStop() {
		return stop;
	}

	public void setStop(boolean stop) {
		this.stop = stop;
	}

}
