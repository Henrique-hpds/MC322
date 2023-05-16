import java.util.Date;

public class ClientePJ extends Cliente {
    private final String cnpj;
    private Date dataFundacao;
    private int quantidadeFuncionarios;
    
    public ClientePJ(String nome, String endereco, String cnpj, Date dataFundacao, int quantidadeFuncionarios){

        super(nome, endereco);
        this.cnpj = cnpj;
        this.dataFundacao = dataFundacao;
        this.quantidadeFuncionarios = quantidadeFuncionarios;
    }

    public String getCnpj(){
        return cnpj;
    }

    public Date getDataFundacao(){
        return dataFundacao;
    }

    public int getQuantidadeFuncionarios(){
        return quantidadeFuncionarios;
    }

    public void setDataFundacao(Date dataFundacao){
        this.dataFundacao = dataFundacao;
    }

    public void setQuantidadeFuncionarios(int quantidadeFuncionarios){
        this.quantidadeFuncionarios = quantidadeFuncionarios;
    }

    @Override
    public double calculaScore(){
        return CalcSeguro.VALOR_BASE.getFator() * (1 + (quantidadeFuncionarios)/100) * getListaVeiculos().size();
    }

    public String toString() {
        return super.toString() + "\n\nCNPJ: " + cnpj + "\nDataFundacao: " + dataFundacao + "\n---------------------------------------";
    }
}