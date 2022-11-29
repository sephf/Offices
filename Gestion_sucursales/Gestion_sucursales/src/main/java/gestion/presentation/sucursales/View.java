package gestion.presentation.sucursales;

import gestion.Application;
import gestion.logic.Sucursal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;

public class View implements Observer {
    private JLabel referenciaLbl;
    private JTextField referenciaFld;
    private JButton buscarFld;
    private JButton agregarFld;
    private JButton borrarFld;
    private JButton reporteLbl;
    private JTable sucursalesFld;
    private JPanel panel;
    private JLabel mapaLbl;


    public View() throws Exception {
        buscarFld.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controller.buscar(referenciaFld.getText());
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        agregarFld.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.preAgregar();
            }
        });
        borrarFld.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.borrar(sucursalesFld.getSelectedRow());
                try {
                    cargarSucursales();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        reporteLbl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Se ha generado el pdf");
                try {
                    controller.imprimir();
                    if (Desktop.isDesktopSupported()) {
                        File myFile = new File("sucursales.pdf");
                        Desktop.getDesktop().open(myFile);
                    }
                } catch (Exception ignored) { }
            }
        });
        sucursalesFld.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount()==2){
                    int row= sucursalesFld.getSelectedRow();
                    controller.editar(row);
                }else{
                    try {
                        selectSucursal(sucursalesFld.getSelectedRow());
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
        loadImage();
        cargarSucursales();
        mapaLbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }
        });
    }
    Controller controller;
    Model model;
    private final int setSize=2;

    public JPanel getPanel(){return panel;}
    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setModel(Model model) {
        this.model = model;
        model.addObserver(this);
    }
    @Override
    public void update(Observable updateModel, Object parametros){
            int[] cols={TableModel.CODIGO, TableModel.REFERENCIA,TableModel.DIRECCION, TableModel.ZONAJE};
            sucursalesFld.setModel(new gestion.presentation.sucursales.TableModel(cols,model.getSucursales()));
            sucursalesFld.setRowHeight(30);
            this.panel.revalidate();
    }

    public void cargarSucursales() throws Exception {
        mapaLbl.removeAll();
        JLabel este = null;
            for (Sucursal sucursal : Controller.retornaSucursales()) {
                este = new JLabel();
                este.setSize(20 * setSize, 20 * setSize);
                ImageIcon image = new ImageIcon(Application.class.getResource("/azul.png"));
                Icon icon = new ImageIcon(image.getImage().getScaledInstance(este.getWidth(), este.getHeight(), Image.SCALE_SMOOTH));
                este.setIcon(icon);
                este.setBounds(sucursal.getUbicacionX() * setSize, sucursal.getUbicacionY() * setSize, este.getWidth(), este.getHeight());
                mapaLbl.add(este);
                mapaLbl.repaint();
            }
    }

    public void loadImage() {
            mapaLbl.setLayout(null);
            mapaLbl.setSize(375 * setSize, 250 * setSize);
            ImageIcon image = new ImageIcon(Application.class.getResource("/mapa.png"));;
            Icon icon = new ImageIcon(image.getImage().getScaledInstance(mapaLbl.getWidth(), mapaLbl.getHeight(), Image.SCALE_SMOOTH));
            mapaLbl.setIcon(icon);
            mapaLbl.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
    }
    public void selectSucursal(int index) throws Exception {
        ImageIcon image=null;
        JLabel pin=null;
        for(int iterator=0;iterator<mapaLbl.getComponentCount(); iterator++){
            if(index==iterator){
                image = new ImageIcon(Application.class.getResource("/rojo.png"));
            }else{
                image = new ImageIcon(Application.class.getResource("/azul.png"));
            }
            pin=(JLabel)mapaLbl.getComponent(iterator);
            pin.setIcon(new ImageIcon(image.getImage().getScaledInstance(20*setSize, 20*setSize, Image.SCALE_SMOOTH)));
        }
    }
}
