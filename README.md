# 🖥️ hackathon_abled
AI 기반 장애인 고용 매칭 서비스: “Abled” 앱 개발


## 👏 Team Members 👏
|이름|역할|
|------|---|
|남경현|데이터분석 ,아이디어 기획|
|최연아|데이터분석 ,AI모델 빌딩 |
|성호정|UI/UX 설계 ,프로토타입 개발|
|임형준|앱 개발 ,프로토타입 개발|

## 🏛️ system architecture 🏛️

<img width="941" alt="image" src="https://github.com/hj78080/hackathon_abled/assets/102707496/c32f8235-c917-4a2e-b869-c44d2762e7e1">



## ✨ Data analysis Flow chart ✨


- 공공데이터 포털 / 한국장애인고용공단_장애인 취업 정보 - https://www.data.go.kr/data/15088956/fileData.do
- 공공데이터 포털 / 한국장애인고용공단_장애인 구직자 현황 - https://www.data.go.kr/data/15088956/fileData.do
- 공공데이터 포털 / 한국장애인고용공단_장애인 구인 정보 - https://www.data.go.kr/data/3072637/fileData.do
- 공공데이터 포털 / 한국장애인고용공단_장애인 구인 실시간 현황 - https://www.data.go.kr/data/15117692/openapi.do

### ✨ Data preprocessing ✨

1. 희망직종 전처리
   - 자카드 유사도를 적용하여 희망직종을 16개 카테고리로 통일

2. 근로지역 전처리
   - 세분화 된 근로지역을 지역별로 분석을 용이하게 하기 위해 도 권역 단위로 끊어서 진행
     
<img width="1146" alt="image" src="https://github.com/hj78080/hackathon_abled/assets/102707496/28267ade-2813-436f-8b93-4f6be753916f">

<img width="1133" alt="image" src="https://github.com/hj78080/hackathon_abled/assets/102707496/4965cf7e-f2c9-47fe-b85d-a6750ac70fb4">


## 📈 AutoML Flow chart 📈

1. Pycaret 설치
   - 라이브러리 설치 및 데이터 임포트

2. 데이터 전처리
   - 데이터가 2개 뿐인 ‘인문-사회과학 연구직', 자연-생명과학 연구직' 데이터 삭제
   - 45,117 rows x 5 columns 데이터 확인

3. 모델 생성
  - 분류/회귀/군집에따라서 생성하는 모델 상이
  - 분류 모델중 성능이 좋은 모델인 RF 모델 생성 (공식문서 참조)
  - fold → 데이터셋을 5개로 나누어 교차 검증

4. 모델 성능 비교
  - 15개의 ML모델 -> 평가지표별 성능 비교
  - 각각 모델들을 acc를 기준으로 상위 3개 모델 top3에 저장

5. 모델 튜닝 및 앙상블 

 <img width="668" alt="image" src="https://github.com/hj78080/hackathon_abled/assets/102707496/106d78d2-ba3a-4c38-9228-a18a01e78b9d">
 <br/>
  - Gradient Boosting Classifier + Extreme Gradient Boosting + Logistic Regression  -> Voting Classifier

<br/>
<br/>

6. 최종모델

  - 학습 데이터 불균형으로 일어나는 문제를 해결하기 위해 상위 10개 직업군의 학습 데이터수 undersampling, oversampling 진행

  <img width="384" alt="image" src="https://github.com/hj78080/hackathon_abled/assets/102707496/1be9c4d9-1961-4da8-a856-3423a5eb49eb">


## ❤️‍🩹 일자리 추천 유저 테스트 ❤️‍🩹

<img width="463" alt="image" src="https://github.com/hj78080/hackathon_abled/assets/102707496/c8ee4833-1542-458d-b546-b110b4cdbb97">

<img width="264" alt="image" src="https://github.com/hj78080/hackathon_abled/assets/102707496/6fc80dee-de38-4dbf-80a4-1d6e747afd52">

### 👏 가장 높은 확률을 가지는 일자리 추천 👏

<img width="568" alt="image" src="https://github.com/hj78080/hackathon_abled/assets/102707496/45449741-c78e-4aaa-aff2-eb5140980ba1">



## 🎬 세부 직무 설명 영상 제작 🎬

