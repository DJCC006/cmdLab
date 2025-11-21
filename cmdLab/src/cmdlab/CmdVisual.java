/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cmdlab;

/**
 *
 * @author Hp
 */

import javax.swing.*;
import java.awt.*;

public class CmdVisual extends JFrame {
    
    private JTextArea Consola;
    private JTextField Input;
    private logica Logica;
    
    public CmdVisual() {
        setTitle("Command Prompt");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        //Barrita azul de arriba asi como se mira en la imagen toda tumbada
        JPanel BarraAzul = new JPanel();
        BarraAzul.setLayout(new BorderLayout());
        BarraAzul.setBackground(new Color(0, 120, 215)); //Pa simular el color que sale en la imagen
        
        JLabel LblTitulo = new JLabel("Administrador: Command Prompt");
        LblTitulo.setForeground(Color.WHITE);
        LblTitulo.setFont(new Font("Consolas", Font.PLAIN, 14));
        
        BarraAzul.add(LblTitulo, BorderLayout.WEST);
        
        add(BarraAzul, BorderLayout.NORTH);
        
        
        Consola = new JTextArea();
        Consola.setEditable(false);
        Consola.setBackground(Color.BLACK);
        Consola.setForeground(Color.WHITE);
        Consola.setFont(new Font("Consolas", Font.PLAIN, 14));
        Consola.setLineWrap(true);
        Consola.setWrapStyleWord(true);
        
        JScrollPane Scroll = new JScrollPane(Consola);
        
        add(Scroll, BorderLayout.CENTER);
        
        
        Input = new JTextField();
        Input.setBackground(Color.BLACK);
        Input.setForeground(Color.WHITE);
        Input.setCaretColor(Color.WHITE);
        Input.setFont(new Font("Consolas", Font.PLAIN, 14));
        
        //El texto que se mira cada que se abre el cmd
        Consola.append("Microsoft Windows [Version 10.0.19045.6466]\n");
        Consola.append("(c) Patito Corporation. Todos los derechos reservados.\n\n");
        MostrarPrompt();
        

        //Aqui se mira lo de la tecla ENTER para poder ejecutar los comandos
        Input.addActionListener(e -> {
            String comando = Input.getText().trim();
            
            if (!comando.isEmpty()) {
                Consola.append(comando + "\n");
                
                ManejarComando(comando);
            }
            
            Input.setText("");
            MostrarPrompt();
        });
    }
    
    private void MostrarPrompt() {
//        Consola.append(Logica.getPrompt());

        Consola.setCaretPosition(Consola.getDocument().getLength());
    }
    
    private void ManejarComando(String comando) {
        String[] partes = comando.split("\\s+", 2); //Segun google esto significa, "para uno o mas caracteres de espacio en blanco"
        
        String cmd = partes[0].toLowerCase();
        String argumento = (partes.length > 1) ? partes[1].trim() : "";
        
        try {
            //Pa los comando especiales de WR y RD ya que estos piden texto
            if (cmd.equals("WR") || cmd.equals("escribir")) {
                String texto = JOptionPane.showInputDialog(this, "Texto a escribir en " + argumento + ":", "Escribir archivo", JOptionPane.PLAIN_MESSAGE);
                
                if (texto != null) {
//                    Consola.append(Logica.writetoFile(argumento, texto));
                }
            } else if (cmd.equals("RD") || cmd.equals("leer")) {
//                Consola.append(Logica.readFile(argumento));
            } else if (cmd.equals("cls")) {
                Consola.setText("");
            } else {
//                Consola.append(logica.processCommand());
            }
        } catch (Exception e) {
            Consola.append("Error: " + e.getMessage() + "\n");
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CmdVisual().setVisible(true);
        });
    }
}
