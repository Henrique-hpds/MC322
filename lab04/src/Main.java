import java.util.List;
import java.util.ArrayList;

public class Main {
    
    public static void rotinaSubMenu(MenuOperacoes opcao, List<Seguradora> listaSeguradoras){
        
        boolean fimExecucao = false;
        
        do{
            SubMenu.listarOperacoes(opcao);
            fimExecucao = submenu(Leitura.lerSubOperacao((opcao.getOperacao().split(" ")[0]).toLowerCase()), listaSeguradoras);
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
                System.out.print("\n    Tipo do cliente (PJ/PF): ");
                tipoCliente = Leitura.leitura();
                if (tipoCliente.equals("PF") || tipoCliente.equals("PJ"))
                    break;
                System.out.println("    Tipo de cliente invalido. Tente novamente!");
            }

            listaSeguradoras.get(indice).cadastrarCliente(Leitura.lerCliente(tipoCliente));
            System.out.println("\n    Cliente cadastrado com sucesso!");

        }else System.out.println("\n    A seguradora informada nao foi encontrada no sistema!");
    }

    public static void cadastrar_veiculo(List<Seguradora> listaSeguradoras){ // o
    
        Veiculo vrumvrum = Leitura.lerVeiculo();

        String nomeBuscar;

        while (true) {
            
            System.out.print("    Digite o nome do Proprietario: ");

            nomeBuscar = Leitura.leitura();

            if (Validacao.validarNome(nomeBuscar))
                break;
            System.out.println("    Nome Invalido, tente novamente!");
        }

        for (Seguradora seguradora : listaSeguradoras)
            for (Cliente cliente : seguradora.getListaClientes())
                if (cliente.getNome().equals(nomeBuscar)){
                    cliente.addVeiculo(vrumvrum);
                    cliente.setValorSeguro(cliente.calculaScore());
                    System.out.println("    Veiculo cadastrado com sucesso!");
                    return;
                }
        
        System.out.println("    Cliente nao encontrado no sistema!");
    }

    public static void cadastrar_seguradora(List<Seguradora> listaSeguradoras){ //o
        listaSeguradoras.add(Leitura.lerSeguradora());
        System.out.println("    Seguradora cadastrada com sucesso!");
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

    public static void listar_sinistros_por_seguradora(List<Seguradora> listaSeguradoras){ // o
        
        System.out.print("    Nome da Seguradora: ");
        String nomeSeguradora = Leitura.leitura();

        for (Seguradora seguradora : listaSeguradoras) {
            if (seguradora.getNome().equals(nomeSeguradora)) {
                seguradora.listarSinistros();
                return;
            }
        }

        System.out.println("    Seguradora nao encontrada!");
    }    
    
    public static void listar_sinistro_por_cliente(List<Seguradora> listaSeguradoras){ // o
        System.out.print("    Nome do Cliente: ");
        String nomeCiente;

        while (true) {
            nomeCiente = Leitura.leitura();

            if (Validacao.validarNome(nomeCiente))
                break;
            System.out.println("    Nome invalido, tente novamente!");
        }

        boolean achou1 = false;

        for (Seguradora seguradora : listaSeguradoras) {
            if (seguradora.visualizarSinistro(nomeCiente)){
                achou1 = true;
            }
        }

        if (!achou1)
            System.out.println("    O cliente nao foi localizado no sistema!");
    }

    public static void listar_veiculo_por_cliente(List<Seguradora> listaSeguradoras){ // o
        System.out.print("    Nome do Cliente: ");
        String nomeCliente;

        while (true) {
            nomeCliente = Leitura.leitura();

            if (Validacao.validarNome(nomeCliente))
                break;
            System.out.println("    Nome invalido, tente novamente!");
        }

        for (Seguradora seguradora : listaSeguradoras) {
            for (Cliente cliente : seguradora.getListaClientes()) {
                if (cliente.getNome().equals(nomeCliente)) {
                    for (Veiculo veiculo : cliente.getListaVeiculos()) {
                        System.out.println(veiculo.toString());
                    }

                    return;
                }
            }
        }

        System.out.println("    O cliente nao foi localizado no sistema!");
    }

    public static void listar_veiculo_por_seguradora(List<Seguradora> listaSeguradoras){ // o
        System.out.print("    Nome da Seguradora: ");
        String nomeSeguradora = Leitura.leitura();

        for (Seguradora seguradora : listaSeguradoras) {
            if (seguradora.getNome().equals(nomeSeguradora)) {
                for (Cliente cliente : seguradora.getListaClientes()) {
                    for (Veiculo veiculo : cliente.getListaVeiculos()) {
                        System.out.println(veiculo.toString());
                    }
                }

                return;
            }
        }

        System.out.println("    Seguradora nao encontrada, tente novamente!");
    }

    public static void excluir_cliente(List<Seguradora> listaSeguradoras){ // o
        System.out.print("    Nome do Cliente: ");
        String nomeCiente;

        while (true) {
            nomeCiente = Leitura.leitura();

            if (Validacao.validarNome(nomeCiente))
                break;
            System.out.println("    Nome invalido, tente novamente!");
        }

        for (Seguradora seguradora : listaSeguradoras) {
            for (Cliente cliente : seguradora.getListaClientes()) {
                if (cliente.getNome().equals(nomeCiente)) {
                    seguradora.getListaClientes().remove(cliente);
                    System.out.println("    Cliente removido com sucesso!");
                    return;
                }
            }
        }

        System.out.println("    Cliente nao encontrado!");
    }    

    public static void excluir_veiculo(List<Seguradora> listaSeguradoras){ // o
        System.out.print("    Placa do carro: ");
        String placaCarro = Leitura.leitura();

        for (Seguradora seguradora : listaSeguradoras) {
            for (Cliente cliente : seguradora.getListaClientes()) {
                for (Veiculo veiculo : cliente.getListaVeiculos()) {
                    if (veiculo.getPlaca().equals(placaCarro)) {
                        cliente.getListaVeiculos().remove(veiculo);
                        cliente.setValorSeguro(cliente.calculaScore());
                        System.out.println("    Veiculo removido com Sucesso!");
                        return;
                    }
                }
            }
        }

        System.out.println("    Veiculo nao localizado!");

    }

    public static void excluir_sinistro(List<Seguradora> listaSeguradoras){ // o

        String data;

        while (true){
            System.out.print("    Data do Sinistro: ");
            data = Leitura.leitura();
                
            if (Validacao.validarData(data))
                break;

            System.out.println("    Data Invalida! Digite Novamente.");    
        }

        System.out.print("    Endereco do sinistro: ");
        String endereco = Leitura.leitura();

        for (Seguradora seguradora : listaSeguradoras) {
            for (Sinistro sinistro : seguradora.getListaSinistros()) {
                if (sinistro.getData().equals(data) && sinistro.getEndereco().equals(endereco)) {
                    seguradora.getListaSinistros().remove(sinistro);
                    System.out.println("    Sinistro removido!");
                    return;
                }
            }
        }

        System.out.println("    Sinistro nao localizado!");
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

            case LISTAR_SINISTROS_POR_SEGURADORA: // o
                listar_sinistros_por_seguradora(listaSeguradoras);
                break;

            case LISTAR_SINISTRO_POR_CLIENTE: // o
                listar_sinistro_por_cliente(listaSeguradoras);
                break;

            case LISTAR_VEICULO_POR_CLIENTE: // o
                listar_veiculo_por_cliente(listaSeguradoras);
                break;

            case LISTAR_VEICULO_POR_SEGURADORA: // o
                listar_veiculo_por_seguradora(listaSeguradoras);
                break;

            case EXCLUIR_CLIENTE: // o
                excluir_cliente(listaSeguradoras);
                break;

            case EXCLUIR_VEICULO: // o
                excluir_veiculo(listaSeguradoras);
                break;

            case EXCLUIR_SINISTRO:
                excluir_sinistro(listaSeguradoras);
                break;

            case VOLTAR:
                return true;    

            default:
                return false;
        }

        return false;
    }

    public static void gerar_sinistro(List<Seguradora> listaSeguradoras){ // o
        Sinistro sinistro = Leitura.lerSinistro();

        System.out.print("    Nome da Seguradora: ");
        String nomeSeguradora = Leitura.leitura();

        for (Seguradora seguradora : listaSeguradoras) {
            if (seguradora.getNome().equals(nomeSeguradora)) {
                
                System.out.print("    Nome do Individuo: ");
                String nomePessoa;
                
                while(true){
                    nomePessoa = Leitura.leitura();
                    
                    if (Validacao.validarNome(nomePessoa))
                        break;
                    
                    System.out.println("    Nome invalido, tente novamemte!");
                }
                
                for (Cliente cliente : seguradora.getListaClientes())
                    if (cliente.getNome().equals(nomePessoa))
                        sinistro.setCliente(cliente);
            
                
                seguradora.gerarSinistro(sinistro);
                System.out.println("    Sinistro cadastrado com sucesso!");
                return;
            }
        }

        System.out.println("    Seguradora nao encontrada!");
    }

    public static void transferir_seguro(List<Seguradora> listaSeguradoras){ // o

        String nomeTranfere, nomeRecebe;
        Cliente transfere = null, recebe = null;

        while (true){
            System.out.print("    Nome de quem vai transferir: ");
            nomeTranfere = Leitura.leitura();

            if (Validacao.validarNome(nomeTranfere))
                break;
            System.out.println("    Nome Invalido! Digite Novamente.");
        }

        while (true){
            System.out.print("    Nome de quem vai receber: ");
            nomeRecebe = Leitura.leitura();

            if (Validacao.validarNome(nomeRecebe))
                break;
            System.out.println("    Nome Invalido! Digite Novamente.");
        }

        if (nomeRecebe.equals(nomeTranfere)){
            System.out.println("    Erro! Destinatario e Emissor sao iguais!");   
            return;
        }

        boolean achou1 = false, achou2 = false;

        for (Seguradora seguradora : listaSeguradoras) {
            for (Cliente cliente : seguradora.getListaClientes()) {
                if (cliente.getNome().equals(nomeTranfere)){
                    transfere = cliente;
                    achou1 = true;
                    break;
                }                
            }
        }


        for (Seguradora seguradora : listaSeguradoras) {
            for (Cliente cliente : seguradora.getListaClientes()) {
                if (cliente.getNome().equals(nomeRecebe)){
                    recebe = cliente;
                    achou2 = true;
                    break;
                }        
            }
        }

        if (!achou1){
            System.out.println("    Remetente nao encontrado!");
            return;
        }
        if (!achou2){
            System.out.println("    Destinatario nao encontrado!");
            return;
        }

        transfere.getListaVeiculos().addAll(recebe.getListaVeiculos());
        recebe.setListaVeiculos(transfere.getListaVeiculos());
        transfere.setListaVeiculos(new ArrayList<Veiculo>());

        recebe.setValorSeguro(recebe.calculaScore());
        recebe.setValorSeguro(transfere.calculaScore());

        System.out.println("    Operacao finalizada!");
    }

    public static void calcular_receita_seguradora(List<Seguradora> listaSeguradoras){ // o
        System.out.print("    Nome da Seguradora: ");
        String nomeSeguradora = Leitura.leitura();

        for (Seguradora seguradora : listaSeguradoras) {
            if (seguradora.getNome().equals(nomeSeguradora)) {
                seguradora.calcularReceita();
                System.out.println("    A receita da seguradora " + seguradora.getNome() + " vale " + seguradora.getReceita());
                return;
            }
        }
        System.out.println("    Seguradora nao encontrada!");
    }

    public static boolean menu(MenuOperacoes opcao, List<Seguradora> listaSeguradoras){ // o
        
        switch (opcao) {
        
            case CADASTRAR:
            case LISTAR:
            case EXCLUIR:
                rotinaSubMenu(opcao, listaSeguradoras);
                break;
            
            case GERAR_SINISTRO: // o
                gerar_sinistro(listaSeguradoras);
                break;

            case TRANSFERIR_SEGURO: // o
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