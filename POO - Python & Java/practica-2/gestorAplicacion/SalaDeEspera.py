
#
# MAIN CLASS
#

class SalaDeEspera:

    """
    La sala de espera del hospital, es un atributo de hospital
    El atributo que destaca en sala de espera es pacientes, 
    ya que muchas funcionalidades hacen uso de este atributo
    """

    def __init__(self, pacientes):
        self._pacientes=pacientes
        self._bioseguro = False




    # INSTANCE METHODS 
    #	
    #	 * @param Paciente
    #	 * @return void
    #	 * 
    #	 * agregarPaciente: agrega la referencia del paciente/s al atributo pacientes[]s
    #	 
    def agregarPaciente(self, pacienteNuevo):
        i = 0
        while i < len(self._pacientes):
            if self._pacientes[i] is None:
                self._pacientes[i] = pacienteNuevo
            i += 1




    def agregarPaciente(self, pacientesNuevos):

        i = 0
        while i < len(pacientesNuevos):
            j = 0
            while j < len(self._pacientes):
                if self._pacientes[j] is None:
                    self._pacientes[j] = pacientesNuevos[i]
                    break
                j += 1
            i += 1




    #	 
    #	 * getSiguientePaciente
    #	 
    def getSiguientePaciente(self):
        siguientePaciente = None

        i = 0
        while i < len(self._pacientes):
            if self._pacientes[i].getTurno() == 1:
                siguientePaciente = self._pacientes[i]
                self.quitarPaciente(self._pacientes[i])
            i += 1
        return siguientePaciente




    #	
    #	 * quitarPaciente: quita el paciente de la sala de espera
    #	 * actualiza los fichos de las personas que venian detras de el
    #	 
    def quitarPaciente(self, paciente):
        turnoPacienteQuitar = paciente.getTurno()
        i = 0
        while i < len(self._pacientes):

            if self._pacientes is None:
                continue

            turnoiPaciente = self._pacientes[i].getTurno()

            if self._pacientes[i] is paciente:
                self._pacientes[i] = None
            elif turnoiPaciente > turnoPacienteQuitar:
                self._pacientes[i].setTurno(turnoiPaciente - 1)
            i += 1




    #	
    #	 * implementarBioseguridad: se deja una silla en medio en la sala de espera
    #	 
    def implementarBioseguridad(self):
        # LA GENTE EN POBLACION DE RIESGO PRIMERO EN LA FILA
        self._sortByRiesgo()

        pacientesHechados = []
        # SI LA SALA DE ESPERA YA ES BIOSEGURA, ENTONCES NO HAGA NADA
        if self.isBioseguro():
            return pacientesHechados

        # DEJAR UNA SILLA EN MEDIO PARA LOS PACIENTES
        # i = 0
        # nuevoOrden = [None for _ in range(40)]
        # while (2*i) < len(self._pacientes):
        #     nuevoOrden[2*i] = self._pacientes[i]
        #     i += 1
        # # LOS QUE SOBRARON, AGREGARLOS AL ARRAYLIST PACIENTESHECHADOS
        # while i < len(self._pacientes):
        #     if self._pacientes[i] is not None:
        #         pacientesHechados.append(self._pacientes[i])
        #     i += 1
        nuevo_orden = []
        #print(self)
        #n_iteraciones = len(self._pacientes)
        for p in self._pacientes:
            if p != None:
                nuevo_orden.append(p)
                nuevo_orden.append(None)
            

        # UPDATE VARIABLES
        self._pacientes = nuevo_orden
        self.setBioseguro(True)

        # retorna un ArrayList con los pacientes que toco hechar porque no habia puesto para ellos
        return pacientesHechados




    #	
    #	 * sortByRiesgo: Ordena la lista pacientes(atributo de instancia)
    #	 * donde primero estan los que sean poblacion de riesgo
    #	 
    def _sortByRiesgo(self):
        i = 0
        while i < len(self._pacientes):
            key = self._pacientes[i]
            j = i - 1

            while j >= 0 and self._pacientes[j] is not None and self._pacientes[j].isPoblacionDeRiesgo() and key is not None and not key.isPoblacionDeRiesgo():
                self._pacientes[j+1] = self._pacientes[j]
                j = j - 1
            self._pacientes[j+1] = key
            i += 1




    #	
    #	 * sacarPaciente: saca al paciente de la sala de espera
    #	 
    def sacarPaciente(self, cedula):

        i = 0
        while i < len(self._pacientes):
            if self._pacientes[i] is not None and self._pacientes[i].getCedula() == cedula:
                self._pacientes[i] = None
            i += 1




    #
    #   * equivalente a toString: Retorna str bonito
    #
    def __str__(self) -> str:
        output = ""
        for idx, p in enumerate(self._pacientes):
            if self.isBioseguro() and not p:
                output += "*** \t"
            elif p:
                output += f"{p.getNombre()} \t"
            else:
                output += "libre \t"
                
            if (idx+1) % 3 == 0:
                output += "\n"
                
        return output

    #
    #	GETTERS & SETTERS
    #
    def getPacientes(self):
        return self._pacientes
    def setPacientes(self, pacientes):
        self._pacientes = pacientes

    def isBioseguro(self):
        return self._bioseguro
    def setBioseguro(self, bioseguro):
        self._bioseguro = bioseguro
