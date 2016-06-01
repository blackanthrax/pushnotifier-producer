package com.github.blackanthrax.pushnotifier.producer.messaging;

public class MessageSendingException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 134745867L;

    public MessageSendingException() {
        super();
    }

    public MessageSendingException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public MessageSendingException(String message, Throwable cause) {
        super(message, cause);
    }

    public MessageSendingException(String message) {
        super(message);
    }

    public MessageSendingException(Throwable cause) {
        super(cause);
    }

}
