package Note;

import java.time.LocalDateTime;

public class NotaConAllert extends Nota {
    public boolean allertAttivo;

    public NotaConAllert(String titolo, String testo, boolean allertAttivo) {
        
        super(titolo, testo); 
        this.allertAttivo = allertAttivo;
    }
    
    public NotaConAllert(String titolo, String testo, LocalDateTime timestamp, boolean allertAttivo) {
        
        super(titolo, testo, timestamp); 
        this.allertAttivo = allertAttivo;
    }

    public boolean isAllertAttivo() {
        return allertAttivo;
    }

    public void setAllertAttivo(boolean allertAttivo) {
        this.allertAttivo = allertAttivo;
        
    }
    
    
    

    @Override
    public String toString() {
        String resultVecchioMetodo = super.toString();
        if(allertAttivo){
            return resultVecchioMetodo + " - allert attivo!";
        }else{
            return resultVecchioMetodo + " - allert non attivo!";
        }
    }
}
