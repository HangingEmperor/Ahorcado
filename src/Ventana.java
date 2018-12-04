package mainahorcado;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;

/**
 *
 * @author Omar Flores Salazar
 */
public class Ventana extends JFrame implements ActionListener {
    
    static JPanel panelBtn;
    static JPanel panelPalabra;
    static JPanel panelPrincipal;
    static JButton[] letrasBtn = new JButton[28];
    static JLabel palabra;
    static JLabel img;
    JLabel titulo;
    JMenuBar barraMenu;
    JMenu barraOpciones;
    JMenu barraEditar;
    JMenuItem itemConfigurar;
    JMenuItem itemSalir;
    JDialog dialogConfiguracion;
    JRadioButton memoriaButton;
    JRadioButton archivoButton;
    JButton aceptar;
    JButton cancelar;
    JPanel panelOpciones;
    JPanel panelConfiguracion;
    JPanel panelMemoria;
    JTextField ubicacionArchivo;
    JDialog dialogEditar;
    JMenuItem itemEditar;
    JTextField fielUbicacionArchivo;
    JButton btnAbrir;
    JButton btnGuardar;
    JButton btnCancelar;
    JTextArea textArea;
    File abre;
    File guarda;
    JButton btnAplicar;

    /**
     *
     * Constructor que crea la ventana y al inicio se adapta en la pantalla en
     * modo pantalla completa, y si se arrastra se ajustara a la mitad del eje X
     * y un 75% del eje Y.
     *
     * @see Ventana#Ventana()
     */
    public Ventana() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        
        this.setTitle("Ahorcado");
        this.setResizable(true);
        this.setSize(screenSize.width / 2, (screenSize.height / 2) + (screenSize.height / 4));
        this.setLayout(new java.awt.BorderLayout());
//        this.setExtendedState(MAXIMIZED_BOTH);
        this.setMinimumSize(new Dimension(screenSize.width / 2, (screenSize.height / 2) + screenSize.height / 4));
        setDefaultCloseOperation(3);
        
