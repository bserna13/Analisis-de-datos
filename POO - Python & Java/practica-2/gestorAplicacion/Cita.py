class Cita:

    vetados=[]
    def __init__(self, fecha, paciente, doctor, quirofano):

        self._fecha = fecha
        self._paciente = paciente
        self._doctor = doctor
        self._quirofano = quirofano


    #	GETTERS & SETTERS	
    def getFecha(self):
        return self._fecha
    def setFecha(self, fecha):
        self._fecha = fecha

    def getPaciente(self):
        return self._paciente
    def setPaciente(self, paciente):
        self._paciente = paciente

    def getDoctor(self):
        return self._doctor
    def setDoctor(self, doctor):
        self._doctor = doctor

    def getQuirofano(self):
        return self._quirofano
    def setQuirofano(self, quirofano):
        self._quirofano = quirofano
