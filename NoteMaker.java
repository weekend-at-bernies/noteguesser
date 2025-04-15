import java.util.*;

/*

Clef artwork from here:
https://asciiart.website/index.php?art=music/musical%20notation

0             -()-  <--- highest possible: G (or E if clef is treble)
1
2             ---
3 
4             --- 
5            
6  |----------                                                              
7  |  ,-.      
8  |--o  |:---                                                         
9  |    /    
10 |---/------
11 |  /       
12 |----------
13 |          
14 |----------
15           
16            ---
17
18            ---
19
20            -()-  <---lowest possible: A (or F if clef is treble)




Some examples:



        -()-              <--- n = 20 : G (bass) | E (treble)
         |
        -|--
         |
        -|--
         |
---------|------
          
----------------

----------------

----------------

----------------




          
          
          
----------------
          
----------------
        ()               <--- n = 11 : E (bass) | C (treble)
--------|-------
        |
--------|-------
        |
--------|-------
        |








        
         |
         |
---------|------
         |
---------|------
         |
--------()------         <--- n = 10 : D (bass) | B (treble)     

----------------

----------------






----------------        <--- n = 14
          
----------------

----------------

----------------

--------|-------        <--- n = 6
        |
      --|-
        |
      --|-
        |
      -()-              <--- n = 0 : A (bass) | F (treble)







     --|-
       |
     --|-
       |
     --|-
       |
   X -()-                <--- The 'X' denotes where we'd put
                              a sharp ('#') or flat ('b')

*/

public class NoteMaker 
{

  ArrayList<Integer> a;
  Random myRandom;


  String[] bclef = {"           ",
                    "           ",
                    "           ",
                    "           ",
                    "           ",
                    "           ",
                    "|----------",
                    "|  ,-.     ",
                    "|--o  |:---",
                    "|    /     ",
                    "|---/------",
                    "|  /       ",
                    "|----------",
                    "|          ",
                    "|----------",
                    "           ",
                    "           ",
                    "           ",
                    "           ",
                    "           ",
                    "           "};


// The '\' character has to be escaped:
  String[] tclef = {"           ",
                    "           ",
                    "           ",
                    "           ",
                    "           ",
                    "     /\\    ",
                    "|----|-|---",
                    "|    |/    ",
                    "|----|-----",
                    "|   /|     ",
                    "|--/-|-----",
                    "| |  | _   ",
                    "|-|-(@)-)--",
                    "|  \\ | /   ",
                    "|----|-----",
                    "     |     ",
                    "   (_|     ",
                    "           ",
                    "           ",
                    "           ",
                    "           "};


  public String intToNote_bass(int n) 
  {
    switch (n) 
    {
      case 0:
        return("A");
      case 1:
        return("B");
      case 2:
        return("C");
      case 3:
        return("D");
      case 4:
        return("E");
      case 5:
        return("F");
      case 6:
        return("G");
      default:
        break;
    }
    return("");
  }

  public String intToNote_treble(int n) 
  {
    switch (n) 
    {
      case 0:
        return("F");
      case 1:
        return("G");
      case 2:
        return("A");
      case 3:
        return("B");
      case 4:
        return("C");
      case 5:
        return("D");
      case 6:
        return("E");
      default:
        break;
    }
    return("");
  }


  public NoteMaker(Random random) 
  {
    myRandom = random;
    a = new ArrayList<>(21);
    for (int i = 0; i < 21; i++)
    {                             
      a.add(i);
    }
  
    Collections.shuffle(a, myRandom);
   // 'a' is now an arraylist with randomized ints 0 to 20
   // System.out.println(a);
  }

