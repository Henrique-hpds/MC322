import java.util.List;
import java.util.ArrayList;

public class Main {
    
    public static void rotinaSubMenu(MenuOperacoes opcao, List<Seguradora> listaSeguradoras){
        
        boolean fimExecucao = false;
        
        do{
            SubMenu.listarOperacoes(opcao);
            fimExecucao = submenu(Leitura.lerSubOperacao(), listaSeguradoras);
        }while(!fimExecucao);
    }
    
    public static void RotinaMenu(List<Seguradora> listaSeguradoras){

        boolean fimExecucao = false;

        while (!fimExecucao) {
            MenuOperacoes.listarOpcoesMenu();
            fimExecucao = menu(Leitura.lerOperacaoMenu(), listaSeguradoras);
        }
    }

    // retorna o índice da primeira ocorrência da palavra buscada ou -1 se a palavra não estiver na lista
    public static int BuscaNomeSeguradoraListaSeguradoras(List<Seguradora> listaSeguradoras, String nome){
        for (Seguradora atual : listaSeguradoras)
            if (atual.getNome().equals(nome))
                return listaSeguradoras.indexOf(atual);

        return -1;
    }

    public static void cadastrar_cliente(List<Seguradora> listaSeguradoras){ // o
        System.out.print("    Nome da Seguradora: ");

        int indice = BuscaNomeSeguradoraListaSeguradoras(listaSeguradoras, Leitura.leitura());

        if (indice >= 0) {
            String tipoCliente;

            while(true){
                System.out.print("\n    Tipo do cliente (PJ/PF):");
                tipoCliente = Leitura.leitura();
                if (tipoCliente.equals("PF") || tipoCliente.equals("PJ"))
                    break;
                System.out.println("    Tipo de cliente invalido. Tente novamente!");
            }

            listaSeguradoras.get(indice).cadastrarCliente(Leitura.lerCliente(tipoCliente));
            
        }else System.out.println("\n    A seguradora informada nao foi encontrada no sistema!");
    }

    public static void cadastrar_veiculo(List<Seguradora> listaSeguradoras){ // o
    

        Veiculo vrumvrum = Leitura.lerVeiculo();

        String nomeBuscar;

        while (true) {
            
            System.out.print("    Digite o nome do Proprietario:");

            nomeBuscar = Leitura.leitura();

            if (Validacao.validarNome(nomeBuscar))
                break;
            System.out.println("    Nome Invalido, tente novamente!");
            
        }

        for (Seguradora seguradora : listaSeguradoras)
            for (Cliente cliente : seguradora.getListaClientes())
                if (cliente.getNome().equals(nomeBuscar))
                    cliente.addVeiculo(vrumvrum);
    }

    public static void cadastrar_seguradora(List<Seguradora> listaSeguradoras){ //o
        listaSeguradoras.add(Leitura.lerSeguradora());
    }

    public static void listar_cliente_por_seguradora(List<Seguradora> listaSeguradoras){ // o
        
        System.out.print("    Nome da Seguradora: ");
        String nomeSeguradora = Leitura.leitura();

        for (Seguradora seguradora : listaSeguradoras){
            if (seguradora.getNome().equals(nomeSeguradora)){
                seguradora.listarClientes("ALL");
                return;
            }
        }

        System.out.println("    Seguradora nao encontrada!");
    }

    public static void listar_sinistros_por_seguradora(List<Seguradora> listaSeguradoras){

    }    
    
    public static void listar_sinistro_por_cliente(List<Seguradora> listaSeguradoras){

    }

    public static void listar_veiculo_por_cliente(List<Seguradora> listaSeguradoras){

    }

    public static void listar_veiculo_por_seguradora(List<Seguradora> listaSeguradoras){

    }

    public static void excluir_cliente(List<Seguradora> listaSeguradoras){

    }    

    public static void excluir_veiculo(List<Seguradora> listaSeguradoras){

    }

    public static void excluir_sinistro(List<Seguradora> listaSeguradoras){

    }

    

    public static boolean submenu(SubMenu opcao, List<Seguradora> listaSeguradoras){
        switch (opcao) {
            case CADASTRAR_CLIENTE://o
                cadastrar_cliente(listaSeguradoras);
                break;

            case CADASTRAR_VEICULO://o
                cadastrar_veiculo(listaSeguradoras);
                break;

            case CADASTRAR_SEGURADORA: // o
                cadastrar_seguradora(listaSeguradoras);
                break;

            case LISTAR_CLIENTE_POR_SEGURADORA: // o
                listar_cliente_por_seguradora(listaSeguradoras);
                break;

            case LISTAR_SINISTROS_POR_SEGURADORA:
                listar_sinistros_por_seguradora(listaSeguradoras);
                break;

            case LISTAR_SINISTRO_POR_CLIENTE:
                listar_sinistro_por_cliente(listaSeguradoras);
                break;

            case LISTAR_VEICULO_POR_CLIENTE:
                listar_veiculo_por_cliente(listaSeguradoras);
                break;

            case LISTAR_VEICULO_POR_SEGURADORA:
                listar_veiculo_por_seguradora(listaSeguradoras);
                break;

            case EXCLUIR_CLIENTE:
                excluir_cliente(listaSeguradoras);
                break;

            case EXCLUIR_VEICULO:
                excluir_veiculo(listaSeguradoras);
                break;

            case EXCLUIR_SINISTRO:
                excluir_sinistro(listaSeguradoras);
                break;

            case VOLTAR:
                return true;    

            default:
                break;
        }

        return false;
    }

    public static void gerar_sinistro(List<Seguradora> listaSeguradoras){
        Sinistro sinistro = Leitura.lerSinistro();

        System.out.print("  Nome da Seguradora: ");
        String nomeSeguradora = Leitura.leitura();

        for (Seguradora seguradora : listaSeguradoras) {
            if (seguradora.getNome().equals(nomeSeguradora)) {
                seguradora.gerarSinistro(sinistro);
            }
        }

    }

    public static void transferir_seguro(List<Seguradora> listaSeguradoras){

    }

    public static void calcular_receita_seguradora(List<Seguradora> listaSeguradoras){ // o
        System.out.print("    Nome da Seguradora: ");
        String nomeSeguradora = Leitura.leitura();

        for (Seguradora seguradora : listaSeguradoras) {
            if (seguradora.getNome().equals(nomeSeguradora)) {
                seguradora.calcularReceita();
                System.out.println("    A receita da seguradora " + seguradora.getNome() + "vale " + seguradora.getReceita());
                return;
            }
        }
        System.out.println("    Seguradora nao encontrada!");
    }

    public static boolean menu(MenuOperacoes opcao, List<Seguradora> listaSeguradoras){
        
        switch (opcao) {
        
            case CADASTRAR:
            case LISTAR:
            case EXCLUIR:
                rotinaSubMenu(opcao, listaSeguradoras);
                break;
            
            case GERAR_SINISTRO:
                gerar_sinistro(listaSeguradoras);
                break;

            case TRANSFERIR_SEGURO:
                transferir_seguro(listaSeguradoras);
                break;

            case CALCULAR_RECEITA_SEGURADORA: // o
                calcular_receita_seguradora(listaSeguradoras);
                break;

            case SAIR:
                System.out.println("    Sistema finalizado com sucesso!");
                return true;
        }

        return false;
    }

    public static void main(String[] args){

        List<Seguradora> listaSeguradoras = new ArrayList<Seguradora>();
        
        

        System.out.println("\n------------------------Menu de Cadastro------------------------");

        RotinaMenu(listaSeguradoras);

    }
}