package utils;

public class InputParser {
    
     public int parseAsInt(String input) {
        int value = 0;
        try {
         value = Integer.parseInt(input);
        } catch (Exception e) {
           System.out.println(e.getMessage());
        }

        return value;
    }

    public String parseInputAsStr(String input) {
        return "x";
    }

}
