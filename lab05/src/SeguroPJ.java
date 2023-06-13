import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class SeguroPJ extends Seguro {
    private Frota frota;
    private ClientePJ cliente;

    public SeguroPJ(String dataInicio, String dataFim, Seguradora seguradora, Frota frota, ClientePJ cliente) {
        super(dataInicio, dataFim, seguradora);
        this.frota = frota;
        this.cliente = cliente;
    }

    public Frota getFrota() {
        return frota;
    }

    public void setFrota(Frota frota) {
        this.frota = frota;
    }

    public ClientePJ getCliente() {
        return cliente;
    }

    public void setCliente(ClientePJ cliente) {
        this.cliente = cliente;
    }

    public boolean autorizarCondutor(Condutor condutor) {
        ArrayList<Condutor> lista = getListaCondutores();
        boolean retorno = lista.add(condutor);
        setListaCondutores(lista);
        return retorno;
    }

    public boolean desautorizarCondutor(Condutor condutor) {
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

    public double calcularValor() {

        int quantidadeFuncionarios = getListaCondutores().size();
        int quantidadeVeiculos = frota.getListaVeiculo().size();

        long idade = ChronoUnit.YEARS.between(
                cliente.getDataFundacao().toInstant().atZone(ZoneId.of("America/Sao_Paulo")).toLocalDate(),
                LocalDate.now());

        int quantidadesinistrosCliente = getListaSinistros().size();
        int quantidadeSinistrosCondutor = 0;

        for (Condutor condutor : getListaCondutores())
            quantidadeSinistrosCondutor += condutor.getListaSinistros().size();

        return (VALOR_BASE * (10 + (quantidadeFuncionarios) / 10) * (1 + 1 / (quantidadeVeiculos + 2))
                * (1 + 1 / (idade + 2)) * (2 + quantidadesinistrosCliente / 10)
                * (5 + quantidadeSinistrosCondutor / 10));
    }

    public String toString() {
        return super.toString() + "\n\tFrota: " + frota.getCode() + "\n\tCliente: " + cliente.getNome();
    }
}
