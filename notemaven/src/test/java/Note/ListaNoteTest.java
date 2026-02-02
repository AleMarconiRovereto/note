package Note;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

public class ListaNoteTest {

    private ListaNote listaNote;

    @BeforeEach
    public void setUp() {
        listaNote = new ListaNote();
        // Svuotiamo la lista per i test unitari se carica dati reali
        listaNote.setListaNote(new ArrayList<>());
    }

    @Test
    public void testAggiuntaNota() {
        Nota n = new Nota("Titolo Test", "Contenuto");
        listaNote.pushaNotaInList(n);

        assertEquals(1, listaNote.listaNote.size());
        assertEquals(n, listaNote.listaNote.get(0));
    }

    @Test
    public void testRitornaPosizionePerNome() {
        listaNote.pushaNotaInList(new Nota("Nota 1", "Contenuto"));
        listaNote.pushaNotaInList(new Nota("Nota 2", "Contenuto"));

        int pos = listaNote.returnJsonPositionInList("Nota 2");
        assertEquals(1, pos);

        int posInesistente = listaNote.returnJsonPositionInList("Inesistente");
        assertEquals(-1, posInesistente);
    }
}
