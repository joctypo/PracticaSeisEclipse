package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.google.gson.Gson;

import processing.core.PApplet;

public class Main extends PApplet {
	

	//Globales
	int xBolita = 250;
	int yBolita = 250;
	int boton;
	int r=255;
	int g=255;
	int b=255;
	
	
	public static void main(String[] args) {
		PApplet.main("main.Main");
	}

	// 1
	public void settings() {
		size(500, 500);
	}

	// 1
	public void setup() {

		// Ejecutar en segundo
		new Thread(() -> {
			try {
				ServerSocket server = new ServerSocket(5000);
				
				System.out.println("Esperando cliente...");
				
				Socket socket = server.accept();
				
				System.out.println("Cliente esta conectado");

				
				InputStream is = socket.getInputStream();
				OutputStream os = socket.getOutputStream();

				// Hacer que el objeto is tenga la capacidad de leer Strings completos
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader breader = new BufferedReader(isr);

				while (true) {
					// Esperando mensaje
					System.out.println("Esperando mensaje...");
					String mensajeRecibido = breader.readLine(); //BW::X::Y::ALTO::ANCHO
					
					System.out.println(mensajeRecibido);
					
					Gson gson = new Gson();
					
					//Deserializacion
					Coordenada coord= gson.fromJson(mensajeRecibido,Coordenada.class);
					
					System.out.println(coord.getb());
					
					//Coordenada obj = gson.fromJson(mensajeRecibido, Coordenada.class);
					
					switch (coord.getb()) {
					
					case 1: 
					
						xBolita = xBolita + 10;
						
					
						break;
						
						
					case 2: 
						yBolita = yBolita -10;
					
						
						break; 
						
						
					case 3: 
						
						
						xBolita = xBolita - 10;
						break;
					
					case 4: 
						
						
						yBolita = yBolita + 10;
						break;
						
						
					case 5:
						r= (int)(Math.random()*255+1);
						b= (int)(Math.random()*255+1);
						g= (int)(Math.random()*255+1);
						
						
						break;
					
					
					
					
					}
					
					
					
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}).start();

	}

	// Inifito
	public void draw() {
		background(0, 0, 0);
		fill(r,g,b);
		ellipse(xBolita, yBolita, 50, 50);
	}

}
