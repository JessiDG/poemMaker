import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Scanner;

public class sestina {

    public void sestina(){
        String firstStanza = getFirstStanza();
        String[] lines = toStringArraySestina(firstStanza);
        for(int n = 0; n < lines.length; n++)
            lines[n] = cleanUp(lines[n]);
        toTxtFile(lines);
        toConsole(lines);
    }

    private String getFirstStanza(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the first 6 lines of a sestina with / between the lines");
        String firstStanza = sc.nextLine();
        return firstStanza;
    }

    private String[] toStringArraySestina(String firstStanza){
        int numStanzas = 6;
        String[] lines = new String[numStanzas];
        int lineCounter = 0;

        for (int i = 0; i < firstStanza.length(); i++){
            if(firstStanza.charAt(i) != '\n'){

                if (firstStanza.charAt(i) == '/'){
                    lineCounter++;
                    if(lineCounter > 6){
                        System.out.println(lineCounter + " is too many lines for a sestina");
                        return null;
                    }
                }
                lines[lineCounter] += firstStanza.charAt(i);
            }
        }
        return lines;
    }

    private void toTxtFile(String[] lines){
        java.util.Date currentDate = Calendar.getInstance().getTime();
        int numStanzas = lines.length;

        try (PrintWriter out = new PrintWriter("sestina."+currentDate+".txt")) {
            out.println("Your sestina:");

            for(int i = 0; i < numStanzas; i++){
                String[] nextStanza = lines.clone();
                out.println((i+1) + "." + "\n" + nextStanza[0] + "\n" + nextStanza[1] + "\n" +
                        nextStanza[2] + "\n" + nextStanza[3] + "\n" + nextStanza[4] + "\n" + nextStanza[5] + "\n");
                nextStanza[0] = lines[5];
                nextStanza[1] = lines[0];
                nextStanza[2] = lines[4];
                nextStanza[3] = lines[1];
                nextStanza[4] = lines[3];
                nextStanza[5] = lines[2];

                lines = nextStanza;
            }

            out.println((numStanzas+1) + "." + "\n" + lines[0] + "\n" + lines[2] + "\n" +
                    lines[4] + "\n" + "\n");
        }
        catch (java.io.FileNotFoundException e){System.out.println("There has been a writing error.");}
    }

    private void toConsole(String[] lines){
        int numStanzas = lines.length;
        for(int n = 0; n < lines.length; n++)
            lines[n] = cleanUp(lines[n]);

        for(int i = 0; i < numStanzas; i++){
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
        System.out.println((numStanzas+1) + "." + "\n" + lines[0] + "\n" + lines[2] + "\n" +
                lines[4] + "\n" + "\n");
    }

    private String cleanUp(String line){
        String[] badPrefices = {" ", "null/ ", "null /", "null"};
        for(String s : badPrefices) {
            if (line.startsWith(s))
                line = line.substring(s.length());
        }
        return line;
    }
}
