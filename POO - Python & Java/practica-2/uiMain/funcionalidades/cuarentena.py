from tkinter import messagebox
from gestorAplicacion.Hospital import Hospital
from gestorAplicacion.SalaDeEspera import SalaDeEspera
from gestorAplicacion.Paciente import Paciente

class Cuarentena:
    
    def decretarCuarentena(hospital):        
        messagebox.showinfo(message="Hoy es un dia normal y corriente")
        messagebox.showinfo(message="Estado actual de la sala de espera")
        
        messagebox.showinfo(message=hospital.getSalaDeEspera())
        
        messagebox.showinfo(message="Se esta trabajando con un nuevo virus muy raro")
        messagebox.showinfo(message="Tal laboratorio se encuentra al lado de la sala de espera")
        messagebox.showinfo(message="Oh no, se ha derramado la muestra, y la puerta esta abierta F")
        messagebox.showerror(message="El virus se ha esparcido")
        
        muertos = hospital.esparcirVirus()
        despedida = ""
        for m in muertos:
            despedida += f"{m.getNombre()} ha fallecido :( \n"
            
        if despedida != "":
            messagebox.showinfo(message=despedida)
        
        messagebox.showinfo(message="La cuarentena ha empezado\n *Comprando antibacteriales* \n Implementado Bioseguridad...")
        
        personas_hechadas = hospital.implementarBioseguridad()
        output = ""
        
        for p in personas_hechadas:
            output += f"{p.getNombre()} ha sido hechado de la sala de espera \n"
        
        messagebox.showinfo(message='Nadie ha sido hechado de la sala de espera' if not output else output)
        
        messagebox.showinfo(message="Estado despues de implementar bioseguridad")
        messagebox.showinfo(message=hospital.getSalaDeEspera())
        return "Estado de la sala de espera despues de implementar bioseguridad: \n" + str(hospital.getSalaDeEspera())