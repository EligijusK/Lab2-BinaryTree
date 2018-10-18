/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laborai.Eligijus;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import laborai.studijosktu.AvlSetKTUx;
import laborai.studijosktu.BstSetKTUx;
import laborai.studijosktu.BstSetKTU;
import laborai.studijosktu.Ks;
import laborai.studijosktu.SetADT;
import laborai.studijosktu.SortedSetADTx;
import java.util.Locale;
import java.util.TreeSet;
/**
 *
 * @author msi-Laptop
 */
public class GameTest {
    
    static Game[] gameSet;
    static SortedSetADTx<Game> aSeries = new BstSetKTUx(new Game(), Game.byPrice);
    
    static SortedSetADTx generateSet(int index, int generN) {
        gameSet = new Game[generN];
        for (int i = 0; i < generN; i++) {
            gameSet[i] = new Game.Builder().buildRandom();
        }
        Collections.shuffle(Arrays.asList(gameSet));
        aSeries.clear();
        for (int i = 0; i < index; i++) {
            aSeries.add(gameSet[i]);
        }
        return aSeries;
    }
    
    public static void aibėsTestas() throws CloneNotSupportedException {
        
        // idejimas
        Game a1 = new Game("Electronic arts", "BattleField V","FPS", 3.500000, 28.000000, 2017);
        
        Game a2 = new Game.Builder()
                .company("Electronic arts")
                .name("Need More Speeed: PayBack")
                .genre("Racing")
                .raiting(6.500000)
                .price(32.000000)
                .madeYear(2006)
                .build();
        
        Game a10 = new Game.Builder()
                .company("Electronic arts")
                .name("Need More Speeed: PayBack")
                .genre("Racing")
                .raiting(6.500000)
                .price(31.000000)
                .madeYear(2006)
                .build();
        
        Game a3 = new Game("Electronic arts;TitanFall;FPS;5.500000;19.000000;2010");
        Game a4 = new Game("Activision Blizzard;Call Of Duty WW2;FPS;3.500000;11.000000;2004");
        Game a5 = new Game("Ubisoft;Anno 1800;City-building;5.500000;39.000000;2010");
        Game a6 = new Game("Ubisoft;Starlink Battle For Atlas;Action-adventure;3.500000;41.000000;2003");
        Game a7 = new Game("2K;Battleborn;FPS;8.500000;29.000000;2003");
        Game a8 = new Game("2K;XCom;Tactical role-playing;7.500000;33.000000;2009");
        Game a9 = new Game("Rokstar Games;Grand Theft Auto V;Action-adventure;6.500000;37.000000;2006");
                
        Game[] gameArray = {a9, a7, a8, a5, a1, a6};

        // kazkas prasideda
        

        Ks.oun("Zaidimu Aibe:");
        SortedSetADTx<Game> gameSet = new BstSetKTUx(new Game());
        BstSetKTU<Game> newSet = new BstSetKTU(Game.byPrice);
        for (Game a : gameArray) { // pridejimas i aibe
            //gameSet.add(a);
            //Ks.oun("Aibė papildoma: " + a + ". Jos dydis: " + gameSet.size());
            newSet.add(a);
        }
        BstSetKTU<Game> newSetClone = (BstSetKTU<Game>) newSet.clone();
        newSetClone.add(new Game("GG", "op", "Fst", 5.12, 1, 2015));
        System.out.print(newSet.toVisualizedString(""));
        Ks.oun(newSet.higher(a7));
        System.out.println();
       // System.out.print(newSet.toVisualizedString(""));
        //Ks.oun(newSet.toVisualizedString(""));
        //Ks.oun(aSeries.toString());
        //Ks.oun("Pasikartojančios automobilių markės:\n" + aSeries.toVisualizedString(""));
          
          
//
//        Ks.oun("");
//
//        Ks.oun(gameSet.toVisualizedString(""));
//
//        SortedSetADTx<Game> gameSetCopy = (SortedSetADTx<Game>) gameSet.clone(); // nukopijuota ir surikiuota zaidimo aibe
//
//        gameSetCopy.add(a2);
//        gameSetCopy.add(a3);
//        gameSetCopy.add(a4);
//        Ks.oun("Papildyta zaidimuAibes kopija:");
//        Ks.oun(gameSetCopy.toVisualizedString(""));
//
//        a9.setGenre("FPS");
//
//        Ks.oun("Originalas:");
//        Ks.ounn(gameSet.toVisualizedString(""));
//
//        Ks.oun("Ar elementai egzistuoja aibėje?");
//        for (Game a : gameArray) {
//            Ks.oun(a + ": " + gameSet.contains(a));
//        }
//        Ks.oun(a2 + ": " + gameSet.contains(a2));
//        Ks.oun(a3 + ": " + gameSet.contains(a3));
//        Ks.oun(a4 + ": " + gameSet.contains(a4));
//        Ks.oun("");
//
//        Ks.oun("Ar elementai egzistuoja aibės kopijoje?");
//        for (Game a : gameArray) {
//            Ks.oun(a + ": " + gameSetCopy.contains(a));
//        }
//        Ks.oun(a2 + ": " + gameSetCopy.contains(a2));
//        Ks.oun(a3 + ": " + gameSetCopy.contains(a3));
//        Ks.oun(a4 + ": " + gameSetCopy.contains(a4));
//        Ks.oun("");
//
//        Ks.oun("Elementų šalinimas iš kopijos. Aibės dydis prieš šalinimą:  " + gameSetCopy.size());
//        for (Game a : new Game[]{a2, a1, a9, a8, a5, a3, a4, a2, a7, a6, a7, a9}) {
//            gameSetCopy.remove(a);
//            Ks.oun("Iš autoaibės kopijos pašalinama: " + a + ". Jos dydis: " + gameSetCopy.size());
//        }
//        Ks.oun("");
//
//        Ks.oun("Automobilių aibė su iteratoriumi:");
//        Ks.oun("");
//        for (Game a : gameSet) {
//            Ks.oun(a);
//        }
//        Ks.oun("");
//        Ks.oun("Automobilių aibė AVL-medyje:");
//        SortedSetADTx<Game> autoAibe3 = new AvlSetKTUx(new Game());
//        for (Game a : gameArray) {
//            autoAibe3.add(a);
//        }
//        Ks.ounn(autoAibe3.toVisualizedString(""));
//
//        Ks.oun("Automobilių aibė su iteratoriumi:");
//        Ks.oun("");
//        for (Game a : autoAibe3) {
//            Ks.oun(a);
//        }
//
//        Ks.oun("");
//        Ks.oun("Automobilių aibė su atvirkštiniu iteratoriumi:");
//        Ks.oun("");
//        Iterator iter = autoAibe3.descendingIterator();
//        while (iter.hasNext()) {
//            Ks.oun(iter.next());
//        }
//
//        Ks.oun("");
//        Ks.oun("Automobilių aibės toString() metodas:");
//        Ks.ounn(autoAibe3);
//
//        // Išvalome ir suformuojame aibes skaitydami iš failo
//        gameSet.clear();
//        autoAibe3.clear();
//
//
//        Ks.oun("");
//        Ks.oun("Automobilių aibė DP-medyje:");
//        gameSet.load("Duomenys\\t1.txt");
//        Ks.ounn(gameSet.toVisualizedString(""));
//        Ks.oun("Išsiaiškinkite, kodėl medis augo tik į vieną pusę.");
//
//
//
//        Ks.oun("");
//        Ks.oun("Automobilių aibė AVL-medyje:");
//        autoAibe3.load("Duomenys\\t2.txt");
//        Ks.ounn(autoAibe3.toVisualizedString(""));
//
//
//
//        SetADT<String> autoAibe4 = GamesRecord.gamesGnere(gameArray); // kartojasi
//        Ks.oun("Pasikartojančios automobilių markės:\n" + autoAibe4.toString());
    }
    
    public static void main(String[] args) throws CloneNotSupportedException {
        Locale.setDefault(Locale.US); // Suvienodiname skaičių formatus
        generateSet(6, 9);
        aibėsTestas();
    }
}
