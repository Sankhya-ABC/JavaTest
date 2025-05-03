package br.com.sankhya.agendalocacao.actions;


import br.com.sankhya.agendalocacao.controllers.GerarRemessa;
import br.com.sankhya.extensions.actionbutton.AcaoRotinaJava;
import br.com.sankhya.extensions.actionbutton.ContextoAcao;
import br.com.sankhya.extensions.actionbutton.Registro;

import java.math.BigDecimal;

public class GeraRemessaAction implements AcaoRotinaJava {


    @Override
    public void doAction(ContextoAcao contexto) throws Exception {
        GerarRemessa gerarRemessa = new GerarRemessa();
        Registro[] registros = contexto.getLinhas();
        for(Registro registro : registros){
            gerarRemessa.geraRemessa((BigDecimal) registro.getCampo("NUMCONTRATO"),
                    (BigDecimal) registro.getCampo("CODPARC"),
                    (String) registro.getCampo("CODBEM"),
                    (BigDecimal) registro.getCampo("CODPROD"),
                    (BigDecimal) registro.getCampo("CODEMP"),
                    contexto.getUsuarioLogado(), (BigDecimal) registro.getCampo("SEQUENCIA"));
        }


    }
}
