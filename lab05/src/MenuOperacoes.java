public enum MenuOperacoes {
    CADASTRAR("Cadastrar", 1),

    LISTAR("Listar",2),

    GERAR_SINISTRO("Gerar Sinistro",3),
    CALCULAR_RECEITA_SEGURADORA("Calcular Receita Seguradora",4),
    
    SAIR("Sair", 0);

    private final String operacao;
    private final int codigo;

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
        for (MenuOperacoes atual : MenuOperacoes.values())            
            System.out.println("    " + atual.codigo + " - " + atual.operacao);
        System.out.println();
    }
}