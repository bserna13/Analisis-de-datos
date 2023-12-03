#
# IMPORTS
# 
from cmath import exp
from tkinter import *

from uiMain.funcionalidades.Fantasma import Fantasma
from baseDatos.leer import Leer
from baseDatos.actualizar import Actualizar
from uiMain.funcionalidades.Prom import Prom




# MAIN CLASS

class VentanaSistema(Tk):

    """
    Esta es la ventana de Usuario que se pide en la practica
    Tiene los Menu Archivo, Procesos y Consultas, Ayuda
    """

    # CONSTRUCTOR
    #
    def __init__(self, geometria="640x420", titulo="Clinical"):
        
        # COSITAS PARA INICIALIZAR
        super().__init__()
        self.hospital = Leer.leer() # PERSISTENCIA DE DATOS
        self.geometry(geometria)
        self.title(titulo)
        
        # COSAS PARA MANTENER EL ORDEN DE LA GRILLAY OTRAS COSAS VISUALES, EN GENERAL COSAS VISUAL
        self.grid_columnconfigure(0, weight=1)
        self.grid_rowconfigure(0, weight=1)
        self.option_add('*Dialog.msg.font', 'Helvetica 12')        
        
        # MUY IMPORTANTE, ESTE ATRIBUTO ES UTILIZADO PARA QUE NO SE ANIDEN FRAMES
        self.active_frames = [] # ES UNA LISTA DONDE SE GUARDAN LOS FRAMES QUE ESTEN ACTIVOS
        # ES UTILIZADO PARA QUE ANTES DE INICIAR UN FRAME, SE BORREN TODOS LOS QUE ESTEN
        
        # CREA EL MENU DE ARCHIVO | PROCESOS Y CONSULTAS | AYUDA
        self.crearMenu()
        
        self.mainloop()
        
        
    # 
    # METHODS
    #


    def crearMenu(self):
        """
        @params: NINGUNO
        @return: NINGUNO
        @description: CREA EL MENU DE ARCHIVO | PROCESOS Y CONSULTAS | AYUDA
        """ 
        
        menubar = Menu(self)
        self.config(menu=menubar)

        # PESTAÑA ARCHIVO
        menu_archivo = Menu(menubar, tearoff=0)
        menu_archivo.add_command(label="Aplicacion", command=self.mostrarAplicacion)
        menu_archivo.add_command(label="Salir", command=self.irInicio)
        menubar.add_cascade(label="Archivo", menu=menu_archivo)
        
        # PESTAÑA PROCESOS Y CONSULTAS
        menu_procesos = Menu(menubar, tearoff=0)
        menu_procesos.add_command(label="Experimento Sale Mal", command=self.funcExperimento)
        menu_procesos.add_command(label="Ha Llegado Un Paciente Urgente!", command=self.funcUrgencia)
        menu_procesos.add_command(label="Fanstasmon", command=self.funcFantasmon)
        menu_procesos.add_command(label="Cirugia Experimental", command=self.funcCirugia)
        menu_procesos.add_command(label="Promocion Doctor", command=self.funcPromo)

        #SUB MENU PARA COMPROBAR LA CAPA DE PERCISTENCIA
        submenu = Menu(self)
        submenu.add_command(label = "Registrar paciente", command=self.registrarPacienteSalaDeEspera)
        submenu.add_command(label = "Mostrar pacientes en la sala de espera", command=self.mostrarPacientesSalaDeEspera)
        submenu.add_command(label = "Registrar Doctor@s", command=self.registrarDoctor)
        submenu.add_command(label = "Mostrar Doctores Disponibles", command=self.mostrarDoctores)
        menu_procesos.add_cascade(label="Consultas y registros", menu = submenu)
        menubar.add_cascade(label="Procesos y Consultas", menu=menu_procesos)
        
        # PESTAÑA AYUDA
        menu_ayuda = Menu(menubar, tearoff=0)
        menu_ayuda.add_command(label="Acerca de", command=self.mostrarAcercaDe)
        menubar.add_cascade(label="Ayuda", menu=menu_ayuda)
        
        
    
    
    def irInicio(self):
        """
        @params: NINGUNO
        @return:  NINGUNO
        
        @description: SU EFECTO ES CERRAR LA VENTANA ACTUAL Y ABRE LA VENTANA DE INICIO    
        """
        from uiMain.ventana_inicio import VentanaInicio
        
        self.destroy()
        Actualizar.actualizar(self.hospital)
        VentanaInicio()
        
        
        
        
    def kill_frames(self):
        """
        @description: Utilidad para borrar los frames que esten en la ventana
        Esto es util porque cuando ejecutamos las funcionalidades, creamos frames
        Y para ejecutar otra, lo que hacemos es borrar el frame que estaba de la anterior
        funcionalidad y crear uno, esta funcion solo borra el frame que se creo en la anterior func
        """
        for f in self.active_frames:
            f.pack_forget()
    
    
    #
    # Funcionalidades
    
    
    def funcExperimento(self):
        """
        @description: Funcionalidad de cuarentena del programa
        """
        from uiMain.field_frame import FieldFrame
        from uiMain.funcionalidades.cuarentena import Cuarentena
        self.kill_frames()
        descripcion = "En este hospital tan particular de un mundo ficticio, hay un laboratorio lider en investigacion\n"
        descripcion += "que actualmente se encuentra haciendo experimentos con un nuevo virus que hace envejecer a\n"
        descripcion += "las personas La funcionalidad hace que el experimento salga mal y se esparsa el virus por el hospital\n"
        descripcion += "ejecutando esta funcionalidad puede hacer que las cosas salgan mal"

        frame = FieldFrame(self, 
                           "Experimento con Nuevo Virus sale mal", 
                           descripcion, 
                           Cuarentena.decretarCuarentena,
                           self.hospital)
        self.active_frames.append(frame)        
    
    
    
    
    def funcUrgencia(self):
        """
        @description: Funcionalidad de que llega un paciente a urgencias
        """
        from uiMain.field_frame import FieldFrame
        from uiMain.funcionalidades.urgencia import Urgencia 
        
        self.kill_frames()
        
        descripcion = """
        Alguien ha llegado a emergencias, quien sera?
        Esta funcionalidad determina de manera aleatoria varias cosas
        , cosas sobre este paciente, y cada ejecucion varia
        estara identificado? porque vino aca? que se hara con el?
        """.replace("\t","")
        
        frame = FieldFrame(self, 
                           "Urgencia en Emergencias",
                           descripcion,
                           Urgencia.nueva_urgencia,
                           self.hospital)        
        self.active_frames.append(frame)        
        
    
    
    
    def funcFantasmon(self):
        from uiMain.field_frame import FieldFrame

        descripcion = "En este hospital hace mucho tiempo murio un paciente por culpa de la mala administracion\n"
        descripcion += "del hospital, entonces este está rondando por el hospital donde si alguien posee problemas \n"
        descripcion += "cardiacos la puede pasar muy mal\n"

        
        
        self.kill_frames()
        frame = FieldFrame(self, 
                           "Fanstasmon",
                           descripcion,
                           Fantasma.despertar_fantasmon,
                           self.hospital)# aca iria self.hospital para la persistencia        
        self.active_frames.append(frame)        
        
    
    
    
    
    def funcCirugia(self):
        """
        @description: Funcionalidad de que se opera a alguien
        se cambio el enfoque de juego como buscaminas a uno tipo battleship
        porque para aprovechar los entrys
        """
        from uiMain.field_frame import FieldFrame
        from uiMain.funcionalidades.proyecto_experimental import ProyectoExperimental
        
        descripcion = """
        En este hospital tan particular se tiene un proyecto experimental para curar una gripa operando,
        como es un proyecto experimental se necesita un doctor que este dispuesto a ello
        , y el usuario tiene el poder de elegirlo<Cedula Doctor>, ademas como es una cirugia, el doctor debe operar a alguien,
        el usuario elige a alguien de la sala de espera <Cedula Paciente>
        
        Para realizar la cirujia en el sistema, el pulmon del paciente se divide por secciones,
        y se tiene un tablero de 10x10, y el usuario elije donde va a utilizar un poderoso laser 
        que elimina la gripa, para ello se esperan cordenadas en un formato <Fila>,<Columna>
        """.replace("\t","")
        
        self.kill_frames()
        frame = FieldFrame(self, "(Experimento) Cirugia Contra La Gripa", 
                           descripcion, 
                           ProyectoExperimental.experimentar, 
                           self.hospital,
                           "Entradas", 
                           ["Cedula Doctor", "Cedula Paciente", "Coordenadas 1", "Coordenadas 2", "Coordenadas 3"],
                           "Entradas")        
        self.active_frames.append(frame)        
    
    
    
    
    
    def funcPromo(self):
        from uiMain.field_frame import FieldFrame

        descripcion="Aquí se podran promover o ascender a los doctores en el hospital pero para esto tendra que pasar \n"
        descripcion+="por unas dificultades el cual si son superadas el doctor podrá tener un mejor cargo, para esto\n"
        descripcion+="se recolectan doctores todo el hospital, los que estan en el hospital disponibles o en los quirofanos"
        
        self.kill_frames()
        frame = FieldFrame(self, "Promo", descripcion,Prom.SubirDoctor,self.hospital)       
        self.active_frames.append(frame)        
    
    
    
    
    #
    #CRUD PURO PARA ENSAYAR LA CAPA DE PERSISTENCIA
    #
    def registrarDoctor(self):
        """
        Crea un doctor y lo añade a self.hospital._doctores(este atributo es una lista)
        """
        from uiMain.field_frame import FieldFrame
        from gestorAplicacion.Doctor import Doctor
            
        self.kill_frames()
    
        frame = FieldFrame(self, "Registrar Doctor", "Descripcion generica", lambda _, args: self.hospital._doctores.append(Doctor(*args)), self.hospital, "Atributos", ["Nombre", "Cedula"])        
        self.active_frames.append(frame)  




    def mostrarDoctores(self):
        """
        Muestra los doctores que hayan, un label por cada uno dando la cedula y nombre, ni mas ni menos
        """        
        self.kill_frames()
        infos = Frame(self)
        
        for idx, doctor in enumerate(self.hospital._doctores):
            if doctor:
                Label(infos, text=f"Nombre: {doctor.getNombre()} | Cedula: {doctor.getCedula()}").grid(row=idx, column=0)        
        
        infos.pack(expand=True, fill="both")
        self.active_frames.append(infos) 
        
        
        
        
    def registrarPacienteSalaDeEspera(self):
        """
        Crea pacientes y los añade a self.hospital._salaDeEspera._pacientes que es una lista
        """
        
        from uiMain.field_frame import FieldFrame
        from gestorAplicacion.Paciente import Paciente
            
        self.kill_frames()
        frame = FieldFrame(self, "Crear Paciente", "Descripcion generica", 
                           lambda _, args: self.hospital.getSalaDeEspera()._pacientes.append(Paciente(*(args + [False, [None], -1]))), 
                           self.hospital,"Atributos", ["Nombre", "Cedula"])        
        
        self.active_frames.append(frame)  




    def mostrarPacientesSalaDeEspera(self):     
        """
        Muestra los pacientes que hayan en self.hospital._salaDeEspera._pacientes
        """   
        self.kill_frames()
        infos = Frame(self)
        
        for idx, paciente in enumerate(self.hospital.getSalaDeEspera().getPacientes()):
            if paciente:
                Label(infos, text=f"Nombre: {paciente.getNombre()} | Cedula: {paciente.getCedula()}").grid(row=idx, column=0)        
        
        infos.pack(expand=True, fill="both")
        self.active_frames.append(infos) 




    def mostrarAplicacion(self):
        """
        @description: Muestra una descripcion del sistema en un texto de dialogo
        Esta funcion es ejecutada cuando se pulsa Aplicacion en el menu de Archivo
        """
        descripcion_del_sistema = """
        CLInical no tiene aplicación en el mundo real y es solo un programa con propósito académico, está contextualizado con el programa para un hospital, 
        no es práctico para la vida real porque las funcionalidades están sobre situaciones imaginarias, 
        por ende no resuelve ningún problema de negocio, administración, etc.
        
        Dicho esto, las funcionalidades son diferentes y no tienen relacion entre si, por lo que para
        mas informacion acceda a las funcionalidades en el menu de Procesos y Consultas
        """
        from tkinter import messagebox
        messagebox.showinfo("Descripcion del sistema", " ".join(descripcion_del_sistema.split()))
    
    
    
    
    def mostrarAcercaDe(self):
        """
        @description: Muestra los nombres de los autores en un texto de dialogo
        Esta funcion es ejecutada cuando se pulsa AcercaDe en el menu de Ayuda
        """
        from tkinter import messagebox
        messagebox.showinfo("Autores","Daniel Correa\nBrahian Serna")