### Model
<img width="942" alt="image" src="https://github.com/hj78080/hackathon_abled/assets/102707496/f2012c75-db7c-4416-b2c7-1f3051dd77e2">
<br/>
Diffussion generation model 을 기반으로 하는
<br/>
Sora는 OpenAI에서 공개된 모델로, 잡음이 있는 패치를 입력으로 받아 
깨끗한 패치를 예측하도록 훈련받음.
<br/>
특히, Sora는 diffusion transformers 이므로 언어 모델링,
컴퓨터 비전 , 이미지 생성과 같은 다양한 분야에서 뛰어난 성능을 보여줌.

### 영상 제작 Flow
1. 실시간 API
   - ‘한국장애인고용공단 장애인 구인 실시간 현황 API’에서 필요한 직무 기능이 들어간 키워드 발췌
   <img width="552" alt="image" src="https://github.com/hj78080/hackathon_abled/assets/102707496/cb4de5d9-1439-45fc-a06f-0d71e346dd29">
<br/>

2. 텍스트 전처리
   - 발췌한 직무 기능 키워드를 Gpt 3.5 api를 사용하여 Sora의 프롬프트에 알맞게 튜닝하는 전처리를 거침
   <img width="552" alt="image" src="https://github.com/hj78080/hackathon_abled/assets/102707496/eefa6452-64d8-4727-88c4-7797c7204d41">

<br/>

3. Sora에 입력
   - Sora에 프롬프트를 입력해 영상을 제작
<br/>




4. 직무 영상 제공 (현재 Sora api는 비공개이기 때문에 ‘Fliki’ 생성형 AI 비디오 툴 이용해서 직접 제작 )
   - 원하는 구인 공고를 클릭하면,구인 공고와 관련된 직무 영상이 바로 작동하도록 연결
      https://github.com/hj78080/hackathon_abled/assets/102707496/d155737e-3801-4460-9e15-8b98ccbc9226



## 🎤 음성인식 기능 설계 🎤



## 📕 피그마 앱 프로토타입 설계 📕


1. 사용자는 자신의 연령, 장애유형, 중증여부, 근무지역 입력을 입력할 수 있습니다. 사용자를 고려해 직관적이고 한눈에 볼수있는 UI를 도입하였습니다.
<img width="974" alt="image" src="https://github.com/hj78080/hackathon_abled/assets/102707496/f423f862-bfe1-488a-a34f-26b68c23fe91">

2. AutoML 기반의 AI 모델을 통해 가장 높은 확률을 가진 맞춤형 직업 추천을 해줍니다. 시각화 자료 또한 제공하며 실시간 API를 통해 맞춤형 공고를 제안합니다. 
<img width="974" alt="image" src="https://github.com/hj78080/hackathon_abled/assets/102707496/524c5899-71cf-4837-86de-325e31bd5b9d">

3. 홈페이지 또한 사용자를 고려하여 쉽고 직관적인 UI를 도입하였습니다. 일자리 찾기 , 커뮤니티 , 장애유형별 고용현황을 볼 수 있으며 커뮤니티를 사용하기 위한 회원가입/로그인 로직을 구현하였습니다.
<img width="974" alt="image" src="https://github.com/hj78080/hackathon_abled/assets/102707496/e365b9d2-458d-49fa-8643-99da822e1bdf">

4. 자연어 처리와 STT 기술을 UI에 도입하여, 사용자가 요구하는 페이지를 직관적으로 제공함으로 보다 쉽게 사용할 수 있고, 신체적으로 불편한 장애인들의 선택지가 늘어날 수 있게 구현하였습니다.
<img width="974" alt="image" src="https://github.com/hj78080/hackathon_abled/assets/102707496/d5ca3fbb-16ed-48f3-a828-102d46818f21">

5. 커뮤니티 페이지입니다. 커뮤니티 페이지는 질문게시판, 정보 공유 게시판, 급한 구인 게시판으로 나누어져있으며 사용자들이 편하게 자신들의 이야기를 하고 정보를 공유할 수 있도록 기능에 따라 설계하였습니다.
<img width="974" alt="image" src="https://github.com/hj78080/hackathon_abled/assets/102707496/29335d4e-8dec-4542-9fb6-1227fefd1dbf">

1. 피그마 링크
   https://www.figma.com/file/3Pp2s4s9Yi9eFRBdS3Ht2j/Abled_%ED%95%B4%EC%BB%A4%ED%86%A4?type=design&node-id=0%3A1&mode=design&t=LaSdLeZz1hlGhxIR-1
2. 프로토타입 시연 영상
   https://www.youtube.com/shorts/kd3qTiy557I


## 👩‍💻 안드로이드 스튜디오 개발 파이프라인 👩‍💻







