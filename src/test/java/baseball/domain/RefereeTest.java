package baseball.domain;

import baseball.common.exception.InvalidGameStatusException;
import baseball.presentation.GameStatus;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.UUID.randomUUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RefereeTest {

    @Test
    void 컴퓨터와_유저의_숫자가_모두_같을때_참이다() throws IllegalAccessException {
        // given
        List<Integer> computerDigits = List.of(1, 2, 3);
        List<Integer> userDigits = List.of(1, 2, 3);

        String gameId = randomUUID().toString();
        int tryCount = 1;
        ComputerDigit computerDigit = new ComputerDigit(gameId, computerDigits);
        UserDigit userDigit = new UserDigit(gameId, tryCount, userDigits);

        Referee referee = new Referee();

        // when
        boolean result = referee.judge(computerDigit, userDigit);

        // then
        assertThat(result).isTrue();
    }

    @Test
    void 컴퓨터와_유저의_숫자가_하나라도_다르면_거짓이다() throws IllegalAccessException {
        // given
        List<Integer> computerDigits = List.of(1, 2, 3);
        List<Integer> userDigits = List.of(1, 2, 4);

        String gameId = randomUUID().toString();
        int tryCount = 1;
        ComputerDigit computerDigit = new ComputerDigit(gameId, computerDigits);
        UserDigit userDigit = new UserDigit(gameId, tryCount, userDigits);

        Referee referee = new Referee();

        // when
        boolean result = referee.judge(computerDigit, userDigit);

        // then
        assertThat(result).isFalse();
    }

    @Test
    void 게임아이디가_다르면_에러를_반환한다() throws IllegalAccessException {
        // given
        List<Integer> computerDigits = List.of(1, 2, 3);
        List<Integer> userDigits = List.of(1, 2, 3);

        String gameId1 = randomUUID().toString();
        String gameId2 = randomUUID().toString();

        int tryCount = 1;
        ComputerDigit computerDigit = new ComputerDigit(gameId1, computerDigits);
        UserDigit userDigit = new UserDigit(gameId2, tryCount, userDigits);

        Referee referee = new Referee();

        // when && then
        assertThatThrownBy(() -> referee.judge(computerDigit, userDigit))
                .isInstanceOf(IllegalAccessException.class)
                .hasMessage("gameId가 일치하지 않습니다.");    }


    @Test
    void 심판콜을_생성한다() throws IllegalAccessException {
        // given
        List<Integer> computerDigits = List.of(1, 2, 3);
        List<Integer> userDigits = List.of(3, 2, 1);
        String message = "2볼 1스트라이크";

        String gameId = randomUUID().toString();
        int tryCount = 1;
        ComputerDigit computerDigit = new ComputerDigit(gameId, computerDigits);
        UserDigit userDigit = new UserDigit(gameId, tryCount, userDigits);

        Referee referee = new Referee();

        // when
        referee.judge(computerDigit, userDigit);
        String callMessage = referee.getCallMessage();

        // then
        assertThat(callMessage).isEqualTo(message);
    }

    @Test
    void 낫싱_심판콜을_생성한다() throws IllegalAccessException {
        // given
        List<Integer> computerDigits = List.of(1, 2, 3);
        List<Integer> userDigits = List.of(4, 5, 6);
        String message = "낫싱";

        String gameId = randomUUID().toString();
        int tryCount = 1;
        ComputerDigit computerDigit = new ComputerDigit(gameId, computerDigits);
        UserDigit userDigit = new UserDigit(gameId, tryCount, userDigits);

        Referee referee = new Referee();

        // when
        referee.judge(computerDigit, userDigit);
        String callMessage = referee.getCallMessage();

        // then
        assertThat(callMessage).isEqualTo(message);
    }
}