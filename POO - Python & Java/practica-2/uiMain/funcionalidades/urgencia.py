
#
# IMPORTS
#
from tkinter import messagebox
from gestorAplicacion.Especialidad import Especialidad

from gestorAplicacion.Sintomas import Sintomas
from gestorAplicacion.Paciente import Paciente
from gestorAplicacion.Hospital import Hospital
from gestorAplicacion.Quirofano import Quirofano
from gestorAplicacion.Doctor import Doctor
from gestorAplicacion.Factura import Factura

import random




#
# Main Class
# 


class Urgencia:
    """
    @description: Un paciente llega a emergencias, quien sera? eso lo determinara el azar
    """
    def nueva_urgencia(hospital):
        transporte_opciones = ["helicoptero", "ambulancia", "carro particular"]
        transporte = random.choice(transporte_opciones)
        messagebox.showinfo(message=f"Ha llegado alguien en {transporte}")
        
        opciones = {
            "le dio un paro cardiaco mientras se comia una hamburguesa": Sintomas.DOLORPECHO,
            "le envenenaron la comida": Sintomas.DOLORESTOMAGO,
            "tuvo un shock electrico y le dio un paro cardiaco": Sintomas.DEFORMACION,
            "le dio un paro cardia": Sintomas.DOLORPECHO,
            "se puso un stricker y ahora la piel se ve bastante mal": Sintomas.PIELRARA,
            "lamio la calle y ahora tiene una tos increible": Sintomas.TOS
        }
        
        causa = random.choice(list(opciones.keys()))
        messagebox.showinfo(message=f"El paciente llego a urgencias porque {causa}")
        
        identificado = random.choice([True, False])
        
        output_final = ""
        
        if identificado:
            nombre_opciones = ["Juan", "Pedro", "Camilo", "Julian", "Daniel", "Sebastian", "Maria", "Juliana", "Natalia"]
            nombre = random.choice(nombre_opciones)
            
            cedula = random.randint(1000000, 19999999)
            
            messagebox.showinfo(message=f"El paciente se llama {nombre} y esta identificado con la cedula {cedula}")
            
            profesiones_opciones = ["militar", "doctor", "otro"]
            profesion = random.choice(profesiones_opciones)
            
            descuento = 0
            if profesion == "militar":
                messagebox.showinfo(message=f"El paciente es militar y redimio el codigo MILITAR, que le da un 50% de descuento en su cirugia B)")
                descuento = 0.50
            elif profesion == "doctor":
                messagebox.showinfo(message=f"El paciente es un doctor y tiene un descuento del 15%")
                descuento = 0.15
            
            # CREAR INSTANCIA PACIENTE
            paciente = Paciente(nombre, cedula, random.choice([True, False]), [opciones[causa]], -1)
            quirofano_urgente = hospital.apartarQuirofanoUrgencia(paciente)
            
            if quirofano_urgente == None:
                output_final = "F, el paciente murio porque no habia ciruganos/doctores disponibles X_X"
            else:
                factura = Factura(opciones[causa].getEspecialidad().costoCirujia, descuento)
                output_final = f"Ya lo estan operando\nLa factura llego por {factura.getValorPagar()}"
        else:
            messagebox.showwarning(message="El paciente no esta identificado :(, esto genera problemas")
            doctor = hospital.getcirujanoEspecialidad(opciones[causa])
            
            if doctor:
                messagebox.showwarning(message="Segun las politicas del hospital, no podemos operar al paciente al no estar identificado")
                messagebox.showinfo(message="Pero... podriamos operarlo de todas maneras  ( ͡° ͜ʖ ͡°)")
                operar = messagebox.askyesno(message="Quieres operar el paciente a escondidas?")
                if operar:
                    output_final = "Estan operando al paciente"
                else:
                    output_final = "El paciente murio X_X"
            else:
                doctor = hospital.getcirujanoEspecialidad(Especialidad.GENERAL)
                if doctor:
                    output_final = f"El doctor {doctor.getNombre()} va a realizar la operacion, es de medicina general, sirve"
                else:
                    output_final = "Igual no habia doctores, no hubo nada que hacer, el paciente murio X_X"
            
        return output_final