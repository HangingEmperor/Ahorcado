package mainahorcado;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Display extends JFrame implements ActionListener {

    JLabel tiempoActual;
    static JLabel tiempoPartida;
    static JLabel horaActual;
    JButton cancelarPartida;

    public Display() {
        this.setTitle("Temporizador");
        this.setSize(200, 200);
        this.setLayout(new BorderLayout());
        this.setVisible(true);
        initComponents();
    }

    public void initComponents() {
        JPanel panel = new JPanel();
//        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel labelTiempoPartida = new JLabel("", SwingConstants.CENTER);
        labelTiempoPartida.setText("Tiempo en partida: ");
        tiempoPartida = new JLabel("", SwingConstants.CENTER);
        tiempoPartida.setText("00:00:00");

        JLabel labelHoraActual = new JLabel("", SwingConstants.CENTER);
        labelHoraActual.setText("Hora actual: ");
        horaActual = new JLabel("", SwingConstants.CENTER);

        cancelarPartida = new JButton();
        cancelarPartida.setText("Cancelar partida");
        cancelarPartida.addActionListener(this);

        panel.add(labelTiempoPartida);
        panel.add(tiempoPartida);
        panel.add(labelHoraActual);
        panel.add(horaActual);
        panel.add(cancelarPartida);
        this.add(panel);
    }

    public void printTime(int hour, int minute, int second) {
        String fullHour = "";

        fullHour += (hour > 9) ? ":" + hour : "0" + hour;
        fullHour += (minute > 9) ? ":" + minute : ":0" + minute;
        fullHour += (second > 9) ? ":" + second : ":0" + second;
        tiempoPartida.setText(fullHour);

        Date ahora = new Date();
        SimpleDateFormat formateador = new SimpleDateFormat("hh:mm:ss");
        horaActual.setText(formateador.format(ahora));
    }

    static int hour = 0;
    static int minute = 0;
    static int secondTotal = 0;
    static int second;
    static boolean isTimerRunning;
    Display display;
    int limit;

    public void setLimit(int limit) {
        this.limit = limit;
    }
    public static javax.swing.Timer miTemporizador;

    public void setTimer(javax.swing.Timer miTemporizador) {
        this.miTemporizador = miTemporizador;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        isTimerRunning = true;
        secondTotal++;
        second++;

        if (second > 59) {
            second = 0;
            minute++;
            if (minute > 60) {
                minute = 0;
                hour++;
                if (hour > 24) {
                    System.out.println("Are you crazy?");
                }
            }
        }
        if (secondTotal == 10000) {
            isTimerRunning = false;
            System.exit(0);
        }
        if (ae.getSource() == cancelarPartida) {
            this.miTemporizador.stop();
            Juego.restartGame();
            isTimerRunning = true;
            secondTotal = 0;
            second = 0;
            minute = 0;
            hour = 0;
            this.miTemporizador.restart();
            this.miTemporizador.start();
        }
        if (isTimerRunning) {
            this.printTime(hour, minute, second);
        }
    }
}
