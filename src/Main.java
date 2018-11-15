import controller.ControlGroup;

public class Main {

    public static void main(String[] args){
       // On lance le jeux ici
        javax.swing.SwingUtilities.invokeLater( new Runnable() {
            public void run() {
                ControlGroup control = new ControlGroup();
            }
        });

    }
}
