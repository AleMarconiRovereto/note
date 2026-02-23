package Note;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import Memoria.MemoriaPrincipale;

public class ListaNoteTest {

    private ListaNote listaNote;

    // reset delle liste
    @BeforeEach
    public void setUp() {
        listaNote = new ListaNote(new MemoriaPrincipale("test_note.ser"));
        listaNote.setListaNote(new ArrayList<>());
        
    }

    // test per verificare se una nota viene aggiunta correttamente alla lista
    @Test
    public void testAggiuntaNota() {
        Nota n = new Nota("Titolo Test", "contenuto");
        listaNote.pushaNotaInList(n);

        assertEquals(1, listaNote.listaNote.size());
        assertEquals(n, listaNote.listaNote.get(0));
    }

    // test della ricerca nella lista per nome
    @Test
    public void testRitornaPosizionePerNome() {
        listaNote.pushaNotaInList(new Nota("Nota 1", "contenuto"));
        listaNote.pushaNotaInList(new Nota("Nota 2", "contenuto"));

        int pos = listaNote.returnJsonPositionInList("Nota 2");
        assertEquals(1, pos);

        int posInesistente = listaNote.returnJsonPositionInList("Inesistente");
        assertEquals(-1, posInesistente);
    }
}
