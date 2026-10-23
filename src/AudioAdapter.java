public class AudioAdapter implements MediaPlayer {
    private final AdvancedAudioPlayer player;

    public AudioAdapter(AdvancedAudioPlayer player) {
        this.player = player;
    }

    @Override
    public void play(String format, String file) {
        if (format == null) {
            throw new IllegalArgumentException("Format must not be null");
        }
        switch (format.toLowerCase()) {
            case "mp4":
                player.playMp4(file);
                break;
            case "vlc":
                player.playVlc(file);
                break;
            default:
                throw new IllegalArgumentException("Unsupported format: " + format);
        }
    }

    public static void main(String[] args) {
        final String STUDENT_ID = "220103032";
        System.out.println("Student ID: " + STUDENT_ID);

        MediaPlayer player = new AudioAdapter(new AdvancedAudioPlayer());
        player.play("mp4", "test_" + STUDENT_ID + ".mp4");
        player.play("vlc", "test_" + STUDENT_ID + ".vlc");
    }
}