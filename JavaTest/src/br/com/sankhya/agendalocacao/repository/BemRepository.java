package br.com.sankhya.agendalocacao.repository;

import br.com.sankhya.jape.core.JapeSession;
import br.com.sankhya.jape.wrapper.JapeFactory;
import br.com.sankhya.jape.wrapper.JapeWrapper;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class BemRepository {
    JapeSession.SessionHandle hnd = null;
    public void updateTCIBEM(BigDecimal numContrato, BigDecimal nunotaSaida, String codBem, BigDecimal codProd){
        hnd = JapeSession.open();
        try {
            JapeWrapper bemDAO = JapeFactory.dao("Imobilizado");
            bemDAO.prepareToUpdateByPK(new Object[]{codBem, codProd}).set("NUMCONTRATO", numContrato).set("NUNOTASAIDA",nunotaSaida).update();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JapeSession.close(hnd);
        }
    }
    public void insertIBE(String codBem, BigDecimal codProd, BigDecimal nunota){
        hnd = JapeSession.open();
        try {
            JapeWrapper bemDAO = JapeFactory.dao("BemNotafiscal");
            bemDAO.create().set("CODBEM", codBem).set("CODPROD", codProd).set("NUNOTA", nunota).save();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            JapeSession.close(hnd);
        }
    }
    public void insertLOC(String codBem, BigDecimal codEmp, BigDecimal codProd, BigDecimal codUsu, BigDecimal nunota){
        hnd = JapeSession.open();
        try {
            JapeWrapper bemDAO = JapeFactory.dao("Local");
            bemDAO.create().set("CODBEM", codBem).set("CODEMP", codEmp).set("CODPROD", codProd).set("CODUSU", codUsu).set("NUNOTA", nunota)
                    .set("DTENTRADA", new Timestamp(System.currentTimeMillis())).set("CODDEPTO", BigDecimal.ZERO).set("SEQUENCIA", new BigDecimal(1)).save();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            JapeSession.close(hnd);
        }

    }
}
