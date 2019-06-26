package daniellopes.treinamento.trabalhoartigodaves.Util;

import daniellopes.treinamento.trabalhoartigodaves.Model.Usuario;
import daniellopes.treinamento.trabalhoartigodaves.Model.UsuarioLogin;

public class UsuarioUtils {

    private static UsuarioLogin _usuarioLogado;

    public UsuarioUtils(UsuarioLogin usuario) {
        _usuarioLogado = usuario;
    }

    public static UsuarioLogin getUsuarioLogado() {
        return _usuarioLogado;
    }
}
