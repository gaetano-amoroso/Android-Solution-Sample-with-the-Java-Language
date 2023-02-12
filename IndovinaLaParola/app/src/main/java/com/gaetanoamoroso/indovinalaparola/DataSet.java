package com.gaetanoamoroso.indovinalaparola;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class DataSet {
    private Map<String, String> wordList;

    public DataSet() {
        wordList = new HashMap<>();
        wordList.put("Impegnativo", "Qualcosa che richiede molto sforzo o dedizione");
        wordList.put("Eccezionale", "Al di fuori del comune, straordinario");
        wordList.put("Microscopio", "Strumento scientifico che consente di vedere oggetti molto piccoli");
        wordList.put("Abbreviazione", "Forma più corta di una parola o di una frase");
        wordList.put("Archiviazione", "Processo di registrazione e conservazione di documenti");
        wordList.put("Conquistare", "Prendere il controllo di un territorio o di un gruppo di persone");
        wordList.put("Determinazione", "Decisione ferma e risoluta di fare qualcosa");
        wordList.put("Espressivo", "Che esprime sentimenti o emozioni");
        wordList.put("Fotografia", "Arte o scienza di catturare immagini su pellicola o digitalmente");
        wordList.put("Gerarchia", "Organizzazione di elementi o persone secondo un ordine di importanza");
        wordList.put("Iperbole", "Figura retorica che consiste nell'esagerare volontariamente la realtà");
        wordList.put("Jubilato", "Persona che ha raggiunto la pensione o che gode di altri diritti simili");
        wordList.put("Logaritmo", "Funzione matematica che descrive il rapporto tra due quantità");
        wordList.put("Metallurgia", "Scienza che si occupa della produzione e lavorazione dei metalli");
        wordList.put("Nautico", "Relativo al mare o alla navigazione");
        wordList.put("Otorinolaringoiatra", "Medico specializzato in malattie dell'orecchio, del naso e della gola");
        wordList.put("Pancreatite", "Infiammazione del pancreas, un organo importante per la digestione");
        wordList.put("Quadrato", "Forma geometrica con quattro lati uguali e quattro angoli retti");
        wordList.put("Reciprocità", "Relazione simmetrica tra due elementi o gruppi");
        wordList.put("Superficiale", "Che riguarda solo la superficie o l'apparenza esterna, senza profondità");

    }

    public Map<String, String> getWordList() {
        return wordList;
    }

    private void addWord(String word, String hint) {
        if (!wordList.containsKey(word)) {
            wordList.get(word);
        }
    }
    public String getHint(String key){
        return getWordList().get(key);
    }

    public String randomWord(){
      int count = 0;
      int randomKey = new Random().nextInt(wordList.size());
      String key_selected=null;
        for (String key :getWordList().keySet()) {
            if (count == randomKey){
                key_selected = key;
                break;
            }
            count++;
        }
        return key_selected;
    }



}