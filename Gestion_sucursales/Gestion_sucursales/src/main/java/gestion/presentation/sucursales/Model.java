package gestion.presentation.sucursales;

import gestion.logic.Sucursal;

import java.util.List;
import java.util.Observable;

public class Model extends Observable {
    List<Sucursal> sucursales;
    public Model(){}

    public void setSucursales(List<Sucursal> sucursales) {
        this.sucursales = sucursales;
    }

    public List<Sucursal> getSucursales() {
        return sucursales;
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
