package ru.otus.springcourse.studenttestingboot.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.springcourse.studenttestingboot.domain.Person;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@SpringBootTest
class PersonServiceImplTest {

    @MockBean
    private IOService ioService;
    @Autowired
    private PersonService personService;

    @BeforeEach
    void setUp() {
        given(ioService.printAndRead(any())).willReturn("IVAN");
    }
    private Person person = new Person("IVAN", "IVAN");

    @Test
    @DisplayName("requestPersonInfo Should Return Person")
    void requestPersonInfoShouldReturnPerson() {
        assertThat(personService.requestPersonInfo().toString()).isEqualTo(person.toString());
    }
}