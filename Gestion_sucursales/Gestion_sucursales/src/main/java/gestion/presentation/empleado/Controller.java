package gestion.presentation.empleado;
import gestion.Application;
import gestion.logic.Empleado;
import gestion.logic.Service;
import gestion.logic.Sucursal;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Controller {
    View view;
    Model model;
    public Controller(View view, Model model){
        model.setCurrent(new Empleado());
        this.model=model;
        this.view=view;
        view.setController(this);
        view.setModel(model);
    }
    public void preAgregar(){
        model.setModo(Application.MODO_AGREGAR);
        model.setCurrent(new Empleado());
        model.commit();
        this.show();
    }
    JDialog dialog;
    public void show(){
        dialog = new JDialog(Application.window,"Empleado", true);
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

    public void guardar(Empleado e) throws Exception {
        switch (model.getModo()) {
            case Application.MODO_AGREGAR:
                Service.instance().empleadoAdd(e);
                model.setCurrent(new Empleado());
                break;
            case Application.MODO_EDITAR:
                Service.instance().empleadoUpdate(e);
                model.setCurrent(e);
                break;
        }
        Application.empleadosController.buscar("");
        model.commit();
    }

    public void editar(Empleado e){
        model.setModo(Application.MODO_EDITAR);
        model.setCurrent(e);
        model.commit();
        this.show();
    }

    public static List<Sucursal> retornaSucursales() throws Exception {
        return Service.instance().retornaSucursales();
    }

    public void actualizarMapa() throws Exception {
        view.cargarSucursales();
    }
    public Sucursal filtraSucursal(String codigo)throws Exception{
        return Service.instance().sucursalGet(codigo);
    }
    /*
    public Sucursal filtraSucursalReferencia(String ref) throws Exception {
        return Service.instance().filtraSucursalReferencia(ref);
    }*/
}
