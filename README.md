## 서비스 요약
> **🎓Gracker** - 대학생, 성공적인 졸업 지원 서비스

## 주제 구분
> **E타입**: 경북대에 다니는 다양한 배경의 학우들을 위한 서비스

## 팀원 소개


<table>
  <tbody>
    <tr>
      <td align="center"><a href="https://github.com/lsmin3388"><img src="https://github.com/user-attachments/assets/ce739ae9-f8d4-40c0-bece-d75d819925af" width="100px;" alt=""/><br /><sub><b> 이상민 👋<br> FE/BE/팀장 <br> (컴퓨터학부 심컴 23학번)</b></sub></a><br /></td>
      <td align="center"><a href="https://github.com/j9hyeon"><img src="https://github.com/user-attachments/assets/7e130631-0cc6-4030-af76-a369b0a18625" width="100px;" alt=""/><br /><sub><b> 정구현 👍 <br> Design/팀원 <br> (컴퓨터학부 글솦 23학번) </b></sub></a><br /></td>
      <td align="center"><a href="https://github.com/wodydl0"><img src="https://github.com/user-attachments/assets/de59afdc-f176-4002-afbf-a52b92cfb6d2" width="100px;" alt=""/><br /><sub><b> 박재영 🤟 <br> BE/팀원 <br> (컴퓨터학부 글솦 22학번) </b></sub></a><br /></td>
    </tr>
  </tbody>
</table>

