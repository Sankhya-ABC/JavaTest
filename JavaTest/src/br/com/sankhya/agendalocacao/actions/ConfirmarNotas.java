package br.com.sankhya.agendalocacao.actions;

import br.com.sankhya.agendalocacao.services.CabServices;
import br.com.sankhya.extensions.actionbutton.AcaoRotinaJava;
import br.com.sankhya.extensions.actionbutton.ContextoAcao;
import br.com.sankhya.extensions.actionbutton.Registro;

import java.math.BigDecimal;

import static br.com.sankhya.logistica.helper.ControlePneusHelper.confirmaNota;

public class ConfirmarNotas implements AcaoRotinaJava {
    private final CabServices cabServices = new CabServices();
    @Override
    public void doAction(ContextoAcao contexto) throws Exception {
        Registro[] registros = contexto.getLinhas();


        for (Registro registro : registros) {
            BigDecimal nunota = (BigDecimal) registro.getCampo("NUNOTA");
           cabServices.confirmaNota(nunota);
        }
    }
}
