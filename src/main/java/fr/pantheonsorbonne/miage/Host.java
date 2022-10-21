package fr.pantheonsorbonne.miage;

import fr.pantheonsorbonne.miage.exception.NoMoreCardException;
import fr.pantheonsorbonne.miage.game.Card;
import fr.pantheonsorbonne.miage.game.Deck;
import fr.pantheonsorbonne.miage.model.Game;
import fr.pantheonsorbonne.miage.model.GameCommand;

import java.util.*;

/**
 * Hello world!
 */
public class Host {
    private static final int PLAYER_COUNT = 3;

    public static void main(String[] args) {
        //create the host facade
        HostFacade hostFacade = Facade.getFacade();
        hostFacade.waitReady();

        //set the name of the player
        hostFacade.createNewPlayer("Host");

        //create a new game of war
        Game war = hostFacade.createNewGame("WAR");

        //wait for enough players to join
        hostFacade.waitForExtraPlayerCount(PLAYER_COUNT);

        //send the initial hand to every players
        for (String playerName : war.getPlayers()) {
            //get random cards
            Card[] cards = Deck.getRandomCards(3);
            // transform them to String
            String hand = Card.cardsToString(cards);
            //send them to this players
            hostFacade.sendGameCommandToPlayer(war, playerName, new GameCommand("cardsForYou", hand));
        }
        // make a queue with all the players
        final Queue<String> players = new LinkedList<>(war.getPlayers());
        //repeat until only 1 player is left
        while (players.size() > 1) {
            //these are the cards played by the players on this round
            Queue<Card> roundStack = new LinkedList<>();

            //loop until there is a winner
            while (true) {
                //take the first player form the queue
                String firstPlayerInRound = players.poll();
                //and put it immediately at the end
                players.offer(firstPlayerInRound);

                //take the second player from the queue
                String secondPlayerInRound = players.poll();
                //and put it back immediately also
                players.offer(secondPlayerInRound);

                //here, we try to get the first player card
                Card firstPlayerCard = null;
                try {
                    firstPlayerCard = getCardFromPlayer(hostFacade, war, firstPlayerInRound);
                } catch (NoMoreCardException nmc) {
                    //first round player is out of cards
                    //we send him a gameover
                    hostFacade.sendGameCommandToPlayer(war, firstPlayerInRound, new GameCommand("gameOver"));
                    //remove him from the queue so he won't play again
                    players.remove(firstPlayerInRound);
                    //give back all the cards for this round to the second players
                    hostFacade.sendGameCommandToPlayer(war, secondPlayerInRound, new GameCommand("cardsForYou", Card.cardsToString(roundStack.toArray(new Card[roundStack.size()]))));
                    break;
                }
                //here we also get the second player card
                Card secondPlayerCard = null;
                try {
                    secondPlayerCard = getCardFromPlayer(hostFacade, war, secondPlayerInRound);
                } catch (NoMoreCardException nmc) {
                    //second round player is out
                    //send him a gameover
                    hostFacade.sendGameCommandToPlayer(war, secondPlayerInRound, new GameCommand("gameOver"));
                    //remove him from the queue
                    players.remove(secondPlayerInRound);
                    //give this round's card to the first player
                    hostFacade.sendGameCommandToPlayer(war, firstPlayerInRound, new GameCommand("cardsForYou", Card.cardsToString(roundStack.toArray(new Card[roundStack.size()]))));
                    break;
                }

                //put the two cards on the roundstack
                roundStack.offer(firstPlayerCard);
                roundStack.offer(secondPlayerCard);

                //compute who is the winner
                String winner = null;
                if (firstPlayerCard.getValue().getRank() > secondPlayerCard.getValue().getRank()) {
                    winner = firstPlayerInRound;
                } else if (firstPlayerCard.getValue().getRank() < secondPlayerCard.getValue().getRank()) {
                    winner = secondPlayerInRound;
                }
                //if there's a winner, we distribute the card to him
                if (winner != null) {
                    List<Card> cards = new ArrayList<>();
                    cards.addAll(roundStack);
                    //shuffle the round deck so we are not stuck
                    Collections.shuffle(cards);
                    hostFacade.sendGameCommandToPlayer(war, winner, new GameCommand("cardsForYou", Card.cardsToString(cards.toArray(new Card[cards.size()]))));
                    break;
                }
                //otherwise we do another round.
            }


        }
        //since we've left the loop, we have only 1 player left: the winner
        String winner = players.poll();
        //send him the gameover and leave
        hostFacade.sendGameCommandToPlayer(war, winner, new GameCommand("gameOver", "win"));
        System.out.println(winner + " won! bye");
        System.exit(0);


    }

    /**
     * we get a card from a player, if possible.
     *
     * If the player has no more card, throw an exception
     *
     * @param facade tje facade to use
     * @param war the game to use
     * @param player the name of the player
     * @return a card from a player
     * @throws NoMoreCardException if player has no more card.
     */
    private static Card getCardFromPlayer(Facade facade, Game war, String player) throws NoMoreCardException {
        facade.sendGameCommandToPlayer(war, player, new GameCommand("playACard"));
        GameCommand expectedCard = facade.receiveGameCommand(war);
        if (expectedCard.name().equals("card")) {
            return Card.valueOf(expectedCard.body());
        }
        if (expectedCard.name().equals("outOfCard")) {
            throw new NoMoreCardException();
        }
        //should not happen!
        throw new RuntimeException("invalid state");

    }

}