  public void work(String[] clef, String[] arr) 
  {
    int i = 0;
    for (i = (arr.length - 1); i >= 0; i--)
    {
      System.out.print(clef[clef.length - 1 - i]);
      System.out.print(arr[i]);
      System.out.print("\n");
    }
  }
  
  
  public String getRandomNote(boolean isBass)
  {

    String s;
    int n = a.get(0);
    a.remove(0);


    /*
       10% chance sharp
       10% chance flat
       80% chance neither sharp nor flat (ie. nothing)
   */
    int code = 0;
    int x = myRandom.nextInt(10);
    if (x == 1)
    {
      code = 1;
    }
    else if (x == 2)
    {
      code = 2;
    }


    if (isBass)
    {
      work(bclef, magic(n, code));
      s = (String) this.intToNote_bass(n % 7);
    }
    else
    {
      work(tclef, magic(n, code));
      s = (String) this.intToNote_treble(n % 7);
    }

    if (code == 1)
    {
      s += "#";
    }
    else if (code == 2)
    {
      s += "b";
    }

    return s;
    
  }


  /* 
     Each element in 'arr' is a string of length 8.
     There are 21 elements (string) in 'arr'.
     Each element (string) is a stdout printable line.
     They should be printed in reverse order, that is to
     say, print the 21st string FIRST, print the 1st string
     LAST.

     code == 0   => nothing
     code == 1   => sharp '#'
     code == 2   => flat 'b'
     code == 3   => natural '?'  NOT IMPLEMENTED
  */ 
  public String[] magic(int n, int code) 
  {
   
    int i = 0;
    String[] arr = new String[21]; 

    for (i = 0; i < arr.length; i++)
    {
      arr[i] = "";
    }

    if (n <= 10) 
    {
      // stem up
      if ((n % 2) == 1)
      {
        arr[n+6] = "  | ";
        arr[n+5] = "--|-";
        arr[n+4] = "  | ";
        arr[n+3] = "--|-";
        arr[n+2] = "  | ";
        arr[n+1] = "--|-";
        arr[n]   = " () ";
      }
      else
      {
        if (n == 10)
        {
          arr[n+6] = "  | ";
        }
        else
        {
          arr[n+6] = "--|-";
        }
        arr[n+5] = "  | ";
        arr[n+4] = "--|-";
        arr[n+3] = "  | ";
        arr[n+2] = "--|-";
        arr[n+1] = "  | ";
        arr[n]   = "-()-";
      }
    }
    else
    {
      // stem down
      if ((n % 2) == 1)
      {
        arr[n]   = " () ";
        arr[n-1] = "-|--";
        arr[n-2] = " |  ";
        arr[n-3] = "-|--";
        arr[n-4] = " |  ";
        arr[n-5] = "-|--";
        arr[n-6] = " |  ";       
      }
      else
      {
        arr[n]   = "-()-";
        arr[n-1] = " |  ";
        arr[n-2] = "-|--";
        arr[n-3] = " |  ";
        arr[n-4] = "-|--";
        arr[n-5] = " |  ";
        arr[n-6] = "-|--";              
      }
    }

    for (i = 0; i < arr.length; i++)
    {
      switch (i)
      {
        case 6:
        case 8:
        case 10:
        case 12:
        case 14:
          if (arr[i].length() > 0)
          {
            if ((i == n) && (code == 1))
            {
              arr[i] = "-#" + arr[i] + "--";
            }
            else if ((i == n) && (code == 2))
            {
              arr[i] = "-b" + arr[i] + "--";
            }
            else
            {
              arr[i] = "--" + arr[i] + "--";
            }
          }
          else
          {
            arr[i] = "--------";
          }
          break;
        default:
          if (arr[i].length() > 0)
          {
            if ((i == n) && (code == 1))
            {
              arr[i] = " #" + arr[i] + "  ";
            }
            else if ((i == n) && (code == 2))
            {
              arr[i] = " b" + arr[i] + "  ";
            }
            else
            {
              arr[i] = "  " + arr[i] + "  ";
            }
          }
          else
          {
            arr[i] = "        ";
          }
          break;         
      }
    }
    return arr;
  }
}
