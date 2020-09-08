package tpp2;

public abstract class Trailer extends Transporte {

    protected double seguroDeCarga;
	
    public Trailer(String numeroId, double cargaM, double volumenM,double costoPorKm, double seguroCarga, Boolean refrigeracion) {
    	super(numeroId, cargaM, volumenM, costoPorKm);
    	this.refrigeracion = refrigeracion;
    	this.seguroDeCarga = seguroCarga;
    	
    }

	public double getSeguroDeCarga() {
		return seguroDeCarga;
	}
	
	public void cancelarSeguro() {
		seguroDeCarga = 0;
	}
	
	public void cancelarCostosAdicionales() {
		this.seguroDeCarga = 0;
	}

	@Override
	public void asignarDestino(String destino, double distancia) {
		this.destino = destino;
		km = (distancia);
	}
	
	@Override
	public double costoDeViajeTotal() {
		return super.costoDeViajeTotal()+seguroDeCarga;
		
	}
	@Override
	public String toString() {
		StringBuilder acum = new StringBuilder();
		acum.append(super.toString());
		acum.append("Seguro de carga: ");
		acum.append(seguroDeCarga);
		acum.append(", ");
//		for (int i = 0; i < getPaquete().size(); i++) {
//			acum.append(i);
//			acum.append(" = ");
//			acum.append(getPaquete().get(i).toString());
//			acum.append(", ");
//		}
		return acum.toString();
	}

}
