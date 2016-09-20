// Stephanie Gan & John Lester
// CST 338-30_FA16
// Deck of Cards (M3)

public class Assign3Main {

   public static void main(String[] args) {
      //testing some cards

      Card sample1 = new Card();
      Card sample2 = new Card('2', Card.Suit.SPADES);
      Card sample3 = new Card('7', Card.Suit.CLUBS);
      Card sample4 = new Card('Q', Card.Suit.DIAMONDS);

      //testing toString()
      System.out.println(sample1.toString());
      System.out.println(sample2.toString());
      System.out.println(sample3.toString());
      System.out.println(sample4.toString());

      //testing set()
      sample1.set('5', Card.Suit.HEARTS);
      System.out.println("New sample1: " + sample1.toString());
   }
}

class Card {
   //create values for value and suit
   public enum Suit {
      SPADES, DIAMONDS, HEARTS, CLUBS
   }
   private char value;
   private Suit suit;
   private boolean errorFlag;

   //default constructor
   public Card() {
      this('A', Suit.SPADES);
   }

   //constructor to set the card's value and suit
   public Card(char value, Suit suit) {
      set(value, suit);
   }

   public boolean set(char value, Suit suit) {
      //calling method to check validity
      boolean validityCheck = isValid(value, suit);

      //if the values are valid, set the value and suit
      if (validityCheck == true) {
         this.value = Character.toUpperCase(value);
         this.suit = suit;

         //set error flag to false
         this.errorFlag = false;
      }
      else {
         //if the validity check comes back false, set errorflag to true
         this.errorFlag = true;
      }

      return validityCheck;
   }

   private boolean isValid(char value, Suit suit) {
      //making a character array to check validity
      char[] vals = {'1', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A'};
		
      //initializing boolean
      boolean check = false;

      //checking the value against the character array vals
      for (char c : vals) {
         if (Character.toUpperCase(value) == c) {
            check = true;
         }
      }

      return check;		
   }

   public String toString() {
      //initialize string
      String display = "";
      //if the errorflag is true, set display as "invalid"
      if (this.getErrorFlag() == true) {
         display = "Invalid card.";
      }
      else {
         display = this.value + " of " + this.suit;
      }
      return display;
   }

   //creating the accessors
   public char getValue() {
      return value;
   }

   public Suit getSuit() {
      return suit;
   }

   public boolean getErrorFlag() {
      return errorFlag;
   }

   //checking the current instance of value and suit against the values of the passed in card
   public boolean equals(Card card) {
      boolean checkCard = false;
      if (this.getSuit() == card.getSuit()) {
         if (this.getValue() == card.getValue()) {
            checkCard = true;
         }
      }
      return checkCard;
   }
}

class Hand {
   public int MAX_CARDS = 52;
   private Card[] myCards;
   private int numCards;

   // default constructor
   public Hand(int k) {
      for (int x = 0; x <= k; x++) {
         // add card each iteration
      }
   }

   // remove all cards from hand
   public void resetHand() {
      //Hand(0);
   }

   // add card to next avail position
   public boolean set() {
      //this();
      return false;
   }

   // return and remove top card
   public Card playCard() {
      //this();
      return new Card();
   }

   // stringizer to return entire string
   public String toString() {
      //this();
      return "testing";
   }

   // accessor for numCards

   // accessor for individual card
   Card inspectCard(int k) {
      //this();
      return new Card();
   }
}