## 시연 영상
[![Gracker 서비스 시연 영상](https://github.com/user-attachments/assets/a91c29c1-ed3d-448a-ab60-2dd462802df1)](https://youtu.be/8_eYfl3y1BE)
* 사진 클릭 시 이동됩니다.

## 서비스 소개

### 📍 서비스 개요
> **Gracker**는 경북대학교 학생들이 졸업 요건을 체계적으로 관리하고, 졸업 준비 과정을 효율적으로 진행할 수 있도록 지원하는 통합 플랫폼입니다. 사용자 친화적인 인터페이스와 고도화된 기능을 통해 학생들은 자신의 학업 현황을 실시간으로 파악하고, 향후 학업 계획을 수립할 수 있습니다. 또한, 졸업한 선배들의 검증된 팁과 추천 과목 정보를 제공하여 졸업 전략 설계를 돕습니다.


### 📍 타서비스와의 차별점
경북대학교 통합정보시스템과 비교하여 Gracker는 다음과 같은 차별점을 가지고 있습니다:

1. **가시성 향상**: 많은 학생들이 현재 시스템의 존재조차 인지하지 못하는 반면, Gracker는 직관적인 디자인과 접근성을 통해 사용자들이 쉽게 이용할 수 있습니다.
2. **향상된 사용자 경험(UX)**: 단순한 정보 제공을 넘어, 사용자 경험을 극대화한 인터페이스로 졸업 요건 관리가 용이합니다.
3. **종합적인 졸업 요건 관리(추가 시스템)**: 단순히 졸업 요건 충족 여부를 알려주는 것을 넘어, 남은 요건을 충족하기 위한 전략 수립, 다음 학기에 추천할 과목 제안, 졸업한 선배들의 검증된 졸업 전략 정보 등을 제공합니다.

### 📍 구현 내용 및 결과물

[웹사이트로 바로가기: https://gracker.site](https://gracker.site)

> ER-Diagram

<img width="300" alt="erd" src="https://github.com/user-attachments/assets/a1c783d5-bc4b-431f-807e-c97507ab7d78">

<br><br><br>

> **경북대학교 구글 로그인**: 경북대학교 구글 계정 (@knu.ac.kr)만 구글 소셜 로그인이 가능하도록 개발했습니다.

<img width="300" alt="학번학과페이지" src="https://github.com/user-attachments/assets/bdfce580-1ca8-4804-a62d-6ac9f1dc73fa">
<img width="300" alt="학번학과페이지-1" src="https://github.com/user-attachments/assets/baae4fbc-326f-437b-8376-67a61a3f90ce">
<img width="300" alt="학번학과페이지2" src="https://github.com/user-attachments/assets/e4a9b3f8-69d1-42c7-af6d-bfaf3fe47619">

<br><br><br>

> **홈 화면**: 졸업에 필요한 해당 학생의 학적 정보를 조회해 "학점 현황", "평점 현황", "기타 현황" 을 제공합니다.
<img width="50" alt="홈 페이지" src="https://github.com/user-attachments/assets/58d15365-1417-4b08-9c35-1f5407d6993b">

<br><br><br>

> **분석 결과 화면**: 졸업 계획 수립에 도움을 줄 수 있도록 다양한 정보를 제공합니다.
- **졸업 예상 학점**: 앞으로 남은 학기동안 학점 분배에 대한 도움을 제공합니다.
- **다음 학기 커리큘럼**: 과에서 제공하는 다음 학기 커리큘럼 정보를 제공합니다.
- **선 이수/선 후수 과목 톺아보기**: 지금까지 수강한 과목을 기반으로, 선 이수 / 선 후수 과목을 추적해 과목을 추천합니다.
- **추천 과목**: 그래프를 활용해 자체적으로 만든 추천 알고리즘을 활용해 과목을 추천합니다.
<img width="300" alt="분석 페이지" src="https://github.com/user-attachments/assets/87c87a35-1304-48cc-b441-9d52d23019bb">

<br><br><br>

> **수강 강좌 및 졸업요건 추가 화면**: 수강한 과목들을 추가하거나, 기타 졸업 요건 충족 여부를 추가할 수 있습니다.
<img width="300" alt="플러스 페이지" src="https://github.com/user-attachments/assets/d0388215-c284-4b05-b4f0-3f6d68d8a6ad">

<br><br><br>

> **선배들의 팁**: 전공 별 선배들의 졸업 관련 팁들을 모아둔 화면입니다.
<img width="300" alt="플솦 팁" src="https://github.com/user-attachments/assets/9444ac28-6e67-4257-b329-4b022be519da">
<img width="300" alt="글솦 팁" src="https://github.com/user-attachments/assets/5896aaa7-ef9d-498c-99fb-51beb0908991">

<br><br><br>

> **더보기**: 언어 및 지역(한국어, 영어 지원)을 선택할 수 있으며, 그 밖의 다양한 기능을 제공합니다.
<img width="390" alt="_페이지" src="https://github.com/user-attachments/assets/14eeaf44-a109-4ca6-8aec-2922fc2010d1">

<br><br><br>

> 기능 정리

- **졸업 요건 자동 계산**: 학생의 학업 이력을 기반으로 졸업 요건을 자동으로 계산하고, 남은 요건을 시각적으로 표시합니다.
- **맞춤형 학업 계획 수립**: 남은 졸업 요건을 충족하기 위한 구체적인 학업 계획을 제안하며, 다음 학기에 수강하면 유용한 과목을 추천합니다.
- **선배 팁 제공**: 졸업한 선배들의 검증된 팁을 통해 학업 및 졸업 준비 과정에서의 실질적인 조언을 제공합니다.
- **실시간 업데이트**: 학사 정보 시스템과 연동하여 실시간으로 학업 현황을 업데이트하고, 변화에 신속하게 대응할 수 있습니다.

### 구현 방식

| 구분        | 기술 스택                              | 설명                                                                 |
|-------------|----------------------------------------|----------------------------------------------------------------------|
| **Frontend**| React, Vite, Redux, Styled-Components  | 반응형 UI 구현과 상태 관리를 통해 사용자 친화적인 인터페이스 제공     |
| **Backend** | Spring, Spring JPA, Spring Security, MySQL | 안정적이고 확장 가능한 서버 구축을 통해 데이터 관리 및 보안 강화      |
| **DevOps**  | AWS EC2, Ubuntu, Nginx, Docker         | 클라우드 인프라를 활용한 서비스 배포 및 관리 자동화                   |
| **ETC**     | OpenAI                                 | 인공지능을 활용한 추천 시스템 및 데이터 분석 기능 구현                |

### 향후 개선 및 발전 방안

| 단계 | 기간                     | 계획 내용                                                      |
|------|--------------------------|----------------------------------------------------------------|
| 1차  | 2024년 9월 23일 ~ 10월 7일      | 경북대학교 컴퓨터학부 학생들을 대상으로 서비스 시범 운영 및 초기 피드백 수집 |
| 2차  | 2024년 10월 8일 ~ 11월 30일     | 경북대학교 전체 학과로 서비스 확장 및 추가 기능 도입             |
| 3차  | 2025년 1월 ~             | 타 대학으로 서비스 범위를 확대하여 전국적인 졸업 요건 관리 플랫폼으로 발전 |

## 결론
Gracker는 경북대학교 학생들의 성공적인 졸업을 지원하기 위해 시작한 서비스입니다. 체계적인 졸업 요건 관리와 맞춤형 학업 계획 제공을 통해 학생들이 효율적으로 졸업을 준비할 수 있도록 돕는 것을 목표로 하고 있습니다. 서비스를 꾸준히 확장하여 경북대학교 학생을 넘어서 전국의 모든 대학교 학생들이 복잡한 졸업 관리를 쉽게 할 수 있도록 서비스를 제공할 것입니다.

---

**문의 및 피드백**
서비스에 대한 문의나 피드백은 팀원 이메일을 통해 접수받고 있습니다. 여러분의 소중한 의견을 반영하여 Gracker를 더욱 발전시켜 나가겠습니다.

- lsmin3388@knu.ac.kr

감사합니다.
