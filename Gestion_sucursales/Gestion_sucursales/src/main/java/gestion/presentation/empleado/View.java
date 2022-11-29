package gestion.presentation.empleado;

import gestion.Application;
import gestion.logic.Empleado;
import gestion.logic.Sucursal;

import javax.naming.ldap.Control;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class View implements Observer {
    private JPanel panel1;
    private JLabel nombreLbl;
    private JLabel cedulaLbl;
    private JLabel telefonoLbl;
    private JLabel salarioLbl;
    private JLabel sucursalLbl;
    private JTextField cedulaFld;
    private JTextField nombreFld;
    private JTextField telefonoFld;
    private JTextField sucursalFld;
    private String sucursalCode;
    private JTextField salarioFld;
    private JButton guardarFld;
    private JButton cancelarFld;
    private JLabel mapaLbl;

    public View() throws Exception {
        guardarFld.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validate()) {
                    Empleado n = null;
                    try {
                        n = take();
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    try {
                        controller.guardar(n);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(panel1, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                }
                try {
                    selectSucursal(-1);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        cancelarFld.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    selectSucursal(-1);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                controller.hide();
            }
        });
        loadImage();
        cargarSucursales();
        mapaLbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    int index=0;
                    cargarSucursales();
                    for(Sucursal sucursal: Controller.retornaSucursales()){
                        if (sucursal.getUbicacionX() >= e.getX()-10 && sucursal.getUbicacionX() <= e.getX()
                                && sucursal.getUbicacionY() >= e.getY()-16 && sucursal.getUbicacionY() <= e.getY()){
                            sucursalFld.setText(sucursal.getReferencia());
                            sucursalCode=sucursal.getCodigo();
                            selectSucursal(index);
                        }
                        index++;
                    }
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    public JPanel getPanel() {
        return panel1;
    }

    Controller controller;
    Model model;

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setModel(Model model) {
        this.model = model;
        model.addObserver(this);
    }

    @Override
    public void update(Observable updatedModel, Object parametros) {
        Empleado current = model.getCurrent();
        this.cedulaFld.setEnabled(model.getModo() == Application.MODO_AGREGAR);
        this.cedulaFld.setText(current.getCedula());
        this.nombreFld.setText(current.getNombre());
        this.telefonoFld.setText(current.getTelefono());
        this.salarioFld.setText(String.valueOf(current.getSalario()));
        this.sucursalFld.setText(current.getSucursal().getReferencia());
        this.sucursalCode=current.getSucursal().getCodigo();
        this.panel1.validate();
    }

    public Empleado take() throws Exception {
        Empleado e = new Empleado();
        e.setCedula(cedulaFld.getText());
        e.setNombre(nombreFld.getText());
        e.setTelefono(telefonoFld.getText());
        e.setSalario(Double.parseDouble(salarioFld.getText()));
        e.setSucursal(controller.filtraSucursal(sucursalCode));
        return e;
    }

    private boolean validate() {
        boolean valid = true;
        if (cedulaFld.getText().isEmpty()) {
            valid = false;
            cedulaLbl.setBorder(Application.BORDER_ERROR);
            cedulaLbl.setToolTipText("Id requerido");
        } else {
            cedulaLbl.setBorder(null);
            cedulaLbl.setToolTipText(null);
        }

        if (nombreFld.getText().length() == 0) {
            valid = false;
            nombreLbl.setBorder(Application.BORDER_ERROR);
            nombreLbl.setToolTipText("Nombre requerido");
        } else {
            nombreLbl.setBorder(null);
            nombreLbl.setToolTipText(null);
        }
        if (telefonoFld.getText().length() == 0) {
            valid = false;
            telefonoLbl.setBorder(Application.BORDER_ERROR);
            telefonoLbl.setToolTipText("Telefono requerido");
        } else {
            telefonoLbl.setBorder(null);
            telefonoLbl.setToolTipText(null);
        }
        if (salarioFld.getText().isEmpty()) {
            valid = false;
            salarioLbl.setBorder(Application.BORDER_ERROR);
            salarioLbl.setToolTipText("Salario requerido");
        } else {
            salarioLbl.setBorder(null);
            salarioLbl.setToolTipText(null);
        }
        if (sucursalFld.getText().length() == 0) {
            valid = false;
            sucursalLbl.setBorder(Application.BORDER_ERROR);
            sucursalLbl.setToolTipText("Sucursal requerido");
        } else {
            sucursalLbl.setBorder(null);
            sucursalLbl.setToolTipText(null);
        }
        return valid;
    }

    public void loadImage(){

        mapaLbl.setLayout(null);
        mapaLbl.setSize(375,250);
        ImageIcon image = new ImageIcon(Application.class.getResource("/mapa.png"));
        Icon icon = new ImageIcon(image.getImage().getScaledInstance(mapaLbl.getWidth(),mapaLbl.getHeight(),Image.SCALE_SMOOTH));
        mapaLbl.setIcon(icon);
        mapaLbl.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
    }
    private void createUIComponents(){
        loadImage();
    }
    public void cargarSucursales() throws Exception{
        mapaLbl.removeAll();
        JLabel este =null;
            for (Sucursal sucursal : Controller.retornaSucursales()) {
                este = new JLabel();
                este.setSize(20, 20);
                ImageIcon image = new ImageIcon(Application.class.getResource("/azul.png"));
                Icon icon = new ImageIcon(image.getImage().getScaledInstance(este.getWidth(), este.getHeight(), Image.SCALE_SMOOTH));
                este.setIcon(icon);
                este.setBounds(sucursal.getUbicacionX(), sucursal.getUbicacionY(), este.getWidth(), este.getHeight());
                mapaLbl.add(este);
                mapaLbl.repaint();
            }
    }
    public void selectSucursal(int index) {
        JLabel pin=null;
        ImageIcon image=null;
        for(int iterator=0;iterator<mapaLbl.getComponentCount(); iterator++){
            if(index==iterator){
                image = new ImageIcon(Application.class.getResource("/rojo.png"));
            }else{
                image = new ImageIcon(Application.class.getResource("/azul.png"));
            }
            pin=(JLabel)mapaLbl.getComponent(iterator);
            pin.setIcon(new ImageIcon(image.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
        }
    }
}
