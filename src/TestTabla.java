import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class TestTabla {
public static void main(String[] args) {
    ControladorPrincipal.getController().getControladorDatos().getController().cargarMemoria();
    Menu menu= new Menu();
    menu.setVisible(true);
}
}