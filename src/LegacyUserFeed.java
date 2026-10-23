record UserProfile(String studentId, String email, int accessLevel) {}

public class LegacyUserFeed {
    private final String rawRecord;

    public LegacyUserFeed(String rawRecord) {
        this.rawRecord = rawRecord;
    }

    public String getRawRecord() {
        return rawRecord;
    }

}
