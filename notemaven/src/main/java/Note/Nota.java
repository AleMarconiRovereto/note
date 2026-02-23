package Note;

import java.time.LocalDateTime;

/** Nota semplice senza allert, prima sottoclasse concreta di NotaBase */
public class Nota extends NotaBase {

    public Nota(String titolo, String testo) {
        super(titolo, testo);
    }

    public Nota(String titolo, String testo, LocalDateTime timestamp) {
        super(titolo, testo, timestamp);
    }

    @Override
    public String getDescrizionePerLista() {
        return ": " + testo;
    }
}
