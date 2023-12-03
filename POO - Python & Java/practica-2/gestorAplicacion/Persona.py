import math
from gestorAplicacion.Cita import Cita

class Persona:

    def __init__(self, nombre, cedula, poblacionDeRiesgo=False, edad=0, strikes=0, agresivo=False, genero=''):

        self.nombre = nombre
        self.cedula = cedula
        self.edad = edad
        self._poblacionDeRiesgo = poblacionDeRiesgo
        self._strikes = strikes
        self._agresivo = agresivo
        self.genero = genero

    #	INSTANCE METHODS
    #
    #	 * presentarQueja: se presenta una queja hacia una persona(ya sea doctor o paciente)
    #

    def presentarQueja(self, hospital, persona):
        persona.setStrikes(persona.getStrikes() + 1)
        if persona.getStrikes() >= 3:
            try:
                doctor = persona
                hospital.despedirDoctor(doctor.getCedula(), True)
            finally:
                # TODO: handle finally clause
                Cita.vetados.append(persona.cedula)

    #
    #	 * enfermar: El paciente se enferma
    #
    def infectar(self):
        nuevaEdad = int(math.floor(self.getEdad() * 1.10))
        self.setEdad(nuevaEdad)
        return self.isPoblacionDeRiesgo()

    #
    #	 * ABSTRACT METHODS
    #

    def toString(self):
        pass

    # GETTERS & SETTERS

    def getNombre(self):
        return self.nombre

    def setNombre(self, nombre):
        self.nombre = nombre

    def getCedula(self):
        return self.cedula

    def setCedula(self, cedula):
        self.cedula = cedula

    def getEdad(self):
        return self.edad

    def setEdad(self, edad):
        self.edad = edad

    def isPoblacionDeRiesgo(self):
        return self._poblacionDeRiesgo

    def setPoblacionDeRiesgo(self, poblacionDeRiesgo):
        self._poblacionDeRiesgo = poblacionDeRiesgo

    def getStrikes(self):
        return self._strikes

    def setStrikes(self, strikes):
        self._strikes = strikes

    def getGenero(self):
        return self._genero

    def setGenero(self, genero):
        self._genero = genero

    def isAgresivo(self):
        return self._agresivo

    def setAgresivo(self, agresivo):
        self._agresivo = agresivo
