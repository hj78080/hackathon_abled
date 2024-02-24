import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
from matplotlib import font_manager, rc
from sklearn.preprocessing import LabelEncoder
from pycaret.classification import *
from sklearn.utils import resample

rc('font', family='AppleGothic')
plt.rcParams['axes.unicode_minus'] = False

# CSV 파일 읽기
file_path = 'employ_processed.csv'
data = pd.read_csv(file_path)

# 최종 결과 확인
print("\n##############최종 데이터################")
print(data['취업직종대분류'].value_counts())

# PyCaret에서 사용할 데이터셋 로드
data.reset_index(drop=True, inplace=True)
exp1 = setup(data=data, target='취업직종대분류')


"""
모델 생성
"""

# 모델 생성
rf = create_model('rf', fold=5)
top3 = compare_models(sort='Accuracy', n_select=3, fold=5)
tuned_top3 = [tune_model(i, n_iter=5) for i in top3]

# 교차검증
tuned_rf = tune_model(rf, fold=5, optimize= 'Accuracy')

#모델 Blening
#선택한 모델을 조합하여 더욱 강력한 앙상블 모델 만들기, 기본적으로 10번 학습함
blender_top3 = blend_models(estimator_list=top3, verbose=True, method='auto', fold=10)

#모델 다시 tuning_예측성능을 올리기
blender_tune3 = tune_model(blender_top3)


"""
최종 결과 및 저장
"""
final_model = finalize_model(blender_tune3)
prediction = predict_model(final_model, data=data)

#타겟값 잘 나오나 확인
prediction = prediction['취업직종대분류']
print(prediction)

# 모델 저장
save_model(final_model, 'saved_model')

loaded_model = load_model('saved_model')
print(loaded_model)