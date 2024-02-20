import pandas as pd
from sklearn.utils import resample

# CSV 파일 읽기
file_path = 'employ.csv'
data = pd.read_csv(file_path)

data = data[['연령', '장애유형', '중증여부', '근무지역','취업직종대분류']].copy()

print("##############원본 데이터################")
print(data['취업직종대분류'].value_counts())

# 상위 10개 항목 추출
item_counts = data['취업직종대분류'].value_counts()
top_10_items = item_counts.head(10).index
data = data[data['취업직종대분류'].isin(top_10_items)]

print("\n##############상위 10개 데이터################")
print(data['취업직종대분류'].value_counts())

# 평균 계산
item_counts = data['취업직종대분류'].value_counts()
total_data_count = len(data)
average_per_item = total_data_count / len(item_counts)

print("\n##############상위 10개 데이터 평균################")
print("전체 데이터 수:", total_data_count)
print("항목 수:", len(item_counts))
print("평균 데이터 수 per 항목:", average_per_item)

# 평균 이상의 항목들에 대해 언더샘플링
undersampled_data = pd.DataFrame()
for category, count in item_counts.items():
    if count > average_per_item:
        category_data = data[data['취업직종대분류'] == category]
        undersampled_category_data = resample(category_data, replace=False, n_samples=int(average_per_item), random_state=42)
        undersampled_data = pd.concat([undersampled_data, undersampled_category_data])

# 평균 이하의 항목들에 대해 오버샘플링
oversampled_data = pd.DataFrame()
for category, count in item_counts.items():
    if count <= average_per_item:
        category_data = data[data['취업직종대분류'] == category]
        oversampled_category_data = resample(category_data, replace=True, n_samples=int(average_per_item), random_state=42)
        oversampled_data = pd.concat([oversampled_data, oversampled_category_data])

# 언더샘플링된 데이터와 오버샘플링된 데이터를 합쳐 최종 데이터 생성
data = pd.concat([undersampled_data, oversampled_data])

# 최종 데이터를 CSV 파일로 저장
output_file_path = 'employ_processed.csv'
data.to_csv(output_file_path, index=False)