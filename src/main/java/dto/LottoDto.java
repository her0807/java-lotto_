package dto;

import java.util.List;

public class LottoDto {
    private final List<Integer> lottoNumbers;

    public LottoDto(final List<Integer> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public List<Integer> getLottoNumbers() {
        return lottoNumbers;
    }
}
