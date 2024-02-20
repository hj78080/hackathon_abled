import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
from matplotlib import font_manager, rc
from sklearn.preprocessing import LabelEncoder
from pycaret.classification import *
from sklearn.utils import resample

# CSV 파일 읽기
file_path = 'employ_processed.csv'  # 파일 경로를 적절히 수정하세요
data = pd.read_csv(file_path)

# PyCaret에서 사용할 데이터셋 로드
data.reset_index(drop=True, inplace=True)
exp1 = setup(data=data, target='취업직종대분류')

print("############## result ###############")
loaded_model = load_model('saved_model')
predictions = predict_model(loaded_model, data=data)

user_input = {
    '연령': float(input("연령을 입력하세요: ")),
    '장애유형': input("장애유형을 입력하세요: "),
    '중증여부': input("중증여부를 입력하세요: "),
    '근무지역': input("근무지역을 입력하세요: "),
}

user_data = pd.DataFrame([user_input])
predictions = predict_model(loaded_model, data=user_data)

# 예측 결과 출력
print(predictions)