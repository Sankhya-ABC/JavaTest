package br.com.sankhya.agendalocacao.actions;

import br.com.sankhya.agendalocacao.services.CabServices;
import br.com.sankhya.extensions.actionbutton.AcaoRotinaJava;
import br.com.sankhya.extensions.actionbutton.ContextoAcao;
import br.com.sankhya.extensions.actionbutton.Registro;
import br.com.sankhya.modelcore.PlatformService;
import br.com.sankhya.modelcore.PlatformServiceFactory;
import br.com.sankhya.ws.ServiceContext;

import javax.servlet.ServletContext;
import java.math.BigDecimal;

public class TransfereAtivoAction implements AcaoRotinaJava {
    CabServices cabServices = new CabServices();
    @Override
    public void doAction(ContextoAcao contexto) throws Exception {
        Registro[] registros = contexto.getLinhas();
        for(Registro registro : registros){
            System.out.println("CHAMA TRANSFERE ATIVO");
            System.out.println("CODPROD: " + registro.getCampo("CODPROD"));
            System.out.println("CODBEM: " + registro.getCampo("CODBEM"));
            System.out.println("NUNOTA: " + registro.getCampo("NUNOTA"));
            System.out.println("CODPARC: " + registro.getCampo("CODPARC"));
            cabServices.enviaRemessa((BigDecimal) registro.getCampo("CODPROD"),
                    (String) registro.getCampo("CODBEM"),
                    (BigDecimal) registro.getCampo("NUNOTA"), (BigDecimal) registro.getCampo("SEQUENCIA"));
        }


    }
}
