# 🔁 Quing
큐잉(Queue+ing)은 음식점 대기순서를 관리 해주는 서비스입니다.

Swagger : http://quing.shop/swagger-ui/index.html

# 프로젝트 목표
- 기술적인 측면에서 실력 성장하기
- SpringBoot에 대해 공부한것들을 응용해보기
- API Call을 하는것 만으로 동작이 충분히 예상 되도록 개발하기
- 협업에 대한 감 익히기
- 좋은 코드를 만들기 위해 많은 고민을 함께하기

# 설계

## 서비스 구성 다이어그램
![image](https://user-images.githubusercontent.com/22807730/208409687-58cd84fd-21e3-4c55-9664-6b5a489c6a55.png)

## API Server 구성 다이어그램
![component](https://user-images.githubusercontent.com/22807730/191471325-862134f8-6d8f-4062-9b12-ecd541a6323a.png)

## 테이블 구조 다이어그램(ERD)
![erd](https://user-images.githubusercontent.com/22807730/191471281-661a34b7-bc23-4539-9451-44cc3e0b7c89.png)


## 기능 정의
### 주요 기능, 시나리오 

### 손님
- 로그인 : 별도의 회원가입 없이 `전화번호`를 통해 유저를 식별한다.
- 대기 등록 : 손님은 매장에 줄서기를 등록한다.
- 대기 등록(메뉴) : 손님은 메뉴를 지정하여 줄서기를 등록한다.
- 대기 현황 : 내 앞에 몇 팀이 남았는지 확인한다.
- 대기 취소 : 대기 의사가 없어졌을 때 취소를 할 수 있다.
- 리뷰 기능 : 손님은 매장에 리뷰를 등록/수정/삭제 할 수 있다.

### 매장
- 로그인 : 손님과 달리 매장은 `id + pw`를 통한 인증을 진행한다.
- 메뉴 기능 : 매장의 메뉴를 추가/삭제 할 수 있다.
- 대기줄 확인 : 매장의 대기줄의 상태를 확인한다.
- 손님 입장 : 매장은 대기중인 손님을 입장시킨다. (상태 : 대기→완료)
- 손님 대기 취소/복구 : 대기열에 있는 손님을 취소/복구한다. (상태를 취소→대기, 대기→취소 변경)
- 가게 영업시작/영업종료 설정을 할수있다. (영업중이 아닐땐 대기 불가)

### 줄서기
- 입장 대기 메시지 발송 : 입장순서가 가까워진 손님에게 입장 준비 메시지를 보낸다.

### 리뷰
- 기간별 별점 : 매장의 (전체/한달/1주)간의 별점을 하루 한번 갱신하여 제공한다.

### 메뉴
- 매장에 손님들이 주문했던 메뉴별 별점을 제공(전체/한달/1주)


## 시퀀스 다이어그램
- 사용자가 매장에 대기 등록
  ![seq1](https://user-images.githubusercontent.com/22807730/191471317-5faf3b71-b10f-45b2-af29-4ad70f2f55d3.png)

- 매장 : 현재 대기열 확인, 순서가 되었을 때 손님 호출
  ![seq2](https://user-images.githubusercontent.com/22807730/191471309-6f363902-9745-4011-9603-286556773cff.png)



## 브랜치, 커밋 규칙
### Branch
- feature-#{issue_number}
- develop
- master

### Commit Message
- #{issue_number}-{작업내용}

## 사용된 기술
- Java11
- SpringBoot 2.7.2
- Spring MVC
- Spring Security
- Spring DataJPA
- Junit5, AssertJ, Mockito
- Gradle
- Jenkins
