/*
 * emisszió v3.17.0 @ 2017.3.5
 */
package steve._01_stackgas;

import java.util.ArrayList;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import org.joda.time.LocalTime;

/**
 * Ennek az osztálynak a segítségével történik az adatfájlban tárolt értékek
 * beolvasása és átalakítása egyperces átlagokká.
 * @author István Ócsai
 */
public class CFData {
    
    private SortedMap<LocalTime,ArrayList<Double>> CFDataImport;
    
    private final ArrayList<Double> valueCO = new ArrayList<>();
    private final ArrayList<Double> valueCO2 = new ArrayList<>();
    private final ArrayList<Double> valueO2 = new ArrayList<>();
    private final ArrayList<Double> valueNO = new ArrayList<>();
    private final ArrayList<Double> valueNOx = new ArrayList<>();
    private final ArrayList<Double> valueSO2 = new ArrayList<>();

    public CFData() {
        this.CFDataImport = new TreeMap<>();
    }

    public CFData(FileIO fileName) {
        this.CFDataImport = fileName.importCFData();
    }

    public SortedMap<LocalTime, ArrayList<Double>> getCFDataImport() {
        return CFDataImport;
    }
    
    public void setCFDataImport(FileIO fileName) {
        this.CFDataImport = fileName.importCFData();
    }

    public void setCFDataImport(SortedMap<LocalTime, ArrayList<Double>> CFDataImport) {
        this.CFDataImport = CFDataImport;
    }
    /**
     * A <code>CFDataImport</code> adattagban tárolt nagyfelbontású adatokat
     * alakítja át egyperces átlagokká.
     * @return A füstgázkomponensek egyperces átlagait adja vissza rendezett formában.
     */
    public SortedMap<LocalTime, ArrayList<Double>> getOneMinuteAverages() {
        SortedMap<LocalTime, ArrayList<Double>> result = new TreeMap<>();
        int minute = CFDataImport.firstKey().getMinuteOfHour();
        for (Map.Entry<LocalTime, ArrayList<Double>> entry : CFDataImport.entrySet()) {
            LocalTime key = entry.getKey();
            ArrayList<Double> value = entry.getValue();
            if (key.getMinuteOfHour() == minute) {
                addValues(value);
            } else {
                result.put(key.minusMinutes(1), getAverages());
                clearAllValues();
                addValues(value);
                minute = key.getMinuteOfHour();
            }
        }
        return result;
    }
    
    private void addValues(ArrayList<Double> value) {
        valueCO.add(value.get(0));
        valueCO2.add(value.get(1));
        valueO2.add(value.get(2));
        valueNO.add(value.get(3));
        valueNOx.add(value.get(4));
        valueSO2.add(value.get(5));
    }
    
    private ArrayList<Double> getAverages() {
        ArrayList<Double> averages = new ArrayList<>();
        averages.add(average(valueCO));
        averages.add(average(valueCO2));
        averages.add(average(valueO2));
        averages.add(average(valueNO));
        averages.add(average(valueNOx));
        averages.add(average(valueSO2));
        return averages;
    }

    private double average(ArrayList<Double> values) {
        double sum = 0;
        for (Double value : values) {
            sum += value;
        }
        return sum / values.size();
    }

    private void clearAllValues() {
        valueCO.clear();
        valueCO2.clear();
        valueO2.clear();
        valueNO.clear();
        valueNOx.clear();
        valueSO2.clear();
    }

    @Override
    public String toString() {
        String result = "";
        for (Map.Entry<LocalTime, ArrayList<Double>> entry : CFDataImport.entrySet()) {
            LocalTime key = entry.getKey();
            ArrayList<Double> value = entry.getValue();
            result += key.toString(Constants.TIME_FORMAT);
            for (Double elem : value) {
                result += String.format(Constants.NUM_FORMAT, elem);
            }
        }
        return result;
    }
    
    
}
