package br.com.sankhya.agendalocacao.actions;

import br.com.sankhya.agendalocacao.controllers.GerarRemessa;
import br.com.sankhya.extensions.actionbutton.AcaoRotinaJava;
import br.com.sankhya.extensions.actionbutton.ContextoAcao;
import br.com.sankhya.extensions.actionbutton.Registro;

import java.math.BigDecimal;

public class GerarRetorno implements AcaoRotinaJava {
    GerarRemessa gerarRemessa = new GerarRemessa();
    @Override
    public void doAction(ContextoAcao contexto) throws Exception {
        Registro[] registros = contexto.getLinhas();
        for(Registro registro : registros){
            BigDecimal nunotaRem = (BigDecimal) registro.getCampo("NUNOTA");
            BigDecimal sequencia = (BigDecimal) registro.getCampo("SEQUENCIA");

            gerarRemessa.geraRetorno((BigDecimal) registro.getCampo("NUMCONTRATO"),
                    (BigDecimal) registro.getCampo("CODPARC"),
                    (String) registro.getCampo("CODBEM"),
                    (BigDecimal) registro.getCampo("CODPROD"),
                    (BigDecimal) registro.getCampo("CODEMP"),
                    nunotaRem,
                    (BigDecimal) registro.getCampo("SEQUENCIA"));
        }
    }
}
