package tpp2;


public class Comun extends Trailer {

	private String tipoTransporte;

	public Comun(String idTransp, double cargaMax, double capacidad, boolean frigorifico, double costoKm,
			double segCarga) {
		super(idTransp, cargaMax, capacidad, costoKm, segCarga, frigorifico);
		this.tipoTransporte = "Trailer Comun";
	}

	public String getTipoTransporte() {
		return tipoTransporte;
	}



	@Override
	public void asignarDestino(String destino, double distancia) {
		if (distancia < 500) {
			super.asignarDestino(destino, distancia);
		} else {
			throw new RuntimeException("Este transporte recorre de 500km para arriba");
		}
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
		acum.append(" ]");
		return acum.toString();
	}
}