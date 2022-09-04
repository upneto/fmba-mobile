package br.com.fiap.fmba.usecase.ordemservico;


import java.util.Objects;

public class OrdemServico {

    private String dataInicio;
    private String dataFinal;
    private String nomeCliente;
    private String veiculo;
    private String placa;

    public OrdemServico() {
        super();
    }

    public OrdemServico(String placa) {
        super();
        this.placa = placa;
    }

    public OrdemServico(String dataInicio, String dataFinal, String nomeCliente, String veiculo, String placa) {
        super();
        this.dataInicio = dataInicio;
        this.dataFinal = dataFinal;
        this.nomeCliente = nomeCliente;
        this.veiculo = veiculo;
        this.placa = placa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrdemServico that = (OrdemServico) o;
        return placa.equals(that.placa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(placa);
    }

    // ---- Getters and Setter

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(String dataFinal) {
        this.dataFinal = dataFinal;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(String veiculo) {
        this.veiculo = veiculo;
    }

    public String getPlaca() {
        return placa != null ? placa.toUpperCase() : placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }
}
