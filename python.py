# from random import *

# # 당신은 최근에 코딩 스터디 모임을 새로 만들었습니다.
# # 월 4회 스터디를 하는데 3번은 온라인으로 하고 1번은 오프라인으로 하기로 했습니다.
# # 아래 조건에 맞는 오프라인 모임 날짜를 정해주는 프로그램을 작성하시오.

# # 조건1 : 랜덤으로 날짜를 뽑아야 함
# # 조건2 : 월별 날짜는 다름을 감안하여 최소 일수인 28 이내로 정함
# # 조건3 : 매월 1~3일은 스터디 준비를 해야 하므로 제외

# # 출력문 예제
# # 오프라인 스터디 모임 날짜는 매월 X일로 선정되었습니다.

# print("오프라인 스터디 모임 날짜는 매월 ", randint(4, 28), "일로 선정되었습니다.")
# print("오프라인 스터디 모임 날짜는 매월 ", randint(4, 28), "일로 선정되었습니다.")
# print("오프라인 스터디 모임 날짜는 매월 ", randint(4, 28), "일로 선정되었습니다.")

# ==================================================================================

# # 사이트별로 비밀번호를 만들어 주는 프로그램을 작성하시오.

# # 예)http://naver.com
# # 규칙1 : http://부분은 제외 => naver.com
# # 규칙2 : 처음 만나는 점(.) 이후 부분은 제외 => naver
# # 규칙3 : 남은 글자 중 처음 세자리 + 글자 갯수 + 글자 내 'e' 갯수 + "!"로 구성

# url = "http://naver.com"
# url = "http://daum.net"
# url = "http://google.com"
# my_str = url.replace("http://", "")
# my_str = my_str[:my_str.index(".")]
# passwd = my_str[:3] + str(len(my_str)) + str(my_str.count("e"))+"!"
# print("{0}의 비밀번호는 {1}입니다.".format(url, passwd))

# ===============================================================================

# # 당신의 학교에서는 파이썬 코딩 대횔르 주최합니다.
# # 참석률을 높이기 위해 댓글 이벤트를 진행하기로 하였습니다.
# # 댓글 작성자들 중에 추첨을 통해 1명은 치킨, 3명은 커피 쿠폰을 받게 됩니다.
# # 추첨 프로그램을 작성하시오.

# # 조건1 : 편의상 댓글은 20명이 작성하였고 아이디는 1~20이라고 가정
# # 조건2 : 댓글 내용과 상관 없이 무작위로 추첨하되 중복 불가
# # 조건3 : random 모듈의 shuffle 과 sample을 활용

# # 출력 예제
# # --당첨자 발표 --
# # 치킨 당첨자 : 1
# # 커피 당첨자 : [2,3,4]
# # --축하합니다 --

# from random import *

# lst = list(range(1, 21))

# winners = sample(lst, 4)
# chicken = sample(winners, 1)
# winners.pop(winners.index(chicken[0]))

# print("--당첨자 발표--")
# print("치킨 당첨자 :", chicken)
# print("커피 당첨자 :", winners)
# print("--축하합니다--")

# =================================================================================
# # 당신은 Cocoa 서비스를 이용하는 택시 기사님입니다.
# # 50명의 승객과 매칭 기회가 있을 때, 총 탑승 승객 수를 구하는 프로그램을 작성하시오.

# # 조건1 : 승객별 운행 소요 시간은 5분 ~ 50분 사이의 난수로 정해집니다.
# # 조건2 : 당신은 소요 시간 5~15분 사이의 승객만 매칭해야 합니다.

# # 출력문 예제
# # [O] 1번째 손님 (소요시간 : 15분)
# # [ ] 2번째 손님 (소요시간 : 50분)
# # [O] 3번째 손님 (소요시간 : 5분)

# # 총 탑승 승객 : 2분

# from random import *

# count = 0
# check = " "

# for i in range(1, 51):
#     time = randint(5, 50)
#     if 5 <= time <= 15:
#         count += 1
#         check = "O"
#     else:
#         check = " "
#     print("[{0}] {1}번째 손님 (소요시간 : {2}분".format(check, i, time))

# print("총 탑승 승객 : {0}분".format(count))

# ========================================================================================
# # 표준 체중을 구하는 프로그램을 작성하시오

# # *표준 체중 : 각 개인의 키에 적당한 체중

# # (성별에 따른 공식)
# #     남자 : 키(m) * 키(m) * 22
# #     여자 : 키(m)**2 * 21

# # 조건1 : 표준 체중은 별도의 함수 내에서 계산
# #     *함수명 : std_weight
# #     *전달값 : 키(heiht), 성별(gender)
# # 조건2 : 표준 체중은 소수점 둘째자리까지 표시

# # (출력 예제)
# # 키 175cm 남자의 표준 체중은 67.38kg 입니다.

# def std_weight(height, gender):
#     weight = 0

#     if(gender == "남자"):
#         weight = height*height*22
#     elif(gender == "여자"):
#         weight = height*height*21
#     else:
#         print("성별이 무엇입니까?")
#     return weight


# print("키 {0}cm {1}의 표준 체중은 {2}kg 입니다.".format(
#     175, "남자", round(std_weight(1.75, "남자"), 2)))

# import sys
# print("Python", "Java", file=sys.stdout)
# print("Python", "Java", file=sys.stderr)
# print("무엇이 더 재밌을까요?")


# scores = {"수학": 0, "영어": 50, "코딩": 100}
# for subject, score in scores.items():
#     # print(subject, score)
#     print(subject.ljust(8), str(score).rjust(4), sep=":")

# for num in range(1, 21):
#     print("대기번호 : "+str(num).zfill(3))

# answer = input("아무 값이나 입력하세요 : ")
# print("입력하신 값은 {0} 입니다.".format(answer))

# =======================================================================
# score_file = open("score.txt", "w", encoding="utf8")
# print("수학 : 0", file=score_file)
# print("영어 : 50", file=score_file)
# score_file.close()

# score_file = open("score.txt", "a", encoding="utf8")
# score_file.write("과학 : 80")
# score_file.write("\n코딩 : 100")
# score_file.close()

# score_file = open("score.txt", "r", encoding="utf8")
# print(score_file.read())
# score_file.close()

# score_file = open("score.txt", "r", encoding="utf8")
# print(score_file.readline(), end="")
# print(score_file.readline(), end="")
# print(score_file.readline(), end="")
# print(score_file.readline(), end="")
# score_file.close()

# score_file = open("score.txt", "r", encoding="utf8")

# lines = score_file.readlines()
# for line in lines:
#     print(line, end="")
# score_file.close()

# import pickle
# # profile_file = open("profile.pickle", "wb")
# # profile = {"이름": "박명수", "나이": 30, "취미": ["축구", "골프", "코딩"]}
# # print(profile)
# # pickle.dump(profile, profile_file)

# # profile_file.close()
# profile_file = open("profile.pickle", "rb")
# profile = pickle.load(profile_file)
# print(profile)
# profile_file.close()

# import pickle

# with open("profile.pickle", "rb") as profile_file:
# print(pickle.load(profile_file))

# with open("study.txt", "w", encoding="utf8") as study_file:
# study_file.write("파이써을 열심히 공부하고 있어요")

with open("study.txt", "r", encoding="utf8") as study_file:
    print(study_file.read())
