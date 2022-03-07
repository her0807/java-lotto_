# java-lotto

로또 미션 저장소

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)

## 기능목록
- [x] 구입금액을 입력할 수 있다.
  - 1000원 단위로 받아야 한다.
- [x] 지난 주 당첨 번호를 입력할 수 있다.
  - 입력 방식
    - 6개의 숫자를 입력한다.
    - 당첨 번호는 1 ~ 45 사이의 정수
    - 대괄호 안에서 쉼표 + 공백으로 구분한다.
    - ex) ```1, 2, 3, 4, 5, 6```
- [x] 보너스 볼을 입력할 수 있다.
  - 보너스 볼은 1 ~ 45 사이의 정수
- [x] 로또번호가 자동으로 발급되어야 한다.
  - (투입금액 / 1000) 만큼 발급한다.
  - 입력 방식
    - 6개의 숫자를 발급한다.
    - 로또 번호는 1 ~ 45 사이의 정수
    - 대괄호 안에서 쉼표 + 공백으로 구분한다.
    - ex) ```[8, 21, 23, 41, 42, 43]```
- [x] 당첨을 판단해야 한다.
  - 각 로또당 일치한 숫자의 개수로 등수를 판단한다.
  - 3개 일치 : 5,000원
  - 4개 일치 : 50,000원
  - 5개 일치 : 1,500,000원 (150만원)
  - 5개 일치 + 보너스 볼 일치 : 30,000,000원 (3천만원)
  - 6개 일치 : 2,000,000,000원 (20억원)
- [x] 등수 별로 당첨된 개수를 판단 해야한다.
- [x] 수익률을 판단해야 한다.
  - 총 당첨금액 / 구입금액
  - 소숫점 2자리까지 나타낸다.
  - 총 수익률이 1미만이면 안내문구를 출력한다.
- [x] 수동으로 로또를 구매할 수 있어야 한다.