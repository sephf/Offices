package gestion;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
public class Application {
    public static void main(String[] args) throws Exception {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        }catch (Exception ex){};
        gestion.presentation.empleados.Model empleadosModel= new gestion.presentation.empleados.Model();
        gestion.presentation.empleados.View empleadosView = new gestion.presentation.empleados.View();
        empleadosController = new gestion.presentation.empleados.Controller(empleadosView,empleadosModel);

        gestion.presentation.empleado.Model empleadoModel= new gestion.presentation.empleado.Model();
        gestion.presentation.empleado.View empleadoView = new gestion.presentation.empleado.View();
        empleadoController = new gestion.presentation.empleado.Controller(empleadoView,empleadoModel);
        
        gestion.presentation.sucursal.Model sucursalModel=new gestion.presentation.sucursal.Model();
        gestion.presentation.sucursal.View sucursalView=new gestion.presentation.sucursal.View();
        sucursalController=new gestion.presentation.sucursal.Controller(sucursalView,sucursalModel);

        gestion.presentation.sucursales.Model sucursalesModel=new gestion.presentation.sucursales.Model();
        gestion.presentation.sucursales.View sucursalesView=new gestion.presentation.sucursales.View();
        sucursalesController=new gestion.presentation.sucursales.Controller(sucursalesView,sucursalesModel);

        gestion.presentation.info.Model infoModel=new gestion.presentation.info.Model();
        gestion.presentation.info.View infoView= new gestion.presentation.info.View();
        infoController=new gestion.presentation.info.Controller(infoView,infoModel);

        gestion.presentation.main.Model mainModel= new gestion.presentation.main.Model();
        gestion.presentation.main.View mainView = new gestion.presentation.main.View();
        mainController = new gestion.presentation.main.Controller(mainView, mainModel);

        mainView.getPanel().add("Empleados",empleadosView.getPanel());
        mainView.getPanel().add("Sucursales", sucursalesView.getPanel());
        mainView.getPanel().add("Acerca de..", infoView.getPanel1());
        
        window= new JFrame();
        window.setSize(400,300);
        window.setExtendedState(JFrame.MAXIMIZED_BOTH);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setTitle("SISE: Sistema de Sucursales y Empleados");
        try {
            window.setIconImage((new ImageIcon(Application.class.getResource("/el-mundo.png"))).getImage());
        } catch (Exception e) {}
        window.setVisible(true);
        mainController.show();
    }
    public static gestion.presentation.empleados.Controller empleadosController;
    public static gestion.presentation.empleado.Controller empleadoController;
    
    public static gestion.presentation.sucursal.Controller sucursalController;

    public static gestion.presentation.sucursales.Controller sucursalesController;

    public static gestion.presentation.info.Controller infoController;
    public static gestion.presentation.main.Controller mainController;
    public static JFrame window;
    public static Border BORDER_ERROR= BorderFactory.createMatteBorder(0,0,2,0, Color.RED);
    public static final int MODO_AGREGAR=0;
    public static final int MODO_EDITAR=1;
}
