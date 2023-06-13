import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public abstract class Seguro {
    protected final int VALOR_BASE = 10;
    private final int id;
    private Date dataInicio;
    private Date dataFim;
    private Seguradora seguradora;
    private ArrayList<Sinistro> listaSinistros;
    private ArrayList<Condutor> listaCondutores;
    private double valorMensal;

    public Seguro(String dataInicio, String dataFim, Seguradora seguradora) {
        this.id = gerarCode();
        this.dataInicio = Leitura.stringToDate(dataInicio);
        this.dataFim = Leitura.stringToDate(dataFim);
        this.seguradora = seguradora;
        listaCondutores = new ArrayList<Condutor>();
        listaSinistros = new ArrayList<Sinistro>();
    }

    private int gerarCode(){
        return new Random().nextInt(9999999 - 1000000) + 9999999;
    }

    public int getId() {
        return id;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public Seguradora getSeguradora() {
        return seguradora;
    }

    public void setSeguradora(Seguradora seguradora) {
        this.seguradora = seguradora;
    }

    public ArrayList<Sinistro> getListaSinistros() {
        return listaSinistros;
    }

    public void setListaSinistros(ArrayList<Sinistro> listaSinistros) {
        this.listaSinistros = listaSinistros;
    }

    public ArrayList<Condutor> getListaCondutores() {
        return listaCondutores;
    }

    public void setListaCondutores(ArrayList<Condutor> listaCondutores) {
        this.listaCondutores = listaCondutores;
    }

    public double getValorMensal() {
        return valorMensal;
    }

    public void setValorMensal(double valorMensal) {
        this.valorMensal = valorMensal;
    }

    public abstract boolean desautorizarCondutor(Condutor condutor);

    public abstract boolean autorizarCondutor(Condutor condutor);

    public abstract double calcularValor();
    
    public abstract void gerarSinistro(Sinistro sinistro);

    public String toString() {
        return "\n\tId: " + id + "\n\tData Inicio: " + dataInicio + "\n\tData Fim: " + dataFim + "\n\tSeguradora: " + seguradora.getNome()
                + "\n\tValor Mensal: " + valorMensal;
    }
        
}
