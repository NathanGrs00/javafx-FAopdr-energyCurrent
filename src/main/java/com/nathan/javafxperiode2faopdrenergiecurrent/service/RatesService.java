package com.nathan.javafxperiode2faopdrenergiecurrent.service;

import com.nathan.javafxperiode2faopdrenergiecurrent.dao.RatesDAO;
import com.nathan.javafxperiode2faopdrenergiecurrent.model.Rates;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RatesService {
    private RatesDAO ratesDAO;

    public RatesService(){
        this.ratesDAO = new RatesDAO();
    }

    //Passing to database class.
    public void saveRates(int customerId, double rateCurrent, double rateGas) {
        Rates rate = new Rates(customerId, rateCurrent, rateGas);
        ratesDAO.addRate(rate);
    }

    public void updateRates(int customerId, double rateCurrent, double rateGas) {
        ratesDAO.updateRate(customerId, rateCurrent, rateGas);
    }

    public Rates getRates(){
        ResultSet allRates = ratesDAO.getRates();
        Rates rates = null;
        try{
            while (allRates.next()){
                // Putting the database data in Model format.
                int id = allRates.getInt("id");
                Double current_rate = allRates.getDouble("current_rate");
                Double gas_rate = allRates.getDouble("gas_rate");
                rates = new Rates(id, current_rate, gas_rate);
            }
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
        return rates;
    }

    public boolean hasRates(int customerId) {
        return ratesDAO.checkHasRates(customerId);
    }
}
