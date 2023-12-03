from enum import Enum

class Jerarquia(Enum):
    INTERNO=("Interno"),
    PLANTA=("Medico de planta"),
    DIRECTORMEDICO=("Director medico")
    JEFE=("Jefe")

    _titulo=""

    def __init__(self,titulo):
        super().__init__()
        self._titulo=titulo

