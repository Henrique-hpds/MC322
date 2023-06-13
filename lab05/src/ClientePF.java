import java.util.ArrayList;
import java.util.Date;

public class ClientePF extends Cliente {
    private final String cpf;
    private String genero;
    private String educacao;
    private Date dataNascimento;
    private ArrayList<Veiculo> listaVeiculos;

    public ClientePF(String nome, String telefone, String endereco, String email, String cpf, String genero, String educacao, String dataNascimento) {
        super(nome, telefone, endereco, email);
        this.cpf = cpf;
        this.genero = genero;
        this.educacao = educacao;
        this.dataNascimento = Leitura.stringToDate(dataNascimento);
        this.listaVeiculos = new ArrayList<Veiculo>();
    }

    public String getCpf() {
        return cpf;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEducacao() {
        return educacao;
    }

    public void setEducacao(String educacao) {
        this.educacao = educacao;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public ArrayList<Veiculo> getListaVeiculos() {
        return listaVeiculos;
    }

    public void setListaVeiculos(ArrayList<Veiculo> listaVeiculos) {
        this.listaVeiculos = listaVeiculos;
    }

    public boolean cadastrarVeiculo(Veiculo veiculo){
        return listaVeiculos.add(veiculo);
    }

    public boolean removerVeiculo(Veiculo veiculo){
        return listaVeiculos.remove(veiculo);
    }

    public String toString() {
        return super.toString() + "\n\tCpf: " + cpf + "\n\tGenero: " + genero + "\n\tEducacao: " + educacao + "\n\tData Nascimento:"
                + dataNascimento;
    }       
}