
from gestorAplicacion.Cuarto import Cuarto

class Quirofano(Cuarto):

    def __init__(self, paciente, doctor):

        self._paciente = paciente
        self._doctor = doctor

    #	
    #	 * despejar: Actualiza el paciente y el doctor a null
    #	 
    def despejar(self):
        self.setPaciente(None)
        self.setDoctor(None)


    #	
    #	 * apartar: actualiza los atributos de instancia al doctor y pacinete que se pasen como paremetros
    #	 * @ params: Doctor, Paciente
    #	 

    def apartar(self, doctor, paciente):
        self.setDoctor(doctor)
        self.setPaciente(paciente)

    #	
    #	 * toString: 
    #	 
    def toString(self):
        output = None
        if self.getDoctor() is None:
            output = "Quirofano vacio"
        else:
            output = "Quirofano con el doctor " + self.getDoctor().getNombre() + " que esta operando al paciente " + self.getPaciente().getNombre()
        return output

    #	SETTERS & GETTERS	
    def getPaciente(self):
        return self._paciente
    def setPaciente(self, paciente):
        self._paciente = paciente

    def getDoctor(self):
        return self._doctor
    def setDoctor(self, doctor):
        self._doctor = doctor
