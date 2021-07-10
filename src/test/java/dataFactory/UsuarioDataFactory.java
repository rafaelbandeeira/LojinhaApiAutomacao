package dataFactory;

import pojo.UsuarioPOJO;

public class UsuarioDataFactory {
    public static UsuarioPOJO acessarComUsuario(
            String username, String password
    ) {
        UsuarioPOJO usuario = new UsuarioPOJO();
        usuario.setUsuarioLogin(username);
        usuario.setUsuarioSenha(password);

        return usuario;
    }
}
