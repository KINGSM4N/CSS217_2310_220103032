class SmsSender implements Sender {
    @Override
    public void send(String message) {
        System.out.println("[SMS] " + message);
    }
}



