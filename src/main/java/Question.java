/**
 * Created by Bob on 2016-04-05.
 */
public class Question {
    private String question;
    private String response;
    private int column;

    public Question () {
        this.question = "";
        this.response = "";
        this.column = 0;
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

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
