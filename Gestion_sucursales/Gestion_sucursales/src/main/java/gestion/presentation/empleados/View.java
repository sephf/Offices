package gestion.presentation.empleados;
import gestion.logic.Sucursal;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
public class View implements Observer{
    private JButton reporteFld;
    private JPanel panel1;
    private JLabel nombreLbl;
    private JTextField nombreFld;
    private JButton buscarFld;
    private JButton agregarFld;
    private JButton borrarFld;
    private JTable empleadosFld;

    public View(){

        buscarFld.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controller.buscar(nombreFld.getText());
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
                controller.borrar(empleadosFld.getSelectedRow());
            }
        });
        reporteFld.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Se ha generado el pdf");
               try {
                    controller.imprimir();
                    if (Desktop.isDesktopSupported()) {
                        File myFile = new File("empleados.pdf");
                        Desktop.getDesktop().open(myFile);
                    }
                } catch (Exception ex) {
                   System.out.println(ex.getMessage());
                   ex.printStackTrace();
               }
            }
        });
        empleadosFld.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = empleadosFld.getSelectedRow();
                    controller.editar(row);
                }
            }
            }
        );
    }
    public JPanel getPanel(){return panel1;}

    Controller controller;
    Model model;
    public void setController(Controller controller){this.controller=controller;}
    public void setModel(Model model){
        this.model=model;
        model.addObserver(this);
    }
    @Override
    public void update(Observable updateModel,Object parametros){
        int[] cols={TableModel.CEDULA, TableModel.NOMBRE,TableModel.TELEFONO, TableModel.SALARIO, TableModel.SUCURSAL,TableModel.ZONAJE,TableModel.SALTOTAL};
        empleadosFld.setModel(new gestion.presentation.empleados.TableModel(cols,model.getEmpleados()));
        empleadosFld.setRowHeight(30);
        this.panel1.revalidate();
    }
}
