

package garbogame.card;

public class Card {
    private int rank;
    private Suit suit;
    private String name;

//    public Card() { }

    public Card(int rank, Suit suit, String name) {
        this.rank = rank;
        this.suit = suit;
        this.name = name;
    }

    public int getRank() {
        return rank;
    }

//    public void setRank(int rank) {
//        this.rank = rank;
//    }

    public Suit getSuit() {
        return suit;
    }

//    public void setSuit(Suit suit) {
//        this.suit = suit;
//    }

    public String getName() {
        return name;
    }

//    public void setName(String name) {
//        this.name = name;
//    }
}
