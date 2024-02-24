from flask import Flask, request
from nlp import page_nlp
import matplotlib.pyplot as plt
import matplotlib
from automl import predict
from io import BytesIO
import base64

matplotlib.use('Agg')
app = Flask(__name__)

@app.route('/api/page', methods=['GET'])
def handle_get_request():
    text_data = request.args.get('text')
    page = page_nlp(text_data)
    print(f"text: {text_data}, page: {page}")
    return page

@app.route('/api/graph', methods=['GET'])
def generate_graph():
    year = int(request.args.get('year'))
    typ = request.args.get('type')
    state = request.args.get('state')
    region = request.args.get('region')
    
    img_bytesio = predict(year, typ, state, region)

    # 이미지를 Base64로 인코딩하여 클라이언트에 전송
    img_base64 = base64.b64encode(img_bytesio.getvalue()).decode('utf-8')

    return img_base64

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000, debug=True)