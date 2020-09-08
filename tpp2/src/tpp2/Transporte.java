package tpp2;

import java.util.*;
import java.util.Iterator;

public abstract class Transporte {

	private String numeroIdentificacion;
	private double cargaMaxima;
	private double volumenMaximo;
	protected double costoPorDistancia;
	protected String destino;
	protected double km;
	private double cargaActual;
	private double volumenActual;
	private Boolean viajando;
	protected Boolean refrigeracion;
	protected double costoTer;
	protected ArrayList<Paquete> paquete;
	
	public Transporte(String numeroId, double cargaM, double volumenM, double costoPorKm) {
		if (numeroId.length() > 4 && numeroId.length() < 16) {
			this.numeroIdentificacion = numeroId;
		} else {
			throw new RuntimeException("El numero de matricula es vacio");
		}
		if (cargaM > 0) {
			this.cargaMaxima = cargaM;
		} else {
			throw new RuntimeException("La carga maxima debe ser mayor a 0");
		}
		if (volumenM > 0) {
			this.volumenMaximo = volumenM;
		} else {
			throw new RuntimeException("El volumen maximo debe ser mayor a 0");
		}
		if (costoPorKm >= 0) {
			this.costoPorDistancia = costoPorKm;
		} else {
			throw new RuntimeException("El costo por km no puede ser negativo");
		}
		this.cargaActual = 0;
		this.volumenActual = 0;
		this.viajando = false;
		this.destino = "";
		this.km = 0;
		this.refrigeracion = null;
		this.paquete = new ArrayList<>();
		this.costoTer = 0;

	}

	public String getNumeroIdentificacion() {
		return numeroIdentificacion;
	}

	public double getCargaMaxima() {
		return cargaMaxima;
	}

	public double getVolumenMaximo() {
		return volumenMaximo;
	}

	public double getCostoPorDistancia() {
		return costoPorDistancia;
	}

	public double getCargaActual() {
		return cargaActual;
	}

	public double getVolumenActual() {
		return volumenActual;
	}

	public boolean getViajando() {
		return viajando;
	}

	public String getDestino() {
		return destino;
	}

	public double getKm() {
		return km;
	}

	public Boolean getRefrigeracion() {
		return refrigeracion;
	}

	public void iniciarViaje() {
		// Si la carga del transporte es 0 no va a iniciar ya que esta vacio
		if (cargaActual != 0) {
			viajando = true;
		} else {
			throw new RuntimeException("El transporte no puede iniciar un viaje si esta vacio");
		}
	}

	public void terminarViaje() {
		// Vacia todo el transporte y blanquea su destino
		// Se crea un iterador para el arraylist de paquetes, va a ir recorriendo y
		// eliminando todos los elementos.
		paquete.clear();
		km = 0;
		cargaActual = 0;
		volumenActual = 0;
		destino = "";
		viajando = false;
	}

	// Los dos metodos son abstractos ya que van a depender de cada transporte como
	// va a ser su carga y
	// su costo de viaje.

	public void costoTercierizado(double costo) {
		costoTer += costo;
	}

	public void asignarDestino(String destino, double distancia) {
		this.destino = destino;
		km = (distancia);
	}

	public double costoDeViajeTotal() {
		return costoTer + costoPorDistancia * km;
	}
	public boolean condicionesCargarTransporte(Paquete p) {
		if ((cargaActual + p.getPeso() < cargaMaxima) && (volumenActual + p.getVolumen() < volumenMaximo) && refrigeracion.equals(p.getRefrigeracion())) {
			return true;
		}
		return false;
	}

	public double cargarTransporte(Paquete p) {
		if (condicionesCargarTransporte(p)) {
			paquete.add(p);
			cargaActual += p.getPeso();
			volumenActual += p.getVolumen();
			return p.getPeso();
		}
		return 0;

	}

	public boolean comprobarCondicionesParaReemplazarTransporte(Transporte reemplazo) {
		if (reemplazo.getViajando() == false && reemplazo.getClass().equals(this.getClass())
				&& reemplazo.getCargaMaxima() >= this.getCargaMaxima()
				&& reemplazo.getVolumenMaximo() >= this.getVolumenMaximo() && reemplazo.refrigeracion.equals(this.refrigeracion)) {
			return true;
		}
		return false;
	}

	public void cambiarTransporte(Transporte reemplazo) {
		if (comprobarCondicionesParaReemplazarTransporte(reemplazo)) {
		reemplazo.asignarDestino(destino, km);
		reemplazo.costoTer = costoTer;
		reemplazo.costoPorDistancia = costoPorDistancia;
		Iterator<Paquete> iterador = paquete.iterator();
		this.costoTer = 0;
		while (iterador.hasNext()) {
			Paquete p = iterador.next();
			reemplazo.cargarTransporte(p);
			iterador.remove();
		}
		reemplazo.costoPorDistancia = costoPorDistancia;
		reemplazo.iniciarViaje();
		this.terminarViaje();
		this.cancelarCostosAdicionales();
		}
	}
	
	public abstract void cancelarCostosAdicionales();

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass())
			return false;
		if (obj instanceof Transporte) {
			Transporte other = (Transporte) obj;
			if (this.destino == null && other.getDestino() != null) {
				return false;

			} else if (!this.destino.equals(other.getDestino()))
				return false;

			if (this.paquete.size() != other.paquete.size()) {
				return false;
			}
			ArrayList<Paquete> listaPrimaria = new ArrayList<>();
			listaPrimaria.addAll(other.paquete);
			for (Paquete paquete : this.paquete) {
				listaPrimaria.remove(paquete);
			}
			if (listaPrimaria.size() != 0) {
				return false;
			}
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder acum = new StringBuilder("[ ");
		acum.append("Numero de identificacion: ");
		acum.append(getNumeroIdentificacion());
		acum.append(", ");
		acum.append("Carga Maxima: ");
		acum.append(getCargaMaxima());
		acum.append(", ");
		acum.append("Volumen Maximo: ");
		acum.append(getVolumenMaximo());
		acum.append(", ");
		acum.append("Costo por km: ");
		acum.append(getCostoPorDistancia());
		acum.append(", ");
		acum.append("Destino: ");
		acum.append(getDestino());
		acum.append(", ");
		acum.append("Distancia: ");
		acum.append(getKm());
		acum.append(", ");
		acum.append("Peso actual: ");
		acum.append(getCargaActual());
		acum.append(", ");
		acum.append("Volumen actual: ");
		acum.append(getVolumenActual());
		acum.append(", ");
		acum.append("En viaje: ");
		acum.append(getViajando());
		acum.append(", ");
		acum.append("\n");
		acum.append("Cantidad de paquetes: ");
		acum.append(paquete.size());
		acum.append(", ");
		return acum.toString();
	}

}