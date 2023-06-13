import java.util.Date;
import java.util.Random;

public class Sinistro {
    private final int id;
    private Date data;
    private String endereco;
    private Condutor condutor;
    private Seguro seguro;

    public Sinistro(String data, String endereco, Condutor condutor, Seguro seguro) {
        this.data = Leitura.stringToDate(data);
        this.endereco = endereco;
        this.condutor = condutor;
        this.seguro = seguro;
        this.id = generateId();
    }

    public int getId() {
        return id;
    }

    public Date getData() {
        return data;
    }
    
    public void setData(Date data) {
        this.data = data;
    }
    
    public String getEndereco() {
        return endereco;
    }
    
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
    public Condutor getCondutor() {
        return condutor;
    }
    
    public void setCondutor(Condutor condutor) {
        this.condutor = condutor;
    }
    
    public Seguro getSeguro() {
        return seguro;
    }

    public void setSeguro(Seguro seguro) {
        this.seguro = seguro;
    }

    /* gera um inteiro id a partir de um número pseudo-aleatório e dos hashs de data e endereço */
    private int generateId(){

        Date data_atual = new Date();
        Random rand = new Random();

        int denominador = rand.nextInt(100);

        while(denominador == 0)
            denominador = rand.nextInt(100);      
        
        int retorno = (this.data.hashCode() + data_atual.hashCode() + this.endereco.hashCode()) / denominador;
        
        if (retorno < 0)
            retorno *= -1;

        return retorno;
    }

    public String toString() {
        return "\n\tId: " + id + "\n\tData: " + data + "\n\tEndereco: " + endereco;
    }
    
    
}
