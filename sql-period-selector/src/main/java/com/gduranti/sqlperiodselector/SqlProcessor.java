package com.gduranti.sqlperiodselector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

import com.gduranti.sqlperiodselector.connection.BDConnector;
import com.gduranti.sqlperiodselector.connection.ConnectionProperties;
import com.gduranti.sqlperiodselector.period.DateType;
import com.gduranti.sqlperiodselector.period.Period;
import com.gduranti.sqlperiodselector.report.Report;
import com.gduranti.sqlperiodselector.report.ReportAcumulator;
import com.gduranti.sqlperiodselector.util.Stopwatch;

public class SqlProcessor {

    private ConnectionProperties connectionProperties;
    private Connection connection;
    private PreparedStatement preparedStatement;

    public SqlProcessor(ConnectionProperties connectionProperties) {
        this.connectionProperties = connectionProperties;
    }

    public Report execute(String query, Iterator<Period> periods, ReportAcumulator reportAcumulator) throws Exception {
        return execute(query, periods, reportAcumulator, DateType.SAMD);
    }

    public Report execute(String query, Iterator<Period> periods, ReportAcumulator reportAcumulator, DateType dateType) throws Exception {
        try {
            Stopwatch stopwatch = new Stopwatch();
            stopwatch.start();
            initConnection(query);
            periods.forEachRemaining(p -> process(p, reportAcumulator, dateType));
            stopwatch.stop();
            return reportAcumulator.buildReport(stopwatch.get());
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

    private void process(Period period, ReportAcumulator reportAcumulator, DateType dateType) {
        System.out.println("Processing period " + period.toString());
        try {
            dateType.addParameters(preparedStatement, period);
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
