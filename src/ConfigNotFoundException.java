class ConfigNotFoundException extends RuntimeException {
    public ConfigNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
