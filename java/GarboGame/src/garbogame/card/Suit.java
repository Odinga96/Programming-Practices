package garbogame.card;

public class Suit {
    private String Name;
    private int rank;

    //Initialises the contents of the suit
    public Suit(String name, int rank) {
        Name = name;
        this.rank = rank;
    }

    //used to create an object that does not need to initialize the
//    public Suit() {}

    public String getName() {
        return Name;
    }

//    public void setName(String name) {
//        Name = name;
//    }

    public int getRank() {
        return rank;
    }

//    public void setRank(int rank) {
//        this.rank = rank;
//    }
}
