import java.util.Collections;
import java.util.Stack;

//This class builds the UNO deck
public class Deck_Concept 
{
    static Stack<Card_Object> deck = new Stack<Card_Object>();

    public Deck_Concept()
    {
        Integer number = 1;

        //Red Zero
        deck.push(new Card_Object("Standard","Red",0));

        //Standard Reds besides 0
        for(int i =0;i<18;i++)
        {
            if(i<=8)
            {
                deck.push(new Card_Object("Standard","Red",number));
            }
            else
            {
                number = 1;
            }
            number++;
        }
        number = 1;

        //Special Reds
        for(int i=0;i<2;i++)
        {
            deck.push(new Card_Object("Plus Two","Red",null));
            deck.push(new Card_Object("Skip","Red",null));
            deck.push(new Card_Object("Reverse","Red",null));
        }
        //Yellow Zero
        deck.push(new Card_Object("Standard","Yellow",0));

        //Standard Yellows besides 0
        for(int i =0;i<18;i++)
        {
            if(i<=8)
            {
                deck.push(new Card_Object("Standard","Yellow",number));
            }
            else
            {
                number = 1;
            }
            number++;
        }
        number = 1;
        
        //Special Yellows
        for(int i=0;i<2;i++)
        {
            deck.push(new Card_Object("Plus Two","Yellow",null));
            deck.push(new Card_Object("Skip","Yellow",null));
            deck.push(new Card_Object("Reverse","Yellow",null));
        }
        //Blue Zero
        deck.push(new Card_Object("Standard","Blue",0));

        //Standard Blues besides 0
        for(int i =0;i<18;i++)
        {
            if(i<=8)
            {
                deck.push(new Card_Object("Standard","Blue",number));
            }
            else
            {
                number = 1;
            }
            number++;
        }
        number = 1;

        //Special Blues
        for(int i=0;i<2;i++)
        {
            deck.push(new Card_Object("Plus Two","Blue",null));
            deck.push(new Card_Object("Skip","Blue",null));
            deck.push(new Card_Object("Reverse","Blue",null));
        }
        //Green Zero
        deck.push(new Card_Object("Standard","Green",0));

        //Standard Greens besides 0
        for(int i =0;i<18;i++)
        {
            if(i<=8)
            {
                deck.push(new Card_Object("Standard","Green",number));
            }
            else
            {
                number = 1;
            }
            number++;
        }
        number = 1;

        //Special Greens
        for(int i=0;i<2;i++)
        {
            deck.push(new Card_Object("Plus Two","Green",null));
            deck.push(new Card_Object("Skip","Green",null));
            deck.push(new Card_Object("Reverse","Green",null));
        }

        //Wild Cards and Draw Fours
        for(int i =0;i<4;i++)
        {
            deck.push(new Card_Object("Wild Card",null,null));
            deck.push(new Card_Object("Draw Four",null,null));
        }

        Collections.shuffle(deck);
    }

    public static Stack<Card_Object> getDeck()
    {
        return deck;
    }
}
