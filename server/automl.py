import pandas as pd
from pycaret.classification import *
import matplotlib.pyplot as plt
from io import BytesIO
import base64

def predict(year, typ, state, region) :
    loaded_model = load_model('./flask/model/saved_model')
    user_data = {
        '연령': float(year),
        '장애유형': typ,
        '중증여부': state,
        '근무지역': region,
    }
    user_data = pd.DataFrame([user_data])
    prediction = predict_model(loaded_model, data=user_data, raw_score=True)
    
    # 'prediction_score'로 시작하는 열 출력, 상위 3개 남기기
    prediction_score_columns = [col for col in prediction.columns if col.startswith('prediction_score')]
    score_values = prediction[prediction_score_columns].iloc[0]
    top3_columns_values = score_values.nlargest(3)

    labels = []
    values = []
    for col, value in top3_columns_values.items():
        labels.append(col.replace('prediction_score_', '').replace('직', ''))
        values.append(value)

    plt.figure(figsize=(10, 6))
    plt.rcParams['font.family'] = 'Malgun Gothic'
    plt.rcParams['font.size'] = 30
    plt.pie(values, labels=labels, autopct='%1.1f%%', startangle=90, wedgeprops=dict(width=0.7), textprops={'fontsize': 28},  labeldistance=0.9)
    plt.tight_layout()

    img_bytesio = BytesIO()
    plt.savefig(img_bytesio, format='png')
    plt.close()
    
    return img_bytesio