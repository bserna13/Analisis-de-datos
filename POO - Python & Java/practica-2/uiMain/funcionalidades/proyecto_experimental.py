#
# IMPORTS
#
import random




#
# MAIN CLASS
#

class ProyectoExperimental:
    
    def experimentar(hospital, args=None):
        tablero = [[random.choice([True, False, False, False, False]) for _ in range(10)] for _ in range(10)]
        
        ganador = True
        
        doctor = list(filter(lambda doc: doc and int(doc.getCedula()) == int(args[0]), hospital.getDoctores()))[0]
        paciente = list(filter(lambda p: p and int(p.getCedula()) == int(args[1]), hospital.getSalaDeEspera().getPacientes()))[0]
        
        args = [] if not args else args
        for cord in args[2:]:
            j, i = [int(x) for x in cord.split(",")]
            ganador = ganador and not(tablero[i-1][j-1])
        
        tablero_str = ""
        for row in tablero:
            for element in row:
                if element:
                    tablero_str += "*"
                else:
                    tablero_str += "_"
            tablero_str += "\n"
        
        resultado = "Cirugia exitosa" if ganador else f"Fallecio {paciente.getNombre()} en cirugia y el doctor {doctor.getNombre()} ha sido despedido"
        if not ganador:
            hospital.getSalaDeEspera()._pacientes.remove(paciente)
            hospital.despedirDoctor(doctor.getCedula(), True)
            
        return resultado + "\n\nPosicion de las celulas malas: \n" + tablero_str