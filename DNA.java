
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class DNA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String start;
        String end;
        

        String []DNA={"ATTGCGAA","ATTGCGGA", "ATTACGGA", "ACTACGGA"};
        System.out.print("Start DNA : ");
        start = br.readLine();
        System.out.print("End DNA : ");
        end = br.readLine();
        boolean flag = false;
        int distance = 0;
        for (int i = 0; i < start.length(); i++) {
            if (start.charAt(i) != 'A' && start.charAt(i) != 'T' && start.charAt(i) != 'G' && start.charAt(i) != 'C' && start.length() != 8 && end.length() != 8) {
                System.out.println("This is not a DNA sequence.");
                flag = false;
                break;
            } 
        }
        
        
        flag = FindDistance(start, end, DNA, "");

        if (flag) {
            for (int j = 0; j < start.length(); j++) {
                if (start.charAt(j) != end.charAt(j)) {
                    distance++;
                }
            }
        } else{
             System.out.println("No such Path, can not mutate and reach from start to end");
        }
        System.out.println("Distance between start and end is: " + distance);

    }

    public static boolean FindDistance(String start, String end, String[] Database , String step ){
               step=step+ "  "; 
               boolean done=false;
               char[] tempArr;
               String nextString=null;
               System.out.println(step + "Searching : " + start + " -> " + end);

               if (start.equals(end)){
                    if (isValid_mutation(Database, end)){
                        done=true;
                        System.out.println(step + "search successful ");
                     }                   
               }
               
               
               
               else{
                       // Find out all possible paths
		       for (int i = 0; i < start.length(); i++) {
                                                            
		              if (start.charAt(i) != end.charAt(i)){
                                     tempArr= start.toCharArray();
                                     tempArr[i]=end.charAt(i);
                                     nextString = String.valueOf(tempArr);
		                     if(isValid_mutation(Database, nextString))  // If the string is in database
                                     {
                                             System.out.println(step +"Mutate at " + i + " " + nextString + " going to the next level..");  
		                             done=FindDistance(nextString, end, Database, step); // recursively call
		                             if(done){ 
		                                return done; // As soon as one path is found stop and return
		                              }
                                      } else {
                                           System.out.println(step +"Mutate at " + i + " " + nextString + " ->not PRESENT IN DICTIONARY");      
                                      }
                               }
		       }
               }
               return done;

    }

    public static boolean isValid_mutation(String[] DataBase, String s){
           String x;
           boolean flag=false;
           for (int i = 0; i < DataBase.length; i++) {
                        x=DataBase[i];
                        if(x.equals(s)){
                               flag=true;
                         }
            }
            return flag;            
    }

}
