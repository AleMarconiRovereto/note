package Note;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;

public class NotaTest {

    @Test
    public void testCreazioneNota() {
        String titolo = "Test Titolo";
        String testo = "Test Contenuto";
        Nota nota = new Nota(titolo, testo);

        assertEquals(titolo, nota.titolo, "Il titolo dovrebbe corrispondere");
        assertEquals(testo, nota.testo, "Il testo dovrebbe corrispondere");
        assertNotNull(nota.timestamp, "Il timestamp non dovrebbe essere null");
    }

    @Test
    public void testIsTitoloEqual() {
        Nota nota = new Nota("Titolo", "Contenuto");
        assertTrue(nota.isTitoloEqual("Titolo"), "Dovrebbe restituire true per lo stesso titolo");
        assertFalse(nota.isTitoloEqual("Altro"), "Dovrebbe restituire false per un titolo diverso");
    }

    @Test
    public void testEquals() {
        LocalDateTime ora = LocalDateTime.now();
        Nota nota1 = new Nota("A", "B", ora);
        Nota nota2 = new Nota("A", "B", ora);
        Nota nota3 = new Nota("Diversa", "B", ora);

        assertEquals(nota1, nota2, "Le due note dovrebbero essere uguali");
        assertNotEquals(nota1, nota3, "Le note con titoli diversi non dovrebbero essere uguali");
    }
}
