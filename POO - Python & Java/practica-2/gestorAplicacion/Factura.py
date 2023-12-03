class Factura:

    #	CLASS VARIABLES	
    _SERIALVERSIONUID = 1
    _NIT = 12
    _DIRECCION = "Calle 1 #23"
    _TELEFONO = "#444 4444 (444)"

    def __init__(self, valorPagar, descuento=0):
        self._valorPagar = (1-descuento)*valorPagar

    #	INSTANCE METHODS	
    def devolucionDinero(self, factura):
        valorDevolver = factura.getValorPagar()
        factura.setValorPagar(0)
        return valorDevolver

    # GETTERS & SETTERS 
    def getNIT(self):
        return Factura._NIT

    def getDireccion(self):
        return Factura._DIRECCION

    def telefono(self):
        return Factura._TELEFONO

    def getValorPagar(self):
        return self._valorPagar
    def setValorPagar(self, valorPagar):
        self._valorPagar = valorPagar

