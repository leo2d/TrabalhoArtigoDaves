package daniellopes.treinamento.trabalhoartigodaves.Util;

public class SituacaoEvento {

    public static String  EmSubmissao = "EmSubmissao";
    public static String  Aberto = "Aberto";
    public static String  Fechado = "Fechado";
    //public static String  AguardandoEvento = "AguardandoEvento";

    public static boolean eventoFechado(String status){
        return status.toLowerCase()
                .equals(SituacaoEvento.Fechado.toLowerCase());
    }
    public static boolean eventoAberto(String status){
        return status.toLowerCase()
                .equals(SituacaoEvento.Aberto.toLowerCase());
    }
}
