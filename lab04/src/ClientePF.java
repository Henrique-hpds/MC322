import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;


public class ClientePF extends Cliente {
    private final String cpf;
    private String genero;
    private Date dataLicensa;
    private String educacao;
    private Date dataNascimento;
    private String classeEconomica;

    public ClientePF(String nome, String endereco, Date dataLicensa, String educacao, String genero, String classeEconomica, String cpf, Date dataNascimento){
        super(nome, endereco);
        
        this.cpf = cpf;
        this.genero = genero;
        this.dataLicensa = dataLicensa;
        this.educacao = educacao;
        this.dataNascimento = dataNascimento;
        this.classeEconomica = classeEconomica;
    }

    public String getCpf(){
        return cpf;
    }

    public String getGenero(){
        return genero;
    }

    public Date getDataLicensa(){
        return dataLicensa;
    }

    public String getEducacao(){
        return educacao;
    }

    public Date getDataNascimento(){
        return dataNascimento;
    }

    public String getClasseEconomica(){
        return classeEconomica;
    }

    public void setGenero(String genero){
        this.genero = genero;
    }

    public void setDataLicensa(Date dataLicensa){
        this.dataLicensa = dataLicensa;
    }

    public void setEducacao(String educacao){
        this.educacao = educacao;
    }

    public void setDataNascimento(Date dataNascimento){
        this.dataNascimento = dataNascimento;
    }

    public void setClasseEconomica(String ClasseEconomica){
        this.classeEconomica = ClasseEconomica;
    }

    @Override
    public double calculaScore(){
        double fator_idade;
        long idade = ChronoUnit.YEARS.between(dataNascimento.toInstant().atZone(ZoneId.of("America/Sao_Paulo")).toLocalDate(), LocalDate.now());
    
        if (idade >= 18 && idade <= 30)
            fator_idade = CalcSeguro.FATOR_18_30.getFator();
        else if (idade > 30 && idade <= 60)
            fator_idade = CalcSeguro.FATOR_30_60.getFator();
        else if (idade > 60 && idade <= 90)
            fator_idade = CalcSeguro.FATOR_60_90.getFator();
        else fator_idade = 0; // idade muito baixa ou muito alta, não pode ter seguro

        return CalcSeguro.VALOR_BASE.getFator() * fator_idade * getListaVeiculos().size();
    }

    public String toString() {
        return super.toString() + "\n\nCPF: " + cpf + "\nGenero: " + genero + "\nDataLicensa: " + dataLicensa + "\nEducacao: " + educacao + "\nDataNascimento: " + dataNascimento + "\nClasseEconomica: " + classeEconomica + "\n---------------------------------------";
    }
   
}