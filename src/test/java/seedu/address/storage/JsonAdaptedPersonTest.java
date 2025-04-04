package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Email;
import seedu.address.model.person.Housing;
import seedu.address.model.person.Link;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.testutil.PersonBuilder;

public class JsonAdaptedPersonTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_YEAR = "0";
    private static final String INVALID_MAJOR = " ";
    private static final String INVALID_HOUSING = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_LINK = "www.nusmods.com";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_YEAR = "1";
    private static final String VALID_MAJOR = "Computer Science";
    private static final String VALID_HOUSING = BENSON.getHousing().toString();
    private static final String VALID_LINK =
            "https://nusmods.com/timetable/sem-2/share?CS2103T=LEC:G12";
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void constructor_nonmandatoryFieldsNull_success() {
        Person person = new PersonBuilder().withEmail(null).withHousing(null).withLink(null)
                .withMajor(null).withPhone(null).withYear(null).build();
        assertDoesNotThrow(() -> new JsonAdaptedPerson(person));
    }

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_YEAR,
                        VALID_MAJOR, VALID_HOUSING, VALID_LINK, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(null, VALID_PHONE, VALID_EMAIL, VALID_YEAR,
                VALID_MAJOR, VALID_HOUSING, VALID_LINK, VALID_TAGS);
        String expectedMessage =
                String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_YEAR,
                        VALID_MAJOR, VALID_HOUSING, VALID_LINK, VALID_TAGS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_doesNotThrow() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, null, VALID_EMAIL, VALID_YEAR,
                VALID_MAJOR, VALID_HOUSING, VALID_LINK, VALID_TAGS);
        assertDoesNotThrow(person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_YEAR,
                        VALID_MAJOR, VALID_HOUSING, VALID_LINK, VALID_TAGS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_doesNotThrow() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, null, VALID_YEAR,
                VALID_MAJOR, VALID_HOUSING, VALID_LINK, VALID_TAGS);
        assertDoesNotThrow(person::toModelType);
    }

    @Test
    public void toModelType_invalidYear_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_YEAR,
                        VALID_MAJOR, VALID_HOUSING, VALID_LINK, VALID_TAGS);
        String expectedMessage = seedu.address.model.person.Year.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullYear_doesNotThrow() throws Exception {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, null,
                        VALID_MAJOR, VALID_HOUSING, VALID_LINK, VALID_TAGS);
        assertDoesNotThrow(person::toModelType);
    }

    @Test
    public void toModelType_invalidMajor_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_YEAR,
                        INVALID_MAJOR, VALID_HOUSING, VALID_LINK, VALID_TAGS);
        String expectedMessage = seedu.address.model.person.Major.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullMajor_doesNotThrow() throws Exception {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_YEAR,
                        null, VALID_HOUSING, VALID_LINK, VALID_TAGS);
        assertDoesNotThrow(person::toModelType);
    }

    @Test
    public void toModelType_invalidHousing_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_YEAR,
                        VALID_MAJOR, INVALID_HOUSING, VALID_LINK, VALID_TAGS);
        String expectedMessage = Housing.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullHousing_doesNotThrow() throws Exception {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_YEAR,
                        VALID_MAJOR, null, VALID_LINK, VALID_TAGS);
        assertDoesNotThrow(person::toModelType);
    }

    @Test
    public void toModelType_invalidLink_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_YEAR,
                        VALID_MAJOR, VALID_HOUSING, INVALID_LINK, VALID_TAGS);
        String expectedMessage = Link.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullLink_doesNotThrow() throws Exception {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_YEAR,
                        VALID_MAJOR, VALID_HOUSING, null, VALID_TAGS);
        assertDoesNotThrow(person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_YEAR,
                        VALID_MAJOR, VALID_HOUSING, VALID_LINK, invalidTags);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

}
