import controller.ControlGroup;
import model.Model;

public class Main {

    public static void main(String[] args){
       // On lance le jeux ici
        javax.swing.SwingUtilities.invokeLater( new Runnable() {
            public void run() {
                //ControlGroup control = new ControlGroup();
                Model model = new Model();
                ControlGroup control = new ControlGroup(model);
            }
        });

    }
}
