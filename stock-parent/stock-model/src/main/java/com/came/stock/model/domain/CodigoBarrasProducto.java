package com.came.stock.model.domain;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.krysalis.barcode4j.impl.code128.EAN128;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.zkoss.image.AImage;

@Entity
@Table
public class CodigoBarrasProducto {
	private Long idCodigoBarrasProducto;
	private String codigo;
	private Producto producto;
	private boolean selected;
	private boolean activo;
	private byte[] barCode;
	private AImage barCodeAImage;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	public Long getIdCodigoBarrasProducto() {
		return this.idCodigoBarrasProducto;
	}

	public void setIdCodigoBarrasProducto(Long idCodigoBarrasProducto) {
		this.idCodigoBarrasProducto = idCodigoBarrasProducto;
	}

	@Column
	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "producto")
	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	@Transient
	public boolean isSelected() {
		return this.selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	@Column
	@Lob
	public byte[] getBarCode() {
		return barCode;
	}

	public void setBarCode(byte[] barCode) {
		this.barCode = barCode;
	}

	@Transient
	public AImage getBarCodeAImage() {
		if(codigo != null && !codigo.isEmpty()){
			barCode = barCodeEan128(codigo);
		}
		if (barCode != null) {
			try {
				barCodeAImage = new AImage("barCodeByte", barCode);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return barCodeAImage;
	}

	public void setBarCodeAImage(AImage barCodeAImage) {
		this.barCodeAImage = barCodeAImage;
	}

	@Column
	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
	
	public static byte[] barCodeEan128(String codigo) {
		EAN128 bean = new EAN128();

		final int dpi = 150;

		// Configure the barcode generator

		// Open output file
		ByteArrayOutputStream out = null;
		out = new ByteArrayOutputStream();
		try {
			// Set up the canvas provider for monochrome PNG output
			BitmapCanvasProvider canvas = new BitmapCanvasProvider(out, "image/x-png", dpi,
					BufferedImage.TYPE_BYTE_BINARY, false, 0);

			// Generate the barcode
			bean.generateBarcode(canvas, codigo);

			// Signal end of generation
			canvas.finish();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return out.toByteArray();
	}
}
