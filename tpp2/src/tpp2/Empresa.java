package tpp2;

import java.util.*;

public class Empresa {

	private String nombre;
	private HashMap<String, Viaje> viaje;
	private ArrayList<Deposito> deposito;
	private HashMap<String, Transporte> transporte;
	private String cuit;

	public Empresa(String cuit, String nombre) {
		if (nombre != "") {
			this.nombre = nombre;
		} else {
			throw new RuntimeException("El nombre no puede ser vacio");
		}
		if (cuit.length() > 7) {
			this.cuit = cuit;
		} else {
			throw new RuntimeException("El cuit no puede ser vacio");
		}
		this.deposito = new ArrayList<>();
		this.transporte = new HashMap<>();
		this.viaje = new HashMap<>();
	}

	public String empresaNombre() {
		return nombre;
	}

	public String dameCuit() {
		return cuit;
	}
	public Transporte dameTransporte(String numeroIdentificacion) {
		if(transporte.containsKey(numeroIdentificacion)) {
		return transporte.get(numeroIdentificacion);}
		else {
			throw new RuntimeException("No existe el transporte");
		}
		
	}
	
	public boolean reemplazoTransporte(String identificacion) {
		if (transporte.containsKey(identificacion)){
			Transporte t = transporte.get(identificacion);
			for (Transporte trans : transporte.values()) {
					t.cambiarTransporte(trans);
					return true;
			}
			return false;
		}
		return false;
 	}

	public int agregarDeposito(double capacidad, Boolean refrigeracion, Boolean propio) {
		Deposito depositoPropio = new Deposito(propio, capacidad, refrigeracion);
		deposito.add(depositoPropio);
		return depositoPropio.getNumeroDeposito();
	}

	public int agregarDepTercerizFrio(double capacidad, double costoPorTonelada) {
		Deposito depositoTerFrio = new Deposito(capacidad, costoPorTonelada);
		deposito.add(depositoTerFrio);
		return depositoTerFrio.getNumeroDeposito();
	}

	public boolean incorporarPaquete(String destino, double peso, double volumen, Boolean refrigeracion) {
		Paquete paqueteEntrante = new Paquete(destino, peso, volumen, refrigeracion);
		for (int i = 0; i < deposito.size(); i++) {
			if (deposito.get(i).agregarPaqueteDeposito(paqueteEntrante)) {
				return true;
			}
		}
		return false;
	}

	public void agregarFlete(String idTransp, double cargaMax, double capacidad, double costoKm, int acomp,
			double costoPorAcom) {
		Transporte flete = new Flete(idTransp, cargaMax, capacidad, costoKm, acomp, costoPorAcom);
		transporte.put(flete.getNumeroIdentificacion(), flete);

	}

	public void agregarTrailer(String idTransp, double cargaMax, double capacidad, boolean frigorifico, double costoKm,
			double segCarga) {
		Transporte trailerComun = new Comun(idTransp, cargaMax, capacidad, frigorifico, costoKm, segCarga);
		transporte.put(trailerComun.getNumeroIdentificacion(), trailerComun);
	}

	public void agregarMegaTrailer(String idTransp, double cargaMax, double capacidad, boolean frigorifico,
			double costoKm, double segCarga, double costoFijo, double comida) {
		Transporte trailerMega = new Mega(idTransp, cargaMax, capacidad, frigorifico, costoKm, segCarga, costoFijo,
				comida);
		transporte.put(trailerMega.getNumeroIdentificacion(), trailerMega);

	}

	public void agregarDestino(String destino, double distancia) {
		Viaje viajeNuevo = new Viaje(destino, distancia);
		viaje.put(destino, viajeNuevo);

	}

	public void asignarDestino(String numeroIdentificacion, String destino) {
		if (transporte.containsKey(numeroIdentificacion)) {
			Transporte t = transporte.get(numeroIdentificacion);
			if (viaje.containsKey(destino)) {
				Viaje v = viaje.get(destino);
				t.asignarDestino(destino, v.getDistancia());
			}
		} else {
			throw new RuntimeException("No existe ese transporte");
		}
	}

