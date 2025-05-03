package br.com.sankhya.agendalocacao.repository;

import br.com.sankhya.jape.core.JapeSession;
import br.com.sankhya.jape.wrapper.JapeFactory;
import br.com.sankhya.jape.wrapper.JapeWrapper;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class RemRepository {
    JapeSession.SessionHandle hnd;
    public void atualizaRem(BigDecimal nunotaRem, BigDecimal sequencia, String status){
        hnd = JapeSession.open();
        try{
            JapeWrapper remDAO = JapeFactory.dao("AD_GERAREMRET");
            remDAO.prepareToUpdateByPK(sequencia).set("NUNOTA", nunotaRem).set("STATUS", status).set("DTENVIO", new Timestamp(System.currentTimeMillis())).update();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JapeSession.close(hnd);
        }
    }
    public void atualizaStatus(String status, BigDecimal sequencia){
        hnd = JapeSession.open();
        try{
            JapeWrapper remDAO = JapeFactory.dao("AD_GERAREMRET");
            remDAO.prepareToUpdateByPK(sequencia).set("STATUS", status).update();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JapeSession.close(hnd);
        }
    }
    public void atualizaStatusTx(String status, BigDecimal sequencia){
        hnd = JapeSession.open();
        try{
            hnd.execWithTX(new JapeSession.TXBlock() {
                @Override
                public void doWithTx() throws Exception {
                    JapeWrapper remDAO = JapeFactory.dao("AD_GERAREMRET");
                    remDAO.prepareToUpdateByPK(sequencia).set("STATUS", status).update();
                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JapeSession.close(hnd);
        }
    }

    public void atualizaRemTx(BigDecimal nunotaRem, BigDecimal sequencia, String status){
        hnd = JapeSession.open();
        try{
            hnd.execWithTX(new JapeSession.TXBlock() {
                @Override
                public void doWithTx() throws Exception {
                    JapeWrapper remDAO = JapeFactory.dao("AD_GERAREMRET");
                    remDAO.prepareToUpdateByPK(sequencia).set("NUNOTARET", nunotaRem).set("STATUS", status).set("DTRETORNO", new Timestamp(System.currentTimeMillis())).update();
                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JapeSession.close(hnd);
        }
    }

}
