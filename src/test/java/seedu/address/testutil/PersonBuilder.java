package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Email;
import seedu.address.model.person.Housing;
import seedu.address.model.person.Link;
import seedu.address.model.person.Major;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Year;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_HOUSING = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_YEAR = "1";
    public static final String DEFAULT_MAJOR = "Computer Science";
    public static final String DEFAULT_LINK = "https://nusmods.com/timetable/sem-1/share?CS1010=TUT:06,LAB:E07";

    private Name name;
    private Phone phone;
    private Email email;
    private Year year;
    private Major major;
    private Housing housing;
    private Link link;
    private Set<Tag> tags;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        year = Year.fromString(DEFAULT_YEAR);
        major = new Major(DEFAULT_MAJOR);
        housing = new Housing(DEFAULT_HOUSING);
        link = new Link(DEFAULT_LINK);
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        year = personToCopy.getYear();
        major = personToCopy.getMajor();
        housing = personToCopy.getHousing();
        link = personToCopy.getLink();
        tags = new HashSet<>(personToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Housing} of the {@code Person} that we are building.
     */
    public PersonBuilder withHousing(String housing) {
        if (housing == null) {
            this.housing = null;
            return this;
        }
        this.housing = new Housing(housing);
        return this;
    }

    /**
     * Sets the {@code Year} of the {@code Person} that we are building.
     */
    public PersonBuilder withYear(String year) {
        if (year == null) {
            this.year = null;
            return this;
        }
        this.year = Year.fromString(year);
        return this;
    }

    /**
     * Sets the {@code Major} of the {@code Person} that we are building.
     */
    public PersonBuilder withMajor(String major) {
        if (major == null) {
            this.major = null;
            return this;
        }
        this.major = new Major(major);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        if (phone == null) {
            this.phone = null;
            return this;
        }
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        if (email == null) {
            this.email = null;
            return this;
        }
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Link} of the {@code Person} that we are building.
     */
    public PersonBuilder withLink(String link) {
        if (link == null) {
            this.link = null;
            return this;
        }
        this.link = new Link(link);
        return this;
    }

    public Person build() {
        return new Person(name, phone, email, year, major, housing, link, tags);
    }

}
