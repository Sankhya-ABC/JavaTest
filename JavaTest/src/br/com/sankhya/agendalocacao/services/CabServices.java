package br.com.sankhya.agendalocacao.services;


import br.com.sankhya.agendalocacao.model.CabRetorno;
import br.com.sankhya.agendalocacao.model.ModelCab;
import br.com.sankhya.agendalocacao.repository.BemRepository;
import br.com.sankhya.agendalocacao.repository.CabRepository;
import br.com.sankhya.agendalocacao.repository.IteRepository;
import br.com.sankhya.agendalocacao.repository.RemRepository;
import br.com.sankhya.agendalocacao.sankhyaServices.RequestCACSP;
import br.com.sankhya.agendalocacao.model.ModelIte;
import br.com.sankhya.agendalocacao.model.ModelLis;
import br.com.sankhya.agendalocacao.utils.RequestUtils;
import br.com.sankhya.mgecomercial.model.centrais.cac.CACSPBean;
import br.com.sankhya.modelcore.comercial.EstoqueHelpper;
import br.com.sankhya.modelcore.comercial.centrais.CACHelper;

import java.math.BigDecimal;
import java.util.List;

public class CabServices {
    private final CabRepository cabRepository = new CabRepository();
    private final IteRepository iteRepository = new IteRepository();
    private final RequestCACSP requestCACSP = new RequestCACSP();
    private final RequestUtils requestUtils = new RequestUtils();
    private final BemRepository bemRepository = new BemRepository();
    private final RemRepository remRepository = new RemRepository();

    public void geraRemessa(ModelLis modelLis, BigDecimal codUsu, BigDecimal sequencia) throws Exception {
        BigDecimal nunotaModeloRemessa = cabRepository.getNunotaModeloRem();
        System.out.println("NUNOTA MODELO REMESSA: " + nunotaModeloRemessa);
        System.out.println("CODPARC: " + modelLis.getCodParc());
        System.out.println("CODPROD: " + modelLis.getCodProd());
        System.out.println("CODBEM: " + modelLis.getCodBem());
        System.out.println("CODCONTRATO: " + modelLis.getNumContrato());

        ModelCab modelCab = cabRepository.getCabInfo(nunotaModeloRemessa, modelLis.getNumContrato(), modelLis.getCodParc(), modelLis.getCodEmp());
        CabRetorno cabRetorno = cabRepository.insertCab(modelCab);
        ModelIte modelIte = iteRepository.montaModelIte(modelLis.getCodProd());
        BigDecimal nunotaGerado =  cabRetorno.getNunota();
        iteRepository.insertIte(modelIte, nunotaGerado);

        try {
            Thread.sleep(3000);
          /*  requestCACSP.enviaAtivo(requestUtils.montaRequest(modelIte.getCodProd(), modelLis.getCodBem(), nunotaGerado, "T"));*/
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
          /*  requestCACSP.confirmaNota(requestUtils.montaConfirmaNota(nunotaGerado));*/
            remRepository.atualizaRem(nunotaGerado, sequencia, "2");
        }
    }

    public void enviaRemessa(BigDecimal codProd, String codBem, BigDecimal nunota, BigDecimal sequencia) throws Exception {
        requestCACSP.enviaAtivo(requestUtils.montaRequest(codProd, codBem, nunota, "T"));
        System.out.println("ENVIA ATIVO");
        remRepository.atualizaStatus("3", sequencia);
    }

    public void confirmaNota(BigDecimal nunota) throws Exception {

            requestCACSP.confirmaNota(requestUtils.montaConfirmaNota(nunota));

            remRepository.atualizaStatusTx("4", nunota);

    }



    public void geraRetorno(ModelLis modelLis, BigDecimal nunotaRem, BigDecimal sequencia) throws Exception {
        BigDecimal nunotaModeloRet = cabRepository.getNunotaModeloRet();
      /*  requestCACSP.confirmaNota(requestUtils.montaConfirmaNota(nunotaRem));*/
        BigDecimal nunotaRet = new BigDecimal(0);
        ModelCab modelCab = cabRepository.getCabInfo(nunotaModeloRet, modelLis.getNumContrato(), modelLis.getCodParc(), modelLis.getCodEmp());


        requestCACSP.faturaNota(requestUtils.buildNotasElement(modelCab.getCodTipoper(), nunotaRem, modelLis.getCodEmp()));

        try {
            Thread.sleep(1000);
            nunotaRet = cabRepository.getNunotaGerada(nunotaRem);
        } catch (Exception e) {
            e.printStackTrace();
        }
        requestCACSP.enviaAtivo(requestUtils.montaRequest(modelLis.getCodProd(), modelLis.getCodBem(), nunotaRet, "D"));
        requestCACSP.confirmaNota(requestUtils.montaConfirmaNota(nunotaRet));
        remRepository.atualizaRemTx(nunotaRet, sequencia, "5");
    }

    public void transfereBem(ModelLis modelLis, BigDecimal nunota, BigDecimal codUsu) {
        bemRepository.updateTCIBEM(modelLis.getNumContrato(), nunota, modelLis.getCodBem(), modelLis.getCodProd());
        bemRepository.insertIBE(modelLis.getCodBem(), modelLis.getCodProd(), nunota);
        bemRepository.insertLOC(modelLis.getCodBem(), modelLis.getCodEmp(), modelLis.getCodProd(), codUsu, nunota);
    }

}
