import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Date;

public class Leitura {

    public static Date stringToDate(String dataString){
        SimpleDateFormat formatar = new SimpleDateFormat("dd/MM/yyyy");
        formatar.setLenient(false);

        Date data = null;

        try {
            data = formatar.parse(dataString);
        } catch (ParseException excecao) {
            excecao.printStackTrace();
        }

        return data;
    }

    @SuppressWarnings("resource")
    public static String leitura(){
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
    }

    @SuppressWarnings("resource")
    public static String[] leitura(int limiteSplit){
        Scanner scan = new Scanner(System.in);
        return scan.nextLine().split(" ", limiteSplit);
    }

    public static Cliente lerCliente(String tipo){
        String nome, telefone, endereco, email;

        if (tipo.equals("PF")) {

            String educacao, genero, cpf, dataNascimento;
            
            System.out.println("\tCadastre um Cliente (Pessoa Física)\n");
            
            while (true){
                System.out.print("\tNome: ");
                nome = leitura();

                if (Validacao.validarNome(nome))
                    break;
                System.out.println("\tNome Invalido! Digite Novamente.");
            }

            System.out.print("\tTelefone: ");
            telefone = leitura();
            
            System.out.print("\tEndereco: ");
            endereco = leitura();

            System.out.print("\tEmail: ");
            email = leitura();

            System.out.print("\tEducacao: ");
            educacao = leitura();
            
            System.out.print("\tGenero: ");
            genero = leitura();
            
            while(true){
                System.out.print("\tCPF(Formato XXX.XXX.XXX-XX): ");
                cpf = leitura();

                if (Validacao.validarCPF(cpf))
                    break;
                System.out.println("\tCPF Invalido! Digite Novamente.");  
            }

            while (true) {
                System.out.print("\tData de Nascimento(Formato dd/mm/aaaa): ");
                
                dataNascimento = leitura();
                
                if (Validacao.validarData(dataNascimento))
                    break;

                System.out.println("\tData Invalida! Digite Novamente.");                
            }
            System.out.println();

            return new ClientePF(nome, telefone, endereco, email, cpf, genero, educacao, dataNascimento);
        }else{
            String cnpj, aux2, dataFundacao;

            System.out.println("\tCadastre um Cliente (Pessoa Jurídica)\n");
            
            while (true){
                System.out.print("\tNome: ");
                nome = leitura();

                if (Validacao.validarNome(nome))
                    break;
                System.out.println("\tNome Invalido! Digite Novamente.");
            }

            System.out.print("\tTelefone: ");
            telefone = leitura();

            System.out.print("\tEndereco: ");
            endereco = leitura();

            System.out.print("\tEmail: ");
            email = leitura();

            while(true){
                System.out.print("\tCNPJ(Formato XX.XXX.XXX/XXXX-XX): ");
                cnpj = leitura();
                if (Validacao.validarCNPJ(cnpj))
                    break;
                System.out.println("\tCNPJ Invalido! Digite Novamente.");  
            }

            while (true) {
                System.out.print("\tData de Fundacao(Formato dd/mm/aaaa): ");
                aux2 = leitura();
                
                if (Validacao.validarData(aux2)){
                    dataFundacao = aux2;
                    break;
                }

                System.out.println("\tData Invalida! Digite Novamente.");
            }

            System.out.println();


            return new ClientePJ(nome, telefone, endereco, email, cnpj, dataFundacao);
        }
    }

    public static Seguradora lerSeguradora(){
        String cnpj, nome, telefone, endereco, email;
        
        System.out.println("\tCadastre a Seguradora\n");

        while(true){
            System.out.print("\tCNPJ(Formato XX.XXX.XXX/XXXX-XX): ");
            cnpj = leitura();
            if (Validacao.validarCNPJ(cnpj))
                break;
            System.out.println("\tCNPJ Invalido! Digite Novamente.");  
        }
        
        System.out.print("\tNome: ");
        nome = leitura();

        System.out.print("\tTelefone: ");
        telefone = leitura();
        
        System.out.print("\tEndereco: ");
        endereco = leitura();
        
        System.out.print("\tEmail: ");
        email = leitura();

        System.out.println();

        return new Seguradora(cnpj, nome, telefone, endereco, email);
    }

    public static Veiculo lerVeiculo(){
        String placa, marca, modelo;
        int anoFabricacao;

        System.out.println("\tCadastre o Veiculo\n");
        System.out.print("\tPlaca: ");
        placa = leitura();
        System.out.print("\tMarca: ");
        marca = leitura();
        System.out.print("\tModelo: ");
        modelo = leitura();
        
        while (true){
            System.out.print("\tAno de Fabricacao: ");
            try {
                anoFabricacao = Integer.parseInt(leitura());
            } catch (Exception e) {
                continue;
            }
            if (anoFabricacao >= 0)
                break;

            System.out.println("\tAno Invalido!");
        }
        System.out.println();

        return new Veiculo(placa, marca, modelo, anoFabricacao);
    }

