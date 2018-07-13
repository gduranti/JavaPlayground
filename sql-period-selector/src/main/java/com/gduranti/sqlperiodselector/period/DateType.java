package com.gduranti.sqlperiodselector.period;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

public enum DateType {

    SAMD {
        @Override
        public void addParameters(PreparedStatement preparedStatement, Period period) throws NumberFormatException, SQLException {
            DateTimeFormatter samdFormatter = DateTimeFormatterFactory.createSamdFormatter();
            preparedStatement.setInt(1, new Integer(period.getStart().format(samdFormatter)));
            preparedStatement.setInt(2, new Integer(period.getEnd().format(samdFormatter)));
        }
    },

    DATE {
        @Override
        public void addParameters(PreparedStatement preparedStatement, Period period) throws SQLException {
            preparedStatement.setDate(1, Date.valueOf(period.getStart()));
            preparedStatement.setDate(2, Date.valueOf(period.getEnd()));
        }
    };

    public abstract void addParameters(PreparedStatement preparedStatement, Period period) throws NumberFormatException, SQLException;

}