	public double cargarTransporte(String numeroIdentificacion) {
		double volumenDeTransporte = 0;
		if (transporte.containsKey(numeroIdentificacion)) {
			Transporte t = transporte.get(numeroIdentificacion);
			for (Deposito dep : deposito) {
				dep.cargarTransporte(t);
				volumenDeTransporte = t.getVolumenActual();
			}
		}
		else {
			throw new RuntimeException("No existe ese transporte");

		}
		return volumenDeTransporte;
	}

	public void iniciarViaje(String numeroIdentificacion) {
		if (transporte.containsKey(numeroIdentificacion)) {
			Transporte t = transporte.get(numeroIdentificacion);
			t.iniciarViaje();
		}
		else {
			throw new RuntimeException("No existe ese transporte");
		}
	}

	public void finalizarViaje(String numeroIdentificacion) {
		if (transporte.containsKey(numeroIdentificacion)) {
			Transporte t = transporte.get(numeroIdentificacion);
			t.terminarViaje();
		}
		else {
			throw new RuntimeException("No existe ese transporte");
		}
	}

	public double obtenerCostoViaje(String numeroIdentificacion) {
		if (transporte.containsKey(numeroIdentificacion)) {
			Transporte t = transporte.get(numeroIdentificacion);
			t.costoDeViajeTotal();
			return t.costoDeViajeTotal();
		} else {
			throw new RuntimeException("No se encontro ese micro");
		}
	}

	public String obtenerTransporteIgual(String idTransp) {
		if (transporte.containsKey(idTransp)) {
			Transporte t = transporte.get(idTransp);
			for (Transporte t2 : transporte.values()) {
				if (t.equals(t2) && !t.getNumeroIdentificacion().equals(t2.getNumeroIdentificacion())) {
					return t2.getNumeroIdentificacion();
				}
			}
		}
		else {
			throw new RuntimeException("No existe ese transporte");

		}
		return null;
	}
	
	@Override
	public String toString() {
		StringBuilder acum = new StringBuilder("[ ");
		acum.append("Nombre de la empresa: ");
		acum.append(empresaNombre());
		acum.append(", ");
		acum.append("Cuit de la empresa: ");
		acum.append(dameCuit());
		acum.append(" ]");
		acum.append("\n");
		acum.append("\n");
		acum.append("Todos los transportes:");
		acum.append("\n");
		int transporteFlete = 0;
		int transporteMega = 0;
		int transporteComun = 0;
		for (Map.Entry<String, Transporte> entry : transporte.entrySet()) {
			acum.append("\n");
			acum.append(entry.getValue().toString());
			acum.append("\n");
			if(entry.getValue() instanceof Flete && entry.getValue().getViajando()) {
				transporteFlete++;
			}
			if(entry.getValue() instanceof Mega && entry.getValue().getViajando()) {
				transporteMega++;
			}
			if(entry.getValue() instanceof Comun && entry.getValue().getViajando()) {
				transporteComun++;
			}
		}
		acum.append("\n");
		acum.append("Transportes Fletes en viaje: ");
		acum.append(transporteFlete);
		acum.append("\n");
		acum.append("\n");
		acum.append("Transportes Mega en viaje: ");
		acum.append(transporteMega);
		acum.append("\n");
		acum.append("\n");
		acum.append("Transportes Comunes en viaje: ");
		acum.append(transporteComun);
		acum.append("\n");
		int paquetesTercierizadosFrios = 0;
		int paquetesPropiosFrios = 0;
		for (Deposito deposito : deposito) {
			acum.append("\n");
			acum.append(deposito.toString());
			acum.append("\n");
			if(deposito.getPropio() == true && deposito.getRefrigeracion() == true) {
			paquetesPropiosFrios += deposito.contarPaquetes();
			}
			if(deposito.getPropio() == false && deposito.getRefrigeracion() == true) {
			paquetesTercierizadosFrios += deposito.contarPaquetes();
			}
		}
		acum.append("\n");
		acum.append("Paquetes tercierizados que necesitan frio: ");
		acum.append(paquetesTercierizadosFrios);
		acum.append("\n");
		acum.append("\n");
		acum.append("Paquetes propios que necesitan frio: ");
		acum.append(paquetesPropiosFrios);
		acum.append("\n");
		acum.append("\n");
		acum.append("Viajes : ");
		acum.append("\n");
		for (Map.Entry<String, Viaje> entry : viaje.entrySet()) {
			acum.append("\n");
			acum.append(entry.getValue().toString());
			acum.append("\n");
		}
		return acum.toString();
	}

}