    public static Sinistro lerSinistro(Condutor condutor, Seguro seguro){
        String endereco, data;

        System.out.println("\tCadastre o Sinistro\n");
        
        while (true) {
            System.out.print("\tData de Fundacao(Formato dd/mm/aaaa): ");
            data = leitura();
            
            if (Validacao.validarData(data))
                break;

            System.out.println("\tData Invalida! Digite Novamente.");
        }

        System.out.print("\tEndereco: ");
        endereco = leitura();

        return new Sinistro(data, endereco, condutor, seguro);

    }

    public static Condutor lerCondutor(){
        String cpf, nome, telefone, endereco, email, dataNascimento;

        while(true){
            System.out.print("\tCPF(Formato XXX.XXX.XXX-XX): ");
            cpf = leitura();

            if (Validacao.validarCPF(cpf))
                break;
            System.out.println("\tCPF Invalido! Digite Novamente.");  
        }

        while (true){
            System.out.print("\tNome: ");
            nome = leitura();

            if (Validacao.validarNome(nome))
                break;
            System.out.println("\tNome Invalido! Digite Novamente.");
        }

        System.out.print("\tTelefone: ");
        telefone = leitura();
        
        System.out.print("\tEndereco: ");
        endereco = leitura();

        System.out.print("\tEmail: ");
        email = leitura();

        while (true) {
            System.out.print("\tData de Nascimento(Formato dd/mm/aaaa): ");
            dataNascimento = leitura();
            
            if (Validacao.validarData(dataNascimento))
                break;

            System.out.println("\tData Invalida! Digite Novamente.");
        }

        return new Condutor(cpf, nome, telefone, endereco, email, dataNascimento);
    }

    public static Object lerSeguro(Seguradora seguradora, Cliente cliente, Object elementoX){
        String dataInicio, dataFim;
        
        if (cliente instanceof ClientePF && elementoX instanceof Veiculo ){
            System.out.println("\tCadastre um Seguro (Pessoa Fisica)\n");

            while (true) {
                System.out.print("\tData de Inicio(Formato dd/mm/aaaa): ");
                dataInicio = leitura();
                
                if (Validacao.validarData(dataInicio))
                    break;

                System.out.println("\tData Invalida! Digite Novamente.");
            }
            while (true) {
                System.out.print("\tData de Fundacao(Formato dd/mm/aaaa): ");
                dataFim = leitura();
                
                if (Validacao.validarData(dataFim))
                    break;

                System.out.println("\tData Invalida! Digite Novamente.");
            }

            return new SeguroPF(dataInicio, dataFim, seguradora, ((Veiculo)elementoX), ((ClientePF)cliente));
            


        }else if (cliente instanceof ClientePJ && elementoX instanceof Frota){
            System.out.println("\tCadastre um Seguro (Pessoa Juridica)\n");

            while (true) {
                System.out.print("\tData de Inicio(Formato dd/mm/aaaa): ");
                dataInicio = leitura();
                
                if (Validacao.validarData(dataInicio))
                    break;

                System.out.println("\tData Invalida! Digite Novamente.");
            }
            while (true) {
                System.out.print("\tData de Fundacao(Formato dd/mm/aaaa): ");
                dataFim = leitura();
                
                if (Validacao.validarData(dataInicio))
                    break;

                System.out.println("\tData Invalida! Digite Novamente.");
            }

            return new SeguroPJ(dataInicio, dataFim, seguradora, ((Frota)elementoX), ((ClientePJ)cliente));
        }

        return 0; // nas cntps não é pra chegar aqui
    }

    public static MenuOperacoes lerOperacaoMenu(){
        MenuOperacoes operacao= MenuOperacoes.SAIR;
        boolean terminou = false;
        int entrada;

        do {
            try {
                entrada = Integer.parseInt(Leitura.leitura());
            } catch (Exception excessao) {
                continue;
            }

            for (MenuOperacoes opcao : MenuOperacoes.values()) {
                if (opcao.getCodigo() == entrada) {
                    operacao = opcao;
                    terminou = true;
                    break;
                }
            }
        }while (!terminou);

        return operacao;
    }

    public static SubMenu lerSubOperacao(String tipo){
        SubMenu operacao = SubMenu.VOLTAR;
        boolean terminou = false;
        int entrada;
        
        do {
            try {
                entrada = Integer.parseInt(Leitura.leitura());
            } catch (Exception excessao) {
                continue;
            }
            
            for (SubMenu opcao : SubMenu.values()) {
                if (entrada == 0)
                    return SubMenu.VOLTAR;
                    
                if (opcao.getCodigo() == entrada && opcao.getTipo().equals(tipo)) {
                    operacao = opcao;
                    terminou = true;
                    break;
                }
            }
        }while (!terminou);

        return operacao;
    }

}