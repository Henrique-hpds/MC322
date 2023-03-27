import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        Scanner entrada = new Scanner(System.in);
        String nome, cpf, data, endereco, placa, marca, modelo, email, telefone, atributo, valor   ;
        int idade;


        System.out.println("\n\nSISTEMA DE CADASTRO\n");



        System.out.println("  CLIENTE\n");

        System.out.print("    Insira o nome: ");
        nome = entrada.nextLine();
        System.out.print("    Insira o CPF: ");
        cpf = entrada.nextLine();
        System.out.print("    Insira a data de nascimento: ");
        data = entrada.nextLine();
        System.out.print("    Insira a idade: ");
        idade = Integer.parseInt(entrada.nextLine());
        System.out.print("    Insira o endereço: ");
        endereco = entrada.nextLine();

        Cliente cliente = new Cliente(nome, cpf, data, idade, endereco);

        System.out.println("\n  VEÍCULO\n");

        System.out.print("    Insira a placa: ");
        placa = entrada.nextLine();
        System.out.print("    Insira a marca: ");
        marca = entrada.nextLine();
        System.out.print("    Insira o modelo: ");
        modelo = entrada.nextLine();

        Veiculo veiculo = new Veiculo(placa, marca, modelo);



        System.out.println("\n  SINISTRO\n");

        System.out.print("    Insira a data: ");
        data = entrada.nextLine();
        System.out.print("    Insira o endereco: ");
        endereco = entrada.nextLine();

        Sinistro sinistro = new Sinistro(data, endereco);




        System.out.println("\n  SEGURADORA\n");
        

        System.out.print("    Insira o nome: ");
        nome = entrada.nextLine();
        System.out.print("    Insira o telefone: ");
        telefone = entrada.nextLine();
        System.out.print("    Insira o email: ");
        email = entrada.nextLine();
        System.out.print("    Insira o endereco: ");
        endereco = entrada.nextLine();

        Seguradora seguradora = new Seguradora(nome, telefone, email, endereco);

        String operacao = "" ;

        while (!operacao.equals("0")){
            System.out.println("\n  OPERAÇÕES\n");
            System.out.println("    Digite 0 para sair");
            System.out.println("    consultar");
            System.out.println("    alterar <nome> <atributo> <valor>\n");

            System.out.print("  Operação: ");;

            operacao = entrada.nextLine();

            if (operacao.equals("consultar")) {
                System.out.println(cliente.toString());
                System.out.println(veiculo.toString());
                System.out.println(sinistro.toString());
                System.out.println(seguradora.toString());

            }else if(operacao.split(" ")[0].equals("alterar")){
                try{
                    nome = operacao.split(" ")[1];
                    atributo = operacao.split(" ")[2];
                    valor = operacao.split(" ", 4)[3];        
                }catch (ArrayIndexOutOfBoundsException a){
                    System.out.println("\n  Operação inválida!");
                    continue;
                }
                

                if (nome.equals("cliente")) {
                    if (atributo.equals("nome"))
                        cliente.setNome(valor);
                    if (atributo.equals("cpf"))
                        cliente.setCpf(valor);
                    if (atributo.equals("data-nascimento"))
                        cliente.setDataNascimento(valor);
                    if (atributo.equals("idade"))
                        cliente.setIdade(Integer.parseInt(valor));
                    if (atributo.equals("endereco"))
                        cliente.setEndereco(valor);
                }else if (nome.equals("veiculo")) {
                    if (atributo.equals("placa"))
                        veiculo.setPlaca(valor);
                    if (atributo.equals("marca"))
                        veiculo.setMarca(valor);
                    if (atributo.equals("modelo"))
                        veiculo.setModelo(valor);
                }else if (nome.equals("sinistro")) {
                    /* o id não deve ser alterado pelo usuário, apesar de haver o setter */
                    if (atributo.equals("data"))
                        sinistro.setData(valor);
                    if (atributo.equals("endereco"))
                        sinistro.setEndereco(valor);
                }else if (nome.equals("seguradora")) {
                    if (atributo.equals("nome"))
                        seguradora.setNome(valor);
                    if (atributo.equals("telefone"))
                        seguradora.setTelefone(valor);
                    if (atributo.equals("email"))
                        seguradora.setEmail(valor);
                    if (atributo.equals("endereco"))
                        seguradora.setEndereco(valor);
                }   
            }else if (!operacao.equals("0")) {
                System.out.println("\n  Operação inválida!");
                continue;
            }
            System.out.println("\n  Operação realizada com sucesso!!!");
        }
        System.out.println("\n  Sistema encerrado com sucesso!");
    }
}