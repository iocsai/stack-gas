/*
 * emisszió v3.17.0 @ 2017.3.5
 */
package steve._01_stackgas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import org.joda.time.LocalTime;
import org.joda.time.Minutes;

/**
 * A füstgázkomponensek alapvonal- és meredekség beállítását elvézgő metódusokat
 * tartalmazó osztály.
 * @author István Ócsai
 */
public class Calibration {
    
    private final SortedMap<LocalTime,ArrayList<Double>> oneMinAvg;
    private SortedMap<LocalTime,ArrayList<Double>> zeroCorrection;
    private SortedMap<LocalTime,ArrayList<Double>> spanCorrection;
    private final ArrayList<Double> spanConcentrations;
    private LocalTime zeroTimeInit;
    private LocalTime zeroTimeFinit;
    private LocalTime calibTimeInit;
    private LocalTime calibTimeFinit;
    private LocalTime calibNOTimeInit;
    private LocalTime calibNOTimeFinit;

    public Calibration(SortedMap<LocalTime,ArrayList<Double>> oneMinAvg) {
        this.oneMinAvg = oneMinAvg;
        this.spanConcentrations = new ArrayList<>();
        LocalTime initial = oneMinAvg.firstKey();
        LocalTime ultimate = oneMinAvg.lastKey();
        this.zeroTimeInit = initial;
        this.zeroTimeFinit = ultimate;
        this.calibTimeInit = initial;
        this.calibTimeFinit = ultimate;
        this.calibNOTimeInit = initial;
        this.calibNOTimeFinit = ultimate;
    }

    public SortedMap<LocalTime, ArrayList<Double>> getOneMinAvg() {
        return oneMinAvg;
    }

    public ArrayList<Double> getSpanConcentrations() {
        return spanConcentrations;
    }
    
    public LocalTime getZeroTimeInit() {
        return zeroTimeInit;
    }

    public LocalTime getZeroTimeFinit() {
        return zeroTimeFinit;
    }

    public LocalTime getCalibTimeInit() {
        return calibTimeInit;
    }

    public LocalTime getCalibTimeFinit() {
        return calibTimeFinit;
    }

    public LocalTime getCalibNOTimeInit() {
        return calibNOTimeInit;
    }

    public LocalTime getCalibNOTimeFinit() {
        return calibNOTimeFinit;
    }

    public SortedMap<LocalTime, ArrayList<Double>> getZeroCorrection() {
        return zeroCorrection;
    }

    public SortedMap<LocalTime, ArrayList<Double>> getSpanCorrection() {
        return spanCorrection;
    }

    /**
     * SQL adatbázis segítségével beállítja a kalibrálási koncentrációkat.
     */
    public void setSpanConcentrations() {
        try(Connection conn = DriverManager.getConnection
                ("jdbc:mysql://localhost:3306/certificates", "root", "1234")) {
            CalGasJDBC concentrations = new CalGasJDBC(conn);
            this.spanConcentrations.add(concentrations.findComponent("CO"));
            this.spanConcentrations.add(concentrations.findComponent("CO2"));
            this.spanConcentrations.add(concentrations.findComponent("O2"));
            this.spanConcentrations.add(concentrations.findComponent("NO"));
            this.spanConcentrations.add(concentrations.findComponent("NO"));
            this.spanConcentrations.add(concentrations.findComponent("SO2"));
        } catch (SQLException ex) {
            System.err.println("Hiba történt az adatbáziskapcsolatban! Üzenet: " + ex.getMessage());
        }
    }
    
    /**
     * Config file segítségével beállítja a kalibrálási koncentrációkat.
     * @param fileName a config file neve
     */
    public void setSpanConcentrations(String fileName) {
        this.spanConcentrations.addAll(FileIO.importConfig());
        /*this.spanConcentrations.add(75.6);
        this.spanConcentrations.add(9.41);
        this.spanConcentrations.add(8.95);
        this.spanConcentrations.add(295.0);
        this.spanConcentrations.add(295.0);
        this.spanConcentrations.add(281.0);*/
    }
    
    public void setZeroTimeInit(LocalTime zeroTimeInit) {
        this.zeroTimeInit = zeroTimeInit;
    }

    public void setZeroTimeFinit(LocalTime zeroTimeFinit) {
        this.zeroTimeFinit = zeroTimeFinit;
    }

    public void setCalibTimeFinit(LocalTime calibTimeFinit) {
        this.calibTimeFinit = calibTimeFinit;
    }

    public void setCalibTimeInit(LocalTime calibTimeInit) {
        this.calibTimeInit = calibTimeInit;
    }
    
    public void setCalibNOTimeInit(LocalTime calibNOTimeInit) {
        this.calibNOTimeInit = calibNOTimeInit;
    }

    public void setCalibNOTimeFinit(LocalTime calibNOTimeFinit) {
        this.calibNOTimeFinit = calibNOTimeFinit;
    }

