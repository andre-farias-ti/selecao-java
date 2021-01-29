package com.indra.selecaoJava.exception;

    import lombok.Getter;

public class ExceptionDefault extends RuntimeException{

    private static final long serialVersionUID = -6930779424249682830L;

    @Getter
    private ExceptionsMessagesEnum msgEnum;

    public ExceptionDefault(String key, String msg) {

        super(msg);
        this.msgEnum = ExceptionsMessagesEnum.getEnum(key);

    }

    public ExceptionDefault(ExceptionsMessagesEnum globalRegistroNaoEncontrado) {

        super(globalRegistroNaoEncontrado.getMessage());
        this.msgEnum = globalRegistroNaoEncontrado;

    }

    public static void checkThrow(boolean expression, ExceptionsMessagesEnum exceptionsMessagesEnum) throws ExceptionDefault {

        if (expression) {
            exceptionsMessagesEnum.raise();
        }

    }


    public static void checkThrow(boolean expression, ExceptionsMessagesEnum exceptionsMessagesEnum, String msg) throws ExceptionDefault {

        if (expression) {
            exceptionsMessagesEnum.raise(msg);
        }

    }

}
