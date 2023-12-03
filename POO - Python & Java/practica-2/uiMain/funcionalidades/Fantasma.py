import math
from re import X
from tkinter import messagebox, simpledialog


from gestorAplicacion.Hospital import Hospital
from gestorAplicacion.SalaDeEspera import SalaDeEspera
from gestorAplicacion.Paciente import Paciente
from gestorAplicacion.Doctor import Doctor
from gestorAplicacion.Quirofano import Quirofano
from gestorAplicacion.Sintomas import Sintomas

class Fantasma:

    def despertar_fantasmon(hospital):
        #hospital = Hospital(SalaDeEspera([Paciente("Juanito", 1, False, [Sintomas.DOLORPECHO], 0), Paciente("Maria", 2, True, [Sintomas.DOLORPECHO], 1), Paciente("Pepito", 3, True, [], 2)]),
        #[Quirofano(Paciente("Maria", 3, True, [], 2),Doctor("Brahian", 10)),Quirofano(Paciente("Mario", 4, False, [Sintomas.DOLORPECHO], 3),Doctor("Fernando", 11)),None],
        #[])

        messagebox.showinfo(message="Alguien ha despertado a fantasmon, un paciente que murio en el siglo XVIII que busca venganza")
    
        messagebox.showinfo(message="Eres muy valiente por permanecer aun aquí, selecciona a donde va a ir fantasmon pero cuidado, esto puede traer consecuencia negativas")

        x = simpledialog.askstring(title="Selecciona a donde va fantasmon",prompt="[1] SALA DE ESPERA\n [2] QUIROFANOS")
        x=int(x)

        #AQUI EN nombres_pacientes_fallecidos GUARDAREMOS LOS NOMBRES QUE AL FINAL MOSTRAREMOS COMO RESULTADO DE LA FUNCIONALIDAD
        nombres_pacientes_fallecidos=[]# EL CUAL SON LOS PACIENTES QUE FALLECIERON :(


        #    
        #    Como se puede observar aqui hay un condicional, basicamente si el usuario selecciona 1, el fantasma entrará a la sala de espera
        #    sala de espera es un atributo referenciado de hospital, y en sala de espera tambien tiene un atributo de tipo pacientes.
        #    se espera que cuando se entre a esta condicion "x==1" busquemos en la sala de espera pacientes que tengan problemas cardiacos(DOLORPECHO)
        #    el cual es una constante se uso para esto los enumerados "Sintomas", si el paciente posee este sintoma probablemente muera es decir
        #    su instancia deje de existir
        #   
        if x==1:

            messagebox.showinfo(message="Fantasmon entrando a la sala de espera...")
            pacientes = hospital.getSalaDeEspera().getPacientes()# recolectamos los pacientes que están en la sala de espera, esto es una lista con las intancias

            contador =-1 #este contador es como el indice para el paciente en especifico y comienza en -1 para que cuando entre al ciclo
                         #comience a contar desde 0 y si el paciente muere se hace pacientes[contador]=None y se guarda

            pp =0       #Esta pp cuenta cuantas personas poseen problemas cardiacos y sirve mas adelante porque si alguien poseer problemas se dice
                        #"Julanito posee problemas cardiacos" y si pp>1 y Pepito tambien posee p.c. entonces se dice "Pepito TAMBIEN posee p.c."

            for paci in pacientes:#paci va a ser cada paciente de la sala de espera
                contador += 1#Comienza a contar
                if paci is None:#Si Paci no tiene referencia se sigue continua con otro paciente pero si la tiene:
                    continue
                
                #Buscamos los sintomas que tiene el paciente y los obtenemos en esta lista
                sintomasPaciente = paci.getSintomas()

                if sintomasPaciente==None: #Si el paciente no tiene sintomas se contina con otro
                    continue

                #BUCLE QUE RECORRE LOS SINTOMAS DE DETERMINADO PACIENTE Y SI TIENE DOLORPECHO ENTRA AL CONDICIONAL
                for sintoma in sintomasPaciente:
                    if sintoma is Sintomas.DOLORPECHO:
                        pp += 1 #Aqui entra en juego la pp como se explico anteriormente
                        if pp>1:
                            messagebox.showinfo(message="\nOh no! el paciente "+paci.getNombre()+" tambien posee problemas cardiacos y está muy asustado al ver el fantasma, ¿Se salvará?\n")
                        else:
                            messagebox.showinfo(message="\nOh no! el paciente "+paci.getNombre()+" posee problemas cardiacos y está muy asustado al ver el fantasma, ¿Se salvará?\n")


                    #AQUI SE DECIDE SI EL PACIENTE VIVE O FALLECE DEPENDIENDO DEL USUARIO SI EJECUTA A FAVOR O ENCONTRA
                        resultado = Fantasma.decision(paci) #Ejecutamos este metodo que se encuentra en la parte inferior de esta misma clase

                        if resultado: #Si resultado es true se continua con los demas pacientes y este mismo no pasa nada
                            continue
                        else:
                            nombres_pacientes_fallecidos.append(paci.getNombre()) #Si resultado es false entro acá y se guarda su nombre
                            pacientes[contador]=None                                #antes de que se pierda la referencia en esta misma linea
                            continue

            if len(nombres_pacientes_fallecidos)==0: #numeros de personas con problemas cardiacos "DOLORPECHO" que fallecieron, es decir que si es 0 a ninguno le pasa nada
                return "Fantasmon entró, todo el mundo lo vio y se asusto pero afortunadamente no paso nada"
            else:

                #Pero si entro aca es porque si hubieron perdidas las mostramos como resultado final de proceso
                texto="Lamentamos la perdida de:\n"
                for e in nombres_pacientes_fallecidos:
                    texto+=str(e)+"\n"
                texto+=":("
                return texto
        #HASTA AQUI EL PRIMER CONDICIONAL Y SE ACABA LA FUNCION, PEROOO SI ENTRA AL OTRO CONDICIONAL SE EJECUTA LO SIGUIENTE



        elif x==2:
            #AQUÍ ENTRO A LOS QUIROFANOS Y LA DINAMICA ES LA MISMA, quirofanos es un atributo de hospital y tiene tambien 2 atributos,1 de tipo doctor
            #y otro de tipo paciente por tanto aqui es donde nos damos cuenta que pasa con ese paciente

            messagebox.showinfo(message="Fantasmon entrará solo a 3 quirofanos segun el numero, seleccione cuales\n")
   
            mostrar_quirofanos=Fantasma.printQuirofanos(hospital) #AQUI SE GUARDA UN STRING PARA MOSTRAR LOS QUIROFANOS, esta funcion se encuentra en la parte de abajo de esta misma clase

            quirofanos = hospital.getQuirofanos() #lista de los quirofanos el cual los obtenemos con un get de hospital ya que es su atributo

            #PUEDEN HABER MUCHOS QUIROFANOS POR TANTO PODEMOS ENTRAR A 3 PARA MAS PLACER :V
            for i in range(0, 3):
                messagebox.showinfo(message="Intento #"+str(i+1))

                num = simpledialog.askstring(title="Selecciona a donde va fantasmon",prompt=mostrar_quirofanos+"\nQuirofano numero:")
                num=int(num) #el numero del quirofano

                messagebox.showinfo(message="Haz seleccionado el quirofano numero "+str(num))
                messagebox.showinfo(message="Fantasmon entrando...")
    
                #QUIROFANO SELECCIONADO por el usuario
                quirofano = quirofanos[num-1]

                #HAY DOS OPCIONES, O EL QUIROFANO ESTA VACIDO (None) O HAY ALQUIEN ADENTRO (else)
                if quirofano is None:
                    messagebox.showinfo(message="En este quirofano no hay nadie\n")
                    continue

                else:
                    paci = quirofano.getPaciente() #tomamos al paciente y buscamos sus sintomas

                    if paci==None:
                        messagebox.showinfo(message="En este quirofano no hay nadie\n")
                        continue
                    
                    sintomasPaciente = paci.getSintomas()#lista de sintomas y empiece de nuevo, here we go again

                    contador2 =0 #cuenta la gente que tiene DOLOPECHO

                    #BUCLE QUE RECORRE LOS SINTOMAS DEL PACIENTE REVISANDO SI TIENE DOLOR DE PECHO, Note que este ciclo es practicamente el mismo 
                    #ciclo del anterior donde buscamos si tiene el DOLOPECHO y si sí esto se va a una toma de decicion y bla bla bla lo mismo
                    for sintoma in sintomasPaciente:

                        if sintoma is Sintomas.DOLORPECHO:
                            contador2 += 1
                        
                            messagebox.showinfo(message="Oh no! aquí se encuentra el paciente "+paci.getNombre()+" y posee problemas cardiacos, está muy asustado al ver el fantasma, ¿Se salvará?\n")
                        
                            resultado = Fantasma.decision(paci)

                            if resultado:
                                break
                            else:
                                quirofanos[num-1]=None
                                nombres_pacientes_fallecidos.append(paci.getNombre())
                                break

                    if contador2==0:#SI NADIE TIENE DOLO PECHO ENTONCES NADIE MUERE :)
                        messagebox.showinfo(message="\nEn este quirofano está "+paci.getNombre()+", y apesar de ver a fantasmon fue muy fuerte no le pasó absolutamente nada\n")
                        

            #LO MISMO QUE LO ANTERIO PERO PARA ESTE CONDICIONAL DONDE SE ENTRO A LOS QUIROFANOS, se muestra por resultado en pantalla si hubo fallecidos
            #o no 
            if len(nombres_pacientes_fallecidos)==0:
                return "Fantasmon entró a los quirofanos, todo el mundo lo vio y se asusto pero afortunadamente no paso nada"
            else:
                texto="Lamentamos la perdida de:\n"
                for e in nombres_pacientes_fallecidos:
                    texto+=str(e)+"\n"
                texto+=":("
                return texto




    #Para este metodo se pide a una instancia paciente como parametro en cuestion para preguntar por su nombre y asi
    #En este metodo se decide si un paciente muere o vive, por tal razon retorna un resutado de tipo Boolean
    @staticmethod
    def decision(paci):

        #Aqui se pide una cantidad de n´s para tomar una decicion
        x = simpledialog.askstring(title="Desicion paciente",prompt="Presione un numero par de [n]'s si no quieres que el paciente muera")
        numero = len(x)

        #Si entra a este condicional viveee
        if numero%2==0:
            messagebox.showinfo(message="El paciente "+paci.getNombre() +" se ha salvado gracias a ti :))))\n")
            return True

        #Si entra a este concional fallece :(
        else:
            messagebox.showinfo(message="El paciente "+paci.getNombre() +" acaba de fallecer debido a complicaciones cardiacas :( \n")
            return False



#   En este metodo printQuirofanos recibe como parametro un hospital y retorna un string donde estan organizados los quirofanos   
#   el cual le permite al usuario ver de forma ordenada los quirofanos del hospital
    @staticmethod
    def printQuirofanos(hospital):
        quirofanos = hospital.getQuirofanos()

        #En este texto organizamos los quirofanos que se encuentras en hospital
        texto_quirfanos=""
        i = 0
        while i < len(quirofanos):

            texto_quirfanos+="Quirofano["+str(i+1)+"]" + " \t"

            if (i+1)% 10 == 0 or len(quirofanos)==i+1:
                texto_quirfanos+="\n"
            i += 1

        return texto_quirfanos
    #System.out.println("\n")

