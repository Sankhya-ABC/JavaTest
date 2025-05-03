package br.com.sankhya.agendalocacao.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class OrdemLocacaoDTO {

    private BigDecimal codEmp;
    private BigDecimal codParc;
    private String codBem;
    private String motorista;
    private String local;
    private String placa;
    private Timestamp dtInicio;
    private Timestamp dtFinal;

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
}
