package cl.tamila.services;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

//Creamos el servicio
@Service
@Primary
public class QrCodeService {

	public byte[] crearQr(String text, int width, int height) throws WriterException, IOException 
	{
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		//Codifica la construccion del formato
		BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
		ByteArrayOutputStream pngOutput = new ByteArrayOutputStream();
		MatrixToImageConfig config = new MatrixToImageConfig(Color.BLACK.getRGB(), Color.WHITE.getRGB());
	
		//Convierte la config de la imagen para poder trabajar
		MatrixToImageWriter.writeToStream(bitMatrix, "PNG" , pngOutput);
		
		byte[] pngData = pngOutput.toByteArray();
		return pngData;
		
	}

}
