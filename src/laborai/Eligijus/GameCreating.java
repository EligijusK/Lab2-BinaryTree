/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laborai.Eligijus;
import laborai.gui.MyException;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;
/**
 *
 * @author msi-Laptop
 */
public class GameCreating {
    
    private static Game[] games;
    private static int firstIndex = 0, lastIndex = 0;
    private static boolean checkStart = true;

    public static Game[] generate(int index) {
        games = new Game[index];
        for (int i = 0; i < index; i++) {
            games[i] = new Game.Builder().buildRandom();
        }
        return games;
    }

    public static Game[] generateAndMix(int setSize, double randomRate)
       throws MyException {
        return generateAndMix(setSize, setSize, randomRate);
    }
    
    
    public static Game[] generateAndMix(int setSize, int setIntake, double randomRate)
       throws MyException {
        games = generate(setSize);
        return generateAndMix(setSize, setIntake, randomRate);
    }

    // Galima paduoti masyvą išmaišymui iš išorės
    public static Game[] ismaisyti(Game[] gamesData,
            int index, double isbarstKoef) throws MyException {
        if (gamesData == null) {
            throw new IllegalArgumentException("Game baze yra null");
        }
        if (index <= 0) {
            throw new MyException(String.valueOf(index), 1);
        }
        if (gamesData.length < index) {
            throw new MyException(gamesData.length + " >= " + index, 2);
        }
        if ((isbarstKoef < 0) || (isbarstKoef > 1)) {
            throw new MyException(String.valueOf(isbarstKoef), 3);
        }

        int leftCount = gamesData.length - index;
        int firstIndex = (int) (leftCount * isbarstKoef / 2);

        Game[] mainGameSet = Arrays.copyOfRange(gamesData, firstIndex, firstIndex + index);
        Game[] leftGameSet = Stream
                .concat(Arrays.stream(Arrays.copyOfRange(gamesData, 0, firstIndex)),
                        Arrays.stream(Arrays.copyOfRange(gamesData, firstIndex + index, gamesData.length)))
                .toArray(Game[]::new);

        Collections.shuffle(Arrays.asList(mainGameSet)
                .subList(0, (int) (mainGameSet.length * isbarstKoef)));
        Collections.shuffle(Arrays.asList(leftGameSet)
                .subList(0, (int) (leftGameSet.length * isbarstKoef)));

        GameCreating.firstIndex = 0;
        lastIndex = leftGameSet.length - 1;
        GameCreating.games = leftGameSet;
        return mainGameSet;
    }

    public static Game getFromSet() throws MyException {
        if ((lastIndex - firstIndex) < 0) {
            throw new MyException(String.valueOf(lastIndex - firstIndex), 4);
        }
        // Vieną kartą Automobilis imamas iš masyvo pradžios, kitą kartą - iš galo.     
        checkStart = !checkStart;
        return games[checkStart ? firstIndex++ : lastIndex--];
    }
}
