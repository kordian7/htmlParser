package application.parser;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import annotations.Column;
import annotations.Entity;

@Entity(name="book")

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Book {
	@Column(name="tytul")
	private String tytul;
	@Column
	private String autor;
	@Column
	private String ISBN;
	@Column
	private String wydawnictwo;
	@Column
	private int liczbaStron;
	@Column
	private String tlumaczenie;
	@Column
	private String jezyk;
	@Column
	private String kategoria;
	
	// TODO - zamiana
	@Column
	private String data;
	
	
	public String getTytul() {
		return tytul;
	}
	public void setTytul(String tytul) {
		this.tytul = tytul;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public String getWydawnictwo() {
		return wydawnictwo;
	}
	public void setWydawnictwo(String wydawnictwo) {
		this.wydawnictwo = wydawnictwo;
	}
	public int getLiczbaStron() {
		return liczbaStron;
	}
	public void setLiczbaStron(int liczbaStron) {
		this.liczbaStron = liczbaStron;
	}
	public String getTlumaczenie() {
		return tlumaczenie;
	}
	public void setTlumaczenie(String tlumaczenie) {
		this.tlumaczenie = tlumaczenie;
	}
	public String getJezyk() {
		return jezyk;
	}
	public void setJezyk(String jezyk) {
		this.jezyk = jezyk;
	}
	public String getKategoria() {
		return kategoria;
	}
	public void setKategoria(String kategoria) {
		this.kategoria = kategoria;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "Book [tytul=" + tytul + ", autor=" + autor + ", ISBN=" + ISBN + ", wydawnictwo=" + wydawnictwo
				+ ", liczbaStron=" + liczbaStron + ", tlumaczenie=" + tlumaczenie + ", jezyk=" + jezyk + ", kategoria="
				+ kategoria + ", data=" + data + "]";
	}
	
	
}
