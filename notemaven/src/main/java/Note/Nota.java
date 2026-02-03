package Note;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.Serializable;
import java.util.Objects;

public class Nota implements Serializable {
    private static final long serialVersionUID = 1L;
    public LocalDateTime timestamp;
    public String titolo;
    public String testo;

    public Nota(String titolo, String testo) {
        this.timestamp = LocalDateTime.now();
        this.titolo = titolo;
        this.testo = testo;
    }

    public Nota(String titolo, String testo, LocalDateTime timestamp) {
        this.timestamp = timestamp;
        this.titolo = titolo;
        this.testo = testo;
    }

    public boolean isTitoloEqual(String outsideName) {
        return this.titolo.equals(outsideName);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.timestamp);
        hash = 79 * hash + Objects.hashCode(this.titolo);
        hash = 79 * hash + Objects.hashCode(this.testo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Nota other = (Nota) obj;
        if (!Objects.equals(this.titolo, other.titolo)) {
            return false;
        }
        if (!Objects.equals(this.testo, other.testo)) {
            return false;
        }
        if (!Objects.equals(this.timestamp, other.timestamp)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return "[" + timestamp.format(formatter) + "] " + titolo + ": " + testo;
    }
}
