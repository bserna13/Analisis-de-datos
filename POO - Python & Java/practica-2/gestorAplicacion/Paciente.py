from gestorAplicacion.Persona import Persona
from gestorAplicacion.Especialidad import Especialidad
from gestorAplicacion.Sintomas import Sintomas

class Paciente(Persona):

    def _initialize_instance_fields(self):
        #instance fields found by Java to Python Converter:
        self._cita = None
        self._turno = -1
        self._sintomas = []
        self._tipoProblemasMedicos = []

    _numeroPacientes = 0

    def __init__(self, nombre, cedula, poblacionDeRiesgo, sintomas, turno=-1):
        self._initialize_instance_fields()

        super().__init__(nombre, cedula, poblacionDeRiesgo, 0)
        Paciente._numeroPacientes += 1

        i = 0
        while i < len(sintomas):
            self._sintomas.append(sintomas[i])
            i += 1

        self._turno = turno
        self.diagnosticar()

    #	
    #	 * diagnosticar: El sistema le asigna a que especialista busca el paciente
    #	 
    def diagnosticar(self):
        for sintoma in self.getSintomas():
            if sintoma == Sintomas.DOLORPECHO:
                self.addTipoProblemasMedicos(Especialidad.CARDIOLOGIA)
            elif sintoma == Sintomas.DEMENCIA:
                self.addTipoProblemasMedicos(Especialidad.NEUROCIRUJANO)
            elif sintoma == Sintomas.PIELRARA:
                self.addTipoProblemasMedicos(Especialidad.DERMATOLOGIA)

        if len(self.getTipoProblemasMedicos()) == 0:
            self.addTipoProblemasMedicos(Especialidad.GENERAL)
    #	
    #	 * 
    #	 
    def toString(self):
        return "Paciente " + self.nombre + " identificado con documento " + str(self.cedula)

    def infectar(self):
        retorno = super().infectar()
        self.addSintomas(Sintomas.DEMENCIA)
        return retorno

    #	GETTERS & SETTERS	
    def getSintomas(self):
        return self._sintomas
    def addSintomas(self, sintomas):
        i = 0
        while i < len(sintomas):
            self._sintomas.append(sintomas[i])
            i += 1
    def addSintomas(self, sintoma):
        self._sintomas.append(sintoma)

    def getNombre(self):
        return super().getNombre()

    def getCita(self):
        return self._cita
    def setCita(self, cita):
        self._cita = cita

    def getTurno(self):
        return self._turno
    def setTurno(self, turno):
        self._turno = turno

    def getTipoProblemasMedicos(self):
        return self._tipoProblemasMedicos
    def setTipoProblemasMedicos(self, tipoProblemasMedicos):
        self._tipoProblemasMedicos = tipoProblemasMedicos
    def addTipoProblemasMedicos(self, tipoProblemasMedicos):
        self._tipoProblemasMedicos.append(tipoProblemasMedicos)
