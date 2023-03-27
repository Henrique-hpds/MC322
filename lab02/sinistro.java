import java.util.Date;
import java.util.Random;

class Sinistro {
    private int id;
    private String data;
    private String endereco;

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

    public Sinistro(String data, String endereco){
        this.data = data;
        this.endereco = endereco;
        this.id = generateId();
    }

    public int getId(){
        return id;
    }

    public String getData(){
        return data;
    }
    
    public String getEndereco(){
        return endereco;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setData(String data){
        this.data = data;
    }

    public void setEndereco(String endereco){
        this.endereco = endereco;
    }

    public String toString(){
        return 
        "\n  Sinistro ID " + String.valueOf(id) +
        "\n    Data: " + data +
        "\n    Endereço: " + endereco +
        "\n  #########################\n";
    }
}
