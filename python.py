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

# # 당신의 회사에서는 매주 1회 작성해야 하는 보고서가 있습니다.
# # 보고서는 항상 아래와 같은 형태로 출력되어야 합니다.

# # - X 주차 주간보고 -
# # 부서 :
# # 이름 :
# # 업무 요약 :

# # 1주차부터 50주차까지의 보고서 파일을 만드는 프로그램을 작성하시오.

# # 조건 : 파일명은 '1주차.txt', '2주차.txt', ... 와 같이 만듭니다.

# for i in range(1, 51):
#     with open("file/{0}주차.txt".format(i), "w", encoding="utf8") as file:
#         file.write("-{0} 주차 주간보고 -".format(i))
#         file.write("\n부서 :")
#         file.write("\n이름 :")
#         file.write("\n업무 요약 :")

# ================================================================================
# from random import *


# # 일반 유닛
# class Unit:
#     def __init__(self, name, hp, speed):
#         self.name = name
#         self.hp = hp
#         self.speed = speed
#         print("{0} 유닛이 생성되었습니다.".format(self.name))

#     def move(self, location):
#         print("{0} : {1} 방향으로 이동합니다. [속도 {2}]".format(
#             self.name, location, self.speed))

#     def damaged(self, damage):
#         print("{0} : {1} 데미지를 입었습니다.".format(self.name, damage))
#         self.hp -= damage
#         print("{0} : 현재 체력은 {1} 입니다.".format(self.name, self.hp))
#         if self.hp <= 0:
#             print("{0} : 파괴되었습니다.".format(self.name))


# # 공격 유닛
# class AttackUnit(Unit):
#     def __init__(self, name, hp, damage, speed):
#         Unit.__init__(self, name, hp, speed)
#         self.damage = damage

#     def attack(self, location):
#         print("{0} : {1} 방향으로 적군을 공격 합니다. [공격력 {2}]".format(
#             self.name, location, self.damage))


# # 날 수 있는 기능을 가진 클래스
# class Flyable:
#     def __init__(self, flying_speed):
#         self.flying_speed = flying_speed

#     def fly(self, location):
#         print("{0} : {1} 방향으로 날아갑니다. [속도 {2}]".format(
#             self.name, location, self.flying_speed))


# # 공중 공격 유닛 클래스
# class FlyableAttackUnit(AttackUnit, Flyable):
#     def __init__(self, name, hp, damaage, flying_speed):
#         AttackUnit.__init__(self, name, hp, damaage, 0)
#         Flyable.__init__(self, flying_speed)

#     def move(self, location):
#         self.fly(location)


# # 건물
# class BuildingUnit(Unit):
#     def __init__(self, name, hp, location):
#         # Unit.__init__(slef,name,hp,0)
#         super().__init__(name, hp, 0)
#         self.location = location


# # 마린
# class Marine(AttackUnit):
#     def __init__(self):
#         AttackUnit.__init__(self, "마린", 40, 5, 1)

#     # 스팀팩 : 일정 시간 동안 이동 및 공격 속도를 증가, 체력 10 감소
#     def stimpack(self):
#         if self.hp > 10:
#             self.hp -= 10
#             print("{0} : 스팀팩을 사용합니다. (HP 10 감소)".format(self.name))
#         else:
#             print("{0} : 체력이 부족하여 스팀팩을 사용하지 않습니다.".format(self.name))


# # 탱크
# class Tank(AttackUnit):
#     def __init__(self):
#         super().__init__("탱크", 150, 35, 1)
#         self.seize_mode = False

#     # 시즈 모드 : 탱크를 지상에 고정시켜, 더 높은 파워로 공격 가능
#     seize_develope = False

#     def set_seize_mode(self):
#         if Tank.seize_develope == False:
#             return

#         # 현재 시즈모드가 아닐 때 -> 시즈모드
#         if self.seize_mode == False:
#             print("{0} : 시즈모드로 전환합니다.".format(self.name))
#             self.damage *= 2
#             self.seize_mode = True
#         # 현재 시즈모드일 때 -> 시즈모드 해제
#         else:
#             print("{0} : 시즈모드를 해제합니다.".format(self.name))
#             self.damage /= 2
#             self.seize_mode = False


# # 레이스
# class Wraith(FlyableAttackUnit):
#     def __init__(self):
#         super().__init__("레이스", 80, 20, 5)
#         self.clocked = False  # 클로킹 모드 (해제 상태)

#     def clocking(self):
#         if self.clocked == True:
#             print("{0} : 클로킹 모드 해제합니다.".format(self.name))
#             self.clocked = False
#         else:
#             print("{0} : 클로킹 모드 설정합니다.".format(self.name))
#             self.clocked = True


