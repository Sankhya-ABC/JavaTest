package br.com.sankhya.agendalocacao.model;

import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;

public class OrdemLocacaoModel {

    private BigDecimal codEmp;
    private BigDecimal codParc;
    private String codBem;
    private String motorista;
    private String local;
    private String placa;
    private Timestamp dtInicio;
    private Timestamp dtFinal;
    private String nomeEmp;
    private String nomeParc;
    private Timestamp dtLocacao;
    private String tipoReg;
    private BigDecimal numContrato;
    private BigDecimal codLoc;
    private BigDecimal codProd;

    public BigDecimal getCodProd() {
        return codProd;
    }

    public void setCodProd(BigDecimal codProd) {
        this.codProd = codProd;
    }

    public BigDecimal getCodLoc() {
        return codLoc;
    }

    public void setCodLoc(BigDecimal codLoc) {
        this.codLoc = codLoc;
    }

    public BigDecimal getNumContrato() {
        return numContrato;
    }

    public void setNumContrato(BigDecimal numContrato) {
        this.numContrato = numContrato;
    }

    public Timestamp getDtLocacao() {
        return dtLocacao;
    }

    public void setDtLocacao(Timestamp dtLocacao) {
        this.dtLocacao = dtLocacao;
    }

    public String getTipoReg() {
        return tipoReg;
    }

    public void setTipoReg(String tipoReg) {
        this.tipoReg = tipoReg;
    }

    public BigDecimal getCodEmp() {
        return codEmp;
    }

    public void setCodEmp(BigDecimal codEmp) {
        this.codEmp = codEmp;
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

    public String getMotorista() {
        return motorista;
    }

    public void setMotorista(String motorista) {
        this.motorista = motorista;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Timestamp getDtInicio() {
        return dtInicio;
    }

    public void setDtInicio(Timestamp dtInicio) {
        this.dtInicio = dtInicio;
    }

    public Timestamp getDtFinal() {
        return dtFinal;
    }

    public void setDtFinal(Timestamp dtFinal) {
        this.dtFinal = dtFinal;
    }

    public String getNomeEmp() {
        return nomeEmp;
    }

    public void setNomeEmp(String nomeEmp) {
        this.nomeEmp = nomeEmp;
    }

    public String getNomeParc() {
        return nomeParc;
    }

    public void setNomeParc(String nomeParc) {
        this.nomeParc = nomeParc;
    }
}
