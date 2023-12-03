
#
# IMPORTS
#
from enum import Enum
from tkinter.messagebox import RETRY
from gestorAplicacion.Especialidad import Especialidad




#
# MAIN CLASS 
#

class Sintomas(Enum):

    """
    Sintomas es una enumeracion
    Donde cada elemento tiene relacion con una especialidad
    Por ejemplo: DOLORPECHO tiene como atributo especialidad a CARDIOLOGIA
    , donde CARDIOLOGIA es un elemento de la enumeracion Especialidad
    """
    GRIPA=(Especialidad.GENERAL)
    TOS=(Especialidad.GENERAL)
    FIEBRE=(Especialidad.GENERAL)
    MALESTAR=(Especialidad.GENERAL)
    DOLORPECHO=(Especialidad.CARDIOLOGIA)
    DEFORMACION=(Especialidad.PLASTICA)
    PIELRARA=(Especialidad.DERMATOLOGIA)
    DOLORESTOMAGO=(Especialidad.GENERAL)
    DEMENCIA=(Especialidad.NEUROCIRUJANO)

    _especialidad=Especialidad.GENERAL
    def __init__(self,especialidad):
        super().__init__()
        self._especialidad=especialidad

    def getEspecialidad(self):
        return self._especialidad.value
        







