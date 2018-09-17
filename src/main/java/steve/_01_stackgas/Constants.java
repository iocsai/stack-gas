/*
 * emisszió v3.17.0 @ 2017.3.5
 */
package steve._01_stackgas;

/**
 * A project megvalósításához szükséges konstansok tárolóobjektuma.
 * @author István Ócsai
 */
public class Constants {
    
    static final String NUM_FORMAT = "%8.3f";
    static final String TIME_FORMAT = "HH:mm";
    static final String STR_SEPARATOR = "\t";
    
    static final String FIRST_LINE = "Time\tCO\tCO2\tO2\tNO\tNOx\tSO2";
    static final String HEADER = "Time from\tto\tCompound\tAverage\t"
            + "Average at referece O2";
    static final String HEADER_PLUS = "\tEmission mass flow\tSpecific emission";
    
    static final String[] COMPONENTS = {"CO", "CO2", "O2", "NO", "NOx", "SO2"};
    static final double PHYS_NORM_VOLUME = 22.41; //m3/mol
    static final double[] MOLAR_FACTOR = {28.0, PHYS_NORM_VOLUME, 
        PHYS_NORM_VOLUME, 30.0, 46.0, 64.0};
    
    static final String REGEX_TIME = "(\\d{1,2}).(\\d{1,2})";
    static final String REGEX = "(\\d{4}-\\d{2}-\\d{2}) "
            + "(\\d{2}:\\d{2}:\\d{2})\\t"
            + "(-?\\d*.\\d{4})\\t"
            + "(-?\\d*.\\d{4})\\t"
            + "(-?\\d*.\\d{4})\\t"
            + "(-?\\d*.\\d{4})\\t"
            + "(-?\\d*.\\d{4})\\t"
            + "(-?\\d*.\\d{4})\\t"
            + "(-?\\d*.\\d{4})\\t";
    
    static final String ENCODING = "UTF-8";
    static final String CONFIG_FILE = "config.ini";
}
