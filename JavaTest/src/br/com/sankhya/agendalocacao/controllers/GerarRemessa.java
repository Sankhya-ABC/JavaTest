package br.com.sankhya.agendalocacao.controllers;



import br.com.sankhya.agendalocacao.model.ModelLis;
import br.com.sankhya.agendalocacao.services.CabServices;
import java.math.BigDecimal;


public class GerarRemessa {
    private CabServices cabServices = new CabServices();
    public void geraRemessa(BigDecimal numContrato, BigDecimal codParc, String codBem, BigDecimal codProd, BigDecimal codEmp, BigDecimal codUsu, BigDecimal sequencia) throws Exception {
        ModelLis modelLis = new ModelLis();
        modelLis.setCodEmp(codEmp);
        modelLis.setCodProd(codProd);
        modelLis.setCodParc(codParc);
        modelLis.setNumContrato(numContrato);
        modelLis.setCodBem(codBem);
        cabServices.geraRemessa(modelLis, codUsu, sequencia);
    }
    public void geraRemessaBens(BigDecimal numContrato, BigDecimal codParc, String codBem, BigDecimal codProd, BigDecimal codEmp, BigDecimal codUsu, BigDecimal sequencia, BigDecimal nunota) throws Exception {
        ModelLis modelLis = new ModelLis();
        modelLis.setCodEmp(codEmp);
        modelLis.setCodProd(codProd);
        modelLis.setCodParc(codParc);
        modelLis.setNumContrato(numContrato);
        modelLis.setCodBem(codBem);
        cabServices.geraRemessa(modelLis, codUsu, sequencia);
    }
    public void geraRetorno(BigDecimal numContrato, BigDecimal codParc, String codBem, BigDecimal codProd, BigDecimal codEmp, BigDecimal nunotaRem, BigDecimal sequencia) throws Exception {
        ModelLis modelLis = new ModelLis();
        modelLis.setCodEmp(codEmp);
        modelLis.setCodProd(codProd);
        modelLis.setCodParc(codParc);
        modelLis.setNumContrato(numContrato);
        modelLis.setCodBem(codBem);
        cabServices.geraRetorno(modelLis, nunotaRem, sequencia);
    }

}
