import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class SeguroPF extends Seguro {
    private Veiculo veiculo;
    private ClientePF cliente;

    public SeguroPF(String dataInicio, String dataFim, Seguradora seguradora, Veiculo veiculo, ClientePF cliente) {
        super(dataInicio, dataFim, seguradora);
        this.veiculo = veiculo;
        this.cliente = cliente;
        setValorMensal(calcularValor());
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public ClientePF getCliente() {
        return cliente;
    }

    public void setCliente(ClientePF cliente) {
        this.cliente = cliente;
    }

    public boolean autorizarCondutor(Condutor condutor){
        ArrayList<Condutor> lista = getListaCondutores();
        boolean retorno = lista.add(condutor); 
        setListaCondutores(lista);
        return retorno;
    }

    public boolean desautorizarCondutor(Condutor condutor){
        ArrayList<Condutor> lista = getListaCondutores();
        boolean retorno = lista.remove(condutor);
        setListaCondutores(lista);
        return retorno;
    }

    public void gerarSinistro(Sinistro sinistro){
        ArrayList<Sinistro> lista = getListaSinistros();
        lista.add(sinistro); 
        setListaSinistros(lista);
    }

    public double calcularValor(){
        long idade = ChronoUnit.YEARS.between(cliente.getDataNascimento().toInstant().atZone(ZoneId.of("America/Sao_Paulo")).toLocalDate(), LocalDate.now());

        int nSinistrosCondutor = 0;

        for (Condutor condutor : getListaCondutores())
            nSinistrosCondutor += condutor.getListaSinistros().size();

        return (VALOR_BASE * getFatorIdade(idade) * (1 + 1/(cliente.getListaVeiculos().size() + 2)) * (2 + getListaSinistros().size()/10) * (5 + nSinistrosCondutor/10));
    }

    private double getFatorIdade(long idade){
        if (idade < 30)
            return 1.25;
        if (idade >= 30 && idade <= 60)
            return 1.0;
        if (idade > 60)
            return 1.5;
        return 0.0;
    }

    public String toString() {
        return super.toString() + "\n\tVeiculo: " + veiculo.getPlaca() + "\n\tCliente: " + cliente.getNome();
    }    
}
