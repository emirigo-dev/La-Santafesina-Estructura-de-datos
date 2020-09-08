package tpp2;


public class Mega extends Trailer {

	private String tipoTransporte;
	protected double costoAdicion;
	protected double comidaChoffer;

	public Mega(String numeroId, double cargaM, double volumenM, Boolean frigorifico, double costoKm,
			double seguroCarga, double costoFijo, double comida) {
		super(numeroId, cargaM, volumenM, costoKm, seguroCarga, frigorifico);
		if (costoFijo >= 0) {
			this.costoAdicion = costoFijo;
		} else {
			throw new RuntimeException("El costo fijo no puede ser negativo");
		}
		if (comida >= 0) {
			this.comidaChoffer = comida;
		} else {
			throw new RuntimeException("La comida no puede tener un costo negativo");
		}
		this.tipoTransporte = "Trailer Mega";
	}

	public String getTipoTransporte() {
		return tipoTransporte;
	}

	public double getCostoAdicion() {
		return costoAdicion;
	}

	public double getComidaChoffer() {
		return comidaChoffer;
	}


	@Override
	public void cancelarCostosAdicionales() {
		super.cancelarCostosAdicionales();
		comidaChoffer = 0;
		costoAdicion = 0;
	}


	@Override
	public void asignarDestino(String destino, double distancia) {
		if (distancia > 500) {
			super.asignarDestino(destino, distancia);
		} else {
			throw new RuntimeException("Este transporte recorre a partir de 500km para arriba");
		}
	}

	@Override
	public double costoDeViajeTotal() {
		return super.costoDeViajeTotal() + costoAdicion + comidaChoffer;

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
		acum.append("Costo adicion: ");
		acum.append(getCostoAdicion());
		acum.append(", ");
		acum.append("Costo de comida del choffer: ");
		acum.append(getComidaChoffer());
		acum.append(" ]");
		return acum.toString();
	}

}
