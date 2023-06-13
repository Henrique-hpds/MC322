import java.util.ArrayList;
import java.util.List;

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

    public static void cadastrar_cliente(List<Seguradora> listaSeguradoras){

        String nome;
        
        while (true){
            System.out.print("\tNome da seguradora: ");
            nome = Leitura.leitura();
            if(Validacao.validarNome(nome))
                break;
            System.out.println("\tNome Invalido!");
        }

        for (Seguradora seguradora : listaSeguradoras)
            if (seguradora.getNome().equals(nome)) {
                System.out.print("\tCliente PF ou PJ?: ");
                String tipo = Leitura.leitura();
                seguradora.cadastrarCliente(Leitura.lerCliente(tipo));
                System.out.println("\tCliente cadastardo com sucesso!");
                return;
            }

        System.out.println("\tSeguradora nao encontrada!");
    }

    public static void cadastrar_seguro(List<Seguradora> listaSeguradoras){

        String nomeSeguradora;
        
        while (true){
            System.out.print("\tNome da seguradora: ");
            nomeSeguradora = Leitura.leitura();
            if(Validacao.validarNome(nomeSeguradora))
                break;
            System.out.println("\tNome Invalido!");
        }

        for (Seguradora seguradora : listaSeguradoras)
            if (seguradora.getNome().equals(nomeSeguradora)) {

                System.out.print("\tNome do cliente: ");
                String nomeCliente = Leitura.leitura();

                for (Cliente cliente : seguradora.getListaClientes()) {
                    if (cliente.getNome().equals(nomeCliente)){

                        System.out.print("\tPlaca do veiculo: ");
                        String placa = Leitura.leitura();

                        if (cliente instanceof ClientePF) {
                            for (Veiculo veiculo: ((ClientePF)cliente).getListaVeiculos()) {
                                if (veiculo.getPlaca().equals(placa)) {
                                    seguradora.gerarSeguro(((Seguro)Leitura.lerSeguro(seguradora, cliente, veiculo)));
                                    return;
                                }
                            }
                            
                        } else if (cliente instanceof ClientePJ) {
                            for (Frota frota : ((ClientePJ)cliente).getListaFrota()) {
                                for (Veiculo veiculo : frota.getListaVeiculo()) {
                                    if (veiculo.getPlaca().equals(placa)) {
                                        seguradora.gerarSeguro(((Seguro)Leitura.lerSeguro(seguradora, cliente, frota)));
                                        return;
                                    }
                                }
                            }
                        }

                        System.out.println("\tVeiculo nao localizado!");
                        return;
                    }
                }
                System.out.println("\tCliente nao localizado!");
                return;
            }

        System.out.println("\tSeguradora nao encontrada!");
    }

    public static void cadastrar_seguradora(List<Seguradora> listaSeguradoras){
        listaSeguradoras.add(Leitura.lerSeguradora());
    }

    public static void listar_cliente_por_seguradora(List<Seguradora> listaSeguradoras){
        
        String nomeSeguradora;
        
        while (true){
            System.out.print("\tNome da seguradora: ");
            nomeSeguradora = Leitura.leitura();
            if(Validacao.validarNome(nomeSeguradora))
                break;
            System.out.println("\tNome Invalido!");
        }
        

        for (Seguradora seguradora : listaSeguradoras) {
            if(seguradora.getNome().equals(nomeSeguradora)){
                seguradora.listarClientes(nomeSeguradora);
                return;
            }
        }

        System.out.println("\tSeguradora nao encontrada!");
    }

    public static void listar_seguro_por_cliente(List<Seguradora> listaSeguradoras){
       String nomeSeguradora;
        
        while (true){
            System.out.print("\tNome da seguradora: ");
            nomeSeguradora = Leitura.leitura();
            if(Validacao.validarNome(nomeSeguradora))
                break;
            System.out.println("\tNome Invalido!");
        }

        List<Seguro> retorno = new ArrayList<Seguro>();

        for (Seguradora seguradora : listaSeguradoras) {
            if(seguradora.getNome().equals(nomeSeguradora)){
                System.out.print("\tNome do cliente: ");
                String nomeCliente = Leitura.leitura();


                for (Seguro seguro : seguradora.getListaSeguros()) {
                    if (seguro instanceof SeguroPF) {
                        if(((SeguroPF)seguro).getCliente().getNome().equals(nomeCliente)){
                            retorno.add(seguro);
                        }
                    }       
                    
                    else if (seguro instanceof SeguroPJ){
                        if(((SeguroPJ)seguro).getCliente().getNome().equals(nomeCliente)){
                            retorno.add(seguro);
                        }
                    }

                }
            }
        }
        
        if (retorno.size() == 0) {
            System.out.println("\tNao foi possivel realizar a operacao!");
            return;
        }
        

        for (Seguro seguro : retorno) {
            System.out.println(seguro.toString());
        }

    }

    public static boolean submenu(SubMenu opcao, List<Seguradora> listaSeguradoras){
        switch (opcao) {
            case CADASTRAR_CLIENTE:
                cadastrar_cliente(listaSeguradoras);
                break;

            case CADASTRAR_SEGURO:
                cadastrar_seguro(listaSeguradoras);
                break;

            case CADASTRAR_SEGURADORA:
                cadastrar_seguradora(listaSeguradoras);
                break;

            case LISTAR_CLIENTE_POR_SEGURADORA:
                listar_cliente_por_seguradora(listaSeguradoras);
                break;

            case LISTAR_SEGURO_POR_CLIENTE:
                listar_seguro_por_cliente(listaSeguradoras);
                break;

            case VOLTAR:
                return true;    

            default:
                return false;
        }

        return false;
    }

    public static void gerar_sinistro(List<Seguradora> listaSeguradoras){
        System.out.print("\tNome da seguradora: ");
        String nome = Leitura.leitura();

        for (Seguradora seguradora : listaSeguradoras) {
            if (seguradora.getNome().equals(nome)) {
                System.out.print("\tID do Seguro: ");
                int id = Integer.parseInt(Leitura.leitura());
                for (Seguro seguro : seguradora.getListaSeguros()) {
                    if (seguro.getId() == id) {
                        seguro.getListaSinistros().add(Leitura.lerSinistro(Leitura.lerCondutor(), seguro));
                    }
                }                
            }
        }
    }

    public static void gerar_sinistro(List<Seguradora> listaSeguradoras, Condutor condutor){
        System.out.print("\tNome da seguradora: ");
        String nome = Leitura.leitura();

        for (Seguradora seguradora : listaSeguradoras) {
            if (seguradora.getNome().equals(nome)) {
                System.out.print("\tID do Seguro: ");
                int id = Integer.parseInt(Leitura.leitura());
                for (Seguro seguro : seguradora.getListaSeguros()) {
                    if (seguro.getId() == id)
                        seguro.getListaSinistros().add(Leitura.lerSinistro(condutor, seguro));
                }                
            }
        }
    }

    public static void calcular_receita_seguradora(List<Seguradora> listaSeguradoras){
        System.out.print("\tNome da seguradora: ");
        String nome = Leitura.leitura();

        for (Seguradora seguradora : listaSeguradoras)
            if (seguradora.getNome().equals(nome)){
                System.out.println("\tReceita: " + seguradora.calcularReceita());
                return;
            }

        System.out.println("\tSeguradora nao encontrada!");
    }

    public static boolean menu(MenuOperacoes opcao, List<Seguradora> listaSeguradoras){
        
        switch (opcao) {
        
            case CADASTRAR:
            case LISTAR:
                rotinaSubMenu(opcao, listaSeguradoras);
                break;
            
            case GERAR_SINISTRO:
                gerar_sinistro(listaSeguradoras);
                break;

            case CALCULAR_RECEITA_SEGURADORA:
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

        Seguradora seguradora = new Seguradora("48.115.974/0001-47", "Dora", "1112345678", "Rua Bobos, 0", "stevejobs@dac.unicamp.br");
        
        ClientePF clientePF = new ClientePF("Esther Luna Colombini", "9283749273", "Rua Saturnino de Brito, 100", "esther@ic.unicamp.br", "583.213.624-19", "feminino", "doutorado", "02/06/1978");
        
        ClientePJ clientePJ = new ClientePJ("Unicamp", "1992846283", "Avenida Albert Einstein", "unicamp@dac.unicamp.br", "42.520.576/0001-20", "05/09/1966");

        clientePF.cadastrarVeiculo(new Veiculo("JND9383", "fiat", "uno", 2005));

        clientePJ.cadastrarFrota(new Frota());
        clientePJ.atualizarFrota("adicionar-veiculo", new Veiculo("ABC8374", "fiat", "palio", 1997));

        SeguroPF seguroPF = new SeguroPF("01/09/2000", "01/09/2001", seguradora, clientePF.getListaVeiculos().get(0) , clientePF);
        SeguroPJ seguroPJ = new SeguroPJ("12/03/2003", "12/03/2004", seguradora, clientePJ.getListaFrota().get(0), clientePJ);

        Condutor condutor = new Condutor("126.020.200-36", "Luis da Silva", "2910293829", "Rua São Paulo, 292", "luis@dac.unicamp.br", "03/03/2002");

        seguroPF.gerarSinistro(new Sinistro("02/03/2003", "Avenida Alan Turing, 100", condutor, seguroPF));
        seguroPJ.gerarSinistro(new Sinistro("28/02/2009", "Rua Roxo Moreira, 90", condutor, seguroPJ));

        seguroPF.autorizarCondutor(condutor);
        seguroPJ.autorizarCondutor(condutor);
    
        seguradora.gerarSeguro(seguroPF);
        seguradora.gerarSeguro(seguroPJ);

        seguradora.cadastrarCliente(clientePF);
        seguradora.cadastrarCliente(clientePJ);
        
        listaSeguradoras.add(seguradora);

        System.out.println("\n------------------------Menu de Cadastro------------------------");

        RotinaMenu(listaSeguradoras);

    }
}