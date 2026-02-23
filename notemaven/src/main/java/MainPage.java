
import Memoria.MemoriaPrincipale;
import Note.ListaNote;
import Note.Nota;
import Note.NotaConAllert;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import utils.LoggerClass;

public class MainPage extends javax.swing.JFrame {

    ListaNote listaNote;
    MemoriaPrincipale memoria;

    // estrae un array di strighe che sono i nomi delle note
    public String[] estraiArrayDiNomiDaListaNote() {
        String[] strings = new String[listaNote.listaNote.size()];
        for (int i = 0; i < listaNote.listaNote.size(); i++) {
            strings[i] = listaNote.listaNote.get(i).titolo;
        }
        return strings;
    }

    // controllo per evitare duplicati by name
    public boolean checkNameAlreadyExist(String outsideName) {
        boolean result = false;
        if (listaNote.listaNote.size() > 0) {
            for (int i = 0; i < listaNote.listaNote.size(); i++) {
                result = listaNote.listaNote.get(i).isTitoloEqual(outsideName);
                if (result == true) {
                    break;
                }
            }
        }

        return result;
    }

    // fa partire la pagina delle note con allert
    private void stampaNoteConAllertAttivo() {
        boolean ciSonoAllert = false;

        for (int i = 0; i < listaNote.listaNote.size(); i++) {
            Nota nota = listaNote.listaNote.get(i);
            if (nota instanceof NotaConAllert) {
                NotaConAllert notaConAllert = (NotaConAllert) nota;
                if (notaConAllert.isAllertAttivo()) {
                    ciSonoAllert = true;
                    break;
                }
            }
        }

        if (ciSonoAllert == true) {
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    PaginaReminders pr = new PaginaReminders(memoria, MainPage.this, listaNote);
                    pr.setLocationRelativeTo(MainPage.this);
                    java.awt.Point p = pr.getLocation();
                    pr.setLocation(p.x + 30, p.y + 30);
                    pr.setVisible(true);
                }
            });
        }
    }

    // si occupa di aggiornare il model per tenere i dati allineati con eventuali modifiche
    public void updateComponenteListaNoteModel() {
        componenteListaNote.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = estraiArrayDiNomiDaListaNote();

            @Override
            public int getSize() {
                return strings.length;
            }

            @Override
            public String getElementAt(int i) {
                return strings[i];
            }

        });
    }

    public MainPage() {
        this("note.ser");
    }

    public MainPage(String storagePath) {
        LoggerClass.info("Inizializzazione MainPage con path: ", storagePath);
        this.memoria = new MemoriaPrincipale(storagePath);
        this.listaNote = new ListaNote(memoria);

        initComponents();

        updateComponenteListaNoteModel();
        labelTitoloInvalido.setVisible(false);
        inputTesto.getDocument().addDocumentListener(new MyDocumentListener());
        inputTitolo.getDocument().addDocumentListener(new MyDocumentListener());
        bottoneSalvaNota.setEnabled(false);
        componenteListaNote.setSelectedIndex(0);

        getContentPane().setBackground(new java.awt.Color(186, 140, 99));
        componenteListaNote.setBackground(new java.awt.Color(210, 180, 140));
        inputTitolo.setBackground(new java.awt.Color(210, 180, 140));
        inputTesto.setBackground(new java.awt.Color(210, 180, 140));

        stampaNoteConAllertAttivo();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jOptionPane1 = new javax.swing.JOptionPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        componenteListaNote = new javax.swing.JList<>();
        bottoneSalvaNota = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        inputTesto = new javax.swing.JTextArea();
        inputTitolo = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        allertOptionButton = new javax.swing.JRadioButton();
        labelTitoloInvalido = new javax.swing.JLabel();

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };

            public int getSize() {
                return strings.length;
            }

            public String getElementAt(int i) {
                return strings[i];
            }
        });
        jScrollPane1.setViewportView(jList1);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {
                        { null, null, null, null },
                        { null, null, null, null },
                        { null, null, null, null },
                        { null, null, null, null }
                },
                new String[] {
                        "Title 1", "Title 2", "Title 3", "Title 4"
                }));
        jScrollPane2.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        componenteListaNote.setBackground(new java.awt.Color(210, 180, 140));
        componenteListaNote.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };

            public int getSize() {
                return strings.length;
            }

            public String getElementAt(int i) {
                return strings[i];
            }
        });
        componenteListaNote.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                componenteListaNoteMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(componenteListaNote);

        bottoneSalvaNota.setText("Salva Nota");
        bottoneSalvaNota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bottoneSalvaNotaActionPerformed(evt);
            }
        });

        inputTesto.setBackground(new java.awt.Color(210, 180, 140));
        inputTesto.setColumns(20);
        inputTesto.setRows(5);
        inputTesto.setText("Scrivi la tua nota");
        jScrollPane4.setViewportView(inputTesto);

        inputTitolo.setBackground(new java.awt.Color(210, 180, 140));
        inputTitolo.setText("Inserisci un titolo");
        inputTitolo.setToolTipText("");

        jLabel1.setText("Crea una nuova Nota");

        allertOptionButton.setText("Allert");
        allertOptionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allertOptionButtonActionPerformed(evt);
            }
        });

        labelTitoloInvalido.setForeground(new java.awt.Color(255, 0, 0));
        labelTitoloInvalido.setText("Titolo Invalido");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane3)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(127, 127, 127)
                                                .addComponent(jLabel1))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jScrollPane4,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 260,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(inputTitolo,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 260,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(bottoneSalvaNota,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(labelTitoloInvalido)
                                                                        .addComponent(allertOptionButton))
                                                                .addGap(0, 0, Short.MAX_VALUE)))))
                                .addContainerGap()));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 131,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(inputTitolo, javax.swing.GroupLayout.PREFERRED_SIZE, 24,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(labelTitoloInvalido))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(allertOptionButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(bottoneSalvaNota)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // salva una nota
    private void bottoneSalvaNotaActionPerformed(java.awt.event.ActionEvent evt) {

        Nota nuovaNota;

        if (allertOptionButton.isSelected()) {
            nuovaNota = new NotaConAllert(inputTitolo.getText(), inputTesto.getText(), allertOptionButton.isSelected());
            listaNote.pushaNotaInList(nuovaNota);
            LoggerClass.debug("Creata nuova nota con allert: {}", nuovaNota.titolo);
        } else {
            nuovaNota = new Nota(inputTitolo.getText(), inputTesto.getText());
            listaNote.pushaNotaInList(nuovaNota);
            LoggerClass.debug("Creata nuova nota: {}", nuovaNota.titolo);
        }

        try {
            memoria.salvaNota(nuovaNota);
            updateComponenteListaNoteModel();
            inputTitolo.setText("Inserisci un titolo");
            inputTesto.setText("Scrivi una nota");
            allertOptionButton.setSelected(false);

            LoggerClass.info("Nota salvata con successo: {}", nuovaNota.titolo);
        } catch (Exception ex) {
            LoggerClass.error("Errore durante il salvataggio della nota: {}", nuovaNota.titolo, ex);
        }

        updateComponenteListaNoteModel();
    }

    private void allertOptionButtonActionPerformed(java.awt.event.ActionEvent evt) {

    }

    // il click sulla singola nota per aprirla
    private void componenteListaNoteMouseClicked(java.awt.event.MouseEvent evt) {
        Object valoreSelezionato = componenteListaNote.getSelectedValue();

        if (valoreSelezionato != null) {
            LoggerClass.info("Apertura nota selezionata: {}", valoreSelezionato);
            final int posizioneOggettoCorrente = listaNote.returnJsonPositionInList(valoreSelezionato.toString());

            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    PaginaNotaAperta pna = new PaginaNotaAperta(memoria, MainPage.this, listaNote,
                            posizioneOggettoCorrente, MainPage.this);
                    pna.setLocationRelativeTo(MainPage.this);
                    java.awt.Point p = pna.getLocation();
                    pna.setLocation(p.x + 30, p.y + 30);
                    pna.setVisible(true);
                }
            });
        } else {
            JOptionPane.showMessageDialog(this, "Devi prima selezionare un elemento dalla lista.");
        }
    }

    // dovrebbe far parte del codice generato da netbeans
    public static void main(String args[]) {
        final String path = (args.length > 0) ? args[0] : "note.ser";

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainPage(path).setVisible(true);
            }
        });
    }

    // il listener sugli input per intercettare gli eventi su testo titolo e botton salva nota
    class MyDocumentListener implements DocumentListener {

        private void handleInputEvent(DocumentEvent event) {
            String testoInput = inputTesto.getText();
            String titoloUnivoco = inputTitolo.getText();

            boolean nomeEsistente = checkNameAlreadyExist(titoloUnivoco);

            labelTitoloInvalido.setVisible(nomeEsistente || titoloUnivoco.length() == 0);

            bottoneSalvaNota.setEnabled(!nomeEsistente && testoInput.length() > 0 && !"".equals(titoloUnivoco) && !"Inserisci un titolo".equals(titoloUnivoco));

        }

        public void insertUpdate(DocumentEvent e) {
            this.handleInputEvent(e);
        }

        public void removeUpdate(DocumentEvent e) {
            this.handleInputEvent(e);
        }

        public void changedUpdate(DocumentEvent e) {
            this.handleInputEvent(e);
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton allertOptionButton;
    private javax.swing.JButton bottoneSalvaNota;
    private javax.swing.JList<String> componenteListaNote;
    private javax.swing.JTextArea inputTesto;
    private javax.swing.JTextField inputTitolo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList<String> jList1;
    private javax.swing.JOptionPane jOptionPane1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel labelTitoloInvalido;
    // End of variables declaration//GEN-END:variables

}
