import pickle
from pydoc import Doc

class Leer:
    def leer():
        try:
            file = open("./baseDatos/tmp/objetos.pkl", "rb")
            return pickle.load(file)
        except:
            from gestorAplicacion.Hospital import Hospital
            from gestorAplicacion.Doctor import Doctor
            from gestorAplicacion.Quirofano import Quirofano
            from gestorAplicacion.Paciente import Paciente
            from gestorAplicacion.SalaDeEspera import SalaDeEspera
            from gestorAplicacion.Sintomas import Sintomas
            from gestorAplicacion.Jerarquia import Jerarquia
            from gestorAplicacion.Especialidad import Especialidad
            print("Error 2.0")
            
            pacientes = [
                Paciente("Juanito",12412,False, [Sintomas.DEFORMACION, Sintomas.DOLORESTOMAGO], -1),
                Paciente("Maria", 1215, True, [Sintomas.DOLORPECHO, Sintomas.FIEBRE], -1),
                Paciente("Carlos", 125125, True, [Sintomas.GRIPA, Sintomas.PIELRARA], -1),
                Paciente("Horacio", 124115, False, [Sintomas.DOLORPECHO], -1),
                Paciente("Manolo", 124515, False, [Sintomas.FIEBRE, Sintomas.GRIPA])
            ]
            
            
            doctores = [
                Doctor("Sebastian", 12236, False, Especialidad.CARDIOLOGIA, True, Jerarquia.DIRECTORMEDICO),
                Doctor("Agustin", 1251898, False, Especialidad.PLASTICA, True, Jerarquia.JEFE),
                Doctor("Laura", 12591, False, Especialidad.GENERAL, True, Jerarquia.JEFE),
                Doctor("Fernanda", 23351, False, Especialidad.DERMATOLOGIA, True, Jerarquia.PLANTA),
                Doctor("Juliana", 193765, False, Especialidad.GENERAL, True, Jerarquia.INTERNO)
            ]
            
            quirofanos = [Quirofano(Paciente("Gustavo", 198532, True, [Sintomas.DOLORPECHO], -1), doctores[1]), Quirofano(Paciente("Rodrigo", 1293296, False, [Sintomas.DOLORESTOMAGO], -1), doctores[1])]+ [Quirofano(None, None) for _ in range(10)] + [Quirofano(Paciente("Andrea", 1990235, False, [Sintomas.DOLORPECHO], -1), doctores[2])]
            
            return Hospital(SalaDeEspera(pacientes), quirofanos, doctores)
        