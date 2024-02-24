import speech_recognition as sr

def recognize_speech():
    recognizer = sr.Recognizer()

    with sr.Microphone() as source:
        print("Q : 필요한 기능을 말씀해 주세요.\n")
        audio = recognizer.listen(source)

    try:
        print("음성 변환 중..")
        command = recognizer.recognize_google(audio, language="ko-KR")
        print("인식된 명령: " + command)
        return command
        
    except sr.UnknownValueError:
        print("Q : 음성을 인식할 수 없습니다.")
    except sr.RequestError as e:
        print(f"Q : Google Speech Recognition 서비스 오류: {e}")

if __name__ == "__main__":
    recognize_speech()