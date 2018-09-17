/*
 * emisszió v3.17.0 @ 2017.3.5
 */
package steve._01_stackgas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import org.apache.commons.lang3.text.StrBuilder;
import org.joda.time.LocalTime;

/**
 * A különböző reportokat elkészítő osztály.
 * @author István Ócsai
 */
public class StackGasReport {

    private final SortedMap<LocalTime, ArrayList<Double>> oneMinAvgCorrected;
    private SortedMap<LocalTime, ArrayList<Double>> oneMinAvgPhysNormCond;
    private LocalTime startTime;
    private LocalTime endTime;
    private double referenceO2;
    
    private double volumeFlow = 1000;
    private double operations = 1;

    public StackGasReport(SortedMap<LocalTime, ArrayList<Double>> oneMinAvg
            , LocalTime startTime, LocalTime endTime) {
        this.oneMinAvgCorrected = oneMinAvg;
        this.startTime = startTime;
        this.endTime = endTime;
        this.oneMinAvgPhysNormCond = new TreeMap<>(setOneMinAvgPhysNormCond());
        this.referenceO2 = 5.0;
    }

    public StackGasReport(SortedMap<LocalTime, ArrayList<Double>> oneMinAvg) {
        this(oneMinAvg, oneMinAvg.firstKey(), oneMinAvg.lastKey());
    }

