class Unit:
    def __init__(self):
        print("Unit 생성자")


class FLyable:
    def __init__(self):
        print("Flyable 생성자")


class FlyableUnit(Unit, FLyable):
    def __init__(self):
        # super().__init__()
        Unit.__init__(self)
        FLyable.__init__(self)


dropship = FlyableUnit()
