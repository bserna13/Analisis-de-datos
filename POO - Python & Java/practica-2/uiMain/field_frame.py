
# 
# IMPORTS
#
from tkinter import *

from exceptions import DatoInexistente


# 
# MAIN CLASS
#
class FieldFrame(Frame):
    """
    crea un nuevo objeto de tipo FieldFrame
    @arg tituloCriterios titulo para la columna "Criterio"
    @arg criterios array con los nombres de los criterios
    @arg tituloValores titulo para la columna "valor"
    @arg valores array con los valores iniciales; Si ‘None’, no hay valores iniciales
    @arg habilitado array con los campos no-editables por el usuario; Si ‘None’, todos son editables
    """
    
    
    # 
    # CONSTRUCTOR
    #
    def __init__(self, ventana, nombre, descripcion, funcionalidad, hospital=None, tituloCriterios=None, criterios=None, tituloValores=None, valores=None, habilitado=None):
        
        # COSITAS GENERALES
        #super().__init__(ventana, bg="blue")
        super().__init__(ventana)
        self.nombre = nombre
        self.descripcion = descripcion
        self.criterios = criterios if criterios else []
        self.funcionalidad = funcionalidad
        self.hospital = hospital if hospital else None
        
        self.tituloCriterios = [] if not tituloCriterios else tituloCriterios
        self.criterios = [] if not criterios else criterios
        self.tituloValores = [] if not tituloValores else tituloValores
        self.valores = ["" for _ in range(len(self.criterios))] if not valores else valores
        
        # 4 DE BASE PORQUE 1 PARA EL TITULO, 1 PARA LA DESCRIPCION, 1 EN LA MITAD PARA MANTENER PROPORCION, 1 PARA LOS BOTONES
        self.n_rows = len(self.criterios) + 6
        
        # COSAS PARA QUE CUADRE LA CUADRILLA
        self.pack(side="top", fill="both", expand=False, ipady=20)
        self.grid_columnconfigure(0, weight=1)
        self.grid_rowconfigure(0, weight=1)
        
        self.generarInterfaz()
    
    
    
    
    # 
    # METHODS
    #    
    
    
    def generarInterfaz(self):
        """
        @description: Crea la etiqueta y la descripcion
        """
        self.configureGrid()
        self.crearEtiquetaNombre()
        self.crearDescripcion()
        self.crearBotones()
        self.crearInputs()
    
    
    
    
    def getValue(self, criterio):
        """
        @arg criterio el criterio cuyo valor se quiere obtener
        @return el valor del criterio cuyo nombre es 'criterio'
        """
        return self.valores[self.criterios.index(criterio)].get()
    
    
    
    def crearInputs(self):
        """
        @description: Crea los campos de textos que son los inputs que espera el programa
        """
        new_valores = [Entry(self) for _ in range(len(self.criterios))]
        
        # SI HAY TITULO PARA EL CRITERIO, HACERLE DISPLAY
        if self.tituloCriterios:
            label_titulo_criterio = Label(self, text=self.tituloCriterios)
            label_titulo_criterio.grid(row=3, column=0)
        
        # SI HAY TITULO PARA LOS VALORES, HACERLE DISPLAY
        if self.tituloValores:
            label_titulo_valores = Label(self, text=self.tituloValores)
            label_titulo_valores.grid(row=3, column=1)
        
        # POR CADA CRITERIO QUE SE PASE, CREAR EN LA COLUMNA 0 UN LABEL CON TAL CRITERIO
        # Y EN LA COLUMNA 1 UN ENTRY QUE SE UTILIZA PARA EL GETVALUE
        for idx, (criterio, valor) in enumerate(list(zip(self.criterios, self.valores))):
            label_criterio = Label(self, text=criterio)
            new_valores[idx].insert(0, valor)
            
            label_criterio.grid(row=4+idx, column=0)
            new_valores[idx].grid(row=4+idx, column=1)
        self.valores = new_valores  # UTIL PARA GETVALUE
        
    
    
    
    
    
    def configureGrid(self):
        """
        @description: Genera una cuadricula de
        2 Columnas
        n Filas
        Donde como minimo hay 4, una para el titulo, descripcion
        una debajo de la descripcion de gratis y una ultima para los botones
        """
        self.grid_columnconfigure(0, weight=1)
        self.grid_columnconfigure(1, weight=1)
        
        
        for i in range(self.n_rows):
            self.grid_rowconfigure(i, weight=1)
    
    
    
    
    def crearEtiquetaNombre(self):
        """
        @description: se encarga de crear y poner la etiqueta
        <Nombre del Proceso o Consulta> en el Frame
        """
        etiqueta_nombre = Label(self, text=self.nombre, borderwidth=2, relief="ridge")
        etiqueta_nombre.configure(anchor="center")
        etiqueta_nombre.grid(row=0, column=0, columnspan=2, ipadx=20, ipady=20, pady=20)
    
    
    
    
    def crearDescripcion(self):
        """
        @description: se encarga de crear y poner la descripcion 
        <Descripcion del detalle del proceso o consulta> en el Frame
        """
        descripcion = Label(self, text=self.descripcion, borderwidth=2, relief="ridge")
        descripcion.configure(anchor="center")
        descripcion.grid(row=1, column=0, columnspan=2, ipadx=20, ipady=20, pady=50, padx=50, sticky="ew")
        
        
        
        
    def crearBotones(self):
        """
        @description: crea los botones aceptar y borrar
        """
        aceptar = Button(self, text="Aceptar", command=self.ejecutarFuncionalidad)
        borrar = Button(self, text="Borrar", command=self.borrarEntradas)
        
        aceptar.grid(row=self.n_rows-2, column=0, ipadx=10, ipady=10)
        borrar.grid(row=self.n_rows-2, column=1, ipadx=10, ipady=10)
        
        
    def ejecutarFuncionalidad(self):             
        if len(self.valores) > 0:
            for idx, v in enumerate(self.valores):
                if v.get() == "":
                    from exceptions import FaltanDatos       
                    raise FaltanDatos(f"te falto colocar valor en {self.criterios[idx]}")
                    return
                elif v.get() in ["Mbappe", "Pogba", "Benzema"] and "Nombre" in self.criterios[idx]:
                    from exceptions import EseNombreNo
                    raise EseNombreNo("ese nombre no esta permitido xd")
                    return
                elif "Cedula" in self.criterios[idx] and ((not v.get().isnumeric()) or int(v.get()) < 0):
                    from exceptions import CedulaFea
                    raise CedulaFea("Esa cedula no esta permitida")
                    return
                elif (not "Registrar" in self.nombre) and ("Cedula" in self.criterios[idx]) and (len(list(filter(lambda x: x and int(x.getCedula()) ==int(v.get()), self.hospital.getPersonasHospital()))) == 0):
                    from exceptions import DatoInexistente
                    raise DatoInexistente("No existe nadie en el hospital con esa cedula")
                    return
                elif len(list(filter(lambda char: char in "!\"#$%&/()='?¿¡´*¨[]{}-_.:;><|°", v.get()))) > 0:
                    from exceptions import CaracterFeo
                    raise CaracterFeo("Noo, los caracteres" + list(filter(lambda char: char in "!\"#$%&/()='?¿¡´*¨[]{}-_.:;><|°", v.get())) + "no estan permitidos")
                    return
                elif "Nombre" in self.criterios[idx] and len(v.get()) == 1:
                    from exceptions import EseNombreNo
                    raise EseNombreNo("Nombre muy corto")
                    return
                elif "Nombre" in self.criterios[idx] and len(v.get()) > 30:
                    from exceptions import EseNombreNo
                    raise EseNombreNo("Nombre muy largo")
                    return
                else:
                    values = [v.get() for v in self.valores]
            output_text = self.funcionalidad(self.hospital, values)
            output_widget = Text(self)
            output_widget.insert("1.0", output_text if type(output_text)==type("xd") else "")
            output_widget.grid(row=self.n_rows-1, column=0, columnspan=2, sticky="nsew")
        else:
            output_text = self.funcionalidad(self.hospital)
            output_widget = Text(self)
            output_widget.insert("1.0", output_text if type(output_text)==type("xd") else "")
            output_widget.grid(row=self.n_rows-1, column=0, columnspan=2, sticky="nsew")
            
            
            
            
    def borrarEntradas(self):
        for v in self.valores:
            v.delete(0, "end")