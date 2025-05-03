package br.com.sankhya.agendalocacao.actions;


import br.com.sankhya.extensions.actionbutton.AcaoRotinaJava;
import br.com.sankhya.extensions.actionbutton.ContextoAcao;
import br.com.sankhya.jape.core.JapeSession;
import br.com.sankhya.jape.dao.JdbcWrapper;
import br.com.sankhya.jape.sql.NativeSql;
import br.com.sankhya.jape.wrapper.JapeFactory;


public class Actions implements AcaoRotinaJava {
    NativeSql sql;
    JdbcWrapper jdbcWrapper;
    JapeSession.SessionHandle hnd;
    @Override
    public void doAction(ContextoAcao contexto) throws Exception {
        jdbcWrapper = JapeFactory.getEntityFacade().getJdbcWrapper();
        sql = new NativeSql(jdbcWrapper);
        String deleteQuery = "DELETE FROM TMEORL WHERE CODORL > 0";
        sql.executeQuery(deleteQuery);
    }
}
