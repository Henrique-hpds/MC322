public enum SubMenu {
    CADASTRAR_CLIENTE("Cadastrar Cliente",1, "cadastrar"),
    CADASTRAR_VEICULO("Cadastrar Veiculo", 2, "cadastrar"),
    CADASTRAR_SEGURADORA("Cadastrar Seguradora", 3, "cadastrar"),

    LISTAR_CLIENTE_POR_SEGURADORA("Listar Cliente por Seguradora", 1, "listar"),
    LISTAR_SINISTROS_POR_SEGURADORA("Listar Sinistros por Seguradora", 2, "listar"),
    LISTAR_SINISTRO_POR_CLIENTE("Listar Sinistro por Cliente", 3, "listar"),
    LISTAR_VEICULO_POR_CLIENTE("Listar Veiculo por Cliente", 4, "listar"),
    LISTAR_VEICULO_POR_SEGURADORA("Listar Veiculo por Seguradora", 5, "listar"),

    EXCLUIR_CLIENTE("Excluir Cliente", 1, "excluir"),
    EXCLUIR_VEICULO("Excluir Veiculo", 2, "excluir"),
    EXCLUIR_SINISTRO("Excluir Sinistro",3, "excluir"),

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

            case EXCLUIR:
                tipoString = "excluir";
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