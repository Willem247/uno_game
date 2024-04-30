import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

public class Uno_Final_game
{
    static ArrayList<Card_Object> player_hand = new ArrayList<Card_Object>();
    static ArrayList<Card_Object> computer_hand = new ArrayList<Card_Object>();
    static Stack<Card_Object> Deck = Deck_Concept.getDeck();
    static Stack<Card_Object> inPlay = new Stack<Card_Object>();
    static Card_Object last_card_played;
    static Card_Object lc;
    static Scanner keyboard = new Scanner(System.in);
    static Boolean player_turn = true;
    static Boolean computer_turn = true;
    static Card_Object fc;
    static Card_Object pt;
    static Random random = new Random();

    public static void main(String[] args)
    {
        new Deck_Concept();

        System.out.println("Welcome to Uno! To play a card enter the index of the card you want.\nThe top card's index is 1.\n");

        //Gives 7 cards to user and computer
        for(int i=0;i<7;i++)
        {
            player_hand.add(Deck.pop());
        }

        for(int i=0;i<7;i++)
        {
            computer_hand.add(Deck.pop());
        }

        System.out.println("This is your hand.\n");
        
        //makes sure the first card flipped from the deck is standard
        do
        {
            fc= Deck.pop();
            inPlay.push(fc);
        }while(!fc.getType().equals("Standard"));
        fc=inPlay.pop();
        if(inPlay.size()!=0)
        {
            Deck.addAll(inPlay);
            Collections.shuffle(Deck);
        }
        inPlay.push(fc);

        fc = inPlay.peek();

        //Win conditions
        while(player_hand.size()!=0&&computer_hand.size()!=0)
        {
            //last card played
            if(inPlay.size()!=0)
            {
                if(!inPlay.peek().getType().equals("Wild Card")&&!inPlay.peek().getType().equals("Draw Four"))
                {
                    lc=inPlay.peek();
                }
            }
            System.out.println("player hand\n");
            display_hand();
            System.out.println("computer hand\n");
            display_computer_hand();
            sleep(1000);
            System.out.println("The last card played is a "+lc.getType()+" "+lc.getColor()+" "+lc.getValue()+"\n");
            if(can_play(player_hand)&&player_turn)
            {
                int card_index;
                do
                {
                    System.out.println("Which card would you like to play?");
                    card_index= keyboard.nextInt()-1;
                    keyboard.nextLine();
                }while((card_index>=player_hand.size()||card_index<0)||(!if_valid(player_hand.get(card_index))));//asks the player what card they want.

                if(player_hand.get(card_index).getType().equals("Draw Four"))
                {
                    String color_choice;
                    do
                    {
                        System.out.println("What color should the Draw Four be?\n");
                        color_choice=keyboard.nextLine();
                    }while(!color_choice.equals("Red")&&!color_choice.equals("Yellow")&&!color_choice.equals("Blue")&&!color_choice.equals("Green"));
                    lc= new Card_Object("Standard",color_choice,null);
                    computer_turn=false;
                    System.out.println("Four cards have been added to the computer's hand.");
                    if(Deck.size()>4)
                    {
                        for(int i =0;i<4;i++)
                        {
                            computer_hand.add(Deck.pop());
                        }
                    }
                    else
                    {
                        ifEmptyDeck();
                        for(int i =0;i<4;i++)
                        {
                            computer_hand.add(Deck.pop());
                        }
                    }
                }

                if(player_hand.get(card_index).getType().equals("Wild Card"))
                {
                    String color_choice;
                    do
                    {
                        System.out.println("What color should the wild card be?");
                        color_choice=keyboard.nextLine();
                    }while(!color_choice.equals("Red")&&!color_choice.equals("Yellow")&&!color_choice.equals("Blue")&&!color_choice.equals("Green"));
                    lc= new Card_Object("Standard",color_choice,null);
                    
                }

                if(player_hand.get(card_index).getType().equals("Plus Two"))
                {
                    if(Deck.size()>2)
                    {
                        for(int i =0;i<2;i++)
                        {
                            computer_hand.add(Deck.pop());
                        }
                    }
                    else
                    {
                        ifEmptyDeck();
                        for(int i =0;i<2;i++)
                        {
                            computer_hand.add(Deck.pop());
                        }
                    }
                    computer_turn=false;
                }

                if(player_hand.get(card_index).getType().equals("Skip"))
                {
                    computer_turn=false;
                }

                if(player_hand.get(card_index).getType().equals("Reverse"))
                {
                    computer_turn=false;
                }

                inPlay.push(player_hand.get(card_index));
                System.out.println("\nYou played a "+inPlay.peek().getType()+" "+inPlay.peek().getColor()+" "+inPlay.peek().getValue()+"\n");
                player_hand.remove(card_index);
            }
            else
            { // checks if the player is allowed to go.
                if(player_turn==true)
                {
                    System.out.println("You have no playable cards. You drew a card.");
                    if(Deck.size()!=0)
                    {
                        player_hand.add(Deck.pop());
                    }
                    else
                    {
                        ifEmptyDeck(); 
                        player_hand.add(Deck.pop());
                    } 
                }
            }
            player_turn=true;
            String[] colors = {"Red","Blue","Yellow","Green"};
            sleep(1000);
            ComputerTurn:
            if(can_play(computer_hand)&&computer_turn)
            {
                //the computer plays the first card in its hand that is valid.
                for(int i=0;i<computer_hand.size();i++)
                {
                    if(if_valid(computer_hand.get(i)))
                    {
                        switch(computer_hand.get(i).getType())
                        {
                            case "Wild Card":
                                lc=new Card_Object("Standard",colors[random.nextInt(colors.length)],null);
                                sleep(1000);
                                System.out.println("The computer chose the color "+lc.getColor());
                                break;
                            case "Draw Four":
                                lc=new Card_Object("Standard",colors[random.nextInt(colors.length)],null);
                                sleep(1000);
                                System.out.println("The computer chose the color "+lc.getColor());
                                for(int n=0;n<4;n++)
                                {
                                    player_hand.add(Deck.pop());
                                }
                                player_turn=false;
                                break;
                            case "Plus Two":
                                player_turn=false;
                                if(Deck.size()>2)
                                {
                                    for(int j =0;j<2;j++)
                                    {
                                        player_hand.add(Deck.pop());
                                    }
                                }
                                else
                                {
                                    ifEmptyDeck();
                                    for(int j =0;j<2;j++)
                                    {
                                        player_hand.add(Deck.pop());
                                    }
                                }
                                break;
                            case "Skip":
                                player_turn=false;
                                break;
                            case "Reverse":
                                player_turn=false;
                                break;  
                        }
                        inPlay.push(computer_hand.get(i));
                        sleep(1000);
                        System.out.println("The computer played a "+computer_hand.get(i).getType()+" "+computer_hand.get(i).getColor()+" "+computer_hand.get(i).getValue()+"\n");
                        computer_hand.remove(i);
                        break ComputerTurn;
                    }
                }
            }
            else
            {
                if(computer_turn==true)
                {
                    if(Deck.size()!=0)
                    {
                        computer_hand.add(Deck.pop());
                    }
                    else
                    {
                        ifEmptyDeck(); 
                        computer_hand.add(Deck.pop());
                    } 
                }
            }
            computer_turn=true;
        }
        //end conditions
        if(player_hand.size()==0)
        {
            System.out.println("You won! Congratulations.");
        }
        else
        {
            System.out.println("You lost.");
        }
    }

