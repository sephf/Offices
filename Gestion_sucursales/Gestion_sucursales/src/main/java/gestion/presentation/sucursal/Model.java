package gestion.presentation.sucursal;

import gestion.logic.Sucursal;

import java.util.Observable;

public class Model extends Observable {
    int modo;
    Sucursal current;
    public Model(){}

    public void setCurrent(Sucursal current) {
        this.current = current;
    }

    public void setModo(int modo) {
        this.modo = modo;
    }

    public int getModo() {
        return modo;
    }

    public Sucursal getCurrent() {
        return current;
    }
    public void commit(){
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
