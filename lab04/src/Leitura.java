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
        String nome, endereco;

        if (tipo.equals("PF")) {

            String educacao, genero, classeEconomica, cpf, aux;
            Date dataLicensa, dataNascimento;
            
            System.out.println("    Cadastre um Cliente (Pessoa Física)\n");
            
            while (true){
                System.out.print("  Nome: ");
                nome = leitura();

                if (Validacao.validarNome(nome))
                    break;
                System.out.println("    Nome Invalido! Digite Novamente.");
            }
            
            System.out.print("  Endereco: ");
            endereco = leitura();

            System.out.print("  Data da Licensa(Formato dd/mm/aaaa): ");
            dataLicensa = stringToDate(leitura());

            System.out.print("  Educacao: ");
            educacao = leitura();
            
            System.out.print("  Genero: ");
            genero = leitura();
            
            System.out.print("  Classe Econômica: ");
            classeEconomica = leitura();
            
            while(true){
                System.out.print("  CPF(Formato XXX.XXX.XXX-XX): ");
                cpf = leitura();

                if (Validacao.validarCPF(cpf))
                    break;
                System.out.println("    CPF Invalido! Digite Novamente.");  
            }

            while (true) {
                System.out.print("  Data de Nascimento(Formato dd/mm/aaaa): ");
                
                aux = leitura();
                
                if (Validacao.validarData(aux)){
                    dataNascimento = stringToDate(aux);
                    break;
                }
                System.out.println("    Data Invalida! Digite Novamente.");
                
            }
            System.out.println();

            return new ClientePF(nome, endereco, dataLicensa, educacao, genero, classeEconomica, cpf, dataNascimento);
        }else{
            String cnpj, aux2;
            Date dataFundacao;
            int quantidadeFuncionarios;

            System.out.println("    Cadastre um Cliente (Pessoa Jurídica)\n");
            
            while (true){
                System.out.print("  Nome: ");
                nome = leitura();

                if (Validacao.validarNome(nome))
                    break;
                System.out.println("    Nome Invalido! Digite Novamente.");
            }

            System.out.print("  Endereco: ");
            endereco = leitura();

            while(true){
                System.out.print("  CNPJ(Formato XX.XXX.XXX/XXXX-XX): ");
                cnpj = leitura();
                if (Validacao.validarCNPJ(cnpj))
                    break;
                System.out.println("    CNPJ Invalido! Digite Novamente.");  
            }

            while (true) {
                System.out.print("  Data de Fundacao(Formato dd/mm/aaaa): ");
                aux2 = leitura();
                
                if (Validacao.validarData(aux2)){
                    dataFundacao = stringToDate(aux2);
                    break;
                }

                System.out.println("    Data Invalida! Digite Novamente.");
            }

            while (true) {
                System.out.println("    Quantidade de Funcionários: ");
                quantidadeFuncionarios = Integer.parseInt(leitura());
                if (quantidadeFuncionarios > 0)
                    break;

            }

            System.out.println();


            return new ClientePJ(nome, endereco, cnpj, dataFundacao, quantidadeFuncionarios);
        }
    }

    public static Seguradora lerSeguradora(){
        String nome, telefone, email, endereco;
        
        System.out.println("    Cadastre a Seguradora\n");
        
        while (true){
            System.out.print("  Nome: ");
            nome = leitura();

            if (Validacao.validarNome(nome))
                break;
            System.out.println("    Nome Invalido! Digite Novamente.");
        }

        System.out.print("  Telefone: ");
        telefone = leitura();
        
        System.out.print("  Email: ");
        email = leitura();
        System.out.print("  Endereco: ");
        endereco = leitura();
        System.out.println();

        return new Seguradora(nome, telefone, email, endereco);
    }

    public static Veiculo lerVeiculo(){
        String placa, marca, modelo;
        int anoFabricacao;

        System.out.println("    Cadastre o Veiculo\n");
        System.out.print("  Placa: ");
        placa = leitura();
        System.out.print("  Marca: ");
        marca = leitura();
        System.out.print("  Modelo: ");
        modelo = leitura();
        System.out.print("  Ano de Fabricacao: ");
        anoFabricacao = Integer.parseInt(leitura());
        System.out.println();

        return new Veiculo(placa, marca, modelo, anoFabricacao);
    }

    public static Sinistro lerSinistro(){
        String data, endereco;

        System.out.println("    Cadastre o Sinistro\n");
        System.out.print("  Data: ");
        data = leitura();
        System.out.print("  Endereco: ");
        endereco = leitura();

        return new Sinistro(data, endereco);

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

    public static SubMenu lerSubOperacao(){
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
                if (opcao.getCodigo() == entrada) {
                    operacao = opcao;
                    terminou = true;
                    break;
                }
            }
        }while (!terminou);

        return operacao;
    }

}