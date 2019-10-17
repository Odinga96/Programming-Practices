package garbogame.player;

import garbogame.card.Card;

import java.util.LinkedList;
import java.util.List;

public class Player {

    private  String name;
    private int score;
    private LinkedList<Card> cards=new LinkedList<>();

//    public Player() {
//    }

    public Player(String name, int score) {
        this.name = name;
        this.score = score;
    }

//    public Player(String name, int score, LinkedList<Card> cards) {
//        this.name = name;
//        this.score = score;
//        this.cards = cards;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public List<Card> getCards() {
        return cards;
    }
//
//    public void setCards(LinkedList<Card> cards) {
//        this.cards = cards;
//    }
}

