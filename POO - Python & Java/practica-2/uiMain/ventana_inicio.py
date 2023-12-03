
#
# IMPORTS
#
# NOTA: EN LOS SIGUIENTES METODOS DE LA CLASE VENTANAINICIO SE IMPORTA LOS SIGUIENTES PAQUETES CUANDO SE EJECUTEN
# metodo: ingresarSistema, import: VentanaSistema from ventana_sistema
from tkinter import *
from exceptions import *



#
# CLASS
#   
class VentanaInicio(Tk):
    
    #
    # CONSTUCTOR
    #
    def __init__(self, geometria="680x420", titulo="Bienvenido") -> None:
        super().__init__()
        
        # COSITAS GENERALES
        self.geometry(geometria)
        self.title(titulo)
        
        self.crearMenu()    # Crea el menu que tiene las opciones salir y descripcion
        self.crearEstructura()  # Crea los Pn(Frames) como se piden en el documento, cada Pn es un atributo
        
        # CREAR LA DESCRIPCION DEL SISTEMA, AUNQUE SU "APARICION" ESTA LIGADO AL MENU INICIO -> DESCRIPCION
        self.descripcion = Label(self.P4, text="""
                                 Clinical no tiene aplicación en el mundo real y es solo un programa 
                                 con propósito académico, está contextualizado con el programa para 
                                 un hospital, no es práctico para la vida real porque las funcionalidades 
                                 están sobre situaciones imaginarias, por ende no resuelve 
                                 ningún problema de negocio, administración, etc.
                                 """.replace("\t",""))
        self.showDescripcion = False
        
        # Se ejecutan los siguientes metodos donde cada uno se encarga de los items que estan en el documento
        self.bienvenida()  
        self.mostrarCV()
        self.mostrarIngresar()
        
        self.i_foto_sistema = 0
        self.contenedor_foto_inicio = Label(self.P4)
        self.contenedor_foto_inicio.bind("<Leave>", self.mostrarFotoInicio)
        self.contenedor_foto_inicio.bind("<Button-3>", self.mostrarErrorBoton3)
        self.mostrarFotoInicio()
        
        self.mainloop()
    
    
    
    
    # ********
    # METHODS
    #
    
    
    def mostrarErrorBoton3(self, event):
        from exceptions import Irrespetuso
        raise Irrespetuso("No me des click derecho >:v")
    
    
    
    
    def mostrarErrorBoton1(self, event):
        from exceptions import Irrespetuso
        raise Irrespetuso("No me des click >:v")
    
    
    

    def mostrarFotoInicio(self, event=None):
        """
        @param: NINGUNO
        @return: NINGUNO
        @description: Muestra las fotos del programa en P4
        """
        self.foto_sistema = PhotoImage(file=f"uiMain/assets/sc{self.i_foto_sistema}.png")
        self.contenedor_foto_inicio.configure(image=self.foto_sistema)
        self.contenedor_foto_inicio.pack()        
        self.i_foto_sistema = self.i_foto_sistema + 1 if self.i_foto_sistema < 4 else 0

    
    

    def crearMenu(self):
        """
        @param: NINGUNO
        @return: NINGUNO
        @description: Crea el menu que tiene la ventana de inicio con las opciones salir y descripcion
        """    
        menubar = Menu(self)
        self.config(menu=menubar)
        
        inicio = Menu(menubar)
        menubar.add_cascade(label="Inicio", menu=inicio)
        
        inicio.add_command(label="Descripcion", command=self.crearDescripcion)
        inicio.add_command(label="Salir", command=lambda : self.destroy())
    
    

    def crearDescripcion(self):
        """
        @description: Hace que la descripcion del programa aparezca en la ventana de inicio
        esta funcion es ejecutada cuando se de en "Descripcion" en el menu de Inicio
        """
        self.descripcion.configure(anchor="center")
        if not self.showDescripcion:
            self.descripcion.pack(expand=True, fill="both", anchor="center")
        else:
            self.descripcion.pack_forget()
        self.showDescripcion = not self.showDescripcion
            
        
    
    
    
    def crearEstructura(self):
        """
        @param: NINGUNO
        @return: NINGUNO
        @description: Le da la estructura como en la Figura 1 del documento de la practica2
        """        
        
        # COSAS RARAS PARA QUE LA CUADRILLA QUEDE BIEN
        #self.P1 = Frame(self, bg="blue")
        #self.P2 = Frame(self, bg="red")        
        self.P1 = Frame(self)
        self.P2 = Frame(self)
        
        self.P1.grid(row=0, column=0, ipadx=10, ipady=10, sticky="nsew")
        self.P2.grid(row=0, column=1, ipadx=10, ipady=10, sticky="nsew")
                
        self.grid_columnconfigure(0, weight=1, uniform="group1")
        self.grid_columnconfigure(1, weight=1, uniform="group1")
        self.grid_rowconfigure(0, weight=1)
        
        
        self.P1.grid_rowconfigure(0, weight=1, uniform="group2")
        self.P1.grid_rowconfigure(1, weight=1, uniform="group2")
        self.P1.grid_rowconfigure(2, weight=1, uniform="group2")
        self.P1.grid_rowconfigure(3, weight=1, uniform="group2")
        self.P1.grid_columnconfigure(0, weight=1)
        
        self.P2.grid_rowconfigure(0, weight=1, uniform="group2")
        self.P2.grid_rowconfigure(1, weight=1, uniform="group2")
        self.P2.grid_rowconfigure(2, weight=1, uniform="group2")
        self.P2.grid_rowconfigure(3, weight=1, uniform="group2")
        self.P2.grid_columnconfigure(0, weight=1)
        
        #self.P3 = Frame(self.P1, bg="black")
        #self.P4 = Frame(self.P1, bg="white")
        self.P3 = Frame(self.P1)
        self.P4 = Frame(self.P1)
        self.P3.grid(row=0, column=0, sticky="nsew", padx=30, pady=10)
        self.P4.grid(row=1, column=0, sticky="nsew", padx=30, pady=10, rowspan=3)
        
        #self.P5 = Frame(self.P2, bg="black")
        #self.P6 = Frame(self.P2, bg="white")
        self.P5 = Frame(self.P2)
        self.P6 = Frame(self.P2)
        self.P5.grid(row=0, column=0, sticky="nsew", padx=30, pady=10, rowspan=1)
        self.P6.grid(row=1, column=0, sticky="nsew", rowspan=3, padx=30, pady=10)    
    
    
    
    
    def bienvenida(self):        
        """
        @param: NINGUNO
        @return: NINGUNO
        @description: DAR LA BIENVENIDA AL USUARIO
        """
        mensaje_de_bienvenida = Entry(self.P3, font=("Helvetica", 25))
        mensaje_de_bienvenida.insert(0, "Bienvenido!")
        mensaje_de_bienvenida.config(state="disabled")
        mensaje_de_bienvenida.pack(expand=True, fill="both")
        
        
    
        
    def mostrarCV(self):
        """
        mostrarCV
        @return: NINGUNO
        @params: NINGUNO
        @description: Este metodo se encarga de mostrar el CV(Hoja de vida) de los desarrolladores en P5
        """
        desarrolladores = [
            {
                "nombre": "Daniel Correa",
                "ocupacion": "Estudiante de Ciencias de la Computacion en la Universidad Nacional De Colombia",
                "Perfil": "no c"
            },
            {
                "nombre": "Brahian Serna",
                "ocupacion": "Estudiante de Estadistica en la Universidad Nacional De Colombia"
            }
        ]
        
        def cambiarDesarrollador(event):
            descripcion.config(state="normal")
            if not ("Daniel" in descripcion.get("1.0",'end-1c')):
                desarrollador = desarrolladores[0]
                self.mostrarFotos("dan")
            else:
                desarrollador = desarrolladores[1]    
                self.mostrarFotos("bra")    # Mientras tanto
            descripcion.delete("1.0", END)
            output = f"Nombre: {desarrollador['nombre']} \n"
            output += f"Ocupacion: {desarrollador['ocupacion']} \n"
            
            descripcion.insert("1.0",output)
            descripcion.config(state="disabled")

        descripcion = Text(self.P5)
        cambiarDesarrollador(None)
        descripcion.pack(expand=True, fill="both", ipady=10)
        descripcion.bind("<Button-1>", cambiarDesarrollador)
        
        
        
        
    def mostrarFotos(self, dev="dan"):
        """
        @arg dev: el nombre que se pase, ya sea <dan/bra> determina de 
        quien son las fotos que se van a mostrar, por defecto muestra a las de daniel
        @description: muestra en una grilla en P6 4 fotos del desarrollador que se pase como <dev>
        """
        # Cosas para configurar la grilla
        for i in range(2):
            self.P6.grid_rowconfigure(i, weight=1, uniform="g")
            self.P6.grid_columnconfigure(i, weight=1, uniform="g")
            
        # PONER LAS FOTOS
        posiciones = {1: "se", 2: "ne", 3: "sw", 4: "nw"}   # USO POSTERIOR CON STICKY
        self.photos = {} # importante para mantener referencia a las fotos, para que funcione se debe mantener referencia de
        for i in [1,2,3,4]:
            self.photos[i] = PhotoImage(file=f"uiMain/assets/{dev}{i}.png")
            x = Label(self.P6, image=self.photos[i])
            x.bind("<Button-1>", lambda _: Irrespetuso(",no me pickees >:v"))   # para que genere error si se pickea las fotos
            x.grid(row=(i-1)%2, column=(i-1)//2, sticky=posiciones[i])  # hace el display en la cuadricula
        
        
        
    
        
    def mostrarIngresar(self):
        """
        @params: NINGUNO
        @return: NINGUNO
        @description: CREA Y MUESTRA EL BOTON DE INGRESO AL SISTEMA EN LA PARTE BAJA DE P4
        """    
        boton_ingresar = Button(self.P4, text="Ingesar al Sistema", command=self.ingresarSistema)
        boton_ingresar.pack(side=BOTTOM, ipady=10, pady=20)
        
    
    
    
    def ingresarSistema(self):
        """
        @params: NINGUNO
        @return: NINGUNO
        @description: Esta funcion es usada cuando se presiona el boton de "Ingresar al sistema"
        Su efecto es cerrar la ventana de bienvenida y abrir la ventana del sistema    
        """
        from uiMain.ventana_sistema import VentanaSistema
        self.destroy()
        VentanaSistema()
        
        
        
        
    