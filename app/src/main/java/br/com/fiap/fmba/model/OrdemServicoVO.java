package br.com.fiap.fmba.model;


import java.util.Objects;

public class OrdemServicoVO {

    private long codigo;
    private String dataInicio;
    private String dataFinal;
    private String nomeCliente;
    private String veiculo;
    private String placa;

    public OrdemServicoVO() {
        super();
    }

    public OrdemServicoVO(long codigo) {
        super();
        this.codigo = codigo;
    }

    public OrdemServicoVO(long codigo, String dataInicio, String dataFinal, String nomeCliente, String veiculo, String placa) {
        super();
        this.codigo = codigo;
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
        OrdemServicoVO that = (OrdemServicoVO) o;
        return codigo == that.codigo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    // ---- Getters and Setter

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

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
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }
}
