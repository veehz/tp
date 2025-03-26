package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Set;

import org.junit.jupiter.api.Test;

public class LinkTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Link(null));
    }

    @Test
    public void constructor_invalidLink_throwsIllegalArgumentException() {
        String invalidLink = "";
        assertThrows(IllegalArgumentException.class, () -> new Link(invalidLink));
    }

    @Test
    public void isValidLink() {
        // null link
        assertThrows(NullPointerException.class, () -> Email.isValidEmail(null));

        // blank link
        assertFalse(Link.isValidLink("")); // empty string
        assertFalse(Link.isValidLink(" ")); // spaces only

        // missing parts
        assertFalse(Link.isValidLink("www.google.com")); // invalid prefix

        // invalid parts
        assertFalse(Link.isValidLink("www.nusmods.com/timetable/sem-2/share?CS2101")); // missing https
        // valid link
        assertTrue(Link.isValidLink("https://nusmods.com/timetable/sem-2/share?CS2101"));
        assertTrue(Link.isValidLink("https://nusmods.com/timetable/sem-1/share?"));
    }

    @Test
    public void extractCodes() {
        // assertTrue(true);
        Set<String> codes =
            Link.extractCodes("https://nusmods.com/timetable/sem-2/share?CP4101=&CS2101=&CS2103T=LEC:G12");
        assertTrue(codes.contains("CP4101"));
        assertTrue(codes.contains("CS2101"));
        assertTrue(codes.contains("CS2103T"));
        assertTrue(codes.size() == 3);
        codes =
            Link.extractCodes("https://nusmods.com/timetable/st-ii/share?AH3550=&CS1010E=TUT:05,SEC:1&MA3289=");
        assertTrue(codes.size() == 3);
    }

    @Test
    public void equals() {
        Link link = new Link("https://nusmods.com/timetable/sem-2/share?CS2101");

        // same values -> returns true
        assertTrue(link.equals(new Link("https://nusmods.com/timetable/sem-2/share?CS2101")));

        // same object -> returns true
        assertTrue(link.equals(link));

        // null -> returns false
        assertFalse(link.equals(null));

        // different types -> returns false
        assertFalse(link.equals(5.0f));

        // different values -> returns false
        assertFalse(link.equals(new Link("https://nusmods.com/timetable/sem-2/share?MA3211=LEC:1,TUT:2")));
    }
}
