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
    //defining a max number of cards here to avoid a huge array by default
    public int MAX_CARDS = 50;
   
    //making the array to hold the player's current hand
    private Card[] myCards;
    private int numCards;
   
    //default constructor
    public Hand() {
        this.myCards = new Card[MAX_CARDS];
        numCards = 0;
    }
   
    public void resetHand() {
        //setting numCards back to zer0
        numCards = 0;
    }
   
    public boolean takeCard(Card card) {
        boolean testing = false;
       
        //adds a card to the myCards array
        if (numCards < MAX_CARDS) {
            myCards[numCards].set(card.getValue(), card.getSuit());
            //incrementing the counter of cards in the hand
            numCards++;
            testing = true;
        }
       
        else {
            testing = false;
        }
        return testing;
    }
   
    public Card playCard() {
      //returns and removes the card in the top occupied position of the array.
      
        Card cardPlayed = new Card(myCards[numCards - 1].getValue(), myCards[numCards - 1].getSuit());
        //decrement counter
        numCards--;
       
        //removing the card by setting it to null
        myCards[numCards - 1] = null;
        
        return cardPlayed;
    }
    
    //a stringizer that the client can use prior to displaying the entire hand
    public String toString() {
      String displayString = "";
      
      if (numCards > 0) {
         for (Card card : myCards) {
            displayString += card.toString() + ", ";
         }
      }
      
      //remove the last two characters in displayString
      displayString.substring(0, displayString.length() - 2);
      
      return displayString;
    }
    
    //accessor for numCards
    public int getNumCards() {
      return numCards;
    }
    
    //Card inspectCard(int k) - Accessor for an individual card.  Returns a card with errorFlag = true if k is bad
    public Card inspectCard(int k) {
      //make a boolean errorFlag
      boolean errorFlag;
      
      //if the card in position k in hand exists, return the card
      if (myCards[k] != null) {
         errorFlag = false;
         return myCards[k];
      }
      
      //if the card in position k is null, set errorFlag to true
      else {
         errorFlag = true;
         return myCards[k];
      }
    }
}

//An object of type Deck represents a deck of playing cards
class Deck {
   public int MAX_CARDS = 6*52;
   private Card[] masterPack; // An array of cards.
   private int cardsUsed; // Keeps track of the number of cards dealt

   // default constructor
   public Deck() {
      masterPack = new Card[MAX_CARDS];
      int cardCount = 0; // How many cards have been created so far.
      for (int suit = 0; suit <= 3; suit++ ) {
         for ( int value = 1; value <= 13; value++ ) {
            int newValue;
            if (value==1) {
               newValue = 65; // convert 1 to A
            }
            else if (value==10) {
               newValue = 84; // convert 10 to T
            }
            else if (value==11) {
               newValue = 74; // convert 11 to J
            }
            else if (value==12) {
               newValue = 81; // convert 12 to Q
            }
            else if (value==13) {
               newValue = 75; // convert 13 to K
            }
            else {
               newValue = value;
            }
            if (suit==0) {
               masterPack[cardCount] = new Card((char)newValue, Card.Suit.SPADES);
            }
            else if (suit == 1) {
               masterPack[cardCount] = new Card((char)newValue, Card.Suit.DIAMONDS);
            }
            else if (suit == 2) {
               masterPack[cardCount] = new Card((char)newValue, Card.Suit.HEARTS);
            }
            else {
               masterPack[cardCount] = new Card((char)newValue, Card.Suit.CLUBS);
            }
            cardCount++;
         }
      }
      cardsUsed = 0;
   }

   //shuffle the deck into a random order.
   public void shuffle() {
      for ( int i = masterPack.length-1; i > 0; i-- ) {
         int rand = (int)(Math.random()*(i+1));
         Card temp = masterPack[i];
         masterPack[i] = masterPack[rand];
         masterPack[rand] = temp;
      }
      cardsUsed = 0;
   }

   // Removes the next card from the deck and returns it.
   public Card dealCard() {
      cardsUsed++;
      return masterPack[cardsUsed - 1];
   }
}
