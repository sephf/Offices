package gestion.presentation.info;

public class Controller {
    Model model;
    View view;
    public Controller(View view, Model model){
        this.model=model;
        this.view=view;
        view.setController(this);
        view.setModel(model);
    }
}
