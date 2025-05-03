package br.com.sankhya.agendalocacao.utils;


import br.com.sankhya.agendalocacao.model.ModelCab;
import br.com.sankhya.agendalocacao.model.ModelIte;
import com.sankhya.util.XMLUtils;
import org.apache.pdfbox.util.XMLUtil;
import org.jdom.Element;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

public class RequestUtils {
    Utils utils = new Utils();
    String[] events = {
            "br.com.sankhya.comercial.recalcula.pis.cofins",
            "br.com.sankhya.actionbutton.clientconfirm",
            "br.com.sankhya.recebimento.com.cartao.antes.aprovacao.sefaz",
            "br.com.sankhya.aprovar.nota.apos.recebimento.cartao",
            "br.com.sankhya.aprovar.nota.apos.baixa",
            "br.com.sankhya.exibir.variacao.valor.item",
            "br.com.sankhya.mgecom.compra.SolicitacaoComprador",
            "br.com.sankhya.mgecom.valida.ChaveNFeCompraTerceiros",
            "br.com.sankhya.mgecom.expedicao.SolicitarUsuarioConferente",
            "br.com.sankhya.mgecom.nota.adicional.SolicitarUsuarioGerente",
            "br.com.sankhya.mgecomercial.event.cadastrarDistancia",
            "br.com.sankhya.mgecomercial.event.baixaPortal",
            "br.com.sankhya.mgecomercial.event.faturamento.confirmacao",
            "br.com.sankhya.mgecomercial.event.compensacao.credito.debito",
            "br.com.utiliza.dtneg.servidor",
            "br.com.sankhya.mgefin.solicitacao.liberacao.orcamento",
            "br.com.sankhya.exibe.msg.variacao.preco",
            "br.com.sankhya.importacaoxml.cfi.para.produto",
            "br.com.sankhya.mgecom.parcelas.financeiro",
            "br.com.sankhya.mgecom.cancelamento.notas.remessa",
            "br.com.sankhya.comercial.solicitaContingencia",
            "br.com.sankhya.modelcore.comercial.centrais.alteracao.pedido.venda.mais",
            "br.com.sankhya.modelcore.comercial.centrais.alteracao.pedido.venda.mais.sem.limite",
            "central.save.grade.itens.mostrar.popup.serie",
            "central.save.grade.itens.mostrar.popup.info.lote",
            "br.com.sankhya.mgecom.central.itens.VendaCasada",
            "br.com.sankhya.exclusao.gradeProduto",
            "br.com.sankhya.mgecomercial.event.estoque.componentes",
            "br.com.sankhya.mgecomercial.event.estoque.insuficiente.produto",
            "br.com.sankhya.mgecom.central.itens.KitRevenda",
            "br.com.sankhya.mgecom.central.itens.KitRevenda.msgValidaFormula",
            "br.com.sankhya.mgecom.imobilizado",
            "br.com.sankhya.mgecom.item.multiplos.componentes.servico",
            "br.com.sankhya.mgecom.coleta.entrega.recalculado",
            "br.com.sankhya.central.alteracao.moeda.cabecalho",
            "br.com.sankhya.mgecom.event.troca.item.por.produto.substituto",
            "br.com.sankhya.mgecom.event.troca.item.por.produto.alternativo",
            "br.com.sankhya.modelcore.comercial.centrais.alteracao.pedido.venda.mais.item",
            "br.com.sankhya.modelcore.comercial.centrais.alteracao.pedido.venda.mais.item.sem.limite",
            "br.com.sankhya.mgeprod.producao.terceiro.inclusao.item.nota"
    };
    public Element montaRequest(BigDecimal codProd, String codBem, BigDecimal nunotaGerado, String atualBem) {
       Element requestBody = new Element("requestBody");
        Element imobilizado = new Element("IMOBILIZADO");
        Element records = new Element("RECORDS");
        XMLUtils.addAttributeElement(records,"DTINIC", utils.formatTimestampToDateStr(new Timestamp(System.currentTimeMillis())));
        XMLUtils.addAttributeElement(records,"CODPROD", codProd.toString());
        XMLUtils.addAttributeElement(records,"NUNOTA", nunotaGerado.toString());
        XMLUtils.addAttributeElement(records,"SEQUENCIA", "1");
        XMLUtils.addAttributeElement(records,"ATUALBEM", atualBem);

        Element row = new Element("row");
        XMLUtils.addAttributeElement(row,"CODBEM", codBem);
        XMLUtils.addAttributeElement(row,"DESCRBEM", "");
        XMLUtils.addAttributeElement(row,"DESCRABREV", "");
        XMLUtils.addAttributeElement(row,"CODEPTO", "0");
        XMLUtils.addAttributeElement(row,"ORDEM", "1");
        XMLUtils.addAttributeElement(row,"BEMNOVO", "true");



        records.addContent(row);
        imobilizado.addContent(records);

        requestBody.addContent(imobilizado);
    /*    Element clientEventList = new Element("clientEventList");
        for (String event : events) {
            Element clientEvent = new Element("clientEvent").setText(event);
            clientEventList.addContent(clientEvent);
        }
        requestBody.addContent(clientEventList);*/
        return requestBody;

    }

