package tpp2;


public class Flete extends Transporte {

	private String tipoTransporte;
	protected double cantidadDeAcompaniantes;
	protected double costoPorPasajeros;

	public Flete(String numeroId, double cargaM, double volumenM, double costoPorKm, double cantidadAcompaniante,
			double costoPasajero) {
		super(numeroId, cargaM, volumenM, costoPorKm);
		if (cantidadAcompaniante >= 0) {
			this.cantidadDeAcompaniantes = cantidadAcompaniante;
		} else {
			throw new RuntimeException("La cantidad de acompaniantes no puede ser negativa");
		}
		if (costoPasajero >= 0) {
			this.costoPorPasajeros = costoPasajero;
		} else {
			throw new RuntimeException("El valor por pasajero no puede ser negativo");
		}
		refrigeracion = false;
		this.tipoTransporte = "Flete";
	}

	public double getCostoPorPasajeros() {
		return costoPorPasajeros;
	}

	public String getTipoTransporte() {
		return tipoTransporte;
	}

	public double getCantidadDeAcompaniantes() {
		return cantidadDeAcompaniantes;
	}
	
	public void cancelarCostosAdicionales() {
		cantidadDeAcompaniantes = 0;
		costoPorPasajeros = 0;
	}

	
	@Override
	public void asignarDestino(String destino, double distancia) {
		super.asignarDestino(destino, distancia);
	}

	@Override
	public double costoDeViajeTotal() {
		// Calcula costo de viaje y dependiendo que transporte y se lo suma al costo
		// total
		return super.costoDeViajeTotal() + cantidadDeAcompaniantes * costoPorPasajeros;
	}

	@Override
	public boolean equals(Object obj) {
		// Pregunto si son del mismo tipo.
		// Si this apunta a la misma direccion en memoria que el otro significa que ya
		// son iguales.
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder acum = new StringBuilder();
		acum.append(super.toString());
		acum.append("Tipo de transporte: ");
		acum.append(getTipoTransporte());
		acum.append(", ");
		acum.append("Cantidad de acompaniantes: ");
		acum.append(getCantidadDeAcompaniantes());
		acum.append(", ");
		acum.append("Costo por cada acompaniante: ");
		acum.append(getCostoPorPasajeros());
//		for (int i = 0; i < getPaquete().size(); i++) {
//			acum.append(i);
//			acum.append(" = ");
//			acum.append(getPaquete().get(i).toString());
//			acum.append(", ");
//		}
		acum.append(" ]");
		return acum.toString();
	}

}