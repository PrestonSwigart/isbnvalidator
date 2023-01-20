import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

/** ISBN number validator
 *  @version 01.20.23
 *  @author Ryfi, Aarav, Bobby, Preston
 */
public class ISBNValidator {
    private String[] validNums;
    private String[] invalidNums;
    private final String filename;

    /**
     * Simple constructor; initializes arrays
     */
    public ISBNValidator() {
        validNums = new String[1000];
        invalidNums = new String[1000];
        filename = "isbn_files/isbn1.dat";
        int lines = 0;
        try {
            Scanner in = new Scanner(new File(filename));
            while(in.hasNext()) {
                lines++;
                in.nextLine();
            }
            in.close();
        }
        catch(Exception e)  { System.out.println(e.toString()); }
        validNums = new String[lines];
        invalidNums = new String[lines];
    }
    /**
     *  Imports .dat file, calls isValidISBN method and stores Strings into corresponding arrays
     */
    public void importData() {
        try {
            Scanner in = new Scanner(new File(filename));
            int validCount = 0, invalidCount = 0;
            while(in.hasNext()) {
                String isbn = in.nextLine().trim().strip();
                if(isValidISBN(isbn))
                    validNums[validCount++] = isbn;
                else
                    invalidNums[invalidCount++] = isbn;
            }
            in.close();
        }
        catch(Exception e)  { System.out.println(e.toString()); }
    }

    /**
     *  Determines validity of supplied ISBN number - called inside importData
     *  @Param isbn - number to determine if is valid or not
     *  @returns true if isbn value is valid false if otherwise
     */
    public boolean isValidISBN(String isbn) {
        String nums = isbn.replaceAll("-", "");
        return (nums.startsWith("978") || nums.startsWith("979")) && 0 == (nums.charAt(0) + nums.charAt(1) * 3 + nums.charAt(2) + nums.charAt(3) * 3 + nums.charAt(4) + nums.charAt(5) * 3 + nums.charAt(6) + nums.charAt(7) * 3 + nums.charAt(8) + nums.charAt(9) * 3 + nums.charAt(10) + nums.charAt(11) * 3 + nums.charAt(12)) % 10;
    }
    /**
     *  output the user-picked ISBN list or quit the application
     */
    public void runProgram() {
        Scanner scan = new Scanner(System.in);
        outerloop:
        while(true){
            System.out.println("All ISBN data has been imported and validated. Would you like to:\n1) View all valid ISBN numbers\n2) View all invalid ISBN numbers\n3) Quit");
            int userChoice = scan.nextInt();
            System.out.println("Your selection: " + userChoice);
            switch (userChoice) {
                case 1 -> System.out.println(validNums[0] != null ? Arrays.toString(validNums): "Error, no valid numbers");
                case 2 -> System.out.println(invalidNums[0] != null ? Arrays.toString(invalidNums): "Error, no invalid numbers");
                case 3 -> { break outerloop; }
                default -> System.out.println("Invalid input");
            }
        }
    }
    /**
     *  Main method for class ISBNValidator
     * @Param String[] args, as needed
     */
    public static void main(String[] args) {
        ISBNValidator app = new ISBNValidator();
        System.out.println("* ISBN Validator Program *");
        System.out.println("...Importing data...");
        app.importData();
        app.runProgram();
        System.out.println("* End of Program *");
    }
}