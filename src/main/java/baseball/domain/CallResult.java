package baseball.domain;

public enum CallResult {

    BALL_CALL("볼"),
    STRIKE_CALL("스트라이크"),
    NOTHING_CALL("낫싱")
    ;

    private final String description;

    CallResult(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
