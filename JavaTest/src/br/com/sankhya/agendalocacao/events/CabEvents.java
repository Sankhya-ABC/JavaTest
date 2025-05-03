package br.com.sankhya.agendalocacao.events;

import br.com.sankhya.extensions.eventoprogramavel.EventoProgramavelJava;
import br.com.sankhya.jape.event.PersistenceEvent;
import br.com.sankhya.jape.event.TransactionContext;

public class CabEvents implements EventoProgramavelJava {
    @Override
    public void beforeInsert(PersistenceEvent event) throws Exception {

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
