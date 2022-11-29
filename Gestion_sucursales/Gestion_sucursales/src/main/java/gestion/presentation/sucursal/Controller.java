package gestion.presentation.sucursal;

import gestion.Application;
import gestion.logic.Sucursal;
import gestion.logic.Service;
import gestion.presentation.sucursal.Model;
import gestion.presentation.sucursal.View;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Controller {
    gestion.presentation.sucursal.View view;
    gestion.presentation.sucursal.Model model;
    public Controller(View view, Model model){
        model.setCurrent(new Sucursal());
        this.model=model;
        this.view=view;
        view.setController(this);
        view.setModel(model);
    }
    public void preAgregar(){
        model.setModo(Application.MODO_AGREGAR);
        model.setCurrent(new Sucursal());
        model.commit();
        this.show();
    }
    JDialog dialog;
    public void show(){
        dialog = new JDialog(Application.window,"Sucursal", true);
        dialog.setSize(600,400);
        dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        dialog.setContentPane(view.getPanel());
        Point location = Application.window.getLocation();
        dialog.setLocation( location.x+400,location.y+100);
        dialog.setVisible(true);
    }

    public void hide(){
        dialog.dispose();
    }

    public void show1(){
        Application.window.setContentPane(view.getPanel());
        Application.window.revalidate();
    }
    public void hide1(){
        Application.mainController.show();
    }

    public void guardar(Sucursal e) throws Exception {
        switch (model.getModo()) {
            case Application.MODO_AGREGAR:
                Service.instance().sucursalAdd(e);
                model.setCurrent(new Sucursal());
                cargarMapaSucursales();
                break;
            case Application.MODO_EDITAR:
                Service.instance().sucursalUpdate(e);
                model.setCurrent(e);
                break;
        }
        Application.sucursalesController.buscar("");
        model.commit();
    }

    public void editar(Sucursal e){
        model.setModo(Application.MODO_EDITAR);
        model.setCurrent(e);
        model.commit();
        this.show();
    }

    /*public Sucursal buscaSucursal(String referencia) throws Exception {
        return Service.instance().filtraSucursalReferencia(referencia);
    }*/

    public void cargarMapaSucursales() throws Exception {
        Application.sucursalesController.actualizarMapa();
        Application.empleadoController.actualizarMapa();
    }

}
