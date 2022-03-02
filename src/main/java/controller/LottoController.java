package controller;

import domain.*;

import java.util.SortedMap;

import view.InputView;
import view.OutputView;

public class LottoController {

    private static final String ERROR_MESSAGE = "[ERROR] ";
    private static final String ERROR_BONUS_NUMBER_CONTAIN_MESSAGE = "지난주 당첨번호와 보너스가 중복일 수 없습니다.";

    public void start() {

        Money money = getMoney();
        Count manualCount = getManualCount(money);

        final LottoFactory lottoFactory = new LottoFactory(money);
        OutputView.printLotto(lottoFactory.issueLotto());

        Lotto lastWinLotto = getWinLotto();
        LottoNumber bonusNumber = getBonusNumber(lastWinLotto);

        SortedMap<RankPrize, Integer> rankCounts = lottoFactory.run(lastWinLotto, bonusNumber);
        int totalPrize = lottoFactory.calculatePrize(rankCounts);

        OutputView.printWinStatistics(rankCounts);
        OutputView.printWinProfit(lottoFactory.calculateProfit(totalPrize));
    }

    private Money getMoney() {
        try {
            return new Money(InputView.getMoney());
        } catch (IllegalArgumentException e) {
            System.out.println(ERROR_MESSAGE + e.getMessage());
            return getMoney();
        }
    }

    private Count getManualCount(Money money) {
        try {
            return Count.getManualCount(InputView.getManualCount(), money);
        } catch (IllegalArgumentException e) {
            System.out.println(ERROR_MESSAGE + e.getMessage());
            return getManualCount(money);
        }
    }

    private Lotto getWinLotto() {
        try {
            return new Lotto(InputView.getWinLotto());
        } catch (IllegalArgumentException e) {
            System.out.println(ERROR_MESSAGE + e.getMessage());
            return getWinLotto();
        }
    }

    private LottoNumber getBonusNumber(final Lotto lotto) {
        try {
            LottoNumber bonusNumber = new LottoNumber(InputView.getBonusNumber());
            if (isBonusNumberContain(lotto, bonusNumber)) {
                throw new IllegalArgumentException(ERROR_BONUS_NUMBER_CONTAIN_MESSAGE);
            }
            return bonusNumber;
        } catch (IllegalArgumentException e) {
            System.out.println(ERROR_MESSAGE + e.getMessage());
            return getBonusNumber(lotto);
        }
    }

    private boolean isBonusNumberContain(final Lotto lotto, final LottoNumber bonusNumber) {
        return lotto.isContainNumber(bonusNumber);
    }
}
