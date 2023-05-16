public enum MenuOperacoes {
    CADASTRAR("Cadastrar", 1),

    LISTAR("Listar",2),

    EXCLUIR("Excluir",3),

    GERAR_SINISTRO("Gerar Sinistro",4),
    TRANSFERIR_SEGURO("Transferir Seguro",5),
    CALCULAR_RECEITA_SEGURADORA("Calcular Receita Seguradora",6),
    
    SAIR("Sair", 0);

    private final String operacao;
    private final int codigo;


    // sobrecarga de construtores, para difernetes opções do menu

    MenuOperacoes(String operacao, int codigo){
        this.operacao = operacao;
        this.codigo = codigo;
    }


    public String getOperacao(){
        return operacao;
    }

    public int getCodigo(){
        return codigo;
    }


    public static void listarOpcoesMenu(){
        System.out.println();
        for (MenuOperacoes atual : MenuOperacoes.values())            System.out.println("    " + atual.codigo + " - " + atual.operacao);
        System.out.println();
    }
}

