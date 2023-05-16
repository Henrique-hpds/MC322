public enum SubMenu {
    CADASTRAR_CLIENTE("Cadastrar Cliente",1, "cadastro"),
    CADASTRAR_VEICULO("Cadastrar Veiculo", 2, "cadastro"),
    CADASTRAR_SEGURADORA("Cadastrar Seguradora", 3, "cadastro"),

    LISTAR_CLIENTE_POR_SEGURADORA("Listar Cliente por Seguradora", 1, "listagem"),
    LISTAR_SINISTROS_POR_SEGURADORA("Listar Sinistros por Seguradora", 2, "listagem"),
    LISTAR_SINISTRO_POR_CLIENTE("Listar Sinistro por Cliente", 3, "listagem"),
    LISTAR_VEICULO_POR_CLIENTE("Listar Veiculo por Cliente", 4, "listagem"),
    LISTAR_VEICULO_POR_SEGURADORA("Listar Veiculo por Seguradora", 5, "listagem"),

    EXCLUIR_CLIENTE("Excluir Cliente", 1, "exclusao"),
    EXCLUIR_VEICULO("Excluir Veiculo", 2, "exclusao"),
    EXCLUIR_SINISTRO("Excluir Sinistro",3, "exclusao"),

    VOLTAR("Voltar", 0);

    private final String operacao;
    private final int codigo;
    private  String tipo;

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

    public double getCodigo(){
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
                tipoString = "cadastro";
                break;
        
            case LISTAR:
                tipoString = "listagem";
                break;

            case EXCLUIR:
                tipoString = "exclusao";
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