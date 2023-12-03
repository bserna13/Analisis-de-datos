

from random import Random, random
from tkinter import messagebox, simpledialog
from gestorAplicacion.Doctor import Doctor

from gestorAplicacion.Hospital import Hospital
from gestorAplicacion.Paciente import Paciente
from gestorAplicacion.Quirofano import Quirofano
from gestorAplicacion.SalaDeEspera import SalaDeEspera
from gestorAplicacion.Sintomas import Sintomas
from gestorAplicacion.Jerarquia import Jerarquia


class Prom:

    def SubirDoctor(hospital):
        #hospital = Hospital(SalaDeEspera([Paciente("Juanito", 1, False, [Sintomas.DOLORPECHO], 0), Paciente("Maria", 2, True, [Sintomas.DOLORPECHO], 1), Paciente("Pepito", 3, True, [], 2)]),
         #[Quirofano(Paciente("Maria", 3, True, [], 2),Doctor("Brahian", 10)),Quirofano(Paciente("Mario", 4, False, [Sintomas.DOLORPECHO], 3),Doctor("Fernando", 11)),None],
         #[Doctor("Francisco", 15),Doctor("Rodolfo", 20)])

        messagebox.showinfo(message="Antes de que el mundo apareciera tal y como lo conocemos, se creó este hospital donde ciertos doctores tenian una jeraquia establecida\nA continuacion ayudaras a un doctor para que suba de rango")

        messagebox.showinfo(message="El orden es el siguiente:")

        messagebox.showinfo(message="1)JEFE\n\n2)DIRECTOR MEDICO\n\n3)DOCTOR DE PLANTA\n\n4)DOCTOR INTERNO")

        messagebox.showinfo(message="En el hospital hay doctores ocupados que están en los quirofanos y otros que estan disponibles\n" +"seleccione a cuales quieres ayudar: ")


        caso = simpledialog.askstring(title="Selecciona una opcion",prompt="[1] Doctores que estan en los quirofanos\n[2] Doctores que estan disponibles en el hospital\n")
        caso=int(caso)

        #CODICIONAL PARA EL USUARIO SI ESCOGE LOS MEDICOS QUE ESTAN EN LOS QUIROFANOS
        if caso==1:
            messagebox.showinfo(message="Haz seleccionado a los doctores que están trabajando en los quirofanos\n" +"Estos son los doctores:")

            mostrarDoctoresQuirofanos= Prom.DoctoresEnQuirofanos(hospital) #En esta variable guardamos a los Doctores que estan en los quirofanos, posteriormente se muestran
            array = hospital.getDoctorQuirofano() #DOCTORES QUE ESTAN TRABAJANDO EN LOS QUIROFANOS

            messagebox.showinfo(message="Seleccione el doctor/a que quieras ayudar:")

            indice = simpledialog.askstring(title="Selecciona",prompt=mostrarDoctoresQuirofanos+"\nDoctor numero:")
            indice =int(indice)

            if array[indice-1] is None:
                messagebox.showinfo(message="Este doctor ya no hace parte del hospital")
                return

            messagebox.showinfo(message="\nHaz seleccionado al doctor/a "+array[indice-1].getNombre()+" y el cargo que ocupa es de "+str(array[indice-1].getJerarquia()))

            messagebox.showinfo(message="Si pierdes el juego el/la doctor/a se mantiene en el cargo y si ganas el/la doctor/a sube de rango (en caso de ser jefe se mantiene), recuerda la jerarquia\nEl siguiente juego se llama ahorcadito")
         
            resultado = Prom.juego()
            #CODICIONAL POR SI GANA O PIERDE EL JUEGO
            if resultado == True:
                
                #SI ES JEFE SE MANTIENE COMO JEFE
                if array[indice-1].getJerarquia()==Jerarquia.JEFE:
                    return "\nEl/la doctor/a "+array[indice-1].getNombre()+ " ya es JEFE y no va a subir más de rango"
    
                    return

                if array[indice-1].getJerarquia()==Jerarquia.INTERNO:
                    array[indice-1].setJerarquia(Jerarquia.PLANTA)
                elif array[indice-1].getJerarquia()==Jerarquia.PLANTA:
                    array[indice-1].setJerarquia(Jerarquia.DIRECTORMEDICO)
                elif array[indice-1].getJerarquia()==Jerarquia.DIRECTORMEDICO:
                    array[indice-1].setJerarquia(Jerarquia.JEFE)
                
                return "\nEl/la doctor/a "+array[indice-1].getNombre()+ " ahora ocupará el cargo de "+str(array[indice-1].getJerarquia())
               
            else:
                return "\n Haz perdido y el doctor/a "+str(array[indice-1].getNombre())+" se mantendra en su cargo"
                
        #CODICIONAL PARA EL USUARIO SI ESCOGE LOS MEDICOS QUE ESTAN EN EL HOSPITAL DISPONIBLES
        elif caso==2:
            messagebox.showinfo(message="Haz seleccionado a los doctores que están disponibles en el hospital\n" +"Estos son los doctores:")

            textoDoctoresHospital=Prom.printDoctores(hospital) #ESTE VARIABLE GUARDA UN TEXTO DONDE SE VEN LOS DOCTORES QUE ESTAN DISPONIBLES (NO EN QUIROFANOS) EN EL HOSPITAL
            array = hospital.getDoctores() #DOCTORES QUE ESTAN DISPONIBLES EN EL HOSPITAL

            messagebox.showinfo(message="Seleccione el doctor/a que quieras ayudar:")

            indice = simpledialog.askstring(title="Selecciona",prompt=textoDoctoresHospital+"\nDoctor numero:")
            indice =int(indice)

            if array[indice-1] is None:
                messagebox.showinfo(message="Este doctor ya no hace parte del hospital")
                return

            messagebox.showinfo(message="\nHaz seleccionado al doctor/a "+array[indice-1].getNombre()+" y el cargo que ocupa es de "+str(array[indice-1].getJerarquia()))

            messagebox.showinfo(message="Si pierdes el juego el/la doctor/a se mantiene en el cargo y si ganas el/la doctor/a sube de rango (en caso de ser jefe se mantiene), recuerda la jerarquia\nEl siguiente juego se llama ahorcadito")
         
            resultado = Prom.juego()
            #CODICIONAL POR SI GANA O PIERDE EL JUEGO
            if resultado == True:
                
                #SI ES JEFE SE MANTIENE COMO JEFE
                if array[indice-1].getJerarquia()==Jerarquia.JEFE:
                    messagebox.showinfo(message="\nEl/la doctor/a "+array[indice-1].getNombre()+ " ya es JEFE y no va a subir más de rango")
    
                    return

                if array[indice-1].getJerarquia()==Jerarquia.INTERNO:
                    array[indice-1].setJerarquia(Jerarquia.PLANTA)
                elif array[indice-1].getJerarquia()==Jerarquia.PLANTA:
                    array[indice-1].setJerarquia(Jerarquia.DIRECTORMEDICO)
                elif array[indice-1].getJerarquia()==Jerarquia.DIRECTORMEDICO:
                    array[indice-1].setJerarquia(Jerarquia.JEFE)
                
                return "\nEl/la doctor/a "+array[indice-1].getNombre()+ " ahora ocupará el cargo de "+str(array[indice-1].getJerarquia())
               
            else:
                return "\n Haz perdido y el doctor/a "+str(array[indice-1].getNombre())+" se mantendra en su cargo"


    @staticmethod
    def DoctoresEnQuirofanos(hospital):
        quirofanos = hospital.getQuirofanos()
        contador =0

        #En textoDoctoresQuirofanos guarda un string donde van a estar los los doctores en sus correspondientes quirofanos
        textoDoctoresQuirofanos=""
        i = 0
        while i < len(quirofanos):

            if quirofanos[i] is not None and quirofanos[i].getDoctor() is not None :
                contador += 1
                textoDoctoresQuirofanos+="["+str(contador)+"] "+"Doctor/a "+quirofanos[i].getDoctor().getNombre() + " \t"


            if contador%10== 0 or len(quirofanos)==i+1:
                textoDoctoresQuirofanos+="\n"
            i += 1

        return textoDoctoresQuirofanos


    #    
    #    En este metodo prinDoctores es un metodos donde recibe a hospital de tipo Hospital como parametro
    #    y nos retora un string de los doctores que estan disponibles en el hospital, es decir los que no estan en los
    #    quirofanos trabajando
    #    
    @staticmethod
    def printDoctores(hospital):
        doctores = hospital.getDoctores()
        texto=""

        i = 0
        while i < len(doctores):

            if doctores[i] is not None:
                texto+="["+str(i+1)+"] Doctor/a "+doctores[i].getNombre()+ " \t"
            else:
                texto+="["+str(i+1)+"] Doctor/a No Registrado \t"

            if (i+1)%7 == 0 or len(doctores)==i+1:
                texto+="\n"
            i += 1
        return texto


    
    @staticmethod
    def juego():
        INTENTOS_TOTALES = 7 # Constante con el limite de fallos
        intentos = 0
        aciertos = 0

        #teclado = input()
        #teclado.useDelimiter("\n")
        resp = None
        # Random para pillar una palabra al azar
        rnd = Random()
        # Creamos unas palabras y le asignamos una aleatoria a una varibale
        arrayPalabras = [None for _ in range(3)]
        arrayPalabras[0] = "poo"
        arrayPalabras[1] = "hola"
        arrayPalabras[2] = "clinical"



        # Desguazamos la palabra y la guardamos en un array de caracteres
        alea = rnd.randint(0,2)
        desguazada = Prom.desguaza(arrayPalabras[alea])
        copia = Prom.desguaza(arrayPalabras[alea]) # Algo auxiliar para mas tarde
        # Array para pintar mierdecillas en pantalla(Guiones o letras vamos)
        tusRespuestas = ['\0' for _ in range(len(desguazada))]

        # Rellenamos palabras ocn guiones
        i = 0
        while i < len(tusRespuestas):
            tusRespuestas[i] = '_'
            i += 1

        # Empezamos a pintar mierdas en pantalla
        messagebox.showinfo(message="Adivina la palabra!")

        # Mientras que no nos pasemos con los intentos y no la acertemos...
        while intentos < INTENTOS_TOTALES and aciertos != len(tusRespuestas):
            Prom.imprimeOculta(tusRespuestas)
            # Preguntamos mierdas por teclado
            resp = simpledialog.askstring(title="Selecciona",prompt="\nIntroduce una letra: ")
            resp = resp[0]

            # Recorremos el array y comprobamos si se ha producido un acierto
            i = 0
            while i < len(desguazada):
                if desguazada[i]==resp:
                    tusRespuestas[i] = desguazada[i]
                    desguazada[i] = ' '
                    aciertos += 1
                i += 1
            intentos += 1
        # Si hemos acertado todas imprimimos un mensahe
        if aciertos == len(tusRespuestas):
            messagebox.showinfo(message="\nFelicidades!! has acertado la palabra")
            #Prom.imprimeOculta(tusRespuestas)
            return True
        # Si no otro

        messagebox.showinfo(message="\nHas fallado en este intento!", end = '')
        i = 0
        while i < len(copia):
            print(copia[i] + " ", end = '')
            i += 1
        return False

        #JUEGO DEL AHORCADITO LOS SIGUIENTES 3 METODOS
    @staticmethod
    def desguaza(palAzar):
        letras = []
        letras = ['\0' for _ in range(len(palAzar))]
        i = 0
        while i < len(palAzar):
            letras[i] = palAzar[i]
            i += 1
        return letras
    #    *
    #     * Esto imprime la palabra con espacios
    #     * @param tusRespuestas el array de caracteres
    #     
    @staticmethod
    def imprimeOculta(tusRespuestas):
        texto=""
        i = 0
        while i < len(tusRespuestas):
            texto+=tusRespuestas[i] + " "
            i += 1
        messagebox.showinfo(message=texto)
        




