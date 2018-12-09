import java.util.Scanner;
import java.io.PrintWriter;
import java.util.Calendar;

public class Sonnet {

    String[] sampleIambicPentameterSonnet = {
            "I’m queer for nouns or verbs that end in -ickle,",
            "Carolina to Boston as a nine-year-old.",
            "coat, tie the scarf to keep her hood in place.",
            "The awful sin remained still unforgiven.",

            "Birds whipping in a storm could be held",
            "reciting from your thigh. “mai, mai e ‘ai. (by NO‘U REVILLA)",
            "lured me to bargain bins. I learned to lie",
            "the rest as cache. Your flesh, my flesh, your dead",

            "sure sprung from Imhotep's kundalini",
            "Last sight of all it may be with these eyes,",
            "I shall go howling into the conscious grave",
            "As night-watching men wait for the sun",

            "Who knows? No matter. When I hear it play",
            "I am. I want to be. I bleed away.",
            "Authors of the example lines: Alfred Corn, Marilyn Nelson, Deborah Paredez, " +
                    "Claude McKay, Terrence Hayes, No'u Revilla, Caitlin Doyle, " +
                    "Anna Maria Hong, Yolanda Wisher, Eleanor Fargeon, Allen Grossman, " +
                    "Ivor Gurney, Jorge Luis Borges (translated by Tony Barnstone)"};

    public void sonnet(){
        String[] sonnet = getSonnet();
        sonnetToTxt(sonnet);
        sonnetToConsole(sonnet);
    }

    private String[] getSonnet(){
        int sonnetLength = 14;
        String[] sonnet = new String[sonnetLength];
        String lastWord = "";

        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < sonnetLength; i ++) {
            System.out.println();
            if(i==0) {
                System.out.println("Write a sentence with 10 syllables, " +
                        "alternating hard and soft");
            }
            else if(i == sonnetLength-2){
                System.out.println("Almost done! Write a sentence with 10 " +
                        "syllables, alternating hard and soft and end it with " +
                        "a word you can rhyme with");
            }
            else if(i == sonnetLength-1) {
                lastWord = getLastWord(sonnet[i - 1]);
                System.out.println("Last one! Write a sentence with 10 " +
                        "syllables, alternating hard and soft and end it with " +
                        "a word that rhymes with \"" + lastWord + "\"");
            }
            else {
                System.out.println("Write another sentence with 10 syllables, " +
                        "alternating hard and soft");
            }
            System.out.println("e.g.: " + sampleIambicPentameterSonnet[i]);
            sonnet[i] = sc.nextLine();
        }
        System.out.println(sampleIambicPentameterSonnet[sampleIambicPentameterSonnet.length-1] + "\n");
        return sonnet;
    }

    private String getLastWord(String lastLine){
        String lastWord = "";

        for(int i = lastLine.length(); i > 0; i--){
            char currentChar = lastLine.charAt(i - 1);
            if(currentChar != ' '){
                lastWord = currentChar + lastWord;
            }
            else {
                return lastWord;
            }
        }
        return lastWord;
    }

    private void sonnetToTxt(String[] sonnet) {

        java.util.Date currentDate = Calendar.getInstance().getTime();

        try (PrintWriter out = new PrintWriter("sonnet."+currentDate+".txt")) {
            out.println("Your sonnet:");

            for(int i = 0; i < sonnet.length; i++){
                out.println(sonnet[i] +"\n");
                if(i % 4 == 0)
                    out.println();
            }
        }
        catch (java.io.FileNotFoundException e){System.out.println("There has been a writing error.");}
    }

    private void sonnetToConsole(String[] sonnet) {
        System.out.println("Your sonnet:");
        for(int i = 0; i < sonnet.length; i++){
            System.out.println(sonnet[i]);
            if(i != 0 && (i + 1) % 4 == 0)
                System.out.println();
        }
    }
}
