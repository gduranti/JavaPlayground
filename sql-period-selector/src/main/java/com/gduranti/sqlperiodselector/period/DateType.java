package com.gduranti.sqlperiodselector.period;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalTime;
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
            preparedStatement.setTimestamp(1, Timestamp.valueOf(period.getStart().atStartOfDay()));
            preparedStatement.setTimestamp(2, Timestamp.valueOf(period.getEnd().atTime(LocalTime.MAX)));
        }
    };

    public abstract void addParameters(PreparedStatement preparedStatement, Period period) throws NumberFormatException, SQLException;

}
