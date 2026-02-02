import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Program {

    private static final Logger logger = LogManager.getLogger(Program.class);
    
    public static void main(String[] args) {
        logger.info("Avvio dell'applicazione Note...");
       java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainPage().setVisible(true);
            }
        });
    }

}
