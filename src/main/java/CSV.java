import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.FileSystems;
import java.nio.file.Path;
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
            sb.append(key.toUpperCase());
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

    public Map<String, List<Integer>> readCSV() {
        Map<String, List<Integer>> allCombinations = new HashMap<>();
        try {
            Path path = FileSystems.getDefault().getPath("").toAbsolutePath();
            Scanner sc = null;
            sc = new Scanner(new File(path + "/combinations.csv"));
            sc.useDelimiter("\n");
            while (sc.hasNext()) {
                String temp = sc.next();
                String[] temp1 = temp.split(",");
                String key = temp1[1];
                String[] value = temp1[0].split(" | ");
                if (value.length == 1) continue;
                List<Integer> temp2 = new ArrayList<>();
                for (int i = 0; i < value.length; i++) {
                    if (i % 2 == 0) temp2.add(Integer.valueOf(value[i]));
                }
                allCombinations.put(key, temp2);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!!");
            System.out.println(e.getMessage());
        }
        return allCombinations;
    }
}