    /**
     * Az alapvonal beállítás adatait tartalmazó kollekciót állítja be.
     */
    public void setZeroCorrection() {
        this.zeroCorrection = getGradient(zeroTimeInit, zeroTimeFinit);
    }

    /**
     * A meredekség beállítás adatait tartalmazó kollekciót állítja be.
     */
    public void setSpanCorrection() {
        ArrayList<Double> initCalib = oneMinAvg.get(calibTimeInit);
        ArrayList<Double> finitCalib = oneMinAvg.get(calibTimeFinit);
        for (int i = 3; i <= 4; i++) {
            double initCalibNO = oneMinAvg.get(calibNOTimeInit).get(i);
            double finitCalibNO = oneMinAvg.get(calibNOTimeFinit).get(i);
            initCalib.remove(i);
            finitCalib.remove(i);
            initCalib.add(i, initCalibNO);
            finitCalib.add(i, finitCalibNO);
        }
        this.spanCorrection = getGradient(calibTimeInit, calibTimeFinit);
        for (Map.Entry<LocalTime, ArrayList<Double>> entry : spanCorrection.entrySet()) {
            LocalTime key = entry.getKey();
            ArrayList<Double> value = entry.getValue();
            ArrayList<Double> originalValue = oneMinAvg.get(key);
            ArrayList<Double> zeroValue = zeroCorrection.get(key);
            ArrayList<Double> newValue = new ArrayList<>();
            for (int i = 0; i < value.size(); i++) {
                newValue.add(originalValue.get(i) + value.get(i) - zeroValue.get(i));
            }
            spanCorrection.put(key, newValue);
        }
    }
    
    /*public SortedMap<LocalTime, ArrayList<Double>> getZeroGrad() {
        SortedMap<LocalTime, ArrayList<Double>> result = new TreeMap<>();
        ArrayList<Double> zeroGrad = getGradient(zeroTimeInit, zeroTimeFinit);
        for (Map.Entry<LocalTime, ArrayList<Double>> entry 
                : oneMinAvg.entrySet()) {
            LocalTime key = entry.getKey();
            ArrayList<Double> value = entry.getValue();
            if (key.isAfter(zeroTimeInit) && key.isBefore(zeroTimeFinit)) {
                result.put(key, zeroCorrection(value, zeroGrad));
            }
        }
        return result;
    }

    private ArrayList<Double> zeroCorrection(ArrayList<Double> value
            , ArrayList<Double> zeroGrad) {
        ArrayList<Double> result = new ArrayList<>();
        for (int i = 0; i < value.size(); i++) {
            result.add(value.get(i) - zeroGrad.get(i));
        }
        return result;
    }*/
    
    private SortedMap<LocalTime, ArrayList<Double>> getGradient(
            LocalTime initial, LocalTime ultimate) {
        SortedMap<LocalTime, ArrayList<Double>> subMap 
                = oneMinAvg.subMap(initial, ultimate);
        SortedMap<LocalTime, ArrayList<Double>> result = new TreeMap<>();
        ArrayList<Double> initValues = oneMinAvg.get(initial);
        ArrayList<Double> finitValues = oneMinAvg.get(ultimate);
        ArrayList<Double> gradValue = new ArrayList<>();
        for (int i = 0; i < initValues.size(); i++) {
            double element = (finitValues.get(i) - initValues.get(i)) 
                        / Minutes.minutesBetween(initial, ultimate).getMinutes();
            gradValue.add(element);
        }
        for (Map.Entry<LocalTime, ArrayList<Double>> entry : subMap.entrySet()) {
            LocalTime key = entry.getKey();
            ArrayList<Double> value = entry.getValue();
            result.put(key, gradValue);
        }
        return result;
    }
    
    /**
     * Az alapvonal- és meredekség beállítást végzi el az eredeti egyperces
     * átlagokat tartalmazó adatokon.
     * @return Az alapvonal- és meredekség válzozással korrigált egyperces átlagok.
     */
    public SortedMap<LocalTime,ArrayList<Double>> getCorrectedAverages() {
        SortedMap<LocalTime,ArrayList<Double>> result = new TreeMap<>();
        LocalTime startTime = zeroTimeInit.isAfter(calibTimeInit) 
                ? zeroTimeInit : calibTimeInit;
        LocalTime endTime = zeroTimeFinit.isBefore(calibTimeFinit) 
                ? zeroTimeFinit : calibTimeFinit;
        for (Map.Entry<LocalTime, ArrayList<Double>> entry 
                : oneMinAvg.subMap(startTime, endTime).entrySet()) {
            LocalTime key = entry.getKey();
            ArrayList<Double> value = entry.getValue();
            ArrayList<Double> zeroValue = zeroCorrection.get(key);
            ArrayList<Double> spanValue = spanCorrection.get(key);
            ArrayList<Double> resultValue = new ArrayList<>();
            for (int i = 0; i < value.size(); i++) {
                resultValue.add(i, value.get(i) - zeroValue.get(i)
                        * spanConcentrations.get(i)
                        / spanValue.get(i)
                );
            }
            result.put(key, resultValue);
        }
        return result;
    }
}
