import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

class Main {
    public static void main(String[] args) {
        System.out.println("\nPodaj nazwe pierwszego pliku: ");
        String filename1 = getLine();
        
        System.out.println("Podaj nazwe drugiego pliku: ");
        String filename2 = getLine();
                
        Path file1 = Paths.get(filename1);
        Path file2 = Paths.get(filename2);
        
        List<String> fileBuffer1 = new ArrayList<>();
        List<String> fileBuffer2 = new ArrayList<>();
        getFile(file1, fileBuffer1);
        getFile(file2, fileBuffer2);
        
        String confirm = "";
        String targetFilename = "";
        Path targetFile;
        
        boolean flag = true;
        while(flag) {
            System.out.println("\nPodaj nazwe pliku docelowego: ");
            targetFilename = getLine();
            targetFile = Paths.get(targetFilename);

            if(Files.exists(targetFile)) {
                System.out.println("Taki plik juz istnieje. Chcesz go nadpisac: \"tak/nie\"");

                confirm = getLine();
                
                if(confirm.equals("tak")) {
                    add(targetFile, fileBuffer1, fileBuffer2, StandardOpenOption.TRUNCATE_EXISTING);
                    flag = false;
                }
                else if(!confirm.equals("nie")) {
                    System.out.println("tak/nie");
                }
            }
            else {
                add(targetFile, fileBuffer1, fileBuffer2, StandardOpenOption.CREATE_NEW);
                flag = false;
            }
        }
    }

    /**
     * Returns one line from the input stream
     * If for some reason the line is null returns empty string 
     * @return One line of input
    */    
    public static String getLine() {
        String line;
        try{
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            if ((line = in.readLine()) != null) {
                return line;
            }
            
        }
        catch(IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    /**
     * Stores all lines inside a file in a List
     * @param file Path to the file
     * @param fileBuffer List for the file contents to be stored
     */
    public static void getFile(Path file, List<String> fileBuffer) {
        try(BufferedReader reader = Files.newBufferedReader(file)) {
            String line;
            while((line = reader.readLine()) != null) {
                fileBuffer.add(line);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes given string to a file
     * @param targetFile Path to the file
     * @param input String to be written
     * @param option StandardOpenOption flag determining how the line will be added to the file
     * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/nio/file/StandardOpenOption.html">StandardOpenOption</a>
     */
    public static void writeToFile(Path targetFile, String input, StandardOpenOption option) {
        try(BufferedWriter writer = Files.newBufferedWriter(targetFile, option)){
            writer.write(input);
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds the second columns of the files if the values in the first columns match
     * and writes it to the target file
     * @param targetFile Path to the file
     * @param fileBuffer1 List<String> with the contents of the first file
     * @param fileBuffer2 List<String> with the contents of the second file
     * @param option StandardOpenOption flag determining how the line will be added to the file
     * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/nio/file/StandardOpenOption.html">StandardOpenOption</a> 
     */
    public static void add(Path targetFile, List<String> fileBuffer1, List<String> fileBuffer2, StandardOpenOption option) {
        writeToFile(targetFile, "", option);

        for(Iterator<String> it1 = fileBuffer1.iterator(); it1.hasNext();) {
            for(Iterator<String> it2 = fileBuffer2.iterator(); it2.hasNext();) {
                String[] line1 = it1.next().split(" ");
                String[] line2 = it2.next().split(" ");

                float x1 = Float.parseFloat(line1[0]);
                float x2 = Float.parseFloat(line2[0]);

                float f1 = Float.parseFloat(line1[1]); 
                float f2 = Float.parseFloat(line2[1]);
                
                if(Float.compare(x1, x2) == 0) {
                    writeToFile(targetFile, (x1 + " " + (f1 + f2) + "\n"), StandardOpenOption.APPEND);
                }
            }
        }
    }
}