class Cliente {
    private String nome;
    private String cpf;
    private String dataNascimento;
    private int idade;
    private String endereco;

    public Cliente(String nome, String cpf, String dataNascimento, int idade, String endereco){
        
        if (validarCPF(cpf)){
            this.cpf = cpf;
        }else{
            System.out.println("\n  cpf inválido!!!");
            this.cpf = "INVALIDO";
        }
        
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.idade = idade;
        this.endereco = endereco;
    }

    public String getNome(){
        return nome;
    }

    public String getCpf(){
        return cpf;
    }

    public String getDataNascimento(){
        return dataNascimento;
    }

    public int getIdade(){
        return idade;
    }

    public String getEndereco(){
        return endereco;
    }

    public void setNome(String nome){
        this.nome = nome;
    }
    
    public void setCpf(String cpf){
        this.cpf = cpf;
    }
    
    public void setDataNascimento(String dataNascimento){
        this.dataNascimento = dataNascimento;
    }
    
    public void setIdade(int idade){
        this.idade = idade;
    }
    
    public void setEndereco(String endereco){
        this.endereco = endereco;
    }

    private boolean validarCPF(String cpf){

        String cpfFormatado;

        // deixa só os números
        cpfFormatado = cpf.replaceAll("-", "");
        cpfFormatado = cpfFormatado.replaceAll("\\.", "");


        if (cpfFormatado.length() != 11)
            return false;


        char anterior = cpfFormatado.charAt(0);

        // verifica se todos os digitos são iguais
        for (int i = 0; i < 11; i++) {
            
            if (anterior != cpfFormatado.charAt(i)) 
                break;
            
            if (i == cpfFormatado.length() - 1)
                return false;

        }

        
        int multiplicador = 10, soma = 0;

        // Dígitos finais dados pelo usuário
        int penultimoDigito = Character.getNumericValue(cpfFormatado.charAt(9));
        int ultimoDigito = Character.getNumericValue(cpfFormatado.charAt(10));

        // soma da multiplicação de cada um dos 9 primeiros termos por um multiplicador que começa no 10 e decresce 1 unidade a cada passo
        for (int i = 0; i < 9; i++)
            soma += Character.getNumericValue(cpfFormatado.charAt(i)) * multiplicador--;


        int penultimoCorreto, ultimoCorreto;

        // verifica se o penúltimo dígito informado bate com o calculado
        if (soma % 11 < 2) 
            penultimoCorreto = 0;
        else penultimoCorreto = 11 - (soma % 11);

        if (penultimoCorreto != penultimoDigito)
            return false;


        soma = 0;
        multiplicador = 11;

        // soma da multiplicação de cada um dos 9 primeiros termos + penúltimo dígito por um multiplicador que começa no 11 e decresce 1 unidade a cada passo
        for (int i = 0; i < 10; i++)
            soma += Character.getNumericValue(cpfFormatado.charAt(i)) * multiplicador--;


        // verifica se o último dígito informado bate com o calculado
        if (soma % 11 < 2) 
            ultimoCorreto = 0;
        else ultimoCorreto = 11 - (soma % 11);

        if (ultimoCorreto != ultimoDigito)
            return false;


        return true;
    }

    public String toString(){
        return 
        "\n  Cliente CPF " + cpf +
        "\n    Nome: " + nome +
        "\n    Data de Nascimento: " + dataNascimento + 
        "\n    Idade: " + String.valueOf(idade) + 
        "\n    Endereço: " + endereco  +
        "\n  #########################\n"; 
    }
}
