package tictactoe;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class PanelTictactoe extends JPanel{
	
	private static int ganador = 0;
	private static int jugador1 = 0;
	private static int jugador2 = 0;
	
	private int WIDTH;
	private int HEIGHT;
	
	public PanelTictactoe(int WIDTH, int HEIGHT) {
		this.WIDTH = WIDTH;
		this.HEIGHT = HEIGHT;
		this.setSize(WIDTH,HEIGHT);
		setLayout(null);
		iniciarComponentes();
	}
	
	private void iniciarComponentes() {
		colocarEtiquetas();
		colocarBotones();
	}
	
	private void colocarEtiquetas(){		
		JLabel etiqueta = new JLabel("Bienvenido a TicTacToe!!", SwingConstants.CENTER);
		etiqueta.setBounds(WIDTH/2 + WIDTH/5,HEIGHT/6,300,50);
		etiqueta.setForeground(Color.BLACK);
		etiqueta.setOpaque(false);

		etiqueta.setFont(new Font("mv boli", Font.BOLD, 20));
		this.add(etiqueta);
		
		JLabel etiquetaJugador1 = new JLabel("Jugador 1: X");
		etiquetaJugador1.setBounds(WIDTH/2-WIDTH/8,HEIGHT/2,300,50);
		etiquetaJugador1.setForeground(Color.BLACK);
		etiquetaJugador1.setOpaque(false);

		etiquetaJugador1.setFont(new Font("mv boli", Font.BOLD, 20));
		this.add(etiquetaJugador1);
		
		JLabel etiquetaJugador2 = new JLabel("Jugador 2: O");
		etiquetaJugador2.setBounds(WIDTH/2-WIDTH/8,HEIGHT/2+25,300,50);
		etiquetaJugador2.setForeground(Color.BLACK);
		etiquetaJugador2.setOpaque(false);

		etiquetaJugador2.setFont(new Font("mv boli", Font.BOLD, 20));
		this.add(etiquetaJugador2);
		
	}
	
	
	@SuppressWarnings("serial")
	public class Boton extends JButton{
		public int Valor;
	}

	private void colocarBotones() {
		Boton[][] botones = new Boton[3][3];
		int ejeX = WIDTH/9 + WIDTH/2;
		int ejeY = HEIGHT/4;

		MouseListener listener = new MouseAdapter() {
			@Override 
			public void mouseClicked(MouseEvent e) {
				Boton boton = (Boton) e.getSource();
			/*	if(boton.Valor !=0 ) {
					boton.setText("");
					boton.Valor = 0;
				}*/
				if(SwingUtilities.isLeftMouseButton(e) && jugador1+1-jugador2<=1) {
					boton.setText("X");
					boton.Valor = 1;
					jugador1++;
					
				}
				if(SwingUtilities.isRightMouseButton(e) && jugador2+1-jugador1<=0) {
					boton.setText("O");
					boton.Valor = 2;
					jugador2++;
				}
				etiquetaGanador(botones);			
			}	
		};
		
		for(int i = 0; i <3; i++) {
			for(int j = 0; j<3;j++) {
				botones[i][j] = new Boton();
				botones[i][j].setBounds(ejeX,ejeY,WIDTH/6,WIDTH/6);
				botones[i][j].Valor = 0;
				botones[i][j].setFont(new Font("arial",Font.BOLD,50));
				botones[i][j].setForeground(Color.GRAY);
				
				this.add(botones[i][j]);
				ejeX += WIDTH/6;
				botones[i][j].addMouseListener(listener);
			}
			
			ejeX = WIDTH/9+WIDTH/2;
			ejeY += WIDTH/6;
		}	
	}
	
	private void etiquetaGanador(Boton[][] botones){
		if(comprobar(botones)) {
			JLabel etiquetaGanador = new JLabel("El jugador " + ganador + " ha ganado!!");
			etiquetaGanador.setBounds(250,150,300,50);
			etiquetaGanador.setForeground(Color.BLACK);
			etiquetaGanador.setOpaque(false);
			etiquetaGanador.setFont(new Font("mv boli", Font.BOLD, 20));
			
			JLabel etiquetaNuevaPartida = new JLabel("¿Quieres volver a jugar?");
			etiquetaNuevaPartida.setBounds(250,200,300,50);
			etiquetaNuevaPartida.setForeground(Color.BLACK);
			etiquetaNuevaPartida.setOpaque(false);
			etiquetaNuevaPartida.setFont(new Font("mv boli", Font.BOLD, 20));
			
			this.removeAll();
			this.add(etiquetaGanador);
			this.add(etiquetaNuevaPartida);
			botonNuevaPartida();
			this.repaint();
		}
	}
	
	public void eliminarTodo() {
		this.removeAll();
	}
	
	public void repintar() {
		this.repaint();
	}
	
	private void botonNuevaPartida() {
		JButton nuevaPartida = new JButton("SI");
		JButton finPrograma = new JButton("Cerrar juego");
		nuevaPartida.setBounds(250, 250, 120, 30);
		finPrograma.setBounds(380, 250, 120, 30);
		
		MouseListener NuevaPartida = new MouseAdapter() {		
			@Override
			public void mouseClicked(MouseEvent e) {
				eliminarTodo();
				iniciarComponentes();
				repintar();
			}
		};
		MouseListener fin = new MouseAdapter() {		
			@Override
			public void mouseClicked(MouseEvent e) {
                System.exit(0);
			}
		};
		
		nuevaPartida.addMouseListener(NuevaPartida);
		finPrograma.addMouseListener(fin);
		
		this.add(nuevaPartida);
		this.add(finPrograma);		
	}
	
	
	public static boolean comprobar (Boton[][] botones) {
		int cont = 0;
		for(int i = 0; i<3 && cont<2; i++) {
			cont = 0;
			for (int j = 1; j<3; j++) {
				if(botones[i][j].Valor>0 && botones[i][j].Valor == botones[i][j-1].Valor) {
					cont++;
				}
				if(cont == 2) {
					ganador = botones[i][j].Valor;
				}
			}
		}
		for(int i = 0; i<3 && cont<2; i++) {
			cont = 0;
			for (int j = 1; j<3; j++) {
				if(botones[j][i].Valor>0 && botones[j][i].Valor == botones[j-1][i].Valor) {
					cont++;
				}
				if(cont == 2) {
					ganador = botones[j][i].Valor;
				}
			}
		}
		
		for(int i = 1; i<3 && cont<2; i++) {
			if(i == 1) {
				cont = 0;
			}
			if(botones[i][i].Valor>0 && botones[i][i].Valor == botones[i-1][i-1].Valor) {
				cont++;
			}
			if(cont == 2) {
				ganador = botones[i][i].Valor;
			}
		}
		
		for(int i = 1; i<3 && cont<2; i++) {
			if(i == 1) {
				cont = 0;
			}
			if(botones[i][2-i].Valor>0 && botones[i][2-i].Valor == botones[i-1][2-(i-1)].Valor) {
				cont++;
			}
			if(cont == 2) {
				ganador = botones[i][2-i].Valor;
			}
		}

		return cont == 2;
	}
}
