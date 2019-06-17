package lotto.domain.lottoresult;

import lotto.domain.Rank;
import lotto.domain.lottonumber.LottoNumber;
import lotto.domain.lottonumber.LottoNumberPool;
import lotto.domain.lottoticket.LottoTicket;
import lotto.domain.lottoticket.LottoTickets;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoResultTest {
    private LottoResult lottoResult;

    @BeforeEach
    void setUp() {
        List<LottoNumber> lottoNumbers1 = Arrays.asList(LottoNumberPool.valueOf(1), LottoNumberPool.valueOf(2)
                , LottoNumberPool.valueOf(3), LottoNumberPool.valueOf(4), LottoNumberPool.valueOf(5)
                , LottoNumberPool.valueOf(6));
        List<LottoNumber> lottoNumbers2 = Arrays.asList(LottoNumberPool.valueOf(7), LottoNumberPool.valueOf(8)
                , LottoNumberPool.valueOf(9), LottoNumberPool.valueOf(10), LottoNumberPool.valueOf(11)
                , LottoNumberPool.valueOf(12));
        List<LottoNumber> lottoNumbers3 = Arrays.asList(LottoNumberPool.valueOf(13), LottoNumberPool.valueOf(14)
                , LottoNumberPool.valueOf(15), LottoNumberPool.valueOf(16), LottoNumberPool.valueOf(17)
                , LottoNumberPool.valueOf(18));
        List<LottoTicket> tickets = Arrays.asList(new LottoTicket(lottoNumbers1)
                , new LottoTicket(lottoNumbers2), new LottoTicket(lottoNumbers3));

        List<LottoNumber> winningTicketNumbers = Arrays.asList(LottoNumberPool.valueOf(1), LottoNumberPool.valueOf(2)
                , LottoNumberPool.valueOf(3), LottoNumberPool.valueOf(10), LottoNumberPool.valueOf(11)
                , LottoNumberPool.valueOf(12));
        LottoTicket winningTicket = new LottoTicket(winningTicketNumbers);
        LottoNumber bonusBall = LottoNumberPool.valueOf(4);
        WinningLotto winningLotto = WinningLotto.of(winningTicket, bonusBall);

        lottoResult = LottoResult.of(new LottoTickets(tickets), winningLotto);
    }

    @Test
    void initial_method_확인() {
        LottoResult initializedLottoResult = LottoResult.getInitialInstance();
        for (Rank rank : Rank.values()) {
            assertThat(initializedLottoResult.getCountsBy(rank)).isEqualTo(0);
        }
    }

    @Test
    void 총_상금을_구하는_메소드() {
        assertThat(lottoResult.sumAllPrize()).isEqualTo(10_000);
    }

    @Test
    void map의_count를_하나_증가시키는_메소드() {
        lottoResult.increaseOneCountOn(Rank.FIFTH);
        assertThat(lottoResult.getCountsBy(Rank.FIFTH)).isEqualTo(3);
    }

    @AfterEach
    void tearDown() {
        lottoResult = null;
    }
}