package Memoria;

import Note.Nota;

@FunctionalInterface
public interface FiltroNota {
    // il metodo che decide se la nota va bene o no, ha bisogno di una lambda
    boolean accetta(Nota nota);
}
