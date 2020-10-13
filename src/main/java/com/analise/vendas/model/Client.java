package com.analise.vendas.model;

public class Client extends Entity {

    private String cnpj;
    private String name;
    private String businessArea;
    
    public Client() {
		super();
	}

	public Client(String cnpj, String name, String businessArea) {
        this.cnpj = cnpj;
        this.name = name;
        this.businessArea = businessArea;
    }

	public static synchronized Client create() {
		return new Client();
	}
	
	public Client withCnpj(String cnpj) {
        this.cnpj = cnpj;
        return this;
    }
	
	public Client withName(String name) {
        this.name = name;
        return this;
    }
	
	public Client withBusinessArea(String businessArea) {
        this.businessArea = businessArea;
        return this;
    }
	
    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBusinessArea() {
        return businessArea;
    }

    public void setBusinessArea(String businessArea) {
        this.businessArea = businessArea;
    }

}