    public SortedMap<LocalTime, ArrayList<Double>> getOneMinAvgCorrected() {
        return oneMinAvgCorrected;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public double getReferenceO2() {
        return referenceO2;
    }

    public void setReferenceO2(double referenceO2) {
        this.referenceO2 = referenceO2;
    }

    public void setVolumeFlow(double volumeFlow) {
        this.volumeFlow = volumeFlow;
    }

    public void setOperations(double operations) {
        this.operations = operations;
    }

    private SortedMap<LocalTime, ArrayList<Double>> setOneMinAvgPhysNormCond() {
        SortedMap<LocalTime, ArrayList<Double>> result = new TreeMap<>();
        for (Map.Entry<LocalTime, ArrayList<Double>> entry : oneMinAvgCorrected.entrySet()) {
            LocalTime key = entry.getKey();
            ArrayList<Double> value = entry.getValue();
            ArrayList<Double> normPhys = new ArrayList<>(value.size());
            for (int i = 0; i < value.size(); i++) {
                normPhys.add(value.get(i) * Constants.MOLAR_FACTOR[i] 
                        / Constants.PHYS_NORM_VOLUME);
            }
            result.put(key, normPhys);
        }
        return result;
    }

    public ArrayList<String> getOneMinReport(LocalTime startTime, LocalTime endTime, 
            boolean header) {
        ArrayList<String> result = new ArrayList<>();
        if (header) {
            result.add(Constants.FIRST_LINE);
        }
        SortedMap<LocalTime, ArrayList<Double>> source
                = oneMinAvgPhysNormCond.subMap(startTime, endTime);
        for (Map.Entry<LocalTime, ArrayList<Double>> entry : source.entrySet()) {
            LocalTime key = entry.getKey();
            ArrayList<Double> values = entry.getValue();
            StrBuilder builder = new StrBuilder();
            builder.append(key.toString(Constants.TIME_FORMAT));
            builder.appendSeparator(Constants.STR_SEPARATOR);
            builder.appendWithSeparators(values, Constants.STR_SEPARATOR);
            //builder.appendSeparator("\n");
            result.add(builder.build());
        }
        return result;
    }

    public ArrayList<String> getOneMinReport(boolean header) {
        return getOneMinReport(getStartTime(), getEndTime(), header);
    }

    public double getAverage(ArrayList<Double> list) {
        double sum = 0;
        for (Double elem : list) {
            sum += elem;
        }
        return sum / list.size();
    }
    
    public ArrayList<Double> getComponent(String componentName
            , LocalTime startTime, LocalTime endTime) {
        ArrayList<Double> result = new ArrayList<>();
        for (Map.Entry<LocalTime, ArrayList<Double>> entry 
                : oneMinAvgPhysNormCond.subMap(startTime, endTime).entrySet()) {
            //LocalTime key = entry.getKey();
            ArrayList<Double> value = entry.getValue();
            for (int i = 0; i < value.size(); i++) {
                if (componentName.equals(Constants.COMPONENTS[i])) {
                    result.add(value.get(i));
                }
            }
        }
        return result;
    }
    
    public ArrayList<String> getPeriodReport(LocalTime startTime, LocalTime endTime, 
            boolean header) {
        ArrayList<String> result = new ArrayList<>();
        if (header) {
            result.add(Constants.HEADER);
        }
        ArrayList<String> components = new ArrayList<>(Arrays.asList(Constants.COMPONENTS));
        components.remove("NO");
        for (String elem : components) {
            result.add(builder(elem, startTime, endTime, false).build());
        }
        
        return result;
    }
    
    public ArrayList<String> getPeriodReport(LocalTime startTime, LocalTime endTime) {
        return getPeriodReport(startTime, endTime, false);
    }
    
    public ArrayList<String> getPeriodReport(boolean header) {
        return getPeriodReport(startTime, endTime, header);
    }

    public double getO2Reference(double compAvg, double avgO2, double referenceO2) {
        return compAvg * (21 - referenceO2) / (21 - avgO2);
    }

    public double getO2Reference(double compAvg, double avgO2) {
        return getO2Reference(compAvg, avgO2, referenceO2);
    }
    
    public ArrayList<String> getFullTimeReport (double volumeFlow, double operations,
            LocalTime startTime, LocalTime endTime) {
        setVolumeFlow(volumeFlow);
        setOperations(operations);
        ArrayList<String> result = new ArrayList<>();
        result.add(Constants.HEADER + Constants.HEADER_PLUS);
        ArrayList<String> components = new ArrayList<>(Arrays.asList(Constants.COMPONENTS));
        components.remove("NO");
        for (String elem : components) {
            result.add(builder(elem, startTime, endTime, true).build());
        }        
        return result;
    }
    
    public ArrayList<String> getFullTimeReport (double volumeFlow, double operations) {
        return getFullTimeReport(volumeFlow, operations, startTime, endTime);
    }
    
    public ArrayList<String> getFullTimeReport () {
        return getFullTimeReport(volumeFlow, operations, startTime, endTime);
    }
    
    private StrBuilder builder(String comp, LocalTime startTime, LocalTime endTime, boolean fullReport) {
        double avgO2 = getAverage(getComponent("O2", startTime, endTime));
        StrBuilder builder = new StrBuilder();
        
        builder.append(startTime.toString(Constants.TIME_FORMAT));
        builder.appendSeparator(Constants.STR_SEPARATOR);
        builder.append(endTime.toString(Constants.TIME_FORMAT));
        builder.appendSeparator(Constants.STR_SEPARATOR);
        builder.append(comp);
        builder.appendSeparator(Constants.STR_SEPARATOR);
        double compAvg = getAverage(getComponent(comp, startTime, endTime));
        builder.append(String.format(Constants.NUM_FORMAT, compAvg));
        builder.appendSeparator(Constants.STR_SEPARATOR);
        if (!comp.equals("O2") && !comp.equals("CO2")) {
            builder.append(String.format(Constants.NUM_FORMAT,
                    getO2Reference(compAvg, avgO2)));
        }
        if (fullReport) {
            builder.appendSeparator(Constants.STR_SEPARATOR);
            builder.append(String.format(Constants.NUM_FORMAT, 
                    compAvg * volumeFlow / 1000));
            builder.appendSeparator(Constants.STR_SEPARATOR);
            builder.append(String.format(Constants.NUM_FORMAT,
                    compAvg * volumeFlow / 1000 / operations));
        }
        
        return builder;
    }
    
}
