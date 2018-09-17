/*
 * emisszió v3.17.0 @ 2017.3.5
 */
package steve._01_stackgas;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.text.StrBuilder;
import org.joda.time.LocalTime;

/**
 * A project fájlkezeló metódusait gyűjtő osztály.
 * @author István Ócsai
 */
public class FileIO {
    
    private File inputFileName;
    private static final String ENCODING = "UTF-8";
    private static final String REGEX = "(\\d{4}-\\d{2}-\\d{2}) "
            + "(\\d{2}:\\d{2}:\\d{2})\\t"
            + "(-?\\d*.\\d{4})\\t"
            + "(-?\\d*.\\d{4})\\t"
            + "(-?\\d*.\\d{4})\\t"
            + "(-?\\d*.\\d{4})\\t"
            + "(-?\\d*.\\d{4})\\t"
            + "(-?\\d*.\\d{4})\\t"
            + "(-?\\d*.\\d{4})\\t";

    public FileIO(File fileName) {
        this.inputFileName = fileName;
    }
    
    public FileIO(String fileName) {
        this.inputFileName = new File(fileName);
    }

    public FileIO() {}

    public String getFileName() {
        return inputFileName.toString();
    }

    public void setInputFileName(File inputFileName) {
        this.inputFileName = inputFileName;
    }
    
    /**
     * A bemeneti fájl adatait tartalmazó kollekciót hoz létre.
     * @return A bemeneti fájl adatait tartalmazó rendezett kollekció.
     */
    public SortedMap<LocalTime, ArrayList<Double>> importCFData() {
        SortedMap<LocalTime, ArrayList<Double>> result = new TreeMap<>();
        try {
            List lines = FileUtils.readLines(inputFileName, ENCODING);
            Pattern p = Pattern.compile(REGEX);
            for (Iterator it = lines.iterator(); it.hasNext();) {
                String line = it.next().toString();
                Matcher m = p.matcher(line);
                while (m.find()) {
                    ArrayList <Double> values = new ArrayList<>();
                    for (int i = 3; i <= 8; i++) {
                        values.add(Double.parseDouble(m.group(i)));
                    }
                    result.put(LocalTime.parse(m.group(2)), values);
                }
            }
        } catch (IOException ex) {
            System.out.println("Hiba a file olvasása közben" + ex.getMessage());
        } catch (NumberFormatException ex) {
            System.out.println("Hibás formátum!" + ex.getMessage());
        }
        return result;
    }
    
    /**
     * Az elkészített reportot elmentő metódus.
     * @param outputFileName A kimeneti fájl neve.
     * @param report A mentésre kijelölt report.
     */
    public static void exportReport(String outputFileName, ArrayList<String> report) {
        StrBuilder builder = new StrBuilder();
        builder.appendWithSeparators(report, "\n");
        try {
            File file = new File(outputFileName);
            FileUtils.write(file, builder, ENCODING);
        } catch (IOException ex) {
            System.out.println("Hiba a file írása közben" + 
                    ex.getMessage());
        }
    }
    
    public static void exportReport(File outputFileName, ArrayList<String> report) {
        StrBuilder builder = new StrBuilder();
        builder.appendWithSeparators(report, "\n");
        try {
            FileUtils.write(outputFileName, builder, ENCODING);
        } catch (IOException ex) {
            System.out.println("Hiba a file írása közben" + 
                    ex.getMessage());
        }
    }
    
    /**
     * Konfigurációs fájlt olvas be a kalibrálási koncentrációkkal.
     * @param configFile A konfigurációs fájl neve.
     * @return az összes komponens kalibrálási koncentrációját tartalmazó gyűjtemény.
     */
    public static ArrayList<Double> importConfig(String configFile) {
        ArrayList<Double> result = new ArrayList<>();
        try {
            Properties prop = new Properties();
            prop.load(new FileReader(configFile));
            for (String comp : Constants.COMPONENTS) {
                result.add(Double.parseDouble(prop.getProperty(comp)));
            }
        } catch (IOException ex) {
            System.out.printf("Hiba a %s olvasása közben%n", configFile);
        }
        return result;
    }
    
    public static ArrayList<Double> importConfig() {
        return importConfig(Constants.CONFIG_FILE);
    }
}
