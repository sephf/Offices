package gestion.presentation.main;

import javax.swing.*;
import java.util.Observer;
import java.util.Observable;
public class View implements Observer{
    private JTabbedPane tabbedPane;
    public JTabbedPane getPanel(){return  tabbedPane;}
    Controller controller;
    Model model;
    public void setController(Controller c){this.controller=c;}
    public void setModel(Model m)
    {
        this.model=m;
        model.addObserver(this);
    }
    @Override
    public void update(Observable updateModel,Object parametros)
    {

    }
}
