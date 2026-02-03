
import Memoria.Memoria;
import Note.ListaNote;
import Note.Nota;
import Note.NotaConAllert;
import java.time.format.DateTimeFormatter;
import utils.LoggerClass;

public class PaginaNotaAperta extends javax.swing.JFrame {

    Memoria memoria;
    MainPage contestoPrincipale;
    javax.swing.JFrame paginaPadre;
    ListaNote listaNote;
    int currentIndex;

    // prende gli argomenti necssari tra cui il context esterno per aggiornare il
    // model
    public PaginaNotaAperta(Memoria memoriaEsterna, MainPage contestoPrincipale, ListaNote listaNoteEsterna,
            int indexNota, javax.swing.JFrame paginaPadreEsterna) {
        this.contestoPrincipale = contestoPrincipale;
        this.listaNote = listaNoteEsterna;
        this.currentIndex = indexNota;
        this.memoria = memoriaEsterna;
        this.paginaPadre = paginaPadreEsterna;
        initComponents();
        if (paginaPadre != null) {
            paginaPadre.setEnabled(false);
        }

        getContentPane().setBackground(new java.awt.Color(255, 255, 153));
        inputTestoNota.setBackground(new java.awt.Color(255, 255, 204));
        if (indexNota != -1) {
            setCurrentValue(listaNote.listaNote.get(indexNota));
        } else {
            setCurrentValue(listaNote.listaNote.get(0));
        }
    }

    // se esiste una pagina antecedente la disattiva
    @Override
    public void dispose() {
        if (paginaPadre != null) {
            paginaPadre.setEnabled(true);
            paginaPadre.toFront();
        }
        super.dispose();
    }

    // utile per risolvere il problema della navigazione di note
    // perchè questa pagina può essere aperta da 2 pagine diverse
    // ognuna con la sua logica (saltare note senza alert)
    private int findNextValidIndex(int start, boolean forward) {
        if (paginaPadre instanceof PaginaReminders) {
            int i = forward ? start + 1 : start - 1;
            while (i >= 0 && i < listaNote.listaNote.size()) {
                Nota n = listaNote.listaNote.get(i);
                if (n instanceof NotaConAllert && ((NotaConAllert) n).isAllertAttivo()) {
                    return i;
                }
                i = forward ? i + 1 : i - 1;
            }
            return -1;
        }

        int next = forward ? start + 1 : start - 1;
        if (next >= 0 && next < listaNote.listaNote.size()) {
            return next;
        }
        return -1;
    }

