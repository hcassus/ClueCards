import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Player {
    private List<String> hand;
    private ClueCardGame game;
    private Map<String, List<String>> classifiedHand = new HashMap<String, List<String>>();

    public Player(ClueCardGame game){
        this.game = game;
        this.game.addPlayer();
    }

    public void getMyCards(){
        hand = game.getMyCards();
        classifyCards();
    }

    private void classifyCards() {
        classifiedHand.put("Suspects", new ArrayList<String>());
        classifiedHand.put("Weapons", new ArrayList<String>());
        classifiedHand.put("Places", new ArrayList<String>());

        for (String card :
                hand) {
            if (game.SUSPECTS.contains(card)) {
                classifiedHand.get("Suspects").add(card);
            } else if (game.WEAPONS.contains(card)) {
                classifiedHand.get("Weapons").add(card);
            } else if (game.PLACES.contains(card)) {
                classifiedHand.get("Places").add(card);
            }

        }
    }
}
