import com.opencsv.CSVWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BuilderCSV {

    private static String FileName;
    private static String TestId;
    private static String Summary;
    private static String Description;
    private static String Action;
    private static String ExpectedResult;
    private static String Data;

    public static void main() throws IOException {

        String CSVName = FileName;
        if (CSVName == null || CSVName == "" ) {
            System.out.println("\n***************************************");
            System.out.println("INFO STATUS: FileName MUST be declared !!");
            System.out.println("***************************************");
            System.exit(0);

        }else{

            List<String[]> csvData = createCsvDataSimple(TestId, Summary, Description, Action, ExpectedResult, Data);
            try (CSVWriter writer = new CSVWriter(new FileWriter("src/test/java/BuilderCSV/"+CSVName+".csv",true))) {
                writer.writeAll(csvData);
            }

        }

    }

    private static List<String[]> createCsvDataSimple(String TestId, String Summary, String Description, String Action, String ExpectedResult, String Data) {
        List<String[]> list = new ArrayList<>();
        String[] header = {"TestId", "TestType", "Summary", "Description", "Action", "ExpectedResult", "Data", "Label"};
        list.add(header);

            String[] record = {TestId, "API",Summary, Description, Action, ExpectedResult, Data, "API_TEST"};
            list.add(record);

        return list;
    }

    public void setFileName(String fileName) {
        FileName = fileName;
    }

    public void setTestId(String testId) {
        TestId = testId;
    }

    public void setSummary(String summary) {
        Summary = summary;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setAction(String action) {
        Action = action;
    }

    public void setExpectedResult(String expectedResult) {
        ExpectedResult = expectedResult;
    }

    public void setData(String data) {
        Data = data;
    }
}
