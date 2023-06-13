import java.util.ArrayList;

public class Seguradora {
    private final String cnpj;
    private String nome;
    private String telefone;
    private String endereco;
    private String email;
    private ArrayList<Cliente> listaClientes;
    private ArrayList<Seguro> listaSeguros;
    private double receita;
    
    public Seguradora(String cnpj, String nome, String telefone, String endereco, String email) {
        this.cnpj = cnpj;
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.email = email;
        this.receita = 0;
        listaClientes = new ArrayList<Cliente>();
        listaSeguros = new ArrayList<Seguro>();
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getReceita(){
        return receita;
    }

    public ArrayList<Cliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(ArrayList<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public ArrayList<Seguro> getListaSeguros() {
        return listaSeguros;
    }

    public void setListaSeguros(ArrayList<Seguro> listaSeguros) {
        this.listaSeguros = listaSeguros;
    }

    public void listarClientes(String tipoCliente){
        String retorno = "\n";
        int i = 1;

        for (Cliente atual3 : listaClientes){
            retorno += "    Cliente " + i++ + ":\n";
            retorno += "    Nome: " + atual3.getNome() + "\n";

            if (atual3 instanceof ClientePF)
                retorno += "    CPF: " + ((ClientePF)atual3).getCpf() + "\n\n";
            else if (atual3 instanceof ClientePJ)
                retorno += "    CNPJ: " + ((ClientePJ)atual3).getCnpj() + "\n\n";
        }        

        if (retorno.equals(""))
            System.out.println("    Nao ha clientes no cadastro!");
        else System.out.println(retorno);
    }
    
    public boolean gerarSeguro(Seguro seguro){
        return listaSeguros.add(seguro);
    }

    public boolean cancelarSeguro(Seguro seguro){
        return listaSeguros.remove(seguro);
    }

    public boolean cadastrarCliente(Cliente cliente){
        return listaClientes.add(cliente);
    }

    public boolean removerCliente(Cliente cliente){
        return listaClientes.remove(cliente);
    }
    
    public ArrayList<Seguro> getSegurosPorCliente(Cliente cliente){
        
        ArrayList<Seguro> retorno = new ArrayList<Seguro>();

        for (Seguro atual : listaSeguros) {

            if (atual instanceof SeguroPJ && cliente instanceof ClientePJ) {
                if (((SeguroPJ)atual).getCliente().getCnpj().equals(((ClientePJ)cliente).getCnpj())) {
                    retorno.add(atual);
                }
            }else if (atual instanceof SeguroPF && cliente instanceof ClientePF){
                if (((SeguroPF)atual).getCliente().getCpf().equals(((ClientePF)cliente).getCpf())){
                    retorno.add(atual);
                }
            }
        }

        return retorno;
    }

    public ArrayList<Sinistro> getSinistrosPorCliente(Cliente cliente){

        ArrayList<Sinistro> retorno = new ArrayList<Sinistro>();

        for (Seguro atual : listaSeguros) {
            if (atual instanceof SeguroPJ && cliente instanceof ClientePJ) {
                if (((SeguroPJ)atual).getCliente().getCnpj().equals(((ClientePJ)cliente).getCnpj())) {
                    for (Sinistro sinistro : atual.getListaSinistros()) {
                        retorno.add(sinistro);                        
                    }
                }
            }else if (atual instanceof SeguroPF && cliente instanceof ClientePF){
                if (((SeguroPF)atual).getCliente().getCpf().equals(((ClientePF)cliente).getCpf())) {
                    for (Sinistro sinistro : atual.getListaSinistros()) {
                        retorno.add(sinistro);                        
                    }
                }
            }
        }

        return retorno;
    }

    public double calcularReceita(){

        int receita = 0;

        for (Seguro seguro : listaSeguros){
            seguro.setValorMensal(seguro.calcularValor());
            receita += seguro.getValorMensal();
        }

        this.receita = receita;
        return receita;
    }

    public String toString() {
        return "\n\tCnpj: " + cnpj + "\n\tNome: " + nome + "\n\tTelefone: " + telefone + "\n\tEndereco: " + endereco
                + "\n\tEmail: " + email + "\n\tReceita: " + receita;
    }
}
