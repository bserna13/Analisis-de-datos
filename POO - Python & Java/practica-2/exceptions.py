
#
# IMPORTS
#
from tkinter import messagebox




#
# CLASSES(ERRORS)
#


class ErrorAplicacion(Exception):
    """
    @description: Es la clase madre de todos los errores que se van a generar en la aplicacion
    tiene 2 hijos MalUso y UsuarioRacista(?), los cuales a su vez tienen 4 hijos cada uno
    """
    def __init__(self, message=""):
        self.message = message
        super().__init__(self.message)




class AlSistemaNoLeGusta(ErrorAplicacion):
    """
    @description: Este tipo de error se piensa utilizar para que cuando el usuario ingrese
    un dato que al sistema le parezca racista :v, fue lo primero que se me ocurrio despues se cambia xd
    """
    def __init__(self, title, message=""):
        super().__init__("AlSistemaNoLeGusta: Al sistema no le gusta lo que pusiste " + message)
        messagebox.showerror(title, self.message)

   
   
   
class EseNombreNo(AlSistemaNoLeGusta):
    """
    @description: Este tipo de error se piensa utilizar cuando el usuario ingrese un nombre que este prohibido
    """
    def __init__(self, message=""):
        super().__init__("Nombre Feo :v", ", el nombre que ingresaste no esta permitido " + message)
   
        

class CedulaFea(AlSistemaNoLeGusta):
    """
    @description: Este tipo de error se piensa utilizar cuando ingresen la edad de un bebe
    """
    def __init__(self, message=""):
        super().__init__("Cedula Invalida", "Al sistema no le gusta ese numero de cedula " + message)
        
        
        
        
class CaracterFeo(AlSistemaNoLeGusta):
    """
    @description: Al sistema no le gusta ningun caracter raro, por lo que si
    en un entrybox se coloca algo que no sea palabra o letra, da error
    """
    def __init__(self, message=""):
        super().__init__("Caracter Malo", "al sistema no le gustan los caracteres raros")



        
class MalUso(ErrorAplicacion):
    """
    @descripcion: Tipo de error relacionado a que el usuario no esta interactuando bien con el sistema
    ya sea porque ingreso datos invalidos, o no ingreso, o cualquier cosa que entre en la categoria
    de 'El usuario esta usando el sistema mal'. ESTA EXCEPCION ARROJA UN MENSAJE DE ERROR
    @note: no es buena practica instanciar desde esta clase sino desde sus hijas
    """
    def __init__(self, title, message=""):
        super().__init__("ErrorPorMalUso: Estas Usando la Aplicacion mal " + message)
        messagebox.showerror(title, self.message)
        
        
        
        
class DatoInexistente(MalUso):
    """
    @description: Excepcion hija de MalUso, esta pensada para hacer raise de esta 
    cuando se introduzca un valor que no exista
    Esta es la excepcion que se insita a crear en el documento de la practica
    """
    def __init__(self, message=""):
        super().__init__("Dato Inexistente", "el programa no tiene ese dato " + message + ", ingrese nuevamente el valor para el dato")        
        
        
        
class FaltanDatos(MalUso):
    """
    @description: Excepcion hija de MalUso, esta pensada para hacer raise de esta cuando
    al presionar el boton aceptar de una funcionalidad y no se tengan todos los datos
    Esta es la excepcion que se insita a crear en el documento de la practica
    """
    def __init__(self, message=""):
        super().__init__("Faltan datos" ,"faltan datos, por favor ingresarlos " + message + ", ingrese nuevamente el valor para el dato")
        
        
        
        
class NoDeberiasHacerEso(MalUso):
    """
    @description: Excepcion hija de MalUso, el programa deja hacer cosas que no son convenientes
    por lo que cuando se detecte tal comportamiento esta excepcion es ideal
    """
    def __init__(self, message=""):
        super().__init__("No deberias hacer eso", "no deberias hacer eso " + message)
        
 
        

class Irrespetuso(MalUso):
    """
    @description: Excepcion hija de MalUso, se debe utilizar para cuando el usuario haga algo
    que sea considerado como 'irrespetuoso' para el sistema
    """
    def __init__(self, message=""):
        super().__init__("Grosero","estas siendo irrespetuoso " + message)

        
# PARA PROBAR EXCEPCIONES
# def f(a):
#     if a == 0:
#         raise ErrorAplicacion()
#     elif a == 1:
#         raise UsuarioRacista()
#     elif a == 2:
#         raise MalUso()
#     elif a == 3:
#         raise DatoInexistente()

# try:
#     f(3)
# except DatoInexistente:
#     pass
# print("xd")