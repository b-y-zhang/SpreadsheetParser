/**
 * Created by Bob on 2016-04-05.
 */
public class Question {
    private String question;
    private String response;

    public Question () {
        this.question = "";
        this.response = "";
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
