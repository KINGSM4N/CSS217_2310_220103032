public class UserRegistryAdapter implements UserRegistry {
    private final LegacyUserFeed feed;

    public UserRegistryAdapter(LegacyUserFeed feed) {
        this.feed = feed;
    }

    @Override
    public UserProfile fetchUser() {
        String raw = feed.getRawRecord();
        if (raw == null || raw.isBlank()) {
            throw new IllegalArgumentException("Raw record is null or empty");
        }

        // limit -1 keeps trailing empty fields, so "a#b#" gives 3 parts, not 2
        String[] parts = raw.split("#", -1);
        if (parts.length != 3) {
            throw new IllegalArgumentException(
                    "Invalid format, expected 3 fields separated by '#', got " + parts.length + ": " + raw);
        }

        String studentId = parts[0].trim();
        String email = parts[1].trim();
        String levelText = parts[2].trim();

        if (studentId.isEmpty() || email.isEmpty() || levelText.isEmpty()) {
            throw new IllegalArgumentException("Empty field in record: " + raw);
        }
        if (!email.contains("@")) {
            throw new IllegalArgumentException("Invalid email: " + email);
        }

        int accessLevel;
        try {
            accessLevel = Integer.parseInt(levelText);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Access level is not a number: " + levelText, e);
        }

        return new UserProfile(studentId, email, accessLevel);
    }

    public static void main(String[] args) {
        final String STUDENT_ID = "220103032";
        System.out.println("Student ID: " + STUDENT_ID);

        UserRegistry registry = new UserRegistryAdapter(
                new LegacyUserFeed(STUDENT_ID + "#user@college.edu#4"));

        UserProfile profile = registry.fetchUser();
        System.out.println(profile);
    }
}