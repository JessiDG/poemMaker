import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Scanner;

public class Sestina {

    int NUM_STANZAS = 6;

    public void sestina(){
        String firstStanza = getFirstStanza();

        try {
            String[] lines = toStringArraySestina(firstStanza);
            for(int n = 0; n < lines.length; n++) {
                lines[n] = cleanUp(lines[n]);
            }
            toTxtFile(lines);
            toConsole(lines);
        }
        catch (NullPointerException e){
            System.out.println("Doesn't look like there's enough text there to make a poem. Try again!");
        }
    }

    private String getFirstStanza(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the first 6 lines of a sestina with / between the lines");
        String firstStanza = sc.nextLine();
        return firstStanza;
    }

    private String[] toStringArraySestina(String firstStanza){
        if(firstStanza.length()<NUM_STANZAS){
            throw new NullPointerException("toStringArraySestina");
        }

        String[] lines = new String[NUM_STANZAS + 1];
        int lineCounter = 0;

        for (int i = 0; i < firstStanza.length(); i++){
            if(firstStanza.charAt(i) != '\n'){
                if(lineCounter < 6) {
                    if (firstStanza.charAt(i) == '/') {
                        lineCounter++;
                    }
                }
                    else{
                        System.out.println("Sex means \"six\" in French, so " + (lineCounter + 1)+ " is too many " +
                                "lines for a sestina, let's stick with the " +
                                "first "+ (lineCounter) +", ok?");
                        return lines;
                    }

                lines[lineCounter] += firstStanza.charAt(i);
            }
        }
        return lines;
    }

    private void toTxtFile(String[] lines){
        java.util.Date currentDate = Calendar.getInstance().getTime();

        try (PrintWriter out = new PrintWriter("sestina."+currentDate+".txt")) {
            out.println("Your sestina:");

            for(int i = 0; i < NUM_STANZAS - 1; i++){
                String[] nextStanza = lines.clone();
                out.println((i+1) + "." + "\n" + nextStanza[0] + "\n" +
                        nextStanza[1] + "\n" + nextStanza[2] + "\n" +
                        nextStanza[3] + "\n" + nextStanza[4] + "\n" +
                        nextStanza[5] + "\n");
                nextStanza[0] = lines[5];
                nextStanza[1] = lines[0];
                nextStanza[2] = lines[4];
                nextStanza[3] = lines[1];
                nextStanza[4] = lines[3];
                nextStanza[5] = lines[2];

                lines = nextStanza;
            }

            out.println((NUM_STANZAS) + "." + "\n" + lines[0] + "\n" + lines[2] + "\n" +
                    lines[4] + "\n" + "\n");
        }
        catch (java.io.FileNotFoundException e){System.out.println("There has been a writing error.");}
    }

    private void toConsole(String[] lines){
        int NUM_STANZAS = lines.length;
        for(int n = 0; n < lines.length; n++)
            lines[n] = cleanUp(lines[n]);

        for(int i = 0; i < NUM_STANZAS - 1; i++){
            String[] nextStanza = lines.clone();
            System.out.println((i+1) + "." + "\n" + nextStanza[0] + "\n" + nextStanza[1] + "\n" +
                    nextStanza[2] + "\n" + nextStanza[3] + "\n" + nextStanza[4] + "\n" + nextStanza[5] + "\n");
            nextStanza[0] = lines[5];
            nextStanza[1] = lines[0];
            nextStanza[2] = lines[4];
            nextStanza[3] = lines[1];
            nextStanza[4] = lines[3];
            nextStanza[5] = lines[2];

            lines = nextStanza;
        }
        System.out.println((NUM_STANZAS) + "." + "\n" + lines[0] + "\n" + lines[2] + "\n" +
                lines[4] + "\n" + "\n");
    }

    private String cleanUp(String line){
        String[] badPrefices = {" ", "null/ ", "null /", "null", "/"};
        for(String s : badPrefices) {
            if (line.startsWith(s))
                line = line.substring(s.length());
        }
        return line;
    }
}
