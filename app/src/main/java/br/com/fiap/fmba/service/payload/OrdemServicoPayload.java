package br.com.fiap.fmba.service.payload;

import java.io.Serializable;
import java.math.BigInteger;

public class OrdemServicoPayload implements Serializable  {

    /**
     * Serial
     */
    private static final long serialVersionUID = 6748462382703973190L;

    private long id;
    private String dataCriacao;
    private String dataFinal;
    private String dataInicio;
    private int status;
    private BigInteger veiculo;
    private BigInteger cliente;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(String dataFinal) {
        this.dataFinal = dataFinal;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public BigInteger getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(BigInteger veiculo) {
        this.veiculo = veiculo;
    }

    public BigInteger getCliente() {
        return cliente;
    }

    public void setCliente(BigInteger cliente) {
        this.cliente = cliente;
    }
}
