import java.util.Arrays;
import java.awt.FlowLayout;
import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.event.*;

public class CardTable extends Canvas {
    Player player;
    Button nextButton;
    private Image[][] images;
    private int cardWidth, cardHeight;
    final String CARDSET ="cardset-oxymoron";
    Hand [] hands;
    // clubs, diamonds, hearts, spades
    // this one-character encoding is required for the card image filename
    char [] suits = {'c','d','h','s'}; 

    public CardTable(Player p) {
        player = p;
        // make the frame
        JFrame frame = new JFrame("Card Table");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // add the CardTable
        String cardset = "cardset-oxymoron";
        frame.getContentPane().add(this);
        setBackground(new Color(0x03FCC6)); // green
        loadImages();
        cardWidth = images[2][2].getWidth(null);
        cardHeight = images[2][2].getHeight(null);
        Deal listener = new Deal(this);

        Panel buttons = new Panel();
        buttons.setLayout(new FlowLayout());
        nextButton = new Button ("DEAL CARDS");
        nextButton.addActionListener(listener);
        buttons.add(nextButton);
        frame.add(buttons);
        setTableSize(14,3);
        hands = new Hand [4];
        frame.pack();
        frame.setVisible(true); 
    }

    public void handleButtonPress(Object src) {    //method that creates button to show nextCard
        if (src==nextButton) {
            player.nextCard();
            repaint();
        }
    }

    public void replaceHand (int index, Hand hand) {
        if (index >= 0 && index <= hands.length) {
            hands[index] = hand;
        }
    }

    private void loadImages () {
        images = new Image[14][4];
        for (int suit = 0; suit <= 3; suit++) {
            char c = suits[suit];
            for (int rank = 1; rank <= 13; rank++) {
                String s = String.format("%s/%02d%c.gif",CARDSET, rank, c);
                images[rank][suit] = new ImageIcon(s).getImage();
            }
        }
    }

    public void setTableSize(int x, int y) {
        setSize((int) (x * cardWidth),
            (int) (y * cardHeight));
    }

    public void drawCardAt(Graphics g, Card card, double row, double col) {
        int rank = card.getRank();
        int suit = card.getSuit();
        Image image = images[rank][suit];
        g.drawImage(image,
            (int) (col * cardWidth),
            (int) (row * cardHeight),
            null);
    }

    public void paint(Graphics g) {
        double row=0;
        double col=0;
        for (int h = 0; h < hands.length; h++) {
            col = 0;
            if (hands[h] != null) {
                Hand hand = hands[h];
                for (int c = 0; c < hand.size(); c++) {
                    Card card = hand.showCard(c);
                    drawCardAt(g, card, row, col);
                    col++;
                }
            }
            row++;
        }
    }

    public static void demoFullDeck() {
        //CardTable table = new CardTable();
        for (int suit = 0; suit < 4; suit++) {
            Hand hand = new Hand("Suit"+suit);
            for (int rank = 1; rank <= 13; rank++) {
                Card card = new Card (rank, suit);
                hand.addCard(card);
            }
            //table.replaceHand(suit,hand);
        }
    }

    public static void demoTwoHands() {
        //CardTable table = new CardTable();
        Deck deck = new Deck("Deck1");
        deck.shuffle();
        Hand hand1 = new Hand("Player1");
        Hand hand2 = new Hand("Player2");
        for (int i=0; i<5; i++) {
            hand1.addCard(deck.popCard());
            hand2.addCard(deck.popCard());
        }
        //table.replaceHand(0,hand1);
        //table.replaceHand(1,hand2);
    }
}
