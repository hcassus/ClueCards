import java.util.ArrayList;
import java.util.List;

/**
 * Created by hcassus on 17/03/16.
 */
public class Player {

    private List<String> hand = new ArrayList<String>();
    private ClueCardGame game;

    public Player(ClueCardGame game){
        this.game = game;
        this.game.addPlayer();
    }

    public void getMyCards(){
        hand = game.getMyCards();
    }



}
