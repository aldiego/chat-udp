/**
 * 
 */
package br.com.fatec.chat.comunication;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

import br.com.fatec.chat.EntryPoint;
import br.com.fatec.chat.model.User;
import br.com.fatec.chat.util.ChatReaderUtil;
import br.com.fatec.chat.util.ChatWriterUtil;
import br.com.fatec.chat.util.ListaUsuarios;

/**
 * @author Diego
 *
 */
public class Writer implements Runnable {

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		System.out.println("Iniciando Writer...");

		while (!EntryPoint.ALGUEM_NA_SALA) {
			enviaSearch();

			try {
				synchronized (this) {
					this.wait(2000L);
				}
			} catch (InterruptedException e) {
				System.err.println(e);
			}

			if (EntryPoint.SAIR) {
				enviaLeave();
			}
		}

		while (EntryPoint.ALGUEM_NA_SALA) {
			enviaKeepAlive();

			try {
				synchronized (this) {
					this.wait(10000L);
				}
			} catch (InterruptedException e) {
				System.err.println(e);
			}

			if (EntryPoint.SAIR) {
				enviaLeave();
			}
		}

	}

	/**
	 * 
	 */
	private void enviaKeepAlive() {
		try {

			for (String endereco : ListaUsuarios.getUsuarios().keySet()) {

				InetAddress address = InetAddress.getByName(endereco);

				if (address == null)
					continue;

				System.out.println("Enviando KeepAlive... " + address);

				DatagramSocket socket = new DatagramSocket();

				byte[] buf = ChatWriterUtil.montaKeepAlive();
				DatagramPacket packet = new DatagramPacket(buf, buf.length, address, EntryPoint.PORTA);
				socket.send(packet);
				socket.close();
			}

		} catch (IOException e) {
			System.err.println("Erro ao realizar o KeepAlive... " + e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * 
	 */
	public void enviaLeave() {
		try {

			for (String endereco : ListaUsuarios.getUsuarios().keySet()) {

				InetAddress address = InetAddress.getByName(endereco);

				if (address == null)
					continue;

				System.out.println("Enviando Leave... " + address);

				DatagramSocket socket = new DatagramSocket();

				byte[] buf = ChatWriterUtil.montaLeave();
				DatagramPacket packet = new DatagramPacket(buf, buf.length, address, EntryPoint.PORTA);
				socket.send(packet);
				socket.close();
			}

		} catch (IOException e) {
			System.err.println("Erro ao realizar o Leave... " + e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * 
	 */
	private void enviaSearch() {

		try {
			Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();

			while (interfaces.hasMoreElements()) {
				NetworkInterface networkInterface = interfaces.nextElement();
				if (networkInterface.isLoopback())
					continue;

				for (InterfaceAddress interfaceAddress : networkInterface.getInterfaceAddresses()) {
					InetAddress broadcast = interfaceAddress.getBroadcast();
					if (broadcast == null)
						continue;

					System.out.println("Enviando Search... " + broadcast);
					DatagramSocket socket = new DatagramSocket();
					socket.setBroadcast(true);

					// send request
					byte[] buf = ChatWriterUtil.montaSearch();
					DatagramPacket packet = new DatagramPacket(buf, buf.length, broadcast, EntryPoint.PORTA);
					socket.send(packet);
					socket.close();
					ChatReaderUtil.atualizaTelaEnviaSearch(EntryPoint.getChat());
				}
			}
		} catch (IOException e) {
			System.err.println("Erro ao realizar o Search... " + e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * @param user
	 * @param texto
	 */
	public void enviaSay(User user, String texto) {
		byte[] buf;

		if (user != null) {
			buf = ChatWriterUtil.montaSay(user, texto);
		}
		buf = ChatWriterUtil.montaSay(texto);

		try {
			for (String endereco : ListaUsuarios.getUsuarios().keySet()) {

				InetAddress address = InetAddress.getByName(endereco);

				if (address == null)
					continue;

				System.out.println("Enviando Say... " + address);

				DatagramSocket socket = new DatagramSocket();

				DatagramPacket packet = new DatagramPacket(buf, buf.length, address, EntryPoint.PORTA);
				socket.send(packet);
				socket.close();
			}
		} catch (IOException e) {
			System.err.println("Erro ao realizar o Say... " + e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * @param selectedItem
	 * @param text
	 */
	public void enviaWhisper(User user, String texto) {
		byte[] buf = ChatWriterUtil.montaWhisper(user, texto);

		try {
			InetAddress address = InetAddress.getByName(user.getAddress());

			System.out.println("Enviando Whisper... " + address);

			DatagramSocket socket = new DatagramSocket();

			DatagramPacket packet = new DatagramPacket(buf, buf.length, address, EntryPoint.PORTA);
			socket.send(packet);
			socket.close();

		} catch (IOException e) {
			System.err.println("Erro ao realizar o Whisper... " + e);
			throw new RuntimeException(e);
		}
	}

}
