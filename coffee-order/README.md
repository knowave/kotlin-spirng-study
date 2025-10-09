## ☕️ Coffee Order System

기술 스택: **kotlin**, **spring boot**, **MySQL**, **JPA**, **Spring Security**, **JWT**


### 개요
카페 주문 시스템을 단일 REST API로 구현한 프로젝트.</br>
**포인트 기반 결제 시스템**을 사용하고, 주문 시 인기 메뉴도 집계함.

### 요구사항 요약
| 기능 | 설명 |
|------|------|
| 사용자 인증 | JWT 토큰 기반의 회원가입/로그인 시스템을 통해 안전한 사용자 인증을 제공합니다. |
| 포인트 관리 | 사용자는 포인트를 충전하고 조회할 수 있으며, 주문 시 포인트로 결제가 이루어집니다. |
| 메뉴 조회 | 전체 메뉴 목록과 주문량 기준 상위 10개의 인기 메뉴를 조회할 수 있습니다. |
| 주문 관리 | 메뉴 선택 후 포인트로 결제하여 주문을 생성하고, 주문 내역을 조회할 수 있습니다. |
| 주문 취소 | 주문 취소 시 자동으로 사용한 포인트가 환불되며, 취소된 주문은 별도로 관리됩니다. |

### 시스템 아키텍처 요약
```
User (회원)
 ├─ email / password / point
 │
 ├─ (1:N)
 │
 ▼
Order (주문)
 ├─ user_id
 ├─ menu_id
 ├─ price
 ├─ status (COMPLETED / CANCELED)
 │
 └─ (N:1)
     ▼
Menu (메뉴)
 ├─ name / price / isActive / orderCount
 ```
* 전형적인 다대일 관계로 **User -> Order -> Menu** flow를 가지고 있다.
* 주문과 동시에 user.point가 감소하고, menu.orderCount는 증가한다.
* 취소 시, user.point는 복구되며, menu.orderCount는 감소한다.

### 설계 이유
#### 도메인 설계
* user
    * 포인트 충전 및 결제의 주체
    * `point` 필드만으로 간결하게 포인트 시스템 유지 (별도 지갑으로 분리하지 않음)
* menu
    * 주문 집계용 `orderCount` 추가
    * 주문 실시간 in/decrement 방식으로 DB 성능 최적화
* order
    * 주문 당시 가격(price)을 별도로 저장해 가격 젼경 시 이력 보존
    * 상태로 멱등 취소 및 복구 가능

소규모 단일 인스턴스 환경에서 **최소한의 기능을 작동**하기 위해 단순한 구조를 목표로 함.</br>
실시간으로 주문 count가 집계가 되고, 이력성 데이터는 필요 시 확장할 수 있게 설계함.

#### 인증/인가 (Security + JWT)
* 로그인 시 `JWT` 발급
* `JWT Payload`에 `userId`, `email` 을 할당.
* `Spring Security`의 `OncePerRequestFilter` 기반 미들웨어로 인증 처리
* `@AuthenticationPrincipal` 을 통해 사용자 정보 자동 주입

OAuth2나 세션 기반 인증을 사용할 수 있지만</br>
최소한의 보안 요건을 충족하기 위해서 **Stateless JWT** 인증 구조 선택 