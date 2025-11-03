package lotto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("로또 랭크 검증")
class LottoCheckerTest {
    @Test
    @DisplayName("1등 테스트")
    void 일등__테스트(){
        //given
        LottoChecker lottoChecker = new LottoChecker();
        WinningLotto winningLotto = new WinningLotto(List.of(1,2,3,4,5,6), 7);
        Lotto lotto = new Lotto(List.of(1,2,3,4,5,6));

        //when
        Rank rank = lottoChecker.checkRank(lotto, winningLotto);

        //then
        assertEquals(Rank.FIRST, rank);
    }

    @Test
    @DisplayName("2등 테스트")
    void 이등_테스트(){
        //given
        LottoChecker lottoChecker = new LottoChecker();
        WinningLotto winningLotto = new WinningLotto(List.of(1,2,3,4,5,6), 7);
        Lotto lotto = new Lotto(List.of(1,2,7,4,5,6));

        //when
        Rank rank = lottoChecker.checkRank(lotto, winningLotto);

        //then
        assertEquals(Rank.SECOND, rank);
    }

    @Test
    @DisplayName("3등 테스트")
    void 삼등_테스트(){
        //given
        LottoChecker lottoChecker = new LottoChecker();
        WinningLotto winningLotto = new WinningLotto(List.of(1,2,3,4,5,6), 7);
        Lotto lotto = new Lotto(List.of(1,2,8,4,5,6));

        //when
        Rank rank = lottoChecker.checkRank(lotto, winningLotto);

        //then
        assertEquals(Rank.THIRD, rank);
    }

    @Test
    @DisplayName("4등 테스트")
    void 사등_테스트(){
        //given
        LottoChecker lottoChecker = new LottoChecker();
        WinningLotto winningLotto = new WinningLotto(List.of(1,2,3,4,5,6), 7);
        Lotto lotto = new Lotto(List.of(1,2,8,9,5,6));

        //when
        Rank rank = lottoChecker.checkRank(lotto, winningLotto);

        //then
        assertEquals(Rank.FOURTH, rank);
    }

    @Test
    @DisplayName("5등 테스트")
    void 오등_테스트(){
        //given
        LottoChecker lottoChecker = new LottoChecker();
        WinningLotto winningLotto = new WinningLotto(List.of(1,2,3,4,5,6), 7);
        Lotto lotto = new Lotto(List.of(8, 2, 9, 4, 5, 7));

        //when
        Rank rank = lottoChecker.checkRank(lotto, winningLotto);

        //then
        assertEquals(Rank.FIFTH, rank);
    }

    @Test
    @DisplayName("미스 테스트")
    void 미스_테스트(){
        //given
        LottoChecker lottoChecker = new LottoChecker();
        WinningLotto winningLotto = new WinningLotto(List.of(1,2,3,4,5,6), 7);
        Lotto lotto = new Lotto(List.of(7, 8, 9, 10, 11, 12));

        //when
        Rank rank = lottoChecker.checkRank(lotto, winningLotto);

        //then
        assertEquals(Rank.MISS, rank);
    }
}