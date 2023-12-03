from gestorAplicacion.Especialidad import Especialidad
from gestorAplicacion.Jerarquia import Jerarquia
from gestorAplicacion.Persona import Persona


class Doctor(Persona):

    def __init__(self, nombre, cedula, poblacionDeRiesgo=False, especialidad=Especialidad.GENERAL, cirujano=True, jerarquia=Jerarquia.INTERNO, voluntadPandemia=False):

        super().__init__(nombre, cedula, poblacionDeRiesgo)
        self._especialidad = especialidad
        self._cirujano = cirujano
        self.jerarquia = jerarquia
        self._voluntadPandemia = voluntadPandemia

    # METODOS DE INSTANCIA

    #
    #	 * toString: <Jerarquia> <Nombre> de la especialidad <Especialidad>
    #
    def toString(self):
        output = ""

        if self.getJerarquia() is not None:
            output += self.getJerarquia().getTitulo() + " "

        output += "" + self.getNombre() + " de la especialidad " + self.getEspecialidad().getTitulo()
        return output

    #
    #	 * infectar
    #
    def infectar(self):
        retorna = super().infectar()
        if self.isPoblacionDeRiesgo():
            self.setVoluntadPandemia(False)
        return retorna

    #	GETTERS & SETTERS
    def getEspecialidad(self):
        return self._especialidad

    def setEspecialidad(self, especialidad):
        self._especialidad = especialidad

    def isCirujano(self):
        return self._cirujano

    def setCirujano(self, cirujano):
        self._cirujano = cirujano

    def getJerarquia(self):
        return self.jerarquia

    def setJerarquia(self, jerarquia):
        self.jerarquia = jerarquia

    def isVoluntadPandemia(self):
        return self._voluntadPandemia

    def setVoluntadPandemia(self, voluntadPandemia):
        self._voluntadPandemia = voluntadPandemia
