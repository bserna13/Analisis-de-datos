import pickle

class Actualizar:
    
    def actualizar(hospital):
        archivo = open("./baseDatos/tmp/objetos.pkl", "wb")    
        try:
            pickle.dump(hospital, archivo)
        except FileNotFoundError:
            print("Error")
        archivo.close()