package seedu.address.model.person;
import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Represents a Person's NUSMods link in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidLink(String)}
 */
public class Link {
    public static final String MESSAGE_CONSTRAINTS =
            "Link needs to be a valid NUSMods timetable link, e.g., "
            + "https://nusmods.com/timetable/sem-1/share?CS1010=TUT:06,LAB:E07";
    private static final Pattern PATH_PATTERN =
        Pattern.compile("^/timetable/(sem-(?:1|2)|st-(?:i{1,2}))/share$");
    public final String value;
    /**
     * Constructs an {@code Link}.
     *
     * @param link A valid NUSMods timetable link.
     */
    public Link(String link) {
        requireNonNull(link);
        checkArgument(isValidLink(link), MESSAGE_CONSTRAINTS);
        value = link;
    }
    /**
     * Extract Module Codes from a given link.
     */
    public static Set<String> extractCodes(String link) {
        Set<String> codes = new HashSet<>();
        int queryStart = link.indexOf('?');
        if (queryStart == -1 || queryStart == link.length() - 1) {
            return codes; // no query parameters
        }

        String queryString = link.substring(queryStart + 1);
        String[] pairs = queryString.split("&");
        for (String pair : pairs) {
            int equalPos = pair.indexOf('=');
            if (equalPos != -1) {
                codes.add(pair.substring(0, equalPos));
            } else {
                codes.add(pair); // handles case with no '='
            }
        }
        return codes;
    }
    /**
     * Returns if a given string is a valid NUSMods timetable link.
     */
    public static boolean isValidLink(String test) {
        try {
            URI uri = new URI(test);
            // Validate scheme and host.
            if (!"https".equals(uri.getScheme())
                    || !"nusmods.com".equals(uri.getHost())) {
                return false;
            }
            // Validate path against the expected pattern.
            String path = uri.getPath();
            if (path == null || !PATH_PATTERN.matcher(path).matches()) {
                return false;
            }
            String query = uri.getQuery();
            if (query != null && !query.trim().isEmpty()) {
                // Validate query parameters: each should be a key=value pair (value may be empty).
                String[] pairs = query.split("&");
                for (String pair : pairs) {
                    if (pair.isEmpty()) {
                        return false;
                    }
                    String[] keyValue = pair.split("=", 2);
                    if (keyValue[0].trim().isEmpty()) {
                        return false;
                    }
                }
            }
            return true;
        } catch (URISyntaxException e) {
            return false;
        }
    }
    @Override
    public String toString() {
        return value;
    }
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Link)) {
            return false;
        }
        Link otherLink = (Link) other;
        return value.equals(otherLink.value);
    }
    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
