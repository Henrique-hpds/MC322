public class Validacao {

    public static boolean validarCPF(String cpf){

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

    public static boolean validarCNPJ(String cnpj){
        String cnpjFormatado;

        // deixa só os números
        cnpjFormatado = cnpj.replaceAll("-", "");
        cnpjFormatado = cnpjFormatado.replaceAll("\\.", "");
        cnpjFormatado = cnpjFormatado.replaceAll("/", "");

        if (cnpjFormatado.length() != 14)
            return false;

        int[] pesos1 = {5,4,3,2,9,8,7,6,5,4,3,2};
        int[] pesos2 = {6,5,4,3,2,9,8,7,6,5,4,3,2};

        int soma = 0;

        // Dígitos finais dados pelo usuário
        int penultimoDigito = Character.getNumericValue(cnpjFormatado.charAt(12));
        int ultimoDigito = Character.getNumericValue(cnpjFormatado.charAt(13));

        for (int i = 0; i < cnpjFormatado.length() - 2; i++)
            soma += Character.getNumericValue(cnpjFormatado.charAt(i)) * pesos1[i];
        
        // verifica se o penúltimo dígito informado bate com o calculado
        if (soma % 11 < 2) {
            if (penultimoDigito != 0) 
                return false;
        } else{
            if (penultimoDigito != 11 - soma % 11)
                return false;
        }

        soma = 0;

        for (int i = 0; i < cnpjFormatado.length() - 1; i++)
            soma += Character.getNumericValue(cnpjFormatado.charAt(i)) * pesos2[i];

        // verifica se o último dígito informado bate com o calculado
        if (soma % 11 < 2) {
            if (ultimoDigito != 0) 
                return false;
        } else{
            if (ultimoDigito != 11 - soma % 11)
                return false;
        }

        return true;
    }

    public static boolean validarNome(String nome){
        for (int i = 0; i < 10; i++) 
            if (nome.contains(Integer.toString(i)))
                return false;

        return true;
    }

    public static boolean validarData(String data){
        
        if (data.length() != 10 || data.charAt(2) != '/' || data.charAt(5) != '/')
            return false;

        int [] posicoesInt = {0,1,3,4,6,7,8,9};

        for (int i : posicoesInt) {
            try {
                Integer.parseInt(data.charAt(i) + "");
            } catch (Exception e) {
                return false;
            }
        }
        
        int primeiro = Integer.parseInt((data.charAt(0) + "") + (data.charAt(1) + ""));
        int segundo = Integer.parseInt((data.charAt(3) + "") + (data.charAt(4) + ""));
        int terceiro = Integer.parseInt((data.charAt(6) + "") + (data.charAt(7) + "") + (data.charAt(8) + "") + (data.charAt(9) + ""));



        if (primeiro > 12 || primeiro < 1 || segundo > 12 || segundo < 1 || terceiro < 0) // não existiam seguradoras antes do ano 0
            return false;            


        return true;

    }   
}