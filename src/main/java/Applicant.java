import java.util.LinkedList;
import java.util.List;

/**
 * Created by Bob on 2016-04-03.
 */
public class Applicant {
    List<Question> questions;


    public Applicant () {
        this.questions = new LinkedList<Question>();
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
