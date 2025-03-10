package clases;

public abstract class Persona {
	//Declaramos atributos
    private String rfc, nombrePersona, telefono, domicilio;
    //Constructor
    public Persona(String rfc, String name, String tel, String dom) {
        setRfc(rfc);
        setNombrePer(name);
        setTelefono(tel);
        setDomicilio(dom);
    }

    //Setters 
    public void setRfc(String rfc) {
        if (rfc.strip() != "") {
            //Acepta solo si no está vacio
            this.rfc = rfc;
        }
    }
    public void setNombrePer(String name) {
        if (name.strip() != "") {
            //Acepta solo si no está vacio
            this.nombrePersona = name;
        }
    }
    public void setTelefono(String phone) {
        if (phone.strip() != "") {
            //Acepta solo si no está vacio
            this.telefono = phone;
        }
    }
    public void setDomicilio(String address) {
        if (address.strip() != "") {
            //Acepta solo si no está vacio
            this.domicilio = address;
        }
    }
    //Getters 
    public String getRfc() {
        return rfc;
    }
    public String getNombrePer() {
        return nombrePersona;
    }
    public String getTelefono() {
        return telefono;
    }
    public String getDomicilio() {
        return domicilio;
    }
}
