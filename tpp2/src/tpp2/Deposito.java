package tpp2;

import java.util.*;

public class Deposito {

	private Boolean propio;
	private double capacidadMax;
	private Boolean refrigeracion;
	private double mercaderiaTotal;
	private HashMap<String, ArrayList<Paquete>> paquete;
	private int numeroDeposito;
	private double costoTonelada;
	static int contador = 0;

	// Sobrecargo el constructor un constructor va a ser para un deposito propio y
	// el otro para los tercierizados
	public Deposito(Boolean propio, double capacidadM, Boolean refrigeracion) {
		if (!propio.equals(null)) {
			this.propio = propio;
		} else {
			throw new RuntimeException("No puede ser nulo");
		}
		if (capacidadM > 0) {
			this.capacidadMax = capacidadM;
		} else {
			throw new RuntimeException("La capacidad maxima tiene que ser mayor a 0");
		}
		if (!refrigeracion.equals(null)) {
			this.refrigeracion = refrigeracion;
		} else {
			throw new RuntimeException("La refriracion no puede ser nula");
		}
		this.paquete = new HashMap<>();
		this.mercaderiaTotal = 0;
		this.costoTonelada = 0;
		this.numeroDeposito = contador++;
	}

	public Deposito(double capacidad, double costoTonelada) {
		this.propio = false;
		if (capacidad > 0) {
			this.capacidadMax = capacidad;
		} else {
			throw new RuntimeException("La capacidad tiene que ser mayor a 0");
		}
		this.refrigeracion = true;
		if (costoTonelada >= 0) {
			this.costoTonelada = costoTonelada;
		} else {
			throw new RuntimeException("El costo de tonelada no puede ser vacio");
		}
		this.mercaderiaTotal = 0;
		this.paquete = new HashMap<>();
		this.numeroDeposito = contador++;

	}

	public int getNumeroDeposito() {
		return numeroDeposito;
	}

	public Boolean getPropio() {
		return propio;
	}

	public double getCapacidadMax() {
		return capacidadMax;
	}

	public Boolean getRefrigeracion() {
		return refrigeracion;
	}

	public double getMercaderiaTotal() {
		return mercaderiaTotal;
	}

	public double getCostoTonelada() {
		return costoTonelada;
	}

	public String consultarDeposito() {
		return toString();
	}

	public boolean agregarPaqueteDeposito(Paquete paqueteNuevo) {
		if (refrigeracion.equals(paqueteNuevo.getRefrigeracion())) {
			if (mercaderiaTotal + paqueteNuevo.getVolumen() < capacidadMax) {
				if (paquete.containsKey(paqueteNuevo.getDestino())) {
					ArrayList<Paquete> p = paquete.get(paqueteNuevo.getDestino());
					p.add(paqueteNuevo);
					mercaderiaTotal = mercaderiaTotal + paqueteNuevo.getVolumen();
					return true;
				}
				paquete.put(paqueteNuevo.getDestino(), new ArrayList<Paquete>());
				paquete.get(paqueteNuevo.getDestino()).add(paqueteNuevo);
				return true;
			}
		}
		return false;
	}


	public void cargarTransporte(Transporte trans) {
		if (paquete.containsKey(trans.getDestino())) {
			Iterator<Paquete> iterador = paquete.get(trans.getDestino()).iterator();
			double carga = 0;
			while (iterador.hasNext()) {
				Paquete p = iterador.next();
				if (trans.condicionesCargarTransporte(p)) {
					double totalCargado = trans.cargarTransporte(p);
					if (totalCargado > 0) {
						carga = carga + totalCargado;
						iterador.remove();
					}
				}
			}
			if (carga > 1000 && propio.equals(false) && refrigeracion.equals(true)) {
				trans.costoTercierizado(Math.floor(carga / 1000) * costoTonelada);
			}

		}
	}

	public int contarPaquetes() {
		int cantidad = 0;
		for (Map.Entry<String, ArrayList<Paquete>> entry : paquete.entrySet()) {
			cantidad += entry.getValue().size();
		}
		return cantidad;
	}

	@Override
	public String toString() {
		StringBuilder acum = new StringBuilder("[ ");
		acum.append("Numero del deposito: ");
		acum.append(getNumeroDeposito());
		acum.append(", ");
		acum.append("Mercaderia total: ");
		acum.append(getMercaderiaTotal());
		acum.append(", ");
		acum.append("¿Tiene refrigeracion?: ");
		acum.append(getRefrigeracion());
		acum.append(", ");
		acum.append("Capacidad maxima: ");
		acum.append(getCapacidadMax());
		acum.append(", ");
		acum.append("¿Es propio?: ");
		acum.append(getPropio());
		acum.append(", ");
		acum.append("Cantidad de paquetes: ");
		int cantPaquetes = 0;
		for (Map.Entry<String, ArrayList<Paquete>> entry : paquete.entrySet()) {
			cantPaquetes += entry.getValue().size();
		}
		acum.append(cantPaquetes);
		acum.append(" ]");
		return acum.toString();
	}

}
