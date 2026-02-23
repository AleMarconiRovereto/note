package Note;

import Memoria.MemoriaPrincipale;
import java.util.ArrayList;
import java.util.List;

public class ListaNote {
    public ArrayList<Nota> listaNote;

    public ListaNote(MemoriaPrincipale memoria) {
        try {
            List<Nota> datiDaMemoria = memoria.caricaNote();
            this.listaNote = new ArrayList<>(datiDaMemoria);
        } catch (Exception ex) {
            this.listaNote = new ArrayList<>();
        }
    }

    public void pushaNotaInList(Nota nuovaNotaDaPushare) {
        this.listaNote.add(nuovaNotaDaPushare);
    }

    public void removeNotaInListByPosition(int posizioneNotaDaRimuovere) {
        
        this.listaNote.remove(posizioneNotaDaRimuovere);
    }

    public void setListaNote(ArrayList<Nota> listaNoteEsterna) {
        
        this.listaNote = listaNoteEsterna;
    }

    public int returnJsonPositionInList(String nomeDaTrovare) {
        
        for (int i = 0; i < this.listaNote.size(); i++) {
            
            if (nomeDaTrovare.equals(this.listaNote.get(i).titolo)) {
                return i;            
            }
        }
        
        return -1;
    }

    @Override
    public String toString() {
        String stampaArray = "";

        if (this.listaNote.size() > 0) {
            for (int i = 0; i < this.listaNote.size(); i++) {
                stampaArray = stampaArray + this.listaNote.toString();
            }
            return stampaArray;
        } else {
            return "Array dell Note vuoto";
        }

    }

}
