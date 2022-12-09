
package cronometro;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// TO DO: PASAR LABELS A STRINGS

/**
 * Ésta clase contiene el cronómetro, cada instancia es independiente
 * 
 * @author https://github.com/rodrigovittori
 */

public class Cronometro implements ActionListener
{

    // Frame y botones
    JFrame frame = new JFrame();
    JButton botonInicio = new JButton("INICIAR");
    JButton botonReiniciar = new JButton("REINICIAR");
    JLabel labelTiempo = new JLabel();
    
    // Atributos
    Integer tiempoTranscurrido = 0; // Tiempo en milisegundos
    Integer segundos = 0; // Tiempo en segundos
    Integer minutos = 0; // Tiempo en minutos
    Integer horas = 0; // Tiempo en horas
    boolean relojCorriendo = false;
    String segundos_string = String.format("%02d", segundos); // Estos son los nombres de las variables
    String minutos_string = String.format("%02d", minutos);
    String horas_string = String.format("%02d", horas);
    
    // Timer
    Timer timer = new Timer(1000, new ActionListener()
    {
        @Override
	public void actionPerformed(ActionEvent e)
	{
		tiempoTranscurrido = tiempoTranscurrido + 1000;
		segundos = (tiempoTranscurrido / 1000) % 60;
		minutos = (tiempoTranscurrido / 60000) % 60;
		horas = (tiempoTranscurrido / 3600000);

		actualizarLabel();
	}
    });
    
    // Constructores
    
    /**
     * labelTiempo es donde mostraremos el tiempo
     * botonInicio es el botón de Iniciar/Detener
     * botonReiniciar es el boton de reinicio
     * -> TO DO: Reemplazar texto en botones por logos
     * 
     * TO DO: Agregar lista (hora inicio/finalización/diferencia)
     * TO DO: Agregar boton contador
     * TO DO: Agregar tiempo promedio
     * TO DO: Agregar diferencia total
     */

    public Cronometro()
    {
        labelTiempo.setText(horas_string + ":" + minutos_string + ":" + segundos_string);
        labelTiempo.setBounds(100,100,200,100); // X.pos, Y.pos, Width, Height... 
        labelTiempo.setFont(new Font("Verdana", Font.PLAIN, 35));
        labelTiempo.setBorder(BorderFactory.createBevelBorder(1));
        labelTiempo.setOpaque(true);
        labelTiempo.setHorizontalAlignment(JTextField.CENTER);
        
        botonInicio.setBounds(100,200,100,50);
        botonInicio.setFont(new Font("Verdana", Font.PLAIN, 12));
        botonInicio.setFocusable(false);
        botonInicio.addActionListener(this);
        
        botonReiniciar.setBounds(200,200,100,50);
        botonReiniciar.setFont(new Font("Verdana", Font.PLAIN, 12));
        botonReiniciar.setFocusable(false);
        botonReiniciar.addActionListener(this);
        
        frame.add(labelTiempo);
        frame.add(botonInicio);
        frame.add(botonReiniciar);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,420);
        frame.setLayout(null);

        frame.setVisible(true); // Dejar esta línea al último para evitar problemas con la visibilidad de componentes 
    }
    
    // Getters
    // Setters
    
    // Métodos de la Interfaz

    /**
     *
     * @param ae
     */
    
    @Override
    public void actionPerformed(ActionEvent ae)
    {
      if (ae.getSource()==botonInicio)
        {
          if (!relojCorriendo)
             {
              relojCorriendo = true;
              botonInicio.setText("PAUSAR");
              start();
             }
          else
             {
             relojCorriendo = false;
             botonInicio.setText("REANUDAR");
             stop();
             }
        }
      
      if (ae.getSource() == botonReiniciar)
         {
          relojCorriendo = false;
          botonInicio.setText("INICIAR");
          reset();
          stop();
         }
      
    }
    
    // Métodos de la clase
    
    void start() { timer.start(); }
    
    void stop() { timer.stop(); }
    
    void reset()
    {
    timer.restart();
    
    tiempoTranscurrido = 0;
    segundos = 0;
    minutos = 0;
    horas = 0;

    actualizarLabel();
    }
    
    void actualizarLabel()
    {
    String seconds_string = String.format("%02d", segundos);
    String minutes_string = String.format("%02d", minutos);
    String hours_string = String.format("%02d", horas);

    labelTiempo.setText(hours_string + ":" + minutes_string + ":" + seconds_string);
    }
}
