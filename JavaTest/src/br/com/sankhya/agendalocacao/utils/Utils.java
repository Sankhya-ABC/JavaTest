package br.com.sankhya.agendalocacao.utils;

import br.com.sankhya.jape.core.JapeSession;
import br.com.sankhya.jape.dao.JdbcWrapper;
import br.com.sankhya.jape.sql.NativeSql;
import br.com.sankhya.jape.vo.DynamicVO;
import br.com.sankhya.jape.wrapper.JapeFactory;
import br.com.sankhya.jape.wrapper.JapeWrapper;
import br.com.sankhya.modelcore.facades.ImpressaoNotasSPBean;
import br.com.sankhya.modelcore.util.DynamicEntityNames;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Utils {
    JapeSession.SessionHandle hnd;
    NativeSql sql;
    JdbcWrapper jdbcWrapper;
    public String findNomeEmp(BigDecimal codEmp) {
        String nomeEmp = "";
        try {
            hnd = JapeSession.open();
            JapeWrapper empresaDAO = JapeFactory.dao(DynamicEntityNames.EMPRESA);
            DynamicVO empresa = empresaDAO.findByPK(codEmp);
            nomeEmp = empresa.asString("NOMEFANTASIA");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JapeSession.close(hnd);
        }
        return nomeEmp;
    }

    public String findNomeParc(BigDecimal codParc) {
        String nomeParc = "";
        try {
            hnd = JapeSession.open();
            JapeWrapper empresaDAO = JapeFactory.dao(DynamicEntityNames.PARCEIRO);
            DynamicVO empresa = empresaDAO.findByPK(codParc);
            nomeParc = empresa.asString("NOMEPARC");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JapeSession.close(hnd);
        }
        return nomeParc;
    }


    public String getInfoTOP(BigDecimal nunota) throws Exception {
        String query = "SELECT ATUALBEM FROM TGFCAB CAB\n" +
                "LEFT JOIN TGFTOP TP ON TP.CODTIPOPER = CAB.CODTIPOPER AND TP.DHALTER = CAB.DHTIPOPER\n" +
                "WHERE NUNOTA = "+nunota;
        jdbcWrapper = JapeFactory.getEntityFacade().getJdbcWrapper();
        sql = new NativeSql(jdbcWrapper);
        String atualBem = "";
        ResultSet rs = sql.executeQuery(query);
        while (rs.next()) {
            atualBem = rs.getString("ATUALBEM");
        }
        return atualBem;
    }


    public BigDecimal validaBemPortal(DynamicVO vo, BigDecimal codParc) throws Exception {
        String query = "WITH DATAS AS (  SELECT\n" +
                "NVL(LOC.DTENTRADA, CON.DTCONTRATO) AS DTINICIO, \n" +
                "NVL(DTTERMINO, DTREFPROXFAT) AS DTFINAL,\n" +
                "LOC.CODBEM\n" +
                "FROM TCILOC LOC\n" +
                "LEFT JOIN TGFCAB CAB ON CAB.NUNOTA = LOC.NUNOTA\n" +
                "LEFT JOIN TCSCON CON ON CON.NUMCONTRATO = CAB.NUMCONTRATO\n" +
                "LEFT JOIN TGFPRO PRO ON PRO.CODPROD = LOC.CODPROD\n" +
                "LEFT JOIN TGFPAR PAR ON PAR.CODPARC = CON.CODPARC\n" +
                "LEFT JOIN TSIEMP EMP ON EMP.CODEMP = CON.CODEMP\n" +
                "                        WHERE LOC.NUNOTA = " + vo.asBigDecimal("NUNOTA") + "\n" +
                "                          AND LOC.CODBEM = '" + vo.asString("CODBEM") +"' \n" +
                "                          AND LOC.CODPROD = " + vo.asBigDecimal("CODPROD") + " \n" +
                ")\n" +
                "SELECT COUNT(*) AS CONTADOR FROM TMEORL \n" +
                "\n" +
                "WHERE DTLOCACAO >=(SELECT MIN(DTINICIO) FROM DATAS) AND DTLOCACAO <=(SELECT MAX(DTFINAL) FROM DATAS)\n" +
                "AND CODBEM = (SELECT MAX(CODBEM) FROM DATAS) AND CODPARC <> "+codParc+" \n";
        jdbcWrapper = JapeFactory.getEntityFacade().getJdbcWrapper();
        sql = new NativeSql(jdbcWrapper);
        BigDecimal contadorBem = new BigDecimal(0);
        ResultSet rs = sql.executeQuery(query);
        while (rs.next()) {
            contadorBem = rs.getBigDecimal("CONTADOR");
        }
        return contadorBem;
    }

    public BigDecimal validaBemReserva(Timestamp dtInicio, Timestamp dtFinal, String codBem) throws Exception {
        String query = "SELECT COUNT(*) AS CONTADOR FROM TMEORL WHERE DTLOCACAO >= '"+this.formatTimestampToDateStr(dtInicio)+"' AND DTLOCACAO <= '"+this.formatTimestampToDateStr(dtFinal)+"' AND CODBEM = '"+codBem+"'";
        jdbcWrapper = JapeFactory.getEntityFacade().getJdbcWrapper();
        sql = new NativeSql(jdbcWrapper);
        BigDecimal contadorBem = new BigDecimal(0);
        ResultSet rs = sql.executeQuery(query);
        try {
            while (rs.next()) {
                contadorBem = rs.getBigDecimal("CONTADOR");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return contadorBem;
    }

    public String getNomeBem(String codBem) throws Exception {
        String nomeBem = "";
        jdbcWrapper = JapeFactory.getEntityFacade().getJdbcWrapper();
        sql = new NativeSql(jdbcWrapper);
        String query = "SELECT DESCRPROD FROM TCICTE CTE LEFT JOIN TGFPRO PRO ON PRO.CODPROD = CTE.CODPROD WHERE CODBEM LIKE '"+codBem+"%'";
        ResultSet rs = sql.executeQuery(query);

        while (rs.next()) {
            nomeBem = rs.getString("DESCRPROD");
        }
        return nomeBem;
    }

    public BigDecimal getCodProd(String codBem) throws Exception {
        BigDecimal codProd = new BigDecimal(0);
        String query = "SELECT CODPROD FROM TCIBEM WHERE CODBEM = '"+codBem+"' ";
        jdbcWrapper = JapeFactory.getEntityFacade().getJdbcWrapper();
        sql = new NativeSql(jdbcWrapper);
        ResultSet rs = sql.executeQuery(query);
        while (rs.next()) {
            codProd = rs.getBigDecimal("CODPROD");
        }
        return codProd;
    }

    public String formatTimestampToDateStr(Timestamp timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(timestamp);
    }
    public String formatTimestampToDateStr2(Timestamp timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(timestamp);
    }


}
