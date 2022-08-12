package br.com.fiap.fmba.model;


import java.util.Objects;

public class OrdemServicoVO {

    private int codigo;
    private String dataInicio;
    private String dataFinal;
    private String nomeCliente;
    private String veiculo;
    private String placa;

    public OrdemServicoVO() {
        super();
    }

    public OrdemServicoVO(int codigo) {
        super();
        this.codigo = codigo;
    }

    public OrdemServicoVO(int codigo, String dataInicio, String dataFinal, String nomeCliente, String veiculo, String placa) {
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

    // ---- Geters and Setter

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
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
