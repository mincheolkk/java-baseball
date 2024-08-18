package baseball.domain;

import static org.assertj.core.api.Assertions.assertThat;

import baseball.presentation.FixedNumberGenerator;
import baseball.presentation.NumberGenerator;
import baseball.presentation.RandomNumberGenerator;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import java.util.List;

class ComputerTest {

    private NumberGenerator randomNumberGenerator = new RandomNumberGenerator();
    private NumberGenerator fixedNumberGenerator = new FixedNumberGenerator();

    private Computer computer = new Computer(randomNumberGenerator);

    @Test
    void 길이를_검증한다() {
        // when
        List<Integer> result = computer.generateRandomDigits();

        // then
        assertThat(result.size()).isEqualTo(3);
    }

    @Test
    void 중복을_검증한다() {
        // when
        List<Integer> result = computer.generateRandomDigits();

        // then
        assertThat(result).doesNotHaveDuplicates();
    }

    @Test
    void 랜덤_숫자생성기를_주입받으면_숫자생성이_정상완료된다() throws InterruptedException {
        // given
        CountDownLatch latch = new CountDownLatch(1);
        Thread testThread = new Thread(() -> {
            try {
                computer.generateRandomDigits();
            } finally {
                latch.countDown();
            }
        });

        // when
        testThread.start();

        // then
        boolean result = latch.await(1, TimeUnit.SECONDS);
        assertThat(result).isTrue();
    }

    @Test
    void 고정된_숫자생성기를_주입받으면_무한루프에_빠진다() throws InterruptedException {
        // given
        Computer fakeComputer = new Computer(fixedNumberGenerator);
        CountDownLatch latch = new CountDownLatch(1);
        Thread testThread = new Thread(() -> {
            try {
                fakeComputer.generateRandomDigits();
            } finally {
                latch.countDown();
            }
        });

        // when
        testThread.start();

        // then
        boolean result = latch.await(1, TimeUnit.SECONDS);
        assertThat(result).isFalse();
    }
}