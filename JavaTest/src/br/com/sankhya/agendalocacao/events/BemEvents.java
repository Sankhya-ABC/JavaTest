package br.com.sankhya.agendalocacao.events;

import br.com.sankhya.extensions.eventoprogramavel.EventoProgramavelJava;
import br.com.sankhya.jape.event.PersistenceEvent;
import br.com.sankhya.jape.event.TransactionContext;
import br.com.sankhya.jape.vo.DynamicVO;

public class BemEvents implements EventoProgramavelJava {
    @Override
    public void beforeInsert(PersistenceEvent event) throws Exception {
        DynamicVO vo = (DynamicVO) event.getVo();
        if(vo.asString("ATUALBEM")== null){
            vo.setProperty("ATUALBEM", "T");
        }
    }

    @Override
    public void beforeUpdate(PersistenceEvent event) throws Exception {

    }

    @Override
    public void beforeDelete(PersistenceEvent event) throws Exception {

    }

    @Override
    public void afterInsert(PersistenceEvent event) throws Exception {

    }

    @Override
    public void afterUpdate(PersistenceEvent event) throws Exception {

    }

    @Override
    public void afterDelete(PersistenceEvent event) throws Exception {

    }

    @Override
    public void beforeCommit(TransactionContext tranCtx) throws Exception {

    }
}
