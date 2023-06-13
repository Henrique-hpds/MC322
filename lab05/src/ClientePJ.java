import java.util.Date;
import java.util.ArrayList;

public class ClientePJ extends Cliente{
    private final String cnpj;
    private Date dataFundacao;
    private ArrayList<Frota> listaFrota;

    public ClientePJ(String nome, String telefone, String endereco, String email, String cnpj, String dataFundacao) {
        super(nome, telefone, endereco, email);
        this.cnpj = cnpj;
        this.dataFundacao = Leitura.stringToDate(dataFundacao);
        this.listaFrota = new ArrayList<Frota>();
    }

    public String getCnpj() {
        return cnpj;
    }

    public Date getDataFundacao() {
        return dataFundacao;
    }

    public void setDataFundacao(Date dataFundacao) {
        this.dataFundacao = dataFundacao;
    }

    public ArrayList<Frota> getListaFrota() {
        return listaFrota;
    }

    public void setListaFrota(ArrayList<Frota> listaFrota) {
        this.listaFrota = listaFrota;
    }

    public boolean cadastrarFrota(Frota frota){
        return listaFrota.add(frota);
    }

    // remove veiculo
    public boolean atualizarFrota(String operacao, Veiculo veiculo){
        
        boolean achou = false;

        if (operacao.equals("remover-veiculo")) 
            for (Frota frota : listaFrota)
                for (Veiculo veiculoAtual : frota.getListaVeiculo())
                    if (veiculoAtual.getPlaca().equals(veiculo.getPlaca())){
                        frota.removeVeiculo(veiculoAtual);
                        achou = true;
                    }
        return achou;

    }

    // adiciona veiculo
    public boolean atualizarFrota(String operacao,Veiculo veiculo, Frota frota){
        
        boolean achou = false;

        if (operacao.equals("adicionar-veiculo"))
            for (Frota frotaAtual : listaFrota) 
                if (frotaAtual.getCode().equals(frota.getCode())) {
                    frotaAtual.addVeiculo(veiculo);
                    achou = true;
                }    
        return achou;
    }

    //remove frota
    public boolean atualizarFrota(String operacao,Frota frota){
        
        boolean achou = false;

        if (operacao.equals("remover-frota")){
            for (Frota frotaAtual : listaFrota){ 
                if (frotaAtual.getCode().equals(frota.getCode())) {
                    listaFrota.remove(frotaAtual);
                    achou = true;
                }
            }
        }
        return achou;
    }

    public boolean getVeiculosPorFrota(Frota frota){
        System.out.println("\tListando Veiculos:");
        for (Veiculo veiculo : frota.getListaVeiculo())
            System.out.println("\t" + veiculo.getPlaca());
        return true;
    }

    public String toString(){
        return super.toString() + "\n\tCnpj: " + cnpj + "\n\tData Fundacao: " + dataFundacao.toString();
    }
}