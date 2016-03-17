import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.util.reflection.Whitebox;

import java.util.List;

/**
 * Created by hcassus on 16/03/16.
 */
public class ClueCardGameTest {

    ClueCardGame game;
    Player player1;

    @Before
    public void setUp(){
        game = new ClueCardGame();
        player1 = new Player(game);
    }

    @Test
    public void getAllSuspectsTest(){
        List<String> allSuspects = game.getAllSuspects();
        Assert.assertEquals(allSuspects.size(), game.SUSPECTS.size());
    }

    @Test
    public void getAllPlacesTest(){
        List<String> allPlaces = game.getAllPlaces();
        Assert.assertEquals(allPlaces.size(), game.PLACES.size());
    }

    @Test
    public void getAllWeaponsTest(){
        List<String> allWeapons = game.getAllWeapons();
        Assert.assertEquals(allWeapons.size(), game.WEAPONS.size());
    }

    @Test
    public void setCrimeTest(){
        Assert.assertTrue(game.getAllSuspects().contains(Whitebox.getInternalState(game,"murderer")) );
        Assert.assertTrue(game.getAllWeapons().contains(Whitebox.getInternalState(game,"crimeWeapon")) );
        Assert.assertTrue(game.getAllPlaces().contains(Whitebox.getInternalState(game,"crimeScene")) );
    }

    @Test
    public void getMyCardTest(){
        List<String> my_cards = game.getMyCards();
        Assert.assertFalse(my_cards.contains(Whitebox.getInternalState(game,"murderer")));
        Assert.assertFalse(my_cards.contains(Whitebox.getInternalState(game,"crimeWeapon")));
        Assert.assertFalse(my_cards.contains(Whitebox.getInternalState(game,"crimeScene")));
        Assert.assertEquals((game.PLACES.size() + game.SUSPECTS.size() + game.WEAPONS.size() -3), my_cards.size());
    }

    @Test
    public void singlePlayerMyCardTest(){
        List<String> remainingCards = (List<String>)Whitebox.getInternalState(game, "remainingCards");
        int gameRemainingCardCount = remainingCards.size();
        player1.getMyCards();
        List<String> playerHand = (List<String>)Whitebox.getInternalState(player1, "hand");
        Assert.assertEquals(gameRemainingCardCount, playerHand.size());
    }

    @Test
    public void multiPlayerMyCardTest(){
        Player player2 = new Player(game);

        List<String> remainingCards = (List<String>)Whitebox.getInternalState(game, "remainingCards");
        int gameRemainingCardCount = remainingCards.size();

        player1.getMyCards();
        player2.getMyCards();

        List<String> player1Hand = (List<String>)Whitebox.getInternalState(player1, "hand");
        List<String> player2Hand = (List<String>)Whitebox.getInternalState(player2, "hand");
        Assert.assertEquals(gameRemainingCardCount, (player1Hand.size()+player2Hand.size()));
        Assert.assertEquals(gameRemainingCardCount / 2, player1Hand.size());
        Assert.assertEquals(gameRemainingCardCount / 2, player2Hand.size());
    }

}
