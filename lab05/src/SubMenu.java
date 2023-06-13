public enum SubMenu {
    CADASTRAR_CLIENTE("Cadastrar Cliente",1, "cadastrar"),
    CADASTRAR_SEGURO("Cadastrar Seguro", 2, "cadastrar"),
    CADASTRAR_SEGURADORA("Cadastrar Seguradora", 3, "cadastrar"),

    LISTAR_CLIENTE_POR_SEGURADORA("Listar Cliente por Seguradora", 1, "listar"),
    LISTAR_SEGURO_POR_CLIENTE("Listar Seguro por Cliente", 2, "listar"),

    VOLTAR("Voltar", 0);

    private final String operacao;
    private final int codigo;
    private String tipo;

    // sobrecarga de construtores, para diferentes opções do menu
    SubMenu(String operacao, int codigo){
        this.operacao = operacao;
        this.codigo = codigo;
    }

    SubMenu(String operacao, int codigo, String tipo){
        this.operacao = operacao;
        this.codigo = codigo;
        this.tipo = tipo;
    }

    public String getOperacao(){
        return operacao;
    }

    public int getCodigo(){
        return codigo;
    }

    public String getTipo(){
        return tipo;
    }

    public static void listarOperacoes(MenuOperacoes categoria){
        System.out.println();
        String tipoString = "";

        switch (categoria) {
            case CADASTRAR:
                tipoString = "cadastrar";
                break;
        
            case LISTAR:
                tipoString = "listar";
                break;

            default:
                break;
        }

        for (SubMenu atual : SubMenu.values())
            if (tipoString.equals(atual.getTipo()) || atual.codigo == 0 ) 
                System.out.println("    " + atual.codigo + " - " + atual.operacao);
        
        System.out.println();
        
    }
}