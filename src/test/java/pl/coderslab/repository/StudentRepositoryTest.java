package pl.coderslab.repository;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import pl.coderslab.hero.Student;

import java.util.List;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class StudentRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private StudentRepository studentRepository;


    @Test
    public void find_by_first_name_then_return_student(){
        //given
        Student john = new Student();
        john.setFirstName("John");
        entityManager.persist(john);
        //when
        Student result = studentRepository.findOneByFirstName("John");
        //then
        assertEquals(result.getFirstName(), john.getFirstName());
    }

    @Test
    @Ignore
    public void given_mark_then_find_john_should_not_be_null(){
        //given
        Student mark = new Student();
        mark.setFirstName("Mark");
        entityManager.persist(mark);

        //when
        Student result = studentRepository.findOneByFirstName("John");

        //then
        assertNull(result);
    }

    @Test
    @Ignore
    public void given_jo_and_john_then_find_jo_should_return_two_elements() {
        // given
        Student jo = entityManager.persistAndFlush(new Student("jo"));
        Student john = entityManager.persistAndFlush(new Student("john"));
        // when
        List<Student> result = studentRepository.findBySome("jo");
        // then
        assertThat(result).containsExactly(jo, john);
    }
}