        iniciarComponentes();
    }

    /**
     *
     * Método que inicia los paneles del juego.
     *
     * @see Ventana#iniciarComponentes()
     */
    public void iniciarComponentes() {
        titulo = new JLabel("Adivina la palabra oculta!", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.PLAIN, 60));
        
        img = new JLabel(new ImageIcon("hangman1.png"), SwingConstants.CENTER);
        panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.add(titulo, BorderLayout.NORTH);
        panelPrincipal.add(img, BorderLayout.CENTER);
        
        this.add(panelPrincipal, BorderLayout.NORTH);
        iniciarMenu();
        iniciarPalabra();
        iniciarBotones();
    }
    
    /**
     *
     */
    public void iniciarMenu() {
        barraMenu = new JMenuBar();
        setJMenuBar(barraMenu);
        
        barraOpciones = new JMenu("Opciones");
        barraMenu.add(barraOpciones);
        
        barraEditar = new JMenu("Editar");
        barraMenu.add(barraEditar);
        
        itemEditar = new JMenuItem("Editar archivo");
        itemEditar.addActionListener(this);
        barraEditar.add(itemEditar);
        
        itemConfigurar = new JMenuItem("Configurar");
        itemConfigurar.addActionListener(this);
        barraOpciones.add(itemConfigurar);
        
        itemSalir = new JMenuItem("Salir");
        itemSalir.addActionListener(this);
        barraOpciones.add(itemSalir);
    }

    /**
     *
     * Método que coloca los botones para poder elegir las letras en el juego.
     * (Incluye la tecla Ñ)
     *
     * @see Ventana#iniciarBotones()
     */
    public void iniciarBotones() {
        char[] s = new char[26];
        panelBtn = new JPanel(new java.awt.GridLayout(3, 2, 10, 10));
        
        for (int i = 0; i < 26; i++) {
            // Condicion para poder agregar el caracter Ñ.
            if (i == 14) {
                letrasBtn[27] = new JButton("Ñ");
                letrasBtn[27].addActionListener(this);
                letrasBtn[27].setBackground(Color.WHITE);
                letrasBtn[27].setBorderPainted(false);
                letrasBtn[27].setFocusPainted(false);
                panelBtn.add(letrasBtn[27]);
            }
            s[i] = (char) ('A' + i);
            letrasBtn[i] = new JButton(Character.toString(s[i]));
            letrasBtn[i].addActionListener(this);
            letrasBtn[i].setBackground(Color.WHITE);
            letrasBtn[i].setBorderPainted(false);
            letrasBtn[i].setFocusPainted(false);
            panelBtn.add(letrasBtn[i]);
        }
        this.add(panelBtn, BorderLayout.SOUTH);
    }

    /**
     *
     * Método que crea el panel donde se ubicara la palabra a adivinar en el
     * juego.
     *
     * @see Ventana#iniciarPalabra()
     */
    public void iniciarPalabra() {
        palabra = new JLabel("", SwingConstants.CENTER);
        palabra.setFont(new Font("Serif", Font.PLAIN, 80));
        panelPalabra = new JPanel();
        
        insertarPalabra();
        panelPalabra.add(palabra);
        this.add(panelPalabra);
    }

    /**
     *
     * Método que se encarga de colocar la palabra a adivinar en el panel del
     * juego.
     *
     * @see Ventana#insertarPalabra()
     */
    public static void insertarPalabra() {
        palabra.setText(Palabra.nuevaPalabra());
    }
    
    /**
     *
     */
    public void ventanaConfiguracion() {
        dialogConfiguracion = new JDialog();
        dialogConfiguracion.setTitle("Configuracion");
        dialogConfiguracion.setLayout(new BorderLayout());
        
        panelConfiguracion = new JPanel();
        JLabel labelConfiguracion = new JLabel();
        labelConfiguracion.setText("Configuracion");
        labelConfiguracion.setFont(new Font("Segoe UI", Font.PLAIN, 30));
        panelConfiguracion.add(labelConfiguracion);
        
        panelMemoria = new JPanel();
        panelMemoria.setLayout(new BoxLayout(panelMemoria, BoxLayout.Y_AXIS));
        memoriaButton = new JRadioButton();
        memoriaButton.setText("Memoria");
        memoriaButton.setSelected(true);
        memoriaButton.addActionListener(this);
        
        archivoButton = new JRadioButton();
        archivoButton.setText("Archivo");
        archivoButton.setSelected(false);
        archivoButton.addActionListener(this);
        JLabel elegirOpcion = new JLabel("Usar palabras de:");
        elegirOpcion.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        
        panelMemoria.add(elegirOpcion);
        panelMemoria.add(memoriaButton);
        panelMemoria.add(archivoButton);
        
        panelOpciones = new JPanel(new FlowLayout());
        aceptar = new JButton("Aceptar");
        aceptar.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        aceptar.addActionListener(this);
        cancelar = new JButton("Cancelar");
        cancelar.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        cancelar.addActionListener(this);
        panelOpciones.add(aceptar);
        panelOpciones.add(cancelar);
        
        ubicacionArchivo = new JTextField(20);
        ubicacionArchivo.setText("C:\\");
        ubicacionArchivo.setVisible(true);
        JPanel panelArchivo = new JPanel(new FlowLayout());
        panelArchivo.add(ubicacionArchivo);
        panelMemoria.add(panelArchivo);
        
        dialogConfiguracion.add(panelConfiguracion, BorderLayout.NORTH);
        dialogConfiguracion.add(panelMemoria, BorderLayout.CENTER);
        dialogConfiguracion.add(panelOpciones, BorderLayout.SOUTH);
        dialogConfiguracion.setResizable(false);
        dialogConfiguracion.setSize(400, 400);
        dialogConfiguracion.setVisible(true);
    }
    
    /**
     *
     */
    public void ventanaEditar() {
        dialogEditar = new JDialog();
        dialogEditar.setTitle("Configuracion");
        dialogEditar.setLayout(new BorderLayout());
        
        JPanel panelEditar = new JPanel();
        JLabel labelEditar = new JLabel();
        labelEditar.setText("Configuracion");
        labelEditar.setFont(new Font("Segoe UI", Font.PLAIN, 30));
        panelEditar.add(labelEditar);
        
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        
        JPanel panelArchivoTxt = new JPanel(new FlowLayout());
        JLabel labelArchivoTxt = new JLabel("Archivo: ");
        labelArchivoTxt.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        fielUbicacionArchivo = new JTextField(20);
        fielUbicacionArchivo.setText("C:\\");
        fielUbicacionArchivo.setEditable(false);
        
        textArea = new JTextArea(0, 10);
        textArea.setEditable(true);
        
        JPanel botones = new JPanel(new FlowLayout());
        btnAbrir = new JButton("Abrir");
        btnAbrir.addActionListener(this);
        btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(this);
        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(this);
        btnAplicar = new JButton("Aplicar");
        btnAplicar.addActionListener(this);
        btnAplicar.setEnabled(false);
        botones.add(btnAbrir);
        botones.add(btnGuardar);
        botones.add(btnAplicar);
        botones.add(btnCancelar);
        
        panelArchivoTxt.add(labelArchivoTxt);
        panelArchivoTxt.add(fielUbicacionArchivo);
        container.add(panelArchivoTxt);
        container.add(textArea);
        
        dialogEditar.add(panelEditar, BorderLayout.NORTH);
        dialogEditar.add(container, BorderLayout.CENTER);
        dialogEditar.add(botones, BorderLayout.SOUTH);
        dialogEditar.setResizable(false);
        dialogEditar.setSize(400, 400);
        dialogEditar.setVisible(true);
    }

    /**
     * Metodo que se encarga de verificar si el boton que pulso el usuario es el
     * correcto respecto a la palabra que se debe encontrar.
     *
     * @see Ventana#actionPerformed(java.awt.event.ActionEvent)
     * @param ae
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == itemConfigurar) {
            ventanaConfiguracion();
            ubicacionArchivo.setVisible(false);
        } else if (ae.getSource() == itemEditar) {
            ventanaEditar();
        } else if (ae.getSource() == itemSalir) {
            System.exit(0);
        } else if (ae.getSource() == memoriaButton) {
            if (archivoButton.isSelected()) {
                archivoButton.setSelected(false);
            }
            ubicacionArchivo.setVisible(false);
        } else if (ae.getSource() == archivoButton) {
            if (memoriaButton.isSelected()) {
                memoriaButton.setSelected(false);
            }
            ubicacionArchivo.setVisible(true);
        } else if (ae.getSource() == aceptar) {
            
        } else if (ae.getSource() == cancelar) {
            dialogConfiguracion.dispose();
        } else if (ae.getSource() == btnAbrir) {
            btnAplicar.setEnabled(true);
            textArea.setText(this.abrirArchivo());
            fielUbicacionArchivo.setText(abre.getAbsolutePath());
        } else if (ae.getSource() == btnGuardar) {
            this.guardarArchivo();
            fielUbicacionArchivo.setText(guarda.getAbsolutePath());
        } else if (ae.getSource() == btnCancelar) {
            dialogEditar.dispose();
        } else if (ae.getSource() == btnAplicar) {
            Biblioteca.palabras.clear();
            Archivo a = new Archivo();
            a.abrirArchivo(abre.getAbsolutePath());
            Juego.restartGame();
            JOptionPane.showMessageDialog(null, "Los cambios seran aplicados al"
                    + "archivo que ha sido abierto recientemente, no sera aplicado"
                    + "al archivo que se ha guardado recientemente");
            dialogEditar.dispose();
        } else {
            Juego juego = new Juego();
            String letraPresionada = ae.getActionCommand();
            juego.inGame(letraPresionada);
        }
    }
    
    /**
     *
     * @return
     */
    public String abrirArchivo() {
        String aux = "";
        String texto = "";
        Boolean pass = true;
        while (pass) {
            try {
                JFileChooser file = new JFileChooser();
                file.showOpenDialog(this);
                abre = file.getSelectedFile();
                
                if (abre != null) {
                    FileReader archivos = new FileReader(abre);
                    BufferedReader lee = new BufferedReader(archivos);
                    while ((aux = lee.readLine()) != null) {
                        texto += aux + "\n";
                    }
                    lee.close();
                }
                pass = false;
            } catch (IOException ex) {
                pass = true;
                JOptionPane.showMessageDialog(null, ex + ""
                        + "\nNo se ha encontrado el archivo",
                        "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        }
        
        return texto;
    }
    
    private void guardarArchivo() {
        try {
            String nombre = "";
            JFileChooser fileGuarda = new JFileChooser();
            fileGuarda.showSaveDialog(this);
            guarda = fileGuarda.getSelectedFile();
            
            if (guarda != null) {
                FileWriter save = new FileWriter(guarda + ".txt");
                save.write(textArea.getText());
                save.close();
                JOptionPane.showMessageDialog(null,
                        "El archivo se a guardado exitosamente",
                        "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,
                    "Su archivo no se ha guardado",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }
}
