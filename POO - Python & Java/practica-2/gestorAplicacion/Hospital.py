import random
from gestorAplicacion.Especialidad import Especialidad
from gestorAplicacion.Quirofano import Quirofano
from gestorAplicacion.Doctor import Doctor
from gestorAplicacion.Paciente import Paciente


class Hospital:

    #	CONSTRUCTORS	
    def __init__(self, salaDeEspera, quirofanos, doctores):
        
        self._denuncias = 0
        self._horario = None
        self._presupuesto = 0
        self._bioseguro = False

        self._salaDeEspera = salaDeEspera
        self._quirofanos = quirofanos
        self._doctores = doctores


    #	INSTANCE METHODS  

    #	
    #	 * despedirMedico: Busca la cedula del medico a despedir
    #	 * lo "indemniza"(resta del presupuesto del hospital el salario del medico)
    #	 
    def despedirDoctor(self, cedula, justificado):
        i = 0
        while i < len(self._doctores):
            if self._doctores[i] is not None and cedula == self._doctores[i].getCedula():
                if not justificado:
                    salario_doc = self._doctores[i].getEspecialidad()._salario
                    self._presupuesto -= (self._doctores[i].getEspecialidad().getSalario())
                self._doctores[i] = None
                break
            i += 1


    #	
    #	 *getDoctorQuirofano: hace un arreglo de los doctores que estan en los quirofanos
    #	 * es decir retorna un array de instancias de tipo doctores que estan en los quirofano, recuerde que los quirofanos 
    #	 * tambien son instacias el cual tiene un atributo referenciado de tipo doctor
    #	 
    def getDoctorQuirofano(self):
        quirofanos = self._quirofanos
        contador =0

        doctores = [None for _ in range(6)]
        i = 0
        while i < len(quirofanos):
            if quirofanos[i] is not None and quirofanos[i].getDoctor() is not None:
                doctores[contador]=quirofanos[i].getDoctor()
                contador += 1
            i += 1
        return doctores



    #	
    #	 * getMuestraDoctores: Retorna un array de 3 elementos de tipo Doctor
    #	 * Que pueden corresponder a una misma especialidad o no(sobrecarga)
    #	 
    def getMuestraDoctores(self):
        muestra = [None for _ in range(3)]

        i = 0
        for doctor in self._doctores:
            if doctor is not None:
                muestra[i] = doctor
                i += 1

        return muestra

    def getMuestraDoctores(self, especialidad):
        muestra = [None for _ in range(3)]

        i = 0
        for doctor in self._doctores:
            if doctor is not None and doctor.getEspecialidad() == especialidad:
                muestra[i] = doctor
                i += 1

        return muestra

    #	
    #	 * getNDoctoresEspecialidad: Retorna la cantidad de doctores que haya en dicha especialidad
    #	 
    def getNDoctoresEspecialidad(self, especialidad):
        i =0
        for doctor in self._doctores:
            if doctor is not None and doctor.getEspecialidad() == especialidad:
                i += 1
        return i

    #	
    #	 * implementarBioseguridad: implementa medidas de bioseguridad en el hospital
    #	 
    def implementarBioseguridad(self):

        # Guardando en un ArrayList una lista de las personas que se echaron
        personasHechadas = self.getSalaDeEspera().implementarBioseguridad()

        for persona in personasHechadas:
            if persona.isAgresivo():
                self._denuncias += 1

        # ITERANDO SOBRE CADA DOCTOR PARA VER SI ESTA DISPUESTO A TRABAJAR EN PANDEMIA
        for doctor in self.getDoctores():
            if doctor is not None and not doctor.isVoluntadPandemia():
                # EL DOCTOR "RENUNCIA"
                self.despedirDoctor(doctor.getCedula(), False)
                personasHechadas.append(doctor)
        self.setBioseguro(True)
        return personasHechadas

    #	
    #	 * apartarQuirofano(int index, Paciente paciente, Doctor doctor)
    #	 * y retorna un boolean para saber si se pudo apartar el quirofano
    #	 
    def apartarQuirofano(self, index, paciente, doctor):
        suceed = False
        quirofano = self.getQuirofanos()[index]
        if quirofano is None:
            self.getQuirofanos()[index] = Quirofano(paciente, doctor)
            suceed = True
        elif quirofano.getDoctor() is None:
            quirofano.setDoctor(doctor)
            quirofano.setPaciente(paciente)
            suceed = True
        return suceed


    #	
    #	 * apartarQuirofanoUrgencia: aparta el quirofano que encuentre disponible el paciente
    #	 * que este en parametro
    #	 * @params: Paciente paciente
    #	 
    def apartarQuirofanoUrgencia(self, paciente):
        # recorre la lista de quirofano verificando si tiene un paciente  
        cirujano = None
        quirofanos = self.getQuirofanos()
        i = 0
        while i < len(quirofanos):
            if quirofanos[i] is None or quirofanos[i].getDoctor() is None:

                # Busca por cada problema medico un cirujano disponible y asigna el primero
                for tipoProblema in paciente.getTipoProblemasMedicos():
                    cirujano = self.getcirujanoEspecialidad(tipoProblema)
                    if cirujano is not None:
                        break

                # Si no hay doctores disponibles, entonces conseguir un cirujano cualquiera
                #				if(cirujano == null) { 
                #					cirujano = this.getcirujanoEspecialidad(Especialidad.GENERAL); 
                #				}

                # Si no hay cirujanos disponibles, entonces conseguir un doctor general para que opere
                # for(Especialidad)

                # Si definitiva no encontro doctor, entonces F
                if cirujano is None:
                    return None

                # Si llego hasta aca es que si hay cirujano, por lo que creemos uno
                # para que ese elemento de la lista no apunte hacia un null sino hacia un Quirofano
                quirofanos[i] = Quirofano(paciente, cirujano)
                return quirofanos[i]
            i += 1
        # Si se retorno null es porque no hay quirofanos disponibles
        return None

    #	
    #	 * getDoctorEspecialidad(Especialidad especialidad)
    #	 
    def getcirujanoEspecialidad(self, especialidad):
        for doctor in self.getDoctores():
            if doctor is not None and doctor.getEspecialidad() == especialidad and doctor.isCirujano():
                return doctor
        return None

    #	
    #	 * esparcirVirus: se esparce el virus a los pacientes en la sala de espera
    #	 * puede ser el caso que el paciente se muera al ser poblacion de riesgo y por mala suerte(azar)
    #	 
    def esparcirVirus(self):
        personasHospital = self.getPersonasHospital()
        muertos = []

        for persona in personasHospital:
            if persona.isPoblacionDeRiesgo() and random.choice([True, False]):
                muerto = persona.infectar()
                if muerto:
                    muertos.append(persona)
                    if isinstance(persona, Paciente):
                        self.getSalaDeEspera().sacarPaciente(persona.getCedula())
                    elif isinstance(persona, Doctor):
                        self.despedirDoctor(persona.getCedula(), True)
        return muertos



    #	
    #	 * 
    #	 
    def getPersonasHospital(self):
        personasHospital = []

        for persona in self.getSalaDeEspera().getPacientes():
            if persona is not None:
                personasHospital.append(persona)

        for persona in self.getDoctores():
            if persona is not None:
                personasHospital.append(persona)
        return personasHospital

    #	GETTERS & SETTERS	
    def getHorario(self):
        return self.horario
    def delCita(self, cita):
        self.horario.remove(cita)
    def addCita(self, cita):
        self.horario.add(cita)

    def getDenuncias(self):
        return self._denuncias
    def setDenuncias(self, denuncias):
        self._denuncias = denuncias

    def getSalaDeEspera(self):
        return self._salaDeEspera
    def setSalaDeEspera(self, salaDeEspera):
        self._salaDeEspera = salaDeEspera

    def getQuirofanos(self):
        return self._quirofanos
    def setQuirofanos(self, quirofanos):
        self._quirofanos = quirofanos

    def getCuartos(self):
        return self._cuartos
    def setCuartos(self, cuartos):
        self._cuartos = cuartos

    def getDoctores(self):
        return self._doctores
    def setDoctores(self, doctores):
        self._doctores = doctores

    def getPresupuesto(self):
        return self._presupuesto
    def setPresupuesto(self, presupuesto):
        self._presupuesto = presupuesto

    def isBioseguro(self):
        return self._bioseguro
    def setBioseguro(self, bioseguro):
        self._bioseguro = bioseguro

    