import java.util.*;

/**
 * Created by hcassus on 16/03/16.
 */
public class ClueCardGame {

    public final List<String> WEAPONS = new ArrayList<String>(){{
        add("Rope");
        add("Knife");
        add("Candlestick");
        add("Revolver");
    }};

    public final List<String> PLACES = new ArrayList<String>(){{
        add("Hall");
        add("Dining Room");
        add("Spa");
        add("Kitchen");
        add("Living Room");
    }};

    public final List<String> SUSPECTS = new ArrayList<String>(){{
        add("Green");
        add("White");
        add("Mustard");
        add("Peacock");
        add("Plum");
        add("Scarlet");
    }};

    private List <String> remainingWeapons = new ArrayList<String>();
    private List <String> remainingPlaces = new ArrayList<String>();
    private List <String> remainingSuspects = new ArrayList<String>();

    private List <String> remainingCards = new ArrayList<String>();

    private String murderer;
    private String crimeWeapon;
    private String  crimeScene;
    private Random random = new Random();

    private int numberOfPlayers = 0;

    public ClueCardGame(){
        setCrime();
        combineAllRemainingCards();
    }

    private void setCrime(){
        murderer = getRandomMurderer();
        crimeWeapon = getRandomCrimeWeapon();
        crimeScene = getRandomCrimeScene();
    }

    public void addPlayer(){
        numberOfPlayers++;
    }

    private String getRandomMurderer(){
        List<String> suspects = SUSPECTS;
        int listSize = suspects.size();
        int random_index = random.nextInt(listSize);
        remainingSuspects.addAll(suspects);
        String randomSuspect = suspects.get(random_index);
        remainingSuspects.remove(random_index);
        return randomSuspect;
    }

    private String getRandomCrimeScene(){
        List<String> places = PLACES;
        int listSize = places.size();
        int random_index = random.nextInt(listSize);
        remainingPlaces.addAll(PLACES);
        String randomPlace = places.get(random_index);
        remainingPlaces.remove(random_index);
        return randomPlace;
    }

    private String getRandomCrimeWeapon(){
        List<String> weapons = WEAPONS;
        int listSize = weapons.size();
        int random_index = random.nextInt(listSize);
        remainingWeapons.addAll(WEAPONS);
        String randomWeapon = weapons.get(random_index);
        remainingWeapons.remove(random_index);
        return randomWeapon;
    }

    public List<String> getAllSuspects(){
        return SUSPECTS;
    }

    public List<String> getAllWeapons(){
        return WEAPONS;
    }

    public List<String> getAllPlaces(){
        return PLACES;
    }

    private void combineAllRemainingCards(){
        remainingCards.addAll(remainingSuspects);
        remainingCards.addAll(remainingPlaces);
        remainingCards.addAll(remainingWeapons);
    }

    public List<String> getMyCards(){
        List<String> myCards = new ArrayList<String>();
        int numberOfCards = remainingCards.size()/numberOfPlayers;
        int dealtCards = 0;
        while (dealtCards < numberOfCards && remainingCards.size() > 0) {
            myCards.add(getRandomCard());
            dealtCards++;
        }
        numberOfPlayers--;
        return myCards;
    }

    private String getRandomCard(){
        List<String> cards = remainingCards;
        int listSize = cards.size();
        int random_index = random.nextInt(listSize);
        String randomCard = cards.get(random_index);
        cards.remove(random_index);
        return randomCard;
    }
}
