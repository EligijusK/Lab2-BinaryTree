/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laborai.Eligijus;

import laborai.studijosktu.BstSetKTU;
import laborai.studijosktu.SetADT;
import laborai.studijosktu.SortedSetADT;

/**
 *
 * @author msi-Laptop
 */
public class GamesRecord {
    
    public static SetADT<String> gamesGnere(Game[] auto) {
        SetADT<Game> uni = new BstSetKTU<>(Game.byGenre);
        SetADT<String> kart = new BstSetKTU<>();
        for (Game a : auto) {
            int sizeBefore = uni.size();
            uni.add(a);
            
            if (sizeBefore == uni.size()) {
                kart.add(a.getGenre());
            }
        }
        return kart;
    }
    public static SortedSetADT<String> gamesYear(Game[] auto) {
        SortedSetADT<Game> uni = new BstSetKTU<>(Game.byYear);
        SortedSetADT<String> kart = new BstSetKTU<>();
        for (Game a : auto) {
            int sizeBefore = uni.size();
            uni.add(a);
            
            if (sizeBefore == uni.size()) {
                kart.add(a.getGenre());
            }
        }
        return kart;
    }
}
