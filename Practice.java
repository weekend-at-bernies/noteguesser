import java.util.*;
import java.io.*;

public class Practice {

  static Random random;
  static NoteMaker noteMaker;
  static int seed;

  // Bass clef: 0
  // Treble clef: 1
  static boolean isBassClef; 

  static void usage() {
    System.out.println("");
    System.out.println("Usage:");
    System.out.println("");
    System.out.println("> java Practice <clef> <optional seed>");
    System.out.println("");
    System.out.println("Bass clef: 0");
    System.out.println("Treble clef: any other number");
    System.out.println("");
  }

  public static void main(String[] args) {
    if (args.length < 1) {
      usage();
      System.exit(1);
    }
    int clef = 0;
    try
    {
      clef = Integer.parseInt(args[0]);
    }
    catch(Exception ex)
    {
      usage();
      System.exit(1);  
    }
    
    System.out.print("\nClef: ");
    if (clef == 0)
    {
      System.out.println("bass");
      isBassClef = true;
    }
    else
    {
      System.out.println("treble");
      isBassClef = false;
    }

    random = new Random();
    if (args.length > 1) {
      seed = (new Integer(args[1])).intValue();
    }
    else
    {
      seed = random.nextInt();
    }
    
    //Set the Random object seed
    random.setSeed(seed);

    noteMaker = new NoteMaker(random);

    System.out.println("Seed: " + seed + "\n");
    userInputLoop();
  }


  static void userInputLoop() {
    BufferedReader br;
  //  System.out.println("\nPress any key to continue!\n");

    br = new BufferedReader(new InputStreamReader(System.in));
   
    String correct;
    String guess;
    for (int i = 0; i < 21; i++) 
    {
      try {
   //     br.readLine();
        System.out.println("-----------------------------------\n");
        System.out.println("Note challenge " + (i + 1) + " of 21\n");
        correct = noteMaker.getRandomNote(isBassClef);
        System.out.print("\nGuess the note: ");
        guess = br.readLine();
        System.out.println("");
        if (correct.equals(guess))
        {
          System.out.println("CORRECT!\n");
        }
        else
        {
          System.out.println("Incorrect! The answer was: " + correct + "\n");
        }
     
      }
      catch (Exception e) {
        System.err.println(e);
      }
    }

  }
}
