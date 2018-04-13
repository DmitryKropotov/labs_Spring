import lab.model.Country;
import lab.model.Person;
import lab.model.UsualPerson;
import lombok.experimental.FieldDefaults;
import org.junit.After;
import org.junit.Before;
import  org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import static lombok.AccessLevel.PRIVATE;
import static org.junit.jupiter.api.Assertions.assertEquals;

@FieldDefaults(level = PRIVATE)
public class Main {

    static final String APPLICATION_CONTEXT_XML_FILE_NAME = "src/test/resources/ioc.xml";

    UsualPerson expectedPerson;

    AbstractApplicationContext context;

    @Before
    public void setUp() throws Exception {
        context = new FileSystemXmlApplicationContext(
                new String[] { APPLICATION_CONTEXT_XML_FILE_NAME });
        expectedPerson = getExpectedPerson();
    }

    @Test
    public void testInitPerson() {
        UsualPerson person = (UsualPerson) context.getBean("person", Person.class);
        assertEquals(expectedPerson, person);
        System.out.println(person);
    }

    private UsualPerson getExpectedPerson() {
        Country country = Country.builder().id(1).name("Russia").codeName("RU").build();
        return  UsualPerson.builder().name("John Smith").age(35).country(country).build();
    }

    @After
    public void tearDown() throws Exception{
        if (context != null)
            context.close();
    }
}
