/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laborai.Eligijus;

import static java.lang.Double.compare;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.StringTokenizer;
import laborai.studijosktu.KTUable;
import laborai.studijosktu.Ks;
import java.util.Random;
/**
 *
 * @author msi-Laptop
 */
public class Game implements KTUable<Game> {

    // data for all classes
    final static private int yearFrom = 2004;
    final static private int thisYear = LocalDate.now().getYear();
    final static private double minPrice = 0.00;
    final static private double maxPrice = 4303.00;
    private static final String idCode = "SteanId";   //  ***** nauja
    private static int serNr = 100;               //  ***** nauja
    private final String uniqueSteamId;
    // data just for Game class
    private String company;
    private String name;
    private String genre;
    private double raiting;
    private double price;
    private int madeYear;

    public Game() {
     uniqueSteamId = idCode + (serNr++);
    }

    public Game(String company, String name, String gener, double raiting, double price, int madeYear) {
        uniqueSteamId = idCode + (serNr++);    // suteikiamas originalus autoRegNr
        this.company = company;
        this.name = name;
        this.genre = gener;
        this.raiting = raiting;
        this.price = price;
        this.madeYear = madeYear;
    }
    
    public Game(Builder builder) {
        uniqueSteamId = idCode + (serNr++);    // suteikiamas originalus autoRegNr
        this.company = builder.company;
        this.name = builder.name;
        this.genre = builder.genre;
        this.raiting = builder.raiting;
        this.price = builder.price;
        this.madeYear = builder.madeYear;
        validate();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.company);
        hash = 59 * hash + Objects.hashCode(this.name);
        hash = 59 * hash + Objects.hashCode(this.genre);
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.raiting) ^ (Double.doubleToLongBits(this.raiting) >>> 32));
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.price) ^ (Double.doubleToLongBits(this.price) >>> 32));
        hash = 59 * hash + this.madeYear;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Game other = (Game) obj;
        if (Double.doubleToLongBits(this.raiting) != Double.doubleToLongBits(other.raiting)) {
            return false;
        }
        if (Double.doubleToLongBits(this.price) != Double.doubleToLongBits(other.price)) {
            return false;
        }
        if (this.madeYear != other.madeYear) {
            return false;
        }
        if (!Objects.equals(this.company, other.company)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.genre, other.genre)) {
            return false;
        }
        return true;
    }
    
    public double yearPrice(int year)
    {
        if(this.madeYear >= year)
        {
            return this.price;
        }
        return 0;
    }
    
    public Game(String dataString) {
        uniqueSteamId = idCode + (serNr++);
        this.parse(dataString);
    }
    @Override
    public final void parse(String data) {
        
        try {   // ed - tai elementarūs duomenys, atskirti tarpais
            
            StringTokenizer scan = new StringTokenizer(data, ";");
            company = scan.nextToken();
            name = scan.nextToken();
            genre = scan.nextToken();
            raiting = Double.parseDouble(scan.nextToken());
            price = Double.parseDouble(scan.nextToken());
            madeYear = Integer.parseInt(scan.nextToken());

        } catch (InputMismatchException e) {
            Ks.ern("Blogas duomenų formatas apie zaidima -> " + data);
        } catch (NoSuchElementException e) {
            Ks.ern("Trūksta duomenų apie zaidima -> " + data);
        }
    }

    public String getCompany() {
        return this.company;
    }
    
    public String getName(){
        return this.name;
    }
    
    public String getGenre(){
        return this.genre;
    }
    
    public double getRaiting() {
        return raiting;
    }

    public double getPrice() {
        return this.price;
    }
    
    public int getMadeYear() {
        return madeYear;
    }
    public String getSteamId() {  //** nauja.
        return uniqueSteamId;
    }
    public void setGenre(String genre)
    {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return String.format(" %-20s | %-35s | %-25s | %8.2f | %8.2f | %3d |",
                company, name, genre, raiting, price, madeYear);
    }

    @Override
    public Game create(String dataString) {
        Game a = new Game();
        a.parse(dataString);
        return a;
    }

    @Override
    public int compareTo(Game a) {
        return getSteamId().compareTo(a.getSteamId());
    }
    
    @Override
    public String validate() {
        String errorType = "";
        if (madeYear < yearFrom || madeYear > thisYear) {
            errorType = "Netinkami gamybos metai, turi būti ["
                    + yearFrom + ":" + thisYear + "]";
        }
        if (price < minPrice || price > maxPrice) {
            errorType += " Kaina už leistinų ribų [" + minPrice
                    + ":" + maxPrice + "]";
        }
        return errorType;
    }

    public final static Comparator<Game> byRaiting = (game1, game2) -> {
        return compare(game1.getRaiting(), game2.getRaiting());
    };
    
    public final static Comparator<Game> byGenre = (game1, game2) -> game1.genre.compareTo(game2.genre);

    public final static Comparator<Game> byPrice = (game1, game2) -> {
        return compare(game1.getPrice(), game2.getPrice());
    };
    public final static Comparator<Game> byYear = (game1, game2) -> {
        return compare(game1.getMadeYear(), game2.getMadeYear());
    };
    
     public static class Builder {

        private final static Random RANDOM = new Random(1949);  // Atsitiktinių generatorius
 
        String[] companies = {"Bethesda", "Electronic arts", "2K", "Ubisoft", "Activision Blizzard", "Rokstar Games"};
        String[][] names = {
            {"Doom Eternal", "Fallout 76", "Quake champions", "Rage 2", "Wolfenstein II the new colossus", "Doom", "Fallout 4", "Dishonored 2", "The Evil WithIn", "The elder scrolls: Skyrim"},
            {"BattleField V", "Fifa 19", "The Sims 4", "Star Wars Battlefront II", "Need More Speeed: PayBack", "TitanFall", "Need For Speed: Most Wanted"},
            {"Civilization VI Rise And Fall", "NBA 2K19", "WWE 2K18", "Mafia III", "Battleborn", "XCom", "BorderLands"},
            {"Anno 1800", "FarCry 5", "The Crew 2", "Assassin's Creed Odyssey", "For Honor", "Starlink Battle For Atlas", "Watch Dog 2", "Just Dance 2018"},
            {"Quake 4", "Call Of Duty WW2", "World Of Warcraft Battle For Azeroth", "StarCraft", "Diablo III", "Overwatch", "HearthStone"},
            {"Red Dead Redemption 2", "Grand Theft Auto V", "Max Payne 3", "Grand Theft Auto IV", "Grand Theft Auto San Andreas", "Grand Theft Auto Vice City"}
        };
        String[][] genres = {
            {"FPS", "Action role-playing", "FPS", "FPS", "FPS", "FPS", "Action role-playing", "Action-adventure", "Survival horror", "Action role-playing"},
            {"FPS", "Sport", "Simulation", "FPS", "Racing", "FPS", "Racing"},
            {"Turn-based strategy", "Sport", "Sport", "Action-adventure", "FPS", "Tactical role-playing", "FPS"},
            {"City-building", "	Action-adventure", "Racing", "Action role-playing", "Action", "Action-adventure", "Action-adventure", "Music"},
            {"FPS", "FPS", "World Of Warcraft Battle For Azeroth", "StarCraft", "Diablo III", "Overwatch", "HearthStone"},
            {"Action-adventure", "Action-adventure", "FPS", "Action-adventure", "Action-adventure", "Action-adventure"}

        };

        private String company;
        private String name;
        private String genre;
        private double raiting;
        private double price;
        private int madeYear;

        public Game build() {
            return new Game(this);
        }

        public Game buildRandom() {
            int com = RANDOM.nextInt(companies.length);     
            int gam = RANDOM.nextInt(names[com].length);
            return new Game(companies[com], names[com][gam], genres[com][gam], 3.50 + RANDOM.nextInt(6), RANDOM.nextInt(60), 2003 + RANDOM.nextInt(15));
        }


        public Builder company(String company) {
        this.company = company;
        return this;
        }
    
        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder genre(String genre){
            this.genre = genre;
            return this;
        }

        public Builder raiting(double raiting) {
            this.raiting = raiting;
            return this;
        }

        public Builder price(double price) {
            this.price = price;
            return this;
        }

        public Builder madeYear(int madeYear) {
            this.madeYear = madeYear;
            return this;
        }

    }

}