# def game_start():
#     print("[알림] 새로운 게임을 시작합니다.")


# def game_over():
#     print("Player : gg")
#     print("[Player] 님이 게임에서 퇴장하셨습니다.")


# # 실제 게임 진행
# game_start()

# # 마린 3기 생성
# m1 = Marine()
# m2 = Marine()
# m3 = Marine()

# # 탱크 2기 생성
# t1 = Tank()
# t2 = Tank()

# # 레이스 1기 생성
# w1 = Wraith()

# # 유닛 일괄 관리 (생성된 모든 유닛 append)
# attack_units = []
# attack_units.append(m1)
# attack_units.append(m2)
# attack_units.append(m3)
# attack_units.append(t1)
# attack_units.append(t2)
# attack_units.append(w1)

# # 전군 이동
# for unit in attack_units:
#     unit.move("1시")

# # 탱크 시즈모드 개발
# Tank.seize_develope = True
# print("[알림] 탱크 시즈 모드 개발이 완료되었습니다.")

# # 공격 모드 준비(마린 : 스팀팩 ,탱크 : 시즈모드, 레이스 : 클로킹)
# for unit in attack_units:
#     if isinstance(unit, Marine):
#         unit.stimpack()
#     elif isinstance(unit, Tank):
#         unit.set_seize_mode()
#     elif isinstance(unit, Wraith):
#         unit.clocking()

# # 전군 공격
# for unit in attack_units:
#     unit.attack("1시")

# # 전군 피해
# for unit in attack_units:
#     unit.damaged(randint(5, 21))  # 공격은 랜덤으로 받음 (5~20)

# # 게임 종료
# game_over()

# ===============================================================
# # 주어진 코드를 활용하여 부동산 프로그램을 작성하시오.

# # (출력 예제)
# # 총 3대의 매물이 있습니다.
# # 강남 아파트 매매 10억 2010년
# # 마포 오피스텔 전세 5억 2007년
# # 송파 빌라 월세 500/50 2000년

# # [코드]
# class House:
#     # 매물 초기화
#     def __init__(self, location, house_type, deal_type, price, completion_year):
#         self.location = location
#         self.house_type = house_type
#         self.deal_type = deal_type
#         self.price = price
#         self.completion_year = completion_year

#     # 매물 정보 표시
#     def show_detail(self):
#         print("{0} {1} {2} {3} {4}년".format(self.location, self.house_type, self.deal_type,
#               self.price, self.completion_year))


# houses = []
# houses.append(House("강남", "아파트", "매매", "10억", 2010))
# houses.append(House("마포", "오피스텔", "전세", "5억", 2007))
# houses.append(House("송파", "빌라", "월세", "500/50", 2000))

# print("총 {0}대의 매물이 있습니다.".format(len(houses)))
# for house in houses:
#     house.show_detail()

# ============================================================================
# # 동네에 항상 대기 손님이 있는 맛있는 치킨집이 있스비다.
# # 대기 손님의 치킨 요리 시간을 줄이고자 자동 주문 시스템을 제작하였습니다.
# # 시스템 코드를 확인하고 적절한 예외처리 구문을 넣으시오

# # 조건1 : 1보다 작거나 숫자가 아닌 입력값이 들어올 때는 ValueError 로 처리
# #         출력 메세지 : "잘못된 값을 입력하였습니다."
# # 조건2 : 대기 손님이 주문할 수 있는 총 치킨량은 10마리로 한정
# #         치킨 소진 시 사용자 정의 에러[SoldOutError]를 발생시키고 프로그램 종료
# #         출력 메시지 : "재고가 소진되어 더 이상 주문을 받지 않습니다."

# class SoldOutError(Exception):
#     def __init__(self):
#         pass


# chicken = 10
# waiting = 1
# while(True):
#     print("[남은 치킨 : {0}]".format(chicken))
#     try:
#         order = int(input("치킨 몇 마리 주문하시겠습니까?"))
#         if order < 1:
#             raise ValueError
#         elif order > chicken:
#             print("재료가 부족합니다.")
#         else:
#             print("[대기번호 {0}] {1} 마리 주문이 완료되었습니다.".format(waiting, order))
#             waiting += 1
#             chicken -= order

#         if chicken == 0:
#             raise SoldOutError
#     except ValueError:
#         print("잘못된 값을 입력하였습니다.")
#     except SoldOutError:
#         print("제고가 소진되어 더 이상 주문을 받지 않습니다.")
#         break
