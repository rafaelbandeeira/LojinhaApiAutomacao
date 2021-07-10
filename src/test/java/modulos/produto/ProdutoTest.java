package modulos.produto;

import dataFactory.ProdutoDataFactory;
import dataFactory.UsuarioDataFactory;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@DisplayName("Testes de API Rest do módulo de Produto")
public class ProdutoTest {
    private String token;

    @BeforeEach
    public void beforeEach() {
        baseURI = "http://165.227.93.41";
        basePath = "lojinha";

        this.token =
                given()
                    .contentType(ContentType.JSON)
                    .body(UsuarioDataFactory.acessarComUsuario(
                            "admin",
                            "admin"))
                .when()
                    .post("/v2/login")
                .then()
                    .extract()
                        .path("data.token");

    }

    @Test
    @DisplayName("Validar que o valor 0.00 é proibido")
    public void testValidarLimitesZeradoProibidoValorProduto() {
        given()
                .contentType(ContentType.JSON)
                .header("token", token)
                .body(ProdutoDataFactory.criarProdutoComumComOValorIgualA(0.00))
            .when()
                .post("/v2/produtos")
            .then()
                .assertThat()
                    .body("error", equalTo(
                            "O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00"
                    ))
                    .statusCode(422);
    }

    @Test
    @DisplayName("Validar que o valor 7000.01 é proibido")
    public void testValidarLimitesSuperiorProibidoValorProduto() {
        given()
            .contentType(ContentType.JSON)
            .header("token", token)
            .body(ProdutoDataFactory.criarProdutoComumComOValorIgualA(7000.01))
        .when()
            .post("/v2/produtos")
        .then()
            .assertThat()
                .body("error", equalTo(
                    "O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00"
                ))
                .statusCode(422);
    }
}
