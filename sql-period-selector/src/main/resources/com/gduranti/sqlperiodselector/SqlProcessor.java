package com.gduranti.sqlperiodselector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;

import com.gduranti.sqlperiodselector.connection.ConnectionProperties;
import com.gduranti.sqlperiodselector.connection.BDConnector;
import com.gduranti.sqlperiodselector.period.DateTimeFormatterFactory;
import com.gduranti.sqlperiodselector.period.Period;
import com.gduranti.sqlperiodselector.report.Report;
import com.gduranti.sqlperiodselector.report.ReportAcumulator;

public class SqlProcessor {

    private ConnectionProperties connectionProperties;
    private Connection connection;
    private PreparedStatement preparedStatement;

    public SqlProcessor(ConnectionProperties connectionProperties) {
        this.connectionProperties = connectionProperties;
    }

    public Report execute(String query, Iterator<Period> periods, ReportAcumulator reportAcumulator) throws Exception {
        try {
            initConnection(query);
            periods.forEachRemaining(p -> process(p, reportAcumulator));
            return reportAcumulator.buildReport();
        } finally {
            close(preparedStatement);
            close(connection);
        }
    }

    private void initConnection(String query) throws SQLException {
        connection = BDConnector.getInstance(connectionProperties);
        preparedStatement = connection.prepareStatement(query);
    }

    private void close(AutoCloseable closeable) throws Exception {
        if (closeable != null) {
            closeable.close();
        }
    }

    private void process(Period period, ReportAcumulator reportAcumulator) {

        System.out.println("Processing period " + period.toString());

        try {
            DateTimeFormatter samdFormatter = DateTimeFormatterFactory.createSamdFormatter();
            preparedStatement.setInt(1, new Integer(period.getStart().format(samdFormatter)));
            preparedStatement.setInt(2, new Integer(period.getEnd().format(samdFormatter)));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                reportAcumulator.addResult(resultSet);
            }
            resultSet.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