    // setta i valori da mostrare
    private void setCurrentValue(Nota notaDaMostrare) {
        LoggerClass.debug("Visualizzazione nota: {}", notaDaMostrare.titolo);
        labelTitoloNota.setText(notaDaMostrare.titolo);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String stringaTime = notaDaMostrare.timestamp.format(formatter);
        labelTimestamp.setText(stringaTime);
        inputTestoNota.setText(notaDaMostrare.testo);
        if (notaDaMostrare instanceof NotaConAllert) {
            NotaConAllert notaConAllert = (NotaConAllert) notaDaMostrare;
            bottoneAllert.setSelected(notaConAllert.isAllertAttivo());
        } else {
            bottoneAllert.setSelected(false);
        }

        btnBack.setEnabled(findNextValidIndex(currentIndex, false) != -1);
        btnNext.setEnabled(findNextValidIndex(currentIndex, true) != -1);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        inputTestoNota = new javax.swing.JTextArea();
        labelTitoloNota = new javax.swing.JLabel();
        labelTimestamp = new javax.swing.JLabel();
        bottoneAllert = new javax.swing.JRadioButton();
        btnBack = new javax.swing.JToggleButton();
        btnNext = new javax.swing.JToggleButton();
        bottoneSalva = new javax.swing.JButton();
        bottoneElimina = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        inputTestoNota.setColumns(20);
        inputTestoNota.setRows(5);
        jScrollPane1.setViewportView(inputTestoNota);

        labelTitoloNota.setText("labelTitoloNota");

        labelTimestamp.setText("jLabel2");

        bottoneAllert.setText("Allert");

        btnBack.setText("<");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        btnNext.setText(">");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        bottoneSalva.setText("Salva");
        bottoneSalva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bottoneSalvaActionPerformed(evt);
            }
        });

        bottoneElimina.setText("Elimina");
        bottoneElimina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bottoneEliminaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jScrollPane1,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, 376,
                                                                Short.MAX_VALUE)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(btnBack)
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(bottoneSalva,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                        Short.MAX_VALUE)
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(btnNext))
                                                        .addComponent(labelTitoloNota,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addContainerGap())
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(bottoneAllert)
                                                        .addComponent(labelTimestamp))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(bottoneElimina)
                                                .addGap(22, 22, 22)))));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(labelTitoloNota)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 117,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(labelTimestamp)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(bottoneAllert)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48,
                                                        Short.MAX_VALUE)
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(btnBack)
                                                        .addComponent(btnNext)
                                                        .addComponent(bottoneSalva))
                                                .addGap(21, 21, 21))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(bottoneElimina)
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        Short.MAX_VALUE)))));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnBackActionPerformed
        int nextIndex = findNextValidIndex(this.currentIndex, false);
        if (nextIndex != -1) {
            this.currentIndex = nextIndex;
            setCurrentValue(listaNote.listaNote.get(this.currentIndex));
        }
    }// GEN-LAST:event_btnBackActionPerformed

    private void bottoneSalvaActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_bottoneSalvaActionPerformed
        Nota nuovaNota;
        Nota notaVecchia = listaNote.listaNote.get(this.currentIndex);

        if (bottoneAllert.isSelected()) {
            nuovaNota = new NotaConAllert(labelTitoloNota.getText(), inputTestoNota.getText(), notaVecchia.timestamp,
                    bottoneAllert.isSelected());
        } else {
            nuovaNota = new Nota(labelTitoloNota.getText(), inputTestoNota.getText(), notaVecchia.timestamp);
        }

        try {
            memoria.rimuoviNota(notaVecchia);
            listaNote.listaNote.set(currentIndex, nuovaNota);
            memoria.salvaNota(nuovaNota);

            contestoPrincipale.updateComponenteListaNoteModel();

            if (paginaPadre instanceof PaginaReminders) {
                ((PaginaReminders) paginaPadre).updateComponenteListaRemindersModel();
            }

            setCurrentValue(nuovaNota);
        } catch (Exception ex) {
            LoggerClass.error("Errore durante il salvataggio della nota modificata", ex);
        }
    }

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {
        int nextIndex = findNextValidIndex(this.currentIndex, true);
        if (nextIndex != -1) {
            this.currentIndex = nextIndex;
            setCurrentValue(listaNote.listaNote.get(this.currentIndex));
        }
    }

    private void bottoneEliminaActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            memoria.rimuoviNota(listaNote.listaNote.get(this.currentIndex));
            listaNote.listaNote.remove(currentIndex);

            contestoPrincipale.updateComponenteListaNoteModel();

            if (paginaPadre instanceof PaginaReminders) {
                ((PaginaReminders) paginaPadre).updateComponenteListaRemindersModel();
            }

            int nextValid = findNextValidIndex(currentIndex - 1, true);

            if (nextValid != -1) {
                this.currentIndex = nextValid;
                setCurrentValue(listaNote.listaNote.get(this.currentIndex));
            } else {

                int prevValid = findNextValidIndex(currentIndex, false);
                if (prevValid != -1) {
                    this.currentIndex = prevValid;
                    setCurrentValue(listaNote.listaNote.get(this.currentIndex));
                } else {

                    this.dispose();
                }
            }
        } catch (Exception e) {
            LoggerClass.error("Errore durante l'eliminazione della nota", e);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton bottoneAllert;
    private javax.swing.JButton bottoneElimina;
    private javax.swing.JButton bottoneSalva;
    private javax.swing.JToggleButton btnBack;
    private javax.swing.JToggleButton btnNext;
    private javax.swing.JTextArea inputTestoNota;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelTimestamp;
    private javax.swing.JLabel labelTitoloNota;
    // End of variables declaration//GEN-END:variables
}