    public static boolean can_play(ArrayList<Card_Object> hand)// checks entire hands
    {
        for(int i=0;i<hand.size();i++)
        {
            if(if_valid(hand.get(i)))
            {
                return true;
            }
        }
        return false;
    }
    public static boolean if_valid(Card_Object card)// checks single cards
    {
        //errors in here
        if(card.getType().equals("Draw Four")||card.getType().equals(("Wild Card")))
        {
            return true;
        }
        if(card.getType().equals(lc.getType())&&!card.getType().equals("Standard"))
        {
            return true;
        }
        if(card.getColor()!=null&&lc.getColor()!=null&&card.getColor().equals(lc.getColor()))
        {
            return true;
        }
        if(card.getValue()!=null&&card.getValue()==lc.getValue())
        {
            return true;
        }
        return false;
    }
    //displays the players hand
    public static void display_hand()
    {
        for(int i=0;i<player_hand.size();i++)
        {
            sleep(250);
            System.out.print(player_hand.get(i).getType()+" "+player_hand.get(i).getColor()+" "+player_hand.get(i).getValue()+"\n");
        }
        System.out.println();
    }

    public static void ifEmptyDeck()
    {
        //Transfers cards in play back into the deck besides the last card played
        last_card_played = inPlay.pop();
        Deck.addAll(inPlay);
        Collections.shuffle(Deck);
        inPlay.push(last_card_played);
    }
    //pauses the program so the game is not jarring.
    public static void sleep(int time)
    {
        try
        {
            Thread.sleep(time);
        }
        catch (Exception e)
        {
            System.out.println("error");
        }
    }
    //displays computer hand.
    public static void display_computer_hand()
    {
        for(int i=0;i<computer_hand.size();i++)
        {
            sleep(250);
            System.out.print(computer_hand.get(i).getType()+" "+computer_hand.get(i).getColor()+" "+computer_hand.get(i).getValue()+"\n");
        }
        System.out.println();
    }
}