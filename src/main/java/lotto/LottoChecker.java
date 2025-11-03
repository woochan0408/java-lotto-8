package lotto;

import java.util.List;
import java.util.Objects;

public class LottoChecker {

    public Rank checkRank(Lotto lotto, WinningLotto winningLotto){
        List<Integer> winningLottoNumbers = winningLotto.getNumbers();
        Integer bonusNumber = winningLotto.getBonusNumber();
        int numberHit = 0;
        boolean bonusNumberHit = false;

        for (Integer lottoNumber : lotto.getNumbers()){
            if(winningLottoNumbers.contains(lottoNumber)){
                numberHit++;
            }
            else if(Objects.equals(bonusNumber, lottoNumber)){
                bonusNumberHit = true;
            }
        }

        if(numberHit == 6){
            return Rank.FIRST;
        }
        else if (numberHit == 5) {
            if(bonusNumberHit){
                return Rank.SECOND;
            }
                return Rank.THIRD;
        }
        else if (numberHit == 4) {
            return Rank.FOURTH;
        }
        else if (numberHit == 3) {
            return Rank.FIFTH;
        }
        return Rank.MISS;
    }
}
