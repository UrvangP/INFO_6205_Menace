import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.ArrayList;

public class CSV {

    public CSV() {
    }

    public void generateCSV(String fileName, HashMap<String, Object> data) {
        try (PrintWriter writer = new PrintWriter(fileName + ".csv")) {
            StringBuilder sb = new StringBuilder();
            this.generateColumns(sb, data);
            this.generateRows(sb, data);
            writer.write(sb.toString());
            System.out.println("done!");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void generateColumns(StringBuilder sb, HashMap<String, Object> data) {
        for (String key : data.keySet()) {
            sb.append(key);
            sb.append(',');
        }
        sb.append('\n');
    }

    public void generateRows(StringBuilder sb, HashMap<String, Object> data) {
        int start = 0;
        int i = 0;
        while (true) {
            for (Object value : data.values()) {
                ArrayList<Object> temp = (ArrayList) value;
                if (start < 0 || start >= temp.size()) return;
                //TODO
//                if (temp.get(start) instanceof ArrayList) {
//                    String str = concatenateMe((ArrayList<String>) temp.get(start));
//                    sb.append(str);
//
//                } else {
                sb.append(temp.get(start));
//                }
                sb.append(',');
            }
            sb.append('\n');
            start++;
            i++;
        }
    }


    public String concatenateMe(ArrayList<String> arr) {
        String str = new String();
        for (int i = 0; i < arr.size(); i++) {
            str += arr.get(i);
        }
        return str;
    }
}