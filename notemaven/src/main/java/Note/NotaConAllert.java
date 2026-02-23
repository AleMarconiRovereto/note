package Note;

import java.time.LocalDateTime;

// nota con allert astende Nota aggiungendo allertAttivo

public class NotaConAllert extends Nota {
    public boolean allertAttivo;

    public NotaConAllert(String titolo, String testo, boolean allertAttivo) {

        super(titolo, testo);
        this.allertAttivo = allertAttivo;
    }

    // costruttore con data 
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
    public String getDescrizionePerLista() {
        String resultVecchioMetodo = super.getDescrizionePerLista();
        
        if (allertAttivo) {
            return resultVecchioMetodo + " - allert attivo!";
        } else {
            return resultVecchioMetodo + " - allert non attivo!";
            
        }
        
    }
}
