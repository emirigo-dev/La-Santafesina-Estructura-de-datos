package tpp2;

public class Viaje {

	private double distancia;

	private String destino;

	public Viaje(String destino, double distancia) {
		if (distancia > 0) {
			this.distancia = distancia;
		} else {
			throw new RuntimeException("La distancia no puede ser 0 ni negativa");
		}
		if (destino !="") {
			this.destino = destino;
		} else {
			throw new RuntimeException("El destino no puede ser vacio");
		}
	}

	public double getDistancia() {
		return distancia;
	}

	public String getDestino() {
		return destino;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Viaje other = (Viaje) obj;
		if (destino == null) {
			if (other.destino != null)
				return false;
		} else if (!destino.equals(other.destino))
			return false;
		if (distancia != other.distancia)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder acum = new StringBuilder("( ");
		acum.append("Destino: ");
		acum.append(getDestino());
		acum.append(", ");
		acum.append("Distancia: ");
		acum.append(getDistancia());
		acum.append(" )");
		return acum.toString();
	}
}