    public Element montaConfirmaNota(BigDecimal nunota){
        Element requestBody = new Element("requestBody");
        Element nota = new Element("nota");
        XMLUtils.addAttributeElement(nota,"confirmacaoCentralNota", "true");
        XMLUtils.addAttributeElement(nota,"ehPedidoWeb", "false");
        XMLUtils.addAttributeElement(nota,"atualizaPrecoItemPedCompra", "false");
        XMLUtils.addAttributeElement(nota,"ownerServiceCall", "CentralNotas_CentralNotas_1");
        XMLUtils.addAttributeElement(nota,"visAutOcorrencias", "true");
        XMLUtils.addContentElement(nota,"NUNOTA",nunota.toString());
        requestBody.addContent(nota);
        return requestBody;
    }
    public Element buildNotasElement(BigDecimal codTipOper, BigDecimal nota, BigDecimal codEMp) {
        Element requestBody = new Element("requestBody");
        Element notasElem = new Element("notas");

        XMLUtils.addAttributeElement(notasElem, "codTipOper", codTipOper.toString());

        XMLUtils.addAttributeElement(notasElem, "dtFaturamento", "");
        XMLUtils.addAttributeElement(notasElem, "dtSaida", "");
        XMLUtils.addAttributeElement(notasElem, "hrSaida", "");
        XMLUtils.addAttributeElement(notasElem, "tipoFaturamento", "FaturamentoNormal");
        XMLUtils.addAttributeElement(notasElem, "dataValidada", "true");
        XMLUtils.addAttributeElement(notasElem, "umaNotaParaCada", "false");
        XMLUtils.addAttributeElement(notasElem, "confirmarNota", "true");
        XMLUtils.addAttributeElement(notasElem, "serie", "1");
        XMLUtils.addAttributeElement(notasElem,"codEmp", codEMp.toString());
        XMLUtils.addAttributeElement(notasElem,"ehWizardFaturamento", "true");
        XMLUtils.addAttributeElement(notasElem,"ehPedidoWeb", "false");
        XMLUtils.addAttributeElement(notasElem,"nfeDevolucaoViaRecusa", "false");

        Element notasComMoedaElem = new Element("notasComMoeda");
        XMLUtils.addAttributeElement(notasComMoedaElem, "valorMoeda", "undefined");
        notasElem.addContent(notasComMoedaElem);
        Element notaElem = new Element("nota");
        notaElem.addContent(nota.toString());
        notasElem.addContent(notaElem);
        requestBody.addContent(notasElem);
        return requestBody;
    }


    public Element montaBem(String codBem, BigDecimal codProd){
        Element requestBody = new Element("requestBody");
        Element bem = new Element("BEM");
        bem.addContent(new Element("CODBEM").setText(codBem));
        bem.addContent(new Element("CODPROD").setText(codProd.toString()));
        requestBody.addContent(bem);
        return requestBody;
    }

}
