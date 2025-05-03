package br.com.sankhya.agendalocacao.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class ModelLis {

     BigDecimal numContrato;
     BigDecimal codParc;
     String codBem;
     Timestamp dtEnvio;
     Timestamp dtRetorno;
     BigDecimal nunotaRemessa;
     BigDecimal nunotaRetorno;
     BigDecimal codProd;
     BigDecimal codEmp;

    public BigDecimal getCodEmp() {
        return codEmp;
    }

    public void setCodEmp(BigDecimal codEmp) {
        this.codEmp = codEmp;
    }

    public BigDecimal getCodProd() {
        return codProd;
    }

    public void setCodProd(BigDecimal codProd) {
        this.codProd = codProd;
    }

    public BigDecimal getNumContrato() {
        return numContrato;
    }

    public void setNumContrato(BigDecimal numContrato) {
        this.numContrato = numContrato;
    }

    public BigDecimal getCodParc() {
        return codParc;
    }

    public void setCodParc(BigDecimal codParc) {
        this.codParc = codParc;
    }

    public String getCodBem() {
        return codBem;
    }

    public void setCodBem(String codBem) {
        this.codBem = codBem;
    }

    public Timestamp getDtEnvio() {
        return dtEnvio;
    }

    public void setDtEnvio(Timestamp dtEnvio) {
        this.dtEnvio = dtEnvio;
    }

    public Timestamp getDtRetorno() {
        return dtRetorno;
    }

    public void setDtRetorno(Timestamp dtRetorno) {
        this.dtRetorno = dtRetorno;
    }

    public BigDecimal getNunotaRemessa() {
        return nunotaRemessa;
    }

    public void setNunotaRemessa(BigDecimal nunotaRemessa) {
        this.nunotaRemessa = nunotaRemessa;
    }

    public BigDecimal getNunotaRetorno() {
        return nunotaRetorno;
    }

    public void setNunotaRetorno(BigDecimal nunotaRetorno) {
        this.nunotaRetorno = nunotaRetorno;
    }


}
