package com.gduranti.sqlperiodselector;

import com.gduranti.sqlperiodselector.column.ColumnType;
import com.gduranti.sqlperiodselector.period.PeriodIterator;
import com.gduranti.sqlperiodselector.period.PeriodIteratorBuilder;
import com.gduranti.sqlperiodselector.period.SelectStrategy;
import com.gduranti.sqlperiodselector.report.Report;
import com.gduranti.sqlperiodselector.report.ReportAcumulator;
import com.gduranti.sqlperiodselector.vei.VeiSecurityFactory;

public class MainExample {


    public static void main(String... args) throws Exception {

        PeriodIterator periodIterator = PeriodIteratorBuilder.lookupYear(2017)
                                                             .selectingBy(SelectStrategy.MONTH)
                                                             .build();

        ReportAcumulator reportAcumulator = new ReportAcumulator()
                .configColumn("servico", ColumnType.KEY)
                .configColumn("descricao", ColumnType.KEY)
                .configColumn("total", ColumnType.COUNT);

        SqlProcessor sqlProcessor = new SqlProcessor(VeiSecurityFactory.createForDesenvolvimento());
        Report report = sqlProcessor.execute(buildQuery(), periodIterator, reportAcumulator);

        report.print();
    }

    private static String buildQuery() {
        return new StringBuilder()
                .append("select  s.servico as servico, ")
                .append("        s.descricao as descricao, ")
                .append("        count(*) total ")
                .append("   from tab_servicos_auditoria a ")
                .append("  inner join tab_tipo_servico s on s.servico = a.servico ")
                .append("  where a.samd_trans between ? and ? ")
                .append("  group by s.servico, ")
                .append("           s.descricao ")
                .append("  order by s.servico ")
                .toString();
    }

}
