import java.util.ArrayList;
import java.util.Random;

public class Frota {
    private String code;
    private ArrayList<Veiculo> listaVeiculo;

    public Frota() {
        this.code = gerarCode();
        this.listaVeiculo = new ArrayList<Veiculo>();
    }

    private String gerarCode(){
        Random rand = new Random();
        return String.valueOf(rand.nextInt(9999999 - 1000000) + 9999999) ;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ArrayList<Veiculo> getListaVeiculo() {
        return listaVeiculo;
    }

    public void setListaVeiculo(ArrayList<Veiculo> listaVeiculo) {
        this.listaVeiculo = listaVeiculo;
    }

    public boolean addVeiculo(Veiculo veiculo){
        return listaVeiculo.add(veiculo);
    }

    public boolean removeVeiculo(Veiculo veiculo){
        return listaVeiculo.remove(veiculo);
    }

    public String toString() {
        return "\n\tCodigo: " + code;
    }
}
