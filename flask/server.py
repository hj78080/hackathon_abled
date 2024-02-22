from flask import Flask, request

app = Flask(__name__)

@app.route('/api', methods=['POST'])
def handle_post_request():
    request_type = request.args.get('type')  # 또는 request.headers.get('Request-Type')

    if request_type == 'text':
        text_data = request.form['text']
        print(text_data)
        return f'Text data received: {text_data}'
    
    elif request_type == 'image':
        return 'Image data received'
    
    else:
        return 'Invalid request type'

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000, debug=True)