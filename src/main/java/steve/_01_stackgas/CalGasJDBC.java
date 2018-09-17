/*
 * emisszió v3.17.0 @ 2017.3.5
 */
package steve._01_stackgas;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A tanúsított gázminták adatait tartalmazó adatbázis kezelését végző osztály.
 * @author István Ócsai
 */
public class CalGasJDBC {
    
    private final Connection conn;
    private final PreparedStatement findAll;
    private final PreparedStatement findComponent;
    private final PreparedStatement update;
    private final PreparedStatement insert;
    private final PreparedStatement delete;

    public CalGasJDBC(Connection conn) throws SQLException {
        this.conn = conn;
        this.findAll = conn.prepareStatement("SELECT * FROM certificates.cal_gas");
        this.findComponent = conn.prepareStatement
                ("SELECT cal_value FROM certificates.cal_gas WHERE component = ?");
        this.insert = conn.prepareStatement
                ("INSERT INTO certificates.cal_gas "
                        + "(component, cal_value, uncertanity, expire_date)"
                        + " VALUES (?,?,?,?)");
        this.update = conn.prepareStatement
                ("UPDATE certificates.cal_gas SET component = ?, cal_value = ?, "
                        + "uncertanity = ?, expire_date = ? WHERE id = ?");
        this.delete = conn.prepareStatement
                ("DELETE FROM certificates.cal_gas WHERE id = ?");
    }
    
    /**
     * A paramáterként megadott komponens kalibrálási értékét keresi meg.
     * @param comp a keresett komponens.
     * @return az adott komponens kalibrálási értéke.
     * @throws SQLException adatbázishiba lehetséges.
     */
    public double findComponent(String comp) throws SQLException {
        this.findComponent.setString(1, comp);
        return this.findComponent.executeQuery().getDouble(1);
    }
    
    /**
     * Az összes komponens kalibrálási koncentrációját tartalmató gyűjteményt adja
     * meg.
     * A kompensek sorrendje megegyezik az adatbázisbeli sorrenddel.
     * @return az összes komponens kalibrálási koncentrációját tartalmazó gyűjtemény.
     * @throws SQLException adatbázishiba lehetséges.
     */
    public HashMap<Integer, List<String>> findAll() throws SQLException {
        ResultSet all = this.findAll.executeQuery();
        HashMap<Integer, List<String>> result = makeList(all);
        return result;
    }
    
    private HashMap<Integer, List<String>> makeList(ResultSet all) throws SQLException {
        HashMap<Integer, List<String>> result = new HashMap<>();
        while (all.next()) {
            result.put(all.getInt("id"), makeOne(all));
        }
        return result;
    }
    
    private List<String> makeOne(ResultSet all) throws SQLException {
        List<String> sz = new ArrayList<>();
        sz.add(all.getString("component"));
        sz.add(all.getString("cal_value"));
        sz.add(all.getString("uncertanity"));
        //sz.add(all.getDate("expire_date").toLocalDate().toString());
        return sz;
    }
    
    /**
     * A paraméterként megadott komponensre vonatkozó tanúsított gázminta 
     * tulajdonságait lehet megváltoztatni az adatbázisban.
     * @param sz a módosítandó komponens adatok.
     * @throws SQLException adatbázishiba lehetséges.
     */
    public void update(HashMap<String, String> sz) throws SQLException {
        this.update.setString(1, sz.get("component"));
        this.update.setDouble(2, Double.parseDouble(sz.get("cal_value")));
        this.update.setInt(3, Integer.parseInt(sz.get("uncertanity")));
        this.update.setDate(4, Date.valueOf(sz.get("expire_date")));
        this.update.setInt(5, Integer.parseInt(sz.get("id")));
    }
    
    /**
     * Egy új tanúsított anyagmintát helyez el az adatbázisban.
     * @param sz az új anyagminta tulajdonságai.
     * @throws SQLException adatbázishiba lehetséges.
     */
    private void insert(HashMap<String, String> sz) throws SQLException {
        this.insert.setString(1, sz.get("component"));
        this.insert.setDouble(2, Double.parseDouble(sz.get("cal_value")));
        this.insert.setInt(3, Integer.parseInt(sz.get("uncertanity")));
        this.insert.setDate(4, Date.valueOf(sz.get("expire_date")));
    }
    
    /**
     * A megadott azonosítójú tanúsított anyagmintát törli az adatbázisból.
     * @param id a törlendő anyagmint azonosítója.
     * @throws SQLException adatbázishiba lehetséges.
     */
    public void delete(int id) throws SQLException {
        delete.setInt(1, id);
        delete.executeUpdate();
    }
}
