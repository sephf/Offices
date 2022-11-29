package gestion.data;

import gestion.logic.Empleado;
import gestion.logic.Sucursal;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Data {

    private List<Empleado> empleados;
    private List<Sucursal> sucursales;

    public Data()
    {
        this.empleados=new ArrayList<>();
        this.sucursales=new ArrayList<>();
    }

    public List<Sucursal> getSucursales() {
        return sucursales;
    }
    public List<Empleado> getEmpleados()
    {
        return empleados;
    }

    public void setSucursales(List<Sucursal> sucursales) {
        this.sucursales = sucursales;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }
}
