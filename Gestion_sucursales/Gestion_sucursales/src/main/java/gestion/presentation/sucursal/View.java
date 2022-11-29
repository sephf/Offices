package gestion.presentation.sucursal;

import com.sun.tools.javac.Main;
import gestion.Application;
import gestion.logic.Sucursal;
import gestion.presentation.sucursal.Controller;
import gestion.presentation.sucursal.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class View implements Observer {
    private JPanel panel1;
    private JLabel codigoLbl;
    private JLabel referenciaLbl;
    private JLabel direccionLbl;
    private JLabel zonajeLbl;
    private JTextField codigoFld;
    private JTextField referenciaFld;
    private JTextField direccionFld;
    private JTextField zonajeFld;
    private JButton guardarFld;
    private JButton cancelarFld;
    private JLabel mapaLbl;
    private int x=0;
    private int y=0;

    public View(){
        guardarFld.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validate()) {
                    Sucursal n = take();
                    try {
                        controller.guardar(n);

                        model.commit();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(panel1, ex.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        loadImage();
        mapaLbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selectPlace(e.getX()-10,e.getY()-16);
            }
        });
        cancelarFld.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.hide();
            }
        });
    }

    public JPanel getPanel() {
        return panel1;
    }

    gestion.presentation.sucursal.Controller controller;
    gestion.presentation.sucursal.Model model;

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setModel(Model model) {
        this.model = model;
        model.addObserver( this);
    }

    @Override
    public void update(Observable updatedModel, Object parametros) {
       Sucursal current = model.getCurrent();
        this.codigoFld.setEnabled(model.getModo() == Application.MODO_AGREGAR);
        this.codigoFld.setText(current.getCodigo());
        this.referenciaFld.setText(current.getReferencia());
        this.zonajeFld.setText(String.valueOf(current.getZonaje()));
        this.direccionFld.setText(current.getDireccion());

        this.panel1.validate();
    }

    public Sucursal take() {
        Sucursal s = new Sucursal();
        s.setCodigo(codigoFld.getText());
        s.setReferencia(referenciaFld.getText());
        s.setDireccion(direccionFld.getText());
        s.setZonaje(Double.parseDouble(zonajeFld.getText()));
        s.setUbicacionX(x);
        s.setUbicacionY(y);
        return s;
    }

    private boolean validate() {
        boolean valid = true;
        if (codigoFld.getText().isEmpty()) {
            valid = false;
            codigoLbl.setBorder(Application.BORDER_ERROR);
            codigoLbl.setToolTipText("Id requerido");
        } else {
            codigoLbl.setBorder(null);
            codigoLbl.setToolTipText(null);
        }

        if (referenciaFld.getText().length() == 0) {
            valid = false;
            referenciaLbl.setBorder(Application.BORDER_ERROR);
            referenciaLbl.setToolTipText("Referencia requerida");
        } else {
            referenciaLbl.setBorder(null);
            referenciaLbl.setToolTipText(null);
        }
        if (direccionFld.getText().length() == 0) {
            valid = false;
            direccionLbl.setBorder(Application.BORDER_ERROR);
            direccionLbl.setToolTipText("Direccion requerida");
        } else {
            direccionLbl.setBorder(null);
            direccionLbl.setToolTipText(null);
        }
        if (zonajeFld.getText().isEmpty()) {
            valid = false;
            zonajeLbl.setBorder(Application.BORDER_ERROR);
            zonajeLbl.setToolTipText("Zonaje requerida");
        } else {
            zonajeLbl.setBorder(null);
            zonajeLbl.setToolTipText(null);
        }   if(x == 0 && y == 0) {
            valid = false;
        } else {
        }
        return valid;
    }
    public void loadImage(){

        mapaLbl.setLayout(null);
        mapaLbl.setSize(375,250);
        ImageIcon image = new ImageIcon(Application.class.getResource("/mapa.png"));
        Icon icon = new ImageIcon(image.getImage().getScaledInstance(mapaLbl.getWidth(), mapaLbl.getHeight(), Image.SCALE_SMOOTH));
        mapaLbl.setIcon(icon);
        mapaLbl.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
    }
    public void selectPlace(int x,int y) {
            if(mapaLbl.getComponentCount()>0){
                JLabel pin=(JLabel)mapaLbl.getComponent(0);
                pin.setBounds(x,y,pin.getWidth(),pin.getWidth());
            }else{
                JLabel este = new JLabel();
                este.setSize(20,20);
                ImageIcon image = new ImageIcon(Application.class.getResource("/azul.png"));
                este.setIcon(new ImageIcon(image.getImage().getScaledInstance(este.getWidth(), este.getHeight(), Image.SCALE_SMOOTH)));
                este.setBounds(x,y,este.getWidth(),este.getHeight());
                mapaLbl.add(este);
                mapaLbl.repaint();
            }
            this.x=x;
            this.y=y;
    }
}
