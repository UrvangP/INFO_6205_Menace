import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class CSV {

    public CSV() {
    }

    public void generateCSV(String fileName, Map<String, Object> data) {
        try (PrintWriter writer = new PrintWriter(fileName + ".csv")) {
            StringBuilder sb = new StringBuilder();
            this.generateColumns(sb, data);
            this.generateRows(sb, data);
            writer.write(sb.toString());
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void generateColumns(StringBuilder sb, Map<String, Object> data) {
        for (String key : data.keySet()) {
            sb.append(key);
            sb.append(',');
        }
        sb.append('\n');
    }

    public void generateRows(StringBuilder sb, Map<String, Object> data) {
        int start = 0;
        int i = 0;
        while (true) {
            for (Object value : data.values()) {
                List<Object> temp = (List) value;
                if (start < 0 || start >= temp.size()) return;
                if (temp.get(start) instanceof ArrayList<?>) {
                    sb.append(concatenateMe((ArrayList<?>) temp.get(start)));
                } else {
                    sb.append(temp.get(start));
                }
                sb.append(',');
            }
            sb.append('\n');
            start++;
            i++;
        }
    }


    public String concatenateMe(ArrayList<?> arr) {
        String str = "";
        for (int i = 0; i < arr.size(); i++) {
            str += arr.get(i);
            if (i < arr.size() - 1) {
                str += " | ";
            }
        }
        return str;
    }
}