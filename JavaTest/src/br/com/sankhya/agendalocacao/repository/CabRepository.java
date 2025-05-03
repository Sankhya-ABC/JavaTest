package br.com.sankhya.agendalocacao.repository;


import br.com.sankhya.agendalocacao.model.CabRetorno;
import br.com.sankhya.agendalocacao.model.ModelCab;
import br.com.sankhya.jape.EntityFacade;
import br.com.sankhya.jape.core.JapeSession;
import br.com.sankhya.jape.dao.JdbcWrapper;
import br.com.sankhya.jape.sql.NativeSql;
import br.com.sankhya.jape.util.JapeSessionContext;
import br.com.sankhya.jape.vo.DynamicVO;
import br.com.sankhya.jape.vo.PrePersistEntityState;
import br.com.sankhya.jape.wrapper.JapeFactory;
import br.com.sankhya.jape.wrapper.JapeWrapper;
import br.com.sankhya.mgecomercial.model.facades.SelecaoDocumentoSPBean;
import br.com.sankhya.modelcore.MGEModelException;
import br.com.sankhya.modelcore.auth.AuthenticationInfo;
import br.com.sankhya.modelcore.comercial.BarramentoRegra;
import br.com.sankhya.modelcore.comercial.ComercialUtils;
import br.com.sankhya.modelcore.comercial.centrais.CACHelper;
import br.com.sankhya.modelcore.util.DynamicEntityNames;
import br.com.sankhya.modelcore.util.EntityFacadeFactory;
import br.com.sankhya.ws.ServiceContext;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Collection;

public class CabRepository {

    public AuthenticationInfo authInfo;
    BigDecimal nuNota = BigDecimal.ZERO;
    BarramentoRegra bRegrasCab = null;
    JapeSession.SessionHandle hnd = null;
    CACHelper cacHelper = new CACHelper();
    NativeSql sql;
    JdbcWrapper jdbcWrapper;
    private ServiceContext sctx;

    public CabRetorno insertCab(ModelCab cab) throws Exception {
        validacoes();
        CabRetorno cabRetorno = new CabRetorno();
        hnd = JapeSession.open();
        try {


            DynamicVO topVO = ComercialUtils.getTipoOperacao(cab.getCodTipoper());
            EntityFacade dwfFacade = EntityFacadeFactory.getDWFFacade();
            DynamicVO cabVO = (DynamicVO) dwfFacade.getDefaultValueObjectInstance("CabecalhoNota");
            cabVO.setProperty("CODTIPOPER", cab.getCodTipoper());
            cabVO.setProperty("TIPMOV", topVO.asString("TIPMOV"));
            cabVO.setProperty("CODEMP", cab.getCodEmp());
            cabVO.setProperty("DTNEG", cab.getDtNeg());
            cabVO.setProperty("CODPARC", cab.getCodParc());
            cabVO.setProperty("CODNAT", cab.getCodNat());
            cabVO.setProperty("CODTIPVENDA", cab.getCodTipVenda());
            cabVO.setProperty("DTALTER", cab.getDtAlter());
            cabVO.setProperty("CODCENCUS", cab.getCodCencus());
            cabVO.setProperty("NUMCONTRATO", cab.getNumContrato());
            JapeSessionContext.putProperty("br.com.sankhya.com.CentralCompraVenda", Boolean.TRUE);
            PrePersistEntityState cabPreState = PrePersistEntityState.build(dwfFacade, "CabecalhoNota", cabVO);
            if (authInfo != null && cabPreState != null) {
                bRegrasCab = cacHelper.incluirAlterarCabecalho(sctx, cabPreState);
                cabRetorno.setBarramentoRegra(bRegrasCab);
            } else {
                System.out.println("algum dos dois estao nulos");
            }
            DynamicVO newCabVO = bRegrasCab.getState().getNewVO();
            nuNota = newCabVO.asBigDecimal("NUNOTA");
            cabRetorno.setNunota(nuNota);

        } catch (Exception e) {
            e.printStackTrace();
            throw new MGEModelException(e.getMessage());
        } finally {
            JapeSession.close(hnd);
        }

        return cabRetorno;
    }

    private void validacoes() {
        try {
            this.sctx = new ServiceContext(null);
            this.authInfo = new AuthenticationInfo("SUP", BigDecimal.ZERO, BigDecimal.ZERO, new Integer(2147483647));
            this.sctx.setAutentication(this.authInfo);
            this.sctx.putHttpSessionAttribute("usuario_logado", this.authInfo.getUserID());
            JapeSessionContext.putProperty("usuario_logado", this.authInfo.getUserID());
            this.sctx.setAttribute("usuario_logado", this.authInfo.getUserID());
            this.sctx.makeCurrent();
        } catch (Exception e) {
            System.out.println("ERRO NA VALIDADE USUARIO");
        }
    }

    public ModelCab getCabInfo(BigDecimal nunota, BigDecimal numContrato, BigDecimal codParc, BigDecimal codEmp) {
        ModelCab modelCab = new ModelCab();
        try {
            hnd = JapeSession.open();
            JapeWrapper cabDAO = JapeFactory.dao(DynamicEntityNames.CABECALHO_NOTA);
            DynamicVO cabVO = cabDAO.findOne("NUNOTA = ?", nunota);
            modelCab.setCodEmp(codEmp);
            modelCab.setCodCencus(cabVO.asBigDecimal("CODCENCUS"));
            modelCab.setCodNat(cabVO.asBigDecimal("CODNAT"));
            modelCab.setCodTipoper(cabVO.asBigDecimal("CODTIPOPER"));
            modelCab.setDtNeg(new Timestamp(System.currentTimeMillis()));
            modelCab.setDtAlter(new Timestamp(System.currentTimeMillis()));
            modelCab.setCodParc(codParc);
            modelCab.setCodTipVenda(cabVO.asBigDecimal("CODTIPVENDA"));
            modelCab.setNumContrato(numContrato);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            JapeSession.close(hnd);
        }

        return modelCab;
    }

    public BigDecimal getNunotaModeloRem() {
        BigDecimal nunota = BigDecimal.ZERO;
        try {
            hnd = JapeSession.open();
            JapeWrapper paramDAO = JapeFactory.dao(DynamicEntityNames.PARAMETRO_SISTEMA);
            Collection<DynamicVO> vo = paramDAO.find("CHAVE = ?", "NOTAMODELOREM");
            for (DynamicVO param : vo) {
                nunota = param.asBigDecimal("INTEIRO");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JapeSession.close(hnd);
        }


        return nunota;
    }

    public BigDecimal getNunotaModeloRet() {
        BigDecimal nunota = BigDecimal.ZERO;
        try {
            hnd = JapeSession.open();
            JapeWrapper paramDAO = JapeFactory.dao(DynamicEntityNames.PARAMETRO_SISTEMA);
            Collection<DynamicVO> vo = paramDAO.find("CHAVE = ?", "NOTAMODELORET");
            for (DynamicVO param : vo) {
                nunota = param.asBigDecimal("INTEIRO");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JapeSession.close(hnd);
        }
        return nunota;
    }


    public BigDecimal getNunotaGerada(BigDecimal nunotaOrig) throws Exception {
        BigDecimal nunota = BigDecimal.ZERO;
        String query = "select * from tgfvar where nunotaorig =" + nunotaOrig;
        jdbcWrapper = JapeFactory.getEntityFacade().getJdbcWrapper();
        sql = new NativeSql(jdbcWrapper);
        ResultSet rs = sql.executeQuery(query);
        while (rs.next()) {
            nunota = rs.getBigDecimal("NUNOTA");
        }

        return nunota;
    }


}
