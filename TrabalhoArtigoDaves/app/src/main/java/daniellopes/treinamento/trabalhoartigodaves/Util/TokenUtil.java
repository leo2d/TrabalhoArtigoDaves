package daniellopes.treinamento.trabalhoartigodaves.Util;

public class TokenUtil {
    private static String token;

    public TokenUtil(String token) {
        TokenUtil.token = token;
    }

    public static String getToken() {
        return token;
    }

}
