from enum import Enum

class Especialidad(Enum):
    CARDIOLOGIA=((15000000, "cardiolog@", 1000000))
    PLASTICA=(30000000, "cirugan@ plastic@", 3000000)
    GENERAL=(45000000, "medic@ general", 1000000)
    DERMATOLOGIA=(10000000, "dermatolog@", 500000)
    NEUROCIRUJANO=(10000000, "neurocirujano", 5000000)

    _salario=150000
    _titulo="medico"
    _costoCirujia=10000

    def Especialidad(self,salario,titulo,costoCirujia):
        self._salario=salario
        self._titulo=titulo
        self._costoCirujia=costoCirujia

    def getSalario(self):
        return self._salario.value
    def getTitulo(self):
        return self._titulo
    def getCostoCirujia(self):
        return self._salario