from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import cosine_similarity
import stt

# 각 페이지의 설명
page_descriptions = {
    'page1': '회원 가입, 등록, 정보 수정',
    'page2': '주변 가능한 일자리 정보',
    'page3': '고용 현황 예측 정보',
    'page4': '커뮤니티, 게시판, 질문',
    # 다른 페이지들도 추가 가능
}

# 사용자 입력 텍스트
user_input = stt.recognize_speech()

# TF-IDF 벡터화
vectorizer = TfidfVectorizer()
page_vectors = vectorizer.fit_transform(list(page_descriptions.values()))
user_vector = vectorizer.transform([user_input])

# 코사인 유사도 계산
similarities = cosine_similarity(user_vector, page_vectors).flatten()

# 유사도가 가장 높은 페이지 찾기
recommended_page_index = similarities.argmax()
recommended_page = list(page_descriptions.keys())[recommended_page_index]
print("\n코사인 유사도")
for i in range(len(similarities)) :
    print(f"page{i+1} : {similarities[i]:.2f}")

print(f"\n사용자에게 추천하는 페이지: {recommended_page}")