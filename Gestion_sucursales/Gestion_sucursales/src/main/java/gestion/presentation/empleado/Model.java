package gestion.presentation.empleado;

import gestion.logic.Empleado;

import java.util.Observable;
import java.util.Observer;

public class Model extends Observable {
    Empleado current;
    int modo;
    public Model(){}

    public int getModo() {
        return modo;
    }

    public void setModo(int modo) {
        this.modo = modo;
    }

    public void setCurrent(Empleado current) {
        this.current = current;
    }

    public Empleado getCurrent() {
        return current;
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
