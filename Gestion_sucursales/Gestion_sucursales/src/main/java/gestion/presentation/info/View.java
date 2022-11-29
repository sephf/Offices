package gestion.presentation.info;

import javax.swing.*;
import java.awt.*;


public class View {
    private JPanel panel1;
    private JLabel encabezadoLbl;

    private void createUIComponents() {
        encabezadoLbl=new JLabel(new ImageIcon("SIP-Trunking-800x534.jpg.webp"));// TODO: place custom component creation code here
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setModel(Model model) {
        this.model = model;
    }
    public JPanel getPanel1(){return panel1;}
    Controller controller;
    Model model;
}
