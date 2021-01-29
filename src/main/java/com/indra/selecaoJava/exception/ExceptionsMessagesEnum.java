package com.indra.selecaoJava.exception;

import com.indra.selecaoJava.util.ExceptionUtil;
import lombok.Getter;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.NOT_IMPLEMENTED;
import static org.springframework.http.HttpStatus.REQUEST_TIMEOUT;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

public enum ExceptionsMessagesEnum {

    USUARIO_NAO_ENCONTRADO(BAD_REQUEST.value(), "usuario.nao.encontrado", BadRequestExceptionAPI.class),
    INFO_NAO_ENCONTRADA(BAD_REQUEST.value(), "info.nao.encontrada", BadRequestExceptionAPI.class),
    ERRO_LEITURA_ARQUIVO(BAD_REQUEST.value(), "erro.leitura.arquivo", BadRequestExceptionAPI.class),
    ERRO_BUSCAR_AVGVALORVENDA(BAD_REQUEST.value(), "erro.buscar.avgvalorvenda", BadRequestExceptionAPI.class),
    ERRO_BUSCAR_AVGVALORCOMPRA(BAD_REQUEST.value(), "erro.buscar.avgvalorcompra", BadRequestExceptionAPI.class),
    GLOBAL_ERRO_BANCO(BAD_REQUEST.value(), "erro.global.banco", BadRequestExceptionAPI.class),
    GLOBAL_ERRO_SERVIDOR(INTERNAL_SERVER_ERROR.value(), "erro.interno.servidor", ServerErrorExceptionAPI.class);

    @Getter
    private Integer codigo;
    @Getter
    private String key;
    @Getter
    private String msg;
    @Getter
    private Class<? extends ExceptionDefault> klass;

    ExceptionsMessagesEnum(int codigo, String key, Class<? extends ExceptionDefault> klass) {

        this.key = key;
        this.klass = klass;
        this.codigo = codigo;

    }

    public static ExceptionsMessagesEnum getEnum(String key) {

        for (ExceptionsMessagesEnum e : ExceptionsMessagesEnum.values()) {

            if (e.getKey().equals(key)) {
                return e;
            }

        }

        return null;

    }

    public String getMessage() {

        return ExceptionUtil.getMessage(this.key);

    }

    public void raise() {
        raiseException(getMessage());
    }

    public void raise(String textoDinamico) {

        raiseException(textoDinamico);

    }


    private void raiseException(String msg) {

        if (this.badRequest()) {

            throw new BadRequestExceptionAPI(key, msg);

        } else if (this.serverError()) {

            throw new ServerErrorExceptionAPI(key, msg);

        } else if (this.unauthorized()) {

            throw new UnauthorizedExceptionAPI(key, msg);

        } else if (this.forbidden()) {

            throw new ForbiddenExceptionAPI(key, msg);

        } else if (this.notFound()) {

            throw new NotFoundExceptionAPI(key, msg);

        } else if (this.timeout()) {

            throw new TimeoutExceptionAPI(key, msg);

        } else if (this.notImplemented()) {

            throw new NotImplementedExceptionAPI(key, msg);
        }
    }

    /**
     *
     * Método responsável pela validação dos códigos de erro com código 400.
     *
     */
    private Boolean badRequest() {

        return this.codigo == BAD_REQUEST.value();
    }

    /**
     *
     * Método responsável pela validação dos códigos de erro com código 401.
     *
     */
    private Boolean unauthorized() {

        return this.codigo == UNAUTHORIZED.value();
    }

    /**
     *
     * Método responsável pela validação dos códigos de erro com código 403.
     *
     */
    private Boolean forbidden() {

        return this.codigo == FORBIDDEN.value();
    }

    /**
     *
     * Método responsável pela validação dos códigos de erro com código 404.
     *
     */
    private Boolean notFound() {

        return this.codigo == NOT_FOUND.value();
    }

    /**
     *
     * Método responsável pela validação dos códigos de erro com código 408.
     *
     */
    private Boolean timeout() {

        return this.codigo == REQUEST_TIMEOUT.value();
    }

    /**
     *
     * Método responsável pela validação dos códigos de erro comcódigo 500.
     *
     */
    private Boolean serverError() {

        return this.codigo == INTERNAL_SERVER_ERROR.value();
    }

    /**
     *
     * Método responsável pela validação dos códigos de erro comcódigo 501.
     *
     */
    private Boolean notImplemented() {

        return this.codigo == NOT_IMPLEMENTED.value();
    }


}