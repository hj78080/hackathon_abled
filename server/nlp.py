from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import cosine_similarity

# 각 페이지의 설명
page_descriptions = {
    'info': '정보 등록, 정보 수정, 정보 변경, 정보 바꾸',
    'joblist': '주변 가능한 일자리 정보',
    'automl': '고용 현황 예측 정보',
    'commu': '커뮤니티, 게시판, 질문',
    'home' : '시작 화면, 홈 화면, 처음 화면'
}

def page_nlp(user_input):
    # TF-IDF 벡터화
    vectorizer = TfidfVectorizer()
    page_vectors = vectorizer.fit_transform(list(page_descriptions.values()))
    user_vector = vectorizer.transform([user_input])

    # 코사인 유사도 계산
    similarities = cosine_similarity(user_vector, page_vectors).flatten()

    # 유사도가 가장 높은 페이지 찾기, 만약 max의 유사도가 0.34 보다 낮다면 none 반환
    recommended_page_index = similarities.argmax()
    if similarities[recommended_page_index] < 0.34 : return "none"

    recommended_page = list(page_descriptions.keys())[recommended_page_index]
    return recommended_page