
package Memoria;

import Note.Nota;
import Note.NotaConAllert;
import java.util.List;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;


public class Memoria {
    
    private static final String SEPARATORE = ";";
    public String MEMORIA_FILE_PATH = "memoria.csv";
    
   
    
    private void scriviInCSV( List<Nota> note) throws Exception {
        List<String> righe = new ArrayList<>();
        for (Nota datiNota : note) {
            String rigaCSV = datiNota.titolo + SEPARATORE + datiNota.testo + SEPARATORE + datiNota.timestamp.toString();
            if (datiNota instanceof NotaConAllert) {
                NotaConAllert datoNotaConAllert = (NotaConAllert) datiNota;
                rigaCSV = rigaCSV + SEPARATORE + datoNotaConAllert.allertAttivo;
            }
        
            rigaCSV = rigaCSV + "\n";
            System.out.println(rigaCSV);
            righe.add(rigaCSV);
        }
        Files.write(Paths.get(MEMORIA_FILE_PATH), righe);
    }
    
    //legge i dati da CSV
    public List<Nota> leggiDaFileCSV() throws Exception {
        List<Nota> lista = new ArrayList<>();
        Path path = Paths.get(MEMORIA_FILE_PATH);

        if (!Files.exists(path)){
            return lista;
        } 

        List<String> righe = Files.readAllLines(path);
        for (String riga : righe) {
            String[] parts = riga.split(";");
        
            if (parts.length == 3) {
                Nota notaDaLeggere = new Nota(parts[0], parts[1], LocalDateTime.parse(parts[2]));
                lista.add(notaDaLeggere);
            }

            if (parts.length == 4) {
                NotaConAllert notaDaLeggere = new NotaConAllert( parts[0], parts[1], LocalDateTime.parse(parts[2]), Boolean.parseBoolean(parts[3]));
                lista.add(notaDaLeggere);
            }
        }

        return lista;
    }

    
    
    // salva l'oggetto nel CSV solo se il titolo è univoco
    public void salvaEControllaDuplicatiInCSV(Nota notaDaSalvare) throws Exception {
        List<Nota> note = leggiDaFileCSV();
        for (Nota nota : note) {
            if (nota.titolo.equals(notaDaSalvare.titolo)) {
                System.out.println("Oggetto con nome già esistente: " + notaDaSalvare.titolo);
                return;
            }
        }
        note.add(notaDaSalvare);
        scriviInCSV(note); //salva effettivamente
        System.out.println("Oggetto salvato: " + notaDaSalvare.titolo);
    }

    // rimuove un singolo oggetto dal CSV tramite nome
    public void rimuoviDaCSV(Nota notaDaRimuovereDaCSV) throws Exception {
        List<Nota> oggetti = leggiDaFileCSV();
        List<Nota> filtrati = oggetti.stream().filter(o -> !o.titolo.equals(notaDaRimuovereDaCSV.titolo)).collect(Collectors.toList());
        if (filtrati.size() == oggetti.size()) {
            System.out.println("Nessun oggetto trovato con nome: " + notaDaRimuovereDaCSV.titolo);
        } else {
            scriviInCSV(filtrati);
            //System.out.println("Oggetto rimosso: " + this.nome);
        }
    }

    
    
    
    
    
}
