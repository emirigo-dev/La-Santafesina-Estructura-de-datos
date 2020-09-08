package tpp2;

public class Paquete {

	private double peso;
	private double volumen;
	private String destino;
	private Boolean refrigeracion;

	public Paquete(String destino, double peso, double volumen, Boolean frio) {
		if (volumen > 0) {
			this.volumen = volumen;
		} else {
			throw new RuntimeException("El volumen del paquete debe ser > a 0");
		}
		if (peso > 0) {
			this.peso = peso;
		} else {
			throw new RuntimeException("El peso del paquete debe ser > a 0");
		}
		if (destino != "") {
			this.destino = destino;
		} else {
			throw new RuntimeException("El destino no puede ser vacio");

		}
		if (frio != null) {
			this.refrigeracion = frio;
		} else {
			throw new RuntimeException("El frio debe ser true o falso");
		}
	}

	public double getPeso() {
		return peso;
	}

	public double getVolumen() {
		return volumen;
	}

	public String getDestino() {
		return destino;
	}

	public Boolean getRefrigeracion() {
		return refrigeracion;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Paquete other = (Paquete) obj;
		if (destino == null && other.getDestino() != null) {
			return false;
		} else if (!destino.equals(other.getDestino()))
			return false;
		if (peso != other.getPeso())
			return false;
		if (refrigeracion == null && other.getRefrigeracion() != null) {
			return false;
		} else if (!refrigeracion.equals(other.getRefrigeracion()))
			return false;
		if (volumen != other.getVolumen())
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder acum = new StringBuilder("( ");
		acum.append("Peso: ");
		acum.append(getPeso());
		acum.append(", ");
		acum.append("¿Necesita refrigeracion?: ");
		acum.append(getRefrigeracion());
		acum.append(", ");
		acum.append("Volumen: ");
		acum.append(getVolumen());
		acum.append(", ");
		acum.append("Destino: ");
		acum.append(getDestino());
		acum.append(" )");
		return acum.toString();
	}

	public Paquete consultarPaquete(Paquete paquete) {
		return paquete;
	}

}