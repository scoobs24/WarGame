import java.util.*;
import java.awt.*;
/**
 * Authors: Kyle Meredith & Brady Hamilton 
 *  
 * 
 */
public class Player
{
    Hand hand;
    Card card;
    CardTable ct;    
    CardTable table = new CardTable(this);
    Deck deck = new Deck("Deck1");
    Hand hand1 = new Hand("Player1");
    Hand hand2 = new Hand("Player2");
    int i=0;
    int p1 = 0;
    int p2 = 0;
    /**
     * Constructor for objects of class Player
     */
    public Player()
    {
        playerDeck();
    }
    /**
     * Creates a new shuffled deck for p1 and p2. 
     */
    public void playerDeck() {
        deck.shuffle();
        for (int i=0; i<1; i++) {
            hand1.addCard(deck.popCard());
            hand2.addCard(deck.popCard());
        }
        table.replaceHand(0,hand1);
        table.replaceHand(1,hand2);
        rules();
    }
    /**
     * Rules of the game.
     */
    void rules(){
        if(i<=26){
            int h1=hand1.showCard(i).getRank();
            int h2=hand2.showCard(i).getRank();
            if(h1>h2){
                System.out.println("PLAYER ONE TAKES THE ROUND"); 
                p1++;
            }else if(h2>h1){
                System.out.println("PLAYER TWO TAKES THE ROUND");
                p2++;   
            } else if(h1==h2){
                System.out.println("ROUND SKIP");
            }
        }
        winner();
    }
    /**
     * When all of the cards are drawn, displays the final score and game winner.
     */
    public void winner(){
        if (p1 == 13){
            System.out.println("PLAYER ONE FINAL SCORE: " + p1);
            System.out.println("PLAYER TWO FINAL SCORE: " + p2);
            System.out.println("PLAYER ONE WINS!");
        }
        if (p2 == 13){
            System.out.println("PLAYER TWO FINAL SCORE: " + p2);
            System.out.println("PLAYER ONE FINAL SCORE: " + p1);
            System.out.println("PLAYER TWO WINS!");
        }
    }
    /**
     * Deals two new cards.
     */
    public void nextCard(){
        hand1.popCard(0);
        hand2.popCard(0);
        hand1.addCard(deck.popCard());
        hand2.addCard(deck.popCard());
        table.repaint();
        rules();
    }
}

