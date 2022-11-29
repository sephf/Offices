package gestion.presentation.empleados;

import gestion.logic.Empleado;

import java.util.List;
import java.util.Observable;

public class Model extends Observable {
    List<Empleado> empleados;
    public Model(){};
    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }
    public void commit()
    {
        setChanged();
        notifyObservers(null);
    }
    @Override
    public void addObserver(java.util.Observer o)
    {
        super.addObserver(o);
        this.commit();
    